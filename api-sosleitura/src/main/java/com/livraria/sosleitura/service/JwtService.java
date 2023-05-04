package com.livraria.sosleitura.service;

import com.livraria.sosleitura.model.JwtConstants;
import com.livraria.sosleitura.model.Usuario;
import com.livraria.sosleitura.security.UserDetailsCustom;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.Access;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService {

    @Autowired
    private UsuarioService usuarioService;

    public String codeJwtToken(Map<String,Object> claims, String sub) throws NoSuchAlgorithmException {


      String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer("localhost:8080")
                .setSubject(sub)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(1L).atZone(ZoneId.systemDefault())
                        .toInstant()))
                .signWith(getKeyCode())
                .compact();

      return token;
    }

    public Boolean validToken(String token, UserDetails userDetails) {
        String login = getSubjectFromToken(token);
        log.info(String.format("%s",!exipredToken(token)));
        return (userDetails.getUsername().equals(login)) && !exipredToken(token);
    }

    public Claims getAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(JwtConstants.CHAVE_TOKEN)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaimFromToken(String token, Function<Claims,T> getClaim){
        Claims claims = getAllClaims(token);
        return getClaim.apply(claims);
    }
    public String getSubjectFromToken(String token){
        return getClaimFromToken(token,Claims::getSubject);
    }

    public Date getExpirationDate(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }

    public Key getKeyCode() throws NoSuchAlgorithmException {
        byte[] encodeKey = Decoders.BASE64.decode(JwtConstants.CHAVE_TOKEN);
        return Keys.hmacShaKeyFor(encodeKey);
    }

    public Boolean exipredToken(String token){
        return getExpirationDate(token).before(new Date(System.currentTimeMillis()));
    }
}
