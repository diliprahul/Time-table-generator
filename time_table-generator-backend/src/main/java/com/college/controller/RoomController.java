package com.college.controller;

import com.college.model.Room;
import com.college.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = "http://localhost:3000")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/all")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping("/add")
    public Room addRoom(@RequestBody Map<String, String> body) {
        String roomNumber = body.get("roomNumber");
        return roomService.ensureExists(roomNumber);
    }

    // ✅ DELETE endpoint for rooms
    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return "Room deleted successfully";
    }
}
