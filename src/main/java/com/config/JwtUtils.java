package com.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JWT的token，区分大小写
 */
@Component
@Slf4j
public class JwtUtils {

    @Value("${jwt.secretKey}")
    private String secret;
    @Value("${jwt.expire}")
    private String expire;
    @Value("${jwt.header}")
    private String header;

    /**
     * 生成token
     * @param subject
     * @return
     */
    public String createToken (String subject){
        long parseExpire = Long.parseLong(expire);
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + parseExpire*1000);//过期时间
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(subject)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    /**
     * 获取token中注册信息
     * @param token
     * @return
     */
    public Claims getTokenClaim (String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (Exception e){
//            e.printStackTrace();
            return null;
            /*  catch (ExpiredJwtException e){
                    return e.getClaims(); //防止jwt过期解析报错
                }
            */
        }
    }
    /**
     * 验证token是否过期失效
     * @param expirationTime
     * @return
     */
    public boolean isTokenExpired (Date expirationTime) {
        return expirationTime.before(new Date());
    }

    /**
     * 获取token失效时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        return getTokenClaim(token).getExpiration();
    }
    /**
     * 获取用户名从token中
     */
    public String getUsernameFromToken(String token) {
        return getTokenClaim(token).getSubject();
    }

    //删除 token

    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getTokenClaim(token).getIssuedAt();
    }

    // --------------------- getter & setter ---------------------

    public String getSecret() {
        return secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }
    public long getExpire() {
        long parseExpire = Long.parseLong(expire);
        return parseExpire;
    }
    public void setExpire(long expire) {
        this.expire = expire+"";
    }
    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }
}