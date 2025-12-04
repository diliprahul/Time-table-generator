package com.college.service;

import com.college.model.Course;
import com.college.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    // ✅ Add new course
    public Course addCourse(Course course) {
        return repository.save(course);
    }

    // ✅ Get all courses
    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    // ✅ Delete course by ID
    public void deleteCourse(Long id) {
        repository.deleteById(id);
    }

    // ✅ Delete all courses (optional helper)
    public void deleteAllCourses() {
        repository.deleteAll();
    }
    public void deleteBySectionName(String sectionName) {
    repository.deleteBySection_Name(sectionName);
}


    // ✅ Get a single course by ID (optional helper)
    public Course getCourseById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }
}
