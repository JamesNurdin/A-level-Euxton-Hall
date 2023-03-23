import java.awt.*;//imports the java awt package,by using a wildcard all aspects of the package are imported
import java.awt.font.TextAttribute;//imports the text attribute package 
import java.awt.event.*;//imports the java awt event package,by using a wildcard all aspects of the package are imported
import javax.swing.*;//imports the java swing package,by using a wildcard all aspects of the package are imported
import java.util.*;//imports the java utlities module
import java.text.SimpleDateFormat;//imports the simple date format package which allows for more simple dates
import java.text.ParseException;//imports the parse exception package
import java.io.*;//imports the io package
public class StaffList extends Staff
{
	//======================== Entity Attributes ===================
	
	Staff[] staffList;//creates a list of staff
	Staff tempstaff;//declares a new instance of staff
	int nextPosition;//declares attribute which holds the position of the next staff 
	SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
	
	//like every other file writing method for instances, the object is passed through the other methods
	//Once here minor attributes that are not manually entered such as id are prosseced and generated
	//then all the attributes are concatenated accordingly to make sure all values are correct
	//any new lines get cusotm tokens to reperesnt them in file (prevents new lines being written on accident)
	//once done the account is returned
	public Staff createstaffFully(Staff staff)
	{
		//archived
		staff.archived=false;
		//Days sinceLast Updated
		Date currentTime = new Date();//declares the date of the closest appointment
		staff.daysSinceLastUpdate=currentTime;//updates the patients time to the current time
		//staffID
		staff.staffID = staff.createUniqueID(staff.surName,"S");
		//System.out.println("ID "+staff.staffID);
		//password
		staff.password= staff.generatePassword();
		
		//EmployeeID
		staff.employeeID = "E"+staff.staffID.substring(1,staff.staffID.length());
		//System.out.println("ID "+staff.staffID);	
		
		staff.allergies = staff.allergies.replace("\n", "#");//replaces \n with # prevent new lines from being created
		staff.disability = staff.disability.replace("\n", "#");//replaces \n with # prevent new lines from being created
		
		String staffDemo=staff.staffID+","+staff.employeeID+","+staff.password+","+staff.surName+","+staff.firstName+","+staff.addressHouseNum+","+staff.addressHouseStreet+","+staff.town+","+staff.postcode+","+staff.contactNum+","+staff.nationality+","+staff.bloodType+","+staff.smoker+","+staff.drinker+","+ft.format(staff.dob)+","+staff.religon+","+staff.allergies+","+staff.gender+","+staff.disability+","+staff.carer+","+staff.translator+","+staff.sex+","+staff.county+","+ft.format(staff.daysSinceLastUpdate)+","+staff.wage+","+staff.hoursPerWeek+","+staff.archived;//concatenates all the attributes togeher

		try//this will try to run
		{
			FileWriter fw = new FileWriter(staff.staffID+"_file.txt");//declare a new file writer that will ammend not not write to file
			fw.write(encrypt(staffDemo));//writes the curent index to line
			fw.write("\r\n");//adds in a space to the file
			fw.write(encrypt("0,"));//adds in a space to the file
			fw.close();//closes the file
		}
		catch(Exception exc)//if any error occurs the error is caught
		{
			//System.out.println("WRITE error");//informs that a write error has occured
			exc.printStackTrace();//prints the stack trace
		}
		
		
		return(staff);
	}
	
	//The final method which calls the write method Now we have the new data to write to file
	//This will read the old file and save it to the array
	//The new data replaces the old data at the desired line
	//The new array of data is then written back to file
	public void updateDemographicfile(int line,String filename,String newData)//declares a procedure that writes the array to file using the object from the patient to be passed through
	{
		String[] arrayOfData = rffReturnFullFile(filename);
		arrayOfData[line] = newData;
		writeNewDataTofile(arrayOfData,filename);
	}

	//This method just retruns an array of staffID most of the work is done by the pullStaffFromFile method
	//however adjusts the output made from the method by only taking the first index of every array from the multidimensitonal array
	//the method then returns the ID
	public String[] returnAllStaff()
	{
		String[][] staffOnSystem = pullStaffFromFile();
		String[] staffOnSystemIDonly = new String[staffOnSystem.length];
		////System.out.println("Number of staff on system  "+staffOnSystem.length);

		for(int counter = 0;counter<staffOnSystem.length;counter++)
		{
			////System.out.println(staffOnSystem[counter][0].substring(0,11));
			staffOnSystemIDonly[counter]= staffOnSystem[counter][0].substring(0,11);
		}		
		return(staffOnSystemIDonly);
	}
	
	//special method that retruns an array of two arrays  essentially the first index holds all the staff members information 
	//While the second index holds all the staffs patient information
	//It works by reading the entire folder on the system
	//for every file it reads the file name and looks whether it starts with an s and ends with file.txt
	//It does this twice so it can get an arrray size and then adds the ID to the array,
	//Once it is finished the array of staff IDs gets returned back to the calling instance
	public String[][] pullStaffFromFile()
	{
		String ifStaff="";
		File folder = new File(System.getProperty("user.dir"));
		File[] listOfFiles = folder.listFiles();
		int numberOfStaff =0;
		//counts the number of staff on the system
		for (int counter = 0;counter< listOfFiles.length;counter++)
		{
			try
			{
				ifStaff =listOfFiles[counter].getName().charAt(0)+ listOfFiles[counter].getName().substring(11,20);
				if(ifStaff.equals("S_file.txt"))
				{
					numberOfStaff++;
		   
				}
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{
			}
		}
		
		String[][] listOfDemoStaff =new String[numberOfStaff][2];
		int counterForInnerArray=0;
		for (int counter = 0;counter< listOfFiles.length;counter++)
		{
			try
			{
				ifStaff =listOfFiles[counter].getName().charAt(0)+ listOfFiles[counter].getName().substring(11,20);
				if(ifStaff.equals("S_file.txt"))
				{
					listOfDemoStaff[counterForInnerArray][0] = readLineFromFile((listOfFiles[counter].getName()),1);
					listOfDemoStaff[counterForInnerArray][1] = readLineFromFile((listOfFiles[counter].getName()),2);
					counterForInnerArray++;
		   
				}
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{
			}
		}
		return(listOfDemoStaff);
	}
	
	//uses a overwritten version of pullstaff to get only the active accounts
	//What it does is check that the account is currently active on the system
	//This inhibits the staff account that may be inactive from gaining new patients 
	//We need a copy as the system may need to return accounts that are inactive also
	
	//It works by reading the entire folder on the system
	//for every file it reads the file name and looks whether it starts with an s and ends with file.txt
	//It does this twice so it can get an arrray size and then adds the ID to the array, 
	
	//What special about this custom version that inbetween the part that adds the staff acount and the selection which checks that it is a staff
	// - file is that we chec that the current staff account on the system is active or not
	public String[][] pullStaffFromFileActive()
	{
		String ifStaff="";
		File folder = new File(System.getProperty("user.dir"));
		File[] listOfFiles = folder.listFiles();
		int numberOfStaff =0;
		//counts the number of staff on the system
		String line1="";
		for (int counter = 0;counter< listOfFiles.length;counter++)
		{
			try
			{
				ifStaff =listOfFiles[counter].getName().charAt(0)+ listOfFiles[counter].getName().substring(11,20);
				if(ifStaff.equals("S_file.txt"))
				{
					line1= readLineFromFile((listOfFiles[counter].getName()),1);
					//substrings the text and finds the last 4 characters(true or false)
					String archived = line1.substring((line1.length()-4),line1.length());
					//System.out.println(archived);
					if(line1.substring((line1.length()-4),line1.length()).equals("alse"))
					{
						numberOfStaff++;
						//System.out.println("Staff account for "+listOfFiles[counter].getName().substring(0,11)+" is active");
					}
					else
					{
						//System.out.println("Staff account for "+listOfFiles[counter].getName().substring(0,11)+" is archived");
					}
				}
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{
			}
		}
		
		String[][] listOfDemoStaff =new String[numberOfStaff][2];
		int counterForInnerArray=0;
		for (int counter = 0;counter< listOfFiles.length;counter++)
		{
			try
			{
				ifStaff =listOfFiles[counter].getName().charAt(0)+ listOfFiles[counter].getName().substring(11,20);
				if(ifStaff.equals("S_file.txt"))
				{
					line1= readLineFromFile((listOfFiles[counter].getName()),1);
					String line2= readLineFromFile((listOfFiles[counter].getName()),2);
					//substrings the text and finds the last 4 characters(true or false)
					String archived = line1.substring((line1.length()-4),line1.length());
					if(line1.substring((line1.length()-4),line1.length()).equals("alse"))
					{
						listOfDemoStaff[counterForInnerArray][0] = line1;
						listOfDemoStaff[counterForInnerArray][1] = line2;
						counterForInnerArray++;
					}
		   
				}
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{
			}
		}
		return(listOfDemoStaff);
	}
	
	//This method is very special what it does is read the entire patients on the system and returns a staffID for the patient
	//It achieves this by reading every staff file on the system and setting the staff account with the smallest number of staff as the desired account
	//This does this by reading every number of patients on the system and then compares this value to the current smallest value
	//If smaller the id updates and a new minimum is sets
	
	//After the account is found we need to update their contents
	//it checks for one of two possiblities, either it has 0 patients (indicating new account or rearchived) or it already has patients
	//Either way it adds the patient to the list and increments the number of patients they have.
	public String returnStaffIDForPatientCreation(String newPatientID)
	{
		int smallestStaffnumberOfPatients = 999999999;
		String IDofStaff = "Null";
		String[][] staffOnSystem = pullStaffFromFileActive();//returns the array of staff this will be used to compare the number of patients each staff account has (With the smallest recieving the newest instance
		//System.out.println("Number of staff "+staffOnSystem.length);
		int staffnumOfPatients = -1;
		int indexOfStaff = -1;
		int[] itemList = new int[2];
		for(int counter = 0;counter<staffOnSystem.length;counter++)
		{
			//using the retrieved staff line the data is split so the number of patients can be retireved
			String[] stringedStaffline =staffOnSystem[counter][1].split(",");//splits up the staffs patients into a list
			try
			{
				staffnumOfPatients = Integer.parseInt(stringedStaffline[0]);//gets the current number of patients
			}
			catch(Exception exc)
			{
			exc.printStackTrace();
			}
			if(smallestStaffnumberOfPatients>staffnumOfPatients)//finds a staff entity with a smaller number of bookings
			{
				smallestStaffnumberOfPatients = staffnumOfPatients;
				IDofStaff = (staffOnSystem[counter][0].substring(0,11));
				//System.out.println(staffOnSystem[counter][0]+" Currently has the least number of patients");
				indexOfStaff = counter;
			}
		}
		//Here we have the staff with the smallest number of Patients

		
			//now we have a new patient found and have a staff associated we can add the patiehnt to the staffs list(return of method will sort out the patients)
		String[] fileStaffContents = rffReturnFullFile(staffOnSystem[indexOfStaff][0].substring(0,11)+"_file.txt");//concatinates the string into an array
		
		//we have the entire file now we need just the patients 
		String[] listOfStaffItems;//holds an array of all the patients possed by the staff
		listOfStaffItems=fileStaffContents[1].split(",");//splits up the staffs patients into a list
		//The 1 is used to compensate for the intial index holding the number of patients
		if(listOfStaffItems.length>1)//checks if the staffs list of patients is more than 1
		{
			itemList = searchPrimaryKeysLinear(Arrays.copyOfRange(listOfStaffItems,1,listOfStaffItems.length),newPatientID,itemList);
			//System.out.println("Position "+itemList[0]);
			//System.out.println("New ? "+itemList[1]);
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
			copyOfStaffFile[indexOfPatient]=newPatientID;
			//updates the old array with the old surnames
			listOfStaffItems=copyOfStaffFile;// the patient has now got the position in the consultant file added
			
			
			//now we have every patient on the list so we need to concatenate them together
			String stringedStaffPatientInfo = "";
			for(int counter = 1;counter<listOfStaffItems.length;counter++)//by setting the first pointer index as 1 we can skip the old number of paitents
			{
				stringedStaffPatientInfo=stringedStaffPatientInfo+listOfStaffItems[counter]+",";
				//System.out.println("line "+counter+ " " +listOfStaffItems[counter]);
			}
			stringedStaffPatientInfo = (staffnumOfPatients+1)+","+stringedStaffPatientInfo;
			fileStaffContents[1] = stringedStaffPatientInfo;
		}
		
		else{//staff has 0 patients we can force in the patient and update the number manually
			fileStaffContents[1] = "1,"+newPatientID;
			//System.out.println("PATIENT ADDED TO STAFF");
		}
		writeNewDataTofile(fileStaffContents,IDofStaff+"_file.txt");
	return(IDofStaff);
		
	}
}
