package com.br.CEMP.service.impl;

import com.br.CEMP.dto.user.AuthenticationDTO;
import com.br.CEMP.dto.user.RegisterDTO;
import com.br.CEMP.exceptions.ex.UserAlreadyExistsByEmail;
import com.br.CEMP.exceptions.ex.UserAlreadyExistsByUsername;
import com.br.CEMP.model.User;
import com.br.CEMP.model.enums.EnumCode;
import com.br.CEMP.repository.UserRepository;
import com.br.CEMP.service.TokenService;
import com.br.CEMP.service.interfaces.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository,
                           TokenService tokenService,
                           AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login(AuthenticationDTO authenticationDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.username(),
                authenticationDTO.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        return tokenService.generateToken((User) auth.getPrincipal());
    }

    @Override
    public String register(RegisterDTO registerDTO) {

        verifyAlreadyExists(registerDTO);

        String passwordCrypt = new BCryptPasswordEncoder().encode(registerDTO.password());

        User user = new User(registerDTO.username(), registerDTO.email(), passwordCrypt, registerDTO.roles());

        userRepository.save(user);

        return "User registered successfully";
    }

    private void verifyAlreadyExists(RegisterDTO registerDTO){
        if(userRepository.existsByUsername(registerDTO.username())){
            throw new UserAlreadyExistsByUsername(EnumCode.USR000.getMessage());
        }
        if(userRepository.existsByEmail(registerDTO.email())){
            throw new UserAlreadyExistsByEmail(EnumCode.USR001.getMessage());
        }
    }
}
