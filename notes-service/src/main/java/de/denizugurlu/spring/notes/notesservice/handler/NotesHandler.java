package de.denizugurlu.spring.notes.notesservice.handler;

import de.denizugurlu.spring.notes.notesservice.domain.Note;
import de.denizugurlu.spring.notes.notesservice.repository.NoteRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.created;

@Component
public class NotesHandler {

    private NoteRepository noteRepository;

    public NotesHandler(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        Mono<Note> note = serverRequest.bodyToMono(Note.class);

        return noteRepository.insert(note).next()
                .flatMap(savedNote -> created(UriComponentsBuilder.fromPath("/notes/{id}").buildAndExpand(savedNote.getId()).toUri())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(fromObject(savedNote)));
    }
}
