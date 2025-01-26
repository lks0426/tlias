package com.wrx;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class GenJwtTest {

    /**
     * 生成令牌
     */
    @Test
    public void genJwt() {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", 1);
        claims.put("name", "wrx");
        claims.put("password", "123456");

        // 设置密钥需要256位（即32字节）的密钥大约可以容纳约10到11个汉字
        String msg = "MjU25L2N77yI5Y2zMzLlrZfoioLvvInnmoTlr4bpkqXlpKfnuqblj6/ku6XlrrnnurPnuqYxMOWIsDEx5Liq5rGJ5a2X";

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,msg) // 指定加密算法，及密钥
                .addClaims(claims) // 添加自定义信息
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 设置过期时间
                .compact(); // 生成令牌
        System.out.println(jwt);
    }

    /**
     * 解析令牌
     */
    @Test
    public void parseJwt() {
        String msg = "MjU25L2N77yI5Y2zMzLlrZfoioLvvInnmoTlr4bpkqXlpKfnuqblj6/ku6XlrrnnurPnuqYxMOWIsDEx5Liq5rGJ5a2X";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsIm5hbWUiOiJ3cngiLCJpZCI6MSwiZXhwIjoxNzM2Mzk0OTU2fQ.Nu15ItGywySamp7T6CJ8uxxYP_A7S8epTmLcVXRpSDg";
        Claims claims = Jwts.parser()
                .setSigningKey(msg) // 指定密钥，跟上面设置的密钥保持一致
                .build()
                .parseClaimsJws(token) // 解析上面创建生成的令牌
                .getBody();
        System.out.println(claims);
    }
}
