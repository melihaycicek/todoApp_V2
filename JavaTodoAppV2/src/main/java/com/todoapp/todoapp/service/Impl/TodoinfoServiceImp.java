

package com.todoapp.todoapp.service.Impl;

import java.util.List;
        import java.util.Optional;

import com.todoapp.todoapp.model.Todoinfo;
import com.todoapp.todoapp.model.User;
import com.todoapp.todoapp.repository.UserRepository;
import com.todoapp.todoapp.repository.TodoinfoRepository;
import com.todoapp.todoapp.service.TodoinfoService;
import org.springframework.data.domain.Page;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.domain.Sort;
        import org.springframework.stereotype.Service;





@Service
public class TodoinfoServiceImp implements TodoinfoService {


    public final UserRepository userRepository;
    public final TodoinfoRepository todoinfoRepository;

    public TodoinfoServiceImp(UserRepository userRepository, TodoinfoRepository todoinfoRepository) {
        this.userRepository= userRepository;
        this.todoinfoRepository=todoinfoRepository;
    }

    public Page<User> GetAllPagination(Pageable pageable){
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        Page<User> page= userRepository.findAll(PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "userid")));
        return page;
    }

    public List<Todoinfo> findAll(){
        return todoinfoRepository.findAll();
    }

    public List<Todoinfo> findByUser(User user){
        return todoinfoRepository.findByUser(user);
    }

    public Boolean save(Todoinfo todoinfo) {
        try {
            todoinfoRepository.save(todoinfo);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public Optional<Todoinfo> findById(Long id){
        return todoinfoRepository.findById(id);
    }
    public Boolean delete(Todoinfo entity) {
        try {
            entity.setUser(null);
            todoinfoRepository.delete(entity);

        } catch (Exception e) {
            return false;
        }
        return true;
    }
}