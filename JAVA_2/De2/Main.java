import java.util.Random;

class DayOfWeek {
    String[] vietnameseDays = {"Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy", "Chủ nhật"};
    String[] englishDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    private String vietnameseDay;
    private String englishDay;

    public synchronized void setVietnameseDay(String day) {
        this.vietnameseDay = day;
        notify();
    }

    public synchronized String getVietnameseDay() {
        return vietnameseDay;
    }

    public synchronized void setEnglishDay(String day) {
        this.englishDay = day;
    }

    public String getEnglishDay() {
        return englishDay;
    }

    public synchronized String convertToEnglish(String vietnameseDay) {
        for (int i = 0; i < vietnameseDays.length; i++) {
            if (vietnameseDays[i].equals(vietnameseDay)) {
                return englishDays[i];
            }
        }
        return "";
    }
}

class FirstThread extends Thread {
    private final DayOfWeek dayOfWeek;

    public FirstThread(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Random random = new Random();
            String randomDay = dayOfWeek.vietnameseDays[random.nextInt(dayOfWeek.vietnameseDays.length)];

            dayOfWeek.setVietnameseDay(randomDay);
            System.out.println("Day in Vietnamese: " + randomDay);

        }
    }
}

class SecondThread extends Thread {
    private final DayOfWeek dayOfWeek;

    public SecondThread(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (dayOfWeek) {
                while (dayOfWeek.getVietnameseDay() == null) {
                    try {
                        dayOfWeek.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                String vietnameseDay = dayOfWeek.getVietnameseDay();
                String englishDay = dayOfWeek.convertToEnglish(vietnameseDay);
                dayOfWeek.setEnglishDay(englishDay);
                System.out.println("Day in English: " + englishDay);
                dayOfWeek.setVietnameseDay(null);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        DayOfWeek dayOfWeek = new DayOfWeek();

        FirstThread firstThread = new FirstThread(dayOfWeek);
        SecondThread secondThread = new SecondThread(dayOfWeek);

        firstThread.start();
        secondThread.start();
    }
}
