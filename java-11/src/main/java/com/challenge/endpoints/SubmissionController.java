package com.challenge.endpoints;

import com.challenge.entity.Submission;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.interfaces.SubmissionServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("submission")
@AllArgsConstructor
public class SubmissionController {

    @Autowired
    private final SubmissionServiceInterface submissionService;

    @Autowired
    private final SubmissionMapper submissionMapper;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSubmissionListByChallengeIdAndAccelerationId(@RequestParam Long challengeId, @RequestParam Long accelerationId) {
        final List<Submission> submissionList = submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId);
        if (Objects.nonNull(submissionList) && !submissionList.isEmpty()) {
            return ResponseEntity.ok(submissionMapper.map(submissionList));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma submissão.");
    }
}
