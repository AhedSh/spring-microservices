package com.hellowspring.course_service.Course;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(
            @Valid @RequestBody CreateCourseDto courseDto,
            Authentication authentication) {

        CourseDto createdCourse =
                courseService.createCourse(
                        courseDto,
                        authentication.getName()
                );

        return new ResponseEntity<>(
                createdCourse,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                courseService.getCourseById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {

        return ResponseEntity.ok(
                courseService.getAllCourses()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCourseDto courseDto,
            Authentication authentication) {

        return ResponseEntity.ok(
                courseService.updateCourse(
                        id,
                        courseDto,
                        authentication.getName()
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable Long id,
            Authentication authentication) {

        courseService.deleteCourse(
                id,
                authentication.getName()
        );

        return ResponseEntity.noContent().build();
    }
}