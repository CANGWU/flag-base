package edu.nju.flag.base.service.impl;

import edu.nju.flag.base.service.UserService;
import edu.nju.flag.base.vo.UserVO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * ReactiveUserServiceImpl
 *
 * @author xuan
 * @date 2018/12/10
 */

@Service
public class ReactiveUserServiceImpl implements UserService{
    @Override
    public Mono<UserVO> register(String code) {
        return null;
    }
}
