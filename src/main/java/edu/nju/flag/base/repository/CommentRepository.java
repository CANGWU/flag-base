package edu.nju.flag.base.repository;

import edu.nju.flag.base.entity.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author xuan
 * @create 2018-12-09 23:01
 **/
public interface CommentRepository extends PagingAndSortingRepository<Comment, String> {
}
