package Entity;

import IGeneral.IStudent;

import java.util.Scanner;

public class Student implements IStudent{
    private int id;
    private String fullName;
    private String email;
    private float mark;
    public Student (){
        ;
    }
    public Student(int id, String fullName, String email, float mark) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the ID");
        this.id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the name:");
        this.fullName = sc.nextLine();

        System.out.println("Enter the email:");
        this.email = sc.nextLine();
        System.out.println("Enter the mark:");
        this.mark =sc.nextFloat();
    }

    @Override
    public void display() {
        System.out.println("ID:"+id +" Fullname"+ fullName +" Email:"+email + " Mark:"+mark);
    }
}
