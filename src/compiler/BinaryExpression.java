package compiler;

import java.io.FileWriter;
import java.io.IOException;

public class BinaryExpression extends Expression{

	//Constructor 
	public BinaryExpression(Expression l, Expression r, String o) {
		lhs =l;
		rhs = r;
		op = o;
	}
	
	
	@Override
	protected void print(String indent,FileWriter f) throws IOException {
		
		System.out.println(indent + op );
		f.write(indent + op +"\n");
		lhs.print("\t"+indent,f);
		rhs.print("\t"+indent,f);
		
	}
	
	Expression lhs;
	Expression rhs;
	String op;
	
	
}