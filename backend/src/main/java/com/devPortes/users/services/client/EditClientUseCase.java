package com.devPortes.users.services.client;

import com.devPortes.client.ICloudinaryClient;
import com.devPortes.fields.dto.EditFieldRequestDto;
import com.devPortes.fields.exceptions.FieldNotFoundException;
import com.devPortes.fields.model.Field;
import com.devPortes.fields.repository.FieldJpaRepositoryAdapter;
import com.devPortes.users.dto.client.EditClientRequestDto;
import com.devPortes.users.dto.client.ListUsersResponseDto;
import com.devPortes.users.mapper.UserInMapper;
import com.devPortes.users.model.Client;
import com.devPortes.users.repository.UserJpaRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditClientUseCase implements IEditClientUseCase {
    private final UserJpaRepositoryAdapter clientRepository;
    private final ICloudinaryClient iCloudinaryClient;

    @Override
    public ListUsersResponseDto execute(Long id, EditClientRequestDto dto) {
        Client saveClient = clientRepository.finById(id)
                .orElseThrow(() -> new FieldNotFoundException(id));
        String urlPicture = iCloudinaryClient.saveImg("usuarios",dto.picture());
        Client editClient = UserInMapper.toEditModel(saveClient,dto, urlPicture);

        return UserInMapper.toUserDto(clientRepository.saveEdit(id,editClient));
    }
}
