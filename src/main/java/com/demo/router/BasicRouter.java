package com.demo.router;

import com.demo.handler.BasicHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class BasicRouter {

    @Bean
    public RouterFunction<ServerResponse> route(BasicHandler basicHandler){

        return RouterFunctions
                .route(GET("/api/v1/basic/functional/flux").and(accept(MediaType.APPLICATION_JSON)),basicHandler::flux)
                .andRoute(GET("/api/v1/basic/functional/mono").and(accept(MediaType.APPLICATION_JSON)),basicHandler::mono);

    }


}
