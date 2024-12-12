public class ThreadOne extends Thread {
    private Data data;
    private ThreadTwo threadTwo;

    public ThreadOne(Data data, ThreadTwo threadTwo) {
        this.data = data;
        this.threadTwo = threadTwo;
    }

    @Override
    public void run() {
        for (int i = 0; i < data.getData().length; i++) {
            String upperCaseStr = data.getData()[i].toUpperCase();
            synchronized (threadTwo) {
                System.out.println("ThreadOne: Processing data " + data.getData()[i] + ". => Sending data to ThreadTwo: " + upperCaseStr);
                threadTwo.setUpperCaseData(upperCaseStr);
                threadTwo.notify();
                try {
                    threadTwo.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        synchronized (threadTwo) {
            threadTwo.setUpperCaseData(null);
            threadTwo.notify();
            System.out.println("ThreadOne: Finished sending data.");
        }
    }
}
