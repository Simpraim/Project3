package compiler;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import x64codegen.X64AssemblyGenerator;
//import parser.*;
import lowlevel.*;
import java.util.*;
import java.io.*;
import optimizer.*;
import x86codegen.*;
import x64codegen.*;
import dataflow.*;

public class SelsSatement extends Statement {

	
	//COnstructor 
	public SelsSatement(Expression e, Statement s, Boolean isE, Statement es){
		expr = e;
		ifStatement = s;
		isElse = isE;
		if(isElse == true){
			elStatement = es;
		}
	}
	@Override
	protected void print(String indent,FileWriter f) throws Exception {
		if(isElse){
			System.out.println(indent +"if");
			f.write(indent +"if\n");
			System.out.println(indent +"(");
			f.write(indent +"(\n");
			expr.print("\t" + indent,f);
			System.out.println(indent+"){");
			f.write(indent+"){\n");
			ifStatement.print("\t" + indent,f);
			System.out.println(indent+"}");
			f.write(indent+"}\n");
			System.out.println( indent +"else");
			f.write( indent +"else\n");
			System.out.println(indent +"{");
			f.write(indent +"{\n");
			elStatement.print("\t" + indent,f);
			System.out.println(indent+"}");
			f.write(indent+"}\n");
		}
		else{
			System.out.println(indent + "if");
			f.write(indent + "if\n");
			expr.print("\t" + indent,f);
			ifStatement.print("\t" + indent,f);
		}
		
	}

	protected void genCode(Function f) throws Exception {

		// 1) make 2/3 blocks

		BasicBlock exprBlock = new BasicBlock(f);
		BasicBlock stmtBlock = new BasicBlock(f);
		BasicBlock elseStmtBlock = new BasicBlock(f);
		BasicBlock postBlock = new BasicBlock(f);

		f.appendToCurrentBlock(exprBlock);
		f.setCurrBlock(exprBlock);

		// 2) Gencode Exper

		expr.genCode(f);

		//how do we get the regnum?
		int exprRegNum = expr.getRegNum();

		// 3) createbranch

		Operation branchOper = new Operation(Operation.OperationType.BEQ, exprBlock);
		Operand src0 = new Operand(Operand.OperandType.REGISTER, exprRegNum);
		Operand src1 = new Operand(Operand.OperandType.INTEGER, 0);
		Operand src2 = null;
		if (isElse) {
			src2 = new Operand(Operand.OperandType.BLOCK, elseStmtBlock.getBlockNum());
		} else {
			src2 = new Operand(Operand.OperandType.BLOCK, postBlock.getBlockNum());
		}

		branchOper.setSrcOperand(0, src0);
		branchOper.setSrcOperand(1, src1);
		branchOper.setSrcOperand(2, src2);

		f.getCurrBlock().appendOper(branchOper);

		// 4) append then

		f.appendToCurrentBlock(stmtBlock);

		// 5) CB = then

		f.setCurrBlock(stmtBlock);

		// 6) GenCode then

		ifStatement.genCode(f);

		// 7) append post

		f.appendToCurrentBlock(postBlock);

		if (isElse) {

			// 8) CB = else

			f.setCurrBlock(elseStmtBlock);

			// 9) gencode else

			elStatement.genCode(f);

			// 10) append jmp to post

			Operation jumpOper = new Operation(Operation.OperationType.JMP, f.getCurrBlock());
			Operand jmpSrc0 = new Operand(Operand.OperandType.BLOCK, postBlock.getBlockNum());
			jumpOper.setSrcOperand(0, jmpSrc0);

			f.getCurrBlock().appendOper(jumpOper);

			// 11) add else to unconnected chain

			f.appendUnconnectedBlock(elseStmtBlock);

		}

		// 12) CB = post

		f.setCurrBlock(postBlock);

	}
	
	Expression expr;
	Boolean isElse;
	Statement ifStatement;
	Statement elStatement;

}
