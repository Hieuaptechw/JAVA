package Controller;

import Entity.Staff;
import java.util.List;
import java.util.Optional;

public class GetPaidController {
    private static List<Staff> staffs;

    public GetPaidController(List<Staff> staffs) {
        GetPaidController.staffs = staffs;
    }

    public static Optional<String> getPaid(String staffName) {

        System.out.println("-----------------------------------------------");
        System.out.println("Paid: " + staffName);
        System.out.println("-----------------------------------------------");

        return staffs.stream()
                .filter(staff -> staff.getName().equalsIgnoreCase(staffName))
                .findFirst()
                .map(staff -> String.format("| %-20s | %-20f |",
                        staff.getName(),
                        staff.getPaid()));

    }

    public static void displayStafff(String staffName) {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Information: " + staffName);
        staffs.stream()
                .filter(staff -> staff.getName().equalsIgnoreCase(staffName))
                .forEach(staff -> {
                    System.out.println(staff.toString());
                });
    }

}
