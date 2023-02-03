package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.sleep;

public class Wget implements Runnable {
    public static final int ONE_SECOND = 1000;
    private final String url;
    private final int speed;

    private final String fileName;

    public Wget(String url, int speed, String fileName) {
        this.url = url;
        this.speed = speed;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long startTime = currentTimeMillis();
            int downloadData = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                downloadData += bytesRead;
                if (downloadData >= speed) {
                    long operationTime = currentTimeMillis() - startTime;
                    if (operationTime < ONE_SECOND) {
                        sleep(ONE_SECOND - operationTime);
                    }
                    downloadData = 0;
                    startTime = currentTimeMillis();
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String name = args[2];
        if (name == null || speed == 0 || url == null) {
            throw new IllegalArgumentException();
        }
        Thread wget = new Thread(new Wget(url, speed, name));
        wget.start();
        wget.join();
    }
}
