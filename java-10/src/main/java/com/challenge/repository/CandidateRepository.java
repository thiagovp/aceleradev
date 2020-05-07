package com.challenge.repository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, CandidateId> {

    @Query("SELECT candidate From Candidate candidate WHERE candidate.id.user.id =:userId " +
            "and candidate.id.company.id =:companyId and candidate.id.acceleration.id =:accelerationId")
    Optional<Candidate> findByUserIdAndCompanyIdAndAccelerationId(@Param("userId") Long userId,
                                                                  @Param("companyId") Long companyId,
                                                                  @Param("accelerationId") Long accelerationId);

    List<Candidate> findById_Company_Id(Long companyId);

    List<Candidate> findById_Acceleration_Id(Long accelerationId);
}
