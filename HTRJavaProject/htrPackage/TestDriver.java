import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;
import java.applet.*;
public class TestDriver extends JFrame implements ActionListener
{
  Timer t;
  JLabel speedDis;
  JLabel wifiDis;
  JLabel weatherDis;
  JLabel locationDis;
  JLabel rpmDis;
  JLabel statusDis;
  JButton loginButton;
  JTextField usernameInput;
  JTextField passwordInput; 
  
  LCS lcs;
  //Might be error could be missing a import or something from a package that i dont need in dr java.
  //could put Null layout to add the labels to wherre they need to be or ata specific spot but for now i used something basic
  //
  public TestDriver()
  {
    //Basically main
    lcs = new LCS();
    setContentPane(new DrawingPanel());
    Container c = getContentPane();
    c.setLayout(null);
    setVisible(true);
    setFocusable(true); 
    setSize(1280,1000);
    //intializing the panel
    speedDis = new JLabel("Speed: ");
    wifiDis= new JLabel("Wifi connection");
    weatherDis = new JLabel("Weather Status: ");
    locationDis = new JLabel("Location: ");
    rpmDis = new JLabel("RPM: ");
    statusDis = new JLabel("Status: ");
    loginButton = new JButton("Login");
    usernameInput = new JTextField("Username");
    passwordInput = new JTextField("Password");
    //Adding towards the panel
    usernameInput.setBounds(100,50,100,50);
    passwordInput.setBounds(300,50,100,50);
    loginButton.setBounds(500,50,100,50);
    usernameInput.setBounds(100,50,100,50);
    wifiDis.setBounds(100,100,100,100);
    speedDis.setBounds(100,200,100,100);
    weatherDis.setBounds(100,300,100,100);
    locationDis.setBounds(100,400,100,100);
    rpmDis.setBounds(100,500,100,100);
    statusDis.setBounds(100,600,100,100);
    c.add(usernameInput);
    c.add(passwordInput);
    c.add(loginButton);
    c.add(wifiDis);
    c.add(speedDis);
    c.add(weatherDis);
    c.add(locationDis);
    c.add(rpmDis);
    c.add(statusDis);
    loginButton.addActionListener(this);
    t= new Timer(500, this);
    t.start();
    


   
  }
   public void actionPerformed(ActionEvent e)
   {
     if(e.getSource() == loginButton)
     {
       //If login button was selected
     }
     if(e.getSource()==t)
     {
       //Put in all the string you want to change
        repaint();
     }
     repaint();
     
   }

  public static void main(String []args)
  {
    TestDriver td = new TestDriver();
  }
  public class DrawingPanel extends JPanel
  {
    public void paintComponent(Graphics g)
    {

      super.paintComponent(g);
      
    }
    
    
    
  }
  
  
}