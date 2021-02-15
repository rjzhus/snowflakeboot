package com.hello.snowflake.snowflakeboot.controller;

import com.hello.snowflake.snowflakeboot.service.SnowFlakeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zrj
 * @date 2021/2/15
 * @since V1.0
 **/
@RestController
public class SnowFlakeController {

    @Resource
    private SnowFlakeService snowFlakeService;

    @GetMapping("/snowflakeId")
    public String getSnowflakeId(){
        return snowFlakeService.getIdBySnowFlake();
    }
}
