package com.devPortes.users.services.auth;

import com.devPortes.users.dto.auth.NewUserRequestDto;
import com.devPortes.users.dto.auth.NewUserResponseDto;

public interface INewUserUseCase{
    NewUserResponseDto execute(NewUserRequestDto dto);
}
