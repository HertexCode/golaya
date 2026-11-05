package com.devPortes.users.services.client;

import com.devPortes.users.dto.client.EditClientRequestDto;
import com.devPortes.users.dto.client.ListUsersResponseDto;

public interface IEditClientUseCase {

    ListUsersResponseDto execute(Long id, EditClientRequestDto dto);

}
