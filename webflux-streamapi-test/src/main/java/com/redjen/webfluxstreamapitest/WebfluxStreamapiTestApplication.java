package com.redjen.webfluxstreamapitest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

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

    @GetMapping("/test")
    Flux<TestDomain> testDomainFlux () {
        return Flux.just(
                TestDomain.builder().name("test1").createdAt(LocalDateTime.now()).build(),
                TestDomain.builder().name("test2").createdAt(LocalDateTime.now()).build()
        );
    }

    @GetMapping("/stream")
    Flux<Map<String, Integer>> streamTest() {
        Stream<Integer> intStream = Stream.iterate(1, i -> i+1).limit(100);
        return Flux.fromStream(intStream)
            .map(i -> Collections.singletonMap("value", i));
    }
}
