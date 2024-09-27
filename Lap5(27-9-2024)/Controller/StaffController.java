package Controller;
import Entity.Staff;
import Entity.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StaffController {
    private static List<Staff> staffs;
    public StaffController(List<Staff> staffs) {
        StaffController.staffs = staffs;
    }
    public static List <Staff> StaffName(String staffname){
        return
                staffs.stream()
                        .filter(s->s.getName().toLowerCase().contains(staffname.toLowerCase()))
                        .collect(Collectors.toList());

    }
    public static List <Staff> StaffID(String sfaffid){
        return
                staffs.stream()
                        .filter(s->s.getId().toLowerCase().contains(sfaffid.toLowerCase()))
                        .collect(Collectors.toList());

    }
    public String SraffMaxPay(){
        Optional<Staff> SraffMaxPay = Staff.stream()
                .filter(s -> b.getGender().equals(Gender.Female))
                .max(Comparator.comparing(Staff::getPay));
        SraffMaxPay.ifPresent(System.out.println);
    }

}