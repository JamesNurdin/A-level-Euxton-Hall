import java.util.*;//imports the java utlities module	
import java.io.*;//imports the io package
import java.text.SimpleDateFormat;//imports the simple date format package which allows for more simple dates
import java.util.Random;//imports the java utilities for the random function
import java.time.*;
import javax.swing.*;//imports the java swing package,by using a wildcard all aspects of the package are imported


public class User //
{
	SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
	SimpleDateFormat ftTimeInc = new SimpleDateFormat ("dd/MM/yyyy k:m");
	
	//======================== Entity Attributes ===================
	
	Date daysSinceLastUpdate;
	String firstName;//declares attribute which holds first name
	String surName;//declares attribute which holds surname
	String gender;//declares attribute which holds their gender
	Date dob = new Date();//declares attribute which holds their dob
	int addressHouseNum;//declares attribute which holds address number
	String addressHouseStreet;//declares attribute which holds their street
	String town;//declares attribute which holds their town
	String postcode;//declares attribute which holds their postcode
	String contactNum;//declares attribute which holds their constact information
	String county;
	String nationality;
	int smoker;//declares attribute which holds their smoker status
	int drinker;//declares attribute which holds their drinker status
	String disability;//declares attribute which holds their disability status
	boolean carer;//declares attribute which holds their carer status
	boolean translator;//declares attribute which holds their translator
	String password;//declares attribute which holds their password
	int daysSinceLastUpdated;
	String bloodType;//declares attribute which holds their bloodType
	String religon;//declares attribute which holds their religon
	String allergies;//declares attribute which holds their allergies
	String sex;
	boolean nomoreNotifications;//value determining if the patient has no notifications
	boolean disabled;
	boolean validated; // a temp validation variable which can be used throughout the system to validate data entry:
	
	
	
	
	
	//================== ACCOUNT CREATION ===================


	//this method generates a username for the user on the sysetm
	//first it retrieves all the surnames on the system and the number of times they occur
	//next it tries to find the index the surname should be (returning that with the value if it was there or not)
	//if it wasnt there it copies the list to a larger array (+1 length) and then inserts it to the correct location
	//if it was it just retireves the the number of values with that name and increments it
	//it will then structure the username together and then retrun it
	//it will also write the new surname to file/ number f times it occurs
	public String createUniqueID(String surname, String type)
	{
		surname = (((surname.charAt(0)+"").toUpperCase())+((surname.charAt(1)+"").toUpperCase())+((surname.charAt(2)+"").toUpperCase()));
		String[] stringedListOfSurnames =rffReturnFullFile("General.txt");//returns entire file in the form of an array
		int asciiValueOfSurname = (int) surname.charAt(0);
		//System.out.println("int value "+ (asciiValueOfSurname-65));
		//System.out.println(surname);
		
		String[][] listOfSurnames = unConcatenateString(stringedListOfSurnames[asciiValueOfSurname-65],"@");
	
		//with the list of surnames it will then locate the surname that the user wants
		int[] items = new int[2]; 
		int[] locationOfSurname = findIndexOfSuranme(listOfSurnames,surname,items);
				//System.out.println(surname);

		//pulls the array with the location of the surname and wether or not it exists already
		int indexOfSurname = locationOfSurname[0];//sets index as a variable
		//if satement checking the new surname needs to be added
		if(locationOfSurname[1]==1)
		{
			//The section of code basically just coppies the list of surnames and then leaves an idex for the user's surname and then adds the rest in
			String[][] copyOfSurnameList = new String[listOfSurnames.length+1][2];
			for(int firstpart =0;firstpart<indexOfSurname;firstpart++)
			{
				copyOfSurnameList[firstpart]= listOfSurnames[firstpart];
			}
			//now adds the rest of the surnames purposefully leaving a space for the new surname
			for(int secondPart =indexOfSurname+1;secondPart<listOfSurnames.length+1;secondPart++)
			{
				copyOfSurnameList[secondPart]= listOfSurnames[secondPart-1];
			}
			//adds the new surname and that now there is 1 on the system
			copyOfSurnameList[indexOfSurname][0]=surname;
			copyOfSurnameList[indexOfSurname][1]=(1+"");
			//updates the old array with the old surname
			listOfSurnames=copyOfSurnameList;
		}
		if(locationOfSurname[1]==0)//if the surname is already present on the system 
		{
			////System.out.println("int position for the surname before"+ listOfSurnames[indexOfSurname][1]);
			listOfSurnames[indexOfSurname][1]=((Integer.parseInt(listOfSurnames[indexOfSurname][1])+1)+"");
			////System.out.println("int position for the surname after"+ listOfSurnames[indexOfSurname][1]);

			//basically retrieves the current value converts it to an int adds 1 and then converts back to string before saving it back to the array
		}
		
		//Surname file updating
		String concatenatedSurnames=(surname.charAt(0)+"")+listOfSurnames.length+";";
		for(int counterLoop = 0;counterLoop<listOfSurnames.length;counterLoop++)
		{
			concatenatedSurnames = concatenatedSurnames+listOfSurnames[counterLoop][0]+","+listOfSurnames[counterLoop][1]+"@";
		}
		////System.out.println(concatenatedSurnames);
		
		writeLineToFile(concatenatedSurnames,((int) surname.charAt(0)-65),"General.txt");
			////System.out.println(type.toUpperCase());
			////System.out.println(surname.substring(0,3).toUpperCase());

		//username construction
		String stringNum = listOfSurnames[indexOfSurname][1];
		while(stringNum.length()< 7)
		{//termination condition when satisified 
			stringNum ="0"+stringNum;//adds a zero untill there are 7 zero's
			////System.out.println(stringNum);
		}
		String username = type.toUpperCase()+ surname.substring(0,3).toUpperCase()+stringNum;
		////System.out.println("username "+ username);
		//returns the new int value for the user 
		return(username);
	}
	
	//method which uniquley generates 7 random integers which have desireable characters for a password
	//it will generate a password in a range of values
	//if undesired it will iterate untill satsified
	//it concatenates the result and retruns it to the user
	public String generatePassword()
	{
		Random r = new Random();//imports and creates a random modual
		int randomnumber;//initlaises buffer value
		String password="";//initalises password attribute
		for(int countCondition = 0;countCondition<7;countCondition++)//for loop iterates 7 times
		{//58 59 60 61 62      91 92 93 94 95 96, list of integers which are undesired
			do//do construct statment
			{
				randomnumber= r.nextInt((122 - 48) + 1) + 48;//produces a number between 48 and 122
			}while((((58<=randomnumber)&&(randomnumber<=62))==true)&&(((91<=randomnumber)&&(randomnumber<=96))==true)); //statement checking random value is not in the undesired range
			password =password+ ((char) randomnumber);//adds character of int to password by concatenating it
		}
		return(password);//retruns the password
	}
	
	
	
	
	
	//================== VALIDATION =======================
	
	
	//if the value equals this length it will retrun true
	public boolean equalsualLengthValidation(String value, int length)
	{
		
		boolean valueMet = false;
		if(value.length()==length)
		{
			valueMet = true;
		}
		return valueMet;
	}
	
	//if the value is greater than this length it will retrun true
	public boolean greaterLengthValidation(String value, int length)
	{
		
		boolean valueMet = false;
		if(value.length()>length)
		{
			valueMet=true;
		}
		return valueMet;
	}
	
	//if the value is less  than or equal to this length it will retrun true
	public boolean lesserLengthValidation(String value, int length)
	{
		
		boolean valueMet = true;
		if(value.length()>length)
		{
			valueMet=false;
		}
		return valueMet;
	}
	
	//validation determining if the value contains only letters/spaces
	public boolean typeValidationString(String value)
	{
		char[] chars = value.toCharArray();
		for (char c : chars) //will iterate through every part of the array
		{
			if((Character.isLetter(c)==true)||(Character.isWhitespace(c)==true))//comparission checking if not a letter
			{
				//////System.out.println("String  found");
			}
			if((Character.isLetter(c)==false)&&(Character.isWhitespace(c)==false))//comparission checking if not a letter
			{
				//////System.out.println("String not found");
				return false;
			}
		}
		return true;//only characters
	}
	
	//validation determining if the value contains only digits
	public boolean typeValidationInt(String value)
	{
		char[] chars = value.toCharArray();
		for (char c : chars) //will iterate through every part of the array
		{
			if(Character.isDigit(c)==true)//comparission checking if a letter
			{
				//////System.out.println("int  found");
				
			}
			if(Character.isDigit(c)==false)//comparission checking if a letter
			{
				//////System.out.println("int not found");
				return false;
			}
		}
		return true;//only characters
	}
	
	//validation determining if the value contains only letters/digits/spaces
	public boolean typeValidationStringOrInt(String value)
	{
		char[] chars = value.toCharArray();
		for (char c : chars) //will iterate through every part of the array
		{
			if((Character.isLetter(c)==true)||(Character.isWhitespace(c)==true)||(Character.isDigit(c)==true))//comparission checking if a letter or number
			{
				//////System.out.println("String  found");
			}
			if((Character.isLetter(c)==false)&&(Character.isWhitespace(c)==false)&&(Character.isDigit(c)==false))//comparission checking if not a letter nor a number
			{
				//////System.out.println("String not found");
				return false;
			}
		}
		return true;//only characters
	}
	
	//checks that the string is greater than 0
	public boolean presenceValidation(String value)
	{
		
		boolean valueMet = false;
		if(value!=null)
		{
			if(value.length()!=0)
			{
				valueMet = true;
			}
			if(value.contains("Please enter"))
			{
				valueMet = false;
			}
			if(value.contains("null"))
			{
				valueMet = false;
			}
		}
		else	
		{
			valueMet = false;
		}			
		return valueMet;
	}
	//here we pass through the bounds for the validation and the value the user enterd
	//only one comparison needs to occur where the value is checked that it is between the ranges
	//else it is declared as false
	public boolean rangeValidation(int startInclusive, int endInclusive, int value)
	{
		boolean valueMet = false;
		if((value<=endInclusive)&&(value>=startInclusive))
		{
			valueMet=true;
		}
		return valueMet;
	}
	//here we pass through the bounds for the validation and the value the user enterd
	//only one comparison needs to occur where the value is checked that it is between the ranges
	//else it is declared as false
	//This version is overwritten to accept real values
	public boolean rangeValidation(double startInclusive, double endInclusive, double value)
	{
		boolean valueMet = false;
		if((value<=endInclusive)&&(value>=startInclusive))
		{
			valueMet=true;
		}
		return valueMet;
	}
	//this validation will determine whether the first date occurs before the sceond one
	//the retrun type for the comparrison is int
	//if the comparrsion does occur before it returns a -1 hence the statement is true
	//if it doesnt i.e it is equal to (0) or greater than (1) then a false occurs
	//the result is returned
	public boolean checkDateAisBeforeDateB(Date dateA, Date dateB)
	{
		boolean valueMet = false;
		if(dateA.compareTo(dateB)<0)
		{
			valueMet=true;
		}
		return valueMet;
	}
	//in essence this is a linear search the item is searched for in the list
	//if it is found a true is returned, if not then false
	public boolean lookUpCheck(String[] listOfItems,String item)
	{
		boolean valueMet = false;
		for(int counter = 0;counter<listOfItems.length;counter++)
		{
			if(listOfItems[counter].equals(item))
			{
				////System.out.println("Found!");
				valueMet=true;
			}
			
		}
		return valueMet;
	}

	//here we are introducing a new validation (likely one of the last)
	//this will check to see if a charactr exists in a string
	//if it does it is not valid so returns false
	//if it doenst it is valid and true is returned
	public boolean checkCharIsNOTpresent(String itemToValidate,String undesiredchar)
	{
		if(itemToValidate.contains(undesiredchar))
		{
			return false;
		}
		return true;
	}

	//this is an overwritten method to validate that the time and date entered in the text feild are not null only, this is for creation method
	//as java util has no built in method for time and date datatypes 
	//nothing too specail just a custom presnece checker for the date and time picker
	//will fail the validation and then generate a popup if a feild is null
	public boolean validatebooking(LocalDate ld,LocalTime lt)
	{
		if(ld==null)
		{
			JOptionPane.showMessageDialog(null, "Date missing please enter a date.");
			return false;
			
		}
		if(lt==null)
		{
			JOptionPane.showMessageDialog(null, "Date missing please enter a time.");
			return false;
			
		}
		
		return true;
	}


	//==================== fILE WRITING ====================
	
	
	//A basic method which line by line for the array writes the data to file
	public void writeNewDataTofile(String[] arrayOfData, String filename)
	{
		try//this will try to run
		{
			FileWriter fw = new FileWriter(filename);//declare a new file writer that will ammend not not write to file
			for(int count = 0;count<arrayOfData.length;count++)//creates for loop that runs for the length of the array
			{
			fw.write(encrypt(arrayOfData[count]));//writes current index to line
			fw.write("\r\n");//adds in a space to the file
			}
			fw.close();//closes the file
		}
		catch(Exception exc)//if any error occurs the error is caught
		{
			////System.out.println("WRITE error");//informs that a write error has occured
			exc.printStackTrace();//prints the stack trace
		}
		
		
	}
	
	//in this statement the loop will read every line of surnames A->Z on the system so 26 iterations
	//Stores values in a array
	//using the new value from the other method it is changed
	//New loop is used writing them all to file
	public void writeLineToFile(String newLine,int Line,String filename)
	{
		String[] arrayOfData = new String[26];//creates an array with the number of indexes as the number of lines
		try//this will try to run
		{
			FileReader fr = new FileReader(filename);//decalres a new file reader called fr
			BufferedReader br = new BufferedReader(fr);//declares a new bufferedreader called br using fr as a parameter to be passed along
			String lineOfData = br.readLine();//saves the line read from file to the variable
			int count = 0;
			while(lineOfData !=null)//a while loop that runs untill the variable read is not empty(The end of the array is not reached)
			{
				arrayOfData[count] = decrypt(lineOfData);//sets the index to the currently read line
				count++;//iterates variable
				lineOfData = br.readLine();//this then reads from file again to check if not empty
			}
		}
		catch(Exception exc)//if any error occurs the error is caught
		{
			////System.out.println("Read error");//informs that a write error has occured
			exc.printStackTrace();//prints the stack trace
		}
		arrayOfData[Line]=newLine;
		for(int count = 0;count<arrayOfData.length;count++)
		{
			////System.out.println(arrayOfData[count]);
			
		}
		writeNewDataTofile(arrayOfData,filename);
	}
	
	
	
	
	//======================= CHANGING ARARY LENGTH ===================
	
	
	//Will create a new array with the length of the parameter array +1
	//will loop through every index adding the data from the paramter arrays location to the new one 
	//Instead of returning the old ne the new one is returned, the return data structure will be saved to the original parameter
	public String[] extendArrayByOne(String[] array)
	{
		String[] copyOfList = new String[array.length+1];
		for(int index =0;index<array.length;index++)
		{
			copyOfList[index]= array[index];
		}
		array = copyOfList;
		return(array);
	}
	public Admission[] extendArrayByOne(Admission[] array)
	{
		Admission[] copyOfList = new Admission[array.length+1];
		for(int index =0;index<array.length;index++)
		{
			copyOfList[index]= array[index];
		}
		array = copyOfList;
		return(array);
	}
	
	
	
	//====================== FILE READING =====================
	
	
	//surnameSpecific
	//will use the list of all the surnames with the surname of the letter that it starts with
	//It will then split each surname into separate indexes and then will assign the number of times it occurs  into the separate index
	//it will remove any unecessary characters that contain it such as  ,
	//will then return the array of names and the number of times they occur
	public String[][] unConcatenateString(String singleString, String gaptype)
	{
		singleString=singleString.substring(0,singleString.length()-1);
				//System.out.println(singleString);

		String[] arrayOfSurnames = (singleString.substring(0,singleString.length()).split(";"));
		int lengtha = Integer.parseInt(arrayOfSurnames[0].substring(1,arrayOfSurnames[0].length()));
		String[][] arrayb = new String[lengtha][2];//saves the value and sets it as the size of the array
		singleString=singleString.substring(3,singleString.length());
						//System.out.println(singleString);

		String[] uncatItems =singleString.split("@"); 
		//System.out.println("We have "+uncatItems.length);
		for(int counter=0;counter<uncatItems.length;counter++)//creates for loop that starts not at the primary key
		{
			arrayb[counter]=uncatItems[counter].split(","); 
			//System.out.println(arrayb[counter][0]);
			//System.out.println(arrayb[counter][1]);
		}
		
		
		
		
		return(arrayb);//returns arary with new list of strings
	}
	
	//baisc file reader will read every lihne from file and store it in an string array
	//first loop will indicate how many indexs are needed
	//Second loop actually adds the data to the array
	public String[] rffReturnFullFile(String filename)//declares a method that reads from file and saves it to  the array
	{
		
		int lengtha = 1;//initaly sets the value to an index that will alloways is out of bounds
		try//the program will try and do this
		{
			FileReader fra = new FileReader(filename);//decalres a new file reader called fr
			BufferedReader bra = new BufferedReader(fra);//declares a new bufferedreader called br using fr as a parameter to be passed along
			String lineOfDataa = bra.readLine();//saves the line read from file to the variable
			int line = 0;//sets start point at 0
			while(lineOfDataa !=null)//a while loop that runs untill the variable read is not empty(The end of the array is not reached)
			{
				//////System.out.println(line);
				line++;//increments line
				
				lineOfDataa = bra.readLine();//this then reads from file again to check if not empty
			}
			lengtha = line;//sets the number of entites on the file
		}
		catch(Exception exc)//if any errrors are found they are caught here
		{
			////System.out.println("Read error A.1");//informs that an error was found
		}
		String[] arrayOfData = new String[lengtha];//creates an array with the number of indexes as the number of lines
		try//the program will try and do this
		{
			FileReader fr = new FileReader(filename);//decalres a new file reader called fr
			BufferedReader br = new BufferedReader(fr);//declares a new bufferedreader called br using fr as a parameter to be passed along
			String lineOfData = br.readLine();//saves the line read from file to the variable
			int count = 0;
			while(lineOfData !=null)//a while loop that runs untill the variable read is not empty(The end of the array is not reached)
			{
				arrayOfData[count] = decrypt(lineOfData);//sets the index to the currently read line
				//System.out.println(arrayOfData[count]);

				count++;//iterates variable
				lineOfData = br.readLine();//this then reads from file again to check if not empty
			}
		}
		catch(Exception exc)//if any errrors are found they are caught here
		{
			////System.out.println("Read error A.2");//informs that an error was found
		}
		return(arrayOfData);//returns array
	}

	public String readLineFromFile(String filename,int line)
	{		
	String lineOfData="";

		try//the program will try and do this
		{
			FileReader fr = new FileReader(filename);//decalres a new file reader called fr
			BufferedReader br = new BufferedReader(fr);//declares a new bufferedreader called br using fr as a parameter to be passed along
			for(int count= 0;count<line;count++)	
			{
				lineOfData = br.readLine();//this then reads from file again to check if not empty
			}
			//////System.out.println("Line of data" + lineOfData);
		}
		catch(Exception exc)//if any errrors are found they are caught here
		{
			////System.out.println("Read error A.2");//informs that an error was found
			
		}
		return(decrypt(lineOfData));//returns array
		
	}




	//==================== SEARCHING =================


	//standard Binary search to locate the correct surname will push the location of the actual location it should be rather despite it being there or not
	//first index will be the actaul pointer to the value it should be 
	//the second index will inform the system wether or not the value needs to be added
	public int[] findIndexOfSuranme(String[][] array, String desiredPatient,int[] items)
	{
		
		String surname;//initalises the id used to find the desired value
		items[1]=0;
		int position = -1;//initaly sets the value to an index that will alloways is out of bounds
		int length = array.length;//finds the length of the array
		int startPoint = 0;//sets start point at 0
		int endPoint = length;//declares the endpoint
		int midPoint=0;//initalises the midpoint of the array
		try
		{
			outerSearch1: //declares a label will be used to force the code to terminate if found the value
			do
			{
				midPoint = ((startPoint+endPoint)/2);//updates the midpoint by finding the median
				
				//statement used to check if midpoint is at end(see dev tessting 17/01/20 for why at this point)
				if(midPoint>= length)//selection determining if the value is at the end of the array
					{
						
						position = endPoint;//assigns position to first index
						//////System.out.println("Option 4");//check variable
						items[1]=1;//if the surname is at the end of the system and not there sets this attrubite respectively 
						break outerSearch1;//exits search with all the items needed
						
					}
					
				//////System.out.println(midPoint+"Midpoint");//informs the current index being checked
				surname = array[midPoint][0];//sets the index of the desired item
				//////System.out.println(surname+"surname");//check variable

				if(surname.equals(desiredPatient))//if the surnames are the same
				{
					position = midPoint;//asigns the correct index the item should be located in
					//////System.out.println("Option 1");//check variable
					break outerSearch1;//exits search with all the items needed
				}
				else if((midPoint-1)>=0)
				{
					if((desiredPatient.compareToIgnoreCase(array[midPoint][0])<0)&&(desiredPatient.compareToIgnoreCase(array[midPoint-1][0])>0))//if the desired location is between index a and index a+1 so is not actually on the system
					{
						position = midPoint;//asigns the correct index the item should be located in
						//////System.out.println("Option 2 occurs at" + (midPoint));//check variable
						items[1]=1;//if the surname is at the end of the system and not there sets this attrubite respectively 
						break outerSearch1;//exits search with all the items needed
					}
				}
				else if(midPoint== 0)//selection determining if the value is at the start of the array
				{
					if(desiredPatient.compareToIgnoreCase(array[0][0])<0)//selection determining if the desired location is at the first index
					{
						position = 0;//assigns position to first index
						//////System.out.println("Option 3");//check variable
						items[1]=1;//if the surname is at the end of the system and not there sets this attrubite respectively 
						break outerSearch1;//exits search with all the items needed
					}
				}
				if(desiredPatient.compareToIgnoreCase(surname)<0)//selection determining if the string occurs before the midpoint
				{
				//////System.out.println("not above midpoint");//check variable
				
					endPoint = midPoint -1;//moves the endpoint to better locate the position
				}
				if(desiredPatient.compareToIgnoreCase(surname)>0)//selection determining if the string occurs after the midpoint
				{
				
				//////System.out.println("not below midpoint");//check variable
					startPoint = midPoint +1;//moves the startpoint to better locate the position
				}	
					
				
			}while((endPoint>= startPoint));//termination condition when satisified 
		}	
		catch(Exception exc)
		{
			////System.out.println("Error");//check variable
			exc.printStackTrace();//outputs error trace
		}
		items[0]=position;//sets the location of the surname to the correct index so it can be returned
		return(items);//returns index
	}


	//index 0 represents the index of the item
	//the second determines whether the item needs to be added or not
	//a big error of the last search in the prototype was that if not found it would throw an error
	//this returns wether it needs to be added
	//if items[1]== 1 it was not found
	//if items[1]==0 it was found 
	public int[] searchPrimaryKeysBinary(String[] array,String patientIDDesired,int[] items)
	{
		String patientID;//initalises the id used to find the desired value
		items[1]=0;
		int position = -1;//initaly sets the value to an index that will alloways is out of bounds
		int length = array.length;//finds the length of the array
		int startPoint = 0;//sets start point at 0
		int endPoint = length;//declares the endpoint
		int midPoint=0;//initalises the midpoint of the array
		try
		{
			outerSearch1: //declares a label will be used to force the code to terminate if found the value
			do
			{
			
				midPoint = ((startPoint+endPoint)/2);//updates the midpoint by finding the median
				//statement used to check if midpoint is at end(see dev tessting 17/01/20 for why at this point)
				if(midPoint>= length)//selection determining if the value is at the end of the array
					{
						
						position = endPoint-1;//assigns position to last index
						////System.out.println("item at last index");//check variable
						items[1]=1;//if the patientID is at the end of the system and not there sets this attrubite respectively 
						break outerSearch1;//exits search with all the items needed
						
					}
					
				patientID = array[midPoint].substring(0,11);//sets the index of the desired item
				////System.out.println(patientID + "ID");//check variable

				if(patientID.equals(patientIDDesired))//if the surnames are the same
				{
					////System.out.println("Item found");//check variable
					position = midPoint;//asigns the correct index the item should be located in
					//////System.out.println("Option 1");//check variable
					break outerSearch1;//exits search with all the items needed
				}
				else if((midPoint-1)>=0)
				{
					if((patientIDDesired.compareToIgnoreCase(array[midPoint].substring(0,11))<0)&&(patientIDDesired.compareToIgnoreCase(array[midPoint-1].substring(0,11))>0))//if the desired location is between index a and index a+1 so is not actually on the system
					{
						position = midPoint;//asigns the correct index the item should be located in
						////System.out.println("occurs between "+array[midPoint]+" and "+array[midPoint-1]);//check variable
						items[1]=1;//if the patientID is at the end of the system and not there sets this attrubite respectively 
						break outerSearch1;//exits search with all the items needed
					}
				}
				else if(midPoint== 0)//selection determining if the value is at the start of the array
				{
					if(patientIDDesired.compareToIgnoreCase(array[0].substring(0,11))<0)//selection determining if the desired location is at the first index
					{
						////System.out.println("Item occurs at the start");//check variable
						position = 0;//assigns position to first index
						//////System.out.println("Option 3");//check variable
						items[1]=1;//if the surname is at the end of the system and not there sets this attrubite respectively 
						break outerSearch1;//exits search with all the items needed
					}
				}
				if(patientIDDesired.compareToIgnoreCase(patientID)<0)//selection determining if the string occurs before the midpoint
				{
					////System.out.println("Item occurs before midpoint");//check variable
					endPoint = midPoint -1;//moves the endpoint to better locate the position
				}
				if(patientIDDesired.compareToIgnoreCase(patientID)>0)//selection determining if the string occurs after the midpoint
				{
					////System.out.println("Item occurs after midpoint");//check variable
					startPoint = midPoint +1;//moves the startpoint to better locate the position
				}	
					
				
			}while((endPoint>= startPoint));//termination condition when satisified 
		}	
		catch(Exception exc)
		{
			////System.out.println("Error");//check variable
			exc.printStackTrace();//outputs error trace
		}
		items[0]=position;//sets the location of the surname to the correct index so it can be returned
		return(items);
	}
	
		
		//search that looks for a patient id in an array it will return the index and an int if it is present or not
	
	
	public int[] searchPrimaryKeysLinear(String[] array,String patientIDDesired,int[] items)
	{
		
		items[1]=0;
		int position = -1;//initaly sets the value to an index that will alloways is out of bounds
		//check first value
		if(patientIDDesired.compareToIgnoreCase(array[0].substring(0,11))<0)//selection determining if the value is at the end of the array
		{
			position = 0;//assigns position to first index
			//////System.out.println("less than WILL BE AT INDEX "+position);
			items[1]=1;//if the surname is at the end of the system and not there sets this attrubite respectively 
					
		}
		//check final value
		if(patientIDDesired.compareToIgnoreCase(array[array.length-1].substring(0,11))>0)//selection determining if the value is at the end of the array
		{
			position = array.length;
			//////System.out.println("more than WILL BE AT INDEX "+position);
			items[1]=1;//if the patientID is at the end of the system and not there sets this attrubite respectively 
			
		}
		//check middle value
		outerSearch1:
		for(int indexValue = 0;indexValue<array.length;indexValue++)
		{
			//check if it is the value
			if(array[indexValue].substring(0,11).equals(patientIDDesired))//if the surnames are the same
			{
				position = indexValue;//asigns the correct index the item should be located in
				//////System.out.println("SHOULD BE AT INDEX "+position);

				break outerSearch1;//exits search with all the items needed
			}
			//check it is between values
			else if((indexValue-1)>=0)
			{
				if((patientIDDesired.compareToIgnoreCase(array[indexValue].substring(0,11))<0)&&(patientIDDesired.compareToIgnoreCase(array[indexValue-1].substring(0,11))>0))//if the desired location is between index a and index a+1 so is not actually on the system
				{
					position = indexValue;//asigns the correct index the item should be located in
					items[1]=1;//if the patientID is at the end of the system and not there sets this attrubite respectively
					//////System.out.println("WILL BE AT INDEX "+position);
					break outerSearch1;//exits search with all the items needed
				}
			}
		}
		items[0]=position;//sets the location of the surname to the correct index so it can be returned
		return(items);
	}
	
	public int[] searchPrimaryKeysLinearConsultantSpcific(String[] array,String patientIDDesired,int[] items)
	{
		////System.out.println("========================================================================");
		////System.out.println("Searching for "+patientIDDesired);
		////System.out.println("");

		for(int indexValue = 0;indexValue<array.length;indexValue++)
		{
			////System.out.println("line "+indexValue +" "+array[indexValue] );
		}
		
		items[1]=0;
		int position = -1;//initaly sets the value to an index that will alloways is out of bounds
		if(array.length>0)
		{
			//check first value
			if(patientIDDesired.compareToIgnoreCase(array[0].substring(0,11))<0)//selection determining if the value is at the end of the array
			{
				position = 0;//assigns position to first index
				////System.out.println("less than WILL BE AT INDEX "+position);
				items[1]=1;//if the surname is at the end of the system and not there sets this attrubite respectively 
						
			}
			//check final value
			if(patientIDDesired.compareToIgnoreCase(array[array.length-1].substring(0,11))>0)//selection determining if the value is at the end of the array
			{
				position = array.length;
				////System.out.println("more than WILL BE AT INDEX "+position);
				items[1]=1;//if the patientID is at the end of the system and not there sets this attrubite respectively 
				
			}
			//check middle value
			outerSearch1:
			for(int indexValue = 0;indexValue<array.length;indexValue++)
			{
				//check if it is the value
				if(array[indexValue].substring(0,11).equals(patientIDDesired))//if the surnames are the same
				{
					position = indexValue;//asigns the correct index the item should be located in
					////System.out.println("SHOULD BE AT INDEX "+position);
					items[1]=0;
					break outerSearch1;//exits search with all the items needed
				}
				//check it is between values
				else if((indexValue-1)>=0)
				{
					if(patientIDDesired.compareToIgnoreCase(array[indexValue].substring(0,11))<0)//if the desired location is between index a and index a+1 so is not actually on the system
					{
						if(array[indexValue-1].substring(0,1).equals("B")==false)//checks to see if the value before is a booking
						{	
							if(patientIDDesired.compareToIgnoreCase(array[indexValue-1].substring(0,11))>0)
							{
								position = indexValue;//asigns the correct index the item should be located in
								items[1]=1;//if the patientID is at the end of the system and not there sets this attrubite respectively
								////System.out.println("WILL BE AT INDEX "+position);
								break outerSearch1;//exits search with all the items needed
							}
						}
						else//the id infront is larger and a booking exists behind it
						{
							position = indexValue;//asigns the correct index the item should be located in
								items[1]=1;//if the patientID is at the end of the system and not there sets this attrubite respectively
								////System.out.println("WILL BE AT INDEX "+position);
								break outerSearch1;//exits search with all the items needed
						}
					}
				}
			}
		}
		else{//incase new consultant 
			position = 0;//assigns position to first index
			items[1]=1;//if the patientID is at the end of the system and not there sets this attrubite respectively
		}
		items[0]=position;//sets the location of the surname to the correct index so it can be returned
		return(items);
	}
	
	
	
	
	//==================== SORTING ====================
	
	
	//while this quick sort can be applied to anything that meets the parameters it is custom to the employee action log system for ordering the unordered employees being read from folder
	//works by using standaard recursive actions on the system.
	//list is split into two by pivot value
	//items less than go left
	//items go right
	//action repeats until "list" has length of 1 and are in order
	//then returns the array in order
	public String[][] quickSort(int indexHigh,int indexLow,String[][] ArrayToSort)
	{
		
		String[] pivotArray;//array that holds both the employeeID and EntityID value that is in correct place
		String[] tempBuffer;//array that holds both the employeeID and EntityID for swapping
		int tempHigh;
		int tempLow;
		tempHigh = indexHigh;
		tempLow = indexLow;
		pivotArray = ArrayToSort[(indexLow+indexHigh)/2];
		
		while(tempLow<=tempHigh)
		{
			while((ArrayToSort[tempLow][0].compareToIgnoreCase(pivotArray[0])<0)&&(tempLow<indexHigh))
			{
				tempLow++;
			}
			while((ArrayToSort[tempHigh][0].compareToIgnoreCase(pivotArray[0])>0)&&(tempHigh>indexLow))
			{
				tempHigh--;
			}
			//finds the next indexes to swap over
			if(tempLow<=tempHigh)
			{
				tempBuffer = ArrayToSort[tempLow];
				ArrayToSort[tempLow] = ArrayToSort[tempHigh];
				ArrayToSort[tempHigh] = tempBuffer;
				tempLow++;
				tempHigh--;
			}
		}
		if(indexLow<tempHigh)
		{
			quickSort(tempHigh,indexLow,ArrayToSort);
		}
		//sorts all the parts to the lesft before moving onto the right
		if(indexHigh>tempLow)
		{
			quickSort(indexHigh,tempLow,ArrayToSort);
		}
		//at this point the array would be in an order otherwise the recursive natrue would have occured
		return ArrayToSort;
	}
	
	//works by using standaard recursive actions on the system.
	//list is split into two by pivot value
	//items less than go left
	//items go right
	//action repeats until "list" has length of 1 and are in order
	//then returns the array in order
	public int[] quickSort(int indexHigh,int indexLow,int[] ArrayToSort)
	{
		int pivotArray;//array that holds both the employeeID and EntityID value that is in correct place
		int tempBuffer;//array that holds both the employeeID and EntityID for swapping
		int tempHigh;
		int tempLow;
		tempHigh = indexHigh;
		tempLow = indexLow;
		pivotArray = ArrayToSort[(indexLow+indexHigh)/2];
		//System.out.print("\n ");
		for(int count = 0;count<ArrayToSort.length;count++)
		{
			//System.out.print(ArrayToSort[count]+" ");
		}			
			//System.out.println("Currnet Pivot "+ pivotArray);

		while(tempLow<=tempHigh)
		{
			while((ArrayToSort[tempLow]<(pivotArray))&&(tempLow<indexHigh))
			{
				
				tempLow++;
			}
			while((ArrayToSort[tempHigh]>(pivotArray))&&(tempHigh>indexLow))
			{
				tempHigh--;
			}
			//finds the next indexes to swap over
			if(tempLow<=tempHigh)
			{
				tempBuffer = ArrayToSort[tempLow];
				ArrayToSort[tempLow] = ArrayToSort[tempHigh];
				ArrayToSort[tempHigh] = tempBuffer;
				tempLow++;
				tempHigh--;
			}
		}
		if(indexLow<tempHigh)
		{
			quickSort(tempHigh,indexLow,ArrayToSort);
		}
		//sorts all the parts to the lesft before moving onto the right
		if(indexHigh>tempLow)
		{
			quickSort(indexHigh,tempLow,ArrayToSort);
		}
		
		//at this point the array would be in an order otherwise the recursive natrue would have occured
		return ArrayToSort;
	}
	
	//works by using standaard recursive actions on the system.
	//list is split into two by pivot value
	//items less than go left
	//items go right
	//action repeats until "list" has length of 1 and are in order
	//then returns the array in order
	//this orders items in desending order
	public int[] quickSortDESC(int indexHigh,int indexLow,int[] ArrayToSort)
	{
		int pivotArray;//array that holds both the employeeID and EntityID value that is in correct place
		int tempBuffer;//array that holds both the employeeID and EntityID for swapping
		int tempHigh;
		int tempLow;
		tempHigh = indexHigh;
		tempLow = indexLow;
		pivotArray = ArrayToSort[(indexLow+indexHigh)/2];
		//System.out.print("\n ");
		for(int count = 0;count<ArrayToSort.length;count++)
		{
			//System.out.print(ArrayToSort[count]+" ");
		}			
			//System.out.println("Currnet Pivot "+ pivotArray);

		while(tempLow<=tempHigh)
		{
			while((ArrayToSort[tempLow]>(pivotArray))&&(tempLow<indexHigh))
			{
				tempLow++;
			}
			while((ArrayToSort[tempHigh]<(pivotArray))&&(tempHigh>indexLow))
			{
				tempHigh--;
			}
			//finds the next indexes to swap over
			if(tempLow<=tempHigh)
			{
				tempBuffer = ArrayToSort[tempLow];
				ArrayToSort[tempLow] = ArrayToSort[tempHigh];
				ArrayToSort[tempHigh] = tempBuffer;
				tempLow++;
				tempHigh--;
			}
		}
		if(indexLow>tempHigh)
		{
			quickSort(tempHigh,indexLow,ArrayToSort);
		}
		//sorts all the parts to the lesft before moving onto the right
		if(indexHigh<tempLow)
		{
			quickSort(indexHigh,tempLow,ArrayToSort);
		}
		//at this point the array would be in an order otherwise the recursive natrue would have occured
		return ArrayToSort;
	}
	
	
	
	
	
	//	================== conversions =====================
	
	//These methods convert the local date datatype used in the date and time pucker and convert them into the standard utility date datatype
	public Date convertToDateViaInstant(LocalDate dateToConvert)
	{
    return java.util.Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	public LocalDate convertToLocalDateViaInstant(Date dateToConvert)
	{
    return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	public LocalTime convertDateTotime(Date dateToConvert)
	{
		return LocalDateTime.ofInstant(dateToConvert.toInstant(), ZoneId.systemDefault()).toLocalTime();
	}

	// ===================== ACTION =======================
	
	
	//here we will use the values passed through the method call and will write them to file
	//surprisingly no validation needs to occur as the data will be computer generated or already valid
	//most attributes are string as they are the easiest to work with
	//with action contemplated about using ints then crosseferncing them but theres no need, this wont be used anywhere else in the system, also input is automated so would see no beneift
	//we are dealing with 6 parameters also as it will be less hassel if before we called the method for every call we would need to not only assign the values but also intialise them in an instance
	//it would be much easier to do it here
	public void createNewAction(String action,String entityID,String patientID,String admissionID,String newData,String oldData)
	{
		Employee tempEmployee = new Employee();
		
		//deal with using the entityid as we can just convert it here, means we can just pass eitherID through
		String employeeID = "E"+entityID.substring(1,entityID.length());
		
		//now we need to pull the contents of the entire file through
		String[] listOfActionsAndEmployees = rffReturnFullFile("EmployeeActionLogs.txt");//list of definitions is read from file
		String[][] AllEmployees = tempEmployee.returnAllEmployees();//returns all the employees on the system
		
		
		//we need to locate the employee
		int indexOfEmployee = tempEmployee.searchAllemployees(AllEmployees,employeeID);
		//split the line by ~ (first array should have two values concatenated ids and concatenated actions)
		String[] splitMainLine = listOfActionsAndEmployees[indexOfEmployee].split("~");
		// need to consider if the account has more than one action
		if(splitMainLine.length>1)
		{			
			//at the end of the second index just manaully add each value using correct splitting values (already have an intial split value for splitting actions at the start we need to add @ at the very end) need to seperate individual fields with £
			splitMainLine[1] = splitMainLine[1]+action+"£"+ftTimeInc.format(new Date())+"£"+newData+"£"+oldData+"£"+patientID+"£"+admissionID+"@";
			//concante items together
			String newLine = splitMainLine[0]+"~"+splitMainLine[1];
			listOfActionsAndEmployees[indexOfEmployee] = newLine;
		}
		else{
		listOfActionsAndEmployees[indexOfEmployee] = listOfActionsAndEmployees[indexOfEmployee]+action+"£"+ftTimeInc.format(new Date())+"£"+newData+"£"+oldData+"£"+patientID+"£"+admissionID+"@";
		}
		//WTF
		writeNewDataTofile(listOfActionsAndEmployees,"EmployeeActionLogs.txt");
	}

	
	//========================= ENCRYPTION ==========================
	
	
	//runs of the standard ASCII character list 
	//When the sysetem is about to be written this is called
	//Converts the character into an intger value
	//corerctly converts it into a new value
	//if the end of the list is reached (either the ASCII int value is 26 or 127 the system will loop and start the item at the other end)
	//returns the new string afterwards.
	
	public String decrypt(String string)//runs the decrypt procedure
	{
		String newString="";//empties the variable
		int length = string.length();//finds the length of the string wanting to be decrypting
		for (int i = 0; i<length;i++)//runs a for loop for the length of the string, it will go through every character
		{
			char letter = string.charAt(i);//finds an individual character of the encrypted string
			int letterASCII = (int)letter-1;//-10, which will find the actual ascii value of the balance
			if(letterASCII==31)
			{
				letterASCII = 126;
				
			}
			char newletter = (char)letterASCII;//converts the ascii valuee to a character
			newString = newString + newletter;//concatines so that at the end of the loop it will be decrypted
		}
		//////System.out.println("New word: "+newString);

		return newString;//returns the new decrypted balance
	}
	public String encrypt(String string)//declares the encryption procedure with the parameters of the string wanted to be converted
	{
		
		String encryptedValue=("");//emptys out any previous data
		int length = string.length();//sets the length of the array
		for (int i = 0; i<length;i++)//runs a for loop for every character of the balance
		{
			char letter = string.charAt(i);//finds the single character
			
			int letterASCII = (int)letter+1;//finds the ascii value and adds 10
			if(letterASCII==127)
			{
				letterASCII = 32;
				
			}
			char newletter = (char)letterASCII;//finds the new character value
			

			encryptedValue = encryptedValue + newletter;//adds the new character to the new encrypted statement
		}
		
		return encryptedValue;//returns the value
	}

}