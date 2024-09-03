package org.todolist.hw214.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.todolist.hw214.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
