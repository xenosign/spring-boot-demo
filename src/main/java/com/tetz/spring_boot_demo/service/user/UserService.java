package com.tetz.spring_boot_demo.service.user;

import com.tetz.spring_boot_demo.entity.user.User;
import com.tetz.spring_boot_demo.entity.user.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findAllMybatis();
    List<User> findAllJpa();
    List<UserVo> findAllJdbc();
}
