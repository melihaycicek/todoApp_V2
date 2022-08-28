package com.todoapp.todoapp.repository;


        import java.util.List;

        import com.todoapp.todoapp.model.Todoinfo;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import com.todoapp.todoapp.model.User;


@Repository
public interface TodoinfoRepository extends JpaRepository<Todoinfo, Long> {
    List<Todoinfo> findByUser(User user);
    List<Todoinfo> findByContent(String content);
    List<Todoinfo> findByName(String name);
    List<Todoinfo> findByHeader(String header);



}