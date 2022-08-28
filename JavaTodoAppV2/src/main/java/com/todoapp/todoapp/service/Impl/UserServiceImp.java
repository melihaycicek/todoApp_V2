package com.todoapp.todoapp.service.Impl;

import com.todoapp.todoapp.model.User;
import com.todoapp.todoapp.repository.UserRepository;
import com.todoapp.todoapp.repository.TodoinfoRepository;
import com.todoapp.todoapp.service.UserService;



import java.util.List;
        import java.util.Optional;

        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.domain.Sort;
        import org.springframework.stereotype.Service;



@Service
public class UserServiceImp implements UserService {


    public final UserRepository userRepository;

    public final TodoinfoRepository todoinfoRepository;

    public UserServiceImp(UserRepository userRepository, TodoinfoRepository todoinfoRepository) {
        this.userRepository=userRepository;
        this.todoinfoRepository=todoinfoRepository;
    }

    @Override
    public Page<User> GetAllPagination(Pageable pageable){
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        Page<User> page= userRepository.findAll(PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "userid")));
        return page;
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public List<User> findByUsername(String name){
        return userRepository.findByUsername(name);
    }

    @Override
    public List<User> findByEmail(String name){
        return userRepository.findByEmail(name);
    }
    @Override
    public Boolean save(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    @Override
    public Optional<User> findById(int userid){
        return userRepository.findById(userid);
    }
    @Override
    public Boolean delete(User user) {
        try {
            todoinfoRepository.deleteAll(user.getTodoinfos());
            user.setTodoinfos(null);
            userRepository.delete(user);

        } catch (Exception e) {
            return false;
        }
        return true;
    }
}