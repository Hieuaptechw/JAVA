import Controller.GetPaidController;
import Entity.Manager;
import Entity.Staff;
import Entity.Technician;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        List<Staff> staff = new ArrayList<>();
        staff.add(new Manager("Hieu", 4000, 500));
        staff.add(new Technician("Hung", 2000,300));
        staff.add(new Technician("Mai", 1500,300));
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name Staff: ");
        String staffName = scanner.nextLine();
        GetPaidController gp = new GetPaidController(staff);
        Optional<String> paid = gp.getPaid(staffName);
        System.out.println(paid.get());
        gp.displayStafff(staffName);



    }
}
