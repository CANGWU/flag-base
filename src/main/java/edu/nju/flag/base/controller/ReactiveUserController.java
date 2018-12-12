package edu.nju.flag.base.controller;

import edu.nju.flag.base.service.UserService;
import edu.nju.flag.base.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * UserController
 *
 * @author xuan
 * @date 2018/12/10
 */

@RestController
@RequestMapping("/user")
public class ReactiveUserController {

    @Autowired
    UserService userService;


    @PostMapping("register")
    public Mono<UserVO> register(@RequestBody String code){
        return userService.register(code);
    }





}
