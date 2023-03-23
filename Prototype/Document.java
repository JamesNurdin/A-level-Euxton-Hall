import java.util.*;//imports the java utlities module
public class Document extends Admission
{
	public Document setRetrievedDocument(String[] arrayAdmission)
	{
		Document tempDocument = new Document();//creates a new instance of the class
		tempDocument.docType = arrayAdmission[2];//the attribute is set from the correct index from the array
		try
		{
			tempDocument.dateOfDocumentCreation = ftTimeInc.parse(arrayAdmission[1]);//trys to convert the string date to the object of date
		}
		catch(Exception exc)//if any errrors are found they are caught here		
		{
		}
		tempDocument.documentID = arrayAdmission[0];//the attribute is set from the correct index from the array
		if(tempDocument.docType.equals("Prescription")==true)//selection determining if the docuemtn type is this document
		{
			tempDocument.medicationName = arrayAdmission[3];//the attribute is set from the correct index from the array
			tempDocument.medicationDosage = arrayAdmission[4];//the attribute is set from the correct index from the array
			tempDocument.medicationIntakeTime = arrayAdmission[5];//the attribute is set from the correct index from the array
			try
			{
				tempDocument.medicationDateOfNextDispatch = ftTimeInc.parse(arrayAdmission[6]);//the attribute is set from the correct index from the array
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{	
			}
		}
		if(tempDocument.docType.equals("Consultant Notes")==true)//selection determining if the docuemtn type is this document
		{
			tempDocument.notes = arrayAdmission[7];//the attribute is set from the correct index from the array
		}
		if(tempDocument.docType.equals("Test Results")==true)//selection determining if the docuemtn type is this document
		{
			tempDocument.testResults = arrayAdmission[8];//the attribute is set from the correct index from the array
			tempDocument.summary = arrayAdmission[9];//the attribute is set from the correct index from the array
		}
		return(tempDocument);//returns the new document
	}
	String briefDocDescription="Blah blah blah filler text ";//declares the sort code variable
	Date dateOfDocument = new Date();//declares attribute which holds thier dateOfDocument
	Date dateOfDocumentCreation= new Date();//declares attribute which holds thier dateOfDocumentCreation
	String documentID;//declares attribute which holds thier documentID
	String hospital;//declares attribute which holds thier hospital
	String notes;//declares attribute which holds thier notes
	String testResults;//declares attribute which holds thier testResults
	String medicationName;//declares attribute which holds thier medicationName
	String medicationDosage;//declares attribute which holds thier medicationDosage
	String medicationIntakeTime;//declares attribute which holds thier medicationIntakeTime
	String summary;//declares attribute which holds thier summary
	Date medicationDateOfNextDispatch = new Date();//declares attribute which holds thier medicationDateOfNextDispatch
	String docType;//declares attribute which holds thier docType
	
	
}  