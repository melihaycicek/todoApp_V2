package com.todoapp.todoapp.IData;
import com.todoapp.todoapp.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INoteData extends JpaRepository<Note, String> {
 }
