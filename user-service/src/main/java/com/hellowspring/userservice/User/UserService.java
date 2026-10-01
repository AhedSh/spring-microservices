package com.hellowspring.userservice.User;

import java.util.List;

public interface UserService {

    UserDto createUser(CreateUserDto userDto);

    UserDto createUserByAdmin(CreateUserByAdminDto userDto);

    UserDto getUserById(Long id);

    UserDto getUserByUsername(String username);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);

    UserDto promoteToInstructor(Long id);
}

