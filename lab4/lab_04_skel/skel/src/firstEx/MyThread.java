package firstEx;


public class MyThread implements Runnable {
        int id;

        MyThread(int id) {
        this.id = id;
        }

        @Override
        public  void run() {
            System.out.println("Hello from thread id =" + id);
        }

}
