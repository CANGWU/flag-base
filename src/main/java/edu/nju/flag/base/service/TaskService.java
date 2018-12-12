package edu.nju.flag.base.service;

import edu.nju.flag.base.vo.CreateTaskVO;
import edu.nju.flag.base.vo.TaskVO;
import reactor.core.publisher.Mono;

/**
 * @author xuan
 * @create 2018-12-11 21:02
 **/
public interface TaskService {


    /**
     * 完成一个任务
     * @param userId
     * @param flagId
     * @param taskId
     * @return
     */
    Mono<Boolean> completeTask(String userId, String flagId, String taskId);


    /**
     * 放弃一个任务
     * @param userId
     * @param flagId
     * @param taskId
     * @return
     */
    Mono<Boolean> disableTask(String userId, String flagId, String taskId);


    /**
     * 创建一个新的任务
     * @param userId
     * @param flagId
     * @param newTask
     * @return
     */
    Mono<TaskVO> createTask(String userId, String flagId, CreateTaskVO newTask);

    /**
     * 删除一个任务
     * @param userId
     * @param flagId
     * @param taskId
     * @return
     */
    Mono<Boolean> removeTask(String userId, String flagId, String taskId);



}
