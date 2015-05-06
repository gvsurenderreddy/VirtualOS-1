package uk.ac.cam.ss2249.virtualos.cpu;

import java.io.IOException;

/**
 * Created by sam on 01/05/15.
 */
public class Main {
    public static void main(String[] args){
        try {
            Program p = Program.programFromFile("/Users/sam/Downloads/testins.txt");

            //ControlUnit.getInstance().loadProgram(p);

            Instruction.decodeInstruction("\tstr\tr3, r4");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
