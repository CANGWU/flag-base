package edu.nju.flag.base.repository;

import edu.nju.flag.base.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author xuan
 * @create 2018-12-09 23:01
 **/
public interface CommentRepository extends PagingAndSortingRepository<Comment, String> {


    /**
     * 分页获取flag下的评论
     * @param flagId
     * @param pageable
     * @return
     */
    Page<Comment> findCommentsByFlagId(String flagId, Pageable pageable);


    /**
     * 删除评论
     * @param id
     * @param userId
     * @return
     */
    Long deleteCommentByIdAndUserId(String id, String userId);


}
