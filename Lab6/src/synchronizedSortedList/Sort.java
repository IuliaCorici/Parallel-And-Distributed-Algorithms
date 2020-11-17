package synchronizedSortedList;

import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Sort extends Thread {
    private final List<Integer> list;
    private Semaphore sem;

    public Sort(List<Integer> list, Semaphore sem) {
        super();
        this.list = list;
        this.sem = sem;
    }

    @Override
    public void run() {
        try {
            sem.acquire();
            Collections.sort(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
