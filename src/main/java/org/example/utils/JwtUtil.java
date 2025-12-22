package org.example.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;


public class JwtUtil {

    // 你的项目密钥，务必保密（建议放配置文件）
    private static final String SECRET = "your-super-secret-key-your-super-secret-key";

    // token 有效期（毫秒）— 这里设置为 7 天
    private static final long EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000L;

    private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    /**
     * ✅ 生成 Token（可根据用户信息生成）
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * ✅ 解析 Token，获取载荷（claims）
     */
    public static Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            return null; // token 失效或伪造
        }
    }

    /**
     * ✅ 校验 Token 是否有效
     */
    public static boolean verifyToken(String token) {
        return parseToken(token) != null;
    }

    /**
     * ✅ 获取用户名（或其他自定义字段）
     */
    public static String getUsername(String token) {
        Claims claims = parseToken(token);
        return claims != null ? (String) claims.get("username") : null;
    }
}
