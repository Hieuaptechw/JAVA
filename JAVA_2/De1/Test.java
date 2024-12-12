public class Test {
    public static void main(String[] args) {
        Data data = new Data();
        ThreadTwo threadTwo = new ThreadTwo();
        ThreadOne threadOne = new ThreadOne(data, threadTwo);
        threadTwo.start();
        threadOne.start();
    }
}
