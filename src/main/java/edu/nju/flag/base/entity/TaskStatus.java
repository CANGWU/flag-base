package edu.nju.flag.base.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

/**
 * @author xuan
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatus {



    private String taskId;

    /**
     * 任务执行状态
     * {@link TaskStatus}
     */
    private Integer status;

    private Date finishTime;




}



