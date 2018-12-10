package edu.nju.flag.base.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private UUID id;

    private String username;



}
