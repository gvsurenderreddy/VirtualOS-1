package uk.ac.cam.ss2249;

import uk.ac.cam.ss2249.virtualos.*;
import uk.ac.cam.ss2249.virtualos.Process;

/**
 * Created by sam on 04/03/15.
 */
public class Main {
    public static void main(String[] args){
        CPU.getInstance().boot();
        Process init = Process.getInit();
        init.exec(100, 500);
        init.fork().exec(50, 5000);
    }
}
