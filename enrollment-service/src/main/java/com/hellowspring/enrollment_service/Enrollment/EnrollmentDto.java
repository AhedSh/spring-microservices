package com.hellowspring.enrollment_service.Enrollment;

import jakarta.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentDto {

    private Long id;

    @NotNull(message = "Course ID is required")
    private Long courseId;
}

