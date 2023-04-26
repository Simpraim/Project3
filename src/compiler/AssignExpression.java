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

public class AssignExpression extends Expression {

	
	
	//Constructor
	public AssignExpression(VarExpression id, Expression r) {
		v = id;
		rhs = r;
	}
	
	
	protected void print(String indent,FileWriter f) throws Exception {
		
		System.out.println(indent+"=");
		f.write(indent+"=\n");
		v.print("\t"+ indent,f);
		rhs.print("\t"+indent,f);
		
		
	}
	protected void setRegNum(int n){
		regNum = n;
	}
	protected int getRegNum(){
		return regNum;
	}
	@Override
	protected void genCode(Function f) throws Exception {
		String variableName = v.Identifier;
		if(f.getTable().containsKey(variableName)){
			Operation operation = new Operation(OperationType.ASSIGN,f.getCurrBlock());
			Operand src = new Operand(OperandType.REGISTER, rhs.getRegNum());
			Operand dest = new Operand(OperandType.REGISTER, v.getRegNum());
			operation.setSrcOperand(rhs.getRegNum(), src);
			operation.setDestOperand(v.getRegNum(), dest);

		}
		else if(CMinusCompiler.globalHash.containsKey(variableName)){

		}
		else{
			throw new Exception("Variable has not been declared");
		}

		
		
	
		
	
		
	}
	
	VarExpression v; //Var Expression
	Expression rhs;
	int regNum;
	

}
