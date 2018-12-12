package edu.nju.flag.base.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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



}
