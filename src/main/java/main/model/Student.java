package main.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String studentName;

    @Column(name = "birthdate")
    private String studentBirthdate;

    @Column(name = "university")
    private String studentUniversity;

    @Column(name = "faculty")
    public String studentFaculty;

    @Column(name = "course")
    private int studentCourse;
}
