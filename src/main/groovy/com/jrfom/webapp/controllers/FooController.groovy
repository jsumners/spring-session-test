package com.jrfom.webapp.controllers

import groovy.json.JsonOutput
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
public class FooController {
  private static final Logger log = LoggerFactory.getLogger(FooController.class)

  @RequestMapping(
      value = "/foo",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  def foo() {
    log.info("Displaying foo")
    return JsonOutput.toJson([foo: "foo"])
  }
}