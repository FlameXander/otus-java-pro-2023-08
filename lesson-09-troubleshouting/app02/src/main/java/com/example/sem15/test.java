package com.example.sem15;

import java.util.concurrent.*;

public class test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> task = () -> {
            // calculate something super impostant
            TimeUnit.SECONDS.sleep(5);
            return 3;
        };
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(task);
        if (future.isDone()) {
            System.out.println(future.get());
        } else {
            System.out.println("Not ready");
        }
        System.out.println(future.get());
        executor.shutdown();
        try {
            executor.submit(task);
        } catch (RejectedExecutionException e) {
            System.out.println("Task rejected");
        }
        try {
            System.out.println(future.get(3, TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            System.out.println("Time out");
        }
        executor.shutdown();
    }
}
