package com.example.clientpersonservice.dtos.client;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class PatchClientResponseDTO {
    @NotNull
    private UUID id;
    @NotNull
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
