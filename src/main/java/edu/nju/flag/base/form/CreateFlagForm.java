package edu.nju.flag.base.form;

import edu.nju.flag.base.vo.CreateTaskVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import java.util.Date;
import java.util.List;

/**
 * @author xuan
 * @create 2018-12-11 20:51
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateFlagForm {

    @NotNull
    private Integer type;

    @NotNull
    private Boolean isPermitJoin;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private Date startTime;

    @NotEmpty
    private List<CreateTaskForm> tasks;
}
