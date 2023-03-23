import java.util.*;//imports the java utlities module
public class AdmissionList extends Admission
{
	Admission[] admissionlist;//declares the list of admissions 
	Admission tempAdmission;//creates an empty instance of admission
	int nextPosition;//creates an attribute which points to the next index of admission
	public Admission[] retrieveAdmissionList(Patient patient)
	{
		//System.out.println("Actual num " +patient.numberOfAdmissions);
		admissionlist = new Admission[patient.numberOfAdmissions];//creates a new instance of the class
		int count = 0;//sets pointer to index 0
		while(count<patient.numberOfAdmissions)//ruins a for loop for every admission a patient has
		{
			//System.out.println(count);
			//System.out.println(patient.patientID+"_Admissions.txt");
			String[] stringedAdmission = rff(patient.patientID+"_Admissions.txt",count);//retireves the liist of admissions rom file
			//System.out.println("number of admisions is "+stringedAdmission.length);
			if(stringedAdmission[count]!="null")//selection determining if admission at this index is not null
			{
				tempAdmission = setRetrievedAdmission(stringedAdmission);//retrieves individual information from string
				admissionlist[count] =tempAdmission;//assigns retrieved values to the arary
				count++;//iterates onto next index
			}
		}
		return(admissionlist);
	}
	public void updatePatientAdmission (Patient patient,Admission admission)
	{
		String[] stringedAdmissionList =rffReturnFullFile(patient.patientID+"_Admissions.txt");//retireves the liist of admissions rom file
		String stringAdmissionID = admission.admissionID;//retrieves the admission id from the array
		int admissionPosition = findPositionOfString(stringedAdmissionList,stringAdmissionID);//finds the position of the admissions location
		String stringedSymptoms = (admission.listOfSymptoms[0]+"|"+admission.listOfSymptoms[1]+"|"+admission.listOfSymptoms[2]+"|");//concatenates information togehter
			String concatenatedAdmission = (admission.admissionID+","+admission.ward+","+admission.consultantName+","+ftTimeInc.format(admission.dateOfNextAppointmentA)+","+admission.active+","+admission.numberOfDocuments+","+admission.medication+","+admission.room+","+admission.currentDiagnosis+","+stringedSymptoms+","+admission.staffName+","+ft.format(admission.dateAdmissionCreated)+","+admission.admissionsStaffID+","+admission.admissionsConsultantID);//concatenates information togehter
		stringedAdmissionList[admissionPosition] = concatenatedAdmission;//correctly updates poisition to contain the relative informaiton
		int length = stringedAdmissionList.length;//finds the length of the array
		wtf(stringedAdmissionList,patient.patientID+"_Admissions.txt",length);//writes the array to file
	}
}  