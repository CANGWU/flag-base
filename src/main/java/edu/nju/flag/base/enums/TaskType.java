package edu.nju.flag.base.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * @author xuan
 */

@Getter
public enum TaskType {

    SIGN_IN(0, "打卡类"),
    TARGET(1, "目标类");


    private Integer type;
    private String description;






    private TaskType(Integer type, String description){
        this.type = type;
        this.description = description;
    }


    public static TaskType getFlagType(Integer type){
        for (TaskType taskType : TaskType.values()) {
            if (Objects.equals(taskType.type, type)) {
                return taskType;
            }
        }
            return null;
    }


}
