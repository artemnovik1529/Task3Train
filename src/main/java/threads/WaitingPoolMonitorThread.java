package threads;


import recourse.WaitingPool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class WaitingPoolMonitorThread extends Thread {
    /*private static final Logger logger = LogManager.getLogger(String.valueOf(WaitingPoolMonitorThread.class));*/


    private final Lock lock = new ReentrantLock();
    WaitingPool pool;

    public WaitingPoolMonitorThread(WaitingPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                /*logger.info(e);*/

            }

            try {
                lock.lock();
                System.out.println("Pool size = " + pool.getSize());
            } finally {
                lock.unlock();
            }

        }

    }

}
