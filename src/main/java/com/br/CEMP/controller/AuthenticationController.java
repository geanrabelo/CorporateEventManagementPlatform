package com.br.CEMP.controller;

import com.br.CEMP.dto.msg.MessageDTO;
import com.br.CEMP.dto.user.AuthenticationDTO;
import com.br.CEMP.dto.user.RegisterDTO;
import com.br.CEMP.service.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/cemp/auth")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(path = "/login")
    @Transactional
    public ResponseEntity<MessageDTO> login(@RequestBody @Validated AuthenticationDTO authenticationDTO){
        return ResponseEntity.ok(new MessageDTO(userService.login(authenticationDTO)));
    }

    @PostMapping(path = "/register")
    @Transactional
    public ResponseEntity<MessageDTO> register(@RequestBody @Validated RegisterDTO registerDTO){
        return ResponseEntity.ok(new MessageDTO(userService.register(registerDTO)));
    }
}
