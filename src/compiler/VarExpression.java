package compiler;

import java.io.FileWriter;
import java.io.IOException;

public class VarExpression extends Expression {

	
	//Constructor 
	public VarExpression(String i, Expression e) {
		Identifier = i;
		expr = e;
	}
	@Override
	protected void print(String indent,FileWriter f) throws IOException {
		System.out.println(indent +Identifier);
		f.write(indent +Identifier+ "\n");
		if (expr != null) {
		  System.out.println("\t"+indent+"[");
		  f.write("\t"+indent+"[\n");
		  expr.print("\t"+indent,f);
		  System.out.println("\t"+indent+"]");
		  f.write("\t"+indent+"]\n");
		}
		
	}
	
	String Identifier;
	Expression expr;
}
