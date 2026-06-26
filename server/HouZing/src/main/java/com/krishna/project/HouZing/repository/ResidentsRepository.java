package com.krishna.project.HouZing.repository;

import com.krishna.project.HouZing.entity.Residents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ResidentsRepository extends JpaRepository<Residents, Long> {
     Residents findByHouseNo(Long houseNo);
}