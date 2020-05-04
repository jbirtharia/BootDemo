package com.example.demo.repository;

import com.example.demo.entity.RoomEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*@NoRepositoryBean*/
@Repository
public interface RoomRepository extends CrudRepository<RoomEntity,Integer> {

    @Modifying
    @Query("UPDATE RoomEntity room SET room.name = :name, room.number = :number, room.bed = :bed WHERE room.id = :id")
    RoomEntity updateRoom(@Param("id") int id, @Param("name") String name, @Param("number") String number, @Param("bed") String bed);

    @Modifying
    @Query("UPDATE RoomEntity room SET room.number = :number WHERE room.id = :id")
    Integer updateRoomNumber(@Param("id") int id, @Param("number") String number);

    @Modifying
    @Query("UPDATE RoomEntity room SET room.name = :name WHERE room.id = :id")
    Integer updateRoomName(@Param("id") int id, @Param("name") String name);

    @Modifying
    @Query("UPDATE RoomEntity room SET room.bed = :bed WHERE room.id = :id")
    Integer updateRoomBed(@Param("id") int id, @Param("bed") String bed);
}
