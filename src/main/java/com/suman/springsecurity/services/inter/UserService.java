package com.suman.springsecurity.services.inter;

import com.suman.springsecurity.dto.*;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserCreate userCreate);
    List<UserResponse> listOfUsers();
    UserResponse getUserById(int userId);
    UserResponse updateUser(UserUpdate userToEdit , int id);
    void deleteExistingUserByUserId(int userId);
    AuthResponse sendsResponseAfterScuessfulLogin(UserLogin login);
}
