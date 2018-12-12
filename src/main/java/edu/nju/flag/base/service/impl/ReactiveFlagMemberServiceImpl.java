package edu.nju.flag.base.service.impl;

import edu.nju.flag.base.entity.Flag;
import edu.nju.flag.base.entity.FlagMemberRelation;
import edu.nju.flag.base.entity.Task;
import edu.nju.flag.base.enums.FlagStatus;
import edu.nju.flag.base.repository.FlagMemberRelationRepository;
import edu.nju.flag.base.repository.FlagRepository;
import edu.nju.flag.base.service.FlagMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

/**
 * ReactiveFlagMemberService
 *
 * @author xuan
 * @date 2018/12/12
 */
// TODO 2018-12-12 这部分的Reactive写得并不纯粹，有空可以改进一波
@Service
@Slf4j
public class ReactiveFlagMemberServiceImpl implements FlagMemberService{

    @Autowired
    FlagRepository flagRepository;

    @Autowired
    FlagMemberRelationRepository flagMemberRelationRepository;

    @Override
    public Mono<Boolean> followFlag(String userId, String flagId) {
        // 确认flag是否存在
        Optional<Flag> flagOptional = flagRepository.findById(flagId);
        if(!flagOptional.isPresent()){
            log.error("flag {} is not exist");
            return Mono.error(new RuntimeException("Not such flag!"));
        }

        Flag flag = flagOptional.get();

        FlagMemberRelation flagMember = flagMemberRelationRepository.findFlagMemberRelationByFlagIdAndUserId(flagId, userId);

        if(flagMember == null){

            flagMember = FlagMemberRelation.builder()
                    .flagId(flagId)
                    .userId(userId)
                    .isFollow(Boolean.TRUE)
                    .isJoin(Boolean.FALSE)
                    .isPraise(Boolean.FALSE)
                    .myTaskStatus(new ArrayList<>())
                    .build();
        }else {
            flagMember.setIsFollow(Boolean.TRUE);
        }

        flagMemberRelationRepository.save(flagMember);

        long count = flagMemberRelationRepository.countByFlagIdAndIsFollow(flagId, Boolean.TRUE);
        flag.setFollowNum((int) count);
        flagRepository.save(flag);

        return Mono.justOrEmpty(true);
    }

    @Override
    public Mono<Boolean> praiseFlag(String userId, String flagId) {
        // 确认flag是否存在
        Optional<Flag> flagOptional = flagRepository.findById(flagId);
        if (!flagOptional.isPresent()) {
            log.error("flag {} is not exist");
            return Mono.error(new RuntimeException("Not such flag!"));
        }

        Flag flag = flagOptional.get();

        FlagMemberRelation flagMember = flagMemberRelationRepository.findFlagMemberRelationByFlagIdAndUserId(flagId, userId);

        if (flagMember == null) {

            flagMember = FlagMemberRelation.builder()
                    .flagId(flagId)
                    .userId(userId)
                    .isFollow(Boolean.FALSE)
                    .isJoin(Boolean.FALSE)
                    .isPraise(Boolean.TRUE)
                    .myTaskStatus(new ArrayList<>())
                    .build();
        } else {
            flagMember.setIsPraise(Boolean.TRUE);
        }

        flagMemberRelationRepository.save(flagMember);


        long count = flagMemberRelationRepository.countByFlagIdAndIsPraise(flagId, Boolean.TRUE);
        flag.setPraiseNum((int) count);
        flagRepository.save(flag);

        return Mono.justOrEmpty(true);
    }
    @Override
    public Mono<Boolean> joinFlag(String userId, String flagId) {
        // 确认flag是否存在
        Optional<Flag> flagOptional = flagRepository.findById(flagId);
        if(!flagOptional.isPresent()){
            log.error("flag {} is not exist");
            return Mono.error(new RuntimeException("Not such flag!"));
        }

        Flag flag = flagOptional.get();

        if(!Objects.equals(flag.getStatus(), FlagStatus.ONGOING.getType())){
            log.error("flag {} is finished!");
            return Mono.error(new RuntimeException("Not permit!"));
        }

        FlagMemberRelation flagMember = flagMemberRelationRepository.findFlagMemberRelationByFlagIdAndUserId(flagId, userId);

        if(flagMember == null){

            flagMember = FlagMemberRelation.builder()
                    .flagId(flagId)
                    .userId(userId)
                    .isFollow(Boolean.FALSE)
                    .isJoin(Boolean.TRUE)
                    .isPraise(Boolean.FALSE)
                    .myTaskStatus(new ArrayList<>())
                    .build();
        }else {
            flagMember.setIsJoin(Boolean.TRUE);


        }

        flagMemberRelationRepository.save(flagMember);

        long count = flagMemberRelationRepository.countByFlagIdAndIsJoin(flagId, Boolean.TRUE);
        flag.setMemberNum((int) count);
        flagRepository.save(flag);

        return Mono.justOrEmpty(true);
    }

    @Override
    public Mono<Boolean> breakFlag(String userId, String flagId) {
        // 确认flag是否存在
        Optional<Flag> flagOptional = flagRepository.findById(flagId);
        if(!flagOptional.isPresent()){
            log.error("flag {} is not exist");
            return Mono.error(new RuntimeException("Not such flag!"));
        }

        Flag flag = flagOptional.get();

        FlagMemberRelation flagMember = flagMemberRelationRepository.findFlagMemberRelationByFlagIdAndUserId(flagId, userId);

        if(flagMember == null){
            log.error("user {} does not follow flag {}", userId, flagId);
            return Mono.error(new RuntimeException("Not such follow record!"));
        }else {
            flagMember.setIsFollow(Boolean.FALSE);
        }

        flagMemberRelationRepository.save(flagMember);

        long count = flagMemberRelationRepository.countByFlagIdAndIsFollow(flagId, Boolean.TRUE);
        flag.setFollowNum((int) count);
        flagRepository.save(flag);

        return Mono.justOrEmpty(true);
    }

    @Override
    public Mono<Boolean> dispraiseFlag(String userId, String flagId) {
        // 确认flag是否存在
        Optional<Flag> flagOptional = flagRepository.findById(flagId);
        if(!flagOptional.isPresent()){
            log.error("flag {} is not exist");
            return Mono.error(new RuntimeException("Not such flag!"));
        }

        Flag flag = flagOptional.get();

        FlagMemberRelation flagMember = flagMemberRelationRepository.findFlagMemberRelationByFlagIdAndUserId(flagId, userId);

        if(flagMember == null){
            log.error("user {} does not praise flag {}", userId, flagId);
            return Mono.error(new RuntimeException("Not such praise record!"));
        }else {
            flagMember.setIsPraise(Boolean.FALSE);
        }

        flagMemberRelationRepository.save(flagMember);

        long count = flagMemberRelationRepository.countByFlagIdAndIsPraise(flagId, Boolean.TRUE);
        flag.setPraiseNum((int) count);
        flagRepository.save(flag);

        return Mono.justOrEmpty(true);
    }

    @Override
    public Mono<Boolean> leaveFlag(String userId, String flagId) {
        // 确认flag是否存在
        Optional<Flag> flagOptional = flagRepository.findById(flagId);
        if(!flagOptional.isPresent()){
            log.error("flag {} is not exist");
            return Mono.error(new RuntimeException("Not such flag!"));
        }

        Flag flag = flagOptional.get();

        FlagMemberRelation flagMember = flagMemberRelationRepository.findFlagMemberRelationByFlagIdAndUserId(flagId, userId);

        if(flagMember == null){
            log.error("user {} does not join flag {}", userId, flagId);
            return Mono.error(new RuntimeException("Not such join record!"));
        }else {
            flagMember.setIsJoin(Boolean.FALSE);
            flagMember.setMyTaskStatus(new ArrayList<>());
        }

        flagMemberRelationRepository.save(flagMember);

        long count = flagMemberRelationRepository.countByFlagIdAndIsJoin(flagId, Boolean.TRUE);
        flag.setMemberNum((int) count);
        flagRepository.save(flag);

        return Mono.justOrEmpty(true);
    }
}
