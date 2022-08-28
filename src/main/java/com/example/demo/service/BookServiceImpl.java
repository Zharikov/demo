package com.example.demo.service;

import com.example.demo.entity.BookEntity;
import com.example.demo.entity.BookRepository;
import com.example.demo.to.BookTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl extends CommonServiceImpl<BookEntity, BookTO, Long, BookRepository>
        implements BookService {

    @Override
    public TreeMap<Character, Long> getStatistic() {
        return getAll()
                .stream()
                .collect(Collectors.groupingBy(BookTO::getFirstLetter, TreeMap::new, Collectors.counting()));
    }

    @Override
    @Cacheable(value = "books", key = "#character")
    public List<BookTO> getAllByLetter(Character character) {
        return getAll().stream()
                .filter(x -> x.getName().toLowerCase(Locale.ROOT).charAt(0) == character)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable("books")
    public List<BookTO> getAll() {
        return super.getAll();
    }

}
