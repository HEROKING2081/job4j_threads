package ru.job4j.producer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Реализация блокирующей очереди, ограниченной по размеру.
 *
 * @author Igor Gatman
 * @version 1.1
 * @since 15.02.2023
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private volatile Queue<T> queue = new LinkedList<>();
    private final int size;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    /**
     * Метод помещает в очередь элемент.
     * Если очередь заполнена, нить переходит в ожидание.
     * После добавления элемента в очередь, оповещаются другие нити, находящиеся в режиме ожидания.
     *
     * @param value элемент, который необходимо добавить в очередь
     * @throws InterruptedException исключение, может возникнуть при вызове wait()
     */
    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == size) {
            wait();
        }
        queue.offer(value);
        notify();
    }

    /**
     * Метод забирает элемент из очереди.
     * Если очередь пустая, нить переходит в ожидание.
     * После взятия элемента из очереди, оповещаются другие нити, находящиеся в режиме ожидания.
     *
     * @return элемент, который взяли из очереди.
     * @throws InterruptedException исключение, может возникнуть при вызове wait()
     */
    public synchronized T poll() throws InterruptedException {
        while (queue.size() == 0) {
            wait();
        }
        T result = queue.poll();
        notify();
        return result;
    }

    /**
     * @return возвращает true, если очередь пустая.
     */
    public boolean isEmpty() {
        return queue.size() == 0;
    }
}
