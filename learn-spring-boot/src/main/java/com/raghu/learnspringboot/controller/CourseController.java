package com.raghu.learnspringboot.controller;

import ch.qos.logback.core.CoreConstants;
import com.raghu.learnspringboot.dto.Course;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class CourseController {
    @GetMapping()
    @ResponseBody
    public String sayHello() {
        return "Hello Raghuveer Reddy";
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourseDetails() {
        List<Course> listOfCourses = Arrays.asList(new Course(101, "Java", "Raghuveer"), new Course(102, "Python", "Ranga Karanam"),new Course(103,"Groovy","Java Guides"));
        return new ResponseEntity<List<Course>>(listOfCourses, HttpStatus.OK);
    }

}
