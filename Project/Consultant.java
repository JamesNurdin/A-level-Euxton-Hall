import java.util.*;//imports the java utlities module
import java.io.*;//imports the io package
import java.text.SimpleDateFormat;//imports the simple date format package which allows for more simple dates
import javax.swing.*;//imports the java swing package,by using a wildcard all aspects of the package are imported

public class Consultant extends Employee
{
	//======================== Date formatting attributes ===================
	
	SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");//declares a simple font for a visual improvement over the full version
	SimpleDateFormat timeft = new SimpleDateFormat ("kk:mm");//declares a simple font for a visual improvement over the full version
	SimpleDateFormat ftTimeInc = new SimpleDateFormat ("dd/MM/yyyy kk:mm");//declares a simple font for a visual improvement over the full version

	//======================== Entity Attributes ===================
	
	int numberOfPatients;//declares attribute which holds thier numberOfPatients
	String wardLocatedIn;//declares attribute which holds thier wardLocatedIn
	String[] expertiese;//declares attribute which holds thier experties
	String consultantID;//declares attribute which holds thier consultantID
	String[] consultantPatientList;//holds ids for all consultants patients
	String[] concatenatedconsultantPatientList;//array of all the admissions each patient has but concatenated together
	Admission[] patientAdmissionList;
	Date dateOfNextAppointmentC = new Date();//declares attribute which holds thier dateOfNextAppointmentC
	String dayOfNextAppointmentC;//declares attribute which holds thier dayOfNextAppointmentC
	String timeOfNextAppointmentC;//declares attribute which holds thier timeOfNextAppointmentC
	String[] listOfPatientsAdmissions;//declares attribute which holds thier listOfPatientsAdmissions
	String singleExpertieseString;//declares attribute which holds thier singleExpertieseString
	String[] listOfIndividualAdmissions;//declares attribute which holds thier 
	Booking[][] listOfBookings;
	Booking[] todaysAppointments = new Booking[20];
	boolean found;
	//Method which retreieves the consultant from file
	//first step is the pulling of details from the actuall file(using the id as a parameter the full contents are pulled, eventually admissions will be pulled from this method also)
	//Next a new array holding all the values from the first line is called, splits every user defined "sub index" indicated by a comma or # if a "sub sub index of an index etc"
	//a new instance is called and all the attributes are assigned to the correct variable for the object
	//To avoid formatting errors when writing to file similar to the process of encryption any variable that uses \n is replaced by a # and vice versa for the opposing action
	//Any array variables have their attributes also assigned
	
	//After the basic demographic has been intialised the process of actually pulling admissions is under went
	//First the patients assigned to the consultant are pulled and twoarrays are produced a copy of all the patients with their actuall attributes and a line of all the admissions available to the consultant 
	//These will get assigned to the consultant, these will be used to pull the admissions when selecting them at a later point at the consultants homeScreen
	//Finally instance is then returned
	public Consultant retrieveConsultant(String consultantIDLocal)
	{
		SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");

		String[] fileContents = rffReturnFullFile(consultantIDLocal+"_file.txt");//concatinates the string into an array
		String[] newConsultantDetails = fileContents[0].split(",");
		Consultant tempConsultant = new Consultant();//creates a new instance of the class
		if(newConsultantDetails[0]!="Null")
		{
			tempConsultant.consultantID = newConsultantDetails[0];//the attribute is set from the correct index from the array
			tempConsultant.employeeID = newConsultantDetails[1];//the attribute is set from the correct index from the array
			tempConsultant.password = newConsultantDetails[2];//the attribute is set from the correct index from the array
			tempConsultant.surName = newConsultantDetails[3];//the attribute is set from the correct index from the array
			tempConsultant.firstName = newConsultantDetails[4];//the attribute is set from the correct index from the array
			tempConsultant.addressHouseNum = Integer.parseInt(newConsultantDetails[5]);//the attribute is set from the correct index from the array
			tempConsultant.addressHouseStreet = newConsultantDetails[6];//the attribute is set from the correct index from the array
			tempConsultant.town = newConsultantDetails[7];//the attribute is set from the correct index from the array
			tempConsultant.postcode = newConsultantDetails[8];//the attribute is set from the correct index from the array
			tempConsultant.contactNum = newConsultantDetails[9];//the attribute is set from the correct index from the array
			tempConsultant.nationality = newConsultantDetails[10];//the attribute is set from the correct index from the array
			tempConsultant.bloodType = newConsultantDetails[11];//the attribute is set from the correct index from the array
			tempConsultant.smoker = Integer.parseInt(newConsultantDetails[12]);//the attribute is set from the correct index from the array
			tempConsultant.drinker = Integer.parseInt(newConsultantDetails[13]);//the attribute is set from the correct index from the array			try
			try
			{
				tempConsultant.dob = ft.parse(newConsultantDetails[14]);//the attribute is set from the correct index from the array
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{
			}
			tempConsultant.religon = newConsultantDetails[15];//the attribute is set from the correct index from the array
			tempConsultant.allergies = newConsultantDetails[16];//the attribute is set from the correct index from the array
			tempConsultant.gender = newConsultantDetails[17];//the attribute is set from the correct index from the array
			tempConsultant.disability = newConsultantDetails[18];//the attribute is set from the correct index from the array
			if(tempConsultant.disability!=null)
			{
			tempConsultant.disabled = false;	
			}
			tempConsultant.carer = Boolean.parseBoolean(newConsultantDetails[19]);//the attribute is set from the correct index from the array
			tempConsultant.translator = Boolean.parseBoolean(newConsultantDetails[20]);//the attribute is set from the correct index from the array
			tempConsultant.sex = newConsultantDetails[21];//the attribute is set from the correct index from the array
			tempConsultant.county = newConsultantDetails[22];//the attribute is set from the correct index from the array
			try
			{
				tempConsultant.daysSinceLastUpdate = ft.parse(newConsultantDetails[23]);//the attribute is set from the correct index from the array
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{
			}
			tempConsultant.wage = Double.parseDouble(newConsultantDetails[24]);//the attribute is set from the correct index from the array
			tempConsultant.hoursPerWeek = Integer.parseInt(newConsultantDetails[25]);//the attribute is set from the correct index from the array
			tempConsultant.archived = Boolean.parseBoolean(newConsultantDetails[26]);//the attribute is set from the correct index from the array
			//consultant specific info
			String[] newConsultantDetailsSpecific = fileContents[1].split(",");
			if(newConsultantDetailsSpecific[0]!="Null")
			{
				//System.out.println("Here wserwerwerwer "+newConsultantDetailsSpecific[0]);
				tempConsultant.expertiese = newConsultantDetailsSpecific[0].split("#");
				tempConsultant.numberOfPatients = Integer.parseInt(newConsultantDetailsSpecific[1]);//the attribute is set from the correct index from the array
				tempConsultant.wardLocatedIn = newConsultantDetailsSpecific[2];//the attribute is set from the correct index from the array
			}
			else
			{
				//System.out.println("Empty");
			}
			tempConsultant.allergies = tempConsultant.allergies.replace("#", "\n");//replaces \n with # prevent new lines from being created
			tempConsultant.disability = tempConsultant.disability.replace("#", "\n");//replaces \n with # prevent new lines from being created
		}
		
		
		tempConsultant = tempConsultant.pullAdmissionsFromFile(tempConsultant);
		return(tempConsultant);
	}
	
	//Before this used to be part of the original initalise method however as the exact process was needed somewhere else it was modularised 
	//What this does is produce a list of all the patients the consultant can view along with their details (to view in the demographic)
	//Along with this the admissionsare also assigned to an array so every available admission is also there
	//These will get read from file so will be the latest data
	//The arrays get attached to the instance overwritting any old values and the original instance is passed back
	public Consultant pullAdmissionsFromFile(Consultant consultant)
	{
		String[] fileContents = rffReturnFullFile(consultant.consultantID+"_file.txt");//concatinates the string into an array
		consultant.consultantPatientList = new String[consultant.numberOfPatients];
		consultant.concatenatedconsultantPatientList = new String[consultant.numberOfPatients];
		int positionOfPatient=0;
		for(int counter = 2;counter<fileContents.length;counter++)
		{
			//System.out.println("Next patient");
			if(fileContents[counter].substring(0,1).equals("P"))//if we get a patient
			{
				//System.out.println("found a patient");
				//System.out.println("PatientID "+fileContents[counter].substring(0,11));
				consultant.consultantPatientList[positionOfPatient]=fileContents[counter].substring(0,11);
				//pulls the contents of the stringed admission 
				consultant.concatenatedconsultantPatientList[positionOfPatient]=fileContents[counter].substring(12,fileContents[counter].length());
				//System.out.println("Admissions include: " +consultant.concatenatedconsultantPatientList[positionOfPatient]);
				positionOfPatient++;
			}
		}
		consultant = retrieveBookingsFromAllPatients(consultant);
	return(consultant);
	}
	
	//For the consultant this is a hefty program
	//First using the instance of the consultant we read the file to get an accurate vlaue
	//starting at the first availbe line for patients we start.
	//here we add every instance of every boking the consutant has where each outer index is a pateient
	//using the Admission IDs for the booking the system then actually retrieves the instances
	
	//here we then perform the hashing algorithm, here we set up a search looking for all the bookings that occur for todays date
	//we then add these values to an array, which uses a basic equation to insert 18 indexes equally between the times of 9 and 6. 
	//The system also converts the time into an integer value for the hashing
	public Consultant retrieveBookingsFromAllPatients(Consultant consultant)
	{
		consultant.listOfBookings = null;
		consultant.listOfBookings = new Booking[consultant.numberOfPatients][];
		String[] fileContents = rffReturnFullFile(consultant.consultantID+"_file.txt");//concatinates the string into an array
		int positionOfPatient=0;
		for(int counter = 2;counter<fileContents.length;counter++)
		{
			//System.out.println("Next patient");
			if(fileContents[counter].substring(0,1).equals("P"))//if we get a patient
			{
				//System.out.println("found a patient");
				//System.out.println("PatientID "+fileContents[counter].substring(0,11));
				
				//All this here creates a jagged multidimensional array
				//It will hold every appointment the consultant will have
				//Every inner array will have a direct corrospondnce to the index of the same patient avaialbe to the consultant
				//i.e if the consultant chooses index 2 on the patient select panel (These will be assoicated to that patient)
				//TO prevent having null booking arrays an empty string value is 
					if(counter==fileContents.length-1)//reachedLastIndex
					{
						//System.out.println("Empty booking created");
						Booking[] bookingList = new Booking[1];
						Booking tempBooking=new Booking();
						tempBooking.room = "null";
						bookingList[0]=tempBooking;
						consultant.listOfBookings[positionOfPatient]=bookingList;
					}
					else
					{
						int innerCounter = counter+1;//index bookings would start
						int numberOfBookings= 0;//numberofbookings on the system
						//For ever line a booking exists will iterate until we reach a document or admission
						//Here we are just counting the bookings each patient has on the system
						WhileLoop:
						while(fileContents[innerCounter].substring(0,1).equals("B")==true)
						{
							////System.out.println("Condition met");//indicates a booking has been found
							numberOfBookings++;
							innerCounter++;//iterates index
							if(innerCounter==fileContents.length)//either the next value is a new patient (Loop stops) or we have to invoke this as this avoids outofBounds exceptions
							{
								break WhileLoop;//escapes loop
							}
						}
						if(numberOfBookings==0)//avoids null pointers if no bookings for a patient//will assign the patient's index a blank booking with the only value in room
						{
							//System.out.println("Empty booking created");
							Booking[] bookingList = new Booking[1];
							Booking tempBooking=new Booking();
							tempBooking.room = "null";//inserts value indicating that the patient has no bookings, but avoids a null pointer exception
							bookingList[0]=tempBooking;
							consultant.listOfBookings[positionOfPatient]=bookingList;
						}
						else if(numberOfBookings>0)//avoids null pointers if no bookings
						{
							//creates a list of admisisons with the correct length	
							Booking[] bookingList = new Booking[numberOfBookings];
							innerCounter = counter+1;//sets the start point in which the bookings will be added
							for(int innerCount = 0;innerCount<bookingList.length;innerCount++)//Will iterate for the number of bookings the pateint has(which are the same admissions avaible to the consultant)
							{
								Booking newBooking = new Booking();//new instance of booking created
								bookingList[innerCount] = newBooking.retrieveBooking(fileContents[innerCounter].split(","));//Reads the booking from file to the consultant's list of bookings 
								bookingList[innerCount].bookingPatientID= fileContents[counter].substring(0,11);
								innerCounter++;
							}
							consultant.listOfBookings[positionOfPatient]=bookingList;//Adds the list of patient bookings to the multidimensional Array
							//Each iteration checks each patient
						}
					}
				positionOfPatient++;
			}
		}
		
		//Here we are selecting bookings that have todays date
		Date todaysDateEarly = new Date();
		Date todaysDateLate = new Date();
		Booking[] todaysAppointmentsUnOrdered = new Booking[20];

		try{
		todaysDateEarly = ftTimeInc.parse(ft.format(todaysDateEarly)+" " + "01:00");//Sets date to the current date and 1am(time which isnt optional for appointment anything can come after it)
		todaysDateLate = ftTimeInc.parse(ft.format(todaysDateLate)+" " +"21:00");//Sets date to the current date and 1am(time which isnt optional for appointment anything can come after it)
		////System.out.println(ftTimeInc.format(todaysDateEarly)+" Current Date am");
		////System.out.println(ftTimeInc.format(todaysDateLate)+" Current Date am");
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
		}
		int startingBookingIndex = 0;//Just an artifical pointer the positions will be hashed by daterather than a numerical pointer
		//Here would be the best time to assign the current dates admissions, here we should assign all of the current date's appointments
		//Adding them in here with no order at first then immediatley order them(insertion as a fiar number of dates)
			for(int counterBookingsOuter = 0;counterBookingsOuter<consultant.listOfBookings.length;counterBookingsOuter++)
			{
				//Runs for each patient
				for(int counterBookingsinner = 0;counterBookingsinner<consultant.listOfBookings[counterBookingsOuter].length;counterBookingsinner++)
				{
					//Runs for each appointment the consultant has with the patient (for different admissions)
					if((consultant.listOfBookings[counterBookingsOuter][counterBookingsinner].room.equals("null"))==false)//will output if the admission was not null
					{
						Date patientsBookingDate = new Date();//creates a concatenated date for the patient with both the time and date in one date attribute
						try{
						patientsBookingDate = ftTimeInc.parse(ft.format(consultant.listOfBookings[counterBookingsOuter][counterBookingsinner].dateOfNextApp)+" " + timeft.format(consultant.listOfBookings[counterBookingsOuter][counterBookingsinner].timeOfNextApp));//Sets date to the current date and 1am(time which isnt optional for appointment anything can come after it)
						////System.out.println(ftTimeInc.format(patientsBookingDate)+" Patients appointment date");
						}
						catch(Exception exc)
						{
						}
						if((patientsBookingDate.compareTo(todaysDateLate)<0)&&(patientsBookingDate.compareTo(todaysDateEarly)>0))//date comparison first statement to determine if it is after todays day
						{						
							//System.out.println("Appointment found for today at time "+consultant.listOfBookings[counterBookingsOuter][counterBookingsinner].timeOfNextApp);//Will output a booking is found
							todaysAppointmentsUnOrdered[startingBookingIndex] = consultant.listOfBookings[counterBookingsOuter][counterBookingsinner];//Adds booking instance to array(unordered)
							startingBookingIndex++;
						}
					}
					else//indexes that have no booking
					{
						////System.out.println("AT patient index: "+counterBookingsOuter+" Has no bookings at all");
						////System.out.println("No booking found for today");
						
					}
				}
			}
			//when this is called we will wipe any old bookings in the lsit
			for(int tempcounterBookingIndex =0;tempcounterBookingIndex<consultant.todaysAppointments.length;tempcounterBookingIndex++)
			{ 
				consultant.todaysAppointments[tempcounterBookingIndex]=null;
			}
			//now that we have the organised list of appointments we need to hash all the bookings into the correct location
			//This will be done using a hashing algorithm
			double tempIndexComparisonValue = -1.00;//declares a tempIndex value from the time i.e 8:30 +> 8.5
			for(int counterBookingIndex =0;counterBookingIndex<startingBookingIndex;counterBookingIndex++)
			{ 
				
				try{
					//converts bookings date into a string
					String currentBookingStrTime =  timeft.format(todaysAppointmentsUnOrdered[counterBookingIndex].timeOfNextApp);
					//attempts to convert the date into a double i.e 08=>0.8,
					tempIndexComparisonValue = Double.parseDouble(currentBookingStrTime.substring(0,2));
				
					//this accounts for the 30 mins
					////System.out.println(currentBookingStrTime.substring(3,5)+" CONTENTS");
					int halfPastTimeCheck = Integer.parseInt(currentBookingStrTime.substring(3,5));
					if(halfPastTimeCheck==31)//The appointment is for half past blank
					{
						tempIndexComparisonValue = tempIndexComparisonValue+0.5;//will correctly adjust for the index
					}
					////System.out.println(todaysAppointmentsUnOrdered[counterBookingIndex].timeOfNextApp+" has a decimal equivlent of "+tempIndexComparisonValue);
					double doubleindex = ((tempIndexComparisonValue-8.00)/0.5);//converts the decimal into the correct index just as a double so needs converting
					////System.out.println("Double value of "+doubleindex);
					String indexForBookingSTR =(doubleindex+"").substring(0,(doubleindex+"").length()-2);//converts to string and removes the .0 or .5 from it
					int indexForBooking =Integer.parseInt(indexForBookingSTR);//converts it to int
					////System.out.println("index "+indexForBooking);
					consultant.todaysAppointments[indexForBooking]=todaysAppointmentsUnOrdered[counterBookingIndex];//adds correct booking to timeslot
				}
				catch(Exception exc){exc.printStackTrace();}
			//	todaysAppointments
				
			}
	
	return(consultant);
	}
	
	
	//While the GUI class retrieves the new written data and updates the instance if not saved to file it wont be there next time the system is ran
	//This method will always write whatever the current instance is to its rightful file allowing anytype of ammendment to be made without the need to call a laborious set of methods
	//Using the instances as a parameter the details are then prepaired to get written to file (Any arays get concatenated)
	//Any large strings get theri new lines replaced with "tokens" which wont screw up the file writing
	//finally the details are concatenated and then passed through to the file writing method which actually writes it to file
	public void updateDemographicDetails(Consultant consultant)
	{
		Date currentTime = new Date();//declares the date of the closest appointment
		consultant.daysSinceLastUpdate=currentTime;//updates the patients time to the current time
		consultant.allergies = consultant.allergies.replace("\n", "#");//replaces \n with # prevent new lines from being created
		consultant.disability = consultant.disability.replace("\n", "#");//replaces \n with # prevent new lines from being created
		String ConsultantDemo=consultant.consultantID+","+consultant.employeeID+","+consultant.password+","+consultant.surName+","+consultant.firstName+","+consultant.addressHouseNum+","+consultant.addressHouseStreet+","+consultant.town+","+consultant.postcode+","+consultant.contactNum+","+consultant.nationality+","+consultant.bloodType+","+consultant.smoker+","+consultant.drinker+","+ft.format(consultant.dob)+","+consultant.religon+","+consultant.allergies+","+consultant.gender+","+consultant.disability+","+consultant.carer+","+consultant.translator+","+consultant.sex+","+consultant.county+","+ft.format(consultant.daysSinceLastUpdate)+","+consultant.wage+","+consultant.hoursPerWeek+","+consultant.archived;//concatenates all the attributes togeher
		ConsultantList cl = new ConsultantList();//creates a new instance of the class
		cl.updateDemographicfile(0,consultant.consultantID+"_file.txt",ConsultantDemo);//writes entire array to file
	}
	//Similar to the method which updates the demographic this is used to update the consultants speciaised info like the number of admissions
	//all it does is take the desired consultant as input and then concatenates all the desired data
	//After this the info is then passed to the file writer method which writes the new data to line 2 which is  a standardised line so i dont have to worry
	public void updateConsultantAdvancedInfo(Consultant consultant)
	{
		String practises =consultant.expertiese[0];//just assigns an intial value
		for(int counter = 1 ; counter<consultant.expertiese.length;counter++)//runs a for loop iterating through the rest of the indexes of the desiplines
		{
			practises =practises+"\n"+consultant.expertiese[counter];//concatenates values with previous statements 
		}
		practises = practises.replace("\n", "#");//replaces \n with # prevent new lines from being created

		String ConsultantSpec =practises+","+consultant.numberOfPatients+","+consultant.wardLocatedIn;//concatenates all attributes which will be written to file
		ConsultantList cl = new ConsultantList();//creates a new instance of the class
		cl.updateDemographicfile(1,consultant.consultantID+"_file.txt",ConsultantSpec);//writes entire array to file
		
	}
	
	//Probably the most important admission pulling method, this will take in a list of admission IDs along with the patient associated with it and then find the admission decalre an instance of it and then assign it to the availbe admisisons to the system
	//This is only needed for the consultant entity as the patient will see all thier admisions to them
	public Admission[] pullAdmissions(String[] listOfAdmissionsToPull,Patient tempPatient)
	{
		Admission[] listOfAdmissionsLocal = new Admission[listOfAdmissionsToPull.length];//declares the array with the size of admissions which are needed
		//Two for loops are utilised so that all admissions needed can get searched against the entierty of the admissions available
		for(int counter = 0;counter<listOfAdmissionsToPull.length;counter++)//runs a for loop that will iterate through every admission that is desired 
		{
			////System.out.println(listOfAdmissionsToPull[counter]+"is needed");
			for(int innercounter = 0;innercounter<tempPatient.listOfAdmissions.length;innercounter++)//runs a for loop that will iterate through every admission the patient has
			{
				if(tempPatient.listOfAdmissions[innercounter].admissionID.equals(listOfAdmissionsToPull[counter]))//Checks to see if the current patient admission is the same as the desired one from the consultant
				{
					////System.out.println(tempPatient.listOfAdmissions[innercounter].admissionID+" was found");
					listOfAdmissionsLocal[counter]=tempPatient.listOfAdmissions[innercounter];//adds admision to array
				}
			}
		}
		return(listOfAdmissionsLocal);//retuns admission
	}

	//This method will be used to validate every consultant on the system, it will be used to validate any field entered by the user on the system
	//If any field is wrong the system will retuern false to the user and will display why it is wrong
	//it works by validating every field on the entity that needs to it will perform a range of validations on the data as specified in the design document
	//the purpose is to identify any erroneous inputs made by the user whether intended or not
	public boolean validateconsultantInput(Consultant consultant)
	{
		boolean validated = true;
		
		//firstname 
		validated=presenceValidation(consultant.firstName);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Firstname, missing.");
			return false;
		}
		validated=typeValidationString(consultant.firstName);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Firstname, letters only.");
			return false;
		}
		validated=consultant.lesserLengthValidation(consultant.firstName,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Firstname, must be less than 26 characters.");
			return false;
		}
		
		//surname
		//presence/type check/length
		validated=consultant.presenceValidation(consultant.surName);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Surname, missing.");
			return false;
		}
		validated=consultant.typeValidationString(consultant.surName);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Surname, letters only.");
			return false;
		}
		validated=consultant.greaterLengthValidation(consultant.surName,2);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Surname, must be three characters.");
			return false;
		}
		validated=consultant.lesserLengthValidation(consultant.surName,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Surname, must be less than 26 characters.");
			return false;
		}
		
		//postcode
		//length i.e missing gap
		validated=consultant.equalsualLengthValidation(consultant.postcode,7);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Postcode, must be 7 characters.");
			return false;
		}
		
		//house number
		//presnce/dataTYPE INT
		validated=consultant.presenceValidation(consultant.addressHouseNum+"");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid house number, missing.");
			return false;
		}
		
		//street
		//presnce datatype String
		validated=consultant.presenceValidation(consultant.addressHouseStreet);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid street name, missing.");
			return false;
		}
		validated=consultant.typeValidationString(consultant.addressHouseStreet);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid street name, letters only.");
			return false;
		}
		validated=consultant.lesserLengthValidation(consultant.addressHouseStreet,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid street name, must be less than 26 characters.");
			return false;
		}
		//town
		//presence datatype
		validated=consultant.presenceValidation(consultant.town);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid town, missing.");
			return false;
		}
		validated=consultant.typeValidationString(consultant.town);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid town, letters only.");
			return false;
		}
		validated=consultant.lesserLengthValidation(consultant.town,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid town, must be less than 26 characters.");
			return false;
		}
		//county
		//presence datatype
		validated=consultant.presenceValidation(consultant.county);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid county, missing.");
			return false;
		}
		validated=consultant.typeValidationString(consultant.county);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid county, letters only.");
			return false;
		}
		validated=consultant.lesserLengthValidation(consultant.county,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid county, must be less than 26 characters.");
			return false;
		}
		//contact number
		validated=consultant.equalsualLengthValidation(consultant.contactNum,11);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid contact number, not 11 digigts.");
			return false;
		}
		validated=consultant.typeValidationInt(consultant.contactNum);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid contact number, not int.");
			return false;
		}
		
		//nationality
		//presence datatype 
		validated=consultant.presenceValidation(consultant.nationality);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid nationality, missing.");
			return false;
		}
		validated=consultant.typeValidationString(consultant.nationality);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid nationality, letters only.");
			return false;
		}
		validated=consultant.lesserLengthValidation(consultant.nationality,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid nationality, must be less than 26 characters.");
			return false;
		}
		//religon
		//presence datatype
		validated=consultant.presenceValidation(consultant.religon);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid religon, missing.");
			return false;
		}
		validated=consultant.typeValidationString(consultant.religon);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid religon, letters only.");
			return false;
		}
		validated=consultant.lesserLengthValidation(consultant.religon,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid religon, must be less than 26 characters.");
			return false;
		}
		//gender
		if(consultant.gender.equals("Please select the gender you identify as."))
		{
			JOptionPane.showMessageDialog(null, "please select a Gender.");
			return false;
		}
		
		//sex
		if(consultant.sex.equals("Please select the sex you are"))
		{
			JOptionPane.showMessageDialog(null, "please select a sex.");
			return false;
		}
		
		//bloodtype
		if(consultant.bloodType.equals("Please select a Blood Type."))
		{
			JOptionPane.showMessageDialog(null, "please select a bloodtype.");
			return false;
		}
		//disiblites
		validated=consultant.checkCharIsNOTpresent(consultant.disability,"#");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid character used, #.");
			return false;
		}
		validated=consultant.checkCharIsNOTpresent(consultant.disability,",");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid character used, ,.");
			return false;
		}
		validated=consultant.lesserLengthValidation(consultant.disability,100);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid text, must be less than 101 characters.");
			return false;
		}
		
		//allergies
		validated=consultant.checkCharIsNOTpresent(consultant.allergies,"#");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid character used, #.");
			return false;
		}
		validated=consultant.checkCharIsNOTpresent(consultant.allergies,",");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid character used, ,.");
			return false;
		}
		validated=consultant.lesserLengthValidation(consultant.allergies,100);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid text, must be less than 101 characters.");
			return false;
		}
		validated=consultant.presenceValidation(consultant.allergies);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Allergies, missing.");
			return false;
		}
		
		return true;
	}

}