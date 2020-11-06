package com.lzb.system.admin.service.impl;



import com.lzb.system.admin.dao.UserDao;
import com.lzb.system.admin.doman.User;
import com.lzb.system.admin.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public User login(User user){
        User login = userDao.login(user.getUsername(), user.getPassword());
        return login;
    }
}
