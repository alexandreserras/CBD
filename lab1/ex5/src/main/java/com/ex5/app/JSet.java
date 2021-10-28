package com.ex5.app;

import java.util.Set;
import redis.clients.jedis.Jedis;
 
public class JSet {
 
	private Jedis jedis;
	public static String USERS = "utilizadores"; // Key set for users' name
	
	public JSet() {
		this.jedis = new Jedis("localhost");
	}
 
	public void saveUser(String username) {
		jedis.sadd(USERS, username);
	}
	public void followUser(String user,String username) {
		jedis.sadd(USERS+":subscrições:"+user, username);
	}
	public Set<String> getUser() {
		return jedis.smembers(USERS);
	}
	public Set<String> getFollows(String user) {
		return jedis.smembers(USERS+":subscrições:"+user);
	}
	
	

	public void unfollowUser(String user, String follow) {
		 jedis.srem(USERS+":subscrições:"+user, follow);
	}
}
 