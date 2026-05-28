package com.my.total_jpa_back.users.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
// 응답 결과를 담아서 보내는 DTO
@Builder
public class HelloResponse {
    private String message;
    private int age;
}
