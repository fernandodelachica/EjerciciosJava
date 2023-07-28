package com.bosonit.formacion.block7CrudValidationRebuild.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtils {
    @Value("${jwt.secret.key}")
    private String secretKey;


    //Obtiene la firma del token
    public Key getSignatureKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Genera el token de acceso
    public String generateAccessToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (864 * 10000))) //24 horas = 86400000 milisegundos
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Comprueba si el token es válido
    public boolean isTokenValid(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e){
            log.error("Token inválido, error: "+e);
            return false;
        }
    }


    //Obtiene el Claim completo (información) del token
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Obtiene solo el userName del token
    public String getUserNameFromToken(String token){
        return getClaims(token, Claims::getSubject);
    }


    //Obtiene un solo Claim (Información) del token
    public <T> T getClaims(String token, Function<Claims, T> claimsTFunction){
        Claims claims = extractAllClaims(token);

        return claimsTFunction.apply(claims);
    }


}
