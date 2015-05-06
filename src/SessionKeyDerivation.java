import java.security.Key;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * @author Mazharul Islam Rakeb (mazharul.islam@konasl.com)
 *
 */
public class SessionKeyDerivation {
	private static SecretKeySpec secretKeySpec;
	private static Cipher cipher;
	static IvParameterSpec ivParameterSpec = new IvParameterSpec(Helper.toByte("0000000000000000"));
	public static byte[] derivedSessionKey(int CVN, byte [] ATC, byte[] UniqueKey) throws Exception {
		int i;
		byte [] sessionKey = new byte [24];
		byte [] data = new byte [8];
		
		if (CVN == 1 || CVN == 2) { // DUAL-Interface 5.1.6
			data[6] = ATC[0]; 
			data[7] = ATC[1]; 
			for(i =0; i<8; i++) {
				sessionKey[i] = (byte) (data[i] ^ UniqueKey[i]);
			}
			
			Arrays.fill(data, (byte)0x00);
			data[6] = (byte) ((byte)0xFF - ATC[0]); 
			data[7] = (byte) ((byte)0xFF - ATC[1]); 
			
			for(; i<16; i++) {
				sessionKey[i] = (byte) (data[i - 8] ^ UniqueKey[i]);
			}
			System.arraycopy(sessionKey, 0, sessionKey, 16, 8);
			return sessionKey;
		} else if(CVN == 4) { // EMV A1.3.1
			secretKeySpec = new SecretKeySpec(UniqueKey, "TripleDES");
			cipher = Cipher.getInstance("TripleDES/CBC/Nopadding");
			cipher.init(Cipher.ENCRYPT_MODE, (Key) secretKeySpec, ivParameterSpec);
			
			Arrays.fill(data, (byte)0x00);
			data[0] = ATC[0]; 
			data[1] = ATC[1];
			data[2] = (byte)0xF0;
			
			
			cipher.doFinal(data, 0, 8, sessionKey, 0);
			
			data[2] = (byte)0x0F;
			
			cipher.doFinal(data, 0, 8, sessionKey, 8);
			
			System.arraycopy(sessionKey, 0, sessionKey, 16, 8);
			return sessionKey;
		} else {
			return null;
		}
	}
}
