package com.lzb;

import com.lzb.common.utils.data.RedisData;
import com.lzb.common.utils.date.AppDateUtils;
import com.lzb.common.utils.md5.MD5Util;
import com.lzb.common.utils.redis.RedisUtils;
import com.lzb.common.utils.token.JwtToken;
import com.lzb.system.admin.dao.UserDao;
import com.lzb.system.admin.doman.Menu;
import com.lzb.system.admin.doman.Role;
import com.lzb.system.admin.doman.User;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;


import javax.annotation.Resource;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class AdminApplicationTests {
    @Resource
    private UserDao userDao;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private JwtToken jwtToken;
    @Test
    void contextLoads() {
    }
    @Test
    public void add(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setCreateTime(AppDateUtils.getFormatTime());
        user.setIsAdmin(true);
        user.setUpdateTime(AppDateUtils.getFormatTime());
        user.setPwdResetTime(AppDateUtils.getFormatTime());
        user.setEnabled(true);
        Role role = new Role();
        role.setEnabled(true);
        role.setCreateTime(AppDateUtils.getFormatTime());
        role.setUpdateTime(AppDateUtils.getFormatTime());
        role.setName("超级管理员");
        role.setDescription("至高无上权力");
        user.getRole().add(role);
        userDao.save(user);
    }

    @Test
    public void find(){
        List<User> all = userDao.findAll();
        for (User u:all) {
            System.out.println(u);
            for (Role role:u.getRole()) {
                System.out.println(role);
                for (Menu menu:role.getMenus()) {
                    System.out.print(menu.getPermission());
                }
            }
        }
    }

    @Test
    public void redis(){


        RedisData<User> redisData = (RedisData<User>) redisUtils.get(MD5Util.digest("1"));

        System.out.println(redisData.getToken());

    }
    @Test
    public void token(){
        Claims claims = jwtToken.verifyToken("eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiJjNGNhNDIzOGEwYjkyMzgyMGRjYzUwOWE2Zjc1ODQ5YiIsImlhdCI6MTYwMzc4OTYxNCwiZXhwIjoxNjAzNzk5NjE0fQ.OX5JWsMZw9Wlrf0cRtBez41iAwD8jlxbn3-N_rQq54qB6l9V1uP8Zh2A90o1TatTwsLa_0oUsCeWN099zoYV_g");
        System.out.println(claims);

        System.out.println(new Date().getTime());
    }
}
