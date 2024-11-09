package com.tetz.spring_boot_demo.service;

import com.tetz.spring_boot_demo.entity.User;
import com.tetz.spring_boot_demo.entity.UserVo;
import com.tetz.spring_boot_demo.repository.UserJdbcRepository;
import com.tetz.spring_boot_demo.repository.UserMapperRepository;
import com.tetz.spring_boot_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserJdbcRepository userJdbcRepository;
    private final UserMapperRepository userMapperRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapperRepository userMapperRepository,
                           DataSource dataSource) {
        this.userRepository = userRepository;
        this.userMapperRepository = userMapperRepository;
        this.userJdbcRepository = UserJdbcRepository.getInstance(dataSource);
    }

    public List<User> findAllMybatis() {
        return userMapperRepository.findAll();
    }

    public List<User> findAllJpa() {
        return userRepository.findAll();
    }

    public List<UserVo> findAllJdbc() {
        return userJdbcRepository.findAll();
    }
}