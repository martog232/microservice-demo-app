package com.example.carservice.web.model;

import lombok.Builder;

@Builder
public record CarReqModel(String name, Long userId) {
}
