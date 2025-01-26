package com.wrx.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * 登录验证：令牌创建、令牌验证
 */
public class JwtUtils {

    // 自定义密钥（需要256位（即32字节）的密钥大约可以容纳约10到11个汉字）
    private static final String JWT_SECRET_KEY = "MjU25L2N77yI5Y2zMzLlrZfoioLvvInnmoTlr4bpkqXlpKfnuqblj6/ku6XlrrnnurPnuqYxMOWIsDEx5Liq5rGJ5a2X";
    // 设置JWT过期时间为12小时
    private static final Integer EXPIRATION_TIME = 12 * 60 * 60 * 1000;

    /**
     * 创建令牌
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();
    }

    /**
     * 解析令牌
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

}
