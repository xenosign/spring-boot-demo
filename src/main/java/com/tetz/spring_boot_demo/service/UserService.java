package com.tetz.spring_boot_demo.service;

import com.tetz.spring_boot_demo.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllMybatis();
    List<User> findAllJpa();
}
