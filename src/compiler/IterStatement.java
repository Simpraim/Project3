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

public class IterStatement extends Statement{

	
	//Constructor 
	public IterStatement(Expression e, Statement s) {
		expr = e;
		stmt = s;
	}
	@Override
	protected void print(String indent,FileWriter f) throws Exception {
		System.out.println(indent+"while(");
		f.write(indent+"while(\n");
		expr.print("\t"+indent,f);
		System.out.println(indent+"){");
		f.write(indent+"){\n");
		stmt.print("\t"+indent,f);
		
	}

	protected void genCode(Function f){

	}
	
	Expression expr;
	Statement stmt;

}
