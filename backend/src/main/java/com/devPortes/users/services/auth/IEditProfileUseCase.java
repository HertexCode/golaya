package com.devPortes.users.services.auth;

import com.devPortes.users.dto.auth.EditProfileRequestDto;
import com.devPortes.users.dto.auth.NewUserResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface IEditProfileUseCase {
    NewUserResponseDto execute(Long userId, EditProfileRequestDto dto);
    NewUserResponseDto executePicture(Long userId, MultipartFile picture);
}
