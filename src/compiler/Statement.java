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
    protected abstract void print(String s, FileWriter f) throws IOException, Exception;

    protected abstract void genCode(Function f) throws Exception;

	// This is probably very very wrong...just an educational guess T-T
	// Add this variable to determine statement type
    protected enum StmtType {
        ExprStatement,
        CompStatement,
        IterStatement,
        RetStatement,
        SelsStatement
    }

    protected static StmtType getStmtType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStmtType'");
    }

    protected StmtType stmtType = StmtType.ExprStatement;

    protected static void genCode(Function f, int regNum) {
        switch (getStmtType()) {
            case ExprStatement:
                ExprStatement.genCode(f, regNum);
                break;
            case CompStatement:
                CompStatement.genCode(f, regNum);
                break;
            case IterStatement:
                IterStatement.genCode(f, regNum);
                break;
            case RetStatement:
                RetStatement.genCode(f, regNum);
                break;
            case SelsStatement:
                SelsSatement.genCode(f, regNum);
                break;
            default:
                throw new RuntimeException("Invalid Statement Type");
        }
    }
}

