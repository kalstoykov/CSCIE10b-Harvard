import javax.swing.*; 

/** 
 *  This class produces a pop up option pane that asks
 *  the user for his or her age and responding with a
 *   message box saying that he or she is young or old.
 *  
 * @author    Kal Stoykov
 * @version   Last modified on 03/26/2016
 **/

public class Age 
{
	 public static void main (String [] args)
	 {
		 String age = JOptionPane.showInputDialog (null, "What's your age, cowboy?");
		 // checks for valid input
		 if (age.matches("[0-9]+") && age.length() > 0)
		 {
			 int number = Integer.parseInt(age);
			 if (number < 40)
			 {
				 JOptionPane.showMessageDialog (null, "You are young!");
				 System.exit(0);
			 }
			 else 
			 {
				 JOptionPane.showMessageDialog (null, "You are old!");
				 System.exit(0);
			 }
		 }
		 // user input was invalid
		 else
		 {
			 JOptionPane.showMessageDialog (null, "Invalid input!");
			 System.exit(0);
		 }
	 }
}
