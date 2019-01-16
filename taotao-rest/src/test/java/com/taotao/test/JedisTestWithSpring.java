package com.taotao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taotao.rest.dao.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class JedisTestWithSpring {
	@Autowired
	private JedisPool jedisPool;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@Autowired
	private JedisClient jedisClient;
	
	
	@Test
	public void demo1() {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get("key121");
		System.out.println(string);
		jedis.close();
	}
	
	@Test
	public void demo2() {
		
		String string = jedisCluster.get("cluster001");
		System.out.println(string);
		jedisCluster.close();
	}
	
	@Test
	public void demo3() {
		String string = jedisClient.get("key121");
		System.out.println(string);
		jedisCluster.close();
	}
}
