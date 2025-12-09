package com.college.model;

import jakarta.persistence.*;

@Entity
public class ScheduleEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sectionName;
    private String subjectName;

    // Type of class: "Lab" or "Theory"
    private String type;

    private String lecturerName;
    private String roomName;
    private String day;
    private String startTime;
    private String endTime;

    // Flag to check if lab is assigned this week
    @Column(name = "lab_assigned_this_week")
    private boolean labAssignedThisWeek;

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isLabAssignedThisWeek() {
        return labAssignedThisWeek;
    }

    public void setLabAssignedThisWeek(boolean labAssignedThisWeek) {
        this.labAssignedThisWeek = labAssignedThisWeek;
    }
}
