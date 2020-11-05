package bug3;
/**
 * @author cristian.chilipirea
 * 
 *         Why is value set correct even though we use different locks in
 *         different threads?
 *
 *         answer: because in java strings with same values are the same string then it's the same lock
 */
public class MyThread implements Runnable {
	static String a = "LOCK";
	static String b = "LOCK";
	int id;
	static int value = 0;

	public MyThread(int id) {
		this.id = id;
	}

	static synchronized void sum() {
		for (int i = 0; i < bug3.Main.N; i++)
			value = value + 3;

		try {
			Thread.sleep(10);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void run() {
		if (id == 0) {
			synchronized (a) {
				for (int i = 0; i < Main.N; i++)
					value = value + 3;
			}
		} else {
			synchronized (b) {
				for (int i = 0; i < Main.N; i++)
					value = value + 3;
			}
		}
	}
}
