package com.college.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lecturer")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lecturer_name", nullable = false)
    private String lecturerName;

    @Column(name = "subject")
    private String subject;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLecturerName() { return lecturerName; }
    public void setLecturerName(String lecturerName) { this.lecturerName = lecturerName; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
}
