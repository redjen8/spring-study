package com.redjen.webfluxstreamapitest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@SpringBootApplication
public class WebfluxStreamapiTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxStreamapiTestApplication.class, args);
    }

    @GetMapping("/hello")
    Flux<String> hello() {
        return Flux.just("Hello", "world!");
    }

}
