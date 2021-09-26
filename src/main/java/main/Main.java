package main;

import enity.Tunnel;
import recourse.WaitingPool;
import threads.CreatorThread;
import threads.DispatcherThread;
import threads.WaitingPoolMonitorThread;

public class Main {
    public static void main(String[] args) {

        Tunnel tunnelTrue = new Tunnel(true);
        Tunnel tunnelFalse = new Tunnel(false);
        WaitingPool waitingPool = new WaitingPool();

        CreatorThread creator = new CreatorThread(waitingPool);
        creator.start();

        WaitingPoolMonitorThread poolMonitor = new WaitingPoolMonitorThread(waitingPool);
        poolMonitor.setDaemon(true);
        poolMonitor.start();

        DispatcherThread dispatcher = new DispatcherThread(waitingPool, tunnelTrue, tunnelFalse);
        dispatcher.setDaemon(true);
        dispatcher.start();


    }

}



