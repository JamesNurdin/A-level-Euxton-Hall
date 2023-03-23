import java.util.*;//imports the java utlities module	
import java.io.*;//imports the io package
import java.text.SimpleDateFormat;  
import javax.swing.*;//imports the java swing package,by using a wildcard all aspects of the package are imported
import java.time.*;
public class Management extends Employee 
{
	//======================== Entity Attributes ===================
	
	boolean loggedIn;//declares attribute which holds thier loggedIn status
	Date startDate;//declares attribute which holds thier oldest action
	Date endDate;//declares attribute which holds thier newest action
	Scanner inputScanner = new Scanner(System.in);//object declaration for scanner to ercieve input in cmd
	SimpleDateFormat ftTimeInc = new SimpleDateFormat ("dd/MM/yyyy k:m");//declares the format for a simplified date
	SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
	Date chosenDate;//declares attribute which holds thier chosen date
	
	
	//A general method wich produces a text based interface
	//A logical loop is provided to loop until a condition is statisified 
	//When a numerical value is entered if an option will call a method to execute
	public void createHomepage()
	{
		loggedIn = true;//makes the attribute true shich runs the infinite loop
		while(loggedIn==true)//termination condition preventing the page from closing
		{
			//main menue options
			System.out.println("================ Hello ================");
			System.out.println("\n");
			System.out.println("Please enter an option");
			System.out.println("\n");
			System.out.println("1.Veiw an employees action log");
			System.out.println("\n");
			System.out.println("2.Consultant management Option");
			System.out.println("\n");
			System.out.println("3.Staff management Option");
			System.out.println("\n");
			System.out.println("4.logout");
			System.out.println("\n");
			System.out.println("===================================================");
			String option = inputScanner.nextLine();
			if(option.equals("1")==true)
			{
				//calls upon the employee entity to load the action log method up
				boolean output = startActionLog();
				if(output==false)
				{
					loggedIn = false;
				}
				
			}
			else if(option.equals("4")==true)//checks to see if user wants to leave the current part
			{
				loggedIn = false;
				Gui loginObj = new Gui();//creates the primary object of login
				loginObj.startSystem();//the objects then performs the inital start method
			}
			else if(option.equals("2")==true)
			{
				boolean option2Loop = true;//makes the attribute true shich runs the infinite loop
				//menue options sub layer 2
				while(option2Loop==true)//termination condition preventing the page from closing
				{
					System.out.println("================Option 2 Consultants================");
					System.out.println("\n");
					System.out.println("Please enter an option");
					System.out.println("\n");
					System.out.println("1.Create a Consultant account");
					System.out.println("\n");
					System.out.println("2.Archive/Rearchive Consultant account");
					System.out.println("\n");
					System.out.println("(B) Go back");
					System.out.println("\n");
					System.out.println("===================================================");
					String option2LoopInput = inputScanner.nextLine();
					if(option2LoopInput.equals("B"))//checks to see if user wants to leave the current part
					{
						option2Loop=false;
					}
					else if(option2LoopInput.equals("1")==true)
					{
						createconsultant();
					}
					else if(option2LoopInput.equals("2")==true)
					{
						archiveRearchiveConsultant();
					}
				
				}
			}
			else if(option.equals("3")==true)
			{
				boolean option4Loop = true;//makes the attribute true shich runs the infinite loop
				//menue options sub layer 2
				while(option4Loop==true)//termination condition preventing the page from closing
				{
					System.out.println("================Option 3 staff================");
					System.out.println("\n");
					System.out.println("Please enter an option");
					System.out.println("\n");
					System.out.println("1.Create a staff account");
					System.out.println("\n");
					System.out.println("2.Archive/Rearchive Staff account");
					System.out.println("\n");
					System.out.println("(B) Go back");
					System.out.println("\n");
					System.out.println("===================================================");
					String option4LoopInput = inputScanner.nextLine();
					if(option4LoopInput.equals("B"))//checks to see if user wants to leave the current part
					{
						option4Loop=false;
					}
					else if(option4LoopInput.equals("1")==true)
					{
						createStaff();
					}
					else if(option4LoopInput.equals("2")==true)
					{
						archiveRearchiveStaff();
					}
				}
			}
		}
	
	}
	
	//Management enters all possible details in here
	//values which are INITIALLY hardcoded is done in te actiual creation method in the list class
	//the loop of each field will keep on iterating until the validation comes true
	//in there it is written to file
	public void createconsultant()
	{
		Consultant tempConsultant = new Consultant();//new instance of consultant
		//FIRST NAME
		//FIRST NAME
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's firstname.");
			String tmpFname = inputScanner.nextLine();
			tempConsultant.firstName = tmpFname;
			tempConsultant.validated=tempConsultant.presenceValidation(tempConsultant.firstName);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Firstname, missing.");
				continue;
			}
			tempConsultant.validated=tempConsultant.typeValidationString(tempConsultant.firstName);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Firstname, letters only.");
				continue;
			}
			tempConsultant.validated=tempConsultant.lesserLengthValidation(tempConsultant.firstName,25);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Firstname, must be less than 26 characters.");
				continue;
			}
		}
		
		//SURNAME
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's surname.");
			String tmpSname = inputScanner.nextLine();
			tempConsultant.surName = tmpSname;
			
			tempConsultant.validated=tempConsultant.presenceValidation(tempConsultant.surName);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Surname, missing.");
				continue;
			}
			tempConsultant.validated=tempConsultant.typeValidationString(tempConsultant.surName);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Surname, letters only.");
				continue;
			}
			tempConsultant.validated=tempConsultant.greaterLengthValidation(tempConsultant.surName,2);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Surname, must be at least three characters.");
				continue;
			}
			tempConsultant.validated=tempConsultant.lesserLengthValidation(tempConsultant.surName,25);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Surname, must be less than 26 characters.");
				continue;
			}
			tempConsultant.surName = tempConsultant.surName.substring(0, 1).toUpperCase() + tempConsultant.surName.substring(1);
		}
		
		//House number
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			
			try{
				System.out.println("Please enter the account's house number.");
				String tmpHouseNumber = inputScanner.nextLine();
				tempConsultant.addressHouseNum = Integer.parseInt(tmpHouseNumber);
				tempConsultant.validated=tempConsultant.presenceValidation(tempConsultant.addressHouseNum+"");
				if(tempConsultant.validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid house number, missing.");
					continue;
				}
			}
			catch(Exception exc)
			{
				JOptionPane.showMessageDialog(null, "Invalid house number, please enter an int.");
			}
		}
		
		//House street
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's street name.");
			String tmpaddressHouseStreet = inputScanner.nextLine();
			tempConsultant.addressHouseStreet = tmpaddressHouseStreet;
			//presnce datatype String
			tempConsultant.validated=tempConsultant.presenceValidation(tempConsultant.addressHouseStreet);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid street name, missing.");
				continue;
			}
			tempConsultant.validated=tempConsultant.typeValidationString(tempConsultant.addressHouseStreet);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid street name, letters only.");
				continue;
			}
			tempConsultant.validated=tempConsultant.lesserLengthValidation(tempConsultant.addressHouseStreet,25);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid street name, must be less than 26 characters.");
				continue;
			}
		}
		
		//Town
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's town name.");
			String tmpTown = inputScanner.nextLine();
			tempConsultant.town = tmpTown;
			tempConsultant.validated=tempConsultant.presenceValidation(tempConsultant.town);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid town, missing.");
				continue;
			}
			tempConsultant.validated=tempConsultant.typeValidationString(tempConsultant.town);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid town, letters only.");
				continue;
			}
			tempConsultant.validated=tempConsultant.lesserLengthValidation(tempConsultant.town,25);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid town, must be less than 26 characters.");
				continue;
			}
		}
		
		//postcode
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's postcode.");
			String tmppostcode = inputScanner.nextLine();
			tempConsultant.postcode = tmppostcode;
			tempConsultant.postcode = tempConsultant.postcode.replaceAll(" ","");
			tempConsultant.validated=tempConsultant.equalsualLengthValidation(tempConsultant.postcode,7);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Postcode, must be 7 characters.");
				continue;
			}
			tempConsultant.validated=tempConsultant.typeValidationStringOrInt(tempConsultant.postcode);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Postcode, can only be int and strings.");
				continue;
			}

		}
		
		//county
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's county.");
			String tmpcounty = inputScanner.nextLine();
			tempConsultant.county = tmpcounty;
			tempConsultant.validated=tempConsultant.presenceValidation(tempConsultant.county);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid county, missing.");
				continue;
			}
			tempConsultant.validated=tempConsultant.typeValidationString(tempConsultant.county);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid county, letters only.");
				continue;
			}
			tempConsultant.validated=tempConsultant.lesserLengthValidation(tempConsultant.county,25);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid county, must be less than 26 characters.");
				continue;
			}
		}
		
		//contact Number
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's contact number.");
			String tmpcontactNum = inputScanner.nextLine();
			tempConsultant.contactNum = tmpcontactNum;
			tempConsultant.validated=tempConsultant.equalsualLengthValidation(tempConsultant.contactNum,11);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid contact number, not 11 digigts.");
				continue;
			}
			tempConsultant.validated=tempConsultant.typeValidationInt(tempConsultant.contactNum);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid contact number, not int.");
				continue;
			}
		}
		
		//nationality
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's nationality.");
			String tmpnationality = inputScanner.nextLine();
			tempConsultant.nationality = tmpnationality;
			tempConsultant.validated=tempConsultant.presenceValidation(tempConsultant.nationality);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid nationality, missing.");
				continue;
			}
			tempConsultant.validated=tempConsultant.typeValidationString(tempConsultant.nationality);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid nationality, letters only.");
				continue;
				
			}
			tempConsultant.validated=tempConsultant.lesserLengthValidation(tempConsultant.nationality,25);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid nationality, must be less than 26 characters.");
				continue;
			}
		}
		
		//smoker
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			try
			{
				System.out.println("Please enter the account's number of cigarettes consumed each day(INT) between 0 and 25 inclusive.");
				String tmpsmoker = inputScanner.nextLine();
				tempConsultant.smoker = Integer.parseInt(tmpsmoker);
				tempConsultant.validated=tempConsultant.rangeValidation(0,25,tempConsultant.smoker);
				if(tempConsultant.validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid value, not between 0 and 25.");
					continue;
				}
			}
			catch(Exception exc)
			{
				JOptionPane.showMessageDialog(null, "Invalid value, please enter an int.");
			}
		}
		
		//drinker
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			try
			{
				System.out.println("Please enter the account's number units consumed each week(INT) between 0 and 30 inclusive.");
				String tmpdrinker = inputScanner.nextLine();
				tempConsultant.drinker = Integer.parseInt(tmpdrinker);
				tempConsultant.validated=tempConsultant.rangeValidation(0,30,tempConsultant.drinker);
				if(tempConsultant.validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid value, not between 0 and 30.");
					continue;
				}
			}
			catch(Exception exc)
			{
				JOptionPane.showMessageDialog(null, "Invalid value, please enter an int.");
			}
		}
		
		//dob
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's date of birth in the form DD/MM/YYYY.");
			String tmpdob = inputScanner.nextLine();
			try
			{
			tempConsultant.dob = ft.parse(tmpdob);
			
			}
			catch(Exception exc)//if any error occurs the error is caught
			{
				JOptionPane.showMessageDialog(null, "Date creation error");
				continue;
			}
			
			//now we have date as a Date dataType we can compare it
			
			Date date = new Date();
			Instant inst = date.toInstant();
			LocalDate localDate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
			Instant dayInst = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
			date = Date.from(dayInst);
			
			tempConsultant.validated=tempConsultant.checkDateAisBeforeDateB(tempConsultant.dob,date);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid date, Date Of Birth should not be in the future.");
				continue;
			}
		}
		
		//religon
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's religon.");
			String tmpreligon = inputScanner.nextLine();
			tempConsultant.religon = tmpreligon;
			tempConsultant.validated=tempConsultant.presenceValidation(tempConsultant.religon);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid religon, missing.");
				continue;
			}
			tempConsultant.validated=tempConsultant.typeValidationString(tempConsultant.religon);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid religon, letters only.");
				continue;
			}
			tempConsultant.validated=tempConsultant.lesserLengthValidation(tempConsultant.religon,25);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid religon, must be less than 26 characters.");
				continue;
			}
		}
		
		//allergies
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's allergies (leave blank for none) to seperate allergies use spaces");
			String tmpallergies = inputScanner.nextLine();
			tempConsultant.allergies = tmpallergies;
			if(tempConsultant.allergies.equals(""))
			{
				tempConsultant.allergies = "None";
			}			
			tempConsultant.allergies = tempConsultant.allergies.trim();
			tempConsultant.allergies = tempConsultant.allergies.replace(" ", "\n");//replaces \n with # prevent new lines from being created
			tempConsultant.validated=tempConsultant.typeValidationString(tempConsultant.allergies);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid allergies, letters only, to seperate allergies use spaces.");
				continue;
			}
		}
		
		//gender
		tempConsultant.validated = false;
		String[] genders = new String[] {"Male", "Female","Other"};//declares list of gender options
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's gender");
			System.out.println("Male\nFemale\nOther");
			String tmpgender = inputScanner.nextLine();
			tempConsultant.gender = tmpgender;
			tempConsultant.validated=tempConsultant.presenceValidation(tempConsultant.gender+"");
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid gender, missing.");
				continue;
			}
			tempConsultant.validated=tempConsultant.lookUpCheck(genders,tempConsultant.gender);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid gender, please enter an item from the list.");
				continue;
			}
		}
		
		//bloodtype
		tempConsultant.validated = false;
		String[] bloodTypes = new String[] {"A+", "A-","B+","B-","O+","O-","AB+","AB-"};//
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's bloodtype");
			System.out.println("A+\nA-\nB+\nB-\nO+\nO-\nAB+\nAB-");
			String tmpbloodType = inputScanner.nextLine();
			tempConsultant.bloodType = tmpbloodType;
			tempConsultant.validated=tempConsultant.presenceValidation(tempConsultant.bloodType+"");
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid bloodType, missing.");
				continue;
			}
			tempConsultant.validated=tempConsultant.lookUpCheck(bloodTypes,tempConsultant.bloodType);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid bloodType, please enter an item from the list.");
				continue;
			}
		}
		
		//disability
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's disability (leave blank for none), leave a space for any others");
			
			String tmpdisability = inputScanner.nextLine();
			tempConsultant.disability = tmpdisability;
			if(tempConsultant.disability.equals(""))
			{
				tempConsultant.disability = "None";
			}
			tempConsultant.disability = tempConsultant.disability.trim();
			tempConsultant.disability = tempConsultant.disability.replace(" ", "\n");//replaces \n with # prevent new lines from being created
			tempConsultant.validated=tempConsultant.typeValidationString(tempConsultant.disability);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid disability, letters only, to seperate disability use spaces.");
				continue;
			}
		}
		
		//carer
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's carer state (true or false)");
			String tmpcarer = inputScanner.nextLine();
			try
			{
				if((tmpcarer.equals("false"))||(tmpcarer.equals("true")))
				{
					tempConsultant.carer = Boolean.parseBoolean(tmpcarer);
					tempConsultant.validated = true;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "carer creation error please enter true or false");
					continue;
				}
				
			}
			catch(Exception exc)//if any error occurs the error is caught
			{
				JOptionPane.showMessageDialog(null, "carer creation error please enter true or false");
				continue;
			}
		}
		
		//translator
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's translator state (true or false)");
			String tmptranslator = inputScanner.nextLine();
			try
			{
				if((tmptranslator.equals("false"))||(tmptranslator.equals("true")))
				{
					tempConsultant.translator = Boolean.parseBoolean(tmptranslator);
					tempConsultant.validated = true;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "translator creation error please enter true or false");
					continue;
				}
			
			}
			catch(Exception exc)//if any error occurs the error is caught
			{
				JOptionPane.showMessageDialog(null, "translator creation error please enter true or false");
				continue;
			}
		}
		
		//sex
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's sex");
			System.out.println("Male\nFemale\nOther");
			String tmpsex = inputScanner.nextLine();
			tempConsultant.sex = tmpsex;
			tempConsultant.validated=tempConsultant.presenceValidation(tempConsultant.sex+"");
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid sex, missing.");
				continue;
			}
			tempConsultant.validated=tempConsultant.lookUpCheck(genders,tempConsultant.sex);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid sex, please enter an item from the list.");
				continue;
			}
		}
		
		//Days sinceLast Updated
		Date currentTime = new Date();//declares the date of the closest appointment
		tempConsultant.daysSinceLastUpdate=currentTime;//updates the patients time to the current time
		
		//wage
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's wage as real 2dp");
			String tempwage = inputScanner.nextLine();
			try
			{
				tempConsultant.wage = Double.parseDouble(tempwage);
				tempConsultant.validated=tempConsultant.rangeValidation(1,9999,tempConsultant.wage);
				if(tempConsultant.validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid value, not between 1 and 9999.");
					continue;
				}
			}
			catch(Exception exc)//if any error occurs the error is caught
			{
				JOptionPane.showMessageDialog(null, "wage creation error please enter a real value 2dp");
				continue;
			}
		}
		
		//hoursPerWeek
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			try
			{
				System.out.println("Please enter the account's hoursPerWeek between 1 and 48 inclusive");
				String tmphoursPerWeek = inputScanner.nextLine();
				tempConsultant.hoursPerWeek = Integer.parseInt(tmphoursPerWeek);
				tempConsultant.validated=tempConsultant.presenceValidation(tempConsultant.hoursPerWeek+"");
				if(tempConsultant.validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid hours per week, missing.");
					continue;
				}
				tempConsultant.validated=tempConsultant.rangeValidation(1,48,tempConsultant.hoursPerWeek);
				if(tempConsultant.validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid value, not between 1 and 48.");
					continue;
				}
			}
			catch(Exception exc)
			{
				JOptionPane.showMessageDialog(null, "Invalid  hours per week, please enter an int.");
			}
		}

		//Ward 
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			System.out.println("Please enter the account's located ward");
			String tmpwardLocatedIn = inputScanner.nextLine();
			tempConsultant.wardLocatedIn = tmpwardLocatedIn;
			tempConsultant.validated=tempConsultant.presenceValidation(tempConsultant.wardLocatedIn);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid wardLocatedIn, missing.");
				continue;
			}
			tempConsultant.validated=tempConsultant.typeValidationString(tempConsultant.wardLocatedIn);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid ward, letters only.");
				continue;
			}
			tempConsultant.validated=tempConsultant.lesserLengthValidation(tempConsultant.wardLocatedIn,25);
			if(tempConsultant.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid ward, must be less than 26 characters.");
				continue;
			}
		}
		int numOfDis = 0;
		//disiplines
		tempConsultant.validated = false;
		while(tempConsultant.validated == false)
		{
			numOfDis = 0;
			try{
				System.out.println("Please enter the account's number Of disiplines(5 max)");
				String tmpNumofDis = inputScanner.nextLine();
				numOfDis= Integer.parseInt(tmpNumofDis);
				tempConsultant.validated=tempConsultant.presenceValidation(numOfDis+"");
				if(tempConsultant.validated==false)
				{
					JOptionPane.showMessageDialog(null, "number Of disiplines, missing.");
					continue;
				}
				tempConsultant.validated=tempConsultant.rangeValidation(1,5,numOfDis);
				if(tempConsultant.validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid value, not between 1 and 5.");
					continue;
				}
			}
			catch(Exception exc)
			{
				JOptionPane.showMessageDialog(null, "number Of disiplines, please enter an int.");
			}
		}
		
		//creates an array with the size of the number of disiplines the consultant has
		tempConsultant.expertiese = new String[numOfDis];
		//for loop to enter for every disipline
		for(int counterForDis = 0; counterForDis< numOfDis;counterForDis++)
		{
			tempConsultant.validated = false;
			while(tempConsultant.validated == false)
			{
				System.out.println("Disipline "+counterForDis);
				String tmpdisiplinebuffer = inputScanner.nextLine();		
				tempConsultant.expertiese[counterForDis] = tmpdisiplinebuffer;
				tempConsultant.validated=tempConsultant.presenceValidation(tempConsultant.expertiese[counterForDis]);
				if(tempConsultant.validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid expertiese, missing.");
					continue;
				}
				tempConsultant.validated=tempConsultant.typeValidationString(tempConsultant.expertiese[counterForDis]);
				if(tempConsultant.validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid expertiese, letters only.");
					continue;
				}
				tempConsultant.validated=tempConsultant.lesserLengthValidation(tempConsultant.expertiese[counterForDis],25);
				if(tempConsultant.validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid expertiese, must be less than 26 characters.");
					continue;
				}
			}
		}
		
		ConsultantList cl = new ConsultantList();
		tempConsultant = cl.createConsultantFully(tempConsultant);
		System.out.println("================================");
		System.out.println("The account's password is this: "+tempConsultant.password+" Please write it down");
		System.out.println("=================================");
		addNewEmployeeToActionLog(tempConsultant.employeeID,tempConsultant.consultantID);
	}
	
	
	
	//Management enters all possible details in here
	//in this method every field is painstakenly validated for every single attritbutte, a wide range of checking occurs
	//the loop of each field will keep on iterating until the validation comes true
	//in there it is written to file
	public void createStaff()
	{
		Staff tempStaff = new Staff();//new instance of consultant
		//FIRST NAME
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			System.out.println("Please enter the account's Firstname");
			String tmpFname = inputScanner.nextLine();
			tempStaff.firstName = tmpFname;
			tempStaff.validated=tempStaff.presenceValidation(tempStaff.firstName);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Firstname, missing.");
				continue;
			}
			tempStaff.validated=tempStaff.typeValidationString(tempStaff.firstName);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Firstname, letters only.");
				continue;
			}
			tempStaff.validated=tempStaff.lesserLengthValidation(tempStaff.firstName,25);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Firstname, must be less than 26 characters.");
				continue;
			}
		}
		
		//SURNAME
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			System.out.println("Please enter the account's Surname");
			String tmpSname = inputScanner.nextLine();
			tempStaff.surName = tmpSname;
			
			tempStaff.validated=tempStaff.presenceValidation(tempStaff.surName);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Surname, missing.");
				continue;
			}
			tempStaff.validated=tempStaff.typeValidationString(tempStaff.surName);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Surname, letters only.");
				continue;
			}
			tempStaff.validated=tempStaff.greaterLengthValidation(tempStaff.surName,2);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Surname, must be three characters.");
				continue;
			}
			tempStaff.validated=tempStaff.lesserLengthValidation(tempStaff.surName,25);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Surname, must be less than 26 characters.");
				continue;
			}
			tempStaff.surName = tempStaff.surName.substring(0, 1).toUpperCase() + tempStaff.surName.substring(1);
		}
		
		
		//House number
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			
			try{
				System.out.println("Please enter the account's House Number");
				String tmpHouseNumber = inputScanner.nextLine();
				tempStaff.addressHouseNum = Integer.parseInt(tmpHouseNumber);
				tempStaff.validated=tempStaff.presenceValidation(tempStaff.addressHouseNum+"");
				if(tempStaff.validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid house number, missing.");
					continue;
				}
			}
			catch(Exception exc)
			{
				JOptionPane.showMessageDialog(null, "Invalid house number, please enter an int.");
			}
		}
		
		//House street
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			System.out.println("Please enter the account's Street");
			String tmpaddressHouseStreet = inputScanner.nextLine();
			tempStaff.addressHouseStreet = tmpaddressHouseStreet;
			//presnce datatype String
			tempStaff.validated=tempStaff.presenceValidation(tempStaff.addressHouseStreet);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid street name, missing.");
				continue;
			}
			tempStaff.validated=tempStaff.typeValidationString(tempStaff.addressHouseStreet);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid street name, letters only.");
				continue;
			}
			tempStaff.validated=tempStaff.lesserLengthValidation(tempStaff.addressHouseStreet,25);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid street name, must be less than 26 characters.");
				continue;
			}
		}
		
		//Town
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			System.out.println("Please enter the account's Town");
			String tmpTown = inputScanner.nextLine();
			tempStaff.town = tmpTown;
			tempStaff.validated=tempStaff.presenceValidation(tempStaff.town);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid town, missing.");
				continue;
			}
			tempStaff.validated=tempStaff.typeValidationString(tempStaff.town);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid town, letters only.");
				continue;
			}
			tempStaff.validated=tempStaff.lesserLengthValidation(tempStaff.town,25);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid town, must be less than 26 characters.");
				continue;
			}
		}
		
		//postcode
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			System.out.println("Please enter the account's postcode");
			String tmppostcode = inputScanner.nextLine();
			tempStaff.postcode = tmppostcode;
			tempStaff.postcode = tempStaff.postcode.replaceAll(" ","");
			tempStaff.validated=tempStaff.equalsualLengthValidation(tempStaff.postcode,7);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Postcode, must be 7 characters.");
				continue;
			}
			tempStaff.validated=tempStaff.typeValidationStringOrInt(tempStaff.postcode);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid Postcode, can only be int and strings.");
				continue;
			}

		}
		
		//county
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			System.out.println("Please enter the account's county");
			String tmpcounty = inputScanner.nextLine();
			tempStaff.county = tmpcounty;
			tempStaff.validated=tempStaff.presenceValidation(tempStaff.county);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid county, missing.");
				continue;
			}
			tempStaff.validated=tempStaff.typeValidationString(tempStaff.county);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid county, letters only.");
				continue;
			}
			tempStaff.validated=tempStaff.lesserLengthValidation(tempStaff.county,25);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid county, must be less than 26 characters.");
				continue;
			}
		}
		
		//contactNum
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			System.out.println("Please enter the account's contactNum");
			String tmpcontactNum = inputScanner.nextLine();
			tempStaff.contactNum = tmpcontactNum;
			tempStaff.validated=tempStaff.equalsualLengthValidation(tempStaff.contactNum,11);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid contact number, not 11 digigts.");
				continue;
			}
			tempStaff.validated=tempStaff.typeValidationInt(tempStaff.contactNum);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid contact number, not int.");
				continue;
			}
		}
		
		//nationality
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			System.out.println("Please enter the account's nationality");
			String tmpnationality = inputScanner.nextLine();
			tempStaff.nationality = tmpnationality;
			tempStaff.validated=tempStaff.presenceValidation(tempStaff.nationality);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid nationality, missing.");
				continue;
			}
			tempStaff.validated=tempStaff.typeValidationString(tempStaff.nationality);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid nationality, letters only.");
				continue;
				
			}
			tempStaff.validated=tempStaff.lesserLengthValidation(tempStaff.nationality,25);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid nationality, must be less than 26 characters.");
				continue;
			}
		}
		
		//smoker
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			try
			{
				System.out.println("Please enter the account's cigarettes consumed each day(INT) between 0 and 25 inclusive");
				String tmpsmoker = inputScanner.nextLine();
				tempStaff.smoker = Integer.parseInt(tmpsmoker);
				tempStaff.validated=tempStaff.rangeValidation(0,25,tempStaff.smoker);
				if(tempStaff.validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid value, not between 0 and 25.");
					continue;
				}
			}
			catch(Exception exc)
			{
				JOptionPane.showMessageDialog(null, "Invalid value, please enter an int.");
			}
		}
		
		//drinker
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			try
			{
				System.out.println("Please enter the account's units consumed each week(INT) between 0 and 30 inclusive");
				String tmpdrinker = inputScanner.nextLine();
				tempStaff.drinker = Integer.parseInt(tmpdrinker);
				tempStaff.validated=tempStaff.rangeValidation(0,30,tempStaff.drinker);
				if(tempStaff.validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid value, not between 0 and 30.");
					continue;
				}
			}
			catch(Exception exc)
			{
				JOptionPane.showMessageDialog(null, "Invalid value, please enter an int.");
			}
		}
		
		//dob
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			System.out.println("Please enter the account's date of birth in the form DD/MM/YYYY");
			String tmpdob = inputScanner.nextLine();
			try
			{
			tempStaff.dob = ft.parse(tmpdob);
			
			}
			catch(Exception exc)//if any error occurs the error is caught
			{
				JOptionPane.showMessageDialog(null, "Date creation error");
				continue;
			}
			
			//now we have date as a Date dataType we can compare it
			
			Date date = new Date();
			Instant inst = date.toInstant();
			LocalDate localDate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
			Instant dayInst = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
			date = Date.from(dayInst);
			
			tempStaff.validated=tempStaff.checkDateAisBeforeDateB(tempStaff.dob,date);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid date, Date Of Birth should not be in the future.");
				continue;
			}
		}
		
		//religon
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			System.out.println("Please enter the account's religon");
			String tmpreligon = inputScanner.nextLine();
			tempStaff.religon = tmpreligon;
			tempStaff.validated=tempStaff.presenceValidation(tempStaff.religon);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid religon, missing.");
				continue;
			}
			tempStaff.validated=tempStaff.typeValidationString(tempStaff.religon);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid religon, letters only.");
				continue;
			}
			tempStaff.validated=tempStaff.lesserLengthValidation(tempStaff.religon,25);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid religon, must be less than 26 characters.");
				continue;
			}
		}
		
		//allergies
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			System.out.println("Please enter the account's allergies (leave blank for none) to seperate allergies use spaces");
			String tmpallergies = inputScanner.nextLine();
			tempStaff.allergies = tmpallergies;
			if(tempStaff.allergies.equals(""))
			{
				tempStaff.allergies = "None";
			}	
			tempStaff.allergies = tempStaff.allergies.trim();
			tempStaff.allergies = tempStaff.allergies.replace(" ", "\n");//replaces \n with # prevent new lines from being created
			tempStaff.validated=tempStaff.typeValidationString(tempStaff.allergies);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid allergies, letters only, to seperate allergies use spaces.");
				continue;
			}
		}
		
		//gender
		tempStaff.validated = false;
		String[] genders = new String[] {"Male", "Female","Other"};//declares list of gender options
		while(tempStaff.validated == false)
		{
			System.out.println("Please enter the account's gender");
			System.out.println("Male\nFemale\nOther");
			String tmpgender = inputScanner.nextLine();
			tempStaff.gender = tmpgender;
			tempStaff.validated=tempStaff.presenceValidation(tempStaff.gender+"");
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid gender, missing.");
				continue;
			}
			tempStaff.validated=tempStaff.lookUpCheck(genders,tempStaff.gender);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid gender, please enter an item from the list.");
				continue;
			}
		}
		
		//bloodtype
		tempStaff.validated = false;
		String[] bloodTypes = new String[] {"A+", "A-","B+","B-","O+","O-","AB+","AB-"};//
		while(tempStaff.validated == false)
		{
			System.out.println("Please enter the account's bloodtype");
			System.out.println("A+\nA-\nB+\nB-\nO+\nO-\nAB+\nAB-");
			String tmpbloodType = inputScanner.nextLine();
			tempStaff.bloodType = tmpbloodType;
			tempStaff.validated=tempStaff.presenceValidation(tempStaff.bloodType+"");
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid bloodType, missing.");
				continue;
			}
			tempStaff.validated=tempStaff.lookUpCheck(bloodTypes,tempStaff.bloodType);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid bloodType, please enter an item from the list.");
				continue;
			}
		}
		
		//disability
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			System.out.println("Please enter the account's disability (Leave blank for none, leave a space for any others");
			
			String tmpdisability = inputScanner.nextLine();
			tempStaff.disability = tmpdisability;
			if(tempStaff.disability.equals(""))
			{
				tempStaff.disability = "None";
			}
			tempStaff.disability = tempStaff.disability.trim();
			tempStaff.disability = tempStaff.disability.replace(" ", "\n");//replaces \n with # prevent new lines from being created
			tempStaff.validated=tempStaff.typeValidationString(tempStaff.disability);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid disability, letters only, to seperate disability use spaces.");
				continue;
			}
		}
		
		//carer
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			System.out.println("Please enter the account's carer state (true or false)");
			String tmpcarer = inputScanner.nextLine();
			try
			{
				if((tmpcarer.equals("false"))||(tmpcarer.equals("true")))
				{
					tempStaff.carer = Boolean.parseBoolean(tmpcarer);
					tempStaff.validated = true;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "carer creation error please enter true or false");
					continue;
				}
				
			}
			catch(Exception exc)//if any error occurs the error is caught
			{
				JOptionPane.showMessageDialog(null, "carer creation error please enter true or false");
				continue;
			}
		}
		
		//translator
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			System.out.println("Please enter the account's translator state (true or false)");
			String tmptranslator = inputScanner.nextLine();
			try
			{
				if((tmptranslator.equals("false"))||(tmptranslator.equals("true")))
				{
					tempStaff.translator = Boolean.parseBoolean(tmptranslator);
					tempStaff.validated = true;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "translator creation error please enter true or false");
					continue;
				}
			
			}
			catch(Exception exc)//if any error occurs the error is caught
			{
				JOptionPane.showMessageDialog(null, "translator creation error please enter true or false");
				continue;
			}
		}
		
		//sex
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			System.out.println("Please enter the account's sex");
			System.out.println("Male\nFemale\nOther");
			String tmpsex = inputScanner.nextLine();
			tempStaff.sex = tmpsex;
			tempStaff.validated=tempStaff.presenceValidation(tempStaff.sex+"");
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid sex, missing.");
				continue;
			}
			tempStaff.validated=tempStaff.lookUpCheck(genders,tempStaff.sex);
			if(tempStaff.validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid sex, please enter an item from the list.");
				continue;
			}
		}
		
		//Days sinceLast Updated
		Date currentTime = new Date();//declares the date of the closest appointment
		tempStaff.daysSinceLastUpdate=currentTime;//updates the patients time to the current time
		
		//wage
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			System.out.println("Please enter the account's wage as real 2dp");
			String tempwage = inputScanner.nextLine();
			try
			{
				tempStaff.wage = Double.parseDouble(tempwage);
				tempStaff.validated=tempStaff.rangeValidation(1,9999,tempStaff.wage);
				if(tempStaff.validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid value, not between 1 and 9999.");
					continue;
				}
			}
			catch(Exception exc)//if any error occurs the error is caught
			{
				JOptionPane.showMessageDialog(null, "wage creation error please enter a real value 2dp");
				continue;
			}
		}
		
		//hoursPerWeek
		tempStaff.validated = false;
		while(tempStaff.validated == false)
		{
			try
			{
				System.out.println("Please enter the account's hoursPerWeek 1 and 48 inclusive");
				String tmphoursPerWeek = inputScanner.nextLine();
				tempStaff.hoursPerWeek = Integer.parseInt(tmphoursPerWeek);
				tempStaff.validated=tempStaff.presenceValidation(tempStaff.hoursPerWeek+"");
				if(tempStaff.validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid hours per week, missing.");
					continue;
				}
				tempStaff.validated=tempStaff.rangeValidation(1,48,tempStaff.hoursPerWeek);
				if(tempStaff.validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid value, not between 1 and 48.");
					continue;
				}
			}
			catch(Exception exc)
			{
				JOptionPane.showMessageDialog(null, "Invalid  hours per week, please enter an int.");
			}
		}
		
		
		StaffList sl = new StaffList();
		//using the staff list instnace the instance is written to file
		tempStaff = sl.createstaffFully(tempStaff);
		System.out.println("================================");
		System.out.println("The account's password is this: "+tempStaff.password+" Please write it down");
		System.out.println("=================================");
		addNewEmployeeToActionLog(tempStaff.employeeID,tempStaff.staffID);
	}
	
	//This process is basically just a front to prepare the system to actually perform the task
	//All the validation occurs here 
	//It will display all the acounts on the system, along with their active status
	//it will also detect if that if there is only one account left it will prohibit the function (one staff account must exist)
	// a search occurs where the input is found
	//it will then check its eligability to be archived or not
	//it will then pull all the active staff one system and pass them through the method underneath this
	public void archiveRearchiveStaff()
	{
		StaffList sl = new StaffList();
		String[] staffListIDs = sl.returnAllStaff();//returns all the staffIDs on the system
		Staff[] listOfStaff = new Staff[staffListIDs.length];
		int numberOfStaffNotArchived = 0;//Check variable to make sure at least one staff account is on the system
		//creates every instance of staff on the system, whilest also counting how many active staff exists
		Staff tempInstance = new Staff();
		for(int countTOCopyStaff =0;countTOCopyStaff<listOfStaff.length;countTOCopyStaff++)
		{
			listOfStaff[countTOCopyStaff] = tempInstance.retrieveStaff(staffListIDs[countTOCopyStaff]);//pulls instance of the staff on the system
			if(listOfStaff[countTOCopyStaff].archived==false)
			{
				numberOfStaffNotArchived++;//increments counter for staff that are not archived
			}
			
		}
		boolean archiveRearchiveStaffLoop = true;//makes the attribute true shich runs the infinite loop
		
		EscapeLoop: //Label to escape the process if management wants to stop
		while(archiveRearchiveStaffLoop==true)//termination condition preventing the page from closing
		{
			//menue options
			System.out.println("================Change active status of staff================");
			System.out.println("\n");
			System.out.println("All staff accounts:");
			System.out.println("\n");
			for(int counter = 0;counter<staffListIDs.length;counter++)
			{
				if(listOfStaff[counter].archived==true)
				{
					//output all staff, including some minor details
					System.out.println(listOfStaff[counter].staffID+","+listOfStaff[counter].surName+","+listOfStaff[counter].firstName+" ARCHIVED");
			
				}
				
				else if(listOfStaff[counter].archived==false)
				{
					//output all staff, including some minor details
					System.out.println(listOfStaff[counter].staffID+","+listOfStaff[counter].surName+","+listOfStaff[counter].firstName+" ACTIVE");
			
				}	
			}
			System.out.println("\n");
			System.out.println("Please enter the staff accounts ID");
			System.out.println("\n");
			System.out.println("(B) Go back");
			System.out.println("=============================================================");
			String desiredStaffID = inputScanner.nextLine();
			
			//selection statements
			if(desiredStaffID.equals("B"))//checks to see if user wants to leave the current part
			{
				archiveRearchiveStaffLoop=false;
				break EscapeLoop;
			}
			boolean validated =tempInstance.presenceValidation(desiredStaffID);
			if(validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid option, cant be null.");
				continue;
			}
			
			if((desiredStaffID.charAt(0)+"").equals("S"))//finds the instance of the staff account
			{
				boolean found = false;
				int indexOfStaff =-1;
				for(int counter1 = 0;counter1<staffListIDs.length;counter1++)
				{
					//checks if the staff was found
					if(desiredStaffID.equals(staffListIDs[counter1]))
					{
						found =true;
						System.out.println("Staff account found");
						indexOfStaff = counter1;
					}
				}
				if(found==true)
				{
					Staff staffToAmmend = new Staff();//initalises instance of staff to use
					staffToAmmend = listOfStaff[indexOfStaff];//assigns the correct instance of staff
					boolean finalWarning = true;//makes the attribute true shich runs the infinite loop
					while(finalWarning==true)//termination condition preventing the page from closing
					{
						
						if(staffToAmmend.archived==true)
						{
							//checks that the current staff is not the only active account on the system
							System.out.println("Staff is currently archived on the system");
						}
						
						if(staffToAmmend.archived==false)
						{
							System.out.println("Staff is currently active on the system");
							if(numberOfStaffNotArchived==1)
							{
								System.out.println("Sorry at least 1 staff account must always exist on the system at all times");
								break EscapeLoop;
							}
							
						}	
						
						//final warnings
						System.out.println("Are You sure you want to proceed");
						System.out.println("\n");
						System.out.println("(Y) Yes");
						System.out.println("(N) No");
						System.out.println("\n");
						String lastChanceWarning = inputScanner.nextLine();
						if(lastChanceWarning.equals("Y"))//checks to see if user wants to leave the current part
						{
							//for respective account one of the two will occur
							if(staffToAmmend.archived==true)
							{
								//rearchieve staff
								staffToAmmend.archived=false;
								staffToAmmend.updateDemographicDetails(staffToAmmend);
								System.out.println("Staff added to system");
								break EscapeLoop; //Label to escape the process if management wants to stop
							}
							
							if(staffToAmmend.archived==false)
							{
								int insideCounter= 0;//counter to place staff that are active into the array
								Staff[] arrayOfnonArchivedStaff = new Staff[numberOfStaffNotArchived-1];//the minus one is to also include the staff account we want to delete
								for(int indexcounter1 =0;indexcounter1<listOfStaff.length;indexcounter1++)
								{
									//here we are ommiting the staff which are archived and the account we wish to archive
									if((indexOfStaff!=indexcounter1)&&(listOfStaff[indexcounter1].archived==false))
									{
										arrayOfnonArchivedStaff[insideCounter]=listOfStaff[indexcounter1];//add to the account to the arary
										System.out.println(arrayOfnonArchivedStaff[insideCounter].staffID+" Has been added to the list of staff");
										insideCounter++;
									}
								}
								archiveStaff(staffToAmmend,arrayOfnonArchivedStaff);//calls method which updates entire system
								break EscapeLoop; //Label to escape the process if management wants to stop
							}
						}
						if(lastChanceWarning.equals("N"))//checks to see if user wants to leave the current part
						{
							break EscapeLoop;//if user at last minute wants to stop the process
						}
					}
					
					
				}
				if(found==false)//checks to see if staff id exists
				{
					System.out.println("Account was not found");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid option, Please select an entity.");
				
			}
		}
	}

	//Here the patients get split between the remainingStaff
	//we then iterate through every staff account adding the intended number of patients
	//each time the patient also gets updated
	//at the end the desired staff account also gets updated
	public void archiveStaff(Staff staffToArchive,Staff[] listOfStaff)
	{
		int numberOfStaffOnSystem = listOfStaff.length;
		int numberOfPateintsStaffHas = staffToArchive.numberOfPatients;
		
		
		//retrieves all patient instances
		Patient tempInstance = new Patient();
		Patient[] listOfPatients = new Patient[numberOfPateintsStaffHas];
		for(int countTOCopyPatient =0;countTOCopyPatient<listOfPatients.length;countTOCopyPatient++)
		{
			listOfPatients[countTOCopyPatient] = tempInstance.retrievePatient(staffToArchive.patientIDs[countTOCopyPatient]);//pulls instance of the staff on the system
		}
		
		
		//THIS SECTION DEALS WITH UPDATING THE PATIENTS AND ADDING THEM TO THE NEW STAFF ACCOUNTS
		//this method runs like the methods for the GUI when calculating the number of panels but works a slight different
		//uses an advanced line of code 
		//here we are checking we cant just set the staff as archived
		if(staffToArchive.numberOfPatients>0)
		{
								
			int patientPerStaff = (numberOfPateintsStaffHas / numberOfStaffOnSystem);
			int remainingStaff = (numberOfPateintsStaffHas % numberOfStaffOnSystem);
			int[] extraPatientsArray = new int[numberOfStaffOnSystem];
			int startingIndexForPateientArray=0;//this will indicate which part of the array to copy the instances of patients at the end this will equal the sum of patients
			for (int j = 1; j <= numberOfStaffOnSystem-1; j++)
			{
            extraPatientsArray[j] = (j <= remainingStaff) ? 1:0;
			
			}
			for (int i = 0; i < numberOfStaffOnSystem; i++)
			{
				System.out.println("staff " + listOfStaff[i].staffID + " will gain " + (patientPerStaff + extraPatientsArray[i]) + " patients.");
				int numberOfPatientsToMove = (patientPerStaff + extraPatientsArray[i]);//length not index
				//this works by creating a new array of patients for each staff account
				//starting at the OG staffs patient list index 0 
				System.out.println("staff "+(i+1)+" has thses patients: ");
				Patient[] listOfNewPatients = new Patient[numberOfPatientsToMove];
				for(int insideCounter = 0;insideCounter<numberOfPatientsToMove;insideCounter++)
				{
					listOfNewPatients[insideCounter]=listOfPatients[startingIndexForPateientArray];
					//System.out.println(listOfNewPatients[insideCounter].patientID);
					startingIndexForPateientArray++;
					
				}
				//now we have an array of patients that need to move and a staff member who will gain them 
				//we will need to loop through every patient
				String[] fileStaffContents = rffReturnFullFile(listOfStaff[i].staffID+"_file.txt");//concatinates the string into an array
				//we have the entire file now we need just the patients 
				String[] listOfStaffItems;//holds an array of all the patients possed by the staff
				listOfStaffItems=fileStaffContents[1].split(",");//splits up the staffs patients into a list
				int[] itemList = new int[2];
				String stringedStaffPatientInfo = "";
				for(int patientCounter=0;patientCounter<listOfNewPatients.length;patientCounter++)
				{
					if(listOfStaffItems.length>1)//checks if the staffs list of patients is more than 1, FIRST INDEX REPRESENTS THE ACCOUNTS NUMBER OF PATIENTS
					{
						//this method adds patients to the list of patients for the consutlant, we can loop it here to continously add the rest of the patients
						
						itemList = searchPrimaryKeysLinear(Arrays.copyOfRange(listOfStaffItems,1,listOfStaffItems.length),listOfNewPatients[patientCounter].patientID,itemList);
						//System.out.println("Position "+itemList[0]);
					//	System.out.println("New ? "+itemList[1]);
						int indexOfPatient = itemList[0]+1;//sets index as a variable
						String[] copyOfStaffFile = new String[listOfStaffItems.length+1];
						for(int firstpart =0;firstpart<indexOfPatient;firstpart++)
						{
							copyOfStaffFile[firstpart]= listOfStaffItems[firstpart];
						}
						//now adds the rest of the surnames purposefully leaving a space for the new surname
						for(int secondPart =indexOfPatient+1;secondPart<listOfStaffItems.length+1;secondPart++)
						{
							copyOfStaffFile[secondPart]= listOfStaffItems[secondPart-1];
						}
						//adds the new surname and that now there is 1 on the system
						copyOfStaffFile[indexOfPatient]=listOfNewPatients[patientCounter].patientID;
						//updates the old array with the old surnames
						listOfStaffItems=copyOfStaffFile;// the patient has now got the position in the consultant file added
					
					
						//now we have every patient on the list so we need to concatenate them together
						
						
						
					}
					else
					{//staff has 0 patients we can force in the patient and update the number manually
						String[] copyOfStaffFile = new String[listOfStaffItems.length+1];
						
						copyOfStaffFile[0]= "1";
						copyOfStaffFile[1] = listOfNewPatients[patientCounter].patientID;
						listOfStaffItems=copyOfStaffFile;// the patient has now got the position in the consultant file added
					}
					listOfNewPatients[patientCounter].linkedStaffID=listOfStaff[i].staffID;
					System.out.println(listOfNewPatients[patientCounter].patientID+" will have a new staff of "+listOfNewPatients[patientCounter].linkedStaffID);
					listOfNewPatients[patientCounter] = listOfNewPatients[patientCounter].createNewNotification(listOfNewPatients[patientCounter],"Your linked staff account ("+staffToArchive.staffID+") has been archived on the system. You have now been issued to "+listOfNewPatients[patientCounter].linkedStaffID);

					listOfNewPatients[patientCounter].updateDemographicDetails(listOfNewPatients[patientCounter]);
			}
				for(int counter = 1;counter<listOfStaffItems.length;counter++)//by setting the first pointer index as 1 we can skip the old number of paitents
				{
					stringedStaffPatientInfo=stringedStaffPatientInfo+listOfStaffItems[counter]+",";
					//System.out.println("line "+counter+ " " +listOfStaffItems[counter]);
					//System.out.println("current contents "+stringedStaffPatientInfo);
				}
				stringedStaffPatientInfo = (listOfStaff[i].numberOfPatients+numberOfPatientsToMove)+","+stringedStaffPatientInfo;
				fileStaffContents[1] = stringedStaffPatientInfo;
				System.out.println(listOfStaff[i].staffID+" NEW LINE:"+fileStaffContents[1]);
				writeNewDataTofile(fileStaffContents,listOfStaff[i].staffID+"_file.txt");

			}
		}
		
		
		
		//UPDATE OLD STAFF INSTANCE SET NUM OF PATIENTS TO 0 AND SET ARCHIVED TO TRUE	
		staffToArchive.allergies = staffToArchive.allergies.replace("\n", "#");//replaces \n with # prevent new lines from being created
		staffToArchive.disability = staffToArchive.disability.replace("\n", "#");//replaces \n with # prevent new lines from being created
		
		String staffDemo=staffToArchive.staffID+","+staffToArchive.employeeID+","+staffToArchive.password+","+staffToArchive.surName+","+staffToArchive.firstName+","+staffToArchive.addressHouseNum+","+staffToArchive.addressHouseStreet+","+staffToArchive.town+","+staffToArchive.postcode+","+staffToArchive.contactNum+","+staffToArchive.nationality+","+staffToArchive.bloodType+","+staffToArchive.smoker+","+staffToArchive.drinker+","+ft.format(staffToArchive.dob)+","+staffToArchive.religon+","+staffToArchive.allergies+","+staffToArchive.gender+","+staffToArchive.disability+","+staffToArchive.carer+","+staffToArchive.translator+","+staffToArchive.sex+","+staffToArchive.county+","+ft.format(staffToArchive.daysSinceLastUpdate)+","+staffToArchive.wage+","+staffToArchive.hoursPerWeek+","+"true";//concatenates all the attributes togeher

		try//this will try to run
		{
			FileWriter fw = new FileWriter(staffToArchive.staffID+"_file.txt");//declare a new file writer that will ammend not not write to file
			fw.write(encrypt(staffDemo));//writes the curent index to line
			fw.write("\r\n");//adds in a space to the file
			fw.write(encrypt("0,"));//adds in a space to the file
			fw.close();//closes the file
		}
		catch(Exception exc)//if any error occurs the error is caught
		{
			System.out.println("WRITE error");//informs that a write error has occured
			exc.printStackTrace();//prints the stack trace
		}
	}
	
	//This process is basically just a front to prepare the system to actually perform the task
	//All the validation occurs here 
	//It will display all the acounts on the system, along with their active status
	// a search occurs where the input is found
	//it will then check its eligability to be archived or not
	//it will then pass the desired entity into the method which actually performs the action
	public void archiveRearchiveConsultant()
	{
		ConsultantList cl = new ConsultantList();
		String[] consultantListIDs = cl.returnAllConsultants();//returns all the staffIDs on the system
		Consultant[] listOfConsultants = new Consultant[consultantListIDs.length];
		int numberOfStaffNotArchived = 0;//Check variable to make sure at least one Consultant account is on the system
		//creates every instance of Consultant on the system, whilest also counting how many active Consultant exists
		Consultant tempInstance = new Consultant();
		for(int countTOCopyConsultant =0;countTOCopyConsultant<listOfConsultants.length;countTOCopyConsultant++)
		{
			listOfConsultants[countTOCopyConsultant] = tempInstance.retrieveConsultant(consultantListIDs[countTOCopyConsultant].substring(0,11));//pulls instance of the consultant on the system
			if(listOfConsultants[countTOCopyConsultant].archived==false)
			{
				numberOfStaffNotArchived++;//increments counter for consultants that are not archived
			}
			
		}
		boolean archiveRearchiveConsultantLoop = true;//makes the attribute true shich runs the infinite loop
		
		EscapeLoop: //Label to escape the process if management wants to stop
		while(archiveRearchiveConsultantLoop==true)//termination condition preventing the page from closing
		{
			//menue options
			System.out.println("================Change active status of Consutlants================");
			System.out.println("\n");
			System.out.println("All Consultant accounts:");
			System.out.println("\n");
			for(int counter = 0;counter<consultantListIDs.length;counter++)
			{
				if(listOfConsultants[counter].archived==true)
				{
					//output all consultants, including some minor details
					System.out.println(listOfConsultants[counter].consultantID+","+listOfConsultants[counter].surName+","+listOfConsultants[counter].archived+" ARCHIVED");
			
				}
				
				else if(listOfConsultants[counter].archived==false)
				{
					//output all consultants, including some minor details
					System.out.println(listOfConsultants[counter].consultantID+","+listOfConsultants[counter].surName+","+listOfConsultants[counter].firstName+" ACTIVE");
			
				}	
			}
			System.out.println("\n");
			System.out.println("Please enter the consultant accounts ID");
			System.out.println("\n");
			System.out.println("(B) Go back");
			System.out.println("=============================================================");
			String desiredConsultantID = inputScanner.nextLine();
			
			//selection statements
			if(desiredConsultantID.equals("B"))//checks to see if user wants to leave the current part
			{
				archiveRearchiveConsultantLoop=false;
				break EscapeLoop;
			}
			boolean validated =tempInstance.presenceValidation(desiredConsultantID);
			if(validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid option, cant be null.");
				continue;
			}
			if((desiredConsultantID.charAt(0)+"").equals("C"))//finds the instance of the consultants account
			{
				boolean found = false;
				int indexOfConsultant =-1;
				for(int counter1 = 0;counter1<consultantListIDs.length;counter1++)
				{
					//checks if the consultants was found
					if(desiredConsultantID.equals(consultantListIDs[counter1].substring(0,11)))
					{
						found =true;
						System.out.println("Consultant account found");
						indexOfConsultant = counter1;
					}
				}
				if(found==true)
				{
					Consultant consultantToAmmend = new Consultant();//initalises instance of Consultant to use
					consultantToAmmend = listOfConsultants[indexOfConsultant];//assigns the correct instance of Consultant
					boolean finalWarning = true;//makes the attribute true shich runs the infinite loop
					while(finalWarning==true)//termination condition preventing the page from closing
					{
						
						if(consultantToAmmend.archived==true)
						{
							//checks that the current Consultant is not the only active account on the system
							System.out.println("Consultant is currently archived on the system");
						}
						
						if(consultantToAmmend.archived==false)
						{
							System.out.println("Consultant is currently active on the system");
							if(numberOfStaffNotArchived==1)
							{
								System.out.println("Sorry at least 1 Consultant account must always exist on the system at all times");
								break EscapeLoop;
							}
							
						}	
						
						//final warnings
						System.out.println("Are You sure you want to proceed");
						System.out.println("\n");
						System.out.println("(Y) Yes");
						System.out.println("(N) No");
						System.out.println("\n");
						String lastChanceWarning = inputScanner.nextLine();
						if(lastChanceWarning.equals("Y"))//checks to see if user wants to leave the current part
						{
							//for respective account one of the two will occur
							if(consultantToAmmend.archived==true)
							{
								//rearchieve Consultant
								consultantToAmmend.archived=false;
								consultantToAmmend.updateDemographicDetails(consultantToAmmend);
								System.out.println("Consultant added to system");
								break EscapeLoop; //Label to escape the process if management wants to stop
							}
							
							if(consultantToAmmend.archived==false)
							{
								//here we want to update the instance of patients along with the consultant and so we will iterate through evey 
								archiveConsultant(consultantToAmmend);//calls method which updates entire system
								break EscapeLoop; //Label to escape the process if management wants to stop
							}
						}
						if(lastChanceWarning.equals("N"))//checks to see if user wants to leave the current part
						{
							break EscapeLoop;//if user at last minute wants to stop the process
						}
					}
					
					
				}
				if(found==false)//checks to see if consultant id exists
				{
					System.out.println("Account was not found");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid option, Please select an entity.");
				
			}
		}
	}
	
	//Here we will perfrom to major tasks
	//intialy we will go through every patient the consultant has and then set the patient's admission as pending
	//not only this but we will also have to cancel any upcoming appointments the patient may or may not have had with the consultant
	//after this is done for all patients the system will update the consultant's file also.
	public void archiveConsultant(Consultant consultantToArchive)
	{
		int numberOfPatientstoUpdate = consultantToArchive.numberOfPatients;
		int NumberOfAdmissions = -1;
		String[] listOfParticularAdmissions;//while active this will set the current array of patient admissions to the patient at index (counter)
		Patient tempPatient = new Patient();
		//here we will initalise every patient on the system and find out how many admissions they have
		for(int counterToITerateThroughAdmissions = 0;counterToITerateThroughAdmissions<numberOfPatientstoUpdate;counterToITerateThroughAdmissions++)
		{
			tempPatient = tempPatient.retrievePatient(consultantToArchive.consultantPatientList[counterToITerateThroughAdmissions]);
			
			System.out.println("Patient "+tempPatient.patientID+" Has "+tempPatient.numberOfAdmissions+" Admissions");
			//here we retrieve the list of admissions with the patient which have the current consultant
			
			listOfParticularAdmissions = consultantToArchive.concatenatedconsultantPatientList[counterToITerateThroughAdmissions].split("#");//READS THE ASSOCIATED ARRAY WITH ALL THE VALUES AND SPLITS THE ADMISISON IDS TO AN ARRAY
			//here we will have an array with the size of admissions for each patient
			
			NumberOfAdmissions = (listOfParticularAdmissions.length);
			System.out.println("The consultnat has "+NumberOfAdmissions+" admissions with the patient");
			//intialises the array which will hold the indexes of only the consultants admissions
			
			//retrieves the indexes all admissions occur
			
			for(int count=0;count<NumberOfAdmissions;count++)
			{
				String admissionIDtoCompare = listOfParticularAdmissions[count];//gets current admission
				System.out.println("Looking for "+admissionIDtoCompare);
				boolean currentAdmissionFound = false;
				int innerCounter=0;
				AdmissionList al = new AdmissionList();
				while(innerCounter<tempPatient.listOfAdmissions.length)
				{
					if(tempPatient.listOfAdmissions[innerCounter].admissionID.equals(admissionIDtoCompare))
					{
						//before admission is archived any bookings are deleted first
						if((tempPatient.listOfAdmissions[innerCounter].upComingBooking==null)==false)
						{
							if(tempPatient.listOfAdmissions[innerCounter].upComingBooking.room.equals("Null")==false)
							{
								tempPatient.listOfAdmissions[innerCounter].upComingBooking.deleteBooking(tempPatient.listOfAdmissions[innerCounter],tempPatient);
							}
						}
						
						//System.out.println("Found admission "+tempPatient.listOfAdmissions[innerCounter].admissionID);
						currentAdmissionFound = true;
						tempPatient.listOfAdmissions[innerCounter].consultantID = "PENDING";//updates the admission
						al.updateAdmission(tempPatient,tempPatient.listOfAdmissions[innerCounter],12);
						innerCounter++;
					}
					else{
						innerCounter++;
					}
				}
			}
			
			
			
		}
		
		//here we have updated all the patients on the accounts file
		//we can now proceed with the task of updating the consultant itself
		//all we do is pretend as if the account is new and then just harcode the values into the file
		consultantToArchive.archived=true;
		consultantToArchive.numberOfPatients=0;
		String ConsultantDemo=consultantToArchive.consultantID+","+consultantToArchive.employeeID+","+consultantToArchive.password+","+consultantToArchive.surName+","+consultantToArchive.firstName+","+consultantToArchive.addressHouseNum+","+consultantToArchive.addressHouseStreet+","+consultantToArchive.town+","+consultantToArchive.postcode+","+consultantToArchive.contactNum+","+consultantToArchive.nationality+","+consultantToArchive.bloodType+","+consultantToArchive.smoker+","+consultantToArchive.drinker+","+ft.format(consultantToArchive.dob)+","+consultantToArchive.religon+","+consultantToArchive.allergies+","+consultantToArchive.gender+","+consultantToArchive.disability+","+consultantToArchive.carer+","+consultantToArchive.translator+","+consultantToArchive.sex+","+consultantToArchive.county+","+ft.format(consultantToArchive.daysSinceLastUpdate)+","+consultantToArchive.wage+","+consultantToArchive.hoursPerWeek+","+consultantToArchive.archived;//concatenates all the attributes togeher
		String practises =consultantToArchive.expertiese[0];
		for(int counter = 1 ; counter<consultantToArchive.expertiese.length;counter++)
		{
			practises =practises+"\n"+consultantToArchive.expertiese[counter];
		}
		practises = practises.replace("\n", "#");//replaces \n with # prevent new lines from being created

		String ConsultantSpec =practises+","+consultantToArchive.numberOfPatients+","+consultantToArchive.wardLocatedIn;
		
		//WRITES CONSULTANT TO FILE 
		try//this will try to run
		{
			FileWriter fw = new FileWriter(consultantToArchive.consultantID+"_file.txt");//declare a new file writer that will ammend not not write to file
			fw.write(encrypt(ConsultantDemo));//writes the curent index to line
			fw.write("\r\n");//adds in a space to the file
			fw.write(encrypt(ConsultantSpec));//writes the curent index to line
			fw.close();//closes the file
		}
		catch(Exception exc)//if any error occurs the error is caught
		{
			System.out.println("WRITE error");//informs that a write error has occured
			exc.printStackTrace();//prints the stack trace
		}
	}
	
}

