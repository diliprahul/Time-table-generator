package com.college.controller;

import com.college.model.Course;
import com.college.model.ScheduleEntry;
import com.college.model.Section;
import com.college.repository.ScheduleRepository;
import com.college.repository.SectionRepository;
import com.college.service.CourseService;
import com.college.service.LecturerService;
import com.college.service.RoomService;
import com.college.service.SectionService;
import com.college.dto.CourseDTO;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {

    private final CourseService courseService;
    private final SectionService sectionService;
    private final LecturerService lecturerService;
    private final RoomService roomService;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public CourseController(CourseService courseService,
                            SectionService sectionService,
                            LecturerService lecturerService,
                            RoomService roomService) {
        this.courseService = courseService;
        this.sectionService = sectionService;
        this.lecturerService = lecturerService;
        this.roomService = roomService;
    }

    // ✅ Get all courses
    @GetMapping("/allCourses")
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses()
                .stream()
                .map(CourseDTO::new)
                .collect(Collectors.toList());
    }

    // ✅ Add a new course
    @PostMapping("/add")
    public Course addCourse(@RequestBody Map<String, String> body) {
        String courseName = body.get("courseName");
        String startTime = body.get("startTime");
        String endTime = body.get("endTime");
        String date = body.get("date");

        String sectionName = body.get("sectionName");
        String lecturerName = body.get("lecturerName");
        String roomNumber = body.get("roomNumber");

        Section section = sectionService.ensureExists(sectionName);
        var lecturer = lecturerService.ensureExists(lecturerName);
        var room = roomService.ensureExists(roomNumber);

        Course course = new Course();
        course.setCourseName(courseName);
        course.setStartTime(startTime);
        course.setEndTime(endTime);
        course.setDate(date);
        course.setSection(section);
        course.setLecturer(lecturer);
        course.setRoom(room);

        return courseService.addCourse(course);
    }

    // ✅ Get weekly schedule for all sections
    @GetMapping("/weekly")
    public Map<String, Map<String, List<ScheduleEntry>>> getWeeklySchedule() {
        Map<String, Map<String, List<ScheduleEntry>>> weeklySchedule = new HashMap<>();
        List<String> days = Arrays.asList("Monday","Tuesday","Wednesday","Thursday","Friday");

        List<Section> sections = sectionRepository.findAll(); // ✅ instance method

        for (Section section : sections) {
            Map<String, List<ScheduleEntry>> sectionSchedule = new HashMap<>();
            for (String day : days) {
                List<ScheduleEntry> entries = scheduleRepository.findBySectionNameAndDay(section.getName(), day);
                sectionSchedule.put(day, entries);
            }
            weeklySchedule.put(section.getName(), sectionSchedule);
        }

        return weeklySchedule;
    }

    // ✅ Delete by ID
    @DeleteMapping("/delete/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
    @DeleteMapping("/deleteSection/{sectionName}")
public void deleteCoursesBySection(@PathVariable String sectionName) {
    courseService.deleteBySectionName(sectionName);
}


    // ✅ Delete all
    @DeleteMapping("/deleteAll")
    public void deleteAllCourses() {
        courseService.deleteAllCourses();
    }
}
