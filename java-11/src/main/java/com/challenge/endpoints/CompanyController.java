package com.challenge.endpoints;

import com.challenge.entity.Company;
import com.challenge.service.interfaces.CompanyServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("company")
@AllArgsConstructor
public class CompanyController {
    @Autowired
    private final CompanyServiceInterface companyService;

    @Autowired
    private final ObjectMapper objectMapper;

    @GetMapping(value = "/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCompanyById(@PathVariable(value = "companyId") Long companyId) {
        return companyService.findById(companyId).<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não foi encontrado."));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCompanyListByAccelerationIdOrUserId(@RequestParam(required = false) Long accelerationId,
                                                                                @RequestParam(required = false) Long userId) {
        List<Company> companyList = new ArrayList<>();

        if (Objects.nonNull(userId)) {
            companyList.addAll(companyService.findByUserId(userId));
        }
        if (Objects.nonNull(accelerationId)) {
            companyList.addAll(companyService.findByAccelerationId(accelerationId));
        }
        if (!companyList.isEmpty()) {
            try {
                return ResponseEntity.ok(objectMapper.writeValueAsString(companyList));
            } catch (JsonProcessingException e) {
                return ResponseEntity.badRequest().body("Formato Inválido.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum candidato.");
    }


}
