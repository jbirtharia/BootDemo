package com.example.demo.controller;

import com.example.demo.entity.RoomEntity;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.util.EntityIdGenerator;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Log
@RestController
@RequestMapping("/rooms")
public class DemoController {

    @Autowired
    List<RoomEntity> rooms;

    @Autowired
    EntityIdGenerator entityIdGenerator;

    @GetMapping("/all")
    public ResponseEntity<List<RoomEntity>> getAllRooms(){
      return new ResponseEntity<List<RoomEntity>>(rooms, HttpStatus.OK);
    }

    @GetMapping("/findRoom/{id}")
    public ResponseEntity<RoomEntity> getRoom(@PathVariable("id") Integer id){
        Optional<RoomEntity> room = rooms.stream()
                .filter(r -> r.getId().equals(id)).findAny();
        if(room.isPresent())
            return new ResponseEntity<RoomEntity>(room.get(),HttpStatus.FOUND);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Please Send Correct Room Number");
    }

    @PostMapping("/add")
    public ResponseEntity<RoomEntity> saveRoom(@RequestBody RoomEntity roomEntity){
        roomEntity.setId(entityIdGenerator.idGenerator(rooms));
        rooms.add(roomEntity);
        return new ResponseEntity<RoomEntity>(roomEntity,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<RoomEntity> updateRoom(@RequestBody RoomEntity roomEntity){
        Optional<RoomEntity> oldRoom = rooms.stream()
                .filter(room -> room.getId().equals(roomEntity.getId())).findAny();
        if (oldRoom.isPresent()){
            oldRoom.get().setBed(roomEntity.getBed());
            oldRoom.get().setName(roomEntity.getName());
            oldRoom.get().setNumber(roomEntity.getNumber());
            return new ResponseEntity<RoomEntity>(roomEntity,HttpStatus.ACCEPTED);
        }
        else
            throw new RoomNotFoundException("Room Not Found",HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/patchRoom/{id}")
    public ResponseEntity patchRecord(@RequestBody RoomEntity roomEntity){
        Optional<RoomEntity> oldRoom = rooms.stream()
                .filter(room -> room.getId().equals(roomEntity.getId())).findAny();
        if(oldRoom.isPresent()){
            if(roomEntity.getBed()!=null)
                oldRoom.get().setBed(roomEntity.getBed());
            if(roomEntity.getName()!=null)
                oldRoom.get().setName(roomEntity.getName());
            if(roomEntity.getNumber()!=null)
                oldRoom.get().setNumber(roomEntity.getNumber());
            return new ResponseEntity("Record Has Been Patched",HttpStatus.ACCEPTED);
        }
        else
            throw new RoomNotFoundException("Room Not Found",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteRoom/{id}")
    public ResponseEntity deleteRoom(@PathVariable("id") Integer id){
        Optional<RoomEntity> room = rooms.stream()
                .filter(r -> r.getId().equals(id)).findAny();
        if(room.isPresent()) {
            rooms.remove(room);
            return new ResponseEntity("Record Has Been Deleted",HttpStatus.ACCEPTED);
        }
        else
            throw new RoomNotFoundException("Room Not Found",HttpStatus.BAD_REQUEST);
    }

}
