import java.util.*;//imports the java utlities module
import javax.swing.*;//imports the java swing package,by using a wildcard all aspects of the package are imported

public class Admission extends Patient
{
	//======================== Entity Attributes ===================
	
	String admissionID;//declares attribute which holds thier admissionID
	String ward;//declares attribute which holds thier ward
	String consultantID;//declares attribute which holds thier consultantName
	boolean active;//declares attribute which holds thier active status
	boolean requested;//if patient wants to reinstate the admission
	int numberOfDocuments;//declares attribute which holds thier numberOfDocuments
	String[] listOfSymptoms = new String[4];//declares attribute which holds thier listOfSymptoms
	String[] listOfAreasAffected = new String[12];//declares attribute which holds thier areas affected
	String typeOfPain;//declares attribute which holds thier type of pain
	Booking upComingBooking;
	String currentDiagnosis;//declares attribute which holds thier currentDiagnosis
	Date dateAdmissionCreated = new Date();//declares attribute which holds thier dateAdmissionCreated
	Document[] listOfDocuments;//declares a list of documents ADD WHEN java documment created
	
	
	//A basic search that will return all indexes where the system sees a line which olds information as an admission
	//As the number of admisisons is known the size can be preset
	//the code will check every line until the end is reached writing the indexes if yes to an int array
	public int[] retrieveIndexesAdmissionsOccur(String[] userFileArray,int numberOfadmissions)
	{
		int[] listOFIndexes = new int[numberOfadmissions];
		int outerCounter = 0;
		for(int lineIndex = 0;lineIndex<userFileArray.length;lineIndex++)
		{
			if((userFileArray[lineIndex].charAt(0)+"").equalsIgnoreCase("A")==true)
			{
				listOFIndexes[outerCounter] = lineIndex;
				////System.out.println("found at index "+lineIndex);
				outerCounter++;
			}
		}
		
		return(listOFIndexes);
	}

	//Like other validation methods on the system this one performs the same
	//initally a boolean variable is declared, using the passed instance many validations occur on the indivual feilds of the value
	//if a false is returned then the method will immidealty return false to the call method preventing the admission being written/updated on the file
	public boolean validateAdmission(Admission admission)//Patient
	{
		boolean validated = true;
		if(listOfAreasAffected.length<1)
		{
			JOptionPane.showMessageDialog(null, "Please enter at least one area.");
			return false;
		}
		//here we are concatenating all the selected areas together checking whether at least one area has been selected
		String concatenatedAreasAffected=admission.listOfAreasAffected[0]+"";
		for(int indexAreasCount = 1;indexAreasCount<12;indexAreasCount++)
		{
			try{
				if(admission.listOfAreasAffected[indexAreasCount]!=null)
				{
					concatenatedAreasAffected=concatenatedAreasAffected+admission.listOfAreasAffected[indexAreasCount]+"";
				}
			}
			catch(Exception exc){}
		}
		//here we are validaitng the areas selected so 1 has been chosen
		

		//here we are validaitng the type of pain so 1 has been chosen
		validated=presenceValidation(admission.typeOfPain);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Please select a type of pain.");
			return false;
		}
		
		
		//symptoms
		for(int symptomIndex = 0;symptomIndex<4;symptomIndex++)
		{
			try
			{
				validated=lesserLengthValidation(admission.listOfSymptoms[symptomIndex],50);
				if(validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid symptom "+(symptomIndex+1)+", must be less than 50 characters.");
					return false;
				}
			}
			catch(Exception exc){}
		}
		if(admission.listOfSymptoms.length<1)
		{
			JOptionPane.showMessageDialog(null, "Please enter at least one symptom.");
			return false;
		}
		if(admission.listOfSymptoms.length>4)
		{
			JOptionPane.showMessageDialog(null, "Please enter at At most 4 symptoms.");
			return false;
		}
		String concatenatedSymptoms=admission.listOfSymptoms[0]+"";
		for(int symptomIndex = 1;symptomIndex<4;symptomIndex++)
		{
			try{
				
			concatenatedSymptoms=concatenatedSymptoms+admission.listOfSymptoms[symptomIndex]+"";
			}
			catch(Exception exc){}
		}
		
		//here we are validaitng the symptoms so 1 has been chosen
		validated=presenceValidation(concatenatedSymptoms);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Please enter at least one symptom.");
			return false;
		}
		
		validated=typeValidationStringOrInt(concatenatedSymptoms);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid symptoms. please use digits and letters only");
			return false;
		}
		return true;
	}
	
	
	
	
	//Like other validation methods on the system this one performs the same
	//initally a boolean variable is declared, using the passed instance many validations occur on the indivual feilds of the value
	//if a false is returned then the method will immidealty return false to the call method preventing the admission being written/updated on the file
	public boolean validateAdmissionConsultant(Admission admission)
	{
		boolean validated = true;
		
		
		//here we are validaitng the areas selected so 1 has been chosen
		validated=presenceValidation(admission.ward);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid ward,Please enter one.");
			return false;
		}
		validated=typeValidationStringOrInt(admission.ward);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid ward. please use digits and letters only");
			return false;
		}
		validated=lesserLengthValidation(admission.ward,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid ward, must be less than 26 characters.");
			return false;
		}

		//here we are validaitng the type of pain so 1 has been chosen
		validated=presenceValidation(admission.currentDiagnosis);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Diagnosis,Please enter one.");
			return false;
		}
		validated=typeValidationStringOrInt(admission.currentDiagnosis);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Diagnosis. please use digits and letters only");
			return false;
		}
		validated=lesserLengthValidation(admission.currentDiagnosis,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Diagnosis, must be less than 26 characters.");
			return false;
		}
		return true;
	}
		
	
	
	//proabably the most advanced method on the system
	//Here if the consultant/staff desides to change the consultant who manages the admission they can do so
	//What happens is that the old consultant has their contents updated to reflect the changes
	//The new xconsultant then has theri file changed to make sure update occurs
	public Patient moveAdmissionBetweenAdmission(Admission toBeUpdatedAdmission,String newConsultantID,String patientID,Patient patient)
	{
		//check to see if the admission still has the cosultant as pending
		////System.out.println("old i"+toBeUpdatedAdmission.consultantID+"!");
		if(toBeUpdatedAdmission.consultantID.equalsIgnoreCase("PENDING")== false)//selection determing if there exists an old consultant after A first consultant is determined this should always be true
		{
			//need to update old consultant file
			////System.out.println("old consultant found");
			//if no locate the index in the consutlants file where it occurs
			
			//this will locate and find the index the patient with the admission is located
			Consultant oldConsultant = new Consultant();
			oldConsultant = oldConsultant.retrieveConsultant(toBeUpdatedAdmission.consultantID);//uses id and existing method from user to retrieve the consultant
			String[] consultantFileOld =rffReturnFullFile(toBeUpdatedAdmission.consultantID+"_file.txt");
			//admission search, using an overwritten method for the surname adding the system will search for the patient and either return where the surname is or where it should be, it next adds a location for it on the file by increasing the size
			int[] itemList = new int[2];
			//find check to see if consultant already has the patient for new admisisons
			////System.out.println("old consultant search");
			itemList = searchPrimaryKeysLinearConsultantSpcific(Arrays.copyOfRange(consultantFileOld,2,consultantFileOld.length),patientID,itemList);
			////System.out.println("Position of patient old"+itemList[0]);
			//System.out.println("New position of patient old ?"+itemList[1]);//this value should not occur we have seelction at the begining checking to see if pending,cant be another consultant either as admission couldnt be loaded otherwise
			int indexOfPatientToremoveAdmission = itemList[0]+2;//sets index as a variable
		
			//now we need to find the admission
			
			int[] itemListAdmissionold = new int[2];
			Booking tempbooking = new Booking();//calls method which deletes the booking for that Admission
			//here we split up the admissions that are concatenated into their residual parts
			String[] listOfAdmissionsOnConsultantold = consultantFileOld[indexOfPatientToremoveAdmission].substring(12,consultantFileOld[indexOfPatientToremoveAdmission].length()).split("#");
			//now we must determine how many admissions the patient currently has with the consultant
			if(listOfAdmissionsOnConsultantold.length==1)//checks to see if the patient needs to be removed
			{
				//at this point the admission will bee removed and the patient as well it would be acceptable to check if the next line contained a booking and to just remove it
				if((indexOfPatientToremoveAdmission+1<consultantFileOld.length)&&(consultantFileOld[indexOfPatientToremoveAdmission+1].substring(0,11).equals("B"+toBeUpdatedAdmission.admissionID.substring(1,11))))
				{
					//System.out.println("Condition met booking found at line"+ (indexOfPatientToremoveAdmission+1+1));
					//here we can set the loop to miss not just one line but two
					String[] copyOfconsultantFileOld = new String[consultantFileOld.length-2];
					for(int firstpart =0;firstpart<indexOfPatientToremoveAdmission;firstpart++)
					{
						copyOfconsultantFileOld[firstpart]= consultantFileOld[firstpart];
					}
					//now adds the rest of the surnames purposefully leaving a space for the new surname
					for(int secondPart =indexOfPatientToremoveAdmission+1;secondPart<consultantFileOld.length-2;secondPart++)
					{
						copyOfconsultantFileOld[secondPart]= consultantFileOld[secondPart-2];
					}
					//updates the old array with the old surname
					consultantFileOld=copyOfconsultantFileOld;// the patient has now got the position in the consultant file added
					oldConsultant.numberOfPatients--;
					//System.out.println("=============");
					//System.out.println("decremented patient number");
					//System.out.println("=============");
					writeNewDataTofile(consultantFileOld,toBeUpdatedAdmission.consultantID+"_file.txt");
					oldConsultant.updateConsultantAdvancedInfo(oldConsultant);
					
					
				}
				else{//here at this point the admission had no booking so like before we can just loop through and clear one line
					String[] copyOfconsultantFileOld = new String[consultantFileOld.length-1];
					for(int firstpart =0;firstpart<indexOfPatientToremoveAdmission;firstpart++)
					{
						copyOfconsultantFileOld[firstpart]= consultantFileOld[firstpart];
					}
					//now adds the rest of the surnames purposefully leaving a space for the new surname
					for(int secondPart =indexOfPatientToremoveAdmission;secondPart<consultantFileOld.length-1;secondPart++)
					{
						copyOfconsultantFileOld[secondPart]= consultantFileOld[secondPart-1];
					}
					//updates the old array with the old surname
					consultantFileOld=copyOfconsultantFileOld;// the patient has now got the position in the consultant file added
					oldConsultant.numberOfPatients--;
					//System.out.println("=============");
					//System.out.println("decremented patient number");
					//System.out.println("=============");
					writeNewDataTofile(consultantFileOld,toBeUpdatedAdmission.consultantID+"_file.txt");
					oldConsultant.updateConsultantAdvancedInfo(oldConsultant);
				}
				
			
				
				
			}
			
			if(listOfAdmissionsOnConsultantold.length>1)//checks to see if it is just fine removing the index the admission is at
			{
				//This section will find the index the admission is located and then skip it when copying an array which as a size one less
				//System.out.println("admission search");
				itemListAdmissionold = toBeUpdatedAdmission.searchPrimaryKeysLinear(listOfAdmissionsOnConsultantold,toBeUpdatedAdmission.admissionID,itemListAdmissionold);
				int indexOfAdmission = itemListAdmissionold[0];
				String[] copyOfconsultantPatientAdmissionsOld = new String[listOfAdmissionsOnConsultantold.length-1];
				for(int firstpart =0;firstpart<indexOfAdmission;firstpart++)
				{
					copyOfconsultantPatientAdmissionsOld[firstpart]= listOfAdmissionsOnConsultantold[firstpart];
				}
				//The loop abover iterates upto the admission itwants to delete but doesn't add it 
				//now adds the rest of the surnames purposefully leaving a space for the new surname
				for(int secondPart =indexOfAdmission;secondPart<listOfAdmissionsOnConsultantold.length-1;secondPart++)
				{
					copyOfconsultantPatientAdmissionsOld[secondPart]= listOfAdmissionsOnConsultantold[secondPart+1];
				}
				//updates the old array with the old surname
				listOfAdmissionsOnConsultantold=copyOfconsultantPatientAdmissionsOld;// the patient has now got the position in the consultant file added
				String stringedAdmissionsOld = patientID+",";
				for(int counter = 0;counter<listOfAdmissionsOnConsultantold.length;counter++)
				{
					stringedAdmissionsOld=stringedAdmissionsOld+listOfAdmissionsOnConsultantold[counter]+"#";
					////System.out.println("line "+counter+ " " +listOfAdmissionsOnConsultantold[counter]);
				}
				////System.out.println("complete new old consultant admission line " +stringedAdmissionsOld);
				consultantFileOld[indexOfPatientToremoveAdmission] = stringedAdmissionsOld;
				writeNewDataTofile(consultantFileOld,toBeUpdatedAdmission.consultantID+"_file.txt");
				
				//HERE AS THE INDEXES OF THE ACTUAL FILE HAVE NOT CHANGED WE ARE FINE TO USE THIS
				tempbooking.deleteBookingConsultantSide(oldConsultant,toBeUpdatedAdmission,consultantFileOld,indexOfPatientToremoveAdmission);

			}
			//now we need to delete the booking from the patient
			tempbooking.deleteBookingPatientSide(patient,toBeUpdatedAdmission);
			
			
			

		}	
		//if yes skip deleting process
			
			//Section which adds the admission to file
			
		//pull new consultant
		Consultant newConsultant = new Consultant();
		newConsultant = newConsultant.retrieveConsultant(newConsultantID);//uses id and existing method from user to retrieve the consultant
		String[] consultantFile =rffReturnFullFile(newConsultantID+"_file.txt");
		//admission search, using an overwritten method for the surname adding the system will search for the patient and either return where the surname is or where it should be, it next adds a location for it on the file by increasing the size
		int[] itemList = new int[2];
		//find check to see if consultant already has the patient for new admisisons
		//System.out.println("new consultant search");
		itemList = searchPrimaryKeysLinearConsultantSpcific(Arrays.copyOfRange(consultantFile,2,consultantFile.length),patientID,itemList);
		//System.out.println("Position"+itemList[0]);
		//System.out.println("New ?"+itemList[1]);
		int indexOfPatient = itemList[0]+2;//sets index as a variable
		
		//if no add new location for patient 
		if(itemList[1]==1)//new position needed
		{
			String[] copyOfconsultantFile = new String[consultantFile.length+1];
			for(int firstpart =0;firstpart<indexOfPatient;firstpart++)
			{
				copyOfconsultantFile[firstpart]= consultantFile[firstpart];
			}
			//now adds the rest of the surnames purposefully leaving a space for the new surname
			for(int secondPart =indexOfPatient+1;secondPart<consultantFile.length+1;secondPart++)
			{
				copyOfconsultantFile[secondPart]= consultantFile[secondPart-1];
			}
			//adds the new surname and that now there is 1 on the system
			copyOfconsultantFile[indexOfPatient]=patientID+","+toBeUpdatedAdmission.admissionID+"#";
			
			//updates the old array with the old surname
			consultantFile=copyOfconsultantFile;// the patient has now got the position in the consultant file added
			//System.out.println("line1 "+consultantFile[0]);
			//System.out.println("line 2 "+consultantFile[1]);
			//System.out.println("Admission added "+consultantFile[2]);
			
			
			
			
			
			writeNewDataTofile(consultantFile,newConsultantID+"_file.txt");
			
			
			
			
			newConsultant.numberOfPatients++;
			newConsultant.updateConsultantAdvancedInfo(newConsultant);
		}
			
		//if yes just add admission
		else if(itemList[1]==0)//checks to see if the patient already exists
		{
			int[] itemListAdmission = new int[2];
			String[] listOfAdmissionsOnConsultant = consultantFile[indexOfPatient].substring(12,consultantFile[indexOfPatient].length()).split("#");
			for(int counter = 0;counter<listOfAdmissionsOnConsultant.length;counter++)
			{
				//System.out.println("line "+counter+ " " +listOfAdmissionsOnConsultant[counter]);
			}
			//System.out.println("new admission search");
			itemListAdmission = toBeUpdatedAdmission.searchPrimaryKeysLinear(listOfAdmissionsOnConsultant,toBeUpdatedAdmission.admissionID,itemListAdmission);
			int indexOfAdmission = itemListAdmission[0];
			String[] copyOfconsultantPatientAdmissions = new String[listOfAdmissionsOnConsultant.length+1];
			for(int firstpart =0;firstpart<indexOfAdmission;firstpart++)
			{
				copyOfconsultantPatientAdmissions[firstpart]= listOfAdmissionsOnConsultant[firstpart];
			}
			//now adds the rest of the surnames purposefully leaving a space for the new surname
			for(int secondPart =indexOfAdmission+1;secondPart<listOfAdmissionsOnConsultant.length+1;secondPart++)
			{
				copyOfconsultantPatientAdmissions[secondPart]= listOfAdmissionsOnConsultant[secondPart-1];
			}
			//adds the new surname and that now there is 1 on the system
			copyOfconsultantPatientAdmissions[indexOfAdmission]=toBeUpdatedAdmission.admissionID;
			//updates the old array with the old surname
			listOfAdmissionsOnConsultant=copyOfconsultantPatientAdmissions;// the patient has now got the position in the consultant file added
			String stringedAdmissions = patientID+",";
			for(int counter = 0;counter<listOfAdmissionsOnConsultant.length;counter++)
			{
				stringedAdmissions=stringedAdmissions+listOfAdmissionsOnConsultant[counter]+"#";
				//System.out.println("line "+counter+ " " +listOfAdmissionsOnConsultant[counter]);
			}
			//System.out.println("complete " +stringedAdmissions);
			consultantFile[indexOfPatient] = stringedAdmissions;
			
			
			
			
			
			writeNewDataTofile(consultantFile,newConsultantID+"_file.txt");
			//need to add the line to the array of patients, then add that to file
		}
		return(patient);
	}
}