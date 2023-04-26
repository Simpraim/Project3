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
		
	}
	
	
	String Identifier;
	Expression expr;
	
}
