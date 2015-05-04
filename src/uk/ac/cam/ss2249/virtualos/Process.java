package uk.ac.cam.ss2249.virtualos;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sam on 04/03/15.
 */
public class Process {
    private Process cont = this;
    private int progress;
    private int length;

    private Process(int length){
        this.progress = 0;
        this.length = length;
    }

    public boolean doWork(){
        return ++progress > length;
    }

    public static Process getIdleProcess(){
        return new Process(0);
    }

    public static Process getInit(){
        return new Process(10);
    }

    public Process fork(){
        Process process = new Process(length);
        process.progress = progress;
        return process;
    }

    private void execWithLength(int length){
        this.length = length;
        this.progress = 0;
    }

    public void exec(int length){
        execWithLength(length);
        Scheduler.getInstance().addProcess(this);
    }

    public void exec(int length, long delay){
        execWithLength(length);
        Timer timer = new Timer();
        TimerTask delayedThreadStartTask = new TimerTask() {
            @Override
            public void run() {
                Scheduler.getInstance().addProcess(cont);
            }
        };
        timer.schedule(delayedThreadStartTask, Clock.time(delay));
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Process: length = ");
        sb.append(length);
        sb.append(", progress = ");
        sb.append(progress);
        sb.append(" (");
        sb.append(100 * (((double) progress) / ((double) length)));
        sb.append("%)");
        return sb.toString();
    }
}
