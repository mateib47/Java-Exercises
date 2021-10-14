import java.util.Scanner;
import java.util.Set;

public class MultithreadedDotProduct {
    private volatile int sum = 0;
    static class MyThread extends Thread {
        private long a,b, prod;

        public MyThread(long a , long b) {
            this.a = a;
            this.b = b;
            prod = 1;
        }

        public void run() {
            prod *= a * b;
        }

        public long getProd() {
            return this.prod;
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of elements of the array");
        int length  = s.nextInt();
        int a[] = new int[length];
        int b[] = new int[length];
        System.out.println("Enter numbers of first array separated by spaces");

        int sum = 0;
        MyThread[] myThreads = new MyThread[length];
        for(int i=0;i<length;i++){
            myThreads[i] = new MyThread(a[i],b[i]);
            myThreads[i].start();
        }
        for (int j = 0; j < length; j++) {
            try {
                myThreads[j].join();
                sum += myThreads[j].getProd();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(sum);
    }
}
