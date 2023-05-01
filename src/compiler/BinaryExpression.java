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

public class BinaryExpression extends Expression{

	//Constructor 
	public BinaryExpression(Expression l, Expression r, String o) {
		lhs =l;
		rhs = r;
		op = o;
	}

	private Operation.OperationType opToType(String o){
		switch(o){
			case "+":
			  	return OperationType.ADD_I;
			case "-":
				return OperationType.SUB_I;
			case "*":
				return OperationType.MUL_I;
			case "/": 
				return OperationType.DIV_I;
			case "!=":
				return OperationType.NOT_EQUAL;
			case ">":
				return OperationType.GT;
			case "<":
				return OperationType.LT;
			case ">=":
				return OperationType.GTE;
			case "<=":
				return OperationType.LTE;
			case "==":
				return OperationType.EQUAL;
			default:
			 return OperationType.UNKNOWN;
		}

	}
	
	
	@Override
	protected void print(String indent,FileWriter f) throws Exception {
		
		System.out.println(indent + op );
		f.write(indent + op +"\n");
		lhs.print("\t"+indent,f);
		rhs.print("\t"+indent,f);
		
	}
	protected void setRegNum(int n){
		regNum = n;
	}
	protected int getRegNum(){
		return regNum;
	}
	protected void genCode(Function f) throws Exception{
		lhs.genCode(f);
		rhs.genCode(f);
		//Get lhs result register
		int leftRegNum = lhs.getRegNum();
		Operand l = new Operand(Operand.OperandType.REGISTER, leftRegNum);
		//get rhs result register
		int rightRegNum = rhs.getRegNum();
		Operand r = new Operand(Operand.OperandType.REGISTER, rightRegNum);

		//Get the operation type and annotate with a new register number
		OperationType t = opToType(op);
		int destRegNum = f.getNewRegNum();
		Operand destOperand = new Operand(OperandType.REGISTER, destRegNum);
		Operation operation = new Operation(t, f.getCurrBlock());
		operation.setSrcOperand(0, l);
		operation.setSrcOperand(1, r);
		operation.setDestOperand(0, destOperand);
		f.getCurrBlock().appendOper(operation);
		setRegNum(destRegNum);
	}
	
	Expression lhs;
	Expression rhs;
	String op;
	int regNum;
	
	
}
