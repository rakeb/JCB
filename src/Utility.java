import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;
import java.util.Collections;

import javax.crypto.Cipher;

/**
 * 
 */

/**
 * @author Mazharul Islam Rakeb (mazharul.islam@konasl.com)
 *
 */
public class Utility {
	public static PublicKey generatePublicKey(byte[] rsaMOD, byte[]  rsaEXP) throws Exception {
		BigInteger modBigInteger = new BigInteger(rsaMOD);
		BigInteger exBigInteger = new BigInteger(rsaEXP);
		
		RSAPublicKeySpec spec = new RSAPublicKeySpec(modBigInteger, exBigInteger);
		KeyFactory factory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = factory.generatePublic(spec);
		
		return publicKey;
	}
	
	public static RSAPrivateCrtKey privateCRTKeyGenerator() throws Exception {
		String stringModulus = "8ecd7acce5811bfc9dd09f049dda81bf49276bd6d69bd715ce6322b60c03e9bf1bfd87f04a9452f5108ee9fea8155511424e2d007f3f7d08c43e68d5f09b83bea5bf90c963edaf9a9ee51a0f9c79dbbbf342d4f696d1a1da26e7e6ae59862af1b1ef5bb3a9e706f4567be07fea3eb8f3831e4b729bdb7a5ae2299061665bd12b";
		String stringRSAPubExp = "010001";
		String stringRSAPriExp = "48e5aded50af22c9470a8b5280fe5d4377a58f1041c795f984bce8a843e3ef7be65efaf8f829555bd29311f2347d874a632da31d60d7d90d1398c44d8a20e91432fb5165bdabcf88f428ba5b924d69e944e24b5921ec172ffc6a2c7f4e660da7f890f525056eedba904c8ae43482541002d541c21192ca6028361832bbb6d3b1";
		String   stringPQ  = "7cc97a4181ff8e07d30431b3f918dabf7fd07cbed0b340b975a4b15ddc61be7a571385824931d312afbb96198872db041f5159042b0a77e5a0b822a8642e18e7";
		String    stringDQ1 = "44d7dfaa875a3e71dc82bae6d94f37dd505e4a20769ddf9b0251a01b543e001519ae2455c110d8a48e5f130d6f456828c8d881d213ddd2b23713d95ef192ccf1";
		String  stringDP1 = "9e12268ef016acf6960e19b4ccfbbc65cbf895de4bd9ec75bbefa10f98e0b3d9902dc69cd25fccb12eddc69507b4a5fba85524271dce4d29453d41e3aacaf0e3";
		String    stringQ   = "b63c39d8bd2621d5788271cc77981d69a84fd9ef81412fad4c95d513eb2b09f018301f7962f0b1931684539835a1e636baebbd84edd232ff6b2a5643e71e7ce9";
		String    stringP   = "c89b2082aaf2170ec5d96bcd0d61e60fa31caad2b428ca4bbd5749c40943006497fcd2430eae80965cd85804138a43366d82c05098794a45eb75a5b6590940f3";
		
		BigInteger modulus = new BigInteger(stringModulus, 16);
		BigInteger publicExponent= new BigInteger(stringRSAPubExp, 16);
		BigInteger privateExponent= new BigInteger(stringRSAPriExp, 16);
		BigInteger crtCoefficient = new BigInteger(stringPQ, 16);
		BigInteger primeExponentQ = new BigInteger(stringDQ1, 16);
		BigInteger primeExponentP = new BigInteger(stringDP1, 16);
		BigInteger primeQ = new BigInteger(stringQ, 16);
		BigInteger primeP = new BigInteger(stringP, 16);
		
		RSAPrivateCrtKeySpec privateKeySpec = new RSAPrivateCrtKeySpec(modulus, publicExponent, privateExponent, primeP, primeQ, primeExponentP, primeExponentQ, crtCoefficient);
		
		KeyFactory factory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = factory.generatePrivate(privateKeySpec);
		
		return (RSAPrivateCrtKey) privateKey;
	}
	
	public static byte[] rsaPublicKeyDecrypt(RSAPublicKey rsaPublicKey, String rsaCipherALG, byte[] input) throws Exception {
		Cipher cipher = Cipher.getInstance(rsaCipherALG);
		cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);
		byte[]  decMsg= cipher.doFinal(input);
		
		return decMsg;
	}
	
	public static byte[] rsaCRTPrivateKeyEncrypt(RSAPrivateCrtKey rsaPrivateCrtKey, String rsaCipherALG, byte[] input) throws Exception {
		Cipher cipher = Cipher.getInstance(rsaCipherALG);
		cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateCrtKey);
		byte[]  encMsg= cipher.doFinal(input);
		
		return encMsg;
	}
	public static byte[] generateHASH(String hashALG, byte[] input) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance(hashALG);
		byte [] hash = messageDigest.digest(input);
		
		return hash;		
	}
	
	public static int findArray(byte[] cdaResponse, byte[] tag)
	{
	    return Collections.indexOfSubList(Arrays.asList(cdaResponse), Arrays.asList(tag));
	}

	public static int indexOfSubArray(byte[] array, byte[] subArray)
	{
		int arrayLen = array.length;
		int subArrayLen = subArray.length;
		int limit = arrayLen - subArrayLen;
		int i,j;
//		int flagCounter = 0;
		if (subArrayLen <= 0) {
			return -1;
		}
	    for (i = 0; i <limit; i++ ) {
	    	for (j = 0; j< subArrayLen; j++) {
	    		if(subArray[j] != array[i + j]) {
	    			break;
	    		}
	    	}
	    	if (j == subArrayLen)
	    		return i;
	    }
		return -1;
	}
}
