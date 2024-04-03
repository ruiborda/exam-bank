package com.example.accountmovementservice.controller;

import com.example.accountmovementservice.common.DefaultResponse;
import com.example.accountmovementservice.dtos.movement.*;
import com.example.accountmovementservice.entity.Movement;
import com.example.accountmovementservice.service.MovementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/movements")
@RequiredArgsConstructor
public class MovementController {
    private final MovementService movementService;

    @GetMapping
    public ResponseEntity<DefaultResponse<Iterable<Movement>>> findAllMovements() {
        return ResponseEntity.ok(movementService.findAllMovements());
    }

    @GetMapping("/reportes")
    public ResponseEntity<DefaultResponse<Iterable<FindMovementsByDateRangeResponseDTO>>> findMovementsByDateRange(@Valid @RequestParam LocalDate startDate, @Valid @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(movementService.findMovementsByDateRange(startDate, endDate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse<Movement>> findMovementById(@Valid @PathVariable UUID id) {
        return ResponseEntity.ok(movementService.findMovementById(id));
    }

    @PostMapping
    public ResponseEntity<DefaultResponse<SaveMovementResponseDTO>> saveMovement(@Valid @RequestBody SaveMovementRequestDTO dto) {
        return ResponseEntity.ok(movementService.saveMovement(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse<Movement>> deleteMovementById(@Valid @PathVariable UUID id) {
        return ResponseEntity.ok(movementService.deleteMovement(id));
    }

    @PutMapping
    public ResponseEntity<DefaultResponse<UpdateMovementResponseDTO>> updateMovement(@Valid @RequestBody UpdateMovementRequestDTO dto) {
        return ResponseEntity.ok(movementService.updateMovement(dto));
    }

    @PatchMapping
    public ResponseEntity<DefaultResponse<PatchMovementResponseDTO>> patchMovement(@Valid @RequestBody PatchMovementRequestDTO dto) {
        return ResponseEntity.ok(movementService.patchMovement(dto));
    }
}
