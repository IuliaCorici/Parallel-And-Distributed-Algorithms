package binarySearch;

public class Main {
    static int N = 100000013;
    static int P = 10;
    static int v[] = new int[N];
    static int ans = -1;

    public static void main(String[] args) {
        Thread threads[] = new Thread[P];
        int target = 7757566;

        for(int i = 0; i < N; i++)
            v[i] = i;

        for (int i = 0; i < P; i++) {
            threads[i] = new Thread(new MyThread(i, target));
            threads[i].start();
        }

        for (int i = 0; i < P; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if ((ans == -1 && (target >= N || target < 0)) || target == ans) {
            System.out.println("CORRECT");
        } else {
            System.out.println("Incorrect");

        }
    }
}
