package java18_to_21.virtual_thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CreateVirtualThread {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

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
                () -> System.out.println("Lambda Runnable running");

        runnable3.run();

        // ExecuteService: java 1.8 - Execute multiple threads in a single task queue
        try (ExecutorService singleTaskQueueExecutor = Executors.newFixedThreadPool(3)) {
            List<String> finalResultList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Future<Integer> futureResult = singleTaskQueueExecutor.submit(new Add(10, 20)); // 2
                Integer intermediateResult = futureResult.get(); // blocking // 3
                Future<Integer> finalResult = singleTaskQueueExecutor.submit(new Multiply(intermediateResult)); // 4
                finalResultList.add(i + " " + finalResult.get()); // blocking //5
            }
            singleTaskQueueExecutor.shutdown();
        }

        // CompletableFuture: java 1.8 - Execute multiple threads in a multiple task queue
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<CompletableFuture<Integer>> finalResultList = new ArrayList<>();

        for (int i = 0; i < 10; i++)  {
            CompletableFuture<Integer> voidCompletableFuture = CompletableFuture
                    .supplyAsync(() -> add(10, 20), executor)
                    .thenApplyAsync(CreateVirtualThread::multiply, executor);

            finalResultList.add(voidCompletableFuture);
        }

        for (CompletableFuture<Integer> future : finalResultList) {
            future.get();
        }
        executor.shutdown();


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
        try (var virtualThreadPerTaskExecutor = Executors.newVirtualThreadPerTaskExecutor()) {

            virtualThreadPerTaskExecutor.submit(runnable);
            virtualThreadPerTaskExecutor.submit(runnable);
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

    static class Add implements Callable<Integer> {
        int a;
        int b;

        Add(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public Integer call() throws Exception {
            return a + b;
        }
    }

    static class Multiply implements Callable<Integer> {
        int result;
        Multiply(int result) {
            this.result = result;
        }

        @Override
        public Integer call() throws Exception {
            result = result * 10;
            System.out.println(Thread.currentThread().getName() + " " + result);
            return result;
        }
    }

    public static Integer add(int a, int b) {
        return a + b;
    }

    public static Integer multiply(int a) {
        a = a * 10;
        System.out.println(Thread.currentThread().getName() + " " + a);
        return a;
    }
}
