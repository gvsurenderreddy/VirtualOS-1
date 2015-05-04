package uk.ac.cam.ss2249.virtualos.cpu;

/**
 * Created by sam on 01/05/15.
 */
public enum OpCode {
    // ALU
    AND,    // d, a, b
    XOR,
    BIS,
    BIC,
    ADD,
    SUB,
    RSB,
    SHL,
    SHR,

    // Branching
    BEQ,    // a, b, offset
    BNE,    // a, b, offset
    BGEX,   // a, offset
    BGTZ,   // a, offset
    BLEZ,   // a, offset
    BLTZ,   // a, offset
    J,      // offset
    JAL,    // offset
    JR,     // a
    JALR,   // a

    // Multiplier
    MULT,   // dhigh, dlow, a, b

    // MISC
    MOV,    // a, c
    PI,     // a
    PB      // a
}
