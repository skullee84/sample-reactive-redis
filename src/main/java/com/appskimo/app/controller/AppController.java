package com.appskimo.app.controller;

import com.appskimo.app.domain.sign.Sign;
import com.appskimo.app.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class AppController {

    private final AppService appService;

    @PostMapping("/sign")
    public void sign(ServerWebExchange exchange) {
        appService.sign(exchange).subscribe();
    }

    @GetMapping("/signs")
    public Flux<Sign> signs() {
        return appService.signs();
    }

}
