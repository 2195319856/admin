package com.lzb.common.utils.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisData<T> implements Serializable {
    private String token;
    private T data;
}
