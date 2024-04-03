package com.example.accountmovementservice.dtos.client_person_external_service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindByClientIdResponseDTO {
    private Boolean success;
    private String message;
    private Integer status;
    private String code;
    private String[] errors;
    private Data data;

    @Getter
    @Setter
    public static class Data {
        private String id;
        private String name;
        private String gender;
        private Short age;
        private String identification;
        private String address;
        private String phone;
        private String clientId;
        private String password;
        private Boolean status;
    }
}
