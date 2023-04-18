package compiler;

import java.io.FileWriter;
import java.io.IOException;

public class IterStatement extends Statement{

	
	//Constructor 
	public IterStatement(Expression e, Statement s) {
		expr = e;
		stmt = s;
	}
	@Override
	protected void print(String indent,FileWriter f) throws IOException {
		System.out.println(indent+"while(");
		f.write(indent+"while(\n");
		expr.print("\t"+indent,f);
		System.out.println(indent+"){");
		f.write(indent+"){\n");
		stmt.print("\t"+indent,f);
		
	}
	
	Expression expr;
	Statement stmt;

}
