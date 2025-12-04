package com.college.controller;

import com.college.model.ScheduleEntry;
import com.college.model.Section;
import com.college.repository.ScheduleRepository;
import com.college.repository.SectionRepository;
import com.college.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private SectionRepository sectionRepository;  // ✅ use instance

    @Autowired
    private ScheduleRepository scheduleRepository; // ✅ use instance

    private final List<String> weekDays = Arrays.asList("Monday","Tuesday","Wednesday","Thursday","Friday");

    // --- Generate weekly schedule for all sections ---
    @PostMapping("/generate")
    public String generateWeeklySchedule() {
        List<Section> sections = sectionRepository.findAll(); // ✅ instance method
        schedulerService.generateWeeklySchedule(sections);
        return "Weekly schedule generated successfully!";
    }

    // --- Get schedule for a section ---
    @GetMapping("/section/{sectionName}")
    public List<ScheduleEntry> getScheduleForSection(@PathVariable String sectionName) {
        return scheduleRepository.findBySectionName(sectionName); // ✅ instance method
    }

    // --- Get schedule for a section on a specific day ---
    @GetMapping("/section/{sectionName}/day/{day}")
    public List<ScheduleEntry> getScheduleForSectionByDay(@PathVariable String sectionName,
                                                          @PathVariable String day) {
        return scheduleRepository.findBySectionNameAndDay(sectionName, day); // ✅ instance method
    }

    // --- Get weekly schedule for all sections (for React frontend) ---
    @GetMapping("/all")
    public Map<String, Map<String, List<ScheduleEntry>>> getWeeklySchedule() {
        Map<String, Map<String, List<ScheduleEntry>>> weeklySchedule = new HashMap<>();

        List<Section> sections = sectionRepository.findAll(); // ✅ instance method

        for (Section section : sections) {
            Map<String, List<ScheduleEntry>> sectionSchedule = new HashMap<>();
            for (String day : weekDays) {
                List<ScheduleEntry> entries = scheduleRepository.findBySectionNameAndDay(section.getName(), day);
                sectionSchedule.put(day, entries);
            }
            weeklySchedule.put(section.getName(), sectionSchedule);
        }

        return weeklySchedule;
    }

    // --- Delete all schedules ---
    @DeleteMapping("/deleteAll")
    public String deleteAllSchedules() {
        scheduleRepository.deleteAll();
        return "All schedules deleted successfully!";
    }
}
