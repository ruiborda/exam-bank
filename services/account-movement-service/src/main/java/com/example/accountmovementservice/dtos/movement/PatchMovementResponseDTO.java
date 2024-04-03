package com.example.accountmovementservice.dtos.movement;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
public class PatchMovementResponseDTO {
    @NotNull
    private UUID id;
    private Date date;
    private String movementType;
    private Double value;
    private Double balance;
    private UUID accountId;
}
