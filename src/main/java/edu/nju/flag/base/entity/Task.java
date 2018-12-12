package edu.nju.flag.base.entity;

import edu.nju.flag.base.vo.CreateTaskVO;
import edu.nju.flag.base.vo.TaskVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.UUID;

/**
 * Task
 *
 * @author xuan
 * @date 2018/12/10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

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


    public Task(CreateTaskVO newTask) {
        BeanUtils.copyProperties(newTask, this);
        this.id = UUID.randomUUID().toString();
        this.createTime = new Date();

    }
}
