/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.nic.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;
//import java.util.Base64;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;


/**
 *
 * @author user1
 */
public class GstUtil {
	
	
	static Logger logger = Logger.getLogger(GstUtil.class);

    private static final String AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String AES_ALGORITHM = "AES";
    private static final String RSA_ALGORITHM = "RSA";
    private static final String RSA_TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    static int ENC_BITS = 256;
    static String CHARACTER_ENCODING = "UTF-8";
    public KeyGenerator KEYGEN;
    public String valueBeforeMD5 = "";
    public String valueAfterMD5 = "";
    private static Random myRand;
    private static SecureRandom mySecureRand;
    private static String s_id;   
//    public static final String PUBLIC_KEY_PATH_PEM = "F:\\GST API\\GstReturnFileCountAPI\\web\\New folder\\SB_public.pem";
//    public static final String PUBLIC_KEY_PATH_CER = "F:\\GST API\\GstReturnFileCountAPI\\web\\New folder\\GSTN_G2G_PublicKey.cer";
    
//    public static final String PUBLIC_KEY_PATH_PEM = "F:\\GST API\\gobinda\\keycer\\GSTN_G2B_Prod_Public.pem";
//    public static final String PUBLIC_KEY_PATH_CER = "F:\\GST API\\gobinda\\keycer\\GSTN_G2B_Prod_Public.cer";
    
  //new product key added on 06/07/2022 and validate till 28/06/2023
    public static final String PUBLIC_KEY_PATH_PEM = "F:\\GST API\\gst_production_key\\GSTN_G2B_Prod_Public.pem";
    public static final String PUBLIC_KEY_PATH_CER = "F:\\GST API\\gst_production_key\\GSTN_G2B_Prod_Public.cer";
   

    public String generateSecureKey() throws Exception {
//    	logger.info("Enter into generateSecureKey method():--");
    	
        KEYGEN = KeyGenerator.getInstance(AES_ALGORITHM);
        KEYGEN.init(ENC_BITS);
        SecretKey secretKey = KEYGEN.generateKey();
//        logger.info("Exit from generateSecureKey method():--");
        
        return encodeBase64String(secretKey.getEncoded());
    }

    /*
     * Method to generate the random GUID
     */
    public String getRandomGuid(boolean secure) {
    	
        MessageDigest md5 = null;
        StringBuffer sbValueBeforeMD5 = new StringBuffer();
        
//        logger.info("Enter into getRandomGuid method():--");
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error("Exception into getRandomGuid() method MD5:-- " + e.getMessage());
        }
        try {
            long time = System.currentTimeMillis();
            long rand = 0;
            if (secure) {
                rand = mySecureRand.nextLong();
            } else {
                rand = myRand.nextLong();
            }

            /* This StringBuffer can be a long as you need; the MD5
               hash will always return 128 bits.  You can change
               the seed to include anything you want here.
               You could even stream a file through the MD5 making
               the odds of guessing it at least as great as that
               of guessing the contents of the file! */
            
            sbValueBeforeMD5.append(s_id);
            sbValueBeforeMD5.append(":");
            sbValueBeforeMD5.append(Long.toString(time));
            sbValueBeforeMD5.append(":");
            sbValueBeforeMD5.append(Long.toString(rand));

            valueBeforeMD5 = sbValueBeforeMD5.toString();
            md5.update(valueBeforeMD5.getBytes());

            byte[] array = md5.digest();
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < array.length; ++j) {
                int b = array[j] & 0xFF;
                if (b < 0x10) {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(b));
            }
            valueAfterMD5 = sb.toString();
        } catch (Exception e) {
        	//logger.equals("Exception into getRandomGuid() method:--"+e.getMessage());           
        }
//        logger.info("Exit from getRandomGuid method():--");
        return valueAfterMD5;
    }

    /**
     * This method is used to encrypt base64 encoded string using an AES 256 bit
     * key.
     *
     * @param plainText : plain text to decrypt
     * @param secret : key to encrypt
     * @return : Encrypted String
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    public static String encrypt(String plainText, byte[] secret) throws NoSuchAlgorithmException, NoSuchPaddingException {
        
//    	 logger.info("Enter into encrypt method():--");    	
         Cipher ENCRYPT_CIPHER = Cipher.getInstance(AES_TRANSFORMATION);        
        try {

            SecretKeySpec sk = new SecretKeySpec(secret, AES_ALGORITHM);
            ENCRYPT_CIPHER.init(Cipher.ENCRYPT_MODE, sk);
            return Base64.encodeBase64String(ENCRYPT_CIPHER.doFinal(plainText.getBytes()));
        } catch (Exception e) {
        	//logger.equals("Exception into encrypt() method:--"+e.getMessage()); 
            return "Error in Encryption";
        }
    }

    /**
     * This method is used to decrypt base64 encoded string using an AES 256 bit
     * key.
     *
     * @param plainText : plain text to decrypt
     * @param secret : key to decrypt
     * @return : Decrypted String
     * @throws IOException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decrypt(String plainText, byte[] secret) throws InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException, Exception {
    	
//    	logger.info("Enter into decrypt method():--"); 
    	
    	Cipher DECRYPT_CIPHER = Cipher.getInstance(AES_TRANSFORMATION);
        SecretKeySpec sk = new SecretKeySpec(secret, AES_ALGORITHM);
        DECRYPT_CIPHER.init(Cipher.DECRYPT_MODE, sk);
//        logger.info("Exit from decrypt method():--"); 
        
        return DECRYPT_CIPHER.doFinal(decodeBase64StringTOByte(plainText));
    }

    /* Generate transaction number */
    /**
     * This method is used to generate mock Transaction id
     *
     * @return the string of random integer numbers length of string is 10 and
     * add prefix "GSTN"
     */
    public String genTransaction() {
    	
//    	logger.info("Enter into genTransaction method():--"); 
        char[] txnid = "1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            char c = txnid[random.nextInt(txnid.length)];
            sb.append(c);
        }
        String transaction = sb.toString();
//        logger.info("Exit from genTransaction method():--"); 
        return "GSTN".concat(transaction);
    }

    /**
     * This method is used to encrypt the string , passed to it using a public
     * key provided
     *
     * @param planTextToEncrypt : Text to encrypt
     * @return :encrypted string
     * @throws FileNotFoundException
     * @throws CertificateException
     * @throws UnsupportedEncodingException
     */
    public String encryptwithPK_CER(byte[] plaintext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, FileNotFoundException, CertificateException, UnsupportedEncodingException {
    	
//    	logger.info("Enter into encryptwithPK_CER method():--"); 
    	
    	String publicKeyUrl = PUBLIC_KEY_PATH_CER;
        FileInputStream fin = new FileInputStream(publicKeyUrl);
        CertificateFactory f = CertificateFactory.getInstance(RSA_ALGORITHM);
        X509Certificate certificate = (X509Certificate) f.generateCertificate(fin);
        PublicKey pk = certificate.getPublicKey();
        Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, pk);
        byte[] encryptedByte = cipher.doFinal(plaintext);
        String encodedString = new String(java.util.Base64.getEncoder().encode(encryptedByte)); 
        
//    	logger.info("Exit from encryptwithPK_CER method():--"); 
        return encodedString;
    }

    public String encryptwithPK_PEM(byte[] planTextToEncrypt) {
        Cipher cipher = null;
    	logger.info("Enter into encryptwithPK_PEM method():--");
        try {
            Path keyPath = Paths.get(PUBLIC_KEY_PATH_PEM);
            String keyContent = new String(Files.readAllBytes(keyPath)).replace("-----BEGIN RSA PUBLIC KEY-----", "");
            String modifiedKeyContent = keyContent.replace("-----END RSA PUBLIC KEY-----", "");
            
//            String keyContent = new String(Files.readAllBytes(keyPath)).replace("-----BEGIN PUBLIC KEY-----", "");
//            String modifiedKeyContent = keyContent.replace("-----END PUBLIC KEY-----", "");
            byte[] decodedKey = Base64.decodeBase64(modifiedKeyContent.getBytes());
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodedKey);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
            cipher = Cipher.getInstance(RSA_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedByte = cipher.doFinal(planTextToEncrypt);
            String encodedString = new String(java.util.Base64.getEncoder().encode(encryptedByte));
            logger.info("Exit from encryptwithPK_PEM method():--"); 
            
            return encodedString;
        } catch (Exception e) {
           logger.error("Exception into encryptwithPK_PEM() method:--"+e.getMessage());
           return null;
        }

    }

    public static String generateHmac(String data, byte[] ek) {
        String hash = null;
       // logger.info("Enter into generateHmac method():--");
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(ek, "AES_ALGORITHM");
            sha256_HMAC.init(secret_key);
            hash = Base64.encodeBase64String(sha256_HMAC.doFinal(data.getBytes(CHARACTER_ENCODING)));
        } catch (Exception e) {
        	 logger.error("Exception into generateHmac() method:--"+e.getMessage());
        }
      //  logger.info("Exit from generateHmac method():--"); 
        return hash;
    }

    @SuppressWarnings("unused")
    private static String gethashdataFile(String filepath) throws Exception {
    	  logger.info("Enter into gethashdataFile method():--");
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            @SuppressWarnings("resource")
            FileInputStream fis = new FileInputStream(filepath);
            byte[] dataBytes = new byte[1024];
            int nread = -1;
            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }
            byte[] mdbytes = md.digest();

            // convert the byte to hex format method 2
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < mdbytes.length; i++) {
                hexString.append(Integer.toHexString(0xFF & mdbytes[i]));
            }
            logger.info("Exit from gethashdataFile method():--");
            
            return hexString.toString();            
        } finally {
        	logger.info("Error calculating Hash");
        }
    }

    public static String getHashValue(String value) throws Exception {
    	
    	logger.info("Enter into getHashValue method():--");
    	
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(value.getBytes());
        byte[] byteData = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        logger.info("Exit from getHashValue method():--");
        
        return sb.toString();
    }

    /**
     * This method is used to encode bytes[] to base64 string.
     *
     * @param bytes : Bytes to encode
     * @return : Encoded Base64 String
     */
    public static String encodeBase64String(byte[] bytes) {
        return new String(java.util.Base64.getEncoder().encode(bytes));
    }

    /**
     * This method is used to decode the base64 encoded string to byte[]
     *
     * @param stringData : String to decode
     * @return : decoded String
     * @throws UnsupportedEncodingException
     */
    public static byte[] decodeBase64StringTOByte(String stringData) throws Exception {
        return java.util.Base64.getDecoder().decode(stringData.getBytes(CHARACTER_ENCODING));
    }

    public void exception_handling(Exception e) {
        logger.error("Exception Occured \n\n");
        logger.error(e);
    }
}
