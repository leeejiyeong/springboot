package co.micol.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //메시지 브로커가 지원하는 ‘WebSocket 메시지 처리’를 활성화한다.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	 @Override
	  public void configureMessageBroker(MessageBrokerRegistry config) {
	    config.enableSimpleBroker("/topic");				//구독신청(메시지를 받겠다는 신청)
	    config.setApplicationDestinationPrefixes("/app");	//메시지를 보냄
	  }

	  @Override
	  public void registerStompEndpoints(StompEndpointRegistry registry) {
	    registry.addEndpoint("/chatServer").withSockJS();	//웹소켓 서버 접속요청하는 url
	  }
	  /* configureMessageBroker
	   * 메모리 기반의 Simple Message Broker를 활성화한다. 
	   * 메시지 브로커는 "/subscribe"으로 시작하는 주소의 Subscriber들에게 메시지를 전달하는 역할을 한다.
	   * 이때, 클라이언트가 서버로 메시지 보낼 때 붙여야 하는 prefix를 지정한다. 
	   * 예제에서는 "/publish"로 지정하였다.
	   * 
	   * 
	   * */
}
