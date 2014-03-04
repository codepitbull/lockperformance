package de.codepitbull.lockperformance;

import java.util.concurrent.CountDownLatch;

/**
 * @author Jochen Mader
 */
public class TwoThreadsWithLock {

    private Long count=0l;
    private CountDownLatch countDownLatch = new CountDownLatch(2);

    public Long run(){
        Thread thread1 = new Thread(new IncrementThread());
        Thread thread2 = new Thread(new IncrementThread());
        long start = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis()-start;
    }

    private class IncrementThread implements Runnable {

        @Override
        public void run() {
            for(;count<500_000_000;)
                synchronized (count){
                    count++;
                };
            countDownLatch.countDown();
        }
    }
}
