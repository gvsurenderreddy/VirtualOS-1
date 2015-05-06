package uk.ac.cam.ss2249.virtualos.cpu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sam on 01/05/15.
 */
public class Instruction{
    private OpCode op;
    private Operand[] operands;

    Instruction(OpCode op, Operand[] operands){
        this.op = op;
        this.operands = operands;
    }

    public OpCode getOp() {
        return op;
    }

    public Operand[] getOperands() {
        return operands;
    }

    static Instruction decodeInstruction(String ins){
        Pattern p = Pattern.compile("\\t([a-z]+)\\t?(.*)");
        Matcher m = p.matcher(ins);

        if(m.find()){
            int count = m.groupCount();
            System.out.println("group count is "+count);
            String code = m.group(1);
            String operandsRaw = m.groupCount() >= 2 ? m.group(3) : "";
            String[] operands = operandsRaw.split(", ");
            Operand[] operandsA = new Operand[operands.length];
            for(int i=0; i<operands.length; i++){
                operandsA[i] = new Operand(operands[i]);
            }
            return new Instruction(OpCode.valueOf(code.toUpperCase()), operandsA);
        }
        return null;
    }

    static Instruction decodeInstruction2(String ins){
        String[] split = ins.split(" ");
        Operand[] op = new Operand[split.length - 1];
        for(int i=1; i<split.length; i++){
            op[i-1] = new Operand(split[i]);
        }
        return new Instruction(OpCode.valueOf(split[0].toUpperCase()), op);
    }

}
