import Controller.StudentManagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManagement stm = new StudentManagement();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("| ");
            System.out.println("1.Add New");
            System.out.println("2.Show all");
            System.out.println("3.Sort");
            System.out.println("4.Exit");
            System.out.print("Your choice:");
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    stm.addStudent();
                    break;
                case 2:
                    stm.showStudent();
                    break;
                case 3:
                    stm.sortStudentByMark();
                    break;
                case 4:
                    System.out.println("Exiting......");
                    break;
                default:
                    System.out.println("Invaild Choice!");
            }
        }
        while(choice != 4);
    }
}