package com.tetz.spring_boot_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String name;
    private LocalDateTime createdAt;
}