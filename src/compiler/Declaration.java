package compiler;

import java.io.FileWriter;
import java.io.IOException;

import lowlevel.CodeItem;


public abstract class Declaration {
	protected abstract void print(String s, FileWriter f)throws IOException;
	protected abstract CodeItem genCode();
}

