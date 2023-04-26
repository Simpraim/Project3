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

public class NumExpression extends Expression{

	//Constructor 
	public NumExpression(int n) {
		num = n;
		
	}
	@Override
	protected void print(String indent,FileWriter f) throws IOException {
		System.out.println(indent+String.valueOf(num));
		f.write(indent+String.valueOf(num)+"\n");
		
	}
	protected void setRegNum(int n){
		regNum = n;
	}
	protected int getRegNum(){
		return regNum;
	}
	@Override
	protected void genCode(Function f) {
	 int newRegNum = f.getNewRegNum();
	 Operation o = new Operation(OperationType.ASSIGN, f.getCurrBlock());
	 Operand dest = new Operand(OperandType.REGISTER, newRegNum);
	 o.setDestOperand(0, dest);
	 Operand src = new Operand(OperandType.INTEGER, num);
	 o.setSrcOperand(0, src);
	 f.getCurrBlock().appendOper(o);
		
	}
	
	int regNum;
	int num;
	
}
