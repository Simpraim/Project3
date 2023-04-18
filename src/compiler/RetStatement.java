package compiler;

import java.io.FileWriter;
import java.io.IOException;

public class RetStatement extends Statement{

	
	//Constructor 
	public RetStatement(Expression el) {
		expr = el;
	}
	@Override
	protected void print(String indent,FileWriter f) throws IOException {
		if(expr != null){
			System.out.println(indent +"return");
			f.write(indent +"return\n");
			expr.print("\t" + indent,f);
			
		}
		else{
			System.out.println(indent + "return");
			f.write(indent + "return");
		}
		
	}
	
	Expression expr;

}
