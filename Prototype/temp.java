import java.awt.*;//imports the java awt package,by using a wildcard all aspects of the package are imported
import java.awt.font.TextAttribute;//imports the text attribute package 
import java.awt.event.*;//imports the java awt event package,by using a wildcard all aspects of the package are imported
import javax.swing.*;//imports the java swing package,by using a wildcard all aspects of the package are imported
import java.util.*;//imports the java utlities module
import java.text.SimpleDateFormat;//imports the simple date format package which allows for more simple dates
import java.text.ParseException;//imports the parse exception package
import java.io.*;//imports the io package
import javax.swing.Box;//
public class temp extends JFrame implements ActionListener//declares the clsas that inherits everything from the JFrame class which allows us to create coponenets of GUIS from the JFrame library 
{
	//GUI global vars
	int endPanelPointer =0;
	
	int loginChoice=-1;//declares a global int to allow the user to decide on what login they are choosing allowing for the correct components to be created
	String[] bloodTypes = new String[] {"Please select a Blood Type","A+", "A-","B+","B-","O+","O-","AB+","AB-"};//
	boolean[][] loaded = new boolean[15][15];//declares the array that has the empty array that holds the state of the panels being loaded
	boolean acceptSymptoms;//declares state for attribute whether symptoms are acceptable for an admission
	String[] genders = new String[] {"Please select a Gender","M", "F","Other"};//declares list of gender options
	String[] sortOptionsAdmissions = new String[] {"Sort By","Newest First", "Oldest First","Alphabetical"};//declares list of sorts for the admission
	String[] sortOptionsJargon = new String[] {"A-Z","Z-A"};//declares list of sort orders
	String[] summaryOptionsArray = new String[] {"Inconclusive","No issues", "Action required","Urgent"};//declares list of summary for the test results
	String[] listOfAllConsultantSnames;//declares list of all consultants names, it is initalised later when the list of consultants is retrieved
	Date closestAppointment = new Date();//declares the date of the closest appointment
	Date todaysDate = new Date();//initalises the current date for the system
	String closestAppointmentDay;//initalises the string for the closest appointment
	String closestAppointmentTime;//initalises the string for the closest appointment
	String closestAppointmentConsultant;//initalises the string for the closest appointment
	String closestAppointmentPatient;//initalises the string for the closest appointment
	String username;//initalises the string for the username
	boolean date = false;//declares the attribute to determine whether a recent date has been found
	
	int numberOfDefenitions;//initalises the number of definitions
	int currentPageDefenitions;//initalises the current page for the definitions
	int currentPageIndexDefenitions;//initalises the index for the correct page
	String[][] listOfDefinitions;//declares list of defintions for the system
	String[][] listOfDefinitionsUpdated;//declares list of definitions when updated
	
	//mains users
	
	Staff currentStaff = new Staff();//declares a new instance of staff
	
	int actionPerformed = -1;//declares the action performed 
	
	//patient
	
	Patient currentPatient;//initialises the current patient
		int	numberOfNotifications;//declares the number of notifications the user currently has this will be needed to find out how many iterations on the algerithm for removing the notifications 
		String[] notifications;//initalises the array for the notifications
		//Homepage
		String nextAppointmentDate;//initalises the date for the next appointment
		String nextAppointmentTime;//initalises the time for the next appointment
		String nextAppointmentConsultant;//initalises the consultant for the next appointment
		//Admission page
		//temp data type (CHANGE LATER TO CORRECT CLASS)
		Admission currentAdmission;//initalises the current admission
		Admission tempAdmission;
		Admission[] admissionList;//initalises the list for the current admissions
		int numberOfAdmissions;//initalises the number of admissions
		int indexofadmission;
		//Admission--> Document page
		Document[] listOfdocumentsGUI;//initalises the list of documents
		Document[] singleDefinition;
		Document currentDocument;//initalises the current document
		Document tempDoc;
		int indexofDoc;
		int numberOfDocuments;//initalises the number of documents
		int currentPage;//initalises the current page for the document
		int currentPageIndex;//initalises the index for the current page
	
	//new user
	boolean newUser;
	//consultant 
	
	Consultant currentConsultant;//initalises the current consultant
	Consultant replacementConsultant;//initalises the replacement consultant 
	Consultant[] listOfAllConsultants;//initalises the list of consultants
	int numberOfConsultants;//initalises the number of consultants
	int numberOfConsultantPatients;//initalises the consultants for a specific patient 
	Patient[] listOfCsPatients;//initalises the list of patients that the consultant has
	Patient searchedPatient = new Patient();
	PatientList listOfConsultantsPatients = new PatientList();//initalises an instance of patient list
	Admission[][] listofAdmissions = new Admission[100][10];//initalises the list of admissions for every patient 
	Admission[] tempAdmissionList;//initalises the a list for every admisison
	Admission[] admissionListCopy;//initalises the list for the current admissions
		int currentPageConsultant;//initalises the page location for the current consultant
		int currentPageIndexConsultant;//initalises the index location for the current consultant's patient 
		int currentPatientConsultantIndex;//initalises the index location for the current consultant's patient 
		Patient consultantPatient = new Patient();//initalises the instance for an attribute for the selected consultant patient 
		Admission consultantAdmission = new Admission();//initalises the instance for an attribute for the selected consultant admission  
		int currentNumberOfPatientAdmissionsConsultant;//initalises the number of admissions the selected patient has for the consultant
		int locationOfReplacementConsultant;//initalises the pointer to the location where the desired consultant is located in  list of all consultants
		int indexOfPatient;
		int finalIndexOfAdmission=0;
		int indexOfCurrentPatient;
		AdmissionList al;//initalises the admission list instance
		
	Managment currentManager = new Managment();//initalises the instance for the current currentManager
	Admission oldAdmissionInfo  = new Admission();//initalises the admission that is used to store older information
	
	//panels and frames
	JPanel[] panelOrder = new JPanel[1000];//declares the start panel on which the system starts on
    JPanel startPanel = new JPanel(null);//declares the start panel on which the system starts on
	JPanel loginPanel = new JPanel(null);//declares the login panel which will be needed to allow the user to login whenever they need to
	JPanel symptomPanel = new JPanel(null); //declares the symptom panel this is needed to be used when cerating a new admisison
	JPanel patientHomepagePanel = new JPanel(null);//declares the homepage for the patient where they can acess all their information
	JPanel consultantHomepagePanel = new JPanel(null);//declares the homepage for the patient where they can acess all their information
	JPanel adminHomepagePanel = new JPanel(null);//declares the homepage for the patient where they can acess all their information
	JPanel newAdmissionPanel = new JPanel(null);//declares the homepage for the patient where they can acess all their information
	JPanel currentPanel = new JPanel(null);//declares the panel the current user is using this will be to remove the panel no longer in use
	JPanel oldpanel = new JPanel(null);//declares the panel that will be removed every time a new one is added this can be used to reduce workload on the GUI
	JPanel symptomRecomendationPanel = new JPanel(null);//declares the panel for the symptom panel
	JPanel symptomNewPatientPanel = new JPanel(null);//declares the panel for the new symptom panel
	JPanel createPatientPanel = new JPanel(null);//declares the homepage for the patient where they can acess all their information
	JPanel jargonLibraryHomepagePanel = new JPanel(null);//declares the homepage for the patient where they can acess all their information
	JPanel demographicHomepagePanel = new JPanel(null);//the declares the demographic home page panel this will be for the demographic of a patient
	JPanel admissionHomepagePanel = new JPanel(null);//admission homepage this will be for the 
	JPanel emptyAdmissionHomepagePanel = new JPanel(null);//admission homepage this will be for the 
	JPanel documentPanel = new JPanel(null);//declares the panel the documents to be viewed
	JPanel consultantAdmissionPanel= new JPanel(null);//declares the panel for the admission page the current consultant
	JPanel consultantPDemographicPanel= new JPanel(null);//declares the panel for the demographic page the current consultant
	JPanel createNewDocumentPanel= new JPanel(null);//declares the panel for the new document page
	JPanel createEditAdmissionPanel= new JPanel(null);//declares the panel for the edit admission page
	JFrame frame = new JFrame();//declares the global frame on which the entire system runs ontop of, panels are added to this in order to be seen
	
	//Start screen components
	JLabel lblLogo = new JLabel();//declares a labbel for the logo, no text will be used and instead will import an image from the folder
 	JLabel mainHeaderlbl = new JLabel();//declares a label for the main header for the system 
	JButton loginBttn = new JButton();//declares a button to allow the user to move to the login panel
	JButton symptomButtn = new JButton();//declares a button to allow the user to move to the symptom panel
	JLabel helpLbl = new JLabel();//declares a labbel for the user to see what to do, it is a help label so informs the user on what to do
	
	//Login screen components
	JLabel loginHeaderlbl = new JLabel();//declares a labbel for the main header on the login screen 
	JButton loginBackToStartBttn = new JButton();//declares a button to allow the user to return back to the start screen if they wish
	JButton selectPatientloginBttn = new JButton();//declares a button to allow the user to login as a patient
	JButton selectAdminloginBttn = new JButton();//declares a button to allow the user to login as admin
	JButton selectManagementloginBttn = new JButton();//declares a button to allow the user to login as managment
	JButton selectConsultantloginBttn = new JButton();//declares a button to allow the user to login as a consultant
	JLabel entitylbl = new JLabel();//declares a labbel for the specific this will be aside the text field and will indicate which type of account the user is logging in as
	
	JTextField usernameTF = new JTextField();//declares a new text field that wiill contain the username the user enters when trying to login
	JPasswordField loginPasswordF = new JPasswordField();//declares the password field the user will enter when trying to logon to the system 
	JButton realLoginbttn = new JButton();//declares a button to allow the system to compare the credentials the user has entered
	JButton createAccountbbtn = new JButton();//declares a button to allow the user to create an account of that type
	
	//symptom screen components 
	JLabel humanImage = new JLabel();//declares a labbel for an image of a human which will allow for an easier understanding from where the pain resonates from
	JLabel symptomHeaderlbl = new JLabel();//declares a labbel for the main header of the symptoms area of the screen
	JButton symptomBackToStartBttn = new JButton();//declares a button to allow the user go back to the start screen if they wish
	JCheckBox cbNeck = new JCheckBox("Neck");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	JCheckBox cbChest = new JCheckBox("Chest");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	JCheckBox cbHand = new JCheckBox("Hand");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	JCheckBox cbFoot = new JCheckBox("Foot");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	JCheckBox cbHead = new JCheckBox("Head");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	JCheckBox cbBack = new JCheckBox("Back");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	JCheckBox cbAbdomen = new JCheckBox("Abdomen");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	JCheckBox cbArm = new JCheckBox("Arm");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	JCheckBox cbPelvis = new JCheckBox("Pelvis");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	JCheckBox cbLeg = new JCheckBox("Leg");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	
	JTextPane painCbLBL = new JTextPane();//declares a text pane to allow for help to be given for the user to understand on what to do on this area
	JTextPane typepainCbLBL = new JTextPane();//declares a text pane to allow for help to be given for the user to understand on what to do on this area
	JTextPane symptomsCbLBL = new JTextPane();//declares a text pane to allow for help to be given for the user to understand on what to do on this area
	JTextPane symptomsTfCbLBL = new JTextPane();//declares a text pane to allow for help to be given for the user to understand on what to do on this area
	JTextPane symptomhelp = new JTextPane();//declares a text pane for the user to see what to do, it is a help text pane so informs the user on what to do
	
	JTextField symptom1TF = new JTextField();//declares a text field which will be used to allow for other symptoms to be entered on the system
	JTextField symptom2TF = new JTextField();//declares a text field which will be used to allow for other symptoms to be entered on the system
	JTextField symptom3TF = new JTextField();//declares a text field which will be used to allow for other symptoms to be entered on the system
	JTextField symptom4TF = new JTextField();//declares a text field which will be used to allow for other symptoms to be entered on the system
	
	JCheckBox cbChronicPains = new JCheckBox("Chronic pains");//declares a text box that will be used to describe the type of pain the patient is experiecing 
	JCheckBox cbAcutePains = new JCheckBox("Acute pains");//declares a text box that will be used to describe the type of pain the patient is experiecing 
	JCheckBox cbStiffnessInMuscle = new JCheckBox("Stiffness in muscle");//declares a text box that will be used to describe the type of pain the patient is experiecing 
	JCheckBox cbFrequentRecurringPains = new JCheckBox("Frequent recurring pains");//declares a text box that will be used to describe the type of pain the patient is experiecing 
	
	/*
	these checkboxes are for some common symptoms to allow for a faster acess for the user.
	*/
	JCheckBox cbWeightLoss = new JCheckBox("Weight loss");//declares a checkbox for the common symptom
	JCheckBox cbNausea = new JCheckBox("Nausea");//declares a checkbox for the common symptom
	JCheckBox cbFever = new JCheckBox("Fever");//declares a checkbox for the common symptom
	JCheckBox cbFatigue = new JCheckBox("Fatigue");//declares a checkbox for the common symptom
	JButton SymptomConfirmBttn = new JButton();//declares a button to allow the user to confirm these are the symptoms they are experiencing 
	
	//symptom recomendation panel once symptoms have been accepted for a real concern these will be needed
	JButton symptomRecomendationBackToExpertSystem = new JButton();//declares the button which returns the user to the prior panel
	JButton symptomRecomendationNewAdmissionBttn = new JButton();//declares the button which creates a new admission
	//temp one 
	JButton symptomRecomendationcreateAccountBttn = new JButton();//declares the button which creates a new account
	JTextPane mainAdviceJPane = new JTextPane();//declares the text pane for the main advice this will be used for both panels
	JTextPane main2AdviceJPane = new JTextPane();//declares the text pane for the main advice this will be used for one of the panels
	JTextPane minorAdviceJPane = new JTextPane();//declares the text pane for the main advice this will be used for one of the panels
	JRadioButton recommendationFalseAcceptanceRB1 = new JRadioButton();//declares the radio button that will be used to confirm the user accepts a term
	JRadioButton recommendationFalseAcceptanceRB2 = new JRadioButton();//declares the radio button that will be used to confirm the user accepts a term
	JRadioButton recommendationFalseAcceptanceRB3 = new JRadioButton();//declares the radio button that will be used to confirm the user accepts a term
	//General components that are used throughout the entire system but are easy to define their use
		//top white bar
		JTabbedPane GeneralTabBar = new JTabbedPane();
	JButton homebttn = new JButton();//declares a button to allow the user to move to the symptom panel
	JButton demographicbttn = new JButton();//declares a button to allow the user to move to the symptom panel
	JButton admissionbttn = new JButton();//declares a button to allow the user to move to the symptom panel
	JButton jargonLibrarybttn = new JButton();//declares a button to allow the user to move to the symptom panel
	JButton newPatientbttn = new JButton();//declares a button to allow the user to move to the symptom panel
	JButton logoutbttn = new JButton();//declares a button to allow the user to move to the symptom panel
	JButton consultantPatientAdmission = new JButton();//declares a button to allow the user to move to the symptom panel
	JButton consultantPatientDemographic = new JButton();//declares a button to allow the user to move to the symptom panel
	JOptionPane logoutConfirm = new JOptionPane();//declares a new pop up window for the user
	
	//top white bar Admission
	JButton topBarAdmission1Bttn = new JButton();//declares the button which is for the top tab bar
	JButton topBarAdmission2Bttn = new JButton();//declares the button which is for the top tab bar
	JButton topBarAdmission3Bttn = new JButton();//declares the button which is for the top tab bar
	JButton topBarAdmission4Bttn = new JButton();//declares the button which is for the top tab bar
	JButton topBarAdmission5Bttn = new JButton();//declares the button which is for the top tab bar
	JButton topBarNewAdmissionBttn = new JButton();//declares the button which is for the top tab bar
	
	
		//contactBar general - declares all the components for the contact bar
	JLabel nameField = new JLabel();//declares the name feild that will contain the user's name
	JLabel phoneNumberPromptField = new JLabel();//declares the prompt feild which shows the user which part shows the users phone number
	JLabel phoneNumberField = new JLabel();//declares the feild that actually holds the user's phone number
	JLabel sexPromptField = new JLabel();//jlabel that holds label that shows the user that this area shows the user's
	JLabel sexField = new JLabel();//declares the actual feild that contains the patient's sex
	JLabel dobPromptField = new JLabel();//jlabel that holds label that shows the user that this area shows the user's dob
	JLabel dobField = new JLabel();//declares the actual feild that contains the patient's dob
	JLabel addressPromptFeild = new JLabel();//jlabel that holds label that shows the user that this area shows the user's address
	JLabel streetAddressField = new JLabel();//declares the actual feild that contains the patient's street address
	JLabel countyAddressField = new JLabel();//declares the actual feild that contains the patient's county
	JLabel postcodeField = new JLabel();//declares the actual feild that contains the patient's postcode
	JLabel userProfilePicfield = new JLabel();//declares a label for the profile picture
		
			//specific between two users
	JLabel nextAppointmentPromptField = new JLabel();//jlabel that holds label that  shows the user that this area shows the user's next appointment in general
	JLabel appointmentDatePromptField = new JLabel();//jlabel that holds label that shows the user that this area shows the user's next appointment date
	JLabel appointmentDateField = new JLabel();//declares the actual feild that contains the patient's date of appointment
	JLabel appointmentTimePromptField = new JLabel();//jlabel that holds label that shows the user that this area shows the user's next appointment time
	JLabel appointmentTimeField = new JLabel();//declares the actual feild that contains the patient's time of appointment
	
			//patient specific
	JLabel patientIDField = new JLabel();//declares the actual feild that contains the patient's id
	JLabel appointmentConsultantPromptField = new JLabel();//jlabel that holds label that shows the user that this area shows the user's appointment - consultant
	JLabel appointmentConsultantField = new JLabel();//declares the actual feild that contains the patient's consultanr
			//consultant specific
	JLabel consultantIDField = new JLabel();//declares the actual feild that contains the consultants's ID
	JLabel appointmentPatientPromptField = new JLabel();//jlabel that holds label that shows the user that this area shows the user's  appointment -  patient
	JLabel appointmentPatientField = new JLabel();//declares the actual feild that contains the consultant's patient
	JLabel wardPromptConsultantHomeFeild = new JLabel();//declares the label for the displaying of the ward prompt
	JLabel wardConsultantHomeFeild = new JLabel();//declares the label for the displaying of the ward 
	JLabel expertisePromptField = new JLabel();//declares the label for the displaying of expertises 
	JLabel expertiseField1 = new JLabel();//declares the label for the displaying of expertises 
	JLabel expertiseField2 = new JLabel();//declares the label for the displaying of expertises 
	JLabel expertiseField3 = new JLabel();//declares the label for the displaying of expertises 
	JButton reinstatePatientBttn = new JButton();//declares the button to reinstatement of the patient 
	boolean reinstatePatient;//declares the attribute for the reinstament status of the patient
			//Admin specific
	JLabel adminIDField = new JLabel();//declares the actual feild that contains the patient's admin id
	JLabel wagePromptField = new JLabel();//jlabel that holds label that shows the user that this area shows the user's wage
	JLabel wageField = new JLabel();//declares the actual feild that contains the patient's wage
	JLabel hoursPerWeekPromptField = new JLabel();//jlabel that holds label that shows the user that this area shows the admin's hours per week
	JLabel hoursPerWeekField = new JLabel();//jlabel that holds label that shows the user that this area shows the user's hours per week
	
	//userHomepageGeneral
	JLabel welcomeLbl = new JLabel();//declares the welcome label for the progoram, used to welcome the user to their homepage.
	JLabel homeLbl = new JLabel();//declares the label for the home indicating the panel the user is located
		//patient specific
	JButton newAdmissionBttn = new JButton();//declares the jbutton for them to create a new admission
	
	JLabel newAdmissionLbl = new JLabel();//declares the new admission label
	JLabel BlueboxBttn = new JLabel();//declares a labbel for a blue box to be created for astetic purposes and display a visual appeal to the user
	MouseListener mouseListenerAdmissionBttn = new MouseAdapter()// a new mouse listner is declared on the class
		{
			public void mouseReleased(MouseEvent mouseEvent)//the method mouse released looks out from when the pressure of the user is released and the mouse is no longer in an activated state
			//the parameter for the mouse event is used to check for this to occur
			{
				
				newAdmissionLbl.setVisible(true);//the component is set so it can be seen again
			}
			public void mouseClicked(MouseEvent mouseEvent)//the method mouse released looks out from when the pressure of the user is released and the mouse is no longer in an activated state
			//the parameter for the mouse event is used to check for this to occur
			{
				
				newAdmissionLbl.setVisible(false);//the component is set so it cant be seen and the blue button can
			}
		};
	JButton admissionCardviewAdmission1bbtn = new JButton();//declares the jbutton which allows the current user to view this particular admission
	JButton admissionCardviewAdmission2bbtn = new JButton();//declares the jbutton which allows the current user to view this current admission
	JLabel admission1Lbl = new JLabel();//declares the jlabel that shows the user the current admission underneath
	JLabel admission2Lbl = new JLabel();//declares the jlabel that shows the user the current admission underneath
	JLabel admissionCardConsultantLbl1 = new JLabel();//declares the jlabel that shows the user the current admission consultant
	JLabel admissionCardConsultantLbl2 = new JLabel();//declares the jlabel that shows the user the current admission consultant
	JLabel admissionCardConsultantFeild1 = new JLabel();//declares the jlabel that shows the user the current admission consultant
	JLabel admissionCardConsultantFeild2 = new JLabel();//declares the jlabel that shows the user the current admission consultant
	
	JLabel admissionCardDOALbl1 = new JLabel();//declares the jlabel that shows the user the current admission date of creation
	JLabel admissionCardDOALbl2 = new JLabel();//declares the jlabel that shows the user the current admission date of creation
	JLabel admissionCardDOAFeild1 = new JLabel();//declares the jlabel that shows the user the current admission date of creation
	JLabel admissionCardDOAFeild2 = new JLabel();//declares the jlabel that shows the user the current admission date of creation
	
	JLabel admissionCardNextAppointmentLbl1 = new JLabel();//declares the jlabel that shows the user the current admission date of next appointment
	JLabel admissionCardNextAppointmentLbl2 = new JLabel();//declares the jlabel that shows the user the current admission date of next appointment
	JLabel admissionCardNextAppointmentFeild1 = new JLabel();//declares the jlabel that shows the user the current admission date of next appointment
	JLabel admissionCardNextAppointmentFeild2 = new JLabel();//declares the jlabel that shows the user the current admission date of next appointment
	
	JLabel admissionCardDischargedLbl1 = new JLabel();//declares the jlabel that shows the user the current admission dischargment
	JLabel admissionCardDischargedLbl2 = new JLabel();//declares the jlabel that shows the user the current admission dischargment
	JLabel admissionCardDischargedFeild1 = new JLabel();//declares the jlabel that shows the user the current admission dischargment
	JLabel admissionCardDischargedFeild2 = new JLabel();//declares the jlabel that shows the user the current admission dischargment
	
	JTextPane noNotifications = new JTextPane();//declares a text pane for which the notifications are stored
	JLabel notificationLbl = new JLabel();//declares the jlabel that indicates this section is for the notification
	JLabel notificationBox1 = new JLabel();//declares the jlabel that will be used to create a box for a particular notification
	JLabel notificationBox2 = new JLabel();//declares the jlabel that will be used to create a box for a particular notification
	JLabel notificationBox3 = new JLabel();//declares the jlabel that will be used to create a box for a particular notification
	JLabel notificationBox4 = new JLabel();//declares the jlabel that will be used to create a box for a particular notification
	JLabel notificationBox5 = new JLabel();//declares the jlabel that will be used to create a box for a particular notification
	JTextPane notificationTf1 = new JTextPane();//declares the text pane that will hold the first notification 
	JTextPane notificationTf2 = new JTextPane();//declares the text pane that will hold the second notification 
	JTextPane notificationTf3 = new JTextPane();//declares the text pane that will hold the third notification 
	JTextPane notificationTf4 = new JTextPane();//declares the text pane that will hold the fourth notification 
	JTextPane notificationTf5 = new JTextPane();//declares the text pane that will hold the fith notification 
	JButton closeNotificationBttn1 = new JButton();//declares the jbutton which allows the current user to close the first notification infront of them
	JButton closeNotificationBttn2 = new JButton();//declares the jbutton which allows the current user to close the second notification infront of them
	JButton closeNotificationBttn3 = new JButton();//declares the jbutton which allows the current user to close the third notification infront of them
	JButton closeNotificationBttn4 = new JButton();//declares the jbutton which allows the current user to close the fourth notification infront of them
	JButton closeNotificationBttn5 = new JButton();//declares the jbutton which allows the current user to close the fith notification infront of them
	
	//demographic panel//new patient panel
	JButton deleteAccountBttn = new JButton();//demographic specific button
	JButton saveDemographicChanges = new JButton();//demographic specific button
	JButton demographicChangeProfilePic = new JButton();//demographic specific button
	JLabel demographicHeaderLbl = new JLabel();//declares a label that is used for the header
	JLabel demographicFNamePromptLbl = new JLabel();//declares a label that is used for the first name
	JLabel demographicSNPromptLbl = new JLabel();//declares a label that is used for the surname
	JLabel demographicDOBPromptLbl = new JLabel();//declares a label that is used for the date of birth
	JLabel demographicBuildingNUMPromptLbl = new JLabel();//declares a label that is used for the building number
	JLabel demographicStreetPromptLbl = new JLabel();//declares a label that is used for the street
	JLabel demographicTownPromptLbl = new JLabel();//declares a label that is used for the town
	JLabel demographicCountyPromptLbl = new JLabel();//declares a label that is used for the county
	JLabel demographicPostcodePromptLbl = new JLabel();//declares a label that is used for the postcode
	JLabel demographicGenderPromptLbl = new JLabel();//declares a label that is used for the gender
	JLabel demographicContactinfoPromptLbl = new JLabel();//declares a label that is used for the contact information
	JRadioButton demographicDrinkerPromptLbl = new JRadioButton();//declares a radio button that is used for the drinker status
	JRadioButton demographicSmokerPromptLbl = new JRadioButton();//declares a radio button that is used for the smoker status
	JLabel demographicAllergiesPromptLbl = new JLabel();//declares a label that is used for the allergies
	JLabel demographicNotioanlityPromptLbl = new JLabel();//declares a label that is used for the nationality
	JLabel demographicBloodTypePromptLbl = new JLabel();//declares a label that is used for the bloodtype
	JLabel demographicReligonPromptLbl = new JLabel();//declares a label that is used for the religon
	JRadioButton demographicDisablitiesPromptLbl = new JRadioButton();//declares a radio button that is used for the disabilities
	JRadioButton demographicCarerPromptLbl = new JRadioButton();//declares a radio button that is used for the carer status
	JRadioButton demographicTranslatorPromptLbl = new JRadioButton();//declares a radio button that is used for the translator status
	
	JTextField demographicFNameTextFeild = new JTextField();//declares the text feild that will hold the first name
	JTextField demographicSNTextFeild = new JTextField();//declares the text feild that will hold the surname
	JTextField demographicDOBTextFeild = new JTextField();//declares the text feild that will hold the date of birth
	JTextField demographicBuildingNUMTextFeild = new JTextField();//declares the text feild that will hold the building number
	JTextField demographicStreetTextFeild = new JTextField();//declares the text feild that will hold the street
	JTextField demographicTownTextFeild = new JTextField();//declares the text feild that will hold the town
	JTextField demographicCountyTextFeild = new JTextField();//declares the text feild that will hold the county
	JTextField demographicPostcode = new JTextField();//declares the text feild that will hold the postcode
	JComboBox<String> demographicGender = new JComboBox<String>(genders);//declares a combo box that holds the list of genders
	JTextField demographicContactinfo = new JTextField();//declares the text feild that will hold the contact information
	JTextArea demographicAllergies = new JTextArea();//declares the text area that will hold the allergies
	JTextField demographicNotioanlityTextFeild = new JTextField();//declares the text feild that will hold the nationality
	JComboBox<String> demographicBloodType = new JComboBox<String>(bloodTypes);//declares a combo box that holds the list of bloodtypes
	JTextField demographicReligonTextFeild = new JTextField();//declares the text feild that will hold the religon
	JTextArea demographicDisablitiesTextFeild = new JTextArea();//declares the text area that will hold the disabilities
	JTextPane newPatientHelpTF = new JTextPane();//declares the text pane that will hold a help for the user
		//new patient demographic page
	JButton newPatientBackToRecommendation = new JButton();//demographic specific, declares a button which returns the user back to the prior panel
	JButton newAccountcreateAccountbbtn = new JButton();//demographic specific, declares a button which creates a new account
	
	//patient admission home
	JLabel noAdmissionLbl = new JLabel();//declares a label for a box which is used for a visual improvement
	JLabel noAdmissionFootNoteLbl = new JLabel();//declares a label for a box which is used for a visual improvement
	JLabel lighterGreyAdmissionPanel = new JLabel();//declares a label for a box which is used for a visual improvement
	JLabel lightGreyTopPanelAdmissionSearchBar = new JLabel();//declares a label for a box which is used for a visual improvement for the search bar
	JTextField searchBoxAdmissionHpagePanel = new JTextField();//declares a text feild for the search box for the admission home page
	JComboBox<String> sortBoxAdmissionHpagePanel = new JComboBox<String>(sortOptionsAdmissions);//declares a combo box for the sort box for the admission cards
	JButton searchLibraryBttnAdmission = new JButton();//declares a button which searches the definition library
	JButton clearSearchbttnAdmission = new JButton();//declares a button which will remove all current searched items in the jargon library
	JTextPane  admissioncardTitlepanel1	= new JTextPane();//Declares the text pane for the title of the admission card
	JTextPane  admissioncardTitlepanel2	= new JTextPane();//Declares the text pane for the title of the admission card
	JTextPane  admissioncardTitlepanel3	= new JTextPane();//Declares the text pane for the title of the admission card
	JTextPane  admissioncardTitlepanel4	= new JTextPane();//Declares the text pane for the title of the admission card
	JTextPane  admissioncardTitlepanel5	= new JTextPane();//Declares the text pane for the title of the admission card
	JTextPane  admissioncardTitlepanel6	= new JTextPane();//Declares the text pane for the title of the admission card
	JButton viewDocument1AdmissionBttn = new JButton();//Declares the button for the view document of the admission card
	JButton viewDocument2AdmissionBttn = new JButton();//Declares the button for the view document of the admission card
	JButton viewDocument3AdmissionBttn = new JButton();//Declares the button for the view document of the admission card
	JButton viewDocument4AdmissionBttn = new JButton();//Declares the button for the view document of the admission card
	JButton viewDocument5AdmissionBttn = new JButton();//Declares the button for the view document of the admission card
	JButton viewDocument6AdmissionBttn = new JButton();//Declares the button for the view document of the admission card
	JTextPane  admissioncardDescriptionpane1 = new JTextPane();//Declares the text pane for the description of the admission card
	JTextPane  admissioncardDescriptionpane2 = new JTextPane();//Declares the text pane for the description of the admission card
	JTextPane  admissioncardDescriptionpane3 = new JTextPane();//Declares the text pane for the description of the admission card
	JTextPane  admissioncardDescriptionpane4 = new JTextPane();//Declares the text pane for the description of the admission card
	JTextPane  admissioncardDescriptionpane5 = new JTextPane();//Declares the text pane for the description of the admission card
	JTextPane  admissioncardDescriptionpane6 = new JTextPane();//Declares the text pane for the description of the admission card
	JButton admissionCardPageForwardBttn = new JButton();//Declares a button which will move the card forward
	JButton admissionCardPageBackBttn = new JButton();//Declares a button which will move the card backwards
		JButton viewDocument1AdmissionSpecificBttn = new JButton();//Declares the button for the view document of the admission card
		JButton viewDocument2AdmissionSpecificBttn = new JButton();//Declares the button for the view document of the admission card
		JButton viewDocument3AdmissionSpecificBttn = new JButton();//Declares the button for the view document of the admission card
		JButton viewDocument4AdmissionSpecificBttn = new JButton();//Declares the button for the view document of the admission card
		JButton viewDocument5AdmissionSpecificBttn = new JButton();//Declares the button for the view document of the admission card
		JButton viewDocument6AdmissionSpecificBttn = new JButton();//Declares the button for the view document of the admission card
		JButton viewDocument1AdmissionSearchBttn = new JButton();//Declares the button for the view document of the admission card

		//ContactBar for the admission homepage
	JLabel adminIDLbl = new JLabel();//Declares a label for the conact bar which shows the information for the admin ID
	JLabel ConsultantNamePromptFeild = new JLabel();//Declares a label for the conact bar which shows the information for the consultant name
	JLabel ConsultantNameFeild = new JLabel();//Declares a label for the conact bar which shows the information for the consultant name
	JLabel wardPromptFeild = new JLabel();//Declares a label for the conact bar which shows the information for the ward
	JLabel wardFeild = new JLabel();//Declares a label for the conact bar which shows the information for the ward
	JLabel dateOfAdmissionPromptField = new JLabel();//Declares a label for the conact bar which shows the information for the date of admission
	JLabel dateOfAdmissionField = new JLabel();//Declares a label for the conact bar which shows the information for the date of admission
	JLabel medicationPromptField = new JLabel();//Declares a label for the conact bar which shows the information for the medication
	JLabel medicationField = new JLabel();//Declares a label for the conact bar which shows the information for the medication
	JLabel dateOfNextAppointmentPromptField = new JLabel();//Declares a label for the conact bar which shows the information for the date of next appointment 
	JLabel dateOfNextAppointmentField = new JLabel();//Declares a label for the conact bar which shows the information for the date of the next appointment
	JLabel timeOfNextAppointmentPromptField = new JLabel();//Declares a label for the conact bar which shows the information for the time of the next appointment
	JLabel timeOfNextAppointmentField = new JLabel();//Declares a label for the conact bar which shows the information for the time of the nexxt appointment
	JLabel dischargmentStatusPromptField = new JLabel();//Declares a label for the conact bar which shows the information for the dischargment status
	JLabel dischargmentStatusField = new JLabel();//Declares a label for the conact bar which shows the information for the dischargment status
	
	JButton viewAppointmentsButton = new JButton();//Declares a button which is for the contact bar to view the appointments
	JButton requestReinstatementBttn = new JButton();//Declares a button to request a reinstatment used on the contact bar
	JButton editAdmissionBttn = new JButton();///Declares a button for the contact bar which allows the user to edit the admission
	
	//new admission Page specifiic
	JButton backToAdmissionbttn = new JButton();//Declares a button which returns the user to the prior panel
	
	//document pages
	JButton backToAdmissionsBttn = new JButton();//Declares a button which returns the user to the prior panel
	JButton nextDocumentBttn = new JButton();//Declares a button which will move the document panel onto the next document
	JButton priorDocumentBttn = new JButton();//Declares a button which will move the document panel onto the prior document
	JButton printDocumentBttn = new JButton();//Declares a button which allows the user to print off the current document
	JTextPane textDocumentTA = new JTextPane();//Declares a text pane which will hold the information for the document 
	JTextArea textDocumentTAND = new JTextArea();//Declares a text area which will hold the information for the document 
	JLabel patientIDDocumentLBL = new JLabel();//Declares a label to show text to the user
	JLabel admissionIDDocumentLBL = new JLabel();//Declares a label to show text to the user
	JLabel dateDocumentLBL = new JLabel();//Declares a label to show text to the user
	JLabel timeDocumentLBL = new JLabel();//Declares a label to show text to the user
	JLabel documentIDDocumentLBL = new JLabel();//Declares a label to show text to the user
	JLabel titleDocumentLBL = new JLabel();//Declares a label to show text to the user
	
	//consultant homepage
	JTextField searchBoxConsultantHpagePanel = new JTextField();//Declares a text field to hold any search queries the consultant has
	JComboBox<String> sortBoxConsultantHpagePanel = new JComboBox<String>(sortOptionsAdmissions);//declares a combo box that holds all the sort options for the consultant
	JButton ConsultantCardPageForwardBttn = new JButton();//declares a search button for the consultant to navigate forwards through the patients
	JButton ConsultantCardPageBackBttn = new JButton();//declares a search button for the consultant to navigate backwards through the patients
	
	JLabel patientCard1FirstName = new JLabel();//declares a label that holds the patients firstname
	JLabel patientCard1SurName = new JLabel();//declares a label that holds the patients surname
	JLabel patientCard1PatientID = new JLabel();//declares a label that holds the patients patient id
	JLabel patientCard1AdmissionID = new JLabel();//declares a label that holds the patients admission ID
	JLabel patientCard1Diagnosis = new JLabel();//declares a label that holds the patients diagnosis
	JLabel patientCard1DONAlbl = new JLabel();//declares a label that holds the patients date of next appointment
	JLabel patientCard1Date = new JLabel();//declares a label that holds the patients date of admisison creation
	JButton viewPatient1bttn = new JButton();//declares a button that allows the consultant to view the admission in more depth
	JLabel patient1image = new JLabel();//declares a label that holds the patients image
	JLabel moreAdmissions1lbl = new JLabel();//declares a label that holds the how may more admissions the patient has with them
	
	JLabel patientCard2FirstName = new JLabel();//declares a label that holds the patients firstname
	JLabel patientCard2SurName = new JLabel();//declares a label that holds the patients surname
	JLabel patientCard2PatientID = new JLabel();//declares a label that holds the patients patient id
	JLabel patientCard2AdmissionID = new JLabel();//declares a label that holds the patients admission ID
	JLabel patientCard2Diagnosis = new JLabel();//eclares a label that holds the patients diagnosis
	JLabel patientCard2DONAlbl = new JLabel();//declares a label that holds the patients date of next appointment
	JLabel patientCard2Date = new JLabel();//declares a label that holds the patients date of admisison creation
	JButton viewPatient2bttn = new JButton();//declares a button that allows the consultant to view the admission in more depth
	JLabel patient2image = new JLabel();//declares a label that holds the patients image
	JLabel moreAdmissions2lbl = new JLabel();//declares a label that holds the how may more admissions the patient has with them
	
	JLabel patientCard3FirstName = new JLabel();//declares a label that holds the patients firstname
	JLabel patientCard3SurName = new JLabel();//declares a label that holds the patients surname
	JLabel patientCard3PatientID = new JLabel();//declares a label that holds the patients patient id
	JLabel patientCard3AdmissionID = new JLabel();//declares a label that holds the patients admission ID
	JLabel patientCard3Diagnosis = new JLabel();//eclares a label that holds the patients diagnosis
	JLabel patientCard3DONAlbl = new JLabel();//declares a label that holds the patients date of next appointment
	JLabel patientCard3Date = new JLabel();//declares a label that holds the patients date of admisison creation
	JButton viewPatient3bttn = new JButton();//declares a button that allows the consultant to view the admission in more depth
	JLabel patient3image = new JLabel();//declares a label that holds the patients image
	JLabel moreAdmissions3lbl = new JLabel();//declares a label that holds the how may more admissions the patient has with them
	
	JButton searchLibraryBttnConsulant = new JButton();//declares a button which searches the definition library
	JButton clearSearchbttnConsulant = new JButton();//declares a button which will remove all current searched items in the jargon library
	JButton viewPatient1AdmissionSpecificBttn = new JButton();//Declares the button for the view document of the admission card
	
			//consultant patient admission homepage
			JButton newDocumentButton = new JButton();//declares a new button that generates a new document
			
		//new document page
		JButton[] listOfDoctypes;//declares a list for all the document types
		JButton docTypeDischargmentBttn = new JButton();//declares a button for the option of discharge
		JButton docTypeTestResultsBttn = new JButton();//declares a button for the option of test results
		JButton docTypeNotesBttn = new JButton();//declares a button for the option of notes
		JButton docTypePrescriptionBttn = new JButton();//declares a button for the option of prescription
		JButton returnBackToadmissionPage = new JButton();//declares a button to return the user back to the admission panel
		JButton createNewDocumentButton= new JButton();//declares a button which creates the nwe document
		JTextPane firstLineForPrescriptionDoc = new JTextPane();//declares the text pane which holds text that is pregenerated for the documents creation
		JLabel medicationDocumentLBL = new JLabel();//declares a label for medication document specific to that type
		JLabel dosageDocumentLBL = new JLabel();//declares a label for medication document specific to that type
		JLabel intakeTimeDocumentLBL = new JLabel();//declares a label for medication document specific to that type
		JLabel DONDDocumentLBL = new JLabel();//declares a label for medication document specific to that type
		JLabel summaryDocumentLBL = new JLabel();//declares a label for medication document specific to that type
		JTextField medicationDocumentTA = new JTextField();//declares a label for medication document specific to that type
		JTextField dosageDocumentTA = new JTextField();//declares a label for medication document specific to that type
		JTextField intakeTimeDocumentTA = new JTextField();//declares a label for medication document specific to that type
		JTextField DONDDocumentTA = new JTextField();//declares a label for medication document specific to that type
		JComboBox<String> summaryOptions = new JComboBox<String>(summaryOptionsArray);//declares the combo box holding all the seriousness of the test results
		
		String oldDocType;//initalises the string for what type of document it is
		
		JTextPane textDocumentConsultantTA = new JTextPane();//declares the texpane to hold the text for documents containig large amounts of text
		JLabel patientIDDocumentConsultantLBL = new JLabel();//declares a label to show information regarding the document 
		JLabel admissionIDDocumentConsultantLBL = new JLabel();//declares a label to show information regarding the document 
		JLabel dateDocumentConsultantLBL = new JLabel();//declares a label to show information regarding the document 
		JLabel timeDocumentConsultantLBL = new JLabel();//declares a label to show information regarding the document 
		JLabel documentIDDocumentConsultantLBL = new JLabel();//declares a label to show information regarding the document 
		JLabel titleDocumentConsultantLBL = new JLabel();//declares a label to show information regarding the document 
		
		//edit admission page
		JLabel admissionTitle = new JLabel();//declares a label to inform the user of where they are
		JLabel editAdmissionTitle = new JLabel();//declares a label to inform the user of where they are
		JLabel wardLblPrompt = new JLabel();//declares a label to prompt them for the feild they are looking at
		JLabel consultantNameLblPrompt = new JLabel();//declares a label to prompt them for the feild they are looking at
		JLabel staffNameLblPrompt = new JLabel();//declares a label to prompt them for the feild they are looking at
		JRadioButton dischargeLblPrompt = new JRadioButton();//declares a label to prompt them for the feild they are looking at
		JLabel currentDiagnosisLblPrompt = new JLabel();//declares a label to prompt them for the feild they are looking at
		JLabel roomLblPrompt = new JLabel();//declares a label to prompt them for the feild they are looking at
		JLabel listOfCurrnetSymptomsLblPrompt = new JLabel();//declares a label to prompt them for the feild they are looking at
		JButton backToAdmissionConBttn = new JButton();//declares a button to move the user back to the privious panel
		JButton archiveAdmissionBttn = new JButton();//declares a button to archive te current admission
		JButton saveChangesToAdmisionBttn = new JButton();//declares a button to save changes made to the admission
		JTextField wardAdmissionTextField = new JTextField();//declares a text field to store input from user
		JTextField StaffsNameTextField = new JTextField();//declares a text field to store input from user
		boolean editAdmission;//bool value which determines whether if the user can edit or view admission
		JTextField roomTextField = new JTextField();//declares a text field to store input from user
		JTextField currentDiagnosisTextField = new JTextField();//declares a text field to store input from user
		JTextArea listOfCurrnetSymptomsTextPane = new JTextArea();//declares a text field to store input from user
		JComboBox<String> staffNameTextField;//initalises a combobox to hold all the staff names in
		JButton clearSearchbttn = new JButton();//declares a button which will remove all current searched items in the jargon library
		
	//jargon library	
	JButton searchLibraryBttn = new JButton();//declares a button which searches the definition library
	JButton upButton = new JButton();//declares a button that navigates the panel
	JButton downButton = new JButton();//declares a button that navigates the panel
	JComboBox<String> sortBoxEmployeeAdmissionPanel = new JComboBox<String>(sortOptionsJargon);//declares the combobox that holds the sort orders for the list
	JLabel word1 = new JLabel();//declares the lable which will hold a word only
	JTextPane word1Definition = new JTextPane();//declares the text pane that will hold the corrosponding definition to the word
	JLabel word2 = new JLabel();//declares the lable which will hold a word only
	JTextPane word2Definition = new JTextPane();//declares the text pane that will hold the corrosponding definition to the word
	JLabel word3 = new JLabel();//declares the lable which will hold a word only
	JTextPane word3Definition = new JTextPane();//declares the text pane that will hold the corrosponding definition to the word
	JLabel word4 = new JLabel();//declares the lable which will hold a word only
	JTextPane word4Definition = new JTextPane();//declares the text pane that will hold the corrosponding definition to the word
	JLabel word5 = new JLabel();//declares the lable which will hold a word only
	JTextPane word5Definition = new JTextPane();//declares the text pane that will hold the corrosponding definition to the word
	JLabel word6 = new JLabel();//declares the lable which will hold a word only
	JTextPane word6Definition = new JTextPane();//declares the text pane that will hold the corrosponding definition to the word
	JLabel word7 = new JLabel();//declares the lable which will hold a word only
	JTextPane word7Definition = new JTextPane();//declares the text pane that will hold the corrosponding definition to the word
	JLabel word8 = new JLabel();//declares the lable which will hold a word only
	JTextPane word8Definition = new JTextPane();//declares the text pane that will hold the corrosponding definition to the word
	JTextField searchBarJargon = new JTextField();//declares a text field to store input from user
	JLabel searchPrompt = new JLabel();//declares the label that contains the text theat shows that the box is for the search criteria

	
	//Misc components, these are assets that are used for design only and are reused throughout the system 
	JLabel Bluebox = new JLabel();//declares a labbel for a blue box to be created for astetic purposes and display a visual appeal to the user//
	JLabel whiteBox1 = new JLabel();//declares a labbel for a white box to be created for astetic purposes and display a visual appeal to the user//
	JLabel whiteBox2 = new JLabel();//declares a labbel for a white box to be created for astetic purposes and display a visual appeal to the user//
	JLabel whiteBox3 = new JLabel();//declares a labbel for a white box to be created for astetic purposes and display a visual appeal to the user//
	JLabel whiteBox4 = new JLabel();//declares a labbel for a white box to be created for astetic purposes and display a visual appeal to the user//demographic fNamebox  ||admission card1  ||ward box !! definition box 1
	JLabel whiteBox5 = new JLabel();//declares a labbel for a white box to be created for astetic purposes and display a visual appeal to the user//demographic sName  ||admission card2  ||consultant box !! definition box 2
	JLabel whiteBox6 = new JLabel();//declares a labbel for a white box to be created for astetic purposes and display a visual appeal to the user//demographic Religon  ||admission card3  ||staff box !! definition box 3
	JLabel whiteBox7 = new JLabel();//declares a labbel for a white box to be created for astetic purposes and display a visual appeal to the user//demographic BloodType  ||discharge patient box !! definition box 4
	JLabel whiteBox8 = new JLabel();//declares a labbel for a white box to be created for astetic purposes and display a visual appeal to the user//demographic Dob ||admission card4  ||current diagnosis !! definition box 5
	JLabel whiteBox9 = new JLabel();//declares a labbel for a white box to be created for astetic purposes and display a visual appeal to the user//demographic Nationality  ||room box !! definition box 6
	JLabel whiteBox10 = new JLabel();//declares a labbel for a white box to be created for astetic purposes and display a visual appeal to the user//demographic Address ||admission card5  ||list of current symptoms !! definition box 7
	JLabel whiteBox11 = new JLabel();//declares a labbel for a white box to be created for astetic purposes and display a visual appeal to the user//demographic Allergies   !! definition box 8
	JLabel whiteBox12 = new JLabel();//declares a labbel for a white box to be created for astetic purposes and display a visual appeal to the user//demographic Smoker  !!
	JLabel whiteBox13 = new JLabel();//declares a labbel for a white box to be created for astetic purposes and display a visual appeal to the user//demographic Drinker  
	JLabel whiteBox14 = new JLabel();//declares a labbel for a white box to be created for astetic purposes and display a visual appeal to the user//demographic Diablities 
	JLabel whiteBox15 = new JLabel();//declares a labbel for a white box to be created for astetic purposes and display a visual appeal to the user//demographic Gender ||admission card6
	JLabel whiteBox16 = new JLabel();//declares a labbel for a white box to be created for astetic purposes and display a visual appeal to the user//demographic Contact Info
	JLabel whiteBox17 = new JLabel();//declares a labbel for a white box to be created for astetic purposes and display a visual appeal to the user//document 
	JLabel greyBox1 = new JLabel();//declares a labbel for a grey box to be created for astetic purposes and display a visual appeal to the user//ContactBarNearestAppointment
	JLabel darkgreyBox1 = new JLabel();//declares a labbel for a grey box to be created for astetic purposes and display a visual appeal to the user//PHomeNotificationbox
	JLabel topwhitelinebar = new JLabel();//this declares the top white line that will be used for a menue which will be used throughout//ContactBar
	JLabel topwhitelinebarAdmission = new JLabel();//this declares the top white line that will be used for a menue which will be used throughout//ContactBar
	JLabel titleUpperBlackLine = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
	JLabel titleLowerBlackLine = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
	//used for contactbar//go in order from top to bottom visually
	JLabel blackLine3 = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
	JLabel blackLine4= new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
	JLabel blackLine5 = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
	JLabel blackLine6 = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//DOB
	JLabel blackLine7 = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
	JLabel blackLine8 = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
	JLabel blackLine9 = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
	JLabel blackLine10 = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
	JLabel blackLine11 = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
	JLabel blackLine12 = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
	JLabel blackLine13 = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
	JLabel blackLine14 = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
	JLabel blackLine15 = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
	
	
	
	JLabel blackLine16 = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//patientHomeAdmissionCardline1
	JLabel blackLine17 = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//patientHomeAdmissionCardline2
	JLabel blackLine18 = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//patientHomeAdmissionCardline3
	JLabel blackLine19 = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//patientHomeAdmissionCardline1
	JLabel blackLine20 = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//patientHomeAdmissionCardline2
	JLabel blackLine21 = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//patientHomeAdmissionCardline3
	
	Color selectedBttcColour = Color.decode("#3a78d7");//decodes the colour for the bttn and saves it
	Font headerFontFormatID = new Font("Helvetica",Font.BOLD,40);//
	Font headerFontFormat = new Font("Helvetica",Font.BOLD,48);//
		/*This creates a new object of font containg the  desired attributes of font type
		if i want it bold and finallythe size, i will be able to inherit this attribute and 
		use it throughout the desgin
		*/
	Color greyColour = Color.decode("#d6d6d6");//decodes the colour from the hex value and saves it
	Color darkGreyColour = Color.decode("#7a7a7a");//decodes the colour from the hex value and saves it
	Color importantRedColor = Color.decode("#b70f0a");//decodes the colour from the hex value and saves it
	Color darkButtonGrey =  new Color(-12303806);//decodes an int value and saves it as a colour
	Color lightGreyColor = Color.decode("#d6d6d6");//decodes the colour from the hex value and saves it
	Font headerFontFormatBlack = new Font("Helvetica",Font.BOLD,24);//This creates a new object of font containg the desired attributes of font type
	Font buttonFontFormat = new Font("Helvetica",Font.BOLD,30);//declares a new font for the buttons text so it is much clearer to 
	Font buttonFontFormatu = new Font("Helvetica",Font.BOLD,30);//declares a new font for the buttons text so it is much clearer to 
	Font textfont = new Font("Helvetica",Font.BOLD,12);//This creates a new object of font containg the desired attributes of font type
	Font consultantCardFont = new Font("Helvetica",Font.BOLD,27);//This creates a new object of font containg the desired attributes of font type
	Font symptomfont = new Font("Helvetica",Font.BOLD,17);//This creates a new object of font containg the desired attributes of font type
	Font whiteLoginFont = new Font("Helvetica",Font.BOLD,24);//declares a new type of font that can be globally used  to alter the font for any text when applied to 
	Font admissionCardFont = new Font("Helvetica",Font.BOLD,20);//declares a new type of font that can be globally used  to alter the font for any text when applied to 
	Map<TextAttribute, Object> attributes = new HashMap<>(buttonFontFormatu.getAttributes());//declares the mapping attribute for the button which retirieves the text attributes of s font
	
	SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");//declares a simple font for a visual improvement over the full version
	SimpleDateFormat ftTimeInc = new SimpleDateFormat ("dd/MM/yyyy k:mm");//declares a simple font for a visual improvement over the full version
	SimpleDateFormat timeft = new SimpleDateFormat ("kk:mm");//declares a simple font for a visual improvement over the full version
	
	//declares the frame
	
    public void startFrameGUI()//this is the start frame gui and is the first method is executed on the system
    {
		newDocumentButton.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		requestReinstatementBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		editAdmissionBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		viewAppointmentsButton.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		reinstatePatientBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		setAllPanelsUnloaded();//method which sets all the panels as unloaded is called
        frame.setLayout(new GridLayout(1,1));//initalises the frame and the format
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Will stop the program when the window is closed
        frame.add(startPanel);
		setUpArray();
		/*
		When the frame is initialised the panel is added to the frame
		*/
        frame.setTitle("loginFrame");//sets the title of the frame
		frame.setResizable(false);
        frame.setSize(1474,913);//Sets the aspect ratio of the intial window
        frame.setForeground( new Color(-16777216) );//the component has its font colour changed to a desireable one
		//Declares the colour of the foreground
        frame.setBackground( new Color(-2696737) );//
		//Declares the colour of the backgound
        frame.setVisible(true);//sets the frame to be visible
		//sets the frame to be visbile along with any panels attached to it
        frame.setResizable(false);//allows the frame to be resized
		currentPanel = startPanel;//declares the start panel to be the current panel
		oldpanel = startPanel;//sets the old panel to be the current page when it moves onto the next panel this one is removed
		
		createComponentsStartPanelGUI();//runs the method which allow the components of the pannel to be created
		createAllboxes();//method which creates all visual boxes is called
		//the components of the panel are cereated by running the method this is done initially so it is only done once and will be used throughout
		ConsultantList cl = new ConsultantList();//declares a new instance of consultant list
			Consultant c = new Consultant();//declares a new instance of consultant 
			String[] stringedConsultants = cl.rffReturnFullFile("ConsultantInfo.txt");//retireives the list of all consultants
			//System.out.println("number of consultants "+stringedConsultants.length);
			numberOfConsultants =stringedConsultants.length;//number of consultants is found
			listOfAllConsultants = new Consultant[numberOfConsultants];//the array of consultants is now correctly declared with the size being the number of consultants
			listOfAllConsultantSnames = new String[numberOfConsultants];//an array containing consultants first names is declared
			for(int count = 0; count<numberOfConsultants; count++)//for loop which will cycle through ever consultqant in both arras
			{
				String[] stringedConsultantinfo = stringedConsultants[count].split(",");//concatinates the string into an array 
				listOfAllConsultants[count] =  c.retrieveConsultantInfoFromSting(stringedConsultantinfo);//the stringed array is then assigned to attributes of the consultant
				listOfAllConsultantSnames[count] =listOfAllConsultants[count].consultantID+","+listOfAllConsultants[count].firstName +","+ listOfAllConsultants[count].surName+",";//for the GUI the list of consultant names is updated the current consultant 
			}		
			staffNameTextField = new JComboBox<String>(listOfAllConsultantSnames);//now list of consultant names has been declared  it has been added to the combobox to finish iit being intialised
		
    } 
	
	//creates all box components
	
	public void createAllboxes()//method which formats all boxes as they need to be used alot throughout it is better to intiaslise them and format them at the start.
	{
		lightGreyTopPanelAdmissionSearchBar.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		lightGreyTopPanelAdmissionSearchBar.setBackground(lightGreyColor);//the back ground colour of the box is set to a predetermined colour
		lightGreyTopPanelAdmissionSearchBar.setOpaque(true);//so the colour can be seen it is set to opaque
		
		lighterGreyAdmissionPanel.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		lighterGreyAdmissionPanel.setBackground(darkGreyColour);//the back ground colour of the box is set to a predetermined colour
		lighterGreyAdmissionPanel.setOpaque(true);//so the colour can be seen it is set to opaque
		
		whiteBox1.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox1.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
		whiteBox1.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		whiteBox1.setOpaque(true);//the component is set to opaque
		
		symptomPanel.add(whiteBox1);//the component is added to the panel
		
		whiteBox2.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox2.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
		whiteBox2.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 		
		whiteBox2.setOpaque(true);//the component is set to opaque
		
		whiteBox3.setOpaque(true);//the component is set to opaque
		whiteBox3.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox3.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
		whiteBox3.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value
 		
		whiteBox4.setOpaque(true);//the component is set to opaque
		whiteBox4.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox4.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
		whiteBox4.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value
				
		whiteBox5.setOpaque(true);//the component is set to opaque
		whiteBox5.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox5.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
		whiteBox5.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value
				
		whiteBox6.setOpaque(true);//the component is set to opaque
		whiteBox6.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox6.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
		whiteBox6.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value
				
		whiteBox7.setOpaque(true);//the component is set to opaque
		whiteBox7.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox7.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
		whiteBox7.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value
				
		whiteBox8.setOpaque(true);//the component is set to opaque
		whiteBox8.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox8.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
		whiteBox8.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value
				
		whiteBox9.setOpaque(true);//the component is set to opaque
		whiteBox9.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox9.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
		whiteBox9.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value
				
		whiteBox10.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox10.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
		whiteBox10.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		whiteBox10.setOpaque(true);//the component is set to opaque
				
		whiteBox11.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox11.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
		whiteBox11.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		whiteBox11.setOpaque(true);//the component is set to opaque
				
		whiteBox12.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox12.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
		whiteBox12.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		whiteBox12.setOpaque(true);//the component is set to opaque
				
		whiteBox13.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox13.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
		whiteBox13.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		whiteBox13.setOpaque(true);//the component is set to opaque
				
		whiteBox14.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox14.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
		whiteBox14.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		whiteBox14.setOpaque(true);//the component is set to opaque
						
		whiteBox15.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox15.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
		whiteBox15.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		whiteBox15.setOpaque(true);//the component is set to opaque
		
		whiteBox16.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox16.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
		whiteBox16.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		whiteBox16.setOpaque(true);//the component is set to opaque
		
		whiteBox17.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox17.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
		whiteBox17.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		whiteBox17.setOpaque(true);//the component is set to opaque
		
		
		Bluebox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		Bluebox.setForeground( new Color(-12616489) );//the foreground of the cmponent is given a white font
		Bluebox.setBackground( new Color(-12616489) );//the background colour of the component is declared to the desired value 
		Bluebox.setSize(480,159);//the components size is correctly declared
		Bluebox.setOpaque(true);//the component is set to opaque
		
		greyBox1.setOpaque(true);//the component is set to opaque
		greyBox1.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		greyBox1.setForeground(greyColour);//the foreground of the cmponent is given a white font
		greyBox1.setBackground(greyColour);//the background colour of the component is declared to the desired value 
	}
	
	//sets all panels to unloaded
	
	public void setAllPanelsUnloaded()//method which sets all panels as loaded to false
	{
		for(int outerLoop=0;outerLoop==14;outerLoop++)//loops through each major section e.g. patient, admission, demographic, document, consultant etc
		{
			for(int innerLoop=0;innerLoop==14;innerLoop++)//loops through the minor panels that can be found for the major part e.g. patient homepage, patient createion etc.
			{
				loaded[outerLoop][innerLoop] = false;//the current panel is set to unloaded
			
			}
		}
	}
	
	//removes the old panel from the frame
	public void setUpArray()
	{
		panelOrder[0]=startPanel;
		showlatestPanel();		
	}
	public void showlatestPanel()
	{
		if(endPanelPointer>=1)
		{
			panelOrder[endPanelPointer-1].setVisible(false);
			frame.remove(panelOrder[endPanelPointer-1]);//Removes the current pannel of the system
		}
		panelOrder[endPanelPointer].setVisible(true);
	}
	public void removePanel()
	{
		frame.remove(panelOrder[endPanelPointer]);//Removes the current pannel of the system
		panelOrder[endPanelPointer].setVisible(false);
		endPanelPointer--;
		frame.add(panelOrder[endPanelPointer]);
		showlatestPanel();
		System.out.println("Pointer is at"+endPanelPointer);
	}
	public void addPanel(JPanel panel)
	{
		endPanelPointer++;
		panelOrder[endPanelPointer]=panel;	
		frame.add(panelOrder[endPanelPointer]);//Removes the current pannel of the system
		showlatestPanel();
		System.out.println("Pointer is at"+endPanelPointer);
	}
	public void emptyArray(JPanel panel)
	{
		frame.remove(panelOrder[endPanelPointer]);//Removes the current pannel of the system
		panelOrder[0]=panel;
		endPanelPointer=0;
		frame.add(panelOrder[endPanelPointer]);//Removes the current pannel of the system
		showlatestPanel();	
		System.out.println("Pointer is at"+endPanelPointer);
	}
	public void outputPanelList()
	{
		for(int i =0;i<=endPanelPointer;i++)
		{
			System.out.println(panelOrder[endPanelPointer]);
		}
		System.out.println("Pointer is at"+endPanelPointer);
		
	}
	public void removeAllPanels(JPanel panel)
	{
		frame.remove(panel);//Removes the current pannel of the system
	}
	
	//StartPanel components
	
	public void createStartPanelGUI()////the method is declared as public with no parameters to pass through
    {
		removeAllPanels(oldpanel);//removes the old panel by passing that panel through a method which just removes it from the frame
		oldpanel = startPanel;//sets the old panel to be the current page when it moves onto the next panel this one is removed
		frame.add(startPanel);//Adds the new desired pannel 
		startPanel.setVisible(false);//when the panel initially loads the label is hidden
		startPanel.setVisible(true);//when the panel initially loads the label is hidden
		entitylbl.setVisible(false);//when the panel initially loads the label is hidden
	}
		public void createComponentsStartPanelGUI()////the method is declared as public with no parameters to pass through
		{
			
		mainHeaderlbl.setForeground( new Color(-12616489) );//matches the colour as closely as possible to the one in my design
		mainHeaderlbl.setFont(headerFontFormat);//Adds the new font created to the header label
		mainHeaderlbl.setLocation(450,112);//sets the location of the component 
		mainHeaderlbl.setSize(610,55);//the components size is correctly declared
		mainHeaderlbl.setOpaque(true);//the component is set to opaque
		mainHeaderlbl.setText("Euxton Healthcare System");//the label is geven text to add meaning to the label
		startPanel.add(mainHeaderlbl);//the component is added to the panel
		
		loginBttn.setFont(buttonFontFormat);//the font that has been declared is attached to the component
		loginBttn.setLocation(396,427);////sets the location of the component 
		loginBttn.setSize(259,113);//the components size is correctly declared
		loginBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		loginBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		loginBttn.setText("Login");//the button is geven text to add meaning to the label
		loginBttn.setEnabled(true);//the component is then enabled to allow it to be used
		loginBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		startPanel.add(loginBttn);//the component is added to the panel

		symptomButtn.setFont(buttonFontFormat);//the font that has been declared is attached to the component
		symptomButtn.setLocation(815,431);//sets the location of the component 
		symptomButtn.setSize(375,113);//the components size is correctly declared
		symptomButtn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		symptomButtn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		symptomButtn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		symptomButtn.setText("Enter Your symptoms");//the button is geven text to add meaning to the label
		symptomButtn.setEnabled(true);//the component is then enabled to allow it to be used
		startPanel.add(symptomButtn);//the component is added to the panel
		
		helpLbl.setLocation(678,616);//sets the location of the component 
		helpLbl.setSize(150,50);//the components size is correctly declared
		helpLbl.setOpaque(true);//the component is set to opaque
		helpLbl.setText("Please select a button");//the button is geven text to add meaning to the label
		startPanel.add(helpLbl);//the component is added to the panel
		
    }
	
	
	//symptom panel
	
	public void createsymptomPanel()//the method is declared as public with no parameters to pass through
    {
		if(loaded[1][0]== false)//selection statment to see if the panels components are yet to be generated
		{
			createComponentssymptomPanelGUI();//as the correct panel is visible the components on the panel is formatted 
			loaded[1][0]= true;//once the components have been loaded the statement is now set permantly to false to prevent multiple instance of the same GUI components
		}
		addPanel(symptomPanel);
		symptomPanel.setVisible(true);//the panel is declared to be visible showing the correct componets 
		whiteBox1.setSize(347,170);//the components size is correctly declared
		whiteBox1.setLocation(96,690); //sets the location of the component 
		whiteBox3.setLocation(540,470); //sets the location of the component 
		whiteBox2.setLocation(540,200); //sets the location of the component 
		whiteBox3.setSize(400,395);//the components size is correctly declared
		whiteBox2.setSize(400,220);//the components size is correctly declared
		symptomPanel.add(symptomHeaderlbl);//the component is added to the panel
		symptomPanel.add(titleLowerBlackLine);//the component is added to the panel
		symptomPanel.add(titleUpperBlackLine);//the component is added to the panel
		symptomPanel.add(symptomsTfCbLBL);//the component is added to the panel
		symptomPanel.add(symptomhelp);//the component is added to the panel
		symptomPanel.add(symptomsCbLBL);//the component is added to the panel
		symptomPanel.add(typepainCbLBL);//the component is added to the panel
		symptomPanel.add(painCbLBL);//the component is added to the panel
		symptomPanel.add(humanImage);//the component is added to the panel
		symptomPanel.add(cbLeg);//the component is added to the panel
		symptomPanel.add(cbPelvis);//the component is added to the panel
		symptomPanel.add(cbArm);//the component is added to the panel
		symptomPanel.add(cbAbdomen);//the component is added to the panel
		symptomPanel.add(cbBack);//the component is added to the panel
		symptomPanel.add(cbHead);//the component is added to the panel
		symptomPanel.add(cbFoot);//the component is added to the panel
		symptomPanel.add(cbHand);//the component is added to the panel
		symptomPanel.add(cbChest);//the component is added to the panel
		symptomPanel.add(cbNeck);//the component is added to the panel
		symptomPanel.add(SymptomConfirmBttn);//the component is added to the panel
		symptomPanel.add(symptomBackToStartBttn);//the component is added to the panel
		symptomPanel.add(symptom1TF);//the component is added to the panel
		symptomPanel.add(symptom2TF);//the component is added to the panel
		symptomPanel.add(symptom3TF);//the component is added to the panel
		symptomPanel.add(symptom4TF);//the component is added to the panel
		symptomPanel.add(cbFatigue);//the component is added to the panel
		symptomPanel.add(cbNausea);//the component is added to the panel
		symptomPanel.add(cbFever);//the component is added to the panel
		symptomPanel.add(cbWeightLoss);//the component is added to the panel
		symptomPanel.add(cbFrequentRecurringPains);//the component is added to the panel
		symptomPanel.add(cbStiffnessInMuscle);//the component is added to the panel
		symptomPanel.add(cbAcutePains);//the component is added to the panel
		symptomPanel.add(cbChronicPains);//the component is added to the panel
		symptomPanel.add(whiteBox1);//the component is added to the panel
		symptomPanel.add(whiteBox3);//the component is added to the panel
		symptomPanel.add(whiteBox2);//the component is added to the panel
	}
		public void createComponentssymptomPanelGUI()//the method is declared as public with no parameters to pass through
		{
		
		symptomBackToStartBttn.setLocation(96,63);//sets the location of the component 
		symptomBackToStartBttn.setSize(200,49);//the components size is correctly declared
		symptomBackToStartBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		symptomBackToStartBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		symptomBackToStartBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		symptomBackToStartBttn.setText("Go Back");//the button is geven text to add meaning to the label
		
		
		SymptomConfirmBttn.setLocation(1050,750);//sets the location of the component 
		SymptomConfirmBttn.setSize(250,50);//the components size is correctly declared
		SymptomConfirmBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		SymptomConfirmBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		SymptomConfirmBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		SymptomConfirmBttn.setText("Request Admission");//the button is geven text to add meaning to the label

		cbNeck.setLocation(177,237);//sets the location of the component 
		cbNeck.setSize(60,30);//the components size is correctly declared
		cbNeck.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		cbNeck.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		
		cbChest.setLocation(142,281);//sets the location of the component 
		cbChest.setSize(60,30);//the components size is correctly declared
		cbChest.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbChest.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		
		cbHand.setLocation(113,427);//sets the location of the component 
		cbHand.setSize(55,23);//the components size is correctly declared
		cbHand.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbHand.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		
		cbFoot.setLocation(180,570);//sets the location of the component 
		cbFoot.setSize(55,23);//the components size is correctly declared
		cbFoot.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		
		cbFoot.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		
		cbHead.setLocation(300,225);//sets the location of the component 
		cbHead.setSize(55,23);//the components size is correctly declared
		cbHead.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbHead.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		
		cbBack.setLocation(315,255);//sets the location of the component 
		cbBack.setSize(55,23);//the components size is correctly declared
		cbBack.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbBack.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		
		cbAbdomen.setLocation(335,340);//sets the location of the component 
		cbAbdomen.setSize(100,30);//the components size is correctly declared
		cbAbdomen.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbAbdomen.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		
		cbArm.setLocation(325,292);//sets the location of the component 
		cbArm.setSize(50,23);//the components size is correctly declared
		cbArm.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		
		cbArm.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		
		cbPelvis.setLocation(300,455);//sets the location of the component 
		cbPelvis.setSize(60,30);//the components size is correctly declared
		cbPelvis.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		
		cbPelvis.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		
		cbLeg.setLocation(295,515);//sets the location of the component 
		cbLeg.setSize(50,23);//the components size is correctly declared
		cbLeg.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		
		cbLeg.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		
		humanImage.setIcon( new ImageIcon("human1.jpg") );//the label is set to an image
		humanImage.setSize(347,414); //the components size is correctly declared
		humanImage.setLocation(96,204); //sets the location of the component 
		humanImage.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		
		painCbLBL.setFont(textfont);//the font that has been declared is attached to the component
		painCbLBL.setText("Please select the areas where the pain resonates from");//the button is geven text to add meaning to the label
		painCbLBL.setSize(347,20); //the components size is correctly declared
		painCbLBL.setLocation(96,180);//sets the location of the component 
		painCbLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		
		typepainCbLBL.setFont(textfont);//the font that has been declared is attached to the component
		typepainCbLBL.setText("Please select the type of pain in the selected areas");//the button is geven text to add meaning to the label
		typepainCbLBL.setSize(347,20); //the components size is correctly declared
		typepainCbLBL.setLocation(96,670);//sets the location of the component 
		typepainCbLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
	
		symptomsCbLBL.setFont(textfont);//the font that has been declared is attached to the component
		symptomsCbLBL.setText("Please select any symptoms that you are experiencing ");//the button is geven text to add meaning to the label
		symptomsCbLBL.setSize(400,20); //the components size is correctly declared
		symptomsCbLBL.setLocation(540,180);//sets the location of the component 
		symptomsCbLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		
		symptomhelp.setFont(textfont);//the font that has been declared is attached to the component
		symptomhelp.setText("On this page please enter all the symptoms you are currently experiencing this will help determine the ailment and select the most suitable consultant to help treat the problem, feel free to include as much as possible all information entered is confidential and encrypted.");//the button is geven text to add meaning to the label
		symptomhelp.setSize(250,350); //the components size is correctly declared
		symptomhelp.setLocation(1050,180);//sets the location of the component 
		symptomhelp.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
	
		symptomsTfCbLBL.setFont(textfont);//the font that has been declared is attached to the component
		symptomsTfCbLBL.setText("If you have any symptoms that do no appear please use the boxes.");//the button is geven text to add meaning to the label
		symptomsTfCbLBL.setSize(400,20); //the components size is correctly declared
		symptomsTfCbLBL.setLocation(540,450);//sets the location of the component 
		symptomsTfCbLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		
        painCbLBL.setEditable(false);//the text field is disabled from being edited

        typepainCbLBL.setEditable(false);//the text field is disabled from being edited

        symptomsCbLBL.setEditable(false);//the text field is disabled from being edited

        symptomsTfCbLBL.setEditable(false);//the text field is disabled from being edited

        symptomhelp.setEditable(false);//the text field is disabled from being edited

		titleUpperBlackLine.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		titleUpperBlackLine.setForeground( new Color(1) );//the foreground of the component is given a black font, this is used to hide the text
		titleUpperBlackLine.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		titleUpperBlackLine.setLocation(680,40);//sets the location of the component 
		titleUpperBlackLine.setSize(723,3);//the components size is correctly declared
		titleUpperBlackLine.setOpaque(true);//the component is set to opaque
		
		titleLowerBlackLine.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		titleLowerBlackLine.setForeground( new Color(1) );//the foreground of the component is given a black font, this is used to hide the text
		titleLowerBlackLine.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		titleLowerBlackLine.setLocation(680,85);//sets the location of the component 
		titleLowerBlackLine.setSize(723,3);//the components size is correctly declared
		titleLowerBlackLine.setOpaque(true);//the component is set to opaque
		
		
		symptomHeaderlbl.setFont(headerFontFormatBlack);//the font that has been declared is attached to the component
		symptomHeaderlbl.setLocation(1209,48);//sets the location of the component 
		symptomHeaderlbl.setSize(250,30);//the components size is correctly declared
		symptomHeaderlbl.setOpaque(true);//the component is set to opaque
		symptomHeaderlbl.setText("New symptoms");//the label is geven text to add meaning to the label
		
		
		symptom1TF.setLocation(557,480);//sets the location of the component 
		symptom1TF.setSize(350,65);//the components size is correctly declared
		symptom1TF.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		symptom1TF.setText("Symptom 1");//the text field is geven text to add meaning to the label
		symptom1TF.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(symptom1TF.getText().equals("Symptom 1"))//selection checking if the text field contains the text prompt
				{
					symptom1TF.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(symptom1TF.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					symptom1TF.setText("Symptom 1");//if satisifed then the prompt text is set again
				}
			}
		});
		
		symptom2TF.setLocation(557,580);//sets the location of the component 
		symptom2TF.setSize(340,65);//the components size is correctly declared
		symptom2TF.setText("Symptom 2");//the label is geven text to add meaning to the label
		symptom2TF.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		symptom2TF.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(symptom2TF.getText().equals("Symptom 2"))//selection checking if the text field contains the text prompt
				{
					symptom2TF.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(symptom2TF.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
				symptom2TF.setText("Symptom 2");//if satisifed then the prompt text is set again

				}
			}
			
		});
		symptom3TF.setLocation(557,680);//sets the location of the component 
		symptom3TF.setSize(340,65);//the components size is correctly declared
		symptom3TF.setText("Symptom 3");//the label is geven text to add meaning to the label
		symptom3TF.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		symptom3TF.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(symptom3TF.getText().equals("Symptom 3"))//selection checking if the text field contains the text prompt
				{
					symptom3TF.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(symptom3TF.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
				symptom3TF.setText("Symptom 3");//if satisifed then the prompt text is set again

				}
			}
			
		});
		
		symptom4TF.setLocation(557,780);//sets the location of the component 
		symptom4TF.setSize(340,65);//the components size is correctly declared
		symptom4TF.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		symptom4TF.setText("Symptom 4");//the label is geven text to add meaning to the label
		symptom4TF.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(symptom4TF.getText().equals("Symptom 4"))//selection checking if the text field contains the text prompt
				{
					symptom4TF.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(symptom4TF.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
				symptom4TF.setText("Symptom 4");//if satisifed then the prompt text is set again

				}
			}
			
		});
		
		cbChronicPains.setLocation(150,700);//sets the location of the component 
		cbChronicPains.setSize(150,23);//the components size is correctly declared
		cbChronicPains.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		cbChronicPains.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		
		
		cbAcutePains.setLocation(150,740);//sets the location of the component 
		cbAcutePains.setSize(150,23);//the components size is correctly declared
		cbAcutePains.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbAcutePains.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		
		
		
		
		cbStiffnessInMuscle.setLocation(150,780);//sets the location of the component 
		cbStiffnessInMuscle.setSize(150,23);//the components size is correctly declared
		cbStiffnessInMuscle.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbStiffnessInMuscle.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		
		
		cbFrequentRecurringPains.setLocation(150,820);//sets the location of the component 
		cbFrequentRecurringPains.setSize(200,23);//the components size is correctly declared
		cbFrequentRecurringPains.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbFrequentRecurringPains.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		
		
		
		cbWeightLoss.setFont(symptomfont);//the font that has been declared is attached to the component
		cbWeightLoss.setLocation(575,225);//sets the location of the component 
		cbWeightLoss.setSize(150,23);//the components size is correctly declared
		cbWeightLoss.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbWeightLoss.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		
		cbFever.setLocation(575,275);//sets the location of the component 
		cbFever.setSize(150,23);//the components size is correctly declared
		cbFever.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbFever.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		cbFever.setFont(symptomfont);//the font that has been declared is attached to the component
		
		cbNausea.setLocation(575,325);//sets the location of the component 
		cbNausea.setSize(150,23);//the components size is correctly declared
		cbNausea.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbNausea.setFont(symptomfont);//the font that has been declared is attached to the component
		cbNausea.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
	
		cbFatigue.setLocation(575,375);//sets the location of the component 
		cbFatigue.setSize(150,23);//the components size is correctly declared
		cbFatigue.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		cbFatigue.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbFatigue.setFont(symptomfont);//the font that has been declared is attached to the component
		

	}	
	
	//Symptom recomendation panel
	
	public void createSymptomRecomendationPanel()
	{
		acceptSymptoms= true;//PROTORTYPE attribute as expert system has not been included all current admission declarations hasve been automatically accepted
		if(loaded[0][1]== false)//selection is used to determine whether if the components have been formatted
		{
			createSymptomRecomendationPanelGUI(symptomRecomendationPanel);//general formatting of components of this panel are ran here
			loaded[0][1]= true;//once the components have been loaded the statement is now set permantly to false to prevent multiple instance of the same GUI components
		}
		addPanel(symptomRecomendationPanel);
		symptomRecomendationPanel.setVisible(false);//panel is set to invisable
		symptomRecomendationPanel.setVisible(true);//panel is set to visable
		recommendationFalseAcceptanceRB1.setSelected(false);//component is set to unselected
		recommendationFalseAcceptanceRB2.setSelected(false);//component is set to unselected
		recommendationFalseAcceptanceRB3.setSelected(false);//component is set to unselected
		if(acceptSymptoms == true)//selection determining whether or not the symptoms have been accepted
		{
			mainAdviceJPane.setText("We feel with the symptoms you have given,\nyou would benefit with an admission.");//formatting component to include text
			main2AdviceJPane.setText("From this we will create an appointment that is at your best convenience, all we need you to do is login.");//formatting component to include text
			mainAdviceJPane.setLocation(50,200);//location of component is set
			mainAdviceJPane.setSize(1000,300);//size of component is set
			main2AdviceJPane.setLocation(50,350);//location of component is set
			main2AdviceJPane.setSize(1000,300);//size of component is set
			
			symptomRecomendationNewAdmissionBttn.setEnabled(true);//enables the ability to create a new admission
			
		}
		if(acceptSymptoms == false)//selection determining whether or not the symptoms have been accepted
		{
			symptomRecomendationNewAdmissionBttn.setEnabled(false);//disables the ability to create a new admission
			mainAdviceJPane.setText("You are experiencing minor symptoms.");//formatting component to include text
			main2AdviceJPane.setText("There is no major need for you to create an admission at this moment of time.");//formatting component to include text
			mainAdviceJPane.setLocation(50,200);//location of component is set
			mainAdviceJPane.setSize(1000,300);//size of component is set
			main2AdviceJPane.setLocation(50,300);//location of component is set
			main2AdviceJPane.setSize(1000,300);//size of component is set
		}
		if(loginChoice!=-1)
		{
			symptomRecomendationcreateAccountBttn.setEnabled(false);
			
		}
		if(loginChoice==-1)
		{
			symptomRecomendationcreateAccountBttn.setEnabled(true);
			
		}
	}
		public void createSymptomRecomendationPanelGUI(JPanel panel)
		{
		symptomRecomendationBackToExpertSystem.setLocation(96,63);//sets the location of the component 
		symptomRecomendationBackToExpertSystem.setSize(200,49);//the components size is correctly declared
		symptomRecomendationBackToExpertSystem.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		symptomRecomendationBackToExpertSystem.setForeground( new Color(-1) );//the foreground of the component is given a white font
		symptomRecomendationBackToExpertSystem.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		symptomRecomendationBackToExpertSystem.setText("Go Back");//the button is geven text to add meaning to the label
		panel.add(symptomRecomendationBackToExpertSystem);//the component is added to the panel
		
		symptomRecomendationNewAdmissionBttn.setLocation(1140,740);//the components size is correctly declared
		symptomRecomendationNewAdmissionBttn.setSize(180,49);//the components size is correctly declared
		symptomRecomendationNewAdmissionBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		symptomRecomendationNewAdmissionBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		symptomRecomendationNewAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		symptomRecomendationNewAdmissionBttn.setText("Create Admission");//the label is geven text to add meaning to the label
		panel.add(symptomRecomendationNewAdmissionBttn);//the component is added to the panel
		
		
		symptomRecomendationcreateAccountBttn.setLocation(1140,680);//the components size is correctly declared
		symptomRecomendationcreateAccountBttn.setSize(180,49);//the components size is correctly declared
		symptomRecomendationcreateAccountBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		symptomRecomendationcreateAccountBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		symptomRecomendationcreateAccountBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		symptomRecomendationcreateAccountBttn.setText("Create account");//the label is geven text to add meaning to the label
		panel.add(symptomRecomendationcreateAccountBttn);//the component is added to the panel
		if(acceptSymptoms == false)//selection determining whether the symptoms have been accepted and can generate an admission
		{
			symptomRecomendationNewAdmissionBttn.setEnabled(false);//the component is disabled
			mainAdviceJPane.setText("You are experiencing minor symptoms.");//formatting component to include text
			main2AdviceJPane.setText("There is no major need for you to create an admission at this moment of time.");//formatting component to include text
			mainAdviceJPane.setLocation(50,200);//the location of the component is set to the desired part of the panel
			mainAdviceJPane.setSize(1000,300);//size of component is set
			main2AdviceJPane.setLocation(50,300);//the location of the component is set to the desired part of the panel
			main2AdviceJPane.setSize(1000,300);//size of component is set
			minorAdviceJPane.setText("We reccomend that you visit your local pharmacist and describe the entered symptoms.\nHowever if you feel concerened you can still create an admission.\nPlease read and confirm the check boxes to still proceed");//formatting component to include text
			minorAdviceJPane.setLocation(50,400);//the location of the component is set to the desired part of the panel
			minorAdviceJPane.setSize(1000,300);//size of component is set
			
			recommendationFalseAcceptanceRB1.setText("I feel like my symptoms require an appointment despite the systems response");//formatting component to include text
			recommendationFalseAcceptanceRB2.setText("I understand that I am using Euxton resources that otherwise could be utilised \nwith other patients with an equal or more serious condition needing treatment");//formatting component to include text
			recommendationFalseAcceptanceRB3.setText("I am happy to have my appointment time/date re-arranged to a later point\nin time if a more urgent patient needs to be seen.");//formatting component to include text
			recommendationFalseAcceptanceRB1.setLocation(50,600);//the location of the component is set to the desired part of the panel
			recommendationFalseAcceptanceRB2.setLocation(50,675);//the location of the component is set to the desired part of the panel
			recommendationFalseAcceptanceRB3.setLocation(50,750);//the location of the component is set to the desired part of the panel
			recommendationFalseAcceptanceRB1.setSize(1000,30);//size of component is set
			recommendationFalseAcceptanceRB2.setSize(2000,30);//size of component is set
			recommendationFalseAcceptanceRB3.setSize(1000,30);//size of component is set
			recommendationFalseAcceptanceRB1.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
			recommendationFalseAcceptanceRB2.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
			recommendationFalseAcceptanceRB3.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
			panel.add(recommendationFalseAcceptanceRB1);//the component is added to the panel
			panel.add(recommendationFalseAcceptanceRB2);//the component is added to the panel
			panel.add(recommendationFalseAcceptanceRB3);//the component is added to the panel
			recommendationFalseAcceptanceRB1.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
			recommendationFalseAcceptanceRB2.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
			recommendationFalseAcceptanceRB3.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		}
		
		mainAdviceJPane.setFont(headerFontFormat);//the component has had its font set to a design with the correct size for its purpose
		mainAdviceJPane.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
		mainAdviceJPane.setOpaque(false);//the component is made transparent
		panel.add(mainAdviceJPane);//the component is added to the panel
		
		
		main2AdviceJPane.setFont(buttonFontFormat);//the component has had its font set to a design with the correct size for its purpose
		main2AdviceJPane.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
		main2AdviceJPane.setOpaque(false);//the component is made transparent
		panel.add(main2AdviceJPane);//the component is added to the panel
		
		minorAdviceJPane.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		minorAdviceJPane.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
		minorAdviceJPane.setOpaque(false);//the component is made transparent
		panel.add(minorAdviceJPane);//the component is added to the panel
	}
	
	//login panel
	
	public void createloginPanel()//this will create the new panel along with the components 
    {
		if(loaded[2][0]== false)//selection determines whether the panel is yet to be loaded
		{
			createComponentsLoginPanelGUI();//as the correct panel is visible the components on the panel is formatted 
			loaded[2][0]= true;//the variable is set as true to prevent the components from breing reran
		}
		addPanel(loginPanel);
		loginPanel.setVisible(true);//allows the panel to be seen by making it visible
		
		selectPatientloginBttn.setVisible(true);//allows the button to be visible initally
		selectManagementloginBttn.setVisible(true);//allows the button to be visible initally
		selectConsultantloginBttn.setVisible(true);//allows the button to be visible initally
		selectAdminloginBttn.setVisible(true);//allows the button to be visible initally
		Bluebox.setVisible(false);//will hide the box preventing it from being seen
		
		loginPasswordF.setVisible(false);//the component is set so it cant be seen
		realLoginbttn.setVisible(false);//the component is set so it cant be seen
		createAccountbbtn.setVisible(false);//the component is set so it cant be seen
		entitylbl.setVisible(false);//the component is set so it cant be seen
		usernameTF.setVisible(false);//the component is set so it cant be seen
		
		selectPatientloginBttn.setLocation(637,300);//sets the location of the component 
		selectAdminloginBttn.setLocation(637,420);//sets the location of the component 
		selectManagementloginBttn.setLocation(637,540);//sets the location of the component 
		selectConsultantloginBttn.setLocation(637,660);//sets the location of the component 
		
		titleUpperBlackLine.setLocation(680,40);//sets the location of the component 
		titleLowerBlackLine.setLocation(680,85);//sets the location of the component 
		titleLowerBlackLine.setSize(723,3);//the components size is correctly declared
		titleUpperBlackLine.setSize(723,3);//the components size is correctly declared		
	}
		public void createComponentsLoginPanelGUI()//the method is declared as public with no parameters to pass through
		{
	
		lblLogo.setIcon( new ImageIcon("Logo.png") );//the correct image is retrieved from the folder
		lblLogo.setVisible(true);//the component is set so it can be seen
		lblLogo.setSize(368,160); //the components size is correctly declared
		lblLogo.setLocation(65,63); //sets the location of the component 
		
		loginHeaderlbl.setFont(headerFontFormatBlack);//the font that has been declared is attached to the component
		loginHeaderlbl.setLocation(1339,48);//sets the location of the component 
		loginHeaderlbl.setSize(70,30);//the components size is correctly declared
		loginHeaderlbl.setOpaque(true);//the component is set to opaque
		loginHeaderlbl.setText("Login");//the label is geven text to add meaning to the label
		
		
		loginBackToStartBttn.setLocation(51,800);//sets the location of the component 
		loginBackToStartBttn.setSize(200,49);//the components size is correctly declared
		loginBackToStartBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		loginBackToStartBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		loginBackToStartBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		loginBackToStartBttn.setText("Go Back");//the label is geven text to add meaning to the label
		
		
		selectPatientloginBttn.setLocation(637,300);//sets the location of the component 
		selectPatientloginBttn.setSize(200,49);//the components size is correctly declared
		selectPatientloginBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		selectPatientloginBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		selectPatientloginBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		selectPatientloginBttn.setText("Patient");//the label is geven text to add meaning to the label
		
		
		selectAdminloginBttn.setLocation(637,420);//sets the location of the component 
		selectAdminloginBttn.setSize(200,49);//the components size is correctly declared
		selectAdminloginBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		selectAdminloginBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		selectAdminloginBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		selectAdminloginBttn.setText("Admin Staff");//the label is geven text to add meaning to the label
		
		
		selectManagementloginBttn.setLocation(637,540);//sets the location of the component 
		selectManagementloginBttn.setSize(200,49);//the components size is correctly declared
		selectManagementloginBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		selectManagementloginBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		selectManagementloginBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		selectManagementloginBttn.setText("Management");//the label is geven text to add meaning to the label
		
		
		selectConsultantloginBttn.setLocation(637,660);//sets the location of the component 
		selectConsultantloginBttn.setSize(200,49);//the components size is correctly declared
		selectConsultantloginBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		selectConsultantloginBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		selectConsultantloginBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		selectConsultantloginBttn.setText("Consultant");//the label is geven text to add meaning to the label
		
		titleUpperBlackLine.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		titleUpperBlackLine.setForeground( new Color(1) );//the foreground of the component is given a white font
		titleUpperBlackLine.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		titleUpperBlackLine.setLocation(680,40);//sets the location of the component 
		titleUpperBlackLine.setSize(723,3);//the components size is correctly declared
		titleUpperBlackLine.setOpaque(true);//the component is set to opaque
		
		titleLowerBlackLine.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		titleLowerBlackLine.setForeground( new Color(1) );//the foreground of the component is given a white font
		titleLowerBlackLine.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		titleLowerBlackLine.setLocation(680,85);//sets the location of the component 
		titleLowerBlackLine.setSize(723,3);//the components size is correctly declared
		titleLowerBlackLine.setOpaque(true);//the component is set to opaque
		
		usernameTF.setSize(240,23); //the components size is correctly declared
		usernameTF.addFocusListener(new FocusListener()//a focus listner is attached to the component on its own and will look out for any interactions that occur 
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(usernameTF.getText().equals("Username:"))//it will then use selection to see if the field contains the text prompt indicating the user hasnt edited it yet
				{
					usernameTF.setText("");//the field is then cleared to allow the user to enter their desired text
				}
			}
			public void focusLost(FocusEvent e)//when the focus is lost on the component the method is ran
			{
				if(usernameTF.getText().equals(""))//selectio occurs checking seeing when foucs has been lost the user has entered nothing 
				{
				usernameTF.setText("Username:");//if this is true then the orinigal text prompt is added back 

				}
			}
			
		});
		
		loginPasswordF.setSize(240,23); //the components size is correctly declared
		
		realLoginbttn.setSize(180,49);//the components size is correctly declared
		realLoginbttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		realLoginbttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		realLoginbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		realLoginbttn.setText("login");//the label is geven text to add meaning to the label
		loginPanel.add(realLoginbttn);//the component is added to the panel
		
		createAccountbbtn.setSize(180,49);//the components size is correctly declared
		createAccountbbtn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		createAccountbbtn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		createAccountbbtn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		createAccountbbtn.setText("Create account");//the label is geven text to add meaning to the label

		
		entitylbl.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		entitylbl.setForeground( new Color(-1) );//the foreground of the component is given a white font
		entitylbl.setSize(480,159);//the components size is correctly declared
		
		loginPanel.add(lblLogo);//the component is added to the panel
		loginPanel.add(loginHeaderlbl);//the component is added to the panel
		loginPanel.add(loginBackToStartBttn);//the component is added to the panel
		loginPanel.add(selectPatientloginBttn);//the component is added to the panel
		loginPanel.add(selectAdminloginBttn);//the component is added to the panel
		loginPanel.add(createAccountbbtn);//the component is added to the panel
		loginPanel.add(entitylbl);//the component is added to the panel
		loginPanel.add(usernameTF);//the component is added to the panel
		loginPanel.add(loginPasswordF);//the component is added to the panel
		loginPanel.add(Bluebox);//the component is added to the panel
		loginPanel.add(selectConsultantloginBttn);//the component is added to the panel
		loginPanel.add(titleUpperBlackLine);//the component is added to the panel
		loginPanel.add(titleLowerBlackLine);//the component is added to the panel
		loginPanel.add(selectManagementloginBttn);//the component is added to the panel
	}
			
		// login minipanels
		
			public void openButtonPatient()//the method is declared as public with no parameters to pass through
			{
				selectAdminloginBttn.setVisible(true);//the component is set so it can be seen
				selectManagementloginBttn.setVisible(true);//the component is set so it can be seen
				selectConsultantloginBttn.setVisible(true);//the component is set so it can be seen

				Bluebox.setLocation(497,300);//sets the location of the component 
				Bluebox.setOpaque(true);//the component is set to opaque
				Bluebox.setVisible(true);//the component is set so it can be seen
				
				selectPatientloginBttn.setVisible(false);//the component is set so it cant be seen
				selectAdminloginBttn.setLocation(637,470);//sets the location of the component 
				selectManagementloginBttn.setLocation(637,590);//the location of the component is set to the desired part of the panel
				selectConsultantloginBttn.setLocation(637,710);//the location of the component is set to the desired part of the panel
				
				entitylbl.setLocation(510,250);//sets the location of the component 
				entitylbl.setText("Patient:");//the label is geven text to add meaning to the label
				entitylbl.setVisible(true);//the component is set so it can be seen
				
				usernameTF.setLocation(527,350);//sets the location of the component 
				usernameTF.setVisible(true);//the component is set so it can be seen
				
				loginPasswordF.setLocation(527,400);//sets the location of the component 
				loginPasswordF.setVisible(true);//the component is set so it can be seen
				
				realLoginbttn.setLocation(787,340);//sets the location of the component 
				realLoginbttn.setVisible(true);//the component is set so it can be seen
				realLoginbttn.setEnabled(true);
				createAccountbbtn.setLocation(787,390);//sets the location of the component 
				createAccountbbtn.setVisible(true);//the component is set so it can be seen
				
				loginChoice = 0;//the type of user login this will determine what user they will be logged in as
			}
			public void openButtonAdmin()//the method is declared as public with no parameters to pass through
			{
				selectPatientloginBttn.setVisible(true);//the component is set so it can be seen
				selectManagementloginBttn.setVisible(true);//the component is set so it can be seen
				selectConsultantloginBttn.setVisible(true);//the component is set so it can be seen

				Bluebox.setOpaque(true);//the component is set to opaque
				Bluebox.setVisible(true);//the component is set so it can be seen
				Bluebox.setLocation(497,400);//sets the location of the component 
				
				selectAdminloginBttn.setVisible(false);//the component is set so it cant be seen
				selectPatientloginBttn.setLocation(637,340);//sets the location of the component 
				selectManagementloginBttn.setLocation(637,590);//sets the location of the component 
				selectConsultantloginBttn.setLocation(637,710);//sets the location of the component 
				
				entitylbl.setLocation(510,350);//sets the location of the component 
				entitylbl.setText("Admin:");//the label is geven text to add meaning to the label
				entitylbl.setVisible(true);//the component is set so it can be seen
				
				usernameTF.setLocation(527,450);//sets the location of the component 
				usernameTF.setVisible(true);//the component is set so it can be seen
				
				loginPasswordF.setLocation(527,500);//sets the location of the component 
				loginPasswordF.setVisible(true);//
				
				realLoginbttn.setLocation(787,440);//sets the location of the component 
				realLoginbttn.setVisible(true);//the component is set so it can be seen
				realLoginbttn.setEnabled(false);
				createAccountbbtn.setLocation(787,490);//sets the location of the component 
				createAccountbbtn.setVisible(true);//the component is set so it can be seen
				
				loginChoice = 1;//the type of user login this will determine what user they will be logged in as
			}
			public void openButtonManagment()//the method is declared as public with no parameters to pass through
			{
				selectPatientloginBttn.setVisible(true);//the component is set so it can be seen
				selectAdminloginBttn.setVisible(true);//the component is set so it can be seen
				selectConsultantloginBttn.setVisible(true);//the component is set so it can be seen

				Bluebox.setLocation(497,510);//sets the location of the component 
				Bluebox.setOpaque(true);//the component is set to opaque
				Bluebox.setVisible(true);//the component is set so it can be seen
				
				entitylbl.setText("Management:");//the label is geven text to add meaning to the label
				entitylbl.setLocation(510,460);//sets the location of the component 

				entitylbl.setVisible(true);//the component is set so it can be seen
				
				selectManagementloginBttn.setVisible(false);//the component is set so it cant be seen
				selectPatientloginBttn.setLocation(637,340);//sets the location of the component 
				selectAdminloginBttn.setLocation(637,440);//sets the location of the component 
				selectConsultantloginBttn.setLocation(637,710);//sets the location of the component 
				
				usernameTF.setLocation(527,560);//sets the location of the component 
				usernameTF.setVisible(true);//the component is set so it can be seen
				
				loginPasswordF.setLocation(527,610);//sets the location of the component 
				loginPasswordF.setVisible(true);//the component is set so it can be seen
				
				realLoginbttn.setLocation(787,550);//sets the location of the component 
				realLoginbttn.setVisible(true);//the component is set so it can be seen
				realLoginbttn.setEnabled(true);
				createAccountbbtn.setLocation(787,600);//sets the location of the component 
				createAccountbbtn.setVisible(true);//the component is set so it can be seen
				
				loginChoice = 2;//the type of user login this will determine what user they will be logged in as
			}
			public void openButtonConsultant()//the method is declared as public with no parameters to pass through
			{
		selectPatientloginBttn.setVisible(true);//the component is set so it can be seen
		selectManagementloginBttn.setVisible(true);//the component is set so it can be seen
		selectAdminloginBttn.setVisible(true);//the component is set so it can be seen

		Bluebox.setLocation(497,640);//sets the location of the component 
		Bluebox.setOpaque(true);//the component is set to opaque
		Bluebox.setVisible(true);//the component is set so it can be seen

		
		entitylbl.setLocation(510,590);//sets the location of the component 
		entitylbl.setText("Consultant:");//the label is geven text to add meaning to the label
		entitylbl.setVisible(true);//the component is set so it can be seen
		
		selectConsultantloginBttn.setVisible(false);//the component is set so it cant be seen
		selectPatientloginBttn.setLocation(637,340);//sets the location of the component 
		selectManagementloginBttn.setLocation(637,550);//sets the location of the component 
		selectAdminloginBttn.setLocation(637,440);//sets the location of the component 
		
		usernameTF.setLocation(527,690);//sets the location of the component 
		usernameTF.setVisible(true);//the component is set so it can be seen
		
		loginPasswordF.setLocation(527,740);//sets the location of the component 
		loginPasswordF.setVisible(true);//the component is set so it can be seen
		
		realLoginbttn.setLocation(787,680);//sets the location of the component 
		realLoginbttn.setVisible(true);//the component is set so it can be seen
		realLoginbttn.setEnabled(true);

		createAccountbbtn.setLocation(787,730);//sets the location of the component 
		createAccountbbtn.setVisible(true);//the component is set so it can be seen
		
		loginChoice = 3;//the type of user login this will determine what user they will be logged in as
	}	
	
	//Patient Homepage
	
	public void createPatientHomepagePanelGUI(Patient currentPatient)//once the login button has been pressed the login page will be opened up and then the homepage will be created and displayed
	{
		if(loaded[3][0]== false)//selection determines whether the panel is yet to be loaded
		{
			createMainPartPatientHomePageGeneral();//the components of the panel are cereated by running the method this is done initially so it is only done once and will be used throughout
			loaded[3][0]= true;//the variable is set as true to prevent the components from breing reran
		}
		emptyArray(patientHomepagePanel);
		System.out.println("patientHomepagePanel");
		createTopbar(patientHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		createContactBarPatient(patientHomepagePanel,currentPatient);//the method that creates the contact bar is used with the current panel is being passed through
		createMainPartPatientHomePage(patientHomepagePanel);//the rest of the panel is created through this, other  components are created through methods that can be utilised by other panels
	}
		public void createMainPartPatientHomePage(JPanel panel)//this will create the main part of the patient's homepage
		{
		updateNotifications();//their notifications are immediatley updated to include any new alterations
		panel.add(noNotifications);//the component is added to the panel
		blackLine16.setLocation(665,290);//sets the location of the component 
		blackLine16.setSize(170,3);//the components size is correctly declared
		panel.add(blackLine16); //the component is added to the panel
		
		blackLine17.setLocation(720,360);//sets the location of the component 
		blackLine17.setSize(115,3);//the components size is correctly declared
		panel.add(blackLine17); //the component is added to the panel
		
		blackLine18.setLocation(720,430);//sets the location of the component 
		blackLine18.setSize(115,3);//the components size is correctly declared
		panel.add(blackLine18); //the component is added to the panel
		
		blackLine19.setLocation(1082,290);//sets the location of the component 
		blackLine19.setSize(170,3);//the components size is correctly declared
		panel.add(blackLine19); //the component is added to the panel
		
		blackLine20.setLocation(1137,360);//sets the location of the component 
		blackLine20.setSize(115,3);//the components size is correctly declared
		panel.add(blackLine20); //the component is added to the panel
		
		blackLine21.setLocation(1137,430);//sets the location of the component 
		blackLine21.setSize(115,3);//the components size is correctly declared
		panel.add(blackLine21); //the component is added to the panel
		
		panel.add(darkgreyBox1);//the component is added to the panel
		darkgreyBox1.setLocation(543,575);//the location of the component is declared
		darkgreyBox1.setSize(320,281);//the size of the component is also set 
		
		newAdmissionBttn.setLocation(960,575);//the location of the component is declared
		newAdmissionBttn.setSize(320,285);//the size of the component is also set 
		
		panel.add(whiteBox2);//the component is added to the panel
		whiteBox2.setLocation(543,180);//the location of the component is declared
		whiteBox2.setSize(320,345);//the size of the component is also set 

		panel.add(whiteBox3);//the component is added to the panel
		whiteBox3.setLocation(960,180);//the location of the component is declared
		whiteBox3.setSize(320,345);//the size of the component is also set
		if(currentPatient.numberOfAdmissions>4)
		{
			newAdmissionBttn.setEnabled(false);
		}
		if(currentPatient.numberOfAdmissions<=4)
		{
			newAdmissionBttn.setEnabled(true);
		}
	}
			public void createMainPartPatientHomePageGeneral()
			{
		
		noNotifications.setSize(300,100); //the components size is correctly declared
		noNotifications.setLocation(553,583); //sets the location of the component 
		noNotifications.setText("You have no new\nnotifications");//text is given to the label to add meaning
		noNotifications.setFont(headerFontFormatBlack);//the font that has been declared is attached to the component
		noNotifications.setEditable(false);//the component is prevented from being edited by the user
		patientHomepagePanel.add(noNotifications);//the component is added to the panel
		
		newAdmissionBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		newAdmissionBttn.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		newAdmissionBttn.setForeground(darkGreyColour);//the foreground of the component is given a white font
		newAdmissionBttn.setBackground(darkGreyColour);//the background colour of the component is declared to the desired value 
		newAdmissionBttn.setOpaque(false);//the component is set to opaque
		patientHomepagePanel.add(newAdmissionBttn);//the component is added to the panel
		
		newAdmissionLbl.setIcon( new ImageIcon("newAdmissionLbl.png") );//the correct image is retrieved from the folder
		newAdmissionLbl.setVisible(true);//the component is set so it can be seen
		newAdmissionLbl.setSize(320,285); //the components size is correctly declared
		newAdmissionLbl.setLocation(960,575); //sets the location of the component 
		patientHomepagePanel.add(newAdmissionLbl);//the component is added to the panel
		
		newAdmissionBttn.addMouseListener(mouseListenerAdmissionBttn);//the newly declared mouselistner is attached to the button to look out for interactions
		
		createAdmissionCard1();//the admission card is generated
		createAdmissionCard2();//the admission card is generated
	
		darkgreyBox1.setForeground(darkGreyColour);//the foreground colour is set for the component 
		darkgreyBox1.setBackground(darkGreyColour);//the background colour is set for the component 
		darkgreyBox1.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		darkgreyBox1.setOpaque(true);//the object is set to opaque so the colours can be seen
		
		notificationLbl.setSize(400,50); //the components size is correctly declared
		notificationLbl.setLocation(573,525); //sets the location of the component 
		notificationLbl.setText("Notifications");//text is given to the label to add meaning
		notificationLbl.setFont(headerFontFormatBlack);//the font that has been declared is attached to the component
		patientHomepagePanel.add(notificationLbl);//the component is added to the panel
	
		notificationTf1.setSize(240,40);//the size of the component is also set 
		notificationTf1.setEditable(false);//the text feild is set to diabled as it is us who wants to add text, this allows for multilines so we have to use this instead of a label
		notificationTf1.setLocation(553,583);//the location of the component is declared
		notificationTf1.setOpaque(false);//the component is set to opaque
		patientHomepagePanel.add(notificationTf1);//the component is added to the panel
		
		notificationTf2.setSize(240,40);//the size of the component is also set 
		notificationTf2.setEditable(false);//the text feild is set to diabled as it is us who wants to add text, this allows for multilines so we have to use this instead of a label
		notificationTf2.setLocation(553,640);//the location of the component is declared
		notificationTf2.setOpaque(false);//the component is set to opaque
		patientHomepagePanel.add(notificationTf2);//the component is added to the panel
		
		notificationTf3.setSize(240,40);//the size of the component is also set 
		notificationTf3.setEditable(false);//the text feild is set to diabled as it is us who wants to add text, this allows for multilines so we have to use this instead of a label
		notificationTf3.setLocation(553,696);//the location of the component is declared
		notificationTf3.setOpaque(false);//the component is set to opaque
		patientHomepagePanel.add(notificationTf3);//the component is added to the panel
		
		notificationTf4.setSize(240,40);//the size of the component is also set 
		notificationTf4.setEditable(false);//the text feild is set to diabled as it is us who wants to add text, this allows for multilines so we have to use this instead of a label
		notificationTf4.setLocation(553,752);//the location of the component is declared
		notificationTf4.setOpaque(false);//the component is set to opaque
		patientHomepagePanel.add(notificationTf4);//the component is added to the panel
		
		notificationTf5.setSize(240,40);//the size of the component is also set 
		notificationTf5.setEditable(false);//the text feild is set to diabled as it is us who wants to add text, this allows for multilines so we have to use this instead of a label
		notificationTf5.setLocation(553,808);//the location of the component is declared
		notificationTf5.setOpaque(false);//the component is set to opaque
		patientHomepagePanel.add(notificationTf5);//the component is added to the panel
		
		closeNotificationBttn1.setOpaque(true);//the object is set to opaque so the colours can be seen
		closeNotificationBttn1.setForeground(new Color(-1));//the font in the foreground is also given a sharp contrast with the background for easy viewing 
		closeNotificationBttn1.setBackground(darkButtonGrey);//background is set to that of a light grey
		closeNotificationBttn1.setFont(symptomfont);//the font that has been declared is attached to the component
		closeNotificationBttn1.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		closeNotificationBttn1.setText("X");//as it is a close button the x is used to indicate its function of the button
		closeNotificationBttn1.setSize(45,45);//the size of the component is also set 
		closeNotificationBttn1.setLocation(800,582);//the location of the component is declared
		patientHomepagePanel.add(closeNotificationBttn1);//the component is added to the panel
		
		closeNotificationBttn2.setOpaque(true);//the object is set to opaque so the colours can be seen
		closeNotificationBttn2.setForeground(new Color(-1));//the font in the foreground is also given a sharp contrast with the background for easy viewing 
		closeNotificationBttn2.setBackground(darkButtonGrey);//background is set to that of a light grey
		closeNotificationBttn2.setFont(symptomfont);//the font that has been declared is attached to the component
		closeNotificationBttn2.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		closeNotificationBttn2.setText("X");//as it is a close button the x is used to indicate its function of the button
		closeNotificationBttn2.setSize(45,45);//the size of the component is also set 
		closeNotificationBttn2.setLocation(800,639);//the location of the component is declared
		patientHomepagePanel.add(closeNotificationBttn2);//the component is added to the panel
		
		closeNotificationBttn3.setOpaque(true);//the object is set to opaque so the colours can be seen
		closeNotificationBttn3.setForeground(new Color(-1));//the font in the foreground is also given a sharp contrast with the background for easy viewing 
		closeNotificationBttn3.setBackground(darkButtonGrey);//background is set to that of a light grey
		closeNotificationBttn3.setFont(symptomfont);//the font that has been declared is attached to the component
		closeNotificationBttn3.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		closeNotificationBttn3.setText("X");//as it is a close button the x is used to indicate its function of the button
		closeNotificationBttn3.setSize(45,45);//the size of the component is also set 
		closeNotificationBttn3.setLocation(800,695);//the location of the component is declared
		patientHomepagePanel.add(closeNotificationBttn3);//the component is added to the panel
		
		closeNotificationBttn4.setOpaque(true);//the object is set to opaque so the colours can be seen
		closeNotificationBttn4.setForeground(new Color(-1));//the font in the foreground is also given a sharp contrast with the background for easy viewing 
		closeNotificationBttn4.setBackground(darkButtonGrey);//background is set to that of a light grey
		closeNotificationBttn4.setFont(symptomfont);//the font that has been declared is attached to the component
		closeNotificationBttn4.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		closeNotificationBttn4.setText("X");//as it is a close button the x is used to indicate its function of the button
		closeNotificationBttn4.setSize(45,45);//the size of the component is also set 
		closeNotificationBttn4.setLocation(800,751);//the location of the component is declared
		patientHomepagePanel.add(closeNotificationBttn4);//the component is added to the panel
		
		closeNotificationBttn5.setOpaque(true);//the object is set to opaque so the colours can be seen
		closeNotificationBttn5.setForeground(new Color(-1));//the font in the foreground is also given a sharp contrast with the background for easy viewing 
		closeNotificationBttn5.setBackground(darkButtonGrey);//background is set to that of a light grey
		closeNotificationBttn5.setFont(symptomfont);//the font that has been declared is attached to the component
		closeNotificationBttn5.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		closeNotificationBttn5.setText("X");//as it is a close button the x is used to indicate its function of the button
		closeNotificationBttn5.setSize(45,45);//the size of the component is also set 
		closeNotificationBttn5.setLocation(800,807);//the location of the component is declared
		patientHomepagePanel.add(closeNotificationBttn5);//the component is added to the panel
		
		notificationBox1.setForeground( new Color(1) );//the font in the foreground is also given a sharp contrast with the background for easy viewing 
		notificationBox1.setBackground( new Color(-1) );//the notification box it set to white to have a sharp contrast with the dark grey
		notificationBox1.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		notificationBox1.setSize(320,57);//the size of the component is also set 
		notificationBox1.setLocation(543,575);//the location of the component is declared
		notificationBox1.setOpaque(true);//the component is set to opaque
		patientHomepagePanel.add(notificationBox1);//the component is added to the panel
		
		notificationBox2.setForeground( new Color(1) );//the font in the foreground is also given a sharp contrast with the background for easy viewing 
		notificationBox2.setBackground( new Color(-1) );//the notification box it set to white to have a sharp contrast with the dark grey
		notificationBox2.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		notificationBox2.setSize(320,56);//the size of the component is also set 
		notificationBox2.setLocation(543,632);//the location of the component is declared
		notificationBox2.setOpaque(true);//the component is set to opaque
		patientHomepagePanel.add(notificationBox2);//the component is added to the panel
		
		notificationBox3.setForeground( new Color(1) );//the font in the foreground is also given a sharp contrast with the background for easy viewing 
		notificationBox3.setBackground( new Color(-1) );//the notification box it set to white to have a sharp contrast with the dark grey
		notificationBox3.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		notificationBox3.setSize(320,56);//the size of the component is also set 
		notificationBox3.setLocation(543,688);//the location of the component is declared
		notificationBox3.setOpaque(true);//the component is set to opaque
		patientHomepagePanel.add(notificationBox3);//the component is added to the panel
		
		notificationBox4.setForeground( new Color(1) );//the font in the foreground is also given a sharp contrast with the background for easy viewing 
		notificationBox4.setBackground( new Color(-1) );//the notification box it set to white to have a sharp contrast with the dark grey
		notificationBox4.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		notificationBox4.setSize(320,56);//the size of the component is also set 
		notificationBox4.setLocation(543,744);//the location of the component is declared
		notificationBox4.setOpaque(true);//the component is set to opaque
		patientHomepagePanel.add(notificationBox4);//the component is added to the panel
		
		notificationBox5.setForeground( new Color(1) );//the font in the foreground is also given a sharp contrast with the background for easy viewing 
		notificationBox5.setBackground( new Color(-1) );//the notification box it set to white to have a sharp contrast with the dark grey 
		notificationBox5.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		notificationBox5.setSize(320,56);//the size of the component is also set 
		notificationBox5.setLocation(543,800);//the location of the component is declared
		notificationBox5.setOpaque(true);//the component is set to opaque
		patientHomepagePanel.add(notificationBox5);//the component is added to the panel
	}
				public void createAdmissionCard1()
				{
		admissionCardviewAdmission1bbtn.setLocation(573,195);//the location of the component is declared
		admissionCardviewAdmission1bbtn.setSize(260,40);//the components size is correctly declared
		admissionCardviewAdmission1bbtn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		admissionCardviewAdmission1bbtn.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		admissionCardviewAdmission1bbtn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		admissionCardviewAdmission1bbtn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		admissionCardviewAdmission1bbtn.setOpaque(true);//the component is set to opaque
		admissionCardviewAdmission1bbtn.setText("View Admission");//text is given to the label to add meaning
		admissionCardviewAdmission1bbtn.setEnabled(false);
		admissionCardviewAdmission1bbtn.setFont(headerFontFormatBlack);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admissionCardviewAdmission1bbtn);//the component is added to the panel
		
		admission1Lbl.setSize(400,50); //the components size is correctly declared
		admission1Lbl.setLocation(573,130); //sets the location of the component 
		admission1Lbl.setText("Admission 1 (Ailment)");//text is given to the label to add meaning
		admission1Lbl.setFont(headerFontFormatBlack);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admission1Lbl);//the component is added to the panel
		
		admissionCardConsultantLbl1.setSize(400,50); //the components size is correctly declared
		admissionCardConsultantLbl1.setLocation(573,255); //sets the location of the component 
		admissionCardConsultantLbl1.setText("Consultant:");//text is given to the label to add meaning
		admissionCardConsultantLbl1.setFont(symptomfont);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admissionCardConsultantLbl1);//the component is added to the panel
		
		blackLine16.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine16.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine16.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine16.setOpaque(true);//the component is set to opaque
		
		admissionCardConsultantFeild1.setSize(400,50); //the components size is correctly declared
		admissionCardConsultantFeild1.setLocation(700,255); //sets the location of the component 
		admissionCardConsultantFeild1.setText("Dr Jones");//text is given to the label to add meaning
		admissionCardConsultantFeild1.setFont(headerFontFormatBlack);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admissionCardConsultantFeild1);//the component is added to the panel
		
		admissionCardDOALbl1.setSize(400,50); //the components size is correctly declared
		admissionCardDOALbl1.setLocation(573,325); //sets the location of the component 
		admissionCardDOALbl1.setText("Date of Admission:");//text is given to the label to add meaning
		admissionCardDOALbl1.setFont(symptomfont);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admissionCardDOALbl1);//the component is added to the panel
		
		blackLine17.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine17.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine17.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine17.setOpaque(true);//the component is set to opaque
		
		admissionCardDOAFeild1.setSize(400,50); //the components size is correctly declared
		admissionCardDOAFeild1.setLocation(730,325); //sets the location of the component 
		admissionCardDOAFeild1.setText("18/07/17");//text is given to the label to add meaning
		admissionCardDOAFeild1.setFont(headerFontFormatBlack);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admissionCardDOAFeild1);//the component is added to the panel
		
		admissionCardNextAppointmentLbl1.setSize(400,50); //the components size is correctly declared
		admissionCardNextAppointmentLbl1.setLocation(573,395); //sets the location of the component 
		admissionCardNextAppointmentLbl1.setText("Next Appointment:");//text is given to the label to add meaning
		admissionCardNextAppointmentLbl1.setFont(symptomfont);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admissionCardNextAppointmentLbl1);//the component is added to the panel
		
		blackLine18.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine18.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine18.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine18.setOpaque(true);//the component is set to opaque
		
		admissionCardNextAppointmentFeild1.setSize(400,50); //the components size is correctly declared
		admissionCardNextAppointmentFeild1.setLocation(730,395); //sets the location of the component 
		admissionCardNextAppointmentFeild1.setText("18/01/20");//text is given to the label to add meaning
		admissionCardNextAppointmentFeild1.setFont(headerFontFormatBlack);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admissionCardNextAppointmentFeild1);//the component is added to the panel
		
		admissionCardDischargedLbl1.setSize(400,50); //the components size is correctly declared
		admissionCardDischargedLbl1.setLocation(573,465); //sets the location of the component 
		admissionCardDischargedLbl1.setText("Discharged:");//text is given to the label to add meaning
		admissionCardDischargedLbl1.setFont(symptomfont);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admissionCardDischargedLbl1);//the component is added to the panel
		
		admissionCardDischargedFeild1.setSize(400,50); //the components size is correctly declared
		admissionCardDischargedFeild1.setLocation(700,465); //sets the location of the component 
		admissionCardDischargedFeild1.setText("false");//text is given to the label to add meaning
		admissionCardDischargedFeild1.setFont(headerFontFormatBlack);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admissionCardDischargedFeild1);//the component is added to the panel
	}
				public void createAdmissionCard2()
				{
		admissionCardviewAdmission2bbtn.setSize(260,40);//the components size is correctly declared
		admissionCardviewAdmission2bbtn.setLocation(990,195);//the location of the component is declared
		admissionCardviewAdmission2bbtn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		admissionCardviewAdmission2bbtn.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		admissionCardviewAdmission2bbtn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		admissionCardviewAdmission2bbtn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		admissionCardviewAdmission2bbtn.setOpaque(true);//the component is set to opaque
		admissionCardviewAdmission2bbtn.setText("View Admission");//text is given to the label to add meaning
		admissionCardviewAdmission2bbtn.setEnabled(false);
		admissionCardviewAdmission2bbtn.setFont(headerFontFormatBlack);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admissionCardviewAdmission2bbtn);//the component is added to the panel
	
		admission2Lbl.setSize(400,50); //the components size is correctly declared
		admission2Lbl.setLocation(990,130); //sets the location of the component 
		admission2Lbl.setText("Admission 2 (Ailment)");//text is given to the label to add meaning
		admission2Lbl.setFont(headerFontFormatBlack);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admission2Lbl);//the component is added to the panel
		
		admissionCardConsultantLbl2.setSize(400,50); //the components size is correctly declared
		admissionCardConsultantLbl2.setLocation(990,255); //sets the location of the component 
		admissionCardConsultantLbl2.setText("Consultant:");//text is given to the label to add meaning
		admissionCardConsultantLbl2.setFont(symptomfont);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admissionCardConsultantLbl2);//the component is added to the panel
		
		blackLine19.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine19.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine19.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine19.setOpaque(true);//the component is set to opaque
		
		admissionCardConsultantFeild2.setSize(400,50); //the components size is correctly declared
		admissionCardConsultantFeild2.setLocation(1117,255); //sets the location of the component 
		admissionCardConsultantFeild2.setText("Dr Jones");//text is given to the label to add meaning
		admissionCardConsultantFeild2.setFont(headerFontFormatBlack);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admissionCardConsultantFeild2);//the component is added to the panel
		
		admissionCardDOALbl2.setSize(400,50); //the components size is correctly declared
		admissionCardDOALbl2.setLocation(990,325); //sets the location of the component 
		admissionCardDOALbl2.setText("Date of Admission:");//text is given to the label to add meaning
		admissionCardDOALbl2.setFont(symptomfont);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admissionCardDOALbl2);//the component is added to the panel
		
		blackLine20.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine20.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine20.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine20.setOpaque(true);//the component is set to opaque
		
		admissionCardDOAFeild2.setSize(400,50); //the components size is correctly declared
		admissionCardDOAFeild2.setLocation(1147,325); //sets the location of the component 
		admissionCardDOAFeild2.setText("18/07/17");//text is given to the label to add meaning
		admissionCardDOAFeild2.setFont(headerFontFormatBlack);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admissionCardDOAFeild2);//the component is added to the panel
		
		admissionCardNextAppointmentLbl2.setSize(400,50); //the components size is correctly declared
		admissionCardNextAppointmentLbl2.setLocation(990,395); //sets the location of the component 
		admissionCardNextAppointmentLbl2.setText("Next Appointment:");//text is given to the label to add meaning
		admissionCardNextAppointmentLbl2.setFont(symptomfont);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admissionCardNextAppointmentLbl2);//the component is added to the panel
		
		blackLine21.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine21.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine21.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine21.setOpaque(true);//the component is set to opaque
		
		admissionCardNextAppointmentFeild2.setSize(400,50); //the components size is correctly declared
		admissionCardNextAppointmentFeild2.setLocation(1147,395); //sets the location of the component 
		admissionCardNextAppointmentFeild2.setText("18/01/20");//text is given to the label to add meaning
		admissionCardNextAppointmentFeild2.setFont(headerFontFormatBlack);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admissionCardNextAppointmentFeild2);//the component is added to the panel
		
		admissionCardDischargedLbl2.setSize(400,50); //the components size is correctly declared
		admissionCardDischargedLbl2.setLocation(990,465); //sets the location of the component 
		admissionCardDischargedLbl2.setText("Discharged:");//text is given to the label to add meaning
		admissionCardDischargedLbl2.setFont(symptomfont);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admissionCardDischargedLbl2);//the component is added to the panel
		
		admissionCardDischargedFeild2.setSize(400,50); //the components size is correctly declared
		admissionCardDischargedFeild2.setLocation(1117,465); //sets the location of the component 
		admissionCardDischargedFeild2.setText("false");//text is given to the label to add meaning
		admissionCardDischargedFeild2.setFont(headerFontFormatBlack);//the font that has been declared is attached to the component
		patientHomepagePanel.add(admissionCardDischargedFeild2);//the component is added to the panel
		
	}
				
				//notifications
				
				public void updateNotifications()//before the homepage loads in this willl be used to update the visual aspect of the notifications, all the array algorithms is dealt in another meathod, this just re-adds the index of notfications back to the text feilds
				{
		//the text boxes are intially made invisible as there is a chance no notifications are there 
		notificationBox1.setVisible(false);//component is set to invisible 
		notificationBox2.setVisible(false);//component is set to invisible 
		notificationBox3.setVisible(false);//component is set to invisible 
		notificationBox4.setVisible(false);//component is set to invisible 
		notificationBox5.setVisible(false);//component is set to invisible 
		noNotifications.setVisible(false);//hides component
		closeNotificationBttn1.setVisible(false);//component is set to invisible 
		closeNotificationBttn2.setVisible(false);//component is set to invisible 
		closeNotificationBttn3.setVisible(false);//component is set to invisible 
		closeNotificationBttn4.setVisible(false);//component is set to invisible 
		closeNotificationBttn5.setVisible(false);//component is set to invisible 
		
		notificationTf1.setVisible(false);//component is set to invisible 
		notificationTf2.setVisible(false);//component is set to invisible 
		notificationTf3.setVisible(false);//component is set to invisible 
		notificationTf4.setVisible(false);//component is set to invisible 
		notificationTf5.setVisible(false);//component is set to invisible
		//outputALl();
		if(numberOfNotifications == 1)//selection determining how many notifications there are
		{
			noNotifications.setVisible(true);//component is set to visible
			notificationTf1.setVisible(true);//component is set to visible
		}
		if(numberOfNotifications == 2)//if only 1 notification exists then only one of the 5 will be created
		{
			notificationBox1.setVisible(true);//component is set to visible
			notificationTf1.setVisible(true);//component is set to visible
			closeNotificationBttn1.setVisible(true);//component is set to visible
			createNotification(1,notificationTf1,notifications[1]);//here the method will pass throgh the number of which notification box will need to be used along with the text feild to be written to and the actual text itself
		}
		if(numberOfNotifications == 3)//if only 2 notification exists then only two of the 5 will be created
		{
			notificationBox1.setVisible(true);//component is set to visible
			notificationTf1.setVisible(true);//component is set to visible
			closeNotificationBttn1.setVisible(true);//component is set to visible
			createNotification(1,notificationTf1,notifications[1]);//here the method will pass throgh the number of which notification box will need to be used along with the text feild to be written to and the actual text itself
			
			notificationBox2.setVisible(true);//component is set to visible
			notificationTf2.setVisible(true);//component is set to visible
			closeNotificationBttn2.setVisible(true);//component is set to visible
			createNotification(2,notificationTf2,notifications[2]);//here the method will pass throgh the number of which notification box will need to be used along with the text feild to be written to and the actual text itself
		}
		if(numberOfNotifications == 4)//if only 3 notification exists then only three of the 5 will be created
		{
			notificationBox1.setVisible(true);//component is set to visible
			notificationTf1.setVisible(true);//component is set to visible
			closeNotificationBttn1.setVisible(true);//component is set to visible
			createNotification(1,notificationTf1,notifications[1]);//here the method will pass throgh the number of which notification box will need to be used along with the text feild to be written to and the actual text itself
			
			notificationBox2.setVisible(true);//component is set to visible
			notificationTf2.setVisible(true);//component is set to visible
			closeNotificationBttn2.setVisible(true);//component is set to visible
			createNotification(2,notificationTf2,notifications[2]);//here the method will pass throgh the number of which notification box will need to be used along with the text feild to be written to and the actual text itself
			
			notificationBox3.setVisible(true);//component is set to visible
			notificationTf3.setVisible(true);//component is set to visible
			closeNotificationBttn3.setVisible(true);//component is set to visible
			createNotification(3,notificationTf3,notifications[3]);//here the method will pass throgh the number of which notification box will need to be used along with the text feild to be written to and the actual text itself
		}
		if(numberOfNotifications == 5)//if only 4 notification exists then only 4 of the 5 will be created
		{
			notificationBox1.setVisible(true);//component is set to visible
			notificationTf1.setVisible(true);//component is set to visible
			closeNotificationBttn1.setVisible(true);//component is set to visible
			createNotification(1,notificationTf1,notifications[1]);//here the method will pass throgh the number of which notification box will need to be used along with the text feild to be written to and the actual text itself
			
			notificationBox2.setVisible(true);//component is set to visible
			notificationTf2.setVisible(true);//component is set to visible
			closeNotificationBttn2.setVisible(true);//component is set to visible
			createNotification(2,notificationTf2,notifications[2]);//here the method will pass throgh the number of which notification box will need to be used along with the text feild to be written to and the actual text itself
			
			notificationBox3.setVisible(true);//component is set to visible
			notificationTf3.setVisible(true);//component is set to visible
			closeNotificationBttn3.setVisible(true);//component is set to visible
			createNotification(3,notificationTf3,notifications[3]);//here the method will pass throgh the number of which notification box will need to be used along with the text feild to be written to and the actual text itself
			
			notificationBox4.setVisible(true);//component is set to visible
			notificationTf4.setVisible(true);//component is set to visible
			closeNotificationBttn4.setVisible(true);//component is set to visible
			createNotification(4,notificationTf4,notifications[4]);//here the method will pass throgh the number of which notification box will need to be used along with the text feild to be written to and the actual text itself
			
		}
		if(numberOfNotifications >= 6)//if more than or 5 notification exists then all of the 5 will be created
		{
			notificationBox1.setVisible(true);//component is set to visible
			notificationTf1.setVisible(true);//component is set to visible
			closeNotificationBttn1.setVisible(true);//component is set to visible
			createNotification(1,notificationTf1,notifications[1]);//here the method will pass throgh the number of which notification box will need to be used along with the text feild to be written to and the actual text itself
			
			notificationBox2.setVisible(true);//component is set to visible
			closeNotificationBttn2.setVisible(true);//component is set to visible
			notificationTf2.setVisible(true);//component is set to visible
			createNotification(2,notificationTf2,notifications[2]);//here the method will pass throgh the number of which notification box will need to be used along with the text feild to be written to and the actual text itself
			
			notificationBox3.setVisible(true);//component is set to visible
			notificationTf3.setVisible(true);//component is set to visible
			closeNotificationBttn3.setVisible(true);//component is set to visible
			createNotification(3,notificationTf3,notifications[3]);//here the method will pass throgh the number of which notification box will need to be used along with the text feild to be written to and the actual text itself
			
			notificationBox4.setVisible(true);//component is set to visible
			closeNotificationBttn4.setVisible(true);//component is set to visible
			notificationTf4.setVisible(true);//component is set to visible
			createNotification(4,notificationTf4,notifications[4]);//here the method will pass throgh the number of which notification box will need to be used along with the text feild to be written to and the actual text itself

			notificationBox5.setVisible(true);//component is set to visible
			closeNotificationBttn5.setVisible(true);//component is set to visible
			notificationTf5.setVisible(true);//component is set to visible
			createNotification(5,notificationTf5,notifications[5]);//here the method will pass throgh the number of which notification box will need to be used along with the text feild to be written to and the actual text itself
		}
	}
				public void createNotification(int position, JTextPane jTF, String text)//method which creates the notification
				{
					jTF.setText(text);//the text feild that will hold the data has the notification from the array passed through as a parameter
				}
				public void outputALl()//method outputs contents of array of notifications 
				{
					//System.out.println("=====================");
					int length = notifications.length;//finds how many notifications there are
					for(int counter = 0; counter<length;counter++)//for loop that iterates through every notificaction
					{
						System.out.println(notifications[counter]);//outputs the current notification
						
					}
					//System.out.println("=====================");
				}
				public void deleteNotification(int position)//the parameter is the position in the array containg the notification wanting removed
				{  
		int iterations = numberOfNotifications - position-1;//the number of iterations is calculated to find the the number of swaps needed
			if(iterations == 0)//if no more swaps can occur this is ran(there is only one item left in the array)
			{
				notifications[position] = null;//the contents are emptied
				numberOfNotifications--;//the number of notfications is decrimented
			}
			else
			{
				do//a do until loop is declared to iterate until the condition is satisfied
				{
					notifications[position] = notifications[position +1];//the current notification is replaced by the newer one, no need for a buffer value as the first one is the deleted value
					iterations --;//the number of iterations required decriments
					position++;// the position of the index incriments as now two indexes will be the same, this porcess repeats until the end of the array is reached
					
				}while(iterations!=0);//termination condition looking untill no more iterations can occur
				notifications[position] = null;//the last position of the array is cleared as this will contain a copied value
				numberOfNotifications--;//the number of iterations left decriments 
			}
			updateNotifications();//the gui aspect of this task now occurs the data is now in the right order and so the gui is re-rendered to contain the new notificactions excluding the intended one
	}
	
	
	//Consultant Homepage
	
	public void createConsultantHomepagePanelGUI(Consultant currentConsultant)//once the login button has been pressed the login page will be opened up and then the homepage will be created and displayed
	{
		if(loaded[5][0]== false)//selection determines whether the panel is yet to be loaded
		{
			createMainPartConsultnantHomePageGeneral();//the components of the panel are cereated by running the method this is done initially so it is only done once and will be used throughout
			loaded[5][0]= true;//the variable is set as true to prevent the components from breing reran
		}
		emptyArray(consultantHomepagePanel);
		System.out.println("consultantHomepagePanel");
		createTopbar(consultantHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		Date closestDateForConsultant = new Date(); 
		try
		{
		closestAppointment  = ftTimeInc.parse("12/05/2999 12:01");//creates a date in the distnat future so that any appouintment will come before it
		}
		catch(Exception exc)//no exception as it will always work
		{
			
		}
		if(currentConsultant.numberOfPatients>0)//selection determining if the consultant has at leat one patient
		{
			for(int outerCounter = 0;outerCounter<listofAdmissions.length;outerCounter++)//for loop that will run for the exact amount of admisisons there are
			{
				for(int innerCount = 0;listofAdmissions[outerCounter][innerCount]!=null;innerCount++)//runs untill the specific admission in the list is null as it is [patient][admission]
				{
					if(listofAdmissions[outerCounter][innerCount].dateOfNextAppointmentA.after(todaysDate))//selection determining if the date for the next appointment is after the current date
						{
							if(listofAdmissions[outerCounter][innerCount].dateOfNextAppointmentA.before(closestAppointment))//selection determining if the date of the next appointment is before the most current admission for the consultant
							{
								closestAppointment = listofAdmissions[outerCounter][innerCount].dateOfNextAppointmentA;//updates the closest appointment to be the current admission in the list
								closestAppointmentPatient = listOfCsPatients[outerCounter].firstName+" "+listOfCsPatients[outerCounter].surName;//updates the closest appointment to be the current admission in the list
								date = true;//informs the system that a closest appointment exists
							}
							
						}
						closestAppointmentDay = ft.format(closestAppointment);//cortrectly formats the date to be a date rather than a string
						closestAppointmentTime = timeft.format(closestAppointment);//cortrectly formats the date to be a date rather than a string
				
				}
				
			}
			consultantPatientAdmission.setEnabled(false);//will initally hide the component 
			consultantPatientDemographic.setEnabled(false);//will initally hide the component 
		}
		createContactBarConsultant(consultantHomepagePanel,currentConsultant);//the method that creates the contact bar is used with the current panel is being passed through
		createMainPartConsultantHomePage(consultantHomepagePanel);//the rest of the panel is created through this, other  components are created through methods that can be utilised by other panels
		consultantHomepagePanel.setVisible(false);//the panel is hidden from sight	
		consultantHomepagePanel.setVisible(true);//the panel is then made to reappear 
		outputPatientCards();//calls the method which outputs the correct number of cards
	}
		public void createMainPartConsultantHomePage(JPanel panel)//where all the components are added to the panel
		{
			searchBoxConsultantHpagePanel.setText("Search...");//formatting component to include text
			panel.add(searchBoxConsultantHpagePanel);//the component is added to the panel
			panel.add(sortBoxConsultantHpagePanel);//the component is added to the panel
			panel.add(ConsultantCardPageForwardBttn);//the component is added to the panel
			panel.add(ConsultantCardPageBackBttn);//the component is added to the panel
			panel.add(patientCard1FirstName);//the component is added to the panel
			panel.add(patient1image);//the component is added to the panel
			panel.add(patientCard1SurName);//the component is added to the panel
			panel.add(patientCard1PatientID);//the component is added to the panel
			panel.add(moreAdmissions1lbl);//the component is added to the panel
			panel.add(patientCard1AdmissionID);//the component is added to the panel
			panel.add(viewPatient1bttn);//the component is added to the panel
			panel.add(patientCard1Diagnosis);//the component is added to the panel
			panel.add(patientCard1DONAlbl);//the component is added to the panel
			panel.add(patientCard1Date);//the component is added to the panel
			panel.add(viewPatient1AdmissionSpecificBttn);//the component is added to the panel
			panel.add(searchLibraryBttnConsulant);//the component is added to the panel
			panel.add(clearSearchbttnConsulant);//the component is added to the panel
			
			panel.add(patientCard2FirstName);//the component is added to the panel
			panel.add(patient2image);//the component is added to the panel
			panel.add(patientCard2SurName);//the component is added to the panel
			panel.add(patientCard2PatientID);//the component is added to the panel
			panel.add(moreAdmissions2lbl);//the component is added to the panel
			panel.add(patientCard2AdmissionID);//the component is added to the panel
			panel.add(viewPatient2bttn);//the component is added to the panel
			panel.add(patientCard2Diagnosis);//the component is added to the panel
			panel.add(patientCard2DONAlbl);//the component is added to the panel
			panel.add(patientCard2Date);//the component is added to the panel
			
			panel.add(patientCard3FirstName);//the component is added to the panel
			panel.add(patient3image);//the component is added to the panel
			panel.add(patientCard3SurName);//the component is added to the panel
			panel.add(patientCard3PatientID);//the component is added to the panel
			panel.add(moreAdmissions3lbl);//the component is added to the panel
			panel.add(patientCard3AdmissionID);//the component is added to the panel
			panel.add(viewPatient3bttn);//the component is added to the panel
			panel.add(patientCard3Diagnosis);//the component is added to the panel
			panel.add(patientCard3DONAlbl);//the component is added to the panel
			panel.add(patientCard3Date);//the component is added to the panel
			
			
		whiteBox4.setSize(250,325);//size of component is set
		whiteBox4.setLocation(460,215);//the location of the component is set to the desired part of the panel
		panel.add(whiteBox4);//the component is added to the panel
		
		whiteBox5.setSize(250,325);//size of component is set
		whiteBox5.setLocation(760,215);//the location of the component is set to the desired part of the panel
		panel.add(whiteBox5);//the component is added to the panel
		
		whiteBox6.setSize(250,325);//size of component is set
		whiteBox6.setLocation(1060,215);//the location of the component is set to the desired part of the panel
		panel.add(whiteBox6);//the component is added to the panel
		
		lightGreyTopPanelAdmissionSearchBar.setSize(910,35);//size of component is set
		lightGreyTopPanelAdmissionSearchBar.setLocation(425,165);//the location of the component is set to the desired part of the panel
		panel.add(lightGreyTopPanelAdmissionSearchBar);//the component is added to the panel
		panel.add(lighterGreyAdmissionPanel);//the component is added to the panel
		lighterGreyAdmissionPanel.setSize(975,445);//size of component is set
		lighterGreyAdmissionPanel.setLocation(400,150);//the location of the component is set to the desired part of the panel
		
	
	}
			public void createMainPartConsultnantHomePageGeneral()//where all the components are declared
			{
				clearSearchbttnConsulant.setText("X");//formatting component to include text
			clearSearchbttnConsulant.setEnabled(false);//the component is disabled
			clearSearchbttnConsulant.setSize(45,35);//size of component is set
			clearSearchbttnConsulant.setLocation(960,165);//the location of the component is set to the desired part of the panel
			clearSearchbttnConsulant.setBackground(darkGreyColour);//the component has its background set to a desireable colour
			clearSearchbttnConsulant.setFont(textfont);//the component has had its font set to a design with the correct size for its purpose
			clearSearchbttnConsulant.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
			clearSearchbttnConsulant.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
				
			searchLibraryBttnConsulant.setSize(130,35);//size of component is set
			searchLibraryBttnConsulant.setLocation(1205,165);//the location of the component is set to the desired part of the panel
			searchLibraryBttnConsulant.setText("Search");//formatting component to include text
			searchLibraryBttnConsulant.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
			searchLibraryBttnConsulant.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
			searchLibraryBttnConsulant.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
			searchLibraryBttnConsulant.addActionListener(this);
		
			currentPageConsultant = 1;//initalises the attribute to be the first page
			currentPageIndexConsultant = 0;//as the page is on the first page they should not be on any other number

			searchBoxConsultantHpagePanel.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
			searchBoxConsultantHpagePanel.setBackground(new Color(-1));//the component has its background set to a desireable colour
			searchBoxConsultantHpagePanel.setSize(200,35);//size of component is set
			searchBoxConsultantHpagePanel.setLocation(1005,165);//the location of the component is set to the desired part of the panel
			searchBoxConsultantHpagePanel.setOpaque(true);//the component is set to opaque
			searchBoxConsultantHpagePanel.setText("Search...");//formatting component to include text
			searchBoxConsultantHpagePanel.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
			{
				public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
				{
					if(searchBoxConsultantHpagePanel.getText().equals("Search..."))//selection checking if the text field contains the text prompt
					{
						searchBoxConsultantHpagePanel.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
					}
				}
				public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
				{
					if(searchBoxConsultantHpagePanel.getText().equals(""))//checks to see if the user has typed in nothing at all
					{
					searchBoxConsultantHpagePanel.setText("Search...");//if satisifed then the prompt text is set again
					}
				}
			});
			
			sortBoxConsultantHpagePanel.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
			sortBoxConsultantHpagePanel.setBackground(new Color(-1));//the component has its background set to a desireable colour
			sortBoxConsultantHpagePanel.setSize(150,30);//size of component is set
			sortBoxConsultantHpagePanel.setLocation(450,168);//the location of the component is set to the desired part of the panel
			sortBoxConsultantHpagePanel.setOpaque(true);//the component is set to opaque
			
			ConsultantCardPageForwardBttn.setSize(60,60);//size of component is set
			ConsultantCardPageForwardBttn.setLocation(1310,343);//the location of the component is set to the desired part of the panel
			ConsultantCardPageForwardBttn.setText(">");//formatting component to include text
			ConsultantCardPageForwardBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
			ConsultantCardPageForwardBttn.setBackground(darkGreyColour);//the component has its background set to a desireable colour
			ConsultantCardPageForwardBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
			ConsultantCardPageForwardBttn.setFont(whiteLoginFont);//the component has had its font set to a design with the correct size for its purpose
			
			ConsultantCardPageBackBttn.setSize(60,60);//size of component is set
			ConsultantCardPageBackBttn.setLocation(400,343);//the location of the component is set to the desired part of the panel
			ConsultantCardPageBackBttn.setText("<");//formatting component to include text
			ConsultantCardPageBackBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
			ConsultantCardPageBackBttn.setBackground(darkGreyColour);//the component has its background set to a desireable colour
			ConsultantCardPageBackBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
			ConsultantCardPageBackBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
			patientCard1FirstName.setSize(240,30);//size of component is set
			patientCard1FirstName.setLocation(555,225);//the location of the component is set to the desired part of the panel
			patientCard1FirstName.setFont(consultantCardFont);//the component has had its font set to a design with the correct size for its purpose
			
			patientCard1SurName.setSize(240,30);//size of component is set
			patientCard1SurName.setLocation(555,260);//the location of the component is set to the desired part of the panel
			patientCard1SurName.setFont(consultantCardFont);//the component has had its font set to a design with the correct size for its purpose
			
			patientCard1PatientID.setSize(240,30);//size of component is set
			patientCard1PatientID.setLocation(465,315);//the location of the component is set to the desired part of the panel
			patientCard1PatientID.setFont(buttonFontFormat);//the component has had its font set to a design with the correct size for its purpose
		
			patientCard1AdmissionID.setSize(240,30);//size of component is set
			patientCard1AdmissionID.setLocation(465,350);//the location of the component is set to the desired part of the panel
			patientCard1AdmissionID.setFont(consultantCardFont);//the component has had its font set to a design with the correct size for its purpose
			
			moreAdmissions1lbl.setSize(240,15);//size of component is set
			moreAdmissions1lbl.setLocation(465,380);//the location of the component is set to the desired part of the panel
			
			patientCard1Diagnosis.setSize(240,30);//size of component is set
			patientCard1Diagnosis.setLocation(465,395);//the location of the component is set to the desired part of the panel
			patientCard1Diagnosis.setFont(whiteLoginFont);//the component has had its font set to a design with the correct size for its purpose
			
			patientCard1DONAlbl.setSize(240,20);//size of component is set
			patientCard1DONAlbl.setLocation(465,425);//the location of the component is set to the desired part of the panel
			patientCard1DONAlbl.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		
			patientCard1Date.setSize(240,30);//size of component is set
			patientCard1Date.setLocation(465,450);//the location of the component is set to the desired part of the panel
			patientCard1Date.setFont(whiteLoginFont);//the component has had its font set to a design with the correct size for its purpose
			
			viewPatient1bttn.setSize(210,30);//size of component is set
			viewPatient1bttn.setLocation(480,500);//the location of the component is set to the desired part of the panel
			viewPatient1bttn.setText("View Patient");//formatting component to include text
			viewPatient1bttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
			viewPatient1bttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
			viewPatient1bttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
			viewPatient1bttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
			
			viewPatient1AdmissionSpecificBttn.setSize(210,30);//size of component is set
			viewPatient1AdmissionSpecificBttn.setLocation(480,500);//the location of the component is set to the desired part of the panel
			viewPatient1AdmissionSpecificBttn.setText("View Patient");//formatting component to include text
			viewPatient1AdmissionSpecificBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
			viewPatient1AdmissionSpecificBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
			viewPatient1AdmissionSpecificBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
			viewPatient1AdmissionSpecificBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
			
			patient1image.setSize(68,77);//size of component is set
			patient1image.setLocation(465,225);//the location of the component is set to the desired part of the panel
			patient1image.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
			patient1image.setIcon( new ImageIcon("personIconConsultantP1.png") );//the correct image is retrieved from the folder
			
			
			
			patientCard2FirstName.setSize(240,30);//size of component is set
			patientCard2FirstName.setLocation(855,225);//the location of the component is set to the desired part of the panel
			patientCard2FirstName.setFont(consultantCardFont);//the component has had its font set to a design with the correct size for its purpose
			
			patientCard2SurName.setSize(240,30);//size of component is set
			patientCard2SurName.setLocation(855,260);//the location of the component is set to the desired part of the panel
			patientCard2SurName.setFont(consultantCardFont);//the component has had its font set to a design with the correct size for its purpose
			
			patientCard2PatientID.setSize(240,30);//size of component is set
			patientCard2PatientID.setLocation(765,315);//the location of the component is set to the desired part of the panel
			patientCard2PatientID.setFont(buttonFontFormat);//the component has had its font set to a design with the correct size for its purpose
		
			patientCard2AdmissionID.setSize(240,30);//size of component is set
			patientCard2AdmissionID.setLocation(765,350);//the location of the component is set to the desired part of the panel
			patientCard2AdmissionID.setFont(consultantCardFont);//the component has had its font set to a design with the correct size for its purpose
			
			moreAdmissions2lbl.setSize(240,15);//size of component is set
			moreAdmissions2lbl.setLocation(765,380);//the location of the component is set to the desired part of the panel
			
			patientCard2Diagnosis.setSize(240,30);//size of component is set
			patientCard2Diagnosis.setLocation(765,395);//the location of the component is set to the desired part of the panel
			patientCard2Diagnosis.setFont(whiteLoginFont);//the component has had its font set to a design with the correct size for its purpose
			
			patientCard2DONAlbl.setSize(240,20);//size of component is set
			patientCard2DONAlbl.setLocation(765,425);//the location of the component is set to the desired part of the panel
			patientCard2DONAlbl.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		
			patientCard2Date.setSize(240,30);//size of component is set
			patientCard2Date.setLocation(765,450);//the location of the component is set to the desired part of the panel
			patientCard2Date.setFont(whiteLoginFont);//the component has had its font set to a design with the correct size for its purpose
			
			viewPatient2bttn.setSize(210,30);//size of component is set
			viewPatient2bttn.setLocation(780,500);//the location of the component is set to the desired part of the panel
			viewPatient2bttn.setText("View Patient");//formatting component to include text
			viewPatient2bttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
			viewPatient2bttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
			viewPatient2bttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
			viewPatient2bttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
			
			patient2image.setSize(68,77);//size of component is set
			patient2image.setLocation(765,225);//the location of the component is set to the desired part of the panel
			patient2image.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
			patient2image.setIcon( new ImageIcon("personIconConsultantP1.png") );//the correct image is retrieved from the folder
			
		
			patientCard3FirstName.setSize(240,30);//size of component is set
			patientCard3FirstName.setLocation(1155,225);//the location of the component is set to the desired part of the panel
			patientCard3FirstName.setFont(consultantCardFont);//the component has had its font set to a design with the correct size for its purpose
			
			patientCard3SurName.setSize(240,30);//size of component is set
			patientCard3SurName.setLocation(1155,260);//the location of the component is set to the desired part of the panel
			patientCard3SurName.setFont(consultantCardFont);//the component has had its font set to a design with the correct size for its purpose
			
			patientCard3PatientID.setSize(240,30);//size of component is set
			patientCard3PatientID.setLocation(1065,315);//the location of the component is set to the desired part of the panel
			patientCard3PatientID.setFont(buttonFontFormat);//the component has had its font set to a design with the correct size for its purpose
		
			patientCard3AdmissionID.setSize(240,30);//size of component is set
			patientCard3AdmissionID.setLocation(1065,350);//the location of the component is set to the desired part of the panel
			patientCard3AdmissionID.setFont(consultantCardFont);//the component has had its font set to a design with the correct size for its purpose
			
			moreAdmissions3lbl.setSize(240,15);//size of component is set
			moreAdmissions3lbl.setLocation(1065,380);//the location of the component is set to the desired part of the panel
			
			patientCard3Diagnosis.setSize(240,30);//size of component is set
			patientCard3Diagnosis.setLocation(1065,395);//the location of the component is set to the desired part of the panel
			patientCard3Diagnosis.setFont(whiteLoginFont);//the component has had its font set to a design with the correct size for its purpose
			
			patientCard3DONAlbl.setSize(240,20);//size of component is set
			patientCard3DONAlbl.setLocation(1065,425);//the location of the component is set to the desired part of the panel
			patientCard3DONAlbl.setFont(symptomfont);
		
			patientCard3Date.setSize(240,30);//size of component is set
			patientCard3Date.setLocation(1065,450);//the location of the component is set to the desired part of the panel
			patientCard3Date.setFont(whiteLoginFont);//the component has had its font set to a design with the correct size for its purpose
			
			viewPatient3bttn.setSize(210,30);//size of component is set
			viewPatient3bttn.setLocation(1080,500);//the location of the component is set to the desired part of the panel
			viewPatient3bttn.setText("View Patient");//formatting component to include text
			viewPatient3bttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
			viewPatient3bttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
			viewPatient3bttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
			viewPatient3bttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
			
			patient3image.setSize(68,77);//size of component is set
			patient3image.setLocation(1065,225);//the location of the component is set to the desired part of the panel
			patient3image.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
			patient3image.setIcon( new ImageIcon("personIconConsultantP1.png") );//the correct image is retrieved from the folder
			outputPatientCards();//calls the method which outputs the correct number of cards
		}
	public void outputPatientCards()
	{
		consultanthideCard1();//calls method which hides the cards
		consultanthideCard2();//calls method which hides the cards
		consultanthideCard3();//calls method which hides the cards
		ConsultantCardPageForwardBttn.setEnabled(false);//the component is disabled
		ConsultantCardPageBackBttn.setEnabled(false);//the component is disabled
		//System.out.println(" number of patients "+numberOfConsultantPatients);
		consultantHomepagePanel.setVisible(false);//hides component
		consultantHomepagePanel.setVisible(true);
		if(numberOfConsultantPatients>0)
		{
			ConsultantCardPageForwardBttn.setEnabled(true);//the component is enabled to interact with the user
			ConsultantCardPageBackBttn.setEnabled(true);//the component is enabled to interact with the user
			int numOfTotalPagesConsultant = (numberOfConsultantPatients / 3);//finds the total number of pages with 3 cards
			int numOfLeftOverPatients = (numberOfConsultantPatients % 3);//finds the number of leftover cards from the prior division
			if(currentPageConsultant>numOfTotalPagesConsultant)//selection determining if the final page hasnt been reached
			{
				ConsultantCardPageForwardBttn.setEnabled(false);//the component is disabled
			}
			if(currentPageConsultant==1)//selection determining if the user is on the first page
			{
				ConsultantCardPageBackBttn.setEnabled(false);//the component is disabled
			}
			if(numberOfConsultantPatients>0)//selection determining if there are more than or 1 patients for the counsutlant
			{
				
				if(currentPageConsultant<=numOfTotalPagesConsultant)//selection determining if the final page hasnt been reached
				{
					//System.out.println("3 patients");
					consultantUpdateCard1(listOfCsPatients[0+currentPageIndexConsultant]);//updates card to show the correct index and passes the correct patient through to be shown
					consultantUpdateCard2(listOfCsPatients[1+currentPageIndexConsultant]);//updates card to show the correct index and passes the correct patient through to be shown
					consultantUpdateCard3(listOfCsPatients[2+currentPageIndexConsultant]);//updates card to show the correct index and passes the correct patient through to be shown
				}
				else if(currentPageConsultant>=numOfTotalPagesConsultant)//selection determining if there are any leftover patients
				{	
					if(numOfLeftOverPatients>0)//selection determining if there are any leftover patients
					{
						if(numOfLeftOverPatients==1)//selection determining if there is only one leftover patient
						{
							consultantUpdateCard1(listOfCsPatients[0+currentPageIndexConsultant]);//updates card to show the correct index and passes the correct patient through to be shown
							consultanthideCard2();//hides the card that would be out of bounds for the array
							consultanthideCard3();//hides the card that would be out of bounds for the array
						}
						else if(numOfLeftOverPatients==2)//selection determining if there is only two leftover patients
						{
							consultantUpdateCard1(listOfCsPatients[0+currentPageIndexConsultant]);//updates card to show the correct index and passes the correct patient through to be shown
							consultantUpdateCard2(listOfCsPatients[1+currentPageIndexConsultant]);//updates card to show the correct index and passes the correct patient through to be shown
							consultanthideCard3();//hides the card that would be out of bounds for the array
							
						}
					}
				}
				
			}
		}
	}
	public void consultantUpdateCard1(Patient temp)
	{
		
		patientCard1FirstName.setText(temp.firstName);//formatting component to include text
		patientCard1SurName.setText(temp.surName);//formatting component to include text
		patientCard1PatientID.setText(temp.patientID);//formatting component to include text
		patientCard1AdmissionID.setText(listofAdmissions[0+currentPageIndexConsultant][0].admissionID);//formatting component to include text
		patientCard1Diagnosis.setText("Depression");//formatting component to include text
		patientCard1DONAlbl.setText("Date Of Next Appointment");//formatting component to include text
		patientCard1Date.setText("25/08/2019");//formatting component to include text
		moreAdmissions1lbl.setText("+ 1 other admission");//formatting component to include text
		patientCard1FirstName.setVisible(true);// the component is made visible so that it can be seen
		patientCard1SurName.setVisible(true);// the component is made visible so that it can be seen
		patientCard1PatientID.setVisible(true);// the component is made visible so that it can be seen
		patientCard1AdmissionID.setVisible(true);// the component is made visible so that it can be seen
		patientCard1Diagnosis.setVisible(true);// the component is made visible so that it can be seen
		patientCard1DONAlbl.setVisible(true);// the component is made visible so that it can be seen
		patientCard1Date.setVisible(true);// the component is made visible so that it can be seen
		moreAdmissions1lbl.setVisible(true);// the component is made visible so that it can be seen
		viewPatient1bttn.setVisible(true);// the component is made visible so that it can be seen
		patient1image.setVisible(true);// the component is made visible so that it can be seen
		whiteBox4.setVisible(true);// the component is made visible so that it can be seen
	}
	public void consultantUpdateCard2(Patient temp)
	{
		patientCard2FirstName.setText(temp.firstName);//formatting component to include text
		patientCard2SurName.setText(temp.surName);//formatting component to include text
		patientCard2PatientID.setText(temp.patientID);//formatting component to include text
		patientCard2AdmissionID.setText(listofAdmissions[1+currentPageIndexConsultant][0].admissionID);//formatting component to include text
		patientCard2Diagnosis.setText("Depression");//formatting component to include text
		patientCard2DONAlbl.setText("Date Of Next Appointment");//formatting component to include text
		patientCard2Date.setText("25/08/2019");//formatting component to include text
		moreAdmissions2lbl.setText("+ 1 other admission");//formatting component to include text
		patientCard2FirstName.setVisible(true);// the component is made visible so that it can be seen
		patientCard2SurName.setVisible(true);// the component is made visible so that it can be seen
		patientCard2PatientID.setVisible(true);// the component is made visible so that it can be seen
		patientCard2AdmissionID.setVisible(true);// the component is made visible so that it can be seen
		patientCard2Diagnosis.setVisible(true);// the component is made visible so that it can be seen
		patientCard2DONAlbl.setVisible(true);// the component is made visible so that it can be seen
		patientCard2Date.setVisible(true);// the component is made visible so that it can be seen
		moreAdmissions2lbl.setVisible(true);// the component is made visible so that it can be seen
		viewPatient2bttn.setVisible(true);// the component is made visible so that it can be seen
		patient2image.setVisible(true);// the component is made visible so that it can be seen
		whiteBox5.setVisible(true);// the component is made visible so that it can be seen
	}
	public void consultantUpdateCard3(Patient temp)
	{
		patientCard3FirstName.setText(temp.firstName);//formatting component to include text
		patientCard3SurName.setText(temp.surName);//formatting component to include text
		patientCard3PatientID.setText(temp.patientID);//formatting component to include text
		patientCard3AdmissionID.setText(listofAdmissions[2+currentPageIndexConsultant][0].admissionID);//formatting component to include text
		patientCard3Diagnosis.setText("Depression");//formatting component to include text
		patientCard3DONAlbl.setText("Date Of Next Appointment");//formatting component to include text
		patientCard3Date.setText("25/08/2019");//formatting component to include text
		moreAdmissions2lbl.setText("+ 1 other admission");//formatting component to include text
		patientCard3FirstName.setVisible(true);// the component is made visible so that it can be seen
		patientCard3SurName.setVisible(true);// the component is made visible so that it can be seen
		patientCard3PatientID.setVisible(true);// the component is made visible so that it can be seen
		patientCard3AdmissionID.setVisible(true);// the component is made visible so that it can be seen
		patientCard3Diagnosis.setVisible(true);// the component is made visible so that it can be seen
		patientCard3DONAlbl.setVisible(true);// the component is made visible so that it can be seen
		patientCard3Date.setVisible(true);// the component is made visible so that it can be seen
		moreAdmissions3lbl.setVisible(true);// the component is made visible so that it can be seen
		viewPatient3bttn.setVisible(true);// the component is made visible so that it can be seen
		patient3image.setVisible(true);// the component is made visible so that it can be seen
		whiteBox6.setVisible(true);// the component is made visible so that it can be seen
	}
	public void consultanthideCard1()
	{
		patientCard1FirstName.setVisible(false);// the component is made invisible so that it cant be seen
		patientCard1SurName.setVisible(false);// the component is made invisible so that it cant be seen
		viewPatient1AdmissionSpecificBttn.setVisible(false);// the component is made invisible so that it cant be seen
		patientCard1PatientID.setVisible(false);// the component is made invisible so that it cant be seen
		patientCard1AdmissionID.setVisible(false);// the component is made invisible so that it cant be seen
		patientCard1Diagnosis.setVisible(false);// the component is made invisible so that it cant be seen
		patientCard1DONAlbl.setVisible(false);// the component is made invisible so that it cant be seen
		patientCard1Date.setVisible(false);// the component is made invisible so that it cant be seen
		moreAdmissions1lbl.setVisible(false);// the component is made invisible so that it cant be seen
		viewPatient1bttn.setVisible(false);// the component is made invisible so that it cant be seen
		patient1image.setVisible(false);// the component is made invisible so that it cant be seen
		whiteBox4.setVisible(false);// the component is made invisible so that it cant be seen
	}
		public void consultanthideCard2()
	{
		patientCard2FirstName.setVisible(false);// the component is made invisible so that it cant be seen
		patientCard2SurName.setVisible(false);// the component is made invisible so that it cant be seen
		patientCard2PatientID.setVisible(false);// the component is made invisible so that it cant be seen
		patientCard2AdmissionID.setVisible(false);// the component is made invisible so that it cant be seen
		patientCard2Diagnosis.setVisible(false);// the component is made invisible so that it cant be seen
		patientCard2DONAlbl.setVisible(false);// the component is made invisible so that it cant be seen
		patientCard2Date.setVisible(false);// the component is made invisible so that it cant be seen
		moreAdmissions2lbl.setVisible(false);// the component is made invisible so that it cant be seen
		viewPatient2bttn.setVisible(false);// the component is made invisible so that it cant be seen
		patient2image.setVisible(false);// the component is made invisible so that it cant be seen
		whiteBox5.setVisible(false);// the component is made invisible so that it cant be seen
	}
	public void consultanthideCard3()
	{
		patientCard3FirstName.setVisible(false);// the component is made invisible so that it cant be seen
		patientCard3SurName.setVisible(false);// the component is made invisible so that it cant be seen
		patientCard3PatientID.setVisible(false);// the component is made invisible so that it cant be seen
		patientCard3AdmissionID.setVisible(false);// the component is made invisible so that it cant be seen
		patientCard3Diagnosis.setVisible(false);// the component is made invisible so that it cant be seen
		patientCard3DONAlbl.setVisible(false);// the component is made invisible so that it cant be seen
		patientCard3Date.setVisible(false);// the component is made invisible so that it cant be seen
		moreAdmissions3lbl.setVisible(false);// the component is made invisible so that it cant be seen
		viewPatient3bttn.setVisible(false);// the component is made invisible so that it cant be seen
		patient3image.setVisible(false);// the component is made invisible so that it cant be seen
		whiteBox6.setVisible(false);// the component is made invisible so that it cant be seen
	}
	public int searchAlgorithmConsultantPage(String desiredWord,Patient[] array)
		{
		int position = -1;//return value is always negative incase no indexc was found
		int length = array.length-1;//finds length of array
		int startPoint = 0;//startpoint is set to first index
		int endPoint = length;//endpoint is the last index
		boolean found = false;//declares attribute to be false as it might not be found
		int midPoint;//initalises the midpoint 
		String currentID;//initialises the id of the curent value
		do
		{	
		//System.out.println("End "+endPoint);
			midPoint = ((startPoint+endPoint)/2);//finds midpoint of array
			//System.out.println("mid "+midPoint);
			String currentDefinition = array[midPoint].patientID;//finds value at midpoint
			System.out.println(currentDefinition);
			if(desiredWord.compareToIgnoreCase(currentDefinition)==0)//selection determining if the two values are the same
			{
				System.out.println("Found");
				found = true;//updates attribute state
				position = midPoint;//sets positition of found point
			}	
			else if(desiredWord.compareToIgnoreCase(currentDefinition)<0)//selection determining ifthe desired value is before the midpoint
			{
			//	System.out.println("not above midpoint");
				endPoint = midPoint -1;//correctly updates the new start value respective to the value retruned from the comparrison
			}
			else if(desiredWord.compareToIgnoreCase(currentDefinition)>0)//selection determining ifthe desired value is before the midpoint
			{
			
			//	System.out.println("not below midpoint");
				startPoint = midPoint +1;//correctly updates the new start value respective to the value retruned from the comparrison
			}
		}
		while((found!=true)&&(endPoint>=startPoint));//temination condtion for the loop until the srat value is larger than the end value or that the item is found
		return(position);//returns the position of the idex the value is located at
		}
//consultant patient admission

	public void createConsultantadmissionHomepagePanelGUI()//
	{
		emptyArray(consultantAdmissionPanel);
		System.out.println("consultantAdmissionPanel");
		sortBoxAdmissionHpagePanel.setEnabled(true);//the component is enabled to interact with the user
			searchBoxAdmissionHpagePanel.setText("  Search:");//sets text to the component
			clearSearchbttnAdmission.setEnabled(false);//disables button
			listOfdocumentsGUI = currentAdmission.listOfDocuments;
		currentPage = 1;//initalises the attribute to be the first page
		currentPageIndex = 0;//as the page is on the first page they should not be on any other number
		
		createTopbar(consultantAdmissionPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		createContactBarAdmission(consultantAdmissionPanel);//as the correct panel is visible the components on the panel is formatte
		createTopbarAdmission(consultantAdmissionPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		setActiveTopPanelBttn(consultantPatientAdmission);//the active top bar is set to the correct button
		if(numberOfAdmissions>0)//selection determining if the patient has at least one admission
		{
			if(loaded[6][2]== false)//selection determines whether the panel is yet to be loaded
			{
				if(numberOfAdmissions>0)//selection determining if the patient has at least one admission
				{
					currentAdmission = admissionList[0];//always sets the inital admission as the earliest admission
					listOfdocumentsGUI = currentAdmission.listOfDocuments;//the current admission has its documents sent to the GUI attribute so it can be held and displayed
					numberOfDocuments =currentAdmission.numberOfDocuments;//the number of documents the patient has for this adimission is also converted over
					singleDefinition = new Document[numberOfDocuments];
					setActiveAdmissionTopPanelBttn(topBarAdmission1Bttn);//the active top bar is set to the correct button
				}
				createMainPartAdmissionHomePageGeneral();//the general components for the page are loaded 
				loaded[6][2]= true;//the variable is set as true to prevent the components from being reran
			}
			createMainPartAdmissionHomePage(consultantAdmissionPanel);//the main part of the page is now called for it to be displayed
		}
		consultantAdmissionPanel.setVisible(false);//the panel is hidden from sight
		consultantAdmissionPanel.setVisible(true);//the panel is then made to reappear 
	}

//edit admission panel

public void createEditEditAdmissionPanel()
{
		addPanel(createEditAdmissionPanel);
		createTopbar(createEditAdmissionPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		createTopbarAdmission(createEditAdmissionPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		oldAdmissionInfo = currentAdmission;//the current admission is set as the old one in case it is updated as the old info is needed
		createEditAdmissionPageMainPart(createEditAdmissionPanel);//the main part of the page is now called for it to be displayed
		if(loaded[6][4]== false)//selection determines whether the panel is yet to be loaded
			{
				createEditAdmissionPageMainPartGeneral();//the general components for the page are loaded
				loaded[6][4]=true;//the variable is set as true to prevent the components from being reran
			}
		
		whiteBox4.setVisible(true);//the component is made visible
		whiteBox5.setVisible(true);//the component is made visible
		whiteBox6.setVisible(true);//the component is made visible
		whiteBox7.setVisible(true);//the component is made visible
		whiteBox8.setVisible(true);//the component is made visible
		whiteBox9.setVisible(true);//the component is made visible
		whiteBox10.setVisible(true);//the component is made visible
		
		createEditAdmissionPanel.add(whiteBox4);// the component is added to the panel 
		createEditAdmissionPanel.add(whiteBox5);// the component is added to the panel 
		createEditAdmissionPanel.add(whiteBox6);// the component is added to the panel 
		createEditAdmissionPanel.add(whiteBox7);// the component is added to the panel 
		createEditAdmissionPanel.add(whiteBox8);// the component is added to the panel 
		createEditAdmissionPanel.add(whiteBox9);// the component is added to the panel 
		createEditAdmissionPanel.add(whiteBox10);// the component is added to the panel 
		createEditAdmissionPanel.add(titleUpperBlackLine);// the component is added to the panel 
		createEditAdmissionPanel.add(titleLowerBlackLine);// the component is added to the panel 
		createEditAdmissionPanel.add(saveChangesToAdmisionBttn);// the component is added to the panel 
		whiteBox4.setLocation(100,240);//the location of the component is set to the desired part of the panel
		whiteBox5.setLocation(100,320);//the location of the component is set to the desired part of the panel
		whiteBox6.setLocation(100,400);//the location of the component is set to the desired part of the panel
		whiteBox7.setLocation(100,480);//the location of the component is set to the desired part of the panel
		whiteBox8.setLocation(100,560);//the location of the component is set to the desired part of the panel
		whiteBox9.setLocation(100,640);//the location of the component is set to the desired part of the panel
		whiteBox10.setLocation(635,240);//the location of the component is set to the desired part of the panel
		whiteBox4.setSize(500,60);//size of component is set
		whiteBox5.setSize(500,60);//size of component is set
		whiteBox6.setSize(500,60);//size of component is set
		whiteBox7.setSize(500,60);//size of component is set
		whiteBox8.setSize(500,60);//size of component is set
		whiteBox9.setSize(500,60);//size of component is set
		whiteBox10.setSize(500,180);//size of component is set
		titleUpperBlackLine.setLocation(700,100);//the location of the component is set to the desired part of the panel
		titleLowerBlackLine.setLocation(700,175);//the location of the component is set to the desired part of the panel
		titleLowerBlackLine.setSize(723,3);//the components size is correctly declared
		titleUpperBlackLine.setSize(723,3);//the components size is correctly declared	
		createEditAdmissionPanel.setVisible(false);//the panel is hidden from sight	
		createEditAdmissionPanel.setVisible(true);//the panel is then made to reappear 
}
	public void createEditAdmissionPageMainPart(JPanel panel)
	{
		panel.add(editAdmissionTitle);//the component is added to the panel
		panel.add(backToAdmissionConBttn);//the component is added to the panel
		editAdmissionTitle.setText("Edit Admission");//formatting component to include text
		saveChangesToAdmisionBttn.setEnabled(true);//the component is enabled to interact with the user
		dischargeLblPrompt.setEnabled(true);//the component is enabled to interact with the user
		archiveAdmissionBttn.setEnabled(false);//the component is disabled
		wardAdmissionTextField.setEditable(true);//the text area is made so that the user can ammend the data in the field
		StaffsNameTextField.setEditable(true);//the text area is made so that the user can ammend the data in the field
		roomTextField.setEditable(true);//the text area is made so that the user can ammend the data in the field
		currentDiagnosisTextField.setEditable(true);//the text area is made so that the user can ammend the data in the field
		listOfCurrnetSymptomsTextPane.setEditable(true);//the text area is made so that the user can ammend the data in the field
		//System.out.print("current admission is "+currentAdmission.active);
		if(currentAdmission.active == false)//selection determining if the current admission has been discharged
		{
			editAdmissionTitle.setText("View Admission");//formatting component to include text
			saveChangesToAdmisionBttn.setEnabled(false);//the component is disabled
			dischargeLblPrompt.setEnabled(false);//the component is disabled
			archiveAdmissionBttn.setEnabled(true);//the component is enabled to interact with the user
			wardAdmissionTextField.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
			StaffsNameTextField.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
			roomTextField.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
			currentDiagnosisTextField.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
			listOfCurrnetSymptomsTextPane.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
		}
		if(loginChoice!=3)//selection determining if the user is not a consultant 
		{
			editAdmissionTitle.setText("View Admission");//formatting component to include text
			saveChangesToAdmisionBttn.setEnabled(false);//the component is disabled
			dischargeLblPrompt.setEnabled(false);//the component is disabled
			archiveAdmissionBttn.setEnabled(true);//the component is enabled to interact with the user
			wardAdmissionTextField.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
			StaffsNameTextField.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
			roomTextField.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
			currentDiagnosisTextField.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
			listOfCurrnetSymptomsTextPane.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
		}
		if(currentAdmission.active==true)//selection determining if the admission is active
		{
			dischargeLblPrompt.setSelected(false);//sets the component to be selected		
		}
		if(currentAdmission.active==false)//selection determining if the admission is not active
		{
			dischargeLblPrompt.setSelected(true);//sets the component to be selected
		}
		wardAdmissionTextField.setText(currentAdmission.ward);//formatting component to include text
		StaffsNameTextField.setText(currentAdmission.staffName);//formatting component to include text
		roomTextField.setText(currentAdmission.room);//formatting component to include text
		currentDiagnosisTextField.setText(currentAdmission.currentDiagnosis);//formatting component to include text
		listOfCurrnetSymptomsTextPane.setText(currentAdmission.listOfSymptoms[0]+"\n"+currentAdmission.listOfSymptoms[1]+"\n"+currentAdmission.listOfSymptoms[2]);//formatting component to include text
	}
		public void createEditAdmissionPageMainPartGeneral()
		{
			editAdmissionTitle.setLocation(1000,110);//the location of the component is set to the desired part of the panel
			editAdmissionTitle.setFont(headerFontFormat);//the component has had its font set to a design with the correct size for its purpose
			editAdmissionTitle.setSize(450,55);//size of component is set
			
			wardLblPrompt.setText("Ward:");//formatting component to include text
			wardLblPrompt.setLocation(110,250);//the location of the component is set to the desired part of the panel
			wardLblPrompt.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			wardLblPrompt.setSize(100,40);//size of component is set
			createEditAdmissionPanel.add(wardLblPrompt);//component is added to the panel
			
			wardAdmissionTextField.setLocation(180,250);//the location of the component is set to the desired part of the panel
			wardAdmissionTextField.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			wardAdmissionTextField.setSize(180,40);//size of component is set
			createEditAdmissionPanel.add(wardAdmissionTextField);//component is added to the panel
			
			listOfCurrnetSymptomsLblPrompt.setText("Current Symptoms:");//formatting component to include text
			listOfCurrnetSymptomsLblPrompt.setLocation(645,250);//the location of the component is set to the desired part of the panel
			listOfCurrnetSymptomsLblPrompt.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			listOfCurrnetSymptomsLblPrompt.setSize(270,40);//size of component is set
			createEditAdmissionPanel.add(listOfCurrnetSymptomsLblPrompt);//component is added to the panel
			
			listOfCurrnetSymptomsTextPane.setLocation(645,290);//the location of the component is set to the desired part of the panel
			listOfCurrnetSymptomsTextPane.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			listOfCurrnetSymptomsTextPane.setSize(480,100);//size of component is set
			listOfCurrnetSymptomsTextPane.setLineWrap(true);//adds the attribute which forces the text onto a new line
			listOfCurrnetSymptomsTextPane.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
			createEditAdmissionPanel.add(listOfCurrnetSymptomsTextPane);//component is added to the panel
			
			consultantNameLblPrompt.setText("Consultant:");//formatting component to include text
			consultantNameLblPrompt.setLocation(110,330);//the location of the component is set to the desired part of the panel
			consultantNameLblPrompt.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			consultantNameLblPrompt.setSize(150,40);//size of component is set
			createEditAdmissionPanel.add(consultantNameLblPrompt);//component is added to the panel
			
			StaffsNameTextField.setLocation(180,410);//the location of the component is set to the desired part of the panel
			StaffsNameTextField.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			StaffsNameTextField.setSize(180,40);//size of component is set
			createEditAdmissionPanel.add(StaffsNameTextField);//component is added to the panel
			
			staffNameLblPrompt.setText("Staff:");//formatting component to include text
			staffNameLblPrompt.setLocation(110,410);//the location of the component is set to the desired part of the panel
			staffNameLblPrompt.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			staffNameLblPrompt.setSize(60,40);//size of component is set
			createEditAdmissionPanel.add(staffNameLblPrompt);//component is added to the panel
			
			dischargeLblPrompt.setText("Discharge status:");//formatting component to include text
			dischargeLblPrompt.setLocation(110,490);//the location of the component is set to the desired part of the panel
			dischargeLblPrompt.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			dischargeLblPrompt.setSize(260,40);//size of component is set
			dischargeLblPrompt.setOpaque(false);//the component is made transparent
			createEditAdmissionPanel.add(dischargeLblPrompt);//component is added to the panel
			
			roomLblPrompt.setText("Room:");//formatting component to include text
			roomLblPrompt.setLocation(110,650);//the location of the component is set to the desired part of the panel
			roomLblPrompt.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			roomLblPrompt.setSize(100,40);//size of component is set
			roomLblPrompt.setOpaque(false);//the component is made transparent
			createEditAdmissionPanel.add(roomLblPrompt);//component is added to the panel
		
			roomTextField.setLocation(190,650);//the location of the component is set to the desired part of the panel
			roomTextField.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			roomTextField.setSize(180,40);//size of component is set
			createEditAdmissionPanel.add(roomTextField);//component is added to the panel
			
			staffNameTextField.setLocation(240,330);//the location of the component is set to the desired part of the panel
			staffNameTextField.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			staffNameTextField.setSize(330,40);//size of component is set
			createEditAdmissionPanel.add(staffNameTextField);//component is added to the panel
			
			saveChangesToAdmisionBttn.setLocation(700,700);//the location of the component is set to the desired part of the panel
			saveChangesToAdmisionBttn.setFont(whiteLoginFont);//the component has had its font set to a design with the correct size for its purpose
			saveChangesToAdmisionBttn.setSize(300,45);//size of component is set
			saveChangesToAdmisionBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
			saveChangesToAdmisionBttn.setText("Update Admission");//formatting component to include text
			saveChangesToAdmisionBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
			saveChangesToAdmisionBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		
			currentDiagnosisLblPrompt.setText("Current Diagnosis:");//formatting component to include text
			currentDiagnosisLblPrompt.setLocation(110,570);//the location of the component is set to the desired part of the panel
			currentDiagnosisLblPrompt.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			currentDiagnosisLblPrompt.setSize(215,40);//size of component is set
			currentDiagnosisLblPrompt.setOpaque(false);//the component is made transparent
			createEditAdmissionPanel.add(currentDiagnosisLblPrompt);//component is added to the panel
			
			currentDiagnosisTextField.setLocation(325,570);//the location of the component is set to the desired part of the panel
			currentDiagnosisTextField.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			currentDiagnosisTextField.setSize(180,40);//size of component is set
			createEditAdmissionPanel.add(currentDiagnosisTextField);//component is added to the panel
			
			backToAdmissionConBttn.setLocation(50,110);//the location of the component is set to the desired part of the panel
			backToAdmissionConBttn.setFont(whiteLoginFont);//the component has had its font set to a design with the correct size for its purpose
			backToAdmissionConBttn.setSize(240,45);//size of component is set
			backToAdmissionConBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
			backToAdmissionConBttn.setText("Return back");//formatting component to include text
			backToAdmissionConBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
			backToAdmissionConBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		}

//consultant patient demographic
	public void consultantPatientDemographicPanel()
	{
		
		emptyArray(consultantPDemographicPanel);
		System.out.println("consultantPDemographicPanel");
		setActiveTopPanelBttn(consultantPatientDemographic);//sets the active topbar to that button
		
		createTopbar(consultantPDemographicPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		
		userProfilePicfield.setSize(242,272); //the components size is correctly declared
		userProfilePicfield.setIcon( new ImageIcon("personIconLarge.png") );//the correct image is retrieved from the folder
		userProfilePicfield.setLocation(1190,55);//the location of the component is set to the desired part of the panel
		
		demographicNotioanlityPromptLbl.setSize(250,40);//size of component is set
		demographicNotioanlityPromptLbl.setLocation(495,325);//the location of the component is set to the desired part of the panel
		consultantPDemographicPanel.add(demographicNotioanlityPromptLbl);//component is added to the panel

		demographicNotioanlityTextFeild.setSize(315,35);//size of component is set
		demographicNotioanlityTextFeild.setLocation(615,332);//the location of the component is set to the desired part of the panel
		
		whiteBox7.setSize(570,55);//size of component is set
		whiteBox7.setLocation(600,235);//the location of the component is set to the desired part of the panel
		
		whiteBox9.setSize(450,55);//size of component is set
		whiteBox9.setLocation(490,325);//the location of the component is set to the desired part of the panel
		
		consultantPDemographicPanel.add(demographicBloodTypePromptLbl);//component is added to the panel
		demographicBloodTypePromptLbl.setLocation(605,235);//the location of the component is set to the desired part of the panel
		
		demographicBloodType.setSize(300,35);//size of component is set
		demographicBloodType.setLocation(735,242);//the location of the component is set to the desired part of the panel
		consultantPDemographicPanel.add(demographicBloodType);//component is added to the panel
	
		
		demographicHeaderLbl.setText(currentPatient.patientID);//formatting component to include text
		demographicHeaderLbl.setFont(buttonFontFormat);//the component has had its font set to a design with the correct size for its purpose
		demographicHeaderLbl.setSize(300,60);//size of component is set
		demographicHeaderLbl.setLocation(45,60);//the location of the component is set to the desired part of the panel
		consultantPDemographicPanel.add(demographicHeaderLbl);//component is added to the panel
		
		demographicContactinfoPromptLbl.setLocation(50,740);//the location of the component is set to the desired part of the panel
		demographicContactinfo.setLocation(290,747);//the location of the component is set to the desired part of the panel
		whiteBox16.setLocation(45,740);//the location of the component is set to the desired part of the panel
		
		demographicSmokerPromptLbl.setLocation(800,585);//the location of the component is set to the desired part of the panel
		whiteBox12.setLocation(770,580);//the location of the component is set to the desired part of the panel
		
		whiteBox13.setLocation(770,670);//the location of the component is set to the desired part of the panel
		demographicDrinkerPromptLbl.setLocation(800,675);//the location of the component is set to the desired part of the panel
		
		demographicAllergiesPromptLbl.setLocation(795,420);//the location of the component is set to the desired part of the panel
		demographicAllergies.setLocation(895,425);//the location of the component is set to the desired part of the panel
		whiteBox11.setLocation(770,415);//the location of the component is set to the desired part of the panel
		
		whiteBox14.setLocation(995,580);//the location of the component is set to the desired part of the panel
		demographicDisablitiesPromptLbl.setLocation(1029,580);//the location of the component is set to the desired part of the panel
		demographicDisablitiesTextFeild.setLocation(1029,620);//the location of the component is set to the desired part of the panel
		demographicTranslatorPromptLbl.setLocation(1029,740);//the location of the component is set to the desired part of the panel
		demographicCarerPromptLbl.setLocation(1029,700);//the location of the component is set to the desired part of the panel
		
		titleUpperBlackLine.setLocation(45,55);//the location of the component is set to the desired part of the panel
		titleUpperBlackLine.setSize(570,3);//size of component is set
		titleLowerBlackLine.setLocation(45,125);//the location of the component is set to the desired part of the panel
		titleLowerBlackLine.setSize(570,3);//size of component is set
		consultantPDemographicPanel.add(titleLowerBlackLine);//component is added to the panel
		consultantPDemographicPanel.add(titleUpperBlackLine);//component is added to the panel
		consultantPDemographicPanel.add(userProfilePicfield);//component is added to the panel
		consultantPDemographicPanel.add(demographicNotioanlityPromptLbl);//component is added to the panel
		consultantPDemographicPanel.add(demographicNotioanlityTextFeild);//component is added to the panel
		consultantPDemographicPanel.add(whiteBox7);//component is added to the panel
		consultantPDemographicPanel.add(whiteBox9);//component is added to the panel

		if(loaded[8][1]== false)//selection determines whether the panel is yet to be loaded
		{
			createdemographicHomepageGeneral(consultantPDemographicPanel,1);//as the correct panel is visible the components on the panel is formatted 
			loaded[8][1]= true;//the variable is set as true to prevent the components from being reran
			loaded[0][2]= false;//the variable is set as false to allow the components to be regenerated
			loaded[3][2]= false;//the variable is set as false to allow the components to be regenerated
		}
		whiteBox4.setSize(530,55);//size of component is set
		whiteBox4.setLocation(45,145);//the location of the component is set to the desired part of the panel
		whiteBox4.setVisible(true);//the component is made to be visible
		consultantPDemographicPanel.add(whiteBox4);//the variable is set as false to allow the components to be regenerated
		
		whiteBox5.setSize(530,55);//size of component is set
		whiteBox5.setLocation(45,235);//the location of the component is set to the desired part of the panel
		whiteBox5.setVisible(true);//the component is made to be visible
		consultantPDemographicPanel.add(whiteBox5);//the variable is set as false to allow the components to be regenerated
		
		whiteBox6.setSize(530,55);//size of component is set
		whiteBox6.setLocation(600,145);//the location of the component is set to the desired part of the panel
		whiteBox6.setVisible(true);//the component is made to be visible
		consultantPDemographicPanel.add(whiteBox6);//the variable is set as false to allow the components to be regenerated
		
		whiteBox8.setSize(420,55);//size of component is set
		whiteBox8.setLocation(45,325);//the location of the component is set to the desired part of the panel
		whiteBox8.setVisible(true);//the component is made to be visible
		consultantPDemographicPanel.add(whiteBox8);//the variable is set as false to allow the components to be regenerated
		
		whiteBox10.setSize(700,205);//size of component is set
		whiteBox10.setVisible(true);//the component is made to be visible
		whiteBox10.setLocation(45,415);//the location of the component is set to the desired part of the panel
		consultantPDemographicPanel.add(whiteBox10);//the variable is set as false to allow the components to be regenerated
		
		whiteBox15.setSize(425,55);//size of component is set
		whiteBox15.setLocation(45,650);//the location of the component is set to the desired part of the panel
		whiteBox15.setVisible(true);//the component is made to be visible
		consultantPDemographicPanel.add(whiteBox15);//the variable is set as false to allow the components to be regenerated
		
		consultantPDemographicPanel.setVisible(false);//the component is made to be invisible
		consultantPDemographicPanel.setVisible(true);//the component is made to be visible
		setDemographicAttributes();
	}
public void setDemographicAttributes()
{
	if(loginChoice==0)//selection determining if the user is the patient
	{
		demographicDrinkerPromptLbl.setEnabled(true);//the component is enabled to interact with the user
		demographicSmokerPromptLbl.setEnabled(true);//the component is enabled to interact with the user
		demographicDisablitiesPromptLbl.setEnabled(true);//the component is enabled to interact with the user
		demographicCarerPromptLbl.setEnabled(true);//the component is enabled to interact with the user
		demographicTranslatorPromptLbl.setEnabled(true);//the text area is made so that the user can ammend the data in the field
		demographicFNameTextFeild.setEditable(true);//the text area is made so that the user can ammend the data in the field
		demographicSNTextFeild.setEditable(true);//the text area is made so that the user can ammend the data in the field
		demographicDOBTextFeild.setEditable(true);//the text area is made so that the user can ammend the data in the field
		demographicBuildingNUMTextFeild.setEditable(true);//the text area is made so that the user can ammend the data in the field
		demographicStreetTextFeild.setEditable(true);//the text area is made so that the user can ammend the data in the field
		demographicTownTextFeild.setEditable(true);//the text area is made so that the user can ammend the data in the field
		demographicCountyTextFeild.setEditable(true);//the text area is made so that the user can ammend the data in the field
		demographicPostcode.setEditable(true);//the text area is made so that the user can ammend the data in the field
		demographicGender.setEnabled(true);//the component is enabled to interact with the user
		demographicContactinfo.setEditable(true);//the text area is made so that the user can ammend the data in the field
		demographicAllergies.setEditable(true);//the text area is made so that the user can ammend the data in the field
		demographicNotioanlityTextFeild.setEditable(true);//the text area is made so that the user can ammend the data in the field
		demographicBloodType.setEnabled(true);//the component is enabled to interact with the user
		demographicReligonTextFeild.setEditable(true);//the text area is made so that the user can ammend the data in the field
		demographicDisablitiesTextFeild.setEditable(true);//the text area is made so that the user can ammend the data in the field
	}
	if((loginChoice==3)||(loginChoice==1))//selection determining if the user is the managment and staff
	{
		demographicDrinkerPromptLbl.setEnabled(false);//the component is disabled
		demographicSmokerPromptLbl.setEnabled(false);//the component is disabled
		demographicDisablitiesPromptLbl.setEnabled(false);//the component is disabled
		demographicCarerPromptLbl.setEnabled(false);//the component is disabled
		demographicTranslatorPromptLbl.setEnabled(false);//the component is disabled
		demographicFNameTextFeild.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
		demographicSNTextFeild.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
		demographicDOBTextFeild.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
		demographicBuildingNUMTextFeild.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
		demographicStreetTextFeild.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
		demographicTownTextFeild.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
		demographicCountyTextFeild.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
		demographicPostcode.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
		demographicGender.setEnabled(false);//the component is disabled
		demographicContactinfo.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
		demographicAllergies.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
		demographicNotioanlityTextFeild.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
		demographicBloodType.setEnabled(false);//the component is disabled
		demographicReligonTextFeild.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
		demographicDisablitiesTextFeild.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
	}
	if(currentPatient.drinker==true)//selection determining if the user has the attribute
	{
		demographicDrinkerPromptLbl.setSelected(true);//sets the active status of the component
	}
	if(currentPatient.drinker==false)//selection determining if the user has the attribute
	{
		demographicDrinkerPromptLbl.setSelected(false);//sets the active status of the component
	}
	
	if(currentPatient.smoker==true)//selection determining if the user has the attribute
	{
		demographicSmokerPromptLbl.setSelected(true);//sets the active status of the component
	}
	if(currentPatient.smoker==false)//selection determining if the user has the attribute
	{
		demographicSmokerPromptLbl.setSelected(false);//sets the active status of the component
	}
	
	if(currentPatient.disability.equals("")==true)//selection determining if the user has the attribute
	{
		demographicDisablitiesPromptLbl.setSelected(false);//sets the active status of the component
	}
	if(currentPatient.disability.equals("")==false)//selection determining if the user has the attribute
	{
		demographicDisablitiesPromptLbl.setSelected(true);//sets the active status of the component
	}	
		
	if(currentPatient.carer==true)//selection determining if the user has the attribute
	{
		demographicCarerPromptLbl.setSelected(true);//sets the active status of the component
	}
	if(currentPatient.carer==false)//selection determining if the user has the attribute
	{
		demographicCarerPromptLbl.setSelected(false);//sets the active status of the component
	}
		
	if(currentPatient.translator==true)//selection determining if the user has the attribute
	{
		demographicTranslatorPromptLbl.setSelected(true);//sets the active status of the component
	}
	if(currentPatient.translator==false)//selection determining if the user has the attribute
	{
		demographicTranslatorPromptLbl.setSelected(false);//sets the active status of the component
	}
		demographicFNameTextFeild.setText(currentPatient.firstName);//text is added to the component
		demographicSNTextFeild.setText(currentPatient.surName);//text is added to the component
		demographicDOBTextFeild.setText(ft.format(currentPatient.dob));//text is added to the component
		demographicBuildingNUMTextFeild.setText(currentPatient.addressHouseNum+"");//text is added to the component
		demographicStreetTextFeild.setText(currentPatient.addressHouseStreet);//text is added to the component
		demographicTownTextFeild.setText(currentPatient.town);//text is added to the component
		demographicPostcode.setText(currentPatient.postcode);//text is added to the component
		System.out.println(currentPatient.gender);//text is added to the component
		demographicGender.setSelectedItem(currentPatient.gender);//text is added to the component
		demographicContactinfo.setText(currentPatient.contactNum);//text is added to the component
		demographicAllergies.setText(currentPatient.allergies);//text is added to the component
		demographicNotioanlityTextFeild.setText(currentPatient.nationality);//text is added to the component
		demographicBloodType.setSelectedItem(currentPatient.bloodType);//text is added to the component
		demographicReligonTextFeild.setText(currentPatient.religon);//text is added to the component
		demographicDisablitiesTextFeild.setText(currentPatient.disability);//text is added to the component
	
	}

//new document panel
	public void createNewDocumentPage()
	{
		addPanel(createNewDocumentPanel);
		hideOldDocument();//the old documents are hidden
		textDocumentTA.setEditable(true);
		
		createTopbar(createNewDocumentPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		createTopbarAdmission(createNewDocumentPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		resetDocument();//the documents are reset
		
		if(loaded[5][1]== false)//selection determines whether the panel is yet to be loaded
			{
				currentDocument = new Document();//a new instance of document is created
				createNewDocumentPageMainPartGeneral();//the main part of the panel
				loaded[5][1]=true;//the variable is set as true to prevent the components from being reran
			}
			
		if(loaded[5][2]== false)//selection determines whether the panel is yet to be loaded
		{
			int offset = 175;//odffset of the document is set 
			textDocumentTAND.setLocation(487+offset,260);//the location of the component is set to the desired part of the panel
			documentIDDocumentLBL.setLocation(842+offset,200);//the location of the component is set to the desired part of the panel
			timeDocumentLBL.setLocation(912+offset,160);//the location of the component is set to the desired part of the panel
			dateDocumentLBL.setLocation(882+offset,120);//the location of the component is set to the desired part of the panel
			titleDocumentLBL.setLocation(490+offset,210);//the location of the component is set to the desired part of the panel
			patientIDDocumentLBL.setLocation(490+offset,120);//the location of the component is set to the desired part of the panel
			admissionIDDocumentLBL.setLocation(490+offset,160);//the location of the component is set to the desired part of the panel
			loaded[5][2]=true;//the variable is set as true to prevent the components from being reran
			loaded[5][3]=false;//the variable is set to false so it can be reran 
		}
		
		if(loaded[10][1]== false)//selection determines whether the panel is yet to be loaded
			{
				createDocumentComponentsGeneral();//the general components are loaded 
				loaded[10][1]=true;//the variable is set as true to prevent the components from being reran
			}
			
			createNewDocumentPageMainPart(createNewDocumentPanel);//main part for the document panel
		createNewDocumentPanel.setVisible(false);//the panel is hidden from sight	
		createNewDocumentPanel.setVisible(true);//the panel is then made to reappear 
		
	}
	public void createNewDocumentPageMainPart(JPanel panel)
	{
			lighterGreyAdmissionPanel.setSize(350,900);//size of component is set
			lighterGreyAdmissionPanel.setLocation(0,60);//the location of the component is set to the desired part of the panel
			whiteBox4.setVisible(true);//sets the component to be visible
			panel.add(docTypeDischargmentBttn);//the component is added to the panel
			panel.add(docTypeTestResultsBttn);//the component is added to the panel
			panel.add(docTypeNotesBttn);//the component is added to the panel
			panel.add(docTypePrescriptionBttn);//the component is added to the panel
			panel.add(lighterGreyAdmissionPanel);//the component is added to the panel
			panel.add(returnBackToadmissionPage);//the component is added to the panel
			panel.add(createNewDocumentButton);//the component is added to the panel
			panel.add(firstLineForPrescriptionDoc);//the component is added to the panel
			panel.add(medicationDocumentTA);//the component is added to the panel
			panel.add(medicationDocumentLBL);//the component is added to the panel
			panel.add(dosageDocumentLBL);//the component is added to the panel
			panel.add(dosageDocumentTA);//the component is added to the panel
			panel.add(intakeTimeDocumentTA);//the component is added to the panel
			panel.add(intakeTimeDocumentLBL);//the component is added to the panel
			panel.add(DONDDocumentLBL);//the component is added to the panel
			panel.add(summaryOptions);//the component is added to the panel
			panel.add(summaryDocumentLBL);//the component is added to the panel
			panel.add(DONDDocumentTA);//the component is added to the panel
			panel.add(textDocumentTAND);//the component is added to the panel
			panel.add(documentIDDocumentLBL);//the component is added to the panel
			panel.add(timeDocumentLBL);//the component is added to the panel
			panel.add(dateDocumentLBL);//the component is added to the panel
			//panel.add(titleDocumentLBL);
			panel.add(admissionIDDocumentLBL);//the component is added to the panel
			panel.add(patientIDDocumentLBL);//the component is added to the panel
			createNewDocumentPanel.add(textDocumentTAND);//the component is added to the panel
			createNewDocumentPanel.add(documentIDDocumentLBL);//the component is added to the panel
			createNewDocumentPanel.add(timeDocumentLBL);//the component is added to the panel
			createNewDocumentPanel.add(dateDocumentLBL);//the component is added to the panel
			createNewDocumentPanel.add(titleDocumentLBL);//the component is added to the panel
			createNewDocumentPanel.add(admissionIDDocumentLBL);//the component is added to the panel
			createNewDocumentPanel.add(patientIDDocumentLBL);//the component is added to the panel
			createNewDocumentPanel.add(whiteBox17);//the component is added to the panel
			whiteBox17.setSize(530,750);//size of component is set
			whiteBox17.setLocation(647,100);//the location of the component is set to the desired part of the panel
	}
	public void createNotesComponents()
	{
		hideOldDocument();//hides the old documents
		textDocumentTAND.setVisible(true);//sets the component to be visible
		textDocumentTAND.setText("");//formatting component to not include text
	}
	public void createPrescriptionComponents()
	{
		hideOldDocument();//hides the old documents
		firstLineForPrescriptionDoc.setVisible(true);//sets the component to be visible
		medicationDocumentLBL.setVisible(true);//sets the component to be visible
		dosageDocumentLBL.setVisible(true);//sets the component to be visible
		intakeTimeDocumentLBL.setVisible(true);//sets the component to be visible
		DONDDocumentLBL.setVisible(true);//sets the component to be visible
		medicationDocumentTA.setVisible(true);//sets the component to be visible
		dosageDocumentTA.setVisible(true);//sets the component to be visible
		intakeTimeDocumentTA.setVisible(true);//sets the component to be visible
		DONDDocumentTA.setVisible(true);//sets the component to be visible
	}
	public void createTestResultsComponents()
	{
		hideOldDocument();//hides the old documents
		textDocumentTAND.setVisible(true);//sets the component to be visible
		textDocumentTAND.setText("");//formatting component to not include text
		summaryDocumentLBL.setVisible(true);//sets the component to be visible
		summaryOptions.setVisible(true);//sets the component to be visible
		
	}
	public void createDischargmentComponents()
	{
		hideOldDocument();//sets the component to be visible
		textDocumentTAND.setVisible(true);//sets the component to be visible
		textDocumentTAND.setText("Due to sufficient evidence provided, "+currentConsultant.consultantID+" believes that\nthe ailment of "+currentAdmission.currentDiagnosis+" affecting the patient has been\nbelieved to be resolved. As of today and the writing of this document Dr "+currentConsultant.surName+" has discharged this patient as of typing\nof this document. If a recurrence in any prior symptoms re-surge the patient is more than welcome to have the \nadmission reinstated.");//formatting component to include text
	}
	public void createDocumentComponentsGeneral()
	{
		textDocumentTAND.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		
		firstLineForPrescriptionDoc.setText("The consultant " + currentConsultant.consultantID +" has wished that the patient undercare needs to receive a prescription. Because of this the followingPrescription has been requested for the patient "+currentPatient.patientID);//formatting component to include text
		firstLineForPrescriptionDoc.setSize(500,100);//size of component is set
			firstLineForPrescriptionDoc.setLocation(662,260);//the location of the component is set to the desired part of the panel
			firstLineForPrescriptionDoc.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			
			summaryDocumentLBL.setText("Summary:");//formatting component to include text
			summaryDocumentLBL.setSize(100,30);//size of component is set
			summaryDocumentLBL.setLocation(662,720);//the location of the component is set to the desired part of the panel
			summaryDocumentLBL.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			
			summaryOptions.setSize(150,30);//size of component is set
			summaryOptions.setLocation(762,720);//the location of the component is set to the desired part of the panel
			summaryOptions.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			
			medicationDocumentLBL.setText("Medication:");//formatting component to include text
			medicationDocumentLBL.setSize(100,30);//size of component is set
			medicationDocumentLBL.setLocation(662,370);//the location of the component is set to the desired part of the panel
			medicationDocumentLBL.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			
			medicationDocumentTA.setSize(250,30);//size of component is set
			medicationDocumentTA.setLocation(762,370);//the location of the component is set to the desired part of the panel
			medicationDocumentTA.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			
			dosageDocumentLBL.setText("Dosage:");//formatting component to include text
			dosageDocumentLBL.setSize(80,30);//size of component is set
			dosageDocumentLBL.setLocation(662,420);//the location of the component is set to the desired part of the panel
			dosageDocumentLBL.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			
			dosageDocumentTA.setSize(250,30);//size of component is set
			dosageDocumentTA.setLocation(742,420);//the location of the component is set to the desired part of the panel
			dosageDocumentTA.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			
			intakeTimeDocumentLBL.setText("Intake Time:");//formatting component to include text
			intakeTimeDocumentLBL.setSize(130,30);//size of component is set
			intakeTimeDocumentLBL.setLocation(662,470);//the location of the component is set to the desired part of the panel
			intakeTimeDocumentLBL.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			
			intakeTimeDocumentTA.setSize(250,30);//size of component is set
			intakeTimeDocumentTA.setLocation(772,470);//the location of the component is set to the desired part of the panel
			intakeTimeDocumentTA.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			
			DONDDocumentLBL.setText("Date of Next Dispatch:");//formatting component to include text
			DONDDocumentLBL.setSize(200,30);//size of component is set
			DONDDocumentLBL.setLocation(662,520);//the location of the component is set to the desired part of the panel
			DONDDocumentLBL.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			
			DONDDocumentTA.setSize(250,30);//size of component is set
			DONDDocumentTA.setLocation(857,520);//the location of the component is set to the desired part of the panel
			DONDDocumentTA.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			
			patientIDDocumentLBL.setSize(250,30);//size of component is set
			patientIDDocumentLBL.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			patientIDDocumentLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
			
			admissionIDDocumentLBL.setSize(250,30);//size of component is set
			admissionIDDocumentLBL.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			admissionIDDocumentLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
			
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);//the attributes of the font are changed to have the fount contain an underlined part to it
			titleDocumentLBL.setFont(buttonFontFormatu.deriveFont(attributes));//the newly applied attributes of the font are applied to the component
			titleDocumentLBL.setSize(300,45);//size of component is set
		
			
			dateDocumentLBL.setSize(100,30);//size of component is set
			dateDocumentLBL.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			dateDocumentLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
			
			timeDocumentLBL.setSize(70,30);//size of component is set
			timeDocumentLBL.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			timeDocumentLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
			
			documentIDDocumentLBL.setSize(140,30);//size of component is set
			documentIDDocumentLBL.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			documentIDDocumentLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		
			textDocumentTAND.setEditable(true);//the text area is made so that the user can ammend the data in the field
			textDocumentTAND.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
			textDocumentTAND.setSize(500,450);//size of component is set
			textDocumentTAND.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose

	}
		public void hideOldDocument()
		{
			reinstatePatient = false;//the attribute for the reinstatment set to false
			if(oldDocType==null)//if the user has no prior doctype
			{
				textDocumentTAND.setVisible(false);//the component is set to invisible
				textDocumentTAND.setText("");//formatting component to not include text
				summaryOptions.setVisible(false);//the component is set to invisible
				summaryDocumentLBL.setVisible(false);//the component is set to invisible
				firstLineForPrescriptionDoc.setVisible(false);//the component is set to invisible
				medicationDocumentLBL.setVisible(false);//the component is set to invisible
				dosageDocumentLBL.setVisible(false);//the component is set to invisible
				intakeTimeDocumentLBL.setVisible(false);//the component is set to invisible
				DONDDocumentLBL.setVisible(false);//the component is set to invisible
				medicationDocumentTA.setVisible(false);//the component is set to invisible
				dosageDocumentTA.setVisible(false);//the component is set to invisible
				intakeTimeDocumentTA.setVisible(false);//the component is set to invisible
				DONDDocumentTA.setVisible(false);//the component is set to invisible
			}
			else if(oldDocType.equals("")==true)//selection determining if the feild is empty 
			{
				textDocumentTAND.setVisible(false);//the component is set to invisible
				textDocumentTAND.setText("");//formatting component to not include text
				summaryOptions.setVisible(false);//the component is set to invisible
				summaryDocumentLBL.setVisible(false);//the component is set to invisible
			}
			else if(oldDocType.equals("Consultant Notes")==true)//selection determining if the current document is this document
			{
				textDocumentTAND.setVisible(false);//the component is set to invisible
				textDocumentTAND.setText("");//formatting component to not include text
			}
			else if(oldDocType.equals("Dischargment")==true)//selection determining if the current document is this document
			{
				textDocumentTAND.setVisible(false);//the component is set to invisible
				textDocumentTAND.setText("");//formatting component to not include text
		
			}
			else if(oldDocType.equals("Prescription")==true)//selection determining if the current document is this document
			{
				firstLineForPrescriptionDoc.setVisible(false);//the component is set to invisible
				medicationDocumentLBL.setVisible(false);//the component is set to invisible
				dosageDocumentLBL.setVisible(false);//the component is set to invisible
				intakeTimeDocumentLBL.setVisible(false);//the component is set to invisible
				DONDDocumentLBL.setVisible(false);//the component is set to invisible
				medicationDocumentTA.setVisible(false);//the component is set to invisible
				dosageDocumentTA.setVisible(false);//the component is set to invisible
				intakeTimeDocumentTA.setVisible(false);//the component is set to invisible
				DONDDocumentTA.setVisible(false);//the component is set to invisible
			}
			else if(oldDocType.equals("Test Results")==true)//selection determining if the current document is this document
			{
				textDocumentTAND.setVisible(false);//the component is set to invisible
				textDocumentTAND.setText("");//formatting component to not include text
				summaryOptions.setVisible(false);//the component is set to invisible
				summaryDocumentLBL.setVisible(false);//the component is set to invisible
			}
			
		}
		public void resetDocument()
		{
			textDocumentTAND.setText("");//formatting component to not include text
			patientIDDocumentLBL.setText("");//formatting component to not include text
			admissionIDDocumentLBL.setText("");//formatting component to not include text
			timeDocumentLBL.setText("");//formatting component to not include text
			dateDocumentLBL.setText("");//formatting component to not include text
			documentIDDocumentLBL.setText("");//formatting component to not include text
			titleDocumentLBL.setText("");//formatting component to not include text
			
		}
		public void assignBasicAttributesToDocument()
		{
			patientIDDocumentLBL.setText(currentPatient.patientID);
			admissionIDDocumentLBL.setText(currentAdmission.admissionID);
			timeDocumentLBL.setText("Time");//formatting component to include text
			dateDocumentLBL.setText("Date");//formatting component to include text
			documentIDDocumentLBL.setText("DocumentID");//formatting component to include text
			titleDocumentLBL.setText(currentDocument.docType);//formatting component to include text
			
			
		}
			public void createNewDocumentPageMainPartGeneral()
			{
				textDocumentTA.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
				
				docTypeDischargmentBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
				docTypeDischargmentBttn.setText("Dischargment Document");//formatting component to include text
				docTypeDischargmentBttn.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
				docTypeDischargmentBttn.setBackground(new Color(-1));//the component has its background set to a desireable colour
				docTypeDischargmentBttn.setForeground(new Color(1));//the component has its font colour changed to a desireable one
				docTypeDischargmentBttn.setSize(330,60);//size of component is set
				docTypeDischargmentBttn.setLocation(0,120);//the location of the component is set to the desired part of the panel
				
				docTypeTestResultsBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
				docTypeTestResultsBttn.setText("Test results Document");//formatting component to include text
				docTypeTestResultsBttn.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
				docTypeTestResultsBttn.setBackground(new Color(-1));//the component has its background set to a desireable colour
				docTypeTestResultsBttn.setSize(330,60);//size of component is set
				docTypeTestResultsBttn.setLocation(0,180);//the location of the component is set to the desired part of the panel
				docTypeTestResultsBttn.setForeground(new Color(1));//the component has its font colour changed to a desireable one
				
				docTypeNotesBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
				docTypeNotesBttn.setText("Notes Document");//formatting component to include text
				docTypeNotesBttn.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
				docTypeNotesBttn.setBackground(new Color(-1));//the component has its background set to a desireable colour
				docTypeNotesBttn.setSize(330,60);//size of component is set
				docTypeNotesBttn.setLocation(0,240);//the location of the component is set to the desired part of the panel
				docTypeNotesBttn.setForeground(new Color(1));//the component has its font colour changed to a desireable one
				
				docTypePrescriptionBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
				docTypePrescriptionBttn.setText("Prescription Document");//formatting component to include text
				docTypePrescriptionBttn.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
				docTypePrescriptionBttn.setBackground(new Color(-1));//the component has its background set to a desireable colour
				docTypePrescriptionBttn.setSize(330,60);//size of component is set
				docTypePrescriptionBttn.setLocation(0,300);//the location of the component is set to the desired part of the panel
				docTypePrescriptionBttn.setForeground(new Color(1));//the component has its font colour changed to a desireable one
				
				returnBackToadmissionPage.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
				returnBackToadmissionPage.setText("Return back");//formatting component to include text
				returnBackToadmissionPage.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
				returnBackToadmissionPage.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
				returnBackToadmissionPage.setSize(200,45);//size of component is set
				returnBackToadmissionPage.setLocation(360,90);//the location of the component is set to the desired part of the panel
				returnBackToadmissionPage.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
				
				
				createNewDocumentButton.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
				createNewDocumentButton.setText("Create Document");//formatting component to include text
				createNewDocumentButton.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
				createNewDocumentButton.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
				createNewDocumentButton.setSize(200,45);//size of component is set
				createNewDocumentButton.setLocation(1244,805);//the location of the component is set to the desired part of the panel
				createNewDocumentButton.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
				
	}
				public void setActiveTopPanelDocumentBttn(JButton button)
				{
		docTypeTestResultsBttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		docTypeNotesBttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		docTypePrescriptionBttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		
		docTypeDischargmentBttn.setBackground(new Color(-1));//the background colour of the component is declared to the desired value 
		docTypeNotesBttn.setBackground(new Color(-1));//the background colour of the component is declared to the desired value 
		docTypeTestResultsBttn.setBackground(new Color(-1));//the background colour of the component is declared to the desired value 
		docTypePrescriptionBttn.setBackground(new Color(-1));//the background colour of the component is declared to the desired value 
		if(currentAdmission.active ==false)//selection determining if attribute is inactive
		{
			docTypeDischargmentBttn.setEnabled(false);//the component is disabled as it is currently being on that panel 
		}
		if(currentAdmission.active ==true)//selection determining if attribute is active
		{
			docTypeDischargmentBttn.setEnabled(true);//the component is disabled as it is currently being on that panel 
		}
		button.setEnabled(false);//the component is disabled as it is currently being on that panel 
		button.setBackground(selectedBttcColour);//the background colour of the component is declared to the desired value 
	}
	
	//Admin Homepage
	
	public void createAdminHomepagePanelGUI(Staff currentStaff)//
	{
		if(loaded[4][0]== false)//selection determines whether the panel is yet to be loaded
		{
			createMainPartAdminHomePageGeneral();//the components of the panel are cereated by running the method this is done initially so it is only done once and will be used throughout
			loaded[4][0]= true;//the variable is set as true to prevent the components from being reran
		}
		removeAllPanels(oldpanel);//removes the old panel by passing that panel through a method which just removes it from the frame
		oldpanel = adminHomepagePanel;//sets the old panel to be the current page when it moves onto the next panel this one is removed
		frame.add(adminHomepagePanel);//the new panel is added to the frame 
		createTopbar(adminHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		createContactBarAdmin(adminHomepagePanel, currentStaff);//the method that creates the contact bar is used with the current panel is being passed through
		createMainPartAdminHomePage(adminHomepagePanel);//the rest of the panel is created through this, other  components are created through methods that can be utilised by other panels
	}
		public void createMainPartAdminHomePage(JPanel panel)
		{

		
		
		
	}
			public void createMainPartAdminHomePageGeneral()
			{

		
		
		
	}
	
	//New patient Panel(Admin)
	
	public void createCreatePatientPagePanelGUI()//
	{
		removeAllPanels(oldpanel);//removes the old panel by passing that panel through a method which just removes it from the frame
		oldpanel = createPatientPanel;//sets the old panel to be the current page when it moves onto the next panel this one is removed
		frame.add(createPatientPanel);//the new panel is added to the frame 
		createTopbar(createPatientPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 

	}
	
	//Jargon Library Panel
	
	public void createjargonLibraryHomepagePanelGUI()//
	{
		addPanel(jargonLibraryHomepagePanel);
		createTopbar(jargonLibraryHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		setActiveTopPanelBttn(jargonLibrarybttn);//active bttn is set
		consultantPatientAdmission.setEnabled(false);//the component is disabled
		consultantPatientDemographic.setEnabled(false);//the component is disabled
		//retrieves definitions
		currentPageDefenitions = 1;//the current page is set to 1
		currentPageIndexDefenitions = 0;//current page index for the definitions is set to 0
		String[] listOfDefinitionsStringed = currentConsultant.rffReturnFullFile("JargonLibrary.txt");//list of definitions is read from file
		listOfDefinitions=new String[listOfDefinitionsStringed.length][2];
		String[] splitDefenittion;//declares the stringed array
		for(int count = 0;count<listOfDefinitionsStringed.length;count++)//runs a for loop for the number of definitions there are`
		{
			splitDefenittion = currentConsultant.unConcatenateStringAdmission(listOfDefinitionsStringed[count],"@");
			listOfDefinitions[count]=splitDefenittion;//
			//System.out.println(listOfDefinitions[count][0]);
		}
		numberOfDefenitions = listOfDefinitions.length;//finds the number of definitions
		listOfDefinitionsUpdated = sortJargonArray(0,listOfDefinitions);//updates the list according to the order
		sortBoxEmployeeAdmissionPanel.setSelectedItem("A-Z");//Set option by text
		formatJargonCards(listOfDefinitionsUpdated);//list is updated according to the order
		
		if(loaded[9][0]== false)//selection determines whether the panel is yet to be loaded
		{
			createJargonHomepageGeneral(jargonLibraryHomepagePanel);//as the correct panel is visible the components on the panel is formatted 
			loaded[9][0]= true;//the variable is set as true to prevent the components from being reran
		}
	
		jargonLibraryHomepagePanel.add(whiteBox4);//the component is added to the panel
		jargonLibraryHomepagePanel.add(whiteBox5);//the component is added to the panel
		jargonLibraryHomepagePanel.add(whiteBox6);//the component is added to the panel
		jargonLibraryHomepagePanel.add(whiteBox7);//the component is added to the panel
		jargonLibraryHomepagePanel.add(whiteBox8);//the component is added to the panel
		jargonLibraryHomepagePanel.add(whiteBox9);//the component is added to the panel
		jargonLibraryHomepagePanel.add(whiteBox10);//the component is added to the panel
		jargonLibraryHomepagePanel.add(whiteBox11);//the component is added to the panel
		jargonLibraryHomepagePanel.add(searchPrompt);//the component is added to the panel
		
		
		
		whiteBox4.setSize(1200,70);//size of component is set
		whiteBox5.setSize(1200,70);//size of component is set
		whiteBox6.setSize(1200,70);//size of component is set
		whiteBox7.setSize(1200,70);//size of component is set
		whiteBox8.setSize(1200,70);//size of component is set
		whiteBox9.setSize(1200,70);//size of component is set
		whiteBox10.setSize(1200,70);//size of component is set
		whiteBox11.setSize(1200,70);//size of component is set
		
		
		whiteBox4.setLocation(80,210);//the location of the component is set to the desired part of the panel
		whiteBox5.setLocation(80,280);//the location of the component is set to the desired part of the panel
		whiteBox6.setLocation(80,350);//the location of the component is set to the desired part of the panel
		whiteBox7.setLocation(80,420);//the location of the component is set to the desired part of the panel
		whiteBox8.setLocation(80,490);//the location of the component is set to the desired part of the panel
		whiteBox9.setLocation(80,560);//the location of the component is set to the desired part of the panel
		whiteBox10.setLocation(80,630);//the location of the component is set to the desired part of the panel
		whiteBox11.setLocation(80,700);//the location of the component is set to the desired part of the panel
		
		jargonLibraryHomepagePanel.add(lighterGreyAdmissionPanel);
		lighterGreyAdmissionPanel.setSize(1390,740);//size of component is set
		lighterGreyAdmissionPanel.setLocation(30,80);//the location of the component is set to the desired part of the panel
		
	}
		public void createJargonHomepageGeneral(JPanel panel)
		{
			
			clearSearchbttn.setText("X");//formatting component to include text
			clearSearchbttn.setEnabled(false);//the component is disabled
			clearSearchbttn.setSize(50,70);//size of component is set
			clearSearchbttn.setLocation(30,100);//the location of the component is set to the desired part of the panel
			clearSearchbttn.setBackground(darkGreyColour);//the component has its background set to a desireable colour
			clearSearchbttn.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
			clearSearchbttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
			clearSearchbttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
			jargonLibraryHomepagePanel.add(clearSearchbttn);
			
		searchLibraryBttn.setSize(150,60);//size of component is set
		searchLibraryBttn.setLocation(1090,105);//the location of the component is set to the desired part of the panel
		searchLibraryBttn.setText("Search");//formatting component to include text
		searchLibraryBttn.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		searchLibraryBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		searchLibraryBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		
		searchBarJargon.setForeground(lightGreyColor);//the component has its font colour changed to a desireable one
		searchBarJargon.setText("  Search:");//formatting component to include text
		searchBarJargon.addFocusListener(new FocusListener()//a focus listner is attached to the component on its own and will look out for any interactions that occur 
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(searchBarJargon.getText().equals("  Search:"))//it will then use selection to see if the field contains the text prompt indicating the user hasnt edited it yet
				{
					searchBarJargon.setText("");//the field is then cleared to allow the user to enter their desired text
					searchBarJargon.setForeground(new Color(1));//the component has its font colour changed to a desireable one
				}
			}
			public void focusLost(FocusEvent e)//when the focus is lost on the component the method is ran
			{
				if(searchBarJargon.getText().equals(""))//selectio occurs checking seeing when foucs has been lost the user has entered nothing 
				{
				searchBarJargon.setText("  Search:");//if this is true then the orinigal text prompt is added back 
				searchBarJargon.setForeground(lightGreyColor);//the component has its font colour changed to a desireable one
				}
			}
			
		});
		sortBoxEmployeeAdmissionPanel.setSize(100,40);//size of component is set
		sortBoxEmployeeAdmissionPanel.setLocation(1300,115);//the location of the component is set to the desired part of the panel
		sortBoxEmployeeAdmissionPanel.setEnabled(true);//the component is enabled so it can be used
		searchBarJargon.setBackground(new Color(-1));//the component has its background set to a desireable colour
		jargonLibraryHomepagePanel.add(sortBoxEmployeeAdmissionPanel);//the component is added to the panel
		
		sortBoxEmployeeAdmissionPanel.addItemListener(new ItemListener()//component receives an action listener
		{
			public void itemStateChanged(ItemEvent ie)//selection determining if the item in the combo box changed
			{
				if(sortBoxEmployeeAdmissionPanel.getSelectedItem()=="A-Z")//selection determining if sort order is this
				{
					
					listOfDefinitionsUpdated = sortJargonArray(0,listOfDefinitions);//sorts the lsit to the corrosponding order
					formatJargonCards(listOfDefinitionsUpdated);//reformats the list for the gui as the order has changed
				}
				if(sortBoxEmployeeAdmissionPanel.getSelectedItem()=="Z-A")//selection determining if sort order is this
				{
					listOfDefinitionsUpdated = sortJargonArray(1,listOfDefinitions);//sorts the lsit to the corrosponding order
					formatJargonCards(listOfDefinitionsUpdated);//reformats the list for the gui as the order has changed
					
				}
            }
        });
		
		
		searchBarJargon.setSize(1200,70);//size of component is set
		searchBarJargon.setLocation(80,100);//the location of the component is set to the desired part of the panel
		searchBarJargon.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		searchLibraryBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		jargonLibraryHomepagePanel.add(searchLibraryBttn);//the component is added to the panel
		jargonLibraryHomepagePanel.add(searchBarJargon);//the component is added to the panel
			
		upButton.setSize(50,50);//size of component is set
		downButton.setSize(50,50);//size of component is set
		
		upButton.setLocation(1330,440);//the location of the component is set to the desired part of the panel
		downButton.setLocation(1330,490);//the location of the component is set to the desired part of the panel
		
		upButton.setText("^");//formatting component to include text
		downButton.setText("v");//formatting component to include text

		upButton.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		downButton.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		
		upButton.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		downButton.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed

		word1.setSize(150,50);//size of component is set
		word2.setSize(150,50);//size of component is set
		word3.setSize(150,50);//size of component is set
		word4.setSize(150,50);//size of component is set
		word5.setSize(150,50);//size of component is set
		word6.setSize(150,50);//size of component is set
		word7.setSize(150,50);//size of component is set
		word8.setSize(150,50);//size of component is set
		
		word1.setLocation(100,220);//the location of the component is set to the desired part of the panel
		word2.setLocation(100,290);//the location of the component is set to the desired part of the panel
		word3.setLocation(100,360);//the location of the component is set to the desired part of the panel
		word4.setLocation(100,430);//the location of the component is set to the desired part of the panel
		word5.setLocation(100,500);//the location of the component is set to the desired part of the panel
		word6.setLocation(100,570);//the location of the component is set to the desired part of the panel
		word7.setLocation(100,640);//the location of the component is set to the desired part of the panel
		word8.setLocation(100,710);//the location of the component is set to the desired part of the panel
		
		word1.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		word2.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		word3.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		word4.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		word5.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		word6.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		word7.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		word8.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		
		word1Definition.setSize(700,50);//size of component is set
		word2Definition.setSize(700,50);//size of component is set
		word3Definition.setSize(700,50);//size of component is set
		word4Definition.setSize(700,50);//size of component is set
		word5Definition.setSize(700,50);//size of component is set
		word6Definition.setSize(700,50);//size of component is set
		word7Definition.setSize(700,50);//size of component is set
		word8Definition.setSize(700,50);//size of component is set
		
		
		word1Definition.setLocation(250,220);//the location of the component is set to the desired part of the panel
		word2Definition.setLocation(250,290);//the location of the component is set to the desired part of the panel
		word3Definition.setLocation(250,360);//the location of the component is set to the desired part of the panel
		word4Definition.setLocation(250,430);//the location of the component is set to the desired part of the panel
		word5Definition.setLocation(250,500);//the location of the component is set to the desired part of the panel
		word6Definition.setLocation(250,570);//the location of the component is set to the desired part of the panel
		word7Definition.setLocation(250,640);//the location of the component is set to the desired part of the panel
		word8Definition.setLocation(250,710);//the location of the component is set to the desired part of the panel
		
		jargonLibraryHomepagePanel.add(upButton);//the component is added to the panel
		jargonLibraryHomepagePanel.add(downButton);//the component is added to the panel
		
		jargonLibraryHomepagePanel.add(word1);//the component is added to the panel
		jargonLibraryHomepagePanel.add(word2);//the component is added to the panel
		jargonLibraryHomepagePanel.add(word3);//the component is added to the panel
		jargonLibraryHomepagePanel.add(word4);//the component is added to the panel
		jargonLibraryHomepagePanel.add(word5);//the component is added to the panel
		jargonLibraryHomepagePanel.add(word6);//the component is added to the panel
		jargonLibraryHomepagePanel.add(word7);//the component is added to the panel
		jargonLibraryHomepagePanel.add(word8);//the component is added to the panel
		
		jargonLibraryHomepagePanel.add(word1Definition);//the component is added to the panel
		jargonLibraryHomepagePanel.add(word2Definition);//the component is added to the panel
		jargonLibraryHomepagePanel.add(word3Definition);//the component is added to the panel
		jargonLibraryHomepagePanel.add(word4Definition);//the component is added to the panel
		jargonLibraryHomepagePanel.add(word5Definition);//the component is added to the panel
		jargonLibraryHomepagePanel.add(word6Definition);//the component is added to the panel
		jargonLibraryHomepagePanel.add(word7Definition);//the component is added to the panel
		jargonLibraryHomepagePanel.add(word8Definition);//the component is added to the panel
		}	
		public void jargonHideDef1()
		{
			whiteBox4.setVisible(false);//sets the component to be invisible
			word1.setVisible(false);//sets the component to be invisible
			word1Definition.setVisible(false);//sets the component to be invisible
		}
		public void jargonHideDef2()
		{
			whiteBox5.setVisible(false);//sets the component to be invisible
			word2.setVisible(false);//sets the component to be invisible
			word2Definition.setVisible(false);//sets the component to be invisible
		}
		public void jargonHideDef3()
		{
			whiteBox6.setVisible(false);//sets the component to be invisible
			word3.setVisible(false);//sets the component to be invisible
			word3Definition.setVisible(false);//sets the component to be invisible
		}
		public void jargonHideDef4()
		{
			whiteBox7.setVisible(false);//sets the component to be invisible
			word4.setVisible(false);//sets the component to be invisible
			word4Definition.setVisible(false);//sets the component to be invisible
		}
		public void jargonHideDef5()
		{
			whiteBox8.setVisible(false);//sets the component to be invisible
			word5.setVisible(false);//sets the component to be invisible
			word5Definition.setVisible(false);//sets the component to be invisible
		}
		public void jargonHideDef6()
		{
			whiteBox9.setVisible(false);//sets the component to be invisible
			word6.setVisible(false);//sets the component to be invisible
			word6Definition.setVisible(false);//sets the component to be invisible
		}
		public void jargonHideDef7()
		{
			whiteBox10.setVisible(false);//sets the component to be invisible
			word7.setVisible(false);//sets the component to be invisible
			word7Definition.setVisible(false);//sets the component to be invisible
		}
		public void jargonHideDef8()
		{
			whiteBox11.setVisible(false);//sets the component to be invisible
			word8.setVisible(false);//sets the component to be invisible
			word8Definition.setVisible(false);//sets the component to be invisible
		}
		public void defenitionsUpdateCard1(String[] array)
		{
			whiteBox4.setVisible(true);//sets the component to be invisible
			word1.setVisible(true);//sets the component to be invisible
			word1Definition.setVisible(true);//sets the component to be invisible
			word1.setText(array[0]);//formatting component to include text
			word1Definition.setText(array[1]);//formatting component to include text
		}
		public void defenitionsUpdateCard2(String[] array)
		{
			whiteBox5.setVisible(true);//sets the component to be invisible
			word2.setVisible(true);//sets the component to be invisible
			word2Definition.setVisible(true);//sets the component to be invisible
			word2.setText(array[0]);//formatting component to include text
			word2Definition.setText(array[1]);//formatting component to include text
		}
		public void defenitionsUpdateCard3(String[] array)
		{
			whiteBox6.setVisible(true);//sets the component to be invisible
			word3.setVisible(true);//sets the component to be invisible
			word3Definition.setVisible(true);//sets the component to be invisible
			word3.setText(array[0]);//formatting component to include text
			word3Definition.setText(array[1]);//formatting component to include text
		}
		public void defenitionsUpdateCard4(String[] array)
		{
			whiteBox7.setVisible(true);//sets the component to be visible
			word4.setVisible(true);//sets the component to be visible
			word4Definition.setVisible(true);//sets the component to be visible
			word4.setText(array[0]);//formatting component to include text
			word4Definition.setText(array[1]);//formatting component to include text
		}
		public void defenitionsUpdateCard5(String[] array)
		{
			whiteBox8.setVisible(true);//sets the component to be visible
			word5.setVisible(true);//sets the component to be visible
			word5Definition.setVisible(true);//sets the component to be visible
			word5.setText(array[0]);//formatting component to include text
			word5Definition.setText(array[1]);//formatting component to include text
		}
		public void defenitionsUpdateCard6(String[] array)
		{
			whiteBox9.setVisible(true);//sets the component to be visible
			word6.setVisible(true);//sets the component to be visible
			word6Definition.setVisible(true);//sets the component to be visible
			word6.setText(array[0]);//formatting component to include text
			word6Definition.setText(array[1]);//formatting component to include text
		}
		public void defenitionsUpdateCard7(String[] array)
		{
			whiteBox10.setVisible(true);//sets the component to be visible
			word7.setVisible(true);//sets the component to be visible
			word7Definition.setVisible(true);//sets the component to be visible
			word7.setText(array[0]);//formatting component to include text
			word7Definition.setText(array[1]);//formatting component to include text
		}
		public void defenitionsUpdateCard8(String[] array)
		{
			whiteBox11.setVisible(true);//sets the component to be visible
			word8.setVisible(true);//sets the component to be visible
			word8Definition.setVisible(true);//sets the component to be visible
			word8.setText(array[0]);//formatting component to include text
			word8Definition.setText(array[1]);//formatting component to include text
		}
		public void formatJargonCards(String[][] listOfDefenitions)
		{
			jargonHideDef1();//calls the method which hides the card
			jargonHideDef2();//calls the method which hides the card
			jargonHideDef3();//calls the method which hides the card
			jargonHideDef4();//calls the method which hides the card
			jargonHideDef5();//calls the method which hides the card
			jargonHideDef6();//calls the method which hides the card
			jargonHideDef7();//calls the method which hides the card
			jargonHideDef8();//calls the method which hides the card
			upButton.setEnabled(false);//the component is disabled
			downButton.setEnabled(false);//the component is disabled
			jargonLibraryHomepagePanel.setVisible(false);//sets the component to be invisible
			jargonLibraryHomepagePanel.setVisible(true);//sets the component to be visible
			if(numberOfDefenitions>0)//selection determining if the user has at least 1 definition
			{
				downButton.setEnabled(true);//the component is enabled to interact with the user
				upButton.setEnabled(true);//the component is enabled to interact with the user
				int numOfTotalPagesDefinitions = (numberOfDefenitions / 8);
				int numOfLeftOverDefinitions = (numberOfDefenitions % 8);
				if(currentPageDefenitions>numOfTotalPagesDefinitions)//selection determining if there are less than 8 definitions
				{
					downButton.setEnabled(false);//the component is disabled
				}
				if(currentPageDefenitions==1)//selection determining if there is one definition
				{
					upButton.setEnabled(false);//the component is disabled
				}
				if(numberOfDefenitions>0)//selection determining if there is at least one definition 
				{
					
					if(currentPageDefenitions<=numOfTotalPagesDefinitions)//selection determining if there is at least one definition
					{
						defenitionsUpdateCard1(listOfDefenitions[0+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
						defenitionsUpdateCard2(listOfDefenitions[1+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
						defenitionsUpdateCard3(listOfDefenitions[2+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
						defenitionsUpdateCard4(listOfDefenitions[3+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
						defenitionsUpdateCard5(listOfDefenitions[4+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
						defenitionsUpdateCard6(listOfDefenitions[5+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
						defenitionsUpdateCard7(listOfDefenitions[6+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
						defenitionsUpdateCard8(listOfDefenitions[7+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
					}
					else if(currentPageDefenitions>=numOfTotalPagesDefinitions)//selection determining if there is at least one definition
					{	
						if(numOfLeftOverDefinitions>0)//selection determining if there are this specific amount of leftover definitions
						{
							if(numOfLeftOverDefinitions==1)//selection determining if there are this specific amount of leftover definitions
							{
								defenitionsUpdateCard1(listOfDefenitions[0+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								jargonHideDef2();//calls the method which hides the card
								jargonHideDef3();//calls the method which hides the card
								jargonHideDef4();//calls the method which hides the card
								jargonHideDef5();//calls the method which hides the card
								jargonHideDef6();//calls the method which hides the card
								jargonHideDef7();//calls the method which hides the card
								jargonHideDef8();//calls the method which hides the card
							}
							else if(numOfLeftOverDefinitions==2)//selection determining if there are this specific amount of leftover definitions
							{
								defenitionsUpdateCard1(listOfDefenitions[0+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard2(listOfDefenitions[1+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								jargonHideDef3();//calls the method which hides the card
								jargonHideDef4();//calls the method which hides the card
								jargonHideDef5();//calls the method which hides the card
								jargonHideDef6();//calls the method which hides the card
								jargonHideDef7();//calls the method which hides the card
								jargonHideDef8();//calls the method which hides the card
							}
							else if(numOfLeftOverDefinitions==3)//selection determining if there are this specific amount of leftover definitions
							{
								defenitionsUpdateCard1(listOfDefenitions[0+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard2(listOfDefenitions[1+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard3(listOfDefenitions[2+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								jargonHideDef4();//calls the method which hides the card
								jargonHideDef5();//calls the method which hides the card
								jargonHideDef6();//calls the method which hides the card
								jargonHideDef7();//calls the method which hides the card
								jargonHideDef8();//calls the method which hides the card
							}
							else if(numOfLeftOverDefinitions==4)//selection determining if there are this specific amount of leftover definitions
							{
								defenitionsUpdateCard1(listOfDefenitions[0+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard2(listOfDefenitions[1+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard3(listOfDefenitions[2+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard4(listOfDefenitions[3+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								jargonHideDef5();//calls the method which hides the card
								jargonHideDef6();//calls the method which hides the card
								jargonHideDef7();//calls the method which hides the card
								jargonHideDef8();//calls the method which hides the card
							}
							else if(numOfLeftOverDefinitions==5)//selection determining if there are this specific amount of leftover definitions
							{
								defenitionsUpdateCard1(listOfDefenitions[0+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard2(listOfDefenitions[1+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard3(listOfDefenitions[2+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard4(listOfDefenitions[3+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard5(listOfDefenitions[4+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								jargonHideDef6();//calls the method which hides the card
								jargonHideDef7();//calls the method which hides the card
								jargonHideDef8();//calls the method which hides the card
							}
							
							else if(numOfLeftOverDefinitions==6)//selection determining if there are this specific amount of leftover definitions
							{
								defenitionsUpdateCard1(listOfDefenitions[0+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard2(listOfDefenitions[1+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard3(listOfDefenitions[2+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard4(listOfDefenitions[3+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard5(listOfDefenitions[4+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard6(listOfDefenitions[5+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								jargonHideDef7();//calls the method which hides the card
								jargonHideDef8();//calls the method which hides the card
							}
							else if(numOfLeftOverDefinitions==7)//selection determining if there are this specific amount of leftover definitions
							{
								defenitionsUpdateCard1(listOfDefenitions[0+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard2(listOfDefenitions[1+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard3(listOfDefenitions[2+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard4(listOfDefenitions[3+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard5(listOfDefenitions[4+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard6(listOfDefenitions[5+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								defenitionsUpdateCard7(listOfDefenitions[6+currentPageIndexDefenitions]);//calls the method that updates the defnition with the new definition 
								jargonHideDef8();//calls the method which hides the card
							}
						}
					}
					
				}
			}
		}
		public void formatJargonCards(String[] listOfDefenitions)
		{
			defenitionsUpdateCard1(listOfDefenitions);//calls method whuch updates the list of definitions 
			jargonHideDef2();//calls the method which hides the card
			jargonHideDef3();//calls the method which hides the card
			jargonHideDef4();//calls the method which hides the card
			jargonHideDef5();//calls the method which hides the card
			jargonHideDef6();//calls the method which hides the card
			jargonHideDef7();//calls the method which hides the card
			jargonHideDef8();//calls the method which hides the card
		}
		public int searchAlgorithm(String desiredWord,String[][] array)
		{
		int position = -1;//return value is always negative incase no indexc was found
		int length = array.length;//finds length of array
		int startPoint = 0;//startpoint is set to first index
		int endPoint = length;//endpoint is the last index
		boolean found = false;//declares attribute to be false as it might not be found
		int midPoint;//initalises the midpoint 
		String currentID;//initialises the id of the curent value
		do
		{	
		//System.out.println("Start "+startPoint);
		//System.out.println("End "+endPoint);
			midPoint = ((startPoint+endPoint)/2);//finds midpoint of array
			//System.out.println("mid "+midPoint);
			String currentDefinition = array[midPoint][0];//finds value at midpoint
			if(desiredWord.compareToIgnoreCase(currentDefinition)==0)//selection determining if the two values are the same
			{
			//	System.out.println("Found");
				found = true;//updates attribute state
				position = midPoint;//sets positition of found point
			}	
			else if(desiredWord.compareToIgnoreCase(currentDefinition)<0)//selection determining ifthe desired value is before the midpoint
			{
			//	System.out.println("not above midpoint");
				endPoint = midPoint -1;//correctly updates the new start value respective to the value retruned from the comparrison
			}
			else if(desiredWord.compareToIgnoreCase(currentDefinition)>0)//selection determining ifthe desired value is before the midpoint
			{
			
			//	System.out.println("not below midpoint");
				startPoint = midPoint +1;//correctly updates the new start value respective to the value retruned from the comparrison
			}
		}
		while((found!=true)&&(endPoint>=startPoint));//temination condtion for the loop until the srat value is larger than the end value or that the item is found
		return(position);//returns the position of the idex the value is located at
			
		}
		public String[][] sortJargonArray(int order, String[][] array)
		{
			String[] newOrderedArray;//declares the array the new values will be stored in
			int sizeOfArray = array.length;//finds length of array
			if(order ==0)//selection determining if they want it in ascending order
			{
				for (int i = 0; i < sizeOfArray; i++) //for loop that will iterate through every value
				{
					for (int j = i + 1; j < sizeOfArray; j++) //for loop that will iterate through every value
					{
						if(array[i][0].compareTo(array[j][0])>0) //selection determining if the value goes after the current index
						{
							newOrderedArray = array[i];//temp value to store array
							array[i] = array[j];//swap occurs
							array[j] = newOrderedArray;//temp value is then inserted back into correct spot
						}
					}
				}
			}
			else if(order ==1)//selection determining if they want it in descending order
			{
				for (int i = 0; i < sizeOfArray; i++)  //for loop that will iterate through every value
				{
					for (int j = i + 1; j < sizeOfArray; j++)  //for loop that will iterate through every value
					{
						if(array[i][0].compareTo(array[j][0])<0) //selection determining if the value goes before the current index
						{
							newOrderedArray = array[i];//temp value to store array
							array[i] = array[j];//swap occurs
							array[j] = newOrderedArray;//temp value is then inserted back into correct spot
						}
					}
				}
			}
			return(array);//new list is returned
		}
			
		
	//Patient demographic homepage
	
	public void createdemographicHomepagePanelGUI()//
	{
		emptyArray(demographicHomepagePanel);
		System.out.println("demographicHomepagePanel");
		createTopbar(demographicHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		
		userProfilePicfield.setSize(242,272); //the components size is correctly declared
		userProfilePicfield.setIcon( new ImageIcon("personIconLarge.png") );//the correct image is retrieved from the folder
		userProfilePicfield.setLocation(1190,55);//the location of the component is set to the desired part of the panel
		
		demographicNotioanlityPromptLbl.setSize(250,40);//size of component is set
		demographicNotioanlityPromptLbl.setLocation(495,325);//the location of the component is set to the desired part of the panel
		demographicHomepagePanel.add(demographicNotioanlityPromptLbl);//the component is added to the panel

		demographicNotioanlityTextFeild.setSize(315,35);//size of component is set
		demographicNotioanlityTextFeild.setLocation(615,332);//the location of the component is set to the desired part of the panel
		
		whiteBox7.setSize(570,55);//size of component is set
		whiteBox7.setLocation(600,235);//the location of the component is set to the desired part of the panel
		
		whiteBox9.setSize(450,55);//size of component is set
		whiteBox9.setLocation(490,325);//the location of the component is set to the desired part of the panel
		
		demographicHomepagePanel.add(demographicBloodTypePromptLbl);//the component is added to the panel
		demographicBloodTypePromptLbl.setLocation(605,235);//the location of the component is set to the desired part of the panel
		
		demographicBloodType.setSize(300,35);//size of component is set
		demographicBloodType.setLocation(735,242);//the location of the component is set to the desired part of the panel
		demographicHomepagePanel.add(demographicBloodType);//the component is added to the panel
		
		deleteAccountBttn.setLocation(45,810);//the location of the component is set to the desired part of the panel
		demographicHomepagePanel.add(deleteAccountBttn);//the component is added to the panel
		
		saveDemographicChanges.setLocation(1230,810);//the location of the component is set to the desired part of the panel
		demographicHomepagePanel.add(saveDemographicChanges);//the component is added to the panel
	
		demographicChangeProfilePic.setLocation(1236,335);//the location of the component is set to the desired part of the panel
		demographicHomepagePanel.add(demographicChangeProfilePic);//the component is added to the panel
		
		demographicHeaderLbl.setText(currentPatient.patientID);//text is added to the component 
		demographicHeaderLbl.setFont(buttonFontFormat);//the component has had its font set to a design with the correct size for its purpose
		demographicHeaderLbl.setSize(300,60);//size of component is set
		demographicHeaderLbl.setLocation(45,60);//the location of the component is set to the desired part of the panel
		demographicHomepagePanel.add(demographicHeaderLbl);//the component is added to the panel
		
		demographicContactinfoPromptLbl.setLocation(50,740);//the location of the component is set to the desired part of the panel
		demographicContactinfo.setLocation(290,747);//the location of the component is set to the desired part of the panel
		whiteBox16.setLocation(45,740);//the location of the component is set to the desired part of the panel
		
		demographicSmokerPromptLbl.setLocation(800,585);//the location of the component is set to the desired part of the panel
		whiteBox12.setLocation(770,580);//the location of the component is set to the desired part of the panel
		
		whiteBox13.setLocation(770,670);//the location of the component is set to the desired part of the panel
		demographicDrinkerPromptLbl.setLocation(800,675);//the location of the component is set to the desired part of the panel
		
		demographicAllergiesPromptLbl.setLocation(795,420);//the location of the component is set to the desired part of the panel
		demographicAllergies.setLocation(895,425);//the location of the component is set to the desired part of the panel
		whiteBox11.setLocation(770,415);//the location of the component is set to the desired part of the panel
		
		whiteBox14.setLocation(995,580);//the location of the component is set to the desired part of the panel
		demographicDisablitiesPromptLbl.setLocation(1029,580);//the location of the component is set to the desired part of the panel
		demographicDisablitiesTextFeild.setLocation(1029,620);//the location of the component is set to the desired part of the panel
		demographicTranslatorPromptLbl.setLocation(1029,740);//the location of the component is set to the desired part of the panel
		demographicCarerPromptLbl.setLocation(1029,700);//the location of the component is set to the desired part of the panel
		
		titleUpperBlackLine.setLocation(45,55);//the location of the component is set to the desired part of the panel
		titleUpperBlackLine.setSize(570,3);//size of component is set
		titleLowerBlackLine.setLocation(45,125);//the location of the component is set to the desired part of the panel
		titleLowerBlackLine.setSize(570,3);//size of component is set
		demographicHomepagePanel.add(titleLowerBlackLine);//the component is added to the panel
		demographicHomepagePanel.add(titleUpperBlackLine);//the component is added to the panel
		demographicHomepagePanel.add(userProfilePicfield);//the component is added to the panel
		demographicHomepagePanel.add(demographicNotioanlityPromptLbl);//the component is added to the panel
		demographicHomepagePanel.add(demographicNotioanlityTextFeild);	//the component is added to the panel
		demographicHomepagePanel.add(whiteBox7);//the component is added to the panel
		demographicHomepagePanel.add(whiteBox9);//the component is added to the panel
		
		if(loaded[3][2]== false)//selection determines whether the panel is yet to be loaded
		{
			createdemographicHomepageGeneral(demographicHomepagePanel,1);//as the correct panel is visible the components on the panel is formatted 
			loaded[3][2]= true;//the variable is set as true to prevent the components from being reran
			loaded[0][2]= false;//the variable is set as false to allow the components to be reran
			loaded[8][1]= false;//the variable is set as false to allow the components to be reran
		}
		whiteBox4.setSize(530,55);//size of component is set
		whiteBox4.setLocation(45,145);//the location of the component is set to the desired part of the panel
		whiteBox4.setVisible(true);//component is made visible
		demographicHomepagePanel.add(whiteBox4);//the component is added to the panel
		
		whiteBox5.setSize(530,55);//size of component is set
		whiteBox5.setLocation(45,235);//the location of the component is set to the desired part of the panel
		whiteBox5.setVisible(true);//component is made visible
		demographicHomepagePanel.add(whiteBox5);//the component is added to the panel
		
		whiteBox6.setSize(530,55);//size of component is set
		whiteBox6.setLocation(600,145);//the location of the component is set to the desired part of the panel
		whiteBox6.setVisible(true);//component is made visible
		demographicHomepagePanel.add(whiteBox6);//the component is added to the panel
		
		whiteBox8.setSize(420,55);//size of component is set
		whiteBox8.setLocation(45,325);//the location of the component is set to the desired part of the panel
		whiteBox8.setVisible(true);//component is made visible
		demographicHomepagePanel.add(whiteBox8);//the component is added to the panel
		
		whiteBox10.setSize(700,205);//size of component is set
		whiteBox10.setVisible(true);//component is made visible
		whiteBox10.setLocation(45,415);//the location of the component is set to the desired part of the panel
		demographicHomepagePanel.add(whiteBox10);//the component is added to the panel
		
		whiteBox15.setSize(425,55);//size of component is set
		whiteBox15.setLocation(45,650);//the location of the component is set to the desired part of the panel
		whiteBox15.setVisible(true);//component is made visible
		demographicHomepagePanel.add(whiteBox15);	//the component is added to the panel
		setDemographicAttributes();//the attributes the patient has is set to the demograpgic
	}
		public void createdemographicHomepageGeneral(JPanel panel,int type)
		{
			
		demographicBloodTypePromptLbl.setText("Blood type:");//formatting component to include text
		demographicBloodTypePromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicBloodTypePromptLbl.setSize(250,40);//size of component is set
		demographicBloodTypePromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		
		deleteAccountBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		deleteAccountBttn.setText("Delete Account");//formatting component to include text
		deleteAccountBttn.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		deleteAccountBttn.setBackground(importantRedColor);//the component has its background set to a desireable colour
		deleteAccountBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		deleteAccountBttn.setSize(200,55);//size of component is set
		deleteAccountBttn.setEnabled(false);
		
		saveDemographicChanges.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		saveDemographicChanges.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		saveDemographicChanges.setSize(200,55);//size of component is set
		saveDemographicChanges.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		saveDemographicChanges.setText("Save Changes");//formatting component to include text
		saveDemographicChanges.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
			
		demographicChangeProfilePic.setText("Change photo");//formatting component to include text
		demographicChangeProfilePic.setSize(150,30);//size of component is set
		demographicChangeProfilePic.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		demographicChangeProfilePic.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		demographicChangeProfilePic.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
			
		demographicFNamePromptLbl.setText("Firstname:");//formatting component to include text
		demographicFNamePromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicFNamePromptLbl.setSize(150,40);//size of component is set
		demographicFNamePromptLbl.setLocation(50,145);//the location of the component is set to the desired part of the panel
		demographicFNamePromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		panel.add(demographicFNamePromptLbl);//the component is added to the panel
		
		demographicFNameTextFeild.setText("Please enter your first name");//formatting component to include text
		demographicFNameTextFeild.setSize(350,40);//size of component is set
		demographicFNameTextFeild.setLocation(170,152);//the location of the component is set to the desired part of the panel
		demographicFNameTextFeild.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(demographicFNameTextFeild.getText().equals("Please enter your first name"))//selection checking if the text field contains the text prompt
				{
					demographicFNameTextFeild.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(demographicFNameTextFeild.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					demographicFNameTextFeild.setText("Please enter your first name");//if satisifed then the prompt text is set again
				}
			}
		});
		panel.add(demographicFNameTextFeild);//the component is added to the panel
		
		demographicSNPromptLbl.setText("Surname:");//formatting component to include text
		demographicSNPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicSNPromptLbl.setSize(150,40);//size of component is set
		demographicSNPromptLbl.setLocation(50,235);//the location of the component is set to the desired part of the panel
		demographicSNPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		panel.add(demographicSNPromptLbl);//the component is added to the panel
	
		demographicSNTextFeild.setText("Please enter your surname");//formatting component to include text
		demographicSNTextFeild.setSize(350,40);//size of component is set
		demographicSNTextFeild.setLocation(165,242);//the location of the component is set to the desired part of the panel
		demographicSNTextFeild.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(demographicSNTextFeild.getText().equals("Please enter your surname"))//selection checking if the text field contains the text prompt
				{
					demographicSNTextFeild.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(demographicSNTextFeild.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					demographicSNTextFeild.setText("Please enter your surname");//if satisifed then the prompt text is set again
				}
			}
		});
		panel.add(demographicSNTextFeild);//the component is added to the panel
		
		demographicDOBPromptLbl.setText("Date Of Birth:");//formatting component to include text
		demographicDOBPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicDOBPromptLbl.setSize(150,40);//size of component is set
		demographicDOBPromptLbl.setLocation(50,325);//the location of the component is set to the desired part of the panel
		demographicDOBPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		panel.add(demographicDOBPromptLbl);//the component is added to the panel
		
		demographicDOBTextFeild.setText("Please enter your Date Of Birth");//formatting component to include text
		demographicDOBTextFeild.setSize(200,40);//size of component is set
		demographicDOBTextFeild.setLocation(215,332);//the location of the component is set to the desired part of the panel
		demographicDOBTextFeild.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(demographicDOBTextFeild.getText().equals("Please enter your Date Of Birth"))//selection checking if the text field contains the text prompt
				{
					demographicDOBTextFeild.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(demographicDOBTextFeild.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					demographicDOBTextFeild.setText("Please enter your Date Of Birth");//if satisifed then the prompt text is set again
				}
			}
		});
		panel.add(demographicDOBTextFeild);//the component is added to the panel
		
		demographicBuildingNUMPromptLbl.setText("Building number/name:");//formatting component to include text
		demographicBuildingNUMPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicBuildingNUMPromptLbl.setSize(250,40);//size of component is set
		demographicBuildingNUMPromptLbl.setLocation(50,415);//the location of the component is set to the desired part of the panel
		demographicBuildingNUMPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		panel.add(demographicBuildingNUMPromptLbl);//the component is added to the panel
		
		demographicBuildingNUMTextFeild.setText("Please enter your building number");//formatting component to include text
		demographicBuildingNUMTextFeild.setSize(350,35);//size of component is set
		demographicBuildingNUMTextFeild.setLocation(300,422);//the location of the component is set to the desired part of the panel
		demographicBuildingNUMTextFeild.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(demographicBuildingNUMTextFeild.getText().equals("Please enter your building number"))//selection checking if the text field contains the text prompt
				{
					demographicBuildingNUMTextFeild.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(demographicBuildingNUMTextFeild.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					demographicBuildingNUMTextFeild.setText("Please enter your building number");//if satisifed then the prompt text is set again
				}
			}
		});
		panel.add(demographicBuildingNUMTextFeild);//the component is added to the panel
		
		demographicStreetPromptLbl.setText("Street:");//formatting component to include text
		demographicStreetPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicStreetPromptLbl.setSize(250,40);//size of component is set
		demographicStreetPromptLbl.setLocation(50,455);//the location of the component is set to the desired part of the panel
		demographicStreetPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		panel.add(demographicStreetPromptLbl);//the component is added to the panel
		
		demographicStreetTextFeild.setText("Please enter your street");//formatting component to include text
		demographicStreetTextFeild.setSize(350,35);//size of component is set
		demographicStreetTextFeild.setLocation(130,462);//the location of the component is set to the desired part of the panel
		demographicStreetTextFeild.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(demographicStreetTextFeild.getText().equals("Please enter your street"))//selection checking if the text field contains the text prompt
				{
					demographicStreetTextFeild.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(demographicStreetTextFeild.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					demographicStreetTextFeild.setText("Please enter your street");//if satisifed then the prompt text is set again
				}
			}
		});
		panel.add(demographicStreetTextFeild);//the component is added to the panel
		
		demographicTownPromptLbl.setText("Town/City:");//formatting component to include text
		demographicTownPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicTownPromptLbl.setSize(250,40);//size of component is set
		demographicTownPromptLbl.setLocation(50,495);//the location of the component is set to the desired part of the panel
		demographicTownPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		panel.add(demographicTownPromptLbl);//the component is added to the panel
		
		demographicTownTextFeild.setText("Please enter your town/city");//formatting component to include text
		demographicTownTextFeild.setSize(350,35);//size of component is set
		demographicTownTextFeild.setLocation(168,502);//the location of the component is set to the desired part of the panel
		demographicTownTextFeild.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(demographicTownTextFeild.getText().equals("Please enter your town/city"))//selection checking if the text field contains the text prompt
				{
					demographicTownTextFeild.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(demographicTownTextFeild.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					demographicTownTextFeild.setText("Please enter your town/city");//if satisifed then the prompt text is set again
				}
			}
		});
		panel.add(demographicTownTextFeild);//the component is added to the panel
		
		demographicCountyPromptLbl.setText("County:");//formatting component to include text
		demographicCountyPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicCountyPromptLbl.setSize(250,40);//size of component is set
		demographicCountyPromptLbl.setLocation(50,535);//the location of the component is set to the desired part of the panel
		demographicCountyPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		panel.add(demographicCountyPromptLbl);//the component is added to the panel
		
		demographicCountyTextFeild.setText("Please enter your county");//formatting component to include text
		demographicCountyTextFeild.setSize(350,35);//size of component is set
		demographicCountyTextFeild.setLocation(145,542);//the location of the component is set to the desired part of the panel
		demographicCountyTextFeild.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(demographicCountyTextFeild.getText().equals("Please enter your county"))//selection checking if the text field contains the text prompt
				{
					demographicCountyTextFeild.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(demographicCountyTextFeild.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					demographicCountyTextFeild.setText("Please enter your county");//if satisifed then the prompt text is set again
				}
			}
		});
		panel.add(demographicCountyTextFeild);//the component is added to the panel
		
		demographicPostcodePromptLbl.setText("Postcode:");//formatting component to include text
		demographicPostcodePromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicPostcodePromptLbl.setSize(250,40);//size of component is set
		demographicPostcodePromptLbl.setLocation(50,575);//the location of the component is set to the desired part of the panel
		demographicPostcodePromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		panel.add(demographicPostcodePromptLbl);//the component is added to the panel
		
		demographicPostcode.setText("Please enter your postcode");//formatting component to include text
		demographicPostcode.setSize(350,33);//size of component is set
		demographicPostcode.setLocation(160,580);//the location of the component is set to the desired part of the panel
		demographicPostcode.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(demographicPostcode.getText().equals("Please enter your postcode"))//selection checking if the text field contains the text prompt
				{
					demographicPostcode.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(demographicPostcode.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					demographicPostcode.setText("Please enter your postcode");//if satisifed then the prompt text is set again
				}
			}
		});
		panel.add(demographicPostcode);//the component is added to the panel
		
		demographicGenderPromptLbl.setText("Gender:");//formatting component to include text
		demographicGenderPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicGenderPromptLbl.setSize(250,40);//size of component is set
		demographicGenderPromptLbl.setLocation(50,650);//the location of the component is set to the desired part of the panel
		demographicGenderPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		panel.add(demographicGenderPromptLbl);//the component is added to the panel
		
		demographicGender.setSize(300,35);//size of component is set
		demographicGender.setLocation(145,657);//the location of the component is set to the desired part of the panel
		panel.add(demographicGender);//the component is added to the panel
		
		demographicContactinfoPromptLbl.setText("Contact Information:");//formatting component to include text
		demographicContactinfoPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicContactinfoPromptLbl.setSize(250,40);//size of component is set
		demographicContactinfoPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		panel.add(demographicContactinfoPromptLbl);//the component is added to the panel
		
		demographicContactinfo.setText("Please enter your contact information");//formatting component to include text
		demographicContactinfo.setSize(450,33);//size of component is set
		
		demographicContactinfo.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e){
			//when focus has been gained(text field has been clicked on) this method will run
			
				if(demographicContactinfo.getText().equals("Please enter your contact information"))//selection checking if the text field contains the text prompt
				{
					demographicContactinfo.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(demographicContactinfo.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					demographicContactinfo.setText("Please enter your contact information");//if satisifed then the prompt text is set again
				}
			}
		});
		panel.add(demographicContactinfo);//the component is added to the panel
		
		demographicDrinkerPromptLbl.setText("Drinker");//formatting component to include text
		demographicDrinkerPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicDrinkerPromptLbl.setSize(250,40);//size of component is set
		demographicDrinkerPromptLbl.setOpaque(false);//the component is made transparent
		demographicDrinkerPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		panel.add(demographicDrinkerPromptLbl);//the component is added to the panel
		
		demographicSmokerPromptLbl.setText("Smoker");//formatting component to include text
		demographicSmokerPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicSmokerPromptLbl.setSize(250,40);//size of component is set
		demographicSmokerPromptLbl.setOpaque(false);//the component is made transparent
		demographicSmokerPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		panel.add(demographicSmokerPromptLbl);//the component is added to the panel
		
		demographicAllergiesPromptLbl.setText("Allergies:");//formatting component to include text
		demographicAllergiesPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicAllergiesPromptLbl.setSize(250,40);//size of component is set
		demographicAllergiesPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		panel.add(demographicAllergiesPromptLbl);//the component is added to the panel
		
		demographicAllergies.setText("Please enter any Allergies You may \npossess");//formatting component to include text
		demographicAllergies.setSize(315,105);//size of component is set
		demographicAllergies.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		demographicAllergies.setForeground(importantRedColor);//the component has its font colour changed to a desireable one
		demographicAllergies.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components
		demographicAllergies.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e){
			//when focus has been gained(text field has been clicked on) this method will run
			
				if(demographicAllergies.getText().equals("Please enter any Allergies You may \npossess"))//selection checking if the text field contains the text prompt
				{
					demographicAllergies.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(demographicAllergies.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					demographicAllergies.setText("Please enter any Allergies You may \npossess");//if satisifed then the prompt text is set again
				}
			}
		});
		panel.add(demographicAllergies);//the component is added to the panel
		
		demographicNotioanlityPromptLbl.setText("Nationality:");//formatting component to include text
		demographicNotioanlityPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicNotioanlityPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		demographicNotioanlityTextFeild.setText("Please enter your home country");//formatting component to include text
		demographicNotioanlityTextFeild.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e){
			//when focus has been gained(text field has been clicked on) this method will run
			
				if(demographicNotioanlityTextFeild.getText().equals("Please enter your home country"))//selection checking if the text field contains the text prompt
				{
					demographicNotioanlityTextFeild.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(demographicNotioanlityTextFeild.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					demographicNotioanlityTextFeild.setText("Please enter your home country");//if satisifed then the prompt text is set again
				}
			}
		});
		
		
		demographicReligonPromptLbl.setText("Religion:");//formatting component to include text
		demographicReligonPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicReligonPromptLbl.setSize(250,40);//size of component is set
		demographicReligonPromptLbl.setLocation(605,145);//the location of the component is set to the desired part of the panel
		demographicReligonPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		panel.add(demographicReligonPromptLbl);//the component is added to the panel
		
		demographicReligonTextFeild.setText("Please enter your Religion");//formatting component to include text
		demographicReligonTextFeild.setSize(315,35);//size of component is set
		demographicReligonTextFeild.setLocation(710,152);//the location of the component is set to the desired part of the panel
		demographicReligonTextFeild.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e){
			//when focus has been gained(text field has been clicked on) this method will run
			
				if(demographicReligonTextFeild.getText().equals("Please enter your Religion"))//selection checking if the text field contains the text prompt
				{
					demographicReligonTextFeild.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(demographicReligonTextFeild.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					demographicReligonTextFeild.setText("Please enter your Religion");//if satisifed then the prompt text is set again
				}
			}
		});
		panel.add(demographicReligonTextFeild);//the component is added to the panel
		
		demographicDisablitiesPromptLbl.setText("Disabilities(if none leave blank):");//formatting component to include text
		demographicDisablitiesPromptLbl.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		demographicDisablitiesPromptLbl.setSize(400,40);//size of component is set
		demographicDisablitiesPromptLbl.setOpaque(false);//the component is made transparent
		demographicDisablitiesPromptLbl.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		panel.add(demographicDisablitiesPromptLbl);//the component is added to the panel
		
		demographicDisablitiesTextFeild.setSize(315,75);//size of component is set
		demographicDisablitiesTextFeild.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components
		panel.add(demographicDisablitiesTextFeild);//the component is added to the panel
		
		demographicCarerPromptLbl.setText("Need a carer:");//formatting component to include text
		demographicCarerPromptLbl.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		demographicCarerPromptLbl.setSize(250,40);//size of component is set
		demographicCarerPromptLbl.setOpaque(false);//the component is made transparent
		demographicCarerPromptLbl.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		panel.add(demographicCarerPromptLbl);//the component is added to the panel
		
		demographicTranslatorPromptLbl.setText("Need a Translator:");//formatting component to include text
		demographicTranslatorPromptLbl.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		demographicTranslatorPromptLbl.setOpaque(false);//the component is made transparent
		demographicTranslatorPromptLbl.setSize(250,40);//size of component is set
		demographicTranslatorPromptLbl.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		panel.add(demographicTranslatorPromptLbl);//the component is added to the panel
				
		whiteBox16.setSize(755,55);//size of component is set
		panel.add(whiteBox16);//the component is added to the panel
				
		whiteBox11.setSize(450,130);//size of component is set
		panel.add(whiteBox11);//the component is added to the panel
				
		whiteBox12.setSize(200,55);//size of component is set
		panel.add(whiteBox12);//the component is added to the panel
				
		whiteBox13.setSize(200,55);//size of component is set
		panel.add(whiteBox13);//the component is added to the panel
		
		whiteBox14.setSize(400,205);//size of component is set
		panel.add(whiteBox14);//the component is added to the panel
		
		
	}
	
	//Create New Patient Demographic Panel
	//NOTE USES THE METHOD createdemographicHomepageGeneral TO CREATE COMPONENTS
	
	public void createNewPatientHomepagePanelGUI()//
	{
		
		addPanel(symptomNewPatientPanel);
		
		symptomNewPatientPanel.setVisible(false);//component is made invisible
		symptomNewPatientPanel.setVisible(true);//component is made visible
		demographicNotioanlityPromptLbl.setSize(250,40);//size of component is set
		demographicNotioanlityPromptLbl.setLocation(605,242);//the location of the component is set to the desired part of the panel
		symptomNewPatientPanel.add(demographicNotioanlityPromptLbl);//the component is added to the panel
		
		demographicNotioanlityTextFeild.setSize(315,35);//size of component is set
		demographicNotioanlityTextFeild.setLocation(730,243);//the location of the component is set to the desired part of the panel
		
		demographicHeaderLbl.setText("New Patient");//formatting component to include text
		demographicHeaderLbl.setFont(buttonFontFormat);//the component has had its font set to a design with the correct size for its purpose
		demographicHeaderLbl.setSize(200,60);//size of component is set
		demographicHeaderLbl.setLocation(1100,40);//the location of the component is set to the desired part of the panel
		symptomNewPatientPanel.add(demographicHeaderLbl);//the component is added to the panel
		
		whiteBox9.setSize(450,55);//size of component is set
		whiteBox9.setLocation(600,235);//the location of the component is set to the desired part of the panel
		
		demographicContactinfoPromptLbl.setLocation(495,325);//the location of the component is set to the desired part of the panel
		demographicContactinfo.setLocation(720,332);//the location of the component is set to the desired part of the panel
		whiteBox16.setLocation(490,325);//the location of the component is set to the desired part of the panel
		
		demographicSmokerPromptLbl.setLocation(1080,235);//the location of the component is set to the desired part of the panel
		whiteBox12.setLocation(1075,235);//the location of the component is set to the desired part of the panel
		
		whiteBox13.setLocation(1155,145);//the location of the component is set to the desired part of the panel
		demographicDrinkerPromptLbl.setLocation(1160,145);//the location of the component is set to the desired part of the panel
		
		demographicAllergiesPromptLbl.setLocation(70,730);//the location of the component is set to the desired part of the panel
		demographicAllergies.setLocation(170,735);//the location of the component is set to the desired part of the panel
		whiteBox11.setLocation(45,725);//the location of the component is set to the desired part of the panel
		
		whiteBox14.setLocation(510,657);//the location of the component is set to the desired part of the panel
		
		demographicDisablitiesPromptLbl.setLocation(544,657);//the location of the component is set to the desired part of the panel
		demographicDisablitiesTextFeild.setLocation(544,697);//the location of the component is set to the desired part of the panel
		demographicTranslatorPromptLbl.setLocation(544,817);//the location of the component is set to the desired part of the panel
		demographicCarerPromptLbl.setLocation(544,777);//the location of the component is set to the desired part of the panel
	
		titleUpperBlackLine.setLocation(770,35);//the location of the component is set to the desired part of the panel
		titleUpperBlackLine.setSize(600,3);//size of component is set
		titleLowerBlackLine.setLocation(770,105);//the location of the component is set to the desired part of the panel
		titleLowerBlackLine.setSize(600,3);//size of component is set
		
		symptomNewPatientPanel.add(demographicNotioanlityPromptLbl);//the component is added to the panel
		symptomNewPatientPanel.add(demographicNotioanlityTextFeild);//the component is added to the panel
		symptomNewPatientPanel.add(titleLowerBlackLine);//the component is added to the panel
		symptomNewPatientPanel.add(titleUpperBlackLine);//the component is added to the panel
		symptomNewPatientPanel.add(whiteBox9);	//the component is added to the panel
		
		if(loaded[0][2]== false)//selection determines whether the panel is yet to be loaded
		{
			createdemographicHomepageGeneral(symptomNewPatientPanel,1);//as the correct panel is visible the components on the panel is formatted 
			loaded[0][2]= true;//the variable is set as true to prevent the components from being reran
			loaded[3][2]= false;//the attribute is set to false so it can be reran
			loaded[8][1]= false;//the attribute is set to false so it can be reran
		}
		if(loaded[0][3]== false)//selection determines whether the panel is yet to be loaded
		{
			createNewPatientdemographicHomepageOtherParts(symptomNewPatientPanel);//as the correct panel is visible the components on the panel is formatted 
			loaded[0][3]= true;//the variable is set as true to prevent the components from being reran
		}
		whiteBox4.setSize(530,55);//size of component is set
		whiteBox4.setLocation(45,145);//the location of the component is set to the desired part of the panel
		whiteBox4.setVisible(true);//the component is made visible
		symptomNewPatientPanel.add(whiteBox4);//the component is added to the panel
		
		whiteBox5.setSize(530,55);//size of component is set
		whiteBox5.setLocation(45,235);//the location of the component is set to the desired part of the panel
		whiteBox5.setVisible(true);//the component is made visible
		symptomNewPatientPanel.add(whiteBox5);//the component is added to the panel
		
		whiteBox6.setSize(530,55);//size of component is set
		whiteBox6.setLocation(600,145);//the location of the component is set to the desired part of the panel
		whiteBox6.setVisible(true);//the component is made visible
		symptomNewPatientPanel.add(whiteBox6);//the component is added to the panel
		
		whiteBox8.setSize(420,55);//size of component is set
		whiteBox8.setLocation(45,325);//the location of the component is set to the desired part of the panel
		whiteBox8.setVisible(true);//the component is made visible
		symptomNewPatientPanel.add(whiteBox8);//the component is added to the panel
		
		whiteBox10.setSize(700,205);//size of component is set
		whiteBox10.setVisible(true);//the component is made visible
		whiteBox10.setLocation(45,415);//the location of the component is set to the desired part of the panel
		symptomNewPatientPanel.add(whiteBox10);//the component is added to the panel
		
		whiteBox15.setSize(425,55);//size of component is set
		whiteBox15.setLocation(45,650);//the location of the component is set to the desired part of the panel
		whiteBox15.setVisible(true);//the component is made visible
		symptomNewPatientPanel.add(whiteBox15);	//the component is added to the panel
		
	}
		public void createNewPatientdemographicHomepageOtherParts(JPanel panel)
		{
		newPatientHelpTF.setSize(400,300);//size of component is set
		newPatientHelpTF.setLocation(770,415);//the location of the component is set to the desired part of the panel
		newPatientHelpTF.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		newPatientHelpTF.setText("Before a new admission can be created you will need to create an account, please fill in all the fields.\nAfter the account has been generated a new admission containing the symptoms entered will be generated and an appointment can be created as soon as possible.");//formatting component to include text
		panel.add(newPatientHelpTF);//the component is added to the panel
		newPatientHelpTF.setOpaque(false);//the component is made transparent
		newPatientHelpTF.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
		
		newAccountcreateAccountbbtn.setLocation(1140,740);//the components size is correctly declared
		newAccountcreateAccountbbtn.setSize(180,49);//the components size is correctly declared
		newAccountcreateAccountbbtn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		newAccountcreateAccountbbtn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		newAccountcreateAccountbbtn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		newAccountcreateAccountbbtn.setText("Create Account");//the label is geven text to add meaning to the label
		symptomNewPatientPanel.add(newAccountcreateAccountbbtn);//the component is added to the panel
		
		newPatientBackToRecommendation.setLocation(96,63);//sets the location of the component 
		newPatientBackToRecommendation.setSize(200,49);//the components size is correctly declared
		newPatientBackToRecommendation.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		newPatientBackToRecommendation.setForeground( new Color(-1) );//the foreground of the component is given a white font
		newPatientBackToRecommendation.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		newPatientBackToRecommendation.setText("Go Back");//the button is geven text to add meaning to the label
		symptomNewPatientPanel.add(newPatientBackToRecommendation);//the component is added to the panel
	}
	
	//Admission homepage
	public void createBlankAdmissionHomePage()
	{
		emptyArray(emptyAdmissionHomepagePanel);
		createTopbar(emptyAdmissionHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		createTopbarAdmission(emptyAdmissionHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		if(numberOfAdmissions<=0)//selection determining if the patient has at least one admission
		{
			if(loaded[6][6]== false)//selection determines whether the panel is yet to be loaded
			{
				noAdmissionLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
				noAdmissionLbl.setFont(headerFontFormat);//a font is applied to the text of the component 
				noAdmissionLbl.setLocation(300,210);//sets the location of the component 
				noAdmissionLbl.setSize(2000,100);//the components size is correctly declared
				noAdmissionLbl.setOpaque(false);//the component is set to opaque
				noAdmissionLbl.setText("You have no admissions currently");
				emptyAdmissionHomepagePanel.add(noAdmissionLbl);
				noAdmissionFootNoteLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
				noAdmissionFootNoteLbl.setFont(buttonFontFormat);//a font is applied to the text of the component 
				noAdmissionFootNoteLbl.setLocation(300,310);//sets the location of the component 
				noAdmissionFootNoteLbl.setSize(2000,100);//the components size is correctly declared
				noAdmissionFootNoteLbl.setOpaque(false);//the component is set to opaque
				noAdmissionFootNoteLbl.setText("Please create one if you need medical assistance");
				loaded[6][6]= true;//loaded is to true that the panel cant be reloaded
				emptyAdmissionHomepagePanel.add(noAdmissionFootNoteLbl);
			}
		}
		
	}
	public void createadmissionHomepagePanelGUI()//
	{
		currentPage = 1;//the current page is set to 1
		currentPageIndex = 0;//the Current page index is set to 0
		emptyArray(admissionHomepagePanel);
		
		loaded[6][0]= false;//loaded is to false that the panel can be reloaded
		createTopbar(admissionHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		
		createTopbarAdmission(admissionHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		if(numberOfAdmissions>0)//selection determining if the patient has at least one admission
		{
			
			System.out.println("Admisison");
			createContactBarAdmission(admissionHomepagePanel);//as the correct panel is visible the components on the panel is formatted
			if(loaded[6][2]== false)//selection determines whether the panel is yet to be loaded
			{
				createContactBarAdmissionGeneral();//creates components for the contact bar as their is new information
				if(currentPatient.numberOfAdmissions>0)//selection determining if the patient has at least one admission
				{
					currentAdmission = admissionList[0];//the current admission is set
					listOfdocumentsGUI = currentAdmission.listOfDocuments;//the list of documents is set
					numberOfDocuments =currentAdmission.numberOfDocuments;//the number of documents is found and then set
					singleDefinition = new Document[numberOfDocuments];
					setActiveAdmissionTopPanelBttn(topBarAdmission1Bttn);//the active panel has its button set as the current tab
				}
				createMainPartAdmissionHomePageGeneral();//general parts are of the homepage are created by calling this method
				loaded[6][2]= true;//loaded is to true that the panel cant be reloaded
			}
			createMainPartAdmissionHomePage(admissionHomepagePanel);//main parts of the homepage are genereated
		}
		admissionHomepagePanel.setVisible(false);//the panel is hidden from sight	
		admissionHomepagePanel.setVisible(true);//the panel is then made to reappear 
		if(currentPatient.numberOfAdmissions>4)
		{
			topBarNewAdmissionBttn.setEnabled(false);
		}
		if(currentPatient.numberOfAdmissions<=4)
		{
			topBarNewAdmissionBttn.setEnabled(true);
		}
	}
		public void createMainPartAdmissionHomePage(JPanel panel)
		{
			sortBoxAdmissionHpagePanel.setEnabled(true);//the component is enabled to interact with the user
			
			
			clearSearchbttnAdmission.setEnabled(false);//disables button
			listOfdocumentsGUI = currentAdmission.listOfDocuments;
		panel.add(admissioncardTitlepanel1);//the component is added to the panel
		panel.add(admissioncardTitlepanel2);//the component is added to the panel
		panel.add(admissioncardTitlepanel3);//the component is added to the panel
		panel.add(admissioncardTitlepanel4);//the component is added to the panel
		panel.add(admissioncardTitlepanel5);//the component is added to the panel
		panel.add(admissioncardTitlepanel6);//the component is added to the panel
		panel.add(viewDocument1AdmissionBttn);//the component is added to the panel
		panel.add(viewDocument2AdmissionBttn);//the component is added to the panel
		panel.add(viewDocument3AdmissionBttn);//the component is added to the panel
		panel.add(viewDocument4AdmissionBttn);//the component is added to the panel
		panel.add(viewDocument5AdmissionBttn);//the component is added to the panel
		panel.add(viewDocument6AdmissionBttn);//the component is added to the panel
		panel.add(admissioncardDescriptionpane1);//the component is added to the panel
		panel.add(admissioncardDescriptionpane2);//the component is added to the panel
		panel.add(admissioncardDescriptionpane3);//the component is added to the panel
		panel.add(admissioncardDescriptionpane4);//the component is added to the panel
		panel.add(admissioncardDescriptionpane5);//the component is added to the panel
		panel.add(admissioncardDescriptionpane6);//the component is added to the panel
		panel.add(admissionCardPageForwardBttn);//the component is added to the panel
		panel.add(viewDocument1AdmissionSpecificBttn);//the component is added to the panel
		panel.add(viewDocument2AdmissionSpecificBttn);//the component is added to the panel
		panel.add(viewDocument3AdmissionSpecificBttn);//the component is added to the panel
		panel.add(viewDocument4AdmissionSpecificBttn);//the component is added to the panel
		panel.add(viewDocument5AdmissionSpecificBttn);//the component is added to the panel
		panel.add(viewDocument6AdmissionSpecificBttn);//the component is added to the panel
		panel.add(admissionCardPageBackBttn);//the component is added to the panel
		panel.add(sortBoxAdmissionHpagePanel);//the component is added to the panel
		panel.add(viewDocument1AdmissionSearchBttn);//the component is added to the panel
		panel.add(searchLibraryBttnAdmission);
		panel.add(clearSearchbttnAdmission);
		whiteBox4.setSize(250,325);//size of component is set
		whiteBox4.setLocation(460,165);//the location of the component is set to the desired part of the panel
		panel.add(whiteBox4);//the component is added to the panel
		
		whiteBox5.setSize(250,325);//size of component is set
		whiteBox5.setLocation(760,165);//the location of the component is set to the desired part of the panel
		panel.add(whiteBox5);//the component is added to the panel
		
		whiteBox6.setSize(250,325);//size of component is set
		whiteBox6.setLocation(1060,165);//the location of the component is set to the desired part of the panel
		panel.add(whiteBox6);//the component is added to the panel
		
		whiteBox8.setSize(250,325);//size of component is set
		whiteBox8.setLocation(460,500);//the location of the component is set to the desired part of the panel
		panel.add(whiteBox8);//the component is added to the panel
		
		whiteBox10.setSize(250,325);//size of component is set
		whiteBox10.setLocation(760,500);//the location of the component is set to the desired part of the panel
		panel.add(whiteBox10);//the component is added to the panel
		
		whiteBox15.setSize(250,325);//size of component is set
		whiteBox15.setLocation(1060,500);//the location of the component is set to the desired part of the panel
		panel.add(whiteBox15);//the component is added to the panel
		
		panel.add(sortBoxAdmissionHpagePanel);//the component is added to the panel
		panel.add(searchBoxAdmissionHpagePanel);//the component is added to the panel
		
		lightGreyTopPanelAdmissionSearchBar.setSize(910,35);//size of component is set
		lightGreyTopPanelAdmissionSearchBar.setLocation(425,115);//the location of the component is set to the desired part of the panel
		panel.add(lightGreyTopPanelAdmissionSearchBar);//the component is added to the panel
		panel.add(lighterGreyAdmissionPanel);//the component is added to the panel
		lighterGreyAdmissionPanel.setSize(975,745);//size of component is set
		lighterGreyAdmissionPanel.setLocation(400,100);//the location of the component is set to the desired part of the panel
		outputDocumentCards(currentAdmission);//the component is added to the panel
		
	}
			public void createTopbarAdmission(JPanel panel)
			{
		if(loaded[7][2]== false)//selection determines whether the panel is yet to be loaded
		{
			createTopbarAdmissionGeneral();//as the correct panel is visible the components on the panel is formatted 
			loaded[7][2]= true;//the variable is set as true to prevent the components from being reran
		}
		//method to set admission 1 as active HERE
		panel.add(topBarAdmission1Bttn);//the component is added to the panel
		panel.add(topBarAdmission2Bttn);//the component is added to the panel
		panel.add(topBarAdmission3Bttn);//the component is added to the panel
		panel.add(topBarAdmission4Bttn);//the component is added to the panel
		panel.add(topBarAdmission5Bttn);//the component is added to the panel
		panel.add(topBarNewAdmissionBttn);//the component is added to the panel
		panel.add(topwhitelinebarAdmission);//the component is added to the panel
		topBarAdmission1Bttn.setVisible(false);//component is set to being invisible
		topBarAdmission2Bttn.setVisible(false);//component is set to being invisible
		topBarAdmission3Bttn.setVisible(false);//component is set to being invisible
		topBarAdmission4Bttn.setVisible(false);//component is set to being invisible
		topBarAdmission5Bttn.setVisible(false);//component is set to being invisible
		createButtonTopBarAdmission();//the top bar is created for the admission buttons
		adminHomepagePanel.setVisible(false);//the panel is hidden from sight	
		adminHomepagePanel.setVisible(true);//the panel is then made to reappear 
		if(currentPatient.numberOfAdmissions>4)
		{
			topBarNewAdmissionBttn.setEnabled(false);
		}
		if(currentPatient.numberOfAdmissions<=4)
		{
			topBarNewAdmissionBttn.setEnabled(true);
		}
	}
				public void createButtonTopBarAdmission()
				{
		int frameWidth = frame.getWidth();//the frame width is found
		int buttonSize = frameWidth/(numberOfAdmissions+1);//the correct size for each button is found
		int buttonLocationXAxis = 0;//the start position for the first button is declared
		if(numberOfAdmissions<=3)//selection determining if the number admissions is too small
		{
			buttonSize = 368;//manually sets the size of button so that it is not the entire width of the frame.
			
		}
		if(numberOfAdmissions>=5)//selection determining ifthere are more than 5 admissions
		{
			topBarAdmission1Bttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			topBarAdmission2Bttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			topBarAdmission3Bttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			topBarAdmission4Bttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			topBarAdmission5Bttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			topBarNewAdmissionBttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			topBarAdmission1Bttn.setLocation(buttonLocationXAxis,40);//the intial button is placed at 0,40
			buttonLocationXAxis =buttonLocationXAxis+buttonSize;//the location increments by adding on the mean size this allows for correct positioning
			topBarAdmission2Bttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
			buttonLocationXAxis =buttonLocationXAxis+buttonSize;//the location increments by adding on the mean size this allows for correct positioning
			topBarAdmission3Bttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
			buttonLocationXAxis =buttonLocationXAxis+buttonSize;//the location increments by adding on the mean size this allows for correct positioning
			topBarAdmission4Bttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
			buttonLocationXAxis =buttonLocationXAxis+buttonSize;//the location increments by adding on the mean size this allows for correct positioning
			topBarAdmission5Bttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
			buttonLocationXAxis =buttonLocationXAxis+buttonSize;//the location increments by adding on the mean size this allows for correct positionin
			topBarNewAdmissionBttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
			topBarAdmission1Bttn.setVisible(true);//the correct button is made visable
			topBarAdmission2Bttn.setVisible(true);//the correct button is made visable
			topBarAdmission3Bttn.setVisible(true);//the correct button is made visable
			topBarAdmission4Bttn.setVisible(true);//the correct button is made visable
			topBarAdmission5Bttn.setVisible(true);//the correct button is made visable
		}
		if(numberOfAdmissions==4)//selection determining if there are 4 admissions
		{
			topBarAdmission1Bttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			topBarAdmission2Bttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			topBarAdmission3Bttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			topBarAdmission4Bttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			topBarNewAdmissionBttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			
			topBarAdmission1Bttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
			buttonLocationXAxis =buttonLocationXAxis+buttonSize;//the location increments by adding on the mean size this allows for correct positioning
			
			topBarAdmission2Bttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
			buttonLocationXAxis =buttonLocationXAxis+buttonSize;//the location increments by adding on the mean size this allows for correct positioning
			
			topBarAdmission3Bttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
			buttonLocationXAxis =buttonLocationXAxis+buttonSize;//the location increments by adding on the mean size this allows for correct positioning
			
			topBarAdmission4Bttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
			buttonLocationXAxis =buttonLocationXAxis+buttonSize;//the location increments by adding on the mean size this allows for correct positioning
			
			topBarNewAdmissionBttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
			topBarAdmission1Bttn.setVisible(true);//the correct button is made visable
			topBarAdmission2Bttn.setVisible(true);//the correct button is made visable
			topBarAdmission3Bttn.setVisible(true);//the correct button is made visable
			topBarAdmission4Bttn.setVisible(true);//the correct button is made visable
		}
		if(numberOfAdmissions==3)//selection determining if there are 3 admissions
		{
			topBarAdmission1Bttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			topBarAdmission2Bttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			topBarAdmission3Bttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			topBarNewAdmissionBttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			
			topBarAdmission1Bttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
			buttonLocationXAxis =buttonLocationXAxis+buttonSize;//the location increments by adding on the mean size this allows for correct positioning
			
			topBarAdmission2Bttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
			buttonLocationXAxis =buttonLocationXAxis+buttonSize;//the location increments by adding on the mean size this allows for correct positioning
			
			topBarAdmission3Bttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
			buttonLocationXAxis =buttonLocationXAxis+buttonSize;//the location increments by adding on the mean size this allows for correct positioning
			
			topBarNewAdmissionBttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
			topBarAdmission1Bttn.setVisible(true);//the correct button is made visable
			topBarAdmission2Bttn.setVisible(true);//the correct button is made visable
			topBarAdmission3Bttn.setVisible(true);//the correct button is made visable
		}
		if(numberOfAdmissions==2)//selection determining if there are 2 admissions
		{
			topBarAdmission1Bttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			topBarAdmission2Bttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			topBarNewAdmissionBttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			
			topBarAdmission1Bttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
			buttonLocationXAxis =buttonLocationXAxis+buttonSize;//the location increments by adding on the mean size this allows for correct positioning
			
			topBarAdmission2Bttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
			buttonLocationXAxis =buttonLocationXAxis+buttonSize;//the location increments by adding on the mean size this allows for correct positioning
			
			topBarNewAdmissionBttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
			topBarAdmission1Bttn.setVisible(true);//the correct button is made visable
			topBarAdmission2Bttn.setVisible(true);//the correct button is made visable
		}
		if(numberOfAdmissions==1)//selection determining if there is 1 admission
		{
			topBarAdmission1Bttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			topBarNewAdmissionBttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size
			
			topBarAdmission1Bttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
			buttonLocationXAxis =buttonLocationXAxis+buttonSize;//the location increments by adding on the mean size this allows for correct positioning
			
			topBarNewAdmissionBttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
		
			topBarAdmission1Bttn.setVisible(true);//the correct button is made visable
		}
		if(numberOfAdmissions==0)//selection determining if no admissions
		{
			topBarNewAdmissionBttn.setSize(buttonSize,30);//the size of the button is found using the integer value found by finding the mean length for the size

			topBarNewAdmissionBttn.setLocation(buttonLocationXAxis,40);//the next butto is located directly next to the otheree button using the newly calculated position
		}
	}
				public void createTopbarAdmissionGeneral()
				{
		formatTopBarAdmissionButton(topBarAdmission2Bttn);//calls method which formats the component correctly
		formatTopBarAdmissionButton(topBarAdmission3Bttn);//calls method which formats the component correctly
		formatTopBarAdmissionButton(topBarAdmission4Bttn);//calls method which formats the component correctly
		formatTopBarAdmissionButton(topBarAdmission5Bttn);//calls method which formats the component correctly
		formatTopBarAdmissionButton(topBarAdmission1Bttn);//calls method which formats the component correctly
		
		topBarAdmission1Bttn.setText("Admission 1");//the label is geven text to add meaning to the label
		topBarAdmission3Bttn.setText("Admission 3");//the label is geven text to add meaning to the label
		topBarAdmission2Bttn.setText("Admission 2");//the label is geven text to add meaning to the label
		topBarAdmission4Bttn.setText("Admission 4");//the label is geven text to add meaning to the label
		topBarAdmission5Bttn.setText("Admission 5");//the label is geven text to add meaning to the label
		
		topBarNewAdmissionBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		topBarNewAdmissionBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		topBarNewAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		topBarNewAdmissionBttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component 
		topBarNewAdmissionBttn.setText("New Admission");//the label is geven text to add meaning to the label
		
		topwhitelinebarAdmission.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		topwhitelinebarAdmission.setForeground( new Color(-1) );//the foreground of the component is given a white font
		topwhitelinebarAdmission.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		topwhitelinebarAdmission.setSize(1457,30);//the components size is correctly declared
		topwhitelinebarAdmission.setOpaque(true);//the component is set to opaque
		topwhitelinebarAdmission.setLocation(0,40); //sets the location of the component 
	}
					public void formatTopBarAdmissionButton(JButton button)
					{
		button.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		button.setForeground( new Color(-1) );//the foreground of the component is given a white font
		button.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		button.setFont(whiteLoginFont);//the font that has been declared is attached to the component 
	}
				public void createContactBarAdmission(JPanel panel)
				{
					
				loaded[6][0]= false;//variable set to false so the corrospoding panel can be reloaded
		panel.add(blackLine14);//the component is added to the panel
		panel.add(blackLine15);//the component is added to the panel
		panel.add(adminIDLbl);//the component is added to the panel
		panel.add(ConsultantNamePromptFeild);//the component is added to the panel
		panel.add(ConsultantNameFeild);//the component is added to the panel
		panel.add(wardFeild);//the component is added to the panel
		panel.add(dateOfAdmissionField);//the component is added to the panel
		panel.add(reinstatePatientBttn);//the component is added to the panel
		panel.add(medicationField);//the component is added to the panel
		panel.add(dateOfNextAppointmentField);//the component is added to the panel
		panel.add(timeOfNextAppointmentField);//the component is added to the panel
		panel.add(dischargmentStatusField);//the component is added to the panel
		panel.add(blackLine3);//the component is added to the pane
		panel.add(wardPromptFeild);//the component is added to the panel
		panel.add(blackLine4);//the component is added to the panel
		panel.add(blackLine5);//the component is added to the panels
		panel.add(dateOfAdmissionPromptField);//the component is added to the panel
		panel.add(blackLine6);//the component is added to the panel
		panel.add(medicationPromptField);//the component is added to the panel
		panel.add(blackLine7);//the component is added to the panel
		panel.add(blackLine8);//the component is added to the panel
		panel.add(dateOfNextAppointmentPromptField);//the component is added to the panel
		panel.add(blackLine9);//the component is added to the panel
		panel.add(viewAppointmentsButton);//the component is added to the panel
		panel.add(blackLine10);//the component is added to the panel
		panel.add(timeOfNextAppointmentPromptField);//the component is added to the panel
		panel.add(blackLine11);//the component is added to the panel
		panel.add(dischargmentStatusPromptField);//the component is added to the panel
		panel.add(editAdmissionBttn);//the component is added to the panel
		panel.add(requestReinstatementBttn);//the component is added to the panel
		panel.add(newDocumentButton);//the component is added to the panel
		
		blackLine14.setLocation(35,115);//sets the location of the component 
		blackLine14.setSize(300,3);//the components size is correctly declared
		blackLine15.setLocation(35,185);//sets the location of the component 
		blackLine15.setSize(300,3);//the components size is correctly declared
		blackLine3.setLocation(137,251);//sets the location of the component 
		blackLine3.setSize(200,2);//the components size is correctly declared
		blackLine4.setLocation(90,301);//sets the location of the component 
		blackLine4.setSize(230,2);//the components size is correctly declared
		blackLine5.setLocation(200,351);//sets the location of the component 
		blackLine5.setSize(130,2);//the components size is correctly declared
		blackLine6.setLocation(135,401);//sets the location of the component 
		blackLine6.setSize(170,2);//the components size is correctly declared
		blackLine7.setLocation(35,440);//sets the location of the component 
		blackLine7.setSize(260,2);//the components size is correctly declared
		blackLine8.setLocation(35,480);//sets the location of the component 
		blackLine8.setSize(260,2);//the components size is correctly declared
		blackLine9.setLocation(200,545);//sets the location of the component 
		blackLine9.setSize(150,2);//the components size is correctly declared
		blackLine10.setLocation(200,675);//sets the location of the component 
		blackLine10.setSize(150,2);//the components size is correctly declared
		blackLine11.setLocation(200,725);//sets the location of the component 
		blackLine11.setSize(150,2);//the components size is correctly declared
		
		panel.add(whiteBox1);//the component is added to the panel
		whiteBox1.setLocation(15,100); //sets the location of the component 
		whiteBox1.setSize(350,745);//the components size is correctly declared
		if(loaded[6][0]== false)//selection determines whether the panel is yet to be loaded
		{
			createContactBarAdmissionGeneral();//as the correct panel is visible the components on the panel is formatted 
			loaded[6][0]= true;//the variable is set as true to prevent the components from being reran
		}
		if(numberOfAdmissions<=0)//selection determining if the patient has at least one admission
		{
			editAdmissionBttn.setEnabled(false);
		}
		if(numberOfAdmissions>0)//selection determining if the patient has at least one admission
		{
			editAdmissionBttn.setEnabled(true);
		}
		adminHomepagePanel.setVisible(false);//the panel is hidden from sight	
		adminHomepagePanel.setVisible(true);//the panel is then made to reappear 
		if(currentPatient.numberOfAdmissions>0)//selection determining if there are more than 0 admissions
		{
			
			dateOfAdmissionField.setText(ft.format(currentAdmission.dateAdmissionCreated));//formatting component to include text
			ConsultantNameFeild.setText("Dr "+currentAdmission.consultantName);//the feild is given the data of the feild prompt inidacting what it is the feild contains
			adminIDLbl.setText(currentAdmission.admissionID);//the feild is given the data of the user's infornmation
			medicationField.setText(currentAdmission.medication);//the feild is given the data of the user's infornmation
			timeOfNextAppointmentField.setText(timeft.format(currentAdmission.dateOfNextAppointmentA));//the feild is given the data of the user's infornmation
			wardFeild.setText(currentAdmission.ward);//the feild is given the data of the user's infornmation
			dateOfNextAppointmentField.setText(ft.format(currentAdmission.dateOfNextAppointmentA));//the feild is given the data of the user's infornmation
			if(currentAdmission.active== true)//selection determining if the current attribute meets the condition
			{
				requestReinstatementBttn.setEnabled(false);//the component is disabled
				reinstatePatientBttn.setEnabled(false);//the component is disabled
				dischargmentStatusField.setText("False");//the feild is given the data of the user's infornmation
			}
			if(currentAdmission.active== false)//selection determining if the current attribute meets the condition
			{
				requestReinstatementBttn.setEnabled(true);//the component is enabled to interact with the user
				dischargmentStatusField.setText("True");//the feild is given the data of the user's infornmation
				reinstatePatientBttn.setEnabled(true);//the component is enabled to interact with the user
			}
			adminHomepagePanel.setVisible(false);//the panel is hidden from sight	
			adminHomepagePanel.setVisible(true);//the panel is then made to reappear
		}	
	}
					public void createContactBarAdmissionGeneral()
					{	
					
		
		if(loginChoice ==0)//selection determining if the current attribute meets the condition
		{
			ConsultantNamePromptFeild.setVisible(true);//the component is made visable
			ConsultantNameFeild.setVisible(true);//the component is made visable
			newDocumentButton.setVisible(false);//the component is hidden from sight
			ConsultantNamePromptFeild.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		ConsultantNamePromptFeild.setFont(symptomfont);//a font is applied to the text of the component 
		ConsultantNamePromptFeild.setLocation(35,210);//sets the location of the component 
		ConsultantNamePromptFeild.setSize(100,50);//the components size is correctly declared
		ConsultantNamePromptFeild.setText("Consultant:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
		ConsultantNamePromptFeild.setOpaque(false);//the component is set to opaque
		
		ConsultantNameFeild.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		ConsultantNameFeild.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		ConsultantNameFeild.setLocation(140,210);//sets the location of the component 
		ConsultantNameFeild.setSize(200,50);//the components size is correctly declared
		ConsultantNameFeild.setOpaque(false);//the component is set to opaque
		
		
		
		}
		if(loginChoice ==3)//selection determining if the current attribute meets the condition
		{
			ConsultantNamePromptFeild.setVisible(false);//the component is hidden from sight
			ConsultantNameFeild.setVisible(false);//the component is hidden from sight
			newDocumentButton.setVisible(true);//the component is made visable
			newDocumentButton.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
			newDocumentButton.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
			
			newDocumentButton.setText("New Document");//formatting component to include text
			newDocumentButton.setOpaque(true);//the component is set to opaque
			newDocumentButton.setLocation(35,205);//t//the component is set to opaquehe location of the component is set to the desired part of the panel
			newDocumentButton.setSize(300,35);//size of component is set
			editAdmissionBttn.setText("Edit Admission");//formatting component to include text
			
			
			reinstatePatientBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
			reinstatePatientBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
			
			reinstatePatientBttn.setText("Reinstate Patient");//formatting component to include text
			reinstatePatientBttn.setOpaque(true);//the component is set to opaque
			reinstatePatientBttn.setLocation(35,795);//the location of the component is set to the desired part of the panel
			reinstatePatientBttn.setSize(300,35);//size of component is set
		}
		if(currentPatient.numberOfAdmissions>0)
		{
		if((currentAdmission.active == false)&&(currentPatient.numberOfAdmissions>0))//selection determining if the current attribute meets the condition
		{
			editAdmissionBttn.setText("View Admission");//formatting component to include text
			
		}
		}
		blackLine14.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine14.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine14.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine14.setOpaque(true);//the component is set to opaque
			
		blackLine4.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine4.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine4.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine4.setOpaque(true);//the component is set to opaque
		
		blackLine4.setVisible(false);//the component is set to opaque
		adminIDLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		adminIDLbl.setFont(headerFontFormatID);//a font is applied to the text of the component 
		adminIDLbl.setLocation(35,125);//sets the location of the component 
		adminIDLbl.setSize(285,50);//the components size is correctly declared
		adminIDLbl.setOpaque(false);//the component is set to opaque
		
		blackLine15.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine15.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine15.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine15.setOpaque(true);//the component is set to opaque
				
		blackLine3.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine3.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine3.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine3.setOpaque(true);//the component is set to opaque
		
	
		wardPromptFeild.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		wardPromptFeild.setFont(symptomfont);//a font is applied to the text of the component 
		wardPromptFeild.setLocation(35,260);//sets the location of the component 
		wardPromptFeild.setSize(50,50);//the components size is correctly declared
		wardPromptFeild.setText("Ward:");//the feild is given the data of the user's infornmation
		wardPromptFeild.setOpaque(false);//the component is set to opaque
		
		wardFeild.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		wardFeild.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		wardFeild.setLocation(90,260);//sets the location of the component 
		wardFeild.setSize(200,50);//the components size is correctly declared
		wardFeild.setOpaque(false);//the component is set to opaque
		
		dateOfAdmissionPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		dateOfAdmissionPromptField.setFont(symptomfont);//a font is applied to the text of the component 
		dateOfAdmissionPromptField.setLocation(35,310);//sets the location of the component 
		dateOfAdmissionPromptField.setSize(180,50);//the components size is correctly declared
		dateOfAdmissionPromptField.setText("Date Of Admission:");//formatting component to include text
		dateOfAdmissionPromptField.setOpaque(false);//the component is set to opaque
		
		dateOfAdmissionField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		dateOfAdmissionField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		dateOfAdmissionField.setLocation(205,310);//sets the location of the component 
		dateOfAdmissionField.setSize(345,50);//the components size is correctly declared
		dateOfAdmissionField.setOpaque(false);//the component is set to opaque
		
		blackLine5.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine5.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine5.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine5.setOpaque(true);//the component is set to opaque
		
		medicationPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		medicationPromptField.setFont(symptomfont);//a font is applied to the text of the component 
		medicationPromptField.setLocation(35,360);//sets the location of the component 
		medicationPromptField.setSize(100,50);//the components size is correctly declared
		medicationPromptField.setText("Medication:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
		medicationPromptField.setOpaque(false);//the component is set to opaque
		
		medicationField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		medicationField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		medicationField.setLocation(140,360);//sets the location of the component 
		medicationField.setSize(345,50);//the components size is correctly declared
		medicationField.setOpaque(false);//the component is set to opaque
		
		blackLine6.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine6.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine6.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine6.setOpaque(true);//the component is set to opaque
		
		blackLine7.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine7.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine7.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine7.setOpaque(true);//the component is set to opaque
		
		blackLine8.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine8.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine8.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine8.setOpaque(true);//the component is set to opaque
		
		dateOfNextAppointmentPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		dateOfNextAppointmentPromptField.setFont(symptomfont);//a font is applied to the text of the component 
		dateOfNextAppointmentPromptField.setLocation(35,490);//sets the location of the component 
		dateOfNextAppointmentPromptField.setSize(215,20);//the components size is correctly declared
		dateOfNextAppointmentPromptField.setText("Date Of Next Appointment");//the feild is given the data of the user's infornmation
		dateOfNextAppointmentPromptField.setOpaque(false);//the component is set to opaque
		
		dateOfNextAppointmentField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		dateOfNextAppointmentField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		dateOfNextAppointmentField.setLocation(220,510);//sets the location of the component 
		dateOfNextAppointmentField.setSize(140,50);//the components size is correctly declared
		dateOfNextAppointmentField.setOpaque(false);//the component is set to opaque
		
		blackLine9.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine9.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine9.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine9.setOpaque(true);//the component is set to opaque
		
		viewAppointmentsButton.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		viewAppointmentsButton.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		
		viewAppointmentsButton.setText("View Appointments");//formatting component to include text
		viewAppointmentsButton.setOpaque(true);//the component is set to opaque
		viewAppointmentsButton.setLocation(35,565);//the location of the component is set to the desired part of the panel
		viewAppointmentsButton.setSize(300,35);//size of component is set
		
		blackLine10.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine10.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine10.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine10.setOpaque(true);//the component is set to opaque
		
		timeOfNextAppointmentPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		timeOfNextAppointmentPromptField.setFont(symptomfont);//a font is applied to the text of the component 
		timeOfNextAppointmentPromptField.setLocation(35,605);//sets the location of the component 
		timeOfNextAppointmentPromptField.setSize(300,50);//the components size is correctly declared
		timeOfNextAppointmentPromptField.setText("Time Of Next Appointment");//the feild is given the data of the user's infornmation
		timeOfNextAppointmentPromptField.setOpaque(false);//the component is set to opaque
		
		timeOfNextAppointmentField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		timeOfNextAppointmentField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		timeOfNextAppointmentField.setLocation(235,635);//sets the location of the component 
		timeOfNextAppointmentField.setSize(140,50);//the components size is correctly declared
		timeOfNextAppointmentField.setOpaque(false);//the component is set to opaque
		
		dischargmentStatusPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		dischargmentStatusPromptField.setFont(symptomfont);//a font is applied to the text of the component 
		dischargmentStatusPromptField.setLocation(35,665);//sets the location of the component 
		dischargmentStatusPromptField.setSize(250,50);//the components size is correctly declared
		dischargmentStatusPromptField.setText("Dischargment Status");//the feild is given the data of the user's infornmation
		dischargmentStatusPromptField.setOpaque(false);//the component is set to opaque
		
		dischargmentStatusField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		dischargmentStatusField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		dischargmentStatusField.setLocation(235,685);//sets the location of the component 
		dischargmentStatusField.setSize(140,50);//the components size is correctly declared
		dischargmentStatusField.setOpaque(false);//the component is set to opaque
			
		blackLine11.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine11.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine11.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine11.setOpaque(true);//the component is set to opaque
			
		
		editAdmissionBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		editAdmissionBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		
		if(loginChoice!=3)//selection determining if the current attribute meets the condition
		{
		editAdmissionBttn.setText("View Admission");//formatting component to include text
		requestReinstatementBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		requestReinstatementBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		requestReinstatementBttn.setText("Request reinstatement");//formatting component to include text
		requestReinstatementBttn.setOpaque(true);//the component is set to opaque
		requestReinstatementBttn.setLocation(35,795);//the location of the component is set to the desired part of the panel
		requestReinstatementBttn.setSize(300,35);//size of component is set
		}
		editAdmissionBttn.setOpaque(true);//the component is set to opaque
		editAdmissionBttn.setLocation(35,740);//the location of the component is set to the desired part of the panel
		editAdmissionBttn.setSize(300,35);//size of component is set
		
		whiteBox1.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox1.setForeground( new Color(-1) );//the foreground of the component is given a white font
		whiteBox1.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		whiteBox1.setOpaque(true);//the component is set to opaque
		
		
	}
	//Admission cards
	public void admissionUpdateCard1SearchSpecific(Document temp)
	{
		admissioncardTitlepanel1.setText(temp.docType+" "+ft.format(temp.dateOfDocumentCreation));//formatting component to include text
		admissioncardTitlepanel1.setVisible(true);//the component is made visible
		viewDocument1AdmissionBttn.setVisible(false);//the component is made visible
		viewDocument1AdmissionSpecificBttn.setVisible(true);//the component is made visible
		admissioncardDescriptionpane1.setVisible(true);//the component is made visible
		whiteBox4.setVisible(true);//the component is made visible
		if(temp.docType.equals("Test Results")==true)//selection determining if the current attribute meets the condition
		{
			admissioncardDescriptionpane1.setText("Summary: "+temp.summary);//formatting component to include text
		}
		else{
			admissioncardDescriptionpane1.setText("");
		}
	}
	public void admissionUpdateCard1(Document temp)
	{
		admissioncardTitlepanel1.setText(temp.docType+" "+ft.format(temp.dateOfDocumentCreation));//formatting component to include text
		admissioncardTitlepanel1.setVisible(true);//the component is made visible
		viewDocument1AdmissionBttn.setVisible(true);//the component is made visible
		viewDocument1AdmissionSpecificBttn.setVisible(false);//the component is made visible

		admissioncardDescriptionpane1.setVisible(true);//the component is made visible
		whiteBox4.setVisible(true);//the component is made visible
		if(temp.docType.equals("Test Results")==true)//selection determining if the current attribute meets the condition
		{
			admissioncardDescriptionpane1.setText("Summary: "+temp.summary);//formatting component to include text
		}
		else{
			admissioncardDescriptionpane1.setText("");
		}
	}
	public void admissionUpdateCard2SearchSpecific(Document temp)
	{
		admissioncardTitlepanel2.setText(temp.docType+" "+ft.format(temp.dateOfDocumentCreation));//formatting component to include text
		admissioncardTitlepanel2.setVisible(true);//the component is made visible
		viewDocument2AdmissionBttn.setVisible(true);//the component is made visible
		admissioncardDescriptionpane2.setVisible(true);//the component is made visible
		whiteBox5.setVisible(true);//the component is made visible
		viewDocument2AdmissionBttn.setVisible(false);//the component is made visible
		viewDocument2AdmissionSpecificBttn.setVisible(true);//the component is made visible
		if(temp.docType.equals("Test Results")==true)//selection determining if the current attribute meets the condition
		{
			admissioncardDescriptionpane2.setText("Summary: "+temp.summary);//formatting component to include text
		}
		else{
			admissioncardDescriptionpane1.setText("");
		}
	}
	public void admissionUpdateCard2(Document temp)
	{
		admissioncardTitlepanel2.setText(temp.docType+" "+ft.format(temp.dateOfDocumentCreation));//formatting component to include text
		admissioncardTitlepanel2.setVisible(true);//the component is made visible
		viewDocument2AdmissionBttn.setVisible(true);//the component is made visible
		viewDocument2AdmissionSpecificBttn.setVisible(false);//the component is made visible
		
		admissioncardDescriptionpane2.setVisible(true);//the component is made visible
		whiteBox5.setVisible(true);//the component is made visible
		if(temp.docType.equals("Test Results")==true)//selection determining if the current attribute meets the condition
		{
			admissioncardDescriptionpane2.setText("Summary: "+temp.summary);//formatting component to include text
		}
		else{
			admissioncardDescriptionpane1.setText("");
		}
	}
	public void admissionUpdateCard3SearchSpecific(Document temp)
	{
		admissioncardTitlepanel3.setText(temp.docType+" "+ft.format(temp.dateOfDocumentCreation));//formatting component to include text
		admissioncardTitlepanel3.setVisible(true);//the component is made visible
		viewDocument3AdmissionBttn.setVisible(false);//the component is made visible
		admissioncardDescriptionpane3.setVisible(true);//the component is made visible
		whiteBox6.setVisible(true);//the component is made visible
		viewDocument3AdmissionSpecificBttn.setVisible(true);//the component is made visible
		if(temp.docType.equals("Test Results")==true)//selection determining if the current attribute meets the condition
		{
			admissioncardDescriptionpane3.setText("Summary: "+temp.summary);//formatting component to include text
		}
		else{
			admissioncardDescriptionpane1.setText("");
		}
	}
	public void admissionUpdateCard3(Document temp)
	{
		admissioncardTitlepanel3.setText(temp.docType+" "+ft.format(temp.dateOfDocumentCreation));//formatting component to include text
		admissioncardTitlepanel3.setVisible(true);//the component is made visible
		viewDocument3AdmissionBttn.setVisible(true);//the component is made visible
		viewDocument3AdmissionSpecificBttn.setVisible(false);//the component is made visible
		admissioncardDescriptionpane3.setVisible(true);//the component is made visible
		whiteBox6.setVisible(true);//the component is made visible
		if(temp.docType.equals("Test Results")==true)//selection determining if the current attribute meets the condition
		{
			admissioncardDescriptionpane3.setText("Summary: "+temp.summary);//formatting component to include text
		}
		else{
			admissioncardDescriptionpane1.setText("");
		}
	}
	public void admissionUpdateCard4(Document temp)
	{
		admissioncardTitlepanel4.setText(temp.docType+" "+ft.format(temp.dateOfDocumentCreation));//formatting component to include text
		admissioncardTitlepanel4.setVisible(true);//the component is made visible
		viewDocument4AdmissionBttn.setVisible(true);//the component is made visible
		viewDocument4AdmissionSpecificBttn.setVisible(false);//the component is made visible
		admissioncardDescriptionpane4.setVisible(true);//the component is made visible
		whiteBox8.setVisible(true);//the component is made visible
		if(temp.docType.equals("Test Results")==true)//selection determining if the current attribute meets the condition
		{
			admissioncardDescriptionpane4.setText("Summary: "+temp.summary);//formatting component to include text
		}
		else{
			admissioncardDescriptionpane1.setText("");
		}
	}
	public void admissionUpdateCard4SearchSpecific(Document temp)
	{
		admissioncardTitlepanel4.setText(temp.docType+" "+ft.format(temp.dateOfDocumentCreation));//formatting component to include text
		admissioncardTitlepanel4.setVisible(true);//the component is made visible
		viewDocument4AdmissionBttn.setVisible(false);//the component is made visible
		admissioncardDescriptionpane4.setVisible(true);//the component is made visible
		whiteBox8.setVisible(true);//the component is made visible
		viewDocument4AdmissionSpecificBttn.setVisible(true);//the component is made visible
		if(temp.docType.equals("Test Results")==true)//selection determining if the current attribute meets the condition
		{
			admissioncardDescriptionpane4.setText("Summary: "+temp.summary);//formatting component to include text
		}
		else{
			admissioncardDescriptionpane1.setText("");
		}
	}
	public void admissionUpdateCard5(Document temp)
	{
		admissioncardTitlepanel5.setText(temp.docType+" "+ft.format(temp.dateOfDocumentCreation));//formatting component to include text
		admissioncardTitlepanel5.setVisible(true);//the component is made visible
		viewDocument5AdmissionBttn.setVisible(true);//the component is made visible
		admissioncardDescriptionpane5.setVisible(true);//the component is made visible
		whiteBox10.setVisible(true);//the component is made visible
		viewDocument5AdmissionSpecificBttn.setVisible(false);//the component is made visible
		if(temp.docType.equals("Test Results")==true)//selection determining if the current attribute meets the condition
		{
			admissioncardDescriptionpane5.setText("Summary: "+temp.summary);//formatting component to include text
		}
		else{
			admissioncardDescriptionpane1.setText("");
		}
	}
	public void admissionUpdateCard5SearchSpecific(Document temp)
	{
		admissioncardTitlepanel5.setText(temp.docType+" "+ft.format(temp.dateOfDocumentCreation));//formatting component to include text
		admissioncardTitlepanel5.setVisible(true);//the component is made visible
		viewDocument5AdmissionBttn.setVisible(false);//the component is made visible
		admissioncardDescriptionpane5.setVisible(true);//the component is made visible
		viewDocument5AdmissionSpecificBttn.setVisible(true);//the component is made visible
		whiteBox10.setVisible(true);//the component is made visible
		if(temp.docType.equals("Test Results")==true)//selection determining if the current attribute meets the condition
		{
			admissioncardDescriptionpane5.setText("Summary: "+temp.summary);//formatting component to include text
		}
		else{
			admissioncardDescriptionpane1.setText("");
		}
	}
	public void admissionUpdateCard6(Document temp)
	{
		admissioncardTitlepanel6.setText(temp.docType+" "+ft.format(temp.dateOfDocumentCreation));//formatting component to include text
		admissioncardTitlepanel6.setVisible(true);//the component is made visible
		viewDocument6AdmissionBttn.setVisible(true);//the component is made visible
		viewDocument6AdmissionSpecificBttn.setVisible(false);//the component is made visible
		
		admissioncardDescriptionpane6.setVisible(true);//the component is made visible
		whiteBox15.setVisible(true);//the component is made visible
		if(temp.docType.equals("Test Results")==true)//selection determining if the current attribute meets the condition
		{
			admissioncardDescriptionpane6.setText("Summary: "+temp.summary);//formatting component to include text
		}
		else{
			admissioncardDescriptionpane1.setText("");
		}
	}
	public void admissionUpdateCard6SearchSpecific(Document temp)
	{
		admissioncardTitlepanel6.setText(temp.docType+" "+ft.format(temp.dateOfDocumentCreation));//formatting component to include text
		admissioncardTitlepanel6.setVisible(true);//the component is made visible
		viewDocument6AdmissionBttn.setVisible(false);//the component is made visible
		viewDocument6AdmissionSpecificBttn.setVisible(true);//the component is made visible
		
		admissioncardDescriptionpane6.setVisible(true);//the component is made visible
		whiteBox15.setVisible(true);//the component is made visible
		if(temp.docType.equals("Test Results")==true)//selection determining if the current attribute meets the condition
		{
			admissioncardDescriptionpane6.setText("Summary: "+temp.summary);//formatting component to include text
		}
		else{
			admissioncardDescriptionpane1.setText("");
		}
	}
	public void admissionhideCard2()
	{
		admissioncardTitlepanel2.setVisible(false);//the component is made invisible
		viewDocument2AdmissionBttn.setVisible(false);//the component is made invisible
		admissioncardDescriptionpane2.setVisible(false);//the component is made invisible
		whiteBox5.setVisible(false);//the component is made invisible
		viewDocument2AdmissionSpecificBttn.setVisible(false);//the component is made visible
	}
	public void admissionhideCard3()
	{
		admissioncardTitlepanel3.setVisible(false);//the component is made invisible
		viewDocument3AdmissionBttn.setVisible(false);//the component is made invisible
		admissioncardDescriptionpane3.setVisible(false);//the component is made invisible
		whiteBox6.setVisible(false);//the component is made invisible
		viewDocument3AdmissionSpecificBttn.setVisible(false);//the component is made visible
	}
	public void admissionhideCard4()
	{
		admissioncardTitlepanel4.setVisible(false);//the component is made invisible
		viewDocument4AdmissionBttn.setVisible(false);//the component is made invisible
		admissioncardDescriptionpane4.setVisible(false);//the component is made invisible
		whiteBox8.setVisible(false);//the component is made invisible
		viewDocument4AdmissionSpecificBttn.setVisible(false);//the component is made visible
	}
	public void admissionhideCard5()
	{
		admissioncardTitlepanel5.setVisible(false);//the component is made invisible
		viewDocument5AdmissionBttn.setVisible(false);//the component is made invisible
		admissioncardDescriptionpane5.setVisible(false);//the component is made invisible
		whiteBox10.setVisible(false);//the component is made invisible
		viewDocument5AdmissionSpecificBttn.setVisible(false);//the component is made visible
	}
	public void admissionhideCard6()
	{
		admissioncardTitlepanel6.setVisible(false);//the component is made invisible
		viewDocument6AdmissionBttn.setVisible(false);//the component is made invisible
		admissioncardDescriptionpane6.setVisible(false);//the component is made invisible
		whiteBox15.setVisible(false);//the component is made invisible
		viewDocument6AdmissionSpecificBttn.setVisible(false);//the component is made visible
	}
	public void admissionhideCard1()
	{
		admissioncardTitlepanel1.setVisible(false);//the component is made invisible
		viewDocument1AdmissionBttn.setVisible(false);//the component is made invisible
		admissioncardDescriptionpane1.setVisible(false);//the component is made invisible
						viewDocument1AdmissionSpecificBttn.setVisible(false);//the component is made visible
						viewDocument1AdmissionSearchBttn.setVisible(false);//the component is made visible

		whiteBox4.setVisible(false);//the component is made invisible
	}
	public void createMainPartAdmissionHomePageGeneral()
	{
		
		clearSearchbttnAdmission.setText("X");//formatting component to include text
		clearSearchbttnAdmission.setEnabled(false);//the component is disabled
		clearSearchbttnAdmission.setSize(45,35);//size of component is set
		clearSearchbttnAdmission.setLocation(960,115);//the location of the component is set to the desired part of the panel
		clearSearchbttnAdmission.setBackground(darkGreyColour);//the component has its background set to a desireable colour
		clearSearchbttnAdmission.setFont(textfont);//the component has had its font set to a design with the correct size for its purpose
		clearSearchbttnAdmission.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		clearSearchbttnAdmission.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
			
		searchLibraryBttnAdmission.setSize(130,35);//size of component is set
		searchLibraryBttnAdmission.setLocation(1205,115);//the location of the component is set to the desired part of the panel
		searchLibraryBttnAdmission.setText("Search");//formatting component to include text
		searchLibraryBttnAdmission.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		searchLibraryBttnAdmission.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		searchLibraryBttnAdmission.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		searchLibraryBttnAdmission.addActionListener(this);
		
		
		searchBoxAdmissionHpagePanel.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		searchBoxAdmissionHpagePanel.setSize(200,35);//size of component is set
		searchBoxAdmissionHpagePanel.setLocation(1005,115);//the location of the component is set to the desired part of the panel
		searchBoxAdmissionHpagePanel.setOpaque(true);//the component is set to opaque
		searchBoxAdmissionHpagePanel.setForeground(lightGreyColor);//the component has its font colour changed to a desireable one
		searchBoxAdmissionHpagePanel.setText("  Search:");//formatting component to include text
		searchBoxAdmissionHpagePanel.addFocusListener(new FocusListener()//a focus listner is attached to the component on its own and will look out for any interactions that occur 
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(searchBoxAdmissionHpagePanel.getText().equals("  Search:"))//it will then use selection to see if the field contains the text prompt indicating the user hasnt edited it yet
				{
					searchBoxAdmissionHpagePanel.setText("");//the field is then cleared to allow the user to enter their desired text
					searchBoxAdmissionHpagePanel.setForeground(new Color(1));//the component has its font colour changed to a desireable one
				}
			}
			public void focusLost(FocusEvent e)//when the focus is lost on the component the method is ran
			{
				if(searchBoxAdmissionHpagePanel.getText().equals(""))//selectio occurs checking seeing when foucs has been lost the user has entered nothing 
				{
				searchBoxAdmissionHpagePanel.setText("  Search:");//if this is true then the orinigal text prompt is added back 
				searchBoxAdmissionHpagePanel.setForeground(lightGreyColor);//the component has its font colour changed to a desireable one
				}
			}
			
		});
		
		sortBoxAdmissionHpagePanel.setSize(150,30);//size of component is set
		sortBoxAdmissionHpagePanel.setLocation(450,117);//the location of the component is set to the desired part of the panel
		sortBoxAdmissionHpagePanel.setEnabled(true);//the component is enabled so it can be used
		searchBoxAdmissionHpagePanel.setBackground(new Color(-1));//the component has its background set to a desireable colour
		
		sortBoxAdmissionHpagePanel.addItemListener(new ItemListener()//component receives an action listener
		{
			public void itemStateChanged(ItemEvent ie)//selection determining if the item in the combo box changed
			{
				if(sortBoxAdmissionHpagePanel.getSelectedItem()=="Alphabetical")//selection determining if sort order is this
				{
					
					singleDefinition = sortlistofdocumentsArray(0,listOfdocumentsGUI);//sorts the lsit to the corrosponding order
					
					outputDocumentCards(listOfdocumentsGUI);//reformats the list for the gui as the order has changed
				}
				if(sortBoxAdmissionHpagePanel.getSelectedItem()=="Z-A")//selection determining if sort order is this
				{
					singleDefinition = sortlistofdocumentsArray(1,listOfdocumentsGUI);//sorts the lsit to the corrosponding order
					
					outputDocumentCards(listOfdocumentsGUI);//reformats the list for the gui as the order has changed
					
				}
				if(sortBoxAdmissionHpagePanel.getSelectedItem()=="Oldest First")//selection determining if sort order is this
				{
					singleDefinition = sortlistofdocumentsArray(2,listOfdocumentsGUI);//sorts the lsit to the corrosponding order
					
					outputDocumentCards(listOfdocumentsGUI);//reformats the list for the gui as the order has changed
					
				}
				if(sortBoxAdmissionHpagePanel.getSelectedItem()=="Newest First")//selection determining if sort order is this
				{
					singleDefinition = sortlistofdocumentsArray(3,listOfdocumentsGUI);//sorts the lsit to the corrosponding order
					
					outputDocumentCards(listOfdocumentsGUI);//reformats the list for the gui as the order has changed
					
				}
            }
        });
		
		
		sortBoxAdmissionHpagePanel.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		sortBoxAdmissionHpagePanel.setBackground(new Color(-1));//the component has its background set to a desireable colour
		sortBoxAdmissionHpagePanel.setSize(150,30);//size of component is set
		sortBoxAdmissionHpagePanel.setLocation(450,117);//the location of the component is set to the desired part of the panel
		sortBoxAdmissionHpagePanel.setOpaque(true);//the component is set to opaque
	
		admissioncardTitlepanel1.setSize(240,60);//size of component is set
		admissioncardTitlepanel1.setLocation(465,175);//the location of the component is set to the desired part of the panel
		admissioncardTitlepanel1.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		admissioncardTitlepanel2.setSize(240,60);//size of component is set
		admissioncardTitlepanel2.setLocation(765,175);//the location of the component is set to the desired part of the panel
		admissioncardTitlepanel2.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		admissioncardTitlepanel3.setSize(240,60);//size of component is set
		admissioncardTitlepanel3.setLocation(1065,175);//the location of the component is set to the desired part of the panel
		admissioncardTitlepanel3.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		admissioncardTitlepanel4.setSize(240,60);//size of component is set
		admissioncardTitlepanel4.setLocation(465,510);//the location of the component is set to the desired part of the panel
		admissioncardTitlepanel4.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		admissioncardTitlepanel5.setSize(240,60);//size of component is set
		admissioncardTitlepanel5.setLocation(765,510);//the location of the component is set to the desired part of the panel
		admissioncardTitlepanel5.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		admissioncardTitlepanel6.setSize(240,60);//size of component is set
		admissioncardTitlepanel6.setLocation(1065,510);//the location of the component is set to the desired part of the panel
		admissioncardTitlepanel6.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		admissioncardDescriptionpane1.setSize(200,200);//size of component is set
		admissioncardDescriptionpane1.setLocation(485,240);//the location of the component is set to the desired part of the panel
		admissioncardDescriptionpane1.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		admissioncardDescriptionpane1.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components

		admissioncardDescriptionpane2.setSize(200,200);//size of component is set
		admissioncardDescriptionpane2.setLocation(785,240);//the location of the component is set to the desired part of the panel
		admissioncardDescriptionpane2.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		admissioncardDescriptionpane2.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 

		admissioncardDescriptionpane3.setSize(200,200);//size of component is set
		admissioncardDescriptionpane3.setLocation(1085,240);//the location of the component is set to the desired part of the panel
		admissioncardDescriptionpane3.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		admissioncardDescriptionpane3.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 

		admissioncardDescriptionpane4.setSize(200,200);//size of component is set
		admissioncardDescriptionpane4.setLocation(485,575);//the location of the component is set to the desired part of the panel
		admissioncardDescriptionpane4.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		admissioncardDescriptionpane4.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 

		admissioncardDescriptionpane5.setSize(200,200);//size of component is set
		admissioncardDescriptionpane5.setLocation(785,575);//the location of the component is set to the desired part of the panel
		admissioncardDescriptionpane5.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		admissioncardDescriptionpane5.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 

		admissioncardDescriptionpane6.setSize(200,200);//size of component is set
		admissioncardDescriptionpane6.setLocation(1085,575);//the location of the component is set to the desired part of the panel
		admissioncardDescriptionpane6.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		admissioncardDescriptionpane6.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 		
	
		viewDocument1AdmissionBttn.setSize(210,30);//size of component is set
		viewDocument1AdmissionBttn.setLocation(480,445);//the location of the component is set to the desired part of the panel
		viewDocument1AdmissionBttn.setText("View Document");//formatting component to include text
		viewDocument1AdmissionBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		viewDocument1AdmissionBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		viewDocument1AdmissionBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		viewDocument1AdmissionBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		viewDocument1AdmissionSpecificBttn.setSize(210,30);//size of component is set
		viewDocument1AdmissionSpecificBttn.setLocation(480,445);//the location of the component is set to the desired part of the panel
		viewDocument1AdmissionSpecificBttn.setText("View Document");//formatting component to include text
		viewDocument1AdmissionSpecificBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		viewDocument1AdmissionSpecificBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		viewDocument1AdmissionSpecificBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		viewDocument1AdmissionSpecificBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		viewDocument1AdmissionSearchBttn.setSize(210,30);//size of component is set
		viewDocument1AdmissionSearchBttn.setLocation(480,445);//the location of the component is set to the desired part of the panel
		viewDocument1AdmissionSearchBttn.setText("View Document");//formatting component to include text
		viewDocument1AdmissionSearchBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		viewDocument1AdmissionSearchBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		viewDocument1AdmissionSearchBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		viewDocument1AdmissionSearchBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		
		viewDocument2AdmissionBttn.setSize(210,30);//size of component is set
		viewDocument2AdmissionBttn.setLocation(780,445);//the location of the component is set to the desired part of the panel
		viewDocument2AdmissionBttn.setText("View Document");//formatting component to include text
		viewDocument2AdmissionBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		viewDocument2AdmissionBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		viewDocument2AdmissionBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		viewDocument2AdmissionBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
			
		viewDocument2AdmissionSpecificBttn.setSize(210,30);//size of component is set
		viewDocument2AdmissionSpecificBttn.setLocation(780,445);//the location of the component is set to the desired part of the panel
		viewDocument2AdmissionSpecificBttn.setText("View Document");//formatting component to include text
		viewDocument2AdmissionSpecificBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		viewDocument2AdmissionSpecificBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		viewDocument2AdmissionSpecificBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		viewDocument2AdmissionSpecificBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		viewDocument3AdmissionBttn.setSize(210,30);//size of component is set
		viewDocument3AdmissionBttn.setLocation(1080,445);//the location of the component is set to the desired part of the panel
		viewDocument3AdmissionBttn.setText("View Document");//formatting component to include text
		viewDocument3AdmissionBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		viewDocument3AdmissionBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		viewDocument3AdmissionBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		viewDocument3AdmissionBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		viewDocument3AdmissionSpecificBttn.setSize(210,30);//size of component is set
		viewDocument3AdmissionSpecificBttn.setLocation(1080,445);//the location of the component is set to the desired part of the panel
		viewDocument3AdmissionSpecificBttn.setText("View Document");//formatting component to include text
		viewDocument3AdmissionSpecificBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		viewDocument3AdmissionSpecificBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		viewDocument3AdmissionSpecificBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		viewDocument3AdmissionSpecificBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		viewDocument4AdmissionBttn.setSize(210,30);//size of component is set
		viewDocument4AdmissionBttn.setLocation(480,780);//the location of the component is set to the desired part of the panel
		viewDocument4AdmissionBttn.setText("View Document");//formatting component to include text
		viewDocument4AdmissionBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		viewDocument4AdmissionBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		viewDocument4AdmissionBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		viewDocument4AdmissionBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		viewDocument4AdmissionSpecificBttn.setSize(210,30);//size of component is set
		viewDocument4AdmissionSpecificBttn.setLocation(480,780);//the location of the component is set to the desired part of the panel
		viewDocument4AdmissionSpecificBttn.setText("View Document");//formatting component to include text
		viewDocument4AdmissionSpecificBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		viewDocument4AdmissionSpecificBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		viewDocument4AdmissionSpecificBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		viewDocument4AdmissionSpecificBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		viewDocument5AdmissionBttn.setSize(210,30);//size of component is set
		viewDocument5AdmissionBttn.setLocation(780,780);//the location of the component is set to the desired part of the panel
		viewDocument5AdmissionBttn.setText("View Document");//formatting component to include text
		viewDocument5AdmissionBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		viewDocument5AdmissionBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		viewDocument5AdmissionBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		viewDocument5AdmissionBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		viewDocument5AdmissionSpecificBttn.setSize(210,30);//size of component is set
		viewDocument5AdmissionSpecificBttn.setLocation(780,780);//the location of the component is set to the desired part of the panel
		viewDocument5AdmissionSpecificBttn.setText("View Document");//formatting component to include text
		viewDocument5AdmissionSpecificBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		viewDocument5AdmissionSpecificBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		viewDocument5AdmissionSpecificBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		viewDocument5AdmissionSpecificBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		viewDocument6AdmissionBttn.setSize(210,30);//size of component is set
		viewDocument6AdmissionBttn.setLocation(1080,780);//the location of the component is set to the desired part of the panel
		viewDocument6AdmissionBttn.setText("View Document");//formatting component to include text
		viewDocument6AdmissionBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		viewDocument6AdmissionBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		viewDocument6AdmissionBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		viewDocument6AdmissionBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		viewDocument6AdmissionSpecificBttn.setSize(210,30);//size of component is set
		viewDocument6AdmissionSpecificBttn.setLocation(1080,780);//the location of the component is set to the desired part of the panel
		viewDocument6AdmissionSpecificBttn.setText("View Document");//formatting component to include text
		viewDocument6AdmissionSpecificBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		viewDocument6AdmissionSpecificBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		viewDocument6AdmissionSpecificBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		viewDocument6AdmissionSpecificBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		admissionCardPageForwardBttn.setSize(60,60);//size of component is set
		admissionCardPageForwardBttn.setLocation(1310,443);//the location of the component is set to the desired part of the panel
		admissionCardPageForwardBttn.setText(">");//formatting component to include text
		admissionCardPageForwardBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		admissionCardPageForwardBttn.setBackground(darkGreyColour);//the component has its background set to a desireable colour
		admissionCardPageForwardBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		admissionCardPageForwardBttn.setFont(whiteLoginFont);//the component has had its font set to a design with the correct size for its purpose
		
		admissionCardPageBackBttn.setSize(60,60);//size of component is set
		admissionCardPageBackBttn.setLocation(400,443);//the location of the component is set to the desired part of the panel
		admissionCardPageBackBttn.setText("<");//formatting component to include text
		admissionCardPageBackBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		admissionCardPageBackBttn.setBackground(darkGreyColour);//the component has its background set to a desireable colour
		admissionCardPageBackBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		admissionCardPageBackBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		
		
	}
	public void outputDocumentCards(Admission currentAdmission)
	{
		admissionhideCard1();//calls method which hides the cards
		admissionhideCard2();//calls method which hides the cards
		admissionhideCard3();//calls method which hides the cards
		admissionhideCard4();//calls method which hides the cards
		admissionhideCard5();//calls method which hides the cards
		admissionhideCard6();//calls method which hides the cards
		admissionCardPageForwardBttn.setEnabled(false);//the component is disabled
		admissionCardPageBackBttn.setEnabled(false);//the component is disabled
	
		
		adminHomepagePanel.setVisible(false);//the component is made invisible
		adminHomepagePanel.setVisible(true);//the component is made visible
		if(currentAdmission.numberOfDocuments>0)
			{
				searchLibraryBttnAdmission.setEnabled(true);
			}
			if(currentAdmission.numberOfDocuments<=0)
			{
				searchLibraryBttnAdmission.setEnabled(false);
			}
		if(currentPatient.numberOfAdmissions>0)
		{
			if(currentAdmission.numberOfDocuments!=0)
			{
			admissionCardPageForwardBttn.setEnabled(true);//the component is enabled to interact with the user
			admissionCardPageBackBttn.setEnabled(true);//the component is enabled to interact with the user
			int numOfTotalPages = (numberOfDocuments / 6);//finds the number of pages with at least 6 cards per page
			int numOfLeftOverDocs = (numberOfDocuments % 6);//finds the number of leftover cards after the full pages have been found
			if(currentPage>numOfTotalPages)//selection determining if the inital page has been found
			{
				admissionCardPageForwardBttn.setEnabled(false);//the component is disabled
			}
			if(currentPage==1)//selection determining if the first page is found
			{
				admissionCardPageBackBttn.setEnabled(false);//the component is disabled
			}
			if(numberOfDocuments>0)//selection determining if there at least one document
			{
				
				if(currentPage<=numOfTotalPages)//selection determining if there are at least 6 cards
				{
					admissionUpdateCard1(listOfdocumentsGUI[0+currentPageIndex]);//calls the method that updates the defnition with the new definition 
					admissionUpdateCard2(listOfdocumentsGUI[1+currentPageIndex]);//calls the method that updates the defnition with the new definition
					admissionUpdateCard3(listOfdocumentsGUI[2+currentPageIndex]);//calls the method that updates the defnition with the new definition
					admissionUpdateCard4(listOfdocumentsGUI[3+currentPageIndex]);//calls the method that updates the defnition with the new definition
					admissionUpdateCard5(listOfdocumentsGUI[4+currentPageIndex]);//calls the method that updates the defnition with the new definition
					admissionUpdateCard6(listOfdocumentsGUI[5+currentPageIndex]);//calls the method that updates the defnition with the new definition
				}
				else if(currentPage>=numOfTotalPages)//selection determining if there are any leftover cards left
				{	
					if(numOfLeftOverDocs>0)//selection determining if there are any leftover cards left
					{
						if(numOfLeftOverDocs==1)//selection determining if there is 1 card left
						{
							admissionUpdateCard1(listOfdocumentsGUI[0+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionhideCard2();//calls the method which hides the card
							admissionhideCard3();//calls the method which hides the card
							admissionhideCard4();//calls the method which hides the card
							admissionhideCard5();//calls the method which hides the card
						}
						else if(numOfLeftOverDocs==2)//selection determining if there are 2 cards left
						{
							admissionUpdateCard1(listOfdocumentsGUI[0+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard2(listOfdocumentsGUI[1+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionhideCard3();//calls the method which hides the card
							admissionhideCard4();//calls the method which hides the card
							admissionhideCard5();//calls the method which hides the card
							admissionhideCard6();//calls the method which hides the card
							
						}
						else if(numOfLeftOverDocs==3)//selection determining if there are 2 cards left
						{
							admissionUpdateCard1(listOfdocumentsGUI[0+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard2(listOfdocumentsGUI[1+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard3(listOfdocumentsGUI[2+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionhideCard4();//calls the method which hides the card
							admissionhideCard5();//calls the method which hides the card
							admissionhideCard6();//calls the method which hides the card
						}
						else if(numOfLeftOverDocs==4)//selection determining if there are 2 cards left
						{
							admissionUpdateCard1(listOfdocumentsGUI[0+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard2(listOfdocumentsGUI[1+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard3(listOfdocumentsGUI[2+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard4(listOfdocumentsGUI[3+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionhideCard5();//calls the method which hides the card
							admissionhideCard6();//calls the method which hides the card
						}
						else if(numOfLeftOverDocs==5)//selection determining if there are 2 cards left
						{
							admissionUpdateCard1(listOfdocumentsGUI[0+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard2(listOfdocumentsGUI[1+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard3(listOfdocumentsGUI[2+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard4(listOfdocumentsGUI[3+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard5(listOfdocumentsGUI[4+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionhideCard6();//calls the method which hides the card
						}
					}
				}
				
			}
		}
		}
	}
	public void outputDocumentCards(Document[] array)
	{
		currentPage = 1;//the current page is set to 1
		currentPageIndex = 0;//the Current page index is set to 0
		admissionhideCard1();//calls method which hides the cards
		admissionhideCard2();//calls method which hides the cards
		admissionhideCard3();//calls method which hides the cards
		admissionhideCard4();//calls method which hides the cards
		admissionhideCard5();//calls method which hides the cards
		admissionhideCard6();//calls method which hides the cards
		admissionCardPageForwardBttn.setEnabled(false);//the component is disabled
		admissionCardPageBackBttn.setEnabled(false);//the component is disabled
		int arrayLength = array.length;//finds the length of the array
		if(currentAdmission.numberOfDocuments>0)//selection determining if that the current admission has more than one admission
			{
				searchLibraryBttnAdmission.setEnabled(true);//enables the button to be used
			}
			if(currentAdmission.numberOfDocuments<=0)//selection determining if that the current admission has no admissions
			{
				searchLibraryBttnAdmission.setEnabled(false);//disables the button from being accessed 
			}
		adminHomepagePanel.setVisible(false);//the component is made invisible
		adminHomepagePanel.setVisible(true);//the component is made visible
		if(arrayLength!=0)
		{
			admissionCardPageForwardBttn.setEnabled(true);//the component is enabled to interact with the user
			admissionCardPageBackBttn.setEnabled(true);//the component is enabled to interact with the user
			int numOfTotalPages = (arrayLength / 6);//finds the number of pages with at least 6 cards per page
			int numOfLeftOverDocs = (arrayLength % 6);//finds the number of leftover cards after the full pages have been found
			if(currentPage>numOfTotalPages)//selection determining if the inital page has been found
			{
				admissionCardPageForwardBttn.setEnabled(false);//the component is disabled
			}
			if(currentPage==1)//selection determining if the first page is found
			{
				admissionCardPageBackBttn.setEnabled(false);//the component is disabled
			}
			if(arrayLength>0)//selection determining if there at least one document
			{
				
				if(currentPage<=numOfTotalPages)//selection determining if there are at least 6 cards
				{
					admissionUpdateCard1SearchSpecific(array[0+currentPageIndex]);//calls the method that updates the defnition with the new definition 
					admissionUpdateCard2SearchSpecific(array[1+currentPageIndex]);//calls the method that updates the defnition with the new definition
					admissionUpdateCard3SearchSpecific(array[2+currentPageIndex]);//calls the method that updates the defnition with the new definition
					admissionUpdateCard4SearchSpecific(array[3+currentPageIndex]);//calls the method that updates the defnition with the new definition
					admissionUpdateCard5SearchSpecific(array[4+currentPageIndex]);//calls the method that updates the defnition with the new definition
					admissionUpdateCard6SearchSpecific(array[5+currentPageIndex]);//calls the method that updates the defnition with the new definition
				}
				else if(currentPage>=numOfTotalPages)//selection determining if there are any leftover cards left
				{	
					if(numOfLeftOverDocs>0)//selection determining if there are any leftover cards left
					{
						if(numOfLeftOverDocs==1)//selection determining if there is 1 card left
						{
							admissionUpdateCard1SearchSpecific(array[0+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionhideCard2();//calls the method which hides the card
							admissionhideCard3();//calls the method which hides the card
							admissionhideCard4();//calls the method which hides the card
							admissionhideCard5();//calls the method which hides the card
						}
						else if(numOfLeftOverDocs==2)//selection determining if there are 2 cards left
						{
							admissionUpdateCard1SearchSpecific(array[0+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard2SearchSpecific(array[1+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionhideCard3();//calls the method which hides the card
							admissionhideCard4();//calls the method which hides the card
							admissionhideCard5();//calls the method which hides the card
							admissionhideCard6();//calls the method which hides the card
							
						}
						else if(numOfLeftOverDocs==3)//selection determining if there are 2 cards left
						{
							admissionUpdateCard1SearchSpecific(array[0+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard2SearchSpecific(array[1+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard3SearchSpecific(array[2+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionhideCard4();//calls the method which hides the card
							admissionhideCard5();//calls the method which hides the card
							admissionhideCard6();//calls the method which hides the card
						}
						else if(numOfLeftOverDocs==4)//selection determining if there are 2 cards left
						{
							admissionUpdateCard1SearchSpecific(array[0+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard2SearchSpecific(array[1+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard3SearchSpecific(array[2+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard4SearchSpecific(array[3+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionhideCard5();//calls the method which hides the card
							admissionhideCard6();//calls the method which hides the card
						}
						else if(numOfLeftOverDocs==5)//selection determining if there are 2 cards left
						{
							admissionUpdateCard1SearchSpecific(array[0+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard2SearchSpecific(array[1+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard3SearchSpecific(array[2+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard4SearchSpecific(array[3+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionUpdateCard5SearchSpecific(array[4+currentPageIndex]);//calls the method that updates the defnition with the new definition
							admissionhideCard6();//calls the method which hides the card
						}
					}
				}
				
			}
		}
	}
		
	public int searchAlgorithmAdmissionPage(String desiredWord,Document[] array)
		{
		int position = -1;//return value is always negative incase no indexc was found
		int length = array.length-1;//finds length of array
		int startPoint = 0;//startpoint is set to first index
		int endPoint = length;//endpoint is the last index
		boolean found = false;//declares attribute to be false as it might not be found
		int midPoint;//initalises the midpoint 
		String currentID;//initialises the id of the curent value
		do
		{	
		//System.out.println("End "+endPoint);
			midPoint = ((startPoint+endPoint)/2);//finds midpoint of array
			//System.out.println("mid "+midPoint);
			String currentDefinition = array[midPoint].documentID;//finds value at midpoint
			System.out.println(currentDefinition);
			if(desiredWord.compareToIgnoreCase(currentDefinition)==0)//selection determining if the two values are the same
			{
				System.out.println("Found");
				found = true;//updates attribute state
				position = midPoint;//sets positition of found point
			}	
			else if(desiredWord.compareToIgnoreCase(currentDefinition)<0)//selection determining ifthe desired value is before the midpoint
			{
			//	System.out.println("not above midpoint");
				endPoint = midPoint -1;//correctly updates the new start value respective to the value retruned from the comparrison
			}
			else if(desiredWord.compareToIgnoreCase(currentDefinition)>0)//selection determining ifthe desired value is before the midpoint
			{
			
			//	System.out.println("not below midpoint");
				startPoint = midPoint +1;//correctly updates the new start value respective to the value retruned from the comparrison
			}
		}
		while((found!=true)&&(endPoint>=startPoint));//temination condtion for the loop until the srat value is larger than the end value or that the item is found
		return(position);//returns the position of the idex the value is located at
		}
	public Document[] sortlistofdocumentsArray(int order, Document[] array)
		{
			Document[] newOrderedArray = new Document[1];//declares the array the new values will be stored in
			int sizeOfArray = array.length;//finds length of array
			if(order ==0)//selection determining if they want it in ascending order
			{
				for (int i = 0; i < sizeOfArray; i++) //for loop that will iterate through every value
				{
					for (int j = i + 1; j < sizeOfArray; j++) //for loop that will iterate through every value
					{
						if(array[i].docType.compareTo(array[j].docType)>0) //selection determining if the value goes after the current index
						{
							newOrderedArray[0] = array[i];//temp value to store array
							array[i] = array[j];//swap occurs
							array[j] = newOrderedArray[0];//temp value is then inserted back into correct spot
						}
					}
				}
				for (int i = 0; i < sizeOfArray; i++) //for loop that will iterate through every value
				{
					System.out.print(array[i].documentID);
				}
			}
			else if(order ==1)//selection determining if they want it in descending order
			{
				for (int i = 0; i < sizeOfArray; i++)  //for loop that will iterate through every value
				{
					for (int j = i + 1; j < sizeOfArray; j++)  //for loop that will iterate through every value
					{
						if(array[i].documentID.compareTo(array[j].documentID)<0) //selection determining if the value goes before the current index
						{
							newOrderedArray[0] = array[i];//temp value to store array
							array[i] = array[j];//swap occurs
							array[j] = newOrderedArray[0];//temp value is then inserted back into correct spot
						}
					}
				}
			}
			
			if(order ==2)//selection determining if they want it in ascending order
			{
				for (int i = 0; i < sizeOfArray; i++) //for loop that will iterate through every value
				{
					for (int j = i + 1; j < sizeOfArray; j++) //for loop that will iterate through every value
					{
						if(array[i].dateOfDocumentCreation.after(array[j].dateOfDocumentCreation)==true) //selection determining if the value goes after the current index
						{
							newOrderedArray[0] = array[i];//temp value to store array
							array[i] = array[j];//swap occurs
							array[j] = newOrderedArray[0];//temp value is then inserted back into correct spot
						}
					}
				}
				for (int i = 0; i < sizeOfArray; i++) //for loop that will iterate through every value
				{
					System.out.print(array[i].documentID);
				}
			}
			else if(order ==3)//selection determining if they want it in descending order
			{
				for (int i = 0; i < sizeOfArray; i++)  //for loop that will iterate through every value
				{
					for (int j = i + 1; j < sizeOfArray; j++)  //for loop that will iterate through every value
					{
						if(array[i].dateOfDocumentCreation.before(array[j].dateOfDocumentCreation)==true) //selection determining if the value goes before the current index
						{
							newOrderedArray[0] = array[i];//temp value to store array
							array[i] = array[j];//swap occurs
							array[j] = newOrderedArray[0];//temp value is then inserted back into correct spot
						}
					}
				}
				for (int i = 0; i < sizeOfArray; i++) //for loop that will iterate through every value
				{
					System.out.print(array[i].documentID);
				}
			}
			System.out.println("=========================");
			for (int i = 0; i < sizeOfArray; i++) //for loop that will iterate through every value
				{
					System.out.print(array[i].documentID);
				}
			return(array);//new list is returned
		}
	//new Admission
	
	public void createNewAdmissionPanel(JPanel panel)
	{
		if(loaded[3][1] ==false)//selection determines whether the panel is yet to be loaded
		{
			createNewAdmissionPanelGeneral();//the components of the panel are cereated by running the method this is done initially so it is only done once and will be used throughout
			loaded[3][1] = true;//the variable is set as true to prevent the components from being reran
		}
		addPanel(newAdmissionPanel);
		createTopbar(newAdmissionPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		setNonActiveTopPanel();//sets the top bar to have all buttons to be enabled
		newAdmissionPanel.setVisible(true);//the panel is declared to be visible showing the correct componets 
		whiteBox1.setSize(347,170);//the components size is correctly declared
		whiteBox1.setLocation(96,690); //sets the location of the component 
		whiteBox3.setLocation(540,470); //sets the location of the component 
		whiteBox2.setLocation(540,200); //sets the location of the component 
		whiteBox3.setSize(400,395);//the components size is correctly declared
		whiteBox2.setSize(400,220);//the components size is correctly declared
	
		newAdmissionPanel.add(symptomHeaderlbl);//the component is added to the panel
		newAdmissionPanel.add(titleLowerBlackLine);//the component is added to the panel
		newAdmissionPanel.add(titleUpperBlackLine);//the component is added to the panel
		newAdmissionPanel.add(symptomsTfCbLBL);//the component is added to the panel
		newAdmissionPanel.add(symptomhelp);//the component is added to the panel
		newAdmissionPanel.add(symptomsCbLBL);//the component is added to the panel
		newAdmissionPanel.add(typepainCbLBL);//the component is added to the panel
		newAdmissionPanel.add(painCbLBL);//the component is added to the panel
		newAdmissionPanel.add(humanImage);//the component is added to the panel
		newAdmissionPanel.add(cbLeg);//the component is added to the panel
		newAdmissionPanel.add(cbPelvis);//the component is added to the panel
		newAdmissionPanel.add(cbArm);//the component is added to the panel
		newAdmissionPanel.add(cbAbdomen);//the component is added to the panel
		newAdmissionPanel.add(cbBack);//the component is added to the panel
		newAdmissionPanel.add(cbHead);//the component is added to the panel
		newAdmissionPanel.add(cbFoot);//the component is added to the panel
		newAdmissionPanel.add(cbHand);//the component is added to the panel
		newAdmissionPanel.add(cbChest);//the component is added to the panel
		newAdmissionPanel.add(cbNeck);//the component is added to the panel
		newAdmissionPanel.add(SymptomConfirmBttn);//the component is added to the panel
		newAdmissionPanel.add(backToAdmissionbttn);//the component is added to the panel
		newAdmissionPanel.add(symptom1TF);//the component is added to the panel
		newAdmissionPanel.add(symptom2TF);//the component is added to the panel
		newAdmissionPanel.add(symptom3TF);//the component is added to the panel
		newAdmissionPanel.add(symptom4TF);//the component is added to the panel
		newAdmissionPanel.add(cbFatigue);//the component is added to the panel
		newAdmissionPanel.add(cbNausea);//the component is added to the panel
		newAdmissionPanel.add(cbFever);//the component is added to the panel
		newAdmissionPanel.add(cbWeightLoss);//the component is added to the panel
		newAdmissionPanel.add(cbFrequentRecurringPains);//the component is added to the panel
		newAdmissionPanel.add(cbStiffnessInMuscle);//the component is added to the panel
		newAdmissionPanel.add(cbAcutePains);//the component is added to the panel
		newAdmissionPanel.add(cbChronicPains);//the component is added to the panel
		newAdmissionPanel.add(whiteBox1);
		newAdmissionPanel.add(whiteBox3);//the component is added to the panel
		newAdmissionPanel.add(whiteBox2);//the component is added to the panel
	}
		public void createNewAdmissionPanelGeneral()
		{
		
				
		backToAdmissionbttn.setLocation(96,63);//sets the location of the component 
		backToAdmissionbttn.setSize(200,49);//the components size is correctly declared
		backToAdmissionbttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		backToAdmissionbttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		backToAdmissionbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		backToAdmissionbttn.setText("Go Back");//the button is geven text to add meaning to the label
		
		
		SymptomConfirmBttn.setLocation(1050,750);//sets the location of the component 
		SymptomConfirmBttn.setSize(250,50);//the components size is correctly declared
		SymptomConfirmBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		SymptomConfirmBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		SymptomConfirmBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		SymptomConfirmBttn.setText("Request Admission");//the button is geven text to add meaning to the label

		cbNeck.setLocation(177,237);//sets the location of the component 
		cbNeck.setSize(60,30);//the components size is correctly declared
		cbNeck.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		cbNeck.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		
		cbChest.setLocation(142,281);//sets the location of the component 
		cbChest.setSize(60,30);//the components size is correctly declared
		cbChest.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbChest.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		
		cbHand.setLocation(113,427);//sets the location of the component 
		cbHand.setSize(55,23);//the components size is correctly declared
		cbHand.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbHand.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		
		cbFoot.setLocation(180,570);//sets the location of the component 
		cbFoot.setSize(55,23);//the components size is correctly declared
		cbFoot.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		
		cbFoot.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		
		cbHead.setLocation(300,225);//sets the location of the component 
		cbHead.setSize(55,23);//the components size is correctly declared
		cbHead.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbHead.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		
		cbBack.setLocation(315,255);//sets the location of the component 
		cbBack.setSize(55,23);//the components size is correctly declared
		cbBack.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbBack.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		
		cbAbdomen.setLocation(335,340);//sets the location of the component 
		cbAbdomen.setSize(100,30);//the components size is correctly declared
		cbAbdomen.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbAbdomen.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		
		cbArm.setLocation(325,292);//sets the location of the component 
		cbArm.setSize(50,23);//the components size is correctly declared
		cbArm.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		
		cbArm.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		
		cbPelvis.setLocation(300,455);//sets the location of the component 
		cbPelvis.setSize(60,30);//the components size is correctly declared
		cbPelvis.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		
		cbPelvis.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		
		cbLeg.setLocation(295,515);//sets the location of the component 
		cbLeg.setSize(50,23);//the components size is correctly declared
		cbLeg.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		
		cbLeg.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		
		humanImage.setIcon( new ImageIcon("human1.jpg") );//the label is set to an image
		humanImage.setSize(347,414); //the components size is correctly declared
		humanImage.setLocation(96,204); //sets the location of the component 
		humanImage.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		
		painCbLBL.setFont(textfont);//the font that has been declared is attached to the component
		painCbLBL.setText("Please select the areas where the pain resonates from");//the button is geven text to add meaning to the label
		painCbLBL.setSize(347,20); //the components size is correctly declared
		painCbLBL.setLocation(96,180);//sets the location of the component 
		painCbLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		
		typepainCbLBL.setFont(textfont);//the font that has been declared is attached to the component
		typepainCbLBL.setText("Please select the type of pain in the selected areas");//the button is geven text to add meaning to the label
		typepainCbLBL.setSize(347,20); //the components size is correctly declared
		typepainCbLBL.setLocation(96,670);//sets the location of the component 
		typepainCbLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
	
		symptomsCbLBL.setFont(textfont);//the font that has been declared is attached to the component
		symptomsCbLBL.setText("Please select any symptoms that you are experiencing ");//the button is geven text to add meaning to the label
		symptomsCbLBL.setSize(400,20); //the components size is correctly declared
		symptomsCbLBL.setLocation(540,180);//sets the location of the component 
		symptomsCbLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		
		symptomhelp.setFont(textfont);//the font that has been declared is attached to the component
		symptomhelp.setText("On this page please enter all the symptoms you are currently experiencing this will help determine the ailment and select the most suitable consultant to help treat the problem, feel free to include as much as possible all information entered is confidential and encrypted.");//the button is geven text to add meaning to the label
		symptomhelp.setSize(250,350); //the components size is correctly declared
		symptomhelp.setLocation(1050,180);//sets the location of the component 
		symptomhelp.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
	
		symptomsTfCbLBL.setFont(textfont);//the font that has been declared is attached to the component
		symptomsTfCbLBL.setText("If you have any symptoms that do no appear please use the boxes.");//the button is geven text to add meaning to the label
		symptomsTfCbLBL.setSize(400,20); //the components size is correctly declared
		symptomsTfCbLBL.setLocation(540,450);//sets the location of the component 
		symptomsTfCbLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		
        painCbLBL.setEditable(false);//the text field is disabled from being edited

        typepainCbLBL.setEditable(false);//the text field is disabled from being edited

        symptomsCbLBL.setEditable(false);//the text field is disabled from being edited

        symptomsTfCbLBL.setEditable(false);//the text field is disabled from being edited

        symptomhelp.setEditable(false);//the text field is disabled from being edited

		titleUpperBlackLine.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		titleUpperBlackLine.setForeground( new Color(1) );//the foreground of the component is given a black font, this is used to hide the text
		titleUpperBlackLine.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		titleUpperBlackLine.setLocation(680,40);//sets the location of the component 
		titleUpperBlackLine.setSize(723,3);//the components size is correctly declared
		titleUpperBlackLine.setOpaque(true);//the component is set to opaque
		
		titleLowerBlackLine.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		titleLowerBlackLine.setForeground( new Color(1) );//the foreground of the component is given a black font, this is used to hide the text
		titleLowerBlackLine.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		titleLowerBlackLine.setLocation(680,85);//sets the location of the component 
		titleLowerBlackLine.setSize(723,3);//the components size is correctly declared
		titleLowerBlackLine.setOpaque(true);//the component is set to opaque
		
		
		symptomHeaderlbl.setFont(headerFontFormatBlack);//the font that has been declared is attached to the component
		symptomHeaderlbl.setLocation(1209,48);//sets the location of the component 
		symptomHeaderlbl.setSize(250,30);//the components size is correctly declared
		symptomHeaderlbl.setOpaque(true);//the component is set to opaque
		symptomHeaderlbl.setText("New symptoms");//the label is geven text to add meaning to the label
		
		
		symptom1TF.setLocation(557,480);//sets the location of the component 
		symptom1TF.setSize(350,65);//the components size is correctly declared
		symptom1TF.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		symptom1TF.setText("Symptom 1");//the text field is geven text to add meaning to the label
		symptom1TF.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(symptom1TF.getText().equals("Symptom 1"))//selection checking if the text field contains the text prompt
				{
					symptom1TF.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(symptom1TF.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					symptom1TF.setText("Symptom 1");//if satisifed then the prompt text is set again
				}
			}
		});
		
		symptom2TF.setLocation(557,580);//sets the location of the component 
		symptom2TF.setSize(340,65);//the components size is correctly declared
		symptom2TF.setText("Symptom 2");//the label is geven text to add meaning to the label
		symptom2TF.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		symptom2TF.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(symptom2TF.getText().equals("Symptom 2"))//selection checking if the text field contains the text prompt
				{
					symptom2TF.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(symptom2TF.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
				symptom2TF.setText("Symptom 2");//if satisifed then the prompt text is set again

				}
			}
			
		});
		symptom3TF.setLocation(557,680);//sets the location of the component 
		symptom3TF.setSize(340,65);//the components size is correctly declared
		symptom3TF.setText("Symptom 3");//the label is geven text to add meaning to the label
		symptom3TF.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		symptom3TF.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(symptom3TF.getText().equals("Symptom 3"))//selection checking if the text field contains the text prompt
				{
					symptom3TF.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(symptom3TF.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
				symptom3TF.setText("Symptom 3");//if satisifed then the prompt text is set again

				}
			}
			
		});
		
		symptom4TF.setLocation(557,780);//sets the location of the component 
		symptom4TF.setSize(340,65);//the components size is correctly declared
		symptom4TF.setBackground( new Color(-2762241) );//the background colour of the component is declared to the desired value 
		symptom4TF.setText("Symptom 4");//the label is geven text to add meaning to the label
		symptom4TF.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(symptom4TF.getText().equals("Symptom 4"))//selection checking if the text field contains the text prompt
				{
					symptom4TF.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(symptom4TF.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
				symptom4TF.setText("Symptom 4");//if satisifed then the prompt text is set again

				}
			}
			
		});
		
		cbChronicPains.setLocation(150,700);//sets the location of the component 
		cbChronicPains.setSize(150,23);//the components size is correctly declared
		cbChronicPains.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		cbChronicPains.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		
		
		cbAcutePains.setLocation(150,740);//sets the location of the component 
		cbAcutePains.setSize(150,23);//the components size is correctly declared
		cbAcutePains.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbAcutePains.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		
		
		
		
		cbStiffnessInMuscle.setLocation(150,780);//sets the location of the component 
		cbStiffnessInMuscle.setSize(150,23);//the components size is correctly declared
		cbStiffnessInMuscle.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbStiffnessInMuscle.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		
		
		cbFrequentRecurringPains.setLocation(150,820);//sets the location of the component 
		cbFrequentRecurringPains.setSize(200,23);//the components size is correctly declared
		cbFrequentRecurringPains.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbFrequentRecurringPains.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		
		
		
		cbWeightLoss.setFont(symptomfont);//the font that has been declared is attached to the component
		cbWeightLoss.setLocation(575,225);//sets the location of the component 
		cbWeightLoss.setSize(150,23);//the components size is correctly declared
		cbWeightLoss.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbWeightLoss.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		
		cbFever.setLocation(575,275);//sets the location of the component 
		cbFever.setSize(150,23);//the components size is correctly declared
		cbFever.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbFever.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		cbFever.setFont(symptomfont);//the font that has been declared is attached to the component
		
		cbNausea.setLocation(575,325);//sets the location of the component 
		cbNausea.setSize(150,23);//the components size is correctly declared
		cbNausea.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbNausea.setFont(symptomfont);//the font that has been declared is attached to the component
		cbNausea.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
	
		cbFatigue.setLocation(575,375);//sets the location of the component 
		cbFatigue.setSize(150,23);//the components size is correctly declared
		cbFatigue.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		cbFatigue.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbFatigue.setFont(symptomfont);//the font that has been declared is attached to the component
		
		
	}
	
	
	//contactbar
	
	public void createContactBarPatient(JPanel panel,Patient currentPatient)
	{
		if(loaded[7][1]== false)//selection determines whether the panel is yet to be loaded
		{
			createContactBarPatientGeneral();//as the correct panel is visible the components on the panel is formatted 
			loaded[7][1]= true;//the variable is set as true to prevent the components from being reran
		}
			patientIDField.setText(currentPatient.patientID);//the feild is given the data of the user's infornmation
				if(date == true)
			{
				appointmentDateField.setText(closestAppointmentDay);//the feild is given the data of the user's infornmation
				appointmentTimeField.setText(closestAppointmentTime);//the feild is given the data of the user's infornmation
			}
				nameField.setText(currentPatient.firstName + " " + currentPatient.surName);//the feild is given the data of the user's name
		phoneNumberField.setText(currentPatient.contactNum);//the feild is given the data of the user's infornmation
		sexField.setText(currentPatient.gender+"");//the feild is given the data of the user's infornmation
		String dobString = ft.format(currentPatient.dob);
		dobField.setText(dobString);//the feild is given the data of the user's infornmation
		streetAddressField.setText(currentPatient.addressHouseNum+" "+currentPatient.addressHouseStreet);//the feild is given the data of the user's infornmation
		countyAddressField.setText(currentPatient.town);//the feild is given the data of the user's infornmation
		postcodeField.setText(currentPatient.postcode);//the feild is given the data of the user's infornmation
				appointmentConsultantField.setText("Dr "+closestAppointmentConsultant);//the feild is given the data of the user's infornmation
		userProfilePicfield.setSize(137,150); //the components size is correctly declared
		userProfilePicfield.setIcon( new ImageIcon("personIcon.png") );//the correct image is retrieved from the folder

		panel.add(titleUpperBlackLine);//the component is added to the panel
		panel.add(titleLowerBlackLine);//the component is added to the panel	
		panel.add(nameField);//the component is added to the panel
		panel.add(userProfilePicfield);//the component is added to the panel
		userProfilePicfield.setLocation(95,175); //sets the location of the component 
		panel.add(blackLine3);//the component is added to the panel
		panel.add(phoneNumberPromptField);//the component is added to the panel
		panel.add(blackLine4);//the component is added to the panel
		panel.add(phoneNumberField);//the component is added to the panel
		panel.add(sexPromptField);//the component is added to the panel
		panel.add(blackLine5);//the component is added to the panels
		panel.add(sexField);//the component is added to the panel
		panel.add(dobPromptField);//the component is added to the panel
		panel.add(blackLine6);//the component is added to the panel
		panel.add(dobField);//the component is added to the panel
		panel.add(blackLine7);//the component is added to the panel
		panel.add(blackLine8);//the component is added to the panel
		panel.add(addressPromptFeild);//the component is added to the panel
		panel.add(streetAddressField);//the component is added to the panel
		panel.add(blackLine9);//the component is added to the panel
		panel.add(blackLine10);//the component is added to the panel
		panel.add(countyAddressField);//the component is added to the panel
		panel.add(postcodeField);//the component is added to the panel
		panel.add(blackLine14);//the component is added to the panel
		panel.add(blackLine15);//the component is added to the panel
		panel.add(welcomeLbl);//the component is added to the panel
		panel.add(homeLbl);//the component is added to the panel
		blackLine15.setLocation(20,160);//sets the location of the component 
		blackLine15.setSize(300,3);//the components size is correctly declared
		blackLine14.setLocation(20,90);//sets the location of the component 
		blackLine14.setSize(300,3);//the components size is correctly declared
		blackLine3.setLocation(0,345);//sets the location of the component 
		blackLine3.setSize(350,2);//the components size is correctly declared
		blackLine4.setLocation(155,389);//sets the location of the component 
		blackLine4.setSize(170,3);//the components size is correctly declared
		blackLine5.setLocation(55,429);//sets the location of the component 
		blackLine5.setSize(170,3);//the components size is correctly declared
		blackLine6.setLocation(60,469);//sets the location of the component 
		blackLine6.setSize(170,3);//the components size is correctly declared
		blackLine7.setLocation(0,490);//sets the location of the component 
		blackLine7.setSize(350,2);//the components size is correctly declared
		blackLine12.setLocation(150,754);//sets the location of the component 
		blackLine12.setSize(180,3);//the components size is correctly declared
		blackLine13.setLocation(120,804);//sets the location of the component 
		blackLine13.setSize(186,3);//the components size is correctly declared
		blackLine11.setLocation(100,704);//sets the location of the component 
		blackLine11.setSize(220,3);//the components size is correctly declared
		blackLine10.setLocation(15,615);//sets the location of the component 
		blackLine10.setSize(310,3);//the components size is correctly declared
		blackLine9.setLocation(15,570);//sets the location of the component 
		blackLine9.setSize(310,3);//the components size is correctly declared
		blackLine8.setLocation(95,534);//sets the location of the component 
		blackLine8.setSize(180,3);//the components size is correctly declared
		
			panel.add(nextAppointmentPromptField);//the component is added to the panel
			panel.add(appointmentDatePromptField);//the component is added to the panel
			panel.add(blackLine11);//the component is added to the panel
			panel.add(appointmentDateField);//the component is added to the panel
			panel.add(appointmentTimePromptField);//the component is added to the panel
			panel.add(blackLine12);//the component is added to the panel
			panel.add(appointmentTimeField);//the component is added to the panel

			
			if(loginChoice==0)//from the inital selection a new one is used to isolate the user even more so just checking if the patient has logged in
			{
				panel.add(patientIDField);//the component is added to the panel
				panel.add(appointmentConsultantPromptField);//the component is added to the panel#
				panel.add(appointmentConsultantField);//the component is added to the panel
				panel.add(blackLine13);//the component is added to the panel
			}
			panel.add(greyBox1);//the component is added to the panel
		
		
		panel.add(whiteBox1);//the component is added to the panel
		titleUpperBlackLine.setLocation(470,65);//sets the location of the component 
		titleLowerBlackLine.setLocation(470,120);//sets the location of the component 
		titleLowerBlackLine.setSize(883,3);//the components size is correctly declared
		titleUpperBlackLine.setSize(883,3);//the components size is correctly declared
		
		whiteBox1.setLocation(0,40); //sets the location of the component 
		whiteBox1.setSize(350,873);//the components size is correctly declared
	}
	public void createContactBarPatientGeneral()
	{	
	
		blackLine14.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine14.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine14.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine14.setOpaque(true);//the component is set to opaque
		blackLine15.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine15.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine15.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine15.setOpaque(true);//the component is set to opaque
		
		nameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		nameField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		nameField.setLocation(60,40);//the location of the component is also declared
		nameField.setSize(250,50);//the components size is correctly declared
		nameField.setOpaque(false);//the component is set to opaque
		
		userProfilePicfield.setSize(137,150); //the components size is correctly declared
		userProfilePicfield.setIcon( new ImageIcon("personIcon.png") );//the correct image is retrieved from the folder
		userProfilePicfield.setVisible(true);//the component is set so it can be seen

		userProfilePicfield.setLocation(95,175); //sets the location of the component 
		userProfilePicfield.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		
		blackLine3.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine3.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine3.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine3.setOpaque(true);//the component is set to opaque
		
		phoneNumberPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		phoneNumberPromptField.setFont(symptomfont);//a font is applied to the text of the component 
		phoneNumberPromptField.setLocation(15,355);//sets the location of the component 
		phoneNumberPromptField.setSize(150,50);//the components size is correctly declared
		phoneNumberPromptField.setText("Phone Number:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
		phoneNumberPromptField.setOpaque(false);//the component is set to opaque
		
		blackLine4.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine4.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine4.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine4.setOpaque(true);//the component is set to opaque
		
		phoneNumberField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		phoneNumberField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		phoneNumberField.setLocation(159,350);//sets the location of the component 
		phoneNumberField.setSize(145,50);//the components size is correctly declared
		phoneNumberField.setOpaque(false);//the component is set to opaque
		
		sexPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		sexPromptField.setFont(symptomfont);//a font is applied to the text of the component 
		sexPromptField.setLocation(15,395);//sets the location of the component 
		sexPromptField.setSize(50,50);//the components size is correctly declared
		sexPromptField.setText("Sex:");//formatting component to include text
		sexPromptField.setOpaque(false);//the component is set to opaque
		
		blackLine5.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine5.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine5.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine5.setOpaque(true);//the component is set to opaque
		
		sexField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		sexField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		sexField.setLocation(105,390);//sets the location of the component 
		sexField.setSize(70,50);//the components size is correctly declared
		sexField.setOpaque(false);//the component is set to opaque
		
		dobPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		dobPromptField.setFont(symptomfont);//a font is applied to the text of the component 
		dobPromptField.setLocation(15,435);//sets the location of the component 
		dobPromptField.setSize(50,50);//the components size is correctly declared
		dobPromptField.setText("DOB:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
		dobPromptField.setOpaque(false);//the component is set to opaque
		
		blackLine6.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine6.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine6.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine6.setOpaque(true);//the component is set to opaque

		dobField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		dobField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		dobField.setLocation(75,431);//sets the location of the component 
		dobField.setSize(300,50);//the components size is correctly declared
		dobField.setOpaque(false);//the component is set to opaque
		
		blackLine7.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine7.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine7.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine7.setOpaque(true);//the component is set to opaque
		
		addressPromptFeild.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		addressPromptFeild.setFont(symptomfont);//a font is applied to the text of the component 
		addressPromptFeild.setLocation(15,500);//sets the location of the component 
		addressPromptFeild.setSize(250,50);//the components size is correctly declared
		addressPromptFeild.setText("Address:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
		addressPromptFeild.setOpaque(false);//the component is set to opaque
		
		blackLine8.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine8.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine8.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine8.setOpaque(true);//the component is set to opaque
		
		streetAddressField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		streetAddressField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		streetAddressField.setLocation(100,495);//sets the location of the component 
		streetAddressField.setSize(250,50);//the components size is correctly declared
		streetAddressField.setOpaque(false);//the component is set to opaque
		
		blackLine9.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine9.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine9.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine9.setOpaque(true);//the component is set to opaque
		
		blackLine10.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine10.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine10.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine10.setOpaque(true);//the component is set to opaque
		
		countyAddressField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		countyAddressField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		countyAddressField.setLocation(120,530);//sets the location of the component 
		countyAddressField.setSize(250,50);//the components size is correctly declared
		countyAddressField.setOpaque(false);//the component is set to opaque
		
		postcodeField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		postcodeField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		postcodeField.setLocation(115,577);//sets the location of the component 
		postcodeField.setSize(250,50);//the components size is correctly declared
		postcodeField.setOpaque(false);//the component is set to opaque
		
		
			nextAppointmentPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			nextAppointmentPromptField.setFont(symptomfont);//a font is applied to the text of the component 
			nextAppointmentPromptField.setLocation(45,630);//sets the location of the component 
			nextAppointmentPromptField.setSize(350,50);//the components size is correctly declared
			nextAppointmentPromptField.setText("Next Appointment:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
			nextAppointmentPromptField.setOpaque(false);//the component is set to opaque
			
			
			appointmentDatePromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			appointmentDatePromptField.setFont(symptomfont);//a font is applied to the text of the component 
			appointmentDatePromptField.setLocation(45,670);//sets the location of the component 
			appointmentDatePromptField.setSize(60,50);//the components size is correctly declared
			appointmentDatePromptField.setText("Date:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
			appointmentDatePromptField.setOpaque(false);//the component is set to opaque
		
			
			blackLine11.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
			blackLine11.setForeground( new Color(1) );//the foreground of the component is given a white font
			blackLine11.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
			blackLine11.setOpaque(true);//the component is set to opaque
			
			appointmentDateField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			appointmentDateField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
			appointmentDateField.setLocation(135,665);//sets the location of the component 
			appointmentDateField.setSize(150,50);//the components size is correctly declared
			
			appointmentDateField.setOpaque(false);//the component is set to opaque
			
			
			appointmentTimePromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			appointmentTimePromptField.setFont(symptomfont);//a font is applied to the text of the component 
			appointmentTimePromptField.setLocation(45,720);//sets the location of the component 
			appointmentTimePromptField.setSize(60,50);//the components size is correctly declared
			appointmentTimePromptField.setText("Time:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
			appointmentTimePromptField.setOpaque(false);//the component is set to opaque
			
			
			blackLine12.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
			blackLine12.setOpaque(true);//the component is set to opaque
			
			
			appointmentTimeField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			appointmentTimeField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
			appointmentTimeField.setLocation(165,715);//sets the location of the component 
			appointmentTimeField.setSize(150,50);//the components size is correctly declared
			
			appointmentTimeField.setOpaque(false);//the component is set to opaque
			
				patientIDField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
				patientIDField.setFont(headerFontFormatID);//a font is applied to the text of the component 
				patientIDField.setLocation(35,100);//sets the location of the component 
				patientIDField.setSize(285,50);//the components size is correctly declared
				patientIDField.setOpaque(false);//the component is set to opaque
				
				
				appointmentConsultantPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
				appointmentConsultantPromptField.setFont(symptomfont);//a font is applied to the text of the component 
				appointmentConsultantPromptField.setLocation(45,770);//sets the location of the component 
				appointmentConsultantPromptField.setSize(180,50);//the components size is correctly declared
				appointmentConsultantPromptField.setText("Consultant:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
				appointmentConsultantPromptField.setOpaque(false);//the component is set to opaque
				
				
				appointmentConsultantField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
				appointmentConsultantField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
				appointmentConsultantField.setLocation(165,765);//sets the location of the component 
				appointmentConsultantField.setSize(150,50);//the components size is correctly declared
				appointmentConsultantField.setOpaque(false);//the component is set to opaque
				
				
				blackLine13.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
				blackLine13.setForeground( new Color(1) );//the foreground of the component is given a white font
				blackLine13.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
				blackLine12.setForeground( new Color(1) );//the foreground of the component is given a white font
			blackLine12.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
				blackLine13.setOpaque(true);//the component is set to opaque
				
				
				blackLine13.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
				blackLine13.setForeground( new Color(1) );//the foreground of the component is given a white font
				blackLine13.setBackground( new Color(1) );//the background colour of the component is declared to the desired value
				blackLine13.setOpaque(true);//the component is set to opaque
				
			
			greyBox1.setSize(310,220);//the components size is correctly declared
			greyBox1.setLocation(20,635); //sets the location of the component 
		
		whiteBox1.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox1.setForeground( new Color(-1) );//the foreground of the component is given a white font
		whiteBox1.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		whiteBox1.setOpaque(true);//the component is set to opaque
		
		
	}
	public void createContactBarAdmin(JPanel panel,Staff currentStaff)
	{
		if(loaded[7][4]== false)//selection determines whether the panel is yet to be loaded
		{
			createContactBarAdminGeneral(currentStaff);//as the correct panel is visible the components on the panel is formatted 
			loaded[7][4]= true;//the variable is set as true to prevent the components from being reran
		}
		userProfilePicfield.setSize(137,150); //the components size is correctly declared
		userProfilePicfield.setIcon( new ImageIcon("personIcon.png") );//the correct image is retrieved from the folder

		panel.add(titleUpperBlackLine);//the component is added to the panel
		panel.add(titleLowerBlackLine);//the component is added to the panel	
		panel.add(nameField);//the component is added to the panel
		panel.add(userProfilePicfield);//the component is added to the panel
		userProfilePicfield.setLocation(95,175); //sets the location of the component 
		panel.add(blackLine3);//the component is added to the panel
		panel.add(phoneNumberPromptField);//the component is added to the panel
		panel.add(blackLine4);//the component is added to the panel
		panel.add(phoneNumberField);//the component is added to the panel
		panel.add(sexPromptField);//the component is added to the panel
		panel.add(blackLine5);//the component is added to the panels
		panel.add(sexField);//the component is added to the panel
		panel.add(dobPromptField);//the component is added to the panel
		panel.add(blackLine6);//the component is added to the panel
		panel.add(dobField);//the component is added to the panel
		panel.add(blackLine7);//the component is added to the panel
		panel.add(blackLine8);//the component is added to the panel
		panel.add(addressPromptFeild);//the component is added to the panel
		panel.add(streetAddressField);//the component is added to the panel
		panel.add(blackLine9);//the component is added to the panel
		panel.add(blackLine10);//the component is added to the panel
		panel.add(countyAddressField);//the component is added to the panel
		panel.add(postcodeField);//the component is added to the panel
		panel.add(blackLine14);//the component is added to the panel
		panel.add(blackLine15);//the component is added to the panel
		panel.add(welcomeLbl);//the component is added to the panel
		panel.add(homeLbl);//the component is added to the panel
		blackLine15.setLocation(20,160);//sets the location of the component 
		blackLine15.setSize(300,3);//the components size is correctly declared
		blackLine14.setLocation(20,90);//sets the location of the component 
		blackLine14.setSize(300,3);//the components size is correctly declared
		blackLine3.setLocation(0,345);//sets the location of the component 
		blackLine3.setSize(350,2);//the components size is correctly declared
		blackLine4.setLocation(155,389);//sets the location of the component 
		blackLine4.setSize(170,3);//the components size is correctly declared
		blackLine5.setLocation(55,429);//sets the location of the component 
		blackLine5.setSize(170,3);//the components size is correctly declared
		blackLine6.setLocation(60,469);//sets the location of the component 
		blackLine6.setSize(170,3);//the components size is correctly declared
		blackLine7.setLocation(0,490);//sets the location of the component 
		blackLine7.setSize(350,2);//the components size is correctly declared
		blackLine12.setLocation(150,754);//sets the location of the component 
		blackLine12.setSize(180,3);//the components size is correctly declared
		blackLine13.setLocation(120,804);//sets the location of the component 
		blackLine13.setSize(186,3);//the components size is correctly declared
		blackLine11.setLocation(100,704);//sets the location of the component 
		blackLine11.setSize(220,3);//the components size is correctly declared
		blackLine10.setLocation(15,615);//sets the location of the component 
		blackLine10.setSize(310,3);//the components size is correctly declared
		blackLine9.setLocation(15,570);//sets the location of the component 
		blackLine9.setSize(310,3);//the components size is correctly declared
		blackLine8.setLocation(95,534);//sets the location of the component 
		blackLine8.setSize(180,3);//the components size is correctly declared
		
		panel.add(adminIDField);//the component is added to the panel
		panel.add(wagePromptField);//the component is added to the panel
		panel.add(blackLine11);//the component is added to the panel
		panel.add(wageField);//the component is added to the panel
		panel.add(hoursPerWeekPromptField);//the component is added to the panel
		panel.add(blackLine12);//the component is added to the panel
			panel.add(hoursPerWeekField);//the component is added to the panel
		panel.add(whiteBox1);//the component is added to the panel
		titleUpperBlackLine.setLocation(470,65);//sets the location of the component 
		titleLowerBlackLine.setLocation(470,120);//sets the location of the component 
		titleLowerBlackLine.setSize(883,3);//the components size is correctly declared
		titleUpperBlackLine.setSize(883,3);//the components size is correctly declared
		
		whiteBox1.setLocation(0,40); //sets the location of the component 
		whiteBox1.setSize(350,873);//the components size is correctly declared
	}
	public void createContactBarAdminGeneral(Staff currentStaff)
	{	
	
		blackLine14.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine14.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine14.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine14.setOpaque(true);//the component is set to opaque
		blackLine15.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine15.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine15.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine15.setOpaque(true);//the component is set to opaque
		
		nameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		nameField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		nameField.setLocation(60,40);//the location of the component is also declared
		nameField.setSize(250,50);//the components size is correctly declared
		nameField.setText(currentStaff.firstName+" "+currentStaff.surName);//the feild is given the data of the user's name
		nameField.setOpaque(false);//the component is set to opaque
		
		userProfilePicfield.setSize(137,150); //the components size is correctly declared
		userProfilePicfield.setIcon( new ImageIcon("personIcon.png") );//the correct image is retrieved from the folder
		userProfilePicfield.setVisible(true);//the component is set so it can be seen

		userProfilePicfield.setLocation(95,175); //sets the location of the component 
		userProfilePicfield.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		
		blackLine3.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine3.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine3.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine3.setOpaque(true);//the component is set to opaque
		
		phoneNumberPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		phoneNumberPromptField.setFont(symptomfont);//a font is applied to the text of the component 
		phoneNumberPromptField.setLocation(15,355);//sets the location of the component 
		phoneNumberPromptField.setSize(150,50);//the components size is correctly declared
		phoneNumberPromptField.setText("Phone Number:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
		phoneNumberPromptField.setOpaque(false);//the component is set to opaque
		
		blackLine4.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine4.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine4.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine4.setOpaque(true);//the component is set to opaque
		
		phoneNumberField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		phoneNumberField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		phoneNumberField.setLocation(159,350);//sets the location of the component 
		phoneNumberField.setSize(145,50);//the components size is correctly declared
		phoneNumberField.setText(currentStaff.contactNum);//the feild is given the data of the user's infornmation
		phoneNumberField.setOpaque(false);//the component is set to opaque
		
		sexPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		sexPromptField.setFont(symptomfont);//a font is applied to the text of the component 
		sexPromptField.setLocation(15,395);//sets the location of the component 
		sexPromptField.setSize(50,50);//the components size is correctly declared
		sexPromptField.setText("Sex:");//formatting component to include text
		sexPromptField.setOpaque(false);//the component is set to opaque
		
		blackLine5.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine5.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine5.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine5.setOpaque(true);//the component is set to opaque
		
		sexField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		sexField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		sexField.setLocation(105,390);//sets the location of the component 
		sexField.setSize(70,50);//the components size is correctly declared
		sexField.setText(currentStaff.gender+"");//the feild is given the data of the user's infornmation
		sexField.setOpaque(false);//the component is set to opaque
		
		dobPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		dobPromptField.setFont(symptomfont);//a font is applied to the text of the component 
		dobPromptField.setLocation(15,435);//sets the location of the component 
		dobPromptField.setSize(50,50);//the components size is correctly declared
		dobPromptField.setText("DOB:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
		dobPromptField.setOpaque(false);//the component is set to opaque
		
		blackLine6.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine6.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine6.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine6.setOpaque(true);//the component is set to opaque
		
		dobField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		dobField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		dobField.setLocation(85,431);//sets the location of the component 
		dobField.setSize(150,50);//the components size is correctly declared
		String dobString = ft.format(currentStaff.dob);
		dobField.setText(dobString);//the feild is given the data of the user's infornmation
		dobField.setOpaque(false);//the component is set to opaque
		
		blackLine7.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine7.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine7.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine7.setOpaque(true);//the component is set to opaque
		
		addressPromptFeild.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		addressPromptFeild.setFont(symptomfont);//a font is applied to the text of the component 
		addressPromptFeild.setLocation(15,500);//sets the location of the component 
		addressPromptFeild.setSize(250,50);//the components size is correctly declared
		addressPromptFeild.setText("Address:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
		addressPromptFeild.setOpaque(false);//the component is set to opaque
		
		blackLine8.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine8.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine8.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine8.setOpaque(true);//the component is set to opaque
		
		streetAddressField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		streetAddressField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		streetAddressField.setLocation(100,495);//sets the location of the component 
		streetAddressField.setSize(250,50);//the components size is correctly declared
		streetAddressField.setText(currentStaff.addressHouseNum+" " +currentStaff.addressHouseStreet);//the feild is given the data of the user's infornmation
		streetAddressField.setOpaque(false);//the component is set to opaque
		
		blackLine9.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine9.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine9.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine9.setOpaque(true);//the component is set to opaque
		
		blackLine10.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine10.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine10.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine10.setOpaque(true);//the component is set to opaque
		
		countyAddressField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		countyAddressField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		countyAddressField.setLocation(120,530);//sets the location of the component 
		countyAddressField.setSize(250,50);//the components size is correctly declared
		countyAddressField.setText(currentStaff.town);//the feild is given the data of the user's infornmation
		countyAddressField.setOpaque(false);//the component is set to opaque
		
		postcodeField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		postcodeField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		postcodeField.setLocation(115,577);//sets the location of the component 
		postcodeField.setSize(250,50);//the components size is correctly declared
		postcodeField.setText(currentStaff.postcode);//the feild is given the data of the user's infornmation
		postcodeField.setOpaque(false);//the component is set to opaque
		
	
		
			adminIDField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			adminIDField.setFont(headerFontFormatID);//a font is applied to the text of the component 
			adminIDField.setLocation(35,100);//sets the location of the component 
			adminIDField.setSize(285,50);//the components size is correctly declared
			adminIDField.setText(currentStaff.staffID);//the feild is given the data of the user's infornmation
			adminIDField.setOpaque(false);//the component is set to opaque
			
			
			wagePromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			wagePromptField.setFont(symptomfont);//a font is applied to the text of the component 
			wagePromptField.setLocation(15,670);//sets the location of the component 
			wagePromptField.setSize(60,50);//the components size is correctly declared
			wagePromptField.setText("Wage:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
			wagePromptField.setOpaque(false);//the component is set to opaque
			
			
			blackLine11.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
			blackLine11.setForeground( new Color(1) );//the foreground of the component is given a white font
			blackLine11.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
			blackLine11.setLocation(70,704);//sets the location of the component 
			blackLine11.setSize(220,3);//the components size is correctly declared
			blackLine11.setOpaque(true);//the component is set to opaque
			
			wageField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			wageField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
			wageField.setLocation(135,665);//sets the location of the component 
			wageField.setSize(150,50);//the components size is correctly declared
			wageField.setText("$"+currentStaff.wage);//the feild is given the data of the user's infornmation
			wageField.setOpaque(false);//the component is set to opaque
			
			hoursPerWeekPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			hoursPerWeekPromptField.setFont(symptomfont);//a font is applied to the text of the component 
			hoursPerWeekPromptField.setLocation(15,720);//sets the location of the component 
			hoursPerWeekPromptField.setSize(250,50);//the components size is correctly declared
			hoursPerWeekPromptField.setText("Hours per Week:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
			hoursPerWeekPromptField.setOpaque(false);//the component is set to opaque
			
			blackLine12.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
			blackLine12.setForeground( new Color(1) );//the foreground of the component is given a white font
			blackLine12.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
			blackLine12.setOpaque(true);//the component is set to opaque
			
			
			hoursPerWeekField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			hoursPerWeekField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
			hoursPerWeekField.setLocation(205,715);//sets the location of the component 
			hoursPerWeekField.setSize(150,50);//the components size is correctly declared
			hoursPerWeekField.setText(currentStaff.hoursPerWeek+"");//the feild is given the data of the user's infornmation
			hoursPerWeekField.setOpaque(false);//the component is set to opaque
		
		whiteBox1.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox1.setForeground( new Color(-1) );//the foreground of the component is given a white font
		whiteBox1.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		whiteBox1.setOpaque(true);//the component is set to opaque
		
		
	}
	public void createContactBarConsultant(JPanel panel, Consultant currentConsultant)
	{
		if(loaded[7][3]== false)//selection determines whether the panel is yet to be loaded
		{
			createContactBarConsultantGeneral(currentConsultant);//as the correct panel is visible the components on the panel is formatted 
			loaded[7][3]= true;//the variable is set as true to prevent the components from being reran
		}
		userProfilePicfield.setSize(137,150); //the components size is correctly declared
		userProfilePicfield.setIcon( new ImageIcon("personIcon.png") );//the correct image is retrieved from the folder

		panel.add(titleUpperBlackLine);//the component is added to the panel
		panel.add(titleLowerBlackLine);//the component is added to the panel	
		panel.add(nameField);//the component is added to the panel
		panel.add(userProfilePicfield);//the component is added to the panel
		userProfilePicfield.setLocation(95,175); //sets the location of the component 
		panel.add(blackLine3);//the component is added to the panel
		panel.add(phoneNumberPromptField);//the component is added to the panel
		panel.add(blackLine4);//the component is added to the panel
		panel.add(phoneNumberField);//the component is added to the panel
		panel.add(sexPromptField);//the component is added to the panel
		panel.add(blackLine5);//the component is added to the panels
		panel.add(sexField);//the component is added to the panel
		panel.add(wardConsultantHomeFeild);//the component is added to the panel
		panel.add(blackLine6);//the component is added to the panel
		panel.add(wardPromptConsultantHomeFeild);//the component is added to the panel
		panel.add(blackLine7);//the component is added to the panel
		panel.add(blackLine8);//the component is added to the panel
		panel.add(expertisePromptField);//the component is added to the panel
		panel.add(expertiseField1);//the component is added to the panel
		panel.add(expertiseField2);//the component is added to the panel
		panel.add(expertiseField3);//the component is added to the panel
		panel.add(blackLine9);//the component is added to the panel
		panel.add(blackLine10);//the component is added to the panel
		panel.add(blackLine14);//the component is added to the panel
		panel.add(blackLine15);//the component is added to the panel
		panel.add(welcomeLbl);//the component is added to the panel
		panel.add(homeLbl);//the component is added to the panel
		blackLine15.setLocation(20,160);//sets the location of the component 
		blackLine15.setSize(300,3);//the components size is correctly declared
		blackLine14.setLocation(20,90);//sets the location of the component 
		blackLine14.setSize(300,3);//the components size is correctly declared
		blackLine3.setLocation(0,345);//sets the location of the component 
		blackLine3.setSize(350,2);//the components size is correctly declared
		blackLine4.setLocation(155,389);//sets the location of the component 
		blackLine4.setSize(170,3);//the components size is correctly declared
		blackLine5.setLocation(55,429);//sets the location of the component 
		blackLine5.setSize(170,3);//the components size is correctly declared
		blackLine6.setLocation(70,469);//sets the location of the component 
		blackLine6.setSize(200,3);//the components size is correctly declared
		blackLine7.setLocation(0,490);//sets the location of the component 
		blackLine7.setSize(350,2);//the components size is correctly declared
		blackLine12.setLocation(150,754);//sets the location of the component 
		blackLine12.setSize(180,3);//the components size is correctly declared
		blackLine13.setLocation(120,804);//sets the location of the component 
		blackLine13.setSize(186,3);//the components size is correctly declared
		blackLine11.setLocation(100,704);//sets the location of the component 
		blackLine11.setSize(220,3);//the components size is correctly declared
		blackLine10.setLocation(15,615);//sets the location of the component 
		blackLine10.setSize(310,3);//the components size is correctly declared
		blackLine9.setLocation(15,570);//sets the location of the component 
		blackLine9.setSize(310,3);//the components size is correctly declared
		blackLine8.setLocation(95,534);//sets the location of the component 
		blackLine8.setSize(180,3);//the components size is correctly declared
		
			panel.add(nextAppointmentPromptField);//the component is added to the panel
			panel.add(appointmentDatePromptField);//the component is added to the panel
			panel.add(blackLine11);//the component is added to the panel
			panel.add(appointmentDateField);//the component is added to the panel
			panel.add(appointmentTimePromptField);//the component is added to the panel
			panel.add(blackLine12);//the component is added to the panel
			panel.add(appointmentTimeField);//the component is added to the panel

			
			
		
				
		
				panel.add(consultantIDField);//the component is added to the panel
				panel.add(appointmentPatientPromptField);//the component is added to the panel#
				panel.add(appointmentPatientField);//the component is added to the panel
				panel.add(blackLine13);//the component is added to the panel
			
		
			panel.add(greyBox1);//the component is added to the panel
		if(date == true)
		{
			appointmentDateField.setText(closestAppointmentDay);//the feild is given the data of the user's infornmation
			appointmentTimeField.setText(closestAppointmentTime);//the feild is given the data of the user's infornmation
		}
		appointmentPatientField.setText(closestAppointmentPatient);//the feild is given the data of the user's infornmation
		nameField.setText(currentConsultant.firstName+" "+currentConsultant.surName);//the feild is given the data of the user's name
		phoneNumberField.setText(currentConsultant.contactNum);//the feild is given the data of the user's infornmation
		wardConsultantHomeFeild.setText(currentConsultant.wardLocatedIn);//the feild is given the data of the user's infornmation
		expertiseField1.setText(currentConsultant.expertiese[0]);//the feild is given the data of the user's infornmation
		expertiseField2.setText(currentConsultant.expertiese[1]);//the feild is given the data of the user's infornmation
			consultantIDField.setText(currentConsultant.consultantID);//the feild is given the data of the feild prompt inidacting what it is the feild contains
		
		
		
		panel.add(whiteBox1);//the component is added to the panel
		titleUpperBlackLine.setLocation(470,65);//sets the location of the component 
		titleLowerBlackLine.setLocation(470,120);//sets the location of the component 
		titleLowerBlackLine.setSize(883,3);//the components size is correctly declared
		titleUpperBlackLine.setSize(883,3);//the components size is correctly declared
		
		whiteBox1.setLocation(0,40); //sets the location of the component 
		whiteBox1.setSize(350,873);//the components size is correctly declared
	}
	public void createContactBarConsultantGeneral(Consultant currentConsultant)
	{	
	
		blackLine14.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine14.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine14.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine14.setOpaque(true);//the component is set to opaque
		blackLine15.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine15.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine15.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine15.setOpaque(true);//the component is set to opaque
		
		nameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		nameField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		nameField.setLocation(60,40);//the location of the component is also declared
		nameField.setSize(250,50);//the components size is correctly declared
		
		nameField.setOpaque(false);//the component is set to opaque
		
		userProfilePicfield.setSize(137,150); //the components size is correctly declared
		userProfilePicfield.setIcon( new ImageIcon("personIcon.png") );//the correct image is retrieved from the folder
		userProfilePicfield.setVisible(true);//the component is set so it can be seen

		userProfilePicfield.setLocation(95,175); //sets the location of the component 
		userProfilePicfield.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		
		blackLine3.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine3.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine3.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine3.setOpaque(true);//the component is set to opaque
		
		phoneNumberPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		phoneNumberPromptField.setFont(symptomfont);//a font is applied to the text of the component 
		phoneNumberPromptField.setLocation(15,355);//sets the location of the component 
		phoneNumberPromptField.setSize(150,50);//the components size is correctly declared
		phoneNumberPromptField.setText("Phone Number:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
		phoneNumberPromptField.setOpaque(false);//the component is set to opaque
		
		blackLine4.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine4.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine4.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine4.setOpaque(true);//the component is set to opaque
		
		phoneNumberField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		phoneNumberField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		phoneNumberField.setLocation(159,350);//sets the location of the component 
		phoneNumberField.setSize(145,50);//the components size is correctly declared
		phoneNumberField.setOpaque(false);//the component is set to opaque
		
		sexPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		sexPromptField.setFont(symptomfont);//a font is applied to the text of the component 
		sexPromptField.setLocation(15,395);//sets the location of the component 
		sexPromptField.setSize(50,50);//the components size is correctly declared
		sexPromptField.setText("Sex:");//formatting component to include text
		sexPromptField.setOpaque(false);//the component is set to opaque
		
		blackLine5.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine5.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine5.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine5.setOpaque(true);//the component is set to opaque
		
		sexField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		sexField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		sexField.setLocation(105,390);//sets the location of the component 
		sexField.setSize(70,50);//the components size is correctly declared
		sexField.setText("Male");//the feild is given the data of the user's infornmation
		sexField.setOpaque(false);//the component is set to opaque
		
		wardPromptConsultantHomeFeild.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		wardPromptConsultantHomeFeild.setFont(symptomfont);//a font is applied to the text of the component 
		wardPromptConsultantHomeFeild.setLocation(15,435);//sets the location of the component 
		wardPromptConsultantHomeFeild.setSize(60,50);//the components size is correctly declared
		wardPromptConsultantHomeFeild.setText("Ward:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
		wardPromptConsultantHomeFeild.setOpaque(false);//the component is set to opaque
		
		blackLine6.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine6.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine6.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine6.setOpaque(true);//the component is set to opaque

		wardConsultantHomeFeild.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		wardConsultantHomeFeild.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		wardConsultantHomeFeild.setLocation(85,431);//sets the location of the component 
		wardConsultantHomeFeild.setSize(150,50);//the components size is correctly declared
		wardConsultantHomeFeild.setOpaque(false);//the component is set to opaque
		
		blackLine7.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine7.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine7.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine7.setOpaque(true);//the component is set to opaque
		
		expertisePromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		expertisePromptField.setFont(symptomfont);//a font is applied to the text of the component 
		expertisePromptField.setLocation(15,500);//sets the location of the component 
		expertisePromptField.setSize(250,50);//the components size is correctly declared
		expertisePromptField.setText("Expertise:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
		expertisePromptField.setOpaque(false);//the component is set to opaque
		
		blackLine8.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine8.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine8.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine8.setOpaque(true);//the component is set to opaque
		
		expertiseField1.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		expertiseField1.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		expertiseField1.setLocation(100,495);//sets the location of the component 
		expertiseField1.setSize(250,50);//the components size is correctly declared
		expertiseField1.setOpaque(false);//the component is set to opaque
		
		blackLine9.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine9.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine9.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine9.setOpaque(true);//the component is set to opaque
		
		blackLine10.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		blackLine10.setForeground( new Color(1) );//the foreground of the component is given a white font
		blackLine10.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		blackLine10.setOpaque(true);//the component is set to opaque
		
		expertiseField2.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		expertiseField2.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		expertiseField2.setLocation(120,530);//sets the location of the component 
		expertiseField2.setSize(250,50);//the components size is correctly declared
		expertiseField2.setOpaque(false);//the component is set to opaque
		
		expertiseField3.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		expertiseField3.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		expertiseField3.setLocation(115,577);//sets the location of the component 
		expertiseField3.setSize(250,50);//the components size is correctly declared
		expertiseField3.setOpaque(false);//the component is set to opaque
		
		
			nextAppointmentPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			nextAppointmentPromptField.setFont(symptomfont);//a font is applied to the text of the component 
			nextAppointmentPromptField.setLocation(45,630);//sets the location of the component 
			nextAppointmentPromptField.setSize(350,50);//the components size is correctly declared
			nextAppointmentPromptField.setText("Next Appointment:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
			nextAppointmentPromptField.setOpaque(false);//the component is set to opaque
			
			
			appointmentDatePromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			appointmentDatePromptField.setFont(symptomfont);//a font is applied to the text of the component 
			appointmentDatePromptField.setLocation(45,670);//sets the location of the component 
			appointmentDatePromptField.setSize(60,50);//the components size is correctly declared
			appointmentDatePromptField.setText("Date:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
			appointmentDatePromptField.setOpaque(false);//the component is set to opaque
		
			
			blackLine11.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
			blackLine11.setForeground( new Color(1) );//the foreground of the component is given a white font
			blackLine11.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
			blackLine11.setOpaque(true);//the component is set to opaque
			
			
			appointmentDateField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			appointmentDateField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
			appointmentDateField.setLocation(135,665);//sets the location of the component 
			appointmentDateField.setSize(150,50);//the components size is correctly declared
			appointmentDateField.setOpaque(false);//the component is set to opaque
			
			
			appointmentTimePromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			appointmentTimePromptField.setFont(symptomfont);//a font is applied to the text of the component 
			appointmentTimePromptField.setLocation(45,720);//sets the location of the component 
			appointmentTimePromptField.setSize(60,50);//the components size is correctly declared
			appointmentTimePromptField.setText("Time:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
			appointmentTimePromptField.setOpaque(false);//the component is set to opaque
			
			
			blackLine12.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
			blackLine12.setOpaque(true);//the component is set to opaque
			
			
			appointmentTimeField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			appointmentTimeField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
			appointmentTimeField.setLocation(165,715);//sets the location of the component 
			appointmentTimeField.setSize(150,50);//the components size is correctly declared
			
			appointmentTimeField.setOpaque(false);//the component is set to opaque
			
				
				blackLine13.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
				blackLine13.setForeground( new Color(1) );//the foreground of the component is given a white font
				blackLine13.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
				blackLine12.setForeground( new Color(1) );//the foreground of the component is given a white font
				blackLine12.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
				blackLine13.setOpaque(true);//the component is set to opaque
			
				consultantIDField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
				consultantIDField.setFont(headerFontFormatID);//a font is applied to the text of the component 
				consultantIDField.setLocation(35,100);//sets the location of the component 
				consultantIDField.setSize(285,50);//the components size is correctly declared
				consultantIDField.setOpaque(false);//the component is set to opaque
				
						
				appointmentPatientPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
				appointmentPatientPromptField.setFont(symptomfont);//a font is applied to the text of the component 
				appointmentPatientPromptField.setLocation(45,770);//sets the location of the component 
				appointmentPatientPromptField.setSize(180,50);//the components size is correctly declared
				appointmentPatientPromptField.setText("Patient:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
				appointmentPatientPromptField.setOpaque(false);//the component is set to opaque
				
				
				appointmentPatientField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
				appointmentPatientField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
				appointmentPatientField.setLocation(135,765);//sets the location of the component 
				appointmentPatientField.setSize(250,50);//the components size is correctly declared
				appointmentPatientField.setOpaque(false);//the component is set to opaque
				
				
				blackLine13.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
				blackLine13.setForeground( new Color(1) );//the foreground of the component is given a white font
				blackLine13.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 

				blackLine13.setOpaque(true);//the component is set to opaque
				
			
			greyBox1.setSize(310,220);//the components size is correctly declared
			greyBox1.setLocation(20,635); //sets the location of the component 
		
		
		
			
			blackLine11.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
			blackLine11.setForeground( new Color(1) );//the foreground of the component is given a white font
			blackLine11.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
			blackLine11.setLocation(70,704);//sets the location of the component 
			blackLine11.setSize(220,3);//the components size is correctly declared
			blackLine11.setOpaque(true);//the component is set to opaque
			
			
			hoursPerWeekPromptField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			hoursPerWeekPromptField.setFont(symptomfont);//a font is applied to the text of the component 
			hoursPerWeekPromptField.setLocation(15,720);//sets the location of the component 
			hoursPerWeekPromptField.setSize(250,50);//the components size is correctly declared
			hoursPerWeekPromptField.setText("Hours per Week:");//the feild is given the data of the feild prompt inidacting what it is the feild contains
			hoursPerWeekPromptField.setOpaque(false);//the component is set to opaque
			
			blackLine12.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
			blackLine12.setForeground( new Color(1) );//the foreground of the component is given a white font
			blackLine12.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
			blackLine12.setOpaque(true);//the component is set to opaque
			
			
		whiteBox1.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		whiteBox1.setForeground( new Color(-1) );//the foreground of the component is given a white font
		whiteBox1.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		whiteBox1.setOpaque(true);//the component is set to opaque
		
		
	}
	
	//Document Page
	public void createDocumentPanel(Document document)
	{
		addPanel(documentPanel);
		setNonActiveTopAdmissionPanel();//calls method which sets no topbar button as active
		createTopbar(documentPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		createMainPartdocumentPanel(documentPanel);//creates main parts for the document panel
		createTopbarAdmission(documentPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		if(loaded[10][0]== false)//selection determines whether the panel is yet to be loaded
		{
			createMainPartdocumentPanelGeneral();//calls method which creates main parts to the document panel
			loaded[10][0]= true;//the variable is set as true to prevent the components from being reran
		}
		documentPanel.setVisible(false);//panel is set to be invisable
		documentPanel.setVisible(true);//panel is set to be visable
		if(loaded[5][3]== false)//selection determines whether the panel is yet to be loaded
		{
			int offset = 0;
			textDocumentTA.setLocation(487+offset,260);//the location of the component is set to the desired part of the panel
			documentIDDocumentLBL.setLocation(842+offset,200);//the location of the component is set to the desired part of the panel
			timeDocumentLBL.setLocation(912+offset,160);//the location of the component is set to the desired part of the panel
			dateDocumentLBL.setLocation(882+offset,120);//the location of the component is set to the desired part of the panel
			titleDocumentLBL.setLocation(490+offset,210);//the location of the component is set to the desired part of the panel
			patientIDDocumentLBL.setLocation(490+offset,120);//the location of the component is set to the desired part of the panel
			admissionIDDocumentLBL.setLocation(490+offset,160);//the location of the component is set to the desired part of the panel
			
			
			loaded[5][2]= false;//the variable is set as true to prevent the components from being reran
			loaded[5][3]= true;//the variable is set as true to prevent the components from being reran
		}
		textDocumentTA.setEditable(false);
	}
		public void createMainPartdocumentPanel(JPanel panel)
		{
			textDocumentTA.setVisible(true);//panel is set to be visable
			patientIDDocumentLBL.setText("  "+currentPatient.patientID);//formatting component to include text
			admissionIDDocumentLBL.setText("  "+currentAdmission.admissionID);//formatting component to include text
			documentIDDocumentLBL.setText("  "+currentDocument.documentID);//formatting component to include text
			titleDocumentLBL.setText(currentDocument.docType);//formatting component to include text
			dateDocumentLBL.setText("  "+ft.format(currentDocument.dateOfDocumentCreation));//formatting component to include text
			timeDocumentLBL.setText("  "+timeft.format(currentDocument.dateOfDocumentCreation));//formatting component to include text
			if(currentDocument.docType.equals("Test Results")==true)//selection determining if the attribute satisfies the condition
			{
				textDocumentTA.setText(currentDocument.testResults);//formatting component to include text
			}
			if(currentDocument.docType.equals("Consultant Notes")==true)//selection determining if the attribute satisfies the condition
			{
				textDocumentTA.setText(currentDocument.notes);//formatting component to include text
			}
			if(currentDocument.docType.equals("Dischargment")==true)//selection determining if the attribute satisfies the condition
			{
				
				textDocumentTA.setText("Due to sufficient evidence provided, "+currentAdmission.admissionsConsultantID+" believes that\nthe ailment of "+currentAdmission.currentDiagnosis+" affecting the patient has been\nbelieved to be resolved. As of today and the writing of this document\nDr "+currentAdmission.consultantName+"has discharged this patient as of typing\nof this document. If a recurrence in any prior symptoms \nre-surge the patient is more than welcome to have the \nadmission reinstated");//formatting component to include text
			}
			if(currentDocument.docType.equals("Prescription")==true)//selection determining if the attribute satisfies the condition
			{
				textDocumentTA.setText("The consultant "+currentAdmission.admissionsConsultantID+"has wished that the patient under\ncare needs to receive a prescription. Because of this the following\nPrescription has been requested for the patient "+currentPatient.patientID+"\n\nMedication: "+currentDocument.medicationName+"\n\nDosage: "  +currentDocument.medicationDosage+"\n\nIntake Time: "+currentDocument.medicationIntakeTime+"\n\nDate of next dispatch: "+ft.format(currentDocument.medicationDateOfNextDispatch));//formatting component to include text

			}
			panel.add(textDocumentTA);//the component is added to the panel
		panel.add(documentIDDocumentLBL);//the component is added to the panel
		panel.add(timeDocumentLBL);//the component is added to the panel
		panel.add(dateDocumentLBL);//the component is added to the panel
		panel.add(titleDocumentLBL);//the component is added to the panel
		panel.add(admissionIDDocumentLBL);//the component is added to the panel
		panel.add(patientIDDocumentLBL);//the component is added to the panel
			panel.add(whiteBox17);//the component is added to the panel
			whiteBox17.setSize(530,750);//size of component is set
			whiteBox17.setLocation(472,100);//the location of the component is set to the desired part of the panel
		}
		public void createMainPartdocumentPanelGeneral()
		{
			
		backToAdmissionsBttn.setLocation(25,100);//the location of the component is set to the desired part of the panel
		backToAdmissionsBttn.setSize(250,45);//size of component is set
		backToAdmissionsBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		backToAdmissionsBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		backToAdmissionsBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		backToAdmissionsBttn.setText("Back to Admissions");//formatting component to include text
		backToAdmissionsBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		documentPanel.add(backToAdmissionsBttn);//component is added to the panel
		
		nextDocumentBttn.setLocation(1179,800);//the location of the component is set to the desired part of the panel
		nextDocumentBttn.setSize(250,45);//size of component is set
		nextDocumentBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		nextDocumentBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		nextDocumentBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		nextDocumentBttn.setText("Next Document");//formatting component to include text
		nextDocumentBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		documentPanel.add(nextDocumentBttn);//component is added to the panel
		nextDocumentBttn.setEnabled(false);
		priorDocumentBttn.setLocation(25,800);//the location of the component is set to the desired part of the panel
		priorDocumentBttn.setSize(250,45);//size of component is set
		priorDocumentBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		priorDocumentBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		priorDocumentBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		priorDocumentBttn.setText("Prior Document");//formatting component to include text
		priorDocumentBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		documentPanel.add(priorDocumentBttn);//component is added to the panel
		priorDocumentBttn.setEnabled(false);
		printDocumentBttn.setLocation(1179,100);//the location of the component is set to the desired part of the panel
		printDocumentBttn.setSize(250,45);//size of component is set
		printDocumentBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		printDocumentBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		printDocumentBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		printDocumentBttn.setText("Print Document");//formatting component to include text
		printDocumentBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		documentPanel.add(printDocumentBttn);//component is added to the panel
		printDocumentBttn.setEnabled(false);
		patientIDDocumentLBL.setSize(250,30);//size of component is set
		patientIDDocumentLBL.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		patientIDDocumentLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		
		admissionIDDocumentLBL.setSize(250,30);//size of component is set
		admissionIDDocumentLBL.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		admissionIDDocumentLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);//atributes of the font are changed to include having it underlined
		titleDocumentLBL.setFont(buttonFontFormatu.deriveFont(attributes));//the component has had its font set to a design with the correct size for its purpose
		titleDocumentLBL.setSize(300,45);//size of component is set
	
		
		dateDocumentLBL.setSize(100,30);//size of component is set
		dateDocumentLBL.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		dateDocumentLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		
		timeDocumentLBL.setSize(70,30);//size of component is set
		timeDocumentLBL.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		timeDocumentLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		
		documentIDDocumentLBL.setSize(140,30);//size of component is set
		documentIDDocumentLBL.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		documentIDDocumentLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
	
		textDocumentTA.setEditable(true);//the text area is made so that the user can ammend the data in the field
		textDocumentTA.setSize(500,450);//size of component is set
		textDocumentTA.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
	}
		
	
	
	//TopBars

	public void createTopbar(JPanel panel)
	{
		if(loaded[7][0]== false)//selection determines whether the panel is yet to be loaded
		{
			createTopbarGeneral();//as the correct panel is visible the components on the panel is formatted 
			loaded[7][0]= true;//the variable is set as true to prevent the components from being reran
		}
		demographicbttn.setLocation(180,0);//sets the location of the component
		jargonLibrarybttn.setLocation(440,0);//sets the location of the component 
		panel.add(homebttn);//the component is added to the panel
		demographicbttn.setVisible(true);//the component is set so it cant be seen
		panel.add(demographicbttn);//the component is added to the panel
		newPatientbttn.setVisible(true);//the component is set so it cant be seen
		panel.add(newPatientbttn);//the component is added to the panel
		jargonLibrarybttn.setVisible(true);//the component is set so it cant be seen
		panel.add(jargonLibrarybttn);//the component is added to the panel
		admissionbttn.setVisible(true);//the component is set so it cant be seen
		panel.add(admissionbttn);//the component is added to the panel
		panel.add(consultantPatientDemographic);//the component is added to the panel
		panel.add(consultantPatientAdmission);//the component is added to the panel
		
		
		if(loginChoice ==0)//selection determing the type of login seeing if this type of user wants to login(patient)
		{
			newPatientbttn.setVisible(false);//will initally hide the component 
			admissionbttn.setVisible(true);//will initally hide the component 
			demographicbttn.setVisible(true);//will initally hide the component 
			homebttn.setVisible(true);//will initally hide the component 
			jargonLibrarybttn.setVisible(false);//will initally hide the component 
			consultantPatientAdmission.setVisible(false);//will initally hide the component 
			consultantPatientDemographic.setVisible(false);//will initally hide the component 			
		}
		else if (loginChoice ==1)//selection determing the type of login seeing if this type of user wants to login(admin)
		{
			newPatientbttn.setVisible(true);//will initally hide the component 
			admissionbttn.setVisible(false);//will initally hide the component 
			demographicbttn.setVisible(false);//will initally hide the component 
			homebttn.setVisible(true);//will initally hide the component 
			jargonLibrarybttn.setVisible(true);//will initally hide the component
			consultantPatientAdmission.setVisible(false);//will initally hide the component 
			consultantPatientDemographic.setVisible(false);//will initally hide the component 			
		}
		else if (loginChoice ==3)//selection determing the type of login seeing if this type of user wants to login(consultant)
		{
			newPatientbttn.setVisible(false);//will initally hide the component 
			admissionbttn.setVisible(false);//will initally hide the component 
			demographicbttn.setVisible(false);//will initally hide the component 
			homebttn.setVisible(true);//will initally hide the component 
			jargonLibrarybttn.setVisible(true);//will initally hide the component 
			jargonLibrarybttn.setLocation(180,0);//sets the location of the component
			consultantPatientAdmission.setVisible(true);//will initally hide the component 
			consultantPatientDemographic.setVisible(true);//will initally hide the component 
		}
		logoutbttn.setVisible(true);//the component is set so it cant be seen
		panel.add(logoutbttn);//the component is added to the panel
		panel.add(topwhitelinebar);//the component is added to the panel
		if(loginChoice == 0)//selection determining if the user was the desired one
		{
			welcomeLbl.setText("Welcome " + currentPatient.firstName);//formatting component to include text
		}
		if(loginChoice == 1)//selection determining if the user was the desired one
		{
			welcomeLbl.setText("Welcome " + currentStaff.firstName);//formatting component to include text
		}
		if(loginChoice == 3)//selection determining if the user was the desired one
		{
			welcomeLbl.setText("Welcome " + currentConsultant.firstName);//formatting component to include text
		}
	}
	public void createTopbarGeneral()
	{
		
		homebttn.setSize(180,40);//the components size is correctly declared
		homebttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		homebttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		homebttn.setBackground(selectedBttcColour);//the background colour of the component is declared to the desired value 
		homebttn.setText("Home");//the label is geven text to add meaning to the label
		homebttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		homebttn.setLocation(0,0);//sets the location of the component 
		
		demographicbttn.setSize(260,40);//the components size is correctly declared
		demographicbttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		demographicbttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		demographicbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		demographicbttn.setText("Demographic");//the label is geven text to add meaning to the label
		demographicbttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		
		newPatientbttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		newPatientbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		newPatientbttn.setSize(260,40);//the components size is correctly declared
		newPatientbttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		newPatientbttn.setText("New Patient");//the label is geven text to add meaning to the label
		newPatientbttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		newPatientbttn.setLocation(180,0);//sets the location of the component 
		
		jargonLibrarybttn.setSize(260,40);//the components size is correctly declared
		jargonLibrarybttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		jargonLibrarybttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		jargonLibrarybttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		jargonLibrarybttn.setText("Jargon");//the label is geven text to add meaning to the label
		jargonLibrarybttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		jargonLibrarybttn.setLocation(440,0);//sets the location of the component 
	
		admissionbttn.setSize(210,40);//the components size is correctly declared
		admissionbttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		admissionbttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		admissionbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		admissionbttn.setText("Admission");//the label is geven text to add meaning to the label
		admissionbttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		admissionbttn.setLocation(440,0);//sets the location of the component 
		
		logoutbttn.setSize(180,40);//the components size is correctly declared
		logoutbttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		logoutbttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		logoutbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		logoutbttn.setText("Logout");//the label is geven text to add meaning to the label
		logoutbttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		logoutbttn.setLocation(1277,0);//sets the location of the component 
		
		consultantPatientDemographic.setSize(210,40);//the components size is correctly declared
		consultantPatientDemographic.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		consultantPatientDemographic.setForeground( new Color(-1) );//the foreground of the component is given a white font
		consultantPatientDemographic.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		consultantPatientDemographic.setText("Patient Demo");//the label is geven text to add meaning to the label
		consultantPatientDemographic.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		consultantPatientDemographic.setEnabled(false);//the component is disabled
		consultantPatientDemographic.setLocation(440,0);//sets the location of the component 
		
		consultantPatientAdmission.setSize(210,40);//the components size is correctly declared
		consultantPatientAdmission.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		consultantPatientAdmission.setForeground( new Color(-1) );//the foreground of the component is given a white font
		consultantPatientAdmission.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		consultantPatientAdmission.setText("Patient Admi");//the label is geven text to add meaning to the label
		consultantPatientAdmission.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		consultantPatientAdmission.setEnabled(false);//the component is disabled
		consultantPatientAdmission.setLocation(650,0);//sets the location of the component 

		topwhitelinebar.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		topwhitelinebar.setForeground( new Color(-1) );//the foreground of the component is given a white font
		topwhitelinebar.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		topwhitelinebar.setSize(1457,40);//the components size is correctly declared
		topwhitelinebar.setOpaque(true);//the component is set to opaque
		topwhitelinebar.setLocation(0,0); //sets the location of the component 
		
		welcomeLbl.setFont(headerFontFormat);//the font that has been declared is attached to the component
		welcomeLbl.setFont(headerFontFormat.deriveFont(headerFontFormat.getStyle() & ~Font.BOLD));
		welcomeLbl.setLocation(505,70);//sets the location of the component 
		welcomeLbl.setSize(550,50);//the components size is correctly declared
		welcomeLbl.setOpaque(false);//the component is set to opaque
		
		homeLbl.setFont(headerFontFormat);//the font that has been declared is attached to the component
		homeLbl.setLocation(1185,65);//sets the location of the component 
		homeLbl.setSize(350,50);//the components size is correctly declared
		homeLbl.setText("Home");//the label is given text to add meaning
		homeLbl.setOpaque(false);//the component is set to opaque
	}
	public void setActiveTopPanelBttn(JButton button)
	{
		homebttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		demographicbttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		admissionbttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		newPatientbttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		jargonLibrarybttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		consultantPatientAdmission.setEnabled(true);//the label is set to enabled to allow them to be used 
		consultantPatientDemographic.setEnabled(true);//the label is set to enabled to allow them to be used 
		homebttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		admissionbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		demographicbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		newPatientbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		jargonLibrarybttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
		consultantPatientAdmission.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
		consultantPatientDemographic.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
		button.setEnabled(false);//the component is disabled as it is currently being on that panel 
		button.setBackground(selectedBttcColour);//the background colour of the component is declared to the desired value 
	}
	public void setActiveAdmissionTopPanelBttn(JButton button)
	{
		topBarAdmission1Bttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		topBarAdmission2Bttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		topBarAdmission3Bttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		topBarAdmission4Bttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		topBarAdmission5Bttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		topBarNewAdmissionBttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		
		topBarAdmission1Bttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		topBarAdmission2Bttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		topBarAdmission3Bttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		topBarAdmission4Bttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		topBarAdmission5Bttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
		topBarNewAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
		button.setEnabled(false);//the component is disabled as it is currently being on that panel 
		button.setBackground(selectedBttcColour);//the background colour of the component is declared to the desired value 
	}
	public void setNonActiveTopPanel()//when the user leaves any of the 3 top panels all of them will be renabled inseatd of the exception of just one
	{
		homebttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		demographicbttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		admissionbttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		newPatientbttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		jargonLibrarybttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		homebttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		admissionbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		demographicbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		newPatientbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		jargonLibrarybttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
	}
	public void setNonActiveTopAdmissionPanel()//when the user leaves any of the 3 top panels all of them will be renabled inseatd of the exception of just one
	{
		topBarAdmission1Bttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		topBarAdmission3Bttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		topBarAdmission2Bttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		topBarAdmission4Bttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		topBarAdmission5Bttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		topBarAdmission2Bttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		topBarAdmission1Bttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		topBarAdmission3Bttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		topBarAdmission5Bttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		topBarAdmission4Bttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
	
	}
	//declares the patient object for the system 



	public void setUpPatientObj()
	{
		date = false;//sets that no date for the patient has been set
		//creates a new patient
		currentPatient = new Patient();
		currentPatient = currentPatient.retrievePatientInfo(username);
		System.out.println("number of admissions ");
		if(currentPatient.patientID!="Null")
		{
			loginPanel.setVisible(false);//the current panel will be hidden 
			System.out.println("PatientID "+ currentPatient.patientID);
			//retireves their notfications
			notifications =currentPatient.getNotifications(currentPatient.patientID);
			System.out.println("number of admissions ");
			numberOfNotifications = notifications.length;
			currentPatient.updateNotificationsPatient(notifications);
			System.out.println("number of admissions ");
			//finds number of admissions
			numberOfAdmissions = currentPatient.numberOfAdmissions;
			System.out.println(numberOfAdmissions);
			admissionList=currentPatient.retrieveAdmissions(currentPatient);
			for (int i = 0; i < admissionList.length; i++)  //for loop that will iterate through every value
					{
						for (int j = i + 1; j < admissionList.length; j++)  //for loop that will iterate through every value
						{
							if(admissionList[i].admissionID.compareTo(admissionList[j].admissionID)>0) //selection determining if the value goes before the current index
							{
								Admission newOrderedArray = admissionList[i];//temp value to store array
								admissionList[i] = admissionList[j];//swap occurs
								admissionList[j] = newOrderedArray;//temp value is then inserted back into correct spot
							}
						}
					}
				
			admissionListCopy=currentPatient.retrieveAdmissions(currentPatient);
			int lengthAdmission = admissionList.length;//finds the length of the admission
			System.out.println("number of admissions "+ lengthAdmission);
			for(int i= 0;i<lengthAdmission;i++)
			{
				admissionList[i].retrieveDocuments(currentPatient,admissionList[i]);//declares the method which retrieves the documents for the current admission in the list
			}
			findSoonestAppointment();//calls method which finds the soonest appointment
			createPatientHomepagePanelGUI(currentPatient);//the method containing all the new components is ran
				currentPanel = patientHomepagePanel;//current panel is correctly updated
		}
		else{
			JOptionPane.showMessageDialog(null, "Invalid Patient Credentials");
		}
	}
		//creates list of admissions from the current patient
		//outputs the admission details to cmd
		public void outputPatientAdmission(Admission admis)
		{
		System.out.println("=============");
		System.out.println(admis.admissionID);//ouputs attribute
		System.out.println(admis.ward);//ouputs attribute
		System.out.println(admis.consultantName);//ouputs attribute
		System.out.println(admis.timeOfNextAppointmentA);//ouputs attribute
		System.out.println(admis.dateOfNextAppointmentA);//ouputs attribute
		System.out.println(admis.active);//ouputs attribute
		System.out.println(admis.numberOfDocuments);//ouputs attribute
		System.out.println(admis.medication);//ouputs attribute
		System.out.println(admis.room);//ouputs attribute
		System.out.println(admis.currentDiagnosis);//ouputs attribute
		for(int s = 0;s<admis.numberOfDocuments;s++);//ouputs attribute
		{
			//if(admis.numberOfDocuments>0)
			//{
				//System.out.println(admis.listOfDocuments[0].documentID);
			//}
		}
		System.out.println("=============");
	}
		//finds the soonest appointment from the admissions
		public void findSoonestAppointment() 
		{
		try
		{
			if(numberOfAdmissions>0)//selection determining if there at least one admission
			{
				closestAppointment  = ftTimeInc.parse("12/05/2999 12:01");//declares date in far future
				for(int a = 0 ; a<numberOfAdmissions; a++)//runs a for loop for the number of admissions
				{
					if(admissionList[a].dateOfNextAppointmentA.before(closestAppointment))//selection determining if the desired date is before the closest appointment
					{
						if(admissionList[a].dateOfNextAppointmentA.after(todaysDate))//selection determining if date is after todays date
						{
							closestAppointment = admissionList[a].dateOfNextAppointmentA;//updates variable with currrent info
							closestAppointmentConsultant =  admissionList[a].consultantName;//updates variable with currrent info
							date = true;//updates variable with currrent info
						}
						
					}
					closestAppointmentDay = ft.format(closestAppointment);//updates variable with currrent info
					closestAppointmentTime = timeft.format(closestAppointment);//updates variable with currrent info
					
				}
			}
		} 
		catch(Exception exc)
		{
		
		}
	
	}
	public void updatePatient()
	{
		currentPatient = currentPatient.retrievePatientInfo(currentPatient.patientID);
	
		//System.out.println("PatientID "+ currentPatient.patientID);
		//retireves their notfications
		notifications =currentPatient.getNotifications(currentPatient.patientID);//list of notification is called for
		numberOfNotifications = notifications.length;//number of notifications is found
		currentPatient.updateNotificationsPatient(notifications);//patient now recieves there notifications
		//finds number of admissions
		numberOfAdmissions = currentPatient.numberOfAdmissions;
		admissionList=currentPatient.retrieveAdmissions(currentPatient);
		admissionListCopy=currentPatient.retrieveAdmissions(currentPatient);
		int lengthAdmission = admissionList.length;
		//System.out.println("number of admissions "+ lengthAdmission);
		for(int i= 0;i<lengthAdmission;i++)
		{
			//System.out.println("=========== admission "+ i +"  =================");
			admissionList[i].retrieveDocuments(currentPatient,admissionList[i]);
			//for(int count = 0; count<admissionList[i].numberOfDocuments;count++)
			
			
		}
		findSoonestAppointment();
		
	}
	//CSUT0000001
	
	public void setUpConsultantObj()
	{
		currentConsultant = new Consultant();//declares new instance of consultant
		currentConsultant = currentConsultant.retrieveConsultantInfo(username);//retrieves the correct consultant information
		if(currentConsultant.consultantID!="Null")
		{
			loginPanel.setVisible(false);//the current panel will be hidden 
			if(currentConsultant.numberOfPatients>0)//selection determining if the consultant has at least one patient
			{
			numberOfConsultantPatients = currentConsultant.listOfPatientsAdmissions.length;//finds out how many patients the consultant has
			}
			else
			{
				numberOfConsultantPatients = 0;//if the consultant has no patients it is set to 0
			}
			//System.out.println(numberOfConsultantPatients + " number of patients");
			al = new AdmissionList();//new instance of admisison list is created
			listOfCsPatients = new Patient[numberOfConsultantPatients];//declares for the list of the consultants patients
			for(int count = 0;count<numberOfConsultantPatients;count++)//runs a for loop for the number of patients he has
			{
				String id = currentConsultant.listOfPatientsAdmissions[count];//id is set 
				finalIndexOfAdmission = 0;
				String consultantPateintID = id.substring(0,11);//id is found by splitting string
				//System.out.println("Test 2 id of desired patient " +consultantPateintID);
				Patient tempPatient = new Patient();//new instance of patient is declared
				
				listOfCsPatients[count] = tempPatient.retrievePatientInfo(consultantPateintID);//create our temp patient in the array of patient
				System.out.println("Test 3.5 number of admissions " +listOfCsPatients[count].numberOfAdmissions);
				
				String stringedAdmissionConsultant = id.substring(12,(id.length()));//finds the concatenated admission ids
				
				
				String[] admissionIDsConsultant =  tempPatient.unConcatenateStringAdmission(stringedAdmissionConsultant,"&");//array with all admissions consultant needs from that patient 
				int lengthOfConcatenatedAdmission = admissionIDsConsultant.length;//number of desired admissions from that one patient 
				tempAdmissionList = al.retrieveAdmissionList(listOfCsPatients[count]);//retrives all the admissions both desired and other consultant from that patient 
				for(int admissionArrayLength = 0;admissionArrayLength< tempAdmissionList.length;admissionArrayLength++)//runs for the entire array of all patient admissions
				{
					for(int innerCount = 0; innerCount<lengthOfConcatenatedAdmission;innerCount++)//runs for the number of times there are desired admissions
					{
						
						if(tempAdmissionList[admissionArrayLength].admissionID.equals(admissionIDsConsultant[innerCount]))//selection determining if the current admission id eqals the one in the list
						{
							listofAdmissions[count][innerCount] = tempAdmissionList[admissionArrayLength];//using the index found from the selection the correct admission is assigned to the consultant list
							finalIndexOfAdmission = innerCount;
						}					
					}
					
				}
			}
			createConsultantHomepagePanelGUI(currentConsultant);//the method containing all the new components is ran
			currentPanel = consultantHomepagePanel;//current panel is correctly updated
		}
		else{
			JOptionPane.showMessageDialog(null, "Invalid Consultant Credentials");
		}
	}
	public void refreshConsultantInfo()
	{
		currentConsultant = currentConsultant.retrieveConsultantInfo(username);//declares new instance of consultant
		if(currentConsultant.numberOfPatients>0)//selection determining if the consultant has at least one patient
		{
		numberOfConsultantPatients = currentConsultant.listOfPatientsAdmissions.length;//finds out how many patients the consultant has
		}
		else
		{
			numberOfConsultantPatients = 0;//if the consultant has no patients it is set to 0
		}
		finalIndexOfAdmission = 0;
		//System.out.println(numberOfConsultantPatients + "number of patients");
		listOfCsPatients = new Patient[numberOfConsultantPatients];//declares for the list of the consultants patients
		//System.out.println("Test 1");
		for(int count = 0;count<numberOfConsultantPatients;count++)//runs a for loop for the number of patients he has
		{
			String id = currentConsultant.listOfPatientsAdmissions[count];//id is set
		
			String consultantPateintID = id.substring(0,11);
		//	System.out.println("Test 2 id of desired patient " +consultantPateintID);
			Patient tempPatient = new Patient();//new instance of patient is declared
			
			listOfCsPatients[count] = tempPatient.retrievePatientInfo(consultantPateintID);//create our temp patient in the array of patient
			System.out.println("Test 3.5 number of admissions " +listOfCsPatients[count].numberOfAdmissions);
			
			String stringedAdmissionConsultant = id.substring(12,(id.length()));//finds the concatenated admission ids
			
			
			String[] admissionIDsConsultant =  tempPatient.unConcatenateStringAdmission(stringedAdmissionConsultant,"&");//array with all admissions consultant needs from that patient 
			int lengthOfConcatenatedAdmission = admissionIDsConsultant.length;//number of desired admissions from that one patient 
			tempAdmissionList = al.retrieveAdmissionList(listOfCsPatients[count]);//retrives all the admissions both desired and other consultant from that patient 
			for(int admissionArrayLength = 0;admissionArrayLength< tempAdmissionList.length;admissionArrayLength++)//runs for the entire array of all patient admissions
			{
				for(int innerCount = 0; innerCount<lengthOfConcatenatedAdmission;innerCount++)//runs for the number of times there are desired admissions
				{
					if(tempAdmissionList[admissionArrayLength].admissionID.equals(admissionIDsConsultant[innerCount]))//selection determining if the current admission id eqals the one in the list
					{
						listofAdmissions[count][innerCount] = tempAdmissionList[admissionArrayLength];//using the index found from the selection the correct admission is assigned to the consultant list
						finalIndexOfAdmission = innerCount;
					}					
				}
				
			}
			
		}
	}

	public void createAdmission()
	{
				admissionListCopy=currentPatient.retrieveAdmissions(currentPatient);
				if(loginChoice==3)
				{
					admissionListCopy=tempAdmissionList = al.retrieveAdmissionList(currentPatient);//retrives all the admissions both desired and other consultant from that patient 
					System.out.println("Size of array is "+admissionListCopy.length);
				}
				Admission tempAdmission = new Admission();//new instance of admission is created
				System.out.println("B ID ");
				for(int i =0;i<admissionListCopy.length;i++)
				{
					System.out.println(admissionListCopy[i].admissionID);
				}
				tempAdmission.admissionID = tempAdmission.createUniqueID(currentPatient.surName, currentPatient.patientID+"_Admissions.txt","A");//a unique id is created for the admission
				System.out.println("B ID "+tempAdmission.admissionID);
				tempAdmission.ward ="Physiotherapy";//sets attribute for object
				tempAdmission.room="P003";//sets attribute for object
				tempAdmission.active=true;//sets attribute for object
				tempAdmission.numberOfDocuments =0;//sets attribute for object
				tempAdmission.staffName="Sue";//sets attribute for object
				tempAdmission.consultantName="Will";//sets attribute for object
				tempAdmission.currentDiagnosis="Depression";//sets attribute for object
				tempAdmission.listOfSymptoms[0] = symptom1TF.getText();//sets attribute for object from component
				tempAdmission.listOfSymptoms[1] = symptom2TF.getText();//sets attribute for object from component
				tempAdmission.listOfSymptoms[2] = symptom3TF.getText();//sets attribute for object from component
				tempAdmission.listOfSymptoms[3] = symptom4TF.getText();//sets attribute for object from component
				tempAdmission.dateAdmissionCreated = todaysDate;//sets attribute for object
				try
				{
					tempAdmission.dateOfNextAppointmentA = ftTimeInc.parse("12/12/2099 19:02");//sets attribute for object
				}
				catch(Exception exc)
				{
				}
				tempAdmission.admissionsStaffID="STOM0000001";//sets attribute for object
				tempAdmission.admissionsConsultantID = "CSUT0000001";//sets attribute for object
				Consultant tempConsultant = new Consultant();//creates  a new instance for consultant
				//System.out.println(tempAdmission.admissionsConsultantID);
				//System.out.println(currentPatient.patientID);
				//System.out.println(tempAdmission.admissionID);
				tempConsultant.addpatientToConsultantRecord(tempAdmission.admissionsConsultantID,currentPatient.patientID,tempAdmission.admissionID);//new admission is added to consultant file
				currentPatient.numberOfAdmissions = currentPatient.numberOfAdmissions + 1;//num of admissions increments
				numberOfAdmissions++;//num of admissions increments
				Admission[] list4 = Arrays.copyOf(admissionListCopy, admissionListCopy.length+1);//sets all the data the same but adds on an extra index
				admissionListCopy = list4;//updates the previous array to equal the newly declared one
				if(admissionList==admissionListCopy)
				{
					admissionListCopy[currentPatient.numberOfAdmissions-1] = tempAdmission;//an array of a smaller length is declared
				}
				if(admissionList!=admissionListCopy)
				{
					admissionListCopy[currentPatient.numberOfAdmissions-1] = tempAdmission;//an array of a smaller length is declared
				}
				currentAdmission = tempAdmission;//current admission size is reduced
				numberOfDocuments = currentAdmission.numberOfDocuments;//number of documents is found
				singleDefinition = new Document[numberOfDocuments];
				listOfdocumentsGUI = currentAdmission.listOfDocuments;//this arary is saved to the GUI array
				PatientList pl = new PatientList();//new instance of patient list is declared
				pl.updatePatientDemo(currentPatient);//the pateint demographic is updated
			try
			{
				String file = currentPatient.patientID+"_"+tempAdmission.admissionID+"_"+"Documentation.txt";//filename for admission is created
				FileWriter fwr = new FileWriter(file);//declare a new file writer that will ammend not not write to file
				fwr.close();//closes the file
			}
			catch(Exception exc)
			{
				System.out.println("Error 1");
			}
			try
			{
				String file = currentPatient.patientID+"_Admissions.txt";//filename for admission is created
				FileWriter fwr = new FileWriter(file);//declare a new file writer that will ammend not not write to file
				int iterationCondition=0;
				if(loginChoice==0)
				{iterationCondition=currentPatient.numberOfAdmissions;
				}
				if(loginChoice==3)
				{iterationCondition=currentPatient.numberOfAdmissions+1;
				}
				for(int count = 0;count<currentPatient.numberOfAdmissions;count++)
				{Admission tempAdmissionwtfa = new Admission();//creates a new instance of admission
					tempAdmissionwtfa = admissionListCopy[count];//updates current admisison
					String stringedSymptoms = (tempAdmissionwtfa.listOfSymptoms[0]+"|"+tempAdmissionwtfa.listOfSymptoms[1]+"|"+tempAdmissionwtfa.listOfSymptoms[2]+"|");//concatenates admission attributes
					String stringedAdmission = (tempAdmissionwtfa.admissionID+","+tempAdmissionwtfa.ward+","+tempAdmissionwtfa.consultantName+","+ftTimeInc.format(tempAdmissionwtfa.dateOfNextAppointmentA)+","+tempAdmissionwtfa.active+","+tempAdmissionwtfa.numberOfDocuments+","+tempAdmissionwtfa.medication+","+tempAdmissionwtfa.room+","+tempAdmissionwtfa.currentDiagnosis+","+stringedSymptoms+","+tempAdmissionwtfa.staffName+","+ft.format(tempAdmissionwtfa.dateAdmissionCreated)+","+tempAdmissionwtfa.admissionsStaffID+","+tempAdmissionwtfa.admissionsConsultantID);//concatenates admission attributes
					System.out.println("==========================="+stringedAdmission);
					fwr.write(stringedAdmission);//writes to file current line and admission
					fwr.write("\r\n");
				}
				fwr.close();//closes the fil
			}
			catch(Exception exc)
			{System.out.println("Error 2");
			}
				if(loginChoice ==0)
				{
					admissionList=admissionListCopy;
					admissionList=currentPatient.retrieveAdmissions(currentPatient);//retieves all admissions patient has
					int lengthAdmission = admissionList.length;//finds how many admissions exist
					//System.out.println("number of admissions "+ lengthAdmission);
					for(int i= 0;i<lengthAdmission;i++)//runs for length of the number of admisisons
					{
						admissionList[i].retrieveDocuments(currentPatient,admissionList[i]);//retireives documents for admission
					}
				}
				if(loginChoice!=0)
				{admissionList[finalIndexOfAdmission+1] =currentAdmission;
				}
				findSoonestAppointment();//finds the soonest appointment due to the admissions creation
				if(numberOfAdmissions ==1)//selection determining if there are now this many admissions now
				{setActiveAdmissionTopPanelBttn(topBarAdmission1Bttn);//sets active top bar to the newest admisison
				}
				if(numberOfAdmissions ==2)//selection determining if there are now this many admissions now
				{setActiveAdmissionTopPanelBttn(topBarAdmission2Bttn);//sets active top bar to the newest admisison
				}
				if(numberOfAdmissions ==3)//selection determining if there are now this many admissions now
				{setActiveAdmissionTopPanelBttn(topBarAdmission3Bttn);//sets active top bar to the newest admisison
				}
				if(numberOfAdmissions ==4)//selection determining if there are now this many admissions now
				{
					setActiveAdmissionTopPanelBttn(topBarAdmission4Bttn);//sets active top bar to the newest admisison
				}
				if(numberOfAdmissions ==5)//selection determining if there are now this many admissions now
				{
					setActiveAdmissionTopPanelBttn(topBarAdmission5Bttn);//sets active top bar to the newest admisison
				}
				createadmissionHomepagePanelGUI();//updates admission homepage by calling method and will include new admision
	}

	public void createNewPatientAccount()
	{
		Patient newPatient = new Patient();//creates new instance of this object
		newPatient.firstName = demographicFNameTextFeild.getText();//retireives attribute from component
		newPatient.surName = demographicSNTextFeild.getText();//retireives attribute from component
		try
		{
			newPatient.dob = ft.parse(demographicDOBTextFeild.getText());//retireives attribute from component
		}
		catch(Exception exc)//if any errrors are found they are caught here		
		{
		}
		newPatient.addressHouseNum = Integer.parseInt(demographicBuildingNUMTextFeild.getText());//retireives attribute from component
		newPatient.addressHouseStreet = demographicStreetTextFeild.getText();//retireives attribute from component
		newPatient.postcode = demographicPostcode.getText();//retireives attribute from component
		newPatient.town = demographicTownTextFeild.getText();//retireives attribute from component
		newPatient.gender = ((String) demographicGender.getSelectedItem()).charAt(0);//retireives attribute from component
		newPatient.contactNum = demographicContactinfo.getText();//retireives attribute from component
		newPatient.religon = demographicReligonTextFeild.getText();//retireives attribute from component
		newPatient.bloodType ="";//retireives attribute from component
		newPatient.nationality = demographicNotioanlityTextFeild.getText();//retireives attribute from component
		newPatient.allergies = demographicAllergies.getText();//retireives attribute from component
		newPatient.smoker = demographicSmokerPromptLbl.isSelected();//retireives attribute from component
		newPatient.drinker = demographicDrinkerPromptLbl.isSelected();//retireives attribute from component
		newPatient.disability = demographicDisablitiesTextFeild.getText();//retireives attribute from component
		newPatient.carer = demographicCarerPromptLbl.isSelected();//retireives attribute from component
		newPatient.translator = demographicTranslatorPromptLbl.isSelected();//retireives attribute from component
		newPatient.numberOfAdmissions = 0;//retireives attribute from component
		PatientList pl = new PatientList();//retireives attribute from component
		currentPatient = pl.createNewPatient(newPatient);//calls using the new instance the create a new patient
			notifications =currentPatient.getNotifications(currentPatient.patientID);///retireves the patients notifications
			//System.out.println("H");
			//System.out.println("H");
		numberOfNotifications = notifications.length;//finds the number of notifications the user has
		//System.out.println("H");
		currentPatient.updateNotificationsPatient(notifications);//updates the patients notifications
		loginChoice = 0;
		//System.out.println("3");
		createPatientHomepagePanelGUI(currentPatient);//calls method which displays panel for the correct user
	}
	
  public void actionPerformed(ActionEvent e)//this is the action listener a method that will pass in events performed by the user
    {
		if((recommendationFalseAcceptanceRB3.isSelected())&&(recommendationFalseAcceptanceRB1.isSelected())&&(recommendationFalseAcceptanceRB2.isSelected()))//selection determining if all radio boxes have been selected
		{
			symptomRecomendationNewAdmissionBttn.setEnabled(true);//the component is enabled to interact with the user
		}
		if((recommendationFalseAcceptanceRB3.isSelected()==false)||(recommendationFalseAcceptanceRB1.isSelected()==false)||(recommendationFalseAcceptanceRB2.isSelected()==false))//selection determining if all radio boxes have been selected
		{
			symptomRecomendationNewAdmissionBttn.setEnabled(false);//the component is disabled
			outputPanelList();
		}
		if(e.getSource()==symptomButtn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			
			createsymptomPanel();//the method containing all the new components is ran
			currentPanel = symptomPanel;//current panel is correctly updated
		}
		if(e.getSource()==loginBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		//if the action comes from the logon button this code will execute
		{
			
			createloginPanel();//the method containing all the new components is ran
			currentPanel = loginPanel;//current panel is correctly updated
			outputPanelList();
		}
	
		if(e.getSource()==backToAdmissionbttn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			symptomPanel.setVisible(false);//the current panel will be hidden 
			setActiveTopPanelBttn(admissionbttn);//active top bar is set to the button just pressed
			if(numberOfAdmissions>0)//selection determining if there is at least one admission
			{
				currentAdmission = admissionList[0];//sets the first admission as the current one
			}
			outputDocumentCards(currentAdmission);//the cards for the documents are outputted 
			removePanel();
			createadmissionHomepagePanelGUI();//the method containing all the new components is ran
			
		}
		
		if(e.getSource()==symptomBackToStartBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			removePanel();
		}
		if(e.getSource()==symptomRecomendationBackToExpertSystem)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			removePanel();
		}
		if(e.getSource()==symptomRecomendationcreateAccountBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			
			if (newUser = true)
			{
				createNewPatientHomepagePanelGUI();//the method containing all the new components is ran
				currentPanel = symptomNewPatientPanel;//current panel is correctly updated
			}
			
			else if(loginChoice==-1)
			{
				createNewPatientHomepagePanelGUI();//the method containing all the new components is ran
			currentPanel = symptomNewPatientPanel;//current panel is correctly updated
			}
		}
		if(e.getSource()==symptomRecomendationNewAdmissionBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			if(loginChoice!=-1)
			{
				createAdmission();
			}
			else{
				newUser = true;
				createloginPanel();
			}
		}
		
		if(e.getSource()==newPatientBackToRecommendation)
		{
			removePanel();
		}
		
		if(e.getSource()==selectPatientloginBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		//if the action comes from the logon button this code will execute
		{
			openButtonPatient();//the new method is ran which will open the patient part open in the login page
			createAccountbbtn.setEnabled(true);
		}
		if(e.getSource()==selectAdminloginBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		//if the action comes from the logon button this code will execute
		{
			openButtonAdmin();//the new method is ran which will open the admin part open in the login page
			createAccountbbtn.setEnabled(false);
		}
		if(e.getSource()==selectManagementloginBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		//if the action comes from the logon button this code will execute
		{
			openButtonManagment();//the new method is ran which will open the management part open in the login page
			createAccountbbtn.setEnabled(false);
		}
		if(e.getSource()==selectConsultantloginBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		//if the action comes from the logon button this code will execute
		{
			openButtonConsultant();//the new method is ran which will open the consultant part open in the login page
			createAccountbbtn.setEnabled(false);
		}
		if(e.getSource()==SymptomConfirmBttn)
		{
			symptomPanel.setVisible(false);//the current panel will be hidden 
			System.out.println("==================");//prints line for visual flair
			System.out.println("Areas selected:");//outputs current area 
			if(cbNeck.isSelected()==true)//selection determining if the checkbox had been selected
			{
			System.out.println("Neck Area");//outputs symptom
			}
			if(cbChest.isSelected()==true)//selection determining if the checkbox had been selected
			{
			System.out.println("Chest Area");//outputs symptom
			}
			if(cbHand.isSelected()==true)//selection determining if the checkbox had been selected
			{
			System.out.println("Hand Area");//outputs symptom
			}
			if(cbFoot.isSelected()==true)//selection determining if the checkbox had been selected
			{
			System.out.println("Foot Area");//outputs symptom
			}
			if(cbHead.isSelected()==true)//selection determining if the checkbox had been selected
			{
			System.out.println("Head Area");//outputs symptom
			}
			if(cbBack.isSelected()==true)//selection determining if the checkbox had been selected
			{
			System.out.println("Back Area");//outputs symptom
			}
			if(cbAbdomen.isSelected()==true)//selection determining if the checkbox had been selected
			{
			System.out.println("Abdomen Area");//outputs symptom
			}
			if(cbArm.isSelected()==true)//selection determining if the checkbox had been selected
			{
			System.out.println("Arm Area");//outputs symptom
			}
			if(cbPelvis.isSelected()==true)//selection determining if the checkbox had been selected
			{
			System.out.println("Pelvis Area");//outputs symptom
			}
			if(cbLeg.isSelected()==true)//selection determining if the checkbox had been selected
			{
			System.out.println("Leg Area");//outputs symptom
			}
			
			System.out.println("==================");//prints line for visual flair
			System.out.println("Symptom entered");//outputs current area 
			System.out.println(symptom1TF.getText());//outputs symptom
			System.out.println(symptom2TF.getText());//outputs symptom
			System.out.println(symptom3TF.getText());//outputs symptom
			System.out.println(symptom4TF.getText());//outputs symptom
			System.out.println("==================");//prints line for visual flair
			System.out.println("Types of pain selected");//outputs current area 
			if(cbChronicPains.isSelected()==true)//selection determining if the checkbox had been selected
			{
			System.out.println("Chronic pains");//outputs symptom
			}
			if(cbAcutePains.isSelected()==true)//selection determining if the checkbox had been selected
			{
			System.out.println("Acute pains");//outputs symptom
			}
			if(cbStiffnessInMuscle.isSelected()==true)//selection determining if the checkbox had been selected
			{
			System.out.println("Stiffness in muscle");//outputs symptom
			}
			if(cbFrequentRecurringPains.isSelected()==true)//selection determining if the checkbox had been selected
			{
			System.out.println("Frequent recurring pains");//outputs symptom
			}
			createSymptomRecomendationPanel();//calls method which displays panel for the correct user
		}
		if(e.getSource()==loginBackToStartBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		//if the action comes from the logon button this code will execute
		{
			removePanel();
		}
		if(e.getSource()==realLoginbttn)//selection determining the location of the action performed by passing the current event through and locating the source
		//if the action comes from the logon button this code will execute
		{
			if(newUser == true)
			{
				username =usernameTF.getText();//sets username as feild of text feild
				//System.out.println(username);
				newUser = false;
				setUpPatientObj();//initalises patient object
				createAdmission();
			}
			else if(loginChoice ==0)//selection determing the type of login seeing if this type of user wants to login(patient)
			{
				username =usernameTF.getText();//sets username as feild of text feild
				//System.out.println(username);
				
				setUpPatientObj();//initalises patient object

			}
			else if (loginChoice ==1)//selection determing the type of login seeing if this type of user wants to login(admin)
			{
				loginPanel.setVisible(false);//the current panel will be hidden 
				//currentStaff=currentStaff.retrieveStaffInfo();
				createAdminHomepagePanelGUI(currentStaff);//the method containing all the new components is ran
				currentPanel = adminHomepagePanel;//current panel is correctly updated
			}
			else if (loginChoice ==2)//selection determing the type of login seeing if this type of user wants to login(managment)
			{
				frame.setVisible(false);//the current panel will be hidden 
				frame.dispose();//disposes of the frame
				currentManager.createHomepage();//runs cmd interface
			}
			else if (loginChoice ==3)//selection determing the type of login seeing if this type of user wants to login(consultant)
			{
				username =usernameTF.getText();//sets username as feild of text feild
				
				setUpConsultantObj();//creates instance of consultant
				
			}
			
		}
		
		
		
		if(e.getSource()==createAccountbbtn)//selection determining the location of the action performed by passing the current event through and locating the source
		//if the action comes from the logon button this code will execute
		{
			if (newUser = true)
			{
				
				createNewPatientHomepagePanelGUI();//the method containing all the new components is ran
				currentPanel = symptomNewPatientPanel;//current panel is correctly updated
				
			}
			
			else if(loginChoice ==0)//selection determing the type of login seeing if this type of user wants to create an account
			{
				createNewPatientHomepagePanelGUI();//the method containing all the new components is ran
				currentPanel = symptomNewPatientPanel;//current panel is correctly updated
			}
			else if (loginChoice ==1)//selection determing the type of login seeing if this type of user wants to create an account
			{
				loginPanel.setVisible(false);//the current panel will be hidden 
			}
			else if (loginChoice ==2)//selection determing the type of login seeing if this type of user wants to to create an account
			{
				loginPanel.setVisible(false);//the current panel will be hidden 
			}
			else if (loginChoice ==3)//selection determing the type of login seeing if this type of user wants to to create an account
			{
				loginPanel.setVisible(false);//the current panel will be hidden 
			}
		}
		
		if(e.getSource()==homebttn)
		{
			setActiveTopPanelBttn(homebttn); //active top bar is set to the button just pressed
			
			if(loginChoice ==0)//selection determining if this user is this type of user
			{
				createPatientHomepagePanelGUI(currentPatient);//correct homepage is created
				patientHomepagePanel.setVisible(false);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
				patientHomepagePanel.setVisible(true);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
				emptyArray(patientHomepagePanel);
				System.out.println("patientHomepagePanel");
			}
			if(loginChoice ==1)//selection determining if this user is this type of user
			{
				createAdminHomepagePanelGUI(currentStaff);//correct homepage is created
				adminHomepagePanel.setVisible(false);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
				adminHomepagePanel.setVisible(true);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
				emptyArray(adminHomepagePanel);
				System.out.println("adminHomepagePanel");
			}
			if(loginChoice ==3)//selection determining if this user is this type of user
			{
				createConsultantHomepagePanelGUI(currentConsultant);//correct homepage is created
				consultantHomepagePanel.setVisible(false);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
				consultantHomepagePanel.setVisible(true);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
				consultantPatientAdmission.setEnabled(false);//the component is disabled
				emptyArray(consultantHomepagePanel);
				System.out.println("consultantHomepagePanel");
				consultantPatientDemographic.setEnabled(false);//the component is disabled
			}			
		}
		if(e.getSource()==demographicbttn)
		{
			emptyArray(demographicHomepagePanel);
			System.out.println("demographicHomepagePanel");
			setActiveTopPanelBttn(demographicbttn);//active top bar is set to the button just pressed
			createdemographicHomepagePanelGUI();//correct homepage is created
			demographicHomepagePanel.setVisible(false);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
			demographicHomepagePanel.setVisible(true);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
			currentPanel = demographicHomepagePanel;//current panel is correctly updated
			
		}
		if(e.getSource()==admissionbttn)
		{
			emptyArray(admissionHomepagePanel);
			setActiveTopPanelBttn(admissionbttn);//active top bar is set to the button just pressed
			createTopbarAdmission(admissionHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 

			if(numberOfAdmissions>0)//selection determining if the user has at least one admission
			{
				currentAdmission = admissionList[0];//sets inital admission 
				createadmissionHomepagePanelGUI();//correct homepage is created
			}
			if(numberOfAdmissions<=0)//selection determining if the patient has no admissions
			{
				createBlankAdmissionHomePage();//as there are no admissions the panel is created 
			}
	
			
			admissionHomepagePanel.setVisible(false);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
			admissionHomepagePanel.setVisible(true);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
		}
		if(e.getSource()==admissionCardPageBackBttn)
		{
			currentPageIndex= currentPageIndex - 6;
			currentPage--;
			outputDocumentCards(currentAdmission);//the cards for the documents are outputted 
		}
		if(e.getSource()==admissionCardPageForwardBttn)
		{
			currentPageIndex= currentPageIndex + 6;
			currentPage++;
			outputDocumentCards(currentAdmission);//the cards for the documents are outputted 
		}
		if(e.getSource()==ConsultantCardPageBackBttn)
		{
			currentPageIndexConsultant= currentPageIndexConsultant - 3;
			currentPageConsultant--;
			outputPatientCards();//calls the method which outputs the correct number of cards
		}
		if(e.getSource()==ConsultantCardPageForwardBttn)
		{
			currentPageIndexConsultant= currentPageIndexConsultant + 3;
			currentPageConsultant++;
			outputPatientCards();//calls the method which outputs the correct number of cards
		}
		
		if(e.getSource()==viewPatient1bttn)
		{
		consultantPatient = listOfCsPatients[0+currentPageIndexConsultant];
		currentPatient = consultantPatient;
		currentPatientConsultantIndex =0+currentPageIndexConsultant;
		admissionList=listofAdmissions[0+currentPageIndexConsultant];
		currentAdmission = listofAdmissions[0+currentPageIndexConsultant][0];
		int lengthAdmission = 0;
		for(int i= 0;admissionList[i]!=null;i++)
		{
			lengthAdmission++;
			admissionList[i].retrieveDocuments(consultantPatient,admissionList[i]);
		}
		numberOfAdmissions = lengthAdmission;
		
			listOfdocumentsGUI = currentAdmission.listOfDocuments;
			numberOfDocuments =currentAdmission.numberOfDocuments;
		singleDefinition = new Document[numberOfDocuments];
		createButtonTopBarAdmission();
		setActiveAdmissionTopPanelBttn(topBarAdmission1Bttn);
		createConsultantadmissionHomepagePanelGUI();//calls method which displays panel for the correct user
		setActiveTopPanelBttn(consultantPatientAdmission);
		consultantPatientAdmission.setEnabled(true);//the component is enabled to interact with the user
		consultantPatientDemographic.setEnabled(true);//the component is enabled to interact with the user
		
		}
		if(e.getSource()==viewPatient1AdmissionSpecificBttn)
		{
		consultantPatient = searchedPatient;
		currentPatient = consultantPatient;
		currentPatientConsultantIndex =0+currentPageIndexConsultant;
		admissionList=listofAdmissions[indexOfPatient];
		System.out.println(admissionList[0].admissionID);
		currentAdmission = listofAdmissions[indexOfPatient][0];
		int lengthAdmission = 0;
		for(int i= 0;admissionList[i]!=null;i++)
		{
			lengthAdmission++;
			admissionList[i].retrieveDocuments(consultantPatient,admissionList[i]);
		}
		numberOfAdmissions = lengthAdmission;
		
			listOfdocumentsGUI = currentAdmission.listOfDocuments;
			numberOfDocuments =currentAdmission.numberOfDocuments;
		singleDefinition = new Document[numberOfDocuments];
		createButtonTopBarAdmission();
		setActiveAdmissionTopPanelBttn(topBarAdmission1Bttn);
		createConsultantadmissionHomepagePanelGUI();//calls method which displays panel for the correct user
		setActiveTopPanelBttn(consultantPatientAdmission);
		consultantPatientAdmission.setEnabled(true);//the component is enabled to interact with the user
		consultantPatientDemographic.setEnabled(true);//the component is enabled to interact with the user
		
		}
		if(e.getSource()==viewPatient2bttn)
		{
			consultantPatient = listOfCsPatients[1+currentPageIndexConsultant];
			currentPatient = consultantPatient;
			currentPatientConsultantIndex =1+currentPageIndexConsultant;
			admissionList=listofAdmissions[1+currentPageIndexConsultant];
			currentAdmission = listofAdmissions[1+currentPageIndexConsultant][0];
			int lengthAdmission = 0;
			for(int i= 0;admissionList[i]!=null;i++)
			{
				admissionList[i].retrieveDocuments(consultantPatient,admissionList[i]);
				//System.out.println("Num of dos "+admissionList[i].listOfDocuments.length);
				lengthAdmission++;
			}
			//System.out.println("number of admissions "+ lengthAdmission);
			numberOfAdmissions = lengthAdmission;
			listOfdocumentsGUI = currentAdmission.listOfDocuments;
			numberOfDocuments =currentAdmission.numberOfDocuments;
			singleDefinition = new Document[numberOfDocuments];
			createButtonTopBarAdmission();//calls method which displays panel for the correct user
			setActiveAdmissionTopPanelBttn(topBarAdmission1Bttn);
		createConsultantadmissionHomepagePanelGUI();//calls method which displays panel for the correct user
		
		consultantPatientAdmission.setEnabled(true);//the component is enabled to interact with the user
		consultantPatientDemographic.setEnabled(true);//the component is enabled to interact with the user
		setActiveTopPanelBttn(consultantPatientAdmission);
		}
		if(e.getSource()==viewPatient3bttn)
		{
			consultantPatient = listOfCsPatients[2+currentPageIndexConsultant];
			currentPatient = consultantPatient;
			currentPatientConsultantIndex =2+currentPageIndexConsultant;
			admissionList=listofAdmissions[2+currentPageIndexConsultant];
			currentAdmission = listofAdmissions[2+currentPageIndexConsultant][0];
			int lengthAdmission = 0;
			
			for(int i= 0;admissionList[i]!=null;i++)
			{
				admissionList[i].retrieveDocuments(consultantPatient,admissionList[i]);
				lengthAdmission++;
			}
			//System.out.println("number of admissions "+ lengthAdmission);
			numberOfAdmissions = lengthAdmission;
			listOfdocumentsGUI = currentAdmission.listOfDocuments;
			numberOfDocuments =currentAdmission.numberOfDocuments;
			singleDefinition = new Document[numberOfDocuments];
			createButtonTopBarAdmission();
			setActiveAdmissionTopPanelBttn(topBarAdmission1Bttn);
		createConsultantadmissionHomepagePanelGUI();//calls method which displays panel for the correct user
		
		consultantPatientAdmission.setEnabled(true);//the component is enabled to interact with the user
		consultantPatientDemographic.setEnabled(true);//the component is enabled to interact with the user
		setActiveTopPanelBttn(consultantPatientAdmission);
		}
		
		if(e.getSource()==newPatientbttn)
		{
			removeAllPanels(oldpanel);//removes the old panel by passing that panel through a method which just removes it from the frame
			oldpanel = createPatientPanel;//sets the old panel to be the current page when it moves onto the next panel this one is removed
			setActiveTopPanelBttn(newPatientbttn); //active top bar is set to the button just pressed
			createCreatePatientPagePanelGUI();//correct panel is created
			createPatientPanel.setVisible(false);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
			createPatientPanel.setVisible(true);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
			currentPanel = createPatientPanel; //current panel is correctly updated
			
		}
		if(e.getSource()==jargonLibrarybttn)
		{
			createjargonLibraryHomepagePanelGUI();//correct panel is created
			emptyArray(jargonLibraryHomepagePanel);
			System.out.println("jargonLibraryHomepagePanel");
			setActiveTopPanelBttn(jargonLibrarybttn); //active top bar is set to the button just pressed 
			
			consultantPatientAdmission.setEnabled(false);//the component is disabled
			consultantPatientDemographic.setEnabled(false);//the component is disabled
		}
		if(e.getSource()==newAdmissionBttn)
		{
			createNewAdmissionPanel(newAdmissionPanel);//the new panel is created
			//this below fixes an issue where the screen would not appear when it should 
			newAdmissionPanel.setVisible(false);//the panel is hidden from sight	
			newAdmissionPanel.setVisible(true);//the panel is then made to reappear 
		}
		if(e.getSource()==topBarNewAdmissionBttn)
		{
			removeAllPanels(oldpanel);//the old panel is removed to make way for the new one
			oldpanel = newAdmissionPanel;//the current panel is declared as the old one for the next panel that may be loaded
			createNewAdmissionPanel(newAdmissionPanel);//the new panel is created
			//this below fixes an issue where the screen would not appear when it should 
			newAdmissionPanel.setVisible(false);//the panel is hidden from sight	
			newAdmissionPanel.setVisible(true);//the panel is then made to reappear 
		}
		
		if(e.getSource()==topBarAdmission1Bttn)
		{
			currentAdmission = admissionList[0];//assigns correct admission
			setActiveAdmissionTopPanelBttn(topBarAdmission1Bttn);//active button is set
			listOfdocumentsGUI = currentAdmission.listOfDocuments;//updates list to have the desired list the user wants
			numberOfDocuments =currentAdmission.numberOfDocuments;//finds the correct number of documents
			singleDefinition = new Document[numberOfDocuments];
			outputDocumentCards(currentAdmission);//the cards for the documents are outputted 
			createadmissionHomepagePanelGUI();//calls method which displays panel for the correct user
			indexofadmission = 0;
		}
		if(e.getSource()==topBarAdmission2Bttn)
		{
			currentAdmission = admissionList[1];//assigns correct admission
			numberOfDocuments =currentAdmission.numberOfDocuments;//finds the correct number of documents
			singleDefinition = new Document[numberOfDocuments];
			setActiveAdmissionTopPanelBttn(topBarAdmission2Bttn);//updates list to have the desired list the user wants
			listOfdocumentsGUI = currentAdmission.listOfDocuments;//active button is set
			outputDocumentCards(currentAdmission);//the cards for the documents are outputted 
			createadmissionHomepagePanelGUI();//calls method which displays panel for the correct user
			indexofadmission = 1;
		}
		if(e.getSource()==topBarAdmission3Bttn)
		{
			currentAdmission = admissionList[2];//assigns correct admission
			numberOfDocuments =currentAdmission.numberOfDocuments;//finds the correct number of documents
			singleDefinition = new Document[numberOfDocuments];
			setActiveAdmissionTopPanelBttn(topBarAdmission3Bttn);//updates list to have the desired list the user wants
			listOfdocumentsGUI = currentAdmission.listOfDocuments;//active button is set
			outputDocumentCards(currentAdmission);//the cards for the documents are outputted 
			createadmissionHomepagePanelGUI();//calls method which displays panel for the correct user
			indexofadmission = 2;
		}
		if(e.getSource()==topBarAdmission4Bttn)
		{
			currentAdmission = admissionList[3];//assigns correct admission
			numberOfDocuments =currentAdmission.numberOfDocuments;//finds the correct number of documents
			singleDefinition = new Document[numberOfDocuments];
			setActiveAdmissionTopPanelBttn(topBarAdmission4Bttn);//updates list to have the desired list the user wants
			listOfdocumentsGUI = currentAdmission.listOfDocuments;//finds the correct number of documents
			outputDocumentCards(currentAdmission);//the cards for the documents are outputted 
			createadmissionHomepagePanelGUI();//calls method which displays panel for the correct user
			indexofadmission = 3;
		}
		if(e.getSource()==topBarAdmission5Bttn)
		{
			currentAdmission = admissionList[4];//assigns correct admission
			setActiveAdmissionTopPanelBttn(topBarAdmission5Bttn);//updates list to have the desired list the user wants
			numberOfDocuments =currentAdmission.numberOfDocuments;//finds the correct number of documents
			singleDefinition = new Document[numberOfDocuments];
			listOfdocumentsGUI = currentAdmission.listOfDocuments;//finds the correct number of documents
			outputDocumentCards(currentAdmission);//the cards for the documents are outputted 
			createadmissionHomepagePanelGUI();//calls method which displays panel for the correct user
			indexofadmission = 4;
		}
		if(e.getSource()==backToAdmissionsBttn)
		{
			currentAdmission = admissionList[indexofadmission];//assigns correct admission
			if(currentAdmission ==admissionList[0])//selection determining if the current admission iss at this index
			{
				setActiveAdmissionTopPanelBttn(topBarAdmission1Bttn);//sets the correct top bar to the respective admission
			}
			else if(currentAdmission ==admissionList[1])//selection determining if the current admission iss at this index
			{
				setActiveAdmissionTopPanelBttn(topBarAdmission2Bttn);//sets the correct top bar to the respective admission
			}
			else if(currentAdmission ==admissionList[2])//selection determining if the current admission iss at this index
			{
				setActiveAdmissionTopPanelBttn(topBarAdmission3Bttn);//sets the correct top bar to the respective admission
			}
			else if(currentAdmission ==admissionList[3])//selection determining if the current admission iss at this index
			{
				setActiveAdmissionTopPanelBttn(topBarAdmission4Bttn);//sets the correct top bar to the respective admission
			}
			else if(currentAdmission ==admissionList[4])//selection determining if the current admission iss at this index
			{
				setActiveAdmissionTopPanelBttn(topBarAdmission5Bttn);//sets the correct top bar to the respective admission
			}
			outputDocumentCards(currentAdmission);//the cards for the documents are outputted 
			removePanel();
			createTopbarAdmission(admissionHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
			createadmissionHomepagePanelGUI();//creates the correct panel
			createContactBarAdmissionGeneral();//creates the correct panel
		}
		
		
		if(e.getSource()==logoutbttn)
		{
			String logoutConifimration = "Are you sure you want to logout?";//confirmation text to ensure this is the option the user wants
			String popUpWindowLogout = "Logout?";//text for frame
			int logoutConfirmationanswer = logoutConfirm.showConfirmDialog(null, logoutConifimration, popUpWindowLogout, logoutConfirm.YES_NO_OPTION);//type of popup is declared along with the answer types
			if (logoutConfirmationanswer == 0)//user wishes to logout
			{
				emptyArray(startPanel);
				System.out.println("startPanel");
				setActiveTopPanelBttn(homebttn);//active top bar is set to the button just pressed
				currentPanel = startPanel;//current panel is correctly updated
				loaded[6][0]= false;//the variable is set as true to prevent the components from being reran
				loaded[6][2]= false;//the variable is set as true to prevent the components from being reran
				loginChoice=-1;
			}
			else//wish to stay
			{
				logoutConfirm.setVisible(false);//popup is hidden from sight
			}
			logoutConfirm.setVisible(false);//popup is hidden from sight regardless of choice as it wont dissapear after intialisation 
		}
		
		if(e.getSource() ==closeNotificationBttn1)//if corrosponding button is pressed that text within the array is removed
		{
			deleteNotification(1);//method is ran which removes notifiaction from the array and is then updated graphically
		}
		if(e.getSource() ==closeNotificationBttn2)//if corrosponding button is pressed that text within the array is removed
		{
			deleteNotification(2);//method is ran which removes notifiaction from the array and is then updated graphically
		}
		if(e.getSource() ==closeNotificationBttn3)//if corrosponding button is pressed that text within the array is removed
		{
			deleteNotification(3);//method is ran which removes notifiaction from the array and is then updated graphically
		}
		if(e.getSource() ==closeNotificationBttn4)//if corrosponding button is pressed that text within the array is removed
		{
			deleteNotification(4);//method is ran which removes notifiaction from the array and is then updated graphically
		}
		if(e.getSource() ==closeNotificationBttn5)//if corrosponding button is pressed that text within the array is removed
		{
			deleteNotification(5);//method is ran which removes notifiaction from the array and is then updated graphically
		}
		if(e.getSource() ==viewDocument1AdmissionBttn)
		{
			currentDocument = listOfdocumentsGUI[0+currentPageIndex];//assigns the current document to the correct index
			createDocumentPanel(currentDocument);//calls method which displays panel for the correct user
			
		}
		if(e.getSource() ==viewDocument2AdmissionSpecificBttn)
		{
			currentDocument = singleDefinition[1+currentPageIndex];//assigns the current document to the correct index
			createDocumentPanel(currentDocument);//calls method which displays panel for the correct user
			
		}
		if(e.getSource() ==viewDocument3AdmissionSpecificBttn)
		{
			currentDocument = singleDefinition[2+currentPageIndex];//assigns the current document to the correct index
			createDocumentPanel(currentDocument);//calls method which displays panel for the correct user
			
		}
		if(e.getSource() ==viewDocument4AdmissionSpecificBttn)
		{
			currentDocument = singleDefinition[3+currentPageIndex];//assigns the current document to the correct index
			createDocumentPanel(currentDocument);//calls method which displays panel for the correct user
			
		}
		if(e.getSource() ==viewDocument5AdmissionSpecificBttn)
		{
			currentDocument = singleDefinition[4+currentPageIndex];//assigns the current document to the correct index
			createDocumentPanel(currentDocument);//calls method which displays panel for the correct user
			
		}
		if(e.getSource() ==viewDocument6AdmissionSpecificBttn)
		{
			currentDocument = singleDefinition[5+currentPageIndex];//assigns the current document to the correct index
			createDocumentPanel(currentDocument);//calls method which displays panel for the correct user
			
		}
		if(e.getSource() ==viewDocument1AdmissionSpecificBttn)
		{
			currentDocument = singleDefinition[0+currentPageIndex];//assigns the current document to the correct index
			createDocumentPanel(currentDocument);//calls method which displays panel for the correct user
			
		}
		if(e.getSource() ==viewDocument1AdmissionSearchBttn)
		{
			currentDocument = currentAdmission.listOfDocuments[indexofDoc];//assigns the current document to the correct index
			createDocumentPanel(currentDocument);//calls method which displays panel for the correct user
			
		}
		if(e.getSource() ==viewDocument2AdmissionBttn)
		{
			currentDocument = listOfdocumentsGUI[1+currentPageIndex];//assigns the current document to the correct index
			createDocumentPanel(currentDocument);//calls method which displays panel for the correct user
			
			
		}
		if(e.getSource() ==viewDocument3AdmissionBttn)
		{
			currentDocument = listOfdocumentsGUI[2+currentPageIndex];//assigns the current document to the correct index
			createDocumentPanel(currentDocument);//calls method which displays panel for the correct user
			
		}
		if(e.getSource() ==viewDocument4AdmissionBttn)
		{
			currentDocument = listOfdocumentsGUI[3+currentPageIndex];//assigns the current document to the correct index
			createDocumentPanel(currentDocument);//calls method which displays panel for the correct user
			
			
		}
		if(e.getSource() ==viewDocument5AdmissionBttn)
		{
			currentDocument = listOfdocumentsGUI[4+currentPageIndex];//assigns the current document to the correct index
			createDocumentPanel(currentDocument);//calls method which displays panel for the correct user
			
			
		}
		if(e.getSource() ==viewDocument6AdmissionBttn)
		{
			
			currentDocument = listOfdocumentsGUI[5+currentPageIndex];//assigns the current document to the correct index
			createDocumentPanel(currentDocument);//calls method which displays panel for the correct user
		}
	

	if(e.getSource()==saveDemographicChanges)
		{
			currentPatient.firstName = demographicFNameTextFeild.getText();//retrieves information from component
			currentPatient.surName = demographicSNTextFeild.getText();//retrieves information from component
			try
			{
				currentPatient.dob = ft.parse(demographicDOBTextFeild.getText());//retrieves information from component
			}
			catch(Exception exc)//if any errrors are found they are caught here		
			{
			}
			currentPatient.addressHouseNum = Integer.parseInt(demographicBuildingNUMTextFeild.getText());//retrieves information from component
			currentPatient.addressHouseStreet = demographicStreetTextFeild.getText();//retrieves information from component
			currentPatient.postcode = demographicPostcode.getText();//retrieves information from component
			currentPatient.town = demographicTownTextFeild.getText();//retrieves information from component
			currentPatient.gender = ((String) demographicGender.getSelectedItem()).charAt(0);//retrieves information from component
			currentPatient.contactNum = demographicContactinfo.getText();//retrieves information from component
			currentPatient.religon = demographicReligonTextFeild.getText();//retrieves information from component
			currentPatient.bloodType =(String) demographicBloodType.getSelectedItem();//retrieves information from component
			currentPatient.nationality = demographicNotioanlityTextFeild.getText();//retrieves information from component
			currentPatient.allergies = demographicAllergies.getText();//retrieves information from component
			currentPatient.smoker = demographicSmokerPromptLbl.isSelected();//retrieves information from component
			currentPatient.drinker = demographicDrinkerPromptLbl.isSelected();//retrieves information from component
			currentPatient.disability = demographicDisablitiesTextFeild.getText();//retrieves information from component
			currentPatient.carer = demographicCarerPromptLbl.isSelected();//retrieves information from component
			currentPatient.translator = demographicTranslatorPromptLbl.isSelected();//retrieves information from component
			PatientList pl = new PatientList();//creates new instance of patient list
			pl.updatePatientDemo(currentPatient);//calls the update patient demograpgic method passing through the current patient 
		}
		if(e.getSource() ==newAccountcreateAccountbbtn)
		{
			createNewPatientAccount();
			if(newUser == true)
			{
				newUser = false;
				createAdmission();
				
			}
		}

		if(e.getSource()==newDocumentButton)
		{
			createNewDocumentPage();//creates a new page for docuemtns
		}
		if(e.getSource()==returnBackToadmissionPage)
		{
			createConsultantadmissionHomepagePanelGUI();//creates a new page for docuemtns
			emptyArray(consultantAdmissionPanel);
			createTopbarAdmission(consultantAdmissionPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
			System.out.print("!");
			
		
		System.out.println("Pointer is at "+endPanelPointer);
		}
		if(e.getSource()==docTypePrescriptionBttn)
		{
			setActiveTopPanelDocumentBttn(docTypePrescriptionBttn);//sets actyive document
			
			currentDocument.docType = "Prescription";//sets type of document
			assignBasicAttributesToDocument();//assigns attributes to the document
			createPrescriptionComponents();//creates components for the document
			oldDocType= "Prescription";//sets type of document
		}
		if(e.getSource()==docTypeDischargmentBttn)
		{
			setActiveTopPanelDocumentBttn(docTypeDischargmentBttn);//sets actyive document
			
			currentDocument.docType = "Dischargment";//sets type of document
			assignBasicAttributesToDocument();//assigns attributes to the document
			createDischargmentComponents();//creates components for the document
			oldDocType= "Dischargment";//sets type of document
		}
		if(e.getSource()==docTypeTestResultsBttn)
		{
			setActiveTopPanelDocumentBttn(docTypeTestResultsBttn);//sets actyive document
			currentDocument.docType = "Test Results";//sets type of document
			assignBasicAttributesToDocument();//assigns attributes to the document
			createTestResultsComponents();//creates components for the document
			oldDocType= "Test Results";//sets type of document
		}
		if(e.getSource()==docTypeNotesBttn)
		{
			setActiveTopPanelDocumentBttn(docTypeNotesBttn);//sets actyive document
			
			currentDocument.docType = "Consultant Notes";//sets type of document
			assignBasicAttributesToDocument();//assigns attributes to the document
			createNotesComponents();//creates components for the document
			oldDocType= "Consultant Notes";//sets type of document
		}
		
		
		if(e.getSource()==createNewDocumentButton)
		{
			System.out.println("============");
			for(int i= 0;admissionList[i]!=null;i++)
			{
				System.out.println(admissionList[i].admissionID);
			}
			listOfdocumentsGUI = sortlistofdocumentsArray(2,listOfdocumentsGUI);//sorts the lsit to the corrosponding order
			currentAdmission.listOfDocuments=listOfdocumentsGUI;
			if(reinstatePatient==true)//selection determining if the patient has been reinstated
			{
				currentAdmission.active=true;//sets current admisison as active agian
			}
			Document tempDocument = new Document();//creates a new instance of document
			tempDocument.documentID = tempDocument.createUniqueID(currentPatient.surName,currentPatient.patientID+"_"+currentAdmission.admissionID+"_Documentation.txt","D");//creates  a unique id
			String stringedDocument="";//declares empty variable
			Date actionCreated = new Date();//creates new instance of this object
			tempDocument.dateOfDocumentCreation = actionCreated;//assigns the current date to the object
			if(currentDocument.docType =="Consultant Notes")//selection determining if the current document is of this type
			{
				actionPerformed = 4;//sets the action to this value
				tempDocument.notes=textDocumentTAND.getText();//retrievs text from component
				stringedDocument = tempDocument.documentID+","+ftTimeInc.format(actionCreated)+","+"Consultant Notes"+",,,,,"+ tempDocument.notes+",, ";//concatenates the data togther
			}
			if(currentDocument.docType =="Reinstatement")//selection determining if the current document is of this type
			{
				actionPerformed = 3;//sets the action to this value
				tempDocument.notes=textDocumentTAND.getText();//retrievs text from component
				stringedDocument = tempDocument.documentID+","+ftTimeInc.format(actionCreated)+","+"Reinstatement"+",,,,,"+ tempDocument.notes+",, ";//concatenates the data togther
			}
			if(currentDocument.docType =="Test Results")//selection determining if the current document is of this type
			{
				actionPerformed = 2;//sets the action to this value
				tempDocument.notes=textDocumentTAND.getText();//retrievs text from component
				tempDocument.summary=(String) summaryOptions.getSelectedItem();//retrievs text from component
				stringedDocument = tempDocument.documentID+","+ftTimeInc.format(actionCreated)+","+"Test Results"+",,,,,,"+ tempDocument.notes+","+tempDocument.summary;//concatenates the data togther
			}
			if(currentDocument.docType =="Dischargment")//selection determining if the current document is of this type
			{
				currentAdmission.active = false;//discharges the patient 
				actionPerformed = 1;//sets the action to this value
				stringedDocument = tempDocument.documentID+","+ftTimeInc.format(actionCreated)+","+"Dischargment"+",,,,,,, ";//concatenates the data togther
			}
			if(currentDocument.docType =="Prescription")//selection determining if the current document is of this type
			{
				actionPerformed = 0;//sets the action to this value
				
				tempDocument.medicationName =medicationDocumentTA.getText();//retrievs text from component
				tempDocument.medicationDosage = dosageDocumentTA.getText();//retrievs text from component
				tempDocument.medicationIntakeTime =intakeTimeDocumentTA.getText();//retrievs text from component
				try{
				tempDocument.medicationDateOfNextDispatch = ftTimeInc.parse(DONDDocumentTA.getText());//retrievs text from component
				}
				catch(Exception exc)
				{
				}
				stringedDocument = tempDocument.documentID+","+ftTimeInc.format(actionCreated)+","+"Prescription"+","+tempDocument.medicationName+","+tempDocument.medicationDosage+","+(tempDocument.medicationIntakeTime)+","+ftTimeInc.format(tempDocument.medicationDateOfNextDispatch)+", , , ";//concatenates the data togther
			}
			currentAdmission.numberOfDocuments++;//increments the number of docuemtns the admission has
			currentManager.writeActionToFile(currentConsultant,tempDocument,currentAdmission,currentPatient,actionPerformed);//writes action to file
			try{
				FileReader fra = new FileReader(currentPatient.patientID+"_"+currentAdmission.admissionID+"_Documentation.txt");//decalres a new file reader called fr
				BufferedReader bra = new BufferedReader(fra);//declares a new bufferedreader called br using fr as a parameter to be passed along
				String lineOfDataa = bra.readLine();//saves the line read from file to the variable
				int line = 0;
				while(lineOfDataa !=null)//a while loop that runs untill the variable read is not empty(The end of the array is not reached)
				{
					line++;//increments variable
					lineOfDataa = bra.readLine();//this then reads from file again to check if not empty
				}
				String[] arrayofDocuments = new String[line+1];//declares array with a size +1
				FileReader fr = new FileReader(currentPatient.patientID+"_"+currentAdmission.admissionID+"_Documentation.txt");//decalres a new file reader called fr
				BufferedReader br = new BufferedReader(fr);//declares a new bufferedreader called br using fr as a parameter to be passed along
				String lineOfData = br.readLine();//saves the line read from file to the variable
				for(int count=0;count<arrayofDocuments.length-1;count++)//for loop that runs for the number of docuemtns of the old array
				{
					arrayofDocuments[count] = lineOfData;//assigns new line to latest line read

					lineOfData = br.readLine();//this then reads from file again to check if not empty
				}
					arrayofDocuments[arrayofDocuments.length-1] = stringedDocument;//adds newest document back to the  array
					FileWriter fw = new FileWriter(currentPatient.patientID+"_"+currentAdmission.admissionID+"_Documentation.txt");//declare a new file writer that will ammend not not write to file
				for(int counter = 0;counter<arrayofDocuments.length;counter++)//for loop that runs for the number of docuemtns of the new array
				{
					fw.write(arrayofDocuments[counter]);//adds document to file
					fw.write("\r\n");//adds in a space to the file
				}
				fw.close();//closes the file
				AdmissionList al = new AdmissionList();//creates new instance of object
				al.updatePatientAdmission(currentPatient,currentAdmission);//updates patient information
			}
			catch(Exception exc)
			{
				exc.printStackTrace();
				System.out.println("read error");
			}
			if(currentAdmission ==admissionList[0])
			{
				setActiveAdmissionTopPanelBttn(topBarAdmission1Bttn);
			}
			else if(currentAdmission ==admissionList[1])
			{
				setActiveAdmissionTopPanelBttn(topBarAdmission2Bttn);
			}
			else if(currentAdmission ==admissionList[2])
			{
				setActiveAdmissionTopPanelBttn(topBarAdmission3Bttn);
			}
			else if(currentAdmission ==admissionList[3])
			{
				setActiveAdmissionTopPanelBttn(topBarAdmission4Bttn);
			}
			else if(currentAdmission ==admissionList[4])
			{
				setActiveAdmissionTopPanelBttn(topBarAdmission5Bttn);
			}
			System.out.println("============");
			for(int i= 0;admissionList[i]!=null;i++)
			{
				System.out.println(admissionList[i].admissionID);
			}
			
			updatePatient();
			refreshConsultantInfo();
			admissionList=listofAdmissions[currentPatientConsultantIndex];
			int lengthAdmission = 0;
			for(int i= 0;admissionList[i]!=null;i++)
			{
				lengthAdmission++;
				
			}
			numberOfAdmissions = lengthAdmission;
			System.out.println("============");
			for(int i= 0;admissionList[i]!=null;i++)
			{
				System.out.println(admissionList[i].admissionID);
			}
			currentAdmission.retrieveDocuments(consultantPatient,currentAdmission);
			listOfdocumentsGUI = currentAdmission.listOfDocuments;
			numberOfDocuments =currentAdmission.numberOfDocuments;
			singleDefinition = new Document[numberOfDocuments];
			createButtonTopBarAdmission();
			createConsultantadmissionHomepagePanelGUI();//creates the correct panel	
			createContactBarAdmissionGeneral();//creates the correct panel
			
		}
		if(e.getSource()==consultantPatientDemographic)
		{
			consultantPatientDemographicPanel();//creates desired panel
		}
		if(e.getSource()==consultantPatientAdmission)
		{
			createConsultantadmissionHomepagePanelGUI();//creates desired panel
			createTopbarAdmission(consultantAdmissionPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 

		}
		if(e.getSource()==editAdmissionBttn)
		{
			createEditEditAdmissionPanel();//creates desired panel
		}
		if(e.getSource()==backToAdmissionConBttn)
		{
			removePanel();
			createConsultantadmissionHomepagePanelGUI();//creates desired panel
			createTopbarAdmission(consultantAdmissionPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 

		}
		
		if(e.getSource()==reinstatePatientBttn)
		{
			createNewDocumentPage();//creates desired panel
			setActiveTopPanelDocumentBttn(docTypeNotesBttn);//sets the top bar panel to the correct location
			currentDocument.docType = "Reinstatement";//assigns type of document
			assignBasicAttributesToDocument();//assigns the correct attributes to the document
			createNotesComponents();//creates desired panel
			oldDocType= "Reinstatement";//assigns the type of document to the variable
			textDocumentTA.setText("Dr "+currentConsultant.surName+" believes that the current patient "+currentPatient.firstName+" "+currentPatient.surName+" needs to have the Current admission "+currentAdmission.admissionID+" reinstated. Therefore as of today the admission is to be treated as such.");//sets text to the component
			reinstatePatient = true;//reinstates the patient 
		}
    
		if(e.getSource()==saveChangesToAdmisionBttn)	
		{
			AdmissionList al = new AdmissionList();//creates new instance of this object
			oldAdmissionInfo=currentConsultant.copyAttributesAdmission(currentAdmission);//assigns old attribute information to a copied instance
			
		if(dischargeLblPrompt.isSelected()==true)//selection determining if the consultant wants the patient discharged
		{
			currentAdmission.active=false;//discharges the admission
			String stringedDocument="";//declares an empty string
			Document tempDocument = new Document();//creates new instance of this object
			actionPerformed = 1;//sets the action as dischraging the patient
			tempDocument.documentID = tempDocument.createUniqueID(currentPatient.surName,currentPatient.patientID+"_"+currentAdmission.admissionID+"_Documentation.txt","D");//creates a unique id for the document 
			stringedDocument = tempDocument.documentID+","+ftTimeInc.format(new Date())+","+"Dischargment"+",,,,,,, ";//concatenates data for the document
			currentAdmission.numberOfDocuments++;//increments the number of documents the patient has in the admission
			try{
			
				FileReader fra = new FileReader(currentPatient.patientID+"_"+currentAdmission.admissionID+"_Documentation.txt");//decalres a new file reader called fr
				BufferedReader bra = new BufferedReader(fra);//declares a new bufferedreader called br using fr as a parameter to be passed along
				String lineOfDataa = bra.readLine();//saves the line read from file to the variable
				int line = 0;
				while(lineOfDataa !=null)//a while loop that runs untill the variable read is not empty(The end of the array is not reached)
				{
					line++;//increments line
					lineOfDataa = bra.readLine();//this then reads from file again to check if not empty
				}
				//System.out.println("Step 1");
					String[] arrayofDocuments = new String[line+1];//creates a new array with an extra index in length
					FileReader fr = new FileReader(currentPatient.patientID+"_"+currentAdmission.admissionID+"_Documentation.txt");//decalres a new file reader called fr
				BufferedReader br = new BufferedReader(fr);//declares a new bufferedreader called br using fr as a parameter to be passed along
				String lineOfData = br.readLine();//saves the line read from file to the variable
				//writes every line of file before desired locatio to the array
				for(int count=0;count<arrayofDocuments.length-1;count++)//declares a of loop that will run for the length of the old array
				{
					arrayofDocuments[count] = lineOfData;//saves the line read from file to the file
					
					//System.out.println(arrayofDocuments[count]);
					lineOfData = br.readLine();//this then reads from file again to check if not empty
				}
				//System.out.println("Step 2");
					arrayofDocuments[arrayofDocuments.length-1] = stringedDocument;
					//adds the new admission
					//System.out.println(arrayofDocuments[arrayofDocuments.length-1]+"HEll");
					//adds rest to array
					FileWriter fw = new FileWriter(currentPatient.patientID+"_"+currentAdmission.admissionID+"_Documentation.txt");//declare a new file writer that will ammend not not write to file
				for(int counter = 0;counter<arrayofDocuments.length;counter++)//declares a of loop that will run for the length of the new array
				{
					fw.write(arrayofDocuments[counter]);//writes the line to file
					fw.write("\r\n");//adds in a space to the file
				}
				//System.out.println("Step 3");
				fw.close();//closes the file
				currentManager.writeActionToFile(currentConsultant,tempDocument,currentAdmission,currentPatient,actionPerformed);//writes the consultants action to file for their action log
				AdmissionList al1 = new AdmissionList();//creates new instance of the object
				al1.updatePatientAdmission(currentPatient,currentAdmission);//updates the attributes to the patient 
			}
			catch(Exception exc)
			{
				System.out.println("read error");//informs of error
			}
		}
		currentAdmission.ward = wardAdmissionTextField.getText();//retrieves attribute from component
		currentAdmission.staffName = StaffsNameTextField.getText();//retrieves attribute from component
		currentAdmission.room = roomTextField.getText();//retrieves attribute from component
		currentAdmission.currentDiagnosis = currentDiagnosisTextField.getText();//retrieves attribute from component
		//System.out.println(oldAdmissionInfo.room);
		String symptomLine = "";//declares an empty string
		String[] symptomsStringed  = listOfCurrnetSymptomsTextPane.getText().split("\\n");//splits line up
		Admission newAdmissionInfo = currentAdmission;//declares the new admission to the newly updated admission
		for(int count =0;count<currentAdmission.listOfSymptoms.length;count++)//runs a for loop that iterates through every list of symptoms
		{
			currentAdmission.listOfSymptoms[count] = symptomsStringed[count];//assigns attribute from list that was split
			
		}
		listOfCurrnetSymptomsTextPane.setText(currentAdmission.listOfSymptoms[0]+"\n"+currentAdmission.listOfSymptoms[1]+"\n"+currentAdmission.listOfSymptoms[2]);//sets text to the component
			
			
			if(staffNameTextField.getSelectedItem()!=currentConsultant.consultantID +","+currentConsultant.firstName +","+ currentConsultant.surName)
			{
				actionPerformed = 7;
				Document tempDocument = new Document();//creates new instance of this object
				ConsultantList cl = new ConsultantList();//creates new instance of this object
				String newConsultantBasicInfo = (String) staffNameTextField.getSelectedItem();//retrieves attribute from component
				//System.out.println(newConsultantBasicInfo);
				String[] splitNewConsultantBasicInfo =  cl.unConcatenateStringConsultanttxt(newConsultantBasicInfo,",");//splits string every comma seen
				int numberOfPatients = currentConsultant.numberOfPatients;//finds out how mant patients the current consultant has
				currentConsultant.numberOfPatients = currentConsultant.removepatientToConsultantRecord(currentAdmission.admissionsConsultantID,currentPatient.patientID,currentAdmission.admissionID);//updates the number of patients the consultant has correctly
				currentAdmission.admissionsConsultantID = splitNewConsultantBasicInfo[0];//assigns attribute from array
				currentAdmission.consultantName = splitNewConsultantBasicInfo[1];//assigns attribute from array
				
					String tempConsultantID = (String) staffNameTextField.getSelectedItem();//retrieves attribute from component
					currentConsultant.newConsultantID = tempConsultantID.substring(0,11);//substribngs the string to get the id
					//System.out.println("Increment other consultant"+tempConsultantID.substring(0,11)+"TEST");
					
				for(int count = 0;count<listOfAllConsultants.length;count++)//runs a for loop for the number of consultants are present on the system
				{
					if((tempConsultantID.substring(0,11) == listOfAllConsultants[count].consultantID))//checks to see if the temporary consultant equals the consultant in the array
					{
						locationOfReplacementConsultant = count;//sets postition of the desired consultant
						
					}
				}
				listOfAllConsultants[locationOfReplacementConsultant] = currentConsultant.updatePatientAdmissionToConsultantRecord(tempConsultantID.substring(0,11),currentPatient.patientID,currentAdmission.admissionID);//updates the file of consultants 
				currentManager.writeActionToFile(currentConsultant,tempDocument,currentAdmission,currentPatient,actionPerformed);//writes action performed by the consultant
				for(int index = 0;index<numberOfAdmissions;index++)
				{
					System.out.println(listofAdmissions[currentPatientConsultantIndex][index]);
				}
				for(int index = numberOfAdmissions;index>indexofadmission;index--)
				{
					listofAdmissions[currentPatientConsultantIndex][index-1] =listofAdmissions[currentPatientConsultantIndex][index]; 
				}
				numberOfAdmissions--;
				for(int index = numberOfAdmissions;index>indexofadmission;index--)
				{
					listofAdmissions[currentPatientConsultantIndex][index-1] =listofAdmissions[currentPatientConsultantIndex][index]; 
				}
				admissionList =listofAdmissions[currentPatientConsultantIndex];
			}
			//System.out.println(oldAdmissionInfo.room);
			actionPerformed = 6;//sets the action performed by the consultant
			Document tempDocument = new Document();//creates new instance of this object
			currentManager.writeActionToFile7(currentConsultant,tempDocument,oldAdmissionInfo,currentPatient,actionPerformed,newAdmissionInfo,symptomsStringed);//writes the new and old patient details to file
			al.updatePatientAdmission(currentPatient,currentAdmission);//updates the patient information
			refreshConsultantInfo();//retireives the new updates information for that consultant
			loaded[6][2] = false;//the variable is set as true to prevent the components from being reran
			createTopbarAdmission(consultantAdmissionPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels
			createConsultantHomepagePanelGUI(currentConsultant);//creates the correct panel
			createButtonTopBarAdmission();
		}
	
		if(e.getSource()==upButton)
		{
			currentPageIndexDefenitions= currentPageIndexDefenitions - 8;//moves the indexes back a page
			currentPageDefenitions--;//goes back a page
			formatJargonCards(listOfDefinitionsUpdated);//creates the correct panel
		}
		if(e.getSource()==downButton)
		{
			currentPageIndexDefenitions= currentPageIndexDefenitions + 8;//moves the indexes forward a page
			currentPageDefenitions++;//moves onto the next page
			formatJargonCards(listOfDefinitionsUpdated);//creates the correct panel
		}
		if(e.getSource()==searchLibraryBttn)
		{
			sortBoxEmployeeAdmissionPanel.setEnabled(false);//the component is disabled
			int indexOfDefinition = searchAlgorithm(searchBarJargon.getText(),listOfDefinitions);//finds the index where the definition exists
			if(indexOfDefinition>=0)//selection determining if there at least one
			{
				String[] singleDefinition = listOfDefinitions[indexOfDefinition];//assigns the list of definition as the indexed list of definitions
				formatJargonCards(singleDefinition);//formats cards
				upButton.setEnabled(false);//the component is disabled
			downButton.setEnabled(false);//the component is disabled
			}
			else{
			jargonHideDef1();//hides definition card
			jargonHideDef2();//hides definition card
			jargonHideDef3();//hides definition card
			jargonHideDef4();//hides definition card
			jargonHideDef5();//hides definition card
			jargonHideDef6();//hides definition card
			jargonHideDef7();//hides definition card
			jargonHideDef8();//hides definition card
			upButton.setEnabled(false);//the component is disabled
			downButton.setEnabled(false);//the component is disabled
			JOptionPane.showMessageDialog(null, "No definition found");
			
			}
			clearSearchbttn.setEnabled(true);//the component is enabled to interact with the user
		}
		if(e.getSource()==clearSearchbttn)
		{
			sortBoxEmployeeAdmissionPanel.setEnabled(true);//the component is enabled to interact with the user
			searchBarJargon.setText("  Search:");//sets text to the component
			clearSearchbttn.setEnabled(false);//disables button
			listOfDefinitionsUpdated = listOfDefinitions;//updates list to contain no definitions
		formatJargonCards(listOfDefinitionsUpdated);//creates the correct panel
		}
		if(e.getSource()==clearSearchbttnAdmission)
		{
			sortBoxAdmissionHpagePanel.setEnabled(true);//the component is enabled to interact with the user
			searchBoxAdmissionHpagePanel.setText("  Search:");//sets text to the component
			clearSearchbttnAdmission.setEnabled(false);//disables button
			listOfdocumentsGUI = currentAdmission.listOfDocuments;
			outputDocumentCards(currentAdmission);//the cards for the documents are outputted );//creates the correct panel
		}
		if(e.getSource()==searchLibraryBttnAdmission)
		{
			sortBoxAdmissionHpagePanel.setEnabled(false);//the component is disabled
			int indexOfDocument = searchAlgorithmAdmissionPage(searchBoxAdmissionHpagePanel.getText(),currentAdmission.listOfDocuments);//finds the index where the definition exists
			indexofDoc =indexOfDocument;
			if(indexOfDocument>=0)//selection determining if there at least one
			{
				tempDoc = currentAdmission.listOfDocuments[indexOfDocument];//assigns the list of definition as the indexed list of definitions
				admissioncardTitlepanel1.setText(tempDoc.docType+" "+ft.format(tempDoc.dateOfDocumentCreation));//formatting component to include text
				admissioncardTitlepanel1.setVisible(true);//the component is made visible
				viewDocument1AdmissionBttn.setVisible(false);//the component is made visible
				viewDocument1AdmissionSpecificBttn.setVisible(false);//the component is made visible
				viewDocument1AdmissionSearchBttn.setVisible(true);//the component is made visible
				admissioncardDescriptionpane1.setVisible(true);//the component is made visible
				whiteBox4.setVisible(true);//the component is made visible
				if(tempDoc.docType.equals("Test Results")==true)//selection determining if the current attribute meets the condition
				{
					admissioncardDescriptionpane1.setText("Summary: "+tempDoc.summary);//formatting component to include text
				}
				else{
					admissioncardDescriptionpane1.setText("");
				}
			
			admissionhideCard2();//hides definition card
			admissionhideCard3();//hides definition card
			admissionhideCard4();//hides definition card
			admissionhideCard5();//hides definition card
			admissionhideCard6();//hides definition card
				admissionCardPageForwardBttn.setEnabled(false);//the component is disabled
				admissionCardPageBackBttn.setEnabled(false);//the component is disabled
				listOfdocumentsGUI = currentAdmission.listOfDocuments;
			}
			else{
			admissionhideCard1();//hides definition card
			admissionhideCard2();//hides definition card
			admissionhideCard3();//hides definition card
			admissionhideCard4();//hides definition card
			admissionhideCard5();//hides definition card
			admissionhideCard6();//hides definition card
			admissionCardPageForwardBttn.setEnabled(false);//the component is disabled
			admissionCardPageBackBttn.setEnabled(false);//the component is disabled
			JOptionPane.showMessageDialog(null, "No Document found");
			
			}
			clearSearchbttnAdmission.setEnabled(true);//the component is enabled to interact with the user
		}
		if(e.getSource()==clearSearchbttnConsulant)
		{
			sortBoxConsultantHpagePanel.setEnabled(true);//the component is disabled
			searchBoxConsultantHpagePanel.setText("  Search:");//sets text to the component
			clearSearchbttnConsulant.setEnabled(false);//disables button
			outputPatientCards();
		}
		if(e.getSource()==searchLibraryBttnConsulant)
		{
			sortBoxConsultantHpagePanel.setEnabled(false);//the component is disabled
			indexOfPatient = searchAlgorithmConsultantPage(searchBoxConsultantHpagePanel.getText(),listOfCsPatients);//finds the index where the definition exists
			
			if(indexOfPatient>=0)//selection determining if there at least one
			{
				searchedPatient = listOfCsPatients[indexOfPatient];
				patientCard1FirstName.setText(searchedPatient.firstName);//formatting component to include text
				patientCard1SurName.setText(searchedPatient.surName);//formatting component to include text
				patientCard1PatientID.setText(searchedPatient.patientID);//formatting component to include text
				patientCard1AdmissionID.setText(listofAdmissions[indexOfPatient][0].admissionID);//formatting component to include text
				patientCard1Diagnosis.setText("Depression");//formatting component to include text
				patientCard1DONAlbl.setText("Date Of Next Appointment");//formatting component to include text
				patientCard1Date.setText("25/08/2019");//formatting component to include text
				moreAdmissions1lbl.setText("+ 1 other admission");//formatting component to include text
				patientCard1FirstName.setVisible(true);// the component is made visible so that it can be seen
				patientCard1SurName.setVisible(true);// the component is made visible so that it can be seen
				patientCard1PatientID.setVisible(true);// the component is made visible so that it can be seen
				patientCard1AdmissionID.setVisible(true);// the component is made visible so that it can be seen
				patientCard1Diagnosis.setVisible(true);// the component is made visible so that it can be seen
				patientCard1DONAlbl.setVisible(true);// the component is made visible so that it can be seen
				patientCard1Date.setVisible(true);// the component is made visible so that it can be seen
				moreAdmissions1lbl.setVisible(true);// the component is made visible so that it can be seen
				viewPatient1AdmissionSpecificBttn.setVisible(true);// the component is made visible so that it can be seen
				patient1image.setVisible(true);// the component is made visible so that it can be seen
				whiteBox4.setVisible(true);// the component is made visible so that it can be seen
				viewPatient1bttn.setVisible(false);
				consultanthideCard3();
				consultanthideCard2();
				ConsultantCardPageForwardBttn.setEnabled(false);//the component is disabled
				ConsultantCardPageBackBttn.setEnabled(false);//the component is disabled
				
			}
			else{
			consultanthideCard3();
				consultanthideCard2();
				consultanthideCard1();
			ConsultantCardPageForwardBttn.setEnabled(false);//the component is disabled
			ConsultantCardPageBackBttn.setEnabled(false);//the component is disabled
			JOptionPane.showMessageDialog(null, "No patient found");
			
			}
			clearSearchbttnConsulant.setEnabled(true);//the component is enabled to interact with the user
		}
	
	}
	
    public static void main(String[] args )//declaring the main method
    {
        temp loginObj = new temp();//creates the primary object of login
        loginObj.startFrameGUI();//the objects then performs the inital start method
		
    }
}  
