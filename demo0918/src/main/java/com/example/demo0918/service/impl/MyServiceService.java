package com.example.demo0918.service.impl;


import com.example.demo0918.domain.MyService;
import com.example.demo0918.service.ServiceMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class MyServiceService {
    @Autowired
    private ServiceMapper serviceMapper;

    public List<MyService> getAllServices() {
        return serviceMapper.findAll();
    }

    public void addService(MyService service) {
        serviceMapper.insert(service);
    }

    public void deleteService(Long id) {
        serviceMapper.delete(id);
    }
}

