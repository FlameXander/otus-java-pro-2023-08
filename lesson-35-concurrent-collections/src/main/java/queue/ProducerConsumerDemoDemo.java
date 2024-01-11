package queue;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Oleg Cherednik
 * @since 11.01.2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProducerConsumerDemoDemo {

    public static void main(String... args) throws InterruptedException {
        final int capacity = 5;
        final boolean fair = false;
        final int max = 20;
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(capacity, fair);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i <= 20; i++) {
                    boolean added = queue.offer(i);

                    if (added) {
                        System.out.format("%s: offer(%d), size: %s\n",
                                          Thread.currentThread().getName(), i, queue.size());
                    } else {
                        System.err.format("%s: cannot offer - wait, size: %s\n",
                                          Thread.currentThread().getName(), queue.size());
                        i--;
                    }

                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "producer");

        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    int i = queue.take();
                    System.out.format("---------- %s: take(%d), size: %s\n",
                                      Thread.currentThread().getName(), i, queue.size());

                    if (i == max)
                        break;

                    Thread.sleep(800);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "consumer");
        consumer.setDaemon(true);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }

}
