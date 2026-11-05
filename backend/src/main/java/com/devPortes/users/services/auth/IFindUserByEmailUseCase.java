package com.devPortes.users.services.auth;


import com.devPortes.users.model.Admin;
import com.devPortes.users.model.Client;
import com.devPortes.users.model.IAuthenticated;

import java.util.Optional;

public interface IFindUserByEmailUseCase {
    Optional<IAuthenticated> execute (String email);
    Optional<Client> findClientByEmail (String email);
    Optional<Admin>findAdminByEmail(String email);

}
