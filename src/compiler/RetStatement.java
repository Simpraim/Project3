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

public class RetStatement extends Statement{

	
	//Constructor 
	public RetStatement(Expression el) {
		expr = el;
	}
	@Override
	protected void print(String indent,FileWriter f) throws Exception {
		if(expr != null){
			System.out.println(indent +"return");
			f.write(indent +"return\n");
			expr.print("\t" + indent,f);
			
		}
		else{
			System.out.println(indent + "return");
			f.write(indent + "return");
		}
		
	}
	protected void genCode(Function f) throws Exception{
		if(expr != null){
			expr.genCode(f);
			//get register of resulting expression
			int resultReg = expr.getRegNum();
			//set return register to that value
			Operand retreg = new Operand(OperandType.MACRO, "RetReg");
			Operation assignOper = new Operation(OperationType.ASSIGN,f.getCurrBlock());
			Operand src = new Operand(OperandType.REGISTER, resultReg);
			assignOper.setDestOperand(0, retreg);
			assignOper.setSrcOperand(0, src);
			f.getCurrBlock().appendOper(assignOper);
			
		}
		//Jump to return block
		Operation jmp = new Operation(OperationType.JMP, f.getCurrBlock());
		Operand bjump = new Operand(OperandType.BLOCK, f.getReturnBlock().getBlockNum());
		jmp.setSrcOperand(0, bjump);
		f.getCurrBlock().appendOper(jmp);
		
	}

	
	Expression expr;

}
