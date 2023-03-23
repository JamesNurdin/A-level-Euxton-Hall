import java.util.*;//imports the java utlities module	
import java.io.*;//imports the io package
import java.text.SimpleDateFormat;//imports the simple date format package which allows for more simple dates
import javax.swing.*;//imports the java swing package,by using a wildcard all aspects of the package are imported

public class Patient extends User 
{
	//======================== Date formatting attributes ===================
	
	SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");//declares a simple font for a visual improvement over the full version
	SimpleDateFormat timeft = new SimpleDateFormat ("kk:mm");//declares a simple font for a visual improvement over the full version
	SimpleDateFormat ftTimeInc = new SimpleDateFormat ("dd/MM/yyyy kk:mm");//declares a simple font for a visual improvement over the full version

	//======================== Entity Attributes ===================
	
	String patientID;//declares attribute which holds their patientID
	String linkedStaffID;
	String nationality;//declares attribute which holds their nationality
	int numberOfAdmissions;//declares attribute which holds their numberOfAdmissions
	int numberOfNotifications;//declares attribute which holds their numberOfNotifications 
	String[] notifications;//declares an array for their notifications
	Admission[] listOfAdmissions;
	Booking mostRecentbooking;
	
	
	//Method which retreieves the patient from file
	//first step is the pulling of details from the actuall file(using the id as a parameter the full contents are pulled, eventually admissions will be pulled from this method also)
	//Next a new array holding all the values from the first line is called, splits every user defined "sub index" indicated by a comma or # if a "sub sub index of an index etc"
	//a new instance is called and all the attributes are assigned to the correct variable for the object
	//To avoid formatting errors when writing to file similar to the process of encryption any variable that uses \n is replaced by a # and vice versa for the opposing action
	//Any array variables have their attributes also assigned
	//Finally instance is then returned
	public Patient retrievePatient(String patientIDLocal)
	{
			SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");

		String[] fileContents = rffReturnFullFile(patientIDLocal+"_file.txt");//concatinates the string into an array
		String[] newPatientDetails = fileContents[0].split(",");
		String[] newPatientNotifications = fileContents[1].split(",");
		Patient tempPatient = new Patient();//creates a new instance of the class
		
		//demographic
		if(newPatientDetails[0]!="Null")
		{
			tempPatient.patientID = newPatientDetails[0];//the attribute is set from the correct index from the array
			tempPatient.password = newPatientDetails[1];//the attribute is set from the correct index from the array
			tempPatient.surName = newPatientDetails[2];//the attribute is set from the correct index from the array
			tempPatient.firstName = newPatientDetails[3];//the attribute is set from the correct index from the array
			tempPatient.addressHouseNum = Integer.parseInt(newPatientDetails[4]);//the attribute is set from the correct index from the array
			tempPatient.addressHouseStreet = newPatientDetails[5];//the attribute is set from the correct index from the array
			tempPatient.town = newPatientDetails[6];//the attribute is set from the correct index from the array
			tempPatient.postcode = newPatientDetails[7];//the attribute is set from the correct index from the array
			tempPatient.contactNum = newPatientDetails[8];//the attribute is set from the correct index from the array
			tempPatient.nationality = newPatientDetails[9];//the attribute is set from the correct index from the array
			tempPatient.bloodType = newPatientDetails[10];//the attribute is set from the correct index from the array
			tempPatient.smoker = Integer.parseInt(newPatientDetails[11]);//the attribute is set from the correct index from the array
			tempPatient.drinker = Integer.parseInt(newPatientDetails[12]);//the attribute is set from the correct index from the array
			tempPatient.numberOfAdmissions = Integer.parseInt(newPatientDetails[13]);//the attribute is set from the correct index from the array
			try
			{
				tempPatient.dob = ft.parse(newPatientDetails[14]);//the attribute is set from the correct index from the array
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{
			}
			tempPatient.religon = newPatientDetails[15];//the attribute is set from the correct index from the array
			tempPatient.allergies = newPatientDetails[16];//the attribute is set from the correct index from the array
			tempPatient.gender = newPatientDetails[17];//the attribute is set from the correct index from the array
			tempPatient.disability = newPatientDetails[18];//the attribute is set from the correct index from the array
			if(tempPatient.disability!=null)
			{
			tempPatient.disabled = true;	
			}
			tempPatient.carer = Boolean.parseBoolean(newPatientDetails[19]);//the attribute is set from the correct index from the array
			tempPatient.translator = Boolean.parseBoolean(newPatientDetails[20]);//the attribute is set from the correct index from the array
			tempPatient.sex = newPatientDetails[21];//the attribute is set from the correct index from the array
			tempPatient.county = newPatientDetails[22];//the attribute is set from the correct index from the array
			try
			{
				tempPatient.daysSinceLastUpdate = ft.parse(newPatientDetails[23]);//the attribute is set from the correct index from the array
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{
				//System.out.println("Patient reading error");
			}
			tempPatient.linkedStaffID = newPatientDetails[24];//the attribute is set from the correct index from the array
	
		}
		tempPatient.allergies = tempPatient.allergies.replace("#", "\n");//replaces \n with # prevent new lines from being created
		tempPatient.disability = tempPatient.disability.replace("#", "\n");//replaces \n with # prevent new lines from being created
		tempPatient.allergies = tempPatient.allergies.replace("@", ",");//replaces \n with # prevent new lines from being created
		tempPatient.disability = tempPatient.disability.replace("@", ",");//replaces \n with # prevent new lines from being created
		//admission and booking
		if(tempPatient.numberOfAdmissions!=0)
		{
			////System.out.print("Got here");
			//before new account is created admissions are also pulled to the account
			Admission tempAdmission = new Admission();//temp instance created
			//Here an array of indexes are pulled from the patients account here all the lines which posses an admission are pulled with their indexes being stored here
			int indexesOfAdmissions[] = tempAdmission.retrieveIndexesAdmissionsOccur(fileContents,tempPatient.numberOfAdmissions);//indexes in the file where admissions are stored are held here
			////System.out.print("Got here 2");
			tempPatient.listOfAdmissions = new Admission[tempPatient.numberOfAdmissions];
			AdmissionList al = new AdmissionList();
			for(int counter = 0;counter<tempPatient.numberOfAdmissions;counter++)
			{
				//here an buffer instance of admission is created with the line of the file containing the admission  as a string being passed in
				tempAdmission = al.retrieveAdmission(indexesOfAdmissions[counter],fileContents);
				tempPatient.listOfAdmissions[counter] = tempAdmission;
				////System.out.println(tempPatient.listOfAdmissions[counter].admissionID);
				
			}
				tempPatient = retrieveEarliestBooking(tempPatient);
		}
		else
		{
			tempPatient.listOfAdmissions = new Admission[0];
			Booking tempBookingNull = new Booking();
			tempBookingNull.room = "Null";
			tempPatient.mostRecentbooking=tempBookingNull;
		}
		
		//System.out.println(newPatientNotifications[0]+" NUMBER OF NOTIFICATIONS");
		tempPatient.numberOfNotifications= Integer.parseInt(newPatientNotifications[0]);//the attribute is set from the correct index from the array
		tempPatient.notifications= new String[tempPatient.numberOfNotifications];
		for(int counterForNontifications = 0;counterForNontifications<tempPatient.numberOfNotifications;counterForNontifications++)
		{
			//System.out.println(tempPatient.notifications.length);
			//System.out.println(newPatientNotifications.length);
			tempPatient.notifications[counterForNontifications]=newPatientNotifications[counterForNontifications+1];
			//System.out.println("Notification "+counterForNontifications+" "+tempPatient.notifications[counterForNontifications]);
		}
		return(tempPatient);
	}
	
	
	//This method like the main one will return an instance of the patient, however this differs as it will have null values for certain variables 
	//these may be because it is unecessary or off limits
	public Patient retreivePatientBasicInfo(String patientIDLocal)
	{
			SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");

		String[] fileContents = rffReturnFullFile(patientIDLocal+"_file.txt");//concatinates the string into an array
		String[] newPatientDetails = fileContents[0].split(",");
		Patient tempPatient = new Patient();//creates a new instance of the class
		
		//demographic
		if(newPatientDetails[0]!="Null")
		{
			tempPatient.patientID = newPatientDetails[0];//the attribute is set from the correct index from the array
			tempPatient.surName = newPatientDetails[2];//the attribute is set from the correct index from the array
			tempPatient.firstName = newPatientDetails[3];//the attribute is set from the correct index from the array
			tempPatient.postcode = newPatientDetails[7];//the attribute is set from the correct index from the array
			try
			{
				tempPatient.dob = ft.parse(newPatientDetails[14]);//the attribute is set from the correct index from the array
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{
			}
			tempPatient.gender = newPatientDetails[17];//the attribute is set from the correct index from the array
			tempPatient.numberOfAdmissions = Integer.parseInt(newPatientDetails[13]);//the attribute is set from the correct index from the array
			//System.out.println("Basic patient details retrieved:"+tempPatient.patientID+","+tempPatient.surName+","+tempPatient.firstName+","+tempPatient.postcode);
		}
	return(tempPatient);
	}
	
	//This method will be used to validate every patient on the system, it will be used to validate any field entered by the user on the system
	//If any field is wrong the system will retuern false to the user and will display why it is wrong
	//it works by validating every field on the entity that needs to it will perform a range of validations on the data as specified in the design document
	//the purpose is to identify any erroneous inputs made by the user whether intended or not
	public boolean validatePatientInput(Patient patient)
	{
		boolean validated = true;
		
		//firstname 
		validated=presenceValidation(patient.firstName);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Firstname, missing.");
			return false;
		}
		validated=typeValidationString(patient.firstName);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Firstname, letters only.");
			return false;
		}
		validated=patient.lesserLengthValidation(patient.firstName,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Firstname, must be less than 26 characters.");
			return false;
		}
		
		//surname
		//presence/type check/length
		validated=patient.presenceValidation(patient.surName);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Surname, missing.");
			return false;
		}
		validated=patient.typeValidationString(patient.surName);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Surname, letters only.");
			return false;
		}
		validated=patient.greaterLengthValidation(patient.surName,2);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Surname, must be three characters.");
			return false;
		}
		validated=patient.lesserLengthValidation(patient.surName,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Surname, must be less than 26 characters.");
			return false;
		}
		
		//postcode
		//length i.e missing gap
		validated=patient.equalsualLengthValidation(patient.postcode,7);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Postcode, must be 7 characters.");
			return false;
		}
		
		//house number
		//presnce/dataTYPE INT
		validated=patient.presenceValidation(patient.addressHouseNum+"");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid house number, missing.");
			return false;
		}
		
		//street
		//presnce datatype String
		validated=patient.presenceValidation(patient.addressHouseStreet);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid street name, missing.");
			return false;
		}
		validated=patient.typeValidationString(patient.addressHouseStreet);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid street name, letters only.");
			return false;
		}
		validated=patient.lesserLengthValidation(patient.addressHouseStreet,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid street name, must be less than 26 characters.");
			return false;
		}
		//town
		//presence datatype
		validated=patient.presenceValidation(patient.town);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid town, missing.");
			return false;
		}
		validated=patient.typeValidationString(patient.town);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid town, letters only.");
			return false;
		}
		validated=patient.lesserLengthValidation(patient.town,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid town, must be less than 26 characters.");
			return false;
		}
		//county
		//presence datatype
		validated=patient.presenceValidation(patient.county);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid county, missing.");
			return false;
		}
		validated=patient.typeValidationString(patient.county);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid county, letters only.");
			return false;
		}
		validated=patient.lesserLengthValidation(patient.county,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid county, must be less than 26 characters.");
			return false;
		}
		//contact number
		validated=patient.equalsualLengthValidation(patient.contactNum,11);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid contact number, not 11 digigts.");
			return false;
		}
		validated=patient.typeValidationInt(patient.contactNum);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid contact number, not int.");
			return false;
		}
		
		//nationality
		//presence datatype 
		validated=patient.presenceValidation(patient.nationality);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid nationality, missing.");
			return false;
		}
		validated=patient.typeValidationString(patient.nationality);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid nationality, letters only.");
			return false;
		}
		validated=patient.lesserLengthValidation(patient.nationality,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid nationality, must be less than 26 characters.");
			return false;
		}
		//religon
		//presence datatype
		validated=patient.presenceValidation(patient.religon);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid religon, missing.");
			return false;
		}
		validated=patient.typeValidationString(patient.religon);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid religon, letters only.");
			return false;
		}
		validated=patient.lesserLengthValidation(patient.religon,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid religon, must be less than 26 characters.");
			return false;
		}
		//gender
		if(patient.gender.equals("Please select the gender you identify as."))
		{
			JOptionPane.showMessageDialog(null, "please select a Gender.");
			return false;
		}
		
		//sex
		if(patient.sex.equals("Please select the sex you are"))
		{
			JOptionPane.showMessageDialog(null, "please select a sex.");
			return false;
		}
		
		//bloodtype
		if(patient.bloodType.equals("Please select a Blood Type."))
		{
			JOptionPane.showMessageDialog(null, "please select a bloodtype.");
			return false;
		}
		//disiblites
		validated=patient.checkCharIsNOTpresent(patient.disability,"#");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid character used, #.");
			return false;
		}
		validated=patient.checkCharIsNOTpresent(patient.disability,",");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid character used, ,.");
			return false;
		}
		validated=patient.lesserLengthValidation(patient.disability,100);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid text, must be less than 101 characters.");
			return false;
		}
		
		//allergies
		validated=patient.checkCharIsNOTpresent(patient.allergies,"#");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid character used, #.");
			return false;
		}
		validated=patient.checkCharIsNOTpresent(patient.allergies,",");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid character used, ,.");
			return false;
		}
		validated=patient.lesserLengthValidation(patient.allergies,100);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid text, must be less than 101 characters.");
			return false;
		}
		validated=patient.presenceValidation(patient.allergies);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Allergies, missing.");
			return false;
		}
		
		return true;
	}
	
	//While the GUI class retrieves the new written data and updates the instance if not saved to file it wont be there next time the system is ran
	//This method will always write whatever the current instance is to its rightful file allowing anytype of ammendment to be made without the need to call a laborious set of methods
	//Using the instances as a parameter the details are then prepaired to get written to file (Any arays get concatenated)
	//Any large strings get theri new lines replaced with "tokens" which wont screw up the file writing
	//finally the details are concatenated and then passed through to the file writing method which actually writes it to file
	public void updateDemographicDetails(Patient patient)
	{
		Date currentTime = new Date();//declares the date of the closest appointment
		patient.daysSinceLastUpdate=currentTime;//updates the patients time to the current time
		patient.allergies = patient.allergies.replace("\n", "#");//replaces \n with # prevent new lines from being created
		patient.disability = patient.disability.replace("\n", "#");//replaces \n with # prevent new lines from being created
		patient.allergies = patient.allergies.replace(",", "@");//replaces \n with # prevent new lines from being created
		patient.disability = patient.disability.replace(",", "@");//replaces \n with # prevent new lines from being created
		String patientDemo=patient.patientID+","+patient.password+","+patient.surName+","+patient.firstName+","+patient.addressHouseNum+","+patient.addressHouseStreet+","+patient.town+","+patient.postcode+","+patient.contactNum+","+patient.nationality+","+patient.bloodType+","+patient.smoker+","+patient.drinker+","+patient.numberOfAdmissions+","+ft.format(patient.dob)+","+patient.religon+","+patient.allergies+","+patient.gender+","+patient.disability+","+patient.carer+","+patient.translator+","+patient.sex+","+patient.county+","+ft.format(patient.daysSinceLastUpdate)+","+patient.linkedStaffID;//concatenates all the attributes togeher
		PatientList pl = new PatientList();//creates a new instance of the class
		pl.updateDemographicfile(0,patient.patientID+"_file.txt",patientDemo);//writes entire array to file
		
		
	}
	
	//this method will be called at the intial patient intialiseation and will calculate the earliest booking for them that is active
	//it will increment through every booking on the system, it first checks that the current admission's booking is not null
	//then it finds the date of the booking
	//it checks that this date is upcoming and not an old booking.
	//next it checks that it is the date closest to days date//if so the new booking date is updated
	//if no bookings are made an empty instance is intialsed and then set so that the system recognises that the instance is null
	public Patient retrieveEarliestBooking(Patient tempPatient)
	{
			//Here we will find the most recent booking
		int numberOfBookingsindex = 0;
		Date avoidPastBookingDates = new Date();//sets threshold date (earliest)
		Date comparrsionDateForBooking = new Date();//date used to compare the further dates of bookings
		for(int counterBooking = 0;counterBooking<tempPatient.numberOfAdmissions;counterBooking++)//Will go through every admission
		{
			try//if a booking is present avoids errors being thrown (every admission should have a booking just that the instances attributes will be null)
			{
				if((tempPatient.listOfAdmissions[counterBooking].upComingBooking.dateOfNextApp ==null)==false);//only adds valid bookings to the array
				{

					Date admissionsBookingDate = ftTimeInc.parse(ft.format(tempPatient.listOfAdmissions[counterBooking].upComingBooking.dateOfNextApp)+" " + timeft.format(tempPatient.listOfAdmissions[counterBooking].upComingBooking.timeOfNextApp));//Sets date to the current date and 1am(time which isnt optional for appointment anything can come after it)
					//System.out.println("Got to inital date comparisson"+ admissionsBookingDate);

					if(avoidPastBookingDates.compareTo(admissionsBookingDate)<0)//checks date is upcoming not in the past
					{
						//tempPatient.mostRecentbooking.tempAdmissionPatient = new Admission();
						//BASLINE DATE(first availble instance)
						//assigns the first avialble date (not expired) to use as a time comparrsion(if earilier date found it will update)
						if(numberOfBookingsindex ==0)//checks that the first proper instance of the date 
						{
							////System.out.println("1");
							tempPatient.mostRecentbooking = tempPatient.listOfAdmissions[counterBooking].upComingBooking;
							////System.out.println("2");
							comparrsionDateForBooking = ftTimeInc.parse(ft.format(tempPatient.mostRecentbooking.dateOfNextApp)+" " + timeft.format(tempPatient.mostRecentbooking.timeOfNextApp));//Sets date to the current date and 1am(time which isnt optional for appointment anything can come after it)
							////System.out.println("3");
						}
						//ACTUAL COMPARRISON
						if(admissionsBookingDate.compareTo(comparrsionDateForBooking)<0)//checks to see if the date of the current admission is before the current eariliest one
						{
							//System.out.println("Earlier booking found");
							//UPDATES ERALIEST BOOKINGS
							tempPatient.mostRecentbooking = tempPatient.listOfAdmissions[counterBooking].upComingBooking;
							comparrsionDateForBooking = admissionsBookingDate;
						}
						if(admissionsBookingDate.compareTo(comparrsionDateForBooking)>0)//checks to see if the date of the current admission is before the current eariliest one
						{
							//System.out.println("Still after Earliest date");
						}
						numberOfBookingsindex++;
					}
				}
			}
			catch(Exception exc)
			{
				////System.out.println("NO booking found for admission: "+tempPatient.listOfAdmissions[counterBooking].admissionID);

			}
			
		}
		//IF NO BOOKINGS AT ALL FOUND
		if(numberOfBookingsindex==0)
		{
			Booking tempBookingNull = new Booking();
			tempBookingNull.room = "Null";
			tempPatient.mostRecentbooking=tempBookingNull;
		}
		else
		{
			////System.out.println("Current Admission booking is "+tempPatient.mostRecentbooking.bookingID);
		}
		return(tempPatient);
	}

	//if a notification is added to the patients account what happens is that the system concatenates all the admmisions together, including the number and then writes them to the first line of the pateitns account by calling the correct call method.
	//this will be used to delete notifications for the account
	public void updateNotifications(Patient patient)
	{
		//Concatenation of the notifications together
		String updatedNotifications= patient.numberOfNotifications+"";
		for(int counter =0;counter<patient.numberOfNotifications;counter++)
		{
			updatedNotifications=updatedNotifications+","+patient.notifications[counter];
		}
		PatientList pl = new PatientList();//creates a new instance of the class
		pl.updateDemographicfile(1,patient.patientID+"_file.txt",updatedNotifications);//writes entire array to file
		
	}

	//while the above method is used to delete this will create them so it needs a little more works
	//as the array is dynamic the array is intially exteneded to accomidate the new notification
	//after this the system correts the method by correctly moving the items into the correct indexes//next the otification is added to the bottom
	//finally we concatenate all the notifications and then write them to the 2nd line of the patients account
	public Patient createNewNotification(Patient patient,String newNotification)
	{
		patient.numberOfNotifications++;
		patient.notifications=extendArrayByOne(patient.notifications);
		//here we move the array all down by an index to make way for the new notification
		for(int countermove =patient.numberOfNotifications-1;countermove>0;countermove--)
		{
			patient.notifications[countermove]=patient.notifications[countermove-1];
		}
		//add new notification in
		patient.notifications[0]=newNotification;
		
		//Concatenation of the notifications together
		String updatedNotifications= patient.numberOfNotifications+"";
		for(int counter =0;counter<patient.numberOfNotifications;counter++)
		{
			updatedNotifications=updatedNotifications+","+patient.notifications[counter];
		}
		PatientList pl = new PatientList();//creates a new instance of the class
		pl.updateDemographicfile(1,patient.patientID+"_file.txt",updatedNotifications);//writes entire array to file
		//returns the instance as the patient wont have it in real time otherwise
		return patient;
	}
	
}