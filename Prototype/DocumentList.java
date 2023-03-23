import java.util.*;//imports the java utlities module
public class DocumentList extends Document//
{
	Document[] doclist;//creates a list of documents
	Document tempDocument;//creates a temp instance of document
	int nextPosition;//declares position for the next document inn the list
	public Document[] retrieveDocumentList(Patient patient, Admission admission)
	{
		doclist = new Document[admission.numberOfDocuments];//creates a new instance of the class
		int count = 0;//intialises the loop counter to 0
		while(count<admission.numberOfDocuments)//runs a while loop that iterates for the number of documents present
		{
			String[] stringedDocuments = rff(patient.patientID+"_"+admission.admissionID+"_Documentation.txt",count);//reads from file the documents
			//System.out.println(stringedDocuments[count]);
			tempDocument = setRetrievedDocument(stringedDocuments);//sets the temp document as the one read from file
			doclist[count] =tempDocument;//adds the list of dopcument at the index to the one read fromm file
			count++;//iterates the loop 
		}
		return(doclist);//returns the list of document
	}
}  