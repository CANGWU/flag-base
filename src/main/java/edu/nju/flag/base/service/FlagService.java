package edu.nju.flag.base.service;

import edu.nju.flag.base.vo.CreateFlagVO;
import edu.nju.flag.base.vo.FlagDetailVO;
import edu.nju.flag.base.vo.FlagVO;
import edu.nju.flag.base.vo.PageableVO;
import org.springframework.data.domain.Page;
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
     * @param newFlag
     */
    Mono<FlagDetailVO> createFlag(String userId, CreateFlagVO newFlag);


    /**
     * 分页根据title模糊搜索flag
     * @param title
     * @param pageable
     * @return
     */
    Mono<Page<FlagVO>> searchFlagByTitle(String title, PageableVO pageable);

    /**
     * 分页获取热门flag
     * @param pageable
     * @return
     */
    Mono<Page<FlagVO>> queryPopularFlag(PageableVO pageable);

    /**
     * 分页获取我点赞的flag
     * @param userId
     * @param pageable
     * @return
     */
    Mono<Page<FlagVO>> queryPraisedFlag(String userId, PageableVO pageable);

    /**
     * 分页获取我参与的flag
     * @param userId
     * @param pageable
     * @return
     */
    Mono<Page<FlagVO>> queryJoinedFlag(String userId, PageableVO pageable);

    /**
     * 分页获取我关注的flag
     * @param userId
     * @param pageable
     * @return
     */
    Mono<Page<FlagVO>> queryFollowedFlag(String userId, PageableVO pageable);


    /**
     * 获取flag的详情
     * @param userId
     * @param flagId
     * @return
     */
    Mono<FlagDetailVO> queryFlagById(String userId, String flagId);

    /**
     * 关闭flag
     * @param userId
     * @param flagId
     * @param flagStatus {@link edu.nju.flag.base.enums.FlagStatus}
     * @return
     */
    Mono<Boolean> closeFlag(String userId, String flagId, Integer flagStatus);


    /**
     * 删除一个flag，管理员使用
     * @param flag
     * @return
     */
    Mono<Boolean> removeFlag(String flag);




}
