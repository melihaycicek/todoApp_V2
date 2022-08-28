package com.todoapp.todoapp.controller;



import java.sql.SQLException;
        import java.util.List;
        import java.util.Map;

import com.todoapp.todoapp.exception.ResourceNotFoundException;
import com.todoapp.todoapp.model.User;
import com.todoapp.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RestController;






@RestController
@RequestMapping("/rest")
public class UserRestController {
    @Autowired
    UserRepository userRepository;

    // Get All Customers
    @PostMapping("/users")
    public List<User> HelloPage() {
        List<User> users = userRepository.findAll();
        return users;
    }

    //http://localhost:8182/rest/user/5
    //http://localhost:8182/rest/user/8

    //API İSTEKLERIN KONTROLU

    @RequestMapping(value = "/user/{userid}", method = RequestMethod.GET)
    public User UserShowPanel(@PathVariable int userid, Map<String, Object> map) throws SQLException {


        return userRepository.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User", "id", userid));
    }


//	@RequestMapping(value = "/user/{userid}", method = RequestMethod.GET)
//	public ResponseEntity<User> CustomerShowPanel(@PathVariable int userid, Map<String, Object> map) throws SQLException {
//		User user = userRepository.findById(user).orElseThrow()get();
//
//		return  ResponseEntity.ok(user);
//	}


}