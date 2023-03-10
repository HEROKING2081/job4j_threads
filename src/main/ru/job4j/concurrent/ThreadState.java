package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(() -> {});
        System.out.println(first.getName() + " -> " + first.getState());
        Thread second = new Thread(() -> {});
        System.out.println(second.getName() + " -> " + second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getName() + " -> " + first.getState());
            System.out.println(second.getName() + " -> " + second.getState());
        }
        System.out.println(first.getName() + " -> job finish");
        System.out.println(second.getName() + " -> job finish");
    }
}
