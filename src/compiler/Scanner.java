package compiler;

import java.io.IOException;

public interface Scanner {
	public Token getNextToken() throws IOException;
    public Token viewNextToken ();
}