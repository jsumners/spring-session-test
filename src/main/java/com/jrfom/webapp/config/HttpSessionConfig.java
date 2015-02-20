package com.jrfom.webapp.config;

import java.io.IOException;

import com.jrfom.webapp.redis.DevelopmentServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import redis.clients.jedis.Protocol;
import redis.embedded.RedisServer;

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {
  private static final Logger log = LoggerFactory.getLogger(HttpSessionConfig.class);

  @Bean
  public RedisServer redisServer() {
    RedisServer server = null;

    try {
      server = new DevelopmentServer(Protocol.DEFAULT_PORT);
    } catch (IOException e) {
      log.error("Couldn't create Redis server: `{}`", e.getMessage());
      log.debug(e.toString());
    }

    return server;
  }

  @Bean
  @DependsOn("redisServer")
  public JedisConnectionFactory connectionFactory() {
    return new JedisConnectionFactory();
  }

  @Bean
  public HttpSessionStrategy httpSessionStrategy() {
    return new CookieHttpSessionStrategy();
  }
}