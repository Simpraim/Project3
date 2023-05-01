package compiler;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
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

public class CallExpression extends Expression {

	public CallExpression(String id, ArrayList<Expression> el) {
		Identifier = id;
		expressionList = el;
	}
	
	@Override
	protected void print(String indent,FileWriter f) throws Exception {
		System.out.println(indent+ Identifier + "(");
		f.write(indent+ Identifier + "(\n");
		if (expressionList.size() > 0) {
		  //expressionList.get(0).print("    "+indent);
		  for (int i = 0; i < expressionList.size(); i++) {
			expressionList.get(i).print("\t"+indent,f);
		  }
		}
		System.out.println(indent+")");
		f.write(indent+")\n");
		
	
		
	}
	protected void setRegNum(int n){
		regNum = n;
	}
	protected int getRegNum(){
		return regNum;
	}
	protected void genCode(Function f)throws Exception{
		//loop through params
		int paramCount = 0;
		for(Expression e:expressionList){
			try {
				
				e.genCode(f);
				int regNum = e.getRegNum();
				//Create an operand
				Operand newOperand = new Operand(OperandType.REGISTER, regNum);
				//Create a pass operation 
				Operation operation = new Operation(OperationType.PASS, f.getCurrBlock());
				Attribute a = new Attribute("PARAM_NUM",Integer.toString(paramCount));
				operation.addAttribute(a);
				paramCount++;
				operation.setSrcOperand(0, newOperand);
				//append operation to function current block
				f.getCurrBlock().appendOper(operation);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		};
		//create call operation
		int newReg = f.getNewRegNum();
		Operand function_string = new Operand(OperandType.STRING, Identifier);
		Operation function = new Operation(OperationType.CALL, f.getCurrBlock());
		Attribute a1 = new Attribute("numParams",Integer.toString(expressionList.size()));
		function.addAttribute(a1);
		function.setSrcOperand(0, function_string);
		f.getCurrBlock().appendOper(function);
		Operation retVal = new Operation(OperationType.ASSIGN, f.getCurrBlock());
		Operand regOperand = new Operand(OperandType.REGISTER, newReg);
		Operand src = new Operand(OperandType.MACRO, "RetReg");
		retVal.setSrcOperand(0, src);
		retVal.setDestOperand(0, regOperand);
		f.getCurrBlock().appendOper(retVal);
		setRegNum(newReg);
		
		
		
		//create assign operation
		

	}
	
	String Identifier;
	ArrayList<Expression> expressionList;
	

}
