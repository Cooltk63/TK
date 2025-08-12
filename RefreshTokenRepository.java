package com.example.loginservice.repository;

import com.example.loginservice.entity.RefreshToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {

    @Query(value = "select * from user_session where user_id=:userId" +
            " order by session_started_at desc fetch first 1 rows only",nativeQuery = true)
    public RefreshToken getLatestRefreshTokensByUserid(@Param("userId") int userId);
}
