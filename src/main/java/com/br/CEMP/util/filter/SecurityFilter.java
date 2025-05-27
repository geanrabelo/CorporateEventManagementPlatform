package com.br.CEMP.util.filter;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.br.CEMP.service.AuthenticationService;
import com.br.CEMP.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final AuthenticationService authenticationService;

    public SecurityFilter(TokenService tokenService, AuthenticationService authenticationService){
        this.tokenService = tokenService;
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        if(token != null){
            DecodedJWT decodedJWT = tokenService.validateToken(token);

            if(decodedJWT != null){
                String username = decodedJWT.getSubject();
                String roles = decodedJWT.getClaim("roles").asString();

                List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + roles));

                var authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Authorization");
        if(token == null) return null;
        return token.replace("Bearer ", "");
    }
}
