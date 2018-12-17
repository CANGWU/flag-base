package edu.nju.flag.base.form;

import lombok.*;
import net.sf.oval.constraint.NotBlank;

/**
 * @author xuan
 * @create 2018-12-12 23:14
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CloseFlagForm {

    @NonNull
    private Integer flagStatus;
}
