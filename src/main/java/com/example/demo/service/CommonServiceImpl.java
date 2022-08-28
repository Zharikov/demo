package com.example.demo.service;

import com.example.demo.exception.ResponseException;
import com.example.demo.mapper.MapperService;
import com.example.demo.to.AbstractTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.CommonRepository;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @param <E>  - entity, например, BookEntity
 * @param <TO> - transfer object, например, BookTO
 * @param <ID> - тип идентификатора, например, Long
 * @param <R>  - репозиторий, например, BookRepository
 */
public abstract class CommonServiceImpl<E, TO extends AbstractTO, ID, R extends CommonRepository<E, ID>>
        implements CommonService<TO, ID> {

    protected static final String ENTITY_NOT_FOUND = "Запись с таким идентификатором не найдена";
    protected static final String ENTITY_ALREADY_EXISTS = "Запись с таким идентификатором уже существует";

    private static final int E_GENERIC_TYPE_INDEX = 0;
    private static final int TO_GENERIC_TYPE_INDEX = 1;
    protected static final int postgreInt = 32000;

    @Autowired
    protected R repository;

    @Autowired
    protected MapperService mapperService;

    private Class<TO> classForTO;
    private Class<E> classForE;

    @PostConstruct
    private void init() {
        classForTO = getClassForGeneric(TO_GENERIC_TYPE_INDEX);
        classForE = getClassForGeneric(E_GENERIC_TYPE_INDEX);
        configure();
    }

    @SuppressWarnings("unchecked")
    private <G> Class<G> getClassForGeneric(int index) {
        return (Class<G>) getClassForGenericType(getClass(), CommonServiceImpl.class, index);
    }

    public static Class<?> getClassForGenericType(Class<?> targetClass, Class<?> superClass, int index) {
        return GenericTypeResolver.resolveTypeArguments(targetClass, superClass)[index];
    }

    public static Class<?> getClassForGenericType(Class<?> targetClass, Class<?> superClass) {
        return GenericTypeResolver.resolveTypeArgument(targetClass, superClass);
    }

    protected void configure() {
    }

    @Override
    public List<TO> getAll() {
        return constructTOList(repository.findAll());
    }

    @Override
    public Page<TO> getAll(Pageable pageable) throws ResponseException {
        return constructTOPage(repository.findAll(pageable));
    }

    @Override
    public TO getById(ID id) throws ResponseException {
        return constructTO(findById(id));
    }

    @Override
    public TO create(TO entityTO) throws ResponseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public TO update(ID id, TO entityTO) throws ResponseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(ID id) throws ResponseException {
        checkNotExists(id);
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() throws ResponseException {
        repository.deleteAll();
    }

    @Override
    public void restore(ID id) throws ResponseException {
        throw new UnsupportedOperationException();
    }

    protected final E save(E entity, TO entityTO) {
        BeanUtils.copyProperties(entityTO, entity);
        return repository.save(entity);
    }

    protected final E findById(ID id) throws ResponseException {
        return repository.findById(id).orElseThrow(() -> new ResponseException(ENTITY_NOT_FOUND));
    }

    protected final void checkAlreadyExists(ID id) throws ResponseException {
        if (repository.existsById(id)) {
            throw new ResponseException(ENTITY_ALREADY_EXISTS);
        }
    }

    protected final void checkNotExists(ID id) throws ResponseException {
        if (!repository.existsById(id)) {
            throw new ResponseException(ENTITY_NOT_FOUND);
        }
    }

    protected final TO constructTO(E entity) {
        return mapperService.map(entity, classForTO);
    }

    protected final <S, D> D constructTO(S entity, Class<D> clazz) {
        return mapperService.map(entity, clazz);
    }

    protected final List<TO> constructTOList(Iterable<E> entities) {

        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::constructTO)
                .collect(Collectors.toList());
    }

    protected final <S, D> List<D> constructTOList(Iterable<S> entities, Class<D> clazz) {
        return StreamSupport.stream(entities.spliterator(), false).map(entity -> constructTO(entity, clazz))
                .collect(Collectors.toList());
    }

    protected final Page<TO> constructTOPage(Page<E> entitiesPage) {
        return entitiesPage.map(this::constructTO);
    }

    protected final <S, D> Page<D> constructTOPage(Page<S> entitiesPage, Class<D> clazz) {
        return entitiesPage.map(entity -> constructTO(entity, clazz));
    }

}

