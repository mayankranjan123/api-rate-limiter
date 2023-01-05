package main.java;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class SlidingWindow implements ApiLimit {

    BlockingQueue<Long> queue;
    int timeInSeconds;
    int bucketCapacity;

    public SlidingWindow(int timeInSeconds, int bucketCapacity) {
        this.queue = new LinkedBlockingDeque<>();
        this.timeInSeconds = timeInSeconds;
        this.bucketCapacity = bucketCapacity;
    }

    @Override
    public boolean grantAccess() {
        long curTime = System.currentTimeMillis();
        checkAndUpdateQueue(curTime);
        if (queue.size() < bucketCapacity) {
            queue.add(curTime);
            return true;
        }
        return false;
    }

    private void checkAndUpdateQueue(long curTime) {
        if (queue.isEmpty())
            return;
        long calculatedTime = (curTime - queue.peek()) / 1000;
        while (calculatedTime >= timeInSeconds) {
            queue.poll();
            if (queue.isEmpty())
                break;
            calculatedTime = (curTime - queue.peek()) / 1000;
        }
    }
}
