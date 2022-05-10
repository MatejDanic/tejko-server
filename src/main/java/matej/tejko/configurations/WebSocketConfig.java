package matej.tejko.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import matej.tejko.constants.TejkoConstants;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("api/socket")
                .setAllowedOrigins(TejkoConstants.ORIGIN_LOCALHOST, TejkoConstants.ORIGIN_DEFAULT,
                        TejkoConstants.ORIGIN_HEROKU, TejkoConstants.ORIGIN_WWW)
                .setHandshakeHandler(new CustomHandshakeHandler()).withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");

    }
}