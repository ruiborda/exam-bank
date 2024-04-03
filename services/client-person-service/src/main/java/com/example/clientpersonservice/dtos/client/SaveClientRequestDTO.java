package com.example.clientpersonservice.dtos.client;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SaveClientRequestDTO {
    private UUID id;
    private UUID clientId;
    @NotNull
    private String name;
    @NotNull
    private Gender gender;
    @NotNull
    private Short age;
    @NotNull
    private String identification;
    @NotNull
    private String address;
    @NotNull
    private String phone;
    @NotNull
    private String password;
    @NotNull
    private Boolean status;

    @Getter
    public enum Gender {
        M, F,
    }
}
