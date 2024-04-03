package com.example.accountmovementservice.dtos.account;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class PatchAccountResponseDTO {
    @NotNull
    private UUID id;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private Boolean status;
    private UUID clientId;
}