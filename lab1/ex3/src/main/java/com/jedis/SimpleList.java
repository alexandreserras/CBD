package com.jedis;

import java.util.List;
import java.util.Set;
import redis.clients.jedis.Jedis;
 
public class SimpleList {
 
	private Jedis jedis;
	public static String USERS = "users2"; // Key set for users' name
	
	public SimpleList() {
		this.jedis = new Jedis("localhost");
	}
 
	public void saveUserL(String username) {
		jedis.lpush(USERS, username);
	}
	public List<String> getUserL() {
		return jedis.lrange(USERS,0,-1);
	}
	
	public Set<String> getAllKeys() {
		return jedis.keys("*");
	}
 
	public static void main(String[] args) {
		SimpleList board = new SimpleList();
		// set some users
		String[] users = { "Ana", "Pedro", "Maria", "Luis" ,"Ana"};
		for (String user: users) 
			board.saveUserL(user);
		board.getAllKeys().stream().forEach(System.out::println);
		board.getUserL().stream().forEach(System.out::println);
		
	}
}