package com.college.repository;

import com.college.model.Course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // 🔍 Find by Section object (optional – keep if needed)
    List<Course> findBySection_Name(String sectionName);

    // ❌ YOUR OLD delete method is missing → Add this:
    // 🔴 Delete all courses for a section by section.name
    void deleteBySection_Name(String sectionName);
}
