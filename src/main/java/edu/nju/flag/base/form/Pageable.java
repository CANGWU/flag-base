package edu.nju.flag.base.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * MyPageable
 *
 * @author xuan
 * @date 2018/12/10
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pageable{


    private Integer pageSize;

    private Integer pageNumber;

    private Sort sort;

}
