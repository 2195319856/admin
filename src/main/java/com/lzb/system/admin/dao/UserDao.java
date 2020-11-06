package com.lzb.system.admin.dao;

import com.lzb.system.admin.doman.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
    @Query("select u from User u where u.username=?1 and u.password=?2")
    User login(@Param("username") String username,@Param("password") String password);
}
