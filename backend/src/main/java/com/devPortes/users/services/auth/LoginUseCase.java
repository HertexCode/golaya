package com.devPortes.users.services.auth;

import com.devPortes.users.dto.auth.LoginDataRequestDto;
import com.devPortes.users.dto.auth.TokenDataDto;
import com.devPortes.users.exceptions.InvalidCredentialException;
import com.devPortes.users.model.IAuthenticated;
import com.devPortes.users.repository.UserJpaRepositoryAdapter;
import com.devPortes.users.security.BCryptPasswordEncoderAdapter;
import com.devPortes.users.security.CustomUserDetails;
import com.devPortes.users.security.TokenImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginUseCase implements ILoginUseCase {
    private final BCryptPasswordEncoderAdapter bCryptPasswordEncoder;
    private final UserJpaRepositoryAdapter userRepository;
    private final TokenImpl tokenService;

    @Override
    public TokenDataDto execute(LoginDataRequestDto dto) {

        IAuthenticated saveUser = userRepository.findByEmail(dto.email())
                .orElseThrow(InvalidCredentialException::new);

        boolean isMatch = bCryptPasswordEncoder.matchesPasswords(
                dto.password(), saveUser.getPasswordHash());

        if (!isMatch){
            throw new InvalidCredentialException();
        }
        String token = tokenService.generateNewToken(new CustomUserDetails(saveUser));

        return new TokenDataDto(
                token,
                saveUser.getName(),
                saveUser.getEmail(),
                saveUser.getIdentityDocument(),
                saveUser.getPhoneNumber()
        );
    }
}
