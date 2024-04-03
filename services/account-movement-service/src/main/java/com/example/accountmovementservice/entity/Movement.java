package com.example.accountmovementservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.UUID;
@Entity
@Table(name = "movement")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Movement {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "date")
    private Date date;

    @Column(name = "movement_type")
    private String movementType;

    @Column(name = "value")
    private Double value;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "initial_balance")
    private Double initialBalance;

    @Column(name = "account_id")
    private UUID accountId;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account account;
}
