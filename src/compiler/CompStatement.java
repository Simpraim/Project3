package compiler;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import x64codegen.X64AssemblyGenerator;
//import parser.*;
import lowlevel.*;
import java.util.*;
import java.io.*;
import optimizer.*;
import x86codegen.*;
import x64codegen.*;
import dataflow.*;

public class CompStatement extends Statement {

	
	
	//Constructor
	public CompStatement(ArrayList<Declaration> dl,ArrayList<Statement> sl) {
		localDecl =dl;
		stmtList = sl;
	}
	@Override
	
	protected void print(String indent,FileWriter f)throws Exception {
		if(localDecl.isEmpty()) {
			stmtList.forEach((s)->{
				try {
					s.print( "\t" + indent,f);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		else {
			localDecl.forEach((ld)->{
				try {
					ld.print( "\t" + indent,f);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			System.out.print('\n');
			stmtList.forEach((s)->{
				try {
					s.print( "\t" + indent,f);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
		}
		
		
		
	}
	@Override
	protected void genCode(Function f) {
		localDecl.forEach((ld)->{
         CodeItem ci = ld.genCode(f);
		});
	}
	
	ArrayList<Declaration> localDecl;
	ArrayList<Statement> stmtList;
	
	

}