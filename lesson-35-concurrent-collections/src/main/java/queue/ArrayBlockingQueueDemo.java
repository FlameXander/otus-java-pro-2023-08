package queue;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Oleg Cherednik
 * @since 11.01.2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ArrayBlockingQueueDemo {

    private static final String DONE = "done";

    public static void main(String... args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1, true);
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }

    @RequiredArgsConstructor
    private static final class Producer implements Runnable {

        private final BlockingQueue<String> queue;
        private final String[] messages = {
                "Mom went to cook lunch",
                "Mom called to the table",
                "Children eat milk porridge",
                "And what does Dad eat?" };

        @Override
        public void run() {
            try {
                int cnt = 0;
                for (int i = 0; i < messages.length; i++) {
                    queue.put(messages[i]);
                    if (++cnt < 3)
                        Thread.sleep(2000);
                }
                queue.put(DONE);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @RequiredArgsConstructor
    private static final class Consumer implements Runnable {

        private final BlockingQueue<String> queue;

        @Override
        public void run() {
            try {
                String msg;
                while (!(msg = queue.take()).equals(DONE))
                    System.out.println(msg);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
