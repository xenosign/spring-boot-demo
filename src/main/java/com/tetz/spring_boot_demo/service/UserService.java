package com.tetz.spring_boot_demo.service;

import com.tetz.spring_boot_demo.entity.User;
import com.tetz.spring_boot_demo.entity.UserVo;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllMybatis();
    List<User> findAllJpa();
    List<UserVo> findAllJdbc();
}
