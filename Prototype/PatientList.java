import java.util.*;//imports the java utlities module
import java.io.*;//imports the io package
public class PatientList extends Patient
{
	Patient[] patientList;//creates a list of patients
	Patient tempPatient;//declares a new instance of patient
	int nextPosition;//declares attribute which holds the position of the next patient 
	public String[] retrievePatient(String stringPatientID)
	{
		String[] stringedPatientList =rffReturnFullFile("Patient_Demographic.txt");//returns entire file in the form of an array
		String[] stringedPatient;
		int patientPosition = findPositionOfString(stringedPatientList,stringPatientID);//finds the correct position  of the desired id
		if(patientPosition!=-1)
		{
		String foundPatient = stringedPatientList[patientPosition];//sets the desired object from the array using the pointer
		stringedPatient = foundPatient.split(",");//concatinates the string into an array
		}
		else
		{
			stringedPatient = new String[1];
			stringedPatient[0] = "Null";
		}
		return(stringedPatient);//returns the patient
	}
	
	public void updatePatientDemo (Patient patient)
	{
		String[] stringedPatientList =rffReturnFullFile("Patient_Demographic.txt");//returns entire file in the form of an array
		String stringPatientID = patient.patientID;//using the patient passed through the id is set from the attribute
		int patientPosition = findPositionOfString(stringedPatientList,stringPatientID);//finds the correct position  of the desired id
		String patientDemo = (patient.patientID+","+patient.surName+","+patient.firstName+","+patient.addressHouseNum+","+patient.addressHouseStreet+","+patient.town+","+patient.postcode+","+patient.contactNum+","+patient.nationality+","+patient.bloodType+","+patient.smoker+","+patient.drinker+","+patient.numberOfAdmissions+","+ft.format(patient.dob)+","+patient.religon+","+patient.allergies+","+patient.gender+","+patient.disability+","+patient.carer+","+patient.translator);
		stringedPatientList[patientPosition] = patientDemo;//sets the desired object from the array using the pointer
		int length = stringedPatientList.length;//findsa out how many characters the string has
		wtf(stringedPatientList,"Patient_Demographic.txt",length);//writes to file the patient string 
	}
	
	public Patient createNewPatient(Patient patient)
	{
		patient.patientID = createUniqueID(patient.surName,"Patient_Demographic.txt","P");//creates a unique id for the entity
		String[] stringedPatientList =rffReturnFullFile("Patient_Demographic.txt");//returns entire file in the form of an array
		String[] stringedNotifications = rffReturnFullFile("Notifications.txt");//returns entire file in the form of an array
		int patientPosition = findIndexOfPatient(stringedPatientList,patient.patientID);//finds the correct position  of the desired id
		//notification file
		String[] list2 = Arrays.copyOf(stringedNotifications, stringedNotifications.length+1);//sets all the data the same but adds on an extra index
		stringedNotifications = list2;//updates the previous array to equal the newly declared one
		String currentID;//initalises the id used to find the desired value
		int position = -1;//initaly sets the value to an index that will alloways is out of bounds
		for(int count = 0;count<stringedNotifications.length-1;count++)//for loop that runs through the entirty of the array
		{
			currentID = stringedNotifications[count];//assigns the current id to the attribute
			if(count== 0)//selection determining if the value is at the start of the array
			{
				/*
				An int value: 0 if the string is equal to the other string, ignoring case differences.
				< 0 if the string is lexicographically less than the other string 
				> 0 if the string is lexicographically greater than the other string (more characters)
				*/
				currentID = stringedNotifications[count].substring(0,11);//substrings string to get primary key
				if(patient.patientID.compareToIgnoreCase(stringedNotifications[0])<0)//selection determining if the desired location is at the first index
				{
					position = 0;//assigns position to first index
				}
			}
			if((count > 0)&&(count<stringedNotifications.length))//if the desired location is between the start and end
			{
				currentID = stringedNotifications[count].substring(0,11);//substrings string to get primary key
				if((patient.patientID.compareToIgnoreCase(stringedNotifications[count])<0)&&(patient.patientID.compareToIgnoreCase(stringedNotifications[count-1])>0))//if the desired location is between index a and index a+1 
				{
					position = count;//asigns the correct index the item should be located in 
				}
			}
			if(count== stringedNotifications.length-1)//selection determining if the location is at the last index of the array
			{
				currentID = stringedNotifications[count].substring(0,11);//substrings string to get primary key
				if(patient.patientID.compareToIgnoreCase(currentID)>0)//making sure that the position is above 0 
				{
					position = stringedNotifications.length;//assigns index to be the next value above the length(last index + 1)
				}
			}
			currentID = stringedNotifications[count].substring(0,11);//substrings string to get primary key
			if(patient.patientID.compareToIgnoreCase(stringedNotifications[0])==0)
			{
				position = count;//asigns the correct index the item should be located in
			}
			System.out.println(position);
		}
		if(position==-1)
		{
			position = stringedNotifications.length-1;
			
		}
		for(int index = stringedNotifications.length-1;index>patientPosition+1;index--)//runs a for loop for that of the number ofpatients above it in the file
		{
			
			stringedNotifications[index] =stringedNotifications[index-1];//sets the index to move up aN INDEX
		}
		String patientNotification = (patient.patientID+",");//declares first part of notification by adding the primary key
		stringedNotifications[position] = patientNotification;//adds new notification to the array
		wtfNewPatient(stringedNotifications,"Notifications.txt");//writes the array to file
		//demographic file
		String[] list3 = Arrays.copyOf(stringedPatientList, stringedPatientList.length+1);//sets all the data the same but adds on an extra index
		stringedPatientList = list3;//updates the previous array to equal the newly declared one
		for(int index = stringedPatientList.length-1;index>patientPosition;index--)//runs a for loop for that of the number ofpatients above it in the file
		{
			stringedPatientList[index] =stringedPatientList[index-1];//sets the index to move up aN INDEX
		}
		String patientDemo = (patient.patientID+","+patient.surName+","+patient.firstName+","+patient.addressHouseNum+","+patient.addressHouseStreet+","+patient.town+","+patient.postcode+","+patient.contactNum+","+patient.nationality+","+patient.bloodType+","+patient.smoker+","+patient.drinker+","+patient.numberOfAdmissions+","+ft.format(patient.dob)+","+patient.religon+","+patient.allergies+","+patient.gender+","+patient.disability+","+patient.carer+","+patient.translator);//concatenates patient attributes togehter
		System.out.println("H");
		stringedPatientList[patientPosition] = patientDemo;//adds new information to the array
		wtfNewPatient(stringedPatientList,"Patient_Demographic.txt");//writes entire array to file
		try
		{
			String file = patient.patientID+"_Admissions.txt";//creates a new filename for the user
			FileWriter fwr = new FileWriter(file);//declare a new file writer that will ammend not not write to file
			fwr.close();//closes the file
		}
		catch(Exception exc)
		{
		}
		System.out.println("j");
		return(patient);
	}
	
	
		
		
	public static void main(String[] args )//declaring the main method
    {
        
    }
	
}  