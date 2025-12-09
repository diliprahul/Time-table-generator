package com.college.repository;

import com.college.model.ScheduleEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntry, Long> {

    // Get all schedule entries for a section for the week
    List<ScheduleEntry> findBySectionName(String sectionName);

    // Get all schedule entries for a specific day and section
    List<ScheduleEntry> findBySectionNameAndDay(String sectionName, String day);

    // Optional: find all labs assigned this week for a section
    List<ScheduleEntry> findBySectionNameAndTypeAndLabAssignedThisWeekTrue(String sectionName, String type);
}
