package com.tetz.spring_boot_demo.service;

import com.tetz.spring_boot_demo.entity.User;

import java.util.List;

public interface UserMapperService {
    List<User> findAll();
}
