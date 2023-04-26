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

public class Program {
	
	
	
	//Constructor 
	public Program(ArrayList dl) {
		dList = dl;
	}
	ArrayList<Declaration> dList;
	CodeItem head;
	CodeItem tail;
	
	void printTree(FileWriter f) throws Exception {
		System.out.println("Program");
		f.write("Program\n");
		dList.forEach((n)-> {
			try {
				n.print("\t",f);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		});
	}
	
	
	public CodeItem genCode() {
		
		dList.forEach((decl)->{
			CodeItem x;
			try{
				x = decl.genCode();
				if(head==null){
					head = x;
					tail = x;
				}
				else{
					tail.setNextItem(x);
					tail = x;
				}



			}
			catch(Exception e){
				e.printStackTrace();
			}

		});
		
		return head;
		
	}
}
