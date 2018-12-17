package edu.nju.flag.base.controller;

import com.alibaba.fastjson.JSON;
import edu.nju.flag.base.form.RegisterForm;
import edu.nju.flag.base.service.UserService;
import edu.nju.flag.base.utils.OvalValidatorUtils;
import edu.nju.flag.base.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import net.sf.oval.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

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


    @PostMapping("/login")
    @ApiOperation(value = "用户登录，不存在用户顺便注册", response = UserVO.class)
    public Mono<UserVO> login(@RequestBody RegisterForm registerForm){

        List<ConstraintViolation> ret = OvalValidatorUtils.validate(registerForm);
        if(!CollectionUtils.isEmpty(ret)){
            return Mono.error(new RuntimeException(JSON.toJSONString(ret)));
        }
        return userService.login(registerForm.getCode(), registerForm.getUsername(), registerForm.getAvatar());
    }







}
