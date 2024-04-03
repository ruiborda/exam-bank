package com.example.accountmovementservice.dtos.movement;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Getter
@Builder
@Setter
public class FindMovementsByDateRangeResponseDTO {
    private UUID id;
    private Date date;
    private String movementType;

    private Double value;

    private Double balance;

    private UUID accountId;

    private String accountNumber;

    private String clientName;
}
