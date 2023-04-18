package compiler;

import java.io.FileWriter;
import java.io.IOException;

class Token {
	
	private TokenType tokenType;
	private Object tokenData;
	public enum TokenType{
		ID_TOKEN, 
		IF_TOKEN, 
		ELSE_TOKEN, 
		INT_TOKEN, 
		VOID_TOKEN,
		RETURN_TOKEN,
		WHILE_TOKEN, 
		PLUS_TOKEN,
		MULTIPLY_TOKEN,
		MINUS_TOKEN, 
		SLASH_TOKEN, 
		GREATER_THAN_TOKEN, 
		LESS_THAN_TOKEN,
		GREATER_THAN_EQUAL_TOKEN,
		LESS_THAN_EQUAL_TOKEN,
		EQUAL_TOKEN,
		NOT_EQUAL_TOKEN,
		ASSIGN_TOKEN,
		SEMI_TOKEN,
		COMMA_TOKEN,
		LEFT_PAREN_TOKEN,
		RIGHT_PAREN_TOKEN,
		LEFT_BRACKET_TOKEN,
		RIGHT_BRACKET_TOKEN,
		LEFT_CURLY_TOKEN,
		RIGHT_CURLY_TOKEN,
		COMMENT_TOKEN,
		EOF_TOKEN,
		ERROR_TOKEN,
		DIVIDE_TOKEN,
		NO_TOKEN,
		
	}
	 
	 public Token (TokenType type, Object data) {
	        tokenType = type;
	        tokenData = data;
	    }
	 public Token(TokenType type) {
		 this(type, null);
	 }

	public  void printToken() {
		if(tokenType == TokenType.ID_TOKEN || tokenType ==TokenType.ERROR_TOKEN) {
			System.out.println(tokenType.name() + ": " + tokenData);
		}
		else if(tokenType == TokenType.INT_TOKEN) {
			System.out.println(tokenType.name() + ": " + tokenData);
		}
		else if(tokenType==TokenType.NO_TOKEN) {
			return;
		}
		else {
			System.out.println(tokenType.name());
		}
	}
	public  void writeToken(FileWriter fw) throws IOException {
		if(tokenType == TokenType.ID_TOKEN || tokenType ==TokenType.ERROR_TOKEN) {
			fw.write(tokenType.name() + ": " + tokenData + '\n');
		}
		else if(tokenType == TokenType.INT_TOKEN) {
			fw.write(tokenType.name() + ": " + tokenData+'\n');
		}
		else if(tokenType==TokenType.NO_TOKEN) {
			return;
		}
		else {
			fw.write(tokenType.name()+'\n');
		}
	}
	public TokenType getType() {
		return tokenType;
	}
	public void setType(TokenType t) {
		tokenType = t;
	}
	public void setData(char[] c, int numChars) {
		String s = "";
		for(int i=0; i<numChars-1; i++) {
		  s = s+Character.toString(c[i]);	
		}
		
		tokenData = (Object)s;
	}
	public Object getData() {
		return tokenData;
	}

}