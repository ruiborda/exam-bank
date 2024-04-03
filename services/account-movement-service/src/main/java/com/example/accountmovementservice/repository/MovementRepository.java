package com.example.accountmovementservice.repository;

import com.example.accountmovementservice.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface MovementRepository extends JpaRepository<Movement, UUID> {
    @Query("SELECT m FROM Movement m WHERE m.date BETWEEN :startDate AND :endDate")
    Iterable<Movement> findMovementsByDateRange(LocalDate startDate, LocalDate endDate);
}
