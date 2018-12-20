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

    private String id;

    /**
     * 评论的flag Id
     */
    private String flagId;

    /**
     * 评论人
     */
    private UserVO creator;

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
    private String toCommentId;


    public CommentVO(Comment comment, UserVO user){
        BeanUtils.copyProperties(comment, this);
        this.creator = user;
    }

}
