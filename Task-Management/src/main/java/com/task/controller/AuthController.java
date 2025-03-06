package com.task.controller;

import com.task.dto.UserRequestDTO;
import com.task.entities.Users;
import com.task.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<String>  Register(@Valid @RequestBody UserRequestDTO userDto) {
        Users registeredUser = authService.Register(userDto);
        return ResponseEntity.ok("User registered successfully with email : " + registeredUser.getEmail() ) ;
    }

    @GetMapping("/auth/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        return ResponseEntity.ok(authentication);
    }

}
