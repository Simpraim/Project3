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
			
		}
		//Jump to return block
		
	}

	
	Expression expr;

}
