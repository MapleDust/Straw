package cn.tedu.straw.api.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码工具类
 */
public class PasswordUtils {

    /**
     * 密码加密器
     */
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 执行密码加密
     *
     * @param rawPassword 原密码
     * @return 加密后得到的密文
     */
    public static String encode(String rawPassword) {
        return "{bcrypt}" + passwordEncoder.encode(rawPassword);
    }

}
