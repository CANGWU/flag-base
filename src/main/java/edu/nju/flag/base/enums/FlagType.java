package edu.nju.flag.base.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum FlagType {

    ADDRESS_SIGN_IN(0, "地点打卡类"),
    SUBJECTIVE_SIGN_IN(1, "主观打卡类"),
    TARGET(2, "目标类");


    private Integer type;
    private String description;






    private FlagType(Integer type, String description){
        this.type = type;
        this.description = description;
    }


    public static FlagType getFlagType(Integer type){
        for (FlagType flagType : FlagType.values()) {
            if (Objects.equals(flagType.type, type)) {
                return flagType;
            }
        }
            return null;
    }


}
