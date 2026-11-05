package com.devPortes.users.entities;

import com.devPortes.users.model.RoleEnum;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admins")
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode()
public class AdminEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "identity_document", nullable = false, unique = true)
    private String identityDocument;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum role;

    @Column(nullable = false)
    private boolean state;

}