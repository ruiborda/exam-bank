package com.example.clientpersonservice.controller;

import com.example.clientpersonservice.common.DefaultResponse;
import com.example.clientpersonservice.dtos.client.*;
import com.example.clientpersonservice.entity.Client;
import com.example.clientpersonservice.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<DefaultResponse<Iterable<Client>>> findAllClients() {
        return ResponseEntity.ok(clientService.findAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse<Client>> findClientById(@Valid @PathVariable UUID id) {
        return ResponseEntity.ok(clientService.findClientById(id));
    }

    @GetMapping("/client-id/{clientId}")
    public ResponseEntity<DefaultResponse<Client>> findClientByClientId(@Valid @PathVariable UUID clientId) {
        return ResponseEntity.ok(clientService.findClientByClientId(clientId));
    }

    @PostMapping
    public ResponseEntity<DefaultResponse<Client>> saveClient(@Valid @RequestBody SaveClientRequestDTO dto) {
        return ResponseEntity.ok(clientService.saveClient(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse<Client>> deleteClientById(@Valid @PathVariable UUID id) {
        return ResponseEntity.ok(clientService.deleteClient(id));
    }

    @PutMapping
    public ResponseEntity<DefaultResponse<UpdateClientResponseDTO>> updateClient(@Valid @RequestBody UpdateClientRequestDTO dto) {
        return ResponseEntity.ok(clientService.updateClient(dto));
    }

    @PatchMapping
    public ResponseEntity<DefaultResponse<PatchClientResponseDTO>> patchClient(@Valid @RequestBody PatchClientRequestDTO dto) {
        return ResponseEntity.ok(clientService.patchClient(dto));
    }
}
