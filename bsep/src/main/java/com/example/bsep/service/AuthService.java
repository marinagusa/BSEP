package com.example.bsep.service;

import com.example.bsep.dto.LoginDto;
import com.example.bsep.exception.BadEmailException;
import com.example.bsep.exception.IncorrectPasswordException;
import com.example.bsep.exception.InvalidPasswordException;
import com.example.bsep.model.InvalidJwt;
import com.example.bsep.model.Role;
import com.example.bsep.model.User;
import com.example.bsep.repository.InvalidJwtRepository;
import com.example.bsep.repository.RoleRepository;
import com.example.bsep.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.bsep.util.Constants.EMAIL_REGEX;
import static com.example.bsep.util.Constants.PASSWORD_REGEX;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private InvalidJwtRepository invalidJwtRepository;

    public User login(LoginDto loginDto){
        User u = userRepository.findByUsername(loginDto.getUsername());
        if(u != null && u.isEnabled()){
            if(!passwordEncoder.matches(loginDto.getPassword(), u.getPassword())){
                u.incUnsuccessfulLogins();
                if(u.getUnsuccessfulLogins() >= 3){
                    u.setEnabled(false);
                }
                userRepository.save(u);
                throw new IncorrectPasswordException("Incorrect password!");
            }
            else{
                u.setUnsuccessfulLogins(0);
                userRepository.save(u);

                return u;
            }
        }
        return null;
    }

    public User register(User user){
        if(!PASSWORD_REGEX.matcher(user.getPassword()).matches() || user.getPassword().length() <= 6){
            throw new InvalidPasswordException();
        }
        if(!EMAIL_REGEX.matcher(user.getEmail()).matches()){
            throw new BadEmailException();
        }
        List<Role> fromDb = new ArrayList<>();

        for (Role r: user.getRoles()) {
            Role dbRole = roleRepository.findByName(r.getName());
            fromDb.add(dbRole);
        }

        user.setRoles(fromDb);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setUnsuccessfulLogins(0);
        return userRepository.save(user);
    }

    public void logout(String token){
        invalidJwtRepository.save(new InvalidJwt(token));
    }

}
