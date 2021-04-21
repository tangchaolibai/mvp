package com.hsb.mvpmsweb.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hsb.mvpmsweb.api.exception.MvpWebException;
import com.hsb.mvpmsweb.api.exception.MvpWebExceptionCode;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil {

    private static Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    /**
     * Parse jwt
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) throws MvpWebException {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (ExpiredJwtException  eje) {
        	logger.error("===== Token expiry =====", eje);
            throw new MvpWebException(MvpWebExceptionCode.ERROR_998_03);
        } catch (Exception e){
        	logger.error("===== token parse exception =====", e);
            throw new MvpWebException(MvpWebExceptionCode.ERROR_998_04);
        }
    }

    /**
     * build jwt
     * @param userId
     * @param username
     * @param role
     * @param audience
     * @return
     */
    public static String createJWT(String userId, String base64Security, int timeOut) throws MvpWebException {
        try {
            // use HS256 encryption algorithm
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            //generate sign secret key
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            //add parameters for JWT
            JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").setHeaderParam("alg", "HS256")
            		.claim("userId", userId)
                    .setSubject(userId)
                    .setIssuer("MVP-BE")
                    .setIssuedAt(new Date())
                    .setAudience("MVP-UI")
                    .signWith(signatureAlgorithm, signingKey);
            //add Token expiry
            if (timeOut >= 0) {
                long expMillis = nowMillis + timeOut * 1000l;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp)
                       .setNotBefore(now);
            }

            //generate JWT
            return builder.compact();
        } catch (Exception e) {
        	logger.error("Sign fail!", e);
            throw new MvpWebException(MvpWebExceptionCode.ERROR_998_05);
        }
    }

    /**
     * get ID from token
     * @param token
     * @param base64Security
     * @return
     */
    public static String getUserId(String token, String base64Secret) throws MvpWebException {
        return parseJWT(token, base64Secret).get("userId", String.class);
    }

    /**
     * check if token is expiry
     * @param token
     * @param base64Security
     * @return
     */
    public static boolean isExpiration(String token, String base64Security) throws MvpWebException {
        return parseJWT(token, base64Security).getExpiration().before(new Date());
    }
    
}
