# CSCIE10b-Harvard
import java.io.*;               
import java.util.*;	            

/** 
 *  This class processes a CaesarCipher / Decipher. The program 
 *  works by substituting for each letter in the original plaintext
 *  a letter obtained by shifting the alphabet by a constant number.  
 *  
 * @author    Kal Stoykov
 * @version   Last modified on 03/16/2016
 **/

class CaesarCipher 
{
	 /**
     * This method takes 2 arguments a String input read from a file
     * and an integer for the number of positions for the shift.
     * 
     * @param	 input 		A String input for a message to be enciphered.
     * @param	 shift 		An integer - number of shifts to encipher a message with.
     * @return   			An enciphered String of characters
     */
	public static String caesarEncipher (String input, int shift)
	{
		 // create an empty string to hold the output value and iterate
		 // through each character of the input string
		 String output = "";
		 for ( int i = 0; i < input.length(); ++i )
		  {
			 // convert each char of the string to its ASCII value
	          int j = (int) input.charAt(i);
	          // enciphers only chars that are uppercase and then go through any of 
	          // the 26 alphabet characters in ASCII code to encipher the code
	          if ( Character.isUpperCase(j) )
	          {
	        	  j = ((j - 'A' + shift) % 26) + 'A';          
	          }
	          output += ((char)j);
	      }
		 return output;
	}
	 /**
	 * This method takes 2 arguments a String input read from a file
	 * and an integer for the number of positions for the shift.
	 * 
	 * @param	 input 		A String input for a message to be deciphered
	 * @param	 shift 		An integer - number of shifts to decipher a message with.
	 * @return   			An deciphered String of characters.
	 */
	public static String caesarDecipher (String input, int shift)
	{
		// create an empty string to hold the output value  and iterate 
		// through each character of the input string
		String output = "";
		for ( int i = 0; i < input.length(); ++i )
		  {
	          int j = (int) input.charAt(i);
	          // deciphers only chars that are uppercase and then go through any of 
	          // the 26 alphabet characters in ASCII code to decipher the code
	          if ( Character.isUpperCase(j) )
	          {
	        	  j = ((j - 'A' + 26 - shift) % 26) + 'A';          
	          }
	          output += ((char)j);
	      }
		return output;
	}
	/**
	 * This is the main method for class CaesarCipher. It takes user input
	 * to encipher or decipher a file and outputs the result in a new file.
	 */
	public static void main (String[] args)        
    {
		// takes user input for their encipher/decipher choice, shift of their
		// choice and asks for input - output files for their action to be performed
		System.out.printf("%s\n\n", "Welcome to CaesarCipher");
		Scanner userInput = new Scanner (System.in);
		System.out.printf ("%s", "Enter 1 to encipher, or 2 to decipher (-1 to exit): ");
		int userSelection;
		userSelection = Integer.parseInt(userInput.next());
		// if the user would like to encipher the desired file
		if (userSelection == 1 )
		{
			  System.out.printf ("%s", "What shift should I use? ");
			  int shift = userInput.nextInt();
			  try
			  {
				  System.out.printf ("%s", "What is the input file name? ");
				  String fileName = userInput.next();
				  Scanner inFile = new Scanner(new File(fileName));
				  System.out.printf ("%s", "What is the output file name? ");
				  String outputFile = userInput.next();
				  PrintWriter outFile = new PrintWriter (outputFile);
				  String nextLine1 = "";
				  // reads all lines of the input file and enciphers them
				  // the result is then stored in the output file
				  while (inFile.hasNextLine())
				  {
					  nextLine1 = inFile.nextLine(); 
					  nextLine1 = caesarEncipher (nextLine1, shift);
					  outFile.println (nextLine1);
				  }
				  System.out.printf ("%s", "DONE!");
				  userInput.close();
				  inFile.close();
				  outFile.close();
			  }
			  catch (IOException e) 
		      {
		        	 System.out.println("File not found. Please try again.");
					 e.printStackTrace();
		      }

		}	
		// if the user would like to decipher the desired file
		else if (userSelection == 2)
		{	
			  System.out.printf ("%s", "What shift should I use? ");
			  int shift = userInput.nextInt();
			  try 
			  {
				  System.out.printf ("%s", "What is the input file name? ");
				  String fileName = userInput.next();
				  Scanner inFile = new Scanner(new File(fileName));
				  System.out.printf ("%s", "What is the output file name? ");
				  String outputFile = userInput.next();
				  PrintWriter outFile = new PrintWriter (outputFile);
				  String nextLine1 = "";
				  // reads all lines of the input file and deciphers them
				  // the result is then stored in the output file
				  while (inFile.hasNextLine())
				  {
					  nextLine1 = inFile.nextLine(); 
					  nextLine1 = caesarDecipher (nextLine1, shift);
					  outFile.println (nextLine1);
				  }
				  System.out.printf ("%s", "DONE!");
				  userInput.close();
				  inFile.close();
				  outFile.close();
			  }
			  
			  catch (IOException e) 
		      {
		        	 System.out.println("File not found. Please try again.");
					 e.printStackTrace();
		      }
			 
		}
		// if the user would like to exit the program
		else if (userSelection == -1)
		{
			 System.exit(0);
		}
		// any other input is invalid and program automatically exits
		else
		{
			System.out.printf ("%s\n", "Invalid selection. Please start again. ");
			System.exit(0);
		}
    }
}
