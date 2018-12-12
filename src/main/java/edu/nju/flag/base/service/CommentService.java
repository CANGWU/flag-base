package edu.nju.flag.base.service;

import edu.nju.flag.base.vo.CommentVO;
import edu.nju.flag.base.vo.PageableVO;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * CommentService
 *
 * @author xuan
 * @date 2018/12/10
 */
public interface CommentService {


    /**
     * 添加评论
     * @param userId
     * @param flagId
     * @param content
     * @param toCommentId
     * @return
     */
    Mono<CommentVO> addComment(String userId, String flagId, String content, String toCommentId);


    /**
     * 删除评论
     * @param userId
     * @param commentId
     * @return
     */
    Mono<Boolean> deleteComment(String userId, String commentId);


    /**
     * 分页获取flag下的评论
     * @param flagId
     * @param pageable
     * @return
     */
    Mono<Page<CommentVO>> queryPageCommentByFlagId(String flagId, PageableVO pageable);

    /**
     * 删除评论，管理员使用
     * @param commentId
     * @return
     */
    Mono<Boolean> removeComment(String commentId);


}
