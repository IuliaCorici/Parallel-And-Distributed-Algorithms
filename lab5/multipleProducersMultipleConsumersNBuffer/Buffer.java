package multipleProducersMultipleConsumersNBuffer;

import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * @author Gabriel Gutu <gabriel.gutu at upb.ro>
 *
 */
public class Buffer {
    
    Queue queue;
    Semaphore consumersSem;
    Semaphore producersSem;
    Integer lock1, lock2;

    public Buffer(int size) {
        queue = new LimitedQueue(size);
        consumersSem = new Semaphore(0);
        producersSem = new Semaphore(size);
        lock1 = 0;
        lock2 = 0;
    }

	void put(int value) throws InterruptedException {
        producersSem.acquire();
        synchronized (lock1) {
            queue.add(value);
        }
        consumersSem.release();
	}

	int get() throws InterruptedException {
        int val = 0;

        consumersSem.acquire();
        synchronized (lock2) {
            val = (int) queue.poll();
        }
        producersSem.release();
        return val;

	}
}
