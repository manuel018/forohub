package com.forohub.service;

import com.forohub.dto.AuthResponse;
import com.forohub.dto.LoginDTO;
import com.forohub.dto.UsuarioDTO;
import com.forohub.model.Usuario;
import com.forohub.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

    public AuthResponse login(LoginDTO loginDTO) {
        log.error(passwordEncoder.encode(loginDTO.password()));
        String password = loginDTO.password();
        String username = loginDTO.email();
        Authentication authentication = authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);
        AuthResponse authResponse = new AuthResponse(username, "Credenciales validadas", accessToken, true);

        return authResponse;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //Se recupera el usuario con el metodo que se implementa de la interfaz
        Usuario usuario = usuarioService.getUsuarioByEmail(email);
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");

        return new User(usuario.getCorreo(), usuario.getPassword(), List.of(authority));
    }


    private Authentication authenticate(String username, String password) {

        UserDetails userDetail = this.loadUserByUsername(username);

        if (userDetail == null) {
            throw new BadCredentialsException("Invalid username or password.");
        }

        if (!passwordEncoder.matches(password, userDetail.getPassword())) {
            throw new BadCredentialsException("Invalid username or password.");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetail.getPassword(), userDetail.getAuthorities());
    }

    public AuthResponse register(UsuarioDTO usuarioDTO) {
        try {
            return new AuthResponse(usuarioService.add(usuarioDTO).getNombre(), "Creado", null, true)
        } catch (Error e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
