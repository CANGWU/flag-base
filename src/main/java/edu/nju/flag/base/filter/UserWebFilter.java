package edu.nju.flag.base.filter;

import edu.nju.flag.base.constants.FlagBaseHeaders;
import edu.nju.flag.base.repository.UserRepository;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;


/**
 * UserWebFilter
 *
 * 验证用户信息
 * @author xuan
 * @date 2018/12/10
 */

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserWebFilter implements WebFilter {

    @Autowired
    UserRepository userRepository;

    private static final String REGISTER_PATH = "/user/register";

    public UserWebFilter(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {

        ServerHttpRequest request = serverWebExchange.getRequest();

        URI uri = request.getURI();


        if(!uri.getPath().contains(REGISTER_PATH)){

            String userId = request.getHeaders().getFirst(FlagBaseHeaders.USER_ID_IN_HEADER);

            if(userId == null || !userRepository.existsById(userId)){
                return Mono.error(new RuntimeException("Need user authorization!"));
            }

        }


        return webFilterChain.filter(serverWebExchange);
    }
}
