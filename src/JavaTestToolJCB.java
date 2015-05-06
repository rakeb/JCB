import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class JavaTestToolJCB {
	byte[] prepareResponseData;
	byte[] tempBuffer;
	byte[] result;
	byte[] tranDataElement;
	byte[] keyDerivationData;
	byte[] sessionKey;
	byte[] outBuffer;
	boolean contactInterface;
	SecretKey tdes2Key;
	Cipher cipherECB;
	Cipher cipherCBC;
	IvParameterSpec ivParameterSpec;
	
	public JavaTestToolJCB() throws NoSuchAlgorithmException, NoSuchPaddingException{
		prepareResponseData = new byte[50];
		tempBuffer = new byte[100];
		result = new byte[19];
		tranDataElement = new byte[50];
		sessionKey = new byte[16];
		outBuffer = new byte[16];
		contactInterface = false;
		cipherECB = Cipher.getInstance("DESede/ECB/NoPadding");
		cipherCBC = Cipher.getInstance("DESede/CBC/NoPadding");
		ivParameterSpec= new IvParameterSpec(Helper.toByte("0000000000000000"));
		keyDerivationData = new byte[50]; 
	}
	
	public void dynamicMagStripComp() throws Exception{
		byte[] dgiBody = Helper.toByte("1122334455667788D20121231234511100002F");
		short offset;
		byte cvs = (byte)0x10;

		cvs += Constants.BIT_5; //Add higher nibble by 1;
		//Step A : Session Key Derivation 
		//Diversifier f1 : Byte 0 to 7
		offset=0;
//		JCBUtil.hexToBCD((short)3, tempBuffer, (short)33);
		tempBuffer[33] = (byte)0x00;
		tempBuffer[34] = (byte)0x16;
		tempBuffer[offset]= tempBuffer[33];
		tempBuffer[offset+1]= tempBuffer[34];
		offset +=2;
		tempBuffer[offset] = cvs;
		tempBuffer[offset] &= Constants.SECOND_NIBBLE_MAX_VALUE;
		offset++;
		tempBuffer[offset] = Constants.SECOND_NIBBLE_MAX_VALUE;
		offset++;
		Arrays.fill(tempBuffer, offset, offset+Constants.FOUR, Constants.ZERO);
		offset += 4;
		
		
		//Diversifier f2: Byte 8 to 15
		offset = 8;
		System.arraycopy(tempBuffer, Constants.ZERO, tempBuffer, offset, Constants.EIGHT);
		offset +=2;
		tempBuffer[offset] = cvs;
		tempBuffer[offset] |= Constants.FRIST_NIBBLE_MAX_VALUE;
		offset++;
		tempBuffer[offset] = Constants.FRIST_NIBBLE_MAX_VALUE;
		//------------------
		System.arraycopy(tempBuffer, 0, result, (short) 0, 16);
		System.out.println("Session Key Derivation Data: "+Helper.toHex(result));
		//------------------
		// SK derivation for Dynamic CAV
		byte[] tdesKeyData = Helper.toByte("404142434445464748494A4B4C4D4E4F4041424344454647");
		SecretKey tdes2Key = new SecretKeySpec(tdesKeyData, "DESede");
		Cipher cipherECB = Cipher.getInstance("DESede/ECB/NoPadding");
		Cipher cipherCBC = Cipher.getInstance("DESede/CBC/NoPadding");
		IvParameterSpec ivParameterSpec;
		ivParameterSpec= new IvParameterSpec(Helper.toByte("0000000000000000"));
		
		try{
			cipherECB.init(Cipher.ENCRYPT_MODE, tdes2Key);
		}catch(InvalidKeyException e){
			System.out.println("InvalidKeyException : "+e.getMessage());
		}
		
		cipherECB.doFinal(tempBuffer, Constants.ZERO, Constants.EIGHT, tempBuffer, Constants.ZERO);
		cipherECB.doFinal(tempBuffer, Constants.EIGHT, Constants.EIGHT, tempBuffer, Constants.EIGHT);
		//------------------
		System.arraycopy(tempBuffer, 0, result, (short) 0, 16);
		System.out.println("Session Key: "+Helper.toHex(result));
		//------------------
		//Input to dCAV Generation
		System.arraycopy(dgiBody, Constants.OFFSET_ZERO, prepareResponseData, Constants.ZERO, Constants.EIGHT);
		offset = 8;
		
		Arrays.fill(prepareResponseData, offset, offset+Constants.EIGHT, Constants.ZERO);
		//Copy YYMM of Mag-Stripe Track 2 Data
		prepareResponseData[offset] = (byte) (dgiBody[8]<<4);
		prepareResponseData[offset] |= (byte)((dgiBody[9]>>4)& Constants.FRIST_NIBBLE_MAX_VALUE);
		offset+=1;
		prepareResponseData[offset] = (byte) ((dgiBody[9]<<4)& Constants.SECOND_NIBBLE_MAX_VALUE);
		prepareResponseData[offset] |= (byte)((dgiBody[10]>>4)& Constants.FRIST_NIBBLE_MAX_VALUE);
		offset+=1;
		
		//Copy Service Code of Mag-Stripe Track 2 Data
		prepareResponseData[offset] = (byte) ((dgiBody[10]<<4)& Constants.SECOND_NIBBLE_MAX_VALUE);
		prepareResponseData[offset] |= (byte)((dgiBody[11]>>4)& Constants.FRIST_NIBBLE_MAX_VALUE);
		offset+=1;
		prepareResponseData[offset] = (byte) ((dgiBody[11]<<4)& Constants.SECOND_NIBBLE_MAX_VALUE);
		
		//Padding with '000000000'
		prepareResponseData[offset] &= Constants.SECOND_NIBBLE_MAX_VALUE;
		offset+=1;
		Arrays.fill(prepareResponseData, offset, offset+Constants.FOUR, Constants.ZERO);
		offset += 4; //Offset : 16
		//------------------
		System.arraycopy(prepareResponseData, 0, result, (short) 0, 16);
		System.out.println("K1 Derivation Data: "+Helper.toHex(result));
		//------------------
		//Single DES 
		SecretKey desKey = new SecretKeySpec(tempBuffer, Constants.ZERO, 8,  "DES");new SecretKeySpec(tempBuffer, "DES");
		cipherECB = Cipher.getInstance("DES/ECB/NoPadding");
		cipherECB.init(Cipher.ENCRYPT_MODE, desKey);
		cipherECB.doFinal(prepareResponseData, Constants.ZERO, Constants.EIGHT, prepareResponseData, Constants.ZERO);
		
		//Final TDES
		System.arraycopy(tempBuffer, Constants.OFFSET_ZERO, tempBuffer, 16, Constants.EIGHT);
		tdes2Key = new SecretKeySpec(tempBuffer, Constants.ZERO, 24,  "DESede");
		
		ivParameterSpec= new IvParameterSpec(prepareResponseData, Constants.ZERO, Constants.EIGHT);
		cipherCBC.init(Cipher.ENCRYPT_MODE, tdes2Key, ivParameterSpec);
		cipherCBC.doFinal(prepareResponseData, Constants.EIGHT, Constants.EIGHT, prepareResponseData, Constants.ZERO);
		//------------------
		System.arraycopy(prepareResponseData, 0, result, (short) 0, 8);
		System.out.println("K1 Derivation: "+Helper.toHex(result));
		//------------------
		convertIntoDecimal();
		//------------------
		System.arraycopy(tempBuffer, 0, result, (short) 0, 8);
		System.out.println("K2 Derivation: "+Helper.toHex(result));
		//------------------
		
		System.arraycopy(dgiBody, Constants.ZERO, tempBuffer, Constants.TWO, (short)19);
		
		//Overwrite the Mag-stripe Track 2 CAV withd dCAV
		tempBuffer[16] &= Constants.SECOND_NIBBLE_MAX_VALUE;
		tempBuffer[16] |= (tempBuffer[0]>>4)& Constants.FRIST_NIBBLE_MAX_VALUE; 
		tempBuffer[17] = (byte) ((tempBuffer[0]<<4)& Constants.SECOND_NIBBLE_MAX_VALUE);
		tempBuffer[17] |= (tempBuffer[1]>>4)& Constants.FRIST_NIBBLE_MAX_VALUE; 
		
		//Overwrite the Mag-stripe Track 2 Discretionery Data ATC
		System.arraycopy(tempBuffer, (short)33, tempBuffer, (short) 18, Constants.TWO);
		
		tempBuffer[20] = (byte) (cvs | Constants.FRIST_NIBBLE_MAX_VALUE);
		
		//------------------
		System.arraycopy(tempBuffer, 2, result, (short) 0, 19);
		System.out.println("Response Data: "+Helper.toHex(result));
		//------------------
	}

	public void pinEncipher(byte[] stringModulus, byte[]  stringRSAPubExp, String iccUPN, String pinBlock) throws Exception{
		/*RSA KeyPair (1024 bit) - START*/
//		stringModulus = "8ecd7acce5811bfc9dd09f049dda81bf49276bd6d69bd715ce6322b60c03e9bf1bfd87f04a9452f5108ee9fea8155511424e2d007f3f7d08c43e68d5f09b83bea5bf90c963edaf9a9ee51a0f9c79dbbbf342d4f696d1a1da26e7e6ae59862af1b1ef5bb3a9e706f4567be07fea3eb8f3831e4b729bdb7a5ae2299061665bd12b";
//		stringRSAPubExp = "010001";
//		String stringRSAPriExp = "48e5aded50af22c9470a8b5280fe5d4377a58f1041c795f984bce8a843e3ef7be65efaf8f829555bd29311f2347d874a632da31d60d7d90d1398c44d8a20e91432fb5165bdabcf88f428ba5b924d69e944e24b5921ec172ffc6a2c7f4e660da7f890f525056eedba904c8ae43482541002d541c21192ca6028361832bbb6d3b1";
//		String   stringPQ  = "7cc97a4181ff8e07d30431b3f918dabf7fd07cbed0b340b975a4b15ddc61be7a571385824931d312afbb96198872db041f5159042b0a77e5a0b822a8642e18e7";
//		String    stringDQ1 = "44d7dfaa875a3e71dc82bae6d94f37dd505e4a20769ddf9b0251a01b543e001519ae2455c110d8a48e5f130d6f456828c8d881d213ddd2b23713d95ef192ccf1";
//		String  stringDP1 = "9e12268ef016acf6960e19b4ccfbbc65cbf895de4bd9ec75bbefa10f98e0b3d9902dc69cd25fccb12eddc69507b4a5fba85524271dce4d29453d41e3aacaf0e3";
//		String    stringQ   = "b63c39d8bd2621d5788271cc77981d69a84fd9ef81412fad4c95d513eb2b09f018301f7962f0b1931684539835a1e636baebbd84edd232ff6b2a5643e71e7ce9";
//		String    stringP   = "c89b2082aaf2170ec5d96bcd0d61e60fa31caad2b428ca4bbd5749c40943006497fcd2430eae80965cd85804138a43366d82c05098794a45eb75a5b6590940f3";
		
		BigInteger modulus = new BigInteger(stringModulus);
		BigInteger publicExponent= new BigInteger(stringRSAPubExp);
//		BigInteger privateExponent= new BigInteger(stringRSAPriExp, 16);
//		BigInteger crtCoefficient = new BigInteger(stringPQ, 16);
//		BigInteger primeExponentQ = new BigInteger(stringDQ1, 16);
//		BigInteger primeExponentP = new BigInteger(stringDP1, 16);
//		BigInteger primeQ = new BigInteger(stringQ, 16);
//		BigInteger primeP = new BigInteger(stringP, 16);
		
		RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(modulus, publicExponent);
//		RSAPrivateCrtKeySpec privateKeySpec = new RSAPrivateCrtKeySpec(modulus, publicExponent, privateExponent, primeP, primeQ, primeExponentP, primeExponentQ, crtCoefficient);
		
		KeyFactory factory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = factory.generatePublic(publicKeySpec);
//		PrivateKey privateKey = factory.generatePrivate(privateKeySpec);
		/*RSA KeyPair (1024 bit) - END*/
		
//		String iccUPN = " D4D73BAC1181FAFD";
		
		String headerTAG = "7F";
//		String pinBlock = "2512345FFFFFFFFF";
		String padding = "276bd6d69bd715ce6322b60c03e9bf1bfd87f04a9452f5108ee9fea8155511424e2d007f3f7d08c43e68d5f09b83bea5bf90c963edaf9a9ee51a0f9c79dbbbf342d4f696d1a1da26e7e6ae59862af1b1ef5bb3a9e706f4567be07fea3eb8f3831e4b729bdb7a5ae2299061665bd12b";
		
		String plainData = headerTAG + pinBlock + iccUPN + padding;
		
		Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] out= cipher.doFinal(Helper.toByte(plainData));
		System.out.println("Result = "+Helper.toHex(out) + "\n\n");
		
//		cipher = Cipher.getInstance("RSA/ECB/NoPadding");
//		cipher.init(Cipher.DECRYPT_MODE, privateKey);
//		byte[] plainText= cipher.doFinal(out);
//		System.out.println("plainText = "+Helper.toHex(plainText));
	}
	
	private void convertIntoDecimal(){
		short i=0, nibble=0;
		byte temp;
		for(i=0; i<Constants.EIGHT; i++){
			temp = (byte) (prepareResponseData[i] >>4 & (0x0F));
			if(temp < Constants.TEN){
				if(nibble%2 == 0){
					tempBuffer[nibble/2] = (byte) (temp <<4);
					++nibble;
				}else{
					tempBuffer[nibble/2] |= temp;
					++nibble;
				}
			}
			
			temp = (byte) (prepareResponseData[i]& (0x0F));
			if(temp < Constants.TEN){
				if(nibble%2 == 0){
					tempBuffer[nibble/2] = (byte) (temp <<4);
					++nibble;
				}else{
					tempBuffer[nibble/2] |= temp;
					++nibble;
				}
			}
		}
		
		for(i=0; i<Constants.EIGHT; i++){
			temp = (byte) (prepareResponseData[i] >>4 & (0x0F));
			if(temp > Constants.NINE){
				if(nibble%2 == 0){
					tempBuffer[nibble/2] = (byte) ((temp - Constants.TEN) <<4);
					++nibble;
				}else{
					tempBuffer[nibble/2] |= (temp - Constants.TEN);
					++nibble;
				}
			}
			
			temp = (byte) (prepareResponseData[i]& (0x0F));
			if(temp > Constants.NINE){
				if(nibble%2 == 0){
					//temp = (byte) (temp - Constants.TEN);
					tempBuffer[nibble/2] = (byte) ((temp - Constants.TEN) <<4);
					++nibble;
				}else{
					tempBuffer[nibble/2] |= (temp - Constants.TEN);
					++nibble;
				}
			}
		}
	}
	
//	private void getMagStripeData(){
//		byte[] convertedAmount = Helper.toByte("100000001583");
//		byte[]  tempCTTA =  Helper.toByte("000000002864");
//		byte[] result = new byte[6];
//		JCBUtil.bcdSubtraction2(convertedAmount, (short)0, tempCTTA,  (short)0,  result,  (short)0,  (short)6);
////		byte carry = JCBUtil.bcdAddition(convertedAmount, (short)0, tempCTTA,  (short)0,  (short)2);
//		System.out.println("Result  = "+Helper.toHex(result));
//		short number = (short)0x7FFF;
//		JCBUtil.hexToBCD(number, result, Constants.OFFSET_ZERO);
//		System.out.println("HEX2BCD  = "+Helper.toHex(result));
//		
//		byte[] prepareResponseData= new byte[8];
//		byte[] dgiBody = {(byte) 0xD1, 0x50, 0x41, 0x23};
//		short offset =0;
//		//Copy YYMM of Mag-Stripe Track 2 Data
//		prepareResponseData[offset] = (byte) (dgiBody[0]<<4);
//		prepareResponseData[offset] |= (byte)((dgiBody[1]>>4)& Constants.FRIST_NIBBLE_MAX_VALUE);
//		offset+=1;
//		prepareResponseData[offset] = (byte) ((dgiBody[1]<<4)& Constants.SECOND_NIBBLE_MAX_VALUE);
//		prepareResponseData[offset] |= (byte)((dgiBody[2]>>4)& Constants.FRIST_NIBBLE_MAX_VALUE);
//		offset+=1;
//		
//		//Copy Service Code of Mag-Stripe Track 2 Data
//		prepareResponseData[offset] = (byte) ((dgiBody[2]<<4)& Constants.SECOND_NIBBLE_MAX_VALUE);
//		prepareResponseData[offset] |= (byte)((dgiBody[3]>>4)& Constants.FRIST_NIBBLE_MAX_VALUE);
//		offset+=1;
//		prepareResponseData[offset] = (byte) ((dgiBody[3]<<4)& Constants.SECOND_NIBBLE_MAX_VALUE);
//		
//		//Padding with '000000000'
//		prepareResponseData[offset] &= Constants.SECOND_NIBBLE_MAX_VALUE;
//		offset+=1;
//		Arrays.fill(prepareResponseData, offset, Constants.FOUR, Constants.ZERO);
//		offset += 4;
//		System.out.println("Prepar Data  = "+Helper.toHex(prepareResponseData));
//		
//		byte[] prepareData = Helper.toByte("1234567812345678");
//		prepareResponseData = Arrays.copyOfRange(prepareData, Constants.ZERO, Constants.EIGHT);
//		System.out.println("Prepar Data  = "+Helper.toHex(prepareResponseData));
//		byte[] tempBuffer = new byte[8];
//		short i, nibble=0;
//		byte temp;
//		for(i=0; i<Constants.EIGHT; i++){
//			temp = (byte) (prepareResponseData[i] >>4 & (0x0F));
//			if(temp < Constants.TEN){
//				if(nibble%2 == 0){
//					tempBuffer[nibble/2] = (byte) (temp <<4);
//					++nibble;
//				}else{
//					tempBuffer[nibble/2] |= temp;
//					++nibble;
//				}
//			}
//			
//			temp = (byte) (prepareResponseData[i]& (0x0F));
//			if(temp < Constants.TEN){
//				if(nibble%2 == 0){
//					tempBuffer[nibble/2] = (byte) (temp <<4);
//					++nibble;
//				}else{
//					tempBuffer[nibble/2] |= temp;
//					++nibble;
//				}
//			}
//		}
//		
//		for(i=0; i<Constants.EIGHT; i++){
//			temp = (byte) (prepareResponseData[i] >>4 & (0x0F));
//			if(temp > Constants.NINE){
//				if(nibble%2 == 0){
//					tempBuffer[nibble/2] = (byte) ((temp - Constants.TEN) <<4);
//					++nibble;
//				}else{
//					tempBuffer[nibble/2] |= (temp - Constants.TEN);
//					++nibble;
//				}
//			}
//			
//			temp = (byte) (prepareResponseData[i]& (0x0F));
//			if(temp > Constants.NINE){
//				if(nibble%2 == 0){
//					//temp = (byte) (temp - Constants.TEN);
//					tempBuffer[nibble/2] = (byte) ((temp - Constants.TEN) <<4);
//					++nibble;
//				}else{
//					tempBuffer[nibble/2] |= (temp - Constants.TEN);
//					++nibble;
//				}
//			}
//		}
//		
//
//	}

	
	public int messageEncDec(byte[] inputData, short inOffset, byte dataLength, boolean encrytionMode) throws Exception {
		short offset = 0;
		short len = (short) (dataLength & (short) 0x00FF);
		short leftByteLen;
		int resopnseLen;
		if (encrytionMode) {
			tempBuffer[offset] = dataLength;
			offset++;
			// ATC set with zeroes
			System.arraycopy(inputData, Constants.OFFSET_ZERO, tempBuffer, offset, len);
			offset += len;
			leftByteLen = (short) (offset % 8);
			if (leftByteLen != 0) {
				tempBuffer[offset] = Constants.BYTE_MSB;
				offset++;
				leftByteLen = (short) (7 - leftByteLen);
				Arrays.fill(tempBuffer, offset, offset+leftByteLen, Constants.BYTE_VALUE_ZERO);
				offset += leftByteLen;
			}
			cipherECB.init(Cipher.ENCRYPT_MODE, tdes2Key);
			resopnseLen = cipherECB.doFinal(tempBuffer, Constants.OFFSET_ZERO, offset, tempBuffer, Constants.OFFSET_ZERO);
		} else {
			cipherECB.init(Cipher.DECRYPT_MODE, tdes2Key);
			resopnseLen = cipherECB.doFinal(inputData, inOffset, (short) dataLength, tempBuffer,
					Constants.OFFSET_ZERO);
			
		}
		return resopnseLen;
	}
	
	
	
	public byte[] getResult(){
//		System.arraycopy(tempBuffer, Constants.OFFSET_ZERO, outBuffer, 0, 16);
//		return outBuffer;
		return tempBuffer;
	}
	
	public void initMethod(byte[] key, int offset, int len,  String keyType){
		tdes2Key = new SecretKeySpec(key, offset, len,  keyType);
	}
}
