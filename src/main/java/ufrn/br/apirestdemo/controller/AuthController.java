package ufrn.br.apirestdemo.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ufrn.br.apirestdemo.config.LoginDTO;
import ufrn.br.apirestdemo.service.TokenService;

@RestController
public class AuthController {


    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/token")
    public String token(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password())
                );

        return tokenService.generateToken(authentication);
    }

    /*
    Basic Authentication
    @PostMapping("/token")
    public String token(Authentication authentication) {
        String token = tokenService.generateToken(authentication);
        return token;
    }
     */

}