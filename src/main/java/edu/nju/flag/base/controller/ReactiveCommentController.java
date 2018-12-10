package edu.nju.flag.base.controller;

import com.alibaba.fastjson.JSON;
import edu.nju.flag.base.form.AddCommentForm;
import edu.nju.flag.base.form.Pageable;
import edu.nju.flag.base.service.CommentService;
import edu.nju.flag.base.utils.OvalValidatorUtils;
import edu.nju.flag.base.vo.CommentVO;
import net.sf.oval.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

import static edu.nju.flag.base.constants.FlagBaseHeaders.USER_ID_IN_HEADER;

/**
 * ReactiveCommentController
 *
 * @author xuan
 * @date 2018/12/10
 */

@RestController
@RequestMapping("/comment")
public class ReactiveCommentController {

    @Autowired
    CommentService commentService;


    @PostMapping("/add")
    public Mono<CommentVO> addComment(@RequestHeader(USER_ID_IN_HEADER)String userId, @RequestBody AddCommentForm addCommentForm){

        List<ConstraintViolation> ret = OvalValidatorUtils.validate(addCommentForm);
        if(!CollectionUtils.isEmpty(ret)){
            return Mono.error(new RuntimeException(JSON.toJSONString(ret)));
        }
        return commentService.addComment(userId, addCommentForm.getFlagId(), addCommentForm.getContent(), addCommentForm.getToCommentId());
    }


    @DeleteMapping("/delete/{commentId}")
    public Mono<Boolean> deleteComment(@RequestHeader(USER_ID_IN_HEADER) String userId, @PathVariable("commentId") String commentId){
        return commentService.deleteComment(userId, commentId);
    }


    @PostMapping("/list/{flagId}")
    public Flux<CommentVO> queryPageCommentByFlagId(@PathVariable("flagId") String flagId, @RequestBody Pageable pageable){
        return commentService.queryPageCommentByFlagId(flagId,
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort() == null ? Sort.by(Sort.Order.desc("commentTime")): pageable.getSort()));

    }







}
