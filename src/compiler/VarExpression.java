package compiler;

import java.io.FileWriter;
import java.io.IOException;
import x64codegen.X64AssemblyGenerator;
//import parser.*;
import lowlevel.*;
import lowlevel.Operand.OperandType;
import lowlevel.Operation.OperationType;

import java.util.*;
import java.io.*;
import optimizer.*;
import x86codegen.*;
import x64codegen.*;
import dataflow.*;

public class VarExpression extends Expression {

	
	//Constructor 
	public VarExpression(String i, Expression e) {
		Identifier = i;
		expr = e;
	}
	@Override
	protected void print(String indent,FileWriter f) throws Exception {
		System.out.println(indent +Identifier);
		f.write(indent +Identifier+ "\n");
		if (expr != null) {
		  System.out.println("\t"+indent+"[");
		  f.write("\t"+indent+"[\n");
		  expr.print("\t"+indent,f);
		  System.out.println("\t"+indent+"]");
		  f.write("\t"+indent+"]\n");
		}
		
	}

	protected void setRegNum(int n){
		regNum = n;
	}
	protected int getRegNum(){
		return regNum;
	}

	@Override
	protected void genCode(Function f) throws Exception{
		if(f.getTable().containsKey(Identifier)){
		
			setRegNum( (int)f.getTable().get(Identifier));
		}
		else if(CMinusCompiler.globalHash.containsKey(Identifier)){
			
			int location = f.getNewRegNum();
			Operand src0 = new Operand(OperandType.STRING, Identifier);
			Operand dest = new Operand(OperandType.REGISTER, location);
			Operation operation = new Operation(OperationType.LOAD_I, f.getCurrBlock());
			operation.setSrcOperand(0, src0);
			operation.setDestOperand(0, dest);
			f.getCurrBlock().appendOper(operation);
			setRegNum(location);
		}
	}
	
	
	String Identifier;
	Expression expr;
	
}
