package com.example.clientpersonservice.converter;

import com.example.clientpersonservice.dtos.client.*;
import com.example.clientpersonservice.entity.Client;

public class ClientConverter {
    public static Client toEntity(SaveClientRequestDTO dto) {
        return Client.builder()
                .id(dto.getId())
                .clientId(dto.getClientId())
                .name(dto.getName())
                .gender(dto.getGender().name().charAt(0))
                .age(dto.getAge())
                .identification(dto.getIdentification())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .password(dto.getPassword())
                .status(dto.getStatus())
                .build();
    }

    public static Client toEntity(UpdateClientRequestDTO dto) {
        return Client.builder()
                .id(dto.getId())
                .clientId(dto.getClientId())
                .name(dto.getName())
                .gender(dto.getGender().name().charAt(0))
                .age(dto.getAge())
                .identification(dto.getIdentification())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .password(dto.getPassword())
                .status(dto.getStatus())
                .build();
    }

    public static Client toEntity(PatchClientRequestDTO dto) {
        return Client.builder()
                .id(dto.getId())
                .clientId(dto.getClientId())
                .name(dto.getName())
                .gender(dto.getGender().name().charAt(0))
                .age(dto.getAge())
                .identification(dto.getIdentification())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .password(dto.getPassword())
                .status(dto.getStatus())
                .build();
    }

    public static PatchClientResponseDTO toPatchClientResponseDTO(Client entity) {
        return PatchClientResponseDTO.builder()
                .id(entity.getId())
                .clientId(entity.getClientId())
                .name(entity.getName())
                .gender(PatchClientResponseDTO.Gender.valueOf(String.valueOf(entity.getGender())))
                .age(entity.getAge())
                .identification(entity.getIdentification())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .password(entity.getPassword())
                .status(entity.getStatus())
                .build();
    }

    public static UpdateClientResponseDTO toUpdateClientResponseDTO(Client entity) {
        return UpdateClientResponseDTO.builder()
                .id(entity.getId())
                .clientId(entity.getClientId())
                .name(entity.getName())
                .gender(UpdateClientResponseDTO.Gender.valueOf(String.valueOf(entity.getGender())))
                .age(entity.getAge())
                .identification(entity.getIdentification())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .password(entity.getPassword())
                .status(entity.getStatus())
                .build();
    }
}
