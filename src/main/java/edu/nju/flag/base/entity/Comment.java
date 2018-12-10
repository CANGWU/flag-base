package edu.nju.flag.base.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

/**
 * 评论实体类
 * @author xuan
 * @create 2018-12-09 22:42
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Comment {

    @Id
    private UUID id;

    /**
     * 评论的flag Id
     */
    private UUID flagId;

    /**
     * 评论人Id
     */
    private String userId;

    /**
     * 评论时间
     */
    private Date commentTime;

    /**
     * 被点赞数
     */
    private Integer praiseNum;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 关联的评论Id
     */
    private UUID toCommentId;




}
