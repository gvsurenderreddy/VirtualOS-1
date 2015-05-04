package uk.ac.cam.ss2249.virtualos.cpu.funcunits;

import uk.ac.cam.ss2249.virtualos.cpu.Instruction;
import uk.ac.cam.ss2249.virtualos.cpu.RegisterFile;

/**
 * Created by sam on 01/05/15.
 */
public class ALU extends FunctionalUnit{
    private static ALU instance;

    private ALU(){

    }

    public static ALU getInstance(){
        if(instance == null){
            instance = new ALU();
        }
        return instance;
    }

    @Override
    public void executeInstruction(Instruction instruction) {
        int a = getValue(instruction.getOperands()[1]);
        int b = getValue(instruction.getOperands()[2]);
        int d = 0;
        switch (instruction.getOp()){
            case AND:
                d = a & b;
                break;
            case XOR:
                d = a ^ b;
                break;
            case BIS:
                d = a | b;
                break;
            case BIC:
                d = a & (~b);
                break;
            case ADD:
                d = a + b;
                break;
            case SUB:
                d = a - b;
                break;
            case RSB:
                d = b - a;
                break;
            case SHL:
                d = a << b;
                break;
            case SHR:
                d = a >> b;
                break;
        }
        if(instruction.getOperands()[0].isRegister()){
            RegisterFile.getInstance().getRegister(instruction.getOperands()[0].getValue()).writeInt(d);
        }
    }

}
