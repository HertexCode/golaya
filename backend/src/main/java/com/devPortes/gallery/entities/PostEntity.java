package com.devPortes.gallery.entities;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
// MODELO DE PERSISTENCIA → DEFINE LA COMO SE ALMACENAN LOS DATOS EN LA BD
@Entity
@Table(name="posts")
@RequiredArgsConstructor
@Getter
@Setter
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(name = "url_pictures", nullable = false)
    private List<String> urlPictures;
    @Column(nullable = false)
    private LocalDate eventDate;

}
