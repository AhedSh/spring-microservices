package com.hellowspring.enrollment_service.Enrollment;

import java.util.List;

public interface EnrollmentService {

    EnrollmentDto createEnrollment(
            EnrollmentDto enrollmentDto,
            String username
    );

    EnrollmentDto getEnrollmentById(
            Long id,
            String username
    );

    List<EnrollmentDto> getAllEnrollments();

    void deleteEnrollment(
            Long id,
            String username
    );
}