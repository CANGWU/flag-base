package edu.nju.flag.base.vo;

import edu.nju.flag.base.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

/**
 * UserVO
 *
 * @author xuan
 * @date 2018/12/10
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {


    private String username;

    private String avatar;


    public UserVO(User user){

        BeanUtils.copyProperties(user, this, "id");


    }


}
