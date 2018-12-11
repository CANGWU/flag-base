package edu.nju.flag.base.service;

import reactor.core.publisher.Mono;

/**
 * @author xuan
 * @create 2018-12-11 20:54
 **/
public interface FlagMemberService {

    /**
     * 关注一个flag
     * @param userId
     * @param flag
     * @return
     */
    Mono<Boolean> followFlag(String userId, String flag);

    /**
     * 给flag点赞
     * @param userId
     * @param flag
     * @return
     */
    Mono<Boolean> praiseFlag(String userId, String flag);


    /**
     * 参与一个flag
     * @param userId
     * @param flag
     * @return
     */
    Mono<Boolean> joinFlag(String userId, String flag);


    /**
     * 取注一个flag
     * @param userId
     * @param flag
     * @return
     */
    Mono<Boolean> breakFlag(String userId, String flag);

    /**
     * 给flag取消点赞
     * @param userId
     * @param flag
     * @return
     */
    Mono<Boolean> dispraiseFlag(String userId, String flag);


    /**
     * 退出一个flag
     * @param userId
     * @param flag
     * @return
     */
    Mono<Boolean> leaveFlag(String userId, String flag);


}
