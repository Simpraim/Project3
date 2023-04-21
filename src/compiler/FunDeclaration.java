package compiler;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import lowlevel.CodeItem;

public class FunDeclaration extends Declaration{

	
	
	//Constructor
	public FunDeclaration(Boolean isI, String id, ArrayList<Param> p, CompStatement s) {
		IsInt = isI;
		Identifier = id;
		parameters = p;
		statements = s;
	}
	@Override
	protected void print(String indent,FileWriter f) throws IOException {
		if(IsInt == false){
			if(parameters != null){
			System.out.println(indent+"void " + Identifier );
			f.write(indent+"void " + Identifier +"\n");
			System.out.println(indent +"(");
			f.write(indent +"(\n");
			for(int i =0; i <parameters.size(); i++){
				parameters.get(i).print("\t"+indent,f);
				
			}
			System.out.println(indent+" ){");
			f.write(indent+" ){");
			statements.print("\t"+indent,f);
		}
		else{
			System.out.println(indent+"void "+ Identifier +"(void)");
			f.write(indent+"void "+ Identifier +"(void)\n");
			System.out.println(indent+" {");
			f.write(indent+" {\n");
			statements.print("\t"+indent,f);
		}
		} 
		else{
			if(parameters != null){
			System.out.println(indent+" int " + Identifier );
			f.write(indent+" int " + Identifier +"\n");
			System.out.println(indent +"(");
			f.write(indent +"(\n");
			
			for(int i =0; i <parameters.size(); i++){
				parameters.get(i).print("\t"+indent,f);
				
			}
			System.out.println(indent+"){");
			f.write(indent+"){\n");
			statements.print("\t"+indent,f);
		}
		else{
			System.out.println(indent+"int "+ Identifier + "(void)");
			f.write(indent+"int "+ Identifier + "(void)\n");
			System.out.println(indent+" {");
			f.write(indent+" {\n");
			statements.print("\t"+indent,f);
		}
		}
		
	}
	@Override
	protected CodeItem genCode() {
		
		return null;
	}
	
	Boolean IsInt;
	String Identifier;
	ArrayList<Param> parameters;
	CompStatement statements;
	
}
