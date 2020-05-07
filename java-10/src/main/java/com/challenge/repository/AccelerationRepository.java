package com.challenge.repository;

import com.challenge.entity.Acceleration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccelerationRepository extends JpaRepository<Acceleration, Long> {

    @Query("SELECT acceleration FROM Candidate c join c.id.acceleration acceleration join c.id.company company WHERE company.id =:companyId")
    List<Acceleration> findByCompanyId(@Param("companyId") Long companyId);
}
