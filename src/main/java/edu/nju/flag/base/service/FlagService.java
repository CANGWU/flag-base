package edu.nju.flag.base.service;

import edu.nju.flag.base.vo.FlagVO;
import reactor.core.publisher.Mono;

/**
 * FlagService
 *
 * @author xuan
 * @date 2018/12/10
 */
public interface FlagService {

    /**
     * 创建一个新的flag
     * @return
     * @param userId
     */
    Mono<FlagVO> createFlag(String userId);

}
