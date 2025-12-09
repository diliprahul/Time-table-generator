package com.college.service;

import com.college.model.ScheduleEntry;
import com.college.model.Section;
import com.college.model.Course;
import com.college.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SchedulerService {

    private final ScheduleRepository scheduleRepository;

    public SchedulerService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    private final String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday"};
    private final String[] slotTimes = {"9:00","10:00","11:00","12:00","1:00","2:00","3:00","4:00"};

    public void generateWeeklySchedule(List<Section> sections) {
        for (Section section : sections) {
            Set<String> assignedLabs = new HashSet<>();

            for (String day : days) {
                List<ScheduleEntry> daySlots = new ArrayList<>();
                Set<String> occupiedLecturers = new HashSet<>();
                Set<String> occupiedRooms = new HashSet<>();

                // 1. Assign Lab if not assigned this week
                for (String lab : section.getLabs()) {
                    if (!assignedLabs.contains(lab)) {
                        ScheduleEntry labEntry = new ScheduleEntry();
                        labEntry.setSectionName(section.getName());
                        labEntry.setSubjectName(lab);
                        labEntry.setType("Lab");
                        labEntry.setDay(day);
                        labEntry.setStartTime("9:00");      // Morning by default
                        labEntry.setEndTime("12:00");       // 3-hour block
                        labEntry.setLecturerName(getAvailableLecturer(lab, occupiedLecturers));
                        labEntry.setRoomName(getAvailableRoom(lab, occupiedRooms));
                        labEntry.setLabAssignedThisWeek(true);

                        daySlots.add(labEntry);

                        // Mark lecturer/room occupied
                        occupiedLecturers.add(labEntry.getLecturerName());
                        occupiedRooms.add(labEntry.getRoomName());

                        assignedLabs.add(lab);
                        break; // Only one lab per day
                    }
                }

                // 2. Fill remaining slots with theory subjects
                List<String> theorySubjects = rotateSubjects(section.getSubjects(), day);
                int slotIndex = 0;
                for (String subject : theorySubjects) {
                    // Skip slot if already occupied by lab
                    while (slotIndex < slotTimes.length && slotIndex < 3 && daySlots.size() > 0) {
                        slotIndex++; // Skip lab slots (first 3 hours)
                    }

                    if (slotIndex >= slotTimes.length) break;

                    ScheduleEntry theoryEntry = new ScheduleEntry();
                    theoryEntry.setSectionName(section.getName());
                    theoryEntry.setSubjectName(subject);
                    theoryEntry.setType("Theory");
                    theoryEntry.setDay(day);
                    theoryEntry.setStartTime(slotTimes[slotIndex]);
                    theoryEntry.setEndTime(slotTimes[slotIndex+1]);
                    theoryEntry.setLecturerName(getAvailableLecturer(subject, occupiedLecturers));
                    theoryEntry.setRoomName(getAvailableRoom(subject, occupiedRooms));

                    daySlots.add(theoryEntry);
                    occupiedLecturers.add(theoryEntry.getLecturerName());
                    occupiedRooms.add(theoryEntry.getRoomName());
                    slotIndex++;
                }

                // Save all daySlots to DB
                scheduleRepository.saveAll(daySlots);
            }
        }
    }

    // Dummy method: select an available lecturer not in occupiedLecturers
    private String getAvailableLecturer(String subject, Set<String> occupied) {
        String lecturer = subject + " Prof"; // Example mapping
        if (!occupied.contains(lecturer)) return lecturer;
        return lecturer + "2"; // fallback
    }

    // Dummy method: select an available room not in occupiedRooms
    private String getAvailableRoom(String subject, Set<String> occupied) {
        String room = subject + " Room";
        if (!occupied.contains(room)) return room;
        return room + "2";
    }

    // Rotate subjects fairly for each day
    private List<String> rotateSubjects(List<String> subjects, String day) {
        int dayIndex = Arrays.asList(days).indexOf(day);
        List<String> rotated = new ArrayList<>(subjects);
        Collections.rotate(rotated, dayIndex);
        return rotated;
    }
}
