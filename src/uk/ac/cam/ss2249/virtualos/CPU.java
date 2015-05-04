package uk.ac.cam.ss2249.virtualos;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sam on 04/03/15.
 */
public class CPU {
    private static CPU instance;

    private Process currentProcess;

    private CPU(){

    }

    public static CPU getInstance(){
        if(instance == null){
            instance = new CPU();
        }
        return instance;
    }

    public void boot(){
        loadProcess(Process.getIdleProcess());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tick();
            }
        }, 0, 1000);
    }

    public Process getCurrentProcess() {
        return currentProcess;
    }

    public void loadProcess(Process process){
        currentProcess = process;
    }

    public void tick(){
        System.out.println("DOING WORK ON " + currentProcess);
        boolean finished = currentProcess.doWork();
        if(finished){
            PIC.getInstance().interruptWithHandler(Scheduler.getInstance(), "finished", currentProcess);
        }
        checkForInterrupt();
    }

    private void checkForInterrupt(){
        if(PIC.getInstance().hasInterrupt()){
            Interrupt interrupt = PIC.getInstance().getInterrupt();
            InterruptHandler handler = InterruptTable.getInstance().getHandlerFor(interrupt);
            handler.handleInterrupt(interrupt);
        }
    }
}
