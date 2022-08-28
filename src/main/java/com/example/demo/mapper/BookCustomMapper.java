package com.example.demo.mapper;

import com.example.demo.entity.BookEntity;
import com.example.demo.to.BookTO;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;


@Component
public class BookCustomMapper extends CustomMapper<BookEntity, BookTO> {

    @Override
    public void mapAtoB(BookEntity book, BookTO bookTO, MappingContext context) {
        bookTO.setAuthor(book.getAuthor());
        bookTO.setIsbn(book.getIsbn());
        bookTO.setName(book.getName());
        bookTO.setId(book.getId());
    }
}
