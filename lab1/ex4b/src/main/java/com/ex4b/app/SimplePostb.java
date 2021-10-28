package com.ex4b.app;

import java.util.Set;


import java.util.Set;
import redis.clients.jedis.Jedis;

public class SimplePostb {
    private Jedis jedis;
	private String USERS = "ex4b"; // Key set for users' name
	
	public SimplePostb() {
		this.jedis = new Jedis("localhost");
	}
 
	public void saveUser(String username,int pontos) {
		jedis.zadd(USERS,pontos,username );
		
	}
	public Set<String> getUser(String prefix) {
		return jedis.zrevrangeByScore(USERS,9999999 ,-99999);
	}
	
	public Set<String> getAllKeys() {
		return jedis.keys("*");
	}
}
