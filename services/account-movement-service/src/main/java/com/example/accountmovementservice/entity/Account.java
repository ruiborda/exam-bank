package com.example.accountmovementservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "account")
@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Account {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "initial_balance")
    private Double initialBalance;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "client_id")
    private UUID clientId;
}
