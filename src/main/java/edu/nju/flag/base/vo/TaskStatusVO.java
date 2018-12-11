package edu.nju.flag.base.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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


}
