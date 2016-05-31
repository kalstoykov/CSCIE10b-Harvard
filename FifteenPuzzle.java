
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*; 

/** 
 *  This class presents a is a sliding puzzle game that
 *  consists of a frame of numbered square tiles in random
 *  order with one tile missing.” A user clicks on one of 
 *  the numbered tiles that’s adjacent to the empty space,
 *  and that tile then moves to the empty space.   

 *  
 * @author    Kal Stoykov
 * @version   Last modified on 04/07/2016
 **/

class FifteenPuzzle1 extends JFrame
{
	// assigns fields of the main window
	 final int ROW = 4;
	 final int COL = 4;
	 // creates a 4X4 array of 16 buttons.
	 JButton button[][] = new JButton[ROW][COL];
	 // creates a shuffle button
	 JButton shuffleButn = new JButton("Shuffle");
	 // creates an exit button
	 JButton exitButn = new JButton("Exit");
	 
	 // main method for the class MailLayout
	 public static void main (String [] args)
	 {
		 // Construct the window
		 FifteenPuzzle1 frame = new FifteenPuzzle1("Fifteen Puzzle", 500, 400);
		 frame.setVisible(true);
		 frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

	 }
	 /**
	   * 	This method constructs the appearance of the Puzzle Game.
	   */
	 public FifteenPuzzle1(String name, int width, int height)
	 {	
		 setSize(width, height);
		 setTitle(name);

		 JPanel panel = new JPanel(new GridLayout(4,4,4,4));
		 panel.setBackground(Color.GREEN);
		 
	     int setTxt = 0;
	     for (int i = 0; i < button.length; i++) 
	     {
	            for (int j = 0; j < button[i].length; j++) 
	            {
	                button[i][j] = new JButton("");
	                button[i][j].setSize(20,20);
	                button[i][j].addActionListener (new ButtonListener ());
	                button[i][j].setForeground (Color.GREEN.darker().darker());
	                Font f = new Font ("TimesRoman", Font.BOLD, 30);
	                button[i][j].setFont (f);
	                button[i][j].setText("" + setTxt);
	                setTxt ++;
	                // setting up the first button to be an empty value - missing tile
	                if (button[0][0].getText().equals("0"))
	                {
	                	button[0][0].setText("");
	                }
	                panel.add(button[i][j]);
	            }
	      }
	     
	     JPanel panel2 = new JPanel(new GridLayout(1,2,7,7));
	     shuffleButn.setSize(10,7);
	     shuffleButn.addActionListener (new ButtonListener ());
	     exitButn.setSize(10,7);
	     exitButn.addActionListener (new ButtonListener ());
	     panel2.add(shuffleButn);
	     panel2.add(exitButn);
	     
	     add(panel, BorderLayout.CENTER);
	     add(panel2, BorderLayout.SOUTH);
	     
	     setDefaultCloseOperation(EXIT_ON_CLOSE);
	     setVisible(true);
	 }
	 
	 /** 
	  *  This class implements the listener interface for 
	  *  all Buttons in the grid. When the user clicks a Button,
	  *  it determines if an action would be performed.
	  *  
	  * @author    Kal Stoykov
	  * @version   Last modified on 04/07/2016
	  **/
	 class ButtonListener implements ActionListener
	 { 
	      public void actionPerformed (ActionEvent event) 
		  {   
	    	  // for all buttons on the grid
	    	  for (int i = 0; i < button.length; i++) 
	 	      {
	 	            for (int j = 0; j < button[i].length; j++) 
	 	            {
	 	            // listens for clicks on any of the buttons in the array
	 	            	if (event.getSource() == button[i][j])
	 	            	{ 
	 	            		// if we are not on the leftmost column and if the tile on
	 	            		// the left is empty, we swap the positions of the clicked 
	 	            		// tile and the empty one 	            		
	 	            		if (j != 0 && button[i][j-1].getText().equals(""))
	 	            		{
	 	            			String s = button[i][j].getText();
	 	            			button[i][j-1].setText(s);
	 	            			button[i][j].setText("");
	 	            		}
	 	            	    // if we are not on the rightmost column and if the tile on
	 	            		// the right is empty, we swap the positions of the clicked 
	 	            		// tile and the empty one 
	 	            		else if (j != COL-1 && button[i][j+1].getText().equals(""))
	 	            		{
	 	            			String s = button[i][j].getText();
	 	            			button[i][j+1].setText(s);
	 	            			button[i][j].setText("");
	 	            		}
	 	            	    // if we are not on the top row and if the tile on top is
	 	            		// empty, we swap the positions of the clicked tile and 
	 	            		// the empty one
	 	            		else if (i != 0 && button[i-1][j].getText().equals(""))
	 	            		{
	 	            			String s = button[i][j].getText();
	 	            			button[i-1][j].setText(s);
	 	            			button[i][j].setText("");
	 	            		}
	 	            		 // if we are not on the bottom row and if the tile under
	 	            		//  is empty, we swap the positions of the clicked tile
	 	            		//  and the empty one 
	 	            		else if (i != ROW-1 && button[i+1][j].getText().equals(""))
	 	            		{
	 	            			String s = button[i][j].getText();
	 	            			button[i+1][j].setText(s);
	 	            			button[i][j].setText("");
	 	            		}
	 	            		
	 	            		// if buttons are in a correct order player wins
	 	            		if(button[0][0].getText().equals("") && 
	 	            		   button[0][1].getText().equals("1") &&	
	 	            		   button[0][2].getText().equals("2") &&
	 	            		   button[0][3].getText().equals("3") &&
	 	            		   button[1][0].getText().equals("4") &&
	 	            		   button[1][1].getText().equals("5") &&
	 	            		   button[1][2].getText().equals("6") &&
	 	            		   button[1][3].getText().equals("7") &&
	 	            		   button[2][0].getText().equals("8") &&
	 	            		   button[2][1].getText().equals("9") &&
	 	            		   button[2][2].getText().equals("10") &&
	 	            	   	   button[2][3].getText().equals("11") &&
	 	            	       button[3][0].getText().equals("12") &&
	 	            		   button[3][1].getText().equals("13") &&
	 	            		   button[3][2].getText().equals("14") &&
	 	            		   button[3][3].getText().equals("15")  )
	 	            		 {
	 	            		     JOptionPane.showMessageDialog (null, "You Win!");
	 	            		 }
 
	 	            	}
	 	                // listens for clicks on the shuffle button
	 	            	if (event.getSource() == shuffleButn) 
	 	            	{
	 	            	  for (int k = 0; k < 10; k++)
			 	          { 
	 	            		 if (button[i][j].getText().equals(""))
	 	            		 {
	 	            			String r = RandomDirection();
	 	            			switch (r)
	 	            			{
	 	            				case "East":
	 	            					// if the empty button is not on last column
	 	            					// switch right
	 	            					if (j != COL-1)
	 	            					{
	 	            						String newS = button[i][j+1].getText();
	 	            						button[i][j+1].setText("");
	 	            						button[i][j].setText(newS);
	 	            						break;
	 	            					} 	            					
	 	            				case "West":
	 	            					if (j != 0)
	 	            					{
	 	            						String newS1 = button[i][j-1].getText();
	 	            						button[i][j-1].setText("");
	 	            						button[i][j].setText(newS1);
	 	            						break;
	 	            					}
	 	            				case "North":
	 	            					if (i != 0)
	 	            					{
	 	            						String newS2 = button[i-1][j].getText();
	 	            						button[i-1][j].setText("");
	 	            						button[i][j].setText(newS2);
	 	            						break;
	 	            					}
	 	            				case "South":
	 	            					if (i != ROW-1)
	 	            					{
	 	            						String newS3 = button[i+1][j].getText();
	 	            						button[i+1][j].setText("");
	 	            						button[i][j].setText(newS3);
	 	            						break;
	 	            					}
	 	            					
	 	            			}
		 	            	 }
	 	            		}
	 	            	}
	 	            }
	 	     }
	    	 // Exits the game when exit button is clicked.
	    	 if (event.getSource() == exitButn) 
	    	 {
	    		 System.exit(0);
	    	 }
		  }
	 }
	 
	 /**
	     *  This method selects from a String array a random 
	     *  direction for a tile to move and returns that direction.
	     *
	     * @return  A random direction from the array as a String.
	     */
	 public String RandomDirection()
	 {
		 String[] array = {"East", "West", "North", "South"};
		 Random random = new Random();
  		 String rand = array[random.nextInt(array.length)];
  		 return rand;
	 }
	 
}
