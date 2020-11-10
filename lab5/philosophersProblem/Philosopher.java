package philosophersProblem;

import java.util.concurrent.Semaphore;

/**
 * @author cristian.chilipirea
 * 
 */
public class Philosopher implements Runnable {
	Semaphore leftFork, rightFork;
	int id;

	public Philosopher(int id, Object leftFork, Object rightFork) {
		leftFork = new Semaphore(1);
		this.leftFork = (Semaphore) leftFork;
		rightFork = new Semaphore(1);
		this.rightFork = (Semaphore) rightFork;
		this.id = id;
	}

	private void sleep() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	// vor manca jumatate din filosofi si apoi va manca cealalta jumatate
	// s1 d1 s2 d2 s3 d3
	// s1 d1 - asignat primului, s2 d2- la al treilea, s3 d3-la al cincilea
	// d1 s2 - la al doilea, etc
	@Override
	public void run() {
		try {
			leftFork.acquire(); // ia furculita stanga
		}
		catch (Exception e) {
			e.printStackTrace(System.out);
		}
		try {
			rightFork.acquire(); // ia furculita dreapta
		}
		catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println("Philosopher " + id + " is eating");
		sleep(); // mananca
		leftFork.release(); // o lasa pe cea stanga pentru a o lua alt filosof
		rightFork.release(); // o lasa si pe cea dreapta si va filosofa
	}
}
