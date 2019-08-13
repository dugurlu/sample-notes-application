package de.denizugurlu.spring.notes.notesservice.configuration;

import de.denizugurlu.spring.notes.notesservice.handler.NotesHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.OPTIONS;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RouterConfiguration {
    static final String API_BASE_URL = "/";

    @Bean
    RouterFunction<ServerResponse> noteRoutes(NotesHandler notesHandler) {
        return route(POST(API_BASE_URL), notesHandler::create);
    }
}
