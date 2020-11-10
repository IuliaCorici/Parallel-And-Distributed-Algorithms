package binarySearch;


import static binarySearch.Main.*;

public class MyThread implements Runnable {
    int id, target;

    public MyThread(int id, int target) {
        this.id = id;
        this.target = target;
    }

    @Override
    public void run() {
        int start = id * N / P;
        int end = Math.min((id + 1) *  N / P, N);
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (v[mid] == target) {
                ans = mid;
                break;
            } else if (v[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
    }

}
