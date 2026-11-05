package com.devPortes.users.controllers;

import com.devPortes.users.dto.client.EditClientRequestDto;
import com.devPortes.users.dto.client.ListUsersResponseDto;
import com.devPortes.users.services.client.IEditClientUseCase;
import com.devPortes.users.services.client.IListClientsUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/client")
public class ClientController {
    private final IListClientsUseCase iListClientsUseCase;
    private final IEditClientUseCase iEditClientUseCase;

    @PutMapping(value = "edit/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ListUsersResponseDto> editClient(@Valid
                                                               @PathVariable Long id,
                                                               @ModelAttribute EditClientRequestDto dto) {
        ListUsersResponseDto response = iEditClientUseCase.execute(id,dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("clients")
    public ResponseEntity<List<ListUsersResponseDto>> getUsers() {
        return ResponseEntity.ok(iListClientsUseCase.execute());
    }
}
