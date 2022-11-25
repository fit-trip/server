package com.example.mapp.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    /**
     * 서버 스펙 JWT 유효기간
     */
    private static final long TOKEN_VALIDATION_SECOND = 1000L * 60 * 120;
    public static final String APP_REFRESH_TOKEN_NAME = "app_refresh_token";

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    @Value("${group.name}")
    private String ISSUER;


    public String generateAccessToken(String id) {
        Map<String, String> payload = Map.of(
                "type", "access",
                "id", id

        );
        return doGenerateToken(TOKEN_VALIDATION_SECOND, payload);
    }

    public String getIdFromToken(String token) {
        DecodedJWT decodedJWT = verifyAccessToken(token, getAccountTokenValidator());
        return decodedJWT.getClaim("id").asString();
    }

    private DecodedJWT verifyAccessToken(String token, JWTVerifier verifier) {
        try {
            return verifier.verify(token);
        }  catch (InvalidClaimException e) {
            throw new IllegalStateException("토큰이 올바르지 않습니다.");
        } catch (TokenExpiredException e) {
            throw new IllegalStateException("토큰이 만료되었습니다.");
        }
    }

    private JWTVerifier getAccountTokenValidator() {
        return JWT.require(getSigningKey(SECRET_KEY))
                .withClaimPresence("type")
                .withClaimPresence("id")
                .withIssuer(ISSUER)
                .build();
    }

    private Algorithm getSigningKey(String secretKey) {
        return Algorithm.HMAC256(secretKey);
    }

    private String doGenerateToken(long expireTime, Map<String, String> payload) {
        return JWT.create()
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
                .withPayload(payload)
                .withIssuer(ISSUER)
                .sign(getSigningKey(SECRET_KEY));
    }
}
