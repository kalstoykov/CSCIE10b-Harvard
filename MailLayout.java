import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import javax.swing.*; 

/** 
 *  This class presents a mail window, used to compose a new
 *  message. When the user clicks the send button, the content
 *  of the email is written to a file named outbox.txt. After 
 *  the message is written to file, the screen is cleared and 
 *  title reset. The title is set to whatever the user types 
 *  inside the Subject field. If the user does not type anything,
 *   then the title displays "New Message".
 *  
 * @author    Kal Stoykov
 * @version   Last modified on 04/03/2016
 **/

class MailLayout extends JFrame
{
	// assigns fields of the mail window
	 private static final String TITLE = "New Message";
	 JTextField sendTo = new JTextField();
	 JTextField sendCc = new JTextField();
	 JTextField sendBcc = new JTextField();
	 JTextField sendSubject = new JTextField();
	 String textFrom1 = "Person1 - person1@mail.com";
	 String textFrom2 = "Person2 - person2@mail.com";
	 String textFrom3 = "Person3 - person3@mail.com";
	 String[] emailList = { textFrom1, textFrom2, textFrom3 };
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 JComboBox sendFrom= new JComboBox(emailList);
	 JTextArea sendText = new JTextArea(15,5);	 
	 JButton sendButton = new JButton("Send");
	 
	 // main method for the class MailLayout
	 public static void main (String [] args)
	 {
		 // Construct the window
		 MailLayout frame = new MailLayout("New Message", 500, 400);
		 frame.setVisible(true);
		 frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

	 }
	 /**
	   * 	This method constructs the appearance of the mail window.
	   */
	 public MailLayout(String name, int width, int height)
	 {	
		 setSize(width, height);
		 setTitle(name);
		 // panel for the text fields and user input
		 JPanel panel = new JPanel(new GridLayout(6,1));
		 // panel for the labels
		 JPanel panel2 = new JPanel(new GridLayout(6,1));
		 // panel for the send button
		 JPanel panel3 = new JPanel(new FlowLayout());
		 panel3.setBackground(Color.GREEN);
		 
		 JLabel sendToLabel = new JLabel("To: ", JLabel.LEFT);
		 sendToLabel.setSize(10, 10);
		 panel.add(sendToLabel);
		 panel2.add(sendTo);
		 
		 JLabel sendCcLabel = new JLabel("Cc: ", JLabel.LEFT);
		 sendCcLabel.setSize(10, 10);
		 panel.add(sendCcLabel);
		 panel2.add(sendCc);
		 
		 JLabel sendBccLabel = new JLabel("Bcc: ", JLabel.LEFT);
		 sendBccLabel.setSize(10, 10);
		 panel.add(sendBccLabel);
		 panel2.add(sendBcc);
		 
		 JLabel sendSubjectLabel = new JLabel("Subject: ", JLabel.LEFT);
		 sendSubject.setSize(10, 10);
		 panel.add(sendSubjectLabel);
		 panel2.add(sendSubject);
		 SubjectListener s1 = new SubjectListener();
		 // add FocusListener to the sendSubject field
		 sendSubject.addFocusListener(s1);
	     
		 JLabel sendFromLabel = new JLabel("From: ", JLabel.LEFT);
		 sendFrom.setSelectedIndex(0);
		 sendFrom.setSize(10, 10);
		 panel.add(sendFromLabel);
		 panel2.add(sendFrom);
	     
	     JLabel sendTextLabel = new JLabel("", JLabel.LEFT);
	     sendSubject.setSize(10, 10);
	     panel.add(sendTextLabel);
		 panel2.add(sendText);
		 
		// adds an ActionListener to the send button 
		 sendButton.addActionListener (new ButtonListener ());
		 panel3.add(sendButton);
		 sendButton.setForeground (Color.GREEN.darker().darker());
	     Font f = new Font ("TimesRoman", Font.BOLD, 30);
	     sendButton.setFont (f);
	     
	     JPanel panel4 = new JPanel(new BorderLayout()); 
	     add(panel, BorderLayout.WEST);
	     add(panel2, BorderLayout.CENTER);
	     add(panel3, BorderLayout.SOUTH);
	     
	     setDefaultCloseOperation(EXIT_ON_CLOSE);
	     setVisible(true);
	 }
	 /** 
	  *  This class implements the listener interface for 
	  *  the sendButton. When the user clicks the send Button,
	  *  the content of the mail will be written to an outFile.
	  *  
	  * @author    Kal Stoykov
	  * @version   Last modified on 04/03/2016
	  **/
	 class ButtonListener implements ActionListener
	 { 
	      public void actionPerformed (ActionEvent event) 
		  { 
			  if (event.getSource() == sendButton)
			  { 
				  try
				  {
					  
					   // Constructs a FileWriter object given a File outbox.txt object
					   // then bytes will be written to the end of the file
					  	FileWriter outFile = new FileWriter ("outbox.txt", true);	
					  	// creating String objects to be printed in the outFile
						String sendToToFile = sendTo.getText();
						String sendCcToFile = sendCc.getText();
						String sendBccToFile = sendBcc.getText();
						String sendSubjectToFile = sendSubject.getText();
						String sendFromToFile = sendFrom.getSelectedItem().toString();	
						String sendTextFile = sendText.getText();
						
						outFile.write(System.lineSeparator());
						outFile.write("To: " + sendToToFile);
						outFile.write(System.lineSeparator());
						outFile.write("Cc: " + sendCcToFile);
						outFile.write(System.lineSeparator());
						outFile.write("Bcc: " + sendBccToFile);
						outFile.write(System.lineSeparator());
						outFile.write("Subject: " + sendSubjectToFile);
						outFile.write(System.lineSeparator());
						outFile.write("From: " + sendFromToFile);
						outFile.write(System.lineSeparator());
						outFile.write("Message: " + sendTextFile);
						outFile.write(System.lineSeparator());
						outFile.close();
						//create a dialog message to notify that file was created
						JOptionPane.showMessageDialog(null, "File created");
						// reset all fields of the mail window
						setTitle(TITLE);
						sendTo.setText("");
						sendCc.setText("");
						sendBcc.setText("");
						sendSubject.setText("");
						sendText.setText("");
				   }
				   // catches exceptions
				   catch(FileNotFoundException e)
				   {
					   System.out.println("File Not Found: " + e.getMessage() + "\n");
					   e.printStackTrace();
				   }
				  catch(IOException e1)
				   {
					   System.out.println("IOERROR: " + e1.getMessage() + "\n");
					   e1.printStackTrace();
				   }
			   }
		     }
	 }
	 /** 
	  *  This class implements the listener interface for receiving
	  *  keyboard focus events on the the subject field.
	  *  
	  * @author    Kal Stoykov
	  * @version   Last modified on 04/03/2016
	  **/
	  class SubjectListener implements FocusListener
	  { 
		   /**
			 * This method is invoked when a component gains the keyboard focus. 
			 * It is caused by clicking in the JTextField to give it the focus,
			 */
		     public void focusGained(FocusEvent e) 
		     {
	         }
		     /**
			   * This method demonstrates the loss of focus by the JTextField
			   * of the subject. When the user moves out of the JTextField
			   * it loses focus.
			   */
			 public void focusLost(FocusEvent e) 
			 {
				    String newTitle = sendSubject.getText();
				    // if the subject is empty, displays default title
				    if (sendSubject.toString().isEmpty() )
					{
						newTitle = "New Message";
					}
				    // otherwise displays title equals to user input in subject field
				    else if(e.getSource().equals(sendSubject))
					{
						setTitle(newTitle);
					}

			 }
	  }
}
