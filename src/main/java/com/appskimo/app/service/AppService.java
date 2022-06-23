package com.appskimo.app.service;

import com.appskimo.app.domain.sign.Sign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppService {

    private final ReactiveRedisOperations<String, Sign> signOperations;

    private static final String TEST_KEY = "test";

    public Mono<Boolean> sign(ServerWebExchange exchange) {
        log.info("{}", Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress());

        return signOperations.opsForList().rightPush(TEST_KEY, getDummy())
            .filter(it -> it > 3)
            .flatMap(r -> signOperations.opsForList().trim(TEST_KEY, 2, -1));
    }

    public Flux<Sign> signs() {
        return signOperations.opsForList().range(TEST_KEY, -2, -2);
    }

    private Sign getDummy() {
        final var sign = new Sign();
        sign.setAccount("dominic.lee");
        sign.setRemoteAddress("127.0.0.1");
        sign.setCreatedDateTime(LocalDateTime.now());

        return sign;
    }

}
