package com.example.employeemanagementsystem.Common;

import com.example.employeemanagementsystem.Model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class JwtUtils {

    /**
     * JWT解析器
     */
    private final JwtParser jwtParser;
    /**
     * 秘钥
     */
    private final SecretKey secretKey;

    @Autowired
    private StringRedisTemplate redisTemplate;


    private final static ObjectMapper mapper = new ObjectMapper();

    public JwtUtils(String key) {
        // 生成秘钥
        secretKey = Keys.hmacShaKeyFor(key.getBytes(Charset.forName("UTF-8")));
        // JWT解析器
        this.jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
    }


    /**
     * 生成jwt，自己指定的JTI
     *
     * @param user 用户信息
     * @return JWT
     */
    public String createJwt(User user) {
        try {
            // 生成tokenid
            String jti=createJti();
            //存入redis中
            this.redisTemplate.opsForValue().set(RedisConstants.JTI_KEY_PREFIX+user.getUserName(),jti,30, TimeUnit.MINUTES);
            return Jwts.builder().signWith(secretKey)
                    .setId(jti)
                    .claim("user", mapper.writeValueAsString(user))
                    .compact();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析jwt，并将用户信息转为指定的Clazz类型
     *
     * @param jwt   token
     * @return 载荷，包含JTI和用户信息
     */
    public PayLoad parseJwt(String jwt) {
        try {
            Jws<Claims> claimsJws = jwtParser.parseClaimsJws(jwt);
            Claims claims = claimsJws.getBody();
            PayLoad payload = new PayLoad();
            payload.setJti(claims.getId());
            payload.setUser(mapper.readValue(claims.get("user", String.class), User.class));
            return payload;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createJti() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }

    /**
     * 刷新jwt有效期
     * @param username
     */
    public void refreshJwt(String  username) {
        String key= RedisConstants.JTI_KEY_PREFIX+username;
        //重置key的过期时间
        redisTemplate.expire(key,30, TimeUnit.MINUTES);
    }
}
