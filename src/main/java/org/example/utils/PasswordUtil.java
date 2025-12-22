package org.example.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    /**
     * 密码加密（注册 / 修改密码时用）
     */
    public static String encrypt(String rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("password cannot be null");
        }
        return ENCODER.encode(rawPassword);
    }

    /**
     * 校验密码（登录时用）
     */
    public static boolean matches(String rawPassword, String encryptedPassword) {
        if (rawPassword == null || encryptedPassword == null) {
            return false;
        }
        return ENCODER.matches(rawPassword, encryptedPassword);
    }

    private PasswordUtil() {
    }
}
