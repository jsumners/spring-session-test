package com.jrfom.webapp.redis;

import java.io.IOException;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.embedded.RedisServer;

public class DevelopmentServer extends RedisServer {
  private static final Logger log = LoggerFactory.getLogger(DevelopmentServer.class);

  public DevelopmentServer(Integer port) throws IOException {
    super(port);
    this.start();
  }

  @PreDestroy
  public void shutdown() {
   if (this.isActive()) {
     try {
       this.stop();
     } catch (InterruptedException e) {
       log.error("Could not stop Redis server: `{}`", e.getMessage());
       log.debug(e.toString());
     }
   }
  }
}