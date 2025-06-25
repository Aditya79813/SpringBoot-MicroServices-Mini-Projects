package in.thiru.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisConfig {
	
//			spring.data.redis.url=redis-17489.c89.us-east-1-3.ec2.redns.redis-cloud.com
//			spring.data.redis.port=17489
//			spring.data.redis.username=default
//			spring.data.redis.password=yS9aqKuaAaN52wqKvrxsmCqBn6nNATHI
	
	
	@Value("${spring.data.redis.url}")
	private String url;
	@Value("${spring.data.redis.port}")
	private Integer port;
	@Value("${spring.data.redis.username}")
	private String userName;
	@Value("${spring.data.redis.password}")
	private String password;
	
	
	
	@Bean
	public JedisConnectionFactory getConnection()
	{
		RedisStandaloneConfiguration redisServer=new RedisStandaloneConfiguration(url, port);
		redisServer.setUsername(userName);
		redisServer.setPassword(password);
		
		return new JedisConnectionFactory(redisServer) ;
	}

}
