package com.todoapp.todoapp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy  ="org.hibernate.id.UUIDGenerator")

    @Column(name = "id")
    private String id;
    @Column(name = "header")
    private String header;
    @Column(name = "content")
    private String content;
    @Column(name = "tarih")
    private Date date;

}


