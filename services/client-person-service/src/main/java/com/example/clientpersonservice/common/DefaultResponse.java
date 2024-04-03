package com.example.clientpersonservice.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
public class DefaultResponse<T> {
    private LocalDateTime timestamp = LocalDateTime.now();
    private Boolean success;
    private String message;
    private Integer status;
    private String code;
    private T data;
    private String[] errors;
}
