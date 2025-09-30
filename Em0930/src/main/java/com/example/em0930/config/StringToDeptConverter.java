package com.example.em0930.config;

import com.example.em0930.entity.Dept;
import com.example.em0930.repository.DeptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StringToDeptConverter implements Converter<String, Dept> {
    private final DeptRepository deptRepository;

    @Override
    public Dept convert(String source) {
        try{
            Long id = Long.parseLong(source);
            return deptRepository.findById(id);
        }catch(Exception e){
            return null;
        }
    }
}
