package main;

import main.model.Student;
import main.model.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping("/")
    public String index(Model model){
        Iterable<Student> studentIterable = studentRepository.findAll();
        ArrayList<Student> students = new ArrayList<>();
        for(Student s : studentIterable){
            students.add(s);
        }
        model.addAttribute("students",students);
        return "index";
    }
}
