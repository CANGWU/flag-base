package edu.nju.flag.base.service;

import edu.nju.flag.base.vo.UserVO;
import reactor.core.publisher.Mono;

/**
 * UserService
 *
 * @author xuan
 * @date 2018/12/10
 */
public interface UserService {


    /**
     * 注册用户
     * @param code
     * @return
     */
    Mono<UserVO> register(String code);



}
