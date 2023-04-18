package compiler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ExprStatement extends Statement{

	
	
	//Constructor 
	public ExprStatement(Expression el) {
		exprList = el;
	}
	@Override
	protected void print(String indent,FileWriter f) throws IOException {
		if(exprList != null){
		exprList.print("\t" + indent,f);
		
		}
		else{
			
		}
	}
	
	Expression exprList;

}