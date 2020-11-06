package com.lzb.common.utils.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResponseEnum {
    USER_NULL(401,"用户不存在"),
    TOKEN_TIME_ERR(401,"信息已过期，请重新登录"),
    TOKEN_ERR(401,"信息失效，请重新登录"),
    TOKEN_NULL(401,"未登录"),
    LOGIN_OK(200,"登录成功"),
    LOGIN_ERR(406,"账号或密码错误");
    private int code;
    private String message;

}
