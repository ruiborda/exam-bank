package com.example.clientpersonservice.repository;

import com.example.clientpersonservice.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}