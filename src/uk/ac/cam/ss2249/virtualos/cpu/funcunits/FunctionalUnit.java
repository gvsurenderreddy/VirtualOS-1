package uk.ac.cam.ss2249.virtualos.cpu.funcunits;

import uk.ac.cam.ss2249.virtualos.cpu.Instruction;
import uk.ac.cam.ss2249.virtualos.cpu.Operand;
import uk.ac.cam.ss2249.virtualos.cpu.RegisterFile;

/**
 * Created by sam on 01/05/15.
 */
public abstract class FunctionalUnit {
    public abstract void executeInstruction(Instruction instruction);

    protected int getValue(Operand operand){
        return operand.getTrueValue();
    }
}
