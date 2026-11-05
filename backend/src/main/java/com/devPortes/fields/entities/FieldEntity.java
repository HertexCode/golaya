package com.devPortes.fields.entities;

import com.devPortes.fields.model.FieldStateEnum;
import com.devPortes.location.entities.LocationEntity;
import com.devPortes.location.model.Location;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "fields")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FieldEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity location;

    @Column(name = "url_pictures", nullable = false)
    private List<String> urlPictures;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String capacity;
    @Column(nullable = false)
    private String sport;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String surface;
    @Column(nullable = false)
    private List<String> details;
    @Column(name = "hourly_rate", nullable = false, precision = 10, scale = 2)
    private BigDecimal hourlyRate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FieldStateEnum state;

    @Column(nullable = false)
    private boolean visible = true;

}
