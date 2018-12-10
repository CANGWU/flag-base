package edu.nju.flag.base.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import java.util.UUID;

/**
 * AddCommentForm
 *
 * @author xuan
 * @date 2018/12/10
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentForm {


    @NotBlank
    private String content;

    @NotNull
    private UUID flagId;

    private UUID toCommentId;


}
