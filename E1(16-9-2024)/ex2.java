import java.util.Scanner;

public class ex2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Nhập số tiền bạn muốn gửi: ");
        int monney = input.nextInt();

        int month;
        double laixuat = 0;

        do {
            System.out.print("Nhập số tháng bạn muốn gửi (6,9,12,24,36): ");
            month = input.nextInt();

            if (month != 6 && month != 9 && month != 12 && month != 24 && month != 36) {
                System.out.println("Tháng không hợp lệ. Vui lòng nhập lại.");
            }
        } while (month != 6 && month != 9 && month != 12 && month != 24 && month != 36);



        switch (month) {
            case 6:
                laixuat = 5.1;
                break;
            case 9:
                laixuat = 5.5; // Lãi suất cho 9 tháng
                break;
            case 12:
                laixuat = 6.4;
                break;
            case 24:
                laixuat = 6.7;
                break;
            case 36:
                laixuat = 6.9;
                break;
        }

        double x = (monney / 100.0) * laixuat;
        System.out.println("+---------------------------------------------------------------------------------+");
        System.out.printf("| %-15s | %-10s | %-15s | %-17s | %-10s |\n",
                "SỐ tiền gửi", "Kỳ hạn", "Loại Tiền gửi", "Tiền lãi theo kỳ", "Lãi + Gốc");
        System.out.println("+---------------------------------------------------------------------------------+");
        System.out.printf("| %-15d | %-10s | %-15s | %-17.2f | %-10.2f |\n",
                monney, month + " tháng", "VND",(monney / 100.0) * laixuat,monney +(monney / 100.0) * laixuat);
        System.out.println("+--------------------------------------------------------------------------------+");
    }

}
