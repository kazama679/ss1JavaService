package com.ra.demo0707.controller;

import com.ra.demo0707.entity.Student;
import com.ra.demo0707.repo.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public String hello(Model model) {
        model.addAttribute("list", StudentRepository.getStudents());
        model.addAttribute("bai3", "Hello, Spring Boot API!");
        return "home";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("student", new Student());
        return "add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("student") Student student) {
        StudentRepository.addStudent(student);
        return "redirect:/hello";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        Student student = StudentRepository.getStudentById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "edit";
        }
        return "redirect:/hello";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("student") Student student) {
        if (StudentRepository.updateStudent(student)) {
            return "redirect:/hello";
        }
        return "redirect:/hello";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        StudentRepository.deleteStudent(id);
        return "redirect:/hello";
    }
}
