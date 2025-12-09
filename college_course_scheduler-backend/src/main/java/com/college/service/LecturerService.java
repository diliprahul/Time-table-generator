package com.college.service;

import com.college.model.Lecturer;
import com.college.repository.LecturerRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LecturerService {

    private final LecturerRepository repository;

    public LecturerService(LecturerRepository repository) {
        this.repository = repository;
    }

    // Ensure lecturer exists or create new one
    public Lecturer ensureExists(String lecturerName) {
        if (lecturerName == null || lecturerName.isBlank()) return null;

        Lecturer existing = repository.findByLecturerName(lecturerName);
        if (existing != null) return existing;

        Lecturer newLecturer = new Lecturer();
        newLecturer.setLecturerName(lecturerName);
        return repository.save(newLecturer);
    }

    public List<Lecturer> getAllLecturers() {
        return repository.findAll();
    }

    public Lecturer addLecturer(Lecturer lecturer) {
        return repository.save(lecturer);
    }

    public Lecturer getLecturerById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Lecturer not found with id: " + id
                        )
                );
    }

    // SAFE DELETE — prevents deleting lecturers that are used in courses
    public void deleteLecturer(Long id) {

        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Lecturer not found with id: " + id
            );
        }

        try {
            repository.deleteById(id);

        } catch (DataIntegrityViolationException ex) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Cannot delete lecturer: This lecturer is assigned to one or more courses."
            );
        }
    }
}
