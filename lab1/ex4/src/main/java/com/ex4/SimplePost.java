package main.java.com.ex4;
import java.util.Set;


import java.util.Set;
import redis.clients.jedis.Jedis;
public class SimplePost {
    private Jedis jedis;
	private String USERS = "Pessoas"; // Key set for users' name
	
	public SimplePost() {
		this.jedis = new Jedis("localhost");
	}
 
	public void saveUser(String username) {
		jedis.zadd(USERS,0, username );
	}
	public Set<String> getUser(String prefix) {
		return jedis.zrangeByLex(USERS, "[" + prefix, "[" + prefix + ((char) 0xFF));
	}
	
	public Set<String> getAllKeys() {
		return jedis.keys("*");
	}
}
