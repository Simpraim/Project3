package compiler;

import java.io.FileWriter;
import java.io.IOException;

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
	
	String Identifier;
	Boolean IsArray;
	String ArrayIndex;
	
}
