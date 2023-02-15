package ru.job4j.producer;

/**
 * Реализация класса Producer - добавляет в очередь элемент.
 *
 * @author Igor Gatman
 * @version 1.1
 * @since 15.02.2023
 */
public class Producer implements Runnable {
    private final SimpleBlockingQueue<Integer> simpleBlockingQueue;
    private final int value;

    public Producer(SimpleBlockingQueue<Integer> simpleBlockingQueue, int value) {
        this.simpleBlockingQueue = simpleBlockingQueue;
        this.value = value;
    }

    @Override
    public void run() {
        System.out.println("Помещаем в очередь значение " + value);
        try {
            simpleBlockingQueue.offer(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
