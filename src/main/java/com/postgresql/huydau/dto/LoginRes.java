package com.postgresql.huydau.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRes {
    private final String accessToken;
}
