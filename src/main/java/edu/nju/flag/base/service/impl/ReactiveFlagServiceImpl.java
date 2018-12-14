package edu.nju.flag.base.service.impl;

import edu.nju.flag.base.entity.Flag;
import edu.nju.flag.base.entity.FlagMemberRelation;
import edu.nju.flag.base.entity.Task;
import edu.nju.flag.base.enums.FlagStatus;
import edu.nju.flag.base.repository.FlagMemberRelationRepository;
import edu.nju.flag.base.repository.FlagRepository;
import edu.nju.flag.base.service.FlagMemberService;
import edu.nju.flag.base.service.FlagService;
import edu.nju.flag.base.vo.CreateFlagVO;
import edu.nju.flag.base.vo.FlagDetailVO;
import edu.nju.flag.base.vo.FlagVO;
import edu.nju.flag.base.vo.PageableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ReactiveFlagServiceImpl
 *
 * @author xuan
 * @date 2018/12/12
 */
// TODO 2018-12-10 这部分的Reactive写得并不纯粹，有空可以改进一波
@Service
@Slf4j
public class ReactiveFlagServiceImpl implements FlagService{


    @Autowired
    FlagRepository flagRepository;
    @Autowired
    FlagMemberRelationRepository flagMemberRelationRepository;
    @Autowired
    FlagMemberService flagMemberService;

    @Override
    public Mono<FlagDetailVO> createFlag(String userId, CreateFlagVO newFlag) {

        Flag flag = Flag.builder()
                .userId(userId)
                .type(newFlag.getType())
                .isPermitJoin(newFlag.getIsPermitJoin())
                .title(newFlag.getTitle())
                .description(newFlag.getDescription())
                .createTime(new Date())
                .startTime(new Date())
                .status(FlagStatus.ONGOING.getType())
                .praiseNum(0)
                .memberNum(0)
                .commentNum(0)
                .followNum(0)
                .tasks(newFlag.getTasks() == null ? new ArrayList<>() : newFlag.getTasks().stream().map(Task::new).collect(Collectors.toList()))
                .build();

        flag = flagRepository.save(flag);

        Mono<Boolean> joinResult = flagMemberService.joinFlag(userId, flag.getId());

        if(!joinResult.block()){
            log.error("user {} join flag {} failed!", userId, flag.getId());
        }

        return Mono.justOrEmpty(new FlagDetailVO(flag, FlagMemberRelation.builder()
                .isJoin(Boolean.TRUE)
                .build()));
    }

    @Override
    public Mono<Page<FlagVO>> searchFlagByTitle(String title, PageableVO pageable) {
        return Mono.justOrEmpty(flagRepository.findFlagsByTitleIsLike(title, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize())).map(FlagVO::new));
    }

    @Override
    public Mono<Page<FlagVO>> queryPopularFlag(PageableVO pageable) {
        return Mono.justOrEmpty(flagRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Order.desc("memberNum"), Sort.Order.desc("praiseNum"), Sort.Order.desc("createTime")))).map(FlagVO::new));
    }

    @Override
    public Mono<Page<FlagVO>> queryPraisedFlag(String userId, PageableVO pageable) {


        Pageable p = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        Page<FlagMemberRelation> flagMemberRelations = flagMemberRelationRepository.findFlagMemberRelationsByUserIdAndIsPraise(userId, Boolean.TRUE, p);

        List<Flag> flags = (List)flagRepository.findAllById(flagMemberRelations.map(FlagMemberRelation::getFlagId));

        return Mono.justOrEmpty(new PageImpl<>(flags.stream().map(FlagVO::new).collect(Collectors.toList()), p, flagMemberRelations.getTotalElements()));


    }

    @Override
    public Mono<Page<FlagVO>> queryJoinedFlag(String userId, PageableVO pageable) {
        Pageable p = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        Page<FlagMemberRelation> flagMemberRelations = flagMemberRelationRepository.findFlagMemberRelationsByUserIdAndIsJoin(userId, Boolean.TRUE, p);

        List<Flag> flags = (List)flagRepository.findAllById(flagMemberRelations.map(FlagMemberRelation::getFlagId));

        return Mono.justOrEmpty(new PageImpl<>(flags.stream().map(FlagVO::new).collect(Collectors.toList()), p, flagMemberRelations.getTotalElements()));
    }

    @Override
    public Mono<Page<FlagVO>> queryFollowedFlag(String userId, PageableVO pageable) {
        Pageable p = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        Page<FlagMemberRelation> flagMemberRelations = flagMemberRelationRepository.findFlagMemberRelationsByUserIdAndIsFollow(userId, Boolean.TRUE, p);

        List<Flag> flags = (List)flagRepository.findAllById(flagMemberRelations.map(FlagMemberRelation::getFlagId));

        return Mono.justOrEmpty(new PageImpl<>(flags.stream().map(FlagVO::new).collect(Collectors.toList()), p, flagMemberRelations.getTotalElements()));
    }

    @Override
    public Mono<FlagDetailVO> queryFlagById(String userId, String flagId) {

        Optional<Flag> flagOptional = flagRepository.findById(flagId);

        if(!flagOptional.isPresent()){
            log.error("flag {} is not exist");
            return Mono.error(new RuntimeException("Not such flag!"));
        }

        Flag flag = flagOptional.get();

        FlagMemberRelation flagMemberRelation = flagMemberRelationRepository.findFlagMemberRelationByFlagIdAndUserId(flagId, userId);

        FlagDetailVO flagDetailVO = new FlagDetailVO(flag, flagMemberRelation);


        return Mono.justOrEmpty(flagDetailVO);
    }

    @Override
    public Mono<Boolean> closeFlag(String userId, String flagId, Integer flagStatus) {

        Optional<Flag> flagOptional = flagRepository.findById(flagId);

        if(!flagOptional.isPresent()){
            log.error("flag {} is not exist");
            return Mono.error(new RuntimeException("Not such flag!"));
        }

        Flag flag = flagOptional.get();


        if(!Objects.equals(flag.getUserId(), userId)){
            log.error("user {} can not operate flag {}!", userId, flagId);
            return Mono.error(new RuntimeException("Not permit!"));
        }

        FlagStatus fs = FlagStatus.getFlagStatus(flagStatus);

        if(fs == null || Objects.equals(fs,FlagStatus.ONGOING)){
            log.error("flag {} can not close!", userId, flagId);
            return Mono.error(new RuntimeException("Not permit!"));
        }

        flag.setFinishTime(new Date());
        flag.setStatus(flagStatus);

        flagRepository.save(flag);

        return Mono.just(Boolean.TRUE);
    }

    @Override
    public Mono<Boolean> removeFlag(String flag) {

        flagRepository.deleteById(flag);
        return Mono.just(Boolean.TRUE);

    }

    @Override
    public Mono<Page<FlagVO>> queryMyFlag(String userId, PageableVO pageable) {
        return Mono.justOrEmpty(flagRepository.findFlagsByUserId(userId, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize())).map(FlagVO::new));
    }
}
