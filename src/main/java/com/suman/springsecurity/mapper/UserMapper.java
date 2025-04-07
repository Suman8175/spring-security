package com.suman.springsecurity.mapper;

import com.suman.springsecurity.dto.AddressResponseDTO;
import com.suman.springsecurity.dto.UserCreate;
import com.suman.springsecurity.dto.UserResponse;
import com.suman.springsecurity.dto.UserUpdate;
import com.suman.springsecurity.entity.User;
import com.suman.springsecurity.utils.DateTimeCustomFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final DateTimeCustomFormatter dateTimeFormatter;
    private final PasswordEncoder passwordEncoder;

    public User mapCreateUserToUserEntity(UserCreate userCreate){
        User user =new User();
        user.setUserName(userCreate.userName());
        user.setUserEmail(userCreate.userEmail());
        user.setUserPhoneNumber(userCreate.userPhoneNumber());
        user.setUserDOB(dateTimeFormatter.formatDateToYearMonthDayFormat(userCreate.userDOB()));
        user.setUserPassword(passwordEncoder.encode(userCreate.userPassword()));
        return user;
    }

    public User mapUpdateUserToUserEntity(UserUpdate userUpdate){
        User userUpdatedData =new User();
        userUpdatedData.setUserName(userUpdate.userName());
        userUpdatedData.setUserDOB(dateTimeFormatter.formatDateToYearMonthDayFormat(userUpdate.userDOB()));
        userUpdatedData.setUserPhoneNumber(userUpdate.userPhoneNumber());
        return userUpdatedData;
    }

    public UserResponse mapUserEntityToUserResponse(User user){
        if (user.getAddress().equals(null)){
            return null;
        }
        AddressResponseDTO addressResponseDTO =new AddressResponseDTO(user.getAddress().getAddressId(),user.getAddress().getCountry(),user.getAddress().getCity(),user.getAddress().getHouseNumber());
        UserResponse userResponse =new UserResponse(user.getUserId(), user.getUserName(), user.getUserPhoneNumber(), user.getUserEmail(), dateTimeFormatter.formatDateToString(user.getUserDOB()),addressResponseDTO);
        return userResponse;
    }

    public User editUserDetails(User originalUserData,User newUserData){
        originalUserData.setUserName(newUserData.getUserName());
        originalUserData.setUserDOB(newUserData.getUserDOB());
        originalUserData.setUserPhoneNumber(newUserData.getUserPhoneNumber());
        return originalUserData;
    }

}
