package com.todoapp.todoapp.controller;


import java.sql.SQLException;
        import java.util.List;
        import java.util.Map;

import com.todoapp.todoapp.model.Todoinfo;
import com.todoapp.todoapp.model.User;
import com.todoapp.todoapp.repository.TodoinfoRepository;
import com.todoapp.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rest")
public class TodoinfoRestController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TodoinfoRepository todoinfoRepository;


    @RequestMapping(value = "/todoinfos/{userid}", method = RequestMethod.GET)
    public ResponseEntity<List<Todoinfo>> findAllTodoinfos(@PathVariable int userid, Map<String, Object> map) throws SQLException {

        User user = userRepository.findById(userid).get();
        List<Todoinfo> todoinfos = todoinfoRepository.findByUser(user);
        return  ResponseEntity.ok(todoinfos);


    }


}