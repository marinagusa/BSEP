package com.example.bsep.controller;

import com.example.bsep.dto.SearchFilterDto;
import com.example.bsep.dto.UserDto;
import com.example.bsep.model.User;
import com.example.bsep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDto> add(@RequestBody User user){
        return new ResponseEntity<>(new UserDto(service.save(user)), HttpStatus.OK);
    }

    @GetMapping(value = "/change-role/{userId}/{roleId}")
    @PreAuthorize("hasAuthority('CHANGE_ROLE')")
    public ResponseEntity<UserDto> changeRole(@PathVariable Long userId, @PathVariable Long roleId){
        return new ResponseEntity<>(new UserDto(service.changeRole(userId, roleId)), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAuthority('DELETE_USER')")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        try{
            service.delete(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/search-filter", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<User>> searchAndFilter(@RequestBody SearchFilterDto dto){
        return new ResponseEntity<>(service.searchAndFilter(dto), HttpStatus.OK);
    }

    @GetMapping(value="/")
    public ResponseEntity<List<User>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){
        return new ResponseEntity<>(new UserDto(service.findById(id)), HttpStatus.OK);
    }

    @GetMapping(value="enable/{id}")
    @PreAuthorize("hasAuthority('DELETE_USER')")
    public ResponseEntity<UserDto> enableUser(@PathVariable Long id){
        return new ResponseEntity<>(service.enableUser(id), HttpStatus.OK);
    }

}
