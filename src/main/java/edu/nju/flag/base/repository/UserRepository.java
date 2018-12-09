package edu.nju.flag.base.repository;

import edu.nju.flag.base.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author xuan
 * @create 2018-12-09 22:58
 **/
public interface UserRepository extends CrudRepository<User, String>{

}
