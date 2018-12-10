package edu.nju.flag.base.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

/**
 * FlagVO
 *
 * @author xuan
 * @date 2018/12/10
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlagVO {

    private UUID id;

    /**
     * flag类型
     * {@link edu.nju.flag.base.enums.FlagType}
     */
    private Integer type;

    /**
     * 是否允许加入
     */
    private Boolean isPermitJoin;

    /**
     * flag标题
     */
    private String title;
    /**
     * flag描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date finishTime;

    /**
     * flag状态
     * {@link edu.nju.flag.base.enums.FlagStatus}
     */
    private Integer status;

    /**
     * 被点赞次数
     */
    private Integer praiseNum;

    /**
     * 参与成员数量
     */
    private Integer memberNum;

    /**
     * 评论数量
     */
    private Integer commentNum;

    /**
     * 关注数量
     */
    private Integer followNum;


    /**
     *
     */



}
