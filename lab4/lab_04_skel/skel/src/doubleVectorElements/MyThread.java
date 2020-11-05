package doubleVectorElements;


import static doubleVectorElements.Main.*;

public class MyThread implements Runnable {
    int id;

    public MyThread(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        int start = id * N / P;
        int end = Math.min((id + 1) *  N / P, N);

        for (int i = start; i < end; i++) {
            v[i] *= 2;
        }

    }

}
