package com.example.demo;

import java.util.List;

import javax.annotation.PostConstruct;

import com.example.demo.model.Project;
import com.example.demo.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolController {

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private StudentRepository studentRepository;

  @PostConstruct
  public void init() {

    Project p1 = new Project("TFG1", 8);
    projectRepository.save(p1);

    Student s1 = new Student("Pepe", 2010);
    s1.setProject(p1);

    Student s2 = new Student("Juan", 2011);

    studentRepository.save(s1);
    studentRepository.save(s2);
  }

  @RequestMapping("/students/")
  public List<Student> getStudents() {
    return studentRepository.findAll();
  }
}
