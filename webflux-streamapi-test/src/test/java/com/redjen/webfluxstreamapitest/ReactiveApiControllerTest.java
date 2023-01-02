package com.redjen.webfluxstreamapitest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ReactiveApiControllerTest {

    private WebTestClient client;

    @BeforeEach
    public void setUp(ApplicationContext context) {
        this.client = WebTestClient
                .bindToApplicationContext(context)
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

    @Test
    void testDomainApiTest() {
        Flux<TestDomain> result = client.get()
                .uri("/test")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .returnResult(TestDomain.class)
                .getResponseBody();

        StepVerifier.create(result)
                .assertNext(t -> assertThat(t.getName()).isEqualTo("test1"))
                .assertNext(t -> assertThat(t.getName()).isEqualTo("test2"))
                .verifyComplete();
    }
}
