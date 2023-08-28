import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

class DESTest {
   public static void main(String[] args) {
      String test = "1";
      try {
         byte[] theKey = null;
         byte[] theMsg = null; 
         byte[] theExp = null; 
         if (test.equals("1")) { 
            theKey = hexToBytes("00000000000000000000000000000000");
            theMsg = hexToBytes("6a118a874519e64e9963798a503f1d35");
            theExp = hexToBytes("dc43be40be0e53712f7e2bf5ca707209");
         } else if (test.equals("2")) { 
            theKey = hexToBytes("38627974656B657938627974656B6579"); 
            theMsg = hexToBytes("6D6573736167652E6D6573736167652E"); 
            theExp = hexToBytes("7CF45E129445D4517CF45E129445D451");
         } else {
            System.out.println("Usage:");
            System.out.println("java AESTest 1/2");
            return;
         }	
         SecretKeySpec ks = new SecretKeySpec(theKey, "AES");
         Cipher cf = Cipher.getInstance("AES/ECB/NoPadding");
         cf.init(Cipher.ENCRYPT_MODE, ks);
         byte[] theCph = cf.doFinal(theMsg);
         System.out.println("Key        : "+bytesToHex(theKey));
         System.out.println("Expected   : "+bytesToHex(theExp));
         System.out.println("Result     : "+bytesToHex(theCph));
         System.out.println("Original   : "+bytesToHex(theMsg));
      } catch (Exception e) {
         return;
      }
   }
   
   public static byte[] hexToBytes(String str) {
      if (str==null) {
         return null;
      } else if (str.length() < 2) {
         return null;
      } else {
         int len = str.length() / 2;
         byte[] buffer = new byte[len];
         for (int i=0; i<len; i++) {
             buffer[i] = (byte) Integer.parseInt(
                str.substring(i*2,i*2+2), 16);
         }
         return buffer;
      }
   }
   
   public static String bytesToHex(byte[] data) {
      if (data==null) {
         return null;
      } else {
         int len = data.length;
         StringBuilder sb = new StringBuilder();
         for (byte b : data) {
            sb.append(String.format("%02X", b));
         }
         return sb.toString();
      }
   }            
}