package com.hellowspring.enrollment_service.Enrollment;

import com.hellowspring.enrollment_service.Security.UserClient;
import com.hellowspring.enrollment_service.Security.UserDto;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserClient userClient;

    @Override
    public EnrollmentDto createEnrollment(
            EnrollmentDto dto,
            String username) {

        // الحصول على المستخدم الحقيقي من user-service
        UserDto user = userClient.getUserByUsername(username);

        Long userId = user.getId();

        // التأكد من عدم وجود enrollment مكرر
        if (enrollmentRepository.existsByUserIdAndCourseId(
                userId,
                dto.getCourseId())) {

            throw new RuntimeException(
                    "User is already enrolled in this course");
        }

        EnrollmentEntity enrollment =
                EnrollmentEntity.builder()
                        .userId(userId)
                        .courseId(dto.getCourseId())
                        .build();

        EnrollmentEntity savedEnrollment =
                enrollmentRepository.save(enrollment);

        return mapToDto(savedEnrollment);
    }

    @Override
    public EnrollmentDto getEnrollmentById(
            Long id,
            String username) {

        EnrollmentEntity enrollment =
                enrollmentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Enrollment not found"));

        return mapToDto(enrollment);
    }

    @Override
    public List<EnrollmentDto> getAllEnrollments() {

        return enrollmentRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public void deleteEnrollment(
            Long id,
            String username) {

        EnrollmentEntity enrollment =
                enrollmentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Enrollment not found"));

        enrollmentRepository.delete(enrollment);
    }

    private EnrollmentDto mapToDto(
            EnrollmentEntity enrollment) {

        return EnrollmentDto.builder()
                .id(enrollment.getId())
                .courseId(enrollment.getCourseId())
                .build();
    }
}

