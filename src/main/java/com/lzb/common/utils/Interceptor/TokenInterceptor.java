package com.lzb.common.utils.Interceptor;


import com.lzb.common.utils.data.RedisData;
import com.lzb.common.utils.exception.AdminRuntimeException;
import com.lzb.common.utils.redis.RedisUtils;
import com.lzb.common.utils.response.ResponseEnum;
import com.lzb.common.utils.token.JwtToken;
import com.lzb.system.admin.doman.User;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private JwtToken jwtToken;
    @Resource
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        //获取token
        String token = request.getHeader("token");
        //是否有token
        if (StringUtils.isEmpty(token)){
            throw new AdminRuntimeException(ResponseEnum.TOKEN_NULL);
        }
        //token解析
        Claims claims = jwtToken.verifyToken(token);
        if (claims==null){
            throw new AdminRuntimeException(ResponseEnum.TOKEN_ERR);
        }
        //token超时
        Date expiration = claims.getExpiration();
        boolean tokenExpired = jwtToken.isTokenExpired(expiration);
        if (tokenExpired){
            throw new AdminRuntimeException(ResponseEnum.TOKEN_TIME_ERR);
        }
        //用户存在
        String subject = claims.getSubject();


        boolean exists = redisUtils.exists(subject);
        if (exists){
            RedisData<User> redisData = (RedisData<User>)redisUtils.get(subject);

            if (token.equals(redisData.getToken())){
                redisUtils.set(subject,redisData,1L, TimeUnit.DAYS);
            }else{
                throw new AdminRuntimeException(ResponseEnum.USER_NULL);
            }
        }else {
            throw new AdminRuntimeException(ResponseEnum.TOKEN_TIME_ERR);
        }

        return true;
    }
}
