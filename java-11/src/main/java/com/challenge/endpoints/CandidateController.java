package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.interfaces.CandidateServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("candidate")
@AllArgsConstructor
public class CandidateController {

    @Autowired
    private final CandidateServiceInterface candidateService;

    private final CandidateMapper candidateMapper = Mappers.getMapper(CandidateMapper.class);

    @GetMapping(value = "/{userId}/{companyId}/{accelerationId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CandidateDTO> getCandidateById(@PathVariable Long userId, @PathVariable Long companyId, @PathVariable Long accelerationId) {
        return candidateService.findById(userId, companyId, accelerationId)
                .map(candidate -> ResponseEntity.ok(candidateMapper.map(candidate)))
                .orElseGet(() -> ResponseEntity.ok(new CandidateDTO()));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCandidateByCompanyIdOrAccelerationId(@RequestParam(required = false) Long companyId,
                                                                     @RequestParam(required = false) Long accelerationId) {
        List<Candidate> candidateList = new ArrayList<>();
        if(Objects.nonNull(companyId)) {
            candidateList.addAll(candidateService.findByCompanyId(companyId));
        }
        if(Objects.nonNull(accelerationId)){
            candidateList.addAll(candidateService.findByAccelerationId(accelerationId));
        }
        if (!candidateList.isEmpty()) {
            return ResponseEntity.ok(candidateMapper.map(candidateList));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum candidato.");

    }

}
