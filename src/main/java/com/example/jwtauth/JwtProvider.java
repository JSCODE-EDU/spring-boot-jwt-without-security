package com.example.jwtauth;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
  private final String plainSecretKey = "secretKeyasdflkasndfnksecretKeyasdflkasndfnksecretKeyasdflkasndfnksecretKeyasdflkasndfnksecretKeyasdflkasndfnk";
  private final long tokenValidMillisecond = 1000L * 60 * 60; // 1000ms * 60 * 60 = 1시간

  private SecretKey secretKey = Keys.hmacShaKeyFor(plainSecretKey.getBytes(StandardCharsets.UTF_8));

  public JwtProvider() {
  }

  // Token 생성
  public String createJwsWithId(Long id) {
    Date now = new Date();
    return Jwts.builder()
        .subject(id.toString())
        .issuedAt(now)
        .expiration(new Date(now.getTime() + tokenValidMillisecond))
        .signWith(secretKey)
        .compact();
  }

  // Token 해독
  public Long getMemberIdByDecoding(String token) {
    try {
      String parsedId = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
          .getSubject();
      Long id = Long.parseLong(parsedId);
      return id;
    } catch (JwtException e) {
      System.out.println(e);
      throw e;
    }
  }
}
