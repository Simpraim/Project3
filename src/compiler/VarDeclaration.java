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

public class VarDeclaration extends Declaration {

	
	//Constructor 
	public VarDeclaration(String i, Boolean iArr,String idx) {
		Identifier = i;
		IsArray = iArr;
		if(IsArray==true)ArrayIndex = idx;
		
		
	}
	@Override
	protected void print(String indent,FileWriter f) throws IOException {
		if(IsArray){
			System.out.println(indent + "int "+Identifier + "["+String.valueOf(ArrayIndex) + "]");
			f.write(indent + "int "+Identifier + "["+String.valueOf(ArrayIndex) + "]\n");
		}
		else{
			System.out.println(indent + "int "+ Identifier);
			f.write(indent + "int "+ Identifier+ "\n");
		}
		
	}
	@Override
	protected void genCode() {
		
		
	}
	protected void genCode(Function f){

	}
	
	
	String Identifier;
	Boolean IsArray;
	String ArrayIndex;
	
	
}
