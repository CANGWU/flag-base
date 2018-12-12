package edu.nju.flag.base.vo;

import edu.nju.flag.base.entity.Flag;
import edu.nju.flag.base.entity.FlagMemberRelation;
import edu.nju.flag.base.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xuan
 * @create 2018-12-11 20:06
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlagDetailVO {

    private String id;


    private UserVO user;

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
    private List<TaskDetailVO> tasks;

    public FlagDetailVO(Flag flag, FlagMemberRelation flagMemberRelation) {


        BeanUtils.copyProperties(flag, this, "tasks", "userId");

        if(!CollectionUtils.isEmpty(flag.getTasks())){
            this.tasks = flag.getTasks().stream().map(TaskDetailVO::new).collect(Collectors.toList());
        }

        if(flagMemberRelation != null && flagMemberRelation.getIsJoin()){

            List<TaskStatus> taskStatuses = flagMemberRelation.getMyTaskStatus();

            Map<String, TaskStatus> taskStatusMap = taskStatuses == null ? new HashMap<>() : taskStatuses.stream().collect(Collectors.toMap(TaskStatus::getTaskId, i -> i));

            for(TaskDetailVO taskDetail : this.tasks){

                TaskStatus taskStatus = taskStatusMap.get(taskDetail.getId());

                TaskStatusVO taskStatusVO = taskStatus != null ? new TaskStatusVO(taskStatus) : new TaskStatusVO(taskDetail.getId(), edu.nju.flag.base.enums.TaskStatus.ONGOING.getType(), null);

                taskDetail.setTaskStatus(taskStatusVO);
            }



        }
    }
}
