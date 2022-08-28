package com.example.demo.service;


import com.example.demo.exception.ResponseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * @param <TO> - transfer object, например, BookTO
 * @param <ID> - тип идентификатора, например, Long
 */
public interface CommonService<TO, ID> {

    List<TO> getAll() throws ResponseException;

    Page<TO> getAll(Pageable pageable) throws ResponseException;

    TO getById(ID id) throws ResponseException;

    TO create(TO entityTO) throws ResponseException;

    TO update(ID id, TO entityTO) throws ResponseException;

    void delete(ID id) throws ResponseException;

    void deleteAll() throws ResponseException;

    void restore(ID id) throws ResponseException;
}
