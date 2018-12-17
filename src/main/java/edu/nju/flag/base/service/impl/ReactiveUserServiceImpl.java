package edu.nju.flag.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.nju.flag.base.dto.JsCodeResponseDTO;
import edu.nju.flag.base.entity.User;
import edu.nju.flag.base.repository.UserRepository;
import edu.nju.flag.base.service.UserService;
import edu.nju.flag.base.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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


        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxbd6909022b1f69a4&secret=ae6da619060ee5b37a37ac9118ed9902&js_code=" + code + "&grant_type=authorization_code" ;

        String resultJson = restTemplate.getForObject(url, String.class);

        JsCodeResponseDTO jsCodeResponseDTO = JSONObject.parseObject(resultJson, JsCodeResponseDTO.class);

        if(jsCodeResponseDTO == null){
            return Mono.error(new RuntimeException("Error in Connection to Weixin"));
        }
        if(!StringUtils.isEmpty(jsCodeResponseDTO.getErrcode()) && !Objects.equals(jsCodeResponseDTO.getErrcode(), "0")){
            return Mono.error(new RuntimeException(jsCodeResponseDTO.getErrmsg()));
        }

        String openId = jsCodeResponseDTO.getOpenid();
        if(StringUtils.isEmpty(openId)){
            return Mono.error(new RuntimeException("Error in Connection to Weixin"));
        }

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
