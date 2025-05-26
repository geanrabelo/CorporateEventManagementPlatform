package com.br.CEMP.service.interfaces;

import com.br.CEMP.dto.user.AuthenticationDTO;
import com.br.CEMP.dto.user.RegisterDTO;

public interface UserService {

    String login(AuthenticationDTO authenticationDTO);

    String register(RegisterDTO registerDTOe);
}
