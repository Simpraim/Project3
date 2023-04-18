package compiler;

import java.io.FileWriter;
import java.io.IOException;

public abstract class Declaration {
	protected abstract void print(String s, FileWriter f)throws IOException;
}
