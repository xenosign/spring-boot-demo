package com.tetz.spring_boot_demo.service;

import com.tetz.spring_boot_demo.entity.User;
import com.tetz.spring_boot_demo.repository.UserMapperRepository;
import com.tetz.spring_boot_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapperServiceImpl implements UserMapperService {
    private final UserMapperRepository userMapperRepository;

    @Autowired
    public UserMapperServiceImpl(UserMapperRepository userMapperRepository) {
        this.userMapperRepository = userMapperRepository;
    }

    public List<User> findAll() {
        return userMapperRepository.findAll();
    }
}