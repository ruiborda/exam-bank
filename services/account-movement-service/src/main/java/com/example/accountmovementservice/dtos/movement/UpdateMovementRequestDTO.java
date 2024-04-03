package com.example.accountmovementservice.dtos.movement;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
public class UpdateMovementRequestDTO {
    @NotNull
    private UUID id;
    private Date date;
    @NotNull
    private MovementType movementType;
    @NotNull
    private Double value;
    @NotNull
    private Double balance;
    @NotNull
    private UUID accountId;

    public enum MovementType {
        DEPOSIT,
        WITHDRAW,
    }
}
