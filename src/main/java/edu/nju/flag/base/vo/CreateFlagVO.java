package edu.nju.flag.base.vo;

import edu.nju.flag.base.form.CreateFlagForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuan
 * @create 2018-12-11 20:52
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateFlagVO {

    private Integer type;

    private Boolean isPermitJoin;

    private String title;

    private String description;

    private Date startTime;

    private List<CreateTaskVO> tasks;


    public CreateFlagVO(CreateFlagForm newFlag) {

        BeanUtils.copyProperties(newFlag, this, "tasks");

        if(!CollectionUtils.isEmpty(newFlag.getTasks())){

            this.tasks = new ArrayList<>();
        }else {
            this.tasks = newFlag.getTasks().stream().map(CreateTaskVO::new).collect(Collectors.toList());
        }

    }
}
