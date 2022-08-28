package com.todoapp.todoapp.service;



import java.util.List;
        import java.util.Optional;

        import com.todoapp.todoapp.model.User;
        import com.todoapp.todoapp.model.Todoinfo;



public interface TodoinfoService {
    List<Todoinfo> findAll();
    List<Todoinfo> findByUser(User user);
    Boolean save(Todoinfo todoinfo);
    Optional<Todoinfo> findById(Long id);
    Boolean delete(Todoinfo entity) ;



}