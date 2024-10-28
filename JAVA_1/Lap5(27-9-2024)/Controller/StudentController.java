package Controller;

import Entity.Student;

import java.util.*;
import java.util.stream.Collectors;

public class StudentController {
    private static List<Student> students;
    public StudentController(List<Student> students) {
        StudentController.students = students;
    }
    public Optional <Student> getYoungest(){
       Optional <Student> studentYoungest = students.stream()
               .min(Comparator.comparing(Student::getAge));
       return (studentYoungest);

    }
    public static List <Student> searchStudent(String studentname){
        return
        students.stream()
                .filter(s->s.getName().toLowerCase().contains(studentname.toLowerCase()))
                .collect(Collectors.toList());

    }
}
