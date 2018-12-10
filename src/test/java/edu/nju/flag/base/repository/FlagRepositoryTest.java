package edu.nju.flag.base.repository;

import edu.nju.flag.base.entity.Flag;
import edu.nju.flag.base.entity.Task;
import edu.nju.flag.base.enums.FlagStatus;
import edu.nju.flag.base.enums.FlagType;
import edu.nju.flag.base.enums.TaskStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * FlagRepositoryTest
 *
 * @author xuan
 * @date 2018/12/10
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class FlagRepositoryTest {


    @Autowired
    FlagRepository flagRepository;


    @Test
    public void testInsertFlag(){

        Task task = Task.builder()
                .createTime(new Date())
                .description("渡劫")
                .type(TaskStatus.ONGOING.getType())
                .build();

        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        Flag flag = Flag.builder()
                .userId("xuan")
                .type(FlagType.TARGET.getType())
                .isPermitJoin(Boolean.TRUE)
                .title("shen的flag")
                .description("成为shen")
                .createTime(new Date())
                .status(FlagStatus.ONGOING.getType())
                .praiseNum(0)
                .memberNum(0)
                .commentNum(0)
                .followNum(0)
                .tasks(taskList)
                .build();

        flagRepository.save(flag);

    }

    @Test
    public void findFlag(){

//        Flag flag = flagRepository.findById(UUID.fromString("884d0ca5-1af1-2fc1-4d79-cec6b7296490")).get();

//        System.out.println(flag);

    }


}
