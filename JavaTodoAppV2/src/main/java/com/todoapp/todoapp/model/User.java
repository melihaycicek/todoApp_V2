package com.todoapp.todoapp.model;


import java.util.List;
        import java.util.Set;

        import javax.persistence.*;

        import com.fasterxml.jackson.annotation.JsonIgnore;

        import lombok.Getter;
        import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userid;
    private String username;
    private String email;

    private String phone_number;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Todoinfo> todoinfos;

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }



    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public List<Todoinfo> getTodoinfos() {
        return todoinfos;
    }

    public void setTodoinfos(List<Todoinfo> todoinfos) {
        this.todoinfos = todoinfos;
    }




}