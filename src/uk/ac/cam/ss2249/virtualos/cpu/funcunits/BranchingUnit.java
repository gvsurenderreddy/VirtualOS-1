package uk.ac.cam.ss2249.virtualos.cpu.funcunits;

import uk.ac.cam.ss2249.virtualos.cpu.Instruction;
import uk.ac.cam.ss2249.virtualos.cpu.RegisterFile;

/**
 * Created by sam on 01/05/15.
 */
public class BranchingUnit extends FunctionalUnit {
    private static BranchingUnit instance;

    private BranchingUnit(){

    }

    public static BranchingUnit getInstance(){
        if(instance == null){
            instance = new BranchingUnit();
        }
        return instance;
    }

    @Override
    public void executeInstruction(Instruction instruction) {
        switch (instruction.getOp()){
            case BEQ:
                beq(instruction.getOperands());
                break;
            case BNE:
                bne(instruction.getOperands());
                break;
            case J:
                j(instruction.getOperands());
                break;
            case BLEZ:
                blez(instruction.getOperands());
                break;
        }
    }

    private void offsetPC(int offset){
        RegisterFile.getInstance().getProgramCounter().increment(offset);
    }

    private void beq(Instruction.Operand[] operands){
        if(getValue(operands[0]) == getValue(operands[1])){
            offsetPC(getValue(operands[2]));
        }
    }

    private void bne(Instruction.Operand[] operands){
        if(getValue(operands[0]) != getValue(operands[1])){
            offsetPC(getValue(operands[2]));
        }
    }

    private void j(Instruction.Operand[] operands){
        offsetPC(getValue(operands[0]));
    }

    private void blez(Instruction.Operand[] operands){
        if(getValue(operands[0]) <= 0){
            offsetPC(getValue(operands[1]));
        }
    }
}
