package com.jojo.Day4;

import java.util.ArrayList;
import java.util.List;

public class Rooms {
    private List<String> rooms;
    public Rooms() {
        rooms = new ArrayList<>();
    }
    public void addRoom(String room) {
        rooms.add(room);
    }

    public List<String> getRooms() {
        return rooms;
    }
}
