import java.util.*;//imports the java utlities module
import java.text.SimpleDateFormat;//imports the simple date format package which allows for more simple dates
import javax.swing.*;//imports the java swing package,by using a wildcard all aspects of the package are imported
import java.awt.Component;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;//imports the io package

public class Document extends Admission
{
	//======================== Date formatting attributes ===================
	
	SimpleDateFormat ftTimeInc = new SimpleDateFormat ("dd/MM/yyyy k:m");
	
	//======================== Entity Attributes ===================

	Date dateOfDocumentCreation= new Date();//declares attribute which holds thier dateOfDocumentCreation
	String documentID;//declares attribute which holds thier documentID
	String summary;//declares attribute which holds thier summary
	String docType;//declares attribute which holds thier docType
	String text;
	String filePath;
	boolean legacyDocument;
	int numberOfPages;
	
	
	//like every other retrueve method on the system this calls for the intialisation of the document datatype
	//Here a stringed line containing all the document info is read from file(in calling method) and is passed through here
	//The string is split into an array
	//each index represents a particular attrbiute of document
	//each attribute is then set by parsing the data to the field
	// the docuemnt is returned at the end
	public Document setRetrievedDocument(String concatenatedDocument)
	{
		String[] documentArray = concatenatedDocument.split("@");
		for(int count = 0;count<documentArray.length;count++)
		{
		//System.out.println(documentArray[count]);
		}
		Document tempDocument = new Document();//creates a new instance of the class
		tempDocument.documentID = documentArray[0];//the attribute is set from the correct index from the array
		tempDocument.docType = documentArray[2];//the attribute is set from the correct index from the array
		try
		{
			tempDocument.dateOfDocumentCreation = ftTimeInc.parse(documentArray[1]);//trys to convert the string date to the object of date
		}
		catch(Exception exc)//if any errrors are found they are caught here		
		{
		}
		tempDocument.text=documentArray[3].replace("#", "\n");//replaces \n with # prevent new lines from being created
;
		tempDocument.summary=documentArray[4];
		tempDocument.numberOfPages=Integer.parseInt(documentArray[5]);
		tempDocument.legacyDocument = Boolean.parseBoolean(documentArray[6]);
		tempDocument.filePath=System.getProperty("user.dir")+File.separator+"LegacyPatientDocs"+"\\"+documentArray[7];
		//System.out.println("DocID FROM DOC CLASS "+tempDocument.documentID);
		return(tempDocument);//returns the new document
	}
	
	//here we have a standard document creating method
	//the instance and other important values are passed through 
	//using the patient istance we find the number of documents which generates the ID
	//Other fields are then concatenated together and or set to null
	//The location of the document is found
	//The document is then added to the array holding all of the patients info.
	public Admission createNewDocument(Document newDocument,Admission parentAdmission,String patientID)
	{
		parentAdmission.numberOfDocuments++;
		String stringedDocument=newDocument.documentID+"@"+ftTimeInc.format(newDocument.dateOfDocumentCreation)+"@"+newDocument.docType+"@"+newDocument.text+"@"+newDocument.summary+"@"+newDocument.numberOfPages+"@"+newDocument.legacyDocument+"@"+newDocument.filePath+"@";
		//now update the patients file with the new document size
		Patient tempPatient = new Patient();
		tempPatient = tempPatient.retrievePatient(patientID);
		AdmissionList al = new AdmissionList();
		al.updateAdmission(tempPatient,parentAdmission,12);
		
		//now we are adding the new document to the list of documents
		Document[] tempArray = new Document[parentAdmission.numberOfDocuments];
		for(int count = 0;count<parentAdmission.numberOfDocuments-1;count++)
		{
			tempArray[count]=parentAdmission.listOfDocuments[count];
		}
		tempArray[parentAdmission.numberOfDocuments-1]=newDocument;
		parentAdmission.listOfDocuments = tempArray;
		
		//now we are going to find the location on file where the admission is located
		int indexNewDocumentShouldGo=-1;
		String[] fileContents =parentAdmission.rffReturnFullFile(patientID+"_file.txt");
		int[] indexesAdmissionsOccur = parentAdmission.retrieveIndexesAdmissionsOccur(fileContents,tempPatient.numberOfAdmissions);
		String[] copiedFileContents = new String[fileContents.length+1];
		//finds indexDocument Should go
		for(int count = 2;count<fileContents.length;count++)
		{
			//System.out.println("file line info " +count+" "+fileContents[count].substring(0,11));
			if(fileContents[count].substring(0,11).equals(parentAdmission.admissionID))
			{
				indexNewDocumentShouldGo=count+parentAdmission.numberOfDocuments;
				//we now need to compensate for whether a booking exists, this is pretty simple all we need to do is check if the first index after the desired admission contains a b or not
				//need to check last line is not the admission
				//dont have to worry about if it is as it should work as normal
				if(count!=fileContents.length-1)
				{
					//checking if a booking exists
					if((fileContents[count+1].charAt(0)+"").equals("B"))
					{
						indexNewDocumentShouldGo++;//compensates for the booking
					}
				}
			}
		}
		//starts copying over file
		for(int counter = 0;counter<indexNewDocumentShouldGo;counter++)
		{
			copiedFileContents[counter]=fileContents[counter];
		}
		copiedFileContents[indexNewDocumentShouldGo]=stringedDocument;//writes in newdocument to correct index
		//copys the rest of the patients file over to the array
		for(int counter2 = indexNewDocumentShouldGo+1;counter2<copiedFileContents.length;counter2++)
		{
			copiedFileContents[counter2]=fileContents[counter2-1];
		}
		fileContents = copiedFileContents;
		for(int counter3 = 0;counter3<fileContents.length;counter3++)
		{
			//System.out.println(fileContents[counter3]);
		}
		newDocument.writeNewDataTofile(fileContents,patientID+"_file.txt");
		return(parentAdmission);
	}

	//validation method for the systems legacy documents
	//as it contains more feidls they need to be checked 
	//if they meet the condition, signifies that the system is then erroneous
	//if no statments are met then the document input is valid
	public boolean validateLegacyDocument(Document newDocument)
	{
		boolean validated = true;
		
		//"filepath" - not actual one we are just presence cheking it 
		validated=newDocument.presenceValidation(newDocument.filePath);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid filePath, missing.");
			return false;
		}
		
		//Type of document
		validated=newDocument.presenceValidation(newDocument.docType);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid document type, missing.");
			return false;
		}
		validated=newDocument.typeValidationString(newDocument.docType);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid document type, letters only.");
			return false;
		}
		validated=newDocument.lesserLengthValidation(newDocument.docType,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid document type, must be less than 26 characters.");
			return false;
		}
		//checking the text area
		validated=newDocument.checkCharIsNOTpresent(newDocument.text,"@");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid character used, @.");
			return false;
		}
		validated=newDocument.checkCharIsNOTpresent(newDocument.text,"£");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid character used, £.");
			return false;
		}
		validated=newDocument.checkCharIsNOTpresent(newDocument.text,"#");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid character used, #.");
			return false;
		}
		
		return true;
	}
	
	//unlike the legacy document the actual one only needs to valdiate the text field
	//as the method checks not just letters and ints we need to flag any erroroneous characters
	//length and null are also checked for reasurance
	//if fails to to meet any condition the value it accetiable, else it is leegal by meeting any condition
	public boolean validateDocumentText(String userEnteredText)
	{
		boolean validated = true;
		Document tempDocumentInstance = new Document();
		validated=tempDocumentInstance.presenceValidation(userEnteredText);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid document, please enter some text.");
			return false;
		}
		validated=tempDocumentInstance.checkCharIsNOTpresent(userEnteredText,"@");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid character used, @.");
			return false;
		}
		validated=tempDocumentInstance.checkCharIsNOTpresent(userEnteredText,"£");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid character used, £.");
			return false;
		}
		validated=tempDocumentInstance.checkCharIsNOTpresent(userEnteredText,"#");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid character used, #.");
			return false;
		}
		validated=tempDocumentInstance.lesserLengthValidation(userEnteredText,1000);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid document type, must be less than 1000 characters.");
			return false;
		}
		return true;
	}
}  