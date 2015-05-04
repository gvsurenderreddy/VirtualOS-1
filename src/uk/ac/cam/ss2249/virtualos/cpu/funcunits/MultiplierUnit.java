package uk.ac.cam.ss2249.virtualos.cpu.funcunits;

import uk.ac.cam.ss2249.virtualos.cpu.Instruction;
import uk.ac.cam.ss2249.virtualos.cpu.RegisterFile;

/**
 * Created by sam on 02/05/15.
 */
public class MultiplierUnit extends FunctionalUnit {
    private static MultiplierUnit instance;

    private MultiplierUnit(){

    }

    public static MultiplierUnit getInstance(){
        if(instance == null){
            instance = new MultiplierUnit();
        }
        return instance;
    }

    @Override
    public void executeInstruction(Instruction instruction) {
        switch (instruction.getOp()){
            case MULT:
                mult(instruction.getOperands());
                break;
        }
    }

    private void mult(Instruction.Operand[] operands){
        int a = getValue(operands[2]);
        int b = getValue(operands[3]);
        long result = a * b;
        int high = (int) (result >> 32);
        int low = (int) (result & ((1L << 33)-1L));
        RegisterFile.getInstance().getRegister(operands[0].getValue()).writeInt(high);
        RegisterFile.getInstance().getRegister(operands[1].getValue()).writeInt(low);
    }
}
