package compiler;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CallExpression extends Expression {

	public CallExpression(String id, ArrayList<Expression> el) {
		Identifier = id;
		expressionList = el;
	}
	
	@Override
	protected void print(String indent,FileWriter f) throws IOException {
		System.out.println(indent+ Identifier + "(");
		f.write(indent+ Identifier + "(\n");
		if (expressionList.size() > 0) {
		  //expressionList.get(0).print("    "+indent);
		  for (int i = 0; i < expressionList.size(); i++) {
			expressionList.get(i).print("\t"+indent,f);
		  }
		}
		System.out.println(indent+")");
		f.write(indent+")\n");
		
	
		
	}
	
	String Identifier;
	ArrayList<Expression> expressionList;

}
