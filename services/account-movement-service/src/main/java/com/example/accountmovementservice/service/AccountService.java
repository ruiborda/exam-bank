package com.example.accountmovementservice.service;

import com.example.accountmovementservice.common.DefaultResponse;
import com.example.accountmovementservice.converter.AccountConverter;
import com.example.accountmovementservice.dtos.account.*;
import com.example.accountmovementservice.dtos.client_person_external_service.FindByClientIdResponseDTO;
import com.example.accountmovementservice.entity.Account;
import com.example.accountmovementservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Log4j2
public class AccountService {
    private final AccountRepository accountRepository;
    private final ClientPersonExternalService clientPersonExternalService;

    public DefaultResponse<Iterable<Account>> findAllAccounts() {
        try {
            Iterable<Account> accounts = accountRepository.findAll();
            return DefaultResponse.<Iterable<Account>>builder()
                    .message("Accounts found")
                    .success(true)
                    .status(200)
                    .code("OK")
                    .data(accounts)
                    .build();
        } catch (Exception e) {
            log.error("Error finding accounts", e);
            return DefaultResponse.<Iterable<Account>>builder()
                    .message("Error finding accounts")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }

    public DefaultResponse<FindAccountByIdResponseDTO> findAccountById(UUID id) {
        try {
            Account account = accountRepository.findById(id).orElseThrow();
            FindAccountByIdResponseDTO accountDto = AccountConverter.toFindAccountByIdResponseDTO(account);

            FindByClientIdResponseDTO client =
                    clientPersonExternalService.findClientByClientId(account.getClientId().toString());

            accountDto.setClientName(client.getData().getName());

            return DefaultResponse.<FindAccountByIdResponseDTO>builder()
                    .message("Account found")
                    .success(true)
                    .status(200)
                    .code("OK")
                    .data(accountDto)
                    .build();
        } catch (NoSuchElementException e) {
            return DefaultResponse.<FindAccountByIdResponseDTO>builder()
                    .message("Account not found")
                    .status(404)
                    .code("NOT_FOUND")
                    .build();
        } catch (Exception e) {
            log.error("Error finding account", e);
            return DefaultResponse.<FindAccountByIdResponseDTO>builder()
                    .message("Error finding account")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }

    public DefaultResponse<Account> saveAccount(SaveAccountRequestDTO dto) {
        try {
            Account account = AccountConverter.toEntity(dto);

            account.setId(Objects.isNull(dto.getId()) ? UUID.randomUUID() : dto.getId());

            Account savedAccount = accountRepository.save(account);
            return DefaultResponse.<Account>builder()
                    .message("Account saved")
                    .success(true)
                    .status(200)
                    .code("OK")
                    .data(savedAccount)
                    .build();
        } catch (Exception e) {
            log.error("Error saving account", e);
            return DefaultResponse.<Account>builder()
                    .message("Error saving account")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }

    public DefaultResponse<Account> deleteAccount(UUID id) {
        try {
            Account accountFind = accountRepository.findById(id).orElseThrow();
            accountRepository.deleteById(id);
            return DefaultResponse.<Account>builder()
                    .message("Account deleted")
                    .status(200)
                    .success(true)
                    .code("OK")
                    .data(accountFind)
                    .build();
        } catch (NoSuchElementException e) {
            return DefaultResponse.<Account>builder()
                    .message("Account not found")
                    .status(404)
                    .code("NOT_FOUND")
                    .build();
        } catch (Exception e) {
            log.error("Error deleting account", e);
            return DefaultResponse.<Account>builder()
                    .message("Error deleting account")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }

    public DefaultResponse<UpdateAccountResponseDTO> updateAccount(UpdateAccountRequestDTO dto) {
        try {
            Account account = AccountConverter.toEntity(dto);
            Account savedAccount = accountRepository.save(account);
            return DefaultResponse.<UpdateAccountResponseDTO>builder()
                    .message("Account updated")
                    .success(true)
                    .status(200)
                    .code("OK")
                    .data(AccountConverter.toUpdateAccountResponseDTO(savedAccount))
                    .build();
        } catch (NoSuchElementException e) {
            return DefaultResponse.<UpdateAccountResponseDTO>builder()
                    .message("Account not found")
                    .status(404)
                    .code("NOT_FOUND")
                    .build();
        } catch (Exception e) {
            log.error("Error updating account", e);
            return DefaultResponse.<UpdateAccountResponseDTO>builder()
                    .message("Error updating account")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }

    public DefaultResponse<PatchAccountResponseDTO> patchAccount(PatchAccountRequestDTO dto) {
        try {
            Account account = accountRepository.findById(dto.getId()).orElseThrow();
            Optional.ofNullable(dto.getAccountNumber()).ifPresent(account::setAccountNumber);
            Optional.ofNullable(dto.getAccountType()).ifPresent(account::setAccountType);
            Optional.ofNullable(dto.getInitialBalance()).ifPresent(account::setInitialBalance);
            Optional.ofNullable(dto.getStatus()).ifPresent(account::setStatus);
            Optional.ofNullable(dto.getClientId()).ifPresent(account::setClientId);
            Account savedAccount = accountRepository.save(account);
            return DefaultResponse.<PatchAccountResponseDTO>builder()
                    .message("Account updated")
                    .status(200)
                    .code("OK")
                    .data(AccountConverter.toPatchAccountResponseDTO(savedAccount))
                    .build();
        } catch (NoSuchElementException e) {
            return DefaultResponse.<PatchAccountResponseDTO>builder()
                    .message("Account not found")
                    .status(404)
                    .code("NOT_FOUND")
                    .build();
        } catch (Exception e) {
            log.error("Error updating account", e);
            return DefaultResponse.<PatchAccountResponseDTO>builder()
                    .message("Error updating account")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }
}
