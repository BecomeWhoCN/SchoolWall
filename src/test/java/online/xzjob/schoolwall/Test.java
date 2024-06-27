package online.xzjob.schoolwall;

import online.xzjob.schoolwall.util.PasswordUtil;
import java.security.NoSuchAlgorithmException;

public class Test {

    public static void main(String[] args) {
        String originalPassword = "123456789";
        System.out.println("原始密码是: " + originalPassword);

        // 加密密码第一次
        String encryptedPassword = PasswordUtil.get_jmPasswd(originalPassword, PasswordUtil.SaltValue.getBytes());
        System.out.println("第一次加密后的密码: " + encryptedPassword);

        // 加密密码第二次
        String encryptedPassword2 = PasswordUtil.get_jmPasswd(originalPassword, PasswordUtil.SaltValue.getBytes());
        System.out.println("第二次加密后的密码: " + encryptedPassword);

        // 验证密码
        boolean isPasswordValid = PasswordUtil.jm_PasswdEQ(originalPassword, encryptedPassword2, PasswordUtil.SaltValue.getBytes());
        System.out.println("密码验证结果: " + isPasswordValid);

    }
}
