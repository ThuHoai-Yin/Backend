package com.example.demo.security;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.demo.model.CustomUserDetails;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {
	
	//** Secret key */
    private final String JWT_SECRET = "thuhoai";
    
    //** jwt token expiration */
    private final long JWT_EXPIRATION = 604800000L;

    /**
   	 * Create jwt token
   	 * 
   	 * @param userDetail
   	 * @return String
   	 */
    public String generateToken(CustomUserDetails userDetails) {
    	
        //** expiry date of jwt */
        Date expiryDate = new Date(new Date().getTime() + JWT_EXPIRATION);
        
        // Create JWT from username
        String jwt=Jwts.builder()
                .setSubject((userDetails.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
        return jwt;
        
    }

    /**
   	 * Get username from JWT
   	 * 
   	 * @param jwt token
   	 * @return string
   	 */
    public String getUsernameFromJWT(String token) {
    	
    	//** get subject from token */
        String claims = Jwts.parser()
                            .setSigningKey(JWT_SECRET)
                            .parseClaimsJws(token)
                            .getBody().getSubject();   
        return claims;
    }

    /**
   	 * Validate token
   	 * 
   	 * @param jwt token
   	 * @return boolean
   	 */
    public boolean validateToken(String authToken) {
    	
        try {
        	// check jwt valid with server secrect key
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
           System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
        	System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
        	System.out.println("JWT claims string is empty.");
        }
        return false;
        
    }
    
}
