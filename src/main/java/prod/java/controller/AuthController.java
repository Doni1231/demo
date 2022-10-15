package prod.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import prod.java.entity.User;
import prod.java.payload.ApiResponse;
import prod.java.payload.JwtToken;
import prod.java.payload.ReqLogin;
import prod.java.payload.UserDto;
import prod.java.security.JwtTokenProvider;
import prod.java.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @PostMapping("/registerUser")
    private HttpEntity<?> registerUser(@Valid @RequestBody UserDto request) {
        ApiResponse apiResponse = authService.registerUser(request);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/verifyEmail")
    private HttpEntity<?> verifyEmail(@RequestParam boolean check,
                                      @RequestParam String email,
                                      @RequestParam String code,
                                      @RequestParam boolean changing) {
        ApiResponse apiResponse = authService.verifyEmail(check, code, email, changing);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @PostMapping("/login")
    private HttpEntity<?> logIn(@Valid @RequestBody ReqLogin reqLogIn) {

        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(reqLogIn.getEmail(), reqLogIn.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(((User) authentication.getPrincipal()));

        return ResponseEntity.ok(new JwtToken(token));
    }

}
