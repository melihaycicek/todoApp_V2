package com.todoapp.todoapp.RestApi;

import com.todoapp.todoapp.Entity.Note;
import com.todoapp.todoapp.IBusiness.INoteBuesiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/note")
@CrossOrigin
public class  NoteApi {
    @Autowired
    private INoteBuesiness noteService;
    public NoteApi(INoteBuesiness noteService) {

        this.noteService = noteService;
    }
    @GetMapping("/list")
    public List<Note> get() {
    return this.noteService.getAll();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Note note) {
        this.noteService.delete(note);
    }
    @PostMapping("/add")
    public void add(@RequestBody Note note) {

        this.noteService.add(note);
    }
    @PostMapping("/update")
    public void update(@RequestBody Note note) {
        this.noteService.update(note);
    }
    @GetMapping("/{id}")
    public Optional<Note> getById(@PathVariable String id){
return this.noteService.getById(id);

    }
}
