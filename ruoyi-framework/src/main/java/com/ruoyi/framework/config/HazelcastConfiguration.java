package com.ruoyi.framework.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.ExecutorConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfiguration {

    @Bean
    public Config hazelcastConfig() {
        Config config = new Config();
        config.setClusterName("hazelcast-cluster");

        // Configuring Hazelcast’s task execution thread pool
        ExecutorConfig executorConfig = new ExecutorConfig()
                .setName("hazelcast-executor")
                .setPoolSize(5);  // Thread pool size
        config.addExecutorConfig(executorConfig);

        // Define cache name and settings
        config.addMapConfig(
            new MapConfig()
                .setName("sjm")//Need the same
                .setBackupCount(1) //Set the number of backups
                .setTimeToLiveSeconds(3600) // Cache expiration time
//                .setMaxIdleSeconds(1000) //Maximum data idle time
        );

        return config;
    }

    @Bean
    public IExecutorService hazelcastExecutorService(HazelcastInstance hazelcastInstance) {
        return hazelcastInstance.getExecutorService("hazelcast-executor");
    }
    // TODO 监控 Hazelcast线程池状态,by ExecutorStats
    // TODO 监控线程池饱和情况并设置报警
    // TODO 设置任务超时控制
}
