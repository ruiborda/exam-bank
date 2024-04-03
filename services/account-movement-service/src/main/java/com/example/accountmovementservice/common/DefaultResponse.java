package com.example.accountmovementservice.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class DefaultResponse<T> {
    private Boolean success;
    private String message;
    private Integer status;
    private String code;
    private T data;
    private String[] errors;
}
