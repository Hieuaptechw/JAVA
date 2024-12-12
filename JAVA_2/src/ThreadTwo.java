public class ThreadTwo extends Thread {
    private String upperCaseData;

    public synchronized void setUpperCaseData(String upperCaseData) {
        this.upperCaseData = upperCaseData;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (upperCaseData == null) {
                    System.out.println("ThreadTwo: Finished receiving data.");
                    break;
                }
                System.out.println("ThreadTwo: Received data: " + upperCaseData);
                notify();
            }
        }
    }
}
