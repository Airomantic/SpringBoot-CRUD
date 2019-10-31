package com.panda.SpringBootbookCRUD.entity;

import javax.persistence.*;

/**
 * @program: SpringBoot-book-CRUD
 * @description:
 * @author: jiangzq
 * @create: 2019-10-30 21:56
 **/
@Entity
@Table(name = "t_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 100)
    private String name;
    @Column(length = 50)
    private String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
