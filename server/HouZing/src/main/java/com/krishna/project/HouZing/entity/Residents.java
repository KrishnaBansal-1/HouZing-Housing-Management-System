package com.krishna.project.HouZing.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Residents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    private HUser user;

    @Column(nullable = false, unique = true)
    private Long houseNo;

    @Column(nullable = false, unique = true)
    private Long phoneNo;

    @Column(nullable = false)
    private Boolean isActive = false;
}
