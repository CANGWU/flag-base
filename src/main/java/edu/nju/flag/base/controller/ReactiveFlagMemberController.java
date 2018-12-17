package edu.nju.flag.base.controller;

import com.alibaba.fastjson.JSON;
import edu.nju.flag.base.form.CreateFlagForm;
import edu.nju.flag.base.form.PageableForm;
import edu.nju.flag.base.service.FlagMemberService;
import edu.nju.flag.base.service.FlagService;
import edu.nju.flag.base.utils.OvalValidatorUtils;
import edu.nju.flag.base.vo.CreateFlagVO;
import edu.nju.flag.base.vo.FlagDetailVO;
import edu.nju.flag.base.vo.PageableVO;
import edu.nju.flag.base.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.oval.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

import static edu.nju.flag.base.constants.FlagBaseHeaders.USER_ID_IN_HEADER;

/**
 * @author xuan
 * @create 2018-12-12 22:56
 **/
@RestController
@Slf4j
@RequestMapping("/member")
public class ReactiveFlagMemberController {



    @Autowired
    FlagMemberService flagMemberService;

    @PostMapping("/follow/{flagId}")
    @ApiOperation(value = "关注/取关一个flag", response = Boolean.class)
    public Mono<Boolean> handleFollow(@RequestHeader(USER_ID_IN_HEADER)String userId, @PathVariable("flagId") String flagId, @RequestParam Boolean des){

        if(des){
            return flagMemberService.followFlag(userId, flagId);
        }else {
            return flagMemberService.breakFlag(userId, flagId);
        }

    }

    @PostMapping("/praise/{flagId}")
    @ApiOperation(value = "点赞/取赞一个flag", response = Boolean.class)
    public Mono<Boolean> handlePraise(@RequestHeader(USER_ID_IN_HEADER)String userId, @PathVariable("flagId") String flagId, @RequestParam Boolean des){

        if(des){
            return flagMemberService.praiseFlag(userId, flagId);
        }else {
            return flagMemberService.dispraiseFlag(userId, flagId);
        }

    }

    @PostMapping("/join/{flagId}")
    @ApiOperation(value = "加入/退出一个flag", response = Boolean.class)
    public Mono<Boolean> handleJoin(@RequestHeader(USER_ID_IN_HEADER)String userId, @PathVariable("flagId") String flagId, @RequestParam Boolean des){

        if(des){
            return flagMemberService.joinFlag(userId, flagId);
        }else {
            return flagMemberService.leaveFlag(userId, flagId);
        }

    }

    @PostMapping("/list/{flagId}")
    @ApiOperation(value = "参看flag参与者", response = UserVO.class)
    public Mono<Page<UserVO>> queryFlagMember(@PathVariable("flagId") String flagId, @RequestBody PageableForm pageableForm){

        return flagMemberService.queryFlagMember(flagId, new PageableVO(pageableForm));
    }






}
