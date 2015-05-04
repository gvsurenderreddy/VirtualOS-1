package uk.ac.cam.ss2249.virtualos;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by sam on 04/03/15.
 */
public class Scheduler implements InterruptHandler {
    private static Scheduler instance;
    private Queue<Process> readyQueue;
    private long quantum = 2000;

    private Scheduler(){
        readyQueue = new ConcurrentLinkedQueue<Process>();
        startQuantumInterrupt();
    }

    public static Scheduler getInstance(){
        if(instance == null){
            instance = new Scheduler();
        }
        return instance;
    }

    public void addProcess(Process process){
        readyQueue.add(process);
    }

    private void processHasFinished(Process process){
        System.out.println("Process has finished");
        Process next = getNextProcess();
        CPU.getInstance().loadProcess(next);
    }

    private Process getNextProcess(){
        if(readyQueue.isEmpty()) {
            return Process.getIdleProcess();
        } else {
            return readyQueue.remove();
        }
    }

    private void quantumPassed(){
        System.out.println("Quantum Passed");
        readyQueue.add(CPU.getInstance().getCurrentProcess());
        CPU.getInstance().loadProcess(getNextProcess());
    }

    private void startQuantumInterrupt(){
        PIC.getInstance().interruptWithHandlerDelayRepeat(0, quantum, this, "schedtick");
    }

    @Override
    public void handleInterrupt(Interrupt interrupt) {
        Object[] args = interrupt.getArgs();
        if(args[0].equals("finished")){
            processHasFinished((Process) args[1]);
        }else if(args[0].equals("schedtick")){
            quantumPassed();
        }
    }
}
