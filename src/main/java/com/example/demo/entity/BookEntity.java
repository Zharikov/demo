package com.example.demo.entity;

import javax.persistence.*;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Table(name = "BOOKS")
@Data
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private String isbn;

}
