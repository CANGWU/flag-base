package edu.nju.flag.base.service.impl;

import edu.nju.flag.base.entity.Comment;
import edu.nju.flag.base.repository.CommentRepository;
import edu.nju.flag.base.repository.FlagRepository;
import edu.nju.flag.base.service.CommentService;
import edu.nju.flag.base.vo.CommentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Mono<CommentVO> addComment(String userId, UUID flagId, String content, UUID toCommentId) {

        // 确认flag是否存在
        if(!flagRepository.existsById(flagId)){
            log.error("flag {} is not exist");
            return Mono.error(new RuntimeException("Not such flag!"));
        }

        Comment comment = Comment.builder()
                .id(UUID.randomUUID())
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
    public Mono<CommentVO> deleteComment(String userId, UUID commentId) {
        return Mono.justOrEmpty(new CommentVO(commentRepository.deleteCommentByIdAndUserId(commentId, userId)));
    }

    @Override
    public Flux<CommentVO> queryPageCommentByFlagId(UUID flagId, Pageable pageable) {

        // 确认flag是否存在
        if(!flagRepository.existsById(flagId)){
            log.error("flag {} is not exist");
            return Flux.error(new RuntimeException("Not such flag!"));
        }

        return Flux.fromIterable(commentRepository.findCommentsByFlagId(flagId, pageable).map(CommentVO::new));
    }
}
