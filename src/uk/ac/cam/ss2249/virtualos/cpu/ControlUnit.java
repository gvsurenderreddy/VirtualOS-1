package uk.ac.cam.ss2249.virtualos.cpu;

/**
 * Created by sam on 01/05/15.
 */
public class ControlUnit {
    private static ControlUnit instance;
    private Program cProgram;

    private ControlUnit(){

    }

    public static ControlUnit getInstance(){
        if(instance == null){
            instance = new ControlUnit();
        }
        return instance;
    }

    public void loadProgram(Program p){
        cProgram = p;
        fetch();
    }

    public void fetch(){
        int pci = RegisterFile.getInstance().getProgramCounter().readInt();
        String instructionString = cProgram.instructionAtAddress(pci);
        if(instructionString == null){
            finishedProgram();
            return;
        }
        System.out.println("Executing " + instructionString + "...");
        Instruction instruction = Instruction.decodeInstruction(instructionString);
        ExecutionUnit.getInstance().execute(instruction);
        RegisterFile.getInstance().getProgramCounter().increment();
        //System.out.println(RegisterFile.getInstance().getRegister(8).readInt());
        fetch();
    }

    private void finishedProgram(){
        System.out.println("Finished!");
    }
}
