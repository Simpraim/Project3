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

public class AssignExpression extends Expression {

	
	
	//Constructor
	public AssignExpression(VarExpression id, Expression r) {
		v = id;
		rhs = r;
	}
	
	
	protected void print(String indent,FileWriter f) throws IOException {
		
		System.out.println(indent+"=");
		f.write(indent+"=\n");
		v.print("\t"+ indent,f);
		rhs.print("\t"+indent,f);
		
		
	}
	protected void setRegNum(int n){
		regNum = n;
	}
	@Override
	protected void genCode(Function f) {
		
	}
	
	VarExpression v; //Var Expression
	Expression rhs;
	int regNum;
	

}
