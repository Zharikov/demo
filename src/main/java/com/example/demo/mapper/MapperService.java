package com.example.demo.mapper;

public interface MapperService {
    <S, D> D map(S source, Class<D> destination);
}
