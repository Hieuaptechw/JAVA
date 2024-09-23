import java.util.Scanner;

public class ex1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int monney;

        do {
            System.out.print("Nhập số tiền bạn muốn rút: ");
            monney = input.nextInt();

            if (monney % 50000 == 0) {
                int a = 0, b = 0, c = 0, d = 0;

                if (monney >= 500000) {
                    a = monney / 500000;
                    monney %= 500000;
                }
                if (monney >= 200000) {
                    b = monney / 200000;
                    monney %= 200000;
                }
                if (monney >= 100000) {
                    c = monney / 100000;
                    monney %= 100000;
                }
                d = monney / 50000;


                int x = a + b + c + d;

                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.printf("| %-30s | %-10s | %-10s | %-10s | %-10s | %-10s|\n",
                        "SỐ tiền rút", "Tờ 500", "Tờ 200", "Tờ 100", "Tờ 50", "Tổng số tờ");
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.printf("| %-30d | %-10d | %-10d | %-10d | %-10d | %-10d|\n",
                        monney + (a * 500000 + b * 200000 + c * 100000 + d * 50000),
                        a, b, c, d, x);
                System.out.println("--------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("Vui lòng nhập số tiền là bội của 50.000");
            }
        } while (monney % 50000 != 0);

        System.out.println("Giao dịch hoàn tất!");
    }
}
