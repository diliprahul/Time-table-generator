package com.college.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    private List<String> subjects;  // Theory subjects

    @ElementCollection
    private List<String> labs;      // Lab subjects

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getSubjects() { return subjects; }
    public void setSubjects(List<String> subjects) { this.subjects = subjects; }

    // --- NEW ---
    public List<String> getLabs() { return labs; }
    public void setLabs(List<String> labs) { this.labs = labs; }
}
