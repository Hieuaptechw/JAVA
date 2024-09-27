package Controller;
import Entity.Gender;
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
    public Optional<Staff> maxPayFeMale() {
        return staffs.stream()
                .filter(s -> s.getGender() == Gender.FEMALE)
                .max(Comparator.comparing(Staff::getPay));
    }
    public void updateStaffName(String staffId, String newName) {
        staffs.stream()
                .filter(s -> s.getId().equals(staffId))
                .findFirst()
                .ifPresent(s -> s.setName(newName));
    }

}
