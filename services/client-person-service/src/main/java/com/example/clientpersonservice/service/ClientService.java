package com.example.clientpersonservice.service;

import com.example.clientpersonservice.common.DefaultResponse;
import com.example.clientpersonservice.converter.ClientConverter;
import com.example.clientpersonservice.dtos.client.*;
import com.example.clientpersonservice.entity.Client;
import com.example.clientpersonservice.repository.ClientRepository;
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
public class ClientService {
    private final ClientRepository clientRepository;

    public DefaultResponse<Iterable<Client>> findAllClients() {
        try {
            Iterable<Client> clients = clientRepository.findAll();
            return DefaultResponse.<Iterable<Client>>builder()
                    .message("Clientes encontrados")
                    .success(true)
                    .status(200)
                    .code("OK")
                    .data(clients)
                    .build();
        } catch (Exception e) {
            log.error("Error al buscar los clientes", e);
            return DefaultResponse.<Iterable<Client>>builder()
                    .message("Error al buscar los clientes")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }

    public DefaultResponse<Client> findClientById(UUID id) {
        try {
            Client client = clientRepository.findById(id).orElseThrow();
            return DefaultResponse.<Client>builder()
                    .message("Cliente encontrado")
                    .success(true)
                    .status(200)
                    .code("OK")
                    .data(client)
                    .build();
        } catch (NoSuchElementException e) {
            return DefaultResponse.<Client>builder()
                    .message("Cliente no encontrado")
                    .status(404)
                    .code("NOT_FOUND")
                    .build();
        } catch (Exception e) {
            log.error("Error al buscar el cliente", e);
            return DefaultResponse.<Client>builder()
                    .message("Error al buscar el cliente")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }

    public DefaultResponse<Client> findClientByClientId(UUID clientId) {
        try {
            Client client = clientRepository.findByClientId(clientId).orElseThrow();
            return DefaultResponse.<Client>builder()
                    .message("Cliente encontrado")
                    .success(true)
                    .status(200)
                    .code("OK")
                    .data(client)
                    .build();
        } catch (NoSuchElementException e) {
            return DefaultResponse.<Client>builder()
                    .message("Cliente no encontrado")
                    .status(404)
                    .code("NOT_FOUND")
                    .build();
        } catch (Exception e) {
            log.error("Error al buscar el cliente", e);
            return DefaultResponse.<Client>builder()
                    .message("Error al buscar el cliente")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }

    public DefaultResponse<Client> saveClient(SaveClientRequestDTO dto) {
        try {
            Client client = ClientConverter.toEntity(dto);

            client.setId(Objects.isNull(dto.getId()) ? UUID.randomUUID() : dto.getId());
            client.setClientId(Objects.isNull(dto.getClientId()) ? UUID.randomUUID() : dto.getClientId());

            Client savedClient = clientRepository.save(client);
            return DefaultResponse.<Client>builder()
                    .message("Cliente guardado")
                    .success(true)
                    .status(200)
                    .code("OK")
                    .data(savedClient)
                    .build();
        } catch (Exception e) {
            log.error("Error al guardar el cliente", e);
            return DefaultResponse.<Client>builder()
                    .message("Error al guardar el cliente")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }

    public DefaultResponse<Client> deleteClient(UUID id) {
        try {
            Client clientFind = clientRepository.findById(id).orElseThrow();
            clientRepository.deleteById(id);
            return DefaultResponse.<Client>builder()
                    .message("Cliente eliminado")
                    .status(200)
                    .success(true)
                    .code("OK")
                    .data(clientFind)
                    .build();
        } catch (NoSuchElementException e) {
            return DefaultResponse.<Client>builder()
                    .message("Cliente no encontrado")
                    .status(404)
                    .code("NOT_FOUND")
                    .build();
        } catch (Exception e) {
            log.error("Error al eliminar el cliente", e);
            return DefaultResponse.<Client>builder()
                    .message("Error al eliminar el cliente")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }

    public DefaultResponse<UpdateClientResponseDTO> updateClient(UpdateClientRequestDTO dto) {
        try {
            Client client = ClientConverter.toEntity(dto);
            Client savedClient = clientRepository.save(client);
            return DefaultResponse.<UpdateClientResponseDTO>builder()
                    .message("Cliente actualizado")
                    .success(true)
                    .status(200)
                    .code("OK")
                    .data(ClientConverter.toUpdateClientResponseDTO(savedClient))
                    .build();
        } catch (NoSuchElementException e) {
            return DefaultResponse.<UpdateClientResponseDTO>builder()
                    .message("Cliente no encontrado")
                    .status(404)
                    .code("NOT_FOUND")
                    .build();
        } catch (Exception e) {
            log.error("Error al actualizar el cliente", e);
            return DefaultResponse.<UpdateClientResponseDTO>builder()
                    .message("Error al actualizar el cliente")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }

    public DefaultResponse<PatchClientResponseDTO> patchClient(PatchClientRequestDTO dto) {
        try {
            Client client = clientRepository.findById(dto.getId()).orElseThrow();
            Optional.ofNullable(dto.getName()).ifPresent(client::setName);
            Optional.ofNullable(dto.getGender())
                    .map((genderEnum)->genderEnum.name().charAt(0))
                    .ifPresent(client::setGender);
            Optional.ofNullable(dto.getAge()).ifPresent(client::setAge);
            Optional.ofNullable(dto.getIdentification()).ifPresent(client::setIdentification);
            Optional.ofNullable(dto.getAddress()).ifPresent(client::setAddress);
            Optional.ofNullable(dto.getPhone()).ifPresent(client::setPhone);
            Optional.ofNullable(dto.getPassword()).ifPresent(client::setPassword);
            Optional.ofNullable(dto.getStatus()).ifPresent(client::setStatus);
            Client savedClient = clientRepository.save(client);
            return DefaultResponse.<PatchClientResponseDTO>builder()
                    .message("Cliente actualizado")
                    .status(200)
                    .code("OK")
                    .data(ClientConverter.toPatchClientResponseDTO(savedClient))
                    .build();
        } catch (NoSuchElementException e) {
            return DefaultResponse.<PatchClientResponseDTO>builder()
                    .message("Cliente no encontrado")
                    .status(404)
                    .code("NOT_FOUND")
                    .build();
        } catch (Exception e) {
            log.error("Error al actualizar el cliente", e);
            return DefaultResponse.<PatchClientResponseDTO>builder()
                    .message("Error al actualizar el cliente")
                    .status(500)
                    .code("INTERNAL_SERVER_ERROR")
                    .build();
        }
    }
}
