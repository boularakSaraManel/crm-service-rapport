package com.example.rapportservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    //same key as the one in SECURITY-SERVICE and the methods in all the Microservices
    private static final String SECRET_KEY = "357538782F413F4428472B4B6250655367566B59703373367639792442264529";


    //takes a JWT token as a String and returns its claims as a Claims object.
    public static Claims getClaimsFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }


    //retrieves the jwt from the authentication
    public static String getJwtTokenFromAuthentication(Authentication authentication) {
        String jwt = (String) authentication.getCredentials();
        return jwt;
    }


    //takes an Authentication object, retrieves the JWT token from it using getJwtTokenFromAuthentication, and returns the claims as a Claims object.
    public static Claims getClaimsFromAuthentication(Authentication authentication) {
        String jwt = getJwtTokenFromAuthentication(authentication);
        Claims claims = getClaimsFromJwtToken(jwt);
        return claims;
    }

    //retrieves the Authentication object from the SecurityContextHolder, retrieves the JWT token from it using getJwtTokenFromAuthentication, and returns the claims as a Claims object.
    public static Claims getCurrentUserClaims() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Claims claims = getClaimsFromAuthentication(authentication);
        return claims;
    }

    //retrieves the "sub" claim (email) from the JWT token in the Authentication object
    public static String getEmailFromAuthentication(Authentication authentication) {
        Claims claims = getClaimsFromAuthentication(authentication);
        String email = claims.get("sub", String.class);
        return email;
    }


    //added cuz of AuthMicroserviceClient: to extract user with restTemplate email request
    /*public static String getUsernameFromAuthentication(Authentication authentication, AuthMicroserviceClient authMicroserviceClient) {
        String jwt = getJwtTokenFromAuthentication(authentication);
        String username = authMicroserviceClient.getUsernameFromAuthMicroservice(jwt);
        return username;
    }*/


}




    // hadi jbtha mn security-service im going to adapt it to my previous methods OR not, found another sol: get userByEmail b restTemplate
    // newly added cuz i added userID to claims
    /*public Long extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("userId", Long.class));
    }*/



