package edu.nju.flag.base.repository;

import edu.nju.flag.base.entity.Flag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * @author xuan
 * @create 2018-12-09 22:59
 **/
public interface FlagRepository extends PagingAndSortingRepository<Flag, String> {


    /**
     * 根据title模糊查询
     * @param title
     * @param pageable
     * @return
     */
    Page<Flag> findFlagsByTitleIsLike(String title, Pageable pageable);




}
