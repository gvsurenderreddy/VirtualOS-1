package uk.ac.cam.ss2249.virtualos;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sam on 04/03/15.
 */
public class InterruptTable {
    private static InterruptTable instance;

    private Map<Interrupt, InterruptHandler> table;

    private InterruptTable(){
        table = new HashMap<Interrupt, InterruptHandler>();
    }

    public static InterruptTable getInstance(){
        if(instance == null){
            instance = new InterruptTable();
        }
        return instance;
    }

    public InterruptHandler getHandlerFor(Interrupt interrupt){
        return table.remove(interrupt);
    }

    public void setHandlerForInterrupt(Interrupt interrupt, InterruptHandler handler){
        table.put(interrupt, handler);
    }
}
