package com.devPortes.payments.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "metodos_pago")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class PaymentMethodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean state;

}
