package com.br.CEMP.service.interfaces;

import com.br.CEMP.dto.AuthenticationDTO;
import com.br.CEMP.dto.RegisterDTO;

public interface UserService {

    String login(AuthenticationDTO authenticationDTO);

    String register(RegisterDTO registerDTOe);
}
