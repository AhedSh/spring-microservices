package com.hellowspring.course_service.Course;

import java.util.List;

public interface CourseService {

    CourseDto createCourse(
            CreateCourseDto courseDto,
            String username
    );

    CourseDto getCourseById(Long id);

    List<CourseDto> getAllCourses();

    CourseDto updateCourse(
            Long id,
            UpdateCourseDto courseDto,
            String username
    );

    void deleteCourse(
            Long id,
            String username
    );
}