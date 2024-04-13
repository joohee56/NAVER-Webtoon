package jh.naverwebtoon.config;

import jh.naverwebtoon.web.interceptor.StompInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer  {

    private final StompInterceptor stompInterceptor;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket")    //클라이언트와 handshake 할 경로 (커넥션 연결)
                .setAllowedOriginPatterns("*")      //CORS
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/send");  //메시지 브로커 활성화, 발행자가 /send로 메시지를 전달하면 브로커가 /send를 구독하는 구독자들에게 전달
//        registry.setApplicationDestinationPrefixes("/app");  //클라이언트에서 발생한 메시지 중 destination이 해당 경로(/app)으로 시작하는 메시지를 메시지 브로커에서 처리한다.
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        //connect / disconnect 인터셉터
        registration.interceptors(stompInterceptor);
    }
}
