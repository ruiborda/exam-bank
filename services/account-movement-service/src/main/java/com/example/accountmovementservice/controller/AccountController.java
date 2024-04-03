package com.example.accountmovementservice.controller;

import com.example.accountmovementservice.common.DefaultResponse;
import com.example.accountmovementservice.dtos.account.*;
import com.example.accountmovementservice.entity.Account;
import com.example.accountmovementservice.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<DefaultResponse<Iterable<Account>>> findAllAccounts() {
        return ResponseEntity.ok(accountService.findAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse<FindAccountByIdResponseDTO>> findAccountById(@Valid @PathVariable UUID id) {
        return ResponseEntity.ok(accountService.findAccountById(id));
    }

    @PostMapping
    public ResponseEntity<DefaultResponse<Account>> saveAccount(@Valid @RequestBody SaveAccountRequestDTO dto) {
        return ResponseEntity.ok(accountService.saveAccount(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse<Account>> deleteAccountById(@Valid @PathVariable UUID id) {
        return ResponseEntity.ok(accountService.deleteAccount(id));
    }

    @PutMapping
    public ResponseEntity<DefaultResponse<UpdateAccountResponseDTO>> updateAccount(@Valid @RequestBody UpdateAccountRequestDTO dto) {
        return ResponseEntity.ok(accountService.updateAccount(dto));
    }

    @PatchMapping
    public ResponseEntity<DefaultResponse<PatchAccountResponseDTO>> patchAccount(@Valid @RequestBody PatchAccountRequestDTO dto) {
        return ResponseEntity.ok(accountService.patchAccount(dto));
    }
}

