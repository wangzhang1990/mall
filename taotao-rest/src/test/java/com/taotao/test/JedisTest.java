package com.taotao.test;

import java.util.HashSet;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	@Test
	public void jedisDemo1() {
		Jedis jedis = new Jedis("192.168.194.133", 6379);
		String string = jedis.get("key10");
		System.out.println(string);
		// 关闭jedis。
		jedis.close();
	}
	
	@Test
	public void jedisPoolDemo1() {
		JedisPool jedisPool = new JedisPool("192.168.194.133", 6379);
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get("key10");
		System.out.println(string);
		// 关闭jedis。
		jedis.close();
		jedisPool.close();
	}
	
	@Test
	public void jedisClusterDemo() {
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.194.133", 7001));
		nodes.add(new HostAndPort("192.168.194.133", 7002));
		nodes.add(new HostAndPort("192.168.194.133", 7003));
		nodes.add(new HostAndPort("192.168.194.133", 7004));
		nodes.add(new HostAndPort("192.168.194.133", 7005));
		nodes.add(new HostAndPort("192.168.194.133", 7006));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		String string = jedisCluster.get("cluster001");
		System.out.println(string);
		jedisCluster.close();
	}
}
