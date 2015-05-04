package uk.ac.cam.ss2249.virtualos.cpu;

import uk.ac.cam.ss2249.virtualos.cpu.funcunits.*;

/**
 * Created by sam on 01/05/15.
 */
public class ExecutionUnit {
    private static ExecutionUnit instance;

    private ExecutionUnit(){

    }

    public static ExecutionUnit getInstance(){
        if(instance == null){
            instance = new ExecutionUnit();
        }
        return instance;
    }

    public void execute(Instruction instruction){
        FunctionalUnit fu = null;
        switch (instruction.getOp()){
            case ADD:
            case SUB:
                fu = ALU.getInstance();
                break;
            case BEQ:
            case BNE:
            case J:
            case BLEZ:
                fu = BranchingUnit.getInstance();
                break;
            case MULT:
                fu = MultiplierUnit.getInstance();
                break;
            case MOV:
            case PI:
            case PB:
                fu = MiscUnit.getInstance();
                break;
        }

        fu.executeInstruction(instruction);
    }
}
