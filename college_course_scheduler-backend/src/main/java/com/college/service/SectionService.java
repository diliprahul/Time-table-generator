package com.college.service;

import com.college.model.Section;
import com.college.repository.SectionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public Section ensureExists(String sectionName) {
        if (sectionName == null || sectionName.isBlank()) return null;

        Section existing = sectionRepository.findByName(sectionName);
        if (existing != null) return existing;

        Section newSection = new Section();
        newSection.setName(sectionName);
        return sectionRepository.save(newSection);
    }

    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    public Section getSectionById(Long id) {
        return sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found with id: " + id));
    }

    public void deleteSection(Long id) {
        if (!sectionRepository.existsById(id)) {
            throw new RuntimeException("Section not found");
        }
        sectionRepository.deleteById(id);
    }
}
