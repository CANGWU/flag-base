package edu.nju.flag.base.vo;

import edu.nju.flag.base.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

/**
 * CommentVO
 *
 * @author xuan
 * @date 2018/12/10
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentVO {

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


    public CommentVO(Comment comment){
        BeanUtils.copyProperties(comment, this);
    }

}
