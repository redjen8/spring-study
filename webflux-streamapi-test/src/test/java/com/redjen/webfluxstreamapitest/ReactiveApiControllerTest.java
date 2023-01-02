package com.redjen.webfluxstreamapitest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.test.StepVerifier;

@SpringBootTest
public class ReactiveApiControllerTest {

    private WebTestClient client;

    @BeforeEach
    public void setUp() {
        this.client = WebTestClient.bindToServer()
            .baseUrl("http://localhost:8080")
            .build();
    }

    @Test
    void helloApiTest() {
        FluxExchangeResult<String> result = client
            .get()
            .uri("/hello")
            .accept(MediaType.TEXT_EVENT_STREAM)
            .exchange()
            .returnResult(String.class);

        StepVerifier.create(result.getResponseBody())
            .expectNext("Hello")
            .expectNext("world!")
            .verifyComplete();
    }
}
