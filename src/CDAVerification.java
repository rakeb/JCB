import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

/**
 * @author Mazharul Islam Rakeb (mazharul.islam@konasl.com)
 *
 */
public class CDAVerification {	
	public static boolean verifyCDA(int AC, byte[] UN, byte[] PDOL, byte[] CDOL1, byte[] CDOL2, byte[] cdaResponse, byte[] rsaMOD, byte[]  rsaEXP, 
			String rsaCipherALG) throws Exception {
		//Extracting CDA from cdaResponse
		byte []tag = {(byte) 0x9F, (byte)0x4B};
		int nicOffset = Utility.indexOfSubArray(cdaResponse, tag);
		int nic = cdaResponse[nicOffset + 2] & (short)0x00FF;
		byte []cda;
		if (nic > 128) {
			nic = cdaResponse[nicOffset + 3] & (short)0x00FF;
			cda = Arrays.copyOfRange(cdaResponse, nicOffset + 4, nic + nicOffset + 4);
		} else {
			cda = Arrays.copyOfRange(cdaResponse, nicOffset + 3, nic + nicOffset + 3);
		}
		
		System.out.println("cda: " + Helper.toHex(cda));
		 
		//EMV book 2 (6.6.2)
		//1
		if((cda.length != rsaMOD.length) && (cda.length != rsaMOD.length -1)) {
			return false;
		}
		
		byte[] plainText = null;
		try {
			RSAPublicKey rsaPublicKey = (RSAPublicKey) Utility.generatePublicKey(rsaMOD, rsaEXP);		
			plainText = Utility.rsaPublicKeyDecrypt(rsaPublicKey, rsaCipherALG, cda);
			System.out.println("plainText: " + Helper.toHex(plainText));
		} catch (Exception e) {
			System.out.println("CDA recovery failed.\n");
			return false;
		}
		
		//2
		if(plainText[plainText.length -1] != (byte)0xBC) {
			return false;
		}
		//3
		if(plainText[0] != (byte)0x6A) {
			return false;
		}		
		//4
		if(plainText[1] != (byte)0x05) {
			return false;
		}
		//5-6
		tag[1] = (byte) 0x27;
		int offset = Utility.indexOfSubArray(cdaResponse, tag);
		if(plainText[13] != cdaResponse[offset + 3]) {
			return false;
		}
		
		//7,8,9
		byte[] hashFromCDA = Arrays.copyOfRange(plainText, plainText.length -21, plainText.length -1);	
		byte[] messageToHash = new byte[plainText.length -22 + 4];		
		System.arraycopy(plainText, 1, messageToHash, 0, plainText.length -22);
		System.arraycopy(UN, 0, messageToHash, plainText.length -22, 4);
		
		byte[] hash = Utility.generateHASH("SHA", messageToHash);		
		if (!Arrays.equals(hashFromCDA, hash)) {
			return false;
		}
		
		//10,11,12
		System.arraycopy(plainText, 22, hashFromCDA, 0, 20);
		System.out.println("hashFromCDA: " + Helper.toHex(hashFromCDA));
		offset = 0;
		if (PDOL != null) {
			System.arraycopy(PDOL, 0, messageToHash, 0, PDOL.length);
			offset += PDOL.length;
		}
		System.arraycopy(CDOL1, 0, messageToHash, offset, CDOL1.length);
		offset += CDOL1.length;
		
		if (AC == 2) {
			System.arraycopy(CDOL2, 0, messageToHash, offset, CDOL2.length);
			offset += CDOL2.length;
		}
		
		tag[1] = (byte) 0x27;
		int startOffset = Utility.indexOfSubArray(cdaResponse, tag);
		tag[1] = (byte) 0x4B;
		int endOffset = Utility.indexOfSubArray(cdaResponse, tag);
		int len = endOffset - startOffset;
		
		System.arraycopy(cdaResponse, startOffset, messageToHash, offset, len);
		offset += len;
		
		len = cdaResponse.length  - (endOffset + nic + 4);
		System.arraycopy(cdaResponse, endOffset + nic + 4, messageToHash, offset, len);
		offset += len;
		
		System.out.println("last msg to hash: " + Helper.toHex(messageToHash));
		
		byte[] digestData = new byte[offset];
		System.arraycopy(messageToHash, 0, digestData, 0, offset);
		
		System.out.println("digestData: " + Helper.toHex(digestData));
		
		hash = Utility.generateHASH("SHA", digestData);
		System.out.println("hash: " + Helper.toHex(hash));
		if (Arrays.equals(hashFromCDA, hash)) {
			System.out.println("AC From CDA : " + Helper.toHex(plainText, 14, 8));
			return true;
		} else {
			return false;
		}
		
	}
}
