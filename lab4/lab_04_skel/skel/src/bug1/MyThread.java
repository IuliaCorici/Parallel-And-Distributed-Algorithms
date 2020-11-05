package bug1;
/**
 * @author cristian.chilipirea
 * 
 *         Why is value set correct even though we do not use
 *         locks/synchronized?
 *         answer: because run is sequential
 *         DO NOT MODIFY
 */
public class MyThread implements Runnable {
	static int value = 0;

	static synchronized void sum() {
		for (int i = 0; i < Main.N; i++)
			value = value + 3;

		try {
			Thread.sleep(10);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	@Override
	public  void run() {
		for (int i = 0; i < Main.N; i++)
			value = value + 3;
//		sum();
	}
}
