package com.livraria.sosleitura.controller;

import com.livraria.sosleitura.error.TokenRefreshNotValidException;
import com.livraria.sosleitura.repository.TokenRefreshRepository;
import com.livraria.sosleitura.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(path = "/token")
@RestController
@Slf4j
public class JwtController {

    @Autowired
    public JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenRefreshRepository tokenRefreshRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/refresh")
    public ResponseEntity<?> jwtRefresh(WebRequest request) throws NoSuchAlgorithmException {
        String authorization = request.getHeader("Authorization");

        if(authorization != null && !authorization.isEmpty() && !authorization.isBlank()){
            String tokenRefresh = authorization.substring(7);
            String login = jwtService.getSubjectFromToken(tokenRefresh);
            if(jwtService.validToken(tokenRefresh,userDetailsService.loadUserByUsername(login))){
                if(jwtService.existTokenById(tokenRefresh)){
                    jwtService.deleteTokenRefreshByJti(tokenRefresh);

                    String token = jwtService.codeJwtToken(new HashMap<>(), login);
                    String novoTokenRefresh = jwtService.codeJwtTokenRefresh(new HashMap<>(),login);

                    Map<String,Object> response = new HashMap<>();
                    response.put("token_at",token);
                    response.put("token_rf",novoTokenRefresh);

                   return ResponseEntity.ok().body(response);
                }
            }
        }
        throw new TokenRefreshNotValidException("token refresh invalido");
    }
}
