package com.practica.controllers;

import com.practica.dto.request.LoginRequest;
import com.practica.dto.request.RegisterRequest;
import com.practica.dto.response.JWTResponse;
import com.practica.dto.response.MessageResponse;
import com.practica.models.ERole;
import com.practica.models.Rol;
import com.practica.models.User;
import com.practica.repositories.RolRepository;
import com.practica.repositories.UserRepository;
import com.practica.security.jwt.JwtUtils;
import com.practica.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("login")
    public ResponseEntity<JWTResponse> authenticateUser(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserService userDetails = (UserService) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(authentication);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JWTResponse(
                jwt,
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles)
        );
    }

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        if(Boolean.TRUE.equals(userRepository.existsByEmail(registerRequest.getEmail()))){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("El email ya está en uso"));
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        Rol roles = rolRepository.findByName(ERole.ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado"));;
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Usuario registrado correctamente"));
    }
}
