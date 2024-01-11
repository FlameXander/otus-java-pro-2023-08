package queue;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Oleg Cherednik
 * @since 11.01.2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConcurrentLinkedQueueDemo {

    public static void main(String... args) {
        Queue<String> queue = new ConcurrentLinkedQueue<>();
        AtomicBoolean cycle = new AtomicBoolean(true);

        Thread producer = new Thread(new Producer(queue, cycle));
        Thread consumer = new Thread(new Consumer(queue, cycle));

        producer.start();
        consumer.start();

        while (consumer.isAlive()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @RequiredArgsConstructor
    private static final class Producer implements Runnable {

        private final Queue<String> queue;
        private final AtomicBoolean cycle;

        @Override
        public void run() {
            System.out.println("Producer started");

            try {
                for (int i = 1; i <= 10; i++) {
                    String str = "String" + i;
                    queue.add(str);
                    System.out.println("Producer added : "
                                               + str);
                    Thread.sleep(200);
                }
                cycle.set(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RequiredArgsConstructor
    private static final class Consumer implements Runnable {

        private final Queue<String> queue;
        private final AtomicBoolean cycle;

        @Override
        public void run() {
            String str;
            System.out.println("Consumer started\n");
            while (cycle.get() || !queue.isEmpty()) {
                if ((str = queue.poll()) != null)
                    System.out.println("  consumer removed : " + str);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
