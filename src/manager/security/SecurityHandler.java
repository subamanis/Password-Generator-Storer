package manager.security;

import org.mindrot.jbcrypt.BCrypt;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public abstract class SecurityHandler
{
    private static final int BCRYPT_WORKLOAD = 12;
    private static final byte[] AES_SALT = new String("12345678").getBytes();
    private static final int AES_ITERATION_COUNT = 40000;
    private static final int AES_KEY_LENGTH = 128;

    private static String storedHashedPass;
    private static SecretKeySpec hashedUserKey;

    public static void hashPassAndSetIfNotSet(final String unhashedPass)
    {
        if(storedHashedPass == null){
            if(unhashedPass.startsWith("$2a$") && unhashedPass.length() == 60){
                storedHashedPass = unhashedPass;
            }else{
                storedHashedPass = BCryptHash(unhashedPass);
            }
        }
    }

    public static String getStoredHashedPass()
    {
        return storedHashedPass;
    }

    public static void hashAndSetUserKey(final char[] key)
    {
        try {
            hashedUserKey = createSecretKey(key);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkProgramPass(final String inputProgramPass)
    {
        if(null == storedHashedPass || !storedHashedPass.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        return(BCrypt.checkpw(inputProgramPass, storedHashedPass));
    }

    public static String encrypt(final String property) {
        try {
            Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            pbeCipher.init(Cipher.ENCRYPT_MODE, hashedUserKey);
            AlgorithmParameters parameters = pbeCipher.getParameters();
            IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
            byte[] cryptoText = pbeCipher.doFinal(property.getBytes("UTF-8"));
            byte[] iv = ivParameterSpec.getIV();
            return base64Encode(iv) + ":" + base64Encode(cryptoText);
        }catch (GeneralSecurityException | UnsupportedEncodingException e){
            System.out.println("An unexpected exception occurred...");
            e.printStackTrace();
        }

        return null;
    }

    public static String decrypt(final String string) {
        try {
            String iv = string.split(":")[0];
            String property = string.split(":")[1];
            Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            pbeCipher.init(Cipher.DECRYPT_MODE, hashedUserKey, new IvParameterSpec(base64Decode(iv)));
            return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
        }catch (BadPaddingException e){
            return string.substring(0,12);
        }catch (GeneralSecurityException | UnsupportedEncodingException e){
            System.out.println("An unexpected exception occurred...");
            e.printStackTrace();
        }

        return null;
    }


    // **********************************- PRIVATE METHODS -****************************************


    private static String BCryptHash(final String input)
    {
        String salt = BCrypt.gensalt(BCRYPT_WORKLOAD);
        return BCrypt.hashpw(input, salt);
    }

    private static SecretKeySpec createSecretKey(final char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec keySpec = new PBEKeySpec(password, AES_SALT, AES_ITERATION_COUNT, AES_KEY_LENGTH);
        SecretKey keyTmp = keyFactory.generateSecret(keySpec);
        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
    }

    private static String base64Encode(final byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    private static byte[] base64Decode(final String property) {
        return Base64.getDecoder().decode(property);
    }

}
