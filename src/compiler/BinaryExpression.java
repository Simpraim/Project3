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

public class BinaryExpression extends Expression{

	//Constructor 
	public BinaryExpression(Expression l, Expression r, String o) {
		lhs =l;
		rhs = r;
		op = o;
	}
	
	
	@Override
	protected void print(String indent,FileWriter f) throws IOException {
		
		System.out.println(indent + op );
		f.write(indent + op +"\n");
		lhs.print("\t"+indent,f);
		rhs.print("\t"+indent,f);
		
	}
	protected void setRegNum(int n){
		regNum = n;
	}
	protected void genCode(Function f){
		
	}
	
	Expression lhs;
	Expression rhs;
	String op;
	int regNum;
	
	
}
