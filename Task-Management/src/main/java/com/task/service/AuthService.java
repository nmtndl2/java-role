package com.task.service;

import com.task.dto.UserRequestDTO;
import com.task.entities.Users;

public interface AuthService {
    public Users Register(UserRequestDTO usersDto);

//    public void RegisterAdmin(Users users);


}
