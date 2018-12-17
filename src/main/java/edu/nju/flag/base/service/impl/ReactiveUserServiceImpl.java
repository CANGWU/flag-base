package edu.nju.flag.base.service.impl;

import edu.nju.flag.base.dto.JsCodeResponseDTO;
import edu.nju.flag.base.entity.User;
import edu.nju.flag.base.repository.UserRepository;
import edu.nju.flag.base.service.UserService;
import edu.nju.flag.base.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * ReactiveUserServiceImpl
 *
 * @author xuan
 * @date 2018/12/10
 */

@Service
public class ReactiveUserServiceImpl implements UserService{


    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;


    private static final String BASIC_URL = "";

    @Override
    public Mono<UserVO> login(String code, String username, String avatar) {


        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=" + code + "&grant_type=authorization_code" ;

        JsCodeResponseDTO jsCodeResponseDTO = restTemplate.getForObject(url, JsCodeResponseDTO.class);

        if(jsCodeResponseDTO == null){
            Mono.error(new RuntimeException("Error in Connection to Weixin"));
        }
        if(!Objects.equals(jsCodeResponseDTO.getErrcode(), "0")){
            Mono.error(new RuntimeException(jsCodeResponseDTO.getErrmsg()));
        }

        String openId = jsCodeResponseDTO.getOpenid();

        User user = userRepository.findUserByOpenId(openId);

        if(user == null){
            // 新用户注册
            user = User.builder()
                    .username(username)
                    .avatar(avatar)
                    .openId(jsCodeResponseDTO.getOpenid())
                    .unionId(jsCodeResponseDTO.getUnionid())
                    .sessionKey(jsCodeResponseDTO.getSession_key())
                    .build();
            userRepository.save(user);
            return Mono.justOrEmpty(new UserVO(user, user.getId()));


        }else {
            // 旧用户更新
            user.setUsername(username);
            user.setAvatar(avatar);
            user.setSessionKey(jsCodeResponseDTO.getSession_key());
            userRepository.save(user);
            return Mono.justOrEmpty(new UserVO(user, user.getId()));
        }

    }

    @Override
    public Mono<Boolean> removeUser(String userId) {
        userRepository.deleteById(userId);
        return Mono.just(Boolean.TRUE);
    }
}
