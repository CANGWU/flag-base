package edu.nju.flag.base.vo;

import edu.nju.flag.base.form.PageableForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;

/**
 * PageableVO
 *
 * @author xuan
 * @date 2018/12/12
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageableVO {

    private Integer pageSize;

    private Integer pageNumber;

    private Sort sort;

    public PageableVO(PageableForm pageableForm){
        BeanUtils.copyProperties(pageableForm, this);
        if(this.pageSize == null){
            this.pageSize = 20;
        }
        if(this.pageNumber == null){
            this.pageSize = 0;
        }
    }
}
