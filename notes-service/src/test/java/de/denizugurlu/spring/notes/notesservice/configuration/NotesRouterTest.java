package de.denizugurlu.spring.notes.notesservice.configuration;

import de.denizugurlu.spring.notes.notesservice.domain.Note;
import de.denizugurlu.spring.notes.notesservice.handler.NotesHandler;
import de.denizugurlu.spring.notes.notesservice.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

import static de.denizugurlu.spring.notes.notesservice.configuration.RouterConfiguration.API_BASE_URL;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest
@ContextConfiguration(classes = {RouterConfiguration.class, NotesHandler.class})
class NotesRouterTest {

    @Autowired
    WebTestClient client;

    @MockBean
    NoteRepository notesRepository;

    @Test
    void testCreateValidNote() {
        when(notesRepository.insert(ArgumentMatchers.<Mono<Note>>any()))
                .thenReturn(Flux.just(Note.builder().id("1").text("test-text").created(new Date()).build()));

        client.post()
                .uri(API_BASE_URL)
                .body(BodyInserters.fromPublisher(Mono.just(Note.builder().created(new Date()).text("test-text").build()), Note.class))
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody().jsonPath("$.id").isNotEmpty();
    }
}
