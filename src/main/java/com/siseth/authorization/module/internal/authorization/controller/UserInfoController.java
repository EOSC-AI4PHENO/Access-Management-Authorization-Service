package com.siseth.authorization.module.internal.authorization.controller;

import com.siseth.authorization.module.internal.authorization.service.UserInfoService;
import com.siseth.authorization.module.keycloak.api.KeyCloakUserInfoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/access/authorization")
@Tag(name = "Authorization Controller", description = "Endpoints to manage authorization")
public class UserInfoController {

    private final UserInfoService service;
    @GetMapping("/userInfo")
    @Operation(summary = "Get user information", description = "Endpoint to get user information")
    public ResponseEntity<KeyCloakUserInfoDTO> userInfo(@RequestParam String token, @RequestParam String realm) {
        return ResponseEntity.ok(service.userInfo(token, realm));
    }

}
