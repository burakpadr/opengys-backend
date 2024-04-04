package com.padr.gys.domain.disposable.entity;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash("DisposableIdentifier")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisposableIdentifier {

    private String id;

    private String key;

    @Indexed
    @Builder.Default
    private String uuid = UUID.randomUUID().toString();

    @TimeToLive(unit = TimeUnit.SECONDS)
    @Builder.Default
    private Long maxLifetime = 86400L; // 86400 seconds = 24 hours
}
