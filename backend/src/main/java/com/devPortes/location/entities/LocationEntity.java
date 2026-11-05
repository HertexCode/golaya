package com.devPortes.location.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "locations")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String headquarters;
    @Column
    private String address;
    @Column(name = "url_qr_address")
    private String urlQrAddress;
    @Column(name = "url_address")
    private String urlAddress;
    @Column
    private String description;
    @Column
    private boolean state;
    @Column(nullable = false)
    private boolean visible = true;

}