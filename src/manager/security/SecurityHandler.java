package manager.security;

import org.mindrot.jbcrypt.BCrypt;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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
    private static final int BCRYPT_WORKLOAD = 11;
    private static final byte[] AES_SALT = new String("12345678").getBytes();
    private static final int AES_ITERATION_COUNT = 30000;
    private static final int AES_KEY_LENGTH = 128;

    private static String hashedUserPass;

    private static SecretKeySpec secretKey;


    public static String getHashedUserPass()
    {
        return hashedUserPass;
    }

    public static boolean checkProgramPass(final String inputProgramPass)
    {
        return(BCrypt.checkpw(inputProgramPass, hashedUserPass));
    }

    public static void hashAndSetUserPass(final String userInputPass)
    {
        try {
            hashedUserPass = BCryptHash(userInputPass);
            secretKey = createSecretKey(hashedUserPass.toCharArray());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(final String property) {
        try {
            Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            pbeCipher.init(Cipher.ENCRYPT_MODE, secretKey);
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
            pbeCipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(base64Decode(iv)));
            return new String(pbeCipher.doFinal(base64Decode(property)), StandardCharsets.UTF_8);
        }catch (BadPaddingException e){
            return string.substring(0,12);
        }catch (GeneralSecurityException e){
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
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
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
