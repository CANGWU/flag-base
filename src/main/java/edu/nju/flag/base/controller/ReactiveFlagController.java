package edu.nju.flag.base.controller;

import com.alibaba.fastjson.JSON;
import edu.nju.flag.base.form.*;
import edu.nju.flag.base.service.FlagService;
import edu.nju.flag.base.utils.OvalValidatorUtils;
import edu.nju.flag.base.vo.*;
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
 * ReactiveFlagController
 *
 * @author xuan
 * @date 2018/12/12
 */

@RestController
@RequestMapping("/flag")
public class ReactiveFlagController {


    @Autowired
    FlagService flagService;

    @PostMapping("/create")
    @ApiOperation(value = "创建一个新的flag", response = FlagDetailVO.class)
    public Mono<FlagDetailVO> createFlag(@RequestHeader(USER_ID_IN_HEADER)String userId, @RequestBody CreateFlagForm newFlag){
        List<ConstraintViolation> ret = OvalValidatorUtils.validate(newFlag);
        if(!CollectionUtils.isEmpty(ret)){
            return Mono.error(new RuntimeException(JSON.toJSONString(ret)));
        }
        return flagService.createFlag(userId, new CreateFlagVO(newFlag));
    }

    @PostMapping("/save/{flagId}")
    @ApiOperation(value = "修改flag", response = FlagDetailVO.class)
    public Mono<FlagDetailVO> saveFlag(@RequestHeader(USER_ID_IN_HEADER)String userId, @PathVariable("flagId") String flagId, @RequestBody SaveFlagForm saveFlag){

        List<ConstraintViolation> ret = OvalValidatorUtils.validate(saveFlag);
        if(!CollectionUtils.isEmpty(ret)){
            return Mono.error(new RuntimeException(JSON.toJSONString(ret)));
        }
        return flagService.saveFlag(userId, flagId, new SaveFlagVO(saveFlag));
    }


    @PostMapping("/search/title")
    @ApiOperation(value = "分页根据title模糊搜索flag", response = FlagVO.class)
    public Mono<Page<FlagVO>> searchFlagByTitle(@RequestParam String title, @RequestBody PageableForm pageableForm){

        return flagService.searchFlagByTitle(title, new PageableVO(pageableForm));

    }

    @PostMapping("/my")
    @ApiOperation(value = "分页获取我创建的flag", response = FlagVO.class)
    public Mono<Page<FlagVO>> queryMyFlag(@RequestHeader(USER_ID_IN_HEADER)String userId, @RequestBody PageableForm pageableForm){
        return flagService.queryMyFlag(userId, new PageableVO(pageableForm));
    }

    @PostMapping("/popular")
    @ApiOperation(value = "分页获取热门flag", response = FlagVO.class)
    public Mono<Page<FlagVO>> queryPopularFlag(@RequestBody PageableForm pageableForm){

        return flagService.queryPopularFlag(new PageableVO(pageableForm));

    }

    @PostMapping("/praised")
    @ApiOperation(value = "分页获取我点赞的flag", response = FlagVO.class)
    public Mono<Page<FlagVO>> queryPraisedFlag(@RequestHeader(USER_ID_IN_HEADER)String userId, @RequestBody PageableForm pageableForm){

        return flagService.queryPraisedFlag(userId, new PageableVO(pageableForm));

    }

    @PostMapping("/joined")
    @ApiOperation(value = "分页获取我参与的flag", response = FlagVO.class)
    public Mono<Page<FlagVO>> queryJoinedFlag(@RequestHeader(USER_ID_IN_HEADER)String userId, @RequestBody PageableForm pageableForm){

        return flagService.queryJoinedFlag(userId, new PageableVO(pageableForm));

    }

    @PostMapping("/followed")
    @ApiOperation(value = "分页获取我关注的flag", response = FlagVO.class)
    public Mono<Page<FlagVO>> queryFollowedFlag(@RequestHeader(USER_ID_IN_HEADER)String userId, @RequestBody PageableForm pageableForm){
        return flagService.queryFollowedFlag(userId, new PageableVO(pageableForm));
    }

    @GetMapping("/detail/{flagId}")
    @ApiOperation(value = "获取flag的详情", response = FlagDetailVO.class)
    public Mono<FlagDetailVO> getFlagDetail(@RequestHeader(USER_ID_IN_HEADER)String userId, @PathVariable("flagId") String flagId){
        return flagService.queryFlagById(userId, flagId);
    }

    @PostMapping("/close/{flagId}")
    @ApiOperation(value = "结束一个flag", response = Boolean.class)
    public Mono<Boolean> closeFlag(@RequestHeader(USER_ID_IN_HEADER)String userId,  @PathVariable("flagId") String flagId, @RequestParam Integer flagStatus){
        return flagService.closeFlag(userId, flagId, flagStatus);
    }



}
