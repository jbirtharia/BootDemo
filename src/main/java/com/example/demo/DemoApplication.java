package com.example.demo;

import com.example.demo.repository.RoomRepositoryHiber;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

//Uncomment below line when wants to run using hibernate
/*@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)*/
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	//Method for initial data populate
	@Bean
	public CommandLineRunner demo(RoomRepositoryHiber repository){
		return args -> {
			/*repository.save(new RoomEntity("red","101","large"));
			repository.save(new RoomEntity("yellow","102","medium"));
			repository.save(new RoomEntity("blue","103","small"));
			repository.save(new RoomEntity("white","104","large"));*/
		};
	}

}
