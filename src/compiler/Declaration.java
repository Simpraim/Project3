package compiler;

import java.io.FileWriter;
import java.io.IOException;

import lowlevel.CodeItem;
import lowlevel.Function;


public abstract class Declaration {
	protected abstract void print(String s, FileWriter f)throws IOException, Exception;
	protected abstract CodeItem genCode(Function f);
	protected abstract CodeItem genCode();
}

