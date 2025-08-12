package com.example.loginservice.repository;

import com.example.loginservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "Select count(1) from USERS where USER_ID = :userId",nativeQuery = true)
    public int checkUser(@Param("userId") int userId);

    public User findByUserId(int userId);

    @Query(value ="select  u.user_id as userId, u.role_id as role , r.role_name as rolename\n" +
            "from  user_roles u join roles r on u.role_id = r.role_id where u.user_Id = :userId\n fetch first 1 rows only",nativeQuery = true)
    public Map<String,Object> getUserRole(@Param("userId") int userId);
}
