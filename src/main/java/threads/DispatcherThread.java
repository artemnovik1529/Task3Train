package threads;

import enity.Tunnel;
import recourse.WaitingPool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class DispatcherThread extends Thread {

    private static final int MAX_IN_TUNNEL_TRAIN_COUNT = 3;
    private static final int MAX_ROW_TRAIN_COUNT = 4;
    /*private static final Logger logger = LogManager.getLogger(String.valueOf(DispatcherThread.class));*/
    private final Lock lock = new ReentrantLock();
    private final WaitingPool pool;
    private final Tunnel tunnelTrue;
    private final Tunnel tunnelFalse;
    private int trueQueueCount;
    private int falseQueueCount;

    public DispatcherThread(WaitingPool pool, Tunnel tunnelTrue, Tunnel tunnelFalse) {
        this.pool = pool;



        this.tunnelTrue = tunnelTrue;
        this.tunnelFalse = tunnelFalse;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
               /* logger.info(e);*/

            }

            if (trueQueueCount < MAX_ROW_TRAIN_COUNT){
                checkAndSendPriorityTrain(tunnelTrue);
            } else {
                sendTrain(tunnelFalse);
            }
            if (falseQueueCount < MAX_ROW_TRAIN_COUNT){
                checkAndSendPriorityTrain(tunnelFalse);
            } else {
                sendTrain(tunnelTrue);
            }

            if(tunnelTrue.getTrainCount() == 0 && tunnelFalse.getTrainCount() == 0){
                sendTrain(tunnelTrue);
            }




        }
    }

    private void checkAndSendPriorityTrain(Tunnel tunnel) {

        if (!tunnelTrue.isBusyFlag() && tunnelTrue.getTrainCount() > 0
                && tunnelTrue.getTrainCount() < MAX_IN_TUNNEL_TRAIN_COUNT) {
            sendTrain(tunnel);
        }

    }

    private void sendTrain(Tunnel tunnel) {
        try {
            lock.lock();
            for (int i = 0; i < pool.getSize(); i++) {
                if (pool.getTrain(i).getRoute() == tunnel.getRoute()) {
                    new TrainThread(pool.getTrain(i), tunnel).start();
                    System.out.println("Train " + pool.getTrain(i).getRoute() + " sended.");
                    if (pool.getTrain(i).getRoute()){
                        trueQueueCount++;
                        falseQueueCount = 0;
                    } else {
                        falseQueueCount++;
                        trueQueueCount = 0;
                    }
                    break;
                }
            }
        } finally {
            lock.unlock();
        }
    }

}
