package com.example.accountmovementservice.dtos.movement;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
public class PatchMovementRequestDTO {
    @NotNull
    private UUID id;
    private Date date;
    private String movementType;
    private Double value;
    private Double balance;
    private UUID accountId;
}
