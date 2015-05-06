import java.util.Properties;
import java.util.Scanner;


/**
 * 
 */

/**
 * @author Mazharul Islam Rakeb (mazharul.islam@konasl.com)
 *
 */
public class Main {
	public static int CVN;
	public static byte[] UniqueKey;
	public static byte[] ATC;
	public static int GENERATE_AC; 
	public static byte[] UN; 
	public static byte[] PDOL;
	public static byte[] CDOL1;
	public static byte[] CDOL2;
	public static byte[] cdaResponse;
	public static byte[] rsaMOD;
	public static byte[] rsaEXP;
	private static String rsaCipherALG;
	private static String iccUPN;
	private static String pinBlock;
	public static byte[] responseTable;
	
	private static Properties properties;
	
	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(String fileName) throws Exception {
		Main.properties = new Properties(); 
		Main.properties.load(Main.class.getClassLoader().getResourceAsStream(fileName));
	}
	
	public static void initVariables() throws Exception {
		Main.setProperties("config.properties");
		CVN 		= Integer.parseInt(properties.getProperty("CVN"), 10);
		UniqueKey 	= Helper.toByte(properties.getProperty("UniqueKey"));
		ATC 		= Helper.toByte(properties.getProperty("ATC"));
		
		GENERATE_AC 	= Integer.parseInt(properties.getProperty("GENERATE_AC"), 10);
		UN 				= Helper.toByte(properties.getProperty("UN"));
		PDOL 			= Helper.toByte(properties.getProperty("PDOL"));
		CDOL1 			= Helper.toByte(properties.getProperty("CDOL1"));
		CDOL2 			= Helper.toByte(properties.getProperty("CDOL2"));
		cdaResponse 	= Helper.toByte(properties.getProperty("cdaResponse"));
		responseTable 	= Helper.toByte(properties.getProperty("responseTable"));
		rsaMOD 			= Helper.toByte(properties.getProperty("rsaMOD"));
		rsaEXP 			= Helper.toByte(properties.getProperty("rsaEXP"));
		rsaCipherALG	= properties.getProperty("rsaCipherALG");
		
		iccUPN			= properties.getProperty("iccUPN");
		pinBlock		= properties.getProperty("pinBlock");
	}

	public static void main(String[] args) throws Exception {
//		while(true) {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter 1 for SessionKey.\n" +
					"Enter 2 for ARPC.\n" + 
					"Enter 3 for PUT DATA.\n" +
					"Enter 4 for CDA.\n" +
					"Enter 5 for PIN.\n" +
					"Enter 6 for MAG Stripe Data.\n" +
					"Enter 0 to terminate.\n");
			
			int a = scanner.nextInt();
			Main.initVariables();
			JavaTestToolJCB jcbTestCode = new JavaTestToolJCB();
			switch (a) {
			case 0:
				System.out.println("Terminating...\n");
				System.exit(0);
			case 1:				
				System.out.println("Session Key : " +Helper.toHex(SessionKeyDerivation.derivedSessionKey(CVN, ATC, UniqueKey), 16) + "\n\n");
				break;
			case 2:
				ARPCgenerator arpCgenerator = new ARPCgenerator();
				byte[] arpc = arpCgenerator.generateARPC();
				if (arpc != null) {
					if(CVN == 4) {
						System.out.println("ARPC : " +Helper.toHex(arpc, 4) + "\n\n");
					} else {
						System.out.println("ARPC : " +Helper.toHex(arpc) + "\n\n");
					}
					
				} else {
					System.out.println("ARPC generation failed"+ "\n\n");
				}
				
				break;
			case 3:
				byte[] sessionKey = SessionKeyDerivation.derivedSessionKey(CVN, ATC, UniqueKey);
				jcbTestCode.initMethod(sessionKey, 0, 24, "DESede");
				
				int len = jcbTestCode.messageEncDec(Helper.toByte(pinBlock), (short)0, (byte)0x08, true);
				byte[] cipherData = jcbTestCode.getResult();
				System.out.println("Cipher Data : "+Helper.toHex(cipherData, len) + "\n\n");
				break;
			case 4:
				try {
					if(CDAVerification.verifyCDA(GENERATE_AC, UN, PDOL, CDOL1, CDOL2, cdaResponse, rsaMOD, rsaEXP, rsaCipherALG)) {
						System.out.println("CDA verification successfull!\n\n");
					} else {
						System.out.println("CDA verification failed!\n\n");
					}
				} catch (Exception e) {
					System.out.println("CDA verification program crahsed!\n\n");
				}
				break;
			case 5:
				jcbTestCode.pinEncipher(rsaMOD, rsaEXP, iccUPN, pinBlock);
				break;
			case 6:
				jcbTestCode.dynamicMagStripComp();
				break;
			case 7:
				System.out.println("HASH : "+Helper.toHex(Utility.generateHASH("SHA", responseTable)) + "\n\n");
				break;
			default:
				System.out.println("Try again :");
			}		
		}
//	}
}
