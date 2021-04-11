package cn.edu.scau.ticket.application.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Map;

/**
 * @description: token的工具类
 * @author: Tensh
 * @createDate: 2021/4/6
 */
@Component
@Data
public class JWTUtil {
    @Value("${token.header}")
    private String header;

    @Value("${token.secret}")
    private String secret;

    @Value("${token.expireTime}")
    private String expireTime;

    @Value("${token.issuer}")
    private String issuer;

    /**
     * 生成token
     * @param claims
     * @return
     */
    public String generateToken(Map<String, Object> claims) {
        long time = System.currentTimeMillis() + Long.parseLong(expireTime) * 60 * 1000;
        return Jwts.builder()
                .setIssuer(issuer)
                .setExpiration(new Date(time))
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    /**
     * 获取声明
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims;
    }

    public String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    /**
     * token是否过期
     * @param token
     * @return
     */
    public boolean isExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    /**
     * 检验token：分别检验是否过期/是否被篡改
     * @return
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return false;
        }
        return !isExpired(token);
    }
}
