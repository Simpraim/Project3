package compiler;

import java.io.FileWriter;
import java.io.IOException;
import x64codegen.X64AssemblyGenerator;
//import parser.*;
import lowlevel.*;
import java.util.*;
import java.io.*;
import optimizer.*;
import x86codegen.*;
import x64codegen.*;
import dataflow.*;

public abstract class Statement {
    protected abstract void print(String s, FileWriter f) throws IOException;

    protected abstract void genCode(Function f);

	// This is probably very very wrong...just an educational guess T-T
	// Add this variable to determine statement type
    protected enum StmtType {
        ExprStatement,
        CompStatement,
        IterStatement,
        RetStatement,
        SelsStatement
    }

    protected abstract StmtType getStmtType();

    protected StmtType stmtType = StmtType.ExprStatement;

    protected void genCode(Function f, int regNum) {
        switch (getStmtType()) {
            case ExprStatement:
                ((ExprStatement) this).genCode(f, regNum);
                break;
            case CompStatement:
                ((CompStatement) this).genCode(f, regNum);
                break;
            case IterStatement:
                ((IterStatement) this).genCode(f, regNum);
                break;
            case RetStatement:
                ((RetStatement) this).genCode(f, regNum);
                break;
            case SelsStatement:
                ((SelsStatement) this).genCode(f, regNum);
                break;
            default:
                throw new RuntimeException("Invalid Statement Type");
        }
    }
}

