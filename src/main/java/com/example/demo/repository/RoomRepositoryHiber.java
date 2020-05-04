package com.example.demo.repository;

import com.example.demo.entity.RoomEntity;
import java.util.Optional;

public interface RoomRepositoryHiber {

    public Optional<RoomEntity> findById (Integer id);

    public RoomEntity save(RoomEntity roomEntity);

    public RoomEntity updateRoom(Integer id, String name, String number, String bed);

    public RoomEntity updateRoomNumber(Integer id, String number);

    public RoomEntity updateRoomName(Integer id, String name);

    public RoomEntity updateRoomBed(Integer id, String bed);

    public void delete(RoomEntity roomEntity);

    public Iterable<RoomEntity> findAlls();
}
