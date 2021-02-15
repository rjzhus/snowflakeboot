package com.hello.snowflake.snowflakeboot.service.impl;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.hello.snowflake.snowflakeboot.service.SnowFlakeService;
import com.hello.snowflake.snowflakeboot.util.IdGeneratorSnowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * 多线程雪花算法获取ID
 * 这里多线程使用的是guava的包
 *
 * @author zrj
 * @date 2021/2/15
 * @since V1.0
 **/
@Service
public class SnowFlakeServiceImpl implements SnowFlakeService {
    /**
     * 参数根据实际需要调整，这里只做测试
     */
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat( "adsytfs-pool-%d" ).build();
    private static ExecutorService pool = new ThreadPoolExecutor( 200, 300, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>( 1 )
            , namedThreadFactory, new ThreadPoolExecutor.AbortPolicy() );

    @Autowired
    private IdGeneratorSnowflake idGeneratorSnowflake;

    /**
     * 根据雪花算法获取ID
     *
     * @return
     */
    @Override
    public String getIdBySnowFlake() {
        for (int i = 0; i < 20; i++) {
            pool.execute( () -> {
                long snowflakeId = idGeneratorSnowflake.snowflakeId();
                System.out.println( "多线程获取snowflakeId：" + snowflakeId );
            } );
        }
        // 关闭线程
        pool.shutdown();

        return "snowflake success";
    }
}
