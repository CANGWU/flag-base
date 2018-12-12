package edu.nju.flag.base.vo;

import edu.nju.flag.base.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author xuan
 * @create 2018-12-11 20:09
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDetailVO {

    private String id;
    /**
     * 任务描述
     */
    private String description;


    /**
     * 任务类型
     * {@link edu.nju.flag.base.enums.TaskType}
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date createTime;


    private TaskStatusVO taskStatus;

    public TaskDetailVO(Task task){
        BeanUtils.copyProperties(task, this, "taskStatus");
    }
}
