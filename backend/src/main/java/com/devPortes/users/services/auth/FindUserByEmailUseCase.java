package com.devPortes.users.services.auth;

import com.devPortes.users.model.Admin;
import com.devPortes.users.model.Client;
import com.devPortes.users.model.IAuthenticated;
import com.devPortes.users.repository.UserJpaRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindUserByEmailUseCase implements IFindUserByEmailUseCase {
    private final UserJpaRepositoryAdapter userRepository;

    @Override
    public Optional<IAuthenticated> execute (String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<Client> findClientByEmail(String email) {
        return userRepository.findClientByEmail(email);
    }

    @Override
    public Optional<Admin> findAdminByEmail(String email) {
        return userRepository.findAdminByEmail(email);
    }


}
