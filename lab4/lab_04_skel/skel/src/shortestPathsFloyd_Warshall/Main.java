package shortestPathsFloyd_Warshall;


import java.util.concurrent.CyclicBarrier;

/**
 * @author cristian.chilipirea
 *
 */
public class Main {

	public static CyclicBarrier barrier;
	static int NUMBER_OF_THREADS = 4;
	static int M = 9;
	public static void main(String[] args) {
		barrier = new CyclicBarrier(NUMBER_OF_THREADS);
		Thread threads[] = new Thread[NUMBER_OF_THREADS];

		int graph[][] = { { 0, 1, M, M, M },
				          { 1, 0, 1, M, M },
				          { M, 1, 0, 1, 1 },
				          { M, M, 1, 0, M },
				          { M, M, 1, M, 0 } };
		
		// Parallelize me (You might want to keep the original code in order to compare)
//		for (int k = 0; k < 5; k++) {
//			for (int i = 0; i < 5; i++) {
//				for (int j = 0; j < 5; j++) {
//					graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);
//				}
//			}
//		}

		for (int i = 0; i < NUMBER_OF_THREADS; i++) {
			threads[i] = new Thread(new MyThread(i, graph));
			threads[i].start();
		}

		for (int i = 0; i < NUMBER_OF_THREADS; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
	}
}
