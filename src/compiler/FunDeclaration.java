package compiler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import lowlevel.BasicBlock;
import lowlevel.CodeItem;
import lowlevel.Data;
import lowlevel.FuncParam;
import lowlevel.Function;

public class FunDeclaration extends Declaration {

	// Constructor
	public FunDeclaration(Boolean isI, String id, ArrayList<Param> p, CompStatement s) {
		IsInt = isI;
		Identifier = id;
		parameters = p;
		statements = s;
	}

	@Override
	protected void print(String indent, FileWriter f) throws Exception {
		if (IsInt == false) {
			if (parameters != null) {
				System.out.println(indent + "void " + Identifier);
				f.write(indent + "void " + Identifier + "\n");
				System.out.println(indent + "(");
				f.write(indent + "(\n");
				for (int i = 0; i < parameters.size(); i++) {
					parameters.get(i).print("\t" + indent, f);

				}
				System.out.println(indent + " ){");
				f.write(indent + " ){");
				statements.print("\t" + indent, f);
			} else {
				System.out.println(indent + "void " + Identifier + "(void)");
				f.write(indent + "void " + Identifier + "(void)\n");
				System.out.println(indent + " {");
				f.write(indent + " {\n");
				statements.print("\t" + indent, f);
			}
		} else {
			if (parameters != null) {
				System.out.println(indent + " int " + Identifier);
				f.write(indent + " int " + Identifier + "\n");
				System.out.println(indent + "(");
				f.write(indent + "(\n");

				for (int i = 0; i < parameters.size(); i++) {
					parameters.get(i).print("\t" + indent, f);

				}
				System.out.println(indent + "){");
				f.write(indent + "){\n");
				statements.print("\t" + indent, f);
			} else {
				System.out.println(indent + "int " + Identifier + "(void)");
				f.write(indent + "int " + Identifier + "(void)\n");
				System.out.println(indent + " {");
				f.write(indent + " {\n");
				statements.print("\t" + indent, f);
			}
		}

	}

	@Override
	protected CodeItem genCode(Function f) {

		return null;
	}

	Boolean IsInt;
	String Identifier;
	ArrayList<Param> parameters;
	CompStatement statements;

	@Override
	protected CodeItem genCode() throws Exception {

		// 1) FuncParams + Symbol table
		Function f = null;
		// function creation
		if (IsInt) {
			f = new Function(Data.TYPE_INT, Identifier, null);
		} else if (!IsInt) {
			f = new Function(Data.TYPE_INT, Identifier, null);
		} else {
			// throw new Exception
		}

		// get symbol table
		HashMap table = f.getTable();

		FuncParam firstParam = null;
		FuncParam tempParam = null;
		FuncParam prevParam = null;

		if (parameters != null) {
			for (Param p : parameters) {

				if (table.containsKey(p.identifier)) {
					throw new Exception("Error: the variable " + p.identifier +
							" is already in use");
				}
				table.put(p.identifier, f.getNewRegNum());

				if (p.isArray)
					tempParam = new FuncParam(Data.TYPE_INT, p.identifier, true);
				else if (!p.isArray)
					tempParam = new FuncParam(Data.TYPE_INT, p.identifier, false);
				if (firstParam == null) {
					firstParam = tempParam;
				} else if (prevParam == null && firstParam != null) {
					prevParam = tempParam;
					firstParam.setNextParam(prevParam);
				} else {
					prevParam.setNextParam(tempParam);
					prevParam = tempParam;
				}
			}
		} 

		// void????
		f.setFirstParam(firstParam);

		// 2) Create Block 0

		f.createBlock0();

		// 3) Create Block + append

		BasicBlock b = new BasicBlock(f);
		f.appendBlock(b);

		// 4) CB = new Block

		f.setCurrBlock(b);

		// 5) compound.genCode()

		statements.genCode(f);

		// 6) Append Block Returnblock

		f.appendBlock(f.getReturnBlock());

		// 7) If UC !empty - append block (UC)

		if (f.getFirstUnconnectedBlock() != null) {
			f.appendBlock(f.getFirstUnconnectedBlock());
		}

		// 8) return

		return f;

	}


	// @Override
	// protected CodeItem genCode() throws Exception {

	// //1) FuncParams + Symbol table
	// FuncParam firstParam = null;
	// FuncParam tempParam = null;
	// FuncParam prevParam = null;

	// //function creation
	// if(IsInt){
	// Function f = new Function(Data.TYPE_INT, Identifier, null);
	// HashMap table = f.getTable();
	// if(parameters==null){
	// tempParam = new FuncParam(Data.TYPE_VOID, "void",false);
	// firstParam = tempParam;
	// f.setFirstParam(firstParam);
	// f.createBlock0();
	// BasicBlock b = new BasicBlock(f);
	// f.appendBlock(b);
	// f.setCurrBlock(b);
	// statements.genCode(f);
	// f.appendBlock(f.getReturnBlock());
	// if(f.getFirstUnconnectedBlock()!=null){
	// f.appendBlock(f.getFirstUnconnectedBlock());
	// }
	// return f;
	// }
	// else{
	// for(Param p : parameters){
	// if(table.containsKey(p.identifier)){
	// throw new Exception("Error: the variable "+ p.identifier+ " is already in
	// use");
	// }
	// table.put(p.identifier, f.getNewRegNum());

	// if(p.isArray)tempParam = new FuncParam(Data.TYPE_INT, p.identifier,true);
	// else if (!p.isArray)tempParam = new FuncParam(Data.TYPE_INT,
	// p.identifier,false);
	// if(firstParam ==null){
	// firstParam = tempParam;
	// }
	// else if(prevParam == null && firstParam != null){
	// prevParam = tempParam;
	// firstParam.setNextParam(prevParam);
	// }
	// else{
	// prevParam.setNextParam(tempParam);
	// prevParam = tempParam;
	// }
	// }
	// f.setFirstParam(firstParam);
	// f.createBlock0();
	// BasicBlock b = new BasicBlock(f);
	// f.appendBlock(b);
	// f.setCurrBlock(b);
	// statements.genCode(f);
	// f.appendBlock(f.getReturnBlock());
	// if(f.getFirstUnconnectedBlock()!=null){
	// f.appendBlock(f.getFirstUnconnectedBlock());
	// }
	// return f;
	// }

	// } else if (!IsInt) {
	// Function f = new Function(Data.TYPE_VOID, Identifier, null);
	// HashMap table = f.getTable();
	// if(parameters==null){
	// tempParam = new FuncParam(Data.TYPE_VOID, "void",false);
	// firstParam = tempParam;
	// f.setFirstParam(firstParam);
	// f.createBlock0();
	// BasicBlock b = new BasicBlock(f);
	// f.appendBlock(b);
	// f.setCurrBlock(b);
	// statements.genCode(f);
	// f.appendBlock(f.getReturnBlock());
	// if(f.getFirstUnconnectedBlock()!=null){
	// f.appendBlock(f.getFirstUnconnectedBlock());
	// }
	// return f;
	// }
	// else{
	// for(Param p : parameters){
	// if(table.containsKey(p.identifier)){
	// throw new Exception("Error: the variable "+ p.identifier+ " is already in
	// use");
	// }
	// table.put(p.identifier, f.getNewRegNum());

	// if(p.isArray)tempParam = new FuncParam(Data.TYPE_INT, p.identifier,true);
	// else if (!p.isArray)tempParam = new FuncParam(Data.TYPE_INT,
	// p.identifier,false);
	// if(firstParam ==null){
	// firstParam = tempParam;
	// }
	// else if(prevParam == null && firstParam != null){
	// prevParam = tempParam;
	// firstParam.setNextParam(prevParam);
	// }
	// else{
	// prevParam.setNextParam(tempParam);
	// prevParam = tempParam;
	// }
	// }
	// f.setFirstParam(firstParam);
	// f.createBlock0();
	// BasicBlock b = new BasicBlock(f);
	// f.appendBlock(b);
	// f.setCurrBlock(b);
	// statements.genCode(f);
	// f.appendBlock(f.getReturnBlock());
	// if(f.getFirstUnconnectedBlock()!=null){
	// f.appendBlock(f.getFirstUnconnectedBlock());
	// }
	// return f;
	// }

	// }

	// else {
	// throw new Exception("error while generating function code");
	// }

	// //get symbol table

	// }

}
