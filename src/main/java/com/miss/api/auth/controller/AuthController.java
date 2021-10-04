package com.miss.api.auth.controller;

import com.miss.api.auth.service.AuthService;
import com.miss.api.auth.wrapper.AuthBody;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthBody authBody) {
        return new ResponseEntity<>(authService.login(authBody.getUsername(), authBody.getPassword()), HttpStatus.OK);
    }

    @PostMapping("/update_pwd")
    public ResponseEntity<?> updatePwd(@RequestBody AuthBody authBody) {
        return new ResponseEntity<>(authService.updatePassword(authBody), HttpStatus.OK);
    }

    @PostMapping("/reset_pwd")
    public ResponseEntity<?> resetPwd(@RequestBody AuthBody authBody) {
        return new ResponseEntity<>(authService.resetPassword(authBody.getUserId(), authBody.getNewPassword()), HttpStatus.OK);
    }

    @PostMapping("/forget_pwd")
    public ResponseEntity<?> forgetPwd(@RequestBody AuthBody authBody) {
        return new ResponseEntity<>(authService.forgetPassword(authBody), HttpStatus.OK);
    }

    @GetMapping("/who_is")
    public ResponseEntity<?> whois(HttpServletRequest request) {
        return new ResponseEntity<>(authService.whoIs(request), HttpStatus.OK);
    }

    @GetMapping("/{username}/refresh_token")
    public ResponseEntity<?> refreshToken(@PathVariable(value = "username") String username) {
        return new ResponseEntity<>(authService.refreskToken(username), HttpStatus.OK);
    }
}
