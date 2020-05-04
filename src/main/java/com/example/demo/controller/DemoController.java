package com.example.demo.controller;

import com.example.demo.entity.RoomEntity;
import com.example.demo.service.RoomService;
import com.example.demo.util.ResponseGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rooms")
public class DemoController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private ResponseGenerator response;


    @GetMapping("/all")
    public ResponseEntity<List<RoomEntity>> getAllRooms(){
        return response.getResponse(roomService.getAllRooms(), HttpStatus.OK);
    }

    @GetMapping("/findRoom/{id}")
    public ResponseEntity<RoomEntity> getRoom(@PathVariable("id") Integer id){
        return response.getResponse(roomService.getRoomById(id),HttpStatus.FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<RoomEntity> saveRoom(@RequestBody RoomEntity roomEntity){
        roomService.saveRoom(roomEntity);
        return response.getResponse(roomEntity,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity updateRoom(@RequestBody RoomEntity roomEntity){
       roomService.updateRoom(roomEntity);
       return response.getResponse(roomEntity,HttpStatus.ACCEPTED);
    }

    @PatchMapping("/patchRoom/{id}")
    public ResponseEntity patchRecord(@PathVariable("id") Integer id,@RequestBody RoomEntity roomEntity){
       roomService.roomPatch(id,roomEntity);
       return response.getResponse();
    }

    @DeleteMapping("/deleteRoom/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") Integer id){
        roomService.deleteRoom(id);
        return response.getResponse();
    }
}
