package com.tetz.spring_boot_demo.service;

import com.tetz.spring_boot_demo.entity.User;
import com.tetz.spring_boot_demo.entity.UserVo;
import com.tetz.spring_boot_demo.repository.UserJdbcRepository;
import com.tetz.spring_boot_demo.repository.UserMapperRepository;
import com.tetz.spring_boot_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapperRepository  userMapperRepository;
    private final UserJdbcRepository userJdbcRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapperRepository userMapperRepository, UserJdbcRepository userJdbcRepository) {
        this.userRepository = userRepository;
        this.userMapperRepository = userMapperRepository;
        this.userJdbcRepository = userJdbcRepository;
    }

    public List<User> findAllMybatis() {
        return userMapperRepository.findAll();
    }

    public List<User> findAllJpa() {
        return userRepository.findAll();
    }

    public List<UserVo> findAllJdbc() { return userJdbcRepository.findAll(); }
}