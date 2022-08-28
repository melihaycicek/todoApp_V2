package com.todoapp.todoapp.IBusiness;
import com.todoapp.todoapp.Entity.Note;
import java.util.List;
import java.util.Optional;


public interface INoteBuesiness {
    void add(Note note);
    void update(Note note);
    void delete(Note note);

    List<Note> getAll();

    Optional<Note> getById(String id);

}
