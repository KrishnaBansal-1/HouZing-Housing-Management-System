package com.krishna.project.HouZing.repository;

import com.krishna.project.HouZing.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    Visitor findByPhoneNo(Long phoneNo);

    List<Visitor> findByHouseNo(Long houseNo);
}