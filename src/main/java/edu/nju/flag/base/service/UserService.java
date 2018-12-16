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
     * @param username
     * @param avatar
     * @return
     */
    Mono<UserVO> register(String code, String username, String avatar);

    /**
     * 删除一个用户，管理员使用
     * @param userId
     * @return
     */
    Mono<Boolean> removeUser(String userId);



}
