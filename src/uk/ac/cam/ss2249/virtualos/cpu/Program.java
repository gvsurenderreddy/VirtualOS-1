package uk.ac.cam.ss2249.virtualos.cpu;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by sam on 01/05/15.
 */
public class Program {
    private static final int MEMORY_SIZE = 1024;

    private String[] instructions;
    private byte[] memory;

    Program(String[] i){
        instructions = i;
        memory = new byte[MEMORY_SIZE];
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

    public byte getMem(int address){
        return memory[address];
    }

    public int getMemInt(int address){
        int n = 0;
        for(int i=0; i<4; i++){
            n |= (getMem(address + i) << (i*8));
        }
        return n;
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
