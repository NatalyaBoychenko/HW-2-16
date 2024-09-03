package org.todolist.hw214.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.todolist.hw214.model.Note;
import org.todolist.hw214.repository.NoteRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public List<Note> listAll(){
        return noteRepository.findAll();
    }

    public Note save(Note note){
        return noteRepository.save(note);
    }

    public void deleteById(long id){
        noteRepository.deleteById(id);
    }

    @Transactional
    public void update(Note note){
        Note savedNote = noteRepository.findById(note.getId()).orElseThrow(NoSuchElementException::new);
        savedNote.setTitle(note.getTitle());
        savedNote.setContent(note.getContent());
        savedNote.setAccess(note.getAccess());
        noteRepository.save(savedNote);

    }

    public Note getById(long id){
        return noteRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
