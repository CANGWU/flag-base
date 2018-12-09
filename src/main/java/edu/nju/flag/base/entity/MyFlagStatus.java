package edu.nju.flag.base.entity;

import edu.nju.flag.base.enums.FlagStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

public interface MyFlagStatus {

    /**
     * 打卡类flag进行状态
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SignInFlagStatus{
        /**
         * 打卡记录
         */
        List<Date> signInRecord;

        /**
         * flag状态
         * {@link FlagStatus}
         */
        FlagStatus flagStatus;

    }

    /**
     * 目标类flag进行状态
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TargetFlagStatus{
        /**
         * flag状态
         * {@link FlagStatus}
         */
        FlagStatus flagStatus;

    }




}



