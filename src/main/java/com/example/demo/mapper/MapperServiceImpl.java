package com.example.demo.mapper;

import ma.glasnost.orika.Converter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MapperServiceImpl extends ConfigurableMapper implements MapperService {

    private ApplicationContext applicationContext;

    @Autowired
    public MapperServiceImpl(ApplicationContext context) {
        super(false);

        this.applicationContext = context;
    }

    @PostConstruct
    protected void postConstruct() {
        init();
    }

    @Override
    protected void configure(MapperFactory factory) {
        configureMappers(factory);
        configureConverters(factory);
    }

    @SuppressWarnings("unchecked")
    private void configureMappers(MapperFactory factory) {
        applicationContext.getBeansOfType(Mapper.class).values().forEach(mapper ->
                factory.classMap(mapper.getAType(), mapper.getBType()).byDefault().customize(mapper).register());
    }

    @SuppressWarnings("unchecked")
    private void configureConverters(MapperFactory factory) {
        applicationContext.getBeansOfType(Converter.class).values().forEach(converter ->
                factory.getConverterFactory().registerConverter(converter));
    }
}
