package org.vben.common.modules.redis.client;

import jakarta.annotation.Resource;
import org.vben.common.base.BaseMap;
import org.vben.common.constant.GlobalConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @Description: redis客户端
 * @author: scott
 * @date: 2020/01/01 16:01
 */
@Configuration
public class JeecgRedisClient {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 发送消息
     *
     * @param handlerName
     * @param params
     */
    public void sendMessage(String handlerName, BaseMap params) {
        params.put(GlobalConstants.HANDLER_NAME, handlerName);
        redisTemplate.convertAndSend(GlobalConstants.REDIS_TOPIC_NAME, params);
    }


}
