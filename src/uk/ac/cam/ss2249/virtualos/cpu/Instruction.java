package uk.ac.cam.ss2249.virtualos.cpu;

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
        String[] split = ins.split(" ");
        Operand[] op = new Operand[split.length - 1];
        for(int i=1; i<split.length; i++){
            op[i-1] = new Operand(split[i].charAt(0) == 'r', Integer.parseInt(split[i].substring(1)));
        }
        return new Instruction(OpCode.valueOf(split[0].toUpperCase()), op);
    }

    public static class Operand{
        private boolean isRegister;
        private int value;

        Operand(boolean r, int v){
            isRegister = r;
            value = v;
        }

        public boolean isRegister() {
            return isRegister;
        }

        public int getValue() {
            return value;
        }
    }
}
