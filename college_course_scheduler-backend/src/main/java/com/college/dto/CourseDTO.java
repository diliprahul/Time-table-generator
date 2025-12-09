package com.college.dto;

import com.college.model.Course;

public class CourseDTO {
    private Long id;
    private String courseName;
    private String startTime;
    private String endTime;
    private String date;
    private String lecturerName;
    private String roomNumber;
    private String sectionName;

    public CourseDTO(Course course) {
        this.id = course.getId();
        this.courseName = course.getCourseName();
        this.startTime = course.getStartTime();
        this.endTime = course.getEndTime();
        this.date = course.getDate();
        this.lecturerName = course.getLecturer() != null ? course.getLecturer().getLecturerName() : "N/A";
        this.roomNumber = course.getRoom() != null ? course.getRoom().getRoomNumber() : "N/A";
        this.sectionName = course.getSection() != null ? course.getSection().getName() : "Unnamed Section";
    }

    // Getters (no setters needed if only for display)
    public Long getId() { return id; }
    public String getCourseName() { return courseName; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public String getDate() { return date; }
    public String getLecturerName() { return lecturerName; }
    public String getRoomNumber() { return roomNumber; }
    public String getSectionName() { return sectionName; }
}
