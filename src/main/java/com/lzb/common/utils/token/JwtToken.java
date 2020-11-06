package com.lzb.common.utils.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtToken {
    // 过期时间
    private static long expire = 1000*60*60*24*365*100;
    public static String TOKEN="HSyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9";
    //生产token
    public  String createJwtToken(Long id) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expire);
        return Jwts.builder().setHeaderParam("type", "JWT").setSubject(id.toString()).setIssuedAt(now)
                .setExpiration(expireDate).signWith(
                        SignatureAlgorithm.HS512, TOKEN).compact();
    }
    //生产token
    public  String createJwtToken(String id) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expire);
        return Jwts.builder().setHeaderParam("type", "JWT").setSubject(id).setIssuedAt(now)
                .setExpiration(expireDate).signWith(
                        SignatureAlgorithm.HS512, TOKEN).compact();
    }

    //解密token
    public  Claims verifyToken(String token) {
        try {
            return Jwts.parser().setSigningKey(TOKEN).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            System.out.println("validate is token error");
            return null;
        }

    }

    /**
     * 判断 token 是否过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
     }
}
