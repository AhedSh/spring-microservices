package com.hellowspring.userservice.User;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hellowspring.userservice.Exciptions.UserNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(CreateUserDto dto) {

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        UserEntity user = UserEntity.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.USER)
                .build();

        UserEntity savedUser = userRepository.save(user);

        return mapToDto(savedUser);
    }

    @Override
    public UserDto createUserByAdmin(
            CreateUserByAdminDto dto) {

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        UserEntity user = UserEntity.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .build();

        UserEntity savedUser = userRepository.save(user);

        return mapToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id
                        ));

        return mapToDto(user);
    }

    @Override
    public UserDto getUserByUsername(String username) {

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with username: " + username
                        ));

        return mapToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public UserDto updateUser(
            Long id,
            UserDto dto) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id
                        ));

        user.setUsername(dto.getUsername());

        user.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        user.setRole(dto.getRole());

        UserEntity updatedUser =
                userRepository.save(user);

        return mapToDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id
                        ));

        userRepository.delete(user);
    }

    private UserDto mapToDto(UserEntity user) {

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

            @Override
                public UserDto promoteToInstructor(Long id) {

                UserEntity user = userRepository.findById(id)
                        .orElseThrow(() ->
                                new UserNotFoundException(
                                        "User not found with id: " + id
                                ));

                user.setRole(Role.INSTRUCTOR);

                UserEntity updatedUser = userRepository.save(user);

                return mapToDto(updatedUser);
                }

}

