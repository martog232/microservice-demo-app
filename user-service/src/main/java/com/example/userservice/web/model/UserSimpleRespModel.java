package com.example.userservice.web.model;

import lombok.Builder;

@Builder
public record UserSimpleRespModel(String name, String countryName) {
}
