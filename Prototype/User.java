import java.util.*;//imports the java utlities module	
import java.io.*;//imports the io package
import java.text.SimpleDateFormat;//imports the simple date format package which allows for more simple dates
public class User //
{
	String firstName;//declares attribute which holds first name
	String surName;//declares attribute which holds surname
	Date dob = new Date();//declares attribute which holds their dob
	SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
	SimpleDateFormat ftTimeInc = new SimpleDateFormat ("dd/MM/yyyy k:m");
	char gender;//declares attribute which holds their gender
	int addressHouseNum;//declares attribute which holds address number
	String addressHouseStreet;//declares attribute which holds their street
	String postcode;//declares attribute which holds their postcode
	String town;//declares attribute which holds their town
	String contactNum;//declares attribute which holds their constact information
	String password;//declares attribute which holds their password
	boolean nomoreNotifications;//value determining if the patient has no notifications
	Random randomObj = new Random();//declares an object of random which will be used to generate random values
	

	//reads a file and will split contents to indexed array
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
				//System.out.println(line);
				line++;//increments line
				
				lineOfDataa = bra.readLine();//this then reads from file again to check if not empty
			}
			lengtha = line;//sets the number of entites on the file
		}
		catch(Exception exc)//if any errrors are found they are caught here
		{
			System.out.println("Read error A.1");//informs that an error was found
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
				arrayOfData[count] = lineOfData;//sets the index to the currently read line
				count++;//iterates variable
				lineOfData = br.readLine();//this then reads from file again to check if not empty
			}
		}
		catch(Exception exc)//if any errrors are found they are caught here
		{
			System.out.println("Read error A.2");//informs that an error was found
		}
		return(arrayOfData);//returns array
	}
	
	
	//notification one
	
	public int rff(String filename,String searchValue)//declares a method that reads from file and saves it to  the array
	{
		int line= -1;
		int position = -1;
		try//the program will try and do this
		{
			line = 0;//sets start point at 0
			FileReader fr = new FileReader(filename);//decalres a new file reader called fr
			BufferedReader br = new BufferedReader(fr);//declares a new bufferedreader called br using fr as a parameter to be passed along
			String ClientReadLine = br.readLine();//saves the line read from file to the variable
			line++;//iterates variable
			while(ClientReadLine !=null)//a while loop that runs untill the variable read is not empty(The end of the array is not reached)
			{
				if(ClientReadLine.substring(0,11).contains(searchValue)== true)//selection determining if the desired id is found
				{
					position = line;//updates the location where the patient was found
				}
				ClientReadLine = br.readLine();//this then reads from file again to check if not empty
				line++;//iterates variable
			}
		}
		catch(Exception exc)//if any errrors are found they are caught here
		{
			System.out.println("Read error B");//informs that an error was found
		}
		return(position);//returns position of desired value
	}
	
	//writes patient's to file
	
	public void wtf(String[] patients, String filename, int length)//declares a procedure that writes the array to file using the object from bankclient to be passed through
	{
		try//this will try to run
		{
			FileWriter fw = new FileWriter(filename);//declare a new file writer that will ammend not not write to file
			for(int count = 0;count<length;count++)//creates for loop that runs for the length of the array
			{
			fw.write(patients[count]);//writes current index to line
			fw.write("\r\n");//adds in a space to the file
			}
			fw.close();//closes the file
		}
		catch(Exception exc)//if any error occurs the error is caught
		{
			System.out.println("WRITE error");//informs that a write error has occured
			exc.printStackTrace();//prints the stack trace
		}
	}
	public String[] unConcatenateStringActionLog(String singleString, String gaptype)
	{
		String line = singleString;//sets variable as string passed through
		int counta = line.length() - line.replace(gaptype, "").length();//finds out how many new indexs there are
		//System.out.println("length for string"+counta);
		String[] array = new String[counta];//saves the value and sets it as the size of the array
		int arrayIndex = 0;//sets start point at 0
		int length = singleString.length();//finds length of array
		for(int count=12;count<length;)//creates for loop that starts not at the primary key
		{
			int wordLength = 0;//sets start point at 0
			String word="";//declares an empty string
			String tempChar = singleString.charAt(count)+"";//retrieves the character and makes it a string
			while(tempChar.equals(gaptype)==false)//while loop with a termination condition where the character is not a gaptype
			{
				tempChar = "";//declares an empty string
				tempChar = singleString.charAt(count)+"";//retrieves the character and makes it a string
				word = word+ singleString.charAt(count);//concatenates existing characters with the new one
				count++;//iterates variable
				wordLength++;//iterates variable
			}
			word = word.substring(0,wordLength-1);//removes the gaptype from the word
			array[arrayIndex] = word;//adds word to array
			arrayIndex++;//iterates variable
			
		}
		return(array);//returns arary with new list of strings
	}
	public String[] unConcatenateStringAdmission(String singleString, String gaptype)
	{
		String line = singleString;//sets variable as string passed through
		int counta = line.length() - line.replace(gaptype, "").length();//finds out how many new indexs there are
		//System.out.println("length for string"+counta);
		String[] array = new String[counta];//saves the value and sets it as the size of the array
		int arrayIndex = 0;//sets start point at 0
		int length = singleString.length();//finds length of array
		for(int count=0;count<length;)//creates for loop that starts not at the primary key
		{
			int wordLength = 0;//sets start point at 0
			String word="";//declares an empty string
			String tempChar = singleString.charAt(count)+"";//retrieves the character and makes it a string
			while(tempChar.equals(gaptype)==false)//while loop with a termination condition where the character is not a gaptype
			{
				tempChar = "";//declares an empty string
				tempChar = singleString.charAt(count)+"";//retrieves the character and makes it a string
				word = word+ singleString.charAt(count);//concatenates existing characters with the new one
				count++;//iterates variable
				wordLength++;//iterates variable
			}
			word = word.substring(0,wordLength-1);//removes the gaptype from the word
			array[arrayIndex] = word;//adds word to array
			arrayIndex++;//iterates variable
			
		}
		return(array);//returns arary with new list of strings
	}
		public String[] unConcatenateStringConsultanttxt(String singleString, String gaptype)
	{
		String line = singleString;//sets variable as string passed through
		int counta = line.length() - line.replace(gaptype, "").length();//finds out how many new indexs there are
		//System.out.println("length for string"+counta);
		String[] array = new String[counta+1];//saves the value and sets it as the size of the array + 1
		int arrayIndex = 0;//sets start point at 0
		int length = singleString.length();//finds length of array
		for(int count=0;count<length;)//creates for loop that starts not at the primary key
		{
			int wordLength = 0;//sets start point at 0
			String word="";//declares an empty string
			String tempChar = singleString.charAt(count)+"";//retrieves the character and makes it a string
			while(tempChar.equals(gaptype)==false)//while loop with a termination condition where the character is not a gaptype
			{
				tempChar = "";//declares an empty string
				tempChar = singleString.charAt(count)+"";//retrieves the character and makes it a string
				word = word+ singleString.charAt(count);//concatenates existing characters with the new one
				count++;//iterates variable
				wordLength++;//iterates variable
			}
			word = word.substring(0,wordLength-1);//removes the gaptype from the word
			array[arrayIndex] = word;//adds word to array
			arrayIndex++;//iterates variable
			
		}
		return(array);//returns arary with new list of strings
	}
	//for single strings will split them up, e.g. symptoms or consultant expetises
	
	
	//Documents and admissions
	public String[] rff(String filename, int line)//declares a method that reads from file and saves it to  the array
	{
		String[] splitArrayData = new String[1000];//creates an array with a large size
		String admissionLine="";//declares an empty string
		int lineAt =0;//starts at at the first index
		try//the program will try and do this
		{
			FileReader fr = new FileReader(filename);//decalres a new file reader called fr
			BufferedReader br = new BufferedReader(fr);//declares a new bufferedreader called br using fr as a parameter to be passed along
			
			loop: //declares a label
			while(admissionLine !=null)//a while loop that runs untill the variable read is not empty(The end of the array is not reached)
			{
				//System.out.println("line "+lineAt);
				admissionLine = br.readLine();//reads line
				//System.out.println("line "+admissionLine);
				if(lineAt==line)//termination condition when satisified 
				{
					break loop;//breaks from loop
				}
				else
				{
					lineAt++;//iterates variable
				}
			}
		}
		catch(Exception exc)//if any errrors are found they are caught here
		{
			System.out.println("Read error C");//informs that an error was found
		}
		//System.out.println("Found on line "+lineAt + " contents "+ admissionLine);
		splitArrayData = admissionLine.split(",");//concatinates the string into an array
		int length = splitArrayData.length;//finds length of array
		String[] array = new String[length];//creates a new array with that length found prior
		array = splitArrayData;//updates array
		return(array);//returns array
	}
	
	//linear search for patient demogrphic finds new location of desired patient 
	public int findIndexOfPatient(String[] array, String desiredPatient)
	{
		String currentID;//initalises the id used to find the desired value
		int position = -1;//initaly sets the value to an index that will alloways is out of bounds
		for(int count = 0;count<array.length;count++)//for loop that runs through the entirty of the array
		{
			currentID = array[count];//assigns the current id to the attribute
			if(count== 0)//selection determining if the value is at the start of the array
			{
				/*
				An int value: 0 if the string is equal to the other string, ignoring case differences.
				< 0 if the string is lexicographically less than the other string 
				> 0 if the string is lexicographically greater than the other string (more characters)
				*/
				currentID = array[count].substring(0,11);//substrings string to get primary key
				if(desiredPatient.compareToIgnoreCase(array[0])<0)//selection determining if the desired location is at the first index
				{
					position = 0;//assigns position to first index
				}
			}
			if((count > 0)&&(count<array.length))//if the desired location is between the start and end
			{
				currentID = array[count].substring(0,11);//substrings string to get primary key
				if((desiredPatient.compareToIgnoreCase(array[count])<0)&&(desiredPatient.compareToIgnoreCase(array[count-1])>0))//if the desired location is between index a and index a+1 
				{
					position = count;//asigns the correct index the item should be located in 
				}
			}
			if(count== array.length-1)//selection determining if the location is at the last index of the array
			{
				currentID = array[count].substring(0,11);//substrings string to get primary key
				if(desiredPatient.compareToIgnoreCase(currentID)>0)//making sure that the position is above 0 
				{
					position = array.length;//assigns index to be the next value above the length(last index + 1)
				}
			}
			currentID = array[count].substring(0,11);//substrings string to get primary key
			if(desiredPatient.compareToIgnoreCase(array[0])==0)
			{
				position = count;//asigns the correct index the item should be located in
			}
			
		}
		return(position);//returns index
		
	}
	//method polymorphised to start at a later index
	public int findIndexOfPatient(String[] array, String desiredPatient,int counter)
	{
		String currentID;//initalises the id used to find the desired value
		int position = -1;//initaly sets the value to an index that will alloways is out of bounds
		loop: //declares a label
		for(int count = counter;count<array.length;count++)//for loop that runs through the entirty of the array
		{
			currentID = array[count];//assigns the current id to the attribute
			//System.out.println("++++++ current Admission "+currentID);
			if(count== 0)//selection determining if the value is at the start of the array
			{
				currentID = array[count].substring(0,11);//substrings string to get primary key
				if(desiredPatient.compareToIgnoreCase(array[0])<0)//selection determining if the desired location is at the first index
				{
					position = 0;//assigns position to first index
				}
			}
			if((count > 0)&&(count<array.length))//if the desired location is between the start and end
			{
				currentID = array[count].substring(0,11);//substrings string to get primary key
				if((desiredPatient.compareToIgnoreCase(array[count])<0)&&(desiredPatient.compareToIgnoreCase(array[count-1])>0))//if the desired location is between index a and index a+1 
				{
					position = count;//asigns the correct index the item should be located in
				}
			}
			if(count== array.length-1)//selection determining if the location is at the last index of the array
			{
				currentID = array[count].substring(0,11);//substrings string to get primary key
				if(desiredPatient.compareToIgnoreCase(currentID)>0)//making sure that the position is above 0 
				{
					position = array.length;//assigns index to be the next value above the length(last index + 1)
				}
			}
			currentID = array[count].substring(0,11);//substrings string to get primary key
			if(desiredPatient.compareToIgnoreCase(array[count])==0)//termination condition when satisified 
			{
				position = count;//asigns the correct index the item should be located in

				break loop;//breaks from loop
			}
			
		}
		//System.out.println("++++++ location of patient"+position);
		return(position);//returns index
		
	}
	public int findIndexOfEmployee(String[] array, String desiredPatient)
	{
		String currentID;//initalises the id used to find the desired value
		int position = -1;//initaly sets the value to an index that will alloways is out of bounds
		loop: //declares a label
		for(int count = 0;count<array.length;count++)//for loop that runs through the entirty of the array
		{
			currentID = array[count];//assigns the current id to the attribute
			if(count== 0)//selection determining if the value is at the start of the array
			{
				currentID = array[count].substring(0,11);//substrings string to get primary key
				if(desiredPatient.compareToIgnoreCase(array[0])<0)//selection determining if the desired location is at the first index
				{
					position = 0;//assigns position to first index
				}
			}
			if((count > 0)&&(count<array.length))//if the desired location is between the start and end
			{
				currentID = array[count].substring(0,11);//substrings string to get primary key
				if((desiredPatient.compareToIgnoreCase(array[count])<0)&&(desiredPatient.compareToIgnoreCase(array[count-1])>0))//if the desired location is between index a and index a+1 
				{
					position = count;//asigns the correct index the item should be located in
				}
			}
			if(count== array.length-1)//selection determining if the location is at the last index of the array
			{
				currentID = array[count].substring(0,11);//substrings string to get primary key
				if(desiredPatient.compareToIgnoreCase(currentID)>0)//making sure that the position is above 0 
				{
					position = array.length;//assigns index to be the next value above the length(last index + 1)
				}
			}
			currentID = array[count].substring(0,11);//substrings string to get primary key
			if(desiredPatient.compareToIgnoreCase(currentID)==0)//termination condition when satisified 
			{
				position = count;//asigns the correct index the item should be located in
				break loop;//breaks from loop
			}
			
		}
		return(position);//returns index
		
	}
	
	//binary search for a list of strings 
	public int findPositionOfString(String[] array, String id)
	{
		int position = 0;//sets start point at 0
		//System.out.println(id);
		if(id.length()<11)
		{
			 position = -1;//sets start point at 0
			
		}
		else
		{
		
		int length = array.length;//finds the length of the array
		int startPoint = 0;//sets start point at 0
		int endPoint = length;//declares the endpoint 
		boolean found = false;//sets the attribute that determine if the id is found
		int midPoint;//initalises the midpoint of the array
		String currentID;//declares the attribute of the 
		do
		{	
		//System.out.println("Start "+startPoint);
		//System.out.println("End "+endPoint);
			midPoint = ((startPoint+endPoint)/2);//updates the midpoint by finding the median
			//System.out.println("mid "+midPoint);
			currentID = array[midPoint].substring(0,11);//substrings the string for the midpoint
			if(id.compareToIgnoreCase(currentID)==0)//compares dates to see if they match
			{
			//	System.out.println("Found");
				found = true;//updates the attribute setting the value as found
				position = midPoint;//sets the index of the found index
			}	
			else if(id.compareToIgnoreCase(currentID)<0)//selection determining if the value is before the midpoint
			{
			//	System.out.println("not above midpoint");
				endPoint = midPoint -1;//iterates on the midpoint to get closer to the midpoint
			}
			else if(id.compareToIgnoreCase(currentID)>0)//selection determining if the value is after the midpoint
			{
			
			//	System.out.println("not below midpoint");
				startPoint = midPoint +1;//iterates on the midpoint to get closer to the midpoint
			}
		}
		while((found!=true)&&(endPoint>=0));//termination of the loop is that the value doesnt exist or the value is found
		}
		return(position);//returns the position of the index
	}
	
	//polymorphised to use dates instead of ids
	public int findPositionOfDate(String[] array, String date)
	{
		//System.out.println(date);
		int position = 0;//sets start point at 0
		int length = array.length;//finds the length of the array
		int startPoint = 0;//sets start point at 0
		int endPoint = length;//declares the endpoint 
		boolean found = false;//sets the attribute that determine if the id is found
		int midPoint;//initalises the midpoint of the array
		String currentID;//intialises the id
		do
		{	
		//System.out.println("Start "+startPoint);
		//System.out.println("End "+endPoint);
			midPoint = ((startPoint+endPoint)/2);//updates the midpoint by finding the median
			//System.out.println("mid "+midPoint);
			currentID = array[midPoint].substring(0,16);//substrings the string for the midpoint
			if(date.compareToIgnoreCase(currentID)==0)//compares dates to see if they match
			{
			//	System.out.println("Found");
				found = true;//updates the attribute setting the value as found
				position = midPoint;//sets the index of the found index
			}	
			else if(date.compareToIgnoreCase(currentID)<0)//selection determining if the value is before the midpoint
			{
			//	System.out.println("not above midpoint");
				endPoint = midPoint -1;//iterates on the midpoint to get closer to the midpoint
			}
			else if(date.compareToIgnoreCase(currentID)>0)//selection determining if the value is after the midpoint
			{
			
			//	System.out.println("not below midpoint");
				startPoint = midPoint +1;//iterates on the midpoint to get closer to the midpoint
			}
		}
		while((found!=true)&&(endPoint>=0));//termination of the loop is that the value doesnt exist or the value is found
		return(position);//returns the position of the index
	}
	
	//writes list of patients back to file
	public void wtfNewPatient(String[] patients, String filename)//declares a procedure that writes the array to file using the object from bankclient to be passed through
	{
	
		try//this will try to run
		{
			FileWriter fw = new FileWriter(filename);//declare a new file writer that will ammend not not write to file
			for(int count = 0;count<patients.length;count++)//runs a for loop that iterates from the full arrays length
			{
			fw.write(patients[count]);//writes the curent index to line
			fw.write("\r\n");//adds in a space to the file
			}
			fw.close();//closes the file
		}
		catch(Exception exc)//if any error occurs the error is caught
		{
			System.out.println("WRITE error");//informs that a write error has occured
			exc.printStackTrace();//prints the stack trace
		}
	}
	
	//id generator
	public String createUniqueID(String surname, String filename,String type)
	{
		String firstPart =  type.charAt(0)+ surname.substring(0,3);//concatenates the string with the type of entity it is along with the first three characters of the users surname
		firstPart = firstPart.toUpperCase();//uppercases allthe text
		String[] allIDs =  rffReturnFullFile(filename);//runs throught he entirety of the file and returns the list of entites it is wanted to search through
		boolean latestIDFound = false;//sets the attribute that determine if the id is found
		int position = 0;//sets start point at 0
		int length = allIDs.length;//finds the length of the array
		int startPoint = 0;//sets start point at 0
		int endPoint = length;//declares the endpoint 
		int midPoint;//initalises the midpoint of the array
		int  location;//intialises the location of the number of zeros 
		String currentID;//declares the id for the midpoint 
		String stringNum="";//declares an empty string
		boolean existingIDs = false;//attribute trying to find that if the id currently exists in the array
		int counter = -1;//initaly sets the value to an index that will alloways is out of bounds
		int currentIntID = -1;//initaly sets the value to an index that will alloways is out of bounds
		
		try
		{
			outerSearch1: //declares a label
			do
			{
				if(allIDs.length > 0)//selection determining ifthere are more than one id in the array
				{
					
					midPoint = ((startPoint+endPoint)/2);//updates the midpoint by finding the median
					
					currentID = allIDs[midPoint].substring(0,4);//substrings to find the part of the primary key needed
					if(currentID.contains(firstPart))//if the ids are the same
					{
						currentIntID = Integer.parseInt(allIDs[midPoint].substring(5,11));//finds the interger value from the rest of the primary id
						System.out.println(midPoint+" MID"+currentIntID);
						if(midPoint<allIDs.length)//selection determining ifthe value is less than the size of the array
						{
							forLoop: //declares a label
							for(counter = midPoint; counter<allIDs.length;counter++)//runs a for loop from this postition until the end of the array
							{
								currentID = allIDs[counter];//assigns value from array
								System.out.println(currentID);
								currentIntID = Integer.parseInt(currentID.substring(5,11));//finds the interger value from the rest of the primary id
								System.out.println(currentIntID);
								location = currentIntID;//linearly iterates the location
								stringNum= location+"";//declares an empty string plus one
								
								while(stringNum.length()< 7){//termination condition when satisified 
									stringNum ="0"+stringNum;//adds a zero untill there are 7 zero's
								}
								System.out.println(firstPart+stringNum);
								if(currentID.substring(0,11).equals(firstPart+stringNum)==true)//selection determining if the ids match
								{
									existingIDs = true;		//informs the system that more of this characters exist
									System.out.println("Found");
									currentIntID++;//iterates variable
									System.out.println(currentIntID+ "   new ID");
								}	
								if(currentID.substring(0,11).equals(firstPart+stringNum)==false)
								{
									System.out.println("Not found");
									existingIDs = true;//informs the system that more of this characters exist
									currentID = allIDs[counter-1];//assigns value from array
								System.out.println(currentID);
								currentIntID = Integer.parseInt(currentID.substring(5,11));//finds the interger value from the rest of the primary id
									currentIntID++;
									break outerSearch1;//breaks from loop
								}	
								if(currentID.substring(0,11).contains(firstPart)==false)
								{
									System.out.println("Not found");
									existingIDs = true;//informs the system that more of this characters exist
									currentID = allIDs[counter-1];//assigns value from array
								System.out.println(currentID);
								currentIntID = Integer.parseInt(currentID.substring(5,11));//finds the interger value from the rest of the primary id
									currentIntID++;
									break outerSearch1;//breaks from loop
								}	
								if(allIDs.length==midPoint+1)
								{
									
									existingIDs = true;//informs the system that more of this characters exist
									break outerSearch1;//breaks from loop
								}
								if(allIDs.length-1==counter)
								{
									
									existingIDs = true;//informs the system that more of this characters exist
									break outerSearch1;//breaks from loop
								}
							}
						}
						else
						{
							existingIDs = true;	//informs the system that more of this characters exist
							break outerSearch1;//breaks from loop
						}
					}
					else if(firstPart.compareToIgnoreCase(currentID)<0)//selection determining if the string occurs before the midpoint
					{
					//	System.out.println("not above midpoint");
						endPoint = midPoint -1;//moves the endpoint to better locate the position
					}
					else if(firstPart.compareToIgnoreCase(currentID)>0)//selection determining if the string occurs after the midpoint
					{
					
					//	System.out.println("not below midpoint");
						startPoint = midPoint +1;//moves the startpoint to better locate the position
					}		
				}
				
			}while((latestIDFound == false)&&(endPoint> startPoint));//termination condition when satisified 
			if(existingIDs == true){//selection determining if any other characters exist
				location = currentIntID;//linearly iterates the location
				stringNum= location+"";//declares an empty string plus one
				while(stringNum.length()< 7){//termination condition when satisified 
					stringNum ="0"+stringNum;//adds a zero untill there are 7 zero's
				}
			}
			else{
				stringNum = "0000001";//initalises the string to contain the first with 0000001
			}
		}
		catch(Exception exc)
		{
			System.out.println("Error");
		}
		String identification = firstPart+stringNum;//the two parts are concatenated together
		return(identification);//returns id
	}
	public Admission copyAttributesAdmission(Admission oldAmission)
	{
		Admission newAdmission = new Admission();//creates new instance of admissions class
		newAdmission.admissionID=oldAmission.admissionID;//coppies attribute over to new admisison
		newAdmission.ward = oldAmission.ward;//coppies attribute over to new admisison
		newAdmission.room = oldAmission.room;//coppies attribute over to new admisison
		newAdmission.consultantName = oldAmission.consultantName;//coppies attribute over to new admisison
		newAdmission.dateOfNextAppointmentA = oldAmission.dateOfNextAppointmentA;//coppies attribute over to new admisison
		newAdmission.timeOfNextAppointmentA = oldAmission.timeOfNextAppointmentA;//coppies attribute over to new admisison
		newAdmission.active = oldAmission.active;//coppies attribute over to new admisison
		newAdmission.numberOfDocuments = oldAmission.numberOfDocuments;//coppies attribute over to new admisison
		newAdmission.medication = oldAmission.medication;//coppies attribute over to new admisison
		newAdmission.listOfSymptoms = oldAmission.listOfSymptoms;//coppies attribute over to new admisison
		newAdmission.currentDiagnosis = oldAmission.currentDiagnosis;//coppies attribute over to new admisison
		newAdmission.staffName = oldAmission.staffName;//coppies attribute over to new admisison
		newAdmission.dateAdmissionCreated = oldAmission.dateAdmissionCreated;//coppies attribute over to new admisison
		newAdmission.admissionsStaffID = oldAmission.admissionsStaffID;//coppies attribute over to new admisison
		newAdmission.admissionsConsultantID = oldAmission.admissionsConsultantID;//coppies attribute over to new admisison
		newAdmission.listOfDocuments = oldAmission.listOfDocuments;//coppies attribute over to new admisison
		return(newAdmission);//returns admission
	}
	public String decrypt(String string)//runs the decrypt procedure
	{
		String newString="";//empties the variable
		int length = string.length();//finds the length of the string wanting to be decrypting
		for (int i = 0; i<length;i++)//runs a for loop for the length of the string, it will go through every character
		{
			char letter = string.charAt(i);//finds an individual character of the encrypted string
			int letterASCII = (int)letter-10;//-10, which will find the actual ascii value of the balance
			char newletter = (char)letterASCII;//converts the ascii valuee to a character
			newString = newString + newletter;//concatines so that at the end of the loop it will be decrypted
		}
		return newString;//returns the new decrypted balance
	}
	public String encrypt(String string)//declares the encryption procedure with the parameters of the string wanted to be converted
	{
		String encryptedValue=("");//emptys out any previous data
		int length = string.length();//sets the length of the array
		for (int i = 0; i<length;i++)//runs a for loop for every character of the balance
		{
			char letter = string.charAt(i);//finds the single character
			int letterASCII = (int)letter+10;//finds the ascii value and adds 10
			char newletter = (char)letterASCII;//finds the new character value
			encryptedValue = encryptedValue + newletter;//adds the new character to the new encrypted statement
		}
		
		return encryptedValue;//returns the value
	}

}