package edu.nju.flag.base.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import java.util.List;

/**
 * @author xuan
 * @create 2018-12-11 20:51
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveFlagForm {

    private Integer type;

    private Boolean isPermitJoin;

    private String title;

    private String description;


}
