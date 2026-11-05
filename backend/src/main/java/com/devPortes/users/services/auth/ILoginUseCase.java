package com.devPortes.users.services.auth;

import com.devPortes.users.dto.auth.LoginDataRequestDto;
import com.devPortes.users.dto.auth.TokenDataDto;

public interface ILoginUseCase {
    TokenDataDto execute(LoginDataRequestDto dto);
}
