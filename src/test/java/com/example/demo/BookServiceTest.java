package com.example.demo;

import com.example.demo.entity.BookEntity;
import com.example.demo.entity.BookRepository;
import com.example.demo.exception.ResponseException;
import com.example.demo.service.BookService;
import com.example.demo.to.BookTO;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {
    @MockBean
    BookRepository repository;

    @Autowired
    BookService bookService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testStudentServiceGetAll() throws ResponseException {
        // Моделируем данные в базе данных
        List<BookEntity> books = new ArrayList<>();
        books.add(new BookEntity(1L, "Opening Spaces: An Anthology of Contemporary African Womens Writing", "Yvonne Vera", "9780435910105"));
        books.add(new BookEntity(2L, "The Caine Prize for African Writing 2010: 11th Annual Collection", "The Caine Prize for African Writing",
                "9781906523374"));
        books.add(new BookEntity(3L, "African Folktales", "Roger D. Abrahams", "9780394721170"));
        books.add(new BookEntity(4L, "Unchained Voices: An Anthology of Black Authors in the English-Speaking World of the Eighteenth Century",
                "Vincent Carretta", "9780813190761"));
        books.add(new BookEntity(5L, "Women Writing Africa: West Africa and the Sahel", "Esi Sutherland-Addy", "9781558615007"));
        books.add(new BookEntity(6L, "10 Years of the Caine Prize for African Writing: Plus Coetzee, Gordimer, Achebe, Okri",
                "The Caine The Caine Prize for African Writing", "9781906523244"));
        books.add(new BookEntity(7L, "Introduction to African Oral Literature and Performance", "Bayo Ogunjimi", "9781592211517"));
        books.add(new BookEntity(8L, "Violence in Francophone African and Caribbean Women Literature", "Marie-Chantal Kalisa", "9780803211025"));
        books.add(new BookEntity(9L, "Oral Epics from Africa", "John William Johnson", "9780253211101"));
        books.add(new BookEntity(10L, "African Fundamentalism: A Literary and Cultural Anthology of Garvey Harlem Renaissance", "Tony Martin",
                "9780912469096"));
        books.add(new BookEntity(11L, "Land Apart: A South African Reader", "Various", "9780140100044"));
        books.add(new BookEntity(12L, "Women Writing Africa: The Eastern Region", "Amandina Lihamba", "9781558615342"));
        books.add(new BookEntity(13L, "Nobody Ever Said AIDS: Poems and Stories from Southern Africa", "Nobantu Rasebotsa", "9780795701849"));

        Mockito.when(repository.findAll()).thenReturn(books);

        List<BookTO> result = bookService.getAll();

        TestCase.assertEquals(13, result.size());
        TestCase.assertEquals("Opening Spaces: An Anthology of Contemporary African Womens Writing", result.get(0).getName());
        TestCase.assertEquals("Nobantu Rasebotsa", result.get(12).getAuthor());

    }

    @Test
    public void testStudentServiceGetAllByLetter() throws ResponseException {
        // Моделируем данные в базе данных
        List<BookEntity> books = new ArrayList<>();
        books.add(new BookEntity(1L, "Opening Spaces: An Anthology of Contemporary African Womens Writing", "Yvonne Vera", "9780435910105"));
        books.add(new BookEntity(2L, "The Caine Prize for African Writing 2010: 11th Annual Collection", "The Caine Prize for African Writing",
                "9781906523374"));
        books.add(new BookEntity(3L, "African Folktales", "Roger D. Abrahams", "9780394721170"));
        books.add(new BookEntity(4L, "Unchained Voices: An Anthology of Black Authors in the English-Speaking World of the Eighteenth Century",
                "Vincent Carretta", "9780813190761"));
        books.add(new BookEntity(5L, "Women Writing Africa: West Africa and the Sahel", "Esi Sutherland-Addy", "9781558615007"));
        books.add(new BookEntity(6L, "10 Years of the Caine Prize for African Writing: Plus Coetzee, Gordimer, Achebe, Okri",
                "The Caine The Caine Prize for African Writing", "9781906523244"));
        books.add(new BookEntity(7L, "Introduction to African Oral Literature and Performance", "Bayo Ogunjimi", "9781592211517"));
        books.add(new BookEntity(8L, "Violence in Francophone African and Caribbean Women Literature", "Marie-Chantal Kalisa", "9780803211025"));
        books.add(new BookEntity(9L, "Oral Epics from Africa", "John William Johnson", "9780253211101"));
        books.add(new BookEntity(10L, "African Fundamentalism: A Literary and Cultural Anthology of Garvey Harlem Renaissance", "Tony Martin",
                "9780912469096"));
        books.add(new BookEntity(11L, "Land Apart: A South African Reader", "Various", "9780140100044"));
        books.add(new BookEntity(12L, "Women Writing Africa: The Eastern Region", "Amandina Lihamba", "9781558615342"));
        books.add(new BookEntity(13L, "Nobody Ever Said AIDS: Poems and Stories from Southern Africa", "Nobantu Rasebotsa", "9780795701849"));

        Mockito.when(repository.findAll()).thenReturn(books);

        List<BookTO> result = bookService.getAllByLetter('o');

        TestCase.assertEquals(2, result.size());
        TestCase.assertEquals("Opening Spaces: An Anthology of Contemporary African Womens Writing", result.get(0).getName());
        TestCase.assertEquals("John William Johnson", result.get(1).getAuthor());

    }

    @Test
    public void testStudentServiceGetStatistic() throws ResponseException {
        // Моделируем данные в базе данных
        List<BookEntity> books = new ArrayList<>();
        books.add(new BookEntity(1L, "Opening Spaces: An Anthology of Contemporary African Womens Writing", "Yvonne Vera", "9780435910105"));
        books.add(new BookEntity(2L, "The Caine Prize for African Writing 2010: 11th Annual Collection", "The Caine Prize for African Writing",
                "9781906523374"));
        books.add(new BookEntity(3L, "African Folktales", "Roger D. Abrahams", "9780394721170"));
        books.add(new BookEntity(4L, "Unchained Voices: An Anthology of Black Authors in the English-Speaking World of the Eighteenth Century",
                "Vincent Carretta", "9780813190761"));
        books.add(new BookEntity(5L, "Women Writing Africa: West Africa and the Sahel", "Esi Sutherland-Addy", "9781558615007"));
        books.add(new BookEntity(6L, "10 Years of the Caine Prize for African Writing: Plus Coetzee, Gordimer, Achebe, Okri",
                "The Caine The Caine Prize for African Writing", "9781906523244"));
        books.add(new BookEntity(7L, "Introduction to African Oral Literature and Performance", "Bayo Ogunjimi", "9781592211517"));
        books.add(new BookEntity(8L, "Violence in Francophone African and Caribbean Women Literature", "Marie-Chantal Kalisa", "9780803211025"));
        books.add(new BookEntity(9L, "Oral Epics from Africa", "John William Johnson", "9780253211101"));
        books.add(new BookEntity(10L, "African Fundamentalism: A Literary and Cultural Anthology of Garvey Harlem Renaissance", "Tony Martin",
                "9780912469096"));
        books.add(new BookEntity(11L, "Land Apart: A South African Reader", "Various", "9780140100044"));
        books.add(new BookEntity(12L, "Women Writing Africa: The Eastern Region", "Amandina Lihamba", "9781558615342"));
        books.add(new BookEntity(13L, "Nobody Ever Said AIDS: Poems and Stories from Southern Africa", "Nobantu Rasebotsa", "9780795701849"));

        Mockito.when(repository.findAll()).thenReturn(books);

        TreeMap<Character, Long> result = bookService.getStatistic();

        TreeMap<Character, Long> testValid = new TreeMap();
        testValid.put('1', 1L);
        testValid.put('a', 2L);
        testValid.put('i', 1L);
        testValid.put('l', 1L);
        testValid.put('n', 1L);
        testValid.put('o', 2L);
        testValid.put('t', 1L);
        testValid.put('u', 1L);
        testValid.put('v', 1L);
        testValid.put('w', 2L);

        TestCase.assertEquals(10, result.size());
        TestCase.assertEquals(testValid, result);

    }
}