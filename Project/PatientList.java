import java.awt.*;//imports the java awt package,by using a wildcard all aspects of the package are imported
import java.awt.font.TextAttribute;//imports the text attribute package 
import java.awt.event.*;//imports the java awt event package,by using a wildcard all aspects of the package are imported
import javax.swing.*;//imports the java swing package,by using a wildcard all aspects of the package are imported
import java.util.*;//imports the java utlities module
import java.text.SimpleDateFormat;//imports the simple date format package which allows for more simple dates
import java.text.ParseException;//imports the parse exception package
import java.io.*;//imports the io package
public class PatientList extends Patient
{
	//======================== Entity Attributes ===================
	
	Patient[] patientList;//creates a list of patients
	Patient tempPatient;//declares a new instance of patient
	int nextPosition;//declares attribute which holds the position of the next patient 
	SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
	
	//==================== ACCOUNT CREATION/EDITING ==================
	
	//Generates a random password
	//sets any non user input vars like date and time number of admissions etc
	//concatenates them alltogether
	//writes to file
	public Patient createNewPatient(Patient patient)
	{
		
		//System.out.println(patient.patientID);		
		patient.password = generatePassword();//creates a password
		//System.out.println(patient.password);
		Date currentTime = new Date();//declares the date of the closest appointment
		patient.daysSinceLastUpdate=currentTime;//updates the patients time to the current time
		patient.numberOfAdmissions= 0;//sets the number of admissions to 0 as none have been created
		
		
		
		//validation for new lines
		patient.allergies = patient.allergies.replace("\n", "#");//replaces \n with # prevent new lines from being created
		patient.disability = patient.disability.replace("\n", "#");//replaces \n with # prevent new lines from being created
		patient.allergies = patient.allergies.replace(",", "@");//replaces \n with # prevent new lines from being created
		patient.disability = patient.disability.replace(",", "@");//replaces \n with # prevent new lines from being created
		String patientDemo=patient.patientID+","+patient.password+","+patient.surName+","+patient.firstName+","+patient.addressHouseNum+","+patient.addressHouseStreet+","+patient.town+","+patient.postcode+","+patient.contactNum+","+patient.nationality+","+patient.bloodType+","+patient.smoker+","+patient.drinker+","+patient.numberOfAdmissions+","+ft.format(patient.dob)+","+patient.religon+","+patient.allergies+","+patient.gender+","+patient.disability+","+patient.carer+","+patient.translator+","+patient.sex+","+patient.county+","+ft.format(patient.daysSinceLastUpdate)+","+patient.linkedStaffID;//concatenates all the attributes togeher
		//WRITES PATIENT TO FILE
		try//this will try to run
		{
			FileWriter fw = new FileWriter(patient.patientID+"_file.txt");//declare a new file writer that will ammend not not write to file
			fw.write(encrypt(patientDemo));//writes the curent index to line
			fw.write("\r\n");//adds in a space to the file
			fw.write(encrypt("0"));//adds in a space to the file
			fw.close();//closes the file
		}
		catch(Exception exc)//if any error occurs the error is caught
		{
			System.out.println("WRITE error");//informs that a write error has occured
			exc.printStackTrace();//prints the stack trace
		}
		return(patient);//returens new instance of patient 
	}
	
	//The final method which calls the write method Now we have the new data to write to file
	//This will read the old file and save it to the array
	//The new data replaces the old data at the desired line
	//The new array of data is then written back to file
	public void updateDemographicfile(int line,String filename,String newData)//declares a procedure that writes the array to file using the object from the patient to be passed through
	{
		String[] arrayOfData = rffReturnFullFile(filename);
		arrayOfData[line] = newData;
		writeNewDataTofile(arrayOfData,filename);
	}



}
	