package matej.tejkogames.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import matej.tejkogames.constants.TejkoGamesConstants;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("api/socket").setAllowedOrigins(TejkoGamesConstants.ORIGIN_LOCALHOST, TejkoGamesConstants.ORIGIN_DEFAULT,
                TejkoGamesConstants.ORIGIN_HEROKU, TejkoGamesConstants.ORIGIN_WWW)
                .setHandshakeHandler(new CustomHandshakeHandler()).withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");

    }
}