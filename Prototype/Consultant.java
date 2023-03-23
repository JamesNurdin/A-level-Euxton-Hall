import java.util.*;//imports the java utlities module
import java.io.*;//imports the io package
public class Consultant extends Employee
{
	int numberOfPatients;//declares attribute which holds thier numberOfPatients
	String wardLocatedIn;//declares attribute which holds thier wardLocatedIn
	String[] expertiese = new String[3];//declares attribute which holds thier expertiese
	Patient nextPatient = new Patient();//declares attribute which holds thier nextPatient
	Date dateOfNextAppointmentC = new Date();//declares attribute which holds thier dateOfNextAppointmentC
	String dayOfNextAppointmentC;//declares attribute which holds thier dayOfNextAppointmentC
	String timeOfNextAppointmentC;//declares attribute which holds thier timeOfNextAppointmentC
	String consultantID;//declares attribute which holds thier consultantID
	String[] listOfPatientsAdmissions;//declares attribute which holds thier listOfPatientsAdmissions
	String singleExpertieseString;//declares attribute which holds thier singleExpertieseString
	String[] listOfIndividualAdmissions;//declares attribute which holds thier 
	String newConsultantID;//declares attribute which holds thier listOfIndividualAdmissions
	boolean found;
	public Consultant retrieveConsultantInfo(String stringedConsultantID)
	{
		ConsultantList cl = new ConsultantList();//creates a new instance of the class
		String[] newConsultantDetails = cl.retrieveConsultant(stringedConsultantID);
		Consultant consultant;
		if(newConsultantDetails.length==1)
		{
			consultant = new Consultant();//creates an empty object  of bankclient to add values read from file
			consultant.consultantID = "Null";//the attribute is set from the correct index from the array
		}
		else
		{
		consultant = new Consultant();//creates an empty object  of bankclient to add values read from file
		consultant.consultantID = newConsultantDetails[0];//the attribute is set from the correct index from the array
		consultant.employeeID = newConsultantDetails[1];//the attribute is set from the correct index from the array
		consultant.surName = newConsultantDetails[2];//the attribute is set from the correct index from the array
		consultant.firstName = newConsultantDetails[3];//the attribute is set from the correct index from the array
		consultant.wardLocatedIn = newConsultantDetails[4];//the attribute is set from the correct index from the array
		singleExpertieseString = newConsultantDetails[5];//the attribute is set from the correct index from the array
		consultant.expertiese=unConcatenateStringAdmission(singleExpertieseString," ");
		consultant.wage = Double.parseDouble(newConsultantDetails[6]);//the attribute is set from the correct index from the array
		consultant.hoursPerWeek = Integer.parseInt(newConsultantDetails[7]);//the attribute is set from the correct index from the array
		consultant.archived = Boolean.parseBoolean(newConsultantDetails[8]);//the attribute is set from the correct index from the array
		consultant.numberOfPatients = Integer.parseInt(newConsultantDetails[9]);//the attribute is set from the correct index from the array
		consultant.addressHouseNum = Integer.parseInt(newConsultantDetails[10]);//the attribute is set from the correct index from the array
		consultant.addressHouseStreet = newConsultantDetails[11];//the attribute is set from the correct index from the array
		consultant.town = newConsultantDetails[12];//the attribute is set from the correct index from the array
		consultant.postcode = newConsultantDetails[13];//the attribute is set from the correct index from the array
		consultant.contactNum = newConsultantDetails[14];//the attribute is set from the correct index from the array
		String listOfPatientsStringed = newConsultantDetails[15];//the attribute is set from the correct index from the array
		if(consultant.numberOfPatients>0)//selection determining if the consultant has at least one patient
		{
		consultant.listOfPatientsAdmissions=unConcatenateStringAdmission(listOfPatientsStringed," ");//sets the attribute for the list of admissions by unconcatenating the string read from file
		}
		}
		return(consultant);//returns the object back to the system
	}
	public Consultant retrieveConsultantInfoFromSting(String[] newConsultantDetails)
	{
		Consultant consultant = new Consultant();//creates an empty object  of bankclient to add values read from file
		consultant.consultantID = newConsultantDetails[0];//the attribute is set from the correct index from the array
		consultant.employeeID = newConsultantDetails[1];//the attribute is set from the correct index from the array
		consultant.surName = newConsultantDetails[2];//the attribute is set from the correct index from the array
		consultant.firstName = newConsultantDetails[3];//the attribute is set from the correct index from the array
		consultant.wardLocatedIn = newConsultantDetails[4];//the attribute is set from the correct index from the array
		singleExpertieseString = newConsultantDetails[5];//the attribute is set from the correct index from the array
		consultant.expertiese=unConcatenateStringAdmission(singleExpertieseString," ");
		consultant.wage = Double.parseDouble(newConsultantDetails[6]);//the attribute is set from the correct index from the array
		consultant.hoursPerWeek = Integer.parseInt(newConsultantDetails[7]);//the attribute is set from the correct index from the array
		consultant.archived = Boolean.parseBoolean(newConsultantDetails[8]);//the attribute is set from the correct index from the array
		consultant.numberOfPatients = Integer.parseInt(newConsultantDetails[9]);//the attribute is set from the correct index from the array
		consultant.addressHouseNum = Integer.parseInt(newConsultantDetails[10]);//the attribute is set from the correct index from the array
		consultant.addressHouseStreet = newConsultantDetails[11];//the attribute is set from the correct index from the array
		consultant.town = newConsultantDetails[12];//the attribute is set from the correct index from the array
		consultant.postcode = newConsultantDetails[13];//the attribute is set from the correct index from the array
		consultant.contactNum = newConsultantDetails[14];//the attribute is set from the correct index from the array
		String listOfPatientsStringed = newConsultantDetails[15];//the attribute is set from the correct index from the array
		if(consultant.numberOfPatients>0)//selection determining if the consultant has at least one patient
		{
		consultant.listOfPatientsAdmissions=unConcatenateStringAdmission(listOfPatientsStringed," ");//sets the attribute for the list of admissions by unconcatenating the string read from file
		}
		return(consultant);//returns the object back to the system
	}
	public void addpatientToConsultantRecord(String consultantID,String patientID,String admissionID)
	{
		Consultant newconsultant = new Consultant();//creates a new instance of the class
		newconsultant = retrieveConsultantInfo(consultantID);//calls method which retireves all the information regarding the consultant from file
		int numOfPatients;//initalises the number of patients the consultant has
		
		//System.out.println("nums of patient"+newconsultant.listOfPatientsAdmissions.length);
		numOfPatients = newconsultant.listOfPatientsAdmissions.length;//finds the number of patients
		
		String desiredPatientString="";//declares an empty string
		boolean patientFound = false;//initally sets the patient as not found
		int indexOfPatient=-1;//initaly sets the value to an index that will alloways is out of bounds
		for(int loopCounter = 0; loopCounter<numOfPatients; loopCounter++)//runs a for loop for the number of patients that the consultant has
		{
			if(newconsultant.listOfPatientsAdmissions[loopCounter].substring(0,11).equals(patientID)==true)//selection determining if the current patient was already present in the arary of patients
			{
				desiredPatientString = newconsultant.listOfPatientsAdmissions[loopCounter];//retireives the string containing the information
				patientFound = true;//sets the found value to true
				indexOfPatient = loopCounter;//sets the pointer index to the point it was found
			}
		}
		if(patientFound == false)//selection determining if the patient does not exist in the file
		{
			indexOfPatient =findIndexOfPatient(newconsultant.listOfPatientsAdmissions,patientID);//calls method which returns index of the desired patient
			String[] list5 = Arrays.copyOf(newconsultant.listOfPatientsAdmissions,(numOfPatients)+1);//sets all the data the same but adds on an extra index
			newconsultant.listOfPatientsAdmissions = list5;//updates the previous array to equal the newly declared one
			for(int loopCounter = indexOfPatient; loopCounter<numOfPatients; loopCounter++)//runs a for loop starting after the desired patient
			{
				newconsultant.listOfPatientsAdmissions[loopCounter+1] =newconsultant.listOfPatientsAdmissions[loopCounter];//moves up the contents to a higher index
			}
			newconsultant.listOfPatientsAdmissions[indexOfPatient] = patientID+"&"+admissionID+"&";//sets the gap to the new patient where they would belong
			newconsultant.numberOfPatients++;//iterates the number of patients the consultant has
		}
		else if(patientFound == true)//selection determining if a prior patient already has an admission with the consultant
		{
			int indexOfAdmission;//initates a variable that holds the index of the admission
			listOfIndividualAdmissions = unConcatenateStringAdmission(desiredPatientString,"&");//splits the string every time a & character is seen
			indexOfAdmission =findIndexOfPatient(listOfIndividualAdmissions,patientID,1);//calls method which returns index of the desired patient
			//System.out.print("desired admission location is at "+indexOfAdmission);
			String[] list6 = Arrays.copyOf(listOfIndividualAdmissions,(listOfIndividualAdmissions.length)+1);//sets all the data the same but adds on an extra index
			listOfIndividualAdmissions = list6;//updates the previous array to equal the newly declared one
			for(int loopCounter = indexOfAdmission+1; loopCounter<listOfIndividualAdmissions.length-1; loopCounter++)//runs a for loop above the postion of thge desired location for the admission
			{
				listOfIndividualAdmissions[loopCounter+1] =listOfIndividualAdmissions[loopCounter];//moves the contents up an index
				//System.out.println(listOfIndividualAdmissions[loopCounter]);
			}
			listOfIndividualAdmissions[indexOfAdmission] = admissionID;//inserts the new admisison into the gap
			String specificPatientAdmissions="";//declares an empty string
			for(int arrayCounter = 0; arrayCounter<listOfIndividualAdmissions.length;arrayCounter++)//runs a for loop the length of the admission list
			{
				specificPatientAdmissions = specificPatientAdmissions+listOfIndividualAdmissions[arrayCounter]+"&";//concatenates the admissions together
			}
			newconsultant.listOfPatientsAdmissions[indexOfPatient] = specificPatientAdmissions;//adds the stringed admission back to the array
		}
		String stringedListOfPatientsAdmissions="";//declares an empty string
		for(int loopCounter = 0; loopCounter<newconsultant.listOfPatientsAdmissions.length; loopCounter++)//runs a for loop the length of the patient list
		{
			stringedListOfPatientsAdmissions=stringedListOfPatientsAdmissions+newconsultant.listOfPatientsAdmissions[loopCounter]+" ";//concatenates the patients together
		}
		String fullyConcatenatedConsultant = newconsultant.consultantID+","+newconsultant.employeeID+","+newconsultant.surName+","+newconsultant.firstName+","+newconsultant.wardLocatedIn+","+singleExpertieseString+","+newconsultant.wage+","+newconsultant.hoursPerWeek+","+newconsultant.archived+","+newconsultant.numberOfPatients+","+newconsultant.addressHouseNum+","+newconsultant.addressHouseStreet+","+newconsultant.town+","+newconsultant.postcode+","+newconsultant.contactNum+","+stringedListOfPatientsAdmissions;//concatenates attributes togehter
		//System.out.println(fullyConcatenatedConsultant);
		String[] stringedConsultantList =rffReturnFullFile("ConsultantInfo.txt");//returns entire file in the form of an array
		int consultantPosition = findIndexOfPatient(stringedConsultantList,newconsultant.consultantID,0);//calls method which returns index of the desired patient
		stringedConsultantList[consultantPosition] = fullyConcatenatedConsultant;//adds the stringed text to file
		try
		{
			String file ="ConsultantInfo.txt";//declares the filename
			FileWriter fwr = new FileWriter(file);//declare a new file writer that will ammend not not write to file
			for(int count = 0; count<stringedConsultantList.length-1;count++)//runs a for loop for the number of list of old admissions
				{
				fwr.write(stringedConsultantList[count]);//writes the patient admission to file
				fwr.write("\r\n");//move onto a new line
				}
				fwr.write(stringedConsultantList[stringedConsultantList.length-1]);//writes the desired admission to file
				fwr.close();//closes the file
		}
		catch(Exception exc)
		{
			System.out.println("Error 1");
		}
		
	}
	public Consultant updatePatientAdmissionToConsultantRecord(String consultantID,String patientID,String admissionID)
	{
		Consultant newconsultant = new Consultant();//creates a new instance of the class
		newconsultant = retrieveConsultantInfo(consultantID);//retireives the attributes of the entity using the id to find them
		if(newconsultant.numberOfPatients>0)//selection determining if the new consultant has more than one patient
		{
			int numOfPatients;//initalises the number of patients the consultant has
			//System.out.println("nums of patient"+newconsultant.listOfPatientsAdmissions.length);
			numOfPatients = newconsultant.listOfPatientsAdmissions.length;//finds the number of patients
		
			String desiredPatientString="";//declares an empty string
			boolean patientFound = false;//initally sets the patient as not found
			int indexOfPatient=-1;//initaly sets the value to an index that will alloways is out of bounds
			for(int loopCounter = 0; loopCounter<numOfPatients; loopCounter++)//runs a for loop for the number of patients that the consultant has
			{
				if(newconsultant.listOfPatientsAdmissions[loopCounter].substring(0,11).equals(patientID)==true)//selection determining if the current patient was already present in the arary of patients
				{
					desiredPatientString = newconsultant.listOfPatientsAdmissions[loopCounter];//retireives the string containing the information
					patientFound = true;//sets the found value to true
					indexOfPatient = loopCounter;//sets the pointer index to the point it was found
				}
			}
			if(patientFound == false)//selection determining if the patient does not exist in the file
			{
				indexOfPatient =findIndexOfPatient(newconsultant.listOfPatientsAdmissions,patientID);//calls method which returns index of the desired patient
				String[] list5 = Arrays.copyOf(newconsultant.listOfPatientsAdmissions,(numOfPatients)+1);//sets all the data the same but adds on an extra index
				newconsultant.listOfPatientsAdmissions = list5;//updates the previous array to equal the newly declared one
				for(int loopCounter = indexOfPatient; loopCounter<numOfPatients; loopCounter++)//runs a for loop starting after the desired patient
				{
					newconsultant.listOfPatientsAdmissions[loopCounter+1] =newconsultant.listOfPatientsAdmissions[loopCounter];//moves up the contents to a higher index
				}
				newconsultant.listOfPatientsAdmissions[indexOfPatient] = patientID+"&"+admissionID+"&";//sets the gap to the new patient where they would belong
				newconsultant.numberOfPatients++;//iterates the number of patients the consultant has
			}
			else if(patientFound == true)//selection determining if a prior patient already has an admission with the consultant
			{
				int indexOfAdmission;//initates a variable that holds the index of the admission
				listOfIndividualAdmissions = unConcatenateStringAdmission(desiredPatientString,"&");//splits the string every time a & character is seen
				indexOfAdmission =findIndexOfPatient(listOfIndividualAdmissions,patientID,1);//calls method which returns index of the desired patient
				//System.out.print("desired admission location is at "+indexOfAdmission);
				String[] list6 = Arrays.copyOf(listOfIndividualAdmissions,(listOfIndividualAdmissions.length)+1);//sets all the data the same but adds on an extra index
				listOfIndividualAdmissions = list6;//updates the previous array to equal the newly declared one
				for(int loopCounter = indexOfAdmission+1; loopCounter<listOfIndividualAdmissions.length-1; loopCounter++)//runs a for loop above the postion of thge desired location for the admission
				{
					listOfIndividualAdmissions[loopCounter+1] =listOfIndividualAdmissions[loopCounter];//moves the contents up an index
					//System.out.println(listOfIndividualAdmissions[loopCounter]);
				}
				listOfIndividualAdmissions[indexOfAdmission] = admissionID;//inserts the new admisison into the gap
				String specificPatientAdmissions="";//declares an empty string
				for(int arrayCounter = 0; arrayCounter<listOfIndividualAdmissions.length;arrayCounter++)//runs a for loop the length of the admission list
				{
					specificPatientAdmissions = specificPatientAdmissions+listOfIndividualAdmissions[arrayCounter]+"&";//concatenates the admissions together
				}
				newconsultant.listOfPatientsAdmissions[indexOfPatient] = specificPatientAdmissions;//adds the stringed admission back to the array
			}
			String stringedListOfPatientsAdmissions="";//declares an empty string
			for(int loopCounter = 0; loopCounter<newconsultant.listOfPatientsAdmissions.length; loopCounter++)//runs a for loop the length of the patient list
			{
				stringedListOfPatientsAdmissions=stringedListOfPatientsAdmissions+newconsultant.listOfPatientsAdmissions[loopCounter]+" ";//concatenates the patients together
			}
			String fullyConcatenatedConsultant = newconsultant.consultantID+","+newconsultant.employeeID+","+newconsultant.surName+","+newconsultant.firstName+","+newconsultant.wardLocatedIn+","+singleExpertieseString+","+newconsultant.wage+","+newconsultant.hoursPerWeek+","+newconsultant.archived+","+newconsultant.numberOfPatients+","+newconsultant.addressHouseNum+","+newconsultant.addressHouseStreet+","+newconsultant.town+","+newconsultant.postcode+","+newconsultant.contactNum+","+stringedListOfPatientsAdmissions;//concatenates attributes togehter
			//System.out.println(fullyConcatenatedConsultant);
			String[] stringedConsultantList =rffReturnFullFile("ConsultantInfo.txt");//returns entire file in the form of an array
			int consultantPosition = findIndexOfPatient(stringedConsultantList,newconsultant.consultantID,0);//calls method which returns index of the desired patient
			stringedConsultantList[consultantPosition] = fullyConcatenatedConsultant;//adds the stringed text to file
			try
			{
				String file ="ConsultantInfo.txt";//declares the filename
				FileWriter fwr = new FileWriter(file);//declare a new file writer that will ammend not not write to file
				for(int count = 0; count<stringedConsultantList.length-1;count++)//runs a for loop for the number of list of old admissions
				{
				fwr.write(stringedConsultantList[count]);//writes the patient admission to file
				fwr.write("\r\n");//move onto a new line
				}
				fwr.write(stringedConsultantList[stringedConsultantList.length-1]);//writes the desired admission to file
				fwr.close();//closes the file
			}
			catch(Exception exc)
			{
				System.out.println("Error 1");
			}
		}
		else{
			String stringedListOfPatientsAdmissions=patientID+"&"+admissionID+"& ";//sets the gap to the new patient where they would belong
			newconsultant.numberOfPatients++;//increments the number of patients
			String fullyConcatenatedConsultant = newconsultant.consultantID+","+newconsultant.employeeID+","+newconsultant.surName+","+newconsultant.firstName+","+newconsultant.wardLocatedIn+","+singleExpertieseString+","+newconsultant.wage+","+newconsultant.hoursPerWeek+","+newconsultant.archived+","+newconsultant.numberOfPatients+","+newconsultant.addressHouseNum+","+newconsultant.addressHouseStreet+","+newconsultant.town+","+newconsultant.postcode+","+newconsultant.contactNum+","+stringedListOfPatientsAdmissions;//concatenates attributes togehter
			//System.out.println(fullyConcatenatedConsultant);
			String[] stringedConsultantList =rffReturnFullFile("ConsultantInfo.txt");//returns entire file in the form of an array
			int consultantPosition = findIndexOfPatient(stringedConsultantList,newconsultant.consultantID,0);//calls method which returns index of the desired patient
			stringedConsultantList[consultantPosition] = fullyConcatenatedConsultant;//adds the stringed text to file
			try
			{
				String file ="ConsultantInfo.txt";//declares the filename
				FileWriter fwr = new FileWriter(file);//declare a new file writer that will ammend not not write to file
				for(int count = 0; count<stringedConsultantList.length-1;count++)//runs a for loop for the number of list of old admissions
				{
				fwr.write(stringedConsultantList[count]);//writes the patient admission to file
				fwr.write("\r\n");//move onto a new line
				}
				fwr.write(stringedConsultantList[stringedConsultantList.length-1]);//writes the desired admission to file
				fwr.close();//closes the file
			}
			catch(Exception exc)
			{
				System.out.println("Error 1");
			}
			
		}
			return(newconsultant);//returns the consultant object to the 
	}
	
	
	public int removepatientToConsultantRecord(String consultantID,String patientID,String admissionID)
	{
		Consultant newconsultant = new Consultant();//creates a new instance of the class
		newconsultant = retrieveConsultantInfo(consultantID);//retireives the attributes of the entity using the id to find them
		int numOfPatients = newconsultant.listOfPatientsAdmissions.length;//initalises the number of patients the consultant has
		String desiredPatientString="";//declares an empty string
		boolean patientFound = false;//initally sets the patient as not found
		int indexOfPatient=-1;//initaly sets the value to an index that will alloways is out of bounds
		for(int loopCounter = 0; loopCounter<numOfPatients; loopCounter++)//runs a for loop for the number of patients that the consultant has
		{	
			if(newconsultant.listOfPatientsAdmissions[loopCounter].substring(0,11).equals(patientID)==true)//selection determining if the current patient was already present in the arary of patients
			{
				desiredPatientString = newconsultant.listOfPatientsAdmissions[loopCounter];//retireives the string containing the information
				patientFound = true;//sets the found value to true
				//System.out.println("pATIENT FOUND");
				indexOfPatient = loopCounter;//pointer to patient is saved
			}
		}
		if(patientFound == true)//selection determining if a prior patient already has an admission with the consultant
		{
			int indexOfAdmission;//initates a variable that holds the index of the admission
			listOfIndividualAdmissions = unConcatenateStringAdmission(desiredPatientString,"&");//splits the string every time a & character is seen
			indexOfAdmission =findIndexOfPatient(listOfIndividualAdmissions,admissionID,0);//calls method which returns index of the desired patient
			
			System.out.println("desired patient is at "+indexOfAdmission);
			//System.out.println("=======================");
			//for(int loopCounter = 0; loopCounter<listOfIndividualAdmissions.length; loopCounter++)//runs a for loop that runs through for the number of admissions
			//{	
			//System.out.println("At index "+ loopCounter+ "We have: "+listOfIndividualAdmissions[loopCounter]);
			//}
			//System.out.println("=======================");
			String[] tempArray = new String[listOfIndividualAdmissions.length-1];//declares an array with the legnth of one less than the old array
			for(int loopCounter = 0; loopCounter<indexOfAdmission; loopCounter++)//runs a for loop that runs upto everything before the desired index
			{
				tempArray[loopCounter] = listOfIndividualAdmissions[loopCounter];//updates the array with the item at the same index
				//System.out.println(listOfIndividualAdmissions[loopCounter]+"admission "+ loopCounter);
			}
			//System.out.println("Otherside");
			for(int loopCounter = indexOfAdmission+1; loopCounter<tempArray.length+1; loopCounter++)//runs the for loop from the index above the desired array until the end of the array is reached
			{
				tempArray[loopCounter-1] = listOfIndividualAdmissions[loopCounter];//moves the rest of the items over by copying over 
				//System.out.println(listOfIndividualAdmissions[loopCounter]+"admission "+ loopCounter);
			}
			
			listOfIndividualAdmissions=tempArray;//coppiess over the array
			
			
			//System.out.println(listOfIndividualAdmissions.length+"Length of patients admissions");
			String specificPatientAdmissions="";//declares an empty string
			if(listOfIndividualAdmissions.length>1)//selection determining if there is at least one admission
			{
				for(int arrayCounter = 0; arrayCounter<listOfIndividualAdmissions.length;arrayCounter++)//runs a for loop tghrough the enire array
				{
					specificPatientAdmissions = specificPatientAdmissions+listOfIndividualAdmissions[arrayCounter]+"&";//concatenates the array together with &
				}
				
			}
			//System.out.println(specificPatientAdmissions);
			newconsultant.listOfPatientsAdmissions[indexOfPatient] = specificPatientAdmissions;//adds the string into the gap left from the above for loops
		}
		String stringedListOfPatientsAdmissions="";//declares an empty string
		if(listOfIndividualAdmissions.length<=1)//selection determining if there is only one admission
		{
			for(int loopCounter = 0; loopCounter<indexOfPatient; loopCounter++)//runs a for loop up to the desired location
			{
				stringedListOfPatientsAdmissions=stringedListOfPatientsAdmissions+newconsultant.listOfPatientsAdmissions[loopCounter]+" ";//copies over the contents from the array
			}
			for(int loopCounter = indexOfPatient+1; loopCounter<newconsultant.listOfPatientsAdmissions.length; loopCounter++)//runs the for loop for the rest of the file
			{
				stringedListOfPatientsAdmissions=stringedListOfPatientsAdmissions+newconsultant.listOfPatientsAdmissions[loopCounter]+" ";//copies over the contents from the array
			}
			newconsultant.numberOfPatients--;//decreminets the array
			if(newconsultant.numberOfPatients ==0)//selection determining if there are no more patients left
			{
				stringedListOfPatientsAdmissions=" ";//adds   as there is no other patients
				
			}
		}
		if(listOfIndividualAdmissions.length>1)//selection determining if there are more than one admission
		{
			for(int loopCounter = 0; loopCounter<newconsultant.listOfPatientsAdmissions.length; loopCounter++)//runs a for loop up to the desired location
			{
				stringedListOfPatientsAdmissions=stringedListOfPatientsAdmissions+newconsultant.listOfPatientsAdmissions[loopCounter]+" ";//copies over the contents from the array
			}
		}
			//System.out.println("=======================");
			//for(int loopCounter = 0; loopCounter<newconsultant.listOfPatientsAdmissions.length; loopCounter++)//runs the for loop for the rest of the file
			//{
				
				//System.out.println("At index "+ loopCounter+ "We have: "+newconsultant.listOfPatientsAdmissions[loopCounter]);
			//}
			//System.out.println("=======================");
		String fullyConcatenatedConsultant = newconsultant.consultantID+","+newconsultant.employeeID+","+newconsultant.surName+","+newconsultant.firstName+","+newconsultant.wardLocatedIn+","+singleExpertieseString+","+newconsultant.wage+","+newconsultant.hoursPerWeek+","+newconsultant.archived+","+newconsultant.numberOfPatients+","+newconsultant.addressHouseNum+","+newconsultant.addressHouseStreet+","+newconsultant.town+","+newconsultant.postcode+","+newconsultant.contactNum+","+stringedListOfPatientsAdmissions;//concatenates attributes togehter
		//System.out.println(fullyConcatenatedConsultant);
		String[] stringedConsultantList =rffReturnFullFile("ConsultantInfo.txt");//reads array from file
		int consultantPosition = findIndexOfPatient(stringedConsultantList,newconsultant.consultantID,0);//finds the index of the desired patient
		stringedConsultantList[consultantPosition] = fullyConcatenatedConsultant;//adds patient to array
		try
		{
			String file ="ConsultantInfo.txt";//declares the filename
			FileWriter fwr = new FileWriter(file);//declare a new file writer that will ammend not not write to file
			for(int count = 0; count<stringedConsultantList.length;count++)//runs a for loop for the interity of the array
			{
			fwr.write(stringedConsultantList[count]);//writes consultant along with the admissions to file
			fwr.write("\r\n");//moves onto a new lien
			}
			fwr.close();//closes the file
		}
		catch(Exception exc)
		{
			System.out.println("Error 1");
		}
		return(newconsultant.numberOfPatients);//returns new number of patients
	}
}