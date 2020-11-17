package synchronizationProblem;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread implements Runnable {
	private final int id;
	public static AtomicInteger value;

	public MyThread(int id) {
		this.id = id;
		value = new AtomicInteger(0);
	}

	@Override
	public void run() {
		if (id == 0) {
			for (int i = 0; i < Main.N; i++) {
				value.getAndAdd(3);
			}
		} else {
			for (int i = 0; i < Main.N; i++) {
				value.getAndAdd(3);
			}
		}
	}
}
