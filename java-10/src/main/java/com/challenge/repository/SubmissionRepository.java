package com.challenge.repository;

import com.challenge.entity.Submission;
import com.challenge.entity.SubmissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, SubmissionId> {


    @Query("SELECT max(submission.score) FROM Submission submission WHERE submission.id.challenge.id =:challengeId")
    Optional<BigDecimal> findHigherScoreByChallengeId(@Param("challengeId") Long challengeId);

    @Query("SELECT submission FROM Submission submission, Acceleration acceleration " +
            "WHERE submission.id.challenge.id = acceleration.challenge.id AND submission.id.challenge.id =:challengeId AND acceleration.id =:accelerationId")
    List<Submission> findByChallengeIdAndAccelerationId(Long challengeId, Long accelerationId);
}
