package compiler;

import java.io.FileWriter;
import java.io.IOException;

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
	
	int num;
	
}
