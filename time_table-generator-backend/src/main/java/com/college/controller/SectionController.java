package com.college.controller;

import com.college.model.Section;
import com.college.service.SectionService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;
@RestController
@RequestMapping("/sections")
@CrossOrigin(origins = "http://localhost:3000")
public class SectionController {
    private final SectionService service;

    public SectionController(SectionService service) { 
        this.service = service; 
    }

    @PostMapping("/add")
    public Section addSection(@RequestBody Map<String,String> body) {
        String name = body.get("sectionName");
        return service.ensureExists(name);
    }

    @GetMapping("/all")
    public List<Section> getAllSections() { 
        return service.getAllSections(); 
    }

    // ✅ New delete endpoint
    @DeleteMapping("/{id}")
    public String deleteSection(@PathVariable Long id) {
        service.deleteSection(id);
        return "Section deleted successfully";
    }
}
