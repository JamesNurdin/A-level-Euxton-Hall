import java.awt.*;//imports the java awt package,by using a wildcard all aspects of the package are imported
import java.awt.font.TextAttribute;//imports the text attribute package 
import java.awt.event.*;//imports the java awt event package,by using a wildcard all aspects of the package are imported
import javax.swing.*;//imports the java swing package,by using a wildcard all aspects of the package are imported
import java.util.*;//imports the java utlities module
import java.text.SimpleDateFormat;//imports the simple date format package which allows for more simple dates
import java.text.ParseException;//imports the parse exception package
import java.io.*;//imports the io package
public class ConsultantList extends Consultant
{
	//======================== Entity Attributes ===================
	
	Consultant[] consultantList;//creates a list of consultants
	Consultant tempConsultant;//declares a new instance of consultant
	int nextPosition;//declares attribute which holds the position of the next consultant 
	SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
	
	//Again another account generation method, here the system will pass an instance of patient through and will then concatenate the results
	//Will call on methods such as for the ID and password, for fields that shouldnt be mage by user themselves
	//It also replaces any invalid characters used in formatting the text for the GUI
	//At the end the data is then concatenetd and then writes itself to file suing custom file writer
	//instance is then resturned back afterwards
	public Consultant createConsultantFully(Consultant consultant)
	{
		//archived
		consultant.archived=false;
		//Days sinceLast Updated
		Date currentTime = new Date();//declares the date of the closest appointment
		consultant.daysSinceLastUpdate=currentTime;//updates the patients time to the current time
		//ConsultantID
		consultant.consultantID = consultant.createUniqueID(consultant.surName,"C");
		//System.out.println("ID "+consultant.consultantID);
		//password
		consultant.password= consultant.generatePassword();
		
		//EmployeeID
		consultant.employeeID = "E"+consultant.consultantID.substring(1,consultant.consultantID.length());
		//System.out.println("ID "+consultant.consultantID);	
		
		//CONSULTANT SPECIFIC
		
		//numberOfPatients
		consultant.numberOfPatients = 0;
		
		consultant.allergies = consultant.allergies.replace("\n", "#");//replaces \n with # prevent new lines from being created
		consultant.disability = consultant.disability.replace("\n", "#");//replaces \n with # prevent new lines from being created
		
		//CONCATENATES DETAILS TOGETHER
		String ConsultantDemo=consultant.consultantID+","+consultant.employeeID+","+consultant.password+","+consultant.surName+","+consultant.firstName+","+consultant.addressHouseNum+","+consultant.addressHouseStreet+","+consultant.town+","+consultant.postcode+","+consultant.contactNum+","+consultant.nationality+","+consultant.bloodType+","+consultant.smoker+","+consultant.drinker+","+ft.format(consultant.dob)+","+consultant.religon+","+consultant.allergies+","+consultant.gender+","+consultant.disability+","+consultant.carer+","+consultant.translator+","+consultant.sex+","+consultant.county+","+ft.format(consultant.daysSinceLastUpdate)+","+consultant.wage+","+consultant.hoursPerWeek+","+consultant.archived;//concatenates all the attributes togeher
		String practises =consultant.expertiese[0];
		for(int counter = 1 ; counter<consultant.expertiese.length;counter++)
		{
			practises =practises+"\n"+consultant.expertiese[counter];
		}
		practises = practises.replace("\n", "#");//replaces \n with # prevent new lines from being created

		String ConsultantSpec =practises+","+consultant.numberOfPatients+","+consultant.wardLocatedIn;
		
		//WRITES CONSULTANT TO FILE 
		try//this will try to run
		{
			FileWriter fw = new FileWriter(consultant.consultantID+"_file.txt");//declare a new file writer that will ammend not not write to file
			fw.write(encrypt(ConsultantDemo));//writes the curent index to line
			fw.write("\r\n");//adds in a space to the file
			fw.write(encrypt(ConsultantSpec));//writes the curent index to line
			fw.close();//closes the file
		}
		catch(Exception exc)//if any error occurs the error is caught
		{
			//System.out.println("WRITE error");//informs that a write error has occured
			exc.printStackTrace();//prints the stack trace
		}
		
		return(consultant);
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


	//Most of the work done by pullConsultantsFromFile,
	//regardless calls the method which retrieves all the demographic informaiton about the consultants
	//Here we just loop through the array pulling the ID only,
	//At the end of the loop the id is then retutned to calling method
	public String[] returnAllConsultants()
	{
		String[] consultantsOnSystem = pullConsultantsFromFile();
		for(int counter = 0;counter<consultantsOnSystem.length;counter++)
		{
			//System.out.println(consultantsOnSystem[counter].substring(0,11));
			consultantsOnSystem[counter] = consultantsOnSystem[counter].substring(0,11);
		}

		
		return(consultantsOnSystem);
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
	public String[] pullConsultantsFromFileActive()
	{
		String ifConsultant="";
		File folder = new File(System.getProperty("user.dir"));
		File[] listOfFiles = folder.listFiles();
		int numberOfConsultants =0;
		String line1="";
		for (int counter = 0;counter< listOfFiles.length;counter++)
		{
			try
			{
				ifConsultant =listOfFiles[counter].getName().charAt(0)+ listOfFiles[counter].getName().substring(11,20);
				if(ifConsultant.equals("C_file.txt"))
				{
					line1= readLineFromFile((listOfFiles[counter].getName()),1);
					//substrings the text and finds the last 4 characters(true or false)
					String archived = line1.substring((line1.length()-4),line1.length());
					////System.out.println(archived);
					if(line1.substring((line1.length()-4),line1.length()).equals("alse"))
					{
						numberOfConsultants++;
						//System.out.println("consultant account for "+listOfFiles[counter].getName().substring(0,11)+" is active");
					}
					else
					{
						//System.out.println("consultant account for "+listOfFiles[counter].getName().substring(0,11)+" is archived");
					}
				}
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{
			}
		}
		String[] listOfDemoConsultants =new String[numberOfConsultants];
		int counterForInnerArray=0;
		for (int counter = 0;counter< listOfFiles.length;counter++)
		{
			try
			{
				ifConsultant =listOfFiles[counter].getName().charAt(0)+ listOfFiles[counter].getName().substring(11,20);
				if(ifConsultant.equals("C_file.txt"))
				{
					line1= readLineFromFile((listOfFiles[counter].getName()),1);
					//substrings the text and finds the last 4 characters(true or false)
					String archived = line1.substring((line1.length()-4),line1.length());
					if(line1.substring((line1.length()-4),line1.length()).equals("alse"))
					{
						listOfDemoConsultants[counterForInnerArray] = line1.substring(0,11);
						counterForInnerArray++;
					}
				}
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{
			}
		}
		return(listOfDemoConsultants);
	}
	
	//Will find the folder the system operates in
	//splits contents into a list of files 
	//We then perform a search through all data checking to see if it is a consultant or not
	//if it is the line from the file is then added to the return arrayOfData
	//once at end array is returned
	public String[] pullConsultantsFromFile()
	{
		String ifConsultant="";
		File folder = new File(System.getProperty("user.dir"));
		File[] listOfFiles = folder.listFiles();
		int numberOfConsultants =0;

		for (int counter = 0;counter< listOfFiles.length;counter++)
		{
			try
			{
				ifConsultant =listOfFiles[counter].getName().charAt(0)+ listOfFiles[counter].getName().substring(11,20);
				////System.out.println("file : "+ifConsultant);
				if(ifConsultant.equals("C_file.txt"))
				{
					numberOfConsultants++;
		   
				}
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{
			}
		}
		String[] listOfDemoConsultants =new String[numberOfConsultants];
		int counterForInnerArray=0;
		for (int counter = 0;counter< listOfFiles.length;counter++)
		{
			try
			{
				ifConsultant =listOfFiles[counter].getName().charAt(0)+ listOfFiles[counter].getName().substring(11,20);
				if(ifConsultant.equals("C_file.txt"))
				{
					listOfDemoConsultants[counterForInnerArray] = readLineFromFile((listOfFiles[counter].getName()),1);
					counterForInnerArray++;
		   
				}
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{
			}
		}
		return(listOfDemoConsultants);
	}
}