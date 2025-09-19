package com.example.demo0918.service;

import com.example.demo0918.domain.MyService;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ServiceMapper {
    @Select("SELECT * FROM services ORDER BY id DESC")
    List<MyService> findAll();

    @Insert("INSERT INTO services(name, description, price) VALUES(#{name}, #{description}, #{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(MyService service);

    @Delete("DELETE FROM services WHERE id = #{id}")
    void delete(Long id);
}
