/**
 * Copyright (C), 2020-2020, code_fusheng
 * FileName: StudentHandler
 * Author:   25610
 * Date:     2020/2/4 22:24
 * Description:
 * History:
 * <author>        <time>      <version>       <desc>
 * 作者姓名       修改时间       版本号         描述
 */
package xyz.fusheng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.entity.Student;
import xyz.fusheng.repository.StudentRepository;

import java.util.List;

@RestController
public class StudentHandler {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/findAll")
    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    @GetMapping("/findById/{id}")
    public Student findById(@PathVariable("id") Long id){
        return studentRepository.findById(id).get();
    }

    @PostMapping("/save")
    public Student save(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @PutMapping("/update")
    public Student update(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id){
        studentRepository.deleteById(id);
    }
}
