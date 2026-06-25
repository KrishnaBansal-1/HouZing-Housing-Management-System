package com.krishna.project.HouZing.entity;

import com.krishna.project.HouZing.entity.type.VisitorStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Visitor {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    private String purposeOfVisit;

    @Column(nullable = false)
    private Long phoneNo;

    @Column(nullable = false)
    private Long houseNo;

    @Column(nullable = false)
    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    @Enumerated(EnumType.STRING)
    private VisitorStatus status;
}
