package edu.nju.flag.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.nju.flag.base.entity.Comment;
import edu.nju.flag.base.entity.Flag;
import edu.nju.flag.base.entity.User;
import edu.nju.flag.base.repository.CommentRepository;
import edu.nju.flag.base.repository.FlagRepository;
import edu.nju.flag.base.repository.UserRepository;
import edu.nju.flag.base.service.CommentService;
import edu.nju.flag.base.vo.CommentVO;
import edu.nju.flag.base.vo.FlagVO;
import edu.nju.flag.base.vo.PageableVO;
import edu.nju.flag.base.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    UserRepository userRepository;



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

        comment = commentRepository.save(comment);

        Optional<User> userOptional = userRepository.findById(userId);
        UserVO userVO = null;

        if(userOptional.isPresent()){
            userVO = new UserVO(userOptional.get());
        }

        return Mono.justOrEmpty(new CommentVO(comment, userVO));
    }

    @Override
    public Mono<Boolean> deleteComment(String userId, String flagId, String commentId) {
        return Mono.justOrEmpty(commentRepository.deleteCommentByIdAndUserId(commentId, userId) > 0L);
    }

    @Override
    public Mono<Page<CommentVO>> queryPageCommentByFlagId(String flagId, PageableVO pageable) {

        // 确认flag是否存在
        if(!flagRepository.existsById(flagId)){
            log.error("flag {} is not exist");
            return Mono.error(new RuntimeException("Not such flag!"));
        }

        Page<Comment> comments = commentRepository.findCommentsByFlagId(flagId, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort() == null ? Sort.by(Sort.Order.desc("commentTime")): pageable.getSort()));

        List<String> userIds = comments.map(Comment::getUserId).stream().distinct().collect(Collectors.toList());

        Map<String, User> userMap = new HashMap<>();

        if(!CollectionUtils.isEmpty(userIds)){

            List<User> users =  (List)userRepository.findAllById(userIds);

            if(!CollectionUtils.isEmpty(users)){
                userMap = users.stream().collect(Collectors.toMap(User::getId, i -> i, (v1, v2) -> v1));
            }
        }
        Map<String, User> finalUserMap = userMap;
        return Mono.justOrEmpty(comments.map(i -> {
            User user = finalUserMap.get(i.getUserId());
            UserVO userVO = null;
            if(user != null){
                userVO = new UserVO(user);
            }
            return new CommentVO(i, userVO);
        }));
    }

    @Override
    public Mono<Boolean> removeComment(String commentId) {
        commentRepository.deleteById(commentId);
        return Mono.justOrEmpty(Boolean.TRUE);
    }
}
