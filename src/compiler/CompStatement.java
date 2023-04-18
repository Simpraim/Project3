package compiler;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CompStatement extends Statement {

	
	
	//Constructor
	public CompStatement(ArrayList<Declaration> dl,ArrayList<Statement> sl) {
		localDecl =dl;
		stmtList = sl;
	}
	@Override
	
	protected void print(String indent,FileWriter f)throws IOException {
		if(localDecl.isEmpty()) {
			stmtList.forEach((s)->{
				try {
					s.print( "\t" + indent,f);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		else {
			localDecl.forEach((ld)->{
				try {
					ld.print( "\t" + indent,f);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			System.out.print('\n');
			stmtList.forEach((s)->{
				try {
					s.print( "\t" + indent,f);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
		}
		
		// TODO Auto-generated method stub
		
	}
	
	ArrayList<Declaration> localDecl;
	ArrayList<Statement> stmtList;

}