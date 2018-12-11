package edu.nju.flag.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.nju.flag.base.entity.Comment;
import edu.nju.flag.base.repository.CommentRepository;
import edu.nju.flag.base.repository.FlagRepository;
import edu.nju.flag.base.service.CommentService;
import edu.nju.flag.base.vo.CommentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

/**
 * ReactiveCommentServiceImpl
 *
 * @author xuan
 * @date 2018/12/10
 */

// TODO 2018-12-10 这部分的Reactive写得并不纯粹，有空可以改进一波
@Service
@Slf4j
public class ReactiveCommentServiceImpl implements CommentService{


    @Autowired
    CommentRepository commentRepository;

    @Autowired
    FlagRepository flagRepository;



    @Override
    public Mono<CommentVO> addComment(String userId, String flagId, String content, String toCommentId) {

        // 确认flag是否存在
        if(!flagRepository.existsById(flagId)){
            log.error("flag {} is not exist");
            return Mono.error(new RuntimeException("Not such flag!"));
        }

        Comment comment = Comment.builder()
                .flagId(flagId)
                .userId(userId)
                .commentTime(new Date())
                .praiseNum(0)
                .content(content)
                .toCommentId(toCommentId)
                .build();

        return Mono.justOrEmpty(new CommentVO(commentRepository.save(comment)));
    }

    @Override
    public Mono<Boolean> deleteComment(String userId, String commentId) {
        return Mono.justOrEmpty(commentRepository.deleteCommentByIdAndUserId(commentId, userId) > 0L);
    }

    @Override
    public Mono<Page<CommentVO>> queryPageCommentByFlagId(String flagId, Pageable pageable) {

        // 确认flag是否存在
        if(!flagRepository.existsById(flagId)){
            log.error("flag {} is not exist");
            return Mono.error(new RuntimeException("Not such flag!"));
        }
        return Mono.justOrEmpty(commentRepository.findCommentsByFlagId(flagId, pageable).map(CommentVO::new));
    }

    @Override
    public Mono<Boolean> removeComment(String commentId) {
        commentRepository.deleteById(commentId);
        return Mono.justOrEmpty(Boolean.TRUE);
    }
}
