package edu.nju.flag.base.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.oval.constraint.NotEmpty;

/**
 * RegisterForm
 *
 * @author xuan
 * @date 2018/12/14
 */


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterForm {


    @NotEmpty
    private String code;

    @NotEmpty
    private String username;

    @NotEmpty
    private String avatar;

}
