package com.tetz.spring_boot_demo.repository;

import com.tetz.spring_boot_demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapperRepository {
    // 모든 사용자 검색
    List<User> findAll();
}
