package edu.nju.flag.base.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

/**
 * flag与用户关系实体类
 * @author xuan
 * @create 2018-12-09 22:48
 **/

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlagMemberRelation {

    @Id
    private String id;

    /**
     * 关联的flag Id
     */
    private String flagId;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 是否关注
     */
    private Boolean isFollow;

    /**
     * 是否参与
     */
    private Boolean isJoin;

    /**
     * 是否点赞
     */
    private Boolean isPraise;

    /**
     * 用户任务执行状态
     */
    private List<TaskStatus> myTaskStatus;


}
