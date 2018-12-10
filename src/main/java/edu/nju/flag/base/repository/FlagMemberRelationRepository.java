package edu.nju.flag.base.repository;

import edu.nju.flag.base.entity.FlagMemberRelation;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * @author xuan
 * @create 2018-12-09 23:01
 **/
public interface FlagMemberRelationRepository extends PagingAndSortingRepository<FlagMemberRelation, UUID> {
}
