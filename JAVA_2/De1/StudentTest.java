import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentTest {
    public static void main(String[] args){
        List<Student> students = new ArrayList<>();
        String sysPath = System.getProperty("user.dir");
        System.out.println(sysPath);
        String dataFile = sysPath.replace("/","\\")   +"/src/student.bat";
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            for (int i = 0; i < 1; i++) {
                System.out.println("========= Student " + (i + 1) + "=============");
                String rollNumber;
                do {
                    System.out.print("Enter student roll number: ");
                    rollNumber = input.readLine();
                    if (rollNumber.matches("^[a-zA-Z]{1}[0-9]{4}[a-zA-Z]{1,2}[0-9]{4}$")) {
                        break;
                    } else {
                        System.out.println("Invalid roll number. Try again.");
                    }
                } while (true);
                System.out.print("Enter student name: ");
                String name = input.readLine();
                System.out.print("Enter student address: ");
                String address = input.readLine();
                System.out.print("Enter student age: ");
                int age = Integer.parseInt(input.readLine());
                Student student = new Student(rollNumber, name, address, age);
                students.add(student);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile));
            students.stream()
                    .peek(p->{
                        try{
                            String lineWriter = p.convertData(";");
                            bw.write(lineWriter);
                            bw.newLine();
                            bw.flush();

                        }catch(IOException e){
                            System.out.println(e.getMessage());
                        }
                    }).collect(Collectors.toSet());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        List<Student> readStudents = new ArrayList<>();
        try{
            BufferedReader reader   = new BufferedReader(new FileReader(dataFile));
            String lineData;
            while ((lineData = reader.readLine()) != null){
                Student student = new Student();
                if(!lineData.isEmpty()){
                    String[] parts = lineData.split(";");
                    student.setRollNumber(String.valueOf(parts[0]));
                    student.setName(String.valueOf(parts[1]));
                    student.setAddress(String.valueOf(parts[2]));
                    student.setAge(Integer.parseInt(String.valueOf(parts[3])));
                }
                readStudents.add(student);

            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println("\nStudents read from file:");
        readStudents.forEach(System.out::println);
        System.out.println("\nStudents with age less than 18:");
        readStudents.stream()
                .filter(s->s.getAge()<18)
                .forEach(System.out::println);
    }



}