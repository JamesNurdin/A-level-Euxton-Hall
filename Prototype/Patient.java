import java.util.*;//imports the java utlities module	
import java.io.*;//imports the io package
import java.text.SimpleDateFormat;//imports the simple date format package which allows for more simple dates
public class Patient extends User 
{
	String patientID;//declares attribute which holds their patientID
	String nationality;//declares attribute which holds their nationality
	String bloodType;//declares attribute which holds their bloodType
	boolean smoker;//declares attribute which holds their smoker status
	boolean drinker;//declares attribute which holds their drinker status
	int numberOfAdmissions;//declares attribute which holds their numberOfAdmissions
	String religon;//declares attribute which holds their religon
	String allergies;//declares attribute which holds their allergies
	String disability;//declares attribute which holds their disability status
	boolean carer;//declares attribute which holds their carer status
	boolean translator;//declares attribute which holds their translator
	int numberOfNotifications;//declares attribute which holds their numberOfNotifications 
	String[] notifications;//declares an array for their notifications
	String[] allnotifications;//declares an array for their notifications
	public String[] getNotifications(String patientID)
	{
		allnotifications = rffReturnFullFile("Notifications.txt");//array holding all the notifications is recieved from reading the file
		int positionOfNotification = findPositionOfString(allnotifications,patientID);//array holding all the notifications is recieved from reading the file
		String stringedNotification = allnotifications[positionOfNotification];//adds the notification to the string
		String[] notifications = stringedNotification.split(",");//splits the string into an arary
		numberOfNotifications = notifications.length;//the length of the array is found
		return(notifications);//the array is returned to the method calling it
	}	
	public void updateNotificationsPatient(String[] array)
	{
		String word="";//creates an empty string
		numberOfNotifications = array.length;//finds how many notifications exist
		for(int count =0;count<numberOfNotifications;count++)//runs a for loop for the nubmer of notifications
		{
			if(array[count]!=null)//selection determining if the position is empty
			{
			word = word+array[count]+",";//concatenates the current word with the new notification
			}	
		}
	}
	public Patient retrievePatientInfo(String desiredPatientID)
	{
		PatientList pList = new PatientList();//creates a new instance of the class
		String[] newPatientDetails = pList.retrievePatient(desiredPatientID);//retreives the patients details and saves them to the array
		Patient tempPatient = new Patient();//creates a new instance of the class
		tempPatient.patientID = newPatientDetails[0];//the attribute is set from the correct index from the array
		if(newPatientDetails[0]!="Null")
		{
			
			tempPatient.surName = newPatientDetails[1];//the attribute is set from the correct index from the array
			tempPatient.firstName = newPatientDetails[2];//the attribute is set from the correct index from the array
			tempPatient.addressHouseNum = Integer.parseInt(newPatientDetails[3]);//the attribute is set from the correct index from the array
			tempPatient.addressHouseStreet = newPatientDetails[4];//the attribute is set from the correct index from the array
			tempPatient.town = newPatientDetails[5];//the attribute is set from the correct index from the array
			tempPatient.postcode = newPatientDetails[6];//the attribute is set from the correct index from the array
			tempPatient.contactNum = newPatientDetails[7];//the attribute is set from the correct index from the array
			tempPatient.nationality = newPatientDetails[8];//the attribute is set from the correct index from the array
			tempPatient.bloodType = newPatientDetails[9];//the attribute is set from the correct index from the array
			tempPatient.smoker = Boolean.parseBoolean(newPatientDetails[10]);//the attribute is set from the correct index from the array
			tempPatient.drinker = Boolean.parseBoolean(newPatientDetails[11]);//the attribute is set from the correct index from the array
			tempPatient.numberOfAdmissions = Integer.parseInt(newPatientDetails[12]);//the attribute is set from the correct index from the array
			try
			{
				tempPatient.dob = ft.parse(newPatientDetails[13]);//the attribute is set from the correct index from the array
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{
			}
			tempPatient.religon = newPatientDetails[14];//the attribute is set from the correct index from the array
			tempPatient.allergies = newPatientDetails[15];//the attribute is set from the correct index from the array
			tempPatient.gender = newPatientDetails[16].charAt(0);//the attribute is set from the correct index from the array
			tempPatient.disability = newPatientDetails[17];//the attribute is set from the correct index from the array
			tempPatient.carer = Boolean.parseBoolean(newPatientDetails[18]);//the attribute is set from the correct index from the array
			tempPatient.translator = Boolean.parseBoolean(newPatientDetails[19]);//the attribute is set from the correct index from the array
		}
		return(tempPatient);
	}
	public String[] concatenateNotifications(String StringedNotifications)
	{
		int arrayIndex = 0;//starts at the start of the array
		int length = StringedNotifications.length();//finds the length of the notifications
		for(int count=12;count<length;)//starts at the first part thats not the primary key
		{
			String word="";//declares an empty string
			String tempChar = StringedNotifications.charAt(count)+"";//finds the character
			while(tempChar.equals(",")==false)//while loop termination statment checking a new notification isnt present
			{
				tempChar = "";//declares a new notification
				tempChar = StringedNotifications.charAt(count)+"";//sets the temp character to the variable
				word = word+ StringedNotifications.charAt(count);//adds the character to the word
				count++;//moves onto the next character
			}
			notifications[arrayIndex] = word;//adds the full notification to the array at the current index
			arrayIndex++;//moves onto the next index in the array
		}
		return(notifications);//returns the list of notifications
	}
	public Admission[] retrieveAdmissions(Patient currentPatient)
	{
		AdmissionList aList = new AdmissionList();//creates a new instance of admission list
		Admission[] admissionlist = new Admission[100];//declares a list of admissions of size100
		admissionlist = aList.retrieveAdmissionList(currentPatient);//retreives the admissions the patient has
		return(admissionlist);//returns the array
	}
}