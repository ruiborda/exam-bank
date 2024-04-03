package com.example.accountmovementservice.dtos.account;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SaveAccountRequestDTO {
    private UUID id;
    @NotNull
    private String accountNumber;
    @NotNull
    private String accountType;
    @NotNull
    private Double initialBalance;
    @NotNull
    private Boolean status;
    @NotNull
    private UUID clientId;
}
