package com.example.clientpersonservice.dtos.client;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PatchClientRequestDTO {
    @NotNull
    private UUID id;
    private UUID clientId;
    private String name;
    private Gender gender;
    private Short age;
    private String identification;
    private String address;
    private String phone;
    private String password;
    private Boolean status;

    @Getter
    public enum Gender {
        M, F,
    }
}
