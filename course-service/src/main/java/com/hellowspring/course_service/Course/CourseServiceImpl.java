package com.hellowspring.course_service.Course;

import com.hellowspring.course_service.Security.UserClient;
import com.hellowspring.course_service.Security.UserDto;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserClient userClient;

    @Override
    public CourseDto createCourse(
            CreateCourseDto dto,
            String username) {

        UserDto user = userClient.getUserByUsername(username);

        if (!"INSTRUCTOR".equals(user.getRole())) {
            throw new RuntimeException(
                    "Only instructors can create courses"
            );
        }

        CourseEntity course = CourseEntity.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .instructorId(user.getId())
                .price(dto.getPrice())
                .build();

        CourseEntity savedCourse =
                courseRepository.save(course);

        return mapToDto(savedCourse);
    }

    @Override
    public CourseDto getCourseById(Long id) {

        CourseEntity course =
                courseRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Course not found with id: " + id
                                ));

        return mapToDto(course);
    }

    @Override
    public List<CourseDto> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public CourseDto updateCourse(
            Long id,
            UpdateCourseDto dto,
            String username) {

        UserDto user =
                userClient.getUserByUsername(username);

        if (!"INSTRUCTOR".equals(user.getRole())) {
            throw new RuntimeException(
                    "Only instructors can update courses"
            );
        }

        CourseEntity course =
                courseRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Course not found with id: " + id
                                ));

        if (!course.getInstructorId()
                .equals(user.getId())) {

            throw new RuntimeException(
                    "You can only update your own courses"
            );
        }

        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setPrice(dto.getPrice());

        CourseEntity updatedCourse =
                courseRepository.save(course);

        return mapToDto(updatedCourse);
    }

    @Override
    public void deleteCourse(
            Long id,
            String username) {

        UserDto user =
                userClient.getUserByUsername(username);

        if (!"INSTRUCTOR".equals(user.getRole())) {
            throw new RuntimeException(
                    "Only instructors can delete courses"
            );
        }

        CourseEntity course =
                courseRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Course not found with id: " + id
                                ));

        if (!course.getInstructorId()
                .equals(user.getId())) {

            throw new RuntimeException(
                    "You can only delete your own courses"
            );
        }

        courseRepository.delete(course);
    }

    private CourseDto mapToDto(
            CourseEntity course) {

        return CourseDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .instructorId(course.getInstructorId())
                .price(course.getPrice())
                .build();
    }
}