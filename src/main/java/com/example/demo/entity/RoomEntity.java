package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class RoomEntity {
    private Integer id;
    private String name;
    private String number;
    private String bed;
}
