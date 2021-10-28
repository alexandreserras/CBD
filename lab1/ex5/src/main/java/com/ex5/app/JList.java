package com.ex5.app;
import java.util.List;
import java.util.Set;
import redis.clients.jedis.Jedis;

public class JList {
    private Jedis jedis;
	public static String USERS = "utilizadores:message:"; // Key set for users' name
	
	public JList() {
		this.jedis = new Jedis("localhost");
	}
 
	public void saveUserL(String username,String message) {
		jedis.lpush(USERS+username,message);
	}
	public List<String> getUserL(String user) {
		return jedis.lrange(USERS+user,0,-1);
	}
	

