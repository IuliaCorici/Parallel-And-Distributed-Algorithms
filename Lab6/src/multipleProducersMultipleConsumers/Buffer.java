package multipleProducersMultipleConsumers;

import java.util.concurrent.ArrayBlockingQueue;
public class Buffer {
	int capacity;
	ArrayBlockingQueue<Integer> abq;

	public Buffer() {
		capacity = 15;
		abq =  new ArrayBlockingQueue<Integer>(capacity);
	}

	void put(int value) {
		try {
			abq.put(value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	int get() {
		int value = 0;
		try {
			value = abq.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return value;
	}
}
