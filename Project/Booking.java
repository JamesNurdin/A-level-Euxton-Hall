import java.text.SimpleDateFormat;//imports the simple date format package which allows for more simple dates
import java.util.*;//imports the java utlities module
import javax.swing.*;//imports the java swing package,by using a wildcard all aspects of the package are imported
import java.time.LocalTime;
import java.time.LocalDate;
public class Booking extends Admission
{
	//======================== Entity Attributes ===================
	
	SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");//declares a simple font for a visual improvement over the full version
	SimpleDateFormat timeft = new SimpleDateFormat ("kk:mm");//declares a simple font for a visual improvement over the full version
	Date timeOfNextApp = new Date();
	Date dateOfNextApp = new Date();
	String room;
	String bookingAdmissionID;
	String bookingPatientID;
	boolean automaticBooking;
	String bookingID;
	Admission tempAdmissionPatient;
	boolean newBooking;
	String consultantID;
	
	
	
	//Once we get to the true validation method, we can go deeper into the booking instance and check each field
	//For the room the system performs the three standard checks to the field for strings
	//for the date we perform a presence checker aswell as for the time
	//if no errors are found the method will reach the end and return true allowing creation of the booking.
	public boolean validatebooking(Booking bookingToValidate)
	{
		boolean validated = true;
		validated=presenceValidation(bookingToValidate.room);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid room, missing.");
			return false;
		}
		validated=typeValidationStringOrInt(bookingToValidate.room);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid room, contains non digit or letter characters.");
			return false;
		}
		validated=lesserLengthValidation(bookingToValidate.room,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid room, must be less than 26 characters.");
			return false;
		}
		
		
		if(bookingToValidate.dateOfNextApp==null)
		{
			JOptionPane.showMessageDialog(null, "Date missing please enter a date.");
			return false;
		}
		
		if(bookingToValidate.timeOfNextApp==null)
		{
			JOptionPane.showMessageDialog(null, "Date missing please enter a time.");
			return false;
		}
		return validated;
	}
	
	
	
	
	
	//it is able to add to the patients file as it performs a linear seatch on all the admission indexes until it finds it
	//once it is found it increments the number of indexes in the array by 1 then inserts the booking
	//the file is then written to file
	public Booking createBookingAddToPatientFile(Patient currentPatient,Admission currentAdmission, Booking newBooking)
	{
		String concatenatedBooking=newBooking.bookingID+","+timeft.format(newBooking.timeOfNextApp)+","+ft.format(newBooking.dateOfNextApp)+","+automaticBooking+","+newBooking.room+","+newBooking.consultantID;
		int desiredIndex = -1;
		String[] fileContents =rffReturnFullFile(currentPatient.patientID+"_file.txt");//retruns the pateints fullFile
		//Returns an array of all the indexes every admission is located on the system
		int[] indexesAdmissionOcuursAt = currentAdmission.retrieveIndexesAdmissionsOccur(fileContents,currentPatient.numberOfAdmissions);
		Forloop: //search for the index the current admission is at on the file of the system
		for(int counter =0;counter<indexesAdmissionOcuursAt.length;counter++)
		{
			if(currentAdmission.admissionID.equals(fileContents[indexesAdmissionOcuursAt[counter]].substring(0,11)))
			{
				desiredIndex = indexesAdmissionOcuursAt[counter]+1;	//sets the index of the booking to be the immeidate position after
				break Forloop;//Since the item has been found the loop jumps out
			}
		}
		//creates a copy of the contents read from file
		String[] copyOfList = new String[fileContents.length+1];
		//copies everything before the desired index
		for(int index =0;index<desiredIndex;index++)
		{
			copyOfList[index]= fileContents[index];
		}
		copyOfList[desiredIndex] = concatenatedBooking;//Adds booking to file
		//Adds the rest of the data to the contents file
		for(int index =desiredIndex+1;index<fileContents.length+1;index++)
		{
			copyOfList[index]= fileContents[index-1];
		}
		fileContents = copyOfList;
		
		
		currentPatient.writeNewDataTofile(fileContents,currentPatient.patientID+"_file.txt");
		return(newBooking);
	}
	
	//Here we pass through the booking we want added to the patient and admisisons
	//as the patient can only do this with an active acocunt assigned to consultant we assume that admission already exists.
	//first we concatenate booking together
	//then we call the method below this to find the line we sohuld write the booking to
	//We then increase the size of the entire array and insert the booking
	//we then write array to the correct file.
	public Booking createBookingAddToConsultantFile(Patient currentPatient,String consultantID,Admission currentAdmission, Booking newBooking)
	{
		String concatenatedBooking=newBooking.bookingID+","+timeft.format(newBooking.timeOfNextApp)+","+ft.format(newBooking.dateOfNextApp)+","+automaticBooking+","+newBooking.room+","+newBooking.consultantID;
		
		
		int desiredIndex = -1;
		String[] fileContents =rffReturnFullFile(consultantID+"_file.txt");//retruns the pateints fullFile
		//Returns an array of all the indexes every admission is located on the system
		desiredIndex = newBooking.findIndexBookingIsAtConsultant(fileContents,newBooking.bookingID,currentPatient.patientID);
		//creates a copy of the contents read from file
		String[] copyOfList = new String[fileContents.length+1];
		//copies everything before the desired index
		for(int index =0;index<desiredIndex;index++)
		{
			copyOfList[index]= fileContents[index];
		}
		copyOfList[desiredIndex] = concatenatedBooking;//Adds booking to file
		//Adds the rest of the data to the contents file
		for(int index =desiredIndex+1;index<fileContents.length+1;index++)
		{
			copyOfList[index]= fileContents[index-1];
		}
		fileContents = copyOfList;
		////System.out.println("___________________________________");
		for(int index =0;index<fileContents.length;index++)
		{
			////System.out.println("Line "+index+" "+fileContents[index]);
		}
		currentPatient.writeNewDataTofile(fileContents,consultantID+"_file.txt");
		return(newBooking);
	}
	
	
	//For this method we are finding the index the booking is located at in the file for the consulltant
	//for every line we check to see if we find the line containing the patient if not we carray on
	//if we do find the correct patient then we begin looking at the next index available
	//first we check that we are not at the end of the array
	//if not we then iterate through every booking until we reach the next admission
	//before this we should find the index we are looking for
	//When we do we break the loop
	public int findIndexBookingIsAtConsultant(String[] fileContents,String bookingID,String patientID)
	{	
		int indexBookingShouldBeAt = -1;
		Forloop: //search for the index the current admission is at on the file of the system
		for(int counter =0;counter<fileContents.length;counter++)//goes through every line
		{
			if(patientID.equals(fileContents[counter].substring(0,11)))//we find the index the patient occurs at
			{
				indexBookingShouldBeAt = counter+1;//index first booking should be at
				if(indexBookingShouldBeAt<fileContents.length)//makes sure not at the end 
				{
					InnerForLoop:
					for(indexBookingShouldBeAt = counter+1;(fileContents[indexBookingShouldBeAt].substring(0,1).equals("B") && bookingID.compareTo(fileContents[indexBookingShouldBeAt].substring(0,11))>0);indexBookingShouldBeAt++)//will go through every index the patient has a booking at
					{
						////System.out.println("NEXT INDEX moved");
						if(indexBookingShouldBeAt==fileContents.length-1)//makes sure not at the end 
						{
							indexBookingShouldBeAt++;
							break InnerForLoop;
						}
					}
				}
				else//booking is at the end of the arary
				{
					//nothing needed here we have the index already
				}
				////System.out.println("Desired index should be "+ indexBookingShouldBeAt);
				break Forloop;
			}
		}
		return(indexBookingShouldBeAt);
	}
	
	
	//For this method the system wil delete a booking on the account
	//Here the system will be pulled from file 
	//Then using the consultant their file is then pulled
	//Here we then find the location the admission occurs on the consultants file, similar to how the creation method works
	//After this the actual delete methods are called by the system.
	public void deleteBooking(Admission toBeUpdatedAdmission,Patient patient)
	{
			//this will locate and find the index the patient with the admission is located
			Consultant oldConsultant = new Consultant();
			oldConsultant = oldConsultant.retrieveConsultant(toBeUpdatedAdmission.consultantID);//uses id and existing method from user to retrieve the consultant
			////System.out.println("=============================================================================================");
			////System.out.println("Consultant to delete booking:"+oldConsultant.consultantID);
			////System.out.println("=============================================================================================");

			String[] consultantFileOld =rffReturnFullFile(toBeUpdatedAdmission.consultantID+"_file.txt");
			//admission search, using an overwritten method for the surname adding the system will search for the patient and either return where the surname is or where it should be, it next adds a location for it on the file by increasing the size
			int[] itemList = new int[2];
			//find check to see if consultant already has the patient for new admisisons
			////System.out.println("old consultant search");
			itemList = searchPrimaryKeysLinearConsultantSpcific(Arrays.copyOfRange(consultantFileOld,2,consultantFileOld.length),patient.patientID,itemList);
			////System.out.println("Position of patient old"+itemList[0]);
			////System.out.println("New position of patient old ?"+itemList[1]);//this value should not occur we have seelction at the begining checking to see if pending,cant be another consultant either as admission couldnt be loaded otherwise
			int indexOfPatientToremoveAdmission = itemList[0]+2;//sets index as a variable
			////System.out.println("=============================================================================================");
			////System.out.println("index to delete Booking "+indexOfPatientToremoveAdmission);
			////System.out.println("=============================================================================================");

			//now we need to find the admission
			int[] itemListAdmissionold = new int[2];
			String[] listOfAdmissionsOnConsultantold = consultantFileOld[indexOfPatientToremoveAdmission].substring(12,consultantFileOld[indexOfPatientToremoveAdmission].length()).split("#");
			//now we need to delete the booking from the patient
			deleteBookingConsultantSide(oldConsultant,toBeUpdatedAdmission,consultantFileOld,indexOfPatientToremoveAdmission);
			deleteBookingPatientSide(patient,toBeUpdatedAdmission);

	}
	//We iterate through all the admissions on the system until we find the admission associated with the booking
	//When we find the admission we try and check for the booking.
	//When it is found we note the index
	//An array with 1 less index availbel is intialised 
	//All items except the desried booking line is entered
	//New array is then wirtten to file
	public void deleteBookingPatientSide(Patient patient,Admission toBeUpdatedAdmission)
	{
		try
		{
			String[] fullPatientfile = rffReturnFullFile(patient.patientID+"_file.txt");
			int[] indexesAdmissionsOccur = retrieveIndexesAdmissionsOccur(fullPatientfile,patient.numberOfAdmissions);
			int lineAdmissionOccurs = -1;
			//for loop that goes through every admission for the patient(finding the line it occurs at)
			ForLoop:
			for(int counter = 0;counter<indexesAdmissionsOccur.length;counter++)
			{
				if(fullPatientfile[indexesAdmissionsOccur[counter]].substring(0,11).equals(toBeUpdatedAdmission.admissionID)==true)
				{
					lineAdmissionOccurs =indexesAdmissionsOccur[counter]+1;//finds index that should have the booking
					try
					{
						if(fullPatientfile[lineAdmissionOccurs].substring(0,11).equals("B"+toBeUpdatedAdmission.admissionID.substring(1,11)))
						{
							//System.out.println("Booking to be deleted has been found");
							String[] copyOfList = new String[fullPatientfile.length-1];
							for(int index =0;index<lineAdmissionOccurs;index++)
							{
								copyOfList[index]= fullPatientfile[index];
							}
							for(int index =lineAdmissionOccurs;index<fullPatientfile.length-1;index++)
							{
								copyOfList[index]= fullPatientfile[index+1];
							}
							fullPatientfile = copyOfList;
							//System.out.println("___________________________________");
							for(int index =0;index<fullPatientfile.length;index++)
							{
								//System.out.println("Line "+index+" "+fullPatientfile[index]);
							}
							writeNewDataTofile(fullPatientfile,patient.patientID+"_file.txt");
						}
					}
					catch(Exception exc)
					{
						//System.out.println("Error");
						exc.printStackTrace();
					}
					break ForLoop;
				}
			}
		}
		catch(Exception exc)
		{
			//System.out.println("Booking does not exist");
		}
		
	}
	//using the location the admission was found the sysetm then uses this to locate the booking
	//Once the location the desired booking is found at the index is recorded
	//We then creare a new arary with a length of length -1
	//We iterate through skipping over the index
	//the array is written to file
	public void deleteBookingConsultantSide(Consultant oldConsultant,Admission toBeUpdatedAdmission,String[] consultantFileOld,int indexOfPatientToremoveAdmission)
	{

		int desiredBookingIndex =-1;
		try
		{
			////System.out.println(indexOfPatientToremoveAdmission+1);
			////System.out.println(consultantFileOld.length);
			if(indexOfPatientToremoveAdmission+1<consultantFileOld.length)//selection checking the last patient has no bookings (Would cause out of bounds error)//no other else statement needed as it would have found the booking otherwise
			{
				////System.out.println("METHOD TO DELETE BOOKING FOR THE CONSULTANT STARTS HERE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				//sets the index at which a booking may exist
				int bookingcounter = indexOfPatientToremoveAdmission+1;
				WhileLoop: //will loop until either found the booking or reaches next patient
				while(consultantFileOld[bookingcounter].substring(0,1).equals("B")==true)//Will loop Through and check all the patients bookings
				{
					if(consultantFileOld[bookingcounter].substring(0,11).equals("B"+toBeUpdatedAdmission.admissionID.substring(1,11)))
					{
						////System.out.println("Condition met booking found at line"+ (bookingcounter+1));
						desiredBookingIndex =bookingcounter;
					}
					bookingcounter++;
					if(bookingcounter==consultantFileOld.length)
					{
						break WhileLoop;
					}
				}
				String[] copyOfList = new String[consultantFileOld.length-1];
				for(int index =0;index<desiredBookingIndex;index++)
				{
					copyOfList[index]= consultantFileOld[index];
				}
				for(int index =desiredBookingIndex;index<consultantFileOld.length-1;index++)
				{
					copyOfList[index]= consultantFileOld[index+1];
				}
				consultantFileOld = copyOfList;
				//System.out.println("___________________________________");
				for(int index =0;index<consultantFileOld.length;index++)
				{
					////System.out.println("Line "+index+" "+consultantFileOld[index]);
				}
				writeNewDataTofile(consultantFileOld,oldConsultant.consultantID+"_file.txt");
			}
			else{
				////System.out.println("last booking item");
			}
		}
		catch(Exception exc)
		{
			//this catch is no issue to worry about due to the fact that the method here just deletes the booking
			//System.out.println("Booking does not exist");
			//exc.printStackTrace();
		}
		
	}
	
	//This is the standard file retrieval method
	//The concatenated string is split into an array
	//Each array index is assigned to an attritbute of the instnace
	//parsing is done on the array and is assinged to the instance
	public Booking retrieveBooking(String[] listOfBookings)
	{
		SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
		SimpleDateFormat timeft = new SimpleDateFormat ("kk:mm");//declares a simple font for a visual improvement over the full version
		
		Booking newBooking = new Booking();
		newBooking.bookingID = listOfBookings[0];//the attribute is set from the correct index from the array
		//System.out.println("New Booking to be read:" +newBooking.bookingID);
		try
		{
			newBooking.timeOfNextApp = timeft.parse(listOfBookings[1]);//the attribute is set from the correct index from the array
			//System.out.println("At "+timeft.format(newBooking.timeOfNextApp));
		}
		catch(Exception exc)//if any errrors are found they are caught here		
		{
		}
		try
		{		
			newBooking.dateOfNextApp = ft.parse(listOfBookings[2]);//the attribute is set from the correct index from the array
			//System.out.println("On the "+ft.format(newBooking.dateOfNextApp));
		}
		catch(Exception exc)//if any errrors are found they are caught here		
		{
		}
		newBooking.automaticBooking = Boolean.parseBoolean(listOfBookings[3]);//the attribute is set from the correct index from the array
		//System.out.println("Automated?"+newBooking.automaticBooking);
		newBooking.room = listOfBookings[4];//the attribute is set from the correct index from the array
		//System.out.println("Room: "+newBooking.room);
		newBooking.newBooking = false;
		newBooking.consultantID = listOfBookings[5];//the attribute is set from the correct index from the array
		//System.out.println("Consultant"+newBooking.consultantID);
		return(newBooking);
	}



}