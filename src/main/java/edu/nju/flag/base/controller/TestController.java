package edu.nju.flag.base.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author xuan
 * @create 2018-12-08 23:17
 **/
@RestController
public class TestController {


    @GetMapping("/netty/hello")
    public Mono<String> nettyHello() {
        return Mono.just("Welcome to reactive world!");
    }

    @GetMapping("rest/hello")
    public String restHello(){
        return "Welcome to rest world!";

    }






}
