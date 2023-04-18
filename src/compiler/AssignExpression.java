package compiler;

import java.io.FileWriter;
import java.io.IOException;

public class AssignExpression extends Expression {

	
	
	//Constructor
	public AssignExpression(VarExpression id, Expression r) {
		v = id;
		rhs = r;
	}
	
	
	protected void print(String indent,FileWriter f) throws IOException {
		
		System.out.println(indent+"=");
		f.write(indent+"=\n");
		v.print("\t"+ indent,f);
		rhs.print("\t"+indent,f);
		//System.out.print(";");
		
	}
	
	VarExpression v; //Var Expression
	Expression rhs;

}
