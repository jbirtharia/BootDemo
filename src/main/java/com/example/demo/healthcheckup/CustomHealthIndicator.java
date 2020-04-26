package com.example.demo.healthcheckup;

import com.example.demo.entity.RoomEntity;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Value("${main_url}")
    private String main_url;

    @Autowired
    RestTemplate restTemplate;

    public final static String message_key="Room Service";

    @SneakyThrows
    @Override
    public Health health() {
        if(isServiceRunning())
            return Health.up().withDetail(message_key,"Available").build();
        else
            return Health.down().withDetail(message_key,"Not Available").build();
    }

    @SneakyThrows
    private Boolean isServiceRunning(){
        return restTemplate.getForObject(main_url, RoomEntity[].class) != null;
    }
}
