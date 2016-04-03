# CSCIE10b-Harvard

/** 
 *  This class takes a 5 digit zip code  or a bar code as an input, and prints
 *  the bar code using the symbol | for a full bar and : for a half bar or
 *  prints the corresponding 5 digit zip code.
 *  
 * @author    Kal Stoykov
 * @version   Last modified on 03/06/2016
 **/
public class BarCode 
{
	// constants
	final String [] BARCODE   = {"||:::",":::||","::|:|","::||:",":|::|",":|:|:",
             ":||::","|:::|","|::|:","|:|::"};
	final String [] ZIPCODE  = {"0","1","2","3","4","5","6","7","8","9"};
	 
	// data field String to represent the five digit zip code
	private String myZipCode;
	// data field String to represent the 32-character bar code
	private String myBarCode;
	
	/**
	 * Constructor that takes a zip code or a bar code as a parameter and stores it 
	 * in myZipCode or myBarCode respectively ; then calls either calls the encode 
	 * method to initialize myBarCode or the decode method to initialize myZipCode.
	 * 
	 * @param	input	This is a five digit zip code that is to be encoded or a
	 * 					32 characters BarCode that is to be decoded.
	 */
	public BarCode(String input)
	{
		// checks if the input is of zipCode length or of barCode length and also
		// if it is valid or not
		if (input.length() == 5 && isValidZipCode(input) == true)
		{
			// the input is a zip code
			myZipCode = input;
			myBarCode = encode();
		}
		else if (input.length() == 32 && isValidBarCode(input) == true)
		{
			// the input is a bar code
			myBarCode = input;
			myZipCode = decode();
		}
		// throws exception if the input is invalid
		else
			throw new IllegalArgumentException("Illegal bar code values: " 
					+ input );  
	}
	
	/** 
	 * Getter method to return the myZipCode String
	 *    
	 * @return myZipCode	The value of the myZipCode field.
	 */
	public String getZipCode()
	{
		return myZipCode;
	}
	
	/** 
	 * Getter method to return the myBarCode String
	 *    
	 * @return myBarCode	The value of the myBarCode field.
	 */
	public String getBarCode()	
	{
		return myBarCode;
	}
	
  	/**
     	 * This method takes a digit 0-9 as a parameter and returns the five bars
    	 * that represent the digit.
    	 * 
    	 * @param	 digit 		Encoding the digit string.
    	 * @return   The five bars that represent the digit.
    	 */
	private String digitToCode(char digit)
	{
		// if the iteration number equals the integer value of the digit to be converted, 
		// then the barCode will take the bar code value of that array position 
		String barCode = "";
		for (int i = 0; i < BARCODE.length; i++) 
		{
			if (Character.getNumericValue(digit) == i)
			{
				barCode += BARCODE[i];
			}
		}
		return barCode;				
	}
	
  	/**
          *  Takes a five bar code as a parameter and returns the corresponding digit 0-9.
   	  *
    	  * @param 	barCode 	The BarCode string to be decoded.
     	  * @return  The corresponding digit 0-9 as a String.
     	  */
	 private String codeToDigit(String fiveBarCode)
	 {
		// creating an empty string to hold the result of codeToDigit
		String zipCode = "";
		// iterating through the constant string array BARCODE and the input string
		for(int i = 0; i < BARCODE.length; i ++) 
		{
			for (int j = 0; j < fiveBarCode.length(); j ++)
			{
				// if fiveBarCode string equals to any of the BARCODE strings then we
				// assign their iteration number to the empty string as a result
				if (fiveBarCode.equals(BARCODE[i]))
				{
					//convert the integer value i to a string format
					zipCode = Integer.toString(i) ;
				}
			}
		}
		return zipCode;
	 }
	
   	/**
   	  * Checks the 32 symbol bar code (a String parameter) to determine if it is valid. 
   	  * This method checks for correct delimiters, correct digit patterns and a
    	  *  correct check digit.  It returns a boolean value.
    	  *
    	  * @param myBarCode		The bar code that will be checked if it is valid.
     	  * @return 				A boolean value true if this is a valid bar code.
     	  */
	private boolean isValidBarCode(String myBarCode)
	{
		// validate the length of the barCode and also first and last characters
		if (myBarCode.length() != 32 || myBarCode.charAt(0) != '|' || 
								myBarCode.charAt(myBarCode.length() -1) != '|')
			return false;
		// barCode is of length 32 and starts and ends with a valid character
		else
		{
			// checking if the pattern and check digit are valid
			// iterate through all 25 chars and increase each iteration with
			// 5 to move on the next barcode sequence
			for (int i = 1; i < myBarCode.length() - 7; i+= 5)
			{	
				// performs count of the number of full and half bars in the input
				// per specification each bar code has 2 full and 3 half bars
				int charCountFullBar = 0;
				int charCountHalfBar = 0;
				for (int j = 0; j < myBarCode.substring(i, i + 5).length(); j++) 
				{
					if (myBarCode.substring(i, i + 5).charAt(j) == '|') 
					{
						  charCountFullBar ++;
					}
					// if it contains a half bar, increments the half bar count
					else 
					{
						  charCountHalfBar ++;
					}
				 }
				 // validation of full and half bars 
				 if (charCountFullBar != 2 && charCountHalfBar != 3) 
				 {
					 return false;
				 }	
				 // validation if check digit is correct
				 // iterates through myBarCode chars of 1-25 only
				 String zipCode = "";
				 for (int k = 1; k < myBarCode.length() - 10; k +=5 )
					 zipCode += codeToDigit(myBarCode.substring(k,k+5));
				 String checkDigit = codeToDigit(myBarCode.substring(26,31));
				 int validation = getCheckDigit(zipCode);
				 if (validation != Integer.parseInt(checkDigit))
					 return false;
			  }
			 
		}
		return true;
	}

      /**
     	*  This method checks its String parameter to determine if it is valid,
        *  and returns a boolean value.
        *
        * @param input 	The zip code that will be checked if it is valid.
        * @return 			A boolean value true if this is a valid zip code.
        */
	private static boolean isValidZipCode(String input)
	{	
		// if the length of the zip code is not 5, then zip code in invalid
		if (input.length() != 5)
			return false;
		// if the length of the zip code is 5
		else
		{
			// create an empty string to store all valid chars from the zip code
			String checkStr = "";
			// iterate through all 5 chars of the zip code
			for (int i = 0; i < input.length(); i++)
	        {
				// if each char is between 0 and 9, it is a valid one and
				// it will be stored in the check string
				if (input.charAt(i) >= '0' && input.charAt(i) <= '9')
				{
					checkStr += input.charAt(i);
				}
				// if the length of the check string is 5, this would mean that
				// all characters were valid
				if (checkStr.length() == 5)
					return true;
	         }
			 return false;
         }
	}
	
       /**
    	 *  Returns the integer 0-9 that is necessary for the sum of the digits to 
    	 *  equal the next multiple of 10.
    	 *
    	 * @param 	myZipCode 	This is a five digit zip code String. 	
     	 * @return 				The integer value of the checkDigit, which is
	 * 						the sum of the digits equal to a multiple of 10.
     	 */
	private int getCheckDigit(String myZipCode)
	{
		// initializing an integer variable to store the total of all digits in the
		// zip code
		int zipCodeDigSum = 0;
	    int length = myZipCode.length();
	    // iterating through all characters of the String myZipCode
	    for (int i = 0; i < length; i++) 
	    {
	        char c = myZipCode.charAt(i);
	        // adding the numeric values of each character in the string
	        zipCodeDigSum += Character.getNumericValue(c);
	    }
	    // calculates the next multiple of ten 
		int nextMultipleTen = (zipCodeDigSum / 10 + 1) * 10;
		// Calculate the check digit value by subtracting the nextMultipleTen and 
		// the sum of all digits
		return (nextMultipleTen - zipCodeDigSum);
	}
	
   	/**
    	 *  Tests the 5 digit zip code myZipCode and returns the 32 symbol bar code. 
    	 *  It returns the empty string if passed an invalid argument.
    	 *
    	 * @return the BarCode String
    	 */
	private String encode()
	{
		String zipCode = "";
		// Validating the zip code
		if (isValidZipCode(myZipCode)) 
		{
		    // Assigning the starting frame bar to the empty string
			zipCode += '|';
		    
		    // Converts each digit char of myZipCode to its code equivalent
		    for (int i = 0; i < myZipCode.length(); i++) 
		    {
		    	zipCode += digitToCode(myZipCode.charAt(i));
		    }

		    // Assigns the encoded check digit to the encoded zipcode
		    int checkDigit = getCheckDigit(myZipCode);
		    zipCode += digitToCode(Character.forDigit(checkDigit, 0));

		    // Add the ending frame bar to the end of the zip Code String
		    zipCode += '|';

		    // Returns the zipcode encoded in a String format
		    return zipCode.toString();
		} 
		else
		    // Returns an empty string when  myZipCode  is invalid
		    return "";
		
	}
	
      /**
     	* Tests the 32 symbol bar code myBarCode and returns the 5 digit zip code
     	*   as a String. If the bar code is invalid, this method also returns an empty string.
     	*
     	* @return the ZipCode String.
     	*/
	private String decode()
	{
		// Test for a valid barcode
		if (isValidBarCode(myBarCode)) 
		{
		    // Variable to hold the decoded zipcode
		    String decodedZipCode = "";
		    // Decode the 5 zipcode digits
		    for (int i = 1; i < myBarCode.length()-7; i += 5) {
			// Get the single digit barcode string
			String myBarCodeDigit = myBarCode.substring(i, i + 5);
			// Add the decoded single digit
			decodedZipCode += (codeToDigit(myBarCodeDigit));
		    }
		    // Return the decoded zipcode as a String
		    return decodedZipCode.toString();
		}
		else 
		{
		    //  Returns an empty string when  myBarCode  is invalid
		    return "";
		}
	}

}
