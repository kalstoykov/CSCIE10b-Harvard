import java.awt.*;
import javax.swing.*; 

/** 
 *  This class produces a drawing that resembles 
 *  a traffic light, arranging for each circle to 
 *  have the proper color.
 *  
 * @author    Kal Stoykov
 * @version   Last modified on 04/02/2016
 **/

public class TrafficLight extends JPanel
{
	 Color color;
	 public TrafficLight(Color color) 
	 {
		 this.color = color;
	 }
	 // Draw and fill each oval with the already set color with the help of the
	 // Graphics class
	 public void paintComponent(Graphics g2) 
	 {
		 Graphics2D g = (Graphics2D) g2;
		 int width = getWidth();
		 int height = getHeight();
		 // remove blurring pixels
		 g.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, 
				 			  RenderingHints.VALUE_ANTIALIAS_ON);
		 g.setColor(color);
		 g.fillOval(0, 0, width, height);
		 g.drawOval(0, 0, width, height);
	 }
	 
	 // main method for the class MailLayout
	 public static void main (String [] args)
	 {
		 JFrame frame = new JFrame("Traffic Light");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setLayout(new GridLayout(3, 1));
		 // adds each color to the grid in order of the array of colors
		 Color colors[] = { Color.RED, Color.YELLOW, Color.GREEN };
		 for (int i = 0; i < 3; i++) 
		 {
			 TrafficLight panel = new TrafficLight(colors[i]);
		     frame.add(panel);
		     panel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		 }
		 frame.setSize(150, 450);
		 frame.setVisible(true);
	 }
}
