/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * Cooper Brice ctb220006 CS 4337.007
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Enum for character classes
 *
 */
enum Char {
	LETTER,
	DIGIT,
	UNKNOWN,
	EOF
}

/**
 * Enum for token codes
 *
 */
enum Token {
	LEFT_PAREN,
	RIGHT_PAREN,
	ADD_OP,
	SUB_OP,
	INT_LIT,
	IDENT,
	ASSIGN_OP,
	GR_THAN,
	LS_THAN,
	INCR_OP,
	SEMICOLON,
        COLON,
	LEFT_CURLY,
	RIGHT_CURLY,
        LEFT_BRACKET,
        RIGHT_BRACKET,
        OCTOTHORPE,
        INCLUDE,
        DOT,
        H,
        VOID,
        CHAR,
        INT,
        AOS,
        MAIN,
        STRLEN,
        PRINTF,
        FGETS,
        PUTS,
        RETURN,
        QUOTATION,
        BACKSLASH,
        COMMA,
        FOR,
	IF,
	EOF
}
public class ctb220006_HW2_code {
    
    // input file name
	public static final String filePath = "program.txt";
	public static BufferedReader inputFile; // input file reader
	public static char[] lexeme = new char[100]; // current lexeme array
	public static int lexLen = 0; // current lexeme length 
	public static Char charClass; // next character class
	public static Token nextToken; // next token code
	public static char nextChar; // next character read in

	public static void main(String[] args) {
		try {
			inputFile = new BufferedReader(new FileReader(filePath));	// read in file
		} catch (FileNotFoundException e) {
			System.out.println("ERROR - cannot open " + filePath);
			System.exit(1);
		}
		
		getChar();
		do
                {
                    lex();
                    start();
                }while (nextToken != Token.EOF);
		
		try {
			inputFile.close(); // close input file
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
        
        /* Lexical Analyzer Functions */ 
	
	/**
	 * Function which gets the next character of the input and determine its character class
	 * @throws IOException
	 */
	public static void getChar() {
		try {
			if((nextChar = (char) inputFile.read()) != -1) { // EOF has not been reached
				if(Character.isAlphabetic(nextChar)) 
					charClass = Char.LETTER;
				else if(Character.isDigit(nextChar))
					charClass = Char.DIGIT;
				else charClass = Char.UNKNOWN;
			} else
				charClass = Char.EOF;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
        
        public static void addChar() {
		if (lexLen <= 98) {
			lexeme[lexLen++] = nextChar;
			lexeme[lexLen] = 0;
		}
		else
			System.out.println("ERROR - lexeme is too long");
	}
        
        /**
	 * function that calls getChar until it returns a non-whitespace character
	 */
	public static void getNonBlank() {
		while(Character.isWhitespace(nextChar))
			getChar();
	}
        
        /**
	 * Simple lexical analyzer for arithmetic expressions
	 * @return next token code read in
	 */
	public static Token lex() {
		lexLen = 0;
		getNonBlank();
		switch(charClass) {
		
		// parse identifiers
		case LETTER:
			addChar();
			getChar();
			while(charClass == Char.LETTER || charClass == Char.DIGIT) {
				addChar();
				getChar();
			}
			
			
			if(lexeme[0] == 'i' && lexeme[1] == 'f' && lexLen == 2) // check for if
				nextToken = Token.IF;
                        else if(lexeme[0] == 'i' && lexeme[1] == 'n' && lexeme[2] == 'c' && lexeme[3] == 'l' && lexeme[4] == 'u' && lexeme[5] == 'd' && lexeme[6] == 'e' && lexLen == 7) // check for include
				nextToken = Token.INCLUDE;
                        else if(lexeme[0] == 'h' && lexLen == 1) // check for h
				nextToken = Token.H;
                        else if(lexeme[0] == 'c' && lexeme[1] == 'h' && lexeme[2] == 'a' && lexeme[3] == 'r' && lexLen == 4) // check for char
                                nextToken = Token.CHAR;
                        else if(lexeme[0] == 'v' && lexeme[1] == 'o' && lexeme[2] == 'i' && lexeme[3] == 'd' && lexLen == 4) // check for void
                                nextToken = Token.VOID;
                        else if (lexeme[0] == 'i' && lexeme[1] == 'n' && lexeme[2] == 't' && lexLen == 3) // check for int
                                nextToken = Token.INT;
                        else if(lexeme[0] == 'f' && lexeme[1] == 'o' && lexeme[2] == 'r' && lexLen == 3) // check for for
				nextToken = Token.FOR;
                        else if(lexeme[0] == 'r' && lexeme[1] == 'e' && lexeme[2] == 't' && lexeme[3] == 'u' && lexeme[4] == 'r' && lexeme[5] == 'n' && lexLen == 6) // check for return
				nextToken = Token.RETURN;
                        else if(lexeme[0] == 'p' && lexeme[1] == 'r' && lexeme[2] == 'i' && lexeme[3] == 'n' && lexeme[4] == 't' && lexeme[5] == 'f' && lexLen == 6) // check for printf
				nextToken = Token.PRINTF;
                        else if(lexeme[0] == 'f' && lexeme[1] == 'g' && lexeme[2] == 'e' && lexeme[3] == 't' && lexeme[4] == 's' && lexLen == 5) // check for fgets
				nextToken = Token.FGETS;
                        else if(lexeme[0] == 'a' && lexeme[1] == 's' && lexeme[2] == 'c' && lexeme[3] == 'e' && lexeme[4] == 'n' && lexeme[5] == 'd' && lexeme[6] == 'i' && lexeme[7] == 'n' && lexeme[8] == 'g' && lexeme[9] == 'O' && lexeme[10] == 'r' && lexeme[11] == 'd' && lexeme[12] == 'e' && lexeme[13] == 'r' && lexeme[14] == 'S' && lexeme[15] == 't' && lexeme[16] == 'r' && lexeme[17] == 'i' && lexeme[18] == 'n' &&lexeme[19] == 'g' &&   lexLen == 20) // check for ascendingOrderString
				nextToken = Token.AOS;
                        else if(lexeme[0] == 'p' && lexeme[1] == 'u' && lexeme[2] == 't' && lexeme[3] == 's' && lexLen == 4) // check for puts
				nextToken = Token.PUTS;
                        else if(lexeme[0] == 's' && lexeme[1] == 't' && lexeme[2] == 'r' && lexeme[3] == 'l' && lexeme[4] == 'e' && lexeme[5] == 'n' &&lexLen == 6) // check for fgets
				nextToken = Token.STRLEN;
                        else if(lexeme[0] == 'm' && lexeme[1] == 'a' && lexeme[2] == 'i' && lexeme[3] == 'n' && lexLen == 4) // check for puts
				nextToken = Token.MAIN;
			else
				nextToken = Token.IDENT; // or else it is an identifier
			break;
			
		// parse integer literals
		case DIGIT:
			addChar();
			getChar();
			while(charClass == Char.DIGIT) {
				addChar();
				getChar();
			}
			nextToken = Token.INT_LIT;
			break;
		// parse parenthesis and operators
		case UNKNOWN:
			try {
				lookup(nextChar);
			} catch (IOException e) {
				e.printStackTrace();
			}
			getChar();
			break;
			
		// end of file
		case EOF:
			nextToken = Token.EOF;
			lexeme[0] = 'E';
			lexeme[1] = 'O';
			lexeme[2] = 'F';
			lexeme[3] = 0;
			break;
		}
		
		System.out.printf("Next token is: %s, Next lexeme is %s\n", nextToken, new String(lexeme, 0, lexLen));
		return nextToken;
	}
        
        /**
	 * looks up operators and parentheses and return the token
	 * @param ch character to lookup
	 * @return token code of the character
	 * @throws IOException if file read throws an error
	 */
	public static Token lookup(char ch) throws IOException {
		switch(ch) {
		case '(':
			addChar();
			nextToken = Token.LEFT_PAREN;
			break;
		case ')':
			addChar();
			nextToken = Token.RIGHT_PAREN;
			break;
		case '{':
			addChar();
			nextToken = Token.LEFT_CURLY;
			break;
		case '}':
			addChar();
			nextToken = Token.RIGHT_CURLY;
			break;
		case ';':
			addChar();
			nextToken = Token.SEMICOLON;
			break;
		case '+':
			addChar();
			
			// check if it is ++ instead of addition
			if(peek(inputFile) == '+') {
				nextChar = (char) inputFile.read();
				addChar();
				nextToken = Token.INCR_OP;
			} else
				nextToken = Token.ADD_OP;
			break;
		case '-':
			addChar();
                        nextToken = Token.SUB_OP;
			break;
		case '>':
			addChar();
                        nextToken = Token.GR_THAN;
			break;
		case '<':
			addChar();
                        nextToken = Token.LS_THAN;
			break;
		case '=':
			addChar();
                        nextToken = Token.ASSIGN_OP;
			break;
                case '#':
                        addChar();
                        nextToken = Token.OCTOTHORPE;
                        break;
                case '.':
                        addChar();
                        nextToken = Token.DOT;
                        break;
                case '[':
                        addChar();
                        nextToken = Token.LEFT_BRACKET;
                        break;
                case ']':
                        addChar();
                        nextToken = Token.RIGHT_BRACKET;
                        break;
                case ',':
                        addChar();
                        nextToken = Token.COMMA;
                        break;
                case '"':
                        addChar();
                        nextToken = Token.QUOTATION;
                        break;
                case '\\':
                        addChar();
                        nextToken = Token.BACKSLASH;
                        break;
                case ':':
                        addChar();
                        nextToken = Token.COLON;
                        break;
		default:
			addChar();
			nextToken = Token.EOF;	// set lexeme array to contain EOF
			lexeme[0] = 'E';
			lexeme[1] = 'O';
			lexeme[2] = 'F';
			lexeme[3] = 0;
			lexLen = 3;
			break;
		}
		
		return nextToken;
	}
        
        /**
	 * Acts as the peek() function for the buffered reader
	 * @param r BufferedReader object
	 * @return next char in buffer stream, will not remove char from the stream
	 * @throws IOException i/o error
	 */
	public static char peek(BufferedReader r) throws IOException {
		r.mark(1);
		int b = r.read();
		r.reset();
		return (char)b;
	}
        
        /**
	 * Parses string for EBNF rule: <start> -> <headers> <declar> void ascendingOrderString '(' ')' '{' <stmt> '}' int main '(' ')' '{' <stmt> return INT_LIT ; '}'
	 */
	public static void start() {
		System.out.println("Enter <program>");
                headers();
                declar();
                if(nextToken == Token.VOID)
                {
                    lex();
                    if(nextToken == Token.AOS)
                    {
                        lex();
                        if(nextToken == Token.LEFT_PAREN)
                        {
                            lex();
                            if(nextToken == Token.RIGHT_PAREN)
                            {
                                lex();
                                if(nextToken == Token.LEFT_CURLY)
                                {
                                    lex();
                                    stmt();
                                    if(nextToken == Token.RIGHT_CURLY)
                                    {
                                        lex();
                                        if(nextToken == Token.INT)
                                        {
                                            lex();
                                            if(nextToken == Token.MAIN)
                                            {
                                                lex();
                                                if(nextToken == Token.LEFT_PAREN)
                                                {
                                                    lex();
                                                    if(nextToken == Token.RIGHT_PAREN)
                                                    {
                                                        lex();
                                                        if(nextToken == Token.LEFT_CURLY)
                                                        {
                                                            lex();
                                                            stmt();
                                                            if(nextToken == Token.RETURN)
                                                            {
                                                                lex();
                                                                if(nextToken == Token.INT_LIT)
                                                                {
                                                                    lex();
                                                                    if(nextToken == Token.SEMICOLON)
                                                                    {
                                                                        lex();
                                                                        if(nextToken == Token.RIGHT_CURLY)
                                                                        {
                                                                            lex();
                                                                        } else
                                                                            error();
                                                                    } else
                                                                        error();
                                                                } else
                                                                    error();
                                                            } else
                                                                error();
                                                        } else
                                                            error();
                                                    } else
                                                        error();
                                                } else
                                                    error();
                                            } else
                                                error();
                                        } else
                                            error();
                                    } else
                                        error();
                                } else
                                    error();
                            } else
                                error();
                        } else
                            error();
                    } else
                        error();
                } else
                    error();
		System.out.println("Exit <program>");
	}

        /**
	 * Parses string for EBNF rule: <headers> -> # include '<' ID . h '>' {# include '<' ID . h '>'}
	 */
    public static void headers() {
        System.out.println("Enter <headers>");
        do {
            if(nextToken == Token.OCTOTHORPE) {
                lex();
                if (nextToken == Token.INCLUDE) { // checks for #
                    lex();
                    if (nextToken == Token.LS_THAN) { //checks for <
                        lex();
                        if (nextToken == Token.IDENT) { //checks for header
                            lex();
                            if (nextToken == Token.DOT) { //checks for .
                                lex();
                                if (nextToken == Token.H) { //checks for h
                                    lex();
                                    if (nextToken == Token.GR_THAN) { //checks for >
                                        lex();
                                    } else {
                                        error();
                                    }
                                } else {
                                    error();
                                }
                            } else {
                                error();
                            }
                        } else {
                            error();
                        }
                    } else {
                        error();
                    }
                } else
                    error();
            } else
                error();
        } while(nextToken == Token.OCTOTHORPE);
        System.out.println("Exit <headers>");
    }
    
        /**
	 * Parses string for EBNF rule: <declar> -> (int | char) ID ( '[' INT_LIT ']' | = strlen '(' ID ')' | { , ID }) ;
	 */
	public static void declar() {
		System.out.println("Enter <declar>");
                
                if (nextToken == Token.INT || nextToken == Token.CHAR) {
                    lex();
                    if (nextToken == Token.IDENT) {
                        lex();
                        if (nextToken == Token.LEFT_BRACKET)
                        {
                            lex();
                            if(nextToken == Token.INT_LIT)
                            {
                                lex();
                                if(nextToken == Token.RIGHT_BRACKET)
                                {
                                    lex();
                                } else
                                    error();
                            } else
                                error();
                        }
                        else if (nextToken == Token.ASSIGN_OP) { // checks for =. Optional
                            lex();
                            if (nextToken == Token.STRLEN) { // checks for ;.
                                    lex();
                                    if(nextToken == Token.LEFT_PAREN)
                                    {
                                        lex();
                                        if(nextToken == Token.IDENT)
                                        {
                                            lex();
                                            if(nextToken == Token.RIGHT_PAREN)
                                            {
                                                lex();
                                            } else
                                                error();
                                        } else
                                            error();
                                    } else
                                        error();
                            } else {
                                    error();
                            }
                        } else if(nextToken == Token.COMMA) {
                            lex();
                            if (nextToken == Token.IDENT) { // checks for ID.
                                    lex();
                            } else {
                                    error();
                            }
                        }
                        if(nextToken == Token.SEMICOLON)
                            lex();
                        else
                            error();
                    } else
                        error();
                } else {
                        error();
                }
		
		System.out.println("Exit <declar>");
	}
        
        public static void stmt() {
		System.out.println("Enter <stmt>");
                if(nextToken == Token.FOR)
                {
                    lex();
                    if(nextToken == Token.LEFT_PAREN)
                    {
                        lex();
                        assign();
                        cond();
                        if(nextToken == Token.SEMICOLON)
                        {
                            lex();
                            if(nextToken == Token.IDENT)
                            {
                                lex();
                                if(nextToken == Token.INCR_OP)
                                {
                                    lex();
                                    if(nextToken == Token.RIGHT_PAREN)
                                    {
                                        lex();
                                        if(nextToken == Token.LEFT_CURLY)
                                        {
                                            lex();
                                            stmt();
                                            if(nextToken == Token.RIGHT_CURLY)
                                                lex();
                                            else
                                                error();
                                        } else
                                            error();
                                    } else
                                        error();
                                } else
                                    error();
                            } else
                                error();
                        } else
                            error();
                    } else
                        error();
                }
                else if(nextToken == Token.INT || nextToken == Token.CHAR)
                {
                    declar();
                    if(nextToken != Token.RIGHT_CURLY)
                        stmt();
                }
                else if(nextToken == Token.IF)
                {
                    lex();
                    if(nextToken == Token.LEFT_PAREN) { // parses (<cond>)
                        lex();
                        cond();
                        if(nextToken == Token.RIGHT_PAREN) {
                            lex();			
                            if(nextToken == Token.LEFT_CURLY) {
                                lex();
                                stmt();
                                if(nextToken == Token.RIGHT_CURLY) {
                                    lex();
                                } else
                                    error();
                            } else
                                error();
                        } else
                            error();
                    } else
                        error();
                }
                else if(nextToken == Token.IDENT)
                {
                    assign();
                    if(nextToken != Token.RIGHT_CURLY)
                        stmt();
                }
                else if(nextToken == Token.PRINTF)
                {
                    lex();
                    if(nextToken == Token.LEFT_PAREN)
                    {
                        lex();
                        if(nextToken == Token.QUOTATION)
                        {
                            lex();
                            while(nextToken != Token.QUOTATION)
                                lex();
                            lex();
                            if(nextToken == Token.RIGHT_PAREN)
                            {
                                lex();
                                if(nextToken == Token.SEMICOLON)
                                {
                                    lex();
                                    stmt();
                                } else
                                    error();
                            } else
                                error();
                        } else
                            error();
                    } else
                        error();
                }
                else if(nextToken == Token.FGETS)
                {
                    lex();
                    if(nextToken == Token.LEFT_PAREN)
                    {
                        lex();
                        if(nextToken == Token.IDENT)
                        {
                            lex();
                            if(nextToken == Token.COMMA)
                            {
                                lex();
                                if(nextToken == Token.INT_LIT)
                                {
                                    lex();
                                    if(nextToken == Token.COMMA)
                                    {
                                        lex();
                                        if(nextToken == Token.IDENT)
                                        {
                                            lex();
                                            if(nextToken == Token.RIGHT_PAREN)
                                            {
                                                lex();
                                                if(nextToken == Token.SEMICOLON)
                                                {
                                                    lex();
                                                    stmt();
                                                } else
                                                    error();
                                            } else
                                                error();
                                        } else
                                            error();
                                    } else
                                        error();
                                } else
                                    error();
                            } else
                                error();
                        } else
                            error();
                    } else
                        error();
                }
                else if(nextToken == Token.AOS)
                {
                    lex();
                    if(nextToken == Token.LEFT_PAREN)
                    {
                        lex();
                        if(nextToken == Token.RIGHT_PAREN)
                        {
                            lex();
                            if(nextToken == Token.SEMICOLON)
                            {
                                lex();
                                stmt();
                            } else
                                error();
                        } else
                            error();
                    } else
                        error();
                }
                else if (nextToken == Token.PUTS)
                {
                    lex();
                    if(nextToken == Token.LEFT_PAREN)
                    {
                        lex();
                        if(nextToken == Token.IDENT)
                        {
                            lex();
                            if(nextToken == Token.RIGHT_PAREN)
                            {
                                lex();
                                if(nextToken == Token.SEMICOLON)
                                {
                                    lex();
                                    stmt();
                                } else
                                    error();
                            } else
                                error();
                        } else
                            error();
                    } else
                        error();
                }
		System.out.println("Exit <stmt>");
	}
        
        /**
	 * Parses string for the EBNF rule: <assign> -> ID [ '[' ID ']' ] = <expr> ;
	 */
	public static void assign() {
		System.out.println("Enter <assign>");
                if(nextToken == Token.IDENT)
                {
                    lex();
                    if(nextToken == Token.LEFT_BRACKET)
                    {
                        lex();
                        if(nextToken == Token.IDENT)
                        {
                            lex();
                            if(nextToken == Token.RIGHT_BRACKET)
                                lex();
                            else
                                error();
                        } else 
                            error();
                    }
                    
                    if(nextToken == Token.ASSIGN_OP) { // if next token is assignment, we use =<expr>
                            lex();
                            expr();
                            if(nextToken == Token.SEMICOLON)
                                lex();
                            else
                                error();
                    } else
                        error();
                } else
                        error();

                System.out.println("Exit <assign>");
	}
        
        /**
	 * Parses string for the EBNF Rule: <cond> -> ID [ '[' ID ']' ] ( < | > ) <expr>
	 */
	public static void cond() {
		System.out.println("Enter <cond>");
		
                if(nextToken == Token.IDENT)
                {
                    lex();
                    if(nextToken == Token.LEFT_BRACKET)
                    {
                        lex();
                        if(nextToken == Token.IDENT)
                        {
                            lex();
                            if(nextToken == Token.RIGHT_BRACKET)
                                lex();
                            else
                                error();
                        } else 
                            error();
                    }
                    if(nextToken == Token.GR_THAN || nextToken == Token.LS_THAN) {
                            lex();
                            expr(); // execute expr
                    } else
                        error();
                } else
                    error();
		
		System.out.println("Exit <cond>");
		
	}
                
        /**
	 * Parses string for EBNF Rule: <expr> -> ID [(+ | -) INT_LIT]
	 */
	public static void expr() {
		System.out.println("Enter <expr>");
		
		if(nextToken == Token.INT_LIT)
                {
                    lex();
                } else if (nextToken == Token.IDENT)
                {
                    lex();
                    if(nextToken == Token.LEFT_BRACKET)
                    {
                        lex();
                        if(nextToken == Token.IDENT)
                        {
                            lex();
                            if(nextToken == Token.RIGHT_BRACKET)
                                lex();
                            else
                                error();
                        } else 
                            error();
                    }
                } else
                    error();
                if(nextToken == Token.ADD_OP || nextToken == Token.SUB_OP)
                {
                    lex();
                    if(nextToken == Token.INT_LIT)
                        lex();
                    else
                        error();
                }
		
		System.out.println("Exit <expr>");
	}
        
        
    
    	public static void error() {
		System.out.println("ERROR - parsing error occured");
		System.exit(1);
	}
}
