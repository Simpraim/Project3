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

public class NumExpression extends Expression{

	//Constructor 
	public NumExpression(int n) {
		num = n;
		
	}
	@Override
	protected void print(String indent,FileWriter f) throws IOException {
		System.out.println(indent+String.valueOf(num));
		f.write(indent+String.valueOf(num)+"\n");
		
	}
	protected void setRegNum(int n){
		regNum = n;
	}
	@Override
	protected void genCode(Function f) {
		
	}
	
	int regNum;
	int num;
	
}
