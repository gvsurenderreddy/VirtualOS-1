package uk.ac.cam.ss2249.virtualos;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sam on 04/03/15.
 */
public class PIC {
    private static PIC instance;
    private Interrupt currentInterrupt;

    private PIC(){

    }

    public static PIC getInstance(){
        if(instance == null){
            instance = new PIC();
        }
        return instance;
    }

    public boolean hasInterrupt(){
        return currentInterrupt != null;
    }

    public Interrupt getInterrupt(){
        Interrupt interrupt = currentInterrupt;
        currentInterrupt = null;
        return interrupt;
    }

    public void interruptWithHandlerDelay(long delay, final InterruptHandler handler, final Object... args){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                interruptWithHandler(handler, args);
            }
        }, delay);
    }

    public void interruptWithHandlerDelayRepeat(long delay, long period, final InterruptHandler handler, final Object... args){
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                interruptWithHandler(handler, args);
            }
        }, delay, period);
    }

    public void interruptWithHandler(InterruptHandler handler, Object... args){
        Interrupt interrupt = new Interrupt(args);
        InterruptTable.getInstance().setHandlerForInterrupt(interrupt, handler);
        currentInterrupt = interrupt;
    }
}
