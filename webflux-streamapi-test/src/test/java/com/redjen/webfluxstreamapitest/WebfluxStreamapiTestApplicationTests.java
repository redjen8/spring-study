package com.redjen.webfluxstreamapitest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Flux;

@SpringBootTest
class WebfluxStreamapiTestApplicationTests {

    private final int[] intArr = { 1, 2, 3, 4, 5, 6, 7, 8};

    private static List<Integer> intArrayList1, intArrayList2;

    @BeforeAll
    static void init() {
        intArrayList1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        intArrayList2 = new ArrayList<>(Arrays.asList(8, 7, 6, 5, 4, 3, 2, 1));
    }

    @Test
    void zip_with_test() {
        Flux<Integer> flux1 = Flux.fromIterable(intArrayList1);
        Flux<Integer> flux2 = Flux.fromIterable(intArrayList2);
        flux1.zipWith(flux2).toIterable().forEach(System.out::println);
    }

    @Test
    void zip_test() {
        Flux<Integer> flux1 = Flux.fromIterable(intArrayList1);
        Flux<Integer> flux2 = Flux.fromIterable(intArrayList2);
        Flux<Integer> resultFlux = Flux.zip(flux1, flux2, Integer::sum);
        resultFlux.toIterable().forEach(x -> assertEquals(9, (int)x));
    }

}
