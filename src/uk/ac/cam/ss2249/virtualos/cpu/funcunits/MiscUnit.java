package uk.ac.cam.ss2249.virtualos.cpu.funcunits;

import uk.ac.cam.ss2249.virtualos.cpu.Instruction;
import uk.ac.cam.ss2249.virtualos.cpu.Operand;
import uk.ac.cam.ss2249.virtualos.cpu.RegisterFile;

/**
 * Created by sam on 01/05/15.
 */
public class MiscUnit extends FunctionalUnit {
    private static MiscUnit instance;

    private MiscUnit(){

    }

    public static MiscUnit getInstance(){
        if(instance == null){
            instance = new MiscUnit();
        }
        return instance;
    }

    @Override
    public void executeInstruction(Instruction instruction) {
        switch (instruction.getOp()){
            case MOV:
                mov(instruction.getOperands());
                break;
            case PI:
                pi(instruction.getOperands());
                break;
            case PB:
                pb(instruction.getOperands());
                break;
        }
    }

    private void mov(Operand[] operands){
        int n = getValue(operands[1]);
        RegisterFile.getInstance().getRegister(operands[0].getRegisterName()).writeInt(n);
    }

    private void pi(Operand[] operands){
        System.out.println(RegisterFile.getInstance().getRegister(operands[0].getRegisterName()).readInt());
    }

    private void pb(Operand[] operands){
        System.out.println(RegisterFile.getInstance().getRegister(operands[0].getRegisterName()));
    }
}
