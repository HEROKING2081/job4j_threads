package ru.job4j.producer;

/**
 * Реализация класса Consumer - забирает из очереди элемент.
 *
 * @author Igor Gatman
 * @version 1.1
 * @since 15.02.2023
 */
public class Consumer implements Runnable {
    private final SimpleBlockingQueue<Integer> simpleBlockingQueue;

    public Consumer(SimpleBlockingQueue<Integer> simpleBlockingQueue) {
        this.simpleBlockingQueue = simpleBlockingQueue;
    }

    @Override
    public void run() {
        try {
            System.out.println("Взяли из очереди значение " + simpleBlockingQueue.poll());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
