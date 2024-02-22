package com.github.polijun.wavup.security.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.polijun.wavup.exception.NonExistsException;
import com.github.polijun.wavup.security.config.JwtService;
import com.github.polijun.wavup.security.user.User;
import com.github.polijun.wavup.security.user.UserRepository;
import com.github.polijun.wavup.security.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder().firstName(request.firstName()).lastName(request.lastName())
                .email(request.email()).password(passwordEncoder.encode(request.password()))
                .build();
        userService.createUser(user);
        var token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new NonExistsException("User not found"));
        var token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
