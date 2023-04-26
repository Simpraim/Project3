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
		expressionList.forEach((expr)->{
			try {
				expr.genCode(f);
				int regNum = expr.getRegNum();
				//Create an operand
				Operand newOperand = new Operand(OperandType.REGISTER, regNum);
				//Create a pass operation 
				Operation operation = new Operation(OperationType.PASS, f.getCurrBlock());
				operation.setSrcOperand(0, newOperand);
				//append operation to function current block
				f.getCurrBlock().appendOper(operation);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		//create call operation
		Operand function_string = new Operand(OperandType.STRING, Identifier);
		Operation function = new Operation(OperationType.CALL, f.getCurrBlock());
		function.setSrcOperand(0, function_string);
		f.getCurrBlock().appendOper(function);
		//Where do we set these expression register numbers? regNum = ???

	}
	
	String Identifier;
	ArrayList<Expression> expressionList;
	

}
