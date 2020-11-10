package oneProducerOneConsumer;
/**
 * @author cristian.chilipirea
 * 
 *         DO NOT MODIFY
 */
public class Consumer implements Runnable {
	Buffer buffer;

	Consumer(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		for (int i = 0; i < Main.N; i++) {
			try {
				int value = buffer.get();

				if (value != i) {
					System.out.println("Wrong value. I was supposed to get " + i + " but I received " + value);
					System.exit(1);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("I finished Correctly");
	}

}
/* DO NOT MODIFY */