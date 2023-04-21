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

public class ExprStatement extends Statement{

	
	
	//Constructor 
	public ExprStatement(Expression el) {
		exprList = el;
	}
	@Override
	protected void print(String indent,FileWriter f) throws IOException {
		if(exprList != null){
		exprList.print("\t" + indent,f);
		
		}
		else{
			
		}
	}

	protected void genCode(Function f){
		exprList.genCode(f);
	}
	
	Expression exprList;

}