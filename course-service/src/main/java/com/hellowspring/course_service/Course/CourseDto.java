package com.hellowspring.course_service.Course;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {

    private Long id;

    private String title;

    private String description;

    private Long instructorId;

    private Double price;
}

