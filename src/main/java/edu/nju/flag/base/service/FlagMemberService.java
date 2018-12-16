package edu.nju.flag.base.service;

import edu.nju.flag.base.vo.PageableVO;
import edu.nju.flag.base.vo.UserVO;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

/**
 * @author xuan
 * @create 2018-12-11 20:54
 **/
public interface FlagMemberService {

    /**
     * 关注一个flag
     * @param userId
     * @param flagId
     * @return
     */
    Mono<Boolean> followFlag(String userId, String flagId);

    /**
     * 给flag点赞
     * @param userId
     * @param flagId
     * @return
     */
    Mono<Boolean> praiseFlag(String userId, String flagId);


    /**
     * 参与一个flag
     * @param userId
     * @param flagId
     * @return
     */
    Mono<Boolean> joinFlag(String userId, String flagId);


    /**
     * 取注一个flag
     * @param userId
     * @param flagId
     * @return
     */
    Mono<Boolean> breakFlag(String userId, String flagId);

    /**
     * 给flag取消点赞
     * @param userId
     * @param flagId
     * @return
     */
    Mono<Boolean> dispraiseFlag(String userId, String flagId);


    /**
     * 退出一个flag
     * @param userId
     * @param flagId
     * @return
     */
    Mono<Boolean> leaveFlag(String userId, String flagId);


    /**
     * 获取flag的参与者
     * @param flagId
     * @param pageable
     * @return
     */
    Mono<Page<UserVO>> queryFlagMember(String flagId, PageableVO pageable);
}
