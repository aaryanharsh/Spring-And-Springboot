package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/api")
public class StudentRestController {
    //
    private List<Student> theStudents;
    // define @PostConstruct to load the student data...only once!
    @PostConstruct
    public void loadData(){
        //create some sample students to be returned
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("Mario", "Rosy"));
        theStudents.add(new Student("Mary", "Smith"));
    }

    // define endpoint for "/student" - return list of student
    @GetMapping("/students")
    public List<Student> getStudents(){

        return theStudents;
    }

    //define endpoint or (/students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        //just index into the list..keep it simple for now

        //check the studentId against the list size
        if((studentId >= theStudents.size()) || (studentId<0))
            throw new StudentNotFoundException("StudentId not found "+ studentId);

        return theStudents.get(studentId);
    }

}







