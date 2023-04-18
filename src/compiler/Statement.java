package compiler;

import java.io.FileWriter;
import java.io.IOException;

public abstract class Statement {
	protected abstract void print(String s,FileWriter f)throws IOException;
}
