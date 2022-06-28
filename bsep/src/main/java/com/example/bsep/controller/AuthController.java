package com.example.bsep.controller;

import com.example.bsep.dto.LoginDto;
import com.example.bsep.dto.TokenDto;
import com.example.bsep.dto.UserDto;
import com.example.bsep.model.User;
import com.example.bsep.service.AuthService;
import com.example.bsep.util.TokenUtils;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    private TokenUtils tokenUtils;


    @PostMapping(value = "register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> register(@RequestBody User user){
        return new ResponseEntity<>(new UserDto(service.register(user)), HttpStatus.CREATED);
    }

    @PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto){
        User u = service.login(loginDto);
        if(u == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String fingerprint = tokenUtils.generateFingerprint();

        TokenDto dto = new TokenDto();
        dto.setAccessToken(tokenUtils.generateToken(u.getUsername(), fingerprint));
        dto.setExpiresIn(tokenUtils.getExpiredIn());

        String cookie = "Fingerprint=" + fingerprint + "; HttpOnly; Path=/";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", cookie);

        return ResponseEntity.ok().headers(headers).body(dto);
    }

    @GetMapping(value = "logout")
    public ResponseEntity<Boolean> logout(@RequestHeader (name="Authorization") String token){
        service.logout(token);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
