import java.security.Key;
import java.util.Arrays;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * @author Mazharul Islam Rakeb (mazharul.islam@konasl.com)
 *
 */
public class ARPCgenerator {	
	private static SecretKeySpec secretKeySpec;
	private static Cipher cipher;
	static IvParameterSpec ivParameterSpec;
	
	public static int CVN;
	public static byte[] ARC;
	public static byte[] ARQC;
	public static byte[] CSU;
	public static byte[] PAD;
	public static byte[] UniqueKey;
	public static byte[] AC;
	public static byte[] ATC;
	
	public ARPCgenerator() throws Exception {
		Properties properties = Main.getProperties(); 
		
		CVN 		= Integer.parseInt(properties.getProperty("CVN"), 10);
		ARC 		= Helper.toByte(properties.getProperty("ARC"));
		ARQC 		= Helper.toByte(properties.getProperty("ARQC"));
		CSU 		= Helper.toByte(properties.getProperty("CSU"));
		PAD 		= Helper.toByte(properties.getProperty("PAD"));
		UniqueKey 	= Helper.toByte(properties.getProperty("UniqueKey"));
		AC 			= Helper.toByte(properties.getProperty("AC"));
		ATC 		= Helper.toByte(properties.getProperty("ATC"));
		
		ivParameterSpec = new IvParameterSpec(Helper.toByte("0000000000000000"));
	}
	public byte[] generateARPC() throws Exception {
		int i;
		byte [] data = new byte [8];
		byte [] sessionKey = null;
		byte [] sessionKeySL = new byte [8];
		byte [] arpc = new byte [8];
		switch (CVN) {
		case 1: // DUAL-Interface 5.1.8
			sessionKey = UniqueKey;
		case 2:
			Arrays.fill(data, (byte)0x00);
			data[0] = ARC[0]; 
			data[1] = ARC[1];
			
			for(i =0; i<8; i++) {
				data[i] = (byte) (data[i] ^ AC[i]);
			}
			
			if (sessionKey == null) {
				sessionKey = SessionKeyDerivation.derivedSessionKey(CVN, ATC, UniqueKey);
			}
			secretKeySpec = new SecretKeySpec(sessionKey, "TripleDES");
			cipher = Cipher.getInstance("TripleDES/CBC/Nopadding");
			cipher.init(Cipher.ENCRYPT_MODE, (Key) secretKeySpec, ivParameterSpec);
			cipher.doFinal(data, 0, 8, arpc, 0);
			
			return arpc; 
		case 4: // // EMV 8.2.2 & A1.2
			byte [] data2 = null;
			int ARQCLength = ARQC.length;
			int CSULength = CSU.length;
			int PADLength = PAD.length;
			int totalLen = ARQCLength + CSULength + PADLength;
			
			if(totalLen % 8 != 0) {
				data2 = new byte [totalLen + totalLen % 8];
			} else {
				data2 = new byte [totalLen + 8];
			}
			
			int data2Len = data2.length;
			
			System.arraycopy(ARQC, 0, data2, 0, ARQCLength);
			System.arraycopy(CSU, 0, data2, ARQCLength, CSULength);
			System.arraycopy(PAD, 0, data2, ARQCLength + CSULength, PADLength);
			
			data2[totalLen] = (byte)0x80;
			
			sessionKey = SessionKeyDerivation.derivedSessionKey(CVN, ATC, UniqueKey);
//			sessionKey = UniqueKey;
			System.arraycopy(sessionKey, 0, sessionKeySL, 0, 8);
			
			secretKeySpec = new SecretKeySpec(sessionKeySL, "DES");
			cipher = Cipher.getInstance("DES/CBC/Nopadding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
			cipher.doFinal(data2, 0, data2Len - 8, data2, 0);
			
			secretKeySpec = new SecretKeySpec(sessionKey, "TripleDES");
			cipher = Cipher.getInstance("TripleDES/CBC/Nopadding");
			ivParameterSpec = new IvParameterSpec(data2, data2Len - 16, 8);
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
			cipher.doFinal(data2, data2Len - 8, 8, arpc, 0);
			return arpc;

		default:
			return null;
		}
	}
}
