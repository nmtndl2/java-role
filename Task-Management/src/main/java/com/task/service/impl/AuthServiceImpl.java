package com.task.service.impl;

import com.task.dto.UserRequestDTO;
import com.task.entities.Role;
import com.task.entities.Users;
import com.task.repository.RoleRepository;
import com.task.repository.UsersRepository;
import com.task.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Users Register(UserRequestDTO usersDto) {
        if (usersRepository.findByEmail(usersDto.getEmail()).isPresent()){
            try {
                throw new Exception("User already exists!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        Users users = new Users();
        users.setEmail(usersDto.getEmail());
        users.setPassword(passwordEncoder.encode(usersDto.getPassword()));
//        users.setPassword(usersDto.getPassword());

        Set<Role> roles = usersDto.getRoles().stream()
                .map(roleName -> roleRepository.findByRole("ROLE_" + roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        users.setRoles(roles);

        return usersRepository.save(users);
    }

}
