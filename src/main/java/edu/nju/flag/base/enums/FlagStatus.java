package edu.nju.flag.base.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * @author xuan
 * @create 2018-12-09 22:39
 **/
@Getter
public enum FlagStatus {

    ONGOING(0, "进行中"),
    FAIL(1, "flag倒了"),
    SUCCESS(2, "flag屹立不倒");


    private Integer type;
    private String description;


    private FlagStatus(Integer type, String description){
        this.type = type;
        this.description = description;
    }


    public static FlagStatus getFlagStatus(Integer type){
        for (FlagStatus flagStatus : FlagStatus.values()) {
            if (Objects.equals(flagStatus.type, type)) {
                return flagStatus;
            }
        }
        return null;
    }


}
