package com.jb.bookingSystem.persistence.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true,nullable = false)
    private int roomNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomType type;

    public RoomEntity(int id, int roomNumber, RoomType type) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.type = type;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RoomEntity that = (RoomEntity) o;
        return id == that.id && roomNumber == that.roomNumber && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomNumber, type);
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", type=" + type +
                '}';
    }
}
