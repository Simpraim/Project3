package compiler;

import java.io.FileWriter;
import java.io.IOException;

public class Param {
	
	
	//Constructor 
	public Param(String i, Boolean iv, Boolean ia) {
		identifier = i;
		isVoid = iv;
		isArray = ia;
	}
	String identifier;
	Boolean isVoid;
	Boolean isArray;
	public void print(String indent,FileWriter f) throws IOException {
		if(isVoid == true) {
			System.out.println(indent+"void");
			f.write(indent+"void\n");
		}
		if(isArray){
			System.out.println(indent+" int "+ identifier + "[]");
			f.write(indent+" int "+ identifier + "[]\n");
		}
		else {
			System.out.println(indent+" int " + identifier);
			f.write(indent+" int " + identifier+ "\n");
		}
	}
}
