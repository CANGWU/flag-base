package edu.nju.flag.base.service.impl;

import edu.nju.flag.base.entity.Flag;
import edu.nju.flag.base.entity.FlagMemberRelation;
import edu.nju.flag.base.entity.Task;
import edu.nju.flag.base.entity.TaskStatus;
import edu.nju.flag.base.enums.FlagStatus;
import edu.nju.flag.base.repository.FlagMemberRelationRepository;
import edu.nju.flag.base.repository.FlagRepository;
import edu.nju.flag.base.service.TaskService;
import edu.nju.flag.base.vo.CreateTaskVO;
import edu.nju.flag.base.vo.TaskVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.util.*;

/**
 * ReactiveTaskService
 *
 * @author xuan
 * @date 2018/12/12
 */

@Slf4j
@Service
public class ReactiveTaskServiceImpl implements TaskService{

    @Autowired
    FlagRepository flagRepository;

    @Autowired
    FlagMemberRelationRepository flagMemberRelationRepository;




    @Override
    public Mono<Boolean> completeTask(String userId, String flagId, String taskId) {

        return  modifyTaskStatus(userId, flagId, taskId, edu.nju.flag.base.enums.TaskStatus.SUCCESS);
    }

    @Override
    public Mono<Boolean> disableTask(String userId, String flagId, String taskId) {

        return  modifyTaskStatus(userId, flagId, taskId, edu.nju.flag.base.enums.TaskStatus.FAIL);
    }


    private Mono<Boolean> modifyTaskStatus(String userId, String flagId, String taskId, edu.nju.flag.base.enums.TaskStatus taskStatusEnum){






        Optional<Flag> flagOptional = flagRepository.findById(flagId);
        if(!flagOptional.isPresent()){
            log.error("flag {} is not exist");
            return Mono.error(new RuntimeException("Not such flag!"));
        }

        Flag flag = flagOptional.get();

        if(!Objects.equals(flag.getStatus(), FlagStatus.ONGOING.getType())){
            log.error("flag {} is finished!");
            return Mono.error(new RuntimeException("Not permit!"));
        }


        FlagMemberRelation flagMember = flagMemberRelationRepository.findFlagMemberRelationByFlagIdAndUserId(flagId, userId);

        if(flagMember == null){
            log.error("user {} does not join flag {}", userId, flagId);
            return Mono.error(new RuntimeException("Not such join record!"));
        }

        List<Task> tasks = flag.getTasks();

        if(CollectionUtils.isEmpty(tasks)){
            log.error("flag {} does not have task {}", flagId, taskId);
            return Mono.error(new RuntimeException("Not such task!"));
        }

        for(Task task : tasks){
            if(Objects.equals(task.getId(), taskId)){
                List<TaskStatus> taskStatuses = flagMember.getMyTaskStatus();
                if(CollectionUtils.isEmpty(taskStatuses)){
                    TaskStatus taskStatus = new TaskStatus(taskId, taskStatusEnum.getType(), new Date());
                    taskStatuses = new ArrayList<>();
                    taskStatuses.add(taskStatus);
                    flagMember.setMyTaskStatus(taskStatuses);
                }else {
                    TaskStatus taskStatus = null;
                    for(TaskStatus tmp: taskStatuses){
                        if(Objects.equals(tmp.getTaskId(), taskId)){
                            taskStatus = tmp;
                            break;
                        }
                    }
                    if(taskStatus == null){
                        taskStatus = new TaskStatus(taskId, taskStatusEnum.getType(), new Date());
                        taskStatuses.add(taskStatus);
                    }else {
                        taskStatus.setStatus(taskStatusEnum.getType());
                        taskStatus.setFinishTime(new Date());
                    }
                }

                flagMemberRelationRepository.save(flagMember);
                return Mono.just(Boolean.TRUE);

            }

        }

        log.error("flag {} does not have task {}", flagId, taskId);
        return Mono.error(new RuntimeException("Not such task!"));

    }



    @Override
    public Mono<TaskVO> createTask(String userId, String flagId, CreateTaskVO newTask) {

        Optional<Flag> flagOptional = flagRepository.findById(flagId);
        if(!flagOptional.isPresent()){
            log.error("flag {} is not exist");
            return Mono.error(new RuntimeException("Not such flag!"));
        }

        Flag flag = flagOptional.get();

        if(!Objects.equals(flag.getStatus(), FlagStatus.ONGOING.getType())){
            log.error("flag {} is finished!");
            return Mono.error(new RuntimeException("Not permit!"));
        }

        Task task = new Task(newTask);

        List<Task> tasks = flag.getTasks();

        if(tasks == null){
            tasks = new ArrayList<>();
            flag.setTasks(tasks);
        }

        tasks.add(task);

        flagRepository.save(flag);

        return Mono.just(new TaskVO(task));
    }

    @Override
    @Deprecated
    public Mono<Boolean> removeTask(String userId, String flagId, String taskId) {
        return Mono.error(new RuntimeException("Not support!"));
    }
}
