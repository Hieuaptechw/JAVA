import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClubManager {
    private List<Member> members;

    public ClubManager() {
        this.members = new ArrayList<>();
    }


    public void inputMembers() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the number of members to add: ");
            int numberOfMembers = Integer.parseInt(input.readLine());
            for (int i = 0; i < numberOfMembers; i++) {
                System.out.println("===========================");

                String memberID;
                do {
                    System.out.print("Enter Member ID (Member " + (i + 1) + "): ");
                    memberID = input.readLine();
                    if (memberID.matches("^[TVA](MB|MT|MN)[0-9]{5}$")) {
                        break;
                    } else {
                        System.out.println("Invalid Member ID. Try again.");
                    }
                } while (true);

                System.out.print("Enter member name: ");
                String memberName = input.readLine();
                System.out.print("Enter address: ");
                String address = input.readLine();
                members.add(new Member(memberID, memberName, address));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serializeMembers(String fileOut) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileOut))) {
            for (Member member : members) {
                String lineWriter = member.convertData(";");
                bw.write(lineWriter);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Serialized Members!");
    }

    public void deserializeMembers(String fileIn) {
        List<Member> readMembers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileIn))) {
            String lineData;
            while ((lineData = reader.readLine()) != null) {
                Member member = new Member();
                if (!lineData.isEmpty()) {
                    String[] parts = lineData.split(";");
                    member.setMemberId(parts[0]);
                    member.setMemberName(parts[1]);
                    member.setAddress(parts[2]);
                }
                readMembers.add(member);
            }
            System.out.println("Deserialized Members:");
            readMembers.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        ClubManager manager = new ClubManager();
        String sysPath = System.getProperty("user.dir");
        String dataFile = sysPath.replace("/", "\\") + "\\src\\member_of_club.txt";
        manager.inputMembers();
        manager.serializeMembers(dataFile);
        manager.deserializeMembers(dataFile);
    }
}
