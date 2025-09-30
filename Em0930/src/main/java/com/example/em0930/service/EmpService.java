package com.example.em0930.service;

import com.example.em0930.entity.Emp;
import com.example.em0930.repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class EmpService {
    private final EmpRepository empRepository;

    public void save(Emp emp) {
        empRepository.save(emp);
    }

    public Emp getById(Long id) {
        return empRepository.findById(id);
    }

    public List<Emp> getAll(){
        return empRepository.findAll();
    }

    public void update(Emp emp) {
        empRepository.save(emp);
    }

    public void delete(Long id) {
        empRepository.delete(id);
    }
}
