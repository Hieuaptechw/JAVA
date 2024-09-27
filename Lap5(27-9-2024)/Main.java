import Controller.StaffController;
import Controller.StudentController;
import Entity.Gender;
import Entity.Staff;
import Entity.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        List<Staff> staffs = new ArrayList<>();
        staffs.add(new Staff("C2309","Aptech","Hung",Gender.FEMALE,2.6,"Ha noi"));
        staffs.add(new Staff("C2307","TechApp","Hai",Gender.MALE,2.4,"HCM"));
        staffs.add(new Staff("C2308","ViewApp","Hieu",Gender.FEMALE,3.6,"DA Nang"));

        students.add(new Student("C2309G1234", "Vinh beo", LocalDate.of(2005,05,24),Gender.FEMALE,"Ha Noi"));
        students.add(new Student("C2309G1235", "Phi Hieu", LocalDate.of(2006,12,12),Gender.FEMALE,"Ha Noi"));
        students.add(new Student("C2309G1236", "Hieu Phi", LocalDate.of(1999,03,19),Gender.FEMALE,"Ha Noi"));

        System.out.println("List Student :");
        students.forEach(System.out::println);

        StudentController st = new StudentController(students);
        StaffController sc = new StaffController(staffs);
        Optional<Student> studentYoungest = st.getYoungest();
        System.out.println(studentYoungest);

        System.out.println("Student By Name ");
        StudentController.searchStudent("Vinh beo").forEach(System.out::println);


        System.out.println("List Staff :");
        staffs.forEach(System.out::println);


        System.out.println("Staff By Name ");
        StaffController.StaffName("Hieu").forEach(System.out::println);
        System.out.println("Staff By ID ");
        StaffController.StaffID("2309").forEach(System.out::println);
        StaffController staffController = new StaffController(staffs);
        Optional<Staff> maxPayFemale = staffController.maxPayFeMale();
        maxPayFemale.ifPresent(staff -> System.out.println("Female Staff with Highest Pay:\n" + staff));

    }
}