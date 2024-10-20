package com.tetz.spring_boot_demo.repository;

import com.tetz.spring_boot_demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapperRepository {
    // 모든 사용자 검색
    List<User> findAll();

    // 사용자 이름으로 사용자 찾기
    Optional<User> findByUsername(String username);

    // 이메일로 사용자 찾기
    Optional<User> findByEmail(String email);

    // 이름으로 사용자 목록 찾기
    List<User> findByName(String name);

    // 사용자 이름이 특정 패턴과 일치하는 사용자 목록 찾기
    List<User> findByUsernameLike(String pattern);

    // 특정 날짜 이후에 생성된 사용자 목록 찾기
    List<User> findUsersCreatedAfter(LocalDateTime date);

    // 사용자 이름 업데이트
    int updateUsername(@Param("id") Long id, @Param("name") String name);

    // 사용자 삭제 (ID로)
    void deleteById(Long id);

    // 모든 사용자 수 계산
    long count();
}