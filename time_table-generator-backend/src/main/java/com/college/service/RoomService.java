package com.college.service;

import com.college.model.Room;
import com.college.repository.RoomRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // ✅ Ensure room exists or create new
    public Room ensureExists(String roomNumber) {
        if (roomNumber == null || roomNumber.isBlank()) return null;

        Room existing = roomRepository.findByRoomNumber(roomNumber);
        if (existing != null) return existing;

        Room newRoom = new Room();
        newRoom.setRoomNumber(roomNumber);
        return roomRepository.save(newRoom);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }

    public void deleteRoom(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new RuntimeException("Room not found");
        }
        roomRepository.deleteById(id);
    }
}
