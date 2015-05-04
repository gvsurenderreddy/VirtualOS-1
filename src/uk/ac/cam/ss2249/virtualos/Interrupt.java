package uk.ac.cam.ss2249.virtualos;

/**
 * Created by sam on 04/03/15.
 */
public class Interrupt {
    public static int nextId = 0;
    private int id;
    private Object[] args;

    Interrupt(Object[] a){
        args = a;
        id = nextId++;
    }

    public Object[] getArgs() {
        return args;
    }

    public int getId() {
        return id;
    }
}
