import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @author Mazharul Islam Rakeb (mazharul.islam@konasl.com)
 *
 */
public class RsaCrtGenerator {


	public static byte[] hexToByte(String s){
		
		int len = s.length();
		byte[] data = new byte[len/2];
		
		for(int i=0;i<len;i+=2){			
			data[i/2] = (byte) ( (Character.digit(s.charAt(i), 16)<<4) + Character.digit(s.charAt(i+1), 16)  );
		}
		return data;
	} 
	
	
	public static String byteToHex(byte[] data){
		String r = "";
	    for(int i=0;i<data.length;i++){
	    	r+=Integer.toString(  (data[i] & 0xff) + 0x100,16).substring(1);
	    }	
		return r;
	}
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
	  
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		
		KeyPair keyPair = keyGen.generateKeyPair();
		
		RSAPublicKey publicKey = (RSAPublicKey ) keyPair.getPublic();
		RSAPrivateCrtKey privateKey = (RSAPrivateCrtKey) keyPair.getPrivate();
		
		System.out.println("Public Modulus length: "+  publicKey.getModulus().bitLength()/8);
		System.out.println("Public Modulus: "+  byteToHex(publicKey.getModulus().toByteArray()));
		System.out.println("Public Exponent length: "+publicKey.getPublicExponent().toByteArray().length);
		System.out.println("Public Exponent: "+byteToHex(publicKey.getPublicExponent().toByteArray()));
		
		System.out.println("Private Modulus length: "+privateKey.getModulus().bitLength()/8);
		System.out.println("Private Modulus: "+byteToHex(privateKey.getModulus().toByteArray()));
		
		System.out.println("Private Private Exponent: "+byteToHex(privateKey.getPrivateExponent().toByteArray()));
		System.out.println("Private Prime Exponent DP : "+byteToHex(privateKey.getPrimeExponentP().toByteArray())); // d mod (p-1)
		System.out.println("Private Prime Exponent DQ: "+byteToHex(privateKey.getPrimeExponentQ().toByteArray())); // d mod (q-1)
		
		System.out.println("Private P length: "+privateKey.getPrimeP().bitLength()/8);
		System.out.println("Private Prime P: "+byteToHex(privateKey.getPrimeP().toByteArray())); // P
		
		System.out.println("Private Q length: "+privateKey.getPrimeQ().bitLength()/8);
		System.out.println("Private Prime Q: "+byteToHex(privateKey.getPrimeQ().toByteArray())); // Q
		
		System.out.println("Private Coefficient PQ : "+byteToHex(privateKey.getCrtCoefficient().toByteArray()));  // PQ
		
		
		

	}


}
