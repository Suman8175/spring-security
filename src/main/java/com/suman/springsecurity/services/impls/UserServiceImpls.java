package com.suman.springsecurity.services.impls;

import com.suman.springsecurity.dto.*;
import com.suman.springsecurity.entity.Address;
import com.suman.springsecurity.entity.User;
import com.suman.springsecurity.exception.ResourceConflictException;
import com.suman.springsecurity.exception.ResourceNotFoundException;
import com.suman.springsecurity.mapper.AddressMapper;
import com.suman.springsecurity.mapper.UserMapper;
import com.suman.springsecurity.repository.UserRepository;
import com.suman.springsecurity.services.inter.AddressService;
import com.suman.springsecurity.services.inter.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpls implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final AddressService addressService;


    @Transactional
    @Override
    public UserResponse createUser(UserCreate userCreate) {
        User user = userMapper.mapCreateUserToUserEntity(userCreate);

        boolean doesEmailAlreadyExists = userExistsByEmail(user.getUserEmail());
        if (doesEmailAlreadyExists){
            throw new ResourceConflictException();
        }

        Address address = addressService.createAddress(userCreate.address());
        user.setAddress(address);
        User savedUser = userRepository.save(user);

        return  userMapper.mapUserEntityToUserResponse(savedUser);
    }

    @Override
    @Transactional
    public List<UserResponse> listOfUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserResponse> collectedUser = allUsers.stream().map(user -> userMapper.mapUserEntityToUserResponse(user)).collect(Collectors.toList());
        return collectedUser;
    }

    @Override
    public UserResponse getUserById(int userId) {
        Optional<User> userById = userRepository.findById(userId);
        if (userById.isEmpty()){
            throw new ResourceNotFoundException("User with id:"+userId +" not found");
        }
        return  userMapper.mapUserEntityToUserResponse( userById.get());
    }

    @Override
    public UserResponse updateUser(UserUpdate userToEdit, int id) {
        User newUserDetails = userMapper.mapUpdateUserToUserEntity(userToEdit);
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()){
            throw new ResourceNotFoundException("User not found");
        }
        User updatedUser = userMapper.editUserDetails(byId.get(), newUserDetails);
        User newSavedUser = userRepository.save(updatedUser);
        return  userMapper.mapUserEntityToUserResponse(newSavedUser);
    }

    @Override
    public void deleteExistingUserByUserId(int userId) {
        if (!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public AuthResponse sendsResponseAfterScuessfulLogin(UserLogin login) {
        if (login ==null){
            throw new ResourceNotFoundException("User Missing");
        }
        System.out.println("First Step");
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.userEmail(), login.userPassword()));
        System.out.println(authenticate.getDetails());
        if (authenticate.isAuthenticated()){
            return new AuthResponse("qweererwwerq",12);
        }
        throw new ResourceNotFoundException("User not found");
    }

    public boolean userExistsByEmail(String userEmail){
        return userRepository.existsByUserEmail(userEmail);
    }
}
