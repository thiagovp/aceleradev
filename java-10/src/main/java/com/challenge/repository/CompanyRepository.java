package com.challenge.repository;

import com.challenge.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company,Long> {

    @Query("SELECT DISTINCT candidate.id.company FROM Candidate candidate WHERE candidate.id.acceleration.id=:accelerationId")
    List<Company> findByAccelerationId(Long accelerationId);

    @Query("SELECT candidate.id.company FROM Candidate candidate WHERE candidate.id.user.id=:userId")
    List<Company> findByUserId(Long userId);
}
