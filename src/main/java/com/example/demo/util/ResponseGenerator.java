package com.example.demo.util;

import com.example.demo.entity.RoomEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.List;

@Component
public class ResponseGenerator {

    public ResponseEntity getResponse()
    {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("message","Request has been successfully processed");
        return new ResponseEntity(map, HttpStatus.ACCEPTED);
    }

    public ResponseEntity getResponse(RoomEntity roomEntity,HttpStatus status)
    {
        return new ResponseEntity(roomEntity, status);
    }

    public ResponseEntity getResponse(List<RoomEntity> roomEntities,HttpStatus status)
    {
        return new ResponseEntity(roomEntities, status);
    }

    public ResponseStatusException getErrorResponse()
    {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Please Send Correct Room Number");
    }
}
