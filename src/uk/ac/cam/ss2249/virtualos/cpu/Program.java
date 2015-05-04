package uk.ac.cam.ss2249.virtualos.cpu;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by sam on 01/05/15.
 */
public class Program {
    private static final int HEAP_SIZE = 1024;

    private String[] instructions;
    private byte[] heap;
    private int heapPointer;

    Program(String[] i){
        instructions = i;
        heap = new byte[HEAP_SIZE];
        heapPointer = 0;
    }

    public static Program programFromFile(String filename) throws IOException {
        return programFromReader(new FileReader(filename));
    }

    public String instructionAtAddress(int address){
        if(address < 0 || address >= instructions.length){
            return null;
        }
        return instructions[address];
    }

    private static Program programFromReader(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        ArrayList<String> instructionAL = new ArrayList<String>();
        String line;
        while ((line = br.readLine()) != null) {
            if(line.length() > 0 && line.charAt(0) != '#'){
                instructionAL.add(line);
            }
        }
        return new Program(instructionAL.toArray(new String[instructionAL.size()]));
    }
}
