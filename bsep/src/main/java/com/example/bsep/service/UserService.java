package com.example.bsep.service;

import com.example.bsep.dto.SearchFilterDto;
import com.example.bsep.dto.UserDto;
import com.example.bsep.exception.InvalidOperationException;
import com.example.bsep.model.Role;
import com.example.bsep.model.User;
import com.example.bsep.repository.RoleRepository;
import com.example.bsep.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> searchAndFilter(SearchFilterDto dto){
        List<User> all = userRepository.findAll();
        if(dto.getSearch() != null && !dto.getSearch().equals("")){
            all = all.stream().filter(u ->
                u.getUsername().equals(dto.getSearch()) ||
                u.getFirstName().equals(dto.getSearch()) ||
                u.getLastName().equals(dto.getSearch()) ||
                u.getEmail().equals(dto.getSearch())
            ).collect(Collectors.toList());
        }

        if(dto.getRoleFilter() != null){
            System.out.println(dto.getRoleFilter());
            User first = all.get(0);
            System.out.println(first.getRoles().get(0));
            System.out.println(first.getRoles().get(0).equals(dto.getRoleFilter()));
            all = all.stream().filter(u ->
                u.getRoles().contains(dto.getRoleFilter())
            ).collect(Collectors.toList());
        }

        all = all.stream().filter( u -> u.isEnabled() == dto.isEnabledFilter()).collect(Collectors.toList());

        return all;
    }

    public User save(User u){
        return userRepository.save(u);
    }

    public void delete(Long userId){
        userRepository.deleteById(userId);
    }

    public User changeRole(Long userId, Long roleId){
        //CHANGE
        List<Role> newRoles = new ArrayList<>();
        newRoles.add(roleRepository.findById(roleId).orElseThrow());
        User u = userRepository.findById(userId).orElseThrow();
        u.setRoles(newRoles);
        return userRepository.save(u);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    public UserDto enableUser(Long id){
        User u = userRepository.findById(id).orElseThrow();
        if(u.isEnabled()){
            throw new InvalidOperationException();
        }
        u.setEnabled(true);
        u.setUnsuccessfulLogins(0);
        userRepository.save(u);
        return new UserDto(u);
    }
}
