package com.lzb.config.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){

        //初始化cors配置对象
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //1.添加需要跨域的域名,如果要携带cookie就不能些*,*代表所有域名
        corsConfiguration.addAllowedOrigin("*");
        //2.设置是否允许携带cookie
        corsConfiguration.setAllowCredentials(true);
        //3.设置请求方式，列如put,get,post,delete.*代表所有
        corsConfiguration.addAllowedMethod("OPTIONS");
        corsConfiguration.addAllowedMethod("HEAD");
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("PUT");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("DELETE");
        corsConfiguration.addAllowedMethod("PATCH");
        //4.设置请求头部信息,*表示允许携带任何头部信息
        corsConfiguration.addAllowedHeader("*");
        //初始化cors配置源对象
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**",corsConfiguration);
        //返回cors的示例，参数：cors的配置源对象
        return new CorsFilter( configurationSource);
    }

}
