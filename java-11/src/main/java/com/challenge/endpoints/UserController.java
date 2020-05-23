package com.challenge.endpoints;

import com.challenge.entity.User;
import com.challenge.service.interfaces.UserServiceInterface;
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
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserServiceInterface userService;
    @Autowired
    private final ObjectMapper objectMapper;

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById(@PathVariable(value = "userId") Long userId) {
        return userService.findById(userId).<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não foi encontrado."));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserListByCompanyIdOrAccelerationName(@RequestParam(required = false) Long companyId,
                                                             @RequestParam(required = false) String accelerationName) {
        List<User> userList = new ArrayList<>();

        if (Objects.nonNull(companyId)) {
            userList.addAll(userService.findByCompanyId(companyId));
        }
        if (Objects.nonNull(accelerationName)) {
            userList.addAll(userService.findByAccelerationName(accelerationName));
        }
        if (!userList.isEmpty()) {
            try {
                return ResponseEntity.ok(objectMapper.writeValueAsString(userList));
            } catch (JsonProcessingException e) {
                return ResponseEntity.badRequest().body("Formato Inválido.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum candidato.");
    }
}
