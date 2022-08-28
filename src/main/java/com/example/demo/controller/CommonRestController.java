package com.example.demo.controller;

import com.example.demo.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.response.ResponseBuilder.execute;


/**
 * @param <TO> - transfer object, например, UserTO
 * @param <ID> - тип идентификатора, например, Long
 * @param <S>  - сервис, например, UserService
 */
public abstract class CommonRestController<TO, ID, S extends CommonService<TO, ID>> {
    @Autowired
    protected S service;

    @GetMapping
    public ResponseEntity getAll(Pageable pageable) {
        return execute(() -> service.getAll(pageable)).get();
    }

    @GetMapping("/all")
    public ResponseEntity getAll() {
        return execute(() -> service.getAll()).get();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable ID id) {
        return execute(() -> service.getById(id)).get();
    }


    @PostMapping
    public ResponseEntity create(@RequestBody TO entityTO) {
        return execute(() -> service.create(entityTO)).get();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable ID id, @RequestBody TO entityTO) {
        return execute(() -> service.update(id, entityTO)).get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable ID id) {
        return execute(() -> service.delete(id)).get();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity deleteAll() {
        return execute(() -> service.deleteAll()).get();
    }

}
