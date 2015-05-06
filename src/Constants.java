public class Constants {	
	//YYMMDD
	public static final boolean CHECK_TO_MONTH			= false;
	public static final boolean CHECK_TO_DAY			= true;
	//DGI
	public static final short DGI_TOTAL					= 110;
	public static final short DGI_MAX_0101_To_0A0A		= 64;
	public static final short DGI_MAX_0B01_To_1E0A		= 16;
	public static final short EXTENED_DGI_MIN_LEN		= (short)0x00ED; 
	
    public static final short OFFSET_ZERO               = (short) 0x0000;
    
    public static final short HEADER_LEN               = (short) 0x0005;
    
    public static final byte BYTE_VALUE_ZERO			= (byte) 0x00;
    public static final byte BYTE_VALUE_MAX				= (byte) 0xFF;
    public static final byte BYTE_LOWER_NIBBLE			= (byte) 0x0F;
    public static final byte BYTE_MSB					= (byte) 0x80;
    public static final byte BIT_76						= (byte) 0x60;
    
    public static final byte BIT_1						= (byte) 0x01;
    public static final byte BIT_2						= (byte) 0x02;
    public static final byte BIT_3						= (byte) 0x04;
    public static final byte BIT_4						= (byte) 0x08;
    public static final byte BIT_5						= (byte) 0x10;
    public static final byte BIT_6						= (byte) 0x20;
    public static final byte BIT_7						= (byte) 0x40;
    public static final byte BIT_8						= (byte) 0x80;
    
    public static final byte BIT_1_CLEAR				= (byte) 0xFE;
    public static final byte BIT_2_CLEAR				= (byte) 0xFD;
    public static final byte BIT_3_CLEAR				= (byte) 0xFB;
    public static final byte BIT_4_CLEAR				= (byte) 0xF7;
    public static final byte BIT_5_CLEAR				= (byte) 0xEF;
    public static final byte BIT_6_CLEAR				= (byte) 0xDF;
    public static final byte BIT_7_CLEAR				= (byte) 0xBF;
    public static final byte BIT_8_CLEAR				= (byte) 0x7F;
    
    public static final byte BIT_87						= (byte) 0xC0;
    public static final byte BIT_65						= (byte) 0x30;
    public static final byte BIT_21						= (byte) 0x03;
    
    public static final byte BIT_321						= (byte) 0x07;
    public static final byte BIT_321_CLEAR					= (byte) 0xF8;
    
    public static final byte FRIST_NIBBLE_MAX_VALUE		= (byte) 0x0F;
    public static final byte SECOND_NIBBLE_MAX_VALUE	= (byte) 0xF0;
    public static final short BYTE_TO_SHORT_CONVERT		= (short) 0x00FF;
    
    public static final byte ZERO					= (byte) 0x00;
    public static final byte ONE					= (byte) 0x01;
    public static final byte TWO					= (byte) 0x02;
    public static final byte THREE					= (byte) 0x03;
    public static final byte FOUR					= (byte) 0x04;
    public static final byte FIVE					= (byte) 0x05;
    public static final byte SIX					= (byte) 0x06;
    public static final byte SEVEN					= (byte) 0x07;
    public static final byte EIGHT					= (byte) 0x08;
    public static final byte NINE					= (byte) 0x09;
    public static final byte TEN					= (byte) 0x0A;
    
    public static final short BLOCK_SIZE_08				= (short)8;

	public final static byte OFFSET_SFI_01				=(byte) 0x00;
	public final static byte OFFSET_SFI_02				=(byte) 0x01;
	public final static byte OFFSET_SFI_03				=(byte) 0x02;
	public final static byte OFFSET_SFI_0B				=(byte) 0x03;
	public final static byte OFFSET_SFI_15				=(byte) 0x04;
	
	public final static byte OFFSET_SFI_30				=(byte) 0x05;
	public final static byte OFFSET_SFI_80				=(byte) 0x06;
	public final static byte OFFSET_SFI_82				=(byte) 0x07;
	public final static byte OFFSET_SFI_83				=(byte) 0x08;
	public final static byte OFFSET_SFI_84				=(byte) 0x09;
	public final static byte OFFSET_SFI_90				=(byte) 0x0A;
	public final static byte OFFSET_SFI_91				=(byte) 0x0B;
	public final static byte OFFSET_SFI_92				=(byte) 0x0C;
	public final static byte OFFSET_SFI_94				=(byte) 0x0D;
	public final static byte OFFSET_SFI_A0				=(byte) 0x0E;
	public final static byte OFFSET_SFI_B0				=(byte) 0x0F;
	public final static byte OFFSET_SFI_B1				=(byte) 0x10;
	
	public final static short OFFSET_TDE_ARI          	= (short) 0;		// Advice Required Indicator
	public final static short OFFSET_TDE_AC          	= (short) 1;		// Application Cryptogram 
	public final static short OFFSET_TDE_AIP         	= (short) 9;		// Application Interchange Profile (AIP)
	public final static short OFFSET_TDE_ASK         	= (short) 11;		// Application Session Key
	public final static short OFFSET_TDE_CVR         	= (short) 27;		// Card Verification Result(CVR)
	public final static short OFFSET_TDE_CVS         	= (short) 35;		// Cardholder Verification Status
	public final static short OFFSET_TDE_CDOL1RD        = (short) 36;		// CDOL1 Related Data
	public final static short OFFSET_TDE_CTA         	= (short) 69;		// Converter Transaction Amount
	public final static short OFFSET_TDE_CID         	= (short) 75;		// Cryptogram Information Data
	public final static short OFFSET_TDE_ICC_UN         = (short) 76;		// ICC Unpredictable Number
	public final static short OFFSET_TDE_ICC_UNI        = (short) 84; 		// ICC Unpredictable Number Indicator
	public final static short OFFSET_TDE_LEGACY_CVR     = (short) 85;		// LegacyCVR
	public final static short OFFSET_TDE_MAC_SK         = (short) 90;		// MAC Session Key
	public final static short OFFSET_TDE_ME_SK         	= (short) 106;		// Messege Encryption SK
	public final static short OFFSET_TDE_MI         	= (short) 122;		// Mode Indicator
	public final static short OFFSET_TDE_TEMP_CTTA      = (short) 123;		// Temp CTTA
	public final static short OFFSET_TDE_TEMP_CTTACL    = (short) 129;		// Temp CTTA-CL
	public final static short OFFSET_TDE_TEMP_CTTN      = (short) 135;		// Temp CTTN
	public final static short OFFSET_TDE_TEMP_DA        = (short) 136;		// Temp Daily Accumulator
	public final static short OFFSET_TDE_TEMP_DC        = (short) 142;		// Temp Daily Counter
	public final static short OFFSET_TDE_TEMP_MA        = (short) 143;		// Temp Monthly Accumulator
	public final static short OFFSET_TDE_TCSU         	= (short) 149;		// Transaction Card Status Update(CSU)
	public final static short OFFSET_TDE_TRI         	= (short) 153;		// Transaction Recovery Indicator
	public final static short OFFSET_TDE_UCI         	= (short) 154;		// Unrecognized Currency Indicator
	
	public final static short OFFSET_TDE_STATE_MACHINE  = (short) 155;		// State Machine
	
	public final static short TOTAL_TDE_MEMORY			= (short) 156;		// Transient Data Elements
	
	public final static short OFFSET_CDOL1RD_AMOUNT_AUTHORIZED 	= (short) 36;
	public final static short OFFSET_CDOL1RD_AMOUNT				= (short) 42;
	public final static short OFFSET_CDOL1RD_TER_COUNTRY_CODE 	= 48;
	public final static short OFFSET_CDOL1RD_TVR 				= 50;
	public final static short OFFSET_CDOL1RD_TRA_CURRENCY_CODE 	= 55;
	public final static short OFFSET_CDOL1RD_TRA_DATE 			= 57;
	public final static short OFFSET_CDOL1RD_TRA_TYPE 			= 60;
	public final static short OFFSET_CDOL1RD_UNPRE_NUMBER 		= 61;
	public final static short OFFSET_CDOL1RD_TER_TYPE 			= 65;
	public final static short OFFSET_CDOL1RD_TIP 				= 66;
	
	public final static byte CRYPTOGRAM_TYPE_REQUEST_AAC  = (byte)0x00;
	public final static byte CRYPTOGRAM_TYPE_REQUEST_TC   = (byte)0x40;
	public final static byte CRYPTOGRAM_TYPE_REQUEST_ARQC = (byte)0x80;
	public final static byte CRYPTOGRAM_TYPE_REQUEST_RFU  = (byte)0xC0;
	
	public final static byte CVM_ONLINE_PIN_REQUIRED	= (byte)0x20;
	public final static byte CVM_SIGNATURE_REQUIRED		= (byte)0x10;
	public final static byte CVM_NOT_REQUIRED			= (byte)0x00;
	public final static byte CVM_NO_MATCHING_CVM		= (byte)0x30;  //??
	
	public final static short OFFSET_A001_PAS 	     			= (short) 0;		// Payment Application Settings       	-3 byte
	public final static short OFFSET_A001_CAA_COUNTRY_CODE 	    = (short) 3;		// Card Action Analysis Country Code  	-2 byte	
	public final static short OFFSET_A001_CAA_CURRENCY_CODE    	= (short) 5;		// Card Action Analysis Currency Code 	-2 byte
	public final static short OFFSET_A001_CAASI		    	= (short) 7;		// Card Action Analysis Support Info    -3 byte
	public final static short OFFSET_A001_CAASI_CL		    	= (short) 10;		// Card Action Analysis Support Info CL -3 byte
	public final static short OFFSET_A001_CIAC_DECLINE	    	= (short) 13;		// Card Issuer Action Code DC			-4 byte	
	public final static short OFFSET_A001_CIAC_DECLINE_CL	   	= (short) 17;		// Card Issuer Action Code DC_CL		-4 byte
	public final static short OFFSET_A001_CIAC_DEFAULT	    	= (short) 21;		// Card Issuer Action Code D			-4 byte
	public final static short OFFSET_A001_CIAC_DEFAULT_CL	    = (short) 25;		// Card Issuer Action Code D_CL			-4 byte	
	public final static short OFFSET_A001_CIAC_ONLINE	    	= (short) 29;		// Card Issuer Action Code Online		-4 byte
	public final static short OFFSET_A001_CIAC_ONLINE_CL	    = (short) 33;		// Card Issuer Action Code Online(CL)	-4 byte
	public final static short OFFSET_A001_CIAC_CVM	    		= (short) 37;		// Card Issuer Action Code CVM			-4 byte
	public final static short OFFSET_A001_CSU_UA_ARC	    	= (short) 41;		// CSU for Unsigned approval ARC		-4 byte
	public final static short OFFSET_A001_CSU_SA_ARC	    	= (short) 45;		// CSU for signed approval ARC			-4 byte
	public final static short OFFSET_A001_CVN	    			= (short) 49;		// Cryptogram Version Number			-1 byte
	public final static short OFFSET_A001_DKI	    			= (short) 50;		// Derivation Key Index					-1 byte
	public final static short OFFSET_A001_ISSUER_CVM_SL	    		= (short) 51;		// Issuer CVM Selection List			-1 byte
	
	
	public final static short OFFSET_A002_CTTA_LL 	     		= (short) 0;       	// CTTA Lower Limit  		-6 byte 
	public final static short OFFSET_A002_CTTA_UL 	     		= (short) 6;       	// CTTA Upper Limit  		-6 byte
	public final static short OFFSET_A002_CTTA_CL_LL 	     	= (short) 12;      	// CTTA-CL Lower Limit  	-6 byte
	public final static short OFFSET_A002_CTTA_CL_UL 	     	= (short) 18;      	// CTTA-CL Upper Limit  	-6 byte
	public final static short OFFSET_A002_CTTN_LL 	     		= (short) 24;      	// CTTN Lower Limit			-1 byte
	public final static short OFFSET_A002_CTTN_UL 	     		= (short) 25;      	// CTTN Upper Limit  		-1 byte
	public final static short OFFSET_A002_DAL 	     			= (short) 26;      	// Daily Accumulator limit  -6 byte
	public final static short OFFSET_A002_DCL 	     			= (short) 32;      	// Daily Counter limit  	-1 byte
	public final static short OFFSET_A002_MAL 	     			= (short) 33;      	// Monthly Accumulator limit-6 byte
	public final static short OFFSET_A002_TAL_1 	     		= (short) 39;      	// Transaction Amount Limit1-6 byte
	public final static short OFFSET_A002_TAL_2 	     		= (short) 45;	   	// Transaction Amount Limit1-6 byte
	
	public final static short TAG_PAS					= (short)0xDF5C;
	public final static short TAG_ATC					= (short)0x9F36;
	public final static short TAG_CTTA					= (short)0x9F64;
	public final static short TAG_CTTA_UPPER_LIMIT		= (short)0x9F64;
	public final static short TAG_CTTA_LOWER_LIMIT		= (short)0x9F56;
	
	public final static short TAG_PIN_TRY_COUNTER			= (short)0x9F17;
	public final static short TAG_CTTA_CL					= (short)0x9F7B;
	public final static short TAG_CTTA_CL_UPPER_LIMIT		= (short)0x9F7B;
	public final static short TAG_CTTA_CL_LOWER_LIMIT		= (short)0x9F77;
	public final static short TAG_94						= (short)0x0094;
	public final static short TAG_82						= (short)0x0082;
	public final static short TAG_DAILY_ACCUMULATOR_LIMIT	= (short)0xDF21;
	public final static short TAG_DAILY_COUNTER_LIMIT		= (short)0xDF25;
	public final static short TAG_MONTHLY_ACCUMULATOR_LIMIT	= (short)0xDF32;
	public final static short TAG_TAL1						= (short)0xDF4A;
	public final static short TAG_TAL2						= (short)0xDF4B;
	public final static short TAG_CTTN_LOWER_LIMIT			= (short)0xDF19;
	public final static short TAG_CTTN_UPPER_LIMIT			= (short)0xDF1A;
	
	public final static short TAG_CAASI					= (short)0x00C1;
	public final static short TAG_CAASI_CL				= (short)0x00C2;
	public final static short TAG_CSU_SAARC				= (short)0x00CA;
	public final static short TAG_CAA_CURRENCY_CODE		= (short)0xDF52;
	public final static short TAG_CAA_COUNTRY_CODE		= (short)0xDF50;
	
	public final static short TAG_AMOUNT_AUTHORISED			= (short)0x9F02;
	public final static short TAG_TERMINAL_COUNTRY_CODE 	= (short)0x9F1A;
	public final static short TAG_TRANSACTION_CURRENCY_CODE = (short)0x5F2A;
	public final static short TAG_TRANSACTION_DATE			= (short)0x009A;
	public final static short TAG_TRANSACTION_TYPE			= (short)0x009C;
	public final static short TAG_TIP						= (short)0x9F53;
	public final static short TAG_MERCHANT_NAME_LOCATION	= (short)0x9F4E;
	
	public final static byte CVR_AAC_SECOND_GENERATE_AC		= (byte)0x3F;
	public final static byte CVR_TC_SECOND_GENERATE_AC		= (byte)0x7F;
	public final static byte CVR_AAC_FIRST_GENERATE_AC		= (byte)0xCF;
	public final static byte CVR_TC_FIRST_GENERATE_AC		= (byte)0xDF;
	public final static byte CVR_ARQC_FIRST_GENERATE_AC		= (byte)0xEF;
	
	public final static short P1P2_AFL_D4					= (short)0x00D4;
	public final static short P1P2_AFL_D1					= (short)0x00D1;
	public final static short P1P2_AFL_D2					= (short)0x00D2;
	public final static short P1P2_AFL_D3					= (short)0x00D3;
	public final static short P1P2_TCCT						= (short)0x9F65;
	public final static short P1P2_MAG_STRIPE_TRACK2		= (short)0xDF4D;
	public final static short P1P2_00D1					= (short)0x00D1;
	public final static short P1P2_00D2					= (short)0x00D2;
	public final static short P1P2_00D3					= (short)0x00D3;
	public final static short P1P2_00D4					= (short)0x00D4;
	public final static short P1P2_00D5					= (short)0x00D5;
	public final static short P1P2_00D6					= (short)0x00D6;
	public final static short P1P2_00D7					= (short)0x00D7;
	public final static short P1P2_00D8					= (short)0x00D8;
	public final static short P1P2_DF50					= (short)0xDF50;
	public final static short P1P2_DF52					= (short)0xDF52;
	public final static short P1P2_00C1					= (short)0x00C1;
	public final static short P1P2_00C2					= (short)0x00C2;
	public final static short P1P2_00C3					= (short)0x00C3;
	public final static short P1P2_00C4					= (short)0x00C4;
	public final static short P1P2_00C5					= (short)0x00C5;
	public final static short P1P2_00C6					= (short)0x00C6;
	public final static short P1P2_00C7					= (short)0x00C7;
	public final static short P1P2_00C8					= (short)0x00C8;
	public final static short P1P2_00C9					= (short)0x00C9;
	public final static short P1P2_00CB					= (short)0x00CB;
	public final static short P1P2_DF19					= (short)0xDF19;
	public final static short P1P2_DF1A					= (short)0xDF1A;
	public final static short P1P2_00CA					= (short)0x00CA;
	public final static short P1P2_00CC					= (short)0x00CC;
	public final static short P1P2_9F56					= (short)0x9F56;
	public final static short P1P2_DF16					= (short)0xDF16;
	public final static short P1P2_9F64					= (short)0x9F64;
	public final static short P1P2_9F77					= (short)0x9F77;
	public final static short P1P2_DF17					= (short)0xDF17;
	public final static short P1P2_9F7B					= (short)0x9F7B;
	public final static short P1P2_DF21					= (short)0xDF21;
	public final static short P1P2_DF25					= (short)0xDF25;
	public final static short P1P2_DF56					= (short)0xDF56;
	public final static short P1P2_DF4D					= (short)0xDF4D;
	public final static short P1P2_DF32					= (short)0xDF32;
	public final static short P1P2_DF5C					= (short)0xDF5C;
	public final static short P1P2_DF4A					= (short)0xDF4A;
	public final static short P1P2_DF4B					= (short)0xDF4B;
	public final static short P1P2_9F65					= (short)0x9F65;
	
	public final static short GET_DATA_P1P2_9F36					= (short)0x9F36;
	
	public final static byte STORE_DATA_P1_DGI_ENCRYPTED_UNDER_SKU	= (byte)0x60;
	
	public final static short TAG_RECORD_TEMPLATE		= (short)0x0070;
	public final static byte EXTENDED_TAG				= (byte)0x1F;
	public final static byte EXTENDED_LENGTH			= (byte)0x80;
	
	//State constant
	public final static byte STATE_IDLE                 = (byte)0x00;
	public final static byte STATE_SELECTED  			= (byte)0x01;
	public final static byte STATE_INITIATED  			= (byte)0x02;
	public final static byte STATE_ONLINE	  			= (byte)0x03;
	public final static byte STATE_SCRIPT	  			= (byte)0x04;
	public final static byte STATE_CARD_BLOCKED  		= (byte)0x05;
	
	public final static byte STATE_SELECT_NOT_PERSONALIZED           = (byte)0x06;
	public final static byte STATE_NOT_PERSONALIZED           		 = (byte)0x07;
	public final static byte STATE_INITIALIZED           			 = (byte)0x08;
	public final static byte STATE_PERSONALIZED 	          		 = (byte)0x07;
	
	//State Mode Indicator constant
	public final static byte MI_NO_MODE_SELECTED  			= (byte)0x00;
	public final static byte MI_LEGACY_READER_MODE  		= (byte)0x01;
	public final static byte MI_MAG_STRIPE_MODE  			= (byte)0x02;
	public final static byte MI_EMV_MODE		  			= (byte)0x03;
	public final static byte MI_CONTACT_EMV  				= (byte)0x07;
	
	//Terminal Compatibility Indicator
	public final static byte TCI_RFU  						= (byte)0x00;
	public final static byte TCI_EMV_MODE_SUPPORTED			= (byte)0x02;
	public final static byte TCI_MAG_STRIPE_MODE_SUPPORTED	= (byte)0x01;
	
	//State Mode Indicator constant
	public final static byte CAASI_ENABLE_MA				 =(byte)0x20;
	public final static byte CAASI_ENABLE_DA				 =(byte)0x10;
	public final static byte CAASI_ENABLE_DC				 =(byte)0x02;
	
	
	public final static short GROUP_THREE_CAA_COUNTRY_CODE		= (byte)0x00;
	public final static short GROUP_THREE_CAA_CURRENCY_CODE		= (byte)0x02;
	public final static short GROUP_THREE_CAASI					= (byte)0x04;
	public final static short GROUP_THREE_CAASICL				= (byte)0x07;
	public final static short GROUP_THREE_ATC					= (byte)0x0A;
	public final static short GROUP_THREE_CTTN					= (byte)0x0C;
	public final static short GROUP_THREE_CTTA					= (byte)0x0D;
	public final static short GROUP_THREE_CTTACL				= (byte)0x13;
	public final static short GROUP_THREE_DAILY_ACCUMULATOR		= (byte)0x19;
	public final static short GROUP_THREE_DAILY_COUNTER			= (byte)0x1F;
	public final static short GROUP_THREE_MONTHLY_ACCUMULATOR	= (byte)0x20;
	public final static short GROUP_THREE_ISCC					= (byte)0x26;
//	public final static short GROUP_THREE_TCCT					= (byte)0x02;
	
	public final static short DGI_0302							= (short) 0x0302;
	public final static short DGI_A001							= (short) 0xA001;
	public final static short DGI_A002							= (short) 0xA002;
	public final static short DGI_9010							= (short) 0x9010;
	public final static short DGI_8000							= (short) 0x8000;
	public final static short DGI_8001							= (short) 0x8001;
	public final static short DGI_8010							= (short) 0x8010;
	public final static short DGI_8201							= (short) 0x8201;
	public final static short DGI_8301							= (short) 0x8301;
	public final static short DGI_8400							= (short) 0x8400;
	public final static short DGI_A003							= (short) 0xA003;
	public final static short DGI_9200							= (short) 0x9200;
	public final static short DGI_9104							= (short) 0x9104;
	
	//Status word constant
    public final static short SW_PIN_FAILED_00						= (short)0x63C0;
    public final static short SW_HOST_CRYPTOGRAM_FAILED_00			= (short)0x6300;
    public final static short SW_DATA_ELEMENT_NOT_PERSONALIZED		= (short)0x6A88;    
    public final static short SW_PIN_FORMAT_FAILED					= (short)0x6988;
    public final static short SW_DEBUG								= (short)0x6900; 
    
    // Definition of instruction byte
    static final byte INS_UPDATE_RECORD 				= (byte) 0xDC;
	static final byte INS_READ_RECORD 					= (byte) 0xB2;
	static final byte INS_PUT_DATA						= (byte) 0xDA;
	static final byte INS_INITIALIZE_UPDATE 			= (byte) 0x50;
	static final byte INS_EXTERNAL_AUTHENTICATE 		= (byte) 0x82;
	static final byte INS_INTERNAL_AUTHENTICATE	    	= (byte) 0x88;
	static final byte INS_STORE_DATA					= (byte) 0xE2;
	static final byte INS_APPLICATION_BLOCK	        	= (byte) 0x1E;
	static final byte INS_APPLICATION_UNBLOCK	        = (byte) 0x18;
	static final byte INS_CARD_BLOCK	                = (byte) 0x16;
	static final byte INS_GET_PROCESSION_OPTION			= (byte) 0xA8;
	static final byte INS_GET_DATA						= (byte) 0xCA;
	static final byte INS_GET_CHALLENGE					= (byte) 0x84;
	static final byte INS_GET_MAG_STRIPE_DATA			= (byte) 0xD0;
	static final byte INS_SELECT						= (byte) 0xA4;
	static final byte INS_VERIFY						= (byte) 0x20;
	static final byte INS_PIN_CHANGE_OR_UNBLOCK			= (byte) 0x24;
	static final byte INS_ECHO							= (byte) 0xDF;
	static final byte INS_GENERATE_AC					= (byte) 0xAE;
//	static final byte INS_SECOND_GENERATE_AC			= (byte) 0xAA; 
	
	//Last Online Context LOC
	public final static short OFF_LOC_LAST_ONLINE_ATC		= (short) 0;
	public final static short OFF_LOC_APP_CRYPTOGRAM_ARQC 	= (short) 2;	
	public final static short OFF_LOC_AMOUNT_AUTHORISED    	= (short) 10;
	public final static short OFF_LOC_AMOUNT_OTHER	    	= (short) 16;
	public final static short OFF_LOC_TERMINAL_COUNTRY_CODE = (short) 22;
	public final static short OFF_LOC_TVR			    	= (short) 24;	
	public final static short OFF_LOC_TRANSAC_CURRENCY_CODE = (short) 29;
	public final static short OFF_LOC_TRANSAC_DATE	    	= (short) 31;
	public final static short OFF_LOC_TRANSAC_TYPE		    = (short) 34;	
	public final static short OFF_LOC_UNPREDICTED_NUMBER    = (short) 35;
	public final static short OFF_LOC_AIP				    = (short) 39;
	public final static short OFF_LOC_CVR		    		= (short) 41;
	public final static short OFF_LOC_CONV_TRANSAC_AMOUNT	= (short) 49;
	public final static short OFF_LOC_UNRECO_CURRENCY_INDI  = (short) 55;
	public final static short OFF_LOC_MODE_INDICATOR	   	= (short) 56;
	
	public final static byte CARD_MODE_PERSONALIZATION		= (byte) 0x03;
	public final static byte CARD_MODE_OPERATIONAL			= (byte) 0x0F;
	
	public final static byte CARD_APP_MODE_IDLE				= (byte) 0x00;
	public final static byte CARD_APP_MODE_SELECTED			= (byte) 0x01;
	public final static byte CARD_APP_MODE_INITIATED		= (byte) 0x02;
	public final static byte CARD_APP_MODE_AUTHED			= (byte) 0x10;
	public final static byte CARD_APP_MODE_ONLINE			= (byte) 0x04;
	public final static byte CARD_APP_MODE_SCRIPT			= (byte) 0x08;
	
	public final static byte CARD_BLOCK						= (byte) 0xFF;
	
//	public final static Mapping[] mapping = {
//		new Mapping(Constants.PUT_DATA_P1P2_00D1, (byte) 4, (short) 0x9105, (byte)0, (byte)0x20),
//		new Mapping(Constants.PUT_DATA_P1P2_00D2, (byte) 0, (short) 0x9106, (byte)0, (byte)0x20),
//		new Mapping(Constants.PUT_DATA_P1P2_00D3, (byte) 4, (short) 0x9107, (byte)0, (byte)0x20),
//		new Mapping(Constants.PUT_DATA_P1P2_00D4, (byte) 4, (short) 0x9104, (byte)0, (byte)0x20),
//
//		new Mapping(Constants.PUT_DATA_P1P2_00D5, (byte) 2, (short) 0x9105),
//		new Mapping(Constants.PUT_DATA_P1P2_00D6, (byte) 2, (short) 0x9106),
//		new Mapping(Constants.PUT_DATA_P1P2_00D7, (byte) 2, (short) 0x9107),
//		new Mapping(Constants.PUT_DATA_P1P2_00D8, (byte) 2, (short) 0x9104),
//
//		new Mapping(Constants.PUT_DATA_P1P2_DF5C, (byte) 3, (short) 0xA001, Constants.OFFSET_A001_PAS),
//		new Mapping(Constants.PUT_DATA_P1P2_DF50, (byte) 2, (short) 0xA001,  Constants.OFFSET_A001_CAA_COUNTRY_CODE),
//		new Mapping(Constants.PUT_DATA_P1P2_DF52, (byte) 2, (short) 0xA001,  Constants.OFFSET_A001_CAA_CURRENCY_CODE),
//		new Mapping(Constants.PUT_DATA_P1P2_00C1, (byte) 3, (short) 0xA001, Constants.OFFSET_A001_CAASI),
//		new Mapping(Constants.PUT_DATA_P1P2_00C2, (byte) 3, (short) 0xA001, Constants.OFFSET_A001_CAASI_CL),
//		new Mapping(Constants.PUT_DATA_P1P2_00C3, (byte) 4, (short) 0xA001, Constants.OFFSET_A001_CIAC_DECLINE),
//		new Mapping(Constants.PUT_DATA_P1P2_00C4, (byte) 4, (short) 0xA001, Constants.OFFSET_A001_CIAC_DECLINE_CL),
//		new Mapping(Constants.PUT_DATA_P1P2_00C5, (byte) 4, (short) 0xA001, Constants.OFFSET_A001_CIAC_DEFAULT),
//		new Mapping(Constants.PUT_DATA_P1P2_00C6, (byte) 4, (short) 0xA001, Constants.OFFSET_A001_CIAC_DEFAULT_CL),
//		new Mapping(Constants.PUT_DATA_P1P2_00C8, (byte) 4, (short) 0xA001, Constants.OFFSET_A001_CIAC_ONLINE),
//		new Mapping(Constants.PUT_DATA_P1P2_00C9, (byte) 4, (short) 0xA001, Constants.OFFSET_A001_CIAC_ONLINE_CL),
//		new Mapping(Constants.PUT_DATA_P1P2_00C7, (byte) 4, (short) 0xA001, Constants.OFFSET_A001_CIAC_CVM),
//		new Mapping(Constants.PUT_DATA_P1P2_00CC, (byte) 4, (short) 0xA001, Constants.OFFSET_A001_CSU_UA_ARC),
//		new Mapping(Constants.PUT_DATA_P1P2_00CA, (byte) 4, (short) 0xA001, Constants.OFFSET_A001_CSU_SA_ARC),
//		new Mapping(Constants.PUT_DATA_P1P2_DF56, (byte) 1, (short) 0xA001, Constants.OFFSET_A001_ISSUER_CVM_SL),
//		
//		new Mapping(Constants.PUT_DATA_P1P2_9F56, (byte) 6, (short) 0xA002, Constants.OFFSET_A002_CTTA_LL),
//		new Mapping(Constants.PUT_DATA_P1P2_9F64, (byte) 6, (short) 0xA002, Constants.OFFSET_A002_CTTA_UL),
//		new Mapping(Constants.PUT_DATA_P1P2_9F77, (byte) 6, (short) 0xA002, Constants.OFFSET_A002_CTTA_CL_LL),
//		new Mapping(Constants.PUT_DATA_P1P2_9F7B, (byte) 6, (short) 0xA002, Constants.OFFSET_A002_CTTA_CL_UL),
//		new Mapping(Constants.PUT_DATA_P1P2_DF19, (byte) 1, (short) 0xA002, Constants.OFFSET_A002_CTTN_LL),
//		new Mapping(Constants.PUT_DATA_P1P2_DF1A, (byte) 1, (short) 0xA002, Constants.OFFSET_A002_CTTN_UL),
//		new Mapping(Constants.PUT_DATA_P1P2_DF21, (byte) 6, (short) 0xA002, Constants.OFFSET_A002_DAL),
//		new Mapping(Constants.PUT_DATA_P1P2_DF25, (byte) 1, (short) 0xA002, Constants.OFFSET_A002_DCL),
//		new Mapping(Constants.PUT_DATA_P1P2_DF32, (byte) 6, (short) 0xA002, Constants.OFFSET_A002_MAL),
//		new Mapping(Constants.PUT_DATA_P1P2_DF4A, (byte) 6, (short) 0xA002, Constants.OFFSET_A002_TAL_1),
//		new Mapping(Constants.PUT_DATA_P1P2_DF4B, (byte) 6, (short) 0xA002, Constants.OFFSET_A002_TAL_2),
//		
//		new Mapping(Constants.PUT_DATA_P1P2_9F65, (byte) 6, (short) 0xA003, (short)0, (byte) 0x18),
//		
//		new Mapping(Constants.PUT_DATA_P1P2_DF16, (byte) 6, (short) 0xA002),
//		new Mapping(Constants.PUT_DATA_P1P2_DF17, (byte) 6, (short) 0xA002),
//		new Mapping(Constants.PUT_DATA_P1P2_DF4D, (byte) 0x13, (short) 0x00),
//		new Mapping(Constants.PUT_DATA_P1P2_00CB, (byte) 4, (short) 0xA001),
//	};
	
	
	public static final byte[] BASE_AUTH_KEY = { 
		(byte)0x40, (byte)0x41, (byte)0x42, (byte)0x43, (byte)0x44, (byte)0x45, (byte)0x46, (byte)0x47,
        (byte)0x48, (byte)0x49, (byte)0x4a, (byte)0x4b, (byte)0x4c, (byte)0x4d, (byte)0x4e, (byte)0x4f
	};
	
	public static final byte[] AID = { 
		(byte)0xA0, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x65, (byte)0x10, (byte)0x10
	};
	
	public static final byte[] BYTE_ARRAY_ZERO = {(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
	public static final byte[] CARRY_ADDEND = {(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01};
	public static final byte[] PIN_PADDING_BLOCK = {(byte)0x80, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
	
	public final static short OFFSET_APPLICATION_UNIQUE_KEY		= (short) 0;
	public final static short OFFSET_MAC_UNIQUE_KEY				= (short) 16;
	public final static short OFFSET_ENCRYPTION_UNIQUE_KEY		= (short) 32;
}
