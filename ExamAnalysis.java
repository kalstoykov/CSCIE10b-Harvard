# CSCIE10b-Harvard

import java.io.*;
import java.util.*;

/** 
 *  This class processes a large number of student responses 
 *  to questions on a multiple-choice examination.  The input 
 *  consists of the correct answers to the exam questions and
 *  the actual student responses.
 *  
 * @author    Kal Stoykov
 * @version   Last modified on 03/15/2016
 **/

public class ExamAnalysis 
{
	 
	 /**
	     * A main method for the ExamAnalysis Class
	     * 
	     */
	 public static void main (String [] args)
	 {
		 System.out.printf ("%s\n", "I hope you are ready to begin ...");	
		 // Get input from user and the correct answers
		 Scanner keyboard = new Scanner (System.in);	 		 
		 System.out.printf ("%s", "Please type the correct answers to the exam questions, one right after the other: ");
		 String corrAns = "";
		 // Check for validity of the input of user's correct answers
		 corrAns = keyboard.nextLine();
		 for ( int i = 0; i < corrAns.length(); i++ )
		 {
			 if (corrAns.charAt(i) == 'A' || corrAns.charAt(i) == 'B' || corrAns.charAt(i) == 'C' 
				|| corrAns.charAt(i) == 'D' || corrAns.charAt(i) == 'E' || corrAns.charAt(i) == ' ')
			 		System.out.print (corrAns.charAt(i));
			 else
			 {
				 throw new IllegalArgumentException("Invalid input for answers. Please start all over.");
			 }
		 }
		 
		 System.out.println();	 	
		 Scanner sc = null;	 
		 // asks user for the name of the file and checks its validity
		 try 
		 {
			 System.out.printf("%s", "What is the name of the file containing each student's responses to the " + corrAns.length() + " questions? ");
			 String filename = keyboard.nextLine();
			 sc = new Scanner(new File(filename));
         }
		 catch (IOException e) 
         {
        	 System.out.println("File not found. Please try again.");
			 e.printStackTrace();
         }
		 System.out.printf("\n");
		 // stores student answers from the file into an ArrayList
		 ArrayList<String> studentAnswers = new ArrayList<String>();
		 int countStud = 0;
		 // reads through the file until it reaches an empty line
		 while (sc.hasNextLine())
		 {
			String nextLine = sc.nextLine();
			// ignores strings with just spaces and adds values in the ArrayList
			if (nextLine.trim().length() > 0)
			{
				studentAnswers.add(nextLine);
				countStud ++;
			    System.out.println("Student #" + countStud + "'s responses: " + nextLine);
			}
			if (nextLine.equals(""))
	                System.out.printf("%s\n", "We have reached end of file!");
		 }      
		 System.out.printf("\n"); 
		 System.out.printf ("%s%d%s\n\n", "Thank you for the data on " , countStud , " students. Here's the analysis: ");
		 studGradesAnalysis(studentAnswers, corrAns);
		 questionAnalysis(studentAnswers, corrAns);
	 }
	 /**
	     * This method takes 2 arguments an ArrayList and a String
	     * and returns the student analysis.
	     * 
	     * @param	 studAns 		An ArrayList of student responses.
	     * @param	 corrAns 		A String containing the correct answers.
	     */
	 public static void studGradesAnalysis(ArrayList<String> studAns, String corrAns)
	 {
		 // initializes counter for correct, incorrect, blank answers and num students
		 int correctAns = 0;
		 int incorrectAns = 0;
		 int counterStudents = 0;
		 int counterBlank = 0;
		 System.out.printf("%1s%15s%15s%15s\n", "Student #", "Correct" , "Incorrect", "Blank");
		 System.out.printf("%1s%15s%15s%15s\n", "---------", "-------" , "---------", "-----");
		 // iterates through the ArrayList of students and compares each individual
		 // answer to the correct answers String, updating counters accordingly
		 for (String str : studAns) 
		 {
			 	// a case when the total student answers are less than the total
			 	// correct answers. Then the student answers automatically get
			 	// updated with blank answers for the additional unanswered questions
			 	int addToString = corrAns.length() - str.length();
			    if (str.length() < corrAns.length())
			 	{
			 		for (int j = 0; j < addToString; j ++)
			 		{
			 			str += ' ';
			 		}
			 	}
		        for (int i = 0; i < str.length(); i++) 
		        {
		            if (str.charAt(i) == corrAns.charAt(i))
		            {
		            	correctAns ++; 
		            }
		            else if (str.charAt(i) == ' ')
		            {
		            	counterBlank ++;		            		
		            }
		            incorrectAns = corrAns.length() - correctAns - counterBlank ;
		        }
		        counterStudents ++;
		        System.out.printf("%5s", counterStudents);
		        System.out.printf("%16s", correctAns);
		        System.out.printf("%15s", incorrectAns);
		        System.out.printf("%16s\n", counterBlank);
				correctAns = 0;
				incorrectAns = 0;
				counterBlank = 0;
		 }
	 }
	 /**
	     * This method takes 2 arguments an ArrayList and a String
	     * and returns the question analysis.
	     * 
	     * @param	 studAns 		An ArrayList of student responses.
	     * @param	 corrAns 		A String containing the correct answers.
	     */
	 public static void questionAnalysis(ArrayList<String> studAns, String corrAns)
	 {
		 // initializes counters for student responses on different questions
		 int selectedAs = 0;
		 int selectedBs = 0;
		 int selectedCs = 0;
		 int selectedDs = 0;
		 int selectedEs = 0;
		 int selectedBlanks = 0;
		 System.out.println();
		 System.out.printf("%s\n", "QUESTION ANALYSIS (* marks the correct response)");
		 System.out.printf("%1s\n\n", "-----------------");
		 // iterates through the String of the correct answers
		 for (int i = 0; i < corrAns.length(); i++) 
		 {
			 // marks the correct response
			 System.out.printf("%s%d%s\n\n", "Question #", i+1, ":");
			 if (corrAns.charAt(i) == 'A')
				 System.out.printf("%3s%14s%14s%14s%14s%15s\n", "A*", "B", "C", "D", "E", "Blank");
			 else if (corrAns.charAt(i) == 'B')
				 System.out.printf("%3s%14s%14s%14s%14s%15s\n", "A", "B*", "C", "D", "E", "Blank");
			 else if (corrAns.charAt(i) == 'C')
				 System.out.printf("%3s%14s%14s%14s%14s%15s\n", "A", "B", "C*", "D", "E", "Blank");
			 else if (corrAns.charAt(i) == 'D')
				 System.out.printf("%3s%14s%14s%14s%14s%15s\n", "A", "B", "C", "D*", "E", "Blank");
			 else if (corrAns.charAt(i) == 'E')
				 System.out.printf("%3s%14s%14s%14s%14s%15s\n", "A", "B", "C", "D", "E*", "Blank");
			 System.out.println();
			 // goes through the list of student answers
			 for (String str : studAns) 
		     {	    
				 	// if a string from the student list has a 0 length its size get increased
				 	// to the total number of correct answers with all blank spaces
				 	if (str.length() == 0)
				 	{
				 		str = new String(new char[corrAns.length()]).replace('\0', ' ');
				 	}
				 	// a case when the total student answers in a file are less than
				 	// the total correct answers. Then the student answers 
				 	// automatically get updated with blank answers for the 
				 	// additional unanswered questions.
				 	int addToString = corrAns.length() - str.length();
				 	if (str.length() < corrAns.length())
				 	{
				 		for (int j = 0; j < addToString; j ++)
				 		{
				 			str += ' ';
				 		}
				 	}
				 	// update counters if a grade finds a match from the correct answers
		            if (str.charAt(i) == 'A')
		            {
		            	selectedAs ++; 
		            }
		            else if (str.charAt(i) == 'B')
		            {
		            	selectedBs ++; 
		            }
		            else if (str.charAt(i) == 'C')
		            {
		            	selectedCs ++; 
		            }
		            else if (str.charAt(i) == 'D')
		            {
		            	selectedDs ++; 
		            }
		            else if (str.charAt(i) == 'E')
		            {
		            	selectedEs ++; 
		            }
		            else if (str.charAt(i) == ' ')
		            {
		            	selectedBlanks ++;		            		
		            }	 
		     }
			 // calls the helper method printMethod to display the output
			 printMethod(selectedAs, selectedBs, selectedCs, selectedDs, selectedEs, selectedBlanks);
		     selectedAs = 0;
			 selectedBs = 0;
			 selectedCs = 0;
			 selectedDs = 0;
			 selectedEs = 0;
			 selectedBlanks = 0;	
		 }
		 
	 }
	 /**
	     * This is a helper method for the question analysis
	     * that takes 6 arguments selectedAs, selectedBs, 
	     * selectedCs, selectedDs, selectedEs, selectedBlanks
	     * and returns a print out of the question analysis. 
	     * 
	     * @param	 selectedAs 	An integer for questions answered with an A.
	     * @param	 selectedBs 	An integer for questions answered with an B.
	     * @param	 selectedCs 	An integer for questions answered with an C.
	     * @param	 selectedDs 	An integer for questions answered with an D.
	     * @param	 selectedEs 	An integer for questions answered with an E.
	     * @param	 selectedBlanks An integer for questions answered with an space.
	     */
	 public static void printMethod(int selectedAs, int selectedBs, int selectedCs, 
			 int selectedDs, int selectedEs, int selectedBlanks)
	 {
		 System.out.printf("%3s%14s%14s%14s%14s%14s\n", selectedAs, selectedBs, 
	 				selectedCs, selectedDs, selectedEs, selectedBlanks);
	     System.out.println();
	     // converts all integers to floats and displays how the group as a 
	     // whole answered each question in percentage to the 2 decimal place
	     float a = (float) selectedAs;
	     float b = (float) selectedBs;
	     float c = (float) selectedCs;
	     float d = (float) selectedDs;
	     float e = (float) selectedEs;
	     float bl = (float) selectedBlanks;
	     float total = a + b + c + d + e + bl;
	     System.out.printf("%.1f%s%10s",(a * 100) / (total), "%", " ");
	     System.out.printf("%.1f%s%10s",(b * 100) / (total), "%", " ");
	     System.out.printf("%.1f%s%9s",(c * 100) / (total), "%", " ");
	     System.out.printf("%.1f%s%8s",(d * 100) / (total), "%", " ");
	     System.out.printf("%.1f%s%8s",(e * 100) / (total), "%", " ");
	     System.out.printf("%.1f%s%8s\n",(bl * 100) / (total), "%", " ");
	     System.out.println();
	 }
	 
}
