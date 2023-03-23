import java.util.*;//imports the java utlities module	
import java.io.*;//imports the io package
import java.text.SimpleDateFormat;  
public class Managment extends Employee 
{
	
	boolean loggedIn;//declares attribute which holds thier loggedIn status
	Date startDate;//declares attribute which holds thier oldest action
	Date endDate;//declares attribute which holds thier newest action
	Scanner inputScanner = new Scanner(System.in);//object declaration for scanner to ercieve input in cmd
	SimpleDateFormat ftTimeInc = new SimpleDateFormat ("dd/MM/yyyy k:m");//declares the format for a simplified date
	Date chosenDate;//declares attribute which holds thier chosen date
	public static void main(String[] args )//declaring the main method
    {
    }
	public void createHomepage()
	{
		loggedIn = true;//makes the attribute true shich runs the infinite loop
		while(loggedIn==true)//termination condition preventing the page from closing
		{
			System.out.println("================Hello "+firstName+"================");
			System.out.println("\n");
			System.out.println("please enter an option");
			System.out.println("\n");
			System.out.println("1.Veiw an employees action log");
			System.out.println("\n");
			System.out.println("2.Encrypt Data");
			System.out.println("\n");
			System.out.println("3.Decrypt Data");
			System.out.println("\n");
			System.out.println("4.logout");
			System.out.println("\n");
			System.out.println("===================================================");
			String option = inputScanner.nextLine();
			if(option.equals("1")==true)
			{
				useActionLog();
			}
			else if(option.equals("4")==true)
			{
				loggedIn = false;
				test loginObj = new test();//creates the primary object of login
				loginObj.startFrameGUI();//the objects then performs the inital start method
			}
			else if(option.equals("2")==true)
			{
				encryptData();
			}
			else if(option.equals("3")==true)
			{
				decryptData();
			}
		}
	
	}
	public void useActionLog()
	{
		System.out.println("Please enter the id of the employee you want to retrieve the actions from.");
			String DesiredEmployee = inputScanner.nextLine();
			String stringedInfoForAction =findDesiredEmployee(DesiredEmployee);
			String[] arrayOfActions = unConcatenateStringActionLog(stringedInfoForAction,"~");
			String oldestdate = arrayOfActions[0].substring(0,16);
			System.out.println("Oldest date is||"+oldestdate);
			boolean startDateSet =false;
			do
			{
				try
				{
					System.out.println("Please enter the start date you want to see their actions: First instance of any activity is "+ftTimeInc.format(ftTimeInc.parse(oldestdate)));
				}
				catch(Exception exc)
				{
				}
				String StringedstartDate = inputScanner.nextLine();
				try
				{
					startDate  = ftTimeInc.parse(StringedstartDate);
					System.out.println("chosen start date is "+ftTimeInc.format(startDate));
					startDateSet =true;
				}
				catch(Exception exc)
				{
					System.out.print("invalid date given");
				}
			}
			while(startDateSet ==false);
				
			boolean endDateSet =false;
			do
			{
				System.out.println("Please enter the end date you want to see their actions or press R for today's date");
				String StringedEndDate = inputScanner.nextLine();
				Date todaysDate = new Date();
				try
				{
					chosenDate = ftTimeInc.parse(StringedEndDate);
					if((chosenDate.before(todaysDate))&&(chosenDate.after(startDate)))
					{
						System.out.println("chosen end date is "+ftTimeInc.format(chosenDate));
						endDate  = chosenDate;
						endDateSet =true;
					}
				}
				catch(Exception exc)
				{
					if(StringedEndDate.equalsIgnoreCase("R")==true)
					{
						endDate = new Date();
						System.out.println("Most rescent date chosen");
					}
				}
			}
			while(startDateSet ==false);
			
		
		outputEmployeeActionLog(startDate,endDate,arrayOfActions);
		
	}
	public String findDesiredEmployee(String employeeID)
	{
		String[] readInfo = rffReturnFullFile("Employee_Actionlog.txt");//retrieves the employees from file
		int indexOfEmployee = findIndexOfPatient(readInfo,employeeID);//finds the index of the employee
		String foundEmployee = readInfo[indexOfEmployee];//saves the index of the correct consultant from the array
		System.out.println("EMPLOYEE FOUND");//informs the user that the employe was found on the system
		return(foundEmployee);//returns the employee
		
	}
	public void outputEmployeeActionLog(Date startDate, Date endDate,String[] arrayOfActions)
	{
		Date tempDate = new Date();//declares a new object of date
		int startIndex  = -1;//sets the start index to -1 as a rouge value
		loop:
		for(int counter = 0;counter<arrayOfActions.length;counter++)
		{
			try
			{
				tempDate = ftTimeInc.parse(arrayOfActions[counter].substring(0,16));
			}
			catch(Exception exc)
				{
				}
			if(tempDate.equals(startDate))
			{
				startIndex = counter;
				System.out.println("Start at index "+startIndex);
				break loop;//exits the loop
			}
			if(tempDate.after(startDate))
			{
				startIndex = counter;
				System.out.println("Start at index "+startIndex);
				break loop;//exits the loop
			}
		}
		int numOfIterations = 0;
		
		loop2:
		for(int counter = startIndex;counter<arrayOfActions.length;counter++)
		{
			if(counter==-1)
			{
				System.out.println("NO ACTIONS WHERE FOUND BETWEEN THE DATES "+ ftTimeInc.format(startDate) +" AND "+ ftTimeInc.format(endDate));
				break loop2;//exits the loop
			}
			if(counter>=0)
			{
				System.out.println(arrayOfActions[counter]);
				try
				{
					tempDate = ftTimeInc.parse(arrayOfActions[counter].substring(0,16));
				}
				catch(Exception exc)
					{
					}
				if(tempDate.after(endDate))
				{
					startIndex = counter;
					break loop2;//exits the loop
				}
				if(tempDate.equals(endDate))
				{
					startIndex = counter;
					System.out.println(arrayOfActions[counter]);
					break loop2;//exits the loop
				}
			}
		}
	}
		public void encryptData()
		{
			String stringToBeEncrypted = inputScanner.nextLine();
			System.out.println("New data: "+encrypt(stringToBeEncrypted));
			
			
		}
		public void decryptData()
		{
			String stringToBeDecrypted = inputScanner.nextLine();
			System.out.println("New data: "+decrypt(stringToBeDecrypted));
			
		}
		
	
}