package Controller;

import Entity.Student;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class StudentManagement {
    private List<Student> students;
    public StudentManagement(){
        this.students = new ArrayList<>();
    }
    public void addStudent(){
        for (int i =0;i<3;i++){
            System.out.println("Student" +(i+1));
            Student student = new Student();
            student.input();
            students.add(student);
        }
    }
    public void showStudent(){
        for (Student student : students){
            student.display();
        }
    }
    public List<Student> sortStudentByMark(){
       List <Student> student =  students.stream()
                .sorted(Comparator.comparing(Student::getMark))
                .toList();
        student.forEach(s->s.display());
        return student;
    }
}
