package com.challenge.endpoints;

import com.challenge.entity.Challenge;
import com.challenge.service.interfaces.ChallengeServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@RequestMapping("challenge")
@AllArgsConstructor
public class ChallengeController {


    @Autowired
    private final ChallengeServiceInterface challengeService;

    @Autowired
    private final ObjectMapper objectMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getChallengeListByAccelerationIdAndUserId(@RequestParam Long accelerationId, @RequestParam Long userId) {
        final List<Challenge> challengeList = challengeService.findByAccelerationIdAndUserId(accelerationId, userId);
        if (Objects.nonNull(challengeList) && !challengeList.isEmpty()) {
            try {
                return ResponseEntity.ok(objectMapper.writeValueAsString(challengeList));
            } catch (JsonProcessingException e) {
                return ResponseEntity.badRequest().body("Formato inválido.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum desafio.");
    }
}
