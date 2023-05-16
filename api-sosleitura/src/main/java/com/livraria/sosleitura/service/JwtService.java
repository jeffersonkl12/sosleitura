package com.livraria.sosleitura.service;

import com.livraria.sosleitura.model.JwtConstants;
import com.livraria.sosleitura.security.TokenRefresh;
import com.livraria.sosleitura.model.Usuario;
import com.livraria.sosleitura.repository.TokenRefreshRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TokenRefreshRepository repository;

    public String codeJwtToken(Map<String,Object> claims, String login) throws NoSuchAlgorithmException {

      String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer("localhost:8080")
                .setSubject(login)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(1L).atZone(ZoneId.systemDefault())
                        .toInstant()))
                .signWith(getKeyCode())
                .compact();

      return token;
    }

    public String codeJwtTokenRefresh(Map<String,Object> claims, String login) throws NoSuchAlgorithmException {

        Map<String,Object> claimsRefresh = new HashMap<>();
        claimsRefresh.putAll(claims);
        TokenRefresh tokenRefresh = salvaTokenRefresh(login);

        String tokenRefresehCode = Jwts.builder()
                .setClaims(claimsRefresh)
                .setIssuer("localhost:8080")
                .setSubject(login)
                .setIssuedAt(Date.from(tokenRefresh.getCreatAt().atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(tokenRefresh.getExpires().atZone(ZoneId.systemDefault()).toInstant()))
                .setId(tokenRefresh.getId().toString())
                .signWith(getKeyCode())
                .compact();

        return tokenRefresehCode;
    }

    public Boolean validToken(String token, UserDetails userDetails) {
        String login = getSubjectFromToken(token);
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
    public Date getAt(String token){
        return getClaimFromToken(token,Claims::getIssuedAt);
    }
    public String getAud(String token){
        return getClaimFromToken(token,Claims::getAudience);
    }
    public String getJti(String token){
        return getClaimFromToken(token,Claims::getId);
    }

    public Key getKeyCode() throws NoSuchAlgorithmException {
        byte[] encodeKey = Decoders.BASE64.decode(JwtConstants.CHAVE_TOKEN);
        return Keys.hmacShaKeyFor(encodeKey);
    }

    public Boolean exipredToken(String token){
        return getExpirationDate(token).before(new Date(System.currentTimeMillis()));
    }

    public TokenRefresh salvaTokenRefresh(String login){
        TokenRefresh tokenRefresh = new TokenRefresh();
        Usuario usuario = usuarioService.buscaUsuarioByLogin(login);

        tokenRefresh.setUsuario(usuario);
        tokenRefresh.setCreatAt(LocalDateTime.now());
        tokenRefresh.setExpires(LocalDateTime.now().plusHours(8L));
        tokenRefresh = repository.save(tokenRefresh);

        return tokenRefresh;
    }

    public void deleteTokenRefreshByJti(String tokenRefresh){
        String id = getJti(tokenRefresh);
        repository.deleteById(UUID.fromString(id));
    }

    public Boolean existTokenById(String tokenRefresh){
        String id = getJti(tokenRefresh);
        return repository.existsById(UUID.fromString(id));

    }

    @Transactional
    public void deleteTokenByUser(String login){
        log.info(usuarioService.buscaUsuarioByLogin(login).getLogin());
        Usuario usuario = usuarioService.buscaUsuarioByLogin(login);
        if(repository.existsByUsuario(usuario)){
            repository.deleteByUsuario(usuario);
        }
    }
}
