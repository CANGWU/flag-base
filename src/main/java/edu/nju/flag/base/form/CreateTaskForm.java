package edu.nju.flag.base.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * @author xuan
 * @create 2018-12-12 23:01
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskForm {


    /**
     * 任务描述
     */
    @NotBlank
    String description;

    /**
     * 任务类型
     * {@link edu.nju.flag.base.enums.TaskType}
     */
    @NotNull
    private Integer type;

}
