package bugConcurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

public class MyThread implements Runnable {
	public static ConcurrentHashMap<Integer, Integer> hashMap = new ConcurrentHashMap<>();
	private final int id;
	int oldVal;

	public MyThread(int id) {
		this.id = id;
	}

	private void addValue(int key, int value) {

		if (hashMap.putIfAbsent(key, value) != null) {
			oldVal = hashMap.putIfAbsent(key, value);
			hashMap.put(key, oldVal + value);
		}
	}
	
	@Override
	public void run() {
		if (id == 0) {
			for (int i = 0; i < Main.N; i++) {
				addValue(i, 2 * i);
			}
		} else {
			for (int i = 0; i < Main.N; i++) {
				addValue(i, 3 * i);
			}
		}
	}
}