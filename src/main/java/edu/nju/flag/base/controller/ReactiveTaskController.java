package edu.nju.flag.base.controller;

import com.alibaba.fastjson.JSON;
import edu.nju.flag.base.form.CloseFlagForm;
import edu.nju.flag.base.form.CreateFlagForm;
import edu.nju.flag.base.form.CreateTaskForm;
import edu.nju.flag.base.service.TaskService;
import edu.nju.flag.base.utils.OvalValidatorUtils;
import edu.nju.flag.base.vo.CreateFlagVO;
import edu.nju.flag.base.vo.CreateTaskVO;
import edu.nju.flag.base.vo.FlagDetailVO;
import edu.nju.flag.base.vo.TaskVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.oval.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

import static edu.nju.flag.base.constants.FlagBaseHeaders.USER_ID_IN_HEADER;

/**
 * @author xuan
 * @create 2018-12-12 22:57
 **/

@RestController
@Slf4j
@RequestMapping("/task")
public class ReactiveTaskController {

    @Autowired
    TaskService taskService;



    @PostMapping("/complete")
    @ApiOperation(value = "完成一项任务", response = Boolean.class)
    public Mono<Boolean> completeTask(@RequestHeader(USER_ID_IN_HEADER)String userId, @RequestParam String flagId, @RequestParam String taskId){

        return taskService.completeTask(userId, flagId, taskId);
    }

    @PostMapping("/disable")
    @ApiOperation(value = "失败一项任务", response = Boolean.class)
    public Mono<Boolean> disableTask(@RequestHeader(USER_ID_IN_HEADER)String userId, @RequestParam String flagId, @RequestParam String taskId){

        return taskService.disableTask(userId, flagId, taskId);
    }


    @PostMapping("/create")
    @ApiOperation(value = "创建一个新的任务", response = TaskVO.class)
    public Mono<TaskVO> createFlag(@RequestHeader(USER_ID_IN_HEADER)String userId, @RequestParam String flagId, @RequestBody CreateTaskForm newTask){
        List<ConstraintViolation> ret = OvalValidatorUtils.validate(newTask);
        if(!CollectionUtils.isEmpty(ret)){
            return Mono.error(new RuntimeException(JSON.toJSONString(ret)));
        }
        return taskService.createTask(userId, flagId, new CreateTaskVO(newTask));
    }


    @PostMapping("/delete")
    @ApiOperation(value = "删除一个任务", response = TaskVO.class)
    public Mono<Boolean> removeFlag(@RequestHeader(USER_ID_IN_HEADER)String userId, @RequestParam String flagId, @RequestParam String taskId){
        return taskService.removeTask(userId, flagId, taskId);
    }
}
