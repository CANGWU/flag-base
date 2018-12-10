package edu.nju.flag.base.repository;

import edu.nju.flag.base.entity.Flag;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * @author xuan
 * @create 2018-12-09 22:59
 **/
public interface FlagRepository extends PagingAndSortingRepository<Flag, UUID> {



}
