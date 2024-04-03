package com.example.accountmovementservice.service;

import com.example.accountmovementservice.common.DefaultResponse;
import com.example.accountmovementservice.dtos.client_person_external_service.FindByClientIdResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientPersonExternalService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${services.client-person-service.url}")
    private String clientPersonServiceUrl;

    public FindByClientIdResponseDTO findClientByClientId(String id) {
       return restTemplate.getForObject(clientPersonServiceUrl + "/clientes/client-id/" + id, FindByClientIdResponseDTO.class);
    }


}
