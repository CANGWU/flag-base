package edu.nju.flag.base.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * @author xuan
 * @create 2018-12-09 22:39
 **/
@Getter
public enum TaskStatus {

    ONGOING(0, "进行中"),
    FAIL(1, "惹不起惹不起"),
    SUCCESS(2, "轻松搞定");


    private Integer type;
    private String description;


    private TaskStatus(Integer type, String description){
        this.type = type;
        this.description = description;
    }


    public static TaskStatus getFlagStatus(Integer type){
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (Objects.equals(taskStatus.type, type)) {
                return taskStatus;
            }
        }
        return null;
    }


}
