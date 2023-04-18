package compiler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import compiler.CMinusScanner.State;
import compiler.Token.TokenType;

 public class CMinusParser {

    private CMinusScanner scanner;
    private Token currToken;

    //Helper functions

    private Token advanceToken() throws IOException {
        currToken = scanner.scanToken();
        while(currToken.getType() == TokenType.NO_TOKEN) {
        	 currToken = scanner.scanToken();
        }
        scanner.reservedNameLookup(String.valueOf(currToken.getData()), currToken);
        return currToken;
    }
    private void match(TokenType t) throws Exception {
        if (t == currToken.getType()) {
            currToken = scanner.scanToken();
            while(currToken.getType() == TokenType.NO_TOKEN) {
           	 currToken = scanner.scanToken();
           }
            scanner.reservedNameLookup(String.valueOf(currToken.getData()), currToken);
            return;
        } else {
            throw new Exception("Unmatched Token Exception: " + t.toString() + " and " + currToken.getType().toString());
        }

    }
    
    private Boolean isRelop(TokenType t) {
    	switch(t) {
    		case GREATER_THAN_TOKEN:
    			return true;
    		case LESS_THAN_TOKEN:
    			return true;
    		case GREATER_THAN_EQUAL_TOKEN:
    			return true;
    		case LESS_THAN_EQUAL_TOKEN:
    			return true;
    		case EQUAL_TOKEN:
    			return true;
    		case NOT_EQUAL_TOKEN:
    			return true;
    		default:
    			return false;	
      }
    }
    
    private Boolean isAddop(TokenType t) {
    	switch(t) {
    		case PLUS_TOKEN:
    			return true;
    		case MINUS_TOKEN:
    			return true;
    		default:
    			return false;
    	}
    }
    private Boolean isMulop(TokenType t) {
    	switch(t) {
    		case MULTIPLY_TOKEN:
    			return true;
    		case DIVIDE_TOKEN:
    			return true;
    		default:
    			return false;
    	}
    }
    
    public enum RelopType{
    	GREATER_THAN_TOKEN(">"), 
		LESS_THAN_TOKEN("<"),
		GREATER_THAN_EQUAL_TOKEN(">="),
		LESS_THAN_EQUAL_TOKEN("<="),
		EQUAL_TOKEN("=="),
		NOT_EQUAL_TOKEN("!=");
    	
    	public String value;
		RelopType(String string) {
			value = string;
		}
    }
    
    
    public enum MulOpType{
    	MULTIPLY_TOKEN("*"), 
		DIVIDE_TOKEN("/");
		public String value;
		MulOpType(String string) {
			value = string;
		}
    }
    
    public enum AddOpType{
    	PLUS_TOKEN("+"), 
		MINUS_TOKEN("-");
		public String value;
		AddOpType(String string) {
			value = string;
		}
    }
    private RelopType getRelOp(Token.TokenType t) throws Exception {
    	switch(t) {
		case GREATER_THAN_TOKEN:
			return RelopType.GREATER_THAN_TOKEN;
		case LESS_THAN_TOKEN:
			return RelopType.LESS_THAN_TOKEN;
		case GREATER_THAN_EQUAL_TOKEN:
			return RelopType.GREATER_THAN_EQUAL_TOKEN;
		case LESS_THAN_EQUAL_TOKEN:
			return RelopType.LESS_THAN_EQUAL_TOKEN;
		case EQUAL_TOKEN:
			return RelopType.EQUAL_TOKEN;
		case NOT_EQUAL_TOKEN:
			return RelopType.NOT_EQUAL_TOKEN;
		default:
			throw new Exception("Invalid.  Expected relop got : " + t.toString());
  }
    
    }
    private AddOpType getAddOp(Token.TokenType t) throws Exception {
    	switch(t) {
		case PLUS_TOKEN:
			return AddOpType.PLUS_TOKEN;
		case MINUS_TOKEN:
			return AddOpType.MINUS_TOKEN;
		
		default:
			throw new Exception("Invalid.  Expected addOP got : " + t.toString());
  }
    
    }
    private MulOpType getMulOp(Token.TokenType t) throws Exception {
    	switch(t) {
		case MULTIPLY_TOKEN:
			return  MulOpType.MULTIPLY_TOKEN;
		case DIVIDE_TOKEN:
			return  MulOpType.DIVIDE_TOKEN;
		
		default:
			throw new Exception("Invalid.  Expected addOP got : " + t.toString());
  }
    
    }
    
    
    
    

    //Parse functions
    private Program parseProgram() throws Exception {
        ArrayList < Declaration > d = parseDeclaration();
        if(d.isEmpty()) throw new Exception("Error! No declarations!");
        Program p = new Program(d);
        return p;
       
    }
    
    private ArrayList < Declaration > parseDeclaration() throws Exception {
        ArrayList < Declaration > result = new ArrayList < > ();
        
        while (currToken.getType() != TokenType.EOF_TOKEN) {
            switch (currToken.getType()) {
                case VOID_TOKEN:
                    advanceToken();
                    String id = (String) currToken.getData();
                    advanceToken();
                    match(TokenType.LEFT_PAREN_TOKEN);//check here
                    FunDeclaration f = parseFunDeclaration(id, false);
                    result.add(f);
                    break;
                case INT_TOKEN:
                    advanceToken();
                    String id2 = (String) currToken.getData();
                    advanceToken();
                    Declaration d = parseVarDeclaration(id2);
                    result.add(d);
                    break;
                    
                    default:
                        throw new Exception("Token was neither a VOID or INT token"); 
                    }
                }


                
            
        return result;
        }
        // Note: program has to have one or more declarations, hence the ArrayList cannot be empty
        // TODO: check and make sure the ArrayList is not empty before return result
        // If it is empty, throw an exception

    
    private FunDeclaration parseFunDeclaration(String id, Boolean isInt) throws Exception {
       
        
        // IF VOID -> DO NOTHING
        // ELSE
        //  WHILE INT
        //    parse PARAM

        switch (currToken.getType()) {
            case VOID_TOKEN:
                advanceToken();
                match(TokenType.RIGHT_PAREN_TOKEN);
                
                CompStatement cs = parseCompoundStatement();
                FunDeclaration f = new FunDeclaration(isInt, id, null, cs);
                return f;

            case INT_TOKEN:
                isInt = true;
                ArrayList < Param > pList = new ArrayList < > ();
                while(currToken.getType()==TokenType.INT_TOKEN){
                    advanceToken();
                    Param p = parseParam();
                    pList.add(p);
                }
               
                
                CompStatement cs2 = parseCompoundStatement();
                FunDeclaration f2 = new FunDeclaration(isInt, id, pList, cs2);
                return f2;
                

            default:
                throw new Exception("Error while parsing parameters: " + currToken.getType());
        }
       
    }
    private Declaration parseVarDeclaration(String id) throws Exception {
      
       switch(currToken.getType()){
            case SEMI_TOKEN:
                advanceToken();
                VarDeclaration v = new VarDeclaration(id, false, "");
                return v;
            case LEFT_BRACKET_TOKEN:
                Token oldToken = advanceToken();
                String arrayIndex = (String)oldToken.getData();
                advanceToken();
                match(TokenType.RIGHT_BRACKET_TOKEN);
                match(TokenType.SEMI_TOKEN);
                VarDeclaration v2 = new VarDeclaration(id, true, arrayIndex);
                return v2;
            case LEFT_PAREN_TOKEN:
                advanceToken();
                FunDeclaration f = parseFunDeclaration(id, true);
                return f;
            default:
                throw new Exception("Error while parsing Var declaration: " + currToken.toString());
                

       }

      
    }
    private Param parseParam() throws Exception{
        String id = (String) currToken.getData();
        advanceToken();
        switch(currToken.getType()){
            case LEFT_BRACKET_TOKEN:
                advanceToken();
                match(TokenType.RIGHT_BRACKET_TOKEN);
                match(TokenType.COMMA_TOKEN);
                Param p = new Param(id, false,true);
                return p;
            case COMMA_TOKEN:
                advanceToken();
                Param p2 = new Param(id, false, false);
                return p2;
            case RIGHT_PAREN_TOKEN:
            	advanceToken();
            	Param p3 = new Param(id, false, false);
            	return p3;
            default:
                throw new Exception("Error! Did not recieve expected token while parsing parameters: "+ currToken.toString());
        }
          
      }
    private CompStatement parseCompoundStatement() throws Exception {
        //parse all the declarations
    	match(compiler.Token.TokenType.LEFT_CURLY_TOKEN);
        ArrayList<Declaration> declarations = new ArrayList<>();
        while(currToken.getType() ==TokenType.INT_TOKEN){
            advanceToken();
            String id = (String)currToken.getData();
            advanceToken();
            Declaration d = parseVarDeclaration(id);
            declarations.add(d);    
            }
         //parse all the statements
        ArrayList<Statement> statements = new ArrayList<>();
        while(currToken.getType() !=TokenType.RIGHT_CURLY_TOKEN){
            Statement s = parseStatement();
            statements.add(s);
        }
        match(TokenType.RIGHT_CURLY_TOKEN);
        CompStatement cs = new CompStatement(declarations, statements);
            return cs;

        }
       
       
    
    private Statement parseStatement() throws Exception { //CurrToken should be either an INT,ID, WHILE, RETURN, or IF
        
        switch(currToken.getType()){
            case ID_TOKEN:
            case INT_TOKEN:
            case SEMI_TOKEN:
            case LEFT_PAREN_TOKEN:
                //advanceToken();
                ExprStatement e = parseExprStatement();
           
                return e;
            case WHILE_TOKEN:
                //advanceToken();
                IterStatement w = parseIterStatement();
                return w;
            case RETURN_TOKEN:
                //advanceToken();
                RetStatement r = parseRetStatement();
                return r;
            case IF_TOKEN:
                //advanceToken();
                SelsSatement s = parseSelStatement();
                return s;
            case LEFT_CURLY_TOKEN:
            	//advanceToken();
            	CompStatement cs = parseCompoundStatement();
            	return cs;
            	
            default:
                throw new Exception("Did not receive expected token while parsing ");
        }

       
       
    }
    
    

    private ExprStatement parseExprStatement() throws Exception{
        if(currToken.getType() == TokenType.SEMI_TOKEN) {
        	 advanceToken();
             ExprStatement es = new ExprStatement(null);
             return es;
        }
        else {
            Expression e = parseExpression();
            ExprStatement es2 = new ExprStatement(e);
            match(TokenType.SEMI_TOKEN);
            return es2;
        	
        }
    	
    	
		/*
		 * switch(currToken.getType()){ case SEMI_TOKEN: advanceToken(); ExprStatement
		 * es = new ExprStatement(null); return es; case LEFT_BRACKET_TOKEN:
		 * advanceToken(); Expression e = parseExpression(); ExprStatement es2 = new
		 * ExprStatement(e); return es2; default: throw new
		 * Exception("Error while parsing expression statement: " +
		 * currToken.toString());
		 * 
		 * 
		 * }
		 */
        
            
            
        }
        
    
    private SelsSatement parseSelStatement() throws Exception {//Current Token should be left paren
    	match(TokenType.IF_TOKEN);
    	Boolean hasElse = false;
        match(TokenType.LEFT_PAREN_TOKEN);
        Expression e = parseExpression();
        match(TokenType.RIGHT_PAREN_TOKEN);
        
        Statement s = parseStatement();
    
        if(currToken.getType()==TokenType.ELSE_TOKEN){
            hasElse = true;
            advanceToken();
            Statement es = parseStatement();
            SelsSatement ss = new SelsSatement(e, s, hasElse, es);
            return ss;
        }
        SelsSatement ss = new SelsSatement(e, s, hasElse, null);
        return ss;
    }
   
    private RetStatement parseRetStatement() throws Exception{
        //DONT KNOW IF THIS IS RIGHT
    	match(TokenType.RETURN_TOKEN);
        if(currToken.getType()==TokenType.SEMI_TOKEN){
            //no Expression returned
            advanceToken();
            RetStatement rs = new RetStatement(null);
            return rs;
            
        } else if(currToken.getType()!= TokenType.SEMI_TOKEN){
            Expression e = parseExpression();
            match(TokenType.SEMI_TOKEN);
            RetStatement rs = new RetStatement(e);  
            return rs;
        } 
        else{
            throw new Exception("Error while parsing the return statement "+ currToken.toString());
        }
        
        
    }
    private IterStatement parseIterStatement()throws Exception {//CurrToken should be left paren
    	match(TokenType.WHILE_TOKEN);
    	match(TokenType.LEFT_PAREN_TOKEN);
        Expression e = parseExpression();
        match(TokenType.RIGHT_PAREN_TOKEN);
               Statement s = parseStatement();
        IterStatement is = new IterStatement(e, s);
        return is;
    }


    private Expression parseExpression() throws Exception {
        switch(currToken.getType()){

            case ID_TOKEN:
                String s = currToken.getData().toString();
                advanceToken();
                Expression idExp =parseExpressionPrime(s); 
                return idExp;
            case INT_TOKEN:
                int num = Integer.valueOf(currToken.getData().toString());
                NumExpression n = new NumExpression(num);
                advanceToken();
                Expression sePrime1 = parseSimpleExpressionPrime(n);
                return sePrime1;
            case LEFT_PAREN_TOKEN:
                match(TokenType.LEFT_PAREN_TOKEN);
                Expression e = parseExpression();
                match(TokenType.RIGHT_PAREN_TOKEN);
                Expression sePrime = parseSimpleExpressionPrime(e);
                return sePrime;
            default:
                throw new Exception("Error while parsing expression");
               
                
        }
        
    }

    private Expression parseExpressionPrime(String s) throws Exception {
        switch(currToken.getType()){
            case ASSIGN_TOKEN:
            	VarExpression v = new VarExpression(s, null);
                advanceToken();
                Expression e = parseExpression();
                AssignExpression a = new AssignExpression(v, e);
                return a;
            case LEFT_BRACKET_TOKEN:
                advanceToken();
                Expression e2 = parseExpression();
                match(TokenType.RIGHT_BRACKET_TOKEN);
                Expression doublePrime = parseExpressionDoublePrime(s, e2);
                return doublePrime;
            case LEFT_PAREN_TOKEN:
                advanceToken();
                ArrayList<Expression> el = parseArgs();
                CallExpression caller = new CallExpression(s, el);
                match(TokenType.RIGHT_PAREN_TOKEN);
                Expression sePrime = parseSimpleExpressionPrime(caller);
                return sePrime;
            
            default:
                VarExpression v2 = new VarExpression(s,null);
                return parseSimpleExpressionPrime(v2);
            
        }
      
    }
   
    
    
    private Expression parseExpressionDoublePrime(String s, Expression e2) throws Exception {
        VarExpression v = new VarExpression(s, e2);
    	if(currToken.getType()==TokenType.ASSIGN_TOKEN){
                advanceToken();
                Expression e = parseExpression();
                AssignExpression ae = new AssignExpression(v, e);
                return ae;
           
        }
    	else {
    		return parseSimpleExpressionPrime(v);
    	}
       
    }
    private ArrayList<Expression> parseArgs() throws Exception {
    	ArrayList<Expression> args = new ArrayList<>();
    	if(currToken.getType()== TokenType.RIGHT_PAREN_TOKEN) {
    		return args;
    	}
    	Expression arg1 = parseExpression();
    	args.add(arg1);
    	while(currToken.getType() == TokenType.COMMA_TOKEN) {
    		advanceToken();
    		Expression arg2 = parseExpression();
    		args.add(arg2);
    	}
        return args;
    }
   

    private Expression parseSimpleExpressionPrime(Expression e) throws Exception{
            Expression lhs = parseAdditiveExpressionPrime(e);
            if(isRelop(currToken.getType())){
            	RelopType op = getRelOp(currToken.getType());
                advanceToken();
                Expression rhs = parseAdditiveExpression();
// make lhs the result, so set up for next iter
                lhs = new BinaryExpression(lhs, rhs, op.value);
                }


        return lhs;
    }


    private Expression parseAdditiveExpression() throws Exception {
    	Expression lhs = parseTerm();
    	while (isAddop (currToken.getType())) {
    		AddOpType op = getAddOp(currToken.getType());
    		advanceToken();
    		Expression rhs = parseTerm();
    	// make lhs the result, so set up for next iter
    		lhs = new BinaryExpression( lhs, rhs,op.value );
    	}
    	return lhs;
	}
    
    private Expression parseFactor() throws Exception {
    	switch(currToken.getType()) {
    	
    	case LEFT_PAREN_TOKEN:
    		advanceToken();
    		Expression e = parseExpression();
    		match(TokenType.RIGHT_PAREN_TOKEN);
    		return e;
    	case ID_TOKEN:
    		String id = (String)currToken.getData();
    		advanceToken();
        	Expression e2 = parseVarCall(id);
        	return e2;
 
    		
    		
    		
    	case INT_TOKEN:
    		int num = Integer.valueOf(currToken.getData().toString());
    		advanceToken();
    		NumExpression n = new NumExpression(num);
    		return n;
    	default:
    		throw new Exception("Error while parsing factor: " + currToken.getType().toString());
    	}
    	
    	
    }
    private Expression parseVarCall(String id) throws Exception {
    	switch(currToken.getType()) {
    	
    		case LEFT_BRACKET_TOKEN:
                advanceToken();
                Expression e = parseExpression();
                match(TokenType.RIGHT_BRACKET_TOKEN);
                VarExpression v = new VarExpression(id, e);
                return v;
            case LEFT_PAREN_TOKEN:
                advanceToken();
                ArrayList<Expression> argsList = parseArgs();
                match(TokenType.RIGHT_PAREN_TOKEN);
                CallExpression call = new CallExpression(id, argsList);
                return call;

            default:
                return new VarExpression(id,null);
    	}
    	
    }
    
   
    
    
    
	private Expression parseAdditiveExpressionPrime(Expression e) throws Exception {
		Expression lhs = parseTermPrime(e);
		
		while(isAddop(currToken.getType())) {
		  AddOpType op = getAddOp(currToken.getType());
		  advanceToken();
		  Expression rhs = parseTerm();
		  lhs = new BinaryExpression(lhs, rhs, op.value);
		}
		
		
        return lhs;
    }
    private Expression parseTermPrime(Expression e) throws Exception {
		// TODO Auto-generated method stub
    	Expression lhs = e;
    	while (isMulop (currToken.getType())) {
    		MulOpType op = getMulOp(currToken.getType());
    		advanceToken();
    		Expression rhs = parseFactor();
    	// make lhs the result, so set up for next iter
    		lhs = new BinaryExpression( lhs, rhs,op.value );
    	}
		return lhs;
	}

    private Expression parseTerm() throws Exception{
    	Expression lhs = parseFactor();
    	while (isMulop (currToken.getType())) {
    		MulOpType op = getMulOp(currToken.getType());
    		advanceToken();
    		Expression rhs = parseFactor();
    	// make lhs the result, so set up for next iter
    		lhs = new BinaryExpression( lhs, rhs,op.value );
    	}
    	return lhs;
	}
        
       
    
    
    


     
    public CMinusParser(String filename) throws Exception {
        FileReader file = new FileReader(filename);
        BufferedReader br = new BufferedReader(file);
        CMinusScanner s = new CMinusScanner(br);
        scanner = s;
        currToken = scanner.viewNextToken();
        scanner.reservedNameLookup(String.valueOf(currToken.getData()), currToken);
        
        
    }

    public Program parse() throws Exception {
        Program p = parseProgram();
        return p;
    }

    public static void main(String args[]) throws Exception {
    	FileWriter fw = new FileWriter("Proj2output.ast");
    	CMinusParser CMP = new CMinusParser("C:\\Users\\SIBal\\eclipse-workspace\\Project2\\Proj3_Input.txt");
    	Program p = CMP.parse();
    	p.printTree(fw);

    }

}