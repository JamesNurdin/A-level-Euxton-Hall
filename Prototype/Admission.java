import java.util.*;//imports the java utlities module
public class Admission extends Patient
{
	String admissionID;//declares attribute which holds thier admissionID
	String ward;//declares attribute which holds thier ward
	String room;//declares attribute which holds thier room 
	String consultantName;//declares attribute which holds thier consultantName
	Date dateOfNextAppointmentA = new Date();//declares attribute which holds thier dateOfNextAppointmentA
	String timeOfNextAppointmentA;//declares attribute which holds thier timeOfNextAppointmentA
	boolean active;//declares attribute which holds thier active status
	int numberOfDocuments;//declares attribute which holds thier numberOfDocuments
	String medication;//declares attribute which holds thier medication
	String[] listOfSymptoms = new String[10];//declares attribute which holds thier listOfSymptoms
	String currentDiagnosis;//declares attribute which holds thier currentDiagnosis
	String staffName;//declares attribute which holds thier staffName
	Date dateAdmissionCreated = new Date();//declares attribute which holds thier dateAdmissionCreated
	String admissionsStaffID;//declares attribute which holds thier admissionsStaffID
	String admissionsConsultantID;//declares attribute which holds thier admissionsConsultantID
	Document[] listOfDocuments;//declares a list of documents
	public Admission setRetrievedAdmission(String[] arrayAdmission)
	{
		Admission tempAdmission = new Admission();//creates a new instance of the class
		tempAdmission.admissionID = arrayAdmission[0];//the attribute is set from the correct index from the array
		tempAdmission.ward = arrayAdmission[1];//the attribute is set from the correct index from the array
		tempAdmission.consultantName = arrayAdmission[2];//the attribute is set from the correct index from the array
		try
		{
			tempAdmission.dateOfNextAppointmentA = ftTimeInc.parse(arrayAdmission[3]);//the attribute is set from the correct index from the array
		}
		catch(Exception exc)//if any errrors are found they are caught here		
		{
			System.out.println("error");
		}
		
		tempAdmission.active = Boolean.parseBoolean(arrayAdmission[4]);//the attribute is set from the correct index from the array
		tempAdmission.numberOfDocuments = Integer.parseInt(arrayAdmission[5]);//the attribute is set from the correct index from the array
		tempAdmission.medication = arrayAdmission[6];//the attribute is set from the correct index from the array
		tempAdmission.room = arrayAdmission[7];//the attribute is set from the correct index from the array
		tempAdmission.currentDiagnosis = arrayAdmission[8];//the attribute is set from the correct index from the array
		String stringedSymptoms = arrayAdmission[9];//the attribute is set from the correct index from the array
		tempAdmission.listOfSymptoms = unConcatenateStringAdmission(stringedSymptoms,"|");
		tempAdmission.staffName = arrayAdmission[10];//the attribute is set from the correct index from the array
		try
		{
			tempAdmission.dateAdmissionCreated = ft.parse(arrayAdmission[11]);//the attribute is set from the correct index from the array
		}
		catch(Exception exc)//if any errrors are found they are caught here		
		{
			System.out.println("error");
		}
		tempAdmission.admissionsStaffID = arrayAdmission[12];//the attribute is set from the correct index from the array
		tempAdmission.admissionsConsultantID = arrayAdmission[13];//the attribute is set from the correct index from the array
		return(tempAdmission);//returns the admission
	}
	
	public void retrieveDocuments(Patient currentPatient,Admission currentAdmission)
	{
		DocumentList dList = new DocumentList();//declares a new instance of the class DocumentList
		listOfDocuments = new Document[100];//sets size to 100
		listOfDocuments = dList.retrieveDocumentList(currentPatient,currentAdmission);//retrieves the list of documents 
	}
}