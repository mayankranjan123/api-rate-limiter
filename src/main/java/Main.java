package main.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        RateLimit rateLimit = new RateLimit("1", new SlidingWindow(1, 5));
        ExecutorService service = Executors.newFixedThreadPool(12);
        for (int i = 0; i < 12; i++) {
            service.submit(() -> rateLimit.accessApplication("1"));
        }
    }
}
