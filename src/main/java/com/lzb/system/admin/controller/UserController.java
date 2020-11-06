package com.lzb.system.admin.controller;

import com.lzb.common.utils.aes.AesUtil;
import com.lzb.common.utils.data.RedisData;
import com.lzb.common.utils.md5.MD5Util;
import com.lzb.common.utils.redis.RedisUtils;
import com.lzb.common.utils.response.ResponseData;
import com.lzb.common.utils.response.ResponseEnum;
import com.lzb.common.utils.token.JwtToken;
import com.lzb.system.admin.doman.User;
import com.lzb.system.admin.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private JwtToken jwtToken;
    @Resource
    private RedisUtils redisUtils;
    @RequestMapping(value = "login")
    public ResponseData<User> login(@RequestBody User user){
        user.setPassword(AesUtil.aesDecrypt(user.getPassword()));
        User login = userService.login(user);
        if (!StringUtils.isEmpty(login)){
            String id = MD5Util.digest(login.getId().toString());
            if (redisUtils.exists(id)){
                RedisData<User> redisData = (RedisData<User>) redisUtils.get(id);
                boolean set = redisUtils.set(id, redisData, 1L, TimeUnit.DAYS);
                return new ResponseData<>(ResponseEnum.LOGIN_OK,login,redisData.getToken());
            }else {
                String jwtToken = this.jwtToken.createJwtToken(id);
                RedisData<User> redisData = new RedisData<>(jwtToken, login);
                redisUtils.set(id,redisData,1L,TimeUnit.DAYS);
                return new ResponseData<>(ResponseEnum.LOGIN_OK,login,jwtToken);
            }
        }else {
            return new ResponseData<>(ResponseEnum.LOGIN_ERR);
        }
    }

    @RequestMapping(value = "user")
    @Secured("ROLE_user:list")
    public String user(){
        return "user";
    }

    @RequestMapping(value = "index")
    public String index(){
        return "index";
    }
}
