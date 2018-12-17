package edu.nju.flag.base.vo;

import edu.nju.flag.base.form.CreateFlagForm;
import edu.nju.flag.base.form.SaveFlagForm;
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
public class SaveFlagVO {

    private Integer type;

    private Boolean isPermitJoin;

    private String title;

    private String description;


    public SaveFlagVO(SaveFlagForm saveFlag) {
        BeanUtils.copyProperties(saveFlag, this);

    }
}
