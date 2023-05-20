package com.livraria.sosleitura.security;

import com.livraria.sosleitura.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "tokenRefresh")
public class TokenRefresh {

    @Id
    private UUID id;
    private LocalDateTime expires;
    private LocalDateTime creatAt;
}
