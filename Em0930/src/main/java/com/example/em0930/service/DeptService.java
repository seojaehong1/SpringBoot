package com.example.em0930.service;

import com.example.em0930.entity.Dept;
import com.example.em0930.repository.DeptRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DeptService {
    private final DeptRepository deptRepository;

    public void save(Dept dept) {
        deptRepository.save(dept);
    }

    public List<Dept> getAll() {
        return deptRepository.findAll();
    }

    public Dept getById(Long id){
        return deptRepository.findById(id);
    }
}
