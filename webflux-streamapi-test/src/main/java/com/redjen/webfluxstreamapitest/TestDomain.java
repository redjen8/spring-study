package com.redjen.webfluxstreamapitest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestDomain {
    private String name;
    private LocalDateTime createdAt;
}
