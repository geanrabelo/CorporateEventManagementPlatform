package com.br.CEMP.service.impl;

import com.br.CEMP.dto.AuthenticationDTO;
import com.br.CEMP.dto.RegisterDTO;
import com.br.CEMP.repository.UserRepository;
import com.br.CEMP.service.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public String login(AuthenticationDTO authenticationDTO) {

        return "";
    }

    @Override
    public String register(RegisterDTO registerDTOe) {

        return "";
    }
}
