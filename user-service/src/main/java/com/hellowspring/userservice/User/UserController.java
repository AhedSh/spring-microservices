package com.hellowspring.userservice.User;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(
            @Valid @RequestBody CreateUserDto userDto) {

        UserDto createdUser =
                userService.createUser(userDto);

        return new ResponseEntity<>(
                createdUser,
                HttpStatus.CREATED
        );
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> createUserByAdmin(
            @Valid @RequestBody CreateUserByAdminDto userDto) {

        UserDto createdUser =
                userService.createUserByAdmin(userDto);

        return new ResponseEntity<>(
                createdUser,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.getUserById(id)
        );
    }

    @GetMapping("/internal/{id}")
    public ResponseEntity<UserDto> getUserByIdInternal(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.getUserById(id)
        );
    }

    @GetMapping("/internal/username/{username}")
    public ResponseEntity<UserDto> getUserByUsernameInternal(
            @PathVariable String username) {

        return ResponseEntity.ok(
                userService.getUserByUsername(username)
        );
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {

        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserDto userDto) {

        return ResponseEntity.ok(
                userService.updateUser(id, userDto)
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

                @PutMapping("/{id}/promote-to-instructor")
                @PreAuthorize("hasRole('ADMIN')")
                public ResponseEntity<UserDto> promoteToInstructor(
                        @PathVariable Long id) {

                return ResponseEntity.ok(
                        userService.promoteToInstructor(id)
                );
                }
}
