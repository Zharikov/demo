package com.example.demo.entity;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface CommonRepository<T, ID>
        extends PagingAndSortingRepository<T, ID>, JpaSpecificationExecutor<T> {

}