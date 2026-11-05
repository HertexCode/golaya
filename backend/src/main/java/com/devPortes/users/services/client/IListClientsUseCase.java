package com.devPortes.users.services.client;

import com.devPortes.users.dto.client.ListUsersResponseDto;

import java.util.List;

public interface IListClientsUseCase {
    List<ListUsersResponseDto> execute();
}
