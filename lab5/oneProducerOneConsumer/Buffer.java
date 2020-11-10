package oneProducerOneConsumer;
/**
 * @author cristian.chilipirea
 *
 */
public class Buffer {
	int a = -1;

	void put(int value) throws InterruptedException {

			synchronized (this) {
				while (a != -1) {
					this.wait();
				}
				a = value;
				notify();
			}

	}

	int get() throws InterruptedException {
			synchronized (this) {
				while (a == -1) {
					this.wait();
				}
				int copy = a;
				a = -1;
				notify();
				return copy;
			}
	}
}
