import java.util.*;//imports the java utlities module
import java.io.*;//imports the io package
import java.text.SimpleDateFormat;//imports the simple date format package which allows for more simple dates
public class Staff extends Employee
{
	String staffID;//declares attribute which holds thier staffID
	public static void main(String[] args )//declaring the main method
    {
        Staff staff = new Staff();
    }
	/*
	public Staff retrieveStaffInfo()
	{
		String[] newStaffDetails=rff("StaffInfo.txt");
		Staff staff = new Staff();//creates an empty object  of bankclient to add values read from file
		staff.staffID = newStaffDetails[0];
		staff.employeeID = newStaffDetails[1];
		staff.surName = newStaffDetails[2];
		staff.firstName = newStaffDetails[3];
		staff.wage = Double.parseDouble(newStaffDetails[4]);
		staff.hoursPerWeek = Integer.parseInt(newStaffDetails[5]);
		staff.archived = Boolean.parseBoolean(newStaffDetails[6]);
		staff.addressHouseNum = Integer.parseInt(newStaffDetails[7]);
		staff.addressHouseStreet = newStaffDetails[8];
		staff.town = newStaffDetails[9];
		staff.postcode = newStaffDetails[10];
		staff.contactNum = newStaffDetails[11];
		try
		{
			staff.dob = ft.parse(newStaffDetails[12]);
		}
		catch(Exception exc)//if any errrors are found they are caught here		
		{
			
		}
		staff.gender = newStaffDetails[13].charAt(0);
		return(staff);
	}
	*/
}