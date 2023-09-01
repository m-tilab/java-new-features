package java18_to_21.virtual_thread;

import java.util.concurrent.Executors;

public class CreateVirtualThread {

    public static void main(String[] args) throws InterruptedException {

        // Traditional 1: java 1.0 - Thread class
        Thread thread1 = new Thread() {

            public void run() {
                System.out.println(Thread.currentThread());
            }
        };
        thread1.start();

        // Traditional 2: java 1.0 - Runnable interface
        Runnable runnable2 =
                new Runnable() {
                    public void run() {
                        System.out.println(Thread.currentThread());
                    }
                };

        runnable2.run();

        // Traditional 3: java 1.8 - Runnable interface with lambda and functional interface
        Runnable runnable3 =
                () -> { System.out.println("Lambda Runnable running"); };

        runnable3.run();

        // Platform Thread
        var platformThread = Thread.ofPlatform().unstarted(() -> System.out.println(Thread.currentThread()));

        platformThread.start();
        platformThread.join();

        System.out.println("Class: " + platformThread.getClass());

        // Virtual Thread
        var virtualThread = Thread.ofVirtual().unstarted(() -> System.out.println(Thread.currentThread()));

        virtualThread.start();
        virtualThread.join();

        System.out.println("Class: " + virtualThread.getClass());

        // 2
        Runnable runnable = () -> System.out.println(Thread.currentThread());
        Thread.startVirtualThread(runnable);

        // 3
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            executor.submit(runnable);
            executor.submit(runnable);
        }


        /**
         *  Advantages of Java virtual threads *
        1. Improves application availability

        2. Improves application throughput

        3. Reduces ‘OutOfMemoryError: unable to create new native thread’

        4. Reduces application memory consumption

        5. Improves code quality

        6. 100% compatible with Platform Threads
        */
    }
}
