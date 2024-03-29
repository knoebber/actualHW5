package edu.grinnell.csc207.ajunaNfriends.utils;

import java.io.IOException;
import java.io.PrintWriter;


/**
 * An implementation of a simple calculator.
 *
 * @author Ajuna Kyaruzi and Hattie Zucker 
 * From HW4
 *
 */
public class Calculator
{
  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+
   Fraction[] state=new Fraction[10];


  public Calculator()
  {
    
    for(int i=0;i<10;i++)
      state[i]= new Fraction("0");
    
  }

  public Fraction evaluate(String str) throws Exception
  {
    Fraction result = new Fraction("0");
    char curr;
    int last = 0;
    char op = '+';
    boolean savedState=false;
    boolean foundState=false;
    int index;
    Fraction currFrac = new Fraction("0");
    
    /*
    * Go through the string and calculate the result using the given fractions
    * and operators.
    */
	  for(int i=0;i<str.length();i++) //make sure the input is okay
	  {
		  if(str.charAt(i)=='+' || str.charAt(i)=='-' || str.charAt(i)=='*' || str.charAt(i)== '=')
		  {
			  if(str.charAt(i-1) != ' ' || str.charAt(i+1) !=' ') //check if everything has space in-between
			  {
				  Exception badSpace = new Exception("Incorrect spacing");
				  throw badSpace;
			  }//if
		  }//if
		  else if(Character.isLetter(str.charAt(i)) && str.charAt(i) != 'r') //check to make sure that everything is a number or an 'r'
		  {
			  Exception badInput = new Exception("bad input");
			  throw badInput;
		  }
	  }
    
    if(str.length()==2 && str.charAt(0)=='r') //if its just a state
    	return(state[str.charAt(1)-48]);

   
    for (int i = 0; i < str.length(); i++)
      {
        curr = str.charAt(i);
        //System.out.println(curr);
        //foundState=false;
        if(curr=='r'){
        	
        	index = str.charAt(i+1)-48;
        	if(i+2 < str.length()){
        		if(str.charAt(i+2) != ' ')
        		{
        			Exception badR= new Exception("R value cannot exceed 9");
        			throw badR;
        		}
        	}
            currFrac = state[index];
            foundState=true;
            //System.out.println(currFrac.toString());
        }
        
        else if ((curr == '*' || curr == '/' || curr == '+' || curr == '-' || curr == '^')
            && str.charAt(i + 1) == ' ')
          {
        	if(!foundState)
        		currFrac = new Fraction(str.substring(last, i).trim());
        	//System.out.println(op);
        	//System.out.println("curr frac "+currFrac.toString());
        	//System.out.println("result before "+result.toString());
            result = operate(result, op, currFrac);
            //System.out.println("result after "+result.toString());
            last = i + 1;
            op = curr;
            foundState=false;
            
          } // if
        else if(curr== '=')
          {
            String expres=str.substring(i+2);
            index = str.charAt(i-2)-48; //subtracting 48 changes char to int
            //System.out.println(expres);
            //System.out.println("expres: " + expres);
            state[index]=evaluate(expres);
            
            result = state[index];
            savedState=true;
            //System.out.println("saving...");
            break;
          }//else if =
        
      } // for

    if(savedState) //if we saved a fraction, then just return what we saved
    	return result;
    
    else
    {
        /*
    	    * Find and operate on the last fraction in the string.
    	    */
    	if(str.substring(last).trim().charAt(0)=='r')
    		currFrac=state[str.substring(last).trim().charAt(1)-48];
    	else
    		currFrac = new Fraction(str.substring(last).trim());
    	
    	result = operate(result, op, currFrac);
    	
    	return result;
    }
  } // eval(String)

  /**
  * Performs an operation between two fractions using the given operator.
  * @param first
  * @param op
  * @param second
  * @return Fraction
  */
  public static Fraction operate(Fraction first, char op, Fraction second)
  {
    Fraction result = new Fraction("0");
    //System.out.println(first.toString()+" "+second.toString());
    switch (op)
      {
        case '+':
          result = first.add(second);
          break;
        case '-':
          result = first.subtract(second);
          break;
        case '*':
          result = first.multiply(second);
          break;
        case '/':
          result = first.divide(second);
          break;
      } // switch
    return result;
  }//operate
  
  public void userInterface() throws Exception{
	  java.io.BufferedReader eyes;
	  java.io.InputStreamReader istream;
	  istream = new java.io.InputStreamReader (System.in);
	  eyes = new java.io.BufferedReader (istream);
	  PrintWriter pen = new PrintWriter(System.out, true);
	  String expression;
	  
	  //System.out.print("-> ");
	  while(true)
	  {
		  System.out.print("-> ");
		  expression=eyes.readLine();

		  pen.println(evaluate(expression).toString());
		  
		  //pen.println(expression);
		 
	  }
  }

}
