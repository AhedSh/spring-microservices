package com.hellowspring.enrollment_service.Enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {

    boolean existsByUserIdAndCourseId(Long userId, Long courseId);

    Optional<EnrollmentEntity> findByUserIdAndCourseId(
            Long userId,
            Long courseId
    );
}