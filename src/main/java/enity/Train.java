package enity;




import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Train {

   /* private static final Logger logger = LogManager.getLogger(String.valueOf(Train.class));*/
    private final boolean route;


    public Train(boolean route){
        this.route = route;
    }

    public boolean getRoute() {
        return route;
    }
    public void busyGo(){
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            /*logger.info(e);*/

        }
    }
    public void restGo(){
        try {
            Thread.sleep(8_000);
        } catch (InterruptedException e) {
           /* logger.info(e);*/

        }
    }

    @Override
    public String toString() {
        return "Train [route=" + route + "]";
    }

}
