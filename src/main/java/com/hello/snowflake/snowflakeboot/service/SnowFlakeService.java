package com.hello.snowflake.snowflakeboot.service;

/**
 * @author zrj
 * @date 2021/2/15
 * @since V1.0
 **/
public interface SnowFlakeService {
    /**
     * 根据雪花算法获取ID
     *
     * @return
     */
    String getIdBySnowFlake();
}
