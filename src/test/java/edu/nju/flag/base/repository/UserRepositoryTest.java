package edu.nju.flag.base.repository;

import edu.nju.flag.base.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * UserRepositoryTest
 *
 * @author xuan
 * @date 2018/12/10
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {



    @Autowired
    UserRepository userRepository;



    @Test
    public void testInsertUser(){

        User user = User.builder()
                .id("xuan")
                .username("shen")
                .build();

        userRepository.save(user);


    }



}
