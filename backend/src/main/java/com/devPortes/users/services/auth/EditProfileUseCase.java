package com.devPortes.users.services.auth;

import com.devPortes.users.dto.auth.EditProfileRequestDto;
import com.devPortes.users.dto.auth.NewUserResponseDto;
import com.devPortes.users.exceptions.ExistingUserDataBaseException;
import com.devPortes.users.exceptions.UserNotFoundException;
import com.devPortes.users.mapper.UserInMapper;
import com.devPortes.users.model.Client;
import com.devPortes.users.repository.UserJpaRepositoryAdapter;
import com.devPortes.users.security.CustomUserDetails;
import com.devPortes.users.security.TokenImpl;
import com.devPortes.client.ICloudinaryClient;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditProfileUseCase implements IEditProfileUseCase {

    private final UserJpaRepositoryAdapter clientRepository;
    private final TokenImpl tokenService;
    private final ICloudinaryClient cloudinaryClient;

    @Override
    public NewUserResponseDto execute(Long userId, EditProfileRequestDto dto) {
        Client existingClient = clientRepository.finById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.valueOf(userId)));

        if (clientRepository.existsByEmailForAnotherUser(dto.email(), userId)) {
            throw new ExistingUserDataBaseException(dto.email());
        }

        if (clientRepository.existsByIdentityDocumentForAnotherClient(dto.identityDocument(), userId)) {
            throw new ExistingUserDataBaseException(
                    "identityDocument",
                    "La cédula " + dto.identityDocument() + " ya está registrada."
            );
        }

        Client editClient = UserInMapper.toEditProfileModel(existingClient, dto);
        Client savedClient = clientRepository.saveEdit(userId, editClient);

        // Generar nuevo token con el email actualizado para que el cliente
        // no quede con un JWT stale (el subject del token es el email).
        String newToken = tokenService.generateNewToken(new CustomUserDetails(savedClient));

        return UserInMapper.toNewUserDto(savedClient, newToken);
    }

    @Override
    public NewUserResponseDto executePicture(Long userId, MultipartFile picture) {
        if (picture == null || picture.isEmpty()) {
            throw new IllegalArgumentException("Debes seleccionar una imagen");
        }

        Client existingClient = clientRepository.finById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.valueOf(userId)));
        String urlPicture = cloudinaryClient.saveImg("usuarios", picture);
        Client updatedClient = Client.edit(
                urlPicture,
                existingClient.getName(),
                existingClient.getIdentityDocument(),
                existingClient.getPhoneNumber(),
                existingClient.getEmail(),
                existingClient.getPasswordHash(),
                existingClient.getClassification(),
                existingClient.getReservationAmount(),
                existingClient.getRole(),
                existingClient.isState()
        );
        Client savedClient = clientRepository.saveEdit(userId, updatedClient);
        String newToken = tokenService.generateNewToken(new CustomUserDetails(savedClient));
        return UserInMapper.toNewUserDto(savedClient, newToken);
    }
}
