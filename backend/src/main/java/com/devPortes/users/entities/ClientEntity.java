package com.devPortes.users.entities;

import com.devPortes.users.model.ClasificationEnum;
import com.devPortes.users.model.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clients")
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode()
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name="url_pictures")
    private String urlPicture;

    @Column(name = "identity_document", nullable = false, unique = true)
    private String identityDocument;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "reservation_amount",nullable = false)
    private int reservationAmount;

    @Enumerated(EnumType.STRING)
    private ClasificationEnum classification;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum role;

    @Column(nullable = false)
    private boolean state;

}
