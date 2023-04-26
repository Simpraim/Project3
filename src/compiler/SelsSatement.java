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

	protected void genCode(Function f){
		
	}
	
	Expression expr;
	Boolean isElse;
	Statement ifStatement;
	Statement elStatement;

}
