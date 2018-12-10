package edu.nju.flag.base.controller;

import com.alibaba.fastjson.JSON;
import edu.nju.flag.base.form.AddCommentForm;
import edu.nju.flag.base.service.CommentService;
import edu.nju.flag.base.utils.OvalValidatorUtils;
import edu.nju.flag.base.vo.CommentVO;
import net.sf.oval.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

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
    public Mono<CommentVO> addComment(@RequestHeader String userId, @RequestBody AddCommentForm addCommentForm){

        List<ConstraintViolation> ret = OvalValidatorUtils.validate(addCommentForm);
        if(!CollectionUtils.isEmpty(ret)){
            return Mono.error(new RuntimeException(JSON.toJSONString(ret)));
        }
        return commentService.addComment(userId, addCommentForm.getFlagId(), addCommentForm.getContent(), addCommentForm.getToCommentId());
    }









}
