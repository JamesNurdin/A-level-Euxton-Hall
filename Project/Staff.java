import java.util.*;//imports the java utlities module
import java.io.*;//imports the io package
import java.text.SimpleDateFormat;//imports the simple date format package which allows for more simple dates
import javax.swing.*;//imports the java swing package,by using a wildcard all aspects of the package are imported

public class Staff extends Employee
{
	//======================== Entity Attributes ===================
	
	String staffID;//declares attribute which holds thier staffID
	String[] patientIDs;//an array which holds all their associated patients 
	int numberOfPatients;
	//Method which retreieves the staff from file
	//first step is the pulling of details from the actuall file(using the id as a parameter the full contents are pulled, eventually admissions will be pulled from this method also)
	//Next a new array holding all the values from the first line is called, splits every user defined "sub index" indicated by a comma or # if a "sub sub index of an index etc"
	//a new instance is called and all the attributes are assigned to the correct variable for the object
	//To avoid formatting errors when writing to file similar to the process of encryption any variable that uses \n is replaced by a # and vice versa for the opposing action
	//Any array variables have their attributes also assigned
	//Finally instance is then returned
	public Staff retrieveStaff(String staffIDLocal)
	{
		SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");

		String[] fileContents = rffReturnFullFile(staffIDLocal+"_file.txt");//concatinates the string into an array
		String[] newStaffDetails = fileContents[0].split(",");
		String[] newStaffDetailsLine2 = fileContents[1].split(",");
		Staff tempStaff = new Staff();//creates a new instance of the class
		if(newStaffDetails[0]!="Null")
		{
			tempStaff.staffID = newStaffDetails[0];//the attribute is set from the correct index from the array
			tempStaff.employeeID = newStaffDetails[1];//the attribute is set from the correct index from the array
			tempStaff.password = newStaffDetails[2];//the attribute is set from the correct index from the array
			tempStaff.surName = newStaffDetails[3];//the attribute is set from the correct index from the array
			tempStaff.firstName = newStaffDetails[4];//the attribute is set from the correct index from the array
			tempStaff.addressHouseNum = Integer.parseInt(newStaffDetails[5]);//the attribute is set from the correct index from the array
			tempStaff.addressHouseStreet = newStaffDetails[6];//the attribute is set from the correct index from the array
			tempStaff.town = newStaffDetails[7];//the attribute is set from the correct index from the array
			tempStaff.postcode = newStaffDetails[8];//the attribute is set from the correct index from the array
			tempStaff.contactNum = newStaffDetails[9];//the attribute is set from the correct index from the array
			tempStaff.nationality = newStaffDetails[10];//the attribute is set from the correct index from the array
			tempStaff.bloodType = newStaffDetails[11];//the attribute is set from the correct index from the array
			tempStaff.smoker = Integer.parseInt(newStaffDetails[12]);//the attribute is set from the correct index from the array
			tempStaff.drinker = Integer.parseInt(newStaffDetails[13]);//the attribute is set from the correct index from the array			try
			try
			{
				tempStaff.dob = ft.parse(newStaffDetails[14]);//the attribute is set from the correct index from the array
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{
			}
			tempStaff.religon = newStaffDetails[15];//the attribute is set from the correct index from the array
			tempStaff.allergies = newStaffDetails[16];//the attribute is set from the correct index from the array
			tempStaff.gender = newStaffDetails[17];//the attribute is set from the correct index from the array
			tempStaff.disability = newStaffDetails[18];//the attribute is set from the correct index from the array
			if(tempStaff.disability!=null)
			{
			tempStaff.disabled = false;	
			}
			tempStaff.carer = Boolean.parseBoolean(newStaffDetails[19]);//the attribute is set from the correct index from the array
			tempStaff.translator = Boolean.parseBoolean(newStaffDetails[20]);//the attribute is set from the correct index from the array
			tempStaff.sex = newStaffDetails[21];//the attribute is set from the correct index from the array
			tempStaff.county = newStaffDetails[22];//the attribute is set from the correct index from the array
			try
			{
				tempStaff.daysSinceLastUpdate = ft.parse(newStaffDetails[23]);//the attribute is set from the correct index from the array
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{
			}
			tempStaff.wage = Double.parseDouble(newStaffDetails[24]);//the attribute is set from the correct index from the array
			tempStaff.hoursPerWeek = Integer.parseInt(newStaffDetails[25]);//the attribute is set from the correct index from the array
			tempStaff.archived = Boolean.parseBoolean(newStaffDetails[26]);//the attribute is set from the correct index from the array
			tempStaff.numberOfPatients = Integer.parseInt(newStaffDetailsLine2[0]);//the attribute is set from the correct index from the array
			tempStaff.patientIDs = new String[tempStaff.numberOfPatients];
			for(int counterPatientsAvaialbe = 0;counterPatientsAvaialbe<tempStaff.numberOfPatients;counterPatientsAvaialbe++)
			{
			tempStaff.patientIDs[counterPatientsAvaialbe]=newStaffDetailsLine2[counterPatientsAvaialbe+1];
			}
	}
		
		tempStaff.allergies = tempStaff.allergies.replace("#", "\n");//replaces \n with # prevent new lines from being created
		tempStaff.disability = tempStaff.disability.replace("#", "\n");//replaces \n with # prevent new lines from being created
		return(tempStaff);
	}
	
	//While the GUI class retrieves the new written data and updates the instance if not saved to file it wont be there next time the system is ran
	//This method will always write whatever the current instance is to its rightful file allowing anytype of ammendment to be made without the need to call a laborious set of methods
	//Using the instances as a parameter the details are then prepaired to get written to file (Any arays get concatenated)
	//Any large strings get theri new lines replaced with "tokens" which wont screw up the file writing
	//finally the details are concatenated and then passed through to the file writing method which actually writes it to file
	
	public void updateDemographicDetails(Staff staff)
	{
		Date currentTime = new Date();//declares the date of the closest appointment
		staff.daysSinceLastUpdate=currentTime;//updates the patients time to the current time
		staff.allergies = staff.allergies.replace("\n", "#");//replaces \n with # prevent new lines from being created
		staff.disability = staff.disability.replace("\n", "#");//replaces \n with # prevent new lines from being created
		String stafDemo=staff.staffID+","+staff.employeeID+","+staff.password+","+staff.surName+","+staff.firstName+","+staff.addressHouseNum+","+staff.addressHouseStreet+","+staff.town+","+staff.postcode+","+staff.contactNum+","+staff.nationality+","+staff.bloodType+","+staff.smoker+","+staff.drinker+","+ft.format(staff.dob)+","+staff.religon+","+staff.allergies+","+staff.gender+","+staff.disability+","+staff.carer+","+staff.translator+","+staff.sex+","+staff.county+","+ft.format(staff.daysSinceLastUpdate)+","+staff.wage+","+staff.hoursPerWeek+","+staff.archived;//concatenates all the attributes togeher
		StaffList sl = new StaffList();//creates a new instance of the class
		sl.updateDemographicfile(0,staff.staffID+"_file.txt",stafDemo);//writes entire array to file
		
		
	}
	
	
	//This method will be used to validate every staff on the system, it will be used to validate any field entered by the user on the system
	//If any field is wrong the system will retuern false to the user and will display why it is wrong
	//it works by validating every field on the entity that needs to it will perform a range of validations on the data as specified in the design document
	//the purpose is to identify any erroneous inputs made by the user whether intended or not
	public boolean validateStaffInput(Staff staff)
	{
		boolean validated = true;
		
		//firstname 
		validated=presenceValidation(staff.firstName);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Firstname, missing.");
			return false;
		}
		validated=typeValidationString(staff.firstName);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Firstname, letters only.");
			return false;
		}
		validated=staff.lesserLengthValidation(staff.firstName,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Firstname, must be less than 26 characters.");
			return false;
		}
		
		//surname
		//presence/type check/length
		validated=staff.presenceValidation(staff.surName);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Surname, missing.");
			return false;
		}
		validated=staff.typeValidationString(staff.surName);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Surname, letters only.");
			return false;
		}
		validated=staff.greaterLengthValidation(staff.surName,2);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Surname, must be three characters.");
			return false;
		}
		validated=staff.lesserLengthValidation(staff.surName,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Surname, must be less than 26 characters.");
			return false;
		}
		
		//postcode
		//length i.e missing gap
		validated=staff.equalsualLengthValidation(staff.postcode,7);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Postcode, must be 7 characters.");
			return false;
		}
		
		//house number
		//presnce/dataTYPE INT
		validated=staff.presenceValidation(staff.addressHouseNum+"");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid house number, missing.");
			return false;
		}
		
		//street
		//presnce datatype String
		validated=staff.presenceValidation(staff.addressHouseStreet);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid street name, missing.");
			return false;
		}
		validated=staff.typeValidationString(staff.addressHouseStreet);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid street name, letters only.");
			return false;
		}
		validated=staff.lesserLengthValidation(staff.addressHouseStreet,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid street name, must be less than 26 characters.");
			return false;
		}
		//town
		//presence datatype
		validated=staff.presenceValidation(staff.town);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid town, missing.");
			return false;
		}
		validated=staff.typeValidationString(staff.town);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid town, letters only.");
			return false;
		}
		validated=staff.lesserLengthValidation(staff.town,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid town, must be less than 26 characters.");
			return false;
		}
		//county
		//presence datatype
		validated=staff.presenceValidation(staff.county);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid county, missing.");
			return false;
		}
		validated=staff.typeValidationString(staff.county);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid county, letters only.");
			return false;
		}
		validated=staff.lesserLengthValidation(staff.county,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid county, must be less than 26 characters.");
			return false;
		}
		//contact number
		validated=staff.equalsualLengthValidation(staff.contactNum,11);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid contact number, not 11 digigts.");
			return false;
		}
		validated=staff.typeValidationInt(staff.contactNum);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid contact number, not int.");
			return false;
		}
		
		//nationality
		//presence datatype 
		validated=staff.presenceValidation(staff.nationality);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid nationality, missing.");
			return false;
		}
		validated=staff.typeValidationString(staff.nationality);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid nationality, letters only.");
			return false;
		}
		validated=staff.lesserLengthValidation(staff.nationality,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid nationality, must be less than 26 characters.");
			return false;
		}
		//religon
		//presence datatype
		validated=staff.presenceValidation(staff.religon);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid religon, missing.");
			return false;
		}
		validated=staff.typeValidationString(staff.religon);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid religon, letters only.");
			return false;
		}
		validated=staff.lesserLengthValidation(staff.religon,25);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid religon, must be less than 26 characters.");
			return false;
		}
		//gender
		if(staff.gender.equals("Please select the gender you identify as."))
		{
			JOptionPane.showMessageDialog(null, "please select a Gender.");
			return false;
		}
		
		//sex
		if(staff.sex.equals("Please select the sex you are"))
		{
			JOptionPane.showMessageDialog(null, "please select a sex.");
			return false;
		}
		
		//bloodtype
		if(staff.bloodType.equals("Please select a Blood Type."))
		{
			JOptionPane.showMessageDialog(null, "please select a bloodtype.");
			return false;
		}
		//disiblites
		validated=staff.checkCharIsNOTpresent(staff.disability,"#");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid character used, #.");
			return false;
		}
		validated=staff.checkCharIsNOTpresent(staff.disability,",");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid character used, ,.");
			return false;
		}
		validated=staff.lesserLengthValidation(staff.disability,100);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid text, must be less than 101 characters.");
			return false;
		}
		
		//allergies
		validated=staff.checkCharIsNOTpresent(staff.allergies,"#");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid character used, #.");
			return false;
		}
		validated=staff.checkCharIsNOTpresent(staff.allergies,",");
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid character used, ,.");
			return false;
		}
		validated=staff.lesserLengthValidation(staff.allergies,100);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid text, must be less than 101 characters.");
			return false;
		}
		validated=staff.presenceValidation(staff.allergies);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Allergies, missing.");
			return false;
		}
		
		return true;
	}
}

