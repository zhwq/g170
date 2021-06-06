package com.g170.web;

import com.g170.service.HelloStarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // @ResponseBody
public class HelloController {

  @Autowired
  HelloStarterService helloStarterService;

  @GetMapping("/hello/{message}")
  public String say(@PathVariable String message) {
    return helloStarterService.sayHello(message);
  }
}
