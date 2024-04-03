package com.example.accountmovementservice.converter;

import com.example.accountmovementservice.dtos.movement.*;
import com.example.accountmovementservice.entity.Movement;

public class MovementConverter {
    public static Movement toEntity(SaveMovementRequestDTO dto) {
        return Movement.builder()
                .date(dto.getDate())
                .movementType(dto.getMovementType().name())
                .value(dto.getValue())
                .accountId(dto.getAccountId())
                .build();
    }

    public static Movement toEntity(UpdateMovementRequestDTO dto) {
        return Movement.builder()
                .date(dto.getDate())
                .movementType(dto.getMovementType().name())
                .value(dto.getValue())
                .balance(dto.getBalance())
                .accountId(dto.getAccountId())
                .build();
    }

    public static PatchMovementResponseDTO toPatchMovementResponseDTO(Movement entity) {
        return PatchMovementResponseDTO.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .movementType(entity.getMovementType())
                .value(entity.getValue())
                .balance(entity.getBalance())
                .accountId(entity.getAccountId())
                .build();
    }

    public static UpdateMovementResponseDTO toUpdateMovementResponseDTO(Movement entity) {
        return UpdateMovementResponseDTO.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .movementType(entity.getMovementType())
                .value(entity.getValue())
                .balance(entity.getBalance())
                .accountId(entity.getAccountId())
                .build();
    }

    public static SaveMovementResponseDTO toSaveMovementResponseDTO(Movement entity) {
        return SaveMovementResponseDTO.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .movementType(entity.getMovementType())
                .value(entity.getValue())
                .balance(entity.getBalance())
                .accountId(entity.getAccountId())
                .build();
    }

    public static FindMovementsByDateRangeResponseDTO toFindMovementsByDateRangeResponseDTO(Movement entity) {
        return FindMovementsByDateRangeResponseDTO.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .movementType(entity.getMovementType())
                .value(entity.getValue())
                .balance(entity.getBalance())
                .accountId(entity.getAccountId())
                .build();
    }
}
