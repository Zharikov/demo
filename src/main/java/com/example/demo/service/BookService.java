package com.example.demo.service;

import com.example.demo.to.BookTO;

import java.util.List;
import java.util.TreeMap;

public interface BookService extends CommonService<BookTO, Long> {

    TreeMap<Character, Long> getStatistic();
    List<BookTO> getAllByLetter(Character character);

}
