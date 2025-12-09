package com.college.controller;

import com.college.model.Lecturer;
import com.college.service.LecturerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/lecturers")
@CrossOrigin(origins = "http://localhost:3000")
public class LecturerController {

    private final LecturerService service;

    public LecturerController(LecturerService service) { this.service = service; }

    @GetMapping("/all")
    public List<Lecturer> getAllLecturers() { return service.getAllLecturers(); }

    @PostMapping("/add")
    public Lecturer addLecturer(@RequestBody Lecturer lecturer) { return service.addLecturer(lecturer); }

    @DeleteMapping("/{id}")
    public void deleteLecturer(@PathVariable Long id) { service.deleteLecturer(id); }
}
