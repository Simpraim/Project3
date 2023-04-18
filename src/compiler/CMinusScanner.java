package compiler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class CMinusScanner implements Scanner{
   
	public enum State{
		START,
		DONE,
		ERROR,
		NE_ERROR,
		IN_ID,
        IN_INT,
        IN_COMMENT,
        IN_COMMENT_STAR,
        IN_LESS_THAN,
        IN_GREATER_THAN,
        IN_EQUAL,
        IN_DIVIDE,
        IN_NOT_EQUAL
	}
	public void reservedNameLookup(String s, Token t) {
		switch (s){
		case "else":
			t.setType(Token.TokenType.ELSE_TOKEN);
			break;
		case "if":
			t.setType(Token.TokenType.IF_TOKEN);
			break;
		case "int":
			t.setType(Token.TokenType.INT_TOKEN);
			break;
		case "return":
			t.setType(Token.TokenType.RETURN_TOKEN);
			break;
		case "void":
			t.setType(Token.TokenType.VOID_TOKEN);
			break;
		case "while":
			t.setType(Token.TokenType.WHILE_TOKEN);
			break;
		}
	}
	
	private BufferedReader inFile;
    private Token nextToken;
    
    //Make DFA for states
    public CMinusScanner (BufferedReader file) throws IOException {
        inFile = file;
        nextToken = scanToken();
    }
    public Token getNextToken() throws IOException {
    	Token returnToken = nextToken;
        if (nextToken.getType() != Token.TokenType.EOF_TOKEN)
            nextToken = scanToken();
        return returnToken;
    }
    public Token viewNextToken () {
        return nextToken;
    }
	
    
    public Token scanToken() throws IOException {
    	
    	State state = State.START;
    	Token token = new Token(Token.TokenType.NO_TOKEN);
    	char t[] = new char[1024];
    	int string_index = 0;
    	
    	
    	while(state !=State.DONE) {
    			inFile.mark(999999999);//Marks the current 
    			int i  = inFile.read();
        		char c = (char)i;
        		if(c!=' '||c!='\n'|| c!='\t'||c!='\r') {
        		t[string_index]=c;
        		string_index++;
        		}
    		
    		
    		switch(state) {
    		case START:
    			if(Character.isDigit(c)) {state = State.IN_INT;break;}
    			else if(i == -1) {
    				token.setType(Token.TokenType.EOF_TOKEN);
    				state = State.DONE;
    				break;
    			}
    			else if(Character.isWhitespace(c)) {state = State.DONE;break;}
    			else if(Character.isLetter(c)) {state = State.IN_ID;continue;}
    			else if(c=='=') {state = State.IN_EQUAL;break;}
    			else if(c=='!') {state = State.IN_NOT_EQUAL;break;}
    			else if(c=='>') {state = State.IN_GREATER_THAN;break;}
    			else if(c =='<') {state = State.IN_LESS_THAN;break;}
    			else if(c=='/') {state = State.IN_DIVIDE;break;}
    			
    			else if(c=='+') {
    				token.setType(Token.TokenType.PLUS_TOKEN);
    				state = State.DONE;
    				break;
    			}
    			else if( c=='-') {
    				token.setType(Token.TokenType.MINUS_TOKEN);
    				state = State.DONE;
    				break;
    			}
    			else if(c=='*') {
    				token.setType(Token.TokenType.MULTIPLY_TOKEN);
    				state = State.DONE;
    				break;
    			}
    			else if(c==';') {
    				token.setType(Token.TokenType.SEMI_TOKEN);
    				state= State.DONE;
    				break;
    			}
    			else if(c==',') {
    				token.setType(Token.TokenType.COMMA_TOKEN);
    				state = State.DONE;
    				break;
    			}
    			else if(c=='(') {
    				token.setType(Token.TokenType.LEFT_PAREN_TOKEN);
    				state = State.DONE;
    				break;
    			}
    			else if(c==')') {
    				token.setType(Token.TokenType.RIGHT_PAREN_TOKEN);
    				state = State.DONE;
    				break;
    			}
    			else if(c=='[') {
    				token.setType(Token.TokenType.LEFT_BRACKET_TOKEN);
    				state = State.DONE;
    				break;
    			}
    			else if(c==']') {
    				token.setType(Token.TokenType.RIGHT_BRACKET_TOKEN);
    				state = State.DONE;
    				break;
    			}
    			else if(c=='{') {
    				token.setType(Token.TokenType.LEFT_CURLY_TOKEN);
    				state = State.DONE;
    				break;
    			}
    			else if(c=='}') {
    				token.setType(Token.TokenType.RIGHT_CURLY_TOKEN);
    				state = State.DONE;
    				break;
    			}
    			
    		case IN_EQUAL:
    			
    			if(c=='=') {
    				token.setType(Token.TokenType.EQUAL_TOKEN);
    			    state = State.DONE;
    			    break;
    			}
    			else {
    				inFile.reset();
    				token.setType(Token.TokenType.ASSIGN_TOKEN);
    				state = State.DONE;
    				break;
    			}
    		case IN_NOT_EQUAL:
    			
    			if(c=='=') {
    				
    				token.setType(Token.TokenType.NOT_EQUAL_TOKEN);
    				state = State.DONE;
    				break;
    			}
    			else {
    				inFile.reset();
    				token.setType(Token.TokenType.ERROR_TOKEN);
    				token.setData(t,string_index);
    				state = State.NE_ERROR;
    			}
    			
    		case NE_ERROR:
    			
    			if(c==' '|| c=='\n'|| c=='\t') {
    				token.setType(Token.TokenType.ERROR_TOKEN);
    				token.setData(t,string_index);
    				state = State.DONE;
    				break;
    			}
    			
    			
    		case ERROR:
    			
    			if(Character.isDigit(c)||Character.isLetter(c)) {
    				state = State.ERROR;
    				break;
    			}
    			else {
    			token.setType(Token.TokenType.ERROR_TOKEN);
    			token.setData(t,string_index);
    			state= State.DONE;
    			break;
    			}
    			
    		case IN_INT:
    			if(Character.isLetter(c)) {state=State.ERROR;break;}
    			else if(Character.isDigit(c)) {state= State.IN_INT;break;}
    			else if(Character.isWhitespace(c)) {
    				token.setType(Token.TokenType.INT_TOKEN);
    				token.setData(t,string_index);
    				state = State.DONE;
    				break;
    				}
    			
    			else {
    				inFile.reset();
    				token.setType(Token.TokenType.INT_TOKEN);
    				token.setData(t,string_index);
    				state= State.DONE;
    				break;
    				
    			}
    		case IN_ID:
    			if(Character.isLetter(c)) {
    				state = State.IN_ID; 
    				break;
    				}
    			else if(Character.isDigit(c)) {state = State.ERROR; break;}
    			else if(Character.isWhitespace(c)) {
    				token.setType(Token.TokenType.ID_TOKEN);
    				token.setData(t,string_index);
    				state = State.DONE;
    				break;
    			}
    			else {
    				inFile.reset();
    				token.setType(Token.TokenType.ID_TOKEN);
    				token.setData(t,string_index);
    				state = State.DONE; 
    				break;
    				}
    		case IN_LESS_THAN:
    			
    			if(c=='=') {
    				token.setType(Token.TokenType.LESS_THAN_EQUAL_TOKEN);
    				state = State.DONE;
    				break;
    			}
    			else {
    				inFile.reset();
    				token.setType(Token.TokenType.LESS_THAN_TOKEN);
    				state = State.DONE;
    				break;
    			}
    		case IN_GREATER_THAN:
    			
    			if(c=='=') {
    				token.setType(Token.TokenType.GREATER_THAN_EQUAL_TOKEN);
    				state = State.DONE;
    				break;
    			}
    			else {
    				inFile.reset();
    				token.setType(Token.TokenType.GREATER_THAN_TOKEN);
    				state = State.DONE;
    				break;
    			}
    		case IN_DIVIDE:
    			if(c=='*') {
    				state= State.IN_COMMENT;
    				break;
    			}
    			else {
    				inFile.reset();
    				token.setType(Token.TokenType.DIVIDE_TOKEN);
    				state = State.DONE;
    				break;
    			}
    		case IN_COMMENT:
    			
    			if(c=='*') {
    				state = State.IN_COMMENT_STAR;
    				break;
    			}
    			else if(c=='?') {//EOF character
    				state = State.ERROR;
    				break;
    			}
    			else {
    				state = State.IN_COMMENT;
    				break;
    			}
    		case IN_COMMENT_STAR:
    			
    			if(c=='/') {
    				
    				state = State.DONE;
    				break;
    				
    			}
    			
    		case DONE:
    			continue;
    			
    				
    			}
    	}
    		
    		
    	
    	return token;
    }
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		FileReader file = new FileReader("C:\\Users\\yuqin\\OneDrive - Cedarville University\\Cedarville University\\Spring 2023\\Compiler Theory & Pract\\CS3510_Compiler\\src\\Token\\code.txt");
		FileWriter fw = new FileWriter("Proj1output.txt");
		BufferedReader br = new BufferedReader(file);
		CMinusScanner Scanner = new CMinusScanner(br);
		while(Scanner.viewNextToken().getType()!=Token.TokenType.EOF_TOKEN) {
			Token mytoken = Scanner.getNextToken();
			if(mytoken.getType()==Token.TokenType.ID_TOKEN) {
				Scanner.reservedNameLookup(String.valueOf(mytoken.getData()), mytoken);
			}
			mytoken.printToken();
			mytoken.writeToken(fw);
		}
		file.close();
		fw.close();

	}

}