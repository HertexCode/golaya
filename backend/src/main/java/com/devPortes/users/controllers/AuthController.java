package com.devPortes.users.controllers;

import com.devPortes.users.dto.auth.EditProfileRequestDto;
import com.devPortes.users.dto.auth.LoginDataRequestDto;
import com.devPortes.users.dto.auth.NewUserRequestDto;
import com.devPortes.users.dto.auth.NewUserResponseDto;
import com.devPortes.users.dto.auth.TokenDataDto;
import com.devPortes.users.mapper.UserInMapper;
import com.devPortes.users.security.CustomUserDetails;
import com.devPortes.users.services.auth.IEditProfileUseCase;
import com.devPortes.users.services.auth.ILoginUseCase;
import com.devPortes.users.services.auth.INewUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final INewUserUseCase iNewUserUseCase;
    private final ILoginUseCase iLoginUseCase;
    private final IEditProfileUseCase iEditProfileUseCase;


    @PostMapping("register")
    public ResponseEntity<NewUserResponseDto> register(@Valid @RequestBody NewUserRequestDto dto) {
        NewUserResponseDto newUserResult = iNewUserUseCase.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newUserResult);
    }

    @PostMapping("login")
    public ResponseEntity<TokenDataDto> login(@Valid @RequestBody LoginDataRequestDto dto){
        TokenDataDto tokenResponse = iLoginUseCase.execute(dto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(tokenResponse);
    }

    @GetMapping("profile")
    public ResponseEntity<NewUserResponseDto> myProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity.ok(UserInMapper.toDtoProfile(customUserDetails.getUser()));
    }

    @PutMapping("profile")
    public ResponseEntity<NewUserResponseDto> updateProfile(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Valid @RequestBody EditProfileRequestDto dto) {
        Long userId = customUserDetails.getUser().getId();
        NewUserResponseDto response = iEditProfileUseCase.execute(userId, dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "profile/picture", consumes = "multipart/form-data")
    public ResponseEntity<NewUserResponseDto> updateProfilePicture(
            @RequestPart("picture") MultipartFile picture,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long userId = customUserDetails.getUser().getId();
        return ResponseEntity.ok(iEditProfileUseCase.executePicture(userId, picture));
    }



}