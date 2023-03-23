import java.util.*;//imports the java utlities module
import java.text.SimpleDateFormat;//imports the simple date format package which allows for more simple dates
import java.io.*;//imports the io package
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Action extends Employee
{
	//======================== Entity Attributes ===================
	
	String currentAction;//declares attribute which holds thier currentAction
	Date DateActionPerformed;//declares attribute which holds thier DateActionPerformed
	String newData;//declares attribute which holds thier newData
	String oldData;//declares attribute which holds thier oldData
	String affectedPatient;
	String affectedAdmission;
	
	SimpleDateFormat ftTimeInc = new SimpleDateFormat ("dd/MM/yyyy k:m");//declares a simple format so dates look presentable

	//like all objects on the system, when they are read they need a method which turns the string from the file to attribute data
	//We pass through the line read from file
	//then we slit it up into individual items
	//from this we then assign the correct index to the correct attribute (always follow a correct order)
	//we return the instance
	public Action intialiseAction(String ConcaenatedAction)
	{
		Action currentActionInstance = new Action();
		String[] listOfAttributes = ConcaenatedAction.split("£");
		currentActionInstance.currentAction = listOfAttributes[0];
		try
		{
			currentActionInstance.DateActionPerformed = ftTimeInc.parse(listOfAttributes[1]);
		}
		catch(Exception exc){}
		currentActionInstance.newData = listOfAttributes[2];
		currentActionInstance.oldData = listOfAttributes[3];
		currentActionInstance.affectedPatient = listOfAttributes[4];
		currentActionInstance.affectedAdmission = listOfAttributes[5];
		
		return(currentActionInstance);
	}
	
	//returns all the actions from the start to the end
	//the earilest at index 0 the latest at the index length-1
	public Action[] returnAllActions(String allConcaenatedActions)
	{
		Action[] allActions;
		if(allConcaenatedActions.length()>24)//determines if the user has at least one action
		{
			String[] concatenatedActions = allConcaenatedActions.substring(24,allConcaenatedActions.length()).split("@");
			//System.out.println("The current employee has "+concatenatedActions.length+" Actions associated");
			allActions = new Action[concatenatedActions.length];
			for(int counterIndex = 0;counterIndex<allActions.length;counterIndex++)
			{
				allActions[counterIndex]=intialiseAction(concatenatedActions[counterIndex]);
			}
		}
		else{
			//System.out.println("Employee has no actions");
			allActions = new Action[0];
		}
		return allActions;
	}

	//after being past through the method in employee which returns the correct list of items this will now display them to the user
	//this is just for display purposes only
	public void outputActions(Action[] arrayOfActions)
	{
		String[] actionTabelHeadings= {"Current Action","Patient","Admission","Date action occured","New Data","Old Data"};

	
		JFrame frameAction = new JFrame();//declares the global frame on which the entire system runs ontop of, panels are added to this in order to be seen
		frameAction.setLayout(new GridLayout(1,1));//initalises the frame and the format
		frameAction.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e)
			{
				//after the window is closed the home screen will be called again
				//after the window is set up the system, technically has ended but what happens is that we have a listner attached which will relaunch it when it is closed
				frameAction.dispose();//disposes of the frame
				Management tempManagement = new Management();
				tempManagement.createHomepage();
			}
		});
        frameAction.setTitle("Employee Action Log");//sets the title of the frame
		frameAction.setResizable(false);
        frameAction.setSize(1474,913);//Sets the aspect ratio of the intial window
        frameAction.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frameAction.setVisible(true);//sets the frame to be visible
		//sets the frame to be visbile along with any panels attached to it
        frameAction.setResizable(false);//allows the frame to be resized
		
		JPanel actionPanel = new JPanel(null);
		actionPanel.setSize(1474,913);//sets the size of the object
		String[][] listOfData = setItemsOfData(arrayOfActions);
		//set model
		DefaultTableModel model = new DefaultTableModel(listOfData, actionTabelHeadings);
		//set tabel to use model
		JTable table = new JTable(model);
		table.setSize(1300,800);
		table.setLocation(87,56);
		//set scrollpane to use tabel
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		scrollPane.setSize(1300,800);
		scrollPane.setLocation(87,56);
		actionPanel.add(scrollPane);
		frameAction.add(actionPanel);//When the frame is initialised the panel is added to the frame
	}
	//what this does is convert the array of actions to a multidimentsion array of string so it can be passed through into the model
	//it will iterate through every list of objects in the system
	public String[][] setItemsOfData(Action[] listOfActions)
	{
		String[][] newListOfActions = new String[listOfActions.length][6];
		for(int counter = 0;counter<listOfActions.length;counter++)
		{

			newListOfActions[counter][0] = listOfActions[counter].currentAction;
			newListOfActions[counter][1] = listOfActions[counter].affectedPatient;
			newListOfActions[counter][2] = listOfActions[counter].affectedAdmission;
			newListOfActions[counter][3] = ftTimeInc.format(listOfActions[counter].DateActionPerformed);
			newListOfActions[counter][4] = listOfActions[counter].newData;
			newListOfActions[counter][5] = listOfActions[counter].oldData;
			
			//System.out.println(newListOfActions[counter][0]);
		}
		return newListOfActions;
	}
	
	
}