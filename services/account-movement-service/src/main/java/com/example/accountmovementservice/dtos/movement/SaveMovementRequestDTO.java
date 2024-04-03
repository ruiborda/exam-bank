package com.example.accountmovementservice.dtos.movement;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
public class SaveMovementRequestDTO {
    private UUID id;
    private Date date;
    @NotNull
    private MovementType movementType;
    @NotNull
    private Double value;
    @NotNull
    private UUID accountId;

    @Getter
    public enum MovementType {
        DEPOSIT("DEPOSIT"),
        WITHDRAW("WITHDRAW");

        private final String value;

        MovementType(String value) {
            this.value = value;
        }
    }

}
