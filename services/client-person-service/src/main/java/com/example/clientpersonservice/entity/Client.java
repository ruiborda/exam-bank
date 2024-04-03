package com.example.clientpersonservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "person_id")
public class Client extends Person {
    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Boolean status;

    @Builder
    public Client(UUID id, String name, Character gender, Short age, String identification, String address, String phone, String password, Boolean status,UUID clientId) {
        super(id, name, gender, age, identification, address, phone);
        this.clientId = clientId;
        this.password = password;
        this.status = status;
    }

}