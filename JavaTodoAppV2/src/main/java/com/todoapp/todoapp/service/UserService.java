package com.todoapp.todoapp.service;



import java.util.List;
import java.util.Optional;

import com.todoapp.todoapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;




public interface UserService {

    public Page<User> GetAllPagination(Pageable pageable);
    public List<User> findAll();
    public List<User> findByUsername(String username);
    public List<User> findByEmail(String email);
    public Boolean save(User user) ;
    public Optional<User> findById(int userid);
    public Boolean delete(User user);
}