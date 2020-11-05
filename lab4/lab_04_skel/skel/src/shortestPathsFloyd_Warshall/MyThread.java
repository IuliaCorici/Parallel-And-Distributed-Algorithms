package shortestPathsFloyd_Warshall;


import java.util.concurrent.BrokenBarrierException;

import static shortestPathsFloyd_Warshall.Main.NUMBER_OF_THREADS;

public class MyThread implements Runnable {
    int id;
    private int[][] graph;

    public MyThread(int id, int[][] graph) {
        this.id = id;
        this.graph = graph;

    }


    @Override
    public void run() {
        int start = id * 5 / NUMBER_OF_THREADS;
        int end = Math.min((id + 1) *  5 / NUMBER_OF_THREADS, 5);


        for (int k = 0; k < 5; k++) {

            for (int i = start; i < end; i++) {
                for (int j = 0; j < 5; j++) {
                    graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);
                }
            }

            try {
                Main.barrier.await();
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}