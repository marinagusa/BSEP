package com.example.bsep.dto;

import com.example.bsep.model.Role;
import com.example.bsep.model.User;

import java.util.List;

public class UserDto {
    private Long id;
    private String username;
    private String name;
    private String email;
    private boolean enabled;
    private List<Role> roles;
    private String jwt;

    private UserDto(){}

    public UserDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getFirstName() + " " + user.getLastName();
        this.email = user.getEmail();
        this.enabled = user.isEnabled();
        this.roles = user.getRoles();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
