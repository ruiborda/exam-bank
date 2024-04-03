package com.example.accountmovementservice.service;

import com.example.accountmovementservice.common.DefaultResponse;
import com.example.accountmovementservice.converter.AccountConverter;
import com.example.accountmovementservice.converter.MovementConverter;
import com.example.accountmovementservice.dtos.account.FindAccountByIdResponseDTO;
import com.example.accountmovementservice.dtos.client_person_external_service.FindByClientIdResponseDTO;
import com.example.accountmovementservice.dtos.movement.*;
import com.example.accountmovementservice.entity.Account;
import com.example.accountmovementservice.entity.Movement;
import com.example.accountmovementservice.repository.AccountRepository;
import com.example.accountmovementservice.repository.MovementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
@Log4j2
public class MovementService {
    private final MovementRepository movementRepository;
    private final ClientPersonExternalService clientPersonExternalService;
    private final AccountRepository accountRepository;

    public DefaultResponse<Iterable<Movement>> findAllMovements() {
        try {
            Iterable<Movement> movements = movementRepository.findAll();
            return DefaultResponse.<Iterable<Movement>>builder()
                    .message("Movimientos encontrados")
                    .success(true)
                    .status(200)
                    .code("OK")
                    .data(movements)
                    .build();
        } catch (Exception e) {
            log.error("Error al buscar los movimientos", e);
            return DefaultResponse.<Iterable<Movement>>builder()
                    .message("Error al buscar los movimientos")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }

    public DefaultResponse<Iterable<FindMovementsByDateRangeResponseDTO>> findMovementsByDateRange(LocalDate startDate, LocalDate endDate) {
        try {
            Iterable<Movement> movements = movementRepository.findMovementsByDateRange(startDate, endDate);
            List<FindMovementsByDateRangeResponseDTO> response = StreamSupport.stream(movements.spliterator(), false)
                    .map(movement -> {
                        // TODO: optimizar con peticiones masivas
                        FindMovementsByDateRangeResponseDTO dto = MovementConverter.toFindMovementsByDateRangeResponseDTO(movement);
                        Account account = accountRepository.findById(movement.getAccountId()).orElseThrow();
                        FindByClientIdResponseDTO client = clientPersonExternalService.findClientByClientId(account.getClientId().toString());
                        FindAccountByIdResponseDTO accountDTO = AccountConverter.toFindAccountByIdResponseDTO(account);
                        dto.setAccountNumber(accountDTO.getAccountNumber());
                        dto.setClientName(client.getData().getName());
                        return dto;
                    })
                    .collect(Collectors.toList());

            return DefaultResponse.<Iterable<FindMovementsByDateRangeResponseDTO>>builder()
                    .message("Movimientos encontrados")
                    .success(true)
                    .status(200)
                    .code("OK")
                    .data(response)
                    .build();
        } catch (Exception e) {
            log.error("Error al buscar los movimientos", e);
            return DefaultResponse.<Iterable<FindMovementsByDateRangeResponseDTO>>builder()
                    .message("Error al buscar los movimientos")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }

    public DefaultResponse<Movement> findMovementById(UUID id) {
        try {
            Movement movement = movementRepository.findById(id).orElseThrow();
            return DefaultResponse.<Movement>builder()
                    .message("Movimiento encontrado")
                    .success(true)
                    .status(200)
                    .code("OK")
                    .data(movement)
                    .build();
        } catch (NoSuchElementException e) {
            return DefaultResponse.<Movement>builder()
                    .message("Movimiento no encontrado")
                    .status(404)
                    .code("NOT_FOUND")
                    .build();
        } catch (Exception e) {
            log.error("Error al buscar el movimiento", e);
            return DefaultResponse.<Movement>builder()
                    .message("Error al buscar el movimiento")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }

    public DefaultResponse<SaveMovementResponseDTO> saveMovement(SaveMovementRequestDTO dto) {
        try {
            Movement movement = MovementConverter.toEntity(dto);

            Account account = accountRepository.findById(dto.getAccountId()).orElseThrow();

            movement.setId(Objects.isNull(dto.getId()) ? UUID.randomUUID() : dto.getId());

            if (Objects.isNull(dto.getDate())) {
                Date date = new Date(System.currentTimeMillis());
                movement.setDate(date);
            }


            movement.setInitialBalance(account.getInitialBalance());

            double newBalance = movement.getInitialBalance() + movement.getValue();

            if (newBalance < 0) {
                log.error("Saldo no disponible para la cuenta {}", dto.getAccountId());
                return DefaultResponse.<SaveMovementResponseDTO>builder()
                        .message("Saldo no disponible")
                        .status(400)
                        .code("BAD_REQUEST")
                        .build();
            }

            movement.setBalance(newBalance);
            account.setInitialBalance(newBalance);

            accountRepository.save(account);
            Movement savedMovement = movementRepository.save(movement);

            SaveMovementResponseDTO saveMovementResponseDTO = MovementConverter.toSaveMovementResponseDTO(savedMovement);
            return DefaultResponse.<SaveMovementResponseDTO>builder()
                    .message("Movimiento guardado")
                    .success(true)
                    .status(200)
                    .code("OK")
                    .data(saveMovementResponseDTO)
                    .build();
        } catch (Exception e) {
            log.error("Error al guardar el movimiento", e);
            return DefaultResponse.<SaveMovementResponseDTO>builder()
                    .message("Error al guardar el movimiento")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }

    public DefaultResponse<Movement> deleteMovement(UUID id) {
        try {
            Movement movementFind = movementRepository.findById(id).orElseThrow();
            movementRepository.deleteById(id);
            return DefaultResponse.<Movement>builder()
                    .message("Movimiento eliminado")
                    .status(200)
                    .success(true)
                    .code("OK")
                    .data(movementFind)
                    .build();
        } catch (NoSuchElementException e) {
            return DefaultResponse.<Movement>builder()
                    .message("Movimiento no encontrado")
                    .status(404)
                    .code("NOT_FOUND")
                    .build();
        } catch (Exception e) {
            log.error("Error al eliminar el movimiento", e);
            return DefaultResponse.<Movement>builder()
                    .message("Error al eliminar el movimiento")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }

    public DefaultResponse<UpdateMovementResponseDTO> updateMovement(UpdateMovementRequestDTO dto) {
        try {
            Movement movement = MovementConverter.toEntity(dto);
            Movement savedMovement = movementRepository.save(movement);
            return DefaultResponse.<UpdateMovementResponseDTO>builder()
                    .message("Movimiento actualizado")
                    .success(true)
                    .status(200)
                    .code("OK")
                    .data(MovementConverter.toUpdateMovementResponseDTO(savedMovement))
                    .build();
        } catch (NoSuchElementException e) {
            return DefaultResponse.<UpdateMovementResponseDTO>builder()
                    .message("Movimiento no encontrado")
                    .status(404)
                    .code("NOT_FOUND")
                    .build();
        } catch (Exception e) {
            log.error("Error al actualizar el movimiento", e);
            return DefaultResponse.<UpdateMovementResponseDTO>builder()
                    .message("Error al actualizar el movimiento")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }

    public DefaultResponse<PatchMovementResponseDTO> patchMovement(PatchMovementRequestDTO dto) {
        try {
            Movement movement = movementRepository.findById(dto.getId()).orElseThrow();
            Optional.ofNullable(dto.getDate()).ifPresent(movement::setDate);
            Optional.ofNullable(dto.getMovementType()).ifPresent(movement::setMovementType);
            Optional.ofNullable(dto.getValue()).ifPresent(movement::setValue);
            Optional.ofNullable(dto.getBalance()).ifPresent(movement::setBalance);
            Optional.ofNullable(dto.getAccountId()).ifPresent(movement::setAccountId);
            Movement savedMovement = movementRepository.save(movement);
            return DefaultResponse.<PatchMovementResponseDTO>builder()
                    .message("Movimiento actualizado")
                    .status(200)
                    .code("OK")
                    .data(MovementConverter.toPatchMovementResponseDTO(savedMovement))
                    .build();
        } catch (NoSuchElementException e) {
            return DefaultResponse.<PatchMovementResponseDTO>builder()
                    .message("Movimiento no encontrado")
                    .status(404)
                    .code("NOT_FOUND")
                    .build();
        } catch (Exception e) {
            log.error("Error al actualizar el movimiento", e);
            return DefaultResponse.<PatchMovementResponseDTO>builder()
                    .message("Error al actualizar el movimiento")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }
}
