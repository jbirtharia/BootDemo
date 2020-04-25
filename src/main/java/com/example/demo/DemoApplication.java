package com.example.demo;

import com.example.demo.entity.RoomEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public List<RoomEntity> getRooms()
	{
		List<RoomEntity> rooms = new ArrayList<>();
		rooms.add(new RoomEntity(1,"red","101","large"));
		rooms.add(new RoomEntity(2,"yellow","102","medium"));
		rooms.add(new RoomEntity(3,"blue","103","small"));
		rooms.add(new RoomEntity(4,"white","104","large"));
		return rooms;
	}
}
