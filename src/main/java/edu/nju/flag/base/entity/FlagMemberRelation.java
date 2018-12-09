package edu.nju.flag.base.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

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
     * flag状态
     * {@link MyFlagStatus}
     */
    private MyFlagStatus myStatus;


}
