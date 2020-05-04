package com.example.demo.service;

import com.example.demo.entity.RoomEntity;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.RoomRepositoryHiber;
import com.example.demo.util.ResponseGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    //Uncomment this repository when wants to run using hibernate
    /*@Autowired
    private RoomRepositoryHiber repository;*/

    @Autowired
    private ResponseGenerator response;

    @Autowired
    private RoomRepository repository;

    @Transactional
    public List<RoomEntity> getAllRooms(){
        return getRoomEntities();
    }

    @Transactional
    public RoomEntity getRoomById(Integer id){
        Optional<RoomEntity> roomEntity = repository.findById(id);
        if(roomEntity.isPresent())
            return roomEntity.get();
        else
            throw response.getErrorResponse();
    }

    @Transactional
    public RoomEntity saveRoom(RoomEntity roomEntity){
        return repository.save(roomEntity);
    }

    @Transactional
    public RoomEntity updateRoom(RoomEntity roomEntity){
        if (repository.findById(roomEntity.getId()).isPresent()) {
            repository.updateRoom(roomEntity.getId(), roomEntity.getName(), roomEntity.getNumber(), roomEntity.getBed());
            return roomEntity;
        }
        else
            throw response.getErrorResponse();
    }

    @Transactional
    public RoomEntity roomPatch(Integer id,RoomEntity roomEntity){
        Optional<RoomEntity> oldRoomEntity = repository.findById(id);
        if (oldRoomEntity.isPresent()){
            if(roomEntity.getNumber()!=null)
                repository.updateRoomNumber(oldRoomEntity.get().getId(), roomEntity.getNumber());
            else if (roomEntity.getName() != null)
                repository.updateRoomName(oldRoomEntity.get().getId(), roomEntity.getName());
            else
                repository.updateRoomBed(oldRoomEntity.get().getId(), roomEntity.getBed());
            return roomEntity;
        }
        else
            throw response.getErrorResponse();
    }

    @Transactional
    public void deleteRoom(Integer id){
        Optional<RoomEntity> roomEntity = repository.findById(id);
        if(roomEntity.isPresent())
            repository.delete(roomEntity.get());
        else
            throw new RoomNotFoundException("Room Not Found", HttpStatus.BAD_REQUEST);
    }

    @Transactional
    private List<RoomEntity> getRoomEntities() {
        List<RoomEntity> rooms = new ArrayList<>();
        repository.findAll().forEach(rooms::add);
        return rooms;
    }
}
