package edu.nju.flag.base.repository;

import edu.nju.flag.base.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * UserRepositoryTest
 *
 * @author xuan
 * @date 2018/12/10
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTest {



    @Autowired
    CommentRepository commentRepository;



    @Test
    public void testQuery(){
        Page<Comment> comments = commentRepository.findCommentsByFlagId("5c0e2738b0c8e11a952345a1", PageRequest.of(0, 100, Sort.by(Sort.Order.desc("commentTime"))));
        assert comments.getContent().size() > 0;


    }



}
