package jh.naverwebtoon.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StompInterceptor implements ChannelInterceptor {

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        switch (accessor.getCommand()) {
            case CONNECT:
                //유저가 websocket으로 connect() 한 뒤 호출됨
                log.info("세션 들어옴 => {}", accessor.getSessionId());

                break;
            case DISCONNECT:
                //유저가 websocket으로 disconnect()한 뒤 호출됨 or 세션이 끊어졌을 때 호출
                log.info("세션 끊음 => {}", accessor.getSessionId());
                break;
            default:
                break;
        }
    }
}
