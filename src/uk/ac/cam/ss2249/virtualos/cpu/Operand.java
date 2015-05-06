package uk.ac.cam.ss2249.virtualos.cpu;

/**
 * Created by sam on 06/05/15.
 */
public class Operand{
    private enum Type{
        REGISTER,
        RAW,
        MEMORY,
        MULTIPLE
    }

    private Type type;
    private Object value;
    private Operand offset;

    Operand(String o){
        switch(o.charAt(0)){
            case '%':
                type = Type.REGISTER;
                value = o.substring(1);
            case '$':
                type = Type.RAW;
                value = Integer.parseInt(o.substring(1));
                break;
            case '[':
                type = Type.MEMORY;
                String[] parts = o.substring(1, o.length() - 1).split(", ");
                value = new Operand(parts[0]);
                if(parts.length > 1) {
                    offset = new Operand(parts[1]);
                }else{
                    offset = new Operand("#0");
                }
            default:
                type = Type.REGISTER;
                value = o;
                break;
        }
    }

    public int getTrueValue(){
        switch (type){
            case REGISTER:
                return RegisterFile.getInstance().getRegister((String) value).readInt();
            case RAW:
                return ((Integer) value);
            case MEMORY:
                return ControlUnit.getInstance().getProgram().getMemInt(((Operand) value).getTrueValue() + offset.getTrueValue());
            default:
                return 0;
        }
    }
}
