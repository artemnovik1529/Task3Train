package enity;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Tunnel {
    private final boolean route;
    private final AtomicBoolean isBusyFlag = new AtomicBoolean(false);
    private final AtomicInteger trainCount = new AtomicInteger(0);
    private final Queue<Train> trainsInTunnel = new LinkedList<>();

    public Tunnel(boolean route) {
        this.route = route;
    }

    public boolean isBusyFlag() {
        return isBusyFlag.get();
    }

    public void setBusyFlag(boolean isBusyFlag) {
        this.isBusyFlag.set(isBusyFlag);
    }

    public boolean getRoute() {
        return route;
    }

    public int getTrainCount() {
        return trainCount.get();
    }

    public void incrementTrainCount(){
        trainCount.incrementAndGet();
    }

    public void decrementTrainCount(){
        trainCount.decrementAndGet();
    }

    public void addTrain(Train train){
        trainsInTunnel.add(train);
    }

    public void removeTrain(){
        trainsInTunnel.poll();
    }
}

