package com.example.demo.util;

import com.example.demo.entity.RoomEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class EntityIdGenerator {

    public Integer idGenerator(List<RoomEntity> rooms)
    {
        return
                (rooms.stream().reduce((first, second) -> second).get().getId()+1);
    }
}
