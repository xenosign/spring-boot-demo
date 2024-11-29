package com.tetz.spring_boot_demo.repository.user;

import com.tetz.spring_boot_demo.entity.user.UserVo;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcRepository {
    // 싱글톤 인스턴스
    private static UserJdbcRepository instance;
    private final DataSource dataSource;

    // private 생성자
    private UserJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 싱글톤 인스턴스를 가져오는 메서드
    public static synchronized UserJdbcRepository getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new UserJdbcRepository(dataSource);
        }
        return instance;
    }

    // 모든 사용자 조회
    public List<UserVo> findAll() {
        List<UserVo> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                UserVo user = new UserVo();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all users", e);
        }
        return users;
    }
}