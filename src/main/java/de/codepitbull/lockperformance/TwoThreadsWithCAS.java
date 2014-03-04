package de.codepitbull.lockperformance;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Jochen Mader
 */
public class TwoThreadsWithCAS {

    private AtomicLong count=new AtomicLong();
    private CountDownLatch countDownLatch = new CountDownLatch(2);

    public Long run() throws Exception{
        Thread thread1 = new Thread(new IncrementThread());
        Thread thread2 = new Thread(new IncrementThread());
        long start = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        countDownLatch.await();
        return System.currentTimeMillis()-start;
    }

    private class IncrementThread implements Runnable {

        @Override
        public void run() {
            while(count.incrementAndGet()<500_000_000);
            countDownLatch.countDown();
        }
    }
}
