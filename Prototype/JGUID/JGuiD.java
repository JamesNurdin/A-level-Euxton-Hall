import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import UI.*;
import Util.*;
import Session.*;

import java.io.File;


/**
* This class instantiates GuiDSession either creating a new project or opening an existing file
* Uses interface: iJGuiD
*/


public class JGuiD implements iJGuiD
{
   /**
    * This Constructor bypasses the old project dialog
	* Showing a splash screen and creating a new project
    */
	
	String theVersion = "JGuiD v16";
	
	public JGuiD()
	{
		System.out.println("DEBUG: Running JGuiD-Constructor");
		showSplashScreen();
		
		GuiDSession prim = new GuiDSession("JFrame",this); //Start new JFrame Project
	}
	
	/**
	*This creates an instance for the class GuiDUIPopupProject
	*Which show the Project Dialog
	*/
	
	public void showProjectDialog() //Doesn't ask, just starts new JFrame
	{
	   System.out.println("DEBUG: Running JGuiD-showProjectDialog");
	   GuiDSession prim = new GuiDSession("JFrame",this); //Start new JFrame Project
	}
	
	/**
	*function starts a new Project by sending the type of project to GuiDSession Object
	*which Then start a session for new project
	*/
	public void startNewProject(String type)
	{
		System.out.println("DEBUG: Running JGuiD-startNewProject() DISABLED");
	}
	/**
	*function starts a existing Project by sending the project file  to GuiDSession Object
	*which Then start a session for specified project file
	*/
	public void openExistingProject(File f)
	{
		System.out.println("DEBUG: Running JGuiD-openExistingProblem");
		GuiDSession prim = new GuiDSession(f,this);
	}
	
	 public void showSplashScreen()
	{
		
		System.out.println("DEBUG: Running JGuiD-showSplashScreen");
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		try
		{
			JFrame splashFrame = new JFrame("Welcome: Default Look and Feel - **Nimbus**");
			int splashWidth = 600;
			int splashHeight = 500;
			
			splashFrame.setSize( splashWidth,splashHeight);
			
			splashFrame.setLocation(dim.width/2-splashFrame.getSize().width/2, dim.height/2-splashFrame.getSize().height/2); //Center Centre
			
			splashFrame.setLayout(null);
			
			splashFrame.setResizable(false);

			JLabel lblHeader = new JLabel(new ImageIcon("Images/digitalmonkey.jpg"));
			lblHeader.setText(theVersion);
			lblHeader.setSize(375,(345+50));
			lblHeader.setHorizontalTextPosition(JLabel.CENTER);
			lblHeader.setVerticalTextPosition(JLabel.BOTTOM);
			lblHeader.setFont(new Font("Consolas", Font.PLAIN, 14));
			lblHeader.setForeground(Color.WHITE);
			
			lblHeader.setLocation(splashWidth/2-(lblHeader.getWidth()/2), splashHeight/2-(lblHeader.getHeight()/2)); //Center Centre
			
			splashFrame.getContentPane().setBackground(Color.BLACK);
			splashFrame.add( lblHeader );
			
			splashFrame.setVisible(true);
			
			
			for(int i=0;i<5;i++)
			{
				
				if(i==0)
				{
					lblHeader.setText(theVersion);
				}
				else if(i==1)
				{
					lblHeader.setText(theVersion+"....loading....");
				}
				else if(i==2)
				{
					lblHeader.setText(theVersion+"....hiding exceptions....");
				}
				else if(i==3)
				{
					lblHeader.setText(theVersion+"....hardcoding a fix....");
				}
				else if(i==4)
				{
					lblHeader.setText(theVersion+"....reticulating splines....");
				}
				Thread.sleep(300);
				
			}
			splashFrame.setVisible(false);
			
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION: Running JGuiD-showSplashScreen" + e);
		}
	}
	
	public static void main(String[] args) 
	{
		System.out.println("DEBUG: Running JGuiD-main");
		JGuiD tg = new JGuiD();
	}
}
