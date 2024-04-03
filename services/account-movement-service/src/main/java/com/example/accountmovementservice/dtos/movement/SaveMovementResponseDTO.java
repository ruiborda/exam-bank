package com.example.accountmovementservice.dtos.movement;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Getter
@Builder
@Setter
public class SaveMovementResponseDTO {
    private UUID id;
    private Date date;
    @NotNull
    private String movementType;
    @NotNull
    private Double value;
    @NotNull
    private Double balance;
    @NotNull
    private UUID accountId;
}
