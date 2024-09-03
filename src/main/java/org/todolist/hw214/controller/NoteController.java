package org.todolist.hw214.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.todolist.hw214.model.Note;
import org.todolist.hw214.service.NoteService;

import java.util.List;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/list")
    public String listAllNotes(Model model){
        List<Note> allNotes = noteService.listAll();
        model.addAttribute("notes", allNotes);
        return "list-note";
    }

    @GetMapping("/create")
    public String editNote(Model model){
        Note note = new Note();
//        note.setAccess("#{private}");
        model.addAttribute("note", note);
        return "note-form";
    }


    @PostMapping("/save")
    public String addNote(@Valid @ModelAttribute("note") Note note, BindingResult bindingResult){
        if(bindingResult.hasErrors()){

            return "save-error";

        }
        noteService.save(note);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("noteId") Long id, Model model){
        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return "note-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("noteId") Long id){
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/share/{id}")
    public String getPublicNote(@PathVariable Long id, Model model){
        Note note = noteService.getById(id);
        if (note.getAccess().equalsIgnoreCase("private") || note.getAccess().equalsIgnoreCase("приватна")){
            return "access-denied";
        }
        model.addAttribute("note", note);
        return "access-permit";
    }

}
