package edu.nju.flag.base.repository;

import edu.nju.flag.base.entity.FlagMemberRelation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * @author xuan
 * @create 2018-12-09 23:01
 **/
public interface FlagMemberRelationRepository extends PagingAndSortingRepository<FlagMemberRelation, String> {


    /**
     * 获取用户与flag的关系
     * @param flagId
     * @param userId
     * @return
     */
    FlagMemberRelation findFlagMemberRelationByFlagIdAndUserId(String flagId, String userId);

    /**
     * 获取关注的数量
     * @param flagId
     * @param isFollow
     * @return
     */
    long countByFlagIdAndIsFollow(String flagId, Boolean isFollow);

    /**
     * 获取参与者的数量
     * @param flagId
     * @param isJoin
     * @return
     */
    long countByFlagIdAndIsJoin(String flagId, Boolean isJoin);

    /**
     * 获取关注的数量
     * @param flagId
     * @param isPraise
     * @return
     */
    long countByFlagIdAndIsPraise(String flagId, Boolean isPraise);



    /**
     * 分页获取用户关注的flag
     * @param userId
     * @param isFollow
     * @param pageable
     * @return
     */
    Page<FlagMemberRelation> findFlagMemberRelationsByUserIdAndIsFollow(String userId, Boolean isFollow, Pageable pageable);

    /**
     * 分页获取用户点赞的flag
     * @param userId
     * @param isPraise
     * @param pageable
     * @return
     */
    Page<FlagMemberRelation> findFlagMemberRelationsByUserIdAndIsPraise(String userId, Boolean isPraise, Pageable pageable);

    /**
     * 分页获取用户加入的flag
     * @param userId
     * @param isJoin
     * @param pageable
     * @return
     */
    Page<FlagMemberRelation> findFlagMemberRelationsByUserIdAndIsJoin(String userId, Boolean isJoin, Pageable pageable);

}
