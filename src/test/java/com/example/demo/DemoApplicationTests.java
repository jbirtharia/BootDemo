package com.example.demo;

import com.example.demo.entity.RoomEntity;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.RoomRepositoryHiber;
import com.example.demo.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@MockBean
	private RoomRepository repository;

	@Autowired
	private RoomService roomService;

	@Test
	public void getAllRoomsTest(){
		when(repository.findAll())
				.thenReturn(getCollectionOfRooms());
		log.info("All Rooms {}",
				 roomService.getAllRooms());
		assertEquals(4,roomService.getAllRooms().size());
	}

	@Test
	public void saveRoomTest(){
		RoomEntity roomEntity = new RoomEntity(1, "red", "101", "large");
		when(repository.save(roomEntity))
				.thenReturn(roomEntity);
		assertEquals(roomEntity,roomService.saveRoom(roomEntity));
	}

	@Test
	public void getRoomTest(){
		RoomEntity roomEntity = new RoomEntity(1, "red", "101", "large");
		when(repository.findById(1))
				.thenReturn(java.util.Optional.of(roomEntity));
		assertEquals(roomEntity,roomService.getRoomById(1));
	}

	private List<RoomEntity> getCollectionOfRooms() {
		return Stream.of(new RoomEntity(1, "red", "101", "large"),
						 new RoomEntity(2,"yellow","102","small"),
						 new RoomEntity(3,"white","103","medium"),
						 new RoomEntity(4,"black","104","large"))
							.collect(Collectors.toList());
	}
}
