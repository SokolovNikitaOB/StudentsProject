package main;

import main.model.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students/")
    public List<Student> list(){
        Iterable<Student> studentIterable = studentRepository.findAll();
        ArrayList<Student> students = new ArrayList<>();
        for(Student s : studentIterable){
            students.add(s);
        }
        return students;
    }

    @PostMapping("/students/")
    public int add(Student student){
        Student newStudent = studentRepository.save(student);
        return newStudent.getId();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity get(@PathVariable int id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(!optionalStudent.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else {
            return new ResponseEntity(optionalStudent.get(),HttpStatus.OK);
        }
    }

    @DeleteMapping("/students/{id}")
    public void delete(@PathVariable int id){
        studentRepository.deleteById(id);
    }

    @DeleteMapping("/students/")
    public void deleteAll(){
        studentRepository.deleteAll();
    }

}
