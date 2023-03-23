import java.util.*;//imports the java utlities module
import java.text.SimpleDateFormat;//imports the simple date format package which allows for more simple dates
import java.io.*;//imports the io package
public class Employee extends User
{
	String employeeID;//declares attribute which holds thier employeeID
	double wage;//declares attribute which holds thier wage
	int hoursPerWeek;//declares attribute which holds thier hoursPerWeek
	boolean archived;//declares attribute which holds thier archived status
	
	String currentAction;//declares attribute which holds thier currentAction
	Date DateActionPerformed;//declares attribute which holds thier DateActionPerformed
	String PatientInvloved ;//declares attribute which holds thier PatientInvloved
	String AdmissionInvolved;//declares attribute which holds thier AdmissionInvolved
	String newData;//declares attribute which holds thier newData
	String oldData;//declares attribute which holds thier oldData
	SimpleDateFormat ftTimeInc = new SimpleDateFormat ("dd/MM/yyyy k:m");//declares a simple format so dates look presentable
	public void writeActionToFile(Consultant consultant,Document document,Admission admission,Patient patient,int action)
	{
		String[] readInfo = rffReturnFullFile("Employee_Actionlog.txt");//retireves the file where the actions are stored
		int locationOfEmployee=findIndexOfEmployee(readInfo,consultant.employeeID);//finds the index of the correct employee
		if(action == 0)//selection determining if the current action was of this type
		{
			currentAction="Created Document Prescription";//declares the action of the consultant
			oldData ="n/a";//concatenates the information together
			newData =document.medicationName+" "+document.medicationIntakeTime+" "+ftTimeInc.format(document.medicationDateOfNextDispatch)+" "+document.medicationDosage;//concatenates the information together
		}
		if(action == 1)//selection determining if the current action was of this type
		{
			currentAction="Created Document Dischargment";//declares the action of the consultant
			oldData ="n/a";//concatenates the information together
			newData ="Patient admission Discharged";//concatenates the information together
		}
		if(action == 2)//selection determining if the current action was of this type
		{
			currentAction="Created Document Test Results";//declares the action of the consultant
			oldData ="n/a";//concatenates the information together
			newData =document.notes+" "+document.summary;//concatenates the information together
		}
		if(action == 3)//selection determining if the current action was of this type
		{
			currentAction="Created Reinstament";//declares the action of the consultant
			oldData ="n/a";//concatenates the information together
			newData ="Patient admission reinstated";//concatenates the information together
		}
		if(action == 4)//selection determining if the current action was of this type
		{
			currentAction="Created Document notes";//declares the action of the consultant
			oldData ="n/a";//concatenates the information together
			newData =document.notes;//concatenates the information together
		}
		if(action == 5)//selection determining if the current action was of this type
		{
			currentAction="Created Document notes";//declares the action of the consultant
			oldData ="n/a";//concatenates the information together
			newData =document.notes;//concatenates the information together
		}
		if(action == 7)//selection determining if the current action was of this type
		{
			currentAction="Relocated patient admission";//declares the action of the consultant
			oldData ="From: "+consultant.consultantID;//concatenates the information together
			newData ="To: "+consultant.newConsultantID;//concatenates the information together
		}
		if(action == 8)//selection determining if the current action was of this type
		{
			currentAction="Created Document notes";//declares the action of the consultant
			oldData ="n/a";//concatenates the information together
			newData =document.notes;//concatenates the information together
		}
		try{
			
			if(consultant.employeeID.equals(readInfo[locationOfEmployee].substring(0,11))==false)//trys to see if the desired consultant is  equal to the name in the location(check occurs due to the fact the employee may not exist) and an exception is thrown
			{
				String[] list4 = Arrays.copyOf(readInfo, readInfo.length+1);//sets all the data the same but adds on an extra index
				readInfo = list4;//updates the previous array to equal the newly declared one
				for(int count = readInfo.length-1;locationOfEmployee<count;count--)//runs a for loop that moves every item ahead of the desired location by one to make room for it
				{
					readInfo[count+1]= readInfo[count];//moves items forward by one
				}
				readInfo[locationOfEmployee]=consultant.employeeID+"~"+ftTimeInc.format(document.dateOfDocumentCreation)+","+currentAction+","+patient.patientID+","+admission.admissionID+","+oldData+","+newData+","+"~";//concatenates the information together
				//System.out.println("NEW PATIENT ADDED");//informs the user a new patient has been added
			}
			
			else if(consultant.employeeID.equals(readInfo[locationOfEmployee].substring(0,11))==true)//checks to see if the employee already has an action log
			{
				readInfo[locationOfEmployee]=readInfo[locationOfEmployee]+ftTimeInc.format(document.dateOfDocumentCreation)+","+currentAction+","+patient.patientID+","+admission.admissionID+","+oldData+","+newData+","+"~";//concatenates the information together
			}
			//System.out.println(readInfo[locationOfEmployee]);
		}
		catch(Exception exc)
		{
		if(locationOfEmployee==readInfo.length)//if the line of the desired employee is at the end of the array(out of bounds)
		{
			String[] list4 = Arrays.copyOf(readInfo, readInfo.length+1);//sets all the data the same but adds on an extra index
				readInfo = list4;//updates the previous array to equal the newly declared one
			readInfo[locationOfEmployee]=consultant.employeeID+"~"+ftTimeInc.format(document.dateOfDocumentCreation)+","+currentAction+","+patient.patientID+","+admission.admissionID+","+oldData+","+newData+","+"~";//concatenates the information together
				//System.out.println("NEW PATIENT ADDED");
		}			
		}
		
		try
		{
		FileWriter fw = new FileWriter("Employee_Actionlog.txt");//declare a new file writer that will ammend not not write to file
		for(int count = 0;count<readInfo.length;count++)//runs a for loop for the number of consultants on the system that exist
		{
		fw.write(readInfo[count]);//writes consultant to line
		fw.write("\r\n");//adds in a space to the file
		}
		fw.close();//closes the file
		}
		catch(Exception exc)
		{
			
		}
		
	}
	public void writeActionToFile7(Consultant consultant,Document document,Admission admission,Patient patient,int action,Admission newAdmission,String[] stringedSymptoms)
	{
		String[] readInfo = rffReturnFullFile("Employee_Actionlog.txt");//retireves the file where the actions are stored
		int locationOfEmployee=findIndexOfEmployee(readInfo,consultant.employeeID);//finds the index of the correct employee
		if(action == 6)//selection determining if the current action was of this type
		{
			currentAction="Updated Admission information";//declares the action of the consultant
			oldData =("OLD"+admission.admissionID+","+admission.ward+","+admission.consultantName+","+ftTimeInc.format(admission.dateOfNextAppointmentA)+","+admission.active+","+admission.numberOfDocuments+","+admission.medication+","+admission.room+","+admission.currentDiagnosis+","+admission.listOfSymptoms[0]+","+admission.listOfSymptoms[1]+","+admission.listOfSymptoms[2]+","+admission.staffName+","+ft.format(admission.dateAdmissionCreated)+","+admission.admissionsStaffID+","+admission.admissionsConsultantID);//concatenates the information together
			newData = ("NEW"+newAdmission.admissionID+","+newAdmission.ward+","+newAdmission.consultantName+","+ftTimeInc.format(newAdmission.dateOfNextAppointmentA)+","+newAdmission.active+","+newAdmission.numberOfDocuments+","+newAdmission.medication+","+newAdmission.room+","+newAdmission.currentDiagnosis+","+stringedSymptoms[0]+","+stringedSymptoms[1]+","+stringedSymptoms[2]+","+newAdmission.staffName+","+ft.format(newAdmission.dateAdmissionCreated)+","+newAdmission.admissionsStaffID+","+newAdmission.admissionsConsultantID);//concatenates the information together
		}
		try{
			
			if(consultant.employeeID.equals(readInfo[locationOfEmployee].substring(0,11))==false)//trys to see if the desired consultant is  equal to the name in the location(check occurs due to the fact the employee may not exist) and an exception is thrown
			{
				String[] list4 = Arrays.copyOf(readInfo, readInfo.length+1);//sets all the data the same but adds on an extra index
				readInfo = list4;//updates the previous array to equal the newly declared one
				for(int count = readInfo.length-1;locationOfEmployee<count;count--)//runs a for loop that moves every item ahead of the desired location by one to make room for it
				{
					readInfo[count+1]= readInfo[count];//moves items forward by one
				}
				readInfo[locationOfEmployee]=consultant.employeeID+"~"+ftTimeInc.format(document.dateOfDocumentCreation)+","+currentAction+","+patient.patientID+","+admission.admissionID+","+oldData+","+newData+","+"~";//concatenates the information together
				//System.out.println("NEW PATIENT ADDED");
			}
			
			else if(consultant.employeeID.equals(readInfo[locationOfEmployee].substring(0,11))==true)//checks to see if the employee already has an action log
			{
				readInfo[locationOfEmployee]=readInfo[locationOfEmployee]+ftTimeInc.format(document.dateOfDocumentCreation)+","+currentAction+","+patient.patientID+","+admission.admissionID+","+oldData+","+newData+","+"~";//concatenates the information together
			}
			//System.out.println(readInfo[locationOfEmployee]);
		}
		catch(Exception exc)
		{
		if(locationOfEmployee==readInfo.length)//if the line of the desired employee is at the end of the array(out of bounds)
		{
			String[] list4 = Arrays.copyOf(readInfo, readInfo.length+1);//sets all the data the same but adds on an extra index
				readInfo = list4;//updates the previous array to equal the newly declared one
			readInfo[locationOfEmployee]=consultant.employeeID+"~"+ftTimeInc.format(document.dateOfDocumentCreation)+","+currentAction+","+patient.patientID+","+admission.admissionID+","+oldData+","+newData+","+"~";//concatenates the information together
				//System.out.println("NEW PATIENT ADDED");
		}			
		}
		
		try
		{
		FileWriter fw = new FileWriter("Employee_Actionlog.txt");//declare a new file writer that will ammend not not write to file
		for(int count = 0;count<readInfo.length;count++)//runs a for loop for the number of consultants on the system that exist
		{
		fw.write(readInfo[count]);//writes consultant to line
		fw.write("\r\n");//adds in a space to the file
		}
		fw.close();//closes the file
		}
		catch(Exception exc)
		{
			
		}
	}
}