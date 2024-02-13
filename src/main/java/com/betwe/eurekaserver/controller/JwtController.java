package com.betwe.eurekaserver.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author imjh8751
 *
 */
@RestController
@RequestMapping("/jwt")
public class JwtController {
	
    private final String secretKey = "123456789"; // JWT 시크릿 키

    // JWT에 KEY를 생성합니다.
    @GetMapping("/generateToken")
    public String generateToken(String email, String name) {
    	System.out.println("email ::: " + email);
		// Header
		Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");
        
        // Payload
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("email", email);
        payloads.put("name", name);
        
        Date currentDate = new Date();
        //Date expirationDate = new Date(currentDate.getTime() + 3600000);
        
        return Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setIssuedAt(currentDate)
                .setExpiration(null)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes()) // Signature
                .compact();
    }

    // JWT에 담기는 정보를 추출합니다.
    @GetMapping("/getMemberEmailFromToken")
    public String getMemberEmailFromToken(String token) {
    	System.out.println("token ::: " + token);
    	
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
                
        String email = claims.get("email", String.class);
        String name = claims.get("name", String.class);
		return email + "/" + name;
    }

    // JWT에서 클레임(클레임: JWT에 담기는 정보)을 추출합니다.
    @GetMapping("/extractClaims")
    public Claims extractClaims(String token) {
    	System.out.println("token ::: " + token);
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException ex) {
            // JWT가 만료되었을 때 여기로 예외가 던져집니다.
            // 이 예외를 활용하여 만료 여부를 확인할 수 있습니다.
            return ex.getClaims();
        }
    }

    // JWT의 만료 여부를 확인합니다.
    @GetMapping("/isTokenExpired")
    public boolean isTokenExpired(String token) {
    	System.out.println("token ::: " + token);
        Date expirationDate = extractClaims(token).getExpiration();
        return expirationDate.before(new Date());
    }

    @GetMapping("/isTokenExpired2")
    public boolean isValidToken(JwtParser jwtParser, String token) {
        try {
            Jws<Claims> claims = jwtParser.parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
