package de.denizugurlu.spring.notes.notesservice.repository;

import de.denizugurlu.spring.notes.notesservice.domain.Note;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface NoteRepository extends ReactiveMongoRepository<Note, String> {
}
