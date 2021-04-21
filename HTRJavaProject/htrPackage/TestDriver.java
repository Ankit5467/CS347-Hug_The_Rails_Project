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
  boolean loginInfo=false;
  
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
    c.setBackground(Color.BLACK);
    //intializing the panel
    speedDis = new JLabel("Speeds: ");
    wifiDis= new JLabel("Wifi connection");
    weatherDis = new JLabel("Weather Status: ");
    locationDis = new JLabel("Location: ");
    rpmDis = new JLabel("RPM: ");
    statusDis = new JLabel("Status: ");
    loginButton = new JButton("Login");
    usernameInput = new JTextField("Username");
    passwordInput = new JTextField("Password");
    statusDis.setBounds(100,600,100,100);

    //Adding towards the panel
    usernameInput.setBounds(100,50,100,50);
    passwordInput.setBounds(300,50,100,50);
    loginButton.setBounds(500,50,100,50);
    wifiDis.setBounds(100,100,100,100);
    speedDis.setBounds(100,200,100,100);
    weatherDis.setBounds(100,300,100,100);
    locationDis.setBounds(100,400,100,100);
    rpmDis.setBounds(100,500,100,100);
    loginButton.setBackground(Color.RED);
    loginButton.setOpaque(true);
    loginButton.setBorderPainted(false);
    c.add(usernameInput);
    c.add(passwordInput);
    c.add(loginButton);
    loginButton.addActionListener(this);
    c.add(wifiDis);
    c.add(speedDis);
    c.add(weatherDis);
    c.add(locationDis);
    c.add(rpmDis);
    c.add(statusDis);
    wifiDis.setForeground(Color.green);
    speedDis.setForeground(Color.green);
    weatherDis.setForeground(Color.green);
    locationDis.setForeground(Color.green);
    rpmDis.setForeground(Color.green);
    statusDis.setForeground(Color.green);
    
    
    
    t= new Timer(500, this);
    t.start();
    


   
  }
   public void actionPerformed(ActionEvent e)
   {
     if(e.getSource() == loginButton)
     {
       //If login button was selected
       loginInfo= lcs.checkCredential(usernameInput.getText(),passwordInput.getText() );
       System.out.println(loginInfo);
     }
     if(e.getSource()==t)
     {
     
       //Put in all the string you want to change
       if(loginInfo)
       {
        rpmDis.setText("hey");
        repaint();

       }
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