package recourse;

import enity.Train;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WaitingPool implements Iterable<Train> {

    private final List<Train> listOfTrains;

    public WaitingPool() {
        listOfTrains = new ArrayList<>();
    }

    public Train getTrain(int i){
        return listOfTrains.get(i);
    }

    public void addTrain(Train train){
        listOfTrains.add(train);
    }

    public int getSize(){
        return listOfTrains.size();
    }

    @Override
    public Iterator<Train> iterator() {
        return listOfTrains.iterator();
    }

}
