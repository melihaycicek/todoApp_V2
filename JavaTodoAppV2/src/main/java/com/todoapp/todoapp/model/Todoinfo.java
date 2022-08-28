package com.todoapp.todoapp.model;







import javax.persistence.*;

        import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "todoinfo")
public class Todoinfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



    @Column(name = "header")
    private String header;


    @Column(name = "date")
    private String date;

    @Column(name = "content")
    private String content;

    @Column(name = "name")
    private String name;




    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    public User user;


    public Todoinfo() {
        super();
    }

    public Todoinfo( String header, String date, String content, User user, String name) {
        super();
        this.header = header;
        this.date = date;
        this.content = content;
        this.user = user;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }






}


/***@Column(name="animal_type")
 @Enumerated(EnumType.STRING)
 private Animals type;
 public Long getAge() {
 return age;
 }
 public void setAge(Long age) {
 this.age = age;
 }*****/
