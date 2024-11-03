package com.ruoyi.common.core.Hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.map.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HazelcastCache {


    @Autowired
    private IExecutorService executorService;

    @Autowired
    private HazelcastInstance hazelcastInstance;

    // 存取 IMap 数据
    public void storeData(String key, String value) {
        IMap<String, String> map = hazelcastInstance.getMap("sjm");
        map.put(key, value);
    }

    public String retrieveData(String key) {
        IMap<String, String> map = hazelcastInstance.getMap("sjm");
        String value = map.get(key);
        return value;
    }
}
