package com.devPortes.users.services.client;

import com.devPortes.users.dto.client.ListUsersResponseDto;
import com.devPortes.users.mapper.UserInMapper;
import com.devPortes.users.mapper.UserOutMapper;
import com.devPortes.users.repository.UserJpaRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListClientsUseCase implements IListClientsUseCase {
    private final UserJpaRepositoryAdapter userRepository;

    @Override
    public List<ListUsersResponseDto> execute() {
        return userRepository.findAllClients().stream()
                .map(UserInMapper::toUserDto)
                .toList();
    }
}
