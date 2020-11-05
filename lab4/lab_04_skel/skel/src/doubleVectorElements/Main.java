package doubleVectorElements;


/**
 * @author cristian.chilipirea
 *
 */
public class Main {
	static int N = 100000013;
	static int P = 10;
    static int v[] = new int[N];

	public static void main(String[] args) {
		Thread threads[] = new Thread[P];

		for(int i = 0; i < N; i++)
			v[i] = i;

		for (int i = 0; i < P; i++) {
			threads[i] = new Thread(new MyThread(i));
			threads[i].start();
		}

		for (int i = 0; i < P; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < N; i++) {
			if(v[i]!= i*2) {
				System.out.println("Wrong answer");
				System.exit(1);
			}
		}
		System.out.println("Correct");
	}

}
