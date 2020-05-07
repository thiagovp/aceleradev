package com.challenge.repository;

import com.challenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT candidate.id.user FROM Candidate candidate WHERE candidate.id.acceleration.name=:name")
    List<User> findByAccelerationName(@Param("name") String name);

    @Query("SELECT candidate.id.user FROM Candidate candidate WHERE candidate.id.company.id=:companyId")
    List<User> findByCompanyId(@Param("companyId") Long companyId);
}
