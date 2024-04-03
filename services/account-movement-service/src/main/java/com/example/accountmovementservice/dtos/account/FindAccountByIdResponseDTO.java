package com.example.accountmovementservice.dtos.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class FindAccountByIdResponseDTO {
    private UUID id;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private Boolean status;
    private UUID clientId;
    private String clientName;
}
