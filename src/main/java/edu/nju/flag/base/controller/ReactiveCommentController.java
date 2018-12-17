package edu.nju.flag.base.controller;

import com.alibaba.fastjson.JSON;
import edu.nju.flag.base.form.AddCommentForm;
import edu.nju.flag.base.form.PageableForm;
import edu.nju.flag.base.service.CommentService;
import edu.nju.flag.base.utils.OvalValidatorUtils;
import edu.nju.flag.base.vo.CommentVO;
import edu.nju.flag.base.vo.FlagDetailVO;
import edu.nju.flag.base.vo.PageableVO;
import io.swagger.annotations.ApiOperation;
import net.sf.oval.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

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


    @PostMapping("/add/{flagId}")
    @ApiOperation(value = "添加新的评论", response = CommentVO.class)
    public Mono<CommentVO> addComment(@RequestHeader(USER_ID_IN_HEADER)String userId, @PathVariable("flagId") String flagId, @RequestBody AddCommentForm addCommentForm){

        List<ConstraintViolation> ret = OvalValidatorUtils.validate(addCommentForm);
        if(!CollectionUtils.isEmpty(ret)){
            return Mono.error(new RuntimeException(JSON.toJSONString(ret)));
        }
        return commentService.addComment(userId, flagId, addCommentForm.getContent(), addCommentForm.getToCommentId());
    }


    @DeleteMapping("/delete/{flagId}/{commentId}")
    @ApiOperation(value = "删除评论", response = Boolean.class)
    public Mono<Boolean> deleteComment(@RequestHeader(USER_ID_IN_HEADER) String userId, @PathVariable("flagId") String flagId, @PathVariable("commentId") String commentId){
        return commentService.deleteComment(userId, flagId, commentId);
    }


    @PostMapping("/list/{flagId}")
    @ApiOperation(value = "分页获取flag下的评论", response = CommentVO.class)
    public Mono<Page<CommentVO>> queryPageCommentByFlagId(@PathVariable("flagId") String flagId, @RequestBody PageableForm pageable){
        return commentService.queryPageCommentByFlagId(flagId, new PageableVO(pageable));

    }







}
