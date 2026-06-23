package com.krishna.project.HouZing.repository;

import com.krishna.project.HouZing.entity.HUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface HUserRepository extends JpaRepository<HUser, Long> {
    Optional<HUser> findByUsername(String username);
}