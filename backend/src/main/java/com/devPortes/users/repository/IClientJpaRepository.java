package com.devPortes.users.repository;

import com.devPortes.users.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClientJpaRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByEmail (String email);

    Optional<ClientEntity> findByIdentityDocument(String identityDocument);

}
