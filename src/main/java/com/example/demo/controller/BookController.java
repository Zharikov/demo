package com.example.demo.controller;

import com.example.demo.exception.ResponseException;
import com.example.demo.service.BookService;
import com.example.demo.to.BookTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.response.ResponseBuilder.execute;

@RestController
@RequestMapping("/books")
public class BookController extends CommonRestController<BookTO, Long, BookService> {

    @GetMapping("/getBooks")
    ResponseEntity getBooks() throws ResponseException {
        return execute(() -> service.getAll()).get();
    }

    @GetMapping("/getStatistic")
    ResponseEntity getStatistic() throws ResponseException {
       return  execute(() -> service.getStatistic()).get();
    }

    @GetMapping("/getBooksByLetter")
    ResponseEntity getBooksByLetter(@RequestParam Character letter)throws ResponseException {
        return  execute(() -> service.getAllByLetter(Character.toLowerCase(letter))).get();
    }

}
