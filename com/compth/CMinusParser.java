package com.compth;

import java.io.IOException;
import java.util.ArrayList;

public class CMinusParser implements Parser {
    private ArrayList<Token> tokens;
    private int ptr;

    public CMinusParser(String filename) throws IOException {
        // Read all tokens into the list to be parsed.
        tokens = new ArrayList<Token>(300);
        Scanner s = new CMinusLexer(filename);
        while (s.viewNextToken() != null) {
            Token t = s.getNextToken();
            tokens.add(t);
        }

        ptr = 0;
    }

    public Program parse() {
        return parseProgram();
    }

    public String printTree() {
        return parse().toString();
    }

    protected void acceptToken(TokenType t) {
        if (t == tokens.get(ptr).getType()) {
            ptr++;
        }
        else {
            throw new SyntaxException("Syntax error: invalid token. Expected " + t.toString() + ".");
        }
    }

    protected void acceptToken() {
        ptr++;
    }

    protected Token nextToken() {
        return tokens.get(ptr);
    }

    protected boolean nextIs(TokenType t) {
        return t == tokens.get(ptr).getType();
    }

    // first(expression) = { id, (, num }
    protected boolean nextIsInFirstExpression() {
        return
            nextIs(TokenType.ID) ||
            nextIs(TokenType.LPAREN) ||
            nextIs(TokenType.NUM)
        ;
    }

    // first(expression-stmt) = first(expression) U { ; }
    protected boolean nextIsInFirstExpressionStmt() {
        return
            nextIsInFirstExpression() ||
            nextIs(TokenType.SEMI)
        ;
    }

    protected boolean nextIsRelop() {
        return 
            nextIs(TokenType.LT) || 
            nextIs(TokenType.LTE) || 
            nextIs(TokenType.GT) || 
            nextIs(TokenType.GTE) || 
            nextIs(TokenType.EQ) || 
            nextIs(TokenType.NEQ)
        ;
    }

    protected boolean nextIsAddop() {
        return
            nextIs(TokenType.PLUS) ||
            nextIs(TokenType.MINUS)
        ;
    }

    protected boolean nextIsMulop() {
        return
            nextIs(TokenType.TIMES) ||
            nextIs(TokenType.DIV)
        ;
    }

    protected String nextText() {
        return tokens.get(ptr).getText();
    }
    
    // program → declaration {declaration}
    protected Program parseProgram() { // Implemented
        ArrayList<Declaration> decls = new ArrayList<>(100);

        // Parse the first declaration.

        if (ptr == tokens.size()) {
            throw new SyntaxException("Must be at least one declaration.");
        }
        else {
            decls.add(parseDeclaration());
        }

        // Until we run out of tokens, parse the following declarations.
        // first(declaration) = { void, int }
        while (ptr < tokens.size()) {
            if (nextIs(TokenType.VOID) || nextIs(TokenType.INT)) {
                decls.add(parseDeclaration());
            }
            else {
                throw new SyntaxException("Error: expected declaration");
            }
        }

        // Return the Program production.

        return new Program(decls);
    }

    // declaration -> void id ( params ) compound-stmt | int id declaration'
    protected Declaration parseDeclaration() { // IMPL
        // Parse void | int

        Declaration result;

        if (nextIs(TokenType.VOID)) {
            acceptToken(TokenType.VOID);

            String id = nextText();
            acceptToken(TokenType.ID);

            acceptToken(TokenType.LPAREN);
            ArrayList<Param> params = parseParams();
            acceptToken(TokenType.RPAREN);

            CompoundStatement statements = parseCompoundStatement();

            result = new FunctionDeclaration(id, TokenType.VOID, params, statements);
        }
        else if (nextIs(TokenType.INT)) {
            acceptToken(TokenType.INT);

            String id = nextText();
            acceptToken(TokenType.ID);

            result = parseDeclarationPrime(id);
        }
        else {
            throw new SyntaxException("Syntax Error: invalid token. Expected VOID or INT.");
        }

        return result;
    }

    // declaration' -> [ \[ num \] ] ; | ( params ) compound-stmt
    protected Declaration parseDeclarationPrime(final String id) { // Implemented
        Declaration result;

        // Parse ( params ) compound-stmt
        if (nextIs(TokenType.LPAREN)) {
            acceptToken(TokenType.LPAREN);
            final ArrayList<Param> params = parseParams();
            acceptToken(TokenType.RPAREN);
            final CompoundStatement cmpd = parseCompoundStatement();

            result = new FunctionDeclaration(id, TokenType.INT, params, cmpd);
        }

        // Parse [ \[ num \] ] ;
        else if (nextIs(TokenType.LBRACKET) || nextIs(TokenType.SEMI)) {
            if (nextIs(TokenType.LBRACKET)) {
                acceptToken(TokenType.LBRACKET);
                int num = Integer.parseInt(nextText());
                acceptToken(TokenType.NUM);
                acceptToken(TokenType.RBRACKET);

                result = new VariableDeclaration(id, num);
            }
            else {
                result = new VariableDeclaration(id);
            }

            acceptToken(TokenType.SEMI);
        }

        // Otherwise, report a syntax error.
        else {
            throw new SyntaxException("Error: Expected (, [, or ;");
        }

        return result;
    }

    // params → int id [ \[ \] ] { , int id [ \[ \] ] } | void
    protected ArrayList<Param> parseParams() { // implemented
        final ArrayList<Param> result = new ArrayList<>();

        // Parse the args.
        if (nextIs(TokenType.INT)) {
            String name;
            boolean array = false;

            // Parse the first param
            acceptToken(TokenType.INT);
            name = nextText();
            acceptToken(TokenType.ID);
            if (nextIs(TokenType.LBRACKET)) {
                acceptToken(TokenType.LBRACKET);
                acceptToken(TokenType.RBRACKET);
                array = true;
            }
            
            result.add(new Param(name, array));

            // Parse more arguments until you run out.
            while (nextIs(TokenType.COMMA)) {
                array = false;

                acceptToken(TokenType.COMMA);
                acceptToken(TokenType.INT);
                
                name = nextText();
                acceptToken(TokenType.ID);

                if (nextIs(TokenType.LBRACKET)) {
                    array = true;
                    acceptToken(TokenType.LBRACKET);
                    acceptToken(TokenType.RBRACKET);
                }

                result.add(new Param(name, array));
            }
        }

        // Otherwise, parse the void.
        else if (nextIs(TokenType.VOID)) {
            acceptToken(TokenType.VOID);
        }

        // Otherwise, throw.
        else {
            throw new SyntaxException("Syntax Error: invalid token. Expected VOID or INT");
        }

        return result;
    }

    // compound-stmt → \{ { int id ; } { statement } \}
    protected CompoundStatement parseCompoundStatement() { // implemented
        // Declarations
        ArrayList<String> identifierNames = new ArrayList<>();
        ArrayList<Statement> statements = new ArrayList<>();
        
        // Move past the open brace.
        acceptToken(TokenType.LCURLY);

        // Parse the declarations.
        while (nextIs(TokenType.INT)) {
            acceptToken(TokenType.INT);
            String id = nextText();
            acceptToken(TokenType.ID);
            identifierNames.add(id);
            acceptToken(TokenType.SEMI);
        }

        // Parse the statements.
        while 
        (
            nextIs(TokenType.ID) || nextIs(TokenType.LPAREN) || nextIs(TokenType.NUM) 
            || nextIs(TokenType.SEMI) || nextIs(TokenType.IF) || nextIs(TokenType.WHILE)
            || nextIs(TokenType.RETURN)
        ) 
        {
            statements.add(parseStatement());
        }

        // Move past the close brace.
        acceptToken(TokenType.RCURLY);

        return new CompoundStatement(identifierNames, statements);
    }

    // statement → expression-stmt | compound-stmt | selection-stmt | iteration-stmt | return-stmt
    protected Statement parseStatement() { // Implemented
        Statement result;

        // Parse expression-stmt
        // first(expression-stmt) = first(expression) U { ; } = {id, (, num, { }
        if
        (
            nextIs(TokenType.SEMI) ||
            nextIs(TokenType.ID) ||
            nextIs(TokenType.LPAREN) ||
            nextIs(TokenType.NUM)
        )
        {
            result = parseExpressionStatement();
        }

        // Parse selection-stmt
        // first(selection-stmt) = { if }
        else if (nextIs(TokenType.IF)) {
            result = parseSelectionStatement();
        }

        // Parse iteration-stmt
        // first(iteration-stmt) = { while }
        else if (nextIs(TokenType.WHILE)) {
            result = parseIterationStatement();
        }

        // Parse return-stmt
        // first(return-stmt) = { return }
        else if (nextIs(TokenType.RETURN)) {
            result = parseReturnStatement();
        }

        // Otherwise, throw a syntax error.

        else {
            throw new SyntaxException("Syntax Error: invalid token. Expected SEMI");
        }

        return result;
    }

    // expression-stmt → [expression] ;	
    protected ExpressionStatement parseExpressionStatement() { // implemented
        Expression expression = null;

        // Parse [ expression ] ;
        if (nextIs(TokenType.ID) || nextIs(TokenType.LPAREN) || nextIs(TokenType.NUM)) {
            expression = parseExpression();
        }
        acceptToken(TokenType.SEMI);

        return new ExpressionStatement(expression);
    }
  
    // selection-stmt → if ( expression ) statement [ else statement ]
    protected SelectionStatement parseSelectionStatement() { // Implemented
        Expression exp = null;
        Statement ifPart = null;
        Statement elsePart = null;

        // Parse if ( expression ) statement
        acceptToken(TokenType.IF);
        acceptToken(TokenType.LPAREN);
        exp = parseExpression();
        acceptToken(TokenType.LPAREN);
        ifPart = parseStatement();

        // Parse [ else statement ]
        if (nextIs(TokenType.ELSE)) {
            acceptToken(TokenType.ELSE);
            
            elsePart = parseStatement();
        }

        return new SelectionStatement(exp, ifPart, elsePart);
    }

    // iteration-stmt → while ( expression ) statement
    protected IterationStatement parseIterationStatement() { // Implemented
        Expression expression = null; 
        Statement statement = null;

        acceptToken(TokenType.WHILE);

        expression = parseExpression();
        statement = parseStatement();

        return new IterationStatement(expression, statement);

    }

    // return-stmt -> return [ expression ] ;
    protected ReturnStatement parseReturnStatement() { // Implemented
        Expression expression = null;

        acceptToken(TokenType.RETURN);

        // Parse [ expression ]
        // first(expression) = { id, (, num }
        if (nextIsInFirstExpression()) {
            expression = parseExpression();
        }

        acceptToken(TokenType.SEMI);

        return new ReturnStatement(expression);
    }

    // expression → id expression’ | ( expression ) simple-expression’ | num simple-expression’ 
    protected Expression parseExpression() { // Implemented
        Expression result;
        
        if (nextIs(TokenType.ID)) {
            final String id = nextText();
            acceptToken(TokenType.ID);
            
            result = parseExpressionPrime(id);
        }
        else if (nextIs(TokenType.LPAREN)) {
            acceptToken(TokenType.LPAREN);

            final Expression lhs = parseExpression();
            acceptToken(TokenType.RPAREN);


            result = parseSimpleExpressionPrime(lhs);
        }
        else if (nextIs(TokenType.NUM)) {
            final int num = Integer.parseInt(nextText());
            acceptToken(TokenType.NUM);
            final Expression lhs = new Num(num);

            result = parseSimpleExpressionPrime(lhs); 
        }
        else {
            throw new SyntaxException("Expected an ID, LPAREN, or a NUM.");
        }

        return result;
    }
    
    // expression’ → = expression | \[ expression \] expression’’ | simple-expression’ ( follow factor ) | ( args )
    protected Expression parseExpressionPrime(final String id) { // Implemented
        Expression result;

       // If the next character is \[, parse \[ expression \] expression''
       if (nextIs(TokenType.LBRACKET)) {
            final Expression index;

            acceptToken(TokenType.LBRACKET);
            index = parseExpression();
            acceptToken(TokenType.RBRACKET);

            result = parseExpressionDoublePrime(new Indexed(id, index));
       }

       // If the next character is (, parse ( args )
       else if (nextIs(TokenType.LPAREN)) {
            acceptToken(TokenType.LPAREN);
            result = parseArgs(id);
            acceptToken(TokenType.RPAREN);
            result = parseSimpleExpressionPrime(result);
       }

       // If the next character is the assignment operator, parse  = expression
       else if (nextIs(TokenType.ASSIGN)) {
            acceptToken(TokenType.ASSIGN);
            result = new BinaryOperation(TokenType.ASSIGN, new Var(id), parseExpression());
       }

       // Otherwise, parse simple-expression' ( follow factor ) <-- Dr. G said this was okay
       else {
            result = parseSimpleExpressionPrime(new Var(id));
       }

       return result;
    }
    
    // expression’’ → = expression | simple-expression’
    protected Expression parseExpressionDoublePrime(final Expression lhs) { // implemented
        Expression result;
    
        if (nextIs(TokenType.EQ)) {
            acceptToken(TokenType.EQ);
            result = new BinaryOperation(TokenType.EQ, lhs, parseExpression());
        }
        else {
            result = parseSimpleExpressionPrime(lhs);
        }
    
        return result;
    }
    
    // simple-expression’ → additive-expression’ [ relop additive-expression ]
    protected Expression parseSimpleExpressionPrime(final Expression lhs) { // Implemented
        Expression result;

        // Parse additive-expression'
        result = parseAdditiveExpressionPrime(lhs);

        // Parse [ relop additive-expression ]
        // first(relop) = { <, <=, >, >=, ==, != }
        if (nextIsRelop())
        {
            TokenType op = nextToken().getType();
            acceptToken();
            result = new BinaryOperation(op, result, parseAdditiveExpression());
        }

        return result;

    }

    // additive-expression → term { addop term }
    protected Expression parseAdditiveExpression() { // Implemented
        Expression result;

        result = parseTerm();

        while (nextIsAddop()) {
            TokenType op = nextToken().getType();
            acceptToken();
            result = new BinaryOperation(op, result, parseTerm());
        }
        
        return result;
    }

    // additive-expression’ → term’ { addop term }
    protected Expression parseAdditiveExpressionPrime(final Expression lhs) { // Implemented
        Expression result;

        result = parseTermPrime(lhs);

        while (nextIsAddop()) {
            TokenType op = nextToken().getType();
            acceptToken();
            result = new BinaryOperation(op, result, parseTerm());
        }

        return result;
    }

    // term’ → { mulop factor }
    protected Expression parseTermPrime(final Expression lhs) { // Implemented
        Expression result;

        result = lhs;

        while (nextIsMulop()) {
            TokenType op = nextToken().getType();
            acceptToken();
            result = new BinaryOperation(op, result, parseFactor());
        }

        return result;
    }
    
    // term → factor { mulop factor }
    protected Expression parseTerm() {
        Expression result;

        result = parseFactor();

        while (nextIsMulop()) {
            TokenType op = nextToken().getType();
            acceptToken();
            result = new BinaryOperation(op, result, parseFactor());
        }

        return result;
    }

    protected Relop parseRelop() {
        String specialSymbol = "";

        if (nextIsRelop()) {
            specialSymbol = nextText();
            ptr++;
        }
        else {
            throw new SyntaxException("Expected relop.");
        }

        return new Relop(specialSymbol);
    }

    protected Addop parseAddop() {
        String specialSymbol = "";

        if (nextIs(TokenType.PLUS)) {
            specialSymbol = nextText();
            acceptToken(TokenType.PLUS);
        }
        else if (nextIs(TokenType.MINUS)) {
            specialSymbol = nextText();
            acceptToken(TokenType.MINUS);
        }
        else {
            throw new SyntaxException("Excepted a PLUS or MINUS.");
        }

        return new Addop(specialSymbol);
    }

    protected Mulop parseMulop() {
        String specialMulop = "";

        if (nextIs(TokenType.TIMES)) {
            specialMulop = nextText();
            acceptToken(TokenType.TIMES);
        }
        else if (nextIs(TokenType.DIV)) {
            specialMulop = nextText();
            acceptToken(TokenType.DIV);
        }
        else {
            throw new SyntaxException("Excepted a TIMES or DIV.");
        }

        return new Mulop(specialMulop);
    }

    protected Expression parseFactor() {
        Expression result;

        if (nextIs(TokenType.LPAREN)) {
            acceptToken(TokenType.LPAREN);

            result = parseExpression();

            acceptToken(TokenType.RPAREN);
        }
        else if (nextIs(TokenType.ID)) {
            final String identifierString = nextText();
            acceptToken(TokenType.ID);

            result = parseVarcall(identifierString);
        }
        else if (nextIs(TokenType.NUM)) {
            final int val = Integer.parseInt(nextText());
            acceptToken(TokenType.NUM);
            result = new Num(val);
        }
        else {
            throw new SyntaxException("Syntax Error: invalid token. Expected an Expression, ID, or NUM. Found" + nextToken());
        }

        return result;
    }

    protected Expression parseVarcall(final String id) {
        Expression result;

        // parse [ expression ]
        if (nextIs(TokenType.LBRACKET)) {
            final Expression index;
            acceptToken(TokenType.LBRACKET);
            index = parseExpression();
            acceptToken(TokenType.RBRACKET);

            result = new Indexed(id, index);
        }

        // parse ( args )
        else if (nextIs(TokenType.LPAREN)) {
            acceptToken(TokenType.LPAREN);
            result = parseArgs(id);
            acceptToken(TokenType.RPAREN);
        }

        // Otherwise, ε.
        else {
            result = new Var(id);
        }

        // Return the parse varcall.
        return result;
    }

    protected Expression parseArgs(final String id) {
        Expression result;
        final ArrayList<Expression> expressions = new ArrayList<>();

        if (nextIsInFirstExpression()) {
            expressions.add(parseExpression());

            while (nextIs(TokenType.COMMA)) {
                acceptToken(TokenType.COMMA);
                expressions.add(parseExpression());
            }
        }

        result = new Call(id, expressions);
        return result;
    }
}