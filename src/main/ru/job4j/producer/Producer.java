package ru.job4j.producer;

public class Producer implements Runnable {
    private final SimpleBlockingQueue<Integer> simpleBlockingQueue;
    private int value;

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
