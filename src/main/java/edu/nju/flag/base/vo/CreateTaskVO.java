package edu.nju.flag.base.vo;

import edu.nju.flag.base.form.CreateTaskForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author xuan
 * @create 2018-12-11 21:05
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskVO {

    /**
     * 任务描述
     */
    String description;

    /**
     * 任务类型
     * {@link edu.nju.flag.base.enums.TaskType}
     */
    private Integer type;


    public CreateTaskVO(CreateTaskForm createTaskForm) {

        BeanUtils.copyProperties(createTaskForm, this);
    }
}
