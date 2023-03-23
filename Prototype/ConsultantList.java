import java.util.*;//imports the java utlities module
import java.io.*;//imports the io package
public class ConsultantList extends Patient
{
	Consultant[] consultantList;//creates a list of consultants
	Consultant tempConsultant;//creates a new instance of consultant as a temp consultant
	int nextPosition;//declares a pointer for next avialble index to store a temp consultant in the list
	public String[] retrieveConsultant(String stringConsultantID)
	{
		String[] stringedConsultant;
		String[] stringedConsultantList =rffReturnFullFile("ConsultantInfo.txt");//returns entire file in the form of an array
		int consultantPosition = findPositionOfString(stringedConsultantList,stringConsultantID);//finds the correct position  of the desired id
		if(consultantPosition!=-1)
		{
		String foundConsultant = stringedConsultantList[consultantPosition];//using the pointer obtained before the correct consultant is set
		stringedConsultant = foundConsultant.split(",");//concatinates the string into an array
		}
		else
		{
			stringedConsultant = new String[1];
			stringedConsultant[0] = "NULL";
		}
		return(stringedConsultant);//returns the consultant
	}
	
	
	
}