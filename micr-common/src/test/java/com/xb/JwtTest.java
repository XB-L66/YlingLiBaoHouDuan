package com.xb;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtTest {
    //996cd10f4268436b8052c24cff508b4e
    @Test
    public void testCreatJwt(){
        String key="996cd10f4268436b8052c24cff508b4e";
        SecretKey secretKey= Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
        Date curDate=new Date();
        Map<String,Object> map=new HashMap<>();
        map.put("uid",1001);
        map.put("realname","zhangsan");
        String JWT = Jwts.builder().signWith(secretKey, SignatureAlgorithm.HS256)
                .setExpiration(DateUtils.addMinutes(curDate, 10))
                .setIssuedAt(curDate)
                .setId(UUID.randomUUID().toString()).
                addClaims(map).compact();
        System.out.println(JWT);
    }
    @Test
    public void testParseJwt(){
        String JWT="eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2OTM0MDI5OTQsImlhdCI6MTY5MzQwMjM5NCwianRpIjoiYmQyNjUwYWMtYjZhMC00YWVkLWJiN2ItNDQwZmJkY2FjYTE5IiwidWlkIjoxMDAxLCJyZWFsbmFtZSI6InpoYW5nc2FuIn0.p5E6U5DFvxqiRL1TqpoJpdjRqK_71JCvT0r7ttR6yio";
        String key="996cd10f4268436b8052c24cff508b4e";
        SecretKey secretKey= Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
        Jws<Claims> claimsJws=Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(JWT);

        Claims body=claimsJws.getBody();
        Integer uid = body.get("uid", Integer.class);
        System.out.println("uid = " + uid);
    }
}
