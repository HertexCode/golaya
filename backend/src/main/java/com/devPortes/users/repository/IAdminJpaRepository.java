package com.devPortes.users.repository;

import com.devPortes.users.entities.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAdminJpaRepository extends JpaRepository<AdminEntity, Long> {
    Optional<AdminEntity> findByEmail (String email);
}
