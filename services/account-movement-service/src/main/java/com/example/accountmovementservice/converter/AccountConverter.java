package com.example.accountmovementservice.converter;

import com.example.accountmovementservice.dtos.account.*;
import com.example.accountmovementservice.entity.Account;

public class AccountConverter {
    public static Account toEntity(SaveAccountRequestDTO dto) {
        return Account.builder()
                .id(dto.getId())
                .accountNumber(dto.getAccountNumber())
                .accountType(dto.getAccountType())
                .initialBalance(dto.getInitialBalance())
                .status(dto.getStatus())
                .clientId(dto.getClientId())
                .build();
    }

    public static Account toEntity(UpdateAccountRequestDTO dto) {
        return Account.builder()
                .id(dto.getId())
                .accountNumber(dto.getAccountNumber())
                .accountType(dto.getAccountType())
                .initialBalance(dto.getInitialBalance())
                .status(dto.getStatus())
                .clientId(dto.getClientId())
                .build();
    }

    public static PatchAccountResponseDTO toPatchAccountResponseDTO(Account entity) {
        return PatchAccountResponseDTO.builder()
                .id(entity.getId())
                .accountNumber(entity.getAccountNumber())
                .accountType(entity.getAccountType())
                .initialBalance(entity.getInitialBalance())
                .status(entity.getStatus())
                .clientId(entity.getClientId())
                .build();
    }

    public static UpdateAccountResponseDTO toUpdateAccountResponseDTO(Account entity) {
        return UpdateAccountResponseDTO.builder()
                .id(entity.getId())
                .accountNumber(entity.getAccountNumber())
                .accountType(entity.getAccountType())
                .initialBalance(entity.getInitialBalance())
                .status(entity.getStatus())
                .clientId(entity.getClientId())
                .build();
    }

    public static FindAccountByIdResponseDTO toFindAccountByIdResponseDTO(Account entity) {
        return FindAccountByIdResponseDTO.builder()
                .id(entity.getId())
                .accountNumber(entity.getAccountNumber())
                .accountType(entity.getAccountType())
                .initialBalance(entity.getInitialBalance())
                .status(entity.getStatus())
                .clientId(entity.getClientId())
                .build();
    }
}

