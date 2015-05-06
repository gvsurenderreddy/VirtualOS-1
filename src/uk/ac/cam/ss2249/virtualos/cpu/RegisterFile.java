package uk.ac.cam.ss2249.virtualos.cpu;

import java.util.HashMap;

/**
 * Created by sam on 01/05/15.
 */
public class RegisterFile {
    private static RegisterFile instance;
    private static int REGISTER_SIZE = 32;
    private HashMap<String, Register> registers;

    private RegisterFile(){
        registers = new HashMap<String, Register>();
    }

    public static RegisterFile getInstance(){
        if(instance == null){
            instance = new RegisterFile();
        }
        return instance;
    }

    public Register getRegister(String name){
        if(!registers.containsKey(name)){
            registers.put(name, new Register(32));
        }
        return registers.get(name);
    }

    public Register getProgramCounter(){
        return registers.get("pc");
    }

    public class Register{
        private boolean[] data;

        Register(int bits){
            data = new boolean[bits];
        }

        public void writeInt(int n){
            for(int i=0; i<data.length; i++){
                data[i] = (n & 1) == 1;
                n >>= 1;
            }
        }

        public int readInt(){
            int n = 0;
            int c = 1;
            for(int i=0; i<data.length; i++){
                if(data[i]){
                    n |= c;
                }
                c <<= 1;
            }
            return n;
        }

        public void increment(){
            increment(1);
        }

        public void increment(int n){
            writeInt(readInt() + n);
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            for(int i=data.length - 1; i>=0; i--){
                sb.append(data[i] ? '1' : '0');
            }
            return sb.toString();
        }
    }
}
