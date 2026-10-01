package com.hellowspring.enrollment_service.Enrollment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentDto> createEnrollment(
            @Valid @RequestBody EnrollmentDto enrollmentDto,
            Authentication authentication) {

        EnrollmentDto createdEnrollment =
                enrollmentService.createEnrollment(
                        enrollmentDto,
                        authentication.getName()
                );

        return new ResponseEntity<>(
                createdEnrollment,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDto> getEnrollmentById(
            @PathVariable Long id,
            Authentication authentication) {

        return ResponseEntity.ok(
                enrollmentService.getEnrollmentById(
                        id,
                        authentication.getName()
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentDto>> getAllEnrollments() {

        return ResponseEntity.ok(
                enrollmentService.getAllEnrollments()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(
            @PathVariable Long id,
            Authentication authentication) {

        enrollmentService.deleteEnrollment(
                id,
                authentication.getName()
        );

        return ResponseEntity.noContent().build();
    }
}

