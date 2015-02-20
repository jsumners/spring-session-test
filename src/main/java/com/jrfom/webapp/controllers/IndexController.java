package com.jrfom.webapp.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
  private static final Logger log = LoggerFactory.getLogger(IndexController.class);

  @RequestMapping(value = "/")
  public String index(HttpSession session) {
    log.info("Displaying index.html");

    session.setAttribute("foo", "bar");

    return "index";
  }
}