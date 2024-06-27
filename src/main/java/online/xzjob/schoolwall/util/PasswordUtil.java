package online.xzjob.schoolwall.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {

    // 盐值长度
    private static final int SALT_LENGTH = 16;

    public static final String SaltValue = "bvX3iVS8wf3QnkxwI50v5w==";

    // 生成盐值
    public static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstanceStrong();
        byte[] salt = new byte[SALT_LENGTH];
        sr.nextBytes(salt);
        return salt;
    }

    // 获取使用SHA-256加密的密码
    public static String get_jmPasswd(String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    // 验证两个密码是否匹配
    public static boolean jm_PasswdEQ(String originalPassword, String storedPassword, byte[] salt) {
        String newSecurePassword = get_jmPasswd(originalPassword, salt);
        return newSecurePassword.equals(storedPassword);
    }

    // 将盐值转换为Base64字符串
    public static String encodeSalt(byte[] salt) {
        return Base64.getEncoder().encodeToString(salt);
    }

    // 将Base64字符串转换为盐值
    public static byte[] decodeSalt(String encodedSalt) {
        return Base64.getDecoder().decode(encodedSalt);
    }

}
