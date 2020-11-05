package firstEx;


public class Main {

    static final int N = 10;

    public static void main(String[] args) {
        Thread threads[] = new Thread[N];

        for (int i = 0; i < N; i++)
            threads[i] = new Thread(new MyThread(i));

        for (int i = 0; i < N; i++)
            threads[i].start();

        for (int i = 0; i < N; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
