package threads;

import enity.Train;
import recourse.WaitingPool;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CreatorThread extends Thread {

    /*private static final Logger logger = LogManager.getLogger(String.valueOf(CreatorThread.class));*/
    private final Lock lock = new ReentrantLock();
    private final WaitingPool pool;

    public CreatorThread(WaitingPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        int i = 0;
        while(i < 100){

            try {
                Thread.sleep(new Random().nextInt(12_000));
            } catch (InterruptedException e) {
                /*logger.info(e)*/;

            }

            Train train = new Train(new Random().nextBoolean());

            try {
                lock.lock();
                pool.addTrain(train);

            }  finally {
                lock.unlock();
            }

            i++;

        }

    }

}
