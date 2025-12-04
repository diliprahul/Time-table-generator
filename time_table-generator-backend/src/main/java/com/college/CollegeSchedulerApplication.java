package com.college;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.college.service.CourseService;

@SpringBootApplication
public class CollegeSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollegeSchedulerApplication.class, args);
    }

    // ✅ Auto-clear all courses on startup
    @Bean
    CommandLineRunner clearCoursesOnStartup(CourseService courseService) {
        return args -> {
            courseService.deleteAllCourses();
            System.out.println("✅ All courses cleared on startup.");
        };
    }
}
