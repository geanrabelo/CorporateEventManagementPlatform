package com.br.CEMP.service.impl;

import com.br.CEMP.dto.AuthenticationDTO;
import com.br.CEMP.dto.RegisterDTO;
import com.br.CEMP.model.User;
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
        if(userRepository.existsByEmail(registerDTO.email()) || userRepository.existsByUsername(registerDTO.username())){
            //throw new Exception();
        }
        String passwordCrypt = new BCryptPasswordEncoder().encode(registerDTO.password());

        User user = new User(registerDTO.username(), registerDTO.email(), passwordCrypt, registerDTO.roles());

        userRepository.save(user);

        return "User registered successfully";
    }
}
