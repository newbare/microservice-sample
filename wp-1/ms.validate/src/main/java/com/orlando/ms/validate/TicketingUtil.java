package com.orlando.ms.validate;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicketingUtil {
	private static final Logger log = LoggerFactory.getLogger(TicketingUtil.class);
	
	private static Jedis jedis;
	
    static {
        jedis = new Jedis("redisstore");
    }
    
    public static Jedis getJedis() {
        try {
            return jedis;
        } catch (JedisConnectionException e) {
        	log.error(e.getMessage());
        }
        return null;
    }
    

}
