package com.challenge.repository;

import com.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge,Long> {

    @Query(value = "SELECT s.id.challenge FROM Submission s, Challenge c, Acceleration a  WHERE " +
            "s.id.challenge.id=c.id AND c.id = a.challenge.id AND s.id.user.id =:userId AND a.id =:accelerationId")
    List<Challenge> findByAccelerationIdAndUserId(@Param("accelerationId") Long accelerationId, @Param("userId") Long userId);
}
