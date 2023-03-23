import java.util.*;//imports the java utlities module
import java.text.SimpleDateFormat;//imports the simple date format package which allows for more simple dates
import java.io.*;//imports the io package
import javax.swing.*;//imports the java swing package,by using a wildcard all aspects of the package are imported
public class Employee extends User
{
//======================== Entity Attributes ===================

	String employeeID;//declares attribute which holds thier employeeID
	double wage;//declares attribute which holds thier wage
	int hoursPerWeek;//declares attribute which holds thier hoursPerWeek
	boolean archived;//declares attribute which holds thier archived status
	Action[] allActions;
	//Class variables
	Scanner inputScanner = new Scanner(System.in);//object declaration for scanner to ercieve input in cmd

	//here we are getting the instances of action ready along with the time paramters for the construction method of the system
	//first it gets the user to find the employee they want it does this by searching through all the instances and it returns an index
	//this index is then used to retrieve all the bookings for the entity on the system
	public boolean startActionLog()
	{
		String[][] AllEmployees = returnAllEmployees();//returns all the employees on the system
		boolean selectEmployeeLoop = true;//makes the attribute true shich runs the infinite loop
		int indexOfEmployee = -1;
		EscapeLoop: //Label to escape the process if management wants to stop
		while(selectEmployeeLoop==true)//termination condition preventing the page from closing
		{
			System.out.println("================ All employees on the system ================");
			System.out.println("\n");
			for (int counter1 = 0;counter1< AllEmployees.length;counter1++)
			{
				System.out.println(AllEmployees[counter1][0]+","+AllEmployees[counter1][1]);
			}
			System.out.println("\n");
			System.out.println("Please enter an employee ID");
			System.out.println("\n");
			System.out.println("(B) Go back");
			System.out.println("=============================================================");
			String desiredEmployeeID = inputScanner.nextLine();
			validated=presenceValidation(desiredEmployeeID);
			if(validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid ID, missing.");
				continue;
			}
			validated=typeValidationStringOrInt(desiredEmployeeID);
			if(validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid ID, letters only.");
				continue;
			}
			if(desiredEmployeeID.equals("B"))//checks to see if user wants to leave the current part
			{
				selectEmployeeLoop=false;
				
				return true;
			}
			if((desiredEmployeeID.charAt(0)+"").equals("E"))//finds the instance of the Employee account
			{
				indexOfEmployee = searchAllemployees(AllEmployees,desiredEmployeeID);
				if(indexOfEmployee==-1)
				{
					continue;
				}
				System.out.println("Index of employee found at "+indexOfEmployee);
				selectEmployeeLoop=false;
			}
		}
		Action tempAction = new Action();
		String[] listOfActionsAndEmployees = tempAction.rffReturnFullFile("EmployeeActionLogs.txt");//list of definitions is read from file
		//uses the index obtained from the search to call the method which returns all the actions performed by that employee
		Action[] allActions = tempAction.returnAllActions(listOfActionsAndEmployees[indexOfEmployee]);
		Date startDate = new Date();
		Date endDate = new Date();

		//here we need to check that the user has some actions
		if(allActions.length>0)
		{
			//here we can output all the actions by the user
			/*if(allActions.length>0)
			{
				for(int counter = 0;counter<allActions.length;counter++)
				{
					System.out.println(allActions[counter].currentAction);
				}
				
			}*/
			Date oldestdate = allActions[0].DateActionPerformed;
			Date todaysDate = new Date();
			boolean startDateSet =false;
			StartLoop:
			do
			{
				try
				{
					System.out.println("Please enter the start date (IN FORMAT DD/MM/YYYY) you want to see their actions: First instance of any activity is "+ftTimeInc.format(oldestdate));
				}
				catch(Exception exc){}
				
				String StringedstartDate = inputScanner.nextLine();
				
				try
				{
					//this here protects from it being null as it is thrown to the catch  statment
					startDate  = ft.parse(StringedstartDate);
					//incase the user selects the intial date, by comparing them as strings we can only compare the date not time also
					if((ft.format(startDate)).equals(ft.format(oldestdate)))
					{
						System.out.println("chosen start date is "+ft.format(startDate));
						break StartLoop;
					}
					
					//we can validate the date here:
					//checking not in the future
					boolean validatedDate = allActions[0].checkDateAisBeforeDateB(startDate,todaysDate);
					{
						if(validatedDate==false)
						{
							JOptionPane.showMessageDialog(null, "Invalid date input, please enter a date in the past");
							continue;
						}
					}
					//checking not before earliest date
					if(startDate.before(oldestdate))
					{
						JOptionPane.showMessageDialog(null, "Invalid date input, please enter after the intial date");
						continue;
					}
					
					//if we get here the date is valid
					System.out.println("chosen start date is "+ft.format(startDate));
					break StartLoop;
					
				}
				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(null, "Invalid date input, please follow format DD/MM/YYYY");
					continue;
				}
			}
			
			
			while(startDateSet ==false);
			boolean endDateSet =false;
			EndDateLoop:
			do
			{
				System.out.println("Please enter the end date (IN FORMAT DD/MM/YYYY) you want to see their actions or press T for today's date");
				String StringedEndDate = inputScanner.nextLine();
				//as string used as input i will need to presence check it
				boolean validatedDate = allActions[0].presenceValidation(StringedEndDate);
				{
					if(validatedDate==false)
					{
						JOptionPane.showMessageDialog(null, "Invalid date input,Please enter a value");
						continue;
					}
				}
				if(StringedEndDate.equalsIgnoreCase("T")==true)
				{
					
					System.out.println("Most rescent date chosen");
					break EndDateLoop;
				}
				try
				{
					endDate = ft.parse(StringedEndDate);
					if((endDate.before(todaysDate))&&(endDate.after(startDate)))
					{
						System.out.println("chosen end date is "+ft.format(endDate));
						break EndDateLoop;
					}
					//checking not before earliest date
					if(endDate.before(oldestdate))
					{
						JOptionPane.showMessageDialog(null, "Invalid date input, please enter after the intial date");
						continue;
					}
					//checking not in the future
					boolean validatedDateFuture = allActions[0].checkDateAisBeforeDateB(endDate,todaysDate);
					{
						if(validatedDateFuture==false)
						{
							JOptionPane.showMessageDialog(null, "Invalid date input, please enter a date in the past");
							continue;
						}
					}
				}
				catch(Exception exc)
				{
					exc.printStackTrace();
					JOptionPane.showMessageDialog(null, "Invalid date input, please follow format DD/MM/YYYY");
					continue;
				}
			}
			while(endDateSet ==false);
			//now we have a start point and an end point here before the data is sent to the creation method we can fidn the items
			//as the date entered by the user is just a date a time of 00 will be used so we can just compare normal dates
			
			int startindex =-1;
			boolean startIndexSet = false;
			int endIndex = -1;
			int numberOfActions = 0;
			loop:
			for(int counter = 0;counter<allActions.length;counter++)
			{
				//here we are checking the current action occured after the start date
				if(allActions[counter].DateActionPerformed.after(startDate))
				{
					System.out.println("action "+counter+" occured in time frame");
					numberOfActions++;
					
					//we need to set the start index the first time we get this met, otherwise this will be set to the last item it meets
					if(startIndexSet==false)
					{
						startindex = counter;
						startIndexSet = true;
					}
					
					//here we reach the end of the array, incase enddate was at the very end
					if(counter == allActions.length-1)
					{
						//System.out.println("end of array reached");
						endIndex = counter;
						break loop;//exits the loop
					}
				}
				if(allActions[counter].DateActionPerformed.after(endDate))
				{
					System.out.println("action "+counter+" occured out of the time frame");
					endIndex = counter;
					break loop;//exits the loop
				}
			}
			
			//here using the values we generated, we can construct an array of just the actions we want
			//here we can also use the starting index as a pointer to imm1ediatley locate the index we want to start at
			Action[] correctTimes = new Action[numberOfActions];
			if(numberOfActions==0)
			{
				JOptionPane.showMessageDialog(null, "No actions are present With this search");
			}
			for(int counter = 0;counter<numberOfActions;counter++)
			{
				correctTimes[counter]=allActions[startindex];
				//System.out.println(correctTimes[counter].DateActionPerformed);
				startindex++;
			}
			correctTimes[0].outputActions(correctTimes);
			return false;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "No actions are present on the account");
			return true;
		}
	}
	//in this method we will return to the user a list of all the employees on the system the return value will be a two dimensional array ordered
	//by employeeID in index 0 will be the employeeID and then index 1 will be their entity specific ID
	public String[][] returnAllEmployees()
	{
		String[][] AllEmployees;
		
		String systemFilename="";
		File folder = new File(System.getProperty("user.dir"));
		File[] listOfFiles = folder.listFiles();
		int numberOfEmployees =0;
		//counts the number of staff on the system
		for (int counter = 0;counter< listOfFiles.length;counter++)
		{
			try
			{
				systemFilename =listOfFiles[counter].getName().charAt(0)+ listOfFiles[counter].getName().substring(11,20);
				if((systemFilename.equals("S_file.txt"))||(systemFilename.equals("C_file.txt")))
				{
					numberOfEmployees++;
					//System.out.println("Employee added");
				}
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{
			}
		}
		
		AllEmployees =new String[numberOfEmployees][2];
		int counterForInnerArray=0;
		for (int counter = 0;counter< listOfFiles.length;counter++)
		{
			try
			{
				systemFilename =listOfFiles[counter].getName().charAt(0)+ listOfFiles[counter].getName().substring(11,20);
				if((systemFilename.equals("S_file.txt"))||(systemFilename.equals("C_file.txt")))
				{
					AllEmployees[counterForInnerArray][1] = (listOfFiles[counter].getName()).substring(0,11);
					AllEmployees[counterForInnerArray][0] = "E"+(listOfFiles[counter].getName()).substring(1,11);
					counterForInnerArray++;
				}
			}
			catch(Exception exc){}//if any errrors are found they are caught here	
		}
		//now we have all the staff we need to order them
		AllEmployees=quickSort(AllEmployees.length-1,0,AllEmployees);
		return(AllEmployees);
	}


	//method that searches through all the employees on the system using aa binary search
	//Parameters are a list of employeeIDs in order and the id wanting its index returned from the list
	public int searchAllemployees(String[][] AllEmployees,String desiredEmployeeID)
	{
		int[] itemList = new int[2];
		String[] employeesOnly = new String[AllEmployees.length];
		for(int counterThroughAllEmployees = 0;counterThroughAllEmployees<employeesOnly.length;counterThroughAllEmployees++)
		{
			employeesOnly[counterThroughAllEmployees]=AllEmployees[counterThroughAllEmployees][0];
			
		}
		itemList=searchPrimaryKeysBinary(employeesOnly,desiredEmployeeID,itemList);
		if(itemList[1]==1)
		{
			JOptionPane.showMessageDialog(null, "ID "+desiredEmployeeID+" not found.");
			return -1;
			
		}
		return(itemList[0]);
	}
	
	//This method is used to pass a new emplyee(either staff/consutlant) onto the employeeActionLog file
	//when passed through a search occurs where the file will search through the file to locate the index the employee needs to be located to
	//When found the new line is added to the file, this is done by adding a new index to array by increasing the length by 1
	//finally the correct values are concatenated together
	//then the array is written to file
	public void addNewEmployeeToActionLog(String employeeID,String entityID)
	{
		User tempUser = new User();
		//pulls entire file
		String[] listOfActionsAndEmployees = tempUser.rffReturnFullFile("EmployeeActionLogs.txt");//list of definitions is read from file
		String newData = employeeID+","+entityID+"~";
		
		String[][] AllEmployees = returnAllEmployees();//returns all the employees on the system
		int indexOfEmployee = searchAllemployees(AllEmployees,employeeID);
		
		//here we copy over to include new employee
		String[] copyOfList = new String[listOfActionsAndEmployees.length+1];
		for(int counter = 0;counter<indexOfEmployee;counter++)
		{
			copyOfList[counter]=listOfActionsAndEmployees[counter];
		}
		
		//we insert the new defentition
		copyOfList[indexOfEmployee]=newData;
		
		for(int counter2 = indexOfEmployee+1;counter2<copyOfList.length;counter2++)
		{
			copyOfList[counter2]=listOfActionsAndEmployees[counter2-1];
		}
		listOfActionsAndEmployees = copyOfList;	
		
		for(int counter3 = 0;counter3<listOfActionsAndEmployees.length;counter3++)
		{
			//System.out.println(listOfActionsAndEmployees[counter3]);
		}
		tempUser.writeNewDataTofile(listOfActionsAndEmployees,"EmployeeActionLogs.txt");
	}

}