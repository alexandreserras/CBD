package com.jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import redis.clients.jedis.Jedis;
 
public class SimpleMap {
 
	private Jedis jedis;
	public static String USERS = "users3"; // Key set for users' name
	
	public SimpleMap() {
		this.jedis = new Jedis("localhost");
	}
 
	public void saveUser(Map<String,String> map) {
		this.jedis.hmset(USERS, map);
	}
	public Map<String,String> getUserH() {
		return jedis.hgetAll(USERS);
	}
	
	public Set<String> getAllKeys() {
		return jedis.keys("*");
	}
 
	public static void main(String[] args) {
		SimpleMap board = new SimpleMap();
		// set some users
        Map<String,String> map = new HashMap<>();
        map.put("p1", "Ana3");
        map.put("p2", "Maria3");
        map.put("p3", "Pedro3");
        map.put("p4", "Luis3");
		board.saveUser(map);
		board.getAllKeys().stream().forEach(System.out::println);
        Map<String, String> mapa = board.getUserH();
        for (String a : mapa.keySet()){
            System.out.println(map.get(a));
        }

		
        
		
	}
}


