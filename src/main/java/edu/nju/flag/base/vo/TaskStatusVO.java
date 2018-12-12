package edu.nju.flag.base.vo;

import edu.nju.flag.base.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author xuan
 * @create 2018-12-11 20:34
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatusVO {


    private String taskId;

    private Integer status;

    private Date finishTime;

    public TaskStatusVO(TaskStatus taskStatus){
        BeanUtils.copyProperties(taskStatus, this);
    }


}
