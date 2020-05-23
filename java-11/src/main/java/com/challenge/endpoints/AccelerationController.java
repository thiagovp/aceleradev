package com.challenge.endpoints;

import com.challenge.entity.Acceleration;
import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.service.interfaces.AccelerationServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("acceleration")
@AllArgsConstructor
public class AccelerationController {

    @Autowired
    private final AccelerationServiceInterface accelerationService;

    @Autowired
    private final ObjectMapper objectMapper;

    @GetMapping(path = "/{accelerationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Acceleration> getAccelerationById(@PathVariable(value = "accelerationId") Long accelerationId) {
        return ResponseEntity.ok(accelerationService.findById(accelerationId).orElseThrow(ResourceNotFoundException::new));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Acceleration>> getAccelerationListByCompanyId(@RequestParam Long companyId) {
        final List<Acceleration> accelerationList = accelerationService.findByCompanyId(companyId);
        if (Objects.nonNull(accelerationList)) {
            return ResponseEntity.ok(accelerationList);
        }
        return ResponseEntity.badRequest().build();
    }
}
