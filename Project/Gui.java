//============= IMPORTS ====================

//basic importd
import java.awt.*;//imports the java awt package,by using a wildcard all aspects of the package are imported
import java.awt.font.TextAttribute;//imports the text attribute package 
import java.text.*;//imports the simple date format package which allows for more simple dates
import java.util.Random;//imports the java utilities for the random function
import java.util.*;//imports the java utlities module
import java.io.*;//imports the io package

//Time imports
import java.time.*;
import java.text.SimpleDateFormat;//imports the simple date format package which allows for more simple dates

//Other Graphical compoenents imports
import java.awt.Component;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

//Event imports
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.*;//imports the java awt event package,by using a wildcard all aspects of the package are imported

//Swing importd
import javax.swing.SwingConstants;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.ActionMap;
import javax.swing.filechooser.FileFilter;
import javax.swing.JScrollPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.*;//imports the java swing package,by using a wildcard all aspects of the package are imported
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;
import javax.swing.*;//imports the java swing package,by using a wildcard all aspects of the package are imported
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;

	//custom date picker import from the folder 
import com.github.lgooddatepicker.components.*;
import com.github.lgooddatepicker.optionalusertools.*;
import com.github.lgooddatepicker.zinternaltools.*;
public class Gui extends JFrame implements ActionListener//declares the class that inherits everything from the JFrame class which allows us to create coponenets of GUIS from the JFrame library 
{
	//======================== external class instance declerations ===================
	
	/*
	Here we are initalising some custom/overwritten methods that will need to be called in the system
	<SEE BOTTOM OF FILE FOR CLASSES>
	*/
	SampleDateChangeListener listner = new SampleDateChangeListener();
	documentTextStateChangeListner documentTfListner = new documentTextStateChangeListner();
	buttonItemListner checkBoxListner = new buttonItemListner();
	ComponentWindowListener customWindowListner = new ComponentWindowListener();
	WindowEmterListener windowEmterListener = new WindowEmterListener();
	
	//======================== Date formatting attributes ===================
	
	SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");//declares a simple font for a visual improvement over the full version
	SimpleDateFormat ftTimeInc = new SimpleDateFormat ("dd/MM/yyyy k:mm");//declares a simple font for a visual improvement over the full version
	SimpleDateFormat timeft = new SimpleDateFormat ("kk:mm");//declares a simple font for a visual improvement over the full version
	SimpleDateFormat datePickerConventionft = new SimpleDateFormat ("d MMMMM yyyy");//declares a simple font for a visual improvement over the full version
	ZoneId defaultZoneId = ZoneId.systemDefault();
	
	//=================== Important system global variables ==================
	String username;
	int loginChoice=-1;
	
	//user instances
	Patient userPatient = new Patient();
	Consultant userConsultant = new Consultant();
	ConsultantList arrayOfConsultants = new ConsultantList();
	StaffList arrayOfStaff = new StaffList();
	Staff userStaff = new Staff();
	Management currentManager = new Management();
	String[] availableConsultant;
	String[] availableStaff;
	
	//patient items needed throughout system
	Admission[] systemAdmissionList;
	Booking currentBooking = new Booking();
	boolean disabled = true;
	int currentAdmissionIndex=0;//points to current admission
	boolean acceptSymptoms;//declares state for attribute whether symptoms are acceptable for an admission

	//employee items
	String employeeIDGlobal = "";
	int currentPatientIndex=0;//points to the current patient on the system

	int initialLoad = 1;
	int endPanelPointer =0;
	int numberOfNotificaitons =12;//initalises the number of admissions
	JButton[] listOfNotifications = new JButton[100];
	
	//=================== GRAPHICAL COMPONENETS ======================

	//main frames
	JFrame frame = new JFrame();//declares the global frame on which the entire system runs ontop of, panels are added to this in order to be seen
	JFrame glossaryFrame = new JFrame();//declares the global frame on which the glossary will be held on

	
	//===========ALL SYSTEM PANELS==================
	
	JPanel[] panelOrder = new JPanel[1000];//declares the start panel on which the system starts on
	JPanel loginPanel = new JPanel(null);//declares the login panel which will be needed to allow the user to login whenever they need to
	JPanel patientHomepagePanel = new JPanel(null);//declares the homepage for the patient where they can acess all their information
	JPanel consultantHomepagePanel = new JPanel(null);//declares the homepage for the patient where they can acess all their information
	JPanel staffHomepagePanel = new JPanel(null);//declares the homepage for the patient where they can acess all their information
	JPanel adminHomepagePanel = new JPanel(null);//declares the homepage for the patient where they can acess all their information
	JPanel newAdmissionPanel = new JPanel(null);//declares the homepage for the patient where they can acess all their information
	JPanel currentPanel = new JPanel(null);//declares the panel the current user is using this will be to remove the panel no longer in use
	JPanel oldpanel = new JPanel(null);//declares the panel that will be removed every time a new one is added this can be used to reduce workload on the GUI
	JPanel symptomRecomendationPanel = new JPanel(null);//declares the panel for the symptom panel
	JPanel createPatientPanel = new JPanel(null);//declares the homepage for the patient where they can acess all their information
	JPanel jargonLibraryHomepagePanel = new JPanel(null);//declares the homepage for the patient where they can acess all their information
	JPanel glossaryNexDefenitionPanel = new JPanel(null);//declares the homepage for the patient where they can acess all their information
	JPanel demographicHomepagePanel = new JPanel(null);//the declares the demographic home page panel this will be for the demographic of a patient
	JPanel admissionHomepagePanel = new JPanel(null);//admission homepage this will be for the 
	JPanel emptyAdmissionHomepagePanel = new JPanel(null);//admission homepage this will be for the 
	JPanel documentPanel = new JPanel(null);//declares the panel the documents to be viewed
	JPanel consultantAdmissionPanel= new JPanel(null);//declares the panel for the admission page the current consultant
	JPanel consultantPDemographicPanel= new JPanel(null);//declares the panel for the demographic page the current consultant
	JPanel createNewDocumentPanel= new JPanel(null);//declares the panel for the new document page
	JPanel createOldDocumentPanel= new JPanel(null);//declares the panel for the new document page
	JPanel EditAdmissionPanel= new JPanel(null);//declares the panel for the edit admission page
	JPanel viewAppointmentPanel = new JPanel(null);;//declares the panel which will create the panel so appointments can be viewed 
	String[] headings= {"Term","Definition"};
	
	boolean[][] loaded = new boolean[15][15];//declares the array that has the empty array that holds the state of the panels being loaded
	JButton[] buttonList = new JButton[100];
	
	//Login
	JTextField usernameTF = new JTextField();//declares a new text field that wiill contain the username the user enters when trying to login
	JPasswordField loginPasswordF = new JPasswordField();//declares the password field the user will enter when trying to logon to the system 
	JButton realLoginbttn = new JButton();//declares a button to allow the system to compare the credentials the user has entered
	JButton createAccountbbtn = new JButton();//declares a button to allow the user to create an account of that type
	JLabel backgroundIcon = new JLabel();
	JCheckBox rmCheckBox = new JCheckBox("Remeber Me");//declares a checkbox for the specific area of the body, this will be used along side the human image 

	//new patient
	JButton newPatientBackToLogin = new JButton();//declares a button to allow the user to return to the login page
	JButton newAccountcreateAccountbbtn = new JButton();//declares a button to allow the user to create an account
		
	
	//===========PATIENT HOMEPAGE======
	JButton viewNextAppointmentHomepageBttn = new JButton();//declares a button to allow the system to compare the credentials the user has entered
	Box mainBoxNotifications = new Box(BoxLayout.Y_AXIS);
	JScrollPane scrollpaneNotitifications = new JScrollPane(mainBoxNotifications); 
	
	Box mainAppointmentBox = new Box(BoxLayout.Y_AXIS);
	JPanel appointmentPanelBox = new JPanel(null);//the declares the demographic home page panel this will be for the demographic of a patient
	JPanel validappointmentPanelBox = new JPanel(null);//the declares the demographic home page panel this will be for the demographic of a patient
	JPanel invalidappointmentPanelBox = new JPanel(null);//the declares the demographic home page panel this will be for the demographic of a patient
		Box validAppointmentBox = new Box(BoxLayout.Y_AXIS);
	Box invalidAppointmentBox = new Box(BoxLayout.Y_AXIS);

	JLabel upcomingDate = new JLabel();
	JLabel upcomingTime = new JLabel();
	JLabel upcomingConsultant = new JLabel();
	JLabel upcomingRoom = new JLabel();

	
	//===========ADMISSION=============
	Box mainBox = new Box(BoxLayout.LINE_AXIS);
	JScrollPane scrollpane = new JScrollPane(mainBox); 
	Box mainBoxPerson = new Box(BoxLayout.LINE_AXIS);
	int[] listOfsortedDocumentIndexes = new int[0];
	
	JLayeredPane layeredPane = new JLayeredPane();
	JPanel personPanel = new JPanel(null);
	JPanel personPanelButtons = new JPanel(null);
	JButton reinstateDiscahrgePatientAdmissionBttn = new JButton();
	JButton requestReinstatementBttn = new JButton();
	
	//new admission
	JButton backToAdmissionbttn = new JButton();//this button will be needed to return user back to admission screen
	JButton SymptomConfirmBttn = new JButton();//this button will continue onto symptom checker
	JLabel fillerBox = new JLabel();
	JLabel fillerGreyBox = new JLabel();
	
	
	//Symptom entry
	JButton symptomRecomendationBackToExpertSystem = new JButton();//declares the button which returns the user to the prior panel
	JButton symptomRecomendationNewAdmissionBttn = new JButton();//declares the button which creates a new admission
	Box wardBox = new Box(BoxLayout.LINE_AXIS);
	Box consultantIDBox = new Box(BoxLayout.LINE_AXIS);
	Box staffnameBox = new Box(BoxLayout.LINE_AXIS);
	Box dischargePatient = new Box(BoxLayout.LINE_AXIS);
	Box roomBox = new Box(BoxLayout.LINE_AXIS);
	Box currentDiagnosisBox = new Box(BoxLayout.LINE_AXIS);
	Box currentSymptomsBox = new Box(BoxLayout.Y_AXIS);
	Box currentAreasAffectedBox = new Box(BoxLayout.Y_AXIS);
	JTextField symptom1TF = new JTextField();//declares a text field which will be used to allow for other symptoms to be entered on the system
	JTextField symptom2TF = new JTextField();//declares a text field which will be used to allow for other symptoms to be entered on the system
	JTextField symptom3TF = new JTextField();//declares a text field which will be used to allow for other symptoms to be entered on the system
	JTextField symptom4TF = new JTextField();//declares a text field which will be used to allow for other symptoms to be entered on the system
	
	
	//humanBodyParts
	JLabel humanImage = new JLabel();//declares a labbel for an image of a human which will allow for an easier understanding from where the pain resonates from
	JLabel humanImageHeadSelected = new JLabel();//declares a labbel for an image of a human which will allow for an easier understanding from where the pain resonates from
	JLabel humanImageneckSelected = new JLabel();//declares a labbel for an image of a human which will allow for an easier understanding from where the pain resonates from
	JLabel humanImageFootSelected = new JLabel();//declares a labbel for an image of a human which will allow for an easier understanding from where the pain resonates from
	JLabel humanImageLegsSelected = new JLabel();//declares a labbel for an image of a human which will allow for an easier understanding from where the pain resonates from
	JLabel humanImagePelvisSelected = new JLabel();//declares a labbel for an image of a human which will allow for an easier understanding from where the pain resonates from
	JLabel humanImageHipsSelected = new JLabel();//declares a labbel for an image of a human which will allow for an easier understanding from where the pain resonates from
	JLabel humanImageAbdomenSelected = new JLabel();//declares a labbel for an image of a human which will allow for an easier understanding from where the pain resonates from
	JLabel humanImageChestSelected = new JLabel();//declares a labbel for an image of a human which will allow for an easier understanding from where the pain resonates from
	JLabel humanImageRightShoulderSelected = new JLabel();//declares a labbel for an image of a human which will allow for an easier understanding from where the pain resonates from
	JLabel humanImageRightArmSelected = new JLabel();//declares a labbel for an image of a human which will allow for an easier understanding from where the pain resonates from
	JLabel humanImageRightforeArmSelected = new JLabel();//declares a labbel for an image of a human which will allow for an easier understanding from where the pain resonates from
	JLabel humanImageRightHandSelected = new JLabel();//declares a labbel for an image of a human which will allow for an easier understanding from where the pain resonates from
	JLabel humanImageLeftShoulderSelected = new JLabel();//declares a labbel for an image of a human which will allow for an easier understanding from where the pain resonates from
	JLabel humanImageLeftArmSelected = new JLabel();//declares a labbel for an image of a human which will allow for an easier understanding from where the pain resonates from
	JLabel humanImageLeftForeArmSelected = new JLabel();//declares a labbel for an image of a human which will allow for an easier understanding from where the pain resonates from
	JLabel humanImageLeftHandSelected = new JLabel();//declares a labbel for an image of a human which will allow for an easier understanding from where the pain resonates from
	//human checkboxes
	JCheckBox cbNeck = new JCheckBox("Neck");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	JCheckBox cbChest = new JCheckBox("Chest");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	JCheckBox cbHand = new JCheckBox("Hand");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	JCheckBox cbFoot = new JCheckBox("Foot");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	JCheckBox cbHead = new JCheckBox("Head");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	JCheckBox cbAbdomen = new JCheckBox("Abdomen");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	JCheckBox cbArm = new JCheckBox("Arm");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	JCheckBox cbPelvis = new JCheckBox("Pelvis");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	JCheckBox cbForeArm = new JCheckBox("Forearm");//declares a checkbox for the specific area of the body, this will be used along side the human image 	
	JCheckBox cbLeg = new JCheckBox("Leg");//declares a checkbox for the specific area of the body, this will be used along side the human image 	
	JCheckBox cbShoulder = new JCheckBox("Shoulder");//declares a checkbox for the specific area of the body, this will be used along side the human image 	
	JCheckBox cbHips = new JCheckBox("Hips");//declares a checkbox for the specific area of the body, this will be used along side the human image 	
	
	//type of pain items
	ButtonGroup typeOfPain = new ButtonGroup();
	JCheckBox cbChronicPains = new JCheckBox("Chronic pains");//declares a text box that will be used to describe the type of pain the patient is experiecing 
	JCheckBox cbAcutePains = new JCheckBox("Acute pains");//declares a text box that will be used to describe the type of pain the patient is experiecing 
	JCheckBox cbStiffnessInMuscle = new JCheckBox("Stiffness in muscle");//declares a text box that will be used to describe the type of pain the patient is experiecing 
	JCheckBox cbFrequentRecurringPains = new JCheckBox("Frequent recurring pains");//declares a text box that will be used to describe the type of pain the patient is experiecing 
	ButtonGroup typeOfPainE = new ButtonGroup();
	JCheckBox cbChronicPainsE = new JCheckBox("Chronic pains");//declares a text box that will be used to describe the type of pain the patient is experiecing 
	JCheckBox cbAcutePainsE = new JCheckBox("Acute pains");//declares a text box that will be used to describe the type of pain the patient is experiecing 
	JCheckBox cbStiffnessInMuscleE = new JCheckBox("Stiffness in muscle");//declares a text box that will be used to describe the type of pain the patient is experiecing 
	JCheckBox cbFrequentRecurringPainsE = new JCheckBox("Frequent recurring pains");//declares a text box that will be used to describe the type of pain the patient is experiecing 

	//Admission recomendation
	Admission tmpAdmission;
	int areaArrayCounter;
	JButton symptomRecomendationcreateAccountBttn = new JButton();//declares the button which creates a new account
	JRadioButton recommendationFalseAcceptanceRB1 = new JRadioButton();//declares the radio button that will be used to confirm the user accepts a term
	JRadioButton recommendationFalseAcceptanceRB2 = new JRadioButton();//declares the radio button that will be used to confirm the user accepts a term
	JRadioButton recommendationFalseAcceptanceRB3 = new JRadioButton();//declares the radio button that will be used to confirm the user accepts a term
	
	//==============Bookings =====================
	JButton appointmentGoBackBttn = new JButton();//declares a button to allow the system to compare the credentials the user has entered
	JButton appointmentcreateBookingBttn = new JButton();//Declares button used to create the booking on the system will run the method which creates the data
	JButton appointmentCancelBookingBttn = new JButton();//Declares button used to ddelete the booking
	Box roomBoxBooking = new Box(BoxLayout.LINE_AXIS);
	Box ConsultantBoxBooking = new Box(BoxLayout.LINE_AXIS);
	Box automateBookingBoxBooking = new Box(BoxLayout.LINE_AXIS);
	Box dateBookingBoxBooking = new Box(BoxLayout.LINE_AXIS);
	Box timeBookingBoxBooking = new Box(BoxLayout.LINE_AXIS);
	TimePickerSettings timeSettings = new TimePickerSettings();//Creates a new custom settings for the time picker
	DatePickerSettings dateSettings = new DatePickerSettings();//Creates a new custom settings for the date picker
	JCheckBox automateBookingBookingRadioButton = new JCheckBox("Automate Future Bookings");//declares a checkbox for the specific area of the body, this will be used along side the human image 
	boolean automateBooking;
	TimePicker timePicker1 = new TimePicker(timeSettings);//Creates new instance of timepicker using the settings
	DatePicker datePicker1 = new DatePicker(dateSettings);//
	ArrayList<LocalTime> localTimeArray = new ArrayList<LocalTime>();//Declares a arraylist of datatype LocalTime
	//view admissiom
	JButton viewAdmissionGoBackBttn = new JButton();//declares a button to allow the system to compare the credentials the user has entered
	JButton archiveAdmissionBttn = new JButton();//declares a button to archive te current admission
	JButton updateAdmission = new JButton();//this button will continue onto symptom checker
		//header label
		JLabel bookingLbl = new JLabel();//declares a label that is used for the first name


	//================= Demographic ===========
	
	JLabel userIDLBL = new JLabel();//declares a labbel for the main header on the login screen 
	JLabel userProfilePicfield = new JLabel();//declares a label for the profile picture
	JButton saveDemographicChanges = new JButton();//demographic specific button
	JRadioButton demographicDisablitiesPromptLbl = new JRadioButton();//declares a radio button that is used for the disabilities
	Box fNameBox = new Box(BoxLayout.LINE_AXIS);
	Box sNameBox = new Box(BoxLayout.LINE_AXIS);
	Box dOBBox = new Box(BoxLayout.LINE_AXIS);
	
	Box addressBox = new Box(BoxLayout.Y_AXIS);
	Box addressBuildingBox = new Box(BoxLayout.LINE_AXIS);
	Box addressStreetBox = new Box(BoxLayout.LINE_AXIS);
	Box addressTownBox = new Box(BoxLayout.LINE_AXIS);
	Box addressCountyBox = new Box(BoxLayout.LINE_AXIS);
	Box addressPostcodeBox = new Box(BoxLayout.LINE_AXIS);
	
	
	Box genderBox = new Box(BoxLayout.LINE_AXIS);
	Box sexBox = new Box(BoxLayout.LINE_AXIS);
	Box contactInfoBox = new Box(BoxLayout.LINE_AXIS);
	Box religonBox = new Box(BoxLayout.LINE_AXIS);
	Box bloodTypeBox = new Box(BoxLayout.LINE_AXIS);
	Box nationalityBox = new Box(BoxLayout.LINE_AXIS);
	Box allergiesBox = new Box(BoxLayout.Y_AXIS);
	Box smokerBox = new Box(BoxLayout.Y_AXIS);
	Box drinkerBox = new Box(BoxLayout.Y_AXIS);
	Box disabilitiesBox = new Box(BoxLayout.Y_AXIS);

	String[] genders = new String[] {"Please select the gender you identify as.","Male", "Female","Other"};//declares list of gender options
	String[] sexs = new String[] {"Please select the sex you are","Male", "Female","Other"};//declares list of gender options

	String[] bloodTypes = new String[] {"Please select a Blood Type.","A+", "A-","B+","B-","O+","O-","AB+","AB-"};//

	
	
	//================= DOCUMENT =============
	
	JButton backToAdmissionsBttn = new JButton();//Declares a button which returns the user to the prior panel
	JButton nextDocumentBttn = new JButton();//Declares a button which will move the document panel onto the next document
	JButton priorDocumentBttn = new JButton();//Declares a button which will move the document panel onto the prior document
	JButton printDocumentBttn = new JButton();//Declares a button which allows the user to print off the current document
	int currentDocumentIndex;
	int documentType=-1;
	//new document
	JButton createNewDocumentButton = new JButton();//
	JButton returnBackToadmissionPage = new JButton();//
	
	//highlight
	Box documentOuterBox = new Box(BoxLayout.Y_AXIS);
	
	//overall Document
	Box document = new Box(BoxLayout.Y_AXIS);
	
	//top part which is general
	JPanel topPartsPanel= new JPanel(null);
	JLabel patientIDDocumentLBL = new JLabel();//Declares a label to show text to the user
	JLabel admissionIDDocumentLBL = new JLabel();//Declares a label to show text to the user
	JLabel documentIDDocumentLBL = new JLabel();//Declares a label to show text to the user

	JLabel dateDocumentLBL = new JLabel();//Declares a label to show text to the user
	JLabel timeDocumentLBL = new JLabel();//Declares a label to show text to the user
	JLabel titleDocumentLBL = new JLabel();//Declares a label to show text to the user
	JLabel authorDocumentLBL = new JLabel();//Declares a label to show text to the user
		
	//Text feild
	JPanel textAreaPanel= new JPanel(null);
	JTextArea textDocumentTAND = new JTextArea();//Declares a text area which will hold the information for the document 
	String signOFF="";

	//new document
	JButton docTypeDischargmentBttn = new JButton();//declares a button for the option of discharge
	JButton docTypeTestResultsBttn = new JButton();//declares a button for the option of test results
	JButton docTypeNotesBttn = new JButton();//declares a button for the option of notes
	JButton docTypePrescriptionBttn = new JButton();//declares a button for the option of prescription
	JButton docTypeReinstateBttn = new JButton();//declares a button for the option of prescription
	JLabel darkGreyBackgroung = new JLabel();
	
	
	//new legacy document
	//creation
	//filepath
	Box legacyFilePathBox = new Box(BoxLayout.LINE_AXIS);
	JTextField actualFilePathTF = new JTextField();
	JFileChooser legacyFileChooser = new JFileChooser(System.getProperty("user.dir"));
	JButton buttonSelectfilePathBttn = new JButton();
	JLabel testLbl = new JLabel();
	//type
	Box typeOflegacyDocumentBox	= new Box(BoxLayout.LINE_AXIS);
	JLabel typeOfDocumentLegacyLbl = new JLabel();
	JTextField typeOflegacyDocumentTF = new JTextField();
	//date of creation
	DatePickerSettings dateOfLegacySettings = new DatePickerSettings();//Creates a new custom settings for the date picker
	DatePicker datePicker12 = new DatePicker(dateOfLegacySettings);//
	//Text area
	JTextArea legacyinputTextArea = new JTextArea();//Declares a text area which will hold the information for the document 
	//creation button
	JButton createLegacyDocumentButton = new JButton();

	//output Components
	JTextArea legacyTextArea = new JTextArea();//Declares a text area which will hold the information for the document 
	JPanel legacyDocumentPanel= new JPanel(null);//declares the panel for the edit admission page
	JLabel DocumentBoxlbl = new JLabel();
	Box documentOuterBoxLegacy = new Box(BoxLayout.LINE_AXIS);
	Box legacyDocumentBox = new Box(BoxLayout.LINE_AXIS);
	JLabel dateOfCreationLegacyLbl = new JLabel();

	//=================	CONSULTANT ================
	Box mainBoxConsultant = new Box(BoxLayout.LINE_AXIS);
	JScrollPane scrollConsultant = new JScrollPane(mainBoxConsultant); 
	JButton addToJargonBttn = new JButton();//declares a button to allow the user to create an account of that type
	int[] listOfsortedconsultantPatientIndexes = new int[0]; 
	Box mainBoxConsultantAppointments = new Box(BoxLayout.Y_AXIS);
	JScrollPane scrollConsultantAppointments = new JScrollPane(mainBoxConsultantAppointments); 
	
	
	//=============== STAFF=========================
	Box mainBoxStaffPatients = new Box(BoxLayout.LINE_AXIS);
	JScrollPane scrollStaff = new JScrollPane(mainBoxStaffPatients); 
	int[] listOfsortedstaffPatientIndexes = new int[0];

	JButton returnBackToAdmissionbttn = new JButton();
	JButton createOldDocmentBttn = new JButton();
	
	
	//=================	 TABS ==============
	JTabbedPane GeneralTabBar = new JTabbedPane();
	JButton homebttn = new JButton();//declares a button to allow the user to move to the symptom panel
	JButton demographicbttn = new JButton();//declares a button to allow the user to move to the symptom panel
	JButton patientDemographicbttn = new JButton();//declares a button to allow the user to move to the demographic panel
	JButton admissionbttn = new JButton();//declares a button to allow the user to move to the symptom panel
	JButton admissionPatientbttn = new JButton();//declares a button to allow the user to move to the symptom panel
	JButton jargonLibrarybttn = new JButton();//declares a button to allow the user to move to the symptom panel
	JButton newPatientbttn = new JButton();//declares a button to allow the user to move to the symptom panel
	JButton logoutbttn = new JButton();//declares a button to allow the user to move to the symptom panel
	JButton consultantPatientDemographic = new JButton();//declares a button to allow the user to move to the symptom panel
	JOptionPane logoutConfirm = new JOptionPane();//declares a new pop up window for the user
	JLabel topwhitelinebar = new JLabel();//this declares the top white line that will be used for a menue which will be used throughout//ContactBar
	JButton notificationbttn = new JButton();//declares a new object of button allowing the user to view their notifications
	
	//admission tabs
	JButton topBarNewAdmissionBttn = new JButton();//declares the button which is for the top tab bar
	Box buttonBar = new Box(BoxLayout.LINE_AXIS);
	JScrollPane descScroll = new JScrollPane(buttonBar); 
	boolean hasNewAdmissionLoaded = false;
	
	//notification tab feature
	Box mainBoxNotificationstab = new Box(BoxLayout.Y_AXIS);
	JScrollPane scrollpaneNotitificationstab = new JScrollPane(mainBoxNotificationstab); 
	JWindow displayWindow;
	JPanel notificationPanel = new JPanel(null);
	boolean open = false;
	
	
	Box searchBoxOuter = new Box(BoxLayout.Y_AXIS);
	Box searchBoxOuterConsultant = new Box(BoxLayout.Y_AXIS);
	Box searchBoxOuterStaff = new Box(BoxLayout.Y_AXIS);
	
	//=============== CONTACT BARS =================
	Box contactBox = new Box(BoxLayout.Y_AXIS);
	JLabel ContactBarBlueboxHighlight = new JLabel();//declares a labbel for a blue box to be created for astetic purposes and display a visual appeal to the user//
	JLabel ContactBarWhiteBOx = new JLabel();//declares a labbel for a white box to be created for astetic purposes and display a visual appeal to the user//
	
	Box topBox = new Box(BoxLayout.Y_AXIS);
	Box basicInfoBox = new Box(BoxLayout.Y_AXIS);
	Box consutlantSpecificBox = new Box(BoxLayout.Y_AXIS);
	Box staffSpecificBox = new Box(BoxLayout.Y_AXIS);
	Box patientSpecificBox = new Box(BoxLayout.Y_AXIS);

	
	//admission 
			JLabel greyBoxLblEA = new JLabel();//declares a labbel for the main header on the login screen 
	Box contactBoxAdmission = new Box(BoxLayout.Y_AXIS);
	Box idAdmissionBox = new Box(BoxLayout.Y_AXIS);
	Box admissiongeneralBox = new Box(BoxLayout.Y_AXIS);
	Box admissiongeneral2Box = new Box(BoxLayout.Y_AXIS);
	//patient specfic
	Box patientAdmissionConsultantbox = new Box(BoxLayout.Y_AXIS);
	Box patientAdmissionButtonsbox = new Box(BoxLayout.Y_AXIS);
	//consultant specfic
	Box consultantAdmissionNewDocumentbox = new Box(BoxLayout.Y_AXIS);
	Box consultantAdmissionButtonsbox = new Box(BoxLayout.Y_AXIS);
	//staff
	JButton addNewLegacyDocumentBttn = new JButton();

	
	//glossary
	JTextArea defenition = new JTextArea();
	JPanel definitionPanel= new JPanel(null);//declares the panel for the glossary frame
	JButton backToConsultantHomeBttn = new JButton();
	
	Box outerRequestedTermsBox = new Box(BoxLayout.Y_AXIS);
		JScrollPane desiredTermsScroll = new JScrollPane(outerRequestedTermsBox); 
		JTextField termToDefineTF = new JTextField();//declares the text feild that will hold the search querey
	
		String[] listOfSortOptions ={"Ascending","Descending"};
	//JTextField termToDefineTF = new JTextField();//declares the text feild that will hold the search querey

	//searchSortBox
	Box searchBox = new Box(BoxLayout.LINE_AXIS);
	JButton searchListButton = new JButton();
	JButton clearButton = new JButton();
	JComboBox<String> sortComboBox = new JComboBox<String>(listOfSortOptions);//declares a combo box that holds the list of available sorting options
	JTextField searchQueryTextFeild = new JTextField();//declares the text feild that will hold the search querey

	
//========== MISC ====================
	
	//colours
	Color selectedBttcColour = Color.decode("#3a78d7");//decodes the colour for the bttn and saves it
	Font headerFontFormatID = new Font("Helvetica",Font.BOLD,40);//
	Font headerFontFormat = new Font("Helvetica",Font.BOLD,48);//
	Font headerFontFormatSmallerr = new Font("Helvetica",Font.BOLD,44);//
	/*This creates a new object of font containg the  desired attributes of font type
	if i want it bold and finallythe size, i will be able to inherit this attribute and 
	use it throughout the desgin
	*/
	Color greyColour = Color.decode("#d6d6d6");//decodes the colour from the hex value and saves it
	Color darkGreyColour = Color.decode("#7a7a7a");//decodes the colour from the hex value and saves it
	Color importantRedColor = Color.decode("#b70f0a");//decodes the colour from the hex value and saves it
	Color darkButtonGrey =  new Color(-12303806);//decodes an int value and saves it as a colour
	Color lightGreyColor = Color.decode("#d6d6d6");//decodes the colour from the hex value and saves it
	Color verylightGreyColor = Color.decode("#f7f6f6");//decodes the colour from the hex value and saves it
	Color availableGreen = Color.decode("#81c794");//decodes the colour from the hex value and saves it
	
	
	
	//Fonts
	Font headerFontFormatBlack = new Font("Helvetica",Font.BOLD,24);//This creates a new object of font containg the desired attributes of font type
	Font buttonFontFormat = new Font("Helvetica",Font.BOLD,30);//declares a new font for the buttons text so it is much clearer to 
	Font buttonFontFormatu = new Font("Helvetica",Font.BOLD,35);//declares a new font for the buttons text so it is much clearer to 
	Font welcomeLblF = new Font("Helvetica",0,50);//declares a new font for the buttons text so it is much clearer to 
	Font textfont = new Font("Helvetica",Font.BOLD,12);//This creates a new object of font containg the desired attributes of font type
	Font consultantCardFont = new Font("Helvetica",Font.BOLD,27);//This creates a new object of font containg the desired attributes of font type
	Font symptomfont = new Font("Helvetica",Font.BOLD,17);//This creates a new object of font containg the desired attributes of font type
	Font whiteLoginFont = new Font("Helvetica",Font.BOLD,24);//declares a new type of font that can be globally used  to alter the font for any text when applied to 
	Font admissionCardFont = new Font("Helvetica",Font.BOLD,20);//declares a new type of font that can be globally used  to alter the font for any text when applied to 
	Font headerFontFormatBlackUnder = new Font("Helvetica",Font.BOLD,24);//This creates a new object of font containg the desired attributes of font type
	Map<TextAttribute, Object> attributes = new HashMap<>(headerFontFormatBlackUnder.getAttributes());//declares the mapping attribute for the button which retirieves the text attributes of s font
	Font headerFontFormatBlackUnderSmall = new Font("Helvetica",Font.BOLD,22);//This creates a new object of font containg the desired attributes of font type
	Map<TextAttribute, Object> attributesSmall = new HashMap<>(headerFontFormatBlackUnderSmall.getAttributes());//declares the mapping attribute for the button which retirieves the text attributes of s font
	Font buttonFontFormatun = new Font("Helvetica",Font.BOLD,30);//declares a new font for the buttons text so it is much clearer to 
	Map<TextAttribute, Object> buttonFontFormatunderlined = new HashMap<>(buttonFontFormatun.getAttributes());//declares the mapping attribute for the button which retirieves the text attributes of s font
	Font headerFont = new Font("Helvetica", Font.ITALIC | Font.BOLD, 36);
	Font loginfont = new Font("Helvetica", Font.ITALIC | Font.BOLD, 30);

	
		
	
	// ================================== SYSTEM METHODS ===================================
	
	
	
	//=============== START METHOD ==============
	
	//this is the intial method of the system first instance called from the methods running 
	//in this method alot of objects instances are intialised
	public void startSystem()//Method used to initalise the system will load what ever is necessary
	{		

		UIManager.put("ComboBox.disabledBackground", Color.white);
		UIManager.put("ComboBox.disabledForeground", Color.black);
		UIManager.put("CheckBoxMenuItem.disabledForeground", UIManager.get("CheckBox.foreground"));
		UIManager.put("TextArea.inactiveForeground",Color.black);
		UIManager.put("TextField.disabledBackground", Color.white);
		UIManager.put("RadioButton.disabledForeground", Color.black);
		UIManager.put("TextField.inactiveBackground",Color.white); 
		availableConsultant = arrayOfConsultants.pullConsultantsFromFileActive();
		String[] copyOfList = new String[availableConsultant.length+1];
		for(int index =0;index<availableConsultant.length;index++)
		{
			copyOfList[index+1]= availableConsultant[index];
		}
		availableConsultant = copyOfList;
		availableConsultant[0] = "PENDING";
		availableStaff = arrayOfStaff.returnAllStaff();
		createAllBoxes();
		createActionListners();
		createBoxContactBox();
		createBoxContactAdmissionBox();
		createDemographicNewPatientComponentsGUI();
		createSearchPanel();
		createHumanComponents();
		createEditAdmissionComponents();
		createGUI();//Will call method which will initialise and then load frame and respective GUI
		createDocument();
		createNotificationTabGeneral();
		//RETRIEVE GENERAL FILE
	}
	
	
	//Method used to attach action listeners to every button on the system which is global
	//Not the most effceitnet but avoids the constant persistance of being called twice
	//Aim is to hopefully re-add listners after
	public void createActionListners()
	{
		realLoginbttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		viewNextAppointmentHomepageBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		logoutbttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		jargonLibrarybttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		homebttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		demographicbttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		admissionbttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		notificationbttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		symptomRecomendationNewAdmissionBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		patientDemographicbttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		admissionPatientbttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		consultantPatientDemographic.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		symptomRecomendationBackToExpertSystem.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		backToAdmissionbttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		viewAdmissionGoBackBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		SymptomConfirmBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		appointmentGoBackBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		addToJargonBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		backToConsultantHomeBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		returnBackToAdmissionbttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		createOldDocmentBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		recommendationFalseAcceptanceRB1.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		recommendationFalseAcceptanceRB2.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		recommendationFalseAcceptanceRB3.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		returnBackToadmissionPage.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		createNewDocumentButton.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		saveDemographicChanges.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		createAccountbbtn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		newAccountcreateAccountbbtn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		newPatientBackToLogin.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbLeg.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbPelvis.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbArm.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbAbdomen.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbHead.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbFoot.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbHand.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbChest.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbForeArm.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbNeck.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbShoulder.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		cbHips.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		updateAdmission.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		appointmentcreateBookingBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		appointmentCancelBookingBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		buttonSelectfilePathBttn.addActionListener(this);
		createLegacyDocumentButton.addActionListener(this);
		searchListButton.addActionListener(this);
		clearButton.addActionListener(this);
		sortComboBox.addActionListener(this);
	}
		
	//Method which declares gloabal labels used as visual boxes	these for all the global boxes
	//this method only needs to be called once
	public void createAllBoxes()
	{ 
		ContactBarBlueboxHighlight.setForeground( new Color(-12616489) );//the foreground of the cmponent is given a white font
		ContactBarBlueboxHighlight.setBackground( new Color(-12616489) );//the background colour of the component is declared to the desired value 
		ContactBarBlueboxHighlight.setSize(480,159);//the components size is correctly declared
		ContactBarBlueboxHighlight.setOpaque(true);//the component is set to opaque
		ContactBarBlueboxHighlight.setSize(15,865);//the components size is correctly declared
		ContactBarBlueboxHighlight.setLocation(350,40); //sets the location of the component 


		ContactBarWhiteBOx.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		ContactBarWhiteBOx.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
		ContactBarWhiteBOx.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		ContactBarWhiteBOx.setOpaque(true);//the component is set to opaque
		ContactBarWhiteBOx.setSize(350,865);//the components size is correctly declared
		ContactBarWhiteBOx.setLocation(0,40); //sets the location of the component 

	}
	
	
	//FRAME DECLERATION
	//this initlaises the framee of the system
	public void createGUI()
	{
		
		frame.addComponentListener(customWindowListner);
		setAllPanelsUnloaded();//method which sets all the panels as unloaded is called
        frame.setLayout(new GridLayout(1,1));//initalises the frame and the format
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Will stop the program when the window is closed
		setUpArray();
		frame.add(loginPanel);//When the frame is initialised the panel is added to the frame
		createloginPanel();
		
        frame.setTitle("Exuton HealthCare System");//sets the title of the frame
		frame.setResizable(false);
        frame.setSize(1474,913);//Sets the aspect ratio of the intial window
        frame.setForeground( new Color(-16777216) );//the component has its font colour changed to a desireable one
		//Declares the colour of the foreground
        frame.setBackground( new Color(-2696737) );//
		//Declares the colour of the backgound
        frame.setVisible(true);//sets the frame to be visible
		//sets the frame to be visbile along with any panels attached to it
        frame.setResizable(false);//allows the frame to be resized
		
	}
	
	//Creates the search box the entire system will work by
	//for the systems seatch panel all the attributes are created in the system
	public void createSearchPanel()
	{
		searchListButton.setFont(whiteLoginFont);//the component has had its font set to a design with the correct size for its purpose
		searchListButton.setText("Search List");//formatting component to include text
		searchListButton.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		searchListButton.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		
		clearButton.setFont(whiteLoginFont);//the component has had its font set to a design with the correct size for its purpose
		clearButton.setText("Clear");//formatting component to include text
		clearButton.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		clearButton.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		
		sortComboBox.setMinimumSize(new Dimension(120,35));
		sortComboBox.setPreferredSize(new Dimension(120,35));
		sortComboBox.setMaximumSize(new Dimension(120,35));
		sortComboBox.setOpaque(false);
		
		searchQueryTextFeild.setText("Search...");//formatting component to include text
		searchQueryTextFeild.setMinimumSize(new Dimension(540,40));
		searchQueryTextFeild.setPreferredSize(new Dimension(540,40));
		searchQueryTextFeild.setMaximumSize(new Dimension(540,40));
		searchQueryTextFeild.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		searchQueryTextFeild.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(searchQueryTextFeild.getText().equals("Search..."))//selection checking if the text field contains the text prompt
				{
					searchQueryTextFeild.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(searchQueryTextFeild.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					searchQueryTextFeild.setText("Search...");//if satisifed then the prompt text is set again
				}
			}
		});
		searchBox.add(Box.createRigidArea(new Dimension(10,0)));
		searchBox.add(sortComboBox);//the component is added to the panel
		searchBox.add(Box.createRigidArea(new Dimension(5,0)));
		searchBox.add(searchQueryTextFeild);//the component is added to the panel
		searchBox.add(Box.createRigidArea(new Dimension(5,0)));
		searchBox.add(searchListButton);//the component is added to the panel
		searchBox.add(Box.createRigidArea(new Dimension(5,0)));
		searchBox.add(clearButton);//the component is added to the panel

			searchBox.setMinimumSize(new Dimension(975,55));
		searchBox.setPreferredSize(new Dimension(975,55));
		searchBox.setMaximumSize(new Dimension(975,55));
		searchBox.setLocation(45,145);//the location of the component is set to the desired part of the panel
		searchBox.setBackground(lightGreyColor);
		searchBox.setOpaque(true);
		searchBox.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
	}
	
		
	
	
	
	
	
	//=============== PANEL NAVIGATION ===========
	//the panels o the system work by acting like a staff, so that the curent panel is always the top item on the stack
	//if the user goes back the top item is removed//if they go home the stack is cleared
	//however to reduce resources when a user leaves a penl the panel is not discarded but rather kept in ram
	//to prevent the system loading every panel at once at the start the system only generates panels that it needs to
	//so it records all of the panels here and can check if they need to be genreated or not
	//initlaly they are all set as unloaded
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
	//This initlaises the strat panel list of the system and sets the inital panel as the start panel
	public void setUpArray()
	{
		panelOrder[0]=loginPanel;
		showlatestPanel();		
	}
	//method which will hide the old panel and will make the new panel visilbe thsi will be used whenever a new panel needs to be seen
	//However the method is not called directly as there is no way the array will be reorganised
	public void showlatestPanel()
	{
		if(endPanelPointer>=1)
		{
			panelOrder[endPanelPointer-1].setVisible(false);
			frame.remove(panelOrder[endPanelPointer-1]);//Removes the current pannel of the system
		}
		panelOrder[endPanelPointer].setVisible(false);
		panelOrder[endPanelPointer].setVisible(true);
	}
	//pop method to remove the top item of the stack, not only does it cleat the item but it also 
	//sets the new top panel to active status and can be seen by the user
	public void removePanel()
	{
		frame.remove(panelOrder[endPanelPointer]);//Removes the current pannel of the system
		panelOrder[endPanelPointer].setVisible(false);
		endPanelPointer--;
		frame.add(panelOrder[endPanelPointer]);
		showlatestPanel();
		//System.out.println("Pointer is at"+endPanelPointer);
	}
	//push method this allows new panels to be added to the panel stack, it also sets the current panel to be seen
	public void addPanel(JPanel panel)
	{
		//System.out.println("line at index "+ endPanelPointer);
		endPanelPointer++;
		//System.out.println("line at index "+ endPanelPointer);
		panelOrder[endPanelPointer]=panel;	
		frame.add(panelOrder[endPanelPointer]);//Removes the current pannel of the system
		showlatestPanel();
		
	}
	//similar to the pop method this clears the stack for the enire array and takes the new panel as the new panel
	//this will take the paramter to be the main panel,
	//Cant be called unless we have a panel to replace the removed ones
	public void emptyArray(JPanel panel)
	{
		frame.remove(panelOrder[endPanelPointer]);//Removes the current pannel of the system
		panelOrder[0]=panel;
		endPanelPointer=0;
		frame.add(panelOrder[endPanelPointer]);//Removes the current pannel of the system
		showlatestPanel();	
		panel.setVisible(false);
		panel.setVisible(true);
		//System.out.println("Pointer is at"+endPanelPointer);
	}
	//dev tool used to show the instances of all the panles of the system in the order
	//of the array, the current panel is
	//at the top of the stack and the current starting panel (homepanel) being the bottom
	public void outputPanelList()
	{
		for(int i =0;i<=endPanelPointer;i++)
		{
			//System.out.println(panelOrder[endPanelPointer]);
		}
		//System.out.println("Pointer is at"+endPanelPointer);
		
	}









	//====================== LOGIN PANEL ===================
	//inital call methof for the panel this will load and add the panel to the frame and will perform
	//any other actions such as adding data/updateing it if requeired
	public void createloginPanel()//this will create the new panel along with the components 
    {
		
		if(loaded[2][0]== false)//selection determines whether the panel is yet to be loaded
		{
			createComponentsLoginPanelGUI();//as the correct panel is visible the components on the panel is formatted 
			loaded[2][0]= true;//the variable is set as true to prevent the components from breing reran
		}
		if(initialLoad !=1)
		{
		addPanel(loginPanel);
		loginPanel.setVisible(true);//allows the panel to be seen by making it visible
		loginPanel.setVisible(true);//allows the panel to be seen by making it visible
		initialLoad = 0;
		}
	}
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createComponentsLoginPanelGUI()//the method is declared as public with no parameters to pass through
	{
		
		loginPasswordF.addKeyListener(windowEmterListener);
		usernameTF.addKeyListener(windowEmterListener);
		rmCheckBox.addKeyListener(windowEmterListener);


		JLabel welcomeRamsay = new JLabel();//declares a labbel for the main header on the login screen 
		welcomeRamsay.setFont(headerFont);//the font that has been declared is attached to the component
		welcomeRamsay.setSize(350,40);//the components size is correctly declared
		welcomeRamsay.setText("Ramsay Healthcare");//the label is geven text to add meaning to the label
		welcomeRamsay.setForeground(Color.decode("#004e63"));//the foreground of the component is given a white font
		welcomeRamsay.setLocation(970,10);//sets the location of the component 
		loginPanel.add(welcomeRamsay);//the component is added to the panel



		JLabel welcomeEuxtonLabel = new JLabel();//declares a labbel for the main header on the login screen 
		welcomeEuxtonLabel.setFont(loginfont);//the font that has been declared is attached to the component
		welcomeEuxtonLabel.setSize(550,40);//the components size is correctly declared
		welcomeEuxtonLabel.setText("Euxton Hall Hospital");//the label is geven text to add meaning to the label
		welcomeEuxtonLabel.setLocation(1040,45);//sets the location of the component 
		loginPanel.add(welcomeEuxtonLabel);//the component is added to the panel

		
		JLabel loginHeaderlbl = new JLabel();//declares a labbel for the main header on the login screen 
		loginHeaderlbl.setFont(loginfont);//the font that has been declared is attached to the component
		loginHeaderlbl.setSize(90,40);//the components size is correctly declared
		loginHeaderlbl.setText("Login");//the label is geven text to add meaning to the label
		loginHeaderlbl.setLocation(692,200);//sets the location of the component 
		loginPanel.add(loginHeaderlbl);//the component is added to the panel

		JLabel titleUpperBlackLine = new JLabel();//declares a labbel for the main header on the login screen 
		titleUpperBlackLine.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		titleUpperBlackLine.setForeground( new Color(1) );//the foreground of the component is given a white font
		titleUpperBlackLine.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		titleUpperBlackLine.setLocation(687,195);//sets the location of the component 
		titleUpperBlackLine.setSize(100,3);//the components size is correctly declared
		titleUpperBlackLine.setOpaque(true);//the component is set to opaque
		loginPanel.add(titleUpperBlackLine);//the component is added to the panel

		JLabel titleLowerBlackLine = new JLabel();//declares a labbel for the main header on the login screen 
		titleLowerBlackLine.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		titleLowerBlackLine.setForeground( new Color(1) );//the foreground of the component is given a white font
		titleLowerBlackLine.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		titleLowerBlackLine.setLocation(687,245);//sets the location of the component 
		titleLowerBlackLine.setSize(100,3);//the components size is correctly declared
		titleLowerBlackLine.setOpaque(true);//the component is set to opaque
		loginPanel.add(titleLowerBlackLine);//the component is added to the panel



		usernameTF.setSize(240,23); //the components size is correctly declared
		usernameTF.setLocation(617,300);//sets the location of the component 
		usernameTF.setVisible(true);//the component is set so it can be seen
		((AbstractDocument)usernameTF.getDocument()).setDocumentFilter(new LimitDocumentFilter(11));

		loginPanel.add(usernameTF);//the component is added to the panel

				
		loginPasswordF.setSize(240,23); //the components size is correctly declared
		loginPasswordF.setLocation(617,350);//sets the location of the component 
		loginPanel.add(loginPasswordF);//the component is added to the panel

		rmCheckBox.setSize(150,23); //the components size is correctly declared
		rmCheckBox.setLocation(662,395);//sets the location of the component 
		//loginPanel.add(rmCheckBox);//the component is added to the panel
		rmCheckBox.setOpaque(false);//the component is set to opaque
		rmCheckBox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 




		realLoginbttn.setLocation(617,425);//sets the location of the component 
		realLoginbttn.setVisible(true);//the component is set so it can be seen
		realLoginbttn.setEnabled(true);
		realLoginbttn.setFont(whiteLoginFont);
		realLoginbttn.setSize(240,49);//the components size is correctly declared
		realLoginbttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		realLoginbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		realLoginbttn.setText("Login");//the label is geven text to add meaning to the label
		loginPanel.add(realLoginbttn);//the component is added to the panel
		

		
		createAccountbbtn.setVisible(true);//the component is set so it can be seen
		createAccountbbtn.setEnabled(true);
		createAccountbbtn.setSize(180,49);//the components size is correctly declared
		createAccountbbtn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		createAccountbbtn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		createAccountbbtn.setText("Create Patient Account");//the label is geven text to add meaning to the label
		createAccountbbtn.setLocation(647,510);//sets the location of the component 
		createAccountbbtn.setFont(textfont);
		loginPanel.add(createAccountbbtn);//the component is added to the panel
		
		//blue box
		JLabel whiteBoxLBL = new JLabel();//declares a labbel for the main header on the login screen 
		whiteBoxLBL.setFont(loginfont);//the font that has been declared is attached to the component
		whiteBoxLBL.setSize(400,424);//the components size is correctly declared
		whiteBoxLBL.setBackground(verylightGreyColor);//the background colour of the component is declared to the desired value 
		whiteBoxLBL.setOpaque(true);//the background colour of the component is declared to the desired value 
		whiteBoxLBL.setLocation(537,179);//sets the location of the component 
		loginPanel.add(whiteBoxLBL);//the component is added to the panel
		
		//blue box
		JLabel blueBoxLBL = new JLabel();//declares a labbel for the main header on the login screen 
		blueBoxLBL.setFont(loginfont);//the font that has been declared is attached to the component
		blueBoxLBL.setSize(430,454);//the components size is correctly declared
		blueBoxLBL.setBackground(selectedBttcColour);//the background colour of the component is declared to the desired value 
		blueBoxLBL.setOpaque(true);//the background colour of the component is declared to the desired value 
		blueBoxLBL.setLocation(522,164);//sets the location of the component 
		loginPanel.add(blueBoxLBL);//the component is added to the panel
		
		
		//background image
		backgroundIcon.setSize(1474,913);//Sets the aspect ratio of the intial window
		backgroundIcon.setLocation(0,0);//sets the location of the component 
		try
		{
			File inputFile = new File("euxton-hall-hospital.jpg");//gets the tempfile where the currentImage is 
			BufferedImage bi = ImageIO.read(inputFile);//converts it to an image
			Image resizedImage = bi.getScaledInstance(backgroundIcon.getWidth(), backgroundIcon.getHeight(),Image.SCALE_SMOOTH);
			//createLegacyDocumentButton.setIcon(new ImageIcon(bi));//Test that the image is retrieved
			backgroundIcon.setIcon(null);
			backgroundIcon.repaint();
			backgroundIcon.setIcon( new ImageIcon(resizedImage) );
		}
		catch (IOException exc)
		{
			//System.out.println("background image error");
		}
		loginPanel.add(backgroundIcon);//the component is added to the panel

	}
	//this method will clear the current user instance along with this it will move the user to the home panel
	//it will also "unload" some panels that would need to be regenerated again by new users
	public void logout()
	{
		outputPanelList();
		//System.out.println("==========================");
		emptyArray(loginPanel);
		setActiveTopPanelBttn(homebttn);//active top bar is set to the button just pressed
		currentPanel = loginPanel;//current panel is correctly updated
		loaded[6][2]= false;//the variable is set as true to prevent the components from being reran
		loaded[7][2]= false;//the variable is set as false so it can be reran for a different user type this one os for the admission tab checks to see if the staff so disables the creation of admissions for them but not others
		loginChoice=-1;
		if((systemAdmissionList==null)==false)
		{
			if ((systemAdmissionList.length> 0)&&(loaded[6][2]== true))//user wishes to logout
			{
				setActiveAdmissionPanelBttn(buttonList[0]);
			}
		}
		jargonLibrarybttn.setEnabled(true);
		currentAdmissionIndex=0;
		jargonLibrarybttn.setBackground(darkButtonGrey);
		buttonBar.removeAll();	
		outputPanelList();

	}
	//this is the true login method, here the sysytem will pass through the required password long with the entered password
	//the system will then attempt to compare the values together if they are not the same the system will return false
	//else they will return true
	public boolean checkCredentials(String passwordDesired,String passwordEntered)
	{
		boolean validated =userPatient.presenceValidation(passwordEntered);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Missing Password.");
			return false;
		}
		validated=userPatient.lesserLengthValidation(passwordEntered,15);
		if(validated==false)
		{
			JOptionPane.showMessageDialog(null, "Invalid Password, must be less than 15 characters.");
			return false;
		}
		if(passwordEntered.equals(passwordDesired)==true)
		{
			return true;
		}
		JOptionPane.showMessageDialog(null, "Invalid Password.");
		return false;
	}
	

	
	
	
	
	
	
	
	
	
	
//============== PATIENT ====================

	//======== Entity based section =========
	
	//method which is called whenever a new patient logs into the system
	//before the panel is loaded the entity is initalised here by calling the method and declaring the retun type as itself
	//also here the syserm checks all their admissions if that they have not been fully filled in to determine which admission to show the user
	public void setUpPatientObj(String patientID)
	{
		//here we try and pull the instance of the user
		//if it fails to pull the instnace it will only be down to the fact the ID is invlaid on the system
		//Respectfully the system will retrun a error statement to the user
		try{
		userPatient = userPatient.retrievePatient(patientID);
		}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(null, "Username does not exist.");
			return;
		}
		boolean loggedIn =checkCredentials(userPatient.password,String.valueOf(loginPasswordF.getPassword()));
		if(loggedIn==true)
		{
			systemAdmissionList = userPatient.listOfAdmissions;
			//determines the first admission to set as the currently selected one
			currentAdmissionIndex=0;
			boolean noAdmissions = false;
			Loop:
			for(int count=0;count<systemAdmissionList.length;count++)
			{
				if(systemAdmissionList[count].consultantID.equals("PENDING")==false)
				{
					
					currentAdmissionIndex=count;
					break Loop;
				}
			}
			if((systemAdmissionList.length>0)&&((currentAdmissionIndex==0)==false))
			{
				if(loginChoice!=3)
				{
					if(systemAdmissionList[currentAdmissionIndex].consultantID.equals("PENDING")==false)
					{
					userConsultant = userConsultant.retrieveConsultant(systemAdmissionList[currentAdmissionIndex].consultantID);
					}
				}
				
			}	
			if((systemAdmissionList.length>0)&&((currentAdmissionIndex==-1)))
			{
				currentAdmissionIndex=0;
			}
			createPatientHomepagePanelGUI();
		}
	}
	
	//HERE WE WILL USE A VERSION FOR THE NEW MADE PATIENT
	//this skips the password
	public void setUpPatientObj(String patientID,boolean trueVal)
	{
		//here we try and pull the instance of the user
		//if it fails to pull the instnace it will only be down to the fact the ID is invlaid on the system
		//Respectfully the system will retrun a error statement to the user
		try{
		userPatient = userPatient.retrievePatient(patientID);
		userPatient = userPatient = userPatient.createNewNotification(userPatient,"Welcome to your new account on the system.");
		}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(null, "Username does not exist.");
			return;
		}
		systemAdmissionList = userPatient.listOfAdmissions;
		//determines the first admission to set as the currently selected one
		currentAdmissionIndex=0;
		boolean noAdmissions = false;
		Loop:
		for(int count=0;count<systemAdmissionList.length;count++)
		{
			if(systemAdmissionList[count].consultantID.equals("PENDING")==false)
			{
				currentAdmissionIndex=count;
				break Loop;
			}
		}
		if((systemAdmissionList.length>0)&&((currentAdmissionIndex==0)==false))
		{
			if(loginChoice!=3)
			{
					if(systemAdmissionList[currentAdmissionIndex].consultantID.equals("PENDING")==false)
					{
					userConsultant = userConsultant.retrieveConsultant(systemAdmissionList[currentAdmissionIndex].consultantID);
					}			
			}
		}	
		if((systemAdmissionList.length>0)&&((currentAdmissionIndex==-1)==true))
		{
			currentAdmissionIndex=0;
		}
		createPatientHomepagePanelGUI();
		
	}
	// CREATE PATINET	
	//here all the components in the GUI are pulled from their respective boxes into the correct feilds of the object
	//before this occurs each feild that needs to be validated is done so if the validation method retuns false
	//if false is retruned then the system is returned(escaping the procedure and a pop up occurs informing the user an error has occured)
	//if everything is coccrect the system will assign the values to the instances
	//then a method is called which writes them to file and also assinging them to the main user
	public void createPatient()
	{
		Patient newPatient = new Patient();//creates new instance of this object
		boolean validated = false;
		//Here we retrieve all the local instances of the GUI boxes and set them as components they will be turned into repective components later 
		Component firstNameTf=fNameBox.getComponent(3);//inner components get there local objects pulled
		Component surnameNameTf=sNameBox.getComponent(3);//inner components get there local objects pulled
		Component addressName=addressBuildingBox.getComponent(3);//inner components get there local objects pulled
		Component addressStreet=addressStreetBox.getComponent(3);//inner components get there local objects pulled
		Component addressTown=addressTownBox.getComponent(3);//innercomponents get there local objects pulled
		Component addressCounty=addressCountyBox.getComponent(3);//inner components get there local objects pulled
		Component bloodTypedrop=bloodTypeBox.getComponent(3);//inner components get there local objects pulled
		Component genderDrop=genderBox.getComponent(3);//inner components get there local objects pulled
		Component sexDropDown=sexBox.getComponent(3);//inner components get there local objects pulled
		Component religon=religonBox.getComponent(3);//inner components get there local objects pulled
		Component drinkingSlider=drinkerBox.getComponent(1);//inner components get there local objects pulled
		Component smokingSlider=smokerBox.getComponent(1);//inner components get there local objects pulled
		Component allergies=allergiesBox.getComponent(1);//inner components get there local objects pulled
		Component nationality=nationalityBox.getComponent(3);//inner components get there local objects pulled
		Component listOfDisabilities=disabilitiesBox.getComponent(1);//inner components get there local objects pulled
		Component carerRadio=disabilitiesBox.getComponent(2);//inner components get there local objects pulled
		Component translatorRadio=disabilitiesBox.getComponent(3);//inner components get there local objects pulled
	
		//names
		newPatient.firstName = ((JTextField) firstNameTf).getText();//retireives attribute from component
		newPatient.surName = ((JTextField) surnameNameTf).getText();//retireives attribute from component
		try
		{
		newPatient.addressHouseNum =Integer.parseInt(((JTextField) addressName).getText());//retireives attribute from component
		}
		catch(Exception exc)
		{
			validated = false;
			JOptionPane.showMessageDialog(null, "Invalid street number, must be an int.");
			return;
		}
		try
		{
			Component addressPostcode1=addressPostcodeBox.getComponent(3);//inner components get there local objects pulled
			Component addressPostcode2=addressPostcodeBox.getComponent(5);//inner components get there local objects pulled
			Component addressPostcode3=addressPostcodeBox.getComponent(7);//inner components get there local objects pulled
			Component addressPostcode4=addressPostcodeBox.getComponent(9);//inner components get there local objects pulled
			Component addressPostcode5=addressPostcodeBox.getComponent(11);//inner components get there local objects pulled
			Component addressPostcode6=addressPostcodeBox.getComponent(13);//inner components get there local objects pulled
			Component addressPostcode7=addressPostcodeBox.getComponent(15);//inner components get there local objects pulled
			newPatient.postcode =((JTextField) addressPostcode1).getText()+((JTextField) addressPostcode2).getText()+((JTextField) addressPostcode3).getText()+((JTextField) addressPostcode4).getText()+((JTextField) addressPostcode5).getText()+((JTextField) addressPostcode6).getText()+((JTextField) addressPostcode7).getText();
		}
		catch(Exception exc){validated=false;}//if any errrors are found they are caught here	
		
		
		newPatient.addressHouseStreet = ((JTextField) addressStreet).getText();//retireives attribute from components
		newPatient.town = ((JTextField) addressTown).getText();//retireives attribute from component;


		newPatient.county = ((JTextField) addressCounty).getText();//retireives attribute from component
		//length
		try
		{
			Component contactNumber1=contactInfoBox.getComponent(3);//inner components get there local objects pulled
			Component contactNumber2=contactInfoBox.getComponent(5);//inner components get there local objects pulled
			Component contactNumber3=contactInfoBox.getComponent(7);//inner components get there local objects pulled
			Component contactNumber4=contactInfoBox.getComponent(9);//inner components get there local objects pulled
			Component contactNumber5=contactInfoBox.getComponent(11);//inner components get there local objects pulled
			Component contactNumber6=contactInfoBox.getComponent(13);//inner components get there local objects pulled
			Component contactNumber7=contactInfoBox.getComponent(15);//inner components get there local objects pulled
			Component contactNumber8=contactInfoBox.getComponent(17);//inner components get there local objects pulled
			Component contactNumber9=contactInfoBox.getComponent(19);//inner components get there local objects pulled
			Component contactNumber10=contactInfoBox.getComponent(21);//inner components get there local objects pulled
			Component contactNumber11=contactInfoBox.getComponent(23);//inner components get there local objects pulled
			newPatient.contactNum =((JTextField) contactNumber1).getText()+((JTextField) contactNumber2).getText()+((JTextField) contactNumber3).getText()+((JTextField) contactNumber4).getText()+((JTextField) contactNumber5).getText()+((JTextField) contactNumber6).getText()+((JTextField) contactNumber7).getText()+((JTextField) contactNumber8).getText()+((JTextField) contactNumber9).getText()+((JTextField) contactNumber10).getText()+((JTextField) contactNumber11).getText();

		}
		catch(Exception exc){validated=false;}//if any errrors are found they are caught here		

		newPatient.nationality = ((JTextField) nationality).getText();//retireives attribute from component;
		newPatient.religon = ((JTextField) religon).getText();//retireives attribute from component;
		newPatient.allergies =((JTextArea) allergies).getText();//retireives attribute from component;
		if(newPatient.allergies.equals("Please enter any Allergies You may \npossess"))
		{
			newPatient.allergies = "None";
		}
		newPatient.disability = ((JTextArea) listOfDisabilities).getText();//retireives attribute from component;
		if(newPatient.disability.equals(""))
		{
			newPatient.disability = "None";
		}
		newPatient.gender=""+((JComboBox) genderDrop).getSelectedItem();//retireives attribute from components;
		newPatient.sex=""+((JComboBox) sexDropDown).getSelectedItem();//retireives attribute from component
		Component dateBookingBox=dOBBox.getComponent(3);//inner
		try
		{
		LocalDate localDateData = ((DatePicker) dateBookingBox).getDate();
		Date date = Date.from(localDateData.atStartOfDay(ZoneId.systemDefault()).toInstant());
		newPatient.dob =date;
		}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(null, "Please enter a correct date");
			return;
		}//if any errrors are found they are caught here		
		//Sliders
		newPatient.smoker = ((JSlider) smokingSlider).getValue();//retireives attribute from component
		newPatient.drinker = ((JSlider) drinkingSlider).getValue();//retireives attribute from component
		
		newPatient.bloodType=""+((JComboBox) bloodTypedrop).getSelectedItem();//retireives attribute from component;
		newPatient.carer = ((JRadioButton) carerRadio).isSelected();//retireives attribute from component
		newPatient.translator = ((JRadioButton) translatorRadio).isSelected();//retireives attribute from component
		
		//now we can validate the patient instance to see if it is correct(
		validated = newPatient.validatePatientInput(newPatient);
		if(validated==true)
		{
			StaffList staffList = new StaffList();
			newPatient.numberOfAdmissions = 0;//retireives attribute from component
			//ID 
			newPatient.patientID = newPatient.createUniqueID(newPatient.surName,"P");//creates a unique id for the entity
			newPatient.linkedStaffID=staffList.returnStaffIDForPatientCreation(newPatient.patientID); 
			loginChoice = 0;
			PatientList pl = new PatientList();
			newPatient = pl.createNewPatient(newPatient);
			setUpPatientObj(newPatient.patientID,true);//initalises patient object
			//a  pop up is created for the patient showing them their password 
			JOptionPane.showMessageDialog(null, "Your new password is "+newPatient.password+" Please write it down before.");
			usernameTF.setText("");//built in protection on the system as soon as the user tries to login the username will be cleared 
					
		}
	}
	
	// UPDATE PATIENT 
	//similar to the  account creation the details are pulled from the graphical components 
	//however like the new patient method it updates the object in real time while also passing the instance so it can be written to file(over the old version)
	public void updatePateint()
	{
		Patient tempPatient = new Patient();//creates new instance of this object
		boolean validated = true;
		Component firstNameTf=fNameBox.getComponent(3);//inner
		Component surnameNameTf=sNameBox.getComponent(3);//inner
		Component addressName=addressBuildingBox.getComponent(3);//inner
		Component addressStreet=addressStreetBox.getComponent(3);//inner
		Component addressTown=addressTownBox.getComponent(3);//inner
		Component addressCounty=addressCountyBox.getComponent(3);//inner
		Component bloodTypedrop=bloodTypeBox.getComponent(3);//inner
		Component genderDrop=genderBox.getComponent(3);//inner
		Component sexDropDown=sexBox.getComponent(3);//inner
		Component religon=religonBox.getComponent(3);//inner
		Component drinkingSlider=drinkerBox.getComponent(1);//inner
		Component smokingSlider=smokerBox.getComponent(1);//inner
		Component allergies=allergiesBox.getComponent(1);//inner
		Component nationality=nationalityBox.getComponent(3);//inner
		Component listOfDisabilities=disabilitiesBox.getComponent(1);//inner
		Component carerRadio=disabilitiesBox.getComponent(2);//inner
		Component translatorRadio=disabilitiesBox.getComponent(3);//inner
		
		//retireve attributes
		//names
		tempPatient.firstName = ((JTextField) firstNameTf).getText();//retireives attribute from component
		tempPatient.surName = ((JTextField) surnameNameTf).getText();//retireives attribute from component
		//gender
		tempPatient.gender = ""+((JComboBox) genderDrop).getSelectedItem();//retireives attribute from components
		//sex
		tempPatient.sex = ""+((JComboBox) sexDropDown).getSelectedItem();//retireives attribute from component
		//DOB
		try
		{
			//day
			Component dateBookingBox=dOBBox.getComponent(3);//inner
			LocalDate localDateData = ((DatePicker) dateBookingBox).getDate();
			if(localDateData==null)
			{
			JOptionPane.showMessageDialog(null, "Date missing please enter a date.");
			return;
			}
			
			Date date = Date.from(localDateData.atStartOfDay(ZoneId.systemDefault()).toInstant());
			tempPatient.dob = date;
		}
		catch(Exception exc)//if any errrors are found they are caught here		
		{
			
		}
		//Address
		tempPatient.addressHouseNum = Integer.parseInt(""+((JTextField) addressName).getText());//retireives attribute from component
		tempPatient.addressHouseStreet = ((JTextField) addressStreet).getText();//retireives attribute from components
		try
		{
			Component addressPostcode1=addressPostcodeBox.getComponent(3);//inner
			Component addressPostcode2=addressPostcodeBox.getComponent(5);//inner
			Component addressPostcode3=addressPostcodeBox.getComponent(7);//inner
			Component addressPostcode4=addressPostcodeBox.getComponent(9);//inner
			Component addressPostcode5=addressPostcodeBox.getComponent(11);//inner
			Component addressPostcode6=addressPostcodeBox.getComponent(13);//inner
			Component addressPostcode7=addressPostcodeBox.getComponent(15);//inner
			String concatenatedPostcode =((JTextField) addressPostcode1).getText()+((JTextField) addressPostcode2).getText()+((JTextField) addressPostcode3).getText()+((JTextField) addressPostcode4).getText()+((JTextField) addressPostcode5).getText()+((JTextField) addressPostcode6).getText()+((JTextField) addressPostcode7).getText();
			tempPatient.postcode = concatenatedPostcode;//retireives attribute from components
		}
		catch(Exception exc)//if any errrors are found they are caught here		
		{
			
		}
		tempPatient.town = ((JTextField) addressTown).getText();//retireives attribute from component
		tempPatient.county = ((JTextField) addressCounty).getText();//retireives attribute from component
	//contactNumber
		try
		{
			Component contactNumber1=contactInfoBox.getComponent(3);//inner
			Component contactNumber2=contactInfoBox.getComponent(5);//inner
			Component contactNumber3=contactInfoBox.getComponent(7);//inner
			Component contactNumber4=contactInfoBox.getComponent(9);//inner
			Component contactNumber5=contactInfoBox.getComponent(11);//inner
			Component contactNumber6=contactInfoBox.getComponent(13);//inner
			Component contactNumber7=contactInfoBox.getComponent(15);//inner
			Component contactNumber8=contactInfoBox.getComponent(17);//inner
			Component contactNumber9=contactInfoBox.getComponent(19);//inner
			Component contactNumber10=contactInfoBox.getComponent(21);//inner
			Component contactNumber11=contactInfoBox.getComponent(23);//inner
			String concatenatedContactnumber =((JTextField) contactNumber1).getText()+((JTextField) contactNumber2).getText()+((JTextField) contactNumber3).getText()+((JTextField) contactNumber4).getText()+((JTextField) contactNumber5).getText()+((JTextField) contactNumber6).getText()+((JTextField) contactNumber7).getText()+((JTextField) contactNumber8).getText()+((JTextField) contactNumber9).getText()+((JTextField) contactNumber10).getText()+((JTextField) contactNumber11).getText();
			tempPatient.contactNum =concatenatedContactnumber;//retireives attribute from component
		}
		catch(Exception exc)//if any errrors are found they are caught here		
		{
			
		}
		
		//nationality
		tempPatient.nationality = ((JTextField) nationality).getText();//retireives attribute from component
		//religon
		tempPatient.religon = ((JTextField) religon).getText();//retireives attribute from component
		//bloodtype
		tempPatient.bloodType =(""+((JComboBox) bloodTypedrop).getSelectedItem());//retireives attribute from component
		//allergies
		tempPatient.allergies = ((JTextArea) allergies).getText();//retireives attribute from component
		if(tempPatient.allergies.equals("Please enter any Allergies You may \npossess"))
		{
			tempPatient.allergies = "None";
		}	
		//Sliders
		tempPatient.smoker = ((JSlider) smokingSlider).getValue();//retireives attribute from component
		tempPatient.drinker = ((JSlider) drinkingSlider).getValue();//retireives attribute from component
		tempPatient.disability = ((JTextArea) listOfDisabilities).getText();//retireives attribute from component
		if(tempPatient.disability.equals(""))
		{
			tempPatient.disability = "None";
		}
		tempPatient.carer = ((JRadioButton) carerRadio).isSelected();//retireives attribute from component
		tempPatient.translator = ((JRadioButton) translatorRadio).isSelected();//retireives attribute from component
		
		
		validated = tempPatient.validatePatientInput(tempPatient);
		if(validated==true)
		{
			userPatient.firstName = tempPatient.firstName;
			userPatient.surName =tempPatient.surName;
			userPatient.gender =tempPatient.gender;
			userPatient.sex =tempPatient.sex;
			userPatient.dob =tempPatient.dob;
			userPatient.addressHouseNum =tempPatient.addressHouseNum;
			userPatient.addressHouseStreet = tempPatient.addressHouseStreet;
			userPatient.postcode = tempPatient.postcode;
			userPatient.town = tempPatient.town;
			userPatient.county = tempPatient.county;
			userPatient.contactNum =tempPatient.contactNum;
			userPatient.nationality = tempPatient.nationality;
			userPatient.religon = tempPatient.religon;
			userPatient.bloodType =tempPatient.bloodType;;
			userPatient.allergies =tempPatient.allergies;
			userPatient.smoker =tempPatient.smoker;
			userPatient.drinker = tempPatient.drinker;
			userPatient.disability = tempPatient.disability;
			userPatient.carer = tempPatient.carer;
			userPatient.translator = tempPatient.translator;
			userPatient = userPatient = userPatient.createNewNotification(userPatient,"Your demographic information has been updated.");
			userPatient.updateDemographicDetails(userPatient);
			

		}
	}
	
	//====== graphical aspect ======
	
	//HOMEPAGE
	//inital call methof for the panel this will load and add the panel to the frame and will perform
	//any other actions such as adding data/updateing it if requeired
	public void createPatientHomepagePanelGUI(/*Patient currentPatient*/)//once the login button has been pressed the login page will be opened up and then the homepage will be created and displayed
	{	
		emptyArray(patientHomepagePanel);	
		createTopbar(patientHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		notificationbttn.setEnabled(false);
		createContactBarUser(patientHomepagePanel,contactBox);	
		displayWindow.setVisible(false);

		createNotificationsPatient();
		if(loaded[3][0]== false)//selection determines whether the panel is yet to be loaded
		{
			createMainPartPatientHomePageGeneral();//the components of the panel are cereated by running the method this is done initially so it is only done once and will be used throughout
			
			loaded[3][0]= true;//the variable is set as true to prevent the components from breing reran
		}
		//System.out.println("patientHomepagePanel");
		setActiveTopPanelBttn(homebttn);
		//createContactBarPatient(patientHomepagePanel,currentPatient);//the method that creates the contact bar is used with the current panel is being passed through		
		//here we are checking that  the patient has a booking
		if(userPatient.numberOfAdmissions<=0)
		{
			//here we set it to blank
			setPatientBooking(0);
		}
		else
		{
			if(((userPatient.mostRecentbooking==null)==false)&&((userPatient.mostRecentbooking.room.equals("Null"))==false))
			{
				//here we have a valid booking 1 siginifes true
				setPatientBooking(1);
			}
			else{
				//here we set it to blank
				setPatientBooking(0);
			}
		}
	
	}
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	public void setPatientBooking(int option)
	{
		if(option==1)
		{
			invalidAppointmentBox.setVisible(false);
			validAppointmentBox.setVisible(true);
			upcomingDate.setText(ft.format(userPatient.mostRecentbooking.dateOfNextApp));
			upcomingTime.setText(timeft.format(userPatient.mostRecentbooking.timeOfNextApp));
			upcomingConsultant.setText(userPatient.mostRecentbooking.consultantID);
			upcomingRoom.setText(userPatient.mostRecentbooking.room);
		}
		else
		{
			validAppointmentBox.setVisible(false);
			invalidAppointmentBox.setVisible(true);

		}
		
	}
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createMainPartPatientHomePageGeneral()
	{
		buttonFontFormatunderlined.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);//the attributes of the font are changed to have the fount contain an underlined part to it

		//welcome label
		JLabel homelablel = new JLabel();//declares a labbel for the main header on the login screen 
		homelablel.setFont(headerFont);//the font that has been declared is attached to the component
		homelablel.setSize(350,40);//the components size is correctly declared
		homelablel.setText("Home");//the label is geven text to add meaning to the label
		homelablel.setForeground(Color.decode("#004e63"));//the foreground of the component is given a white font
		homelablel.setLocation(1270,60);//sets the location of the component 
		patientHomepagePanel.add(homelablel);//the component is added to the panel

		JLabel welcomeLbl = new JLabel();//declares a labbel for the main header on the login screen 
		welcomeLbl.setFont(welcomeLblF);//the font that has been declared is attached to the component
		welcomeLbl.setSize(900,40);//the components size is correctly declared
		welcomeLbl.setText("Welcome");//the label is geven text to add meaning to the label
		welcomeLbl.setLocation(440,70);//sets the location of the component 
		patientHomepagePanel.add(welcomeLbl);//the component is added to the panel

		JLabel titleUpperBlackLine = new JLabel();//declares a labbel for the main header on the login screen 
		titleUpperBlackLine.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		titleUpperBlackLine.setForeground( new Color(1) );//the foreground of the component is given a white font
		titleUpperBlackLine.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		titleUpperBlackLine.setLocation(400,60);//sets the location of the component 
		titleUpperBlackLine.setSize(600,3);//the components size is correctly declared
		titleUpperBlackLine.setOpaque(true);//the component is set to opaque
		patientHomepagePanel.add(titleUpperBlackLine);//the component is added to the panel

		JLabel titleLowerBlackLine = new JLabel();//declares a labbel for the main header on the login screen 
		titleLowerBlackLine.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		titleLowerBlackLine.setForeground( new Color(1) );//the foreground of the component is given a white font
		titleLowerBlackLine.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		titleLowerBlackLine.setLocation(400,120);//sets the location of the component 
		titleLowerBlackLine.setSize(600,3);//the components size is correctly declared
		titleLowerBlackLine.setOpaque(true);//the component is set to opaque
		patientHomepagePanel.add(titleLowerBlackLine);//the component is added to the panel



		JLabel notificationPrompt = new JLabel();
		notificationPrompt.setText("Notifications.");
		notificationPrompt.setSize(1000,40);
		notificationPrompt.setLocation(410,170);
		notificationPrompt.setFont(whiteLoginFont);//the component has had its font set to a design with the correct size for its purpose
		patientHomepagePanel.add(notificationPrompt);//the component is added to the panel
		
		
		patientHomepagePanel.add(scrollpaneNotitifications);
		scrollpaneNotitifications.setSize(1000,280);
		scrollpaneNotitifications.setLocation(400,200);
		scrollpaneNotitifications.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpaneNotitifications.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpaneNotitifications.setVisible(true);
		scrollpaneNotitifications.setBackground(darkButtonGrey);
		scrollpaneNotitifications.getVerticalScrollBar().setUnitIncrement(16);
		
		mainAppointmentBox.setBorder(BorderFactory.createLineBorder(Color.black));
		patientHomepagePanel.add(mainAppointmentBox);
		mainAppointmentBox.setSize(700,280);
		mainAppointmentBox.setLocation(400,500);
		mainAppointmentBox.add(appointmentPanelBox);
		
		//here we add the two boexes to the main panel
		appointmentPanelBox.setSize(700,280);
		appointmentPanelBox.add(invalidAppointmentBox);
		appointmentPanelBox.add(validAppointmentBox);
		
		invalidAppointmentBox.setVisible(false);
		validAppointmentBox.setVisible(false);
		
		//we add the mini panels to the sub boxes
		invalidAppointmentBox.add(invalidappointmentPanelBox);
		//invalidappointmentPanelBox
		invalidAppointmentBox.setSize(700,280);
		invalidAppointmentBox.setLocation(0,0);
		invalidappointmentPanelBox.setOpaque(true);
		invalidappointmentPanelBox.setBackground(lightGreyColor);
		//any items we add to the mini panels
		
		JLabel noAppointmentLBL = new JLabel();
		noAppointmentLBL.setText("No Appointments coming up.");
		noAppointmentLBL.setSize(960,60);//the components size is correctly declared
		noAppointmentLBL.setLocation(40,45);
		noAppointmentLBL.setFont(headerFont);//the component has had its font set to a design with the correct size for its purpose
		noAppointmentLBL.setFont(headerFont.deriveFont(headerFont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		invalidappointmentPanelBox.add(noAppointmentLBL);//the component is added to the panel
		
		
		
		JLabel whiteBackGround = new JLabel();
		whiteBackGround.setFont(loginfont);//the font that has been declared is attached to the component
		whiteBackGround.setSize(660,240);//the components size is correctly declared
		whiteBackGround.setBackground(new Color(-1));//the background colour of the component is declared to the desired value 
		whiteBackGround.setOpaque(true);//the background colour of the component is declared to the desired value 
		whiteBackGround.setLocation(20,20);
		invalidappointmentPanelBox.add(whiteBackGround);//the component is added to the panel
		
		//we add the mini panels to the sub boxes
		validAppointmentBox.add(validappointmentPanelBox);
		//validappointmentPanelBox
		validAppointmentBox.setSize(700,280);
		validAppointmentBox.setLocation(0,0);
		validappointmentPanelBox.setOpaque(true);
		validappointmentPanelBox.setBackground(lightGreyColor);
		
		//any items we add to the mini panels
		JLabel nextAppointmentLBL = new JLabel();
		nextAppointmentLBL.setText("Next Appointment");
		nextAppointmentLBL.setSize(960,60);//the components size is correctly declared
		nextAppointmentLBL.setLocation(40,25);
		nextAppointmentLBL.setFont(headerFont);//the component has had its font set to a design with the correct size for its purpose
		nextAppointmentLBL.setFont(headerFont.deriveFont(headerFont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		validappointmentPanelBox.add(nextAppointmentLBL);//the component is added to the panel
		
		JLabel datepromptLBL = new JLabel();
		datepromptLBL.setText("Date");
		datepromptLBL.setSize(960,55);//the components size is correctly declared
		datepromptLBL.setLocation(40,85);
		datepromptLBL.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		datepromptLBL.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		validappointmentPanelBox.add(datepromptLBL);//the component is added to the panel
		
		
		upcomingDate.setSize(960,60);//the components size is correctly declared
		upcomingDate.setLocation(120,85);
		upcomingDate.setFont(buttonFontFormatun.deriveFont(buttonFontFormatunderlined));
		validappointmentPanelBox.add(upcomingDate);//the component is added to the panel


		JLabel timepromptLBL = new JLabel();
		timepromptLBL.setText("Time");
		timepromptLBL.setSize(960,55);//the components size is correctly declared
		timepromptLBL.setLocation(40,155);
		timepromptLBL.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		timepromptLBL.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		validappointmentPanelBox.add(timepromptLBL);//the component is added to the panel
		
		
		upcomingTime.setSize(960,60);//the components size is correctly declared
		upcomingTime.setLocation(120,155);
		upcomingTime.setFont(buttonFontFormatun.deriveFont(buttonFontFormatunderlined));
		validappointmentPanelBox.add(upcomingTime);//the component is added to the panel
		
		
		JLabel consultantPrompt = new JLabel();
		consultantPrompt.setText("Consultant");
		consultantPrompt.setSize(960,55);//the components size is correctly declared
		consultantPrompt.setLocation(315,85);
		consultantPrompt.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		consultantPrompt.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		validappointmentPanelBox.add(consultantPrompt);//the component is added to the panel
		
		upcomingConsultant.setSize(1080,60);//the components size is correctly declared
		upcomingConsultant.setLocation(440,85);
		upcomingConsultant.setFont(buttonFontFormatun.deriveFont(buttonFontFormatunderlined));
		validappointmentPanelBox.add(upcomingConsultant);//the component is added to the panel
		
		JLabel roomPrompt = new JLabel();
		roomPrompt.setText("Room");
		roomPrompt.setSize(960,55);//the components size is correctly declared
		roomPrompt.setLocation(245,155);
		roomPrompt.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		roomPrompt.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		validappointmentPanelBox.add(roomPrompt);//the component is added to the panel
		
		upcomingRoom.setSize(1080,60);//the components size is correctly declared
		upcomingRoom.setLocation(330,155);
		upcomingRoom.setFont(buttonFontFormatun.deriveFont(buttonFontFormatunderlined));
		validappointmentPanelBox.add(upcomingRoom);//the component is added to the panel
		
		upcomingTime.setSize(960,60);//the components size is correctly declared
		upcomingTime.setLocation(120,155);
		upcomingTime.setFont(buttonFontFormatun.deriveFont(buttonFontFormatunderlined));
		validappointmentPanelBox.add(upcomingTime);//the component is added to the panel

		
		JLabel whiteBackGround1 = new JLabel();
		whiteBackGround1.setFont(loginfont);//the font that has been declared is attached to the component
		whiteBackGround1.setSize(660,240);//the components size is correctly declared
		whiteBackGround1.setLocation(20,20);
		whiteBackGround1.setBackground(new Color(-1));//the background colour of the component is declared to the desired value 
		whiteBackGround1.setOpaque(true);//the background colour of the component is declared to the desired value 
		validappointmentPanelBox.add(whiteBackGround1);//the component is added to the panel
	}
	
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createNotificationsPatient()
	{//
		mainBoxNotifications.removeAll();
		mainBoxNotifications.setBackground(darkGreyColour);
		mainBoxNotifications.setOpaque(true);
		if(userPatient.numberOfNotifications==0)
		{
			JLabel noAppointmentLBL = new JLabel();
			noAppointmentLBL.setText("You currently have no notifications.");
			noAppointmentLBL.setForeground( new Color(-1) );//the foreground of the component is given a white font
			noAppointmentLBL.setMinimumSize(new Dimension(1000,265));
			noAppointmentLBL.setPreferredSize(new Dimension(1000,265));
			noAppointmentLBL.setMaximumSize(new Dimension(1000,265));
			noAppointmentLBL.setFont(headerFont);//the component has had its font set to a design with the correct size for its purpose
			noAppointmentLBL.setFont(headerFont.deriveFont(headerFont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose				time.setForeground( new Color(1) );//the foreground of the component is given a white font
			mainBoxNotifications.add(noAppointmentLBL);//the component is added to the panel
		
		}
		for (int i = 0; i <userPatient.numberOfNotifications; ++i)
		{
			mainBoxNotifications.add(Box.createRigidArea(new Dimension(0,20)));
			Box notificationBox = new Box(BoxLayout.LINE_AXIS);
			JTextArea description = new JTextArea();
			JButton deleteNotificationBttn = new JButton();//declares a button to allow the user to create an account of that type
			description.setEditable(false);
			description.setText(userPatient.notifications[i]);
			notificationBox.putClientProperty("temp",String.valueOf(i));
			description.setFont(textfont);
			notificationBox.setOpaque(true);
			
			notificationBox.setMinimumSize(new Dimension(1000,65));
			notificationBox.setPreferredSize(new Dimension(1000,65));
			notificationBox.setMaximumSize(new Dimension(1000,65));
			
			description.setMinimumSize(new Dimension(880,65));
			description.setPreferredSize(new Dimension(880,65));
			description.setMaximumSize(new Dimension(880,65));
			notificationBox.add(Box.createRigidArea(new Dimension(20,0)));

			notificationBox.add(description);
			//formatting 
			
			
			
			
			deleteNotificationBttn.setMinimumSize(new Dimension(65,65));
			deleteNotificationBttn.setPreferredSize(new Dimension(65,65));
			deleteNotificationBttn.setMaximumSize(new Dimension(65,65));
			deleteNotificationBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
			deleteNotificationBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
			deleteNotificationBttn.setFont(admissionCardFont);//the font that has been declared is attached to the component 
			deleteNotificationBttn.setText("X");
			deleteNotificationBttn.putClientProperty("AvailableButton",String.valueOf(i));
			deleteNotificationBttn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					JButton source = (JButton)e.getSource();
					int intA = Integer.parseInt( (String) source.getClientProperty("AvailableButton"));
					deleteNotification(intA);
					mainBoxNotifications.removeAll();
					createNotificationsPatient();
					patientHomepagePanel.setVisible(false);
					patientHomepagePanel.setVisible(true);
					
				}	
			});	
				notificationBox.add(deleteNotificationBttn);
				notificationBox.add(Box.createRigidArea(new Dimension(20,0)));
				mainBoxNotifications.add(notificationBox);
				listOfNotifications[i] = deleteNotificationBttn;
				mainBoxNotifications.add(Box.createRigidArea(new Dimension(20,0)));
			}
	}	
	//this method is unique where the user delete the notification it has to perofrm the task of clearing the value in realtime along with deleting the value in the file
	//using the index of the notification the value is removed from the list and then accordingly dealt with
	public void deleteNotification(int intPosition)//the parameter is the position in the array containg the notification wanting removed
	{  
		int iterations = userPatient.numberOfNotifications - intPosition-1;//the number of iterations is calculated to find the the number of swaps needed
		if(iterations == 0)//if no more swaps can occur this is ran(there is only one item left in the array)
		{
			userPatient.notifications[intPosition] = null;//the contents are emptied
			userPatient.numberOfNotifications--;//the number of notfications is decrimented
		}
		else
		{
			do//a do until loop is declared to iterate until the condition is satisfied
			{
				userPatient.notifications[intPosition] = userPatient.notifications[intPosition +1];//the current notification is replaced by the newer one, no need for a buffer value as the first one is the deleted value
				iterations --;//the number of iterations required decriments
				intPosition++;// the position of the index incriments as now two indexes will be the same, this porcess repeats until the end of the array is reached
				
			}while(iterations!=0);//termination condition looking untill no more iterations can occur
			userPatient.notifications[intPosition] = null;//the last position of the array is cleared as this will contain a copied value
			userPatient.numberOfNotifications--;//the number of iterations left decriments 
		}
		userPatient.updateNotifications(userPatient);
	}
	
	
	//new Patient
	
	//as the Panels components are global and used throughout if a new account needs to be created this method wipes the contents of the values
	//inital call methof for the panel this will load and add the panel to the frame and will perform
	//any other actions such as adding data/updateing it if requeired
	public void createNewPatientComponentsGUIGeneral()
	{
		//Fname
		Component firstNameTf=fNameBox.getComponent(3);//inner
		((JTextField) firstNameTf).setText("Please enter your first name");
		//Sname
		Component surnameNameTf=sNameBox.getComponent(3);//inner
		((JTextField) surnameNameTf).setText("Please enter your surname");
		
		Component dateBookingBox=dOBBox.getComponent(3);//inner
		((DatePicker) dateBookingBox).clear();
	
		//Address
			//name
			Component addressName=addressBuildingBox.getComponent(3);//inner
			((JTextField) addressName).setText("Please enter your building number");
			//Street
			Component addressStreet=addressStreetBox.getComponent(3);//inner
			((JTextField) addressStreet).setText("Please enter your street");
			//Town
			Component addressTown=addressTownBox.getComponent(3);//inner
			((JTextField) addressTown).setText("Please enter your town/city");
			//County
			Component addressCounty=addressCountyBox.getComponent(3);//inner
			((JTextField) addressCounty).setText("Please enter your county");
			//postcode
		
			Component addressPostcode1=addressPostcodeBox.getComponent(3);//inner
			((JTextField) addressPostcode1).setText("");
			Component addressPostcode2=addressPostcodeBox.getComponent(5);//inner
			((JTextField) addressPostcode2).setText("");
			Component addressPostcode3=addressPostcodeBox.getComponent(7);//inner
			((JTextField) addressPostcode3).setText("");
			Component addressPostcode4=addressPostcodeBox.getComponent(9);//inner
			((JTextField) addressPostcode4).setText("");
			Component addressPostcode5=addressPostcodeBox.getComponent(11);//inner
			((JTextField) addressPostcode5).setText("");
			Component addressPostcode6=addressPostcodeBox.getComponent(13);//inner
			((JTextField) addressPostcode6).setText("");
			Component addressPostcode7=addressPostcodeBox.getComponent(15);//inner
			((JTextField) addressPostcode7).setText("");
			
		//contact information
		Component contactNumber1=contactInfoBox.getComponent(3);//inner
		((JTextField) contactNumber1).setText("");
		Component contactNumber2=contactInfoBox.getComponent(5);//inner
		((JTextField) contactNumber2).setText("");
		Component contactNumber3=contactInfoBox.getComponent(7);//inner
		((JTextField) contactNumber3).setText("");
		Component contactNumber4=contactInfoBox.getComponent(9);//inner
		((JTextField) contactNumber4).setText("");
		Component contactNumber5=contactInfoBox.getComponent(11);//inner
		((JTextField) contactNumber5).setText("");
		Component contactNumber6=contactInfoBox.getComponent(13);//inner
		((JTextField) contactNumber6).setText("");
		Component contactNumber7=contactInfoBox.getComponent(15);//inner
		((JTextField) contactNumber7).setText("");
		Component contactNumber8=contactInfoBox.getComponent(17);//inner
		((JTextField) contactNumber8).setText("");
		Component contactNumber9=contactInfoBox.getComponent(19);//inner
		((JTextField) contactNumber9).setText("");
		Component contactNumber10=contactInfoBox.getComponent(21);//inner
		((JTextField) contactNumber10).setText("");
		Component contactNumber11=contactInfoBox.getComponent(23);//inner
		((JTextField) contactNumber11).setText("");
		
		//BloodType
		Component bloodTypedrop=bloodTypeBox.getComponent(3);//inner
		((JComboBox) bloodTypedrop).setSelectedItem("Please select a Blood Type");
		
		//Gender
		Component genderDrop=genderBox.getComponent(3);//inner
		((JComboBox) genderDrop).setSelectedItem("Please select the gender you identify as.");
		
		//Sex
		Component sexDropDown=sexBox.getComponent(3);//inner
		((JComboBox) sexDropDown).setSelectedItem("Please select the sex you are");

		//Religon
		Component religon=religonBox.getComponent(3);//inner
		((JTextField) religon).setText("Please enter your Religion");
		
		//Drinker
		Component drinkingSlider=drinkerBox.getComponent(1);//inner
		((JSlider) drinkingSlider).setValue(0);

		//Smoker
		Component smokingSlider=smokerBox.getComponent(1);//inner
		((JSlider) smokingSlider).setValue(0);

		//allergies
		Component allergies=allergiesBox.getComponent(1);//inner
		((JTextArea) allergies).setText("Please enter any Allergies You may \npossess");
		
		//Nationality
		Component nationality=nationalityBox.getComponent(3);//inner
		((JTextField) nationality).setText("Please enter your home country");
		
		//Disabilities
		
		
		demographicDisablitiesPromptLbl.setSelected(false);
		Component disabilitiesTa=disabilitiesBox.getComponent(1);//inner
		((JTextArea) disabilitiesTa).setEditable(false);
		((JTextArea) disabilitiesTa).setBackground(lightGreyColor);
		Component disabilitiescarer=disabilitiesBox.getComponent(2);//inner
		((JRadioButton) disabilitiescarer).setEnabled(false);
		Component disabilitiesTranslator=disabilitiesBox.getComponent(3);//inner
		((JRadioButton) disabilitiesTranslator).setEnabled(false);
		Component translatorRadio=disabilitiesBox.getComponent(3);//inner
		((JRadioButton) translatorRadio).setSelected(false);
		Component carerRadio=disabilitiesBox.getComponent(2);//inner
			((JRadioButton) carerRadio).setSelected(false);
			Component listOfDisabilities=disabilitiesBox.getComponent(1);//inner
			

		createPatientPanel.add(fNameBox);	
		createPatientPanel.add(dOBBox);	
		createPatientPanel.add(sNameBox);	
		createPatientPanel.add(disabilitiesBox);	
		createPatientPanel.add(nationalityBox);	
		createPatientPanel.add(allergiesBox);
		createPatientPanel.add(smokerBox);
		createPatientPanel.add(drinkerBox);
		createPatientPanel.add(sexBox);	
		createPatientPanel.add(genderBox);	
		createPatientPanel.add(religonBox);	
		createPatientPanel.add(bloodTypeBox);	
		createPatientPanel.add(contactInfoBox);	
		createPatientPanel.add(addressBox);	
		createPatientPanel.add(userProfilePicfield);	
		
	}
	//This intialises the components of the new patient panel that cant be used else where for instance the buttons which navigate between the panels
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createNewPatientHomepagePanelGUI()
	{
		createNewPatientComponentsGUIGeneral();//the components of the panel are cereated by running the method this is done initially so it is only done once and will be used throughout
		if(loaded[0][2] ==false)//selection determines whether the panel is yet to be loaded
		{
			newAccountcreateAccountbbtn.setLocation(1140,740);//the components size is correctly declared
			newAccountcreateAccountbbtn.setSize(180,49);//the components size is correctly declared
			newAccountcreateAccountbbtn.setForeground( new Color(-1) );//the foreground of the component is given a white font
			newAccountcreateAccountbbtn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
			newAccountcreateAccountbbtn.setText("Create Account");//the label is geven text to add meaning to the label
			createPatientPanel.add(newAccountcreateAccountbbtn);//the component is added to the panel
			
			newPatientBackToLogin.setLocation(96,63);//sets the location of the component 
			newPatientBackToLogin.setSize(200,49);//the components size is correctly declared
			newPatientBackToLogin.setForeground( new Color(-1) );//the foreground of the component is given a white font
			newPatientBackToLogin.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
			newPatientBackToLogin.setText("Go Back");//the button is geven text to add meaning to the label
			createPatientPanel.add(newPatientBackToLogin);//the component is added to the panel
			loaded[0][2] = true;//the variable is set as true to prevent the components from being reran
		}
		addPanel(createPatientPanel);
	}
	
	
	












	
//============== ADMISSION ==================

	//======== Entity based section =========
	
	// CREATE ADMISSION
	//in this method a few things occur depending on the user
	//If a patient the current instance of admission has their consultant and ward set as PENDING and then have their information written to file
	//IF the user is the consultant then some advanced things occur
	//Like the patient the consultantID is set as pending but the ward is set to that of the consultant 
	//Then the information is written to file
	//in order for the consutlant swap to occur we needthe consultant value as pending hence the need to set it despite knowing it
	//now we then call the set method which sets the current admission as one of the consultants allowing use to then finally set the current instnace and file version the new consultant id
	//Finally we get to a point both the patient and consultant will run
	// the admision now in file, is attached to the correct instances
	//We return the user back  to the admission homescreen adn set the current admission as the lastest one, making sure the tabs are correctly set also.
	//then a method is called which writes them to file and also assinging them to the main user
	public void createAdmission()
	{
		
		//before the admission is created we can check if the consultant has created the admission and then add the consultant ID
		
			if(loginChoice==0)
			{
				tmpAdmission.ward = "PENDING";
				tmpAdmission.consultantID = "PENDING";
				tmpAdmission.active = false;
				AdmissionList Al = new AdmissionList();
				tmpAdmission = Al.createNewAdmission(tmpAdmission,userPatient,areaArrayCounter);
				userPatient.listOfAdmissions = userPatient.extendArrayByOne(userPatient.listOfAdmissions);
				userPatient.listOfAdmissions[userPatient.numberOfAdmissions-1]=tmpAdmission;
				userPatient = userPatient.retrievePatient(userPatient.patientID);//updates instacne of patient to avoid any overwrites of old data
				systemAdmissionList = userPatient.listOfAdmissions;
		
			}
				
			if(loginChoice!=0)
			{
				tmpAdmission.ward = userConsultant.wardLocatedIn;
				tmpAdmission.consultantID = "PENDING";
				tmpAdmission.active = true;
				AdmissionList Al = new AdmissionList();
				tmpAdmission = Al.createNewAdmission(tmpAdmission,userPatient,areaArrayCounter);
				userPatient.listOfAdmissions = userPatient.extendArrayByOne(userPatient.listOfAdmissions);
				userPatient.listOfAdmissions[userPatient.numberOfAdmissions-1]=tmpAdmission;
				userPatient = tmpAdmission.moveAdmissionBetweenAdmission(tmpAdmission,userConsultant.consultantID,userPatient.patientID,userPatient);
				userPatient.listOfAdmissions[userPatient.numberOfAdmissions-1].consultantID = userConsultant.consultantID;
				Al.updateAdmission(userPatient,userPatient.listOfAdmissions[userPatient.numberOfAdmissions-1],12);//updates the last admission with the updated consultanttID
				userPatient = userPatient.retrievePatient(userPatient.patientID);//updates instacne of patient to avoid any overwrites of old data
				systemAdmissionList = userConsultant.pullAdmissionsFromFile(userConsultant).pullAdmissions(userConsultant.concatenatedconsultantPatientList[currentPatientIndex].split("#"),userPatient);
			userPatient.createNewAction("Created Admission",employeeIDGlobal,userPatient.patientID,systemAdmissionList[currentAdmissionIndex].admissionID,"New admission information","N/A");

			}
			listOfsortedDocumentIndexes = new int[0];
			createAdmissionDocumentpanel(listOfsortedDocumentIndexes);
			
			//GUI Aspects
			//returns user home
			listOfsortedDocumentIndexes = new int[0];
			createAdmissionDocumentpanel(listOfsortedDocumentIndexes);//As new admisison is created this will clear and correclty add any documents to the system
			currentAdmissionIndex = systemAdmissionList.length-1;//sets current admission as the newest one
			createadmissionHomepagePanelGUI();
			buttonBar.removeAll();
			createButtonTopBarAdmission();
			buttonList[currentAdmissionIndex].setEnabled(false);
			buttonList[currentAdmissionIndex].setBackground(selectedBttcColour);//the background colour of the component is declared to the desired value
			topBarNewAdmissionBttn.setEnabled(true);
			topBarNewAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
			userPatient = userPatient = userPatient.createNewNotification(userPatient,"A new admission has been created under"+ systemAdmissionList[currentAdmissionIndex].admissionID);


	}
		
	
	//In this method like for the general account creation the details are pulled from Gui and then assigned to the correct admission
	//from this two processes occur, intially in real time the instance is updated where its values are then assigned the current admission instance, after this the user is returned  home
	//where their new attribues are able to be seen
	//Before being returned home the admission is also wirtten to file to correctly store the data that has been entered
	//before it is written the system will validate the input
	public void updateAdmission()
	{
		//initally we create a backup of the admission incase we need to restore it
		Admission backUpAdmission = new Admission();
		Component wardTfData=wardBox.getComponent(3);//inner
		Component consultantTfData=consultantIDBox.getComponent(3);//inner
		Component currentDiagnosisTfData=currentDiagnosisBox.getComponent(3);//inner
		Component currentSymptomsTfData=currentSymptomsBox.getComponent(1);//inner
	
		
		backUpAdmission.ward =((JTextField) wardTfData).getText();
		backUpAdmission.consultantID = (""+((JComboBox) consultantTfData).getSelectedItem());
		backUpAdmission.currentDiagnosis = ((JTextField) currentDiagnosisTfData).getText();
		backUpAdmission.listOfSymptoms = ((JTextArea) currentSymptomsTfData).getText().split("\n");
		
		int areaArrayCounter = 0;
		if(cbHips.isSelected()==true)//selection determining if the checkbox had been selected
		{
			areaArrayCounter++;
		}
		if(cbShoulder.isSelected()==true)//selection determining if the checkbox had been selected
		{
							areaArrayCounter++;


		}
		if(cbNeck.isSelected()==true)//selection determining if the checkbox had been selected
		{
							areaArrayCounter++;

			

		}
		if(cbChest.isSelected()==true)//selection determining if the checkbox had been selected
		{
							areaArrayCounter++;

			

		}
		if(cbHand.isSelected()==true)//selection determining if the checkbox had been selected
		{
							areaArrayCounter++;

			

		}
		if(cbFoot.isSelected()==true)//selection determining if the checkbox had been selected
		{
							areaArrayCounter++;

		

		}
		if(cbHead.isSelected()==true)//selection determining if the checkbox had been selected
		{
							areaArrayCounter++;

		

		}
		if(cbAbdomen.isSelected()==true)//selection determining if the checkbox had been selected
		{
							areaArrayCounter++;

		

		}
		if(cbForeArm.isSelected()==true)//selection determining if the checkbox had been selected
		{
							areaArrayCounter++;


		}
		if(cbArm.isSelected()==true)//selection determining if the checkbox had been selected
		{
							areaArrayCounter++;


		}
		if(cbPelvis.isSelected()==true)//selection determining if the checkbox had been selected
		{
						areaArrayCounter++;


		}
		if(cbLeg.isSelected()==true)//selection determining if the checkbox had been selected
		{
			areaArrayCounter++;


		}
	
		backUpAdmission.listOfAreasAffected = new String[areaArrayCounter];
		areaArrayCounter=0;
		
		if(cbHips.isSelected()==true)//selection determining if the checkbox had been selected
		{
			backUpAdmission.listOfAreasAffected[areaArrayCounter]="Hips";
			areaArrayCounter++;
		}
		if(cbShoulder.isSelected()==true)//selection determining if the checkbox had been selected
		{
			backUpAdmission.listOfAreasAffected[areaArrayCounter]="Shoulder";
							areaArrayCounter++;


		}
		if(cbNeck.isSelected()==true)//selection determining if the checkbox had been selected
		{
			backUpAdmission.listOfAreasAffected[areaArrayCounter]="Neck";
							areaArrayCounter++;

			

		}
		if(cbChest.isSelected()==true)//selection determining if the checkbox had been selected
		{
			backUpAdmission.listOfAreasAffected[areaArrayCounter]="Chest";
							areaArrayCounter++;

			

		}
		if(cbHand.isSelected()==true)//selection determining if the checkbox had been selected
		{
			backUpAdmission.listOfAreasAffected[areaArrayCounter]="Hand";
							areaArrayCounter++;

			

		}
		if(cbFoot.isSelected()==true)//selection determining if the checkbox had been selected
		{
			backUpAdmission.listOfAreasAffected[areaArrayCounter]="Foot";
							areaArrayCounter++;

		

		}
		if(cbHead.isSelected()==true)//selection determining if the checkbox had been selected
		{
			backUpAdmission.listOfAreasAffected[areaArrayCounter]="Head";
							areaArrayCounter++;

		

		}
		if(cbAbdomen.isSelected()==true)//selection determining if the checkbox had been selected
		{
			backUpAdmission.listOfAreasAffected[areaArrayCounter]="Abdomen";
							areaArrayCounter++;

		

		}
		if(cbForeArm.isSelected()==true)//selection determining if the checkbox had been selected
		{
			backUpAdmission.listOfAreasAffected[areaArrayCounter]="Forearm";
							areaArrayCounter++;


		}
		if(cbArm.isSelected()==true)//selection determining if the checkbox had been selected
		{
			backUpAdmission.listOfAreasAffected[areaArrayCounter]="Arm";
							areaArrayCounter++;


		}
		if(cbPelvis.isSelected()==true)//selection determining if the checkbox had been selected
		{
			backUpAdmission.listOfAreasAffected[areaArrayCounter]="Pelvis";
						areaArrayCounter++;


		}
		if(cbLeg.isSelected()==true)//selection determining if the checkbox had been selected
		{
			backUpAdmission.listOfAreasAffected[areaArrayCounter]="Leg";
							areaArrayCounter++;


		}

		AdmissionList Al = new AdmissionList();
		int indexActaulAdmissionOccurs = -1;
		if(cbFrequentRecurringPainsE.isSelected()==true)
		{
			backUpAdmission.typeOfPain ="Frequent recurring Pains";
			
		}
		if(cbStiffnessInMuscleE.isSelected()==true)
		{
			backUpAdmission.typeOfPain = "Stiffness in muscle";
			
		}
		if(cbAcutePainsE.isSelected()==true)
		{
			backUpAdmission.typeOfPain = "Acute Pains";
			
		}
		if(cbChronicPainsE.isSelected()==true)
		{
			backUpAdmission.typeOfPain = "Chronic pains";
			
		}
		
		//validation starts here
		if(loginChoice!=0)
		{	
			if((""+((JComboBox) consultantTfData).getSelectedItem()).equals("PENDING")==true)
			{
				JOptionPane.showMessageDialog(null, "Please select a consultant.");
				return;
			}
		}
		//here we have two different valiadtion methods depending on the input, if the patient has edited some fiedls the system will call the validation method used to create them
		boolean validatedAdmission;
		if(loginChoice==0)
		{
			validatedAdmission = backUpAdmission.validateAdmission(backUpAdmission);
			//System.out.println(validatedAdmission);
		}
		//if the method is called by an employee the system will then call a validation methods for the relevant fields
		else{
			validatedAdmission = backUpAdmission.validateAdmissionConsultant(backUpAdmission);
			//System.out.println(validatedAdmission);
		}
		if(validatedAdmission == true)
		{
			userPatient = userPatient.createNewNotification(userPatient,"The admission "+ systemAdmissionList[currentAdmissionIndex].admissionID+" has been updated with new information");
			systemAdmissionList[currentAdmissionIndex].typeOfPain = backUpAdmission.typeOfPain;
			systemAdmissionList[currentAdmissionIndex].listOfAreasAffected = backUpAdmission.listOfAreasAffected;
			systemAdmissionList[currentAdmissionIndex].ward = backUpAdmission.ward;
			systemAdmissionList[currentAdmissionIndex].currentDiagnosis = backUpAdmission.currentDiagnosis;
			systemAdmissionList[currentAdmissionIndex].listOfSymptoms = backUpAdmission.listOfSymptoms;
			//here we are proghibiting the patient from updating the consultant
			if(loginChoice!=0)
			{	
					userPatient.createNewAction("Updated Admission",employeeIDGlobal,userPatient.patientID,systemAdmissionList[currentAdmissionIndex].admissionID,"New admission information","Old admission information");
				systemAdmissionList[currentAdmissionIndex].active = true;
				String tempConsultantID = (""+((JComboBox) consultantTfData).getSelectedItem());
				if(tempConsultantID.equals("PENDING")==false)
				{
					if(tempConsultantID.equals(backUpAdmission)==false)
					{
						//System.out.println("Not the same consultant ");
						//MEthod which moves admission between one consultant to the other, at this point the consultant for the admission may still be pending so can use this to my advantage and skip the deleting process
						userPatient= userPatient.listOfAdmissions[currentAdmissionIndex].moveAdmissionBetweenAdmission(systemAdmissionList[currentAdmissionIndex],""+((JComboBox) consultantTfData).getSelectedItem(),userPatient.patientID,userPatient);
					}
				}
			
			}
						systemAdmissionList[currentAdmissionIndex].consultantID = backUpAdmission.consultantID;

			
			ForLoop:
			for(int counter =0;counter<userPatient.numberOfAdmissions;counter++)
			{
				if(userPatient.listOfAdmissions[counter].admissionID.equals(systemAdmissionList[currentAdmissionIndex].admissionID))
				{
					userPatient.listOfAdmissions[counter] = systemAdmissionList[currentAdmissionIndex];
					indexActaulAdmissionOccurs=counter;
					break ForLoop;
				}
			}
			Al.updateAdmission(userPatient,userPatient.listOfAdmissions[indexActaulAdmissionOccurs],areaArrayCounter);
			//has to check which user updated the admission, if it was the patient/staff or the consultant
			if(loginChoice!=3)
			{
				systemAdmissionList = userPatient.listOfAdmissions;
			}
			else{
				updateConsultantAdmissionList();
			}
			if(systemAdmissionList.length>0)
			{
				createadmissionHomepagePanelGUI();//the method containing all the new components is ran
			setActiveAdmissionPanelBttn(buttonList[currentAdmissionIndex]);
			}//only way this case is otherwise true is if we are a consultant with 1 patient with 1 admission
			else{
				createConsultantHomepagePanelGUI();//correct homepage is created
				consultantHomepagePanel.setVisible(false);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
				consultantHomepagePanel.setVisible(true);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
				admissionPatientbttn.setEnabled(false);//the component is disabled
				patientDemographicbttn.setEnabled(false);//the component is disabled
			
			}
		}
	}
	
	//when the discharge buttn is pressed the system will then correctly update the patient's admission and then updates the admission to reflect the changes on the account
	//besides this very little actually happens to the admission
	public void dischargeAdmission()
	{
		//System.out.println("Discharge button pressed");
		systemAdmissionList[currentAdmissionIndex].active=false;
		AdmissionList al = new AdmissionList();
		al.updateAdmission(userPatient,systemAdmissionList[currentAdmissionIndex],12);
		userPatient = userPatient = userPatient.createNewNotification(userPatient,"A staff account has discharged the admission "+ systemAdmissionList[currentAdmissionIndex].admissionID);
	}
	//this performs the opposie action for the process above instead of setting the attribute discharge statement as false instead it resets it back to original value of true
	//once the value has been updated on the instance it is then wirtten to file
	public void reinstateAdmission()
	{
		systemAdmissionList[currentAdmissionIndex].active=false;
				AdmissionList al = new AdmissionList();
		
		al.updateAdmission(userPatient,systemAdmissionList[currentAdmissionIndex],12);
		userPatient = userPatient = userPatient.createNewNotification(userPatient,"A staff account has reinstated the admission "+ systemAdmissionList[currentAdmissionIndex].admissionID);

	}
	
	
	//====== graphical aspect ======
	
	//inital call methof for the panel this will load and add the panel to the frame and will perform
	//any other actions such as adding data/updateing it if requeired
	public void createadmissionHomepagePanelGUI()//
	{
		createTopbar(admissionHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		emptyArray(admissionHomepagePanel);
		createTopbarAdmission(admissionHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		if(loginChoice!=0)
		{
		setActiveTopPanelBttn(admissionPatientbttn);
		}
		else
		{
		setActiveTopPanelBttn(admissionbttn);
		}
		if(systemAdmissionList.length>0)//selection determining if the patient has at least one admission
		{
			listOfsortedDocumentIndexes = new int[0];
			createAdmissionDocumentpanel(listOfsortedDocumentIndexes);//call this to update contents of the scroll box do not call the method which this is in
			createContactBarAdmission();//as the correct panel is visible the components on the panel is formatted
			if(loaded[6][2]== false)//selection determines whether the panel is yet to be loaded
			{
				createMainPartAdmissionHomePageGeneral(admissionHomepagePanel);//general parts are of the homepage are created by calling this method
				loaded[6][2]= true;//loaded is to true that the panel cant be reloaded
			}
			//createMainPartAdmissionHomePage(admissionHomepagePanel);//main parts of the homepage are genereated
			setActiveAdmissionPanelBttn(buttonList[currentAdmissionIndex]);
		}
		admissionHomepagePanel.setVisible(false);//the panel is hidden from sight	
		admissionHomepagePanel.setVisible(true);//the panel is then made to reappear 
		mainBoxNotificationstab.repaint();
		mainBoxNotificationstab.grabFocus();
		
	}
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createMainPartAdmissionHomePageGeneral(JPanel panel)
	{
		
		panel.add(searchBoxOuter);
		searchBoxOuter.setSize(975,745);
		searchBoxOuter.setLocation(400,115);
		searchBoxOuter.add(searchBox);
		searchBoxOuter.add(scrollpane);
		searchBoxOuter.setBorder(BorderFactory.createLineBorder(Color.black));
		searchBox.setBorder(BorderFactory.createLineBorder(Color.black));
		//scrollpane.setBorder(BorderFactory.createLineBorder(Color.black));

		
		scrollpane.setMinimumSize(new Dimension(975,695));
			scrollpane.setPreferredSize(new Dimension(975,695));
			scrollpane.setMaximumSize(new Dimension(975,695));
		scrollpane.setVisible(true);
		scrollpane.setBackground(darkButtonGrey);
		scrollpane.getHorizontalScrollBar().setUnitIncrement(16);
		 scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		
	}
	
	//Document panel
	
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	public void createAdmissionDocumentpanel(int[] listOfDocumentsindexes)
	{
		searchBoxOuter.removeAll();
		searchBoxOuter.add(searchBox);
		searchBoxOuter.add(scrollpane);
		sortComboBox.setSelectedItem("Ascending");//Set option by text
		mainBox.removeAll();
		int numOfPanels;
		//System.out.println("This has been ran");
		int numberOfDocumentsInArray=-1;
		boolean orginalList = false;
		if(listOfDocumentsindexes.length==0)
		{
			orginalList= true;
			//sortComboBox.setSelectedItem("Ascending");//Set option by text
			numberOfDocumentsInArray = systemAdmissionList[currentAdmissionIndex].listOfDocuments.length;
		}
		if(listOfDocumentsindexes.length!=0)
		{
			numberOfDocumentsInArray=listOfDocumentsindexes.length;
			//System.out.println("Short search");
		}
		int currentNumOfDocuments = 0;
		int numberOfMinorPanels = 2;
		int numberOfDocuemntsOutPutted = 3;
		int numOfLeftOverDocs=	numberOfDocumentsInArray%6;
		if(numOfLeftOverDocs!=0)
		{
			numOfPanels = (numberOfDocumentsInArray/6)+1;
			//System.out.println("HELLOOOO "+numOfPanels);
		}
		else
		{
			numOfPanels = (numberOfDocumentsInArray/6);
			//System.out.println("HELLOOOO "+numOfPanels);
		}
		int indexOfDocument = -1;
		Document tempDocument = new Document();
		buttonFontFormatunderlined.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);//the attributes of the font are changed to have the fount contain an underlined part to it

		for (int i = 0; i <numOfPanels; ++i)
		{
			Box largeBox = new Box(BoxLayout.Y_AXIS);
			largeBox.putClientProperty("box order",String.valueOf(i));
			largeBox.setBackground(darkGreyColour);
			largeBox.setOpaque(true);
			largeBox.setMinimumSize(new Dimension(975,680));
			largeBox.setPreferredSize(new Dimension(975,680));
			largeBox.setMaximumSize(new Dimension(975,680));
			largeBox.setBorder(BorderFactory.createLineBorder(Color.black));
			if((i==numOfPanels-1)&&(numOfLeftOverDocs!=0))
			{
				if(numOfLeftOverDocs>3)
				{
					numberOfMinorPanels = 2;
				}
				else
				{
					numberOfMinorPanels = 1;
				}
			}
			for (int k = 0; k <numberOfMinorPanels; ++k)
			{	
				////System.out.println("HERE");
				Box mediumBox = new Box(BoxLayout.LINE_AXIS);
				mediumBox.putClientProperty("box order",String.valueOf(i));
				mediumBox.setMinimumSize(new Dimension(975,340));
				mediumBox.setPreferredSize(new Dimension(975,340));
				mediumBox.setMaximumSize(new Dimension(975,340));
			
				if((i==numOfPanels-1)&&(k==numberOfMinorPanels-1)&&(k==0)&&(numOfLeftOverDocs!=0))
				{
					numberOfDocuemntsOutPutted = numOfLeftOverDocs;
					
				}
				if((i==numOfPanels-1)&&(k==numberOfMinorPanels-1)&&(k==1)&&(numOfLeftOverDocs!=0))
				{
					numberOfDocuemntsOutPutted = numOfLeftOverDocs-3;
					
				}
				mediumBox.add(Box.createRigidArea(new Dimension(30,0)));
				for (int j = 0; j <numberOfDocuemntsOutPutted; ++j)
				{
					
					//Initalising components
					Box SmallDocumentBox = new Box(BoxLayout.Y_AXIS);
					JPanel documentpanel = new JPanel(null);
					JLabel  admissioncardtypeLbl	= new JLabel();//Declares the text pane for the title of the admission card
					JLabel  admissioncardTitlepane	= new JLabel();//Declares the text pane for the title of the admission card
					JButton viewDocumentAdmissionBttn = new JButton();//Declares the button for the view document of the admission card
					JTextArea  admissioncardDescriptionpane11 = new JTextArea();//Declares the text pane for the description of the admission card
					
					//formatting components
					SmallDocumentBox.setOpaque(true);//the component is set to opaque
					SmallDocumentBox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
					SmallDocumentBox.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
					SmallDocumentBox.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value
					SmallDocumentBox.putClientProperty("Box order",String.valueOf(currentNumOfDocuments));
					SmallDocumentBox.setMinimumSize(new Dimension(250,320));
					SmallDocumentBox.setPreferredSize(new Dimension(250,320));
					SmallDocumentBox.setMaximumSize(new Dimension(250,320));
					
					documentpanel.setMinimumSize(new Dimension(250,325));
					documentpanel.setPreferredSize(new Dimension(250,325));
					documentpanel.setMaximumSize(new Dimension(250,325));
					documentpanel.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
					documentpanel.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value
					
					admissioncardTitlepane.setSize(240,40);
					admissioncardTitlepane.setLocation(10,40);
					admissioncardTitlepane.setFont(buttonFontFormatun.deriveFont(buttonFontFormatunderlined));
				
					admissioncardtypeLbl.setSize(240,40);
					admissioncardtypeLbl.setLocation(10,10);
					admissioncardtypeLbl.setFont(whiteLoginFont);
				
					admissioncardDescriptionpane11.setSize(200,190);
					admissioncardDescriptionpane11.setLocation(25,85);
					admissioncardDescriptionpane11.setBorder(BorderFactory.createLineBorder(Color.black));
					admissioncardDescriptionpane11.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
					admissioncardDescriptionpane11.setWrapStyleWord(true);
					admissioncardDescriptionpane11.setFont(admissionCardFont);//the font that has been declared is attached to the component 
					admissioncardDescriptionpane11.setEditable(false);
		
		
		
		
		
					viewDocumentAdmissionBttn.setSize(210,30);
					viewDocumentAdmissionBttn.setLocation(20,280);
					viewDocumentAdmissionBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
					viewDocumentAdmissionBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
					viewDocumentAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
					viewDocumentAdmissionBttn.setFont(admissionCardFont);//the font that has been declared is attached to the component 
					viewDocumentAdmissionBttn.setText("View Document");
					if(loginChoice ==1)
					{
						viewDocumentAdmissionBttn.setEnabled(false);
					}
					else
					{
						viewDocumentAdmissionBttn.setEnabled(true);
					}
					viewDocumentAdmissionBttn.addActionListener(new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent e)
						{
							JButton source = (JButton)e.getSource();
							String buttonID = (String) source.getClientProperty("button order");
							currentDocumentIndex =Integer.parseInt(buttonID);
							////System.out.print(buttonID);
							//special about this line is that depite whereever the document may be in the actaul array the button is always linked to that document index
							//System.out.println("button index "+currentDocumentIndex);
							//THIS LINE SHOULD NEVER CHANGE
							createDocumentPanel(systemAdmissionList[currentAdmissionIndex].listOfDocuments[currentDocumentIndex]);
						}
					});
					
					//here we are determining whether a button list is either a sorted/searched one or the original list if the normal one it will just assign the index of the current one representitive of the current instances
					//if it is a searched/sorted one it will use the global list of sorted indexs(which will hold the true index of the instance in the array and assign that instead of the current iteration)
					//eg if sort retruned the int array of 7 3 6 2 each button would be assigned the correct index along with setting the cards text appropriately instead of being 1 2 3 4
					
					if(orginalList== true)
					{
						//System.out.println("WE HAVE OG LIST");
						viewDocumentAdmissionBttn.putClientProperty("button order",String.valueOf(currentNumOfDocuments));
						indexOfDocument = currentNumOfDocuments;
					}
					else
					{
						viewDocumentAdmissionBttn.putClientProperty("button order",String.valueOf(listOfDocumentsindexes[currentNumOfDocuments]));
						indexOfDocument = listOfDocumentsindexes[currentNumOfDocuments];
					}
					tempDocument =systemAdmissionList[currentAdmissionIndex].listOfDocuments[indexOfDocument];
					
					
					//assinging text
					admissioncardTitlepane.setText(tempDocument.documentID);
					String dataTypeFiller ="";
					if(tempDocument.docType.contains("Discharge"))
					{
						admissioncardtypeLbl.setText("Discharge");
					}
					else if(tempDocument.docType.contains("Reinstatement"))
					{
						admissioncardtypeLbl.setText("Reinstatement");
					}
					else if(tempDocument.docType.contains("Prescription"))
					{
						admissioncardtypeLbl.setText("Prescription");
					}
					else if(tempDocument.legacyDocument==true)
					{
						admissioncardtypeLbl.setText(("[L]"+tempDocument.docType));
					}
					else{
					admissioncardtypeLbl.setText(tempDocument.docType);
					}
					admissioncardDescriptionpane11.setText("Created on: "+ft.format(tempDocument.dateOfDocumentCreation));
					
					
					//adding them to the panel
					documentpanel.add(admissioncardTitlepane);
					documentpanel.add(admissioncardtypeLbl);
					documentpanel.add(admissioncardDescriptionpane11);
					documentpanel.add(viewDocumentAdmissionBttn);
					SmallDocumentBox.add(documentpanel);
					mediumBox.add(SmallDocumentBox);
					mediumBox.add(Box.createRigidArea(new Dimension(80,0)));
					currentNumOfDocuments++;
				}
				
				largeBox.add(mediumBox);
				mediumBox.setAlignmentY(Component.TOP_ALIGNMENT);
			}
			mainBox.add(largeBox);
		}
		if(numberOfDocumentsInArray==0)
		{
			JLabel noAdmissionLbl = new JLabel();//declares a label for a box which is used for a visual improvement
			JLabel noAdmissionFootNoteLbl = new JLabel();//declares a label for a box which is used for a visual improvement
			JLabel noAdmissionFootNoteLbl2 = new JLabel();//declares a label for a box which is used for a visual improvement
			Box largeBox = new Box(BoxLayout.Y_AXIS);
			largeBox.setMinimumSize(new Dimension(975,740));
			largeBox.setPreferredSize(new Dimension(975,740));
			largeBox.setMaximumSize(new Dimension(975,740));
			noAdmissionLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			noAdmissionLbl.setFont(headerFontFormatSmallerr);//a font is applied to the text of the component 
			noAdmissionLbl.setLocation(300,210);//sets the location of the component 
			noAdmissionLbl.setSize(2000,100);//the components size is correctly declared
			noAdmissionLbl.setOpaque(false);//the component is set to opaque
			noAdmissionLbl.setText("The admission currently has no documents.");
			largeBox.add(noAdmissionLbl);
			noAdmissionFootNoteLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			noAdmissionFootNoteLbl.setFont(buttonFontFormat);//a font is applied to the text of the component 
			noAdmissionFootNoteLbl.setLocation(300,310);//sets the location of the component 
			noAdmissionFootNoteLbl.setSize(2000,100);//the components size is correctly declared
			noAdmissionFootNoteLbl.setOpaque(false);//the component is set to opaque
			noAdmissionFootNoteLbl.setText("Please wait for the staff or consultant to add a document before ");
			noAdmissionFootNoteLbl2.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
			noAdmissionFootNoteLbl2.setFont(buttonFontFormat);//a font is applied to the text of the component 
			noAdmissionFootNoteLbl2.setLocation(300,310);//sets the location of the component 
			noAdmissionFootNoteLbl2.setSize(2000,100);//the components size is correctly declared
			noAdmissionFootNoteLbl2.setOpaque(false);//the component is set to opaque
			noAdmissionFootNoteLbl2.setText("viewing this feature");
			loaded[6][6]= true;//loaded is to true that the panel cant be reloaded
			largeBox.add(noAdmissionFootNoteLbl);
			largeBox.add(noAdmissionFootNoteLbl2);
			mainBox.add(largeBox);
		}
		mainBox.setVisible(false);
		mainBox.setVisible(true);
	}
	
	//blank admission
	//when the userhas no admissions on their account the system will then create a homepage to informing them
	//here the system will generate this if the admission count is below 1
	//this to prevent any issues with trying to generate admisson data to a panel when no admission is present.
	public void createBlankAdmissionHomePage()
	{
		emptyArray(emptyAdmissionHomepagePanel);
		createTopbar(emptyAdmissionHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		createTopbarAdmission(emptyAdmissionHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		if(userPatient.numberOfAdmissions<=0)//selection determining if the patient has at least one admission
		{
			if(loaded[6][6]== false)//selection determines whether the panel is yet to be loaded
			{
				JLabel noAdmissionLbl = new JLabel();//declares a label for a box which is used for a visual improvement
				JLabel noAdmissionFootNoteLbl = new JLabel();//declares a label for a box which is used for a visual improvement
	
				noAdmissionLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
				noAdmissionLbl.setFont(headerFontFormat);//a font is applied to the text of the component 
				noAdmissionLbl.setLocation(300,210);//sets the location of the component 
				noAdmissionLbl.setSize(2000,100);//the components size is correctly declared
				noAdmissionLbl.setOpaque(false);//the component is set to opaque
				noAdmissionLbl.setText("There are no admissions assigned yet.");
				emptyAdmissionHomepagePanel.add(noAdmissionLbl);
				noAdmissionFootNoteLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
				noAdmissionFootNoteLbl.setFont(buttonFontFormat);//a font is applied to the text of the component 
				noAdmissionFootNoteLbl.setLocation(300,310);//sets the location of the component 
				noAdmissionFootNoteLbl.setSize(2000,100);//the components size is correctly declared
				noAdmissionFootNoteLbl.setOpaque(false);//the component is set to opaque
				noAdmissionFootNoteLbl.setText("Please create one if medical assistance is needed");
				loaded[6][6]= true;//loaded is to true that the panel cant be reloaded
				emptyAdmissionHomepagePanel.add(noAdmissionFootNoteLbl);
			}
		}
	}
	
	
	//new admission panel 
	//inital call methof for the panel this will load and add the panel to the frame and will perform
	//any other actions such as adding data/updateing it if requeired
	public void createNewAdmissionPanel()
	{
		createTopbar(newAdmissionPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		if(loaded[3][1] ==false)//selection determines whether the panel is yet to be loaded
		{
			createNewAdmissionPanelGeneral();//the components of the panel are cereated by running the method this is done initially so it is only done once and will be used throughout
			loaded[3][1] = true;//the variable is set as true to prevent the components from being reran
		}
		newAdmissionPanel.add(mainBoxPerson);
		mainBoxPerson.setLocation(36,130);//sets the location of the component 
		mainBoxPerson.setSize(447,700);//the components size is correctly declared
		setNonActiveTopPanel();//sets the top bar to have all buttons to be enabled
		addPanel(newAdmissionPanel);
		//clears any old attributes entered by past users
		clearHuman();
		typeOfPain.clearSelection();
		symptom1TF.setText("Symptom 1");
		symptom2TF.setText("Symptom 2");
		symptom3TF.setText("Symptom 3");
		symptom4TF.setText("Symptom 4");
		symptom1TF.setForeground(darkGreyColour);//the foreground of the component is given a white font
		symptom2TF.setForeground(darkGreyColour);//the foreground of the component is given a white font
		symptom3TF.setForeground(darkGreyColour);//the foreground of the component is given a white font
		symptom4TF.setForeground(darkGreyColour);//the foreground of the component is given a white font

	}
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createNewAdmissionPanelGeneral()
	{
			
		backToAdmissionbttn.setLocation(26,53);//sets the location of the component 
		backToAdmissionbttn.setSize(200,49);//the components size is correctly declared
		backToAdmissionbttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		backToAdmissionbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		backToAdmissionbttn.setText("Go Back");//the button is geven text to add meaning to the label
		newAdmissionPanel.add(backToAdmissionbttn);
		

		Box symptomsBox = new Box(BoxLayout.Y_AXIS);
		symptomsBox.setSize(420,395);
		symptomsBox.setLocation(540,100);
		symptomsBox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components
		symptomsBox.setBackground(darkGreyColour);
		symptomsBox.setOpaque(true);
			
		JTextPane symptomsTfCbLBL = new JTextPane();//declares a text pane to allow for help to be given for the user to understand on what to do on this area
		symptomsTfCbLBL.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		symptomsTfCbLBL.setText("Please enter your symptoms below.");//the button is geven text to add meaning to the label
		symptomsTfCbLBL.setMinimumSize(new Dimension(350,75));
		symptomsTfCbLBL.setPreferredSize(new Dimension(350,75));
		symptomsTfCbLBL.setMaximumSize(new Dimension(350,75));	
		symptomsTfCbLBL.setEditable(false);
		symptomsTfCbLBL.setOpaque(false);
		symptomsTfCbLBL.setForeground( new Color(-1) );//the foreground of the component is given a white font
		
		symptom1TF.setMinimumSize(new Dimension(350,65));
		symptom1TF.setPreferredSize(new Dimension(350,65));
		symptom1TF.setMaximumSize(new Dimension(350,65));	
		symptom1TF.setFont(symptomfont);
		symptom1TF.setForeground(darkGreyColour);//the foreground of the component is given a white font
		symptom1TF.setText("Symptom 1");//the text field is geven text to add meaning to the label
		symptom1TF.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(symptom1TF.getText().equals("Symptom 1"))//selection checking if the text field contains the text prompt
				{
					symptom1TF.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
					symptom1TF.setForeground( new Color(1) );//the foreground of the component is given a white font
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(symptom1TF.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					symptom1TF.setText("Symptom 1");//if satisifed then the prompt text is set again
					symptom1TF.setForeground(darkGreyColour);//the foreground of the component is given a white font
				}
			}
		});
		
		symptom2TF.setMinimumSize(new Dimension(350,65));
		symptom2TF.setPreferredSize(new Dimension(350,65));
		symptom2TF.setMaximumSize(new Dimension(350,65));	
		symptom2TF.setFont(symptomfont);
		symptom2TF.setForeground(darkGreyColour);//the foreground of the component is given a white font
		symptom2TF.setText("Symptom 2");//the label is geven text to add meaning to the label
		symptom2TF.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(symptom2TF.getText().equals("Symptom 2"))//selection checking if the text field contains the text prompt
				{
					symptom2TF.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
					symptom2TF.setForeground( new Color(1) );//the foreground of the component is given a white font

				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(symptom2TF.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					symptom2TF.setText("Symptom 2");//if satisifed then the prompt text is set again
					symptom2TF.setForeground(darkGreyColour);//the foreground of the component is given a white font
				}
			}
			
		});
		
		
		symptom3TF.setMinimumSize(new Dimension(350,65));
		symptom3TF.setPreferredSize(new Dimension(350,65));
		symptom3TF.setMaximumSize(new Dimension(350,65));
		symptom3TF.setFont(symptomfont);		
		symptom3TF.setForeground(darkGreyColour);//the foreground of the component is given a white font
		symptom3TF.setText("Symptom 3");//the label is geven text to add meaning to the label
		symptom3TF.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(symptom3TF.getText().equals("Symptom 3"))//selection checking if the text field contains the text prompt
				{
					symptom3TF.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
					symptom3TF.setForeground( new Color(1) );//the foreground of the component is given a white font
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(symptom3TF.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					symptom3TF.setText("Symptom 3");//if satisifed then the prompt text is set again
					symptom3TF.setForeground(darkGreyColour);//the foreground of the component is given a white font		
				}
			}
			
		});
		
		
		symptom4TF.setMinimumSize(new Dimension(350,65));
		symptom4TF.setPreferredSize(new Dimension(350,65));
		symptom4TF.setMaximumSize(new Dimension(350,65));
		symptom4TF.setFont(symptomfont);
		symptom4TF.setForeground(darkGreyColour);//the foreground of the component is given a white font		
		symptom4TF.setText("Symptom 4");//the label is geven text to add meaning to the label
		symptom4TF.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(symptom4TF.getText().equals("Symptom 4"))//selection checking if the text field contains the text prompt
				{
					symptom4TF.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
					symptom4TF.setForeground( new Color(1) );//the foreground of the component is given a white font
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(symptom4TF.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					symptom4TF.setText("Symptom 4");//if satisifed then the prompt text is set again
					symptom4TF.setForeground(darkGreyColour);//the foreground of the component is given a white font		
				}
			}
		});
		symptomsBox.add(symptomsTfCbLBL);	
		symptomsBox.add(symptom1TF);	
		symptomsBox.add(Box.createRigidArea(new Dimension(0,5)));
		symptomsBox.add(symptom2TF);	
		symptomsBox.add(Box.createRigidArea(new Dimension(0,5)));
		symptomsBox.add(symptom3TF);	
		symptomsBox.add(Box.createRigidArea(new Dimension(0,5)));
		symptomsBox.add(symptom4TF);	
		symptomsBox.add(Box.createRigidArea(new Dimension(0,5)));
		newAdmissionPanel.add(symptomsBox);
		
		SymptomConfirmBttn.setLocation(1050,750);//sets the location of the component 
		SymptomConfirmBttn.setSize(250,50);//the components size is correctly declared
		SymptomConfirmBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		SymptomConfirmBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		SymptomConfirmBttn.setText("Request Admission");//the button is geven text to add meaning to the label
		newAdmissionPanel.add(SymptomConfirmBttn);
		
		JLabel lightgreyBackGround = new JLabel();
		lightgreyBackGround.setBackground(greyColour);
		lightgreyBackGround.setSize(575,1000);
		lightgreyBackGround.setLocation(0,40);
		lightgreyBackGround.setOpaque(true);
		
		

		JTextPane typepainCbLBL = new JTextPane();
		typepainCbLBL.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		typepainCbLBL.setText("Please select the type of pain in the selected areas");//the button is geven text to add meaning to the label
		typepainCbLBL.setSize(347,70);
		typepainCbLBL.setLocation(25,5);
		typepainCbLBL.setEditable(false);//the text field is disabled from being edited	
				
		typeOfPain.add(cbChronicPains);
		typeOfPain.add(cbAcutePains);
		typeOfPain.add(cbStiffnessInMuscle);
		typeOfPain.add(cbFrequentRecurringPains);
				
		cbChronicPains.setFont(symptomfont);
		cbChronicPains.setSize(400,23);
		cbChronicPains.setLocation(35,80);
		cbChronicPains.setOpaque(false);
		
		cbAcutePains.setFont(symptomfont);
		cbAcutePains.setSize(400,23);
		cbAcutePains.setLocation(35,120);
		cbAcutePains.setOpaque(false);
		
		cbStiffnessInMuscle.setFont(symptomfont);
		cbStiffnessInMuscle.setSize(400,23);
		cbStiffnessInMuscle.setLocation(35,160);
		cbStiffnessInMuscle.setOpaque(false);
		
		cbFrequentRecurringPains.setFont(symptomfont);
		cbFrequentRecurringPains.setSize(400,23);
		cbFrequentRecurringPains.setLocation(35,200);
		cbFrequentRecurringPains.setOpaque(false);

		
		Box symptomsTypeBox = new Box(BoxLayout.Y_AXIS);
		symptomsTypeBox.setSize(420,240);
		symptomsTypeBox.setLocation(540,570);
		symptomsTypeBox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components
		symptomsTypeBox.setBackground(new Color(-1));
		symptomsTypeBox.setOpaque(true);
		JPanel insidePanel = new JPanel(null);
		insidePanel.add(typepainCbLBL);
		insidePanel.add(cbChronicPains);
		insidePanel.add(cbAcutePains);
		insidePanel.add(cbStiffnessInMuscle);
		insidePanel.add(cbFrequentRecurringPains);
		insidePanel.setBackground(new Color(-1));
		insidePanel.setOpaque(true);
		insidePanel.setSize(420,240);
		symptomsTypeBox.add(insidePanel);
		newAdmissionPanel.add(symptomsTypeBox);
		
		}
	//symptom recomednation
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	public void createSymptomRecomendationPanel()
	{
		JTextPane mainAdviceJPane = new JTextPane();//declares the text pane for the main advice this will be used for both panels
		JTextPane main2AdviceJPane = new JTextPane();//declares the text pane for the main advice this will be used for one of the panels
		createTopbar(symptomRecomendationPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		acceptSymptoms= true;//PROTORTYPE attribute as expert system has not been included all current admission declarations hasve been automatically accepted
		if(loaded[0][1]== false)//selection is used to determine whether if the components have been formatted
		{
			
			symptomRecomendationBackToExpertSystem.setLocation(96,63);//sets the location of the component 
			symptomRecomendationBackToExpertSystem.setSize(200,49);//the components size is correctly declared
			symptomRecomendationBackToExpertSystem.setForeground( new Color(-1) );//the foreground of the component is given a white font
			symptomRecomendationBackToExpertSystem.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
			symptomRecomendationBackToExpertSystem.setText("Go Back");//the button is geven text to add meaning to the label
			symptomRecomendationPanel.add(symptomRecomendationBackToExpertSystem);//the component is added to the panel
			
			symptomRecomendationNewAdmissionBttn.setLocation(1140,740);//the components size is correctly declared
			symptomRecomendationNewAdmissionBttn.setSize(180,49);//the components size is correctly declared
			symptomRecomendationNewAdmissionBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
			symptomRecomendationNewAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
			symptomRecomendationNewAdmissionBttn.setText("Create Admission");//the label is geven text to add meaning to the label
			symptomRecomendationPanel.add(symptomRecomendationNewAdmissionBttn);//the component is added to the panel
			
			
			
			mainAdviceJPane.setLocation(50,200);//the location of the component is set to the desired part of the panel
			mainAdviceJPane.setSize(1000,300);//size of component is set
			main2AdviceJPane.setLocation(50,400);//the location of the component is set to the desired part of the panel
			main2AdviceJPane.setSize(1000,300);//size of component is set
			
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
			symptomRecomendationPanel.add(recommendationFalseAcceptanceRB1);//the component is added to the panel
			symptomRecomendationPanel.add(recommendationFalseAcceptanceRB2);//the component is added to the panel
			symptomRecomendationPanel.add(recommendationFalseAcceptanceRB3);//the component is added to the panel
		
		
		
			mainAdviceJPane.setFont(headerFontFormat);//the component has had its font set to a design with the correct size for its purpose
			mainAdviceJPane.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
			mainAdviceJPane.setOpaque(false);//the component is made transparent
			symptomRecomendationPanel.add(mainAdviceJPane);//the component is added to the panel
			
			
			main2AdviceJPane.setFont(buttonFontFormat);//the component has had its font set to a design with the correct size for its purpose
			main2AdviceJPane.setEditable(false);//the text will be unable to be edited by the user and will retain whatever text it currently has
			main2AdviceJPane.setOpaque(false);//the component is made transparent
			symptomRecomendationPanel.add(main2AdviceJPane);//the component is added to the panel
			
			loaded[0][1]= true;//once the components have been loaded the statement is now set permantly to false to prevent multiple instance of the same GUI components
		}
		addPanel(symptomRecomendationPanel);

		symptomRecomendationPanel.setVisible(false);//panel is set to invisable
		symptomRecomendationPanel.setVisible(true);//panel is set to visable
		if(acceptSymptoms == true)//selection determining whether or not the symptoms have been accepted
		{
			mainAdviceJPane.setText("We feel with the symptoms you have given,\nyou would benefit with an admission.");//formatting component to include text
			main2AdviceJPane.setText("From this we will create an appointment that is at your best convenience, all we need you to do is login.");//formatting component to include text
			recommendationFalseAcceptanceRB1.setVisible(false);
			recommendationFalseAcceptanceRB2.setVisible(false);
			recommendationFalseAcceptanceRB3.setVisible(false);
			
			symptomRecomendationNewAdmissionBttn.setEnabled(true);//enables the ability to create a new admission
			
		}
		if(acceptSymptoms == false)//selection determining whether or not the symptoms have been accepted
		{
			symptomRecomendationNewAdmissionBttn.setEnabled(false);//disables the ability to create a new admission
			mainAdviceJPane.setText("You are experiencing minor symptoms.");//formatting component to include text
			main2AdviceJPane.setText("There is no major need for you to create an admission at this moment of time.");//formatting component to include text
			recommendationFalseAcceptanceRB1.setVisible(true);
			recommendationFalseAcceptanceRB2.setVisible(true);
			recommendationFalseAcceptanceRB3.setVisible(true);

		}
		
	}
		
	//view/edit admission panel
	//inital call methof for the panel this will load and add the panel to the frame and will perform
	//any other actions such as adding data/updateing it if requeired
	public void createViewAdmissionPanelGUI()
	{
		createTopbar(EditAdmissionPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels
	
		mainBoxPerson.setLocation(970,200);//sets the location of the component 
		mainBoxPerson.setSize(447,650);//the components size is correctly declared
		if(loaded[6][4] ==false)//selection determines whether the panel is yet to be loaded
		{
			createViewAdmissionPanelGUIGeneral();//the components of the panel are cereated by running the method this is done initially so it is only done once and will be used throughout
			loaded[6][4] = true;//the variable is set as true to prevent the components from being reran
		}
		addPanel(EditAdmissionPanel);
		createTopbarAdmission(EditAdmissionPanel);
		
		
		EditAdmissionPanel.add(mainBoxPerson);
		updateEditAdmissionComponents();
	}
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createViewAdmissionPanelGUIGeneral()
	{
		JLabel bookingLbl = new JLabel();//declares a label that is used for the first name
		bookingLbl.setText("Ammend Admission:");//formatting component to include text
		bookingLbl.setFont(headerFont);//the component has had its font set to a design with the correct size for its purpose
		bookingLbl.setFont(headerFont.deriveFont(headerFont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		bookingLbl.setLocation(1030,145);//sets the location of the component 
		bookingLbl.setSize(400,50);//the components size is correctly declared
		EditAdmissionPanel.add(bookingLbl);

		JLabel titleUpperBlackLine = new JLabel();//declares a labbel for the main header on the login screen 
		titleUpperBlackLine.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		titleUpperBlackLine.setForeground( new Color(1) );//the foreground of the component is given a white font
		titleUpperBlackLine.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		titleUpperBlackLine.setLocation(1000,130);//sets the location of the component 
		titleUpperBlackLine.setSize(400,3);//the components size is correctly declared
		titleUpperBlackLine.setOpaque(true);//the component is set to opaque
		EditAdmissionPanel.add(titleUpperBlackLine);

		JLabel titleLowerBlackLine = new JLabel();//declares a labbel for the main header on the login screen 
		titleLowerBlackLine.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		titleLowerBlackLine.setForeground( new Color(1) );//the foreground of the component is given a white font
		titleLowerBlackLine.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		titleLowerBlackLine.setLocation(1000,210);//sets the location of the component 
		titleLowerBlackLine.setSize(400,3);//the components size is correctly declared
		titleLowerBlackLine.setOpaque(true);//the component is set to opaque
		EditAdmissionPanel.add(titleLowerBlackLine);


		updateAdmission.setLocation(620,820);//the location of the component is set to the desired part of the panel
		updateAdmission.setFont(whiteLoginFont);//the component has had its font set to a design with the correct size for its purpose
		updateAdmission.setSize(280,45);//size of component is set
		updateAdmission.setText("Update Admission");//formatting component to include text
		updateAdmission.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		updateAdmission.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		EditAdmissionPanel.add(updateAdmission);

		viewAdmissionGoBackBttn.setLocation(50,120);//the location of the component is set to the desired part of the panel
		viewAdmissionGoBackBttn.setFont(whiteLoginFont);//the component has had its font set to a design with the correct size for its purpose
		viewAdmissionGoBackBttn.setSize(240,45);//size of component is set
		viewAdmissionGoBackBttn.setText("Return back");//formatting component to include text
		viewAdmissionGoBackBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		viewAdmissionGoBackBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		EditAdmissionPanel.add(viewAdmissionGoBackBttn);
		
		archiveAdmissionBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		archiveAdmissionBttn.setText("Archive admission");//formatting component to include text
		archiveAdmissionBttn.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		archiveAdmissionBttn.setBackground(importantRedColor);//the component has its background set to a desireable colour
		archiveAdmissionBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		archiveAdmissionBttn.setSize(200,45);//size of component is set
		archiveAdmissionBttn.setEnabled(true);	
		archiveAdmissionBttn.setLocation(45,820);//the location of the component is set to the desired part of the panel
		EditAdmissionPanel.add(archiveAdmissionBttn);
		
		fillerGreyBox.setSize(456,40);//the components size is correctly declared
		fillerGreyBox.setLocation(970,200);//sets the location of the component
		fillerGreyBox.setOpaque(true);
		
		fillerBox.setSize(445,59);//the components size is correctly declared
		fillerBox.setLocation(971,241);//sets the location of the component
		fillerBox.setFont(buttonFontFormat);//the component has had its font set to a design with the correct size for its purpose
		fillerBox.setText("   Areas Affected:");//formatting component to include text
		fillerBox.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		Color blueTemp = Color.decode("#3985db");
		fillerBox.setBackground(blueTemp);//sets the location of the component
		fillerBox.setOpaque(true);
		
		greyBoxLblEA.setSize(1474,80);//the components size is correctly declared
		greyBoxLblEA.setBackground(darkGreyColour);//the background colour of the component is declared to the desired value 
		greyBoxLblEA.setOpaque(true);//the background colour of the component is declared to the desired value 
		greyBoxLblEA.setLocation(0,808);//sets the location of the component 
		
		EditAdmissionPanel.add(fillerGreyBox);		
		EditAdmissionPanel.add(fillerBox);		
		EditAdmissionPanel.add(mainBoxPerson);
		EditAdmissionPanel.add(wardBox);
		EditAdmissionPanel.add(consultantIDBox);
		EditAdmissionPanel.add(dischargePatient);
		EditAdmissionPanel.add(currentDiagnosisBox);
		EditAdmissionPanel.add(currentSymptomsBox);
		EditAdmissionPanel.add(currentAreasAffectedBox);
				EditAdmissionPanel.add(greyBoxLblEA);//the component is added to the panel

	}
	//edit/view Admission basic components generation
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createEditAdmissionComponents()
	{
		JLabel wardPromptLbl = new JLabel();//declares a label that is used for the first name
		wardPromptLbl.setText("Ward:");//formatting component to include text
		wardPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		wardPromptLbl.setMinimumSize(new Dimension(75,40));
		wardPromptLbl.setPreferredSize(new Dimension(75,40));
		wardPromptLbl.setMaximumSize(new Dimension(75,40));		
		wardPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose

		JTextField wardTextFeild = new JTextField();//declares the text feild that will hold the first name
		wardTextFeild.setText("Please enter the ward");//formatting component to include text
		wardTextFeild.setMinimumSize(new Dimension(350,40));
		wardTextFeild.setPreferredSize(new Dimension(350,40));
		wardTextFeild.setMaximumSize(new Dimension(350,40));
		wardTextFeild.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		wardTextFeild.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(wardTextFeild.getText().equals("Please enter the ward"))//selection checking if the text field contains the text prompt
				{
					wardTextFeild.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(wardTextFeild.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					wardTextFeild.setText("Please enter the ward");//if satisifed then the prompt text is set again
				}
			}
		});
		wardBox.add(Box.createRigidArea(new Dimension(10,0)));
		wardBox.add(wardPromptLbl);//the component is added to the panel
		wardBox.add(Box.createRigidArea(new Dimension(5,0)));
		wardBox.add(wardTextFeild);//the component is added to the panel
		wardBox.setSize(450,55);//size of component is set
		wardBox.setLocation(45,185);//the location of the component is set to the desired part of the panel
		wardBox.setBackground( new Color(-1) );
		wardBox.setOpaque(true);
		wardBox.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		
		JLabel ConsultantPromptLbl = new JLabel();//declares a label that is used for the bloodtype
		JComboBox<String> admissionConsultantIDDropDown = new JComboBox<String>(availableConsultant);//declares a combo box that holds the list of bloodtypes
		admissionConsultantIDDropDown.setMinimumSize(new Dimension(200,35));
		admissionConsultantIDDropDown.setPreferredSize(new Dimension(200,35));
		admissionConsultantIDDropDown.setMaximumSize(new Dimension(200,35));
		admissionConsultantIDDropDown.setOpaque(false);
		
		ConsultantPromptLbl.setText("Consultant:");//formatting component to include text
		ConsultantPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		ConsultantPromptLbl.setMinimumSize(new Dimension(130,40));
		ConsultantPromptLbl.setPreferredSize(new Dimension(130,40));
		ConsultantPromptLbl.setMaximumSize(new Dimension(130,40));
		ConsultantPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose

		consultantIDBox.add(Box.createRigidArea(new Dimension(10,0)));
		consultantIDBox.add(ConsultantPromptLbl);//the component is added to the panel
		consultantIDBox.add(Box.createRigidArea(new Dimension(5,0)));
		consultantIDBox.add(admissionConsultantIDDropDown);//the component is added to the panel
		consultantIDBox.setSize(450,55);//size of component is set
		consultantIDBox.setLocation(45,270);//the location of the component is set to the desired part of the panel
		consultantIDBox.setBackground( new Color(-1) );
		consultantIDBox.setOpaque(true);
		consultantIDBox.setBorder(BorderFactory.createLineBorder(Color.black));
		

		JCheckBox disachargeStatusCb = new JCheckBox("Discharged");//declares a checkbox for the specific area of the body, this will be used along side the human image 
		disachargeStatusCb.setEnabled(false);
		disachargeStatusCb.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		disachargeStatusCb.setMinimumSize(new Dimension(250,40));
		disachargeStatusCb.setPreferredSize(new Dimension(250,40));
		disachargeStatusCb.setMaximumSize(new Dimension(250,40));
		disachargeStatusCb.setOpaque(false);
		
		dischargePatient.add(Box.createRigidArea(new Dimension(10,0)));
		dischargePatient.add(disachargeStatusCb);//the component is added to the panel
		dischargePatient.setSize(450,55);//size of component is set
		dischargePatient.setLocation(45,355);//the location of the component is set to the desired part of the panel
		dischargePatient.setBackground( new Color(-1) );
		dischargePatient.setOpaque(true);
		dischargePatient.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel currentDiiagnosisPromptLbl = new JLabel();//declares a label that is used for the first name
		currentDiiagnosisPromptLbl.setText("Diagnosis:");//formatting component to include text
		currentDiiagnosisPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		currentDiiagnosisPromptLbl.setMinimumSize(new Dimension(125,40));
		currentDiiagnosisPromptLbl.setPreferredSize(new Dimension(125,40));
		currentDiiagnosisPromptLbl.setMaximumSize(new Dimension(125,40));		
		currentDiiagnosisPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose

		JTextField currentDiiagnosisTextFeild = new JTextField();//declares the text feild that will hold the first name
		currentDiiagnosisTextFeild.setText("Please enter the Diagnosis");//formatting component to include text
		currentDiiagnosisTextFeild.setMinimumSize(new Dimension(300,40));
		currentDiiagnosisTextFeild.setPreferredSize(new Dimension(300,40));
		currentDiiagnosisTextFeild.setMaximumSize(new Dimension(300,40));
		currentDiiagnosisTextFeild.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		currentDiiagnosisTextFeild.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(currentDiiagnosisTextFeild.getText().equals("Please enter the Diagnosis"))//selection checking if the text field contains the text prompt
				{
					currentDiiagnosisTextFeild.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(currentDiiagnosisTextFeild.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					currentDiiagnosisTextFeild.setText("Please enter the Diagnosis");//if satisifed then the prompt text is set again
				}
			}
		});
		currentDiagnosisBox.add(Box.createRigidArea(new Dimension(10,0)));
		currentDiagnosisBox.add(currentDiiagnosisPromptLbl);//the component is added to the panel
		currentDiagnosisBox.add(Box.createRigidArea(new Dimension(5,0)));
		currentDiagnosisBox.add(currentDiiagnosisTextFeild);//the component is added to the panel
		currentDiagnosisBox.setSize(450,55);//size of component is set
		currentDiagnosisBox.setLocation(510,185);//the location of the component is set to the desired part of the panel
		currentDiagnosisBox.setBackground( new Color(-1) );
		currentDiagnosisBox.setOpaque(true);
		currentDiagnosisBox.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JLabel admissionSymptomsLbl = new JLabel();//declares a label that is used for the allergies
		admissionSymptomsLbl.setText("Symptoms:	");//formatting component to include text
		admissionSymptomsLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		admissionSymptomsLbl.setMinimumSize(new Dimension(250,35));
		admissionSymptomsLbl.setPreferredSize(new Dimension(250,35));
		admissionSymptomsLbl.setMaximumSize(new Dimension(250,40));
		admissionSymptomsLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		admissionSymptomsLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		JTextArea admissionSymptoms = new JTextArea();//declares the text area that will hold the allergies
		admissionSymptoms.setText("Please enter any symptoms You are experiencing");//formatting component to include text
		admissionSymptoms.setMinimumSize(new Dimension(425,150));
		admissionSymptoms.setPreferredSize(new Dimension(425,150));
		admissionSymptoms.setMaximumSize(new Dimension(425,150));
		admissionSymptoms.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
		admissionSymptoms.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		admissionSymptoms.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components
		admissionSymptoms.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e){
			//when focus has been gained(text field has been clicked on) this method will run
			
				if(admissionSymptoms.getText().equals("Please enter any symptoms You are experiencing"))//selection checking if the text field contains the text prompt
				{
					admissionSymptoms.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(admissionSymptoms.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					admissionSymptoms.setText("Please enter any symptoms You are experiencing");//if satisifed then the prompt text is set again
				}
			}
		});
		admissionSymptoms.setAlignmentX(Component.CENTER_ALIGNMENT);
		currentSymptomsBox.add(admissionSymptomsLbl);//the component is added to the panel
		currentSymptomsBox.add(admissionSymptoms);//the component is added to the panel
		currentSymptomsBox.setSize(450,200);//size of component is set
		currentSymptomsBox.setLocation(510,270);//the location of the component is set to the desired part of the panel
		currentSymptomsBox.setBackground( new Color(-1) );
		currentSymptomsBox.setOpaque(true);
		currentSymptomsBox.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel areasAffectedLbl = new JLabel();//declares a label that is used for the allergies
		areasAffectedLbl.setText("Areas affected:	");//formatting component to include text
		areasAffectedLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		areasAffectedLbl.setMinimumSize(new Dimension(250,35));
		areasAffectedLbl.setPreferredSize(new Dimension(250,35));
		areasAffectedLbl.setMaximumSize(new Dimension(250,40));
		areasAffectedLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		areasAffectedLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

		JTextPane typepainCbLBL = new JTextPane();
		typepainCbLBL.setFont(headerFontFormatBlack);//the font that has been declared is attached to the component
		typepainCbLBL.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		typepainCbLBL.setText("Type of pain");//the button is geven text to add meaning to the label
		typepainCbLBL.setSize(347,40);
		typepainCbLBL.setEditable(false);//the text field is disabled from being edited	
		typepainCbLBL.setLocation(25,5);
		
		typeOfPainE.add(cbChronicPainsE);
		typeOfPainE.add(cbAcutePainsE);
		typeOfPainE.add(cbStiffnessInMuscleE);
		typeOfPainE.add(cbFrequentRecurringPainsE);
				//
		cbChronicPainsE.setFont(symptomfont);
		cbChronicPainsE.setSize(400,23);
		cbChronicPainsE.setLocation(35,50);
		cbChronicPainsE.setOpaque(false);
		
		cbAcutePainsE.setFont(symptomfont);
		cbAcutePainsE.setSize(400,23);
		cbAcutePainsE.setLocation(35,90);
		cbAcutePainsE.setOpaque(false);

		cbStiffnessInMuscleE.setFont(symptomfont);
		cbStiffnessInMuscleE.setSize(400,23);
		cbStiffnessInMuscleE.setLocation(35,130);
		cbStiffnessInMuscleE.setOpaque(false);

		cbFrequentRecurringPainsE.setFont(symptomfont);
		cbFrequentRecurringPainsE.setSize(400,23);
		cbFrequentRecurringPainsE.setLocation(35,170);
		cbFrequentRecurringPainsE.setOpaque(false);

		Box symptomsTypeBox = new Box(BoxLayout.Y_AXIS);
		JPanel insidePanel = new JPanel(null);
		insidePanel.add(typepainCbLBL);
		insidePanel.add(cbChronicPainsE);
		insidePanel.add(cbAcutePainsE);
		insidePanel.add(cbStiffnessInMuscleE);
		insidePanel.add(cbFrequentRecurringPainsE);
		insidePanel.setBackground(new Color(-1));
		insidePanel.setOpaque(true);
		insidePanel.setSize(420,210);
		symptomsTypeBox.setSize(420,210);
		symptomsTypeBox.setLocation(45,440);
		symptomsTypeBox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components
		symptomsTypeBox.add(insidePanel);	
		EditAdmissionPanel.add(symptomsTypeBox);
		
		
	}
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	public void updateEditAdmissionComponents()
	{
		
		//here we will need to enable/disable the admission based on whether the admisison is active or not
		
		////System.out.println(userPatient.listOfAdmissions[currentAdmissionIndex].ward);
		//userPatient.listOfAdmissions[currentAdmissionIndex].
		Component wardlblTmp=wardBox.getComponent(3);//inner
		((JTextField) wardlblTmp).setText(systemAdmissionList[currentAdmissionIndex].ward);
		Component consultantlblTmp=consultantIDBox.getComponent(3);//inner
		try
		{
			((JComboBox) consultantlblTmp).setSelectedItem(systemAdmissionList[currentAdmissionIndex].consultantID);
		}
		catch(Exception exc)
		{
			//System.out.println("Consultant missing");
		}
		Component disachargeStatusCblblTmp=dischargePatient.getComponent(1);//inner
		if(systemAdmissionList[currentAdmissionIndex].active==true)
		{
			((JCheckBox) disachargeStatusCblblTmp).setSelected(false);

		}
		if(systemAdmissionList[currentAdmissionIndex].active==false)
		{
			((JCheckBox) disachargeStatusCblblTmp).setSelected(true);

		}
		
		Component diagnosislblTmp=currentDiagnosisBox.getComponent(3);//inner
		((JTextField) diagnosislblTmp).setText(systemAdmissionList[currentAdmissionIndex].currentDiagnosis);


		Component symptomslblTmp=currentSymptomsBox.getComponent(1);//inner
		String concatenatedSymptoms=systemAdmissionList[currentAdmissionIndex].listOfSymptoms[0]+"\n";
		for(int indexAreasCount = 1;indexAreasCount<systemAdmissionList[currentAdmissionIndex].listOfSymptoms.length;indexAreasCount++)
		{
			
				try
				{
					if(systemAdmissionList[currentAdmissionIndex].listOfSymptoms[indexAreasCount].equals("null")==false)
					{
					concatenatedSymptoms=concatenatedSymptoms+systemAdmissionList[currentAdmissionIndex].listOfSymptoms[indexAreasCount]+"\n";
					}
				}
				catch(Exception exc){}
		}
		((JTextArea) symptomslblTmp).setText(concatenatedSymptoms);
		clearHuman();
		String stringedAreas = "";
		for(int counter = 0;counter<systemAdmissionList[currentAdmissionIndex].listOfAreasAffected.length;counter++)
		{
			if(systemAdmissionList[currentAdmissionIndex].listOfAreasAffected[counter].equals("null")==false)
			{
			stringedAreas = stringedAreas+systemAdmissionList[currentAdmissionIndex].listOfAreasAffected[counter]+"\n";
			}
		}
		
		if(stringedAreas.contains("Leg")==true)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			humanImageLegsSelected.setVisible(true);
			cbLeg.setSelected(true);
		}
		if(stringedAreas.contains("Pelvis")==true)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			humanImagePelvisSelected.setVisible(true);
			cbPelvis.setSelected(true);
		}
		
		if(stringedAreas.contains("Arm")==true)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			humanImageRightArmSelected.setVisible(true);
			humanImageLeftArmSelected.setVisible(true);
			cbArm.setSelected(true);
		}
		
		if(stringedAreas.contains("Forearm")==true)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			humanImageRightforeArmSelected.setVisible(true);
			humanImageLeftForeArmSelected.setVisible(true);
			cbForeArm.setSelected(true);
		}
		
		if(stringedAreas.contains("Abdomen")==true)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			humanImageAbdomenSelected.setVisible(true);
			cbAbdomen.setSelected(true);
		}
		
		if(stringedAreas.contains("Head")==true)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			humanImageHeadSelected.setVisible(true);
			cbHead.setSelected(true);
		}
		
		if(stringedAreas.contains("Foot")==true)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			humanImageFootSelected.setVisible(true);
				cbFoot.setSelected(true);
		}
		
		if(stringedAreas.contains("Hand")==true)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			humanImageLeftHandSelected.setVisible(true);
				humanImageRightHandSelected.setVisible(true);
				cbHand.setSelected(true);
		}
		
		if(stringedAreas.contains("Chest")==true)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			humanImageChestSelected.setVisible(true);
			cbChest.setSelected(true);
		}
		
		if(stringedAreas.contains("Neck")==true)//selection determining the location of the action performed by passing the current event through and locating the source
		{
				humanImageneckSelected.setVisible(true);
				cbNeck.setSelected(true);
		}
		
		if(stringedAreas.contains("Shoulder")==true)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			humanImageLeftShoulderSelected.setVisible(true);
				humanImageRightShoulderSelected.setVisible(true);
				cbShoulder.setSelected(true);
				
		}
		if(stringedAreas.contains("Hips")==true)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			humanImageHipsSelected.setVisible(true);
			cbHips.setSelected(true);
			
		}
	
		if(systemAdmissionList[currentAdmissionIndex].typeOfPain.contains("Frequent recurring Pains"))
		{
			cbFrequentRecurringPainsE.setSelected(true);
		}
		if(systemAdmissionList[currentAdmissionIndex].typeOfPain.contains("Stiffness in muscle"))
		{
			cbStiffnessInMuscleE.setSelected(true);
		}
		if(systemAdmissionList[currentAdmissionIndex].typeOfPain.contains("Acute Pains"))
		{
			cbAcutePainsE.setSelected(true);
		}
		if(systemAdmissionList[currentAdmissionIndex].typeOfPain.contains("Chronic pains"))
		{
			cbChronicPainsE.setSelected(true);
		}
		if(systemAdmissionList[currentAdmissionIndex].typeOfPain.contains("null"))
		{
			cbFrequentRecurringPainsE.setSelected(false);
			cbStiffnessInMuscleE.setSelected(false);
			cbAcutePainsE.setSelected(false);
			cbChronicPainsE.setSelected(false);
		}
	
		//here we will disable admission features based on the account 
		//for the patient we disable any infiormation that can be entered by them
		//We also hide any buttons that they should not see
		//The samne principle applys to the rest 
		if(loginChoice==0)
		{
			((JComboBox) consultantlblTmp).setEnabled(false);
			((JTextField) wardlblTmp).setEnabled(false);
			((JTextField) diagnosislblTmp).setEnabled(false);
			//human is enabled
			cbLeg.setEnabled(true);
			cbPelvis.setEnabled(true);
			cbArm.setEnabled(true);
			cbForeArm.setEnabled(true);
			cbAbdomen.setEnabled(true);
			cbHead.setEnabled(true);
			cbFoot.setEnabled(true);
			cbHand.setEnabled(true);
			cbChest.setEnabled(true);
			cbNeck.setEnabled(true);
			cbShoulder.setEnabled(true);
			cbHips.setEnabled(true);
			
			//type of pain
			cbChronicPainsE.setEnabled(true);
			cbAcutePainsE.setEnabled(true);
			cbStiffnessInMuscleE.setEnabled(true);
			cbFrequentRecurringPainsE.setEnabled(true);
			
			//symptom box
			((JTextArea) symptomslblTmp).setEnabled(true);
			
			archiveAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
			archiveAdmissionBttn.setEnabled(false);
		}
		if(loginChoice==1)//staff
		{
			((JComboBox) consultantlblTmp).setEnabled(true);
			((JTextField) wardlblTmp).setEnabled(true);
			((JTextField) diagnosislblTmp).setEnabled(true);
			//human is disabled
			cbLeg.setEnabled(false);
			cbPelvis.setEnabled(false);
			cbArm.setEnabled(false);
			cbForeArm.setEnabled(false);
			cbAbdomen.setEnabled(false);
			cbHead.setEnabled(false);
			cbFoot.setEnabled(false);
			cbHand.setEnabled(false);
			cbChest.setEnabled(false);
			cbNeck.setEnabled(false);
			cbShoulder.setEnabled(false);
			cbHips.setEnabled(false);
			
			//type of pain
			cbChronicPainsE.setEnabled(false);
			cbAcutePainsE.setEnabled(false);
			cbStiffnessInMuscleE.setEnabled(false);
			cbFrequentRecurringPainsE.setEnabled(false);
			
			//symptom box
			((JTextArea) symptomslblTmp).setEnabled(false);
			archiveAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
			archiveAdmissionBttn.setEnabled(false);
		}
		if(loginChoice==3)//consultant
		{
			((JComboBox) consultantlblTmp).setEnabled(true);
			((JTextField) wardlblTmp).setEnabled(true);
			((JTextField) diagnosislblTmp).setEnabled(true);
			//human is disabled
			cbLeg.setEnabled(false);
			cbPelvis.setEnabled(false);
			cbArm.setEnabled(false);
			cbForeArm.setEnabled(false);
			cbAbdomen.setEnabled(false);
			cbHead.setEnabled(false);
			cbFoot.setEnabled(false);
			cbHand.setEnabled(false);
			cbChest.setEnabled(false);
			cbNeck.setEnabled(false);
			cbShoulder.setEnabled(false);
			cbHips.setEnabled(false);
			
			//type of pain
			cbChronicPainsE.setEnabled(false);
			cbAcutePainsE.setEnabled(false);
			cbStiffnessInMuscleE.setEnabled(false);
			cbFrequentRecurringPainsE.setEnabled(false);
			
			//symptom box
			((JTextArea) symptomslblTmp).setEnabled(false);
			
			//Archive admission
			archiveAdmissionBttn.setBackground(importantRedColor);//the background colour of the component is declared to the desired value 
			archiveAdmissionBttn.setEnabled(true);
		}
		EditAdmissionPanel.remove(greyBoxLblEA);//the component is added to the panel
		EditAdmissionPanel.add(greyBoxLblEA);//the component is added to the panel

		
	}

	//human body
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createHumanComponents()
	{
		JTextPane painCbLBL = new JTextPane();//declares a text pane to allow for help to be given for the user to understand on what to do on this area
		painCbLBL.setFont(headerFontFormatBlack);
		painCbLBL.setForeground( new Color(-1) );//the foreground of the component is given a white font
		painCbLBL.setText("Please select the areas where the pain resonates from.");//the button is geven text to add meaning to the label
		painCbLBL.setSize(447,75); //the components size is correctly declared
		painCbLBL.setLocation(10,10);//sets the location of the component 
        painCbLBL.setEditable(false);//the text field is disabled from being edited
		painCbLBL.setOpaque(false);

		humanImage.setIcon( new ImageIcon("human1.jpg") );//the label is set to an image
		humanImage.setSize(444,700); //the components size is correctly declared
		humanImage.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components
	
		humanImageHeadSelected.setIcon( new ImageIcon("humanSelectedHead.jpg") );//the label is set to an image
		humanImageHeadSelected.setSize(89,76); //the components size is correctly declared
		humanImageHeadSelected.setLocation(172,93);//sets the location of the component 
		humanImageHeadSelected.setVisible(false);

	
		humanImageneckSelected.setIcon( new ImageIcon("humanSelectedKneck.jpg") );//the label is set to an image
		humanImageneckSelected.setSize(88,21); //the components size is correctly declared
		humanImageneckSelected.setLocation(171,168);//sets the location of the component 
		humanImageneckSelected.setVisible(false);

		
		humanImageFootSelected.setIcon( new ImageIcon("humanSelectedFoot.jpg") );//the label is set to an image
		humanImageFootSelected.setSize(124,40); //the components size is correctly declared
		humanImageFootSelected.setLocation(155,607);//sets the location of the component 
		humanImageFootSelected.setVisible(false);

		
		humanImageLegsSelected.setIcon( new ImageIcon("humanSelectedLegs.jpg") );//the label is set to an image
		humanImageLegsSelected.setSize(123,194); //the components size is correctly declared
		humanImageLegsSelected.setLocation(154,413);//sets the location of the component 
		humanImageLegsSelected.setVisible(false);
		
		humanImagePelvisSelected.setIcon( new ImageIcon("humanSelectedPelvisjpg.jpg") );//the label is set to an image
		humanImagePelvisSelected.setSize(122,55); //the components size is correctly declared
		humanImagePelvisSelected.setLocation(158,358);//sets the location of the component
		humanImagePelvisSelected.setVisible(false);

		
		humanImageHipsSelected.setIcon( new ImageIcon("humanSelectedHips.jpg") );//the label is set to an image
		humanImageHipsSelected.setSize(99,39); //the components size is correctly declared
		humanImageHipsSelected.setLocation(164,319);//sets the location of the component
		humanImageHipsSelected.setVisible(false);
		
		
		humanImageAbdomenSelected.setIcon( new ImageIcon("humanSelectedAbdomen.jpg") );//the label is set to an image
		humanImageAbdomenSelected.setSize(91,62); //the components size is correctly declared
		humanImageAbdomenSelected.setLocation(171,257);//sets the location of the component
		humanImageAbdomenSelected.setVisible(false);
		
		humanImageChestSelected.setIcon( new ImageIcon("humanSelectedChest.jpg") );//the label is set to an image
		humanImageChestSelected.setSize(91,69); //the components size is correctly declared
		humanImageChestSelected.setLocation(171,188);//sets the location of the component
		humanImageChestSelected.setVisible(false);
		
		humanImageRightShoulderSelected.setIcon( new ImageIcon("humanSelectedRightShoulder.jpg") );//the label is set to an image
		humanImageRightShoulderSelected.setSize(54,46); //the components size is correctly declared
		humanImageRightShoulderSelected.setLocation(261,177);//sets the location of the component
		humanImageRightShoulderSelected.setVisible(false);
		
		humanImageRightArmSelected.setIcon( new ImageIcon("humanSelectedRightArm.jpg") );//the label is set to an image
		humanImageRightArmSelected.setSize(58,63); //the components size is correctly declared
		humanImageRightArmSelected.setLocation(260,223);//sets the location of the component
		humanImageRightArmSelected.setVisible(false);
	
		humanImageRightforeArmSelected.setIcon( new ImageIcon("humanSelectedRightforeArm.jpg") );//the label is set to an image
		humanImageRightforeArmSelected.setSize(71,77); //the components size is correctly declared
		humanImageRightforeArmSelected.setLocation(264,286);//sets the location of the component
		humanImageRightforeArmSelected.setVisible(false);
	
		humanImageRightHandSelected.setIcon( new ImageIcon("humanSelectedRightHand.jpg") );//the label is set to an image
		humanImageRightHandSelected.setSize(76,66); //the components size is correctly declared
		humanImageRightHandSelected.setLocation(286,363);//sets the location of the component
		humanImageRightHandSelected.setVisible(false);
		
		humanImageLeftShoulderSelected.setIcon( new ImageIcon("humanSelectedLeftShoulder.jpg") );//the label is set to an image
		humanImageLeftShoulderSelected.setSize(72,42); //the components size is correctly declared
		humanImageLeftShoulderSelected.setLocation(99,182);//sets the location of the component
		humanImageLeftShoulderSelected.setVisible(false);
	
		humanImageLeftArmSelected.setIcon( new ImageIcon("humanSelectedLefttArm.jpg") );//the label is set to an image
		humanImageLeftArmSelected.setSize(71,64); //the components size is correctly declared
		humanImageLeftArmSelected.setLocation(100,224);//sets the location of the component
		humanImageLeftArmSelected.setVisible(false);
	
		
		humanImageLeftForeArmSelected.setIcon( new ImageIcon("humanSelectedLeftforeArm.jpg") );//the label is set to an image
		humanImageLeftForeArmSelected.setSize(70,82); //the components size is correctly declared
		humanImageLeftForeArmSelected.setLocation(93,288);//sets the location of the component
		humanImageLeftForeArmSelected.setVisible(false);
	
		humanImageLeftHandSelected.setIcon( new ImageIcon("humanSelectedLefttHand.jpg") );//the label is set to an image
		humanImageLeftHandSelected.setSize(55,61); //the components size is correctly declared
		humanImageLeftHandSelected.setLocation(82,369);//sets the location of the component
		humanImageLeftHandSelected.setVisible(false);
			
			
		cbNeck.setLocation(92,137);//sets the location of the component 
		cbNeck.setSize(150,50);//the components size is correctly declared
		cbNeck.setOpaque(false);
		cbNeck.setFont(headerFontFormatBlack);
		cbNeck.setForeground( new Color(-1) );//the foreground of the component is given a white font
		
		cbChest.setLocation(39,201);//sets the location of the component 
		cbChest.setSize(150,50);//the components size is correctly declared
		cbChest.setOpaque(false);
		cbChest.setFont(headerFontFormatBlack);
		cbChest.setForeground( new Color(-1) );//the foreground of the component is given a white font
		
		cbHand.setLocation(70,417);//sets the location of the component 
		cbHand.setSize(150,50);//the components size is correctly declared
		cbHand.setOpaque(false);
		cbHand.setFont(headerFontFormatBlack);
		cbHand.setForeground( new Color(-1) );//the foreground of the component is given a white font
		
		cbFoot.setLocation(255,570);//sets the location of the component 
		cbFoot.setSize(150,50);//the components size is correctly declared
		cbFoot.setOpaque(false);		
		cbFoot.setFont(headerFontFormatBlack);
		cbFoot.setForeground( new Color(-1) );//the foreground of the component is given a white font
		
		cbHead.setLocation(260,125);//sets the location of the component 
		cbHead.setSize(150,50);//the components size is correctly declared
		cbHead.setOpaque(false);
		cbHead.setFont(headerFontFormatBlack);
		cbHead.setForeground( new Color(-1) );//the foreground of the component is given a white font
		
		cbShoulder.setLocation(300,175);//sets the location of the component 
		cbShoulder.setSize(150,50);//the components size is correctly declared
		cbShoulder.setOpaque(false);
		cbShoulder.setFont(headerFontFormatBlack);
		cbShoulder.setForeground( new Color(-1) );//the foreground of the component is given a white font
		
		cbAbdomen.setLocation(7,450);//sets the location of the component 
		cbAbdomen.setSize(150,50);//the components size is correctly declared
		cbAbdomen.setOpaque(false);	
		cbAbdomen.setFont(headerFontFormatBlack);
		cbAbdomen.setForeground( new Color(-1) );//the foreground of the component is given a white font
		
		cbArm.setLocation(325,292);//sets the location of the component 
		cbArm.setSize(150,50);//the components size is correctly declared
		cbArm.setOpaque(false);		
		cbArm.setFont(headerFontFormatBlack);
		cbArm.setForeground( new Color(-1) );//the foreground of the component is given a white font
		
		cbHips.setLocation(40,292);//sets the location of the component 
		cbHips.setSize(150,50);//the components size is correctly declared
		cbHips.setOpaque(false);		
		cbHips.setFont(headerFontFormatBlack);
		cbHips.setForeground( new Color(-1) );//the foreground of the component is given a white font
		
		cbPelvis.setLocation(335,340);//sets the location of the component 
		cbPelvis.setSize(150,50);//the components size is correctly declared
		cbPelvis.setOpaque(false);		
		cbPelvis.setFont(headerFontFormatBlack);
		cbPelvis.setForeground( new Color(-1) );//the foreground of the component is given a white font
		
		cbLeg.setLocation(270,515);//sets the location of the component 
		cbLeg.setSize(150,50);//the components size is correctly declared
		cbLeg.setOpaque(false);
		cbLeg.setFont(headerFontFormatBlack);
		cbLeg.setForeground( new Color(-1) );//the foreground of the component is given a white font
		
		
		cbForeArm.setLocation(305,220);//sets the location of the component 
		cbForeArm.setSize(150,50);//the components size is correctly declared
		cbForeArm.setOpaque(false);
		cbForeArm.setFont(headerFontFormatBlack);
		cbForeArm.setForeground( new Color(-1) );//the foreground of the component is given a white font
		
		
		personPanel.setBorder(BorderFactory.createLineBorder(Color.black));		
		
		personPanel.setSize(444,700);//the components size is correctly declared
		personPanelButtons.setSize(444,700);//the components size is correctly declared
		
		layeredPane.setSize(444,700);//the components size is correctly declared
		
		mainBoxPerson.setSize(447,700);//the components size is correctly declared
		mainBoxPerson.setBorder(BorderFactory.createLineBorder(Color.black));

		personPanel.add(humanImage);//the component is added to the panel
		
		layeredPane.add(painCbLBL);//the component is added to the panel
		layeredPane.add(cbNeck);//the component is added to the panel
		layeredPane.add(cbChest);//the component is added to the panel
		layeredPane.add(cbHand);//the component is added to the panel
		layeredPane.add(cbFoot);//the component is added to the panel
		layeredPane.add(cbHead);//the component is added to the panel
		layeredPane.add(cbAbdomen);//the component is added to the panel
		layeredPane.add(cbArm);//the component is added to the panel
		layeredPane.add(cbPelvis);//the component is added to the panel
		layeredPane.add(cbLeg);//the component is added to the panel
		layeredPane.add(cbHips);//the component is added to the panel
		layeredPane.add(cbForeArm);//the component is added to the panel
		layeredPane.add(cbShoulder);//the component is added to the panel
		
	
		layeredPane.moveToBack(personPanel);
		layeredPane.add(humanImageHeadSelected);//the component is added to the panel
		layeredPane.add(humanImageneckSelected);//the component is added to the panel
		layeredPane.add(humanImageFootSelected);//the component is added to the panel
		layeredPane.add(humanImageLegsSelected);//the component is added to the panel
		layeredPane.add(humanImagePelvisSelected);//the component is added to the panel
		layeredPane.add(humanImageHipsSelected);//the component is added to the panel
		layeredPane.add(humanImageAbdomenSelected);//the component is added to the panel
		layeredPane.add(humanImageChestSelected);//the component is added to the panel
		layeredPane.add(humanImageRightShoulderSelected);//the component is added to the panel
		layeredPane.add(humanImageRightArmSelected);//the component is added to the panel
		layeredPane.add(humanImageRightforeArmSelected);//the component is added to the panel
		layeredPane.add(humanImageRightHandSelected);//the component is added to the panel
		layeredPane.add(humanImageLeftShoulderSelected);//the component is added to the panel
		layeredPane.add(humanImageLeftArmSelected);//the component is added to the panel
		layeredPane.add(humanImageLeftForeArmSelected);//the component is added to the panel
		layeredPane.add(humanImageLeftHandSelected);//the component is added to the panel
		layeredPane.add(personPanel);
		
		layeredPane.moveToFront(personPanelButtons);
		mainBoxPerson.add(layeredPane);
		
	}
	//This method will clear all the selected values on the human, this is to prevent the system from loading the huamn but it already having data from before when the last user was using it
	//this will always be called on the panels that utilise the human 
	//works by setting the box to only contain the base images as visible and that the checkboxes are unselected
	public void clearHuman()
	{
		humanImageHipsSelected.setVisible(false);
			humanImageLeftShoulderSelected.setVisible(false);
				humanImageRightShoulderSelected.setVisible(false);
				humanImageneckSelected.setVisible(false);
				humanImageChestSelected.setVisible(false);
			humanImageLeftHandSelected.setVisible(false);
				humanImageRightHandSelected.setVisible(false);
				humanImageFootSelected.setVisible(false);
			humanImageHeadSelected.setVisible(false);
			humanImageAbdomenSelected.setVisible(false);
			humanImageRightforeArmSelected.setVisible(false);
			humanImageLeftForeArmSelected.setVisible(false);
			humanImageRightArmSelected.setVisible(false);
			humanImageLeftArmSelected.setVisible(false);
			humanImagePelvisSelected.setVisible(false);
			humanImageLegsSelected.setVisible(false);
			cbLeg.setSelected(false);
			cbPelvis.setSelected(false);
			cbArm.setSelected(false);
			cbForeArm.setSelected(false);
			cbAbdomen.setSelected(false);
			cbHead.setSelected(false);
			cbFoot.setSelected(false);
			cbHand.setSelected(false);
			cbChest.setSelected(false);
			cbNeck.setSelected(false);
			cbShoulder.setSelected(false);
			cbHips.setSelected(false);
			cbLeg.setEnabled(true);
			cbPelvis.setEnabled(true);
			cbArm.setEnabled(true);
			cbForeArm.setEnabled(true);
			cbAbdomen.setEnabled(true);
			cbHead.setEnabled(true);
			cbFoot.setEnabled(true);
			cbHand.setEnabled(true);
			cbChest.setEnabled(true);
			cbNeck.setEnabled(true);
			cbShoulder.setEnabled(true);
			cbHips.setEnabled(true);
	}






	
	//====================== BOOKING =====================
	
	//======== Entity based section =========
	
	//we need a retun boolean to confirm that the bookings have been generated, this will prevent further errors being thrown
	//after this the method will then retrieve all the instances informatiom and then call the file writing method.
	//Once in file the system will then attach the booking to the correct corrosponding admission instance
	//finally the user is returned to the admission homepage where they can see the booking
	public boolean createBookingInstance()
	{
		
		Booking newbooking = new Booking();
		
		
		//before assigned needs to check that it is validated (Not in the past)
		Component timeBookingBox=timeBookingBoxBooking.getComponent(0);//declares local copy of the time picker
		Component dateBookingBox=dateBookingBoxBooking.getComponent(0);//inner
		
		
		LocalDate localDateData = ((DatePicker) dateBookingBox).getDate();
		LocalTime localTimeData = ((TimePicker) timeBookingBox).getTime();//gets the local time of the timepicker
		//here we have to have a custom validation for just the dates checking they have been entered
		boolean validateDateAndTimeResult = newbooking.validatebooking(localDateData,localTimeData);
		if(validateDateAndTimeResult==false)
		{
			return false;
		}
		
		//CONVERTS LOCAL DATE TO DATE
		Date date = Date.from(localDateData.atStartOfDay(ZoneId.systemDefault()).toInstant());
		//CONVERTS LOCAL TIME TO DATE (ACTUAL DAY DOENST MATTER)
		//attempts to convert localtime into date
		//What it does is create an instant of the local time and combines this with any date(Will be hid)
		Instant instant = localTimeData.atDate(LocalDate.of(0000, 01, 01)).atZone(ZoneId.systemDefault()).toInstant();
		Date time = Date.from(instant);
		
		//Both attributes are stored as Date
		newbooking.timeOfNextApp = time;
		newbooking.dateOfNextApp = date;
		
		if((automateBookingBookingRadioButton.isSelected())==true)
		{
			newbooking.automaticBooking = true;
		}
		if((automateBookingBookingRadioButton.isSelected())==false)
		{
			newbooking.automaticBooking = false;
			
		}
		
		Component roomBookingBox=roomBoxBooking.getComponent(3);//inner
		newbooking.room = ((JTextField) roomBookingBox).getText()+"";

		//before we continue we need to set the room depending if it is entered by the patient or not
		if((loginChoice==0)&&(((JTextField) roomBookingBox).getText().equals("")))
		{
			////System.out.println("new booking");
			newbooking.room="PENDING";
		}
		
		
		//here we can validate the booking properly if the patient created the booking it should have been put as pending now 
		boolean bookingValidated =newbooking.validatebooking(newbooking);
		if((loginChoice!=0)&&(newbooking.room.equals("PENDING")))
		{
			JOptionPane.showMessageDialog(null, "A room needss to be assigned to the booking.");
			return false;
		}
		if(bookingValidated==true)
		{
			newbooking.consultantID = systemAdmissionList[currentAdmissionIndex].consultantID;
			//Concatenates the Letter b for booking along with the admissionID, however the A in admission is substringed of Leaveing just SURNAME NUMBERS 
			newbooking.bookingID = "B"+systemAdmissionList[currentAdmissionIndex].admissionID.substring(1,systemAdmissionList[currentAdmissionIndex].admissionID.length());
			//Here we are adding the bookkings to both the patient and consultant files respectfully
			newbooking.createBookingAddToPatientFile(userPatient,systemAdmissionList[currentAdmissionIndex],newbooking);
			newbooking.createBookingAddToConsultantFile(userPatient,systemAdmissionList[currentAdmissionIndex].consultantID,systemAdmissionList[currentAdmissionIndex],newbooking);
			systemAdmissionList[currentAdmissionIndex].upComingBooking=newbooking;//adds booking to the admission instance
			refreshEntityInstances();
			createConsultantAppointmentpanel();
			createContactBarAdmission();
			//userPatient = userPatient.retrievePatient(userPatient.patientID);
			return true;
		}
		return false;
	}
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	
	public void refreshEntityInstances()
	{
		if(loginChoice==3)
		{
		systemAdmissionList = userConsultant.pullAdmissionsFromFile(userConsultant).pullAdmissions(userConsultant.concatenatedconsultantPatientList[currentPatientIndex].split("#"),userPatient);
		userConsultant=userConsultant.retrieveBookingsFromAllPatients(userConsultant);
		}
		if(loginChoice==0)
		{
			userPatient=userPatient.retrievePatient(userPatient.patientID);
			systemAdmissionList = userPatient.listOfAdmissions;
		}
		if(loginChoice==1)
		{
			userStaff=userStaff.retrieveStaff(userStaff.staffID);
			userPatient = userPatient.retrievePatient(userStaff.patientIDs[currentPatientIndex]);//FINDS THE PATIENT INDEX NEEDED TO PULL FROM FILE
			systemAdmissionList = userPatient.listOfAdmissions;
							
		}
		
		userConsultant=userConsultant.retrieveBookingsFromAllPatients(userConsultant);
	}
	//here the system will determine whether the cancel booking button is eneabled
	//this will depend on the state of the automated bookings attribute on the system
	//if the automated bookings is true then the button will be disabled and vice versa
	public void automateBookingsMethod()
	{
		if((automateBookingBookingRadioButton.isSelected())==true)
		{
			appointmentcreateBookingBttn.setEnabled(false);
			if((appointmentCancelBookingBttn.isSelected())==true)
			{
				appointmentCancelBookingBttn.setEnabled(false);
			}
			
		}
		if((automateBookingBookingRadioButton.isSelected())==false)
		{
			appointmentcreateBookingBttn.setEnabled(true);
			if((appointmentCancelBookingBttn.isSelected())==true)
			{
				appointmentCancelBookingBttn.setEnabled(false);
			}
			
		}
	}
	
	//====== graphical aspect ======
	
	//appointment Panel 
	//inital call methof for the panel this will load and add the panel to the frame and will perform
	//any other actions such as adding data/updateing it if requeired
	public void createViewAppointmentPanelGUI(Booking currentBooking)
	{
		//method which checks if the booking needs to be updated to the new date 
		createTopbar(viewAppointmentPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		if(loaded[6][1] ==false)//selection determines whether the panel is yet to be loaded
		{
			createViewAppointmentPanelGUIGeneral();//the components of the panel are cereated by running the method this is done initially so it is only done once and will be used throughout
			loaded[6][1] = true;//the variable is set as true to prevent the components from being reran
		}
		addPanel(viewAppointmentPanel);
		try{//used to avoid the patients intial appointment button on the homeScreen
		createTopbarAdmission(viewAppointmentPanel);
		}
		catch(Exception exc)
		{
			
		}		
		createbookingValues(currentBooking);
	}
	//Creates general Components for the booking panel
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createViewAppointmentPanelGUIGeneral()
	{
		bookingLbl.setText("Booking:");//formatting component to include text
		bookingLbl.setFont(headerFont);//the component has had its font set to a design with the correct size for its purpose
		bookingLbl.setFont(headerFont.deriveFont(headerFont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		bookingLbl.setLocation(830,145);//sets the location of the component 
		bookingLbl.setSize(450,50);//the components size is correctly declared
		viewAppointmentPanel.add(bookingLbl);

		JLabel titleUpperBlackLine = new JLabel();//declares a labbel for the main header on the login screen 
		titleUpperBlackLine.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		titleUpperBlackLine.setForeground( new Color(1) );//the foreground of the component is given a white font
		titleUpperBlackLine.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		titleUpperBlackLine.setLocation(800,130);//sets the location of the component 
		titleUpperBlackLine.setSize(600,3);//the components size is correctly declared
		titleUpperBlackLine.setOpaque(true);//the component is set to opaque
		viewAppointmentPanel.add(titleUpperBlackLine);

		JLabel titleLowerBlackLine = new JLabel();//declares a labbel for the main header on the login screen 
		titleLowerBlackLine.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		titleLowerBlackLine.setForeground( new Color(1) );//the foreground of the component is given a white font
		titleLowerBlackLine.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		titleLowerBlackLine.setLocation(800,210);//sets the location of the component 
		titleLowerBlackLine.setSize(600,3);//the components size is correctly declared
		titleLowerBlackLine.setOpaque(true);//the component is set to opaque
		viewAppointmentPanel.add(titleLowerBlackLine);

		
		automateBookingBookingRadioButton.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		automateBookingBookingRadioButton.setMinimumSize(new Dimension(350,40));
		automateBookingBookingRadioButton.setPreferredSize(new Dimension(350,40));
		automateBookingBookingRadioButton.setMaximumSize(new Dimension(350,40));
		automateBookingBookingRadioButton.setOpaque(false);
		
		automateBookingBoxBooking.add(automateBookingBookingRadioButton);
		automateBookingBoxBooking.setSize(530,55);//size of component is set
		automateBookingBoxBooking.setLocation(110,530);//the location of the component is set to the desired part of the panel
		automateBookingBoxBooking.setBackground( new Color(-1) );
		automateBookingBoxBooking.setOpaque(true);
		automateBookingBoxBooking.setBorder(BorderFactory.createLineBorder(Color.black));		
		viewAppointmentPanel.add(automateBookingBoxBooking);
		
		datePicker1.setMinimumSize(new Dimension(300,50));
		datePicker1.setPreferredSize(new Dimension(300,50));
		datePicker1.setMaximumSize(new Dimension(300,50));
		datePicker1.addDateChangeListener(listner);
		datePicker1.getComponentToggleCalendarButton().setMinimumSize(new Dimension(100,40));
		datePicker1.getComponentToggleCalendarButton().setPreferredSize(new Dimension(100,40));
		datePicker1.getComponentToggleCalendarButton().setMaximumSize(new Dimension(100,40));
		datePicker1.getComponentToggleCalendarButton().setText("View Dates");
		datePicker1.getComponentToggleCalendarButton().setForeground( new Color(-1) );//the foreground of the component is given a white font
		datePicker1.getComponentToggleCalendarButton().setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		datePicker1.getComponentToggleCalendarButton().setFont(symptomfont);//the font that has been declared is attached to the component 
		datePicker1.getComponentDateTextField().setDisabledTextColor(Color.BLACK);
	
		dateSettings.setAllowKeyboardEditing(false);//prevents custom dates being entered
		dateSettings.setFontValidDate(symptomfont);
		dateSettings.setSizeDatePanelMinimumWidth(300);
		dateSettings.setSizeDatePanelMinimumHeight(250);

		LocalDate today = LocalDate.now();
		dateSettings.setDateRangeLimits(today.plusDays(0), null);
		dateBookingBoxBooking.setSize(300,55);//size of component is set
		dateBookingBoxBooking.setLocation(110,270);//the location of the component is set to the desired part of the panel
		dateBookingBoxBooking.setBackground( new Color(-1) );
		dateBookingBoxBooking.setOpaque(true);
		dateBookingBoxBooking.setBorder(BorderFactory.createLineBorder(Color.black));	
		dateBookingBoxBooking.add(datePicker1);
		viewAppointmentPanel.add(dateBookingBoxBooking);
		
		
		timePicker1.setMinimumSize(new Dimension(300,50));
		timePicker1.setPreferredSize(new Dimension(300,50));
		timePicker1.setMaximumSize(new Dimension(300,50));
		timePicker1.setEnabled(false);
		timePicker1.getComponentToggleTimeMenuButton().setMinimumSize(new Dimension(120,40));
		timePicker1.getComponentToggleTimeMenuButton().setPreferredSize(new Dimension(120,40));
		timePicker1.getComponentToggleTimeMenuButton().setMaximumSize(new Dimension(120,40));
		timePicker1.getComponentToggleTimeMenuButton().setText("View Times");
		timePicker1.getComponentToggleTimeMenuButton().setForeground( new Color(-1) );//the foreground of the component is given a white font
		timePicker1.getComponentToggleTimeMenuButton().setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		timePicker1.getComponentToggleTimeMenuButton().setFont(symptomfont);//the font that has been declared is attached to the component 
		timePicker1.getComponentTimeTextField().setDisabledTextColor(Color.BLACK);

		restoreListOfTimes();
		timeSettings.generatePotentialMenuTimes(localTimeArray);//Sets the available times to that of the custom array
		timeSettings.setAllowKeyboardEditing(false);//prevents custom dates being entered
		timeSettings.fontValidTime=symptomfont;
		
		timeBookingBoxBooking.add(timePicker1);
		timeBookingBoxBooking.setSize(300,55);//size of component is set
		timeBookingBoxBooking.setBackground( new Color(-1) );
		timeBookingBoxBooking.setLocation(110,400);//sets the location of the component 
		timeBookingBoxBooking.setOpaque(true);
		timeBookingBoxBooking.setBorder(BorderFactory.createLineBorder(Color.black));		
		viewAppointmentPanel.add(timeBookingBoxBooking);
		
		
		JLabel bookingRoomPromptLbl = new JLabel();//declares a label that is used for the first name
		bookingRoomPromptLbl.setText("Room:");//formatting component to include text
		bookingRoomPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		bookingRoomPromptLbl.setMinimumSize(new Dimension(125,40));
		bookingRoomPromptLbl.setPreferredSize(new Dimension(125,40));
		bookingRoomPromptLbl.setMaximumSize(new Dimension(125,40));		
		bookingRoomPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose

		JTextField bookingRoomTextFeild = new JTextField();//declares the text feild that will hold the first name
		bookingRoomTextFeild.setText("");//formatting component to include text
		bookingRoomTextFeild.setMinimumSize(new Dimension(350,40));
		bookingRoomTextFeild.setPreferredSize(new Dimension(350,40));
		bookingRoomTextFeild.setMaximumSize(new Dimension(350,40));
		bookingRoomTextFeild.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		bookingRoomTextFeild.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(bookingRoomTextFeild.getText().equals("Please enter A room"))//selection checking if the text field contains the text prompt
				{
					bookingRoomTextFeild.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(bookingRoomTextFeild.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					bookingRoomTextFeild.setText("Please enter A room");//if satisifed then the prompt text is set again
				}
			}
		});
		roomBoxBooking.add(Box.createRigidArea(new Dimension(10,0)));
		roomBoxBooking.add(bookingRoomPromptLbl);//the component is added to the panel
		roomBoxBooking.add(Box.createRigidArea(new Dimension(5,0)));
		roomBoxBooking.add(bookingRoomTextFeild);//the component is added to the panel
		roomBoxBooking.setSize(530,55);//size of component is set
		roomBoxBooking.setLocation(750,520);//the location of the component is set to the desired part of the panel
		roomBoxBooking.setBackground( new Color(-1) );
		roomBoxBooking.setOpaque(true);
		roomBoxBooking.setBorder(BorderFactory.createLineBorder(Color.black));		
		viewAppointmentPanel.add(roomBoxBooking);
		
		
		JLabel bookingConsultantPromptLbl = new JLabel();//declares a label that is used for the first name
		bookingConsultantPromptLbl.setText("Consultant:");//formatting component to include text
		bookingConsultantPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		bookingConsultantPromptLbl.setMinimumSize(new Dimension(125,40));
		bookingConsultantPromptLbl.setPreferredSize(new Dimension(125,40));
		bookingConsultantPromptLbl.setMaximumSize(new Dimension(125,40));		
		bookingConsultantPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose

		JTextField bookingConsultantTextFeild = new JTextField();//declares the text feild that will hold the first name
		bookingConsultantTextFeild.setMinimumSize(new Dimension(350,40));
		bookingConsultantTextFeild.setPreferredSize(new Dimension(350,40));
		bookingConsultantTextFeild.setMaximumSize(new Dimension(350,40));
		bookingConsultantTextFeild.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		bookingConsultantTextFeild.setEnabled(false);
		bookingConsultantTextFeild.setDisabledTextColor(Color.BLACK);
		
		ConsultantBoxBooking.add(Box.createRigidArea(new Dimension(10,0)));
		ConsultantBoxBooking.add(bookingConsultantPromptLbl);//the component is added to the panel
		ConsultantBoxBooking.add(Box.createRigidArea(new Dimension(5,0)));
		ConsultantBoxBooking.add(bookingConsultantTextFeild);//the component is added to the panel
		ConsultantBoxBooking.setSize(530,55);//size of component is set
		ConsultantBoxBooking.setLocation(110,660);//the location of the component is set to the desired part of the panel
		ConsultantBoxBooking.setBackground( new Color(-1) );
		ConsultantBoxBooking.setOpaque(true);
		ConsultantBoxBooking.setBorder(BorderFactory.createLineBorder(Color.black));		
		viewAppointmentPanel.add(ConsultantBoxBooking);
		
		
		appointmentGoBackBttn.setLocation(96,163);//sets the location of the component 
		appointmentGoBackBttn.setSize(200,49);//the components size is correctly declared
		appointmentGoBackBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		appointmentGoBackBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		appointmentGoBackBttn.setText("Go Back");//the button is geven text to add meaning to the label
		viewAppointmentPanel.add(appointmentGoBackBttn);
		
		appointmentcreateBookingBttn.setLocation(1130,818);//sets the location of the component 
		appointmentcreateBookingBttn.setSize(200,49);//the components size is correctly declared
		appointmentcreateBookingBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		appointmentcreateBookingBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		viewAppointmentPanel.add(appointmentcreateBookingBttn);	
        
		appointmentCancelBookingBttn.setLocation(50,818);//sets the location of the component 
		appointmentCancelBookingBttn.setSize(200,49);//the components size is correctly declared
		appointmentCancelBookingBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		appointmentCancelBookingBttn.setBackground(importantRedColor);//the background colour of the component is declared to the desired value 
		appointmentCancelBookingBttn.setText("Cancel Booking");//the button is geven text to add meaning to the label
		viewAppointmentPanel.add(appointmentCancelBookingBttn);	
		
		JLabel greyBoxLbl = new JLabel();//declares a labbel for the main header on the login screen 
		greyBoxLbl.setSize(1474,80);//the components size is correctly declared
		greyBoxLbl.setBackground(darkGreyColour);//the background colour of the component is declared to the desired value 
		greyBoxLbl.setOpaque(true);//the background colour of the component is declared to the desired value 
		greyBoxLbl.setLocation(0,808);//sets the location of the component 
		viewAppointmentPanel.add(greyBoxLbl);//the component is added to the panel
		
		JTextArea adviceBookingTA = new JTextArea();
		adviceBookingTA.setEditable(false);
		adviceBookingTA.setText("Please select a date and time to book an appointment, bookings can only be created 4 days in advance in order to retreive relevant information. Bookings can no longer be edited later than 24 hours of the actual appointment.");
		adviceBookingTA.setSize(400,600);
		adviceBookingTA.setLocation(800,250);
		adviceBookingTA.setFont(whiteLoginFont);
		adviceBookingTA.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
		adviceBookingTA.setWrapStyleWord(true);
		adviceBookingTA.setOpaque(false);
		viewAppointmentPanel.add(adviceBookingTA);
		
		
     
	}
	//Assigns values to the booking panel (Any entity)
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	public void createbookingValues(Booking currentBooking)
	{
		//general view appointment panel
		if((currentBooking==null)==false)
		{
			
			if(currentBooking.room.equals("Null")==false)
			{
				bookingLbl.setText("Booking:"+ currentBooking.bookingID);//formatting component to include text
				
				//This is the automatic booking feature and will update the booking accordingly
				if((currentBooking.automaticBooking==true)&&(panelOrder[endPanelPointer-1]==admissionHomepagePanel))
				{
					LocalDate currentDate = LocalDate.now();
					//here we are checking for the automatic bookng and then would update the booking if it needs to
					LocalDate currentbookingDate = currentBooking.convertToLocalDateViaInstant(systemAdmissionList[currentAdmissionIndex].upComingBooking.dateOfNextApp);
					if(currentbookingDate.isBefore(currentDate)==true)//found an earlier date that needs updating
					{
						userPatient = userPatient.createNewNotification(userPatient,"The admission "+ systemAdmissionList[currentAdmissionIndex].admissionID+" has generated a new booking for 6 months time");
						////System.out.println("old booking found needs to be updated!!!!!");//visual gratification
						currentbookingDate = currentbookingDate.plusMonths(6);//adds 6 months to the booking
						//updates the booking by converting it back into a date datatype
						systemAdmissionList[currentAdmissionIndex].upComingBooking.dateOfNextApp = systemAdmissionList[currentAdmissionIndex].convertToDateViaInstant(currentbookingDate);
						try{
							////System.out.println("NEW DATE "+ft.format(systemAdmissionList[currentAdmissionIndex].upComingBooking.dateOfNextApp));
							}
						catch(Exception exp){}
						//Write method
						//DELETES OLD BOOKING
						Booking tempBooking = new Booking();
						tempBooking = systemAdmissionList[currentAdmissionIndex].upComingBooking;
						systemAdmissionList[currentAdmissionIndex].upComingBooking.deleteBooking(systemAdmissionList[currentAdmissionIndex],userPatient);
						//updates the system to reflect changes
						refreshEntityInstances();
						//writes new bookings to file
						tempBooking.createBookingAddToPatientFile(userPatient,systemAdmissionList[currentAdmissionIndex],tempBooking);
						tempBooking.createBookingAddToConsultantFile(userPatient,systemAdmissionList[currentAdmissionIndex].consultantID,systemAdmissionList[currentAdmissionIndex],tempBooking);
						//updates the system to reflect changes
						refreshEntityInstances();
						//Visually updates panel for the date/time
						//createbookingValues(currentBooking);
					}
					else
					{
						//System.out.println("Booking is in time");
					}
				}
				
				
				
				//where the actual updates occur
				//checks for the admissionpage
				if(panelOrder[endPanelPointer-1]==admissionHomepagePanel)
				{
					datePicker1.setEnabled(true);
					
					Date avoidPastBookingDates = new Date();
					avoidPastBookingDates = currentBooking.convertToDateViaInstant(currentBooking.convertToLocalDateViaInstant(avoidPastBookingDates).plusDays(-1));
					Date admissionsBookingDate = new Date();
					try
					{
						admissionsBookingDate = ftTimeInc.parse(ft.format(currentBooking.dateOfNextApp)+" " + timeft.format(currentBooking.timeOfNextApp));//Sets date to the current date and 1am(time which isnt optional for appointment anything can come after it)			//System.out.println("Got to inital date comparisson"+ admissionsBookingDate);
					}
					catch(Exception exc)
					{
							//System.out.println("Appointment date invalid");
					}
					if(avoidPastBookingDates.compareTo(admissionsBookingDate)<0)//checks date is upcoming not in the past
					{
						automateBookingBookingRadioButton.setEnabled(true);
						
						
						//using the current booking entity this will fill in the dates to the feilds, not entity specifc except for the enabling and disabling of the entry of boxes etc
						Component roomlblbox=roomBoxBooking.getComponent(3);//inner
						((JTextField) roomlblbox).setText(currentBooking.room);		
						((JTextField) roomlblbox).setEditable(true);	

						
						//get times for this date
						LocalDate ld = LocalDate.now();
						try{
						ld = currentBooking.dateOfNextApp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						}
						catch(Exception exc){}
						datePicker1.setDate(ld);
						timePicker1.setText(timeft.format(currentBooking.timeOfNextApp).substring(0,(timeft.format(currentBooking.timeOfNextApp)+"").length()-1)+"0");
						timePicker1.getRootPane().requestFocus();
						timePicker1.setEnabled(true);
						Component consultantlblbox=ConsultantBoxBooking.getComponent(3);//inner
						if(loginChoice==0)
						{
							((JTextField) consultantlblbox).setText(systemAdmissionList[currentAdmissionIndex].consultantID);
						}
						else{
						((JTextField) consultantlblbox).setText("DR."+userConsultant.firstName.substring(0,1)+"."+userConsultant.surName);
						}
						appointmentCancelBookingBttn.setBackground(importantRedColor);//the background colour of the component is declared to the desired value 
						appointmentCancelBookingBttn.setEnabled(true);
						if(currentBooking.automaticBooking==true)
						{
							automateBookingBookingRadioButton.setSelected(true);
						}
						if(currentBooking.automaticBooking==false)
						{
							automateBookingBookingRadioButton.setSelected(false);
						}
						appointmentcreateBookingBttn.setText("Update Booking");//the button is geven text to add meaning to the label

					}
					else{
						clearAppointmentCompoenents(currentBooking);
					}
				appointmentcreateBookingBttn.setVisible(true);
				appointmentCancelBookingBttn.setVisible(true);

				}
				//for the intial consultant panel
				if(panelOrder[endPanelPointer-1]==consultantHomepagePanel)
				{
					Date avoidPastBookingDates = new Date();
				avoidPastBookingDates = currentBooking.convertToDateViaInstant(currentBooking.convertToLocalDateViaInstant(avoidPastBookingDates).plusDays(-1));
					Date admissionsBookingDate = new Date();
					try
					{
						avoidPastBookingDates =	ftTimeInc.parse(ft.format(avoidPastBookingDates)+" " + "01:00");
						admissionsBookingDate = ftTimeInc.parse(ft.format(currentBooking.dateOfNextApp)+" " + timeft.format(currentBooking.timeOfNextApp));//Sets date to the current date and 1am(time which isnt optional for appointment anything can come after it)			//System.out.println("Got to inital date comparisson"+ admissionsBookingDate);
					}
					catch(Exception exc)
					{
							//System.out.println("Appointment date invalid");
					}
					if(avoidPastBookingDates.compareTo(admissionsBookingDate)<0)//checks date is upcoming not in the past
					{
						automateBookingBookingRadioButton.setEnabled(false);
						if(currentBooking.automaticBooking==true)
						{
							automateBookingBookingRadioButton.setSelected(true);
						}
						if(currentBooking.automaticBooking==false)
						{
							automateBookingBookingRadioButton.setSelected(false);
						}
						
						
						//using the current booking entity this will fill in the dates to the feilds, not entity specifc except for the enabling and disabling of the entry of boxes etc
						Component roomlblbox=roomBoxBooking.getComponent(3);//inner
						((JTextField) roomlblbox).setText(currentBooking.room);
						((JTextField) roomlblbox).setEditable(false);
						
						datePicker1.setEnabled(false);
						LocalDate ld = LocalDate.now();
						try{
						ld = currentBooking.dateOfNextApp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						}
						catch(Exception exc){}
						datePicker1.setDate(ld);
						
						
						//get times for this date
						timePicker1.setEnabled(false);
						timePicker1.setText(timeft.format(currentBooking.timeOfNextApp).substring(0,(timeft.format(currentBooking.timeOfNextApp)+"").length()-1)+"0");
						timePicker1.getRootPane().requestFocus();



						Component consultantlblbox=ConsultantBoxBooking.getComponent(3);//inner
						((JTextField) consultantlblbox).setText("DR."+userConsultant.firstName.substring(0,1)+"."+userConsultant.surName);
						
						appointmentcreateBookingBttn.setText("Update Booking");//the button is geven text to add meaning to the label

					}
					else{
						clearAppointmentCompoenents(currentBooking);
					}
				appointmentcreateBookingBttn.setVisible(false);
				appointmentCancelBookingBttn.setVisible(false);

				}
			}
		}
		else
		{
			clearAppointmentCompoenents(currentBooking);
		}
		//here we are selecting the priorities who can edit the booking room (EMPLOYEES ONLY)
				Component roomlblboxEdit=roomBoxBooking.getComponent(3);//inner
				if(loginChoice==0)
				{
					roomBoxBooking.setVisible(false);	
				}
				else{
					roomBoxBooking.setVisible(true);	
				}
	}
	//This method will clear all the selected values on the booking panel, this is to prevent the system from loading the panel but it already having data from before when the last user was using it
	//this will always be called on the panels that utilise the booking compoenents 
	//The method works by clearing all the text and dates in the panel so that when called no prior data is present
	public void clearAppointmentCompoenents (Booking currentBooking)
	{
			appointmentcreateBookingBttn.setText("Create Booking");//the button is geven text to add meaning to the label
		Component roomlblbox=roomBoxBooking.getComponent(3);//inner
			((JTextField) roomlblbox).setText("");			
			datePicker1.setText("");
			//get times for this date
			timePicker1.setText("");
			//timePicker1.getRootPane().requestFocus();
			Component consultantlblbox=ConsultantBoxBooking.getComponent(3);//inner
			if(panelOrder[endPanelPointer-1]==admissionHomepagePanel)
			{
				appointmentcreateBookingBttn.setVisible(true);
				appointmentCancelBookingBttn.setVisible(true);
			}
			if(panelOrder[endPanelPointer-1]==patientHomepagePanel)
			{
				((JTextField) consultantlblbox).setText(currentBooking.tempAdmissionPatient.consultantID);
			}
			else if((loginChoice==0)||(loginChoice==1))
			{
				((JTextField) consultantlblbox).setText(systemAdmissionList[currentAdmissionIndex].consultantID);
			}
			else if(loginChoice==3)
			{
			((JTextField) consultantlblbox).setText("DR."+userConsultant.firstName.substring(0,1)+"."+userConsultant.surName);
			}
			appointmentCancelBookingBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
			appointmentCancelBookingBttn.setEnabled(false);
			automateBookingBookingRadioButton.setSelected(false);
	}
	//for the array lisst of times before they are then removed to accomedate for existing bookings the liet of times need to remove all the old times then correctly add all of them back
	//this will allow the system to then remove the times when a booking exists eith the consultant.
	//the times can then be added to the combobox
	public void restoreListOfTimes()
	{
		localTimeArray.clear();
		localTimeArray.add(LocalTime.of(8, 00));//Adds new instance to array

		localTimeArray.add(LocalTime.of(8, 30));//Adds new instance to array
		localTimeArray.add(LocalTime.of(9, 00));//Adds new instance to array

		localTimeArray.add(LocalTime.of(9, 30));//Adds new instance to array
		localTimeArray.add(LocalTime.of(10, 00));//Adds new instance to array
		
		localTimeArray.add(LocalTime.of(10, 30));//Adds new instance to array
		localTimeArray.add(LocalTime.of(11, 00));//Adds new instance to array
		
		localTimeArray.add(LocalTime.of(11, 30));//Adds new instance to array
		localTimeArray.add(LocalTime.of(12, 00));//Adds new instance to array
		
		localTimeArray.add(LocalTime.of(13, 00));//Adds new instance to array
		
		localTimeArray.add(LocalTime.of(13, 30));//Adds new instance to array
		localTimeArray.add(LocalTime.of(14, 00));//Adds new instance to array
		
		localTimeArray.add(LocalTime.of(14, 30));//Adds new instance to array
		localTimeArray.add(LocalTime.of(15, 00));//Adds new instance to array
		
		localTimeArray.add(LocalTime.of(15, 30));//Adds new instance to array
		localTimeArray.add(LocalTime.of(16, 00));//Adds new instance to array
		
		localTimeArray.add(LocalTime.of(16, 30));//Adds new instance to array
		localTimeArray.add(LocalTime.of(17, 00));//Adds new instance to array
		
		localTimeArray.add(LocalTime.of(17, 30));//Adds new instance to array
	}
	//as said above  the list of times need to be updated to reflect the times that already have been selected on the system
	//This works by running a for llop through every time in the array and comparing it to all the bookings assigned with the consultant
	//first we see a for loop which will iterate through every patient on the system
	//we then see a for loop which iterates through every booking the patient has
	//then we perform a time comparision to see that the date entered by the user equals the date of the booking
	//if yes then the bookings time is converted to local time which is then removed from the array list.
	public void updateListOfTimes()
	{
		restoreListOfTimes();
		LocalDate date = datePicker1.getDate();
		Date dateVersionOfDatePickerDate = new Date();
		LocalTime timeRetrieved = LocalTime.now();
		try{
		dateVersionOfDatePickerDate = userConsultant.convertToDateViaInstant(date);
		}
		catch(Exception exc){
			//System.out.println("Error with date conversion");
			}
		//Now that all the graphical aspects have been established here we will assign the correct avialbedates to the 
		//for loop that goes through every index in the all bookings array
		//selection checking if the correct date is there
		//then check 
		if((userConsultant.listOfBookings==null)==false)
		{
			for(int outerBookingArrayIndex = 0;outerBookingArrayIndex<userConsultant.listOfBookings.length;outerBookingArrayIndex++)
			{
				for(int innerBookingArrayIndex = 0;innerBookingArrayIndex<userConsultant.listOfBookings[outerBookingArrayIndex].length;innerBookingArrayIndex++)
				{//
					//System.out.println("current booking"+userConsultant.listOfBookings[outerBookingArrayIndex][innerBookingArrayIndex].bookingID);
					if(userConsultant.listOfBookings[outerBookingArrayIndex][innerBookingArrayIndex].dateOfNextApp.compareTo(dateVersionOfDatePickerDate) == 0)
					{
						//System.out.println("Existing date found");
						try{
						timeRetrieved = LocalTime.parse(timeft.format(userConsultant.listOfBookings[outerBookingArrayIndex][innerBookingArrayIndex].timeOfNextApp).substring(0,(timeft.format(userConsultant.listOfBookings[outerBookingArrayIndex][innerBookingArrayIndex].timeOfNextApp)+"").length()-1)+"0");
						//System.out.println(timeRetrieved+"");
						localTimeArray.remove(timeRetrieved);

						}
						catch(Exception exc){}

						
					}
				}
			}
		}			timeSettings.generatePotentialMenuTimes(localTimeArray);//Sets the available times to that of the custom array

	}












//================== Demographic ==================

	//======== Entity based section =========

	//As the attrbiutes are assigned to a specific object a selection statement is ran to determine which user is needed
	//respcectively once determined all the instances attributes are then assigned to the components of the panel
	//Finally the components are then attached to the GUI
	public void createDemographicComponentsGUIGeneral()
	{
		saveDemographicChanges.setVisible(true);//unhides  the button which can update the patients details
		Component firstNameTf=fNameBox.getComponent(3);//inner
		Component surnameNameTf=sNameBox.getComponent(3);//inner
		Component dateBookingBox=dOBBox.getComponent(3);//inner
		Component addressName=addressBuildingBox.getComponent(3);//inner
		Component addressStreet=addressStreetBox.getComponent(3);//inner
		Component addressTown=addressTownBox.getComponent(3);//inner
		Component addressPostcode1=addressPostcodeBox.getComponent(3);//inner
		Component addressPostcode2=addressPostcodeBox.getComponent(5);//inner
		Component addressPostcode3=addressPostcodeBox.getComponent(7);//inner
		Component addressPostcode4=addressPostcodeBox.getComponent(9);//inner
		Component addressPostcode5=addressPostcodeBox.getComponent(11);//inner
		Component addressPostcode6=addressPostcodeBox.getComponent(13);//inner
		Component addressPostcode7=addressPostcodeBox.getComponent(15);//inner
		Component contactNumber1=contactInfoBox.getComponent(3);//inner
		Component contactNumber2=contactInfoBox.getComponent(5);//inner
		Component contactNumber3=contactInfoBox.getComponent(7);//inner
		Component contactNumber4=contactInfoBox.getComponent(9);//inner
		Component contactNumber5=contactInfoBox.getComponent(11);//inner
		Component contactNumber6=contactInfoBox.getComponent(13);//inner
		Component contactNumber7=contactInfoBox.getComponent(15);//inner
		Component contactNumber8=contactInfoBox.getComponent(17);//inner
		Component contactNumber9=contactInfoBox.getComponent(19);//inner
		Component contactNumber10=contactInfoBox.getComponent(21);//inner
		Component contactNumber11=contactInfoBox.getComponent(23);//inner
		Component bloodTypedrop=bloodTypeBox.getComponent(3);//inner
		Component genderDrop=genderBox.getComponent(3);//inner
		Component sexDropDown=sexBox.getComponent(3);//inner
		Component religon=religonBox.getComponent(3);//inner
		Component drinkingSlider=drinkerBox.getComponent(1);//inner
		Component smokingSlider=smokerBox.getComponent(1);//inner
		Component allergies=allergiesBox.getComponent(1);//inner
		Component nationality=nationalityBox.getComponent(3);//inner
		Component listOfDisabilities=disabilitiesBox.getComponent(1);//inner
		Component carerRadio=disabilitiesBox.getComponent(2);//inner
		Component translatorRadio=disabilitiesBox.getComponent(3);//inner
		Component disabilitiesTa=disabilitiesBox.getComponent(1);//inner
		Component disabilitiescarer=disabilitiesBox.getComponent(2);//inner
		Component disabilitiesTranslator=disabilitiesBox.getComponent(3);//inner
		Component addressCounty=addressCountyBox.getComponent(3);//inner

		if(loginChoice==0)
		{
			
			userIDLBL.setText(userPatient.patientID);//the label is geven text to add meaning to the label
			
			((JTextField) firstNameTf).setText(userPatient.firstName);
			
			//Sname
			
			((JTextField) surnameNameTf).setText(userPatient.surName);
			
			//DOB
				//day
					LocalDate ld = LocalDate.now();
			ld = userPatient.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			((DatePicker) dateBookingBox).setDate(ld);
				
			//Address
				//name
				((JTextField) addressName).setText(userPatient.addressHouseNum+"");
				
				//Street
				((JTextField) addressStreet).setText(userPatient.addressHouseStreet);
				
				//Town
				((JTextField) addressTown).setText(userPatient.town);
				
				//County
				((JTextField) addressCounty).setText(userPatient.county);
				
				//postcode
			
				((JTextField) addressPostcode1).setText(userPatient.postcode.charAt(0)+"");
				
				((JTextField) addressPostcode2).setText(userPatient.postcode.charAt(1)+"");
				
				((JTextField) addressPostcode3).setText(userPatient.postcode.charAt(2)+"");
				
				((JTextField) addressPostcode4).setText(userPatient.postcode.charAt(3)+"");
				
				((JTextField) addressPostcode5).setText(userPatient.postcode.charAt(4)+"");
				((JTextField) addressPostcode6).setText(userPatient.postcode.charAt(5)+"");
				((JTextField) addressPostcode7).setText(userPatient.postcode.charAt(6)+"");
				
				
			//contact information
			((JTextField) contactNumber1).setText(userPatient.contactNum.charAt(0)+"");
			((JTextField) contactNumber2).setText(userPatient.contactNum.charAt(1)+"");
			((JTextField) contactNumber3).setText(userPatient.contactNum.charAt(2)+"");
			
			((JTextField) contactNumber4).setText(userPatient.contactNum.charAt(3)+"");
			
			((JTextField) contactNumber5).setText(userPatient.contactNum.charAt(4)+"");
			
			((JTextField) contactNumber6).setText(userPatient.contactNum.charAt(5)+"");
			
			((JTextField) contactNumber7).setText(userPatient.contactNum.charAt(6)+"");
			
			((JTextField) contactNumber8).setText(userPatient.contactNum.charAt(7)+"");
			
			((JTextField) contactNumber9).setText(userPatient.contactNum.charAt(8)+"");
			
			((JTextField) contactNumber10).setText(userPatient.contactNum.charAt(9)+"");
			
			((JTextField) contactNumber11).setText(userPatient.contactNum.charAt(10)+"");
			
			
			//BloodType
			
			((JComboBox) bloodTypedrop).setSelectedItem(userPatient.bloodType);
			
			
			//Gender
			
			((JComboBox) genderDrop).setSelectedItem(userPatient.gender);
			
			
			//Sex
			
			((JComboBox) sexDropDown).setSelectedItem(userPatient.sex);
			

			//Religon
			
			((JTextField) religon).setText(userPatient.religon);
			

			//Drinker
			
			((JSlider) drinkingSlider).setValue(userPatient.drinker);
			

			//Smoker
			
			((JSlider) smokingSlider).setValue(userPatient.smoker);
			

			//allergies
			
			((JTextArea) allergies).setText(userPatient.allergies);
			
			//Nationality
			
			((JTextField) nationality).setText(userPatient.nationality);
			
			//Disabilities
			if(userPatient.disability.equals("None")==false)
			{
				demographicDisablitiesPromptLbl.setSelected(true);
				
				((JTextArea) listOfDisabilities).setText(userPatient.disability);
				
				((JRadioButton) carerRadio).setSelected(userPatient.carer);
				
				
				((JRadioButton) translatorRadio).setSelected(userPatient.translator);
				
			}
			if(userPatient.disability.equals("None")==true)
			{
				demographicDisablitiesPromptLbl.setSelected(false);
				
					((JTextArea) disabilitiesTa).setEditable(false);
					((JTextArea) disabilitiesTa).setText("");
					((JTextArea) disabilitiesTa).setBackground(lightGreyColor);
					((JRadioButton) disabilitiescarer).setEnabled(false);
					((JRadioButton) disabilitiescarer).setSelected(false);
					((JRadioButton) disabilitiesTranslator).setEnabled(false);
					((JRadioButton) disabilitiesTranslator).setSelected(false);

			}
		}
		else if(loginChoice ==3)
		{
			userIDLBL.setText(userConsultant.consultantID);//the label is geven text to add meaning to the label

		//Fname
			((JTextField) firstNameTf).setText(userConsultant.firstName);
			//Sname
			((JTextField) surnameNameTf).setText(userConsultant.surName);
			
			//DOB
				
					LocalDate ld = LocalDate.now();
			ld = userConsultant.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			((DatePicker) dateBookingBox).setDate(ld);
			//Address
				//name
				((JTextField) addressName).setText(userConsultant.addressHouseNum+"");
				
				//Street
				((JTextField) addressStreet).setText(userConsultant.addressHouseStreet);
				
				//Town
				((JTextField) addressTown).setText(userConsultant.town);
				
				//County
				((JTextField) addressCounty).setText(userConsultant.county);
				
				//postcode
			
				((JTextField) addressPostcode1).setText(userConsultant.postcode.charAt(0)+"");
				
				((JTextField) addressPostcode2).setText(userConsultant.postcode.charAt(1)+"");
				
				((JTextField) addressPostcode3).setText(userConsultant.postcode.charAt(2)+"");
				
				((JTextField) addressPostcode4).setText(userConsultant.postcode.charAt(3)+"");
				
				((JTextField) addressPostcode5).setText(userConsultant.postcode.charAt(4)+"");
				
				((JTextField) addressPostcode6).setText(userConsultant.postcode.charAt(5)+"");
				
				((JTextField) addressPostcode7).setText(userConsultant.postcode.charAt(6)+"");
				
				
			//contact information
			((JTextField) contactNumber1).setText(userConsultant.contactNum.charAt(0)+"");
			
			((JTextField) contactNumber2).setText(userConsultant.contactNum.charAt(1)+"");
			
			((JTextField) contactNumber3).setText(userConsultant.contactNum.charAt(2)+"");
			
			((JTextField) contactNumber4).setText(userConsultant.contactNum.charAt(3)+"");
			
			((JTextField) contactNumber5).setText(userConsultant.contactNum.charAt(4)+"");
			
			((JTextField) contactNumber6).setText(userConsultant.contactNum.charAt(5)+"");
			
			((JTextField) contactNumber7).setText(userConsultant.contactNum.charAt(6)+"");
			
			((JTextField) contactNumber8).setText(userConsultant.contactNum.charAt(7)+"");
			
			((JTextField) contactNumber9).setText(userConsultant.contactNum.charAt(8)+"");
			
			((JTextField) contactNumber10).setText(userConsultant.contactNum.charAt(9)+"");
			
			((JTextField) contactNumber11).setText(userConsultant.contactNum.charAt(10)+"");
			
			
			//BloodType
			((JComboBox) bloodTypedrop).setSelectedItem(userConsultant.bloodType);
			
			
			//Gender
			((JComboBox) genderDrop).setSelectedItem(userConsultant.gender);
			
			
			//Sex
			((JComboBox) sexDropDown).setSelectedItem(userConsultant.sex);
			

			//Religon
			((JTextField) religon).setText(userConsultant.religon);
			

			//Drinker
			((JSlider) drinkingSlider).setValue(userConsultant.drinker);
			

			//Smoker
			((JSlider) smokingSlider).setValue(userConsultant.smoker);
			

			//allergies
			((JTextArea) allergies).setText(userConsultant.allergies);
			
			//Nationality
			((JTextField) nationality).setText(userConsultant.nationality);
			
			//Disabilities
			if(userConsultant.disability.equals("None")==false)
			{
				demographicDisablitiesPromptLbl.setSelected(true);
				
				((JTextArea) listOfDisabilities).setText(userConsultant.disability);
				
				
				((JRadioButton) carerRadio).setSelected(userConsultant.carer);
				
				((JRadioButton) translatorRadio).setSelected(userConsultant.translator);
				
			}
			if(userConsultant.disability.equals("None")==true)
			{
				demographicDisablitiesPromptLbl.setSelected(false);
					((JTextArea) disabilitiesTa).setEditable(false);
					((JTextArea) disabilitiesTa).setText("");
					((JTextArea) disabilitiesTa).setBackground(lightGreyColor);
					((JRadioButton) disabilitiescarer).setEnabled(false);
					((JRadioButton) disabilitiescarer).setSelected(false);
					((JRadioButton) disabilitiesTranslator).setEnabled(false);
					((JRadioButton) disabilitiesTranslator).setSelected(false);

			}		
		}
	
		else if(loginChoice ==1)
		{
		userIDLBL.setText(userStaff.staffID);//the label is geven text to add meaning to the label

		//Fname
			((JTextField) firstNameTf).setText(userStaff.firstName);
			//Sname
			((JTextField) surnameNameTf).setText(userStaff.surName);
			
			//DOB
				
					LocalDate ld = LocalDate.now();
			ld = userStaff.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			((DatePicker) dateBookingBox).setDate(ld);
			//Address
				//name
				((JTextField) addressName).setText(userStaff.addressHouseNum+"");
				
				//Street
				((JTextField) addressStreet).setText(userStaff.addressHouseStreet);
				
				//Town
				((JTextField) addressTown).setText(userStaff.town);
				
				//County
				((JTextField) addressCounty).setText(userStaff.county);
				
				//postcode
				////System.out.println(userStaff.postcode);
				((JTextField) addressPostcode1).setText(userStaff.postcode.charAt(0)+"");
				
				((JTextField) addressPostcode2).setText(userStaff.postcode.charAt(1)+"");
				
				((JTextField) addressPostcode3).setText(userStaff.postcode.charAt(2)+"");
				
				((JTextField) addressPostcode4).setText(userStaff.postcode.charAt(3)+"");
				
				((JTextField) addressPostcode5).setText(userStaff.postcode.charAt(4)+"");
				
				((JTextField) addressPostcode6).setText(userStaff.postcode.charAt(5)+"");
				
				((JTextField) addressPostcode7).setText(userStaff.postcode.charAt(6)+"");
				
				
			//contact information
			((JTextField) contactNumber1).setText(userStaff.contactNum.charAt(0)+"");
			
			((JTextField) contactNumber2).setText(userStaff.contactNum.charAt(1)+"");
			
			((JTextField) contactNumber3).setText(userStaff.contactNum.charAt(2)+"");
			
			((JTextField) contactNumber4).setText(userStaff.contactNum.charAt(3)+"");
			
			((JTextField) contactNumber5).setText(userStaff.contactNum.charAt(4)+"");
			
			((JTextField) contactNumber6).setText(userStaff.contactNum.charAt(5)+"");
			
			((JTextField) contactNumber7).setText(userStaff.contactNum.charAt(6)+"");
			
			((JTextField) contactNumber8).setText(userStaff.contactNum.charAt(7)+"");
			
			((JTextField) contactNumber9).setText(userStaff.contactNum.charAt(8)+"");
			
			((JTextField) contactNumber10).setText(userStaff.contactNum.charAt(9)+"");
			
			((JTextField) contactNumber11).setText(userStaff.contactNum.charAt(10)+"");
			
			
			//BloodType
			((JComboBox) bloodTypedrop).setSelectedItem(userStaff.bloodType);
			
			
			//Gender
			((JComboBox) genderDrop).setSelectedItem(userStaff.gender);
			
			
			//Sex
			((JComboBox) sexDropDown).setSelectedItem(userStaff.sex);
			

			//Religon
			((JTextField) religon).setText(userStaff.religon);
			

			//Drinker
			((JSlider) drinkingSlider).setValue(userStaff.drinker);
			

			//Smoker
			((JSlider) smokingSlider).setValue(userStaff.smoker);
			

			//allergies
			((JTextArea) allergies).setText(userStaff.allergies);
			
			//Nationality
			((JTextField) nationality).setText(userStaff.nationality);
			
			//Disabilities
			if(userStaff.disability.equals("None")==false)
			{
				demographicDisablitiesPromptLbl.setSelected(true);
				
				((JTextArea) listOfDisabilities).setText(userStaff.disability);
				
				
				((JRadioButton) carerRadio).setSelected(userStaff.carer);
				
				((JRadioButton) translatorRadio).setSelected(userStaff.translator);
				
			}
			if(userStaff.disability.equals("None")==true)
			{
				demographicDisablitiesPromptLbl.setSelected(false);
					((JTextArea) disabilitiesTa).setEditable(false);
					((JTextArea) disabilitiesTa).setText("");
					((JTextArea) disabilitiesTa).setBackground(lightGreyColor);
					((JRadioButton) disabilitiescarer).setEnabled(false);
					((JRadioButton) disabilitiescarer).setSelected(false);
					((JRadioButton) disabilitiesTranslator).setEnabled(false);
					((JRadioButton) disabilitiesTranslator).setSelected(false);

			}		
		}
		demographicHomepagePanel.add(fNameBox);	
		demographicHomepagePanel.add(dOBBox);	
		demographicHomepagePanel.add(sNameBox);	
		demographicHomepagePanel.add(disabilitiesBox);	
		demographicHomepagePanel.add(nationalityBox);	
		demographicHomepagePanel.add(allergiesBox);
		demographicHomepagePanel.add(smokerBox);
		demographicHomepagePanel.add(drinkerBox);
		demographicHomepagePanel.add(sexBox);	
		demographicHomepagePanel.add(genderBox);	
		demographicHomepagePanel.add(religonBox);	
		demographicHomepagePanel.add(bloodTypeBox);	
		demographicHomepagePanel.add(contactInfoBox);	
		demographicHomepagePanel.add(addressBox);	
		demographicHomepagePanel.add(userProfilePicfield);	
		
	}
	
	//AN OVERWRITTEN VERSION TO MAKE SURE THAT THE ADMISSION VERSION ON THE STAFF SIDE CAN CORRECTLY LOAD THE VALUES
	public void createDemographicComponentsGUIGeneral(Patient userPatientLocal)
	{
		userIDLBL.setText(userPatientLocal.patientID);//the label is geven text to add meaning to the label


		saveDemographicChanges.setVisible(false);//hides the button which can update the patients details
		
		//System.out.println("This is ran");
		//Fname
		Component firstNameTf=fNameBox.getComponent(3);//inner
		((JTextField) firstNameTf).setText(userPatientLocal.firstName);
		((JTextField) firstNameTf).setText(userPatientLocal.firstName);

		//Sname
		Component surnameNameTf=sNameBox.getComponent(3);//inner
		((JTextField) surnameNameTf).setText(userPatientLocal.surName);
		
		//DOB
			//day
			LocalDate ld = LocalDate.now();
			Component dateBookingBox=dOBBox.getComponent(3);//inner
			ld = userPatientLocal.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			((DatePicker) dateBookingBox).setDate(ld);
				
		//Address
			//name
			Component addressName=addressBuildingBox.getComponent(3);//inner
			((JTextField) addressName).setText(userPatientLocal.addressHouseNum+"");
			
			//Street
			Component addressStreet=addressStreetBox.getComponent(3);//inner
			((JTextField) addressStreet).setText(userPatientLocal.addressHouseStreet);
			
			//Town
			Component addressTown=addressTownBox.getComponent(3);//inner
			((JTextField) addressTown).setText(userPatientLocal.town);
			
			//County
			Component addressCounty=addressCountyBox.getComponent(3);//inner
			((JTextField) addressCounty).setText(userPatientLocal.county);
			
			//postcode
		
			Component addressPostcode1=addressPostcodeBox.getComponent(3);//inner
			((JTextField) addressPostcode1).setText(userPatientLocal.postcode.charAt(0)+"");
			
			Component addressPostcode2=addressPostcodeBox.getComponent(5);//inner
			((JTextField) addressPostcode2).setText(userPatientLocal.postcode.charAt(1)+"");
			
			Component addressPostcode3=addressPostcodeBox.getComponent(7);//inner
			((JTextField) addressPostcode3).setText(userPatientLocal.postcode.charAt(2)+"");
			
			Component addressPostcode4=addressPostcodeBox.getComponent(9);//inner
			((JTextField) addressPostcode4).setText(userPatientLocal.postcode.charAt(3)+"");
			
			Component addressPostcode5=addressPostcodeBox.getComponent(11);//inner
			((JTextField) addressPostcode5).setText(userPatientLocal.postcode.charAt(4)+"");
			
			Component addressPostcode6=addressPostcodeBox.getComponent(13);//inner
			((JTextField) addressPostcode6).setText(userPatientLocal.postcode.charAt(5)+"");
			
			Component addressPostcode7=addressPostcodeBox.getComponent(15);//inner
			((JTextField) addressPostcode7).setText(userPatientLocal.postcode.charAt(6)+"");
			
			
		//contact information
		Component contactNumber1=contactInfoBox.getComponent(3);//inner
		((JTextField) contactNumber1).setText(userPatientLocal.contactNum.charAt(0)+"");
		
		Component contactNumber2=contactInfoBox.getComponent(5);//inner
		((JTextField) contactNumber2).setText(userPatientLocal.contactNum.charAt(1)+"");
		
		Component contactNumber3=contactInfoBox.getComponent(7);//inner
		((JTextField) contactNumber3).setText(userPatientLocal.contactNum.charAt(2)+"");
		
		Component contactNumber4=contactInfoBox.getComponent(9);//inner
		((JTextField) contactNumber4).setText(userPatientLocal.contactNum.charAt(3)+"");
		
		Component contactNumber5=contactInfoBox.getComponent(11);//inner
		((JTextField) contactNumber5).setText(userPatientLocal.contactNum.charAt(4)+"");
		
		Component contactNumber6=contactInfoBox.getComponent(13);//inner
		((JTextField) contactNumber6).setText(userPatientLocal.contactNum.charAt(5)+"");
		
		Component contactNumber7=contactInfoBox.getComponent(15);//inner
		((JTextField) contactNumber7).setText(userPatientLocal.contactNum.charAt(6)+"");
		
		Component contactNumber8=contactInfoBox.getComponent(17);//inner
		((JTextField) contactNumber8).setText(userPatientLocal.contactNum.charAt(7)+"");
		
		Component contactNumber9=contactInfoBox.getComponent(19);//inner
		((JTextField) contactNumber9).setText(userPatientLocal.contactNum.charAt(8)+"");
		
		Component contactNumber10=contactInfoBox.getComponent(21);//inner
		((JTextField) contactNumber10).setText(userPatientLocal.contactNum.charAt(9)+"");
		
		Component contactNumber11=contactInfoBox.getComponent(23);//inner
		((JTextField) contactNumber11).setText(userPatientLocal.contactNum.charAt(10)+"");
		
		
		//BloodType
		Component bloodTypedrop=bloodTypeBox.getComponent(3);//inner
		((JComboBox) bloodTypedrop).setSelectedItem(userPatientLocal.bloodType);
		
		
		//Gender
		Component genderDrop=genderBox.getComponent(3);//inner
		((JComboBox) genderDrop).setSelectedItem(userPatientLocal.gender);
		
		
		//Sex
		Component sexDropDown=sexBox.getComponent(3);//inner
		((JComboBox) sexDropDown).setSelectedItem(userPatientLocal.sex);
		

		//Religon
		Component religon=religonBox.getComponent(3);//inner
		((JTextField) religon).setText(userPatientLocal.religon);
		

		//Drinker
		Component drinkingSlider=drinkerBox.getComponent(1);//inner
		((JSlider) drinkingSlider).setValue(userPatientLocal.drinker);
		

		//Smoker
		Component smokingSlider=smokerBox.getComponent(1);//inner
		((JSlider) smokingSlider).setValue(userPatientLocal.smoker);
		

		//allergies
		Component allergies=allergiesBox.getComponent(1);//inner
		((JTextArea) allergies).setText(userPatientLocal.allergies);
		
		//Nationality
		Component nationality=nationalityBox.getComponent(3);//inner
		((JTextField) nationality).setText(userPatientLocal.nationality);
		
		//Disabilities
		if(userPatientLocal.disability.equals("None")==false)
		{
			demographicDisablitiesPromptLbl.setSelected(true);
			
			Component listOfDisabilities=disabilitiesBox.getComponent(1);//inner
			((JTextArea) listOfDisabilities).setText(userPatientLocal.disability);
			
			
			Component carerRadio=disabilitiesBox.getComponent(2);//inner
			((JRadioButton) carerRadio).setSelected(userPatientLocal.carer);
			
			Component translatorRadio=disabilitiesBox.getComponent(3);//inner
			((JRadioButton) translatorRadio).setSelected(userPatientLocal.translator);
			
		}
		if(userPatientLocal.disability.equals("None")==true)
		{
			demographicDisablitiesPromptLbl.setSelected(false);
			Component disabilitiesTa=disabilitiesBox.getComponent(1);//inner
				((JTextArea) disabilitiesTa).setEditable(false);
				((JTextArea) disabilitiesTa).setText("");
				((JTextArea) disabilitiesTa).setBackground(lightGreyColor);
				Component disabilitiescarer=disabilitiesBox.getComponent(2);//inner
				((JRadioButton) disabilitiescarer).setEnabled(false);
				((JRadioButton) disabilitiescarer).setSelected(false);
				Component disabilitiesTranslator=disabilitiesBox.getComponent(3);//inner
				((JRadioButton) disabilitiesTranslator).setEnabled(false);
				((JRadioButton) disabilitiesTranslator).setSelected(false);

		}
		demographicHomepagePanel.add(fNameBox);	
		demographicHomepagePanel.add(dOBBox);	
		demographicHomepagePanel.add(sNameBox);	
		demographicHomepagePanel.add(disabilitiesBox);	
		demographicHomepagePanel.add(nationalityBox);	
		demographicHomepagePanel.add(allergiesBox);
		demographicHomepagePanel.add(smokerBox);
		demographicHomepagePanel.add(drinkerBox);
		demographicHomepagePanel.add(sexBox);	
		demographicHomepagePanel.add(genderBox);	
		demographicHomepagePanel.add(religonBox);	
		demographicHomepagePanel.add(bloodTypeBox);	
		demographicHomepagePanel.add(contactInfoBox);	
		demographicHomepagePanel.add(addressBox);	
		demographicHomepagePanel.add(userProfilePicfield);
	}
	
	//===== graphical aspect ======
	//inital call methof for the panel this will load and add the panel to the frame and will perform
	//any other actions such as adding data/updateing it if requeired
	public void createdemographicHomepagePanelGUI()
	{
		createTopbar(demographicHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		if(panelOrder[endPanelPointer]==demographicHomepagePanel)
		{
			removePanel();
		}
		createDemographicComponentsGUIGeneral();//the components of the panel are cereated by running the method this is done initially so it is only done once and will be used throughout
		addPanel(demographicHomepagePanel);
	}
	
	//AN OVERWRITTEN VERSION TO MAKE SURE THAT THE ADMISSION VERSION ON THE STAFF SIDE CAN CORRECTLY LOAD THE VALUES
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	public void createdemographicHomepagePanelGUI(Patient userPatientLocal)
	{
		createTopbar(demographicHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		createDemographicComponentsGUIGeneral(userPatientLocal);//the components of the panel are cereated by running the method this is done initially so it is only done once and will be used throughout
		addPanel(demographicHomepagePanel);
	}
	
	//This method is large, what it does as the start of the systems start up is it initalises all the Graphical componenets on the system
	//it is done at the start because it is common to every user account on the system
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createDemographicNewPatientComponentsGUI()
	{
		
		userIDLBL.setFont(headerFont);//the font that has been declared is attached to the component
		userIDLBL.setSize(400,40);//the components size is correctly declared
		userIDLBL.setLocation(65,75);//sets the location of the component 
		demographicHomepagePanel.add(userIDLBL);//the component is added to the panel

		JLabel titleUpperBlackLine = new JLabel();//declares a labbel for the main header on the login screen 
		titleUpperBlackLine.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		titleUpperBlackLine.setForeground( new Color(1) );//the foreground of the component is given a white font
		titleUpperBlackLine.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		titleUpperBlackLine.setLocation(45,60);//sets the location of the component 
		titleUpperBlackLine.setSize(500,3);//the components size is correctly declared
		titleUpperBlackLine.setOpaque(true);//the component is set to opaque
		demographicHomepagePanel.add(titleUpperBlackLine);//the component is added to the panel

		JLabel titleLowerBlackLine = new JLabel();//declares a labbel for the main header on the login screen 
		titleLowerBlackLine.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		titleLowerBlackLine.setForeground( new Color(1) );//the foreground of the component is given a white font
		titleLowerBlackLine.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		titleLowerBlackLine.setLocation(45,125);//sets the location of the component 
		titleLowerBlackLine.setSize(500,3);//the components size is correctly declared
		titleLowerBlackLine.setOpaque(true);//the component is set to opaque
		demographicHomepagePanel.add(titleLowerBlackLine);//the component is added to the panel




		userProfilePicfield.setSize(242,272); //the components size is correctly declared
		userProfilePicfield.setIcon( new ImageIcon("personIconLarge.png") );//the correct image is retrieved from the folder
		userProfilePicfield.setLocation(1190,55);//the location of the component is set to the desired part of the panel
		
		
		JLabel demographicFNamePromptLbl = new JLabel();//declares a label that is used for the first name
		demographicFNamePromptLbl.setText("Firstname:");//formatting component to include text
		demographicFNamePromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicFNamePromptLbl.setMinimumSize(new Dimension(125,40));
		demographicFNamePromptLbl.setPreferredSize(new Dimension(125,40));
		demographicFNamePromptLbl.setMaximumSize(new Dimension(125,40));		
		demographicFNamePromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose

		JTextField demographicFNameTextFeild = new JTextField();//declares the text feild that will hold the first name
		demographicFNameTextFeild.setText("Please enter your first name");//formatting component to include text
		demographicFNameTextFeild.setMinimumSize(new Dimension(350,40));
		demographicFNameTextFeild.setPreferredSize(new Dimension(350,40));
		demographicFNameTextFeild.setMaximumSize(new Dimension(350,40));
		
		demographicFNameTextFeild.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
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
		fNameBox.add(Box.createRigidArea(new Dimension(10,0)));
		fNameBox.add(demographicFNamePromptLbl);//the component is added to the panel
		fNameBox.add(Box.createRigidArea(new Dimension(5,0)));
		fNameBox.add(demographicFNameTextFeild);//the component is added to the panel
		fNameBox.setSize(530,55);//size of component is set
		fNameBox.setLocation(45,145);//the location of the component is set to the desired part of the panel
		fNameBox.setBackground( new Color(-1) );
		fNameBox.setOpaque(true);
		fNameBox.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel demographicSNPromptLbl = new JLabel();//declares a label that is used for the surname
		demographicSNPromptLbl.setText("Surname:");//formatting component to include text
		demographicSNPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicSNPromptLbl.setMinimumSize(new Dimension(125,40));
		demographicSNPromptLbl.setPreferredSize(new Dimension(125,40));
		demographicSNPromptLbl.setMaximumSize(new Dimension(125,40));
		demographicSNPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
	
	
		JTextField demographicSNTextFeild = new JTextField();//declares the text feild that will hold the surname
		demographicSNTextFeild.setText("Please enter your surname");//formatting component to include text
		demographicSNTextFeild.setMinimumSize(new Dimension(350,40));
		demographicSNTextFeild.setPreferredSize(new Dimension(350,40));
		demographicSNTextFeild.setMaximumSize(new Dimension(350,40));
		demographicSNTextFeild.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
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
	
		sNameBox.add(Box.createRigidArea(new Dimension(10,0)));
		sNameBox.add(demographicSNPromptLbl);//the component is added to the panel
		sNameBox.add(Box.createRigidArea(new Dimension(5,0)));
		sNameBox.add(demographicSNTextFeild);//the component is added to the panel
		sNameBox.setSize(530,55);//size of component is set
		sNameBox.setLocation(45,235);//the location of the component is set to the desired part of the panel
		sNameBox.setBackground( new Color(-1) );
		sNameBox.setOpaque(true);
		sNameBox.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JLabel demographicDOBPromptLbl = new JLabel();//declares a label that is used for the date of birth
		demographicDOBPromptLbl.setText("Date Of Birth:");//formatting component to include text
		demographicDOBPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicDOBPromptLbl.setMinimumSize(new Dimension(150,40));
		demographicDOBPromptLbl.setPreferredSize(new Dimension(150,40));
		demographicDOBPromptLbl.setMaximumSize(new Dimension(150,40));
		demographicDOBPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		
		DatePickerSettings dateOfBirthSettings = new DatePickerSettings();//Creates a new custom settings for the date picker
		DatePicker dateOfBirthPicker = new DatePicker(dateOfBirthSettings);//
		
		dateOfBirthPicker.setMinimumSize(new Dimension(300,50));
		dateOfBirthPicker.setPreferredSize(new Dimension(300,50));
		dateOfBirthPicker.setMaximumSize(new Dimension(300,50));
		//dateOfBirthPicker.addDateChangeListener(listner);
		dateOfBirthPicker.getComponentToggleCalendarButton().setMinimumSize(new Dimension(100,40));
		dateOfBirthPicker.getComponentToggleCalendarButton().setPreferredSize(new Dimension(100,40));
		dateOfBirthPicker.getComponentToggleCalendarButton().setMaximumSize(new Dimension(100,40));
		dateOfBirthPicker.getComponentToggleCalendarButton().setText("View Dates");
		dateOfBirthPicker.getComponentToggleCalendarButton().setForeground( new Color(-1) );//the foreground of the component is given a white font
		dateOfBirthPicker.getComponentToggleCalendarButton().setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		dateOfBirthPicker.getComponentToggleCalendarButton().setFont(symptomfont);//the font that has been declared is attached to the component 

		dateOfBirthSettings.setAllowKeyboardEditing(false);//prevents custom dates being entered
		dateOfBirthSettings.setFontValidDate(symptomfont);
		dateOfBirthSettings.setSizeDatePanelMinimumWidth(300);
		dateOfBirthSettings.setSizeDatePanelMinimumHeight(250);

		LocalDate today = LocalDate.now();
		dateOfBirthSettings.setDateRangeLimits(today.minusYears(120), today.minusDays(1));
		
		dOBBox.add(Box.createRigidArea(new Dimension(10,0)));
		dOBBox.add(demographicDOBPromptLbl);//the component is added to the panel
		dOBBox.add(Box.createRigidArea(new Dimension(10,0)));
		dOBBox.add(dateOfBirthPicker);
		dOBBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		dOBBox.setSize(490,55);//size of component is set
		dOBBox.setLocation(45,325);//the location of the component is set to the desired part of the panel
		dOBBox.setBackground( new Color(-1) );
		dOBBox.setOpaque(true);
		dOBBox.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JLabel demographicBuildingNUMPromptLbl = new JLabel();//declares a label that is used for the building number
		demographicBuildingNUMPromptLbl.setText("Building number:");//formatting component to include text
		demographicBuildingNUMPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicBuildingNUMPromptLbl.setMinimumSize(new Dimension(190,40));
		demographicBuildingNUMPromptLbl.setPreferredSize(new Dimension(190,40));
		demographicBuildingNUMPromptLbl.setMaximumSize(new Dimension(190,40));
		demographicBuildingNUMPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		
		JTextField demographicBuildingNUMTextFeild = new JTextField();//declares the text feild that will hold the building number
		demographicBuildingNUMTextFeild.setText("Please enter your building number");//formatting component to include text
		demographicBuildingNUMTextFeild.setMinimumSize(new Dimension(350,35));
		demographicBuildingNUMTextFeild.setPreferredSize(new Dimension(350,35));
		demographicBuildingNUMTextFeild.setMaximumSize(new Dimension(350,35));
		demographicBuildingNUMTextFeild.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
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
		addressBuildingBox.setSize(700,55);//size of component is set
		addressBuildingBox.add(Box.createRigidArea(new Dimension(10,0)));
		addressBuildingBox.add(demographicBuildingNUMPromptLbl);//the component is added to the panel
		addressBuildingBox.add(Box.createRigidArea(new Dimension(5,0)));
		addressBuildingBox.add(demographicBuildingNUMTextFeild);//the component is added to the panel
		addressBuildingBox.setAlignmentX(Component.LEFT_ALIGNMENT);

		addressBox.add(Box.createRigidArea(new Dimension(0,10)));
		addressBox.add(addressBuildingBox);
		
		JLabel demographicStreetPromptLbl = new JLabel();//declares a label that is used for the street
		demographicStreetPromptLbl.setText("Street:");//formatting component to include text
		demographicStreetPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicStreetPromptLbl.setMinimumSize(new Dimension(85,35));
		demographicStreetPromptLbl.setPreferredSize(new Dimension(85,35));
		demographicStreetPromptLbl.setMaximumSize(new Dimension(85,35));
		demographicStreetPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		
		JTextField demographicStreetTextFeild = new JTextField();//declares the text feild that will hold the street
		demographicStreetTextFeild.setText("Please enter your street");//formatting component to include text
		demographicStreetTextFeild.setMinimumSize(new Dimension(350,35));
		demographicStreetTextFeild.setPreferredSize(new Dimension(350,35));
		demographicStreetTextFeild.setMaximumSize(new Dimension(350,35));
		demographicStreetTextFeild.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
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
		
		addressStreetBox.setSize(700,55);//size of component is set
		addressStreetBox.add(Box.createRigidArea(new Dimension(10,0)));
		addressStreetBox.add(demographicStreetPromptLbl);//the component is added to the panel
		addressStreetBox.add(Box.createRigidArea(new Dimension(5,0)));
		addressStreetBox.add(demographicStreetTextFeild);//the component is added to the panel
		addressStreetBox.setAlignmentX(Component.LEFT_ALIGNMENT);

		addressBox.add(Box.createRigidArea(new Dimension(0,10)));
		addressBox.add(addressStreetBox);
	
	
		JLabel demographicTownPromptLbl = new JLabel();//declares a label that is used for the town
		JTextField demographicTownTextFeild = new JTextField();//declares the text feild that will hold the town
	
		demographicTownPromptLbl.setText("Town/City:");//formatting component to include text
		demographicTownPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicTownPromptLbl.setMinimumSize(new Dimension(120,40));
		demographicTownPromptLbl.setPreferredSize(new Dimension(120,40));
		demographicTownPromptLbl.setMaximumSize(new Dimension(120,40));
		demographicTownPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		
		demographicTownTextFeild.setText("Please enter your town/city");//formatting component to include text
		demographicTownTextFeild.setMinimumSize(new Dimension(350,35));
		demographicTownTextFeild.setPreferredSize(new Dimension(350,35));
		demographicTownTextFeild.setMaximumSize(new Dimension(350,35));
		demographicTownTextFeild.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
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
		
	
		addressTownBox.setSize(700,55);//size of component is set
		addressTownBox.add(Box.createRigidArea(new Dimension(10,0)));
		addressTownBox.add(demographicTownPromptLbl);//the component is added to the panel
		addressTownBox.add(Box.createRigidArea(new Dimension(5,0)));
		addressTownBox.add(demographicTownTextFeild);//the component is added to the panel
		addressTownBox.setAlignmentX(Component.LEFT_ALIGNMENT);

		addressBox.add(Box.createRigidArea(new Dimension(0,10)));
		addressBox.add(addressTownBox);
	
	
		JLabel demographicCountyPromptLbl = new JLabel();//declares a label that is used for the county
		demographicCountyPromptLbl.setText("County:");//formatting component to include text
		demographicCountyPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicCountyPromptLbl.setMinimumSize(new Dimension(85,40));
		demographicCountyPromptLbl.setPreferredSize(new Dimension(85,40));
		demographicCountyPromptLbl.setMaximumSize(new Dimension(85,40));
		demographicCountyPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		
		JTextField demographicCountyTextFeild = new JTextField();//declares the text feild that will hold the county
		demographicCountyTextFeild.setText("Please enter your county");//formatting component to include text
		demographicCountyTextFeild.setMinimumSize(new Dimension(350,35));
		demographicCountyTextFeild.setPreferredSize(new Dimension(350,35));
		demographicCountyTextFeild.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		demographicCountyTextFeild.setMaximumSize(new Dimension(350,35));
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
		
		addressCountyBox.setSize(700,55);//size of component is set
		addressCountyBox.add(Box.createRigidArea(new Dimension(10,0)));
		addressCountyBox.add(demographicCountyPromptLbl);//the component is added to the panel
		addressCountyBox.add(Box.createRigidArea(new Dimension(5,0)));
		addressCountyBox.add(demographicCountyTextFeild);//the component is added to the panel
		addressCountyBox.setAlignmentX(Component.LEFT_ALIGNMENT);

		addressBox.add(Box.createRigidArea(new Dimension(0,10)));
		addressBox.add(addressCountyBox);
		
		
		JLabel demographicPostcodePromptLbl = new JLabel();//declares a label that is used for the postcode
		demographicPostcodePromptLbl.setText("Postcode:");//formatting component to include text
		demographicPostcodePromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicPostcodePromptLbl.setMinimumSize(new Dimension(115,40));
		demographicPostcodePromptLbl.setPreferredSize(new Dimension(115,40));
		demographicPostcodePromptLbl.setMaximumSize(new Dimension(115,40));
	demographicPostcodePromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		
		JTextField demographicPostcode1 = new JTextField(1);//declares the text feild that will hold the postcode
		demographicPostcode1.setSize(40,33);//size of component is set
		demographicPostcode1.setMinimumSize(new Dimension(30,33));
		demographicPostcode1.setPreferredSize(new Dimension(30,33));
		demographicPostcode1.setMaximumSize(new Dimension(30,33));
		demographicPostcode1.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		((AbstractDocument)demographicPostcode1.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));

	
		JTextField demographicPostcode2 = new JTextField(1);//declares the text feild that will hold the postcode
		demographicPostcode2.setMinimumSize(new Dimension(30,33));
		demographicPostcode2.setPreferredSize(new Dimension(30,33));
		demographicPostcode2.setMaximumSize(new Dimension(30,33));
		demographicPostcode2.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		((AbstractDocument)demographicPostcode2.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));

		
		JTextField demographicPostcode3 = new JTextField(1);//declares the text feild that will hold the postcode
		demographicPostcode3.setMinimumSize(new Dimension(30,33));
		demographicPostcode3.setPreferredSize(new Dimension(30,33));
		demographicPostcode3.setMaximumSize(new Dimension(30,33));
		demographicPostcode3.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		((AbstractDocument)demographicPostcode3.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));

		
		JTextField demographicPostcode4 = new JTextField(1);//declares the text feild that will hold the postcode
		demographicPostcode4.setMinimumSize(new Dimension(30,33));
		demographicPostcode4.setPreferredSize(new Dimension(30,33));
		demographicPostcode4.setMaximumSize(new Dimension(30,33));
		demographicPostcode4.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		((AbstractDocument)demographicPostcode4.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));

		JTextField demographicPostcode5 = new JTextField(1);//declares the text feild that will hold the postcode
		demographicPostcode5.setMinimumSize(new Dimension(30,33));
		demographicPostcode5.setPreferredSize(new Dimension(30,33));
		demographicPostcode5.setMaximumSize(new Dimension(30,33));
		demographicPostcode5.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		((AbstractDocument)demographicPostcode5.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));

		JTextField demographicPostcode6 = new JTextField(1);//declares the text feild that will hold the postcode
		demographicPostcode6.setMinimumSize(new Dimension(30,33));
		demographicPostcode6.setPreferredSize(new Dimension(30,33));
		demographicPostcode6.setMaximumSize(new Dimension(30,33));
		demographicPostcode6.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		((AbstractDocument)demographicPostcode6.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));

		
		JTextField demographicPostcode7 = new JTextField(1);//declares the text feild that will hold the postcode
		demographicPostcode7.setMinimumSize(new Dimension(30,33));
		demographicPostcode7.setPreferredSize(new Dimension(30,33));
		demographicPostcode7.setMaximumSize(new Dimension(30,33));
		demographicPostcode7.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		((AbstractDocument)demographicPostcode7.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));

		addressPostcodeBox.setSize(700,55);//size of component is set
		addressPostcodeBox.add(Box.createRigidArea(new Dimension(10,0)));
		addressPostcodeBox.add(demographicPostcodePromptLbl);//the component is added to the panel
		addressPostcodeBox.add(Box.createRigidArea(new Dimension(5,0)));
		addressPostcodeBox.add(demographicPostcode1);//the component is added to the panel
		addressPostcodeBox.add(Box.createRigidArea(new Dimension(5,0)));
		addressPostcodeBox.add(demographicPostcode2);//the component is added to the panel
		addressPostcodeBox.add(Box.createRigidArea(new Dimension(5,0)));
		addressPostcodeBox.add(demographicPostcode3);//the component is added to the panel
		addressPostcodeBox.add(Box.createRigidArea(new Dimension(5,0)));
		addressPostcodeBox.add(demographicPostcode4);//the component is added to the panel
		addressPostcodeBox.add(Box.createRigidArea(new Dimension(5,0)));
		addressPostcodeBox.add(demographicPostcode5);//the component is added to the panel
		addressPostcodeBox.add(Box.createRigidArea(new Dimension(5,0)));
		addressPostcodeBox.add(demographicPostcode6);//the component is added to the panel
		addressPostcodeBox.add(Box.createRigidArea(new Dimension(5,0)));
		addressPostcodeBox.add(demographicPostcode7);//the component is added to the panel
		
		addressPostcodeBox.setAlignmentX(Component.LEFT_ALIGNMENT);

		addressBox.add(Box.createRigidArea(new Dimension(0,10)));
		addressBox.add(addressPostcodeBox);
		addressBox.setSize(630,270);//size of component is set
		addressBox.setLocation(45,415);//the location of the component is set to the desired part of the panel
		addressBox.setBackground( new Color(-1) );
		addressBox.setOpaque(true);
		addressBox.setBorder(BorderFactory.createLineBorder(Color.black));
	
	
		JLabel demographicContactinfoPromptLbl = new JLabel();//declares a label that is used for the contact information
		demographicContactinfoPromptLbl.setText("Contact Information:");//formatting component to include text
		demographicContactinfoPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicContactinfoPromptLbl.setMinimumSize(new Dimension(225,40));
		demographicContactinfoPromptLbl.setPreferredSize(new Dimension(225,25));
		demographicContactinfoPromptLbl.setMaximumSize(new Dimension(225,25));
		demographicContactinfoPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		
		JTextField demographiccontact1 = new JTextField();//declares the text feild that will hold the postcode
		demographiccontact1.setSize(40,33);//size of component is set
		demographiccontact1.setMinimumSize(new Dimension(30,33));
		demographiccontact1.setPreferredSize(new Dimension(30,33));
		demographiccontact1.setMaximumSize(new Dimension(30,33));
		demographiccontact1.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		((AbstractDocument)demographiccontact1.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));

		JTextField demographiccontact2 = new JTextField();//declares the text feild that will hold the postcode
		demographiccontact2.setMinimumSize(new Dimension(30,33));
		demographiccontact2.setPreferredSize(new Dimension(30,33));
		demographiccontact2.setMaximumSize(new Dimension(30,33));
		demographiccontact2.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		((AbstractDocument)demographiccontact2.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));

		JTextField demographiccontact3 = new JTextField();//declares the text feild that will hold the postcode
		demographiccontact3.setMinimumSize(new Dimension(30,33));
		demographiccontact3.setPreferredSize(new Dimension(30,33));
		demographiccontact3.setMaximumSize(new Dimension(30,33));
		demographiccontact3.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		((AbstractDocument)demographiccontact3.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));
		
		JTextField demographiccontact4 = new JTextField();//declares the text feild that will hold the postcode
		demographiccontact4.setMinimumSize(new Dimension(30,33));
		demographiccontact4.setPreferredSize(new Dimension(30,33));
		demographiccontact4.setMaximumSize(new Dimension(30,33));
		demographiccontact4.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		((AbstractDocument)demographiccontact4.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));
		
		JTextField demographiccontact5 = new JTextField();//declares the text feild that will hold the postcode
		demographiccontact5.setMinimumSize(new Dimension(30,33));
		demographiccontact5.setPreferredSize(new Dimension(30,33));
		demographiccontact5.setMaximumSize(new Dimension(30,33));
		demographiccontact5.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		((AbstractDocument)demographiccontact5.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));
		
		JTextField demographiccontact6 = new JTextField();//declares the text feild that will hold the postcode
		demographiccontact6.setMinimumSize(new Dimension(30,33));
		demographiccontact6.setPreferredSize(new Dimension(30,33));
		demographiccontact6.setMaximumSize(new Dimension(30,33));
		demographiccontact6.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		((AbstractDocument)demographiccontact6.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));
		
		JTextField demographiccontact7 = new JTextField();//declares the text feild that will hold the postcode
		demographiccontact7.setMinimumSize(new Dimension(30,33));
		demographiccontact7.setPreferredSize(new Dimension(30,33));
		demographiccontact7.setMaximumSize(new Dimension(30,33));
		demographiccontact7.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		((AbstractDocument)demographiccontact7.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));
			
		JTextField demographiccontact8 = new JTextField();//declares the text feild that will hold the postcode
		demographiccontact8.setMinimumSize(new Dimension(30,33));
		demographiccontact8.setPreferredSize(new Dimension(30,33));
		demographiccontact8.setMaximumSize(new Dimension(30,33));
		demographiccontact8.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose	
		((AbstractDocument)demographiccontact8.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));
			
		JTextField demographiccontact9 = new JTextField();//declares the text feild that will hold the postcode
		demographiccontact9.setMinimumSize(new Dimension(30,33));
		demographiccontact9.setPreferredSize(new Dimension(30,33));
		demographiccontact9.setMaximumSize(new Dimension(30,33));
		demographiccontact9.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		((AbstractDocument)demographiccontact9.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));
		
		JTextField demographiccontact10 = new JTextField();//declares the text feild that will hold the postcode
		demographiccontact10.setMinimumSize(new Dimension(30,33));
		demographiccontact10.setPreferredSize(new Dimension(30,33));
		demographiccontact10.setMaximumSize(new Dimension(30,33));
		demographiccontact10.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		((AbstractDocument)demographiccontact10.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));
			
		JTextField demographiccontact11 = new JTextField();//declares the text feild that will hold the postcode
		demographiccontact11.setMinimumSize(new Dimension(30,33));
		demographiccontact11.setPreferredSize(new Dimension(30,33));
		demographiccontact11.setMaximumSize(new Dimension(30,33));
		demographiccontact11.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		((AbstractDocument)demographiccontact11.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));
		
		contactInfoBox.add(Box.createRigidArea(new Dimension(10,0)));
		contactInfoBox.add(demographicContactinfoPromptLbl);//the component is added to the panel
		contactInfoBox.add(Box.createRigidArea(new Dimension(5,0)));
		contactInfoBox.add(demographiccontact1);//the component is added to the panel
		contactInfoBox.add(Box.createRigidArea(new Dimension(5,0)));
		contactInfoBox.add(demographiccontact2);//the component is added to the panel
		contactInfoBox.add(Box.createRigidArea(new Dimension(5,0)));
		contactInfoBox.add(demographiccontact3);//the component is added to the panel
		contactInfoBox.add(Box.createRigidArea(new Dimension(5,0)));
		contactInfoBox.add(demographiccontact4);//the component is added to the panel
		contactInfoBox.add(Box.createRigidArea(new Dimension(5,0)));
		contactInfoBox.add(demographiccontact5);//the component is added to the panel
		contactInfoBox.add(Box.createRigidArea(new Dimension(5,0)));
		contactInfoBox.add(demographiccontact6);//the component is added to the panel
		contactInfoBox.add(Box.createRigidArea(new Dimension(5,0)));
		contactInfoBox.add(demographiccontact7);//the component is added to the panel
		contactInfoBox.add(Box.createRigidArea(new Dimension(5,0)));
		contactInfoBox.add(demographiccontact8);//the component is added to the panel
		contactInfoBox.add(Box.createRigidArea(new Dimension(5,0)));
		contactInfoBox.add(demographiccontact9);//the component is added to the panel
		contactInfoBox.add(Box.createRigidArea(new Dimension(5,0)));
		contactInfoBox.add(demographiccontact10);//the component is added to the panel
		contactInfoBox.add(Box.createRigidArea(new Dimension(5,0)));
		contactInfoBox.add(demographiccontact11);//the component is added to the panel
		contactInfoBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		contactInfoBox.setLocation(450,715);//the location of the component is set to the desired part of the panel
		contactInfoBox.setSize(645,55);//size of component is set
		contactInfoBox.setBackground( new Color(-1) );
		contactInfoBox.setOpaque(true);
		contactInfoBox.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JLabel demographicBloodTypePromptLbl = new JLabel();//declares a label that is used for the bloodtype
		JComboBox<String> demographicBloodType = new JComboBox<String>(bloodTypes);//declares a combo box that holds the list of bloodtypes
		demographicBloodType.setMinimumSize(new Dimension(200,35));
		demographicBloodType.setPreferredSize(new Dimension(200,35));
		demographicBloodType.setMaximumSize(new Dimension(200,35));
		demographicBloodType.setOpaque(false);

		
		demographicBloodTypePromptLbl.setText("Blood type:");//formatting component to include text
		demographicBloodTypePromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicBloodTypePromptLbl.setMinimumSize(new Dimension(130,40));
		demographicBloodTypePromptLbl.setPreferredSize(new Dimension(130,40));
		demographicBloodTypePromptLbl.setMaximumSize(new Dimension(130,40));
		demographicBloodTypePromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose

		bloodTypeBox.add(Box.createRigidArea(new Dimension(5,0)));
		bloodTypeBox.add(demographicBloodTypePromptLbl);//the component is added to the panel
		bloodTypeBox.add(Box.createRigidArea(new Dimension(5,0)));
		bloodTypeBox.add(demographicBloodType);//the component is added to the panel
		bloodTypeBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		bloodTypeBox.setLocation(45,715);//the location of the component is set to the desired part of the panel
		bloodTypeBox.setSize(350,50);//size of component is set
		bloodTypeBox.setBackground( new Color(-1) );
		bloodTypeBox.setOpaque(true);
		bloodTypeBox.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JLabel demographicReligonPromptLbl = new JLabel();//declares a label that is used for the religon
		JTextField demographicReligonTextFeild = new JTextField();//declares the text feild that will hold the religon
		
		demographicReligonPromptLbl.setText("Religion:");//formatting component to include text
		demographicReligonPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicReligonPromptLbl.setMinimumSize(new Dimension(100,40));
		demographicReligonPromptLbl.setPreferredSize(new Dimension(100,40));
		demographicReligonPromptLbl.setMaximumSize(new Dimension(100,40));
		demographicReligonPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		
		demographicReligonTextFeild.setText("Please enter your Religion");//formatting component to include text
		demographicReligonTextFeild.setMinimumSize(new Dimension(240,35));
		demographicReligonTextFeild.setPreferredSize(new Dimension(240,35));
		demographicReligonTextFeild.setMaximumSize(new Dimension(240,35));
		demographicReligonTextFeild.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
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
		
		
		religonBox.add(Box.createRigidArea(new Dimension(5,0)));
		religonBox.add(demographicReligonPromptLbl);//the component is added to the panel
		religonBox.add(Box.createRigidArea(new Dimension(5,0)));
		religonBox.add(demographicReligonTextFeild);//the component is added to the panel
		religonBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		religonBox.setSize(410,55);//size of component is set
		religonBox.setLocation(600,230);//the location of the component is set to the desired part of the panel
		religonBox.setBackground( new Color(-1) );
		religonBox.setOpaque(true);
		religonBox.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JLabel demographicGenderPromptLbl = new JLabel();//declares a label that is used for the gender
		JComboBox<String> demographicGender = new JComboBox<String>(genders);//declares a combo box that holds the list of genders
		demographicGenderPromptLbl.setText("Gender:");//formatting component to include text
		demographicGenderPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicGenderPromptLbl.setMinimumSize(new Dimension(95,40));
		demographicGenderPromptLbl.setPreferredSize(new Dimension(95,40));
		demographicGenderPromptLbl.setMaximumSize(new Dimension(95,40));
		demographicGenderPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		
		demographicGender.setMinimumSize(new Dimension(270,25));
		demographicGender.setPreferredSize(new Dimension(270,25));
		demographicGender.setOpaque(false);
		demographicGender.setMaximumSize(new Dimension(270,25));
		
		genderBox.add(Box.createRigidArea(new Dimension(10,0)));
		genderBox.add(demographicGenderPromptLbl);//the component is added to the panel
		genderBox.add(Box.createRigidArea(new Dimension(5,0)));
		genderBox.add(demographicGender);//the component is added to the panel
		genderBox.setSize(390,55);//size of component is set
		genderBox.setLocation(600,70);//the location of the component is set to the desired part of the panel
		genderBox.setBackground( new Color(-1) );
		genderBox.setOpaque(true);
		genderBox.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JLabel demographicSexPromptLbl = new JLabel();//declares a label that is used for the gender
		JComboBox<String> demographicSex = new JComboBox<String>(sexs);//declares a combo box that holds the list of sexs
		
		demographicSexPromptLbl.setText("Sex:");//formatting component to include text
		demographicSexPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicSexPromptLbl.setMinimumSize(new Dimension(65,40));
		demographicSexPromptLbl.setPreferredSize(new Dimension(65,40));
		demographicSexPromptLbl.setMaximumSize(new Dimension(65,40));
		demographicSexPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		
		demographicSex.setMinimumSize(new Dimension(200,25));
		demographicSex.setPreferredSize(new Dimension(200,25));
		demographicSex.setMaximumSize(new Dimension(200,25));
		demographicSex.setOpaque(false);

		
		sexBox.add(Box.createRigidArea(new Dimension(10,0)));
		sexBox.add(demographicSexPromptLbl);//the component is added to the panel
		sexBox.add(Box.createRigidArea(new Dimension(5,0)));
		sexBox.add(demographicSex);//the component is added to the panel
		sexBox.setSize(360,55);//size of component is set
		sexBox.setLocation(600,145);//the location of the component is set to the desired part of the panel
		sexBox.setBackground( new Color(-1) );
		sexBox.setOpaque(true);
		sexBox.setBorder(BorderFactory.createLineBorder(Color.black));
		
	
		JLabel demographicDrinkerPromptLbl = new JLabel();//declares a label that is used for the gender
		demographicDrinkerPromptLbl.setText("Average units a week");//formatting component to include text
		demographicDrinkerPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicDrinkerPromptLbl.setMinimumSize(new Dimension(280,35));
		demographicDrinkerPromptLbl.setPreferredSize(new Dimension(280,35));
		demographicDrinkerPromptLbl.setMaximumSize(new Dimension(280,35));
		demographicDrinkerPromptLbl.setOpaque(false);//the component is made transparent
		demographicDrinkerPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		demographicDrinkerPromptLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

		JSlider drinkerSpinnerer = new JSlider(JSlider.HORIZONTAL,0, 30, 15);
		drinkerSpinnerer.addChangeListener(new ChangeListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void stateChanged(ChangeEvent e)
			{
				demographicDrinkerPromptLbl.setText("Average units a week: "+drinkerSpinnerer.getValue());//formatting component to include text and the size of units
			}
		});
		drinkerSpinnerer.setAlignmentX(Component.CENTER_ALIGNMENT);
		drinkerSpinnerer.setMajorTickSpacing(15);
		drinkerSpinnerer.setMinorTickSpacing(5);
		drinkerSpinnerer.setPaintTicks(true);
		drinkerSpinnerer.setPaintLabels(true);
		drinkerSpinnerer.setOpaque(false);

		drinkerBox.add(demographicDrinkerPromptLbl);//the component is added to the panel
		drinkerBox.add(drinkerSpinnerer);//the component is added to the panel
		drinkerBox.setSize(360,90);//size of component is set
		drinkerBox.setLocation(550,305);//the location of the component is set to the desired part of the panel
		drinkerBox.setBackground( new Color(-1) );
		drinkerBox.setOpaque(true);
		drinkerBox.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JLabel demographicSmokerPromptLbl = new JLabel();//declares a label that is used for the gender
		demographicSmokerPromptLbl.setText("Average cigarettes a week");//formatting component to include text
		demographicSmokerPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicSmokerPromptLbl.setMinimumSize(new Dimension(340,35));
		demographicSmokerPromptLbl.setPreferredSize(new Dimension(340,35));
		demographicSmokerPromptLbl.setMaximumSize(new Dimension(340,35));
		demographicSmokerPromptLbl.setOpaque(false);//the component is made transparent
		demographicSmokerPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		demographicSmokerPromptLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

		JSlider smokingSpinnerer = new JSlider(JSlider.HORIZONTAL,0, 25, 15);
		smokingSpinnerer.addChangeListener(new ChangeListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void stateChanged(ChangeEvent e)
			{
				demographicSmokerPromptLbl.setText("Average cigarettes a day: "+smokingSpinnerer.getValue());//formatting component to include text and the size of units
			}
		});
		smokingSpinnerer.setAlignmentX(Component.CENTER_ALIGNMENT);
		smokingSpinnerer.setMajorTickSpacing(5);
		smokingSpinnerer.setMinorTickSpacing(1);
		smokingSpinnerer.setPaintTicks(true);
		smokingSpinnerer.setPaintLabels(true);
		smokingSpinnerer.setOpaque(false);
		
		smokerBox.add(demographicSmokerPromptLbl);//the component is added to the panel
		smokerBox.add(smokingSpinnerer);//the component is added to the panel
		smokerBox.setSize(360,90);//size of component is set
		smokerBox.setLocation(705,415);//the location of the component is set to the desired part of the panel
		smokerBox.setBackground( new Color(-1) );
		smokerBox.setOpaque(true);
		smokerBox.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JLabel demographicAllergiesPromptLbl = new JLabel();//declares a label that is used for the allergies
		demographicAllergiesPromptLbl.setText("Allergies (Leave blank if none)");//formatting component to include text
		demographicAllergiesPromptLbl.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		demographicAllergiesPromptLbl.setMinimumSize(new Dimension(250,35));
		demographicAllergiesPromptLbl.setPreferredSize(new Dimension(250,35));
		demographicAllergiesPromptLbl.setMaximumSize(new Dimension(250,40));
		demographicAllergiesPromptLbl.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		demographicAllergiesPromptLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		JTextArea demographicAllergies = new JTextArea();//declares the text area that will hold the allergies
		demographicAllergies.setText("Please enter any Allergies You may \npossess");//formatting component to include text
		demographicAllergies.setMinimumSize(new Dimension(315,100));
		demographicAllergies.setPreferredSize(new Dimension(315,100));
		demographicAllergies.setMaximumSize(new Dimension(315,100));
		demographicAllergies.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
		demographicAllergies.setWrapStyleWord(true);
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
		demographicAllergies.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		allergiesBox.add(demographicAllergiesPromptLbl);//the component is added to the panel
		allergiesBox.add(demographicAllergies);//the component is added to the panel
		allergiesBox.setSize(360,160);//size of component is set
		allergiesBox.setLocation(705,530);//the location of the component is set to the desired part of the panel
		allergiesBox.setBackground( new Color(-1) );
		allergiesBox.setOpaque(true);
		allergiesBox.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JLabel demographicNotioanlityPromptLbl = new JLabel();//declares a label that is used for the nationality
		demographicNotioanlityPromptLbl.setText("Nationality:");//formatting component to include text
		demographicNotioanlityPromptLbl.setFont(headerFontFormatBlack);//the component has had its font set to a design with the correct size for its purpose
		demographicNotioanlityPromptLbl.setFont(headerFontFormatBlack.deriveFont(headerFontFormatBlack.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		
		JTextField demographicNotioanlityTextFeild = new JTextField();//declares the text feild that will hold the nationality
		demographicNotioanlityTextFeild.setText("Please enter your home country");//formatting component to include text
		demographicNotioanlityTextFeild.setMinimumSize(new Dimension(315,35));
		demographicNotioanlityTextFeild.setPreferredSize(new Dimension(315,35));
		demographicNotioanlityTextFeild.setMaximumSize(new Dimension(315,35));
		demographicNotioanlityTextFeild.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
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
		
		nationalityBox.add(Box.createRigidArea(new Dimension(5,0)));
		nationalityBox.add(demographicNotioanlityPromptLbl);//the component is added to the panel
		nationalityBox.add(Box.createRigidArea(new Dimension(5,0)));
		nationalityBox.add(demographicNotioanlityTextFeild);//the component is added to the panel
		nationalityBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		nationalityBox.setSize(450,55);//size of component is set
		nationalityBox.setLocation(940,335);//the location of the component is set to the desired part of the panelnationalityBox.setBackground( new Color(-1) );
		nationalityBox.setOpaque(true);
		nationalityBox.setBackground( new Color(-1) );
		nationalityBox.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
	JRadioButton demographicCarerPromptLbl = new JRadioButton();//declares a radio button that is used for the carer status
	JRadioButton demographicTranslatorPromptLbl = new JRadioButton();//declares a radio button that is used for the translator status
	JTextArea demographicDisablitiesTextFeild = new JTextArea();//declares the text area that will hold the disabilities
		
		
		demographicDisablitiesPromptLbl.setText("Disabilities(if none leave blank):");//formatting component to include text
		demographicDisablitiesPromptLbl.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		demographicDisablitiesPromptLbl.setMinimumSize(new Dimension(400,40));
		demographicDisablitiesPromptLbl.setPreferredSize(new Dimension(400,40));
		demographicDisablitiesPromptLbl.setMaximumSize(new Dimension(400,40));
		demographicDisablitiesPromptLbl.setOpaque(false);//the component is made transparent
		demographicDisablitiesPromptLbl.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		demographicDisablitiesPromptLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		demographicDisablitiesPromptLbl.addActionListener(this);
		
		demographicDisablitiesTextFeild.setMinimumSize(new Dimension(315,150));
		demographicDisablitiesTextFeild.setPreferredSize(new Dimension(315,150));
		demographicDisablitiesTextFeild.setMaximumSize(new Dimension(315,150));
		demographicDisablitiesTextFeild.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
		demographicDisablitiesTextFeild.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		demographicDisablitiesTextFeild.setForeground(importantRedColor);//the component has its font colour changed to a desireable one
		demographicDisablitiesTextFeild.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components
		demographicDisablitiesTextFeild.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		demographicCarerPromptLbl.setText("Need a carer:");//formatting component to include text
		demographicCarerPromptLbl.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		demographicCarerPromptLbl.setMinimumSize(new Dimension(250,40));
		demographicCarerPromptLbl.setPreferredSize(new Dimension(250,40));
		demographicCarerPromptLbl.setMaximumSize(new Dimension(250,40));
		demographicCarerPromptLbl.setOpaque(false);//the component is made transparent
		demographicCarerPromptLbl.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		demographicCarerPromptLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		demographicTranslatorPromptLbl.setText("Need a Translator:");//formatting component to include text
		demographicTranslatorPromptLbl.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		demographicTranslatorPromptLbl.setOpaque(false);//the component is made transparent
		demographicTranslatorPromptLbl.setMinimumSize(new Dimension(250,40));
		demographicTranslatorPromptLbl.setPreferredSize(new Dimension(250,40));
		demographicTranslatorPromptLbl.setMaximumSize(new Dimension(250,40));
		demographicTranslatorPromptLbl.setFont(symptomfont.deriveFont(symptomfont.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		demographicTranslatorPromptLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
	
		disabilitiesBox.add(demographicDisablitiesPromptLbl);//the component is added to the panel
		disabilitiesBox.add(demographicDisablitiesTextFeild);//the component is added to the panel
		disabilitiesBox.add(demographicCarerPromptLbl);//the component is added to the panel
		disabilitiesBox.add(demographicTranslatorPromptLbl);//the component is added to the panel
		
		disabilitiesBox.setSize(350,300);//size of component is set
		disabilitiesBox.setLocation(1080,405);//the location of the component is set to the desired part of the panelnationalityBox.setBackground( new Color(-1) );
		disabilitiesBox.setOpaque(true);
		disabilitiesBox.setBackground( new Color(-1) );
		disabilitiesBox.setBorder(BorderFactory.createLineBorder(Color.black));
	
		saveDemographicChanges.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		saveDemographicChanges.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		saveDemographicChanges.setSize(200,55);//size of component is set
		saveDemographicChanges.setText("Save Changes");//formatting component to include text
		saveDemographicChanges.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		saveDemographicChanges.setLocation(1230,810);//the location of the component is set to the desired part of the panel
		demographicHomepagePanel.add(saveDemographicChanges);	

		JLabel greyBoxLbl = new JLabel();//declares a labbel for the main header on the login screen 
		greyBoxLbl.setSize(1474,80);//the components size is correctly declared
		greyBoxLbl.setBackground(darkGreyColour);//the background colour of the component is declared to the desired value 
		greyBoxLbl.setOpaque(true);//the background colour of the component is declared to the desired value 
		greyBoxLbl.setLocation(0,803);//sets the location of the component 
		demographicHomepagePanel.add(greyBoxLbl);//the component is added to the panel
		
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//================= DOCUMENT  ==================

	//======== Entity based section =========
	public void createDocumentInstance()
	{
		Document newDocument = new Document();
		String validatedText = textDocumentTAND.getText();
		boolean validated = newDocument.validateDocumentText(validatedText);
		if(validated==false)
		{
			return;
		}
		userPatient = userPatient = userPatient.createNewNotification(userPatient,"Consultant "+systemAdmissionList[currentAdmissionIndex].consultantID+" for admission "+systemAdmissionList[currentAdmissionIndex].admissionID+" has created a new document for you");

		//ID
		String stringNum = systemAdmissionList[currentAdmissionIndex].numberOfDocuments+1+"";
		while(stringNum.length()< 7)
		{//termination condition when satisified 
			stringNum ="0"+stringNum;//adds a zero untill there are 7 zero's
		}
		newDocument.documentID = "D"+ userPatient.surName.substring(0,3).toUpperCase()+stringNum;
		
		//dateOfCreation
		newDocument.dateOfDocumentCreation = new Date();//declares attribute which holds thier dateAdmissionCreated
		
		//Type
		if(documentType ==1)//discharge
		{
			newDocument.docType=("Discharge Document");//formatting component to include text
				if(systemAdmissionList[currentAdmissionIndex].active==true)
				{
					dischargeAdmission();
					systemAdmissionList[currentAdmissionIndex].active=false;
					//createContactBarAdmission();
				}
		}
		
		if(documentType ==2)//reinstate
		{
			newDocument.docType=("Reinstatement Document");//formatting component to include text
			
			
			if(systemAdmissionList[currentAdmissionIndex].active==false)
				{
					
					reinstateAdmission();
					systemAdmissionList[currentAdmissionIndex].active=true;
					//createContactBarAdmission();

				}
		
			
		}
		
		if(documentType ==3)//perscription
		{
			newDocument.docType=("Prescription Document");//formatting component to include text
		}
		
		if(documentType ==4)//notes
		{
			newDocument.docType=("Consultant Notes");//formatting component to include text
		}
		
		if(documentType ==5)//test results
		{
			newDocument.docType=("Test Reults Document");//formatting component to include text
		}
		
		newDocument.text=validatedText;
		newDocument.summary="";
		newDocument.text = newDocument.text.replace("\n", "#");//replaces \n with # prevent new lines from being created
		newDocument.numberOfPages = 0;
		newDocument.legacyDocument = false;
		newDocument.filePath = " ";
		
		
		//creates document
		userPatient.listOfAdmissions[currentAdmissionIndex]=newDocument.createNewDocument(newDocument,systemAdmissionList[currentAdmissionIndex],userPatient.patientID);
		//new action
		userPatient.createNewAction("Created new Document ("+newDocument.docType+")",employeeIDGlobal,userPatient.patientID,systemAdmissionList[currentAdmissionIndex].admissionID,newDocument.text,"N/A");
		//returns user home
		
		//need to replace the # with (new line) before it is returned
		systemAdmissionList[currentAdmissionIndex].listOfDocuments[systemAdmissionList[currentAdmissionIndex].numberOfDocuments-1].text = systemAdmissionList[currentAdmissionIndex].listOfDocuments[systemAdmissionList[currentAdmissionIndex].numberOfDocuments-1].text.replace("#", "\n");//replaces \n with # prevent new lines from being created
		createadmissionHomepagePanelGUI();//the method containing all the new components is ran
		
		//sets active admission
		setActiveAdmissionPanelBttn(buttonList[currentAdmissionIndex]);
	}

	//legacy document
	//as long as the system is concerned this is techanciaclly a normal docuemtn just with an extra few fields becasue of this it can be passed intothe fiel writting method for documetns
	public void createLegacyDocument()
	{
		boolean validated = true;
		Document newDocument = new Document();
		//we are not actually going to use this file path, we are just checking it is valid
		newDocument.filePath =	actualFilePathTF.getText();
		
		//dateOfCreation
		try
		{
			LocalDate localDateData = datePicker12.getDate();
			Date date = Date.from(localDateData.atStartOfDay(ZoneId.systemDefault()).toInstant());
			newDocument.dateOfDocumentCreation =date;
		}
		catch(Exception exc){JOptionPane.showMessageDialog(null, "Please enter a correct date");return;}//if any errrors are found they are caught here		
		
		//DOC TYPE
		newDocument.docType=typeOflegacyDocumentTF.getText();
		
		newDocument.text=legacyinputTextArea.getText();
		
		newDocument.text = newDocument.text.replace("\n", "#");//replaces \n with # prevent new lines from being created
		
		////System.out.println(newDocument.text);
		validated = newDocument.validateLegacyDocument(newDocument);
		if(validated==true)
		{
			userPatient = userPatient = userPatient.createNewNotification(userPatient,"The staff user "+userPatient.linkedStaffID+" has retrived a legacy document for you Concerning admission "+ systemAdmissionList[currentAdmissionIndex].admissionID+". The date it was created was the "+ft.format(newDocument.dateOfDocumentCreation)+".");
			//we can intiate values that dont need validtion in here
			//ID
			String stringNum = systemAdmissionList[currentAdmissionIndex].numberOfDocuments+1+"";
			while(stringNum.length()< 7)
			{//termination condition when satisified 
				stringNum ="0"+stringNum;//adds a zero untill there are 7 zero's
			}
			newDocument.documentID = "D"+ userPatient.surName.substring(0,3).toUpperCase()+stringNum;
			
			newDocument.summary="Legacy Document From old system";
			newDocument.numberOfPages = 0;
			newDocument.legacyDocument = true;
		
			//True file path
			//this section here we are retireving the patients new document by copying the file instnace over
			String filename ="";
			//File newfile = new File(actualFilePathTF.getText());
			try {
				
				File inputFile = new File(actualFilePathTF.getText());//gets the tempfile where the currentImage is 
				BufferedImage bi = ImageIO.read(inputFile);//converts it to an image
				filename = userPatient.patientID+"_"+systemAdmissionList[currentAdmissionIndex].admissionID+"_"+(systemAdmissionList[currentAdmissionIndex].numberOfDocuments+1)+".jpg";
				File outputfile = new File(System.getProperty("user.dir")+File.separator+"LegacyPatientDocs"+"\\"+filename);
				ImageIO.write(bi, "jpg", outputfile);
				//System.out.println("New image written");
			}
			catch (IOException exc)
			{
				//System.out.println("image writing error");
			}
			
			newDocument.filePath = filename;
			userPatient.listOfAdmissions[currentAdmissionIndex]=newDocument.createNewDocument(newDocument,systemAdmissionList[currentAdmissionIndex],userPatient.patientID);
			userPatient.createNewAction("Created Legacy Document ("+newDocument.docType+")",employeeIDGlobal,userPatient.patientID,systemAdmissionList[currentAdmissionIndex].admissionID,newDocument.text,"N/A");
			createadmissionHomepagePanelGUI();//the method containing all the new components is ran
			setActiveAdmissionPanelBttn(buttonList[currentAdmissionIndex]);
		}
	}
	
	//===== graphical aspect ======
	//inital call methof for the panel this will load and add the panel to the frame and will perform
	//any other actions such as adding data/updateing it if requeired
	public void createDocumentPanel(Document currentDocument)
	{
		createTopbar(documentPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 

		addPanel(documentPanel);
		setNonActiveTopAdmissionPanel();//calls method which sets no topbar button as active
		createMainPartdocumentPanel(documentPanel);//creates main parts for the document panel
		createTopbarAdmission(documentPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		createSpecificDocument();
		documentPanel.add(documentOuterBox);
		if(loaded[10][0]== false)//selection determines whether the panel is yet to be loaded
		{
			createMainPartdocumentPanelGeneral();//calls method which creates main parts to the document panel
			loaded[10][0]= true;//the variable is set as true to prevent the components from being reran
		}
		documentPanel.setVisible(false);//panel is set to be invisable
		documentPanel.setVisible(true);//panel is set to be visable
		documentOuterBox.setLocation(457,105);//the location of the component is set to the desired part of the panel

		setDocumentData(currentDocument);
	}
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createMainPartdocumentPanel(JPanel panel)
	{
		
	}
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createMainPartdocumentPanelGeneral()
	{
		//System.out.println("buttons made");	
		backToAdmissionsBttn.setLocation(25,110);//the location of the component is set to the desired part of the panel
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
		
		priorDocumentBttn.setLocation(25,800);//the location of the component is set to the desired part of the panel
		priorDocumentBttn.setSize(250,45);//size of component is set
		priorDocumentBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		priorDocumentBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		priorDocumentBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		priorDocumentBttn.setText("Prior Document");//formatting component to include text
		priorDocumentBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		documentPanel.add(priorDocumentBttn);//component is added to the panel
		
		
		}
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	public void createSpecificDocument()
	{
		priorDocumentBttn.setEnabled(true);
		nextDocumentBttn.setEnabled(true);
		if(currentDocumentIndex ==0)
		{
			priorDocumentBttn.setEnabled(false);
		}
		if(currentDocumentIndex ==	systemAdmissionList[currentAdmissionIndex].numberOfDocuments-1)
		{
			nextDocumentBttn.setEnabled(false);
		}
		setDocumentData(systemAdmissionList[currentAdmissionIndex].listOfDocuments[currentDocumentIndex]);
	}
	
	//new Document panel
	//inital call methof for the panel this will load and add the panel to the frame and will perform
	//any other actions such as adding data/updateing it if requeired
	public void createNewDocumentPage()
	{
		createTopbar(createNewDocumentPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		addPanel(createNewDocumentPanel);
		createTopbarAdmission(createNewDocumentPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		
		if(loaded[5][1]== false)//selection determines whether the panel is yet to be loaded
			{
				createNewDocumentPageMainPartGeneral();
			}
		documentOuterBox.setLocation(607,105);//the location of the component is set to the desired part of the panel
		createNewDocumentPanel.add(documentOuterBox);		
		createNewDocumentButton.setEnabled(false);
		if(systemAdmissionList[currentAdmissionIndex].active==true)
		{
			docTypeDischargmentBttn.setEnabled(true);
			docTypeReinstateBttn.setEnabled(false);
		}
		else{
			docTypeDischargmentBttn.setEnabled(false);
			docTypeReinstateBttn.setEnabled(true);
		}
		createNewDocumentPanel.add(darkGreyBackgroung);//the component is added to the panel
	}
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createNewDocumentPageMainPartGeneral()
	{
		
		createNewDocumentPanel.add(returnBackToadmissionPage);//the component is added to the panel
		returnBackToadmissionPage.setText("Return back");//formatting component to include text
		returnBackToadmissionPage.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		returnBackToadmissionPage.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		returnBackToadmissionPage.setSize(200,45);//size of component is set
		returnBackToadmissionPage.setLocation(360,120);//the location of the component is set to the desired part of the panel
		returnBackToadmissionPage.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		
		createNewDocumentPanel.add(createNewDocumentButton);//the component is added to the panel
		createNewDocumentButton.setText("Create Document");//formatting component to include text
		createNewDocumentButton.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		createNewDocumentButton.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		createNewDocumentButton.setSize(200,45);//size of component is set
		createNewDocumentButton.setLocation(1200,805);//the location of the component is set to the desired part of the panel
		createNewDocumentButton.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		
		createNewDocumentPanel.add(docTypeDischargmentBttn);//the component is added to the panel
		docTypeDischargmentBttn.setText("Dischargment Document");//formatting component to include text
		docTypeDischargmentBttn.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		docTypeDischargmentBttn.setBackground(new Color(-1));//the component has its background set to a desireable colour
		docTypeDischargmentBttn.setForeground(new Color(1));//the component has its font colour changed to a desireable one
		docTypeDischargmentBttn.setSize(330,60);//size of component is set
		docTypeDischargmentBttn.setLocation(0,120);//the location of the component is set to the desired part of the panel
		docTypeDischargmentBttn.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				setDocumentData(1);

			}
		});
		
		createNewDocumentPanel.add(docTypeTestResultsBttn);//the component is added to the panel
		docTypeTestResultsBttn.setText("Test results Document");//formatting component to include text
		docTypeTestResultsBttn.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		docTypeTestResultsBttn.setBackground(new Color(-1));//the component has its background set to a desireable colour
		docTypeTestResultsBttn.setSize(330,60);//size of component is set
		docTypeTestResultsBttn.setLocation(0,180);//the location of the component is set to the desired part of the panel
		docTypeTestResultsBttn.setForeground(new Color(1));//the component has its font colour changed to a desireable one
		docTypeTestResultsBttn.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				setDocumentData(5);

			}
		});
		
		createNewDocumentPanel.add(docTypeNotesBttn);//the component is added to the panel
		docTypeNotesBttn.setText("Notes Document");//formatting component to include text
		docTypeNotesBttn.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		docTypeNotesBttn.setBackground(new Color(-1));//the component has its background set to a desireable colour
		docTypeNotesBttn.setSize(330,60);//size of component is set
		docTypeNotesBttn.setLocation(0,240);//the location of the component is set to the desired part of the panel
		docTypeNotesBttn.setForeground(new Color(1));//the component has its font colour changed to a desireable one
		docTypeNotesBttn.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				setDocumentData(4);
			}
		});
		
		createNewDocumentPanel.add(docTypePrescriptionBttn);//the component is added to the panel
		docTypePrescriptionBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		docTypePrescriptionBttn.setText("Prescription Document");//formatting component to include text
		docTypePrescriptionBttn.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		docTypePrescriptionBttn.setBackground(new Color(-1));//the component has its background set to a desireable colour
		docTypePrescriptionBttn.setSize(330,60);//size of component is set
		docTypePrescriptionBttn.setLocation(0,300);//the location of the component is set to the desired part of the panel
		docTypePrescriptionBttn.setForeground(new Color(1));//the component has its font colour changed to a desireable one
		docTypePrescriptionBttn.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				setDocumentData(3);
			}
		});	
		
		createNewDocumentPanel.add(docTypeReinstateBttn);//the component is added to the panel
		docTypeReinstateBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		docTypeReinstateBttn.setText("Reinstatement Document");//formatting component to include text
		docTypeReinstateBttn.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		docTypeReinstateBttn.setBackground(new Color(-1));//the component has its background set to a desireable colour
		docTypeReinstateBttn.setSize(330,60);//size of component is set
		docTypeReinstateBttn.setLocation(0,360);//the location of the component is set to the desired part of the panel
		docTypeReinstateBttn.setForeground(new Color(1));//the component has its font colour changed to a desireable one
		docTypeReinstateBttn.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				setDocumentData(2);//reinstate
			}
		});	
		
		darkGreyBackgroung.setSize(340,800);//the components size is correctly declared
		darkGreyBackgroung.setBackground(darkGreyColour);//the background colour of the component is declared to the desired value 
		darkGreyBackgroung.setOpaque(true);//the background colour of the component is declared to the desired value 
		darkGreyBackgroung.setLocation(0,60);//sets the location of the component 

		
	}		
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	public void createDocument()
	{
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);//the attributes of the font are changed to have the fount contain an underlined part to it
		
		//Blue highlisht for the system
		documentOuterBox.setSize(560,770);//size of component is set
		documentOuterBox.setLocation(457,105);//the location of the component is set to the desired part of the panel
		documentOuterBox.setOpaque(true);
		documentOuterBox.setBackground(selectedBttcColour);
		
		document.setAlignmentX(Component.CENTER_ALIGNMENT);

		document.setMinimumSize(new Dimension(530,750));
		document.setPreferredSize(new Dimension(530,750));
		document.setMaximumSize(new Dimension(530,750));
		document.setOpaque(true);
		document.setBorder(BorderFactory.createLineBorder(Color.black));
		document.setBackground(new Color(-1));
		documentOuterBox.add(Box.createRigidArea(new Dimension(0,10)));
		documentOuterBox.add(document);
		
		topPartsPanel.setMinimumSize(new Dimension(530,170));
		topPartsPanel.setPreferredSize(new Dimension(530,170));
		topPartsPanel.setMaximumSize(new Dimension(530,170));
		topPartsPanel.setOpaque(false);	
		
		document.add(topPartsPanel);
		
	
		patientIDDocumentLBL.setSize(250,30);//size of component is set
		admissionIDDocumentLBL.setSize(250,30);//size of component is set
		documentIDDocumentLBL.setSize(175,30);//size of component is set
		titleDocumentLBL.setSize(300,45);//size of component is set
		dateDocumentLBL.setSize(100,30);//size of component is set
		timeDocumentLBL.setSize(70,30);//size of component is set
		authorDocumentLBL.setSize(175,30);//size of component is set
		titleDocumentLBL.setFont(buttonFontFormatu.deriveFont(attributes));//the newly applied attributes of the font are applied to the component
		
		patientIDDocumentLBL.setBorder(BorderFactory.createLineBorder(Color.black));
		admissionIDDocumentLBL.setBorder(BorderFactory.createLineBorder(Color.black));
		documentIDDocumentLBL.setBorder(BorderFactory.createLineBorder(Color.black));
		dateDocumentLBL.setBorder(BorderFactory.createLineBorder(Color.black));
		timeDocumentLBL.setBorder(BorderFactory.createLineBorder(Color.black));
		authorDocumentLBL.setBorder(BorderFactory.createLineBorder(Color.black));
		
		topPartsPanel.add(documentIDDocumentLBL);//the component is added to the panel
		topPartsPanel.add(timeDocumentLBL);//the component is added to the panel
		topPartsPanel.add(dateDocumentLBL);//the component is added to the panel
		topPartsPanel.add(titleDocumentLBL);//the component is added to the panel
		topPartsPanel.add(admissionIDDocumentLBL);//the component is added to the panel
		topPartsPanel.add(patientIDDocumentLBL);//the component is added to the panel
		topPartsPanel.add(authorDocumentLBL);//the component is added to the panel
		
		patientIDDocumentLBL.setLocation(5,10);//the location of the component is set to the desired part of the panel
		admissionIDDocumentLBL.setLocation(5,50);//the location of the component is set to the desired part of the panel
		documentIDDocumentLBL.setLocation(335,90);//the location of the component is set to the desired part of the panel
		titleDocumentLBL.setLocation(5,95);//the location of the component is set to the desired part of the panel
		dateDocumentLBL.setLocation(400,10);//the location of the component is set to the desired part of the panel
		timeDocumentLBL.setLocation(440,50);//the location of the component is set to the desired part of the panel
		authorDocumentLBL.setLocation(335,130);//the location of the component is set to the desired part of the panel

		textAreaPanel.setMinimumSize(new Dimension(530,630));
		textAreaPanel.setPreferredSize(new Dimension(530,630));
		textAreaPanel.setMaximumSize(new Dimension(530,630));
		
		textAreaPanel.setOpaque(false);	


		textDocumentTAND.setSize(500,430);
		textDocumentTAND.setLocation(15,0);
		textDocumentTAND.setBorder(BorderFactory.createLineBorder(Color.black));
		textAreaPanel.add(textDocumentTAND);
		textDocumentTAND.setFont(symptomfont);
		textDocumentTAND.setLineWrap(true);
		textDocumentTAND.setWrapStyleWord(true);
		
		document.add(textAreaPanel);
		
		Box dateOflegacyDocumentCreationBox = new Box(BoxLayout.LINE_AXIS);
		JLabel notesInoLbl = new JLabel();
		documentPanel.add(legacyDocumentBox);
		legacyDocumentBox.add(legacyDocumentPanel);
		legacyDocumentPanel.add(legacyTextArea);
		legacyDocumentPanel.add(dateOflegacyDocumentCreationBox);
		legacyDocumentPanel.add(notesInoLbl);
		legacyDocumentPanel.add(documentOuterBoxLegacy);
		documentOuterBoxLegacy.add(Box.createRigidArea(new Dimension(10,5)));
		documentOuterBoxLegacy.add(DocumentBoxlbl);
		
		//createParts for legacy document
		documentOuterBoxLegacy.setLocation(355,0);//the location of the component is set to the desired part of the panel
		documentOuterBoxLegacy.setOpaque(true);
		documentOuterBoxLegacy.setBackground(selectedBttcColour);
		documentOuterBoxLegacy.setSize(524,720);//size of component is set
		
		legacyDocumentBox.setLocation(275,136);
		legacyDocumentBox.setSize(902,719);
				//legacyDocumentBox.setBorder(BorderFactory.createLineBorder(Color.black));

		legacyDocumentPanel.setMinimumSize(new Dimension(899,718));
		legacyDocumentPanel.setPreferredSize(new Dimension(899,718));
		legacyDocumentPanel.setMaximumSize(new Dimension(899,718));
			
			//legacyDocumentPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel docLegacyLBL = new JLabel();
		docLegacyLBL.setText("Creation date:");
		docLegacyLBL.setSize(300,50);
		docLegacyLBL.setFont(symptomfont);

		dateOfCreationLegacyLbl.setSize(160,50);
		docLegacyLBL.setFont(symptomfont);
		//dateOfCreationLegacyLbl.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		dateOflegacyDocumentCreationBox.add(Box.createRigidArea(new Dimension(10,0)));
		dateOflegacyDocumentCreationBox.add(docLegacyLBL);//the component is added to the panel
		dateOflegacyDocumentCreationBox.add(Box.createRigidArea(new Dimension(5,0)));
		dateOflegacyDocumentCreationBox.add(dateOfCreationLegacyLbl);//the component is added to the panel
		dateOflegacyDocumentCreationBox.setSize(300,50);//size of component is set
		dateOflegacyDocumentCreationBox.setLocation(20,50);//the location of the component is set to the desired part of the panel
		dateOflegacyDocumentCreationBox.setBackground( new Color(-1) );
		dateOflegacyDocumentCreationBox.setOpaque(true);
		dateOflegacyDocumentCreationBox.setBorder(BorderFactory.createLineBorder(Color.black));

		
		notesInoLbl.setText("Additional notes:");
		notesInoLbl.setSize(300,50);
		notesInoLbl.setFont(symptomfont);
		notesInoLbl.setLocation(20,120);
		
		legacyTextArea.setSize(300,500);
		legacyTextArea.setLocation(20,180);
		legacyTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
		legacyTextArea.setFont(symptomfont);
		legacyTextArea.setEditable(false);
		legacyTextArea.setLineWrap(true);
		legacyTextArea.setWrapStyleWord(true);
		//legacyTextArea
		DocumentBoxlbl.setBorder(BorderFactory.createLineBorder(Color.black));
		DocumentBoxlbl.setMinimumSize(new Dimension(504,693));
		DocumentBoxlbl.setPreferredSize(new Dimension(504,693));
		DocumentBoxlbl.setMaximumSize(new Dimension(504,693));
		//dateOfCreationLegacyLbl
		
		
	}
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	public void setDocumentData(int type)
	{
		document.setVisible(true);
		createNewDocumentButton.setEnabled(true);
		textDocumentTAND.setEditable(true);
		if(panelOrder[endPanelPointer]==createNewDocumentPanel)
		{
			signOFF=("\n\nYours sincerely\nWritten and typed by\nMr. "+userConsultant.firstName.substring(0,1)+" "+userConsultant.surName+"\nConsultant General Surgeon");
			if(type ==1)//discharge
			{
				titleDocumentLBL.setText("Discharge Document");//formatting component to include text
				textDocumentTAND.setText("Due to sufficient evidence provided, "+userConsultant.consultantID+" believes that the ailment of "+systemAdmissionList[currentAdmissionIndex].currentDiagnosis+" affecting the patient has been believed to be resolved. As of today and the writing of this document Dr "+userConsultant.surName+" has discharged this patient as of typing\nof this document.\nIf a recurrence in any prior symptoms re-surge the patient is more than welcome to have the \nadmission reinstated."+signOFF);//formatting component to include text
				documentType =1;
			}
			
			if(type ==2)//reinstate
			{
				titleDocumentLBL.setText("Reinstatement Document");//formatting component to include text
				textDocumentTAND.setText("Dr "+userConsultant.surName+" believes that the current patient "+userPatient.firstName+" "+userPatient.surName+" needs to have the Current admission "+systemAdmissionList[currentAdmissionIndex].admissionID+" reinstated. Therefore as of today the admission is to be treated as such."+signOFF);//sets text to the component
				documentType =2;
			}
			
			if(type ==3)//perscription
			{
				titleDocumentLBL.setText("Prescription Document");//formatting component to include text
				textDocumentTAND.setText("The consultant Dr "+userConsultant.surName+" has wished that the patient under care needs to receive a prescription. Because of this the following Prescription has been requested for the patient "+userPatient.patientID+"\n\nMedication:\n\nDosage:\n\nIntake Time:\n\nDate of next dispatch:"+signOFF);//formatting component to include text		}
				documentType =3;
			}
			
			if(type ==4)//notes
			{
				titleDocumentLBL.setText("Consultant Notes");//formatting component to include text
				textDocumentTAND.setText(""+signOFF);
				documentType =4;
			}
			
			if(type ==5)//test results
			{
				titleDocumentLBL.setText("Test Reults Document");//formatting component to include text
				textDocumentTAND.setText("Following recent tests the patient ("+userPatient.patientID+") has got the following test results:\n"+signOFF);//formatting component to include text
				documentType =5;
			}
		}
		patientIDDocumentLBL.setText(userPatient.patientID);//formatting component to include text
		////System.out.println("Current admission position "+ currentAdmissionIndex);
		admissionIDDocumentLBL.setText(systemAdmissionList[currentAdmissionIndex].admissionID);//formatting component to include text
		
	}
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	public void setDocumentData(Document currentDocument)
	{
		textDocumentTAND.setEditable(false);
		if((panelOrder[endPanelPointer]==documentPanel)&&(currentDocument.legacyDocument == false))
		{
			legacyDocumentBox.setVisible(false);
			documentOuterBox.setVisible(true);
			documentIDDocumentLBL.setText(currentDocument.documentID);//formatting component to include text
			dateDocumentLBL.setText(ft.format(currentDocument.dateOfDocumentCreation));//formatting component to include text
			timeDocumentLBL.setText(timeft.format(currentDocument.dateOfDocumentCreation));//formatting component to include text
			authorDocumentLBL.setText("CNUR0000001");//formatting component to include text
			titleDocumentLBL.setText(currentDocument.docType);//formatting component to include text
			textDocumentTAND.setText(currentDocument.text);//formatting component to include text
		patientIDDocumentLBL.setText(userPatient.patientID);//formatting component to include text
		////System.out.println("Current admission position "+ currentAdmissionIndex);
		admissionIDDocumentLBL.setText(systemAdmissionList[currentAdmissionIndex].admissionID);//formatting component to include text
		
		}
		if((panelOrder[endPanelPointer]==documentPanel)&&(currentDocument.legacyDocument == true))
		{
			textDocumentTAND.setText(currentDocument.text);//formatting component to include text
			documentOuterBox.setVisible(false);
			legacyDocumentBox.setVisible(true);
			try{
			dateOfCreationLegacyLbl.setText(ft.format(currentDocument.dateOfDocumentCreation));
			//System.out.println(currentDocument.filePath);
			File inputFile = new File(currentDocument.filePath);//gets the tempfile where the currentImage is 
			BufferedImage bi = ImageIO.read(inputFile);//converts it to an image
			Image resizedImage = bi.getScaledInstance(504,693,Image.SCALE_SMOOTH);
			//createLegacyDocumentButton.setIcon(new ImageIcon(bi));//Test that the image is retrieved
			DocumentBoxlbl.repaint();
			DocumentBoxlbl.setIcon( new ImageIcon(resizedImage) );
			}
			catch(Exception exc){//System.out.println("Output Error");
			exc.printStackTrace();}
			legacyTextArea.setText(currentDocument.text);
		}
	}

	//legacy document panel
	//inital call methof for the panel this will load and add the panel to the frame and will perform
	//any other actions such as adding data/updateing it if requeired
	public void createAddLegacyDocumentPanel()
	{
		createTopbar(createOldDocumentPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		if(loaded[11][0]== false)//selection determines whether the panel is yet to be loaded
		{
			createOldDocumentPanelGeneral();//the components of the panel are cereated by running the method this is done initially so it is only done once and will be used throughout
			loaded[11][0]= true;//the variable is set as true to prevent the components from breing reran
		}
		addPanel(createOldDocumentPanel);
		actualFilePathTF.setText("");
		testLbl.setIcon(null);
		createTopbarAdmission(createOldDocumentPanel);
	}//convert old document panel
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createOldDocumentPanelGeneral()
	{	
		legacyFileChooser.setLocation(96,220);
		createOldDocumentPanel.add(legacyFileChooser);
		////System.out.println(System.getProperty("user.dir")+"filepath");
		legacyFileChooser.addChoosableFileFilter( new ImageFilter());
		legacyFileChooser.setAcceptAllFileFilterUsed(false);
		
		//absolute path box
		buttonSelectfilePathBttn.setSize(200,49);//the components size is correctly declared
		buttonSelectfilePathBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		buttonSelectfilePathBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		buttonSelectfilePathBttn.setText("Get FilePath");//the button is geven text to add meaning to the label
		createOldDocumentPanel.add(buttonSelectfilePathBttn);
			
		actualFilePathTF.setSize(500,49);//the components size is correctly declared
		actualFilePathTF.setFont(symptomfont);
		actualFilePathTF.getDocument().addDocumentListener(documentTfListner);
		actualFilePathTF.setEditable(false);
		legacyFilePathBox.add(Box.createRigidArea(new Dimension(10,0)));
		legacyFilePathBox.add(actualFilePathTF);//the component is added to the panel
		legacyFilePathBox.add(Box.createRigidArea(new Dimension(5,0)));
		legacyFilePathBox.add(buttonSelectfilePathBttn);//the component is added to the panel
		legacyFilePathBox.setSize(530,55);//size of component is set
		legacyFilePathBox.setLocation(45,220);//the location of the component is set to the desired part of the panel
		legacyFilePathBox.setBackground( new Color(-1) );
		legacyFilePathBox.setOpaque(true);
		legacyFilePathBox.setBorder(BorderFactory.createLineBorder(Color.black));
		createOldDocumentPanel.add(legacyFilePathBox);

		//date picker

		dateOfLegacySettings.setAllowKeyboardEditing(false);//prevents custom dates being entered
		dateOfLegacySettings.setFontValidDate(symptomfont);
		dateOfLegacySettings.setSizeDatePanelMinimumWidth(300);
		dateOfLegacySettings.setSizeDatePanelMinimumHeight(250);

		LocalDate today = LocalDate.now();
		dateOfLegacySettings.setDateRangeLimits(today.minusYears(120), today.minusDays(1));
		createOldDocumentPanel.add(datePicker12);
		datePicker12.setLocation(45,320);//the location of the component is set to the desired part of the panel
		datePicker12.setSize(330,55);//size of component is set
		datePicker12.getComponentToggleCalendarButton().setMinimumSize(new Dimension(100,40));
		datePicker12.getComponentToggleCalendarButton().setPreferredSize(new Dimension(100,40));
		datePicker12.getComponentToggleCalendarButton().setMaximumSize(new Dimension(100,40));
		datePicker12.getComponentToggleCalendarButton().setText("View Dates");
		datePicker12.getComponentToggleCalendarButton().setForeground( new Color(-1) );//the foreground of the component is given a white font
		datePicker12.getComponentToggleCalendarButton().setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		datePicker12.getComponentToggleCalendarButton().setFont(symptomfont);//the font that has been declared is attached to the component 


		//type of document
		typeOfDocumentLegacyLbl.setSize(150,49);//the components size is correctly declared
		typeOfDocumentLegacyLbl.setFont(symptomfont);
		typeOfDocumentLegacyLbl.setText("Type of document");
		
		typeOflegacyDocumentTF.setText("Please enter The type of document");//formatting component to include text
		typeOflegacyDocumentTF.setMinimumSize(new Dimension(350,40));
		typeOflegacyDocumentTF.setPreferredSize(new Dimension(350,40));
		typeOflegacyDocumentTF.setMaximumSize(new Dimension(350,40));
		typeOflegacyDocumentTF.setForeground(darkGreyColour);
		typeOflegacyDocumentTF.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		typeOflegacyDocumentTF.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(typeOflegacyDocumentTF.getText().equals("Please enter The type of document"))//selection checking if the text field contains the text prompt
				{
					typeOflegacyDocumentTF.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(typeOflegacyDocumentTF.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					typeOflegacyDocumentTF.setText("Please enter The type of document");//if satisifed then the prompt text is set again
				}
			}
		});
		
		typeOflegacyDocumentBox.add(Box.createRigidArea(new Dimension(10,0)));
		typeOflegacyDocumentBox.add(typeOfDocumentLegacyLbl);//the component is added to the panel
		typeOflegacyDocumentBox.add(Box.createRigidArea(new Dimension(5,0)));
		typeOflegacyDocumentBox.add(typeOflegacyDocumentTF);//the component is added to the panel
		typeOflegacyDocumentBox.setSize(530,55);//size of component is set
		typeOflegacyDocumentBox.setLocation(45,420);//the location of the component is set to the desired part of the panel
		typeOflegacyDocumentBox.setBackground( new Color(-1) );
		typeOflegacyDocumentBox.setOpaque(true);
		typeOflegacyDocumentBox.setBorder(BorderFactory.createLineBorder(Color.black));
		createOldDocumentPanel.add(typeOflegacyDocumentBox);

		JLabel infoLabelLegacyLbl = new JLabel();
		infoLabelLegacyLbl.setText("Additional information:");
		infoLabelLegacyLbl.setSize(300,50);
		infoLabelLegacyLbl.setLocation(45,500);
		infoLabelLegacyLbl.setFont(symptomfont);
		createOldDocumentPanel.add(infoLabelLegacyLbl);

		legacyinputTextArea.setSize(700,250);
		legacyinputTextArea.setLocation(45,540);
		legacyinputTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
		legacyinputTextArea.setFont(symptomfont);
		legacyinputTextArea.setLineWrap(true);
		legacyinputTextArea.setWrapStyleWord(true);
		createOldDocumentPanel.add(legacyinputTextArea);

		returnBackToAdmissionbttn.setLocation(96,120);//sets the location of the component 
		returnBackToAdmissionbttn.setSize(200,49);//the components size is correctly declared
		returnBackToAdmissionbttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		returnBackToAdmissionbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		returnBackToAdmissionbttn.setText("Go Back");//the button is geven text to add meaning to the label
		createOldDocumentPanel.add(returnBackToAdmissionbttn);
		
		createLegacyDocumentButton.setLocation(600,800);//sets the location of the component 
		createLegacyDocumentButton.setSize(200,49);//the components size is correctly declared
		createLegacyDocumentButton.setForeground( new Color(-1) );//the foreground of the component is given a white font
		createLegacyDocumentButton.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		createLegacyDocumentButton.setText("Create Legacy Document");//the button is geven text to add meaning to the label
		createOldDocumentPanel.add(createLegacyDocumentButton);
	}












//================= CONSULTANT ========================
	
	//======== Entity based section =========
	
	//Will call a method which returns all the correct attributes of the desried instanec
	//It will then call the window starting the actual entity specific system
	public void setUpConsultantObj(String consultantID)
	{
		//here we try and pull the instance of the user
		//if it fails to pull the instnace it will only be down to the fact the ID is invlaid on the system
		//Respectfully the system will retrun a error statement to the user
		try
		{
			userConsultant = userConsultant.retrieveConsultant(consultantID);
		}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(null, "Username does not exist.");
			return;
		}
		boolean loggedIn =checkCredentials(userConsultant.password,String.valueOf(loginPasswordF.getPassword()));
		if(loggedIn==true)
		{
			
			if(userConsultant.archived==true)
			{
				logout();
				JOptionPane.showMessageDialog(null, "Account is currently archived.");
			}
			if(userConsultant.archived==false)
			{
				//all checks have come through user has access
				employeeIDGlobal = userConsultant.employeeID;
				createConsultantHomepagePanelGUI();			
			
			}
		}
		
	}
		
	// UPDATE CONSULTANT
	//similar to patient all the graphical components get their data pulled from them(If not ammend should be OG data)
	//These attributes overwrite the old data, which is then passed through to a method which writes new data to the consultants file
	public void updateConsultant()
	{
		Consultant tempConsultant = new Consultant();
		Component firstNameTf=fNameBox.getComponent(3);//inner
		Component surnameNameTf=sNameBox.getComponent(3);//inner
		Component addressName=addressBuildingBox.getComponent(3);//inner
		Component addressStreet=addressStreetBox.getComponent(3);//inner
		Component addressTown=addressTownBox.getComponent(3);//inner
		Component addressCounty=addressCountyBox.getComponent(3);//inner
		Component bloodTypedrop=bloodTypeBox.getComponent(3);//inner
		Component genderDrop=genderBox.getComponent(3);//inner
		Component sexDropDown=sexBox.getComponent(3);//inner
		Component religon=religonBox.getComponent(3);//inner
		Component drinkingSlider=drinkerBox.getComponent(1);//inner
		Component smokingSlider=smokerBox.getComponent(1);//inner
		Component allergies=allergiesBox.getComponent(1);//inner
		Component nationality=nationalityBox.getComponent(3);//inner
		Component listOfDisabilities=disabilitiesBox.getComponent(1);//inner
		Component carerRadio=disabilitiesBox.getComponent(2);//inner
		Component translatorRadio=disabilitiesBox.getComponent(3);//inner
		
		//retireve attributes
		//names
		tempConsultant.firstName = ((JTextField) firstNameTf).getText();//retireives attribute from component
		tempConsultant.surName = ((JTextField) surnameNameTf).getText();//retireives attribute from component
		//gender
		tempConsultant.gender = ""+((JComboBox) genderDrop).getSelectedItem();//retireives attribute from components
		//sex
		tempConsultant.sex = ""+((JComboBox) sexDropDown).getSelectedItem();//retireives attribute from component
		//DOB
		try
		{
			//day
			Component dateBookingBox=dOBBox.getComponent(3);//inner
			LocalDate localDateData = ((DatePicker) dateBookingBox).getDate();
			if(localDateData==null)
			{
			JOptionPane.showMessageDialog(null, "Date missing please enter a date.");
			return;
			}
			
			Date date = Date.from(localDateData.atStartOfDay(ZoneId.systemDefault()).toInstant());
			tempConsultant.dob = date;
		}
		catch(Exception exc)//if any errrors are found they are caught here		
		{
			
		}
		//Address
	tempConsultant.addressHouseNum = Integer.parseInt(""+((JTextField) addressName).getText());//retireives attribute from component
	tempConsultant.addressHouseStreet = ((JTextField) addressStreet).getText();//retireives attribute from components
		try
		{
			Component addressPostcode1=addressPostcodeBox.getComponent(3);//inner
			Component addressPostcode2=addressPostcodeBox.getComponent(5);//inner
			Component addressPostcode3=addressPostcodeBox.getComponent(7);//inner
			Component addressPostcode4=addressPostcodeBox.getComponent(9);//inner
			Component addressPostcode5=addressPostcodeBox.getComponent(11);//inner
			Component addressPostcode6=addressPostcodeBox.getComponent(13);//inner
			Component addressPostcode7=addressPostcodeBox.getComponent(15);//inner
			String concatenatedPostcode =((JTextField) addressPostcode1).getText()+((JTextField) addressPostcode2).getText()+((JTextField) addressPostcode3).getText()+((JTextField) addressPostcode4).getText()+((JTextField) addressPostcode5).getText()+((JTextField) addressPostcode6).getText()+((JTextField) addressPostcode7).getText();
			tempConsultant.postcode = concatenatedPostcode;//retireives attribute from components
		}
		catch(Exception exc)//if any errrors are found they are caught here		
		{
			
		}
		tempConsultant.town = ((JTextField) addressTown).getText();//retireives attribute from component
		tempConsultant.county = ((JTextField) addressCounty).getText();//retireives attribute from component
	//contactNumber
		try
		{
			Component contactNumber1=contactInfoBox.getComponent(3);//inner
			Component contactNumber2=contactInfoBox.getComponent(5);//inner
			Component contactNumber3=contactInfoBox.getComponent(7);//inner
			Component contactNumber4=contactInfoBox.getComponent(9);//inner
			Component contactNumber5=contactInfoBox.getComponent(11);//inner
			Component contactNumber6=contactInfoBox.getComponent(13);//inner
			Component contactNumber7=contactInfoBox.getComponent(15);//inner
			Component contactNumber8=contactInfoBox.getComponent(17);//inner
			Component contactNumber9=contactInfoBox.getComponent(19);//inner
			Component contactNumber10=contactInfoBox.getComponent(21);//inner
			Component contactNumber11=contactInfoBox.getComponent(23);//inner
			String concatenatedContactnumber =((JTextField) contactNumber1).getText()+((JTextField) contactNumber2).getText()+((JTextField) contactNumber3).getText()+((JTextField) contactNumber4).getText()+((JTextField) contactNumber5).getText()+((JTextField) contactNumber6).getText()+((JTextField) contactNumber7).getText()+((JTextField) contactNumber8).getText()+((JTextField) contactNumber9).getText()+((JTextField) contactNumber10).getText()+((JTextField) contactNumber11).getText();
			tempConsultant.contactNum =concatenatedContactnumber;//retireives attribute from component
		}
		catch(Exception exc)//if any errrors are found they are caught here		
		{
			
		}
		
		//nationality
		tempConsultant.nationality = ((JTextField) nationality).getText();//retireives attribute from component
		//religon
		tempConsultant.religon = ((JTextField) religon).getText();//retireives attribute from component
		//bloodtype
		tempConsultant.bloodType =(""+((JComboBox) bloodTypedrop).getSelectedItem());//retireives attribute from component
		//allergies
		tempConsultant.allergies = ((JTextArea) allergies).getText();//retireives attribute from component
		if(tempConsultant.allergies.equals("Please enter any Allergies You may \npossess"))
		{
			tempConsultant.allergies = "None";
		}
		//Sliders
		tempConsultant.smoker = ((JSlider) smokingSlider).getValue();//retireives attribute from component
		tempConsultant.drinker = ((JSlider) drinkingSlider).getValue();//retireives attribute from component
		tempConsultant.disability = ((JTextArea) listOfDisabilities).getText();//retireives attribute from component
		if(tempConsultant.disability.equals(""))
		{
			tempConsultant.disability = "None";
		}
		tempConsultant.carer = ((JRadioButton) carerRadio).isSelected();//retireives attribute from component
		tempConsultant.translator = ((JRadioButton) translatorRadio).isSelected();//retireives attribute from component
		if(tempConsultant.validateconsultantInput(tempConsultant)==true)
		{
			userConsultant.firstName = tempConsultant.firstName;
			userConsultant.surName =tempConsultant.surName;
			userConsultant.gender =tempConsultant.gender;
			userConsultant.sex =tempConsultant.sex;
			userConsultant.dob =tempConsultant.dob;
			userConsultant.addressHouseNum =tempConsultant.addressHouseNum;
			userConsultant.addressHouseStreet =tempConsultant.addressHouseStreet;
			userConsultant.postcode = tempConsultant.postcode;
			userConsultant.town = tempConsultant.town;
			userConsultant.county = tempConsultant.county;
			userConsultant.contactNum =tempConsultant.contactNum;
			userConsultant.nationality =tempConsultant.nationality;
			userConsultant.religon = tempConsultant.religon;
			userConsultant.bloodType =tempConsultant.bloodType;;
			userConsultant.allergies =tempConsultant.allergies;
			userConsultant.smoker =tempConsultant.smoker;
			userConsultant.drinker = tempConsultant.drinker;
			userConsultant.disability = tempConsultant.disability;
			userConsultant.carer = tempConsultant.carer;
			userConsultant.translator = tempConsultant.translator;
			
		//calls method which writes new details to file
		userConsultant.updateDemographicDetails(userConsultant);
		}
	}
	
	//NO CREATION METHOD NEEDED FOR CONSULTANT AS IT IS ALL TEXT BASED FOR THIS ENTITY, method found in management class


	//===== graphical aspect ======
	
	//inital call methof for the panel this will load and add the panel to the frame and will perform
	//any other actions such as adding data/updateing it if requeired
	public void createConsultantHomepagePanelGUI()//once the login button has been pressed the login page will be opened up and then the homepage will be created and displayed
	{
		createTopbar(consultantHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		createContactBarUser(consultantHomepagePanel,contactBox);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		
		if(loaded[5][0]== false)//selection determines whether the panel is yet to be loaded
		{
			createMainPartConsultnantHomePageGeneral();//the components of the panel are cereated by running the method this is done initially so it is only done once and will be used throughout
			loaded[5][0]= true;//the variable is set as true to prevent the components from breing reran
		}
		emptyArray(consultantHomepagePanel);
		admissionPatientbttn.setEnabled(false);
		patientDemographicbttn.setEnabled(false);	
		createConsultantPatientpanel(listOfsortedconsultantPatientIndexes);
		userConsultant=userConsultant.retrieveBookingsFromAllPatients(userConsultant);
		createConsultantAppointmentpanel();
	}
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createMainPartConsultnantHomePageGeneral()
	{
		consultantHomepagePanel.add(searchBoxOuterConsultant);
		searchBoxOuterConsultant.setSize(975,437);
		searchBoxOuterConsultant.setLocation(400,105);
		searchBoxOuterConsultant.add(searchBox);
		searchBoxOuterConsultant.add(scrollConsultant);
		searchBoxOuterConsultant.setBorder(BorderFactory.createLineBorder(Color.black));
		searchBox.setBorder(BorderFactory.createLineBorder(Color.black));
	
		scrollConsultant.setMinimumSize(new Dimension(975,385));
		scrollConsultant.setPreferredSize(new Dimension(975,385));
		scrollConsultant.setMaximumSize(new Dimension(975,385));
		scrollConsultant.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollConsultant.setVisible(true);
		scrollConsultant.setBackground(darkButtonGrey);
		scrollConsultant.getHorizontalScrollBar().setUnitIncrement(16);
		
		addToJargonBttn.setText("Add Glossary");
		addToJargonBttn.setSize(180,49);
		addToJargonBttn.setLocation(1100,40);
		
		//welcome label
		JLabel homelablel = new JLabel();//declares a labbel for the main header on the login screen 
		homelablel.setFont(headerFont);//the font that has been declared is attached to the component
		homelablel.setSize(850,40);//the components size is correctly declared
		homelablel.setText("Home");//the label is geven text to add meaning to the label
		homelablel.setForeground(Color.decode("#004e63"));//the foreground of the component is given a white font
		homelablel.setLocation(1310,45);//sets the location of the component 
		consultantHomepagePanel.add(homelablel);//the component is added to the panel

		
		consultantHomepagePanel.add(addToJargonBttn);
		
		addToJargonBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		addToJargonBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		addToJargonBttn.setFont(admissionCardFont);//the font that has been declared is attached to the component 
	
		
		createConsultantAppointmentpanel();
		consultantHomepagePanel.add(scrollConsultantAppointments);
		scrollConsultantAppointments.setSize(1000,280);
		scrollConsultantAppointments.setLocation(400,550);
		scrollConsultantAppointments.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollConsultantAppointments.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollConsultantAppointments.setVisible(true);
		scrollConsultantAppointments.setBackground(darkButtonGrey);
		scrollConsultantAppointments.getVerticalScrollBar().setUnitIncrement(16);
		
		
	
	}
	
	//panel/selecting patients
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	public void createConsultantPatientpanel(int[] listOfpatientindexes)
	{
		searchBoxOuterConsultant.removeAll();
		searchBoxOuterConsultant.add(searchBox);
		searchBoxOuterConsultant.add(scrollConsultant);
		currentAdmissionIndex=0;
		mainBoxConsultant.removeAll();
		int numberOfPatientsInArray=-1;
		boolean orginalList = false;
		if(listOfpatientindexes.length==0)
		{
			orginalList= true;
			//sortComboBox.setSelectedItem("Ascending");//Set option by text
			numberOfPatientsInArray = userConsultant.numberOfPatients;
		}
		if(listOfpatientindexes.length!=0)
		{
			numberOfPatientsInArray=listOfpatientindexes.length;
			//System.out.println("Short search");
		}
		int numOfPanels;
		int currentNumOfPatients = 0;
		int numberOfPatientsOutPutted = 3;
		int numOfLeftOverpatients=numberOfPatientsInArray%3;
		if(userConsultant.numberOfPatients==0)
		{
			JLabel noPatients = new JLabel();
			noPatients.setFont(whiteLoginFont);
			noPatients.setText("You have no patients assigned to the account");
			mainBoxConsultant.add(noPatients);
			mainBoxConsultant.setOpaque(true);
			mainBoxConsultant.setBackground( new Color(-1));
		}
		if(numOfLeftOverpatients!=0)
		{
			numOfPanels = (numberOfPatientsInArray/3)+1;
		}
		else
		{
			numOfPanels =  (numberOfPatientsInArray/3);
		}
		Patient tempPatient = new Patient();
		buttonFontFormatunderlined.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);//the attributes of the font are changed to have the fount contain an underlined part to it

		for (int i = 0; i <numOfPanels; ++i)
		{
			Box largeBox = new Box(BoxLayout.Y_AXIS);
			largeBox.putClientProperty("box order",String.valueOf(i));
			largeBox.setBackground(darkGreyColour);
			largeBox.setOpaque(true);
			
			largeBox.setMinimumSize(new Dimension(975,380));
			largeBox.setPreferredSize(new Dimension(975,380));
			largeBox.setMaximumSize(new Dimension(975,380));
			
			
			largeBox.setBorder(BorderFactory.createLineBorder(Color.black));
			for (int k = 0; k <1; ++k)
			{	
				Box mediumBox = new Box(BoxLayout.LINE_AXIS);
				mediumBox.putClientProperty("box order",String.valueOf(i));
				mediumBox.setMinimumSize(new Dimension(975,380));
				mediumBox.setPreferredSize(new Dimension(975,380));
				mediumBox.setMaximumSize(new Dimension(975,380));
			
				if((i==numOfPanels-1)&&(k==0)&&(numOfLeftOverpatients!=0))
				{
					numberOfPatientsOutPutted = numOfLeftOverpatients;
					////System.out.print("Hello Esrr");
					
				}
				
				mediumBox.add(Box.createRigidArea(new Dimension(30,0)));
				for (int j = 0; j <numberOfPatientsOutPutted; ++j)
				{

					Box SmallDocumentBox = new Box(BoxLayout.Y_AXIS);
					JPanel documentpanel = new JPanel(null);
					JLabel  admissioncardTitlepane	= new JLabel();//Declares the text pane for the title of the admission card
					JLabel  patientIConLBL	= new JLabel();//Declares the text pane for the title of the admission card
					JLabel  firstnameLBL	= new JLabel();//Declares the text pane for the title of the admission card
					JLabel  surnamenameLBL	= new JLabel();//Declares the text pane for the title of the admission card
					JLabel  dobLBL	= new JLabel();//Declares the text pane for the title of the admission card
					JLabel  genderLBL	= new JLabel();//Declares the text pane for the title of the admission card
					JLabel  numberOfAdmissionslbl	= new JLabel();//Declares the text pane for the title of the admission card
					JLabel  postcodelbl	= new JLabel();//Declares the text pane for the title of the admission card
					JButton viewDocumentAdmissionBttn = new JButton();//Declares the button for the view document of the admission card
					
					SmallDocumentBox.setOpaque(true);//the component is set to opaque
					SmallDocumentBox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
					SmallDocumentBox.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
					SmallDocumentBox.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value
					SmallDocumentBox.putClientProperty("Box order",String.valueOf(currentNumOfPatients));
					SmallDocumentBox.setMinimumSize(new Dimension(250,325));
					SmallDocumentBox.setPreferredSize(new Dimension(250,325));
					SmallDocumentBox.setMaximumSize(new Dimension(250,325));
					
					documentpanel.setMinimumSize(new Dimension(250,325));
					documentpanel.setPreferredSize(new Dimension(250,325));
					documentpanel.setMaximumSize(new Dimension(250,325));
					documentpanel.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
					documentpanel.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value
					
					admissioncardTitlepane.setSize(240,60);
					admissioncardTitlepane.setLocation(10,85);
					admissioncardTitlepane.setFont(buttonFontFormatun.deriveFont(buttonFontFormatunderlined));
				
					firstnameLBL.setSize(240,40);
					firstnameLBL.setLocation(90,40);
					firstnameLBL.setFont(whiteLoginFont);
					
					surnamenameLBL.setSize(240,40);
					surnamenameLBL.setLocation(90,10);
					surnamenameLBL.setFont(whiteLoginFont);
					
					dobLBL.setSize(240,40);
					dobLBL.setLocation(10,140);
					dobLBL.setFont(consultantCardFont);
					
					genderLBL.setSize(240,40);
					genderLBL.setLocation(10,170);
					genderLBL.setFont(consultantCardFont);
					
					numberOfAdmissionslbl.setSize(240,40);
					numberOfAdmissionslbl.setLocation(10,230);
					numberOfAdmissionslbl.setFont(symptomfont);
					
					postcodelbl.setSize(240,40);
					postcodelbl.setLocation(10,200);
					postcodelbl.setFont(whiteLoginFont);
					
					patientIConLBL.setSize(66,77);
					patientIConLBL.setLocation(10,10);
					patientIConLBL.setIcon( new ImageIcon("personIconConsultantP1.png") );//the label is set to an image
					patientIConLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
					
					
					viewDocumentAdmissionBttn.setSize(210,30);
					viewDocumentAdmissionBttn.setLocation(20,280);
					viewDocumentAdmissionBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
					viewDocumentAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
					viewDocumentAdmissionBttn.setFont(admissionCardFont);//the font that has been declared is attached to the component 
					viewDocumentAdmissionBttn.setText("View Patient");
					viewDocumentAdmissionBttn.addActionListener(new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent e)
						{
							JButton source = (JButton)e.getSource();
							String buttonID = (String) source.getClientProperty("button order");
							currentPatientIndex =Integer.parseInt(buttonID);
							////System.out.print(buttonID);
							userPatient = userPatient.retrievePatient(userConsultant.consultantPatientList[currentPatientIndex]);//FINDS THE PATIENT INDEX NEEDED TO PULL FROM FILE
							//pulls the admissionID's from the file
							String[] listOfAdmissionsIDS = userConsultant.concatenatedconsultantPatientList[currentPatientIndex].split("#");//READS THE ASSOCIATED ARRAY WITH ALL THE VALUES AND SPLITS THE ADMISISON IDS TO AN ARRAY
							systemAdmissionList = userConsultant.pullAdmissions(listOfAdmissionsIDS,userPatient);//USING THE ids THE ADMISSION STRINGS GET PULLED AND THE ACTUALOBJECTS GET CREATED
							//outputCurrentAdmissionList();
							createadmissionHomepagePanelGUI();
							if(systemAdmissionList.length>0)//selection determining if the patient has at least one admission
							{
								setActiveAdmissionPanelBttn(buttonList[0]);
								if(systemAdmissionList[currentAdmissionIndex].requested==true)
								{
									popUpreinstateAdmission(userPatient,systemAdmissionList[currentAdmissionIndex]);
								}	
							}						
							else{
								createBlankAdmissionHomePage();
							}
							}
					});
					
					
					
					//here we are determining whether a button list is either a sorted/searched one or the original list if the normal one it will just assign the index of the current one representitive of the current instances
					//if it is a searched/sorted one it will use the global list of sorted indexs(which will hold the true index of the instance in the array and assign that instead of the current iteration)
					//eg if sort retruned the int array of 7 3 6 2 each button would be assigned the correct index along with setting the cards text appropriately instead of being 1 2 3 4
					int indexOfPatientToRetrieve = -1;
					if(orginalList== true)
					{
						//System.out.println("WE HAVE OG LIST");
						viewDocumentAdmissionBttn.putClientProperty("button order",String.valueOf(currentNumOfPatients));
						indexOfPatientToRetrieve = currentNumOfPatients;
						
					}
					else
					{
						viewDocumentAdmissionBttn.putClientProperty("button order",String.valueOf(listOfpatientindexes[currentNumOfPatients]));
						indexOfPatientToRetrieve = listOfpatientindexes[currentNumOfPatients];
					}
					
					tempPatient = tempPatient.retreivePatientBasicInfo(userConsultant.consultantPatientList[indexOfPatientToRetrieve]);
					admissioncardTitlepane.setText(tempPatient.patientID);	
					surnamenameLBL.setText(tempPatient.surName);	
					dobLBL.setText(ft.format(tempPatient.dob));	
					genderLBL.setText(tempPatient.gender);	
					numberOfAdmissionslbl.setText("Number of Admissions: "+tempPatient.numberOfAdmissions);	
					postcodelbl.setText(tempPatient.postcode.substring(0,3)+" "+tempPatient.postcode.substring(4,7));	
					firstnameLBL.setText(tempPatient.firstName);	

					//adds the components to the correct location
					documentpanel.add(admissioncardTitlepane);
					documentpanel.add(patientIConLBL);
					documentpanel.add(firstnameLBL);
					documentpanel.add(surnamenameLBL);
					documentpanel.add(numberOfAdmissionslbl);
					documentpanel.add(genderLBL);
					documentpanel.add(postcodelbl);
					documentpanel.add(dobLBL);
					documentpanel.add(viewDocumentAdmissionBttn);
					SmallDocumentBox.add(documentpanel);
					mediumBox.add(SmallDocumentBox);
					mediumBox.add(Box.createRigidArea(new Dimension(80,0)));
					
					currentNumOfPatients++;
				}
				
				largeBox.add(mediumBox);
				mediumBox.setAlignmentY(Component.TOP_ALIGNMENT);
			}
			mainBoxConsultant.add(largeBox);
			mainBoxConsultant.setVisible(false);
		mainBoxConsultant.setVisible(true);
		}
	}
	
	//appointment scrollbox
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	public void createConsultantAppointmentpanel()
	{
		mainBoxConsultantAppointments.removeAll();
		for (int i = 0; i <20; ++i)
		{
			Box dateBox = new Box(BoxLayout.LINE_AXIS);
			dateBox.setBorder(BorderFactory.createLineBorder(Color.black));
			JTextArea description = new JTextArea();
			description.setEditable(false);
			dateBox.putClientProperty("Available",i);
			dateBox.setOpaque(true);
			JLabel time = new JLabel();
			double decinex = ((0.5*i)+8);
			if(decinex%1==0)
			{
				time.setText(Integer.parseInt((decinex+"").substring(0,(decinex+"").length()-2))+":00");
			}
			else if(decinex%1!=0)
			{
				time.setText((Integer.parseInt((((decinex-0.5)+"").substring(0,(decinex+"").length()-2))))+":30");
			}
			time.setFont(buttonFontFormat);
			time.setMinimumSize(new Dimension(100,65));
			time.setPreferredSize(new Dimension(100,65));
			time.setMaximumSize(new Dimension(100,65));
			
			dateBox.add(Box.createRigidArea(new Dimension(15,0)));
			dateBox.add(time);
			dateBox.add(description);
			//formatting 
			
			
			//available
			if(userConsultant.todaysAppointments[i]==null)
			{
				dateBox.setBackground(availableGreen);
				description.setBackground(availableGreen);
				description.setForeground( new Color(1) );//the foreground of the component is given a white font
				description.setFont(consultantCardFont);//the font that has been declared is attached to the component 
				description.setText("Available");
			}
			//booked
			else if(userConsultant.todaysAppointments[i]!=null)
			{
				JButton viewAppointmentsConsultntButton = new JButton();//declares a button to allow the user to create an account of that type
				viewAppointmentsConsultntButton.putClientProperty("buttonProp",String.valueOf(i));
				viewAppointmentsConsultntButton.setMinimumSize(new Dimension(240,65));
				viewAppointmentsConsultntButton.setPreferredSize(new Dimension(240,65));
				viewAppointmentsConsultntButton.setMaximumSize(new Dimension(240,65));
				viewAppointmentsConsultntButton.setForeground( new Color(-1) );//the foreground of the component is given a white font
				viewAppointmentsConsultntButton.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
				viewAppointmentsConsultntButton.setFont(admissionCardFont);//the font that has been declared is attached to the component 
				viewAppointmentsConsultntButton.setText("View appointment");
				viewAppointmentsConsultntButton.addActionListener(new java.awt.event.ActionListener()
				{
					public void actionPerformed(java.awt.event.ActionEvent e)
					{
						JButton source = (JButton)e.getSource();
						String id = (String) source.getClientProperty("buttonProp");
						int bookingindex = Integer.parseInt(id);
						currentBooking = userConsultant.todaysAppointments[bookingindex];
						createViewAppointmentPanelGUI(currentBooking);
						appointmentcreateBookingBttn.setVisible(false);
					}
				});	
				dateBox.setBackground(lightGreyColor);
				
				dateBox.add(viewAppointmentsConsultntButton);
			}
			mainBoxConsultantAppointments.add(dateBox);
			
			
	}
	}
	
	//this method is used to update the admission list for the GUI aspects of the system for the consultant 
	//if updates the consultant 
	//then it pulls the list of their admissions
	//assgins them to the global list
	public void updateConsultantAdmissionList()
	{
		userConsultant = userConsultant.retrieveConsultant(userConsultant.consultantID);
		if(userConsultant.concatenatedconsultantPatientList.length>0)
		{
			String[] listOfAdmissionsIDS = userConsultant.concatenatedconsultantPatientList[currentPatientIndex].split("#");//READS THE ASSOCIATED ARRAY WITH ALL THE VALUES AND SPLITS THE ADMISISON IDS TO AN ARRAY
			systemAdmissionList = userConsultant.pullAdmissions(listOfAdmissionsIDS,userPatient);//USING THE ids THE ADMISSION STRINGS GET PULLED AND THE ACTUALOBJECTS GET CREATED
		}
					
	}
	//here this method will check that the admission the patient has on the staff account is discharged but the patient wants it reinstated
	//here a 3 choice popup will appear asking the consultant to select an option
	//with the options being yes wait and no
	//once the button has been pressed either the admission is updated, nothing happens or the admission remains discharged as before
	//the user is informed through the actions of notifications
	public void popUpreinstateAdmission(Patient patient,Admission admission)
	{	
		if(loginChoice==3)
		{
			Object[] options = {"Proceed (Reinstate Admission)","Wait","Decline (keep discharged)"};
			int optionAnswer = JOptionPane.showOptionDialog(frame,"Patient " +patient.patientID+ "has wished that admission"+admission.admissionID+" is to be reinstated.\nPlease select an opiton","Reinstate Admission?",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
			if(optionAnswer==0)
			{
				////System.out.println("0");
				createNewDocumentPage();
				setDocumentData(2);//reinstate
				admission.requested=false;
			}
			if(optionAnswer==1)
			{
				////System.out.println("1");
				patient.createNewNotification(patient,"Consultant desided to wait for the admission request");

			}
			if(optionAnswer==2)
			{
				////System.out.println("2");
				admission.requested=false;
			}
				
			AdmissionList al = new AdmissionList();	
			al.updateAdmission(patient,admission,12);
		}
	}
	










//==================== STAFF ==========================

	//======== Entity based section =========

	//method which initalises the staff entity by using their id To pull details from file
	//Once done the GUI is then pushed to the main entites homepage
	public void setUpStaffObj(String staffID)
	{
		//here we try and pull the instance of the user
		//if it fails to pull the instnace it will only be down to the fact the ID is invlaid on the system
		//Respectfully the system will retrun a error statement to the user
		try{
			userStaff = userStaff.retrieveStaff(staffID);
		}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(null, "Username does not exist.");
			return;
		}
		
		boolean loggedIn =checkCredentials(userStaff.password,String.valueOf(loginPasswordF.getPassword()));
		if(loggedIn==true)
		{
			if(userStaff.archived==true)
			{
				logout();
				JOptionPane.showMessageDialog(null, "Account is currently archived.");
			}
			if(userStaff.archived==false)
			{
				//all validation has come through user has access granted
				employeeIDGlobal = userStaff.employeeID;
				createStaffHomepagePanelGUI();
			}
		}
		
	}
	
	// UPDATE STAFF
	//Like for the patient this method will pull all the attributes from the Graphical page and then assignes them to the current Instance
	//The instacne is then passed through a method which overwirtes the details over old values
	public void updateStaff()
	{
		Staff tempBackupStaff = new Staff();
		Component firstNameTf=fNameBox.getComponent(3);//inner
		Component surnameNameTf=sNameBox.getComponent(3);//inner
		Component addressName=addressBuildingBox.getComponent(3);//inner
		Component addressStreet=addressStreetBox.getComponent(3);//inner
		Component addressTown=addressTownBox.getComponent(3);//inner
		Component addressCounty=addressCountyBox.getComponent(3);//inner
		Component bloodTypedrop=bloodTypeBox.getComponent(3);//inner
		Component genderDrop=genderBox.getComponent(3);//inner
		Component sexDropDown=sexBox.getComponent(3);//inner
		Component religon=religonBox.getComponent(3);//inner
		Component drinkingSlider=drinkerBox.getComponent(1);//inner
		Component smokingSlider=smokerBox.getComponent(1);//inner
		Component allergies=allergiesBox.getComponent(1);//inner
		Component nationality=nationalityBox.getComponent(3);//inner
		Component listOfDisabilities=disabilitiesBox.getComponent(1);//inner
		Component carerRadio=disabilitiesBox.getComponent(2);//inner
		Component translatorRadio=disabilitiesBox.getComponent(3);//inner
		
		//retireve attributes
		//names
		tempBackupStaff.firstName = ((JTextField) firstNameTf).getText();//retireives attribute from component
		tempBackupStaff.surName = ((JTextField) surnameNameTf).getText();//retireives attribute from component
		//gender
		tempBackupStaff.gender = ""+((JComboBox) genderDrop).getSelectedItem();//retireives attribute from components
		//sex
		tempBackupStaff.sex = ""+((JComboBox) sexDropDown).getSelectedItem();//retireives attribute from component
		//DOB
		try
		{
			//day
				Component dateBookingBox=dOBBox.getComponent(3);//inner
			LocalDate localDateData = ((DatePicker) dateBookingBox).getDate();
			if(localDateData==null)
			{
			JOptionPane.showMessageDialog(null, "Date missing please enter a date.");
			return;
			}
			
			Date date = Date.from(localDateData.atStartOfDay(ZoneId.systemDefault()).toInstant());
			tempBackupStaff.dob = date;
		}
		catch(Exception exc)//if any errrors are found they are caught here		
		{
			
		}
		//Address
		tempBackupStaff.addressHouseNum = Integer.parseInt(""+((JTextField) addressName).getText());//retireives attribute from component
		tempBackupStaff.addressHouseStreet = ((JTextField) addressStreet).getText();//retireives attribute from components
		try
		{
			Component addressPostcode1=addressPostcodeBox.getComponent(3);//inner
			Component addressPostcode2=addressPostcodeBox.getComponent(5);//inner
			Component addressPostcode3=addressPostcodeBox.getComponent(7);//inner
			Component addressPostcode4=addressPostcodeBox.getComponent(9);//inner
			Component addressPostcode5=addressPostcodeBox.getComponent(11);//inner
			Component addressPostcode6=addressPostcodeBox.getComponent(13);//inner
			Component addressPostcode7=addressPostcodeBox.getComponent(15);//inner
			String concatenatedPostcode =((JTextField) addressPostcode1).getText()+((JTextField) addressPostcode2).getText()+((JTextField) addressPostcode3).getText()+((JTextField) addressPostcode4).getText()+((JTextField) addressPostcode5).getText()+((JTextField) addressPostcode6).getText()+((JTextField) addressPostcode7).getText();
			tempBackupStaff.postcode = concatenatedPostcode;//retireives attribute from components
		}
		catch(Exception exc)//if any errrors are found they are caught here		
		{
			
		}
		tempBackupStaff.town = ((JTextField) addressTown).getText();//retireives attribute from component
		tempBackupStaff.county = ((JTextField) addressCounty).getText();//retireives attribute from component
	//contactNumber
		try
		{
			Component contactNumber1=contactInfoBox.getComponent(3);//inner
			Component contactNumber2=contactInfoBox.getComponent(5);//inner
			Component contactNumber3=contactInfoBox.getComponent(7);//inner
			Component contactNumber4=contactInfoBox.getComponent(9);//inner
			Component contactNumber5=contactInfoBox.getComponent(11);//inner
			Component contactNumber6=contactInfoBox.getComponent(13);//inner
			Component contactNumber7=contactInfoBox.getComponent(15);//inner
			Component contactNumber8=contactInfoBox.getComponent(17);//inner
			Component contactNumber9=contactInfoBox.getComponent(19);//inner
			Component contactNumber10=contactInfoBox.getComponent(21);//inner
			Component contactNumber11=contactInfoBox.getComponent(23);//inner
			String concatenatedContactnumber =((JTextField) contactNumber1).getText()+((JTextField) contactNumber2).getText()+((JTextField) contactNumber3).getText()+((JTextField) contactNumber4).getText()+((JTextField) contactNumber5).getText()+((JTextField) contactNumber6).getText()+((JTextField) contactNumber7).getText()+((JTextField) contactNumber8).getText()+((JTextField) contactNumber9).getText()+((JTextField) contactNumber10).getText()+((JTextField) contactNumber11).getText();
			tempBackupStaff.contactNum =concatenatedContactnumber;//retireives attribute from component
		}
		catch(Exception exc)//if any errrors are found they are caught here		
		{
			
		}
		
		//nationality
		tempBackupStaff.nationality = ((JTextField) nationality).getText();//retireives attribute from component
		//religon
		tempBackupStaff.religon = ((JTextField) religon).getText();//retireives attribute from component
		//bloodtype
		tempBackupStaff.bloodType =(""+((JComboBox) bloodTypedrop).getSelectedItem());//retireives attribute from component
		//allergies
		tempBackupStaff.allergies = ((JTextArea) allergies).getText();//retireives attribute from component
		if(tempBackupStaff.allergies.equals("Please enter any Allergies You may \npossess"))
		{
			tempBackupStaff.allergies = "None";
		}		
		//Sliders
		tempBackupStaff.smoker = ((JSlider) smokingSlider).getValue();//retireives attribute from component
		tempBackupStaff.drinker = ((JSlider) drinkingSlider).getValue();//retireives attribute from component
		tempBackupStaff.disability = ((JTextArea) listOfDisabilities).getText();//retireives attribute from component
		if(tempBackupStaff.disability.equals(""))
		{
			tempBackupStaff.disability = "None";
		}
		tempBackupStaff.carer = ((JRadioButton) carerRadio).isSelected();//retireives attribute from component
		tempBackupStaff.translator = ((JRadioButton) translatorRadio).isSelected();//retireives attribute from component
		if(tempBackupStaff.validateStaffInput(tempBackupStaff)==true)
		{
			userStaff.firstName = tempBackupStaff.firstName;
			userStaff.surName =tempBackupStaff.surName;
			userStaff.gender =tempBackupStaff.gender;
			userStaff.sex =tempBackupStaff.sex;
			userStaff.dob =tempBackupStaff.dob;
			userStaff.addressHouseNum =tempBackupStaff.addressHouseNum;
			userStaff.addressHouseStreet = tempBackupStaff.addressHouseStreet;
			userStaff.postcode = tempBackupStaff.postcode;
			userStaff.town = tempBackupStaff.town;
			userStaff.county = tempBackupStaff.county;
			userStaff.contactNum =tempBackupStaff.contactNum;
			userStaff.nationality =tempBackupStaff.nationality;
			userStaff.religon = tempBackupStaff.religon;
			userStaff.bloodType =tempBackupStaff.bloodType;;
			userStaff.allergies =tempBackupStaff.allergies;
			userStaff.smoker =tempBackupStaff.smoker;
			userStaff.drinker = tempBackupStaff.drinker;
			userStaff.disability = tempBackupStaff.disability;
			userStaff.carer = tempBackupStaff.carer;
			userStaff.translator = tempBackupStaff.translator;
			
			//calls method which writes new details to file
			userStaff.updateDemographicDetails(userStaff);
		}
	}
	
	//NO CREATION METHOD NEEDED FOR CONSULTANT AS IT IS ALL TEXT BASED FOR THIS ENTITY, method found in management class

	
	//===== graphical aspect ======
	
	//inital call methof for the panel this will load and add the panel to the frame and will perform
	//any other actions such as adding data/updateing it if requeired
	public void createStaffHomepagePanelGUI()//once the login button has been pressed the login page will be opened up and then the homepage will be created and displayed
	{
		createTopbar(staffHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		createContactBarUser(staffHomepagePanel,contactBox);
		if(loaded[4][0]== false)//selection determines whether the panel is yet to be loaded
		{
			createMainPartStaffHomePageGeneral();//the components of the panel are cereated by running the method this is done initially so it is only done once and will be used throughout
			loaded[4][0]= true;//the variable is set as true to prevent the components from breing reran
		}
		emptyArray(staffHomepagePanel);
		admissionPatientbttn.setEnabled(false);
		patientDemographicbttn.setEnabled(false);
		createStaffPatientpanel(listOfsortedstaffPatientIndexes);
	}
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createMainPartStaffHomePageGeneral()
	{
		
		
		
		createStaffPatientpanel(listOfsortedstaffPatientIndexes);//call this to update contents of the scroll box do not call the method which this is in
		//staffHomepagePanel.add(scrollStaff);
		scrollStaff.setBackground(darkButtonGrey);

		staffHomepagePanel.add(searchBoxOuterStaff);
		searchBoxOuterStaff.setSize(975,811);
		searchBoxOuterStaff.setLocation(400,60);
		searchBoxOuterStaff.add(searchBox);
		searchBoxOuterStaff.add(scrollStaff);
		searchBoxOuterStaff.setBorder(BorderFactory.createLineBorder(Color.black));
		searchBox.setBorder(BorderFactory.createLineBorder(Color.black));
		//scrollpane.setBorder(BorderFactory.createLineBorder(Color.black));

		
		scrollStaff.setMinimumSize(new Dimension(975,760));
			scrollStaff.setPreferredSize(new Dimension(975,760));
			scrollStaff.setMaximumSize(new Dimension(975,760));
		scrollStaff.setVisible(true);
		scrollStaff.setBackground(darkButtonGrey);
		scrollStaff.getHorizontalScrollBar().setUnitIncrement(16);
		 scrollStaff.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

	}
	//panel
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	public void createStaffPatientpanel(int[] listOfpatientindexes)
	{
		searchBoxOuterStaff.removeAll();
		searchBoxOuterStaff.add(searchBox);
		searchBoxOuterStaff.add(scrollStaff);
		mainBoxStaffPatients.removeAll();
		int numberOfPatientsInArray=-1;
		boolean orginalList = false;
		if(listOfpatientindexes.length==0)
		{
			orginalList= true;
			//sortComboBox.setSelectedItem("Ascending");//Set option by text
			numberOfPatientsInArray = userStaff.numberOfPatients;
		}
		if(listOfpatientindexes.length!=0)
		{
			numberOfPatientsInArray=listOfpatientindexes.length;
			//System.out.println("Short search");
		}
		int numOfPanels;
		int currentNumOfPatients = 0;
		int numberOfMinorPanels = 2;
		int numberOfPatientsOutPutted = 3;
		int numOfLeftOverPatients=numberOfPatientsInArray%6;
		if(numOfLeftOverPatients!=0)
		{
			numOfPanels = (numberOfPatientsInArray/6)+1;
		}
		else
		{
			numOfPanels =  (numberOfPatientsInArray/6);
			////System.out.println("HELLOOOO "+numOfPanels);
		}
		Patient tempPatient = new Patient();
		buttonFontFormatunderlined.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);//the attributes of the font are changed to have the fount contain an underlined part to it

		for (int i = 0; i <numOfPanels; ++i)
		{
			Box largeBox = new Box(BoxLayout.Y_AXIS);
			largeBox.putClientProperty("box order",String.valueOf(i));
			largeBox.setBackground(darkGreyColour);
			largeBox.setOpaque(true);
			largeBox.setMinimumSize(new Dimension(975,740));
			largeBox.setPreferredSize(new Dimension(975,740));
			largeBox.setMaximumSize(new Dimension(975,740));
			largeBox.setBorder(BorderFactory.createLineBorder(Color.black));
			if((i==numOfPanels-1)&&(numOfLeftOverPatients!=0))
			{
				if(numOfLeftOverPatients>3)
				{
					numberOfMinorPanels = 2;
				}
				else
				{
					numberOfMinorPanels = 1;
				}
			}
			for (int k = 0; k <numberOfMinorPanels; ++k)
			{	
				//System.out.println("HERE");
				Box mediumBox = new Box(BoxLayout.LINE_AXIS);
				mediumBox.putClientProperty("box order",String.valueOf(i));
				mediumBox.setMinimumSize(new Dimension(975,370));
				mediumBox.setPreferredSize(new Dimension(975,370));
				mediumBox.setMaximumSize(new Dimension(975,370));
			
				if((i==numOfPanels-1)&&(k==numberOfMinorPanels-1)&&(k==0)&&(numOfLeftOverPatients!=0))
				{
					numberOfPatientsOutPutted = numOfLeftOverPatients;
					//System.out.print("Hello Esrr");
					
				}
				if((i==numOfPanels-1)&&(k==numberOfMinorPanels-1)&&(k==1)&&(numOfLeftOverPatients!=0))
				{
					numberOfPatientsOutPutted = numOfLeftOverPatients-3;
					
						//System.out.print("Hello dfdsf");
				}
				mediumBox.add(Box.createRigidArea(new Dimension(30,0)));
				for (int j = 0; j <numberOfPatientsOutPutted; ++j)
				{
					//declares the components
					Box SmallDocumentBox = new Box(BoxLayout.Y_AXIS);
					JPanel documentpanel = new JPanel(null);
					JLabel  admissioncardTitlepane	= new JLabel();//Declares the text pane for the title of the admission card
					JLabel  patientIConLBL	= new JLabel();//Declares the text pane for the title of the admission card
					JLabel  firstnameLBL	= new JLabel();//Declares the text pane for the title of the admission card
					JLabel  surnamenameLBL	= new JLabel();//Declares the text pane for the title of the admission card
					JLabel  dobLBL	= new JLabel();//Declares the text pane for the title of the admission card
					JLabel  genderLBL	= new JLabel();//Declares the text pane for the title of the admission card
					JLabel  numberOfAdmissionslbl	= new JLabel();//Declares the text pane for the title of the admission card
					JLabel  postcodelbl	= new JLabel();//Declares the text pane for the title of the admission card
					JButton viewDocumentAdmissionBttn = new JButton();//Declares the button for the view document of the admission card
					
					//formats all the components
					SmallDocumentBox.setOpaque(true);//the component is set to opaque
					SmallDocumentBox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
					SmallDocumentBox.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
					SmallDocumentBox.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value
					SmallDocumentBox.putClientProperty("Box order",String.valueOf(currentNumOfPatients));
					SmallDocumentBox.setMinimumSize(new Dimension(250,325));
					SmallDocumentBox.setPreferredSize(new Dimension(250,325));
					SmallDocumentBox.setMaximumSize(new Dimension(250,325));
					
					documentpanel.setMinimumSize(new Dimension(250,325));
					documentpanel.setPreferredSize(new Dimension(250,325));
					documentpanel.setMaximumSize(new Dimension(250,325));
					documentpanel.setForeground( new Color(-1) );//the foreground of the cmponent is given a white font
					documentpanel.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value
					
					admissioncardTitlepane.setSize(240,60);
					admissioncardTitlepane.setLocation(10,85);
					admissioncardTitlepane.setFont(buttonFontFormatun.deriveFont(buttonFontFormatunderlined));
				
					firstnameLBL.setSize(240,40);
					firstnameLBL.setLocation(90,40);
					firstnameLBL.setFont(whiteLoginFont);
					
					surnamenameLBL.setSize(240,40);
					surnamenameLBL.setLocation(90,10);
					surnamenameLBL.setFont(whiteLoginFont);
					
					dobLBL.setSize(240,40);
					dobLBL.setLocation(10,140);
					dobLBL.setFont(consultantCardFont);
					
					genderLBL.setSize(240,40);
					genderLBL.setLocation(10,170);
					genderLBL.setFont(consultantCardFont);
					
					numberOfAdmissionslbl.setSize(240,40);
					numberOfAdmissionslbl.setLocation(10,230);
					numberOfAdmissionslbl.setFont(symptomfont);
					
					postcodelbl.setSize(240,40);
					postcodelbl.setLocation(10,200);
					postcodelbl.setFont(whiteLoginFont);
					
					patientIConLBL.setSize(66,77);
					patientIConLBL.setLocation(10,10);
					patientIConLBL.setIcon( new ImageIcon("personIconConsultantP1.png") );//the label is set to an image
					patientIConLBL.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
					
					viewDocumentAdmissionBttn.setSize(210,30);
					viewDocumentAdmissionBttn.setLocation(20,280);
					viewDocumentAdmissionBttn.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
					viewDocumentAdmissionBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
					viewDocumentAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
					viewDocumentAdmissionBttn.setFont(admissionCardFont);//the font that has been declared is attached to the component 
					viewDocumentAdmissionBttn.setText("View Patient");
					viewDocumentAdmissionBttn.addActionListener(new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent e)
						{
							JButton source = (JButton)e.getSource();
							String buttonID = (String) source.getClientProperty("button order");
							currentPatientIndex =Integer.parseInt(buttonID);
							//retrieves the patient at the index
							userPatient = userPatient.retrievePatient(userStaff.patientIDs[currentPatientIndex]);//FINDS THE PATIENT INDEX NEEDED TO PULL FROM FILE
							systemAdmissionList = userPatient.listOfAdmissions;
							////System.out.println("Staff currentID "+userStaff.patientIDs[currentPatientIndex]);
							createadmissionHomepagePanelGUI();
							if(systemAdmissionList.length>0)//selection determining if the patient has at least one admission
							{
								setActiveAdmissionPanelBttn(buttonList[0]);
								if(loginChoice!=3)
								{
									userConsultant = userConsultant.retrieveConsultant(systemAdmissionList[0].consultantID);
								}
							}
							else{
								createBlankAdmissionHomePage();
							}
						}
					});
					
					
					//here we are determining whether a button list is either a sorted/searched one or the original list if the normal one it will just assign the index of the current one representitive of the current instances
					//if it is a searched/sorted one it will use the global list of sorted indexs(which will hold the true index of the instance in the array and assign that instead of the current iteration)
					//eg if sort retruned the int array of 7 3 6 2 each button would be assigned the correct index along with setting the cards text appropriately instead of being 1 2 3 4					
					//assings the values to the cards
					int indexOfPatientToRetrieve = -1;
					if(orginalList== true)
					{
						////System.out.println("WE HAVE OG LIST");
						viewDocumentAdmissionBttn.putClientProperty("button order",String.valueOf(currentNumOfPatients));
						indexOfPatientToRetrieve = currentNumOfPatients;
						
					}
					else
					{
						viewDocumentAdmissionBttn.putClientProperty("button order",String.valueOf(listOfpatientindexes[currentNumOfPatients]));
						indexOfPatientToRetrieve = listOfpatientindexes[currentNumOfPatients];
					}
					
					tempPatient = tempPatient.retreivePatientBasicInfo(userStaff.patientIDs[indexOfPatientToRetrieve]);
					admissioncardTitlepane.setText(tempPatient.patientID);	
					surnamenameLBL.setText(tempPatient.surName);	
					dobLBL.setText(ft.format(tempPatient.dob));	
					genderLBL.setText(tempPatient.gender);	
					numberOfAdmissionslbl.setText("Number of Admissions: "+tempPatient.numberOfAdmissions);	
					postcodelbl.setText(tempPatient.postcode.substring(0,3)+" "+tempPatient.postcode.substring(4,7));	
					firstnameLBL.setText(tempPatient.firstName);	

					
					
					//adds the components to the correct location
					documentpanel.add(admissioncardTitlepane);
					documentpanel.add(patientIConLBL);
					documentpanel.add(firstnameLBL);
					documentpanel.add(surnamenameLBL);
					documentpanel.add(numberOfAdmissionslbl);
					documentpanel.add(genderLBL);
					documentpanel.add(postcodelbl);
					documentpanel.add(dobLBL);
					documentpanel.add(viewDocumentAdmissionBttn);
					SmallDocumentBox.add(documentpanel);
					mediumBox.add(SmallDocumentBox);
					mediumBox.add(Box.createRigidArea(new Dimension(80,0)));
					
					currentNumOfPatients++;
				}
				
				largeBox.add(mediumBox);
				mediumBox.setAlignmentY(Component.TOP_ALIGNMENT);
			}
			mainBoxStaffPatients.add(largeBox);
			mainBoxStaffPatients.setVisible(false);
			mainBoxStaffPatients.setVisible(true);
		}
	}
	
	
//==================== MANAGEMENT ============================
	
	//will initalise the management entity on the systems
	//will close the GUI and then load the CMD line interafce
	//USER WILL BE ABLE TO ALTER THE INFORMATION TO THE MANAGEMENT ENTITY SETTINGS HERE
	public void setUpManagmentObj(String username)
	{
		if(username.equals("Management"))
		{
		
			boolean loggedIn =checkCredentials("Password",String.valueOf(loginPasswordF.getPassword()));
			if(loggedIn==true)
			{
				frame.setVisible(false);//the current panel will be hidden 
				frame.dispose();//disposes of the frame
				currentManager.createHomepage();//runs cmd interface
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Invalid managementID.");
			return;
		}
	}
	
	
	
	
	
//======================== DEV TOOLS =====================
	
	//Dev tool to help see the order in which the admissions are held
	//Will be used to identify discrepincies between the admissions available to the user and the ones they have
	public void outputCurrentAdmissionList()
	{
		for(int counter = 0;counter<systemAdmissionList.length;counter++)
		{
			//System.out.println("=======================");
			//System.out.println(systemAdmissionList[counter]);
			//System.out.println("========================");
		}
	}
	
	
	
	
	
	
	
	
	
//==================TABS METHODS ================

	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createTopbar(JPanel panel)
	{
		

		if(loaded[7][0]== false)//selection determines whether the panel is yet to be loaded
		{
			createTopbarGeneral();//as the correct panel is visible the components on the panel is formatted 
			loaded[7][0]= true;//the variable is set as true to prevent the components from being reran
		}
		notificationbttn.setEnabled(true);
		panel.add(homebttn);//the component is added to the panel
		demographicbttn.setVisible(true);//the component is set so it cant be seen
		panel.add(demographicbttn);//the component is added to the panel
		newPatientbttn.setVisible(true);//the component is set so it cant be seen
		panel.add(newPatientbttn);//the component is added to the panel
		panel.add(jargonLibrarybttn);//the component is added to the panel
		admissionbttn.setVisible(true);//the component is set so it cant be seen
		panel.add(admissionbttn);//the component is added to the panel
		panel.add(patientDemographicbttn);//the component is added to the panel
		panel.add(admissionPatientbttn);//the component is added to the panel
		panel.add(notificationbttn);//the component is added to the panel
		panel.add(patientDemographicbttn);//the component is added to the panel
		patientDemographicbttn.setVisible(true);//will initally show the component
		jargonLibrarybttn.setVisible(true);//will initally show the component
		admissionPatientbttn.setVisible(true);//the component is set so it cant be seen
		panel.add(admissionPatientbttn);//the component is added to the panel
		open = false;
		if(loginChoice ==0)//selection determing the type of login seeing if this type of user wants to login(patient)
		{
			newPatientbttn.setVisible(false);//will initally hide the component 
			admissionbttn.setVisible(true);//will initally hide the component 
			patientDemographicbttn.setVisible(false);//will initally hide the component 
			admissionPatientbttn.setVisible(false);//will initally hide the component 
			demographicbttn.setVisible(true);//will initally hide the component 
			homebttn.setVisible(true);//will initally hide the component 
			admissionPatientbttn.setVisible(false);//will initally hide the component 
			patientDemographicbttn.setVisible(false);//will initally hide the component 	
			notificationbttn.setVisible(true);//will initally show the component
		}
		
		else if ((loginChoice ==3)||(loginChoice ==1))//selection determing the type of login seeing if this type of user wants to login(consultant)
		{
			//System.out.println("Tried loading tob bar");
			newPatientbttn.setVisible(false);//will initally hide the component 
			admissionbttn.setVisible(false);//will initally hide the component 
			demographicbttn.setVisible(true);//will initally hide the component 
			homebttn.setVisible(true);//will initally hide the component 
			patientDemographicbttn.setVisible(true);//will initally hide the component 
			admissionPatientbttn.setVisible(true);//will initally hide the component 
			notificationbttn.setVisible(false);//will initally show the component
			
			admissionPatientbttn.setVisible(true);//will initally hide the component 
			patientDemographicbttn.setVisible(true);//will initally hide the component 
		}
		
		logoutbttn.setVisible(true);//the component is set so it cant be seen
		panel.add(logoutbttn);//the component is added to the panel
		panel.add(topwhitelinebar);//the component is added to the panel
		//mainBoxNotificationstab.removeAll();
		//createNotificationstab();
		//mainBoxNotificationstab.repaint();
	}
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createTopbarGeneral()
	{
		
		homebttn.setSize(140,40);//the components size is correctly declared
		homebttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		homebttn.setBackground(selectedBttcColour);//the background colour of the component is declared to the desired value 
		homebttn.setText("Home");//the label is geven text to add meaning to the label
		homebttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		homebttn.setLocation(0,0);//sets the location of the component 
		
		demographicbttn.setSize(200,40);//the components size is correctly declared
		demographicbttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		demographicbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		demographicbttn.setText("Demographic");//the label is geven text to add meaning to the label
		demographicbttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		demographicbttn.setLocation(140,0);//sets the location of the component

		admissionPatientbttn.setSize(280,40);//the components size is correctly declared
		admissionPatientbttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		admissionPatientbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		admissionPatientbttn.setText("Patient Admission");//the label is geven text to add meaning to the label
		admissionPatientbttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		admissionPatientbttn.setLocation(620,0);//sets the location of the component

	
		patientDemographicbttn.setSize(280,40);//the components size is correctly declared
		patientDemographicbttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		patientDemographicbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		patientDemographicbttn.setText("Patient Demographic");//the label is geven text to add meaning to the label
		patientDemographicbttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		patientDemographicbttn.setLocation(340,0);//sets the location of the component

	
		admissionbttn.setSize(210,40);//the components size is correctly declared
		admissionbttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		admissionbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		admissionbttn.setText("Admission");//the label is geven text to add meaning to the label
		admissionbttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		admissionbttn.setLocation(340,0);//sets the location of the component 
		
		notificationbttn.setSize(200,40);//the components size is correctly declared
		notificationbttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		notificationbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		notificationbttn.setText("Notifications");//the label is geven text to add meaning to the label
		notificationbttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		notificationbttn.setLocation(900,0);//sets the location of the component 
		
		jargonLibrarybttn.setSize(180,40);//the components size is correctly declared
		jargonLibrarybttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		jargonLibrarybttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		jargonLibrarybttn.setText("Glossary");//the label is geven text to add meaning to the label
		jargonLibrarybttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		jargonLibrarybttn.setLocation(1100,0);//sets the location of the component 
		
		logoutbttn.setSize(180,40);//the components size is correctly declared
		logoutbttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		logoutbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		logoutbttn.setText("Logout");//the label is geven text to add meaning to the label
		logoutbttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component
		logoutbttn.setLocation(1277,0);//sets the location of the component 
		
		topwhitelinebar.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		topwhitelinebar.setForeground( new Color(-1) );//the foreground of the component is given a white font
		topwhitelinebar.setBackground( new Color(-1) );//the background colour of the component is declared to the desired value 
		topwhitelinebar.setSize(1457,40);//the components size is correctly declared
		topwhitelinebar.setOpaque(true);//the component is set to opaque
		topwhitelinebar.setLocation(0,0); //sets the location of the component 
		

	}
	//This method will set all the buttons for the main as unselected for the top bar, by setting them as enabled followed by being grey
	//for the user desired button the correct instance will be set to blue followed by being disabled, creating the visual stimulation that the button has bee pressed
	//this will be applied to all the buttons actionlistners with the respective parameters
	public void setActiveTopPanelBttn(JButton button)
	{
		homebttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		demographicbttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		admissionbttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		newPatientbttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		admissionPatientbttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		jargonLibrarybttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		patientDemographicbttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		homebttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		admissionbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		demographicbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		newPatientbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		jargonLibrarybttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
		admissionPatientbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
		patientDemographicbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
		button.setEnabled(false);//the component is disabled as it is currently being on that panel 
		button.setBackground(selectedBttcColour);//the background colour of the component is declared to the desired value 
	}
	//whereas the method above selects the button to be as active as a paramter this method is similar in the sense that all the buttons are set to being unselectcted
	//the only differnce is that there is no paramter and so no button is set as active
	public void setNonActiveTopPanel()//when the user leaves any of the 3 top panels all of them will be renabled inseatd of the exception of just one
	{
		homebttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		demographicbttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		admissionbttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		admissionPatientbttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		patientDemographicbttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		newPatientbttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		jargonLibrarybttn.setEnabled(true);//the label is set to enabled to allow them to be used 
		homebttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		admissionbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		patientDemographicbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		admissionPatientbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		demographicbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		newPatientbttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		jargonLibrarybttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
	}
	
	//notifications 
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	public void createNotificationstab()
	{
		mainBoxNotificationstab.removeAll();
		mainBoxNotificationstab.setBackground(darkGreyColour);
		mainBoxNotificationstab.setOpaque(true);
		for (int i = 0; i <userPatient.numberOfNotifications; ++i)
		{
			
			Box notificationBox = new Box(BoxLayout.LINE_AXIS);
			JTextArea description = new JTextArea();
			description.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
			description.setWrapStyleWord(true);
							
			JButton deleteNotificationBttn = new JButton();//declares a button to allow the user to create an account of that type
			description.setEditable(false);
					description.setText(userPatient.notifications[i]);
			notificationBox.putClientProperty("temp",String.valueOf(i));
			notificationBox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
			notificationBox.setOpaque(true);
			JLabel time = new JLabel();
		
			//time.setText();
			
			time.setFont(buttonFontFormat);
			
			notificationBox.setMinimumSize(new Dimension(500,65));
			notificationBox.setPreferredSize(new Dimension(500,65));
			notificationBox.setMaximumSize(new Dimension(500,65));
			time.setMinimumSize(new Dimension(100,65));
			time.setPreferredSize(new Dimension(100,65));
			time.setMaximumSize(new Dimension(100,65));
			description.setMinimumSize(new Dimension(420,65));
			description.setPreferredSize(new Dimension(420,65));
			description.setMaximumSize(new Dimension(420,65));
			time.setOpaque(true);
			time.setBackground(new Color(-1));
			time.setForeground( new Color(1) );//the foreground of the component is given a white font
			time.setFont(consultantCardFont);//the font that has been declared is attached to the component 
			
			JLabel date = new JLabel();
		date.setOpaque(true);
			//date.setText();
			date.setBackground(new Color(-1));
			date.setForeground( new Color(1) );//the foreground of the component is given a white font
			date.setFont(consultantCardFont);//the font that has been declared is attached to the component 
			
			
			date.setFont(buttonFontFormat);
			date.setMinimumSize(new Dimension(100,65));
			date.setPreferredSize(new Dimension(100,65));
			date.setMaximumSize(new Dimension(100,65));
			
			notificationBox.add(description);
			//formatting 
			
			
			
			
			deleteNotificationBttn.setMinimumSize(new Dimension(65,65));
			deleteNotificationBttn.setPreferredSize(new Dimension(65,65));
			deleteNotificationBttn.setMaximumSize(new Dimension(65,65));
			deleteNotificationBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
			deleteNotificationBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
			deleteNotificationBttn.setFont(admissionCardFont);//the font that has been declared is attached to the component 
			deleteNotificationBttn.setText("X");
			deleteNotificationBttn.putClientProperty("AvailableButton",String.valueOf(i));
			deleteNotificationBttn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					JButton source = (JButton)e.getSource();
					//System.out.println("Andrew");
					int intA = Integer.parseInt( (String) source.getClientProperty("AvailableButton"));
					deleteNotification(intA);
					mainBoxNotificationstab.removeAll();
					createNotificationstab();
					//mainBoxNotificationstab.repaint();
					displayWindow.setVisible(false);
					displayWindow.setVisible(true);
					
				}	
			});	
				notificationBox.add(deleteNotificationBttn);
				mainBoxNotificationstab.add(notificationBox);
				listOfNotifications[i] = deleteNotificationBttn;
			}
	}	
	
	//tabs admission
	//With this method the box for the admissions is intialised here also, the button for the new admission button is also initalised here 
	//this will create the box and set values such as location and size
	public void createTopbarAdmission(JPanel panel)
	{		
	createButtonTopBarAdmission();//the top bar is created for the admission buttons

		//method which initalises the admission tab bar and adds all the admissions to it.
		if(loaded[7][2]== false)//selection determines whether the panel is yet to be loaded
		{

			loaded[7][2]= true;//the variable is set as true to prevent the components from being reran
			descScroll.setSize(1457,70);//the components size is correctly declared
			buttonBar.setSize(1457,70);//the components size is correctly declared
			descScroll.setLocation(0,40); //sets the location of the component 
			descScroll.setVisible(true);
			descScroll.setBackground(darkButtonGrey);
			descScroll.getHorizontalScrollBar().setUnitIncrement(16);
			topBarNewAdmissionBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
			topBarNewAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
			topBarNewAdmissionBttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component 
			topBarNewAdmissionBttn.setText("New Admission");//the label is geven text to add meaning to the label
			if(hasNewAdmissionLoaded == false)
			{
				formatTopBarAdmissionButton(topBarNewAdmissionBttn);
				hasNewAdmissionLoaded = true;
			}
		}
		panel.add(descScroll);			
		
	}
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createNotificationTabGeneral()
	{
		displayWindow = new JWindow(javax.swing.FocusManager.getCurrentManager().getActiveWindow());
        displayWindow.setFocusable(true);
		displayWindow.setSize(500,800);
		// "Date selection and navigation not working on Mac OS X 10.12.4 under Java 8 update 121."
        displayWindow.setAlwaysOnTop(true);
		
		notificationPanel.setLocation(0,0);
		notificationPanel.setSize(500,800);
		scrollpaneNotitificationstab.setVisible(true);
		notificationPanel.setVisible(true);
		scrollpaneNotitificationstab.setLocation(0,0);
		scrollpaneNotitificationstab.setSize(500,800);
		scrollpaneNotitificationstab.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpaneNotitificationstab.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpaneNotitificationstab.setBackground(darkButtonGrey);
		scrollpaneNotitificationstab.getVerticalScrollBar().setUnitIncrement(16);
		createNotificationstab();
		notificationPanel.add(scrollpaneNotitificationstab);
		
        displayWindow.getContentPane().add(notificationPanel);
		
		//displayWindow.setVisible(false);
	}
	
	//admission button creation
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	public void createButtonTopBarAdmission()
	{
		buttonBar.removeAll();
		/*
		Here this method is very specialised as it will uniquley create buttons corrolating for 
		a specific admission, while not so hard to think about the idea develops completxity quickly 
		when adding action listners and producing a generalised method wich can unqiuely retrun specfic values
		What happens is when the button object is intialised the component is attached with a property of admission ID
		This can be used to identify the correct admission for the button later on and will be cross referneced to open the correct
		admission when pressed
		*/
		for (int i = 0; i <systemAdmissionList.length; ++i)
		{
		JButton admissionButton = new JButton(systemAdmissionList[i].admissionID+"");
		admissionButton.putClientProperty("AdmissionId",String.valueOf(i));
		formatTopBarAdmissionButton(admissionButton);//calls method which formats the component correctly
		//an action listener is attached to allow the buttons to be spepcfically given purpose.
		if(loginChoice==1)
		{
			if((systemAdmissionList[i].ward.equals("PENDING"))||(systemAdmissionList[i].consultantID.equals("PENDING")))
			{
				admissionButton.setBackground(importantRedColor);//the background colour of the component is declared to the desired value 
			}
		}
		admissionButton.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				JButton source = (JButton)e.getSource();
				String id = (String) source.getClientProperty("AdmissionId");
				//System.out.print("BUTTON ID"+id);
				currentAdmissionIndex = Integer.parseInt(id);
				//System.out.println("Current admission index "+currentAdmissionIndex);
				createadmissionHomepagePanelGUI(/*admission object*/);
				listOfsortedDocumentIndexes = new int[0];
				createAdmissionDocumentpanel(listOfsortedDocumentIndexes);
				setActiveAdmissionPanelBttn(source);
				buttonList[currentAdmissionIndex].setEnabled(false);
				buttonList[currentAdmissionIndex].setBackground(selectedBttcColour);//the background colour of the component is declared to the desired valu
				//System.out.println("NUMBER OF DOCUMENTS +++++++++++++++++++    "+systemAdmissionList[currentAdmissionIndex].numberOfDocuments);
				if((loginChoice!=3)&&((systemAdmissionList[currentAdmissionIndex].consultantID.equals("PENDING"))==false))
				{
					userConsultant = userConsultant.retrieveConsultant(systemAdmissionList[currentAdmissionIndex].consultantID);
				}
				if(systemAdmissionList[currentAdmissionIndex].requested==true)
				{
					popUpreinstateAdmission(userPatient,systemAdmissionList[currentAdmissionIndex]);
				}
			}
		});
		buttonBar.add(admissionButton);
		buttonList[i] = admissionButton;
		}
		
		//After all the admissions have been added a new admission button is added at the end
		if(loginChoice!=1)
		{
		buttonBar.add(topBarNewAdmissionBttn);
		buttonList[systemAdmissionList.length+1] = topBarNewAdmissionBttn;
		}	
	}
	//with this method the buttons are formatted so that the clour and fonts are correctly set for the system
	public void formatTopBarAdmissionButton(JButton button)
	{
		//a basic method to just format the button correctly using standardised colours
		button.addActionListener(this);//an action listener is attached to the component to allow for actions to be performed
		button.setForeground( new Color(-1) );//the foreground of the component is given a white font
		button.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		button.setFont(whiteLoginFont);//the font that has been declared is attached to the component 
	}	
	//This method will set all the buttons for the admision as unselected for the top bar, by setting them as enabled followed by being grey
	//for the user desired button the correct instance will be set to blue followed by being disabled, creating the visual stimulation that the button has bee pressed
	//this will be applied to all the buttons actionlistners with the respective parameters
	public void setActiveAdmissionPanelBttn(JButton button)
	{
		//similar to the main tab this will be used to select the desired button and unselect any previously selected button
		//it does this by using the array for the buttons to loop through all the instances and change the formatting so that they follow suit
		topBarNewAdmissionBttn.setEnabled(true);
		topBarNewAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
		
		for (int k = 0; k <systemAdmissionList.length; ++k)
		{
			buttonList[k].setEnabled(true);
			buttonList[k].setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
			if(loginChoice==1)
			{
				if((systemAdmissionList[k].ward.equals("PENDING"))||(systemAdmissionList[k].consultantID.equals("PENDING")))
				{
					buttonList[k].setBackground(importantRedColor);//the background colour of the component is declared to the desired value 
				}
			}	
		}
		button.setEnabled(false);//the component is disabled as it is currently being on that panel 
		button.setBackground(selectedBttcColour);//the background colour of the component is declared to the desired value 	
			
	}
	//whereas the method above selects the button to be as active as a paramter this method is similar in the sense that all the buttons are set to being unselectcted
	//the only differnce is that there is no paramter and so no button is set as active
	public void setNonActiveTopAdmissionPanel()//when the user leaves any of the 3 top panels all of them will be renabled inseatd of the exception of just one
	{
		for (int k = 0; k <systemAdmissionList.length; ++k)
		{
			buttonList[k].setEnabled(true);
			buttonList[k].setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
			if(loginChoice==1)
			{
				if((systemAdmissionList[k].ward.equals("PENDING"))||(systemAdmissionList[k].consultantID.equals("PENDING")))
				{
					buttonList[k].setBackground(importantRedColor);//the background colour of the component is declared to the desired value 
				}
			}	
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//============= CONTACT BAR ================
	//patient
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createBoxContactBox()
	{
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);//the attributes of the font are changed to have the fount contain an underlined part to it
		
		contactBox.setLocation(0,40); //sets the location of the component 
		contactBox.setSize(350,873);//the components size is correctly declared
		contactBox.setBorder(BorderFactory.createLineBorder(Color.black));
		contactBox.setAlignmentX(Component.CENTER_ALIGNMENT);


		topBox.setMinimumSize(new Dimension(348,365));
		topBox.setPreferredSize(new Dimension(348,365));
		topBox.setMaximumSize(new Dimension(348,365));		


		JLabel nameField = new JLabel();//declares the name feild that will contain the user's name
		nameField.setFont(headerFontFormatBlack);//a font is applied to the text of the component 
		nameField.setOpaque(false);//the component is set to opaque
		nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel topBlackLinePatientId = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
		topBlackLinePatientId.setMinimumSize(new Dimension(300,3));
		topBlackLinePatientId.setPreferredSize(new Dimension(300,3));
		topBlackLinePatientId.setMaximumSize(new Dimension(300,3));
		topBlackLinePatientId.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		topBlackLinePatientId.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		topBlackLinePatientId.setOpaque(true);//the component is set to opaque
		topBlackLinePatientId.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel patientIDField = new JLabel();//declares the actual feild that contains the patient's id
		patientIDField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//a border is removed from the component 
		patientIDField.setFont(headerFontFormatID);//a font is applied to the text of the component 
		patientIDField.setOpaque(false);//the component is set to opaque
		patientIDField.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel BottomBlackLinePatientId = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
		BottomBlackLinePatientId.setMinimumSize(new Dimension(300,3));
		BottomBlackLinePatientId.setPreferredSize(new Dimension(300,3));
		BottomBlackLinePatientId.setMaximumSize(new Dimension(300,3));
		BottomBlackLinePatientId.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		BottomBlackLinePatientId.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		BottomBlackLinePatientId.setOpaque(true);//the component is set to opaque
		BottomBlackLinePatientId.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel userProfilePicfield = new JLabel();//declares a label for the profile picture
		userProfilePicfield.setSize(137,150); //the components size is correctly declared
		userProfilePicfield.setIcon( new ImageIcon("personIconLarge.png") );//the correct image is retrieved from the folder
		userProfilePicfield.setAlignmentX(Component.CENTER_ALIGNMENT);
		userProfilePicfield.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 

		JLabel BottomBlackLinePatientPhoto = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
		BottomBlackLinePatientPhoto.setMinimumSize(new Dimension(348,3));
		BottomBlackLinePatientPhoto.setPreferredSize(new Dimension(348,3));
		BottomBlackLinePatientPhoto.setMaximumSize(new Dimension(348,3));
		BottomBlackLinePatientPhoto.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		BottomBlackLinePatientPhoto.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		BottomBlackLinePatientPhoto.setOpaque(true);//the component is set to opaque
		BottomBlackLinePatientPhoto.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		topBox.add(Box.createRigidArea(new Dimension(0,15)));
		topBox.add(nameField);
		topBox.add(Box.createRigidArea(new Dimension(0,10)));
		topBox.add(topBlackLinePatientId);
		topBox.add(Box.createRigidArea(new Dimension(0,5)));
		topBox.add(patientIDField);
		topBox.add(Box.createRigidArea(new Dimension(0,5)));
		topBox.add(BottomBlackLinePatientId);
		topBox.add(Box.createRigidArea(new Dimension(0,15)));
		topBox.add(userProfilePicfield);
		topBox.add(Box.createRigidArea(new Dimension(0,15)));
		topBox.add(BottomBlackLinePatientPhoto);
		contactBox.add(topBox);//the component is added to the outer panel 0
		
		basicInfoBox.setMinimumSize(new Dimension(348,290));
		basicInfoBox.setPreferredSize(new Dimension(348,300));
		basicInfoBox.setMaximumSize(new Dimension(348,300));
		basicInfoBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		basicInfoBox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 

		JLabel blankLbl = new JLabel();//declares the actual feild that contains the patient's dob
		blankLbl.setFont(headerFontFormatBlackUnder.deriveFont(attributes));
		blankLbl.setOpaque(false);//the component is set to opaque
		blankLbl.setAlignmentX(Component.TOP_ALIGNMENT);
		
		JLabel phoneNumberField = new JLabel();//declares the name feild that will contain the user's name
		phoneNumberField.setOpaque(false);//the component is set to opaque
		phoneNumberField.setFont(headerFontFormatBlackUnder.deriveFont(attributes));
		phoneNumberField.setAlignmentX(Component.LEFT_ALIGNMENT);


		JLabel sexField = new JLabel();//declares the actual feild that contains the patient's sex
		sexField.setOpaque(false);//the component is set to opaque
		sexField.setFont(headerFontFormatBlackUnder.deriveFont(attributes));
		sexField.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel dobField = new JLabel();//declares the actual feild that contains the patient's dob
		dobField.setOpaque(false);//the component is set to opaque
		dobField.setFont(headerFontFormatBlackUnder.deriveFont(attributes));
		dobField.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel BottomBlackLinePatientDOB = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
		BottomBlackLinePatientDOB.setMinimumSize(new Dimension(348,3));
		BottomBlackLinePatientDOB.setPreferredSize(new Dimension(348,3));
		BottomBlackLinePatientDOB.setMaximumSize(new Dimension(348,3));
		BottomBlackLinePatientDOB.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		BottomBlackLinePatientDOB.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		BottomBlackLinePatientDOB.setOpaque(true);//the component is set to opaque
		BottomBlackLinePatientDOB.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JTextArea AddressFeild = new JTextArea();//declares the actual feild that contains the patient's dob
		AddressFeild.setOpaque(false);//the component is set to opaque
		AddressFeild.setFont(headerFontFormatBlackUnder.deriveFont(attributes));
		AddressFeild.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
		AddressFeild.setMinimumSize(new Dimension(348,100));
		AddressFeild.setPreferredSize(new Dimension(348,100));
		AddressFeild.setEditable(false);
		AddressFeild.setMaximumSize(new Dimension(348,100));
		AddressFeild.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		
		JLabel BottomBlackLinePatientAddress = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
		BottomBlackLinePatientAddress.setMinimumSize(new Dimension(348,3));
		BottomBlackLinePatientAddress.setPreferredSize(new Dimension(348,3));
		BottomBlackLinePatientAddress.setMaximumSize(new Dimension(348,3));
		BottomBlackLinePatientAddress.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		BottomBlackLinePatientAddress.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		BottomBlackLinePatientAddress.setOpaque(true);//the component is set to opaque
		BottomBlackLinePatientAddress.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		basicInfoBox.add(blankLbl);
		basicInfoBox.add(Box.createRigidArea(new Dimension(10,10)));
		basicInfoBox.add(phoneNumberField);
		basicInfoBox.add(Box.createRigidArea(new Dimension(0,10)));
		basicInfoBox.add(sexField);
		basicInfoBox.add(Box.createRigidArea(new Dimension(0,10)));
		basicInfoBox.add(dobField);
		basicInfoBox.add(Box.createRigidArea(new Dimension(0,20)));
		basicInfoBox.add(BottomBlackLinePatientDOB);
		basicInfoBox.add(Box.createRigidArea(new Dimension(0,10)));
		basicInfoBox.add(AddressFeild);
		basicInfoBox.add(Box.createRigidArea(new Dimension(0,10)));
		basicInfoBox.add(BottomBlackLinePatientAddress);
		contactBox.add(basicInfoBox);//the component is added to the outer panel 1

		
		//now entity specific boxes
		
		//PatientPNUR0000001
		
		patientSpecificBox.setMinimumSize(new Dimension(348,217));
		patientSpecificBox.setPreferredSize(new Dimension(348,217));
		patientSpecificBox.setMaximumSize(new Dimension(348,217));
		patientSpecificBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JTextArea allergiesLblFeild = new JTextArea();//declares the actual feild that contains the patient's dob
		allergiesLblFeild.setOpaque(false);//the component is set to opaque
		allergiesLblFeild.setFont(headerFontFormatBlackUnder.deriveFont(attributes));
		allergiesLblFeild.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
		allergiesLblFeild.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		allergiesLblFeild.setMinimumSize(new Dimension(348,100));
		allergiesLblFeild.setPreferredSize(new Dimension(348,100));
		allergiesLblFeild.setMaximumSize(new Dimension(348,100));
		allergiesLblFeild.setAlignmentX(Component.LEFT_ALIGNMENT);
		allergiesLblFeild.setEditable(false);
		
		JLabel BottomBlackLinePatientAlergies = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
		BottomBlackLinePatientAlergies.setMinimumSize(new Dimension(348,3));
		BottomBlackLinePatientAlergies.setPreferredSize(new Dimension(348,3));
		BottomBlackLinePatientAlergies.setMaximumSize(new Dimension(348,3));
		BottomBlackLinePatientAlergies.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		BottomBlackLinePatientAlergies.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		BottomBlackLinePatientAlergies.setOpaque(true);//the component is set to opaque
		
		JLabel linkedStaffLbl = new JLabel();//declares the actual feild that contains the patient's dob
		linkedStaffLbl.setOpaque(false);//the component is set to opaque
		linkedStaffLbl.setFont(headerFontFormatBlackUnder.deriveFont(attributes));
		linkedStaffLbl.setText("Linked Staff Member:");
		linkedStaffLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel linkedStaffLblFeild = new JLabel();//declares the actual feild that contains the patient's dob
		linkedStaffLblFeild.setOpaque(false);//the component is set to opaque
		linkedStaffLblFeild.setFont(headerFontFormatBlackUnder.deriveFont(attributes));
		linkedStaffLblFeild.setAlignmentX(Component.LEFT_ALIGNMENT);
	
		patientSpecificBox.add(allergiesLblFeild);
		patientSpecificBox.add(BottomBlackLinePatientAlergies);
		patientSpecificBox.add(linkedStaffLbl);
		patientSpecificBox.add(linkedStaffLblFeild);
		

		//consultant
		consutlantSpecificBox.setMinimumSize(new Dimension(348,217));
		consutlantSpecificBox.setPreferredSize(new Dimension(348,217));
		consutlantSpecificBox.setMaximumSize(new Dimension(348,217));
		consutlantSpecificBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JTextArea disciplineLblFeild = new JTextArea();//declares the actual feild that contains the patient's dob
		disciplineLblFeild.setOpaque(false);//the component is set to opaque
		disciplineLblFeild.setFont(headerFontFormatBlackUnder.deriveFont(attributes));
		disciplineLblFeild.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
		disciplineLblFeild.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		
		consutlantSpecificBox.add(Box.createRigidArea(new Dimension(0,15)));
		consutlantSpecificBox.add(disciplineLblFeild);
		
		staffSpecificBox.setMinimumSize(new Dimension(348,507));
		staffSpecificBox.setPreferredSize(new Dimension(348,507));
		staffSpecificBox.setMaximumSize(new Dimension(348,507));
		staffSpecificBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel wageLbl = new JLabel();//declares the actual feild that contains the patient's dob
		wageLbl.setOpaque(false);//the component is set to opaque
		wageLbl.setFont(headerFontFormatBlackUnder.deriveFont(attributes));
		
		
		JLabel BottomBlackLinePatientWage = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
		BottomBlackLinePatientWage.setMinimumSize(new Dimension(348,3));
		BottomBlackLinePatientWage.setPreferredSize(new Dimension(348,3));
		BottomBlackLinePatientWage.setMaximumSize(new Dimension(348,3));
		BottomBlackLinePatientWage.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		BottomBlackLinePatientWage.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		BottomBlackLinePatientWage.setOpaque(true);//the component is set to opaque
		
		
		JLabel HrsPerWeeklbl = new JLabel();//declares the actual feild that contains the patient's dob
		HrsPerWeeklbl.setOpaque(false);//the component is set to opaque
		HrsPerWeeklbl.setFont(headerFontFormatBlackUnder.deriveFont(attributes));
		
		
		staffSpecificBox.add(Box.createRigidArea(new Dimension(0,15)));
		staffSpecificBox.add(wageLbl);
		staffSpecificBox.add(Box.createRigidArea(new Dimension(0,20)));
		staffSpecificBox.add(BottomBlackLinePatientWage);
		staffSpecificBox.add(Box.createRigidArea(new Dimension(0,15)));
		
		staffSpecificBox.add(HrsPerWeeklbl);

	}
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	public void createContactBarUser(JPanel panel,Box box)
	{
		panel.add(contactBox);
		panel.add(ContactBarBlueboxHighlight); 
		panel.add(ContactBarWhiteBOx);
		//General
			Component phoneNumberlbl=basicInfoBox.getComponent(2);//inner
			
			Component sexLblb=basicInfoBox.getComponent(4);//inner
			Component dOBLblb=basicInfoBox.getComponent(6);//inner
			Component address1lbl=basicInfoBox.getComponent(10);//inner
			Component TopBoxNameLbl=topBox.getComponent(1);//inner
		if(loginChoice==0)		
		{
			//System.out.println("HERE 1");
			((JLabel) TopBoxNameLbl).setText(userPatient.firstName+" "+userPatient.surName);
			((JTextArea) address1lbl).setText("Address : "+userPatient.addressHouseNum+userPatient.addressHouseStreet+"\n"+userPatient.town+" " +userPatient.postcode+"\n"+userPatient.county);
			((JLabel) dOBLblb).setText("D.O.B : "+ft.format(userPatient.dob));
			((JLabel) sexLblb).setText("Sex : "+userPatient.sex);
			//System.out.println("HERE 2");
			((JLabel) phoneNumberlbl).setText("Phone : "+userPatient.contactNum);
			contactBox.remove(staffSpecificBox);
			contactBox.remove(consutlantSpecificBox);
			
			//topBox
			Component idTemp=topBox.getComponent(5);
			((JLabel) idTemp).setText(userPatient.patientID);
			//System.out.println("HERE 3");
			//Patient
			Component allergiesLblTmp=patientSpecificBox.getComponent(0);//inner
			((JTextArea) allergiesLblTmp).setText("Allergies : "+userPatient.allergies );
			Component linkedStafflnlTmp=patientSpecificBox.getComponent(3);//inner
			((JLabel) linkedStafflnlTmp).setText(userPatient.linkedStaffID);
			contactBox.add(patientSpecificBox);//the component is added to the outer panel 1
			
		}
		if(loginChoice==3)		
		{
			((JLabel) TopBoxNameLbl).setText(userConsultant.firstName+" "+userConsultant.surName);
			((JTextArea) address1lbl).setText("Address : "+userConsultant.addressHouseNum+userConsultant.addressHouseStreet+"\n"+userConsultant.town+" "+userConsultant.postcode+"\n"+userConsultant.county);
			((JLabel) dOBLblb).setText("D.O.B : "+ft.format(userConsultant.dob));
			((JLabel) sexLblb).setText("Sex : "+userConsultant.sex);
			//System.out.println("HERE 2");
			((JLabel) phoneNumberlbl).setText("Phone : "+userConsultant.contactNum);
			contactBox.remove(staffSpecificBox);
			contactBox.remove(consutlantSpecificBox);
			
			//topBox
			Component idTemp=topBox.getComponent(5);
			((JLabel) idTemp).setText(userConsultant.consultantID);
			//System.out.println("HERE 3");
			
			//consultant
			Component disciplinelbltmp =consutlantSpecificBox.getComponent(1);//inner
			String disciplines =userConsultant.expertiese[0];
			for(int counter = 1; counter<userConsultant.expertiese.length;counter++)
			{
				disciplines = disciplines+"\n"+userConsultant.expertiese[counter];
			}
			((JTextArea) disciplinelbltmp).setText("Expertiese : "+disciplines);
			contactBox.add(consutlantSpecificBox);//the component is added to the outer panel 1
			
	}
		if(loginChoice==1)		
		{
			((JLabel) TopBoxNameLbl).setText(userStaff.firstName+" "+userStaff.surName);
			((JTextArea) address1lbl).setText("Address : "+userStaff.addressHouseNum+" "+userStaff.addressHouseStreet+"\n"+userStaff.town+" "+ userStaff.postcode+"\n"+userStaff.county);
			((JLabel) dOBLblb).setText("D.O.B : "+ft.format(userStaff.dob));
			((JLabel) sexLblb).setText("Sex : "+userStaff.sex);
			//System.out.println("HERE 2");
			((JLabel) phoneNumberlbl).setText("Phone : "+userStaff.contactNum);
			contactBox.remove(staffSpecificBox);
			contactBox.remove(consutlantSpecificBox);
			
			//topBox
			Component idTemp=topBox.getComponent(5);
			((JLabel) idTemp).setText(userStaff.staffID);
			//System.out.println("HERE 3");
			
			//Staff
			Component wageTmp=staffSpecificBox.getComponent(1);//inner
			((JLabel) wageTmp).setText("Wage :"+userStaff.wage);
			Component hrsPerWeekTmp=staffSpecificBox.getComponent(5);//inner
			((JLabel) hrsPerWeekTmp).setText("Hours per week :"+userStaff.hoursPerWeek);
			
			contactBox.add(staffSpecificBox);//the component is added to the outer panel 1
			
	}
		if(loaded[7][1]== false)//selection determines whether the panel is yet to be loaded
		{
			
		

			createContactBarUserGeneral();//as the correct panel is visible the components on the panel is formatted 
			loaded[7][1]= true;//the variable is set as true to prevent the components from being reran
		}
	}

	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createContactBarUserGeneral()
	{	
	
		
	
	}
	
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createBoxContactAdmissionBox()
	{
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);//the attributes of the font are changed to have the fount contain an underlined part to it
		attributesSmall.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);//the attributes of the font are changed to have the fount contain an underlined part to it
		
		contactBoxAdmission.setLocation(0,110); //sets the location of the component 
		contactBoxAdmission.setSize(350,873);//the components size is correctly declared
		contactBoxAdmission.setAlignmentX(Component.CENTER_ALIGNMENT);

		idAdmissionBox.setMinimumSize(new Dimension(348,110));
		idAdmissionBox.setPreferredSize(new Dimension(348,110));
		idAdmissionBox.setMaximumSize(new Dimension(348,110));		
		idAdmissionBox.setBorder(BorderFactory.createLineBorder(Color.black));	

		JLabel topBlackLineAdmissionId = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
		topBlackLineAdmissionId.setMinimumSize(new Dimension(300,3));
		topBlackLineAdmissionId.setPreferredSize(new Dimension(300,3));
		topBlackLineAdmissionId.setMaximumSize(new Dimension(300,3));
		topBlackLineAdmissionId.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		topBlackLineAdmissionId.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		topBlackLineAdmissionId.setOpaque(true);//the component is set to opaque
		topBlackLineAdmissionId.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel admissionIDlbl = new JLabel();//declares the actual feild that contains the patient's dob
		admissionIDlbl.setFont(headerFontFormatID);//a font is applied to the text of the component 
		admissionIDlbl.setOpaque(false);//the component is set to opaque
		admissionIDlbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel bottomBlackLineAdmissionId = new JLabel();//declares a labbel for a black line to be created for astetic purposes and display a visual appeal to the user//
		bottomBlackLineAdmissionId.setMinimumSize(new Dimension(300,3));
		bottomBlackLineAdmissionId.setPreferredSize(new Dimension(300,3));
		bottomBlackLineAdmissionId.setMaximumSize(new Dimension(300,3));
		bottomBlackLineAdmissionId.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		bottomBlackLineAdmissionId.setBackground( new Color(1) );//the background colour of the component is declared to the desired value 
		bottomBlackLineAdmissionId.setOpaque(true);//the component is set to opaque
		bottomBlackLineAdmissionId.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		idAdmissionBox.add(Box.createRigidArea(new Dimension(0,15)));
		idAdmissionBox.add(topBlackLineAdmissionId);
		idAdmissionBox.add(Box.createRigidArea(new Dimension(0,10)));
		idAdmissionBox.add(admissionIDlbl);
		idAdmissionBox.add(Box.createRigidArea(new Dimension(0,15)));
		idAdmissionBox.add(bottomBlackLineAdmissionId);
		contactBoxAdmission.add(idAdmissionBox);//the component is added to the outer panel 0
		
		//patient specific 1
		patientAdmissionConsultantbox.setMinimumSize(new Dimension(348,60));
		patientAdmissionConsultantbox.setPreferredSize(new Dimension(348,60));
		patientAdmissionConsultantbox.setMaximumSize(new Dimension(348,60));		
		patientAdmissionConsultantbox.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		JLabel consultantAdmissionLBL = new JLabel();//declares the actual feild that contains the patient's dob
		consultantAdmissionLBL.setFont(headerFontFormatBlackUnderSmall.deriveFont(attributesSmall));
		consultantAdmissionLBL.setOpaque(false);//the component is set to opaque
		
		patientAdmissionConsultantbox.add(Box.createRigidArea(new Dimension(0,10)));
		patientAdmissionConsultantbox.add(consultantAdmissionLBL);
		
		//consutlant specific 1
		consultantAdmissionNewDocumentbox.setMinimumSize(new Dimension(348,60));
		consultantAdmissionNewDocumentbox.setPreferredSize(new Dimension(348,60));
		consultantAdmissionNewDocumentbox.setMaximumSize(new Dimension(348,60));		
		consultantAdmissionNewDocumentbox.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JButton newDocumentButton = new JButton();//declares the button which is for the top tab bar
		newDocumentButton.setForeground( new Color(-1) );//the foreground of the component is given a white font
		newDocumentButton.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		newDocumentButton.setFont(whiteLoginFont);//the font that has been declared is attached to the component 
		newDocumentButton.setText("New Document");//the label is geven text to add meaning to the label
		newDocumentButton.setMinimumSize(new Dimension(318,40));
		newDocumentButton.setPreferredSize(new Dimension(318,40));
		newDocumentButton.setMaximumSize(new Dimension(318,40));	
		newDocumentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		newDocumentButton.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				createNewDocumentPage();
			}
		});
		
		consultantAdmissionNewDocumentbox.add(Box.createRigidArea(new Dimension(0,10)));
		consultantAdmissionNewDocumentbox.add(newDocumentButton);
		
		//General again
		admissiongeneralBox.setMinimumSize(new Dimension(348,275));
		admissiongeneralBox.setPreferredSize(new Dimension(348,275));
		admissiongeneralBox.setMaximumSize(new Dimension(348,275));		
		admissiongeneralBox.setBorder(BorderFactory.createLineBorder(Color.black));
						admissiongeneralBox.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel blankLbl = new JLabel();//declares the actual feild that contains the patient's dob
		blankLbl.setFont(headerFontFormatBlackUnder.deriveFont(attributes));
		blankLbl.setOpaque(false);//the component is set to opaque
		blankLbl.setAlignmentX(Component.TOP_ALIGNMENT);
		
		JLabel wardLbl = new JLabel();//declares the actual feild that contains the patient's dob
		wardLbl.setFont(headerFontFormatBlackUnder.deriveFont(attributes));
		wardLbl.setOpaque(false);//the component is set to opaque
		wardLbl.setAlignmentX(Component.LEFT_ALIGNMENT);

		
		JLabel doaLbl = new JLabel();//declares the actual feild that contains the patient's dob
		doaLbl.setFont(headerFontFormatBlackUnderSmall.deriveFont(attributesSmall));
		doaLbl.setOpaque(false);//the component is set to opaque
		doaLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JTextArea symptomslblFeild = new JTextArea();//declares the actual feild that contains the patient's dob
		symptomslblFeild.setOpaque(true);//the component is set to opaque
		symptomslblFeild.setFont(headerFontFormatBlackUnder.deriveFont(attributes));
		symptomslblFeild.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
		symptomslblFeild.setWrapStyleWord(true);
		symptomslblFeild.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		symptomslblFeild.setMinimumSize(new Dimension(348,150));
		symptomslblFeild.setEditable(false);
		symptomslblFeild.setPreferredSize(new Dimension(348,150));
		symptomslblFeild.setMaximumSize(new Dimension(348,150));
		symptomslblFeild.setBackground(new Color(-1));
		symptomslblFeild.setAlignmentX(Component.LEFT_ALIGNMENT);
		JScrollPane scrollpaneSYMPTOMS = new JScrollPane(symptomslblFeild);
		
		symptomslblFeild.setMinimumSize(new Dimension(348,150));
		symptomslblFeild.setPreferredSize(new Dimension(348,150));
		symptomslblFeild.setMaximumSize(new Dimension(348,150));
		JLabel donaLbl = new JLabel();//declares the actual feild that contains the patient's dob
		donaLbl.setFont(headerFontFormatBlackUnderSmall.deriveFont(attributesSmall));
		donaLbl.setOpaque(false);//the component is set to opaque
		donaLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		admissiongeneralBox.add(blankLbl);
		admissiongeneralBox.add(wardLbl);
		admissiongeneralBox.add(Box.createRigidArea(new Dimension(0,15)));
		admissiongeneralBox.add(doaLbl);
		admissiongeneralBox.add(Box.createRigidArea(new Dimension(0,20)));
		admissiongeneralBox.add(scrollpaneSYMPTOMS);
		admissiongeneralBox.add(Box.createRigidArea(new Dimension(0,20)));
		admissiongeneralBox.add(donaLbl);
		admissiongeneralBox.add(Box.createRigidArea(new Dimension(0,20)));

//		general2 box 2
		admissiongeneral2Box.setMinimumSize(new Dimension(348,145));
		admissiongeneral2Box.setPreferredSize(new Dimension(348,145));
		admissiongeneral2Box.setMaximumSize(new Dimension(348,145));		
		admissiongeneral2Box.setBorder(BorderFactory.createLineBorder(Color.black));
				admissiongeneral2Box.setAlignmentX(Component.CENTER_ALIGNMENT);


	
		JLabel blankLbl1 = new JLabel();//declares the actual feild that contains the patient's dob
		blankLbl1.setFont(headerFontFormatBlackUnder.deriveFont(attributes));
		blankLbl1.setOpaque(false);//the component is set to opaque
			blankLbl1.setAlignmentY(Component.RIGHT_ALIGNMENT );

		JButton viewNextAppointmentAdmissionBttn = new JButton();//declares a button to allow the system to compare the credentials the user has entered
		viewNextAppointmentAdmissionBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		viewNextAppointmentAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		viewNextAppointmentAdmissionBttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component 
		viewNextAppointmentAdmissionBttn.setText("View Appointments");//the label is geven text to add meaning to the label
		viewNextAppointmentAdmissionBttn.setMinimumSize(new Dimension(318,40));
		viewNextAppointmentAdmissionBttn.setPreferredSize(new Dimension(318,40));
		viewNextAppointmentAdmissionBttn.setMaximumSize(new Dimension(318,40));	
		viewNextAppointmentAdmissionBttn.setAlignmentX(Component.CENTER_ALIGNMENT );
		viewNextAppointmentAdmissionBttn.setAlignmentY(Component.LEFT_ALIGNMENT);
		
		viewNextAppointmentAdmissionBttn.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				createViewAppointmentPanelGUI(systemAdmissionList[currentAdmissionIndex].upComingBooking);
			}
		});
		
		JLabel tonaLbl = new JLabel();//declares the actual feild that contains the patient's dob
		tonaLbl.setFont(headerFontFormatBlackUnderSmall.deriveFont(attributesSmall));
		tonaLbl.setOpaque(false);//the component is set to opaque
		tonaLbl.setAlignmentX(Component.CENTER_ALIGNMENT  );
		
		JLabel dischargeStutsLbl = new JLabel();//declares the actual feild that contains the patient's dob
		dischargeStutsLbl.setFont(headerFontFormatBlackUnderSmall.deriveFont(attributesSmall));
		dischargeStutsLbl.setOpaque(false);//the component is set to opaque
		dischargeStutsLbl.setAlignmentX(Component.CENTER_ALIGNMENT  );

		admissiongeneral2Box.add(blankLbl1);
		admissiongeneral2Box.add(Box.createRigidArea(new Dimension(0,20)));
		admissiongeneral2Box.add(viewNextAppointmentAdmissionBttn);
		
		admissiongeneral2Box.add(tonaLbl);
		admissiongeneral2Box.add(Box.createRigidArea(new Dimension(0,20)));
		admissiongeneral2Box.add(dischargeStutsLbl);
		admissiongeneral2Box.add(Box.createRigidArea(new Dimension(0,20)));
		
		
		//patient specific box 2
		patientAdmissionButtonsbox.setMinimumSize(new Dimension(348,200));
		patientAdmissionButtonsbox.setPreferredSize(new Dimension(348,200));
		patientAdmissionButtonsbox.setMaximumSize(new Dimension(348,200));		
		patientAdmissionButtonsbox.setBorder(BorderFactory.createLineBorder(Color.black));
		patientAdmissionButtonsbox.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton viewAdmissionBttn = new JButton();//declares a button to allow the system to compare the credentials the user has entered
		viewAdmissionBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		viewAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		viewAdmissionBttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component 
		viewAdmissionBttn.setText("View Admission");//the label is geven text to add meaning to the label
		viewAdmissionBttn.setMinimumSize(new Dimension(318,40));
		viewAdmissionBttn.setPreferredSize(new Dimension(318,40));
		viewAdmissionBttn.setMaximumSize(new Dimension(318,40));	
		viewAdmissionBttn.setAlignmentX(Component.CENTER_ALIGNMENT );
		viewAdmissionBttn.setAlignmentY(Component.LEFT_ALIGNMENT);
		viewAdmissionBttn.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				createViewAdmissionPanelGUI();
			}
		});
		
		requestReinstatementBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		requestReinstatementBttn.setBackground(importantRedColor);//the background colour of the component is declared to the desired value 
		requestReinstatementBttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component 
		requestReinstatementBttn.setText("Request Reinstatment");//the label is geven text to add meaning to the label
		requestReinstatementBttn.setMinimumSize(new Dimension(300,40));
		requestReinstatementBttn.setPreferredSize(new Dimension(300,40));
		requestReinstatementBttn.setMaximumSize(new Dimension(300,40));	
		requestReinstatementBttn.setAlignmentX(Component.CENTER_ALIGNMENT );
		requestReinstatementBttn.setAlignmentY(Component.LEFT_ALIGNMENT);
		requestReinstatementBttn.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				systemAdmissionList[currentAdmissionIndex].requested=true;
				AdmissionList al = new AdmissionList();
				al.updateAdmission(userPatient,systemAdmissionList[currentAdmissionIndex],12);
				requestReinstatementBttn.setEnabled(false);
			}
		});

		patientAdmissionButtonsbox.add(Box.createRigidArea(new Dimension(0,20)));
		patientAdmissionButtonsbox.add(viewAdmissionBttn);
		patientAdmissionButtonsbox.add(Box.createRigidArea(new Dimension(0,20)));
		patientAdmissionButtonsbox.add(requestReinstatementBttn);
		
		consultantAdmissionButtonsbox.setMinimumSize(new Dimension(348,155));
		consultantAdmissionButtonsbox.setPreferredSize(new Dimension(348,155));
		consultantAdmissionButtonsbox.setMaximumSize(new Dimension(348,155));		
		consultantAdmissionButtonsbox.setBorder(BorderFactory.createLineBorder(Color.black));
		consultantAdmissionButtonsbox.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton editAdmissionBttn = new JButton();//declares a button to allow the system to compare the credentials the user has entered
		editAdmissionBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		editAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		editAdmissionBttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component 
		editAdmissionBttn.setText("Edit Admission");//the label is geven text to add meaning to the label
		editAdmissionBttn.setMinimumSize(new Dimension(318,40));
		editAdmissionBttn.setPreferredSize(new Dimension(318,40));
		editAdmissionBttn.setMaximumSize(new Dimension(318,40));	
		editAdmissionBttn.setAlignmentX(Component.CENTER_ALIGNMENT );
		editAdmissionBttn.setAlignmentY(Component.LEFT_ALIGNMENT);
		editAdmissionBttn.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				createViewAdmissionPanelGUI();
			}
		});
		
		reinstateDiscahrgePatientAdmissionBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		reinstateDiscahrgePatientAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		reinstateDiscahrgePatientAdmissionBttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component 
		reinstateDiscahrgePatientAdmissionBttn.setMinimumSize(new Dimension(318,40));
		reinstateDiscahrgePatientAdmissionBttn.setPreferredSize(new Dimension(318,40));
		reinstateDiscahrgePatientAdmissionBttn.setMaximumSize(new Dimension(318,40));	
		reinstateDiscahrgePatientAdmissionBttn.setAlignmentX(Component.CENTER_ALIGNMENT );
		reinstateDiscahrgePatientAdmissionBttn.setAlignmentY(Component.LEFT_ALIGNMENT);
		reinstateDiscahrgePatientAdmissionBttn.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				if(systemAdmissionList[currentAdmissionIndex].active==false)
				{
					
					createNewDocumentPage();
					setDocumentData(2);//reinstate

				}
				if(systemAdmissionList[currentAdmissionIndex].active==true)
				{
					createNewDocumentPage();
					setDocumentData(1);//discharge
				}
			}
		});
		consultantAdmissionButtonsbox.add(Box.createRigidArea(new Dimension(0,20)));
		consultantAdmissionButtonsbox.add(editAdmissionBttn);
		consultantAdmissionButtonsbox.add(Box.createRigidArea(new Dimension(0,20)));
		consultantAdmissionButtonsbox.add(reinstateDiscahrgePatientAdmissionBttn);
		
		
		
		addNewLegacyDocumentBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		addNewLegacyDocumentBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		addNewLegacyDocumentBttn.setFont(whiteLoginFont);//the font that has been declared is attached to the component 
		addNewLegacyDocumentBttn.setText("Add legacy documents");//the label is geven text to add meaning to the label
		addNewLegacyDocumentBttn.setMinimumSize(new Dimension(300,40));
		addNewLegacyDocumentBttn.setPreferredSize(new Dimension(300,40));
		addNewLegacyDocumentBttn.setMaximumSize(new Dimension(300,40));	
		addNewLegacyDocumentBttn.setAlignmentX(Component.CENTER_ALIGNMENT );
		addNewLegacyDocumentBttn.setAlignmentY(Component.LEFT_ALIGNMENT);
		addNewLegacyDocumentBttn.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				createAddLegacyDocumentPanel();
			}
		});
		
		consultantAdmissionButtonsbox.add(Box.createRigidArea(new Dimension(0,20)));
	}
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createContactBarAdmission()
	{
		admissionHomepagePanel.add(contactBoxAdmission);
		admissionHomepagePanel.add(ContactBarBlueboxHighlight); 
		admissionHomepagePanel.add(ContactBarWhiteBOx);
		consultantAdmissionButtonsbox.remove(addNewLegacyDocumentBttn);
		//id box
		Component admissionIDlblTmp=idAdmissionBox.getComponent(3);//inner
		((JLabel) admissionIDlblTmp).setText(systemAdmissionList[currentAdmissionIndex].admissionID);
		if(loginChoice==0)
		{
			contactBoxAdmission.remove(consultantAdmissionNewDocumentbox);
			contactBoxAdmission.add(patientAdmissionConsultantbox,1);//the component is added to the outer panel 0
			Component consultantlblTmp=patientAdmissionConsultantbox.getComponent(1);//inner
			((JLabel) consultantlblTmp).setText("Consultant : "+systemAdmissionList[currentAdmissionIndex].consultantID);
		}
		if(loginChoice ==3)
		{
			contactBoxAdmission.remove(patientAdmissionConsultantbox);
			contactBoxAdmission.add(consultantAdmissionNewDocumentbox,1);//the component is added to the outer panel 0
		}
		
		
		
		
		//general again
		contactBoxAdmission.add(admissiongeneralBox);//the component is added to the outer panel 0
		Component wardlbltmp=admissiongeneralBox.getComponent(1);//inner
		((JLabel) wardlbltmp).setText("Ward : "+systemAdmissionList[currentAdmissionIndex].ward);
		Component doalbltmp=admissiongeneralBox.getComponent(3);//inner
		try{
			
		((JLabel) doalbltmp).setText("Date Of Admission: "+ft.format(systemAdmissionList[currentAdmissionIndex].dateAdmissionCreated));
		}
		catch(Exception exc){
			((JLabel) doalbltmp).setText("Date Of Admission: ");
		}
		Component symptomsbltmp =admissiongeneralBox.getComponent(5);//inner
		String concatenatedSymptoms=systemAdmissionList[currentAdmissionIndex].listOfSymptoms[0]+"\n";
		for(int indexAreasCount = 1;indexAreasCount<systemAdmissionList[currentAdmissionIndex].listOfSymptoms.length;indexAreasCount++)
		{
			try{
			concatenatedSymptoms=concatenatedSymptoms+systemAdmissionList[currentAdmissionIndex].listOfSymptoms[indexAreasCount]+"\n";
			}
			catch(Exception exc){
			}
		}
		JViewport viewport = ((JScrollPane) symptomsbltmp).getViewport();
		 JTextArea editorPane = (JTextArea)viewport.getView(); 
		
		editorPane.setText("Symptoms: "+concatenatedSymptoms);
		Component donalbltmp=admissiongeneralBox.getComponent(7);//inner
		try{
			((JLabel) donalbltmp).setText("Appointment Date: "+ft.format(systemAdmissionList[currentAdmissionIndex].upComingBooking.dateOfNextApp));
		}
		catch(Exception exc){
			((JLabel) donalbltmp).setText("No upcoming appointments");
		}
		
		
		contactBoxAdmission.add(admissiongeneral2Box);//the component is added to the outer panel 0
		Component tonalbltmp=admissiongeneral2Box.getComponent(3);//inner
		
		
		Component dischargeStatuslbltmp=admissiongeneral2Box.getComponent(5);//inner	
	
		if(loginChoice==0)
		{
			contactBoxAdmission.remove(consultantAdmissionButtonsbox);
			contactBoxAdmission.add(patientAdmissionButtonsbox,4);//the component is added to the outer panel 0
			
		}
		if(loginChoice ==3)
		{
			contactBoxAdmission.remove(patientAdmissionButtonsbox);
			contactBoxAdmission.add(consultantAdmissionButtonsbox,4);//the component is added to the outer panel 0
			
		}
		if(loginChoice ==1)//STAFF
		{
			contactBoxAdmission.remove(patientAdmissionButtonsbox);
			contactBoxAdmission.add(consultantAdmissionButtonsbox,3);//the component is added to the outer panel 0
			consultantAdmissionButtonsbox.remove(reinstateDiscahrgePatientAdmissionBttn);
			consultantAdmissionButtonsbox.add(addNewLegacyDocumentBttn);		
		}
		if(systemAdmissionList[currentAdmissionIndex].active==true)
		{
			reinstateDiscahrgePatientAdmissionBttn.setText("Discharge Patient");//the label is geven text to add meaning to the label
			requestReinstatementBttn.setEnabled(false);
			((JLabel) dischargeStatuslbltmp).setText("Current Admission: Active");
			((JLabel) dischargeStatuslbltmp).setForeground( new Color(1) );
			try
			{
				((JLabel) tonalbltmp).setText("Appointment time: "+timeft.format(systemAdmissionList[currentAdmissionIndex].upComingBooking.timeOfNextApp));
			}
			catch(Exception exc)
			{
				((JLabel) tonalbltmp).setText("");
			}
		}
		if(systemAdmissionList[currentAdmissionIndex].active==false)
		{
			reinstateDiscahrgePatientAdmissionBttn.setText("Reinstate Patient");//the label is geven text to add meaning to the label
			requestReinstatementBttn.setEnabled(true);
			((JLabel) donalbltmp).setText("No upcoming appointments");
			((JLabel) dischargeStatuslbltmp).setText("Current Admission: Inactive");
			((JLabel) dischargeStatuslbltmp).setForeground(importantRedColor);
			((JLabel) tonalbltmp).setText("");

		}
		if(systemAdmissionList[currentAdmissionIndex].requested==true)
		{
			requestReinstatementBttn.setEnabled(false);
			
		}			
		Component viewAppointmentsButton=admissiongeneral2Box.getComponent(2);//inner
		if((systemAdmissionList[currentAdmissionIndex].consultantID.equals("PENDING"))||(systemAdmissionList[currentAdmissionIndex].ward.equals("PENDING")))
		{
			((JButton) viewAppointmentsButton).setEnabled(false);
			JOptionPane.showMessageDialog(null, "Please wait for a staff member to assign you a ward and consultant before an appointment is made");
			if(systemAdmissionList[currentAdmissionIndex].active==false)
			{
				requestReinstatementBttn.setEnabled(false);
			}
			return;
		}
		else{
			((JButton) viewAppointmentsButton).setEnabled(true);
		}
		if(systemAdmissionList[currentAdmissionIndex].active==true)
		{
			((JButton) viewAppointmentsButton).setEnabled(true);
		}
		else{
			((JButton) viewAppointmentsButton).setEnabled(false);
		}
	
	}

	














//=========== GLOSSARY OF TERMS ==================
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	public void createGlossaryOfTerms(JTextField searchQueryTextGlossaryFeild)
	{		
		glossaryFrame.add(definitionPanel);//no need to add the panel through the function the only panel needed to be added is the defenition panel
		glossaryFrame.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e)
			{
				jargonLibrarybttn.setEnabled(true);
				jargonLibrarybttn.setBackground(darkButtonGrey);
			}
		});
			Box mainBoxdefenitionwindow = new Box(BoxLayout.Y_AXIS);

		//retrieves definitions
		//temp instance to call read method
		User tempUser = new User();
		//pulls entire file
		String[] listOfDefinitionsStringed = tempUser.rffReturnFullFile("GlossaryOfTerms.txt");//list of definitions is read from file
		int numberOfDefenitions = Integer.parseInt(listOfDefinitionsStringed[0]);
		////System.out.println("There are "+numberOfDefenitions+" defenitions");
		String[][] listOfDefinitions=new String[numberOfDefenitions][2];
		String[] splitDefenittion;//declares the stringed array
		for(int count = 0;count<numberOfDefenitions;count++)//runs a for loop for the number of definitions there are`
		{
			//splits the arraty
			splitDefenittion = listOfDefinitionsStringed[count+1].split("@");
			//adds the split array to the main array
			listOfDefinitions[count]=splitDefenittion;
			////System.out.println(listOfDefinitions[count][0]);
		}
		//finds size
			JComboBox<String> sortComboBoxGlossary = new JComboBox<String>(listOfSortOptions);//declares a combo box that holds the list of available sorting options
		sortComboBoxGlossary.setMinimumSize(new Dimension(120,35));
		sortComboBoxGlossary.setPreferredSize(new Dimension(120,35));
		sortComboBoxGlossary.setMaximumSize(new Dimension(120,35));
		sortComboBoxGlossary.setOpaque(false);
		sortComboBoxGlossary.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				String comboText = sortComboBoxGlossary.getSelectedItem()+"";
					if(comboText.equals("Ascending"))
					{
						
						mainBoxdefenitionwindow.removeAll();
						for (int i = 0; i <numberOfDefenitions; ++i)
						{
							Box defenitionBox = new Box(BoxLayout.LINE_AXIS);
							defenitionBox.setMinimumSize(new Dimension(1000,65));
							defenitionBox.setMaximumSize(new Dimension(1000,1000000000));
							defenitionBox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
							defenitionBox.setOpaque(true);
							
							JTextArea word = new JTextArea();
							word.setText(listOfDefinitions[i][0]);
							word.setFont(buttonFontFormat);
							word.setMinimumSize(new Dimension(300,65));
							word.setMaximumSize(new Dimension(300,1000000000));
							word.setOpaque(true);
							word.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
							word.setWrapStyleWord(true);
							word.setBackground(new Color(-1));
							word.setForeground( new Color(1) );//the foreground of the component is given a white font
							word.setFont(consultantCardFont);//the font that has been declared is attached to the component 
											word.setEditable(false);

							JTextArea defenition = new JTextArea();
							defenition.setEditable(false);
							defenition.setText(listOfDefinitions[i][1]);
							defenition.setMinimumSize(new Dimension(650,65));
							defenition.setMaximumSize(new Dimension(650,1000000000));
							defenition.setFont(symptomfont);
							defenition.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
							defenition.setWrapStyleWord(true);
							
							defenitionBox.add(word);
							defenitionBox.add(defenition);
						
							mainBoxdefenitionwindow.add(defenitionBox);
							
						}
						mainBoxdefenitionwindow.setVisible(false);
						mainBoxdefenitionwindow.setVisible(true);

				}
				else if(comboText.equals("Descending"))
				{
					mainBoxdefenitionwindow.removeAll();
					for (int i = (numberOfDefenitions-1); i >-1; i--)
					{
						
						
						Box defenitionBox = new Box(BoxLayout.LINE_AXIS);
						defenitionBox.setMinimumSize(new Dimension(1000,65));
						defenitionBox.setMaximumSize(new Dimension(1000,1000000000));
						defenitionBox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
						defenitionBox.setOpaque(true);
						
						JTextArea word = new JTextArea();
						word.setText(listOfDefinitions[i][0]);
						word.setFont(buttonFontFormat);
						word.setMinimumSize(new Dimension(300,65));
						word.setMaximumSize(new Dimension(300,1000000000));
						word.setOpaque(true);
						word.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
						word.setWrapStyleWord(true);
						word.setBackground(new Color(-1));
						word.setForeground( new Color(1) );//the foreground of the component is given a white font
						word.setFont(consultantCardFont);//the font that has been declared is attached to the component 
										word.setEditable(false);

						JTextArea defenition = new JTextArea();
						defenition.setEditable(false);
						defenition.setText(listOfDefinitions[i][1]);
						defenition.setMinimumSize(new Dimension(650,65));
						defenition.setMaximumSize(new Dimension(650,1000000000));
						defenition.setFont(symptomfont);
						defenition.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
						defenition.setWrapStyleWord(true);
						
						defenitionBox.add(word);
						defenitionBox.add(defenition);
					
						mainBoxdefenitionwindow.add(defenitionBox);
							
					}
					mainBoxdefenitionwindow.setVisible(false);
					mainBoxdefenitionwindow.setVisible(true);

				}
			}	
		});	
		
		
		//SEARCH FEATURE
		JButton searchLibraryBTTN = new JButton();//declares a button to allow the user to create an account of that type
		searchLibraryBTTN.setMinimumSize(new Dimension(210,35));
		searchLibraryBTTN.setPreferredSize(new Dimension(210,35));
		searchLibraryBTTN.setMaximumSize(new Dimension(210,35));
		searchLibraryBTTN.setForeground( new Color(-1) );//the foreground of the component is given a white font
		searchLibraryBTTN.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		searchLibraryBTTN.setFont(admissionCardFont);//the font that has been declared is attached to the component 
		searchLibraryBTTN.setText("Search Library");
		searchLibraryBTTN.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				User tempUser = new User();
				//search here
				String searchValue = searchQueryTextGlossaryFeild.getText();
				if(searchValue.equals("Search..."))
				{
					JOptionPane.showMessageDialog(null, "Invalid querey, missing.");
					return;
				}
				if(tempUser.typeValidationString(searchValue)==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid querey, Invalid dataType.");
					return;
				}
				if(tempUser.lesserLengthValidation(searchValue,35)==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid querey, Term too long.");
					return;
				}
				
				
				
				int numberOfDefenitionsSerach = 0;
				for(int counter = 0;counter<listOfDefinitions.length;counter++)//runs a for loop for the number of definitions there are`
				{
					if(listOfDefinitions[counter][0].toLowerCase().contains(searchValue.toLowerCase()))
					{
						numberOfDefenitionsSerach++;
					}
				}
				String[][]listOfDefinitionsCopy = new String[numberOfDefenitionsSerach][2];
				numberOfDefenitionsSerach=0;
				for(int count = 0;count<listOfDefinitions.length;count++)//runs a for loop for the number of definitions there are`
				{
					if(listOfDefinitions[count][0].toLowerCase().contains(searchValue.toLowerCase()))
					{
						//System.out.println("Found: "+listOfDefinitions[count][0]);
						listOfDefinitionsCopy[numberOfDefenitionsSerach][0]=listOfDefinitions[count][0];
						listOfDefinitionsCopy[numberOfDefenitionsSerach][1]=listOfDefinitions[count][1];
						numberOfDefenitionsSerach++;
					}
				}
				mainBoxdefenitionwindow.removeAll();
				for (int i = 0; i <listOfDefinitionsCopy.length; ++i)
				{
				
					Box defenitionBox = new Box(BoxLayout.LINE_AXIS);
					defenitionBox.setMinimumSize(new Dimension(1000,65));
					defenitionBox.setPreferredSize(new Dimension(1000,100));
					defenitionBox.setMaximumSize(new Dimension(1000,100));
					defenitionBox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
					defenitionBox.setOpaque(true);
					
					JTextArea word = new JTextArea();
					word.setText(listOfDefinitionsCopy[i][0]);
					word.setFont(buttonFontFormat);
					word.setMinimumSize(new Dimension(300,100));
					word.setPreferredSize(new Dimension(300,100));
					word.setMaximumSize(new Dimension(300,1000000000));
					word.setOpaque(true);
					word.setBackground(new Color(-1));
					word.setForeground( new Color(1) );//the foreground of the component is given a white font
					word.setFont(consultantCardFont);//the font that has been declared is attached to the component 
					word.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
					word.setWrapStyleWord(true);
									word.setEditable(false);

					JTextArea defenition = new JTextArea();
					defenition.setEditable(false);
					defenition.setText(listOfDefinitionsCopy[i][1]);
					defenition.setMinimumSize(new Dimension(650,100));
					defenition.setPreferredSize(new Dimension(650,100));
					defenition.setMaximumSize(new Dimension(650,1000000000));
					defenition.setFont(symptomfont);
					defenition.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
					defenition.setWrapStyleWord(true);
					
					defenitionBox.add(word);
					defenitionBox.add(defenition);
				
					mainBoxdefenitionwindow.add(defenitionBox);
				
				}
				if(listOfDefinitionsCopy.length==0)
				{
					Box defenitionsuggestionBox = new Box(BoxLayout.Y_AXIS);
					defenitionsuggestionBox.setMinimumSize(new Dimension(1000,65));
					defenitionsuggestionBox.setPreferredSize(new Dimension(1000,65));
					defenitionsuggestionBox.setMaximumSize(new Dimension(1000,120));
					defenitionsuggestionBox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
					defenitionsuggestionBox.setOpaque(true);
					defenitionsuggestionBox.setBackground(new Color(-1));
					
					JTextArea suggestion = new JTextArea();
					suggestion.setEditable(false);
					suggestion.setText("   No terms where found containing '"+searchValue+"', Would you like to request the term be added to the library?");
					suggestion.setMinimumSize(new Dimension(1000,0));
					suggestion.setPreferredSize(new Dimension(1000,55));
					suggestion.setMaximumSize(new Dimension(1000,120));
					suggestion.setFont(symptomfont);
					suggestion.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
					suggestion.setWrapStyleWord(true);
					suggestion.setAlignmentX(Component.LEFT_ALIGNMENT);
					//suggestion.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
					
					Box defenitionsuggestionBoxsmall = new Box(BoxLayout.LINE_AXIS);
					defenitionsuggestionBoxsmall.setMinimumSize(new Dimension(310,45));
					defenitionsuggestionBoxsmall.setPreferredSize(new Dimension(310,45));
					defenitionsuggestionBoxsmall.setMaximumSize(new Dimension(310,45));
					defenitionsuggestionBoxsmall.setOpaque(true);
					defenitionsuggestionBoxsmall.setBackground(new Color(-1));
					//defenitionsuggestionBoxsmall.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
					defenitionsuggestionBoxsmall.setAlignmentX(Component.LEFT_ALIGNMENT);

					JButton searchLibraryBTTN = new JButton();//declares a button to allow the user to create an account of that type
					searchLibraryBTTN.setMinimumSize(new Dimension(210,35));
					searchLibraryBTTN.setPreferredSize(new Dimension(210,35));
					searchLibraryBTTN.setMaximumSize(new Dimension(210,35));
					searchLibraryBTTN.setForeground( new Color(-1) );//the foreground of the component is given a white font
					searchLibraryBTTN.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
					searchLibraryBTTN.setFont(admissionCardFont);//the font that has been declared is attached to the component 
					searchLibraryBTTN.setText("Request term");
					//searchLibraryBTTN.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 

					searchLibraryBTTN.addActionListener(new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent e)
						{
							User tempUser = new User();
							String[] listOfDefinitionsStringed = tempUser.rffReturnFullFile("GlossaryOfTerms.txt");//list of definitions is read from file
							int numberOfDefenitions = Integer.parseInt(listOfDefinitionsStringed[0]);
							//System.out.println("There are "+numberOfDefenitions+" defenitions");
							String[][] listOfDefinitions=new String[numberOfDefenitions][2];
							String[] splitDefenittion;//declares the stringed array
							for(int count = 0;count<numberOfDefenitions;count++)//runs a for loop for the number of definitions there are`
							{
								//splits the arraty
								splitDefenittion = listOfDefinitionsStringed[count+1].split("@");
								//adds the split array to the main array
								listOfDefinitions[count]=splitDefenittion;
								////System.out.println(listOfDefinitions[count][0]);
							}
							//Check if there are more than 0 requestedTerms
							if(listOfDefinitionsStringed.length==numberOfDefenitions+1)//plus one is for the inital line counting the defenitions
							{
								listOfDefinitionsStringed = tempUser.extendArrayByOne(listOfDefinitionsStringed);
								listOfDefinitionsStringed[numberOfDefenitions+1] = "1,"+searchValue;
							}
							//else just split up the line and then add to the end
							else{
								String[] tempArrayOfReqestedTerms = listOfDefinitionsStringed[numberOfDefenitions+1].split(",");
								listOfDefinitionsStringed[numberOfDefenitions+1] = (tempArrayOfReqestedTerms.length)+listOfDefinitionsStringed[numberOfDefenitions+1].substring(1,listOfDefinitionsStringed[numberOfDefenitions+1].length())+","+searchValue;
							}
							//System.out.println(listOfDefinitionsStringed[numberOfDefenitions+1]);
							tempUser.writeNewDataTofile(listOfDefinitionsStringed,"GlossaryOfTerms.txt");
							normalLibraryPrintout(numberOfDefenitions,listOfDefinitionsStringed,listOfDefinitions,sortComboBoxGlossary,searchQueryTextGlossaryFeild,mainBoxdefenitionwindow);
						}
						
					});
					
					defenitionsuggestionBox.add(Box.createRigidArea(new Dimension(0,20)));
					defenitionsuggestionBox.add(suggestion);
					defenitionsuggestionBoxsmall.add(Box.createRigidArea(new Dimension(40,0)));
					defenitionsuggestionBoxsmall.add(searchLibraryBTTN);				
					defenitionsuggestionBox.add(defenitionsuggestionBoxsmall);				
					mainBoxdefenitionwindow.add(defenitionsuggestionBox);

				}
				mainBoxdefenitionwindow.setVisible(false);
				mainBoxdefenitionwindow.setVisible(true);
			}	
		});	
		
		//CLEAR FEATURE
		JButton clearsearchLibraryBTTN = new JButton();//declares a button to allow the user to create an account of that type
		clearsearchLibraryBTTN.setMinimumSize(new Dimension(170,35));
		clearsearchLibraryBTTN.setPreferredSize(new Dimension(170,35));
		clearsearchLibraryBTTN.setMaximumSize(new Dimension(170,35));
		clearsearchLibraryBTTN.setForeground( new Color(-1) );//the foreground of the component is given a white font
		clearsearchLibraryBTTN.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		clearsearchLibraryBTTN.setFont(admissionCardFont);//the font that has been declared is attached to the component 
		clearsearchLibraryBTTN.setText("clear Search");
		clearsearchLibraryBTTN.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				normalLibraryPrintout(numberOfDefenitions,listOfDefinitionsStringed,listOfDefinitions,sortComboBoxGlossary,searchQueryTextGlossaryFeild,mainBoxdefenitionwindow);
			}	
		});	
		
		//SORT FEATURE	JComboBox<String> sortComboBoxGlossary = new JComboBox<String>(listOfSortOptions);//declares a combo box that holds the list of available sorting options

	
			//declares searchbox
		Box defenitionSearchBox = new Box(BoxLayout.LINE_AXIS);
	defenitionSearchBox.setSize(1000,65);
		defenitionSearchBox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		defenitionSearchBox.setOpaque(true);
		defenitionSearchBox.setBackground(new Color(-1));
		defenitionSearchBox.setLocation(137,25);
		definitionPanel.add(defenitionSearchBox);
		
		defenitionSearchBox.add(Box.createRigidArea(new Dimension(30,0)));
		defenitionSearchBox.add(sortComboBoxGlossary);
		defenitionSearchBox.add(Box.createRigidArea(new Dimension(5,0)));
		defenitionSearchBox.add(searchQueryTextGlossaryFeild);
		defenitionSearchBox.add(searchLibraryBTTN);
		defenitionSearchBox.add(Box.createRigidArea(new Dimension(5,0)));
		defenitionSearchBox.add(clearsearchLibraryBTTN);
		
		//declares scrollpane
		JScrollPane scrollpanedefenitionwindow = new JScrollPane(mainBoxdefenitionwindow); 
		scrollpanedefenitionwindow.setSize(1000,750);
		scrollpanedefenitionwindow.setLocation(137,100);
		scrollpanedefenitionwindow.getVerticalScrollBar().setUnitIncrement(25);
		scrollpanedefenitionwindow.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		definitionPanel.add(scrollpanedefenitionwindow);
		mainBoxdefenitionwindow.setBackground(darkGreyColour);
		mainBoxdefenitionwindow.setOpaque(true);
		normalLibraryPrintout(numberOfDefenitions,listOfDefinitionsStringed,listOfDefinitions,sortComboBoxGlossary,searchQueryTextGlossaryFeild,mainBoxdefenitionwindow);
	}
	//using the global instace of the system some graphical components are set so that the feilds contain the users data
	//unlike the general method this will be called every time the panel is updated
	//this allows the system to refresh values in realtime and can be called whenever a panel/components needs values upating
	public void normalLibraryPrintout(int numberOfDefenitions,String[] listOfDefinitionsStringed,String[][] listOfDefinitions, JComboBox<String> sortComboBoxGlossary,JTextField searchQueryTextGlossaryFeild,Box mainBoxdefenitionwindow)
	{
		sortComboBoxGlossary.setSelectedItem("Ascending");//Set option by text
		mainBoxdefenitionwindow.removeAll();
		searchQueryTextGlossaryFeild.setText("Search...");
		for(int count = 0;count<numberOfDefenitions;count++)//runs a for loop for the number of definitions there are`
		{
			//splits the arraty
			String[] splitDefenittion = listOfDefinitionsStringed[count+1].split("@");
			//adds the split array to the main array
			listOfDefinitions[count]=splitDefenittion;
			////System.out.println(listOfDefinitions[count][0]);
		}
		for (int i = 0; i <numberOfDefenitions; ++i)
		{
		
		Box defenitionBox = new Box(BoxLayout.LINE_AXIS);
		defenitionBox.setMinimumSize(new Dimension(1000,65));
		defenitionBox.setMaximumSize(new Dimension(1000,1000000000));
		defenitionBox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		defenitionBox.setOpaque(true);
		
		JTextArea word = new JTextArea();
		word.setText(listOfDefinitions[i][0]);
		word.setFont(buttonFontFormat);
		word.setMinimumSize(new Dimension(300,65));
		word.setMaximumSize(new Dimension(300,1000000000));
		word.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
		word.setWrapStyleWord(true);
		word.setOpaque(true);
		word.setBackground(new Color(-1));
		word.setForeground( new Color(1) );//the foreground of the component is given a white font
		word.setFont(consultantCardFont);//the font that has been declared is attached to the component 
				word.setEditable(false);

		JTextArea defenition = new JTextArea();
		defenition.setEditable(false);
		defenition.setText(listOfDefinitions[i][1]);
		defenition.setMinimumSize(new Dimension(650,65));
		defenition.setMaximumSize(new Dimension(650,1000000000));
		defenition.setFont(symptomfont);
		defenition.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
		defenition.setWrapStyleWord(true);
		
		defenitionBox.add(word);
		defenitionBox.add(defenition);
	
		mainBoxdefenitionwindow.add(defenitionBox);
		}
		mainBoxdefenitionwindow.setVisible(false);
		mainBoxdefenitionwindow.setVisible(true);
	}
	//components generation
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createComponentsGlossaryOfTerms()
	{
		
		glossaryFrame.setLayout(new GridLayout(1,1));//initalises the frame and the format
        glossaryFrame.setTitle("Glossary Frame");//sets the title of the frame
		glossaryFrame.setLocation(300,300);//creates an inital ofset to show that the jargon library produces a new window rather than a panel
        glossaryFrame.setSize(1274,913);//Sets the aspect ratio of the intial window
        glossaryFrame.setForeground( new Color(-16777216) );//the component has its font colour changed to a desireable one
		//Declares the colour of the foreground
        glossaryFrame.setBackground( new Color(-2696737) );//
		//Declares the colour of the backgound
        glossaryFrame.setVisible(true);//sets the frame to be visible
		//sets the frame to be visbile along with any panels attached to it
        glossaryFrame.setResizable(false);//allows the frame to be resized
		
		JTextField searchQueryTextGlossaryFeild = new JTextField();
		searchQueryTextGlossaryFeild.setText("Search...");//formatting component to include text
		searchQueryTextGlossaryFeild.setMinimumSize(new Dimension(425,35));
		searchQueryTextGlossaryFeild.setPreferredSize(new Dimension(425,35));
		searchQueryTextGlossaryFeild.setMaximumSize(new Dimension(425,35));
		searchQueryTextGlossaryFeild.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		searchQueryTextGlossaryFeild.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(searchQueryTextGlossaryFeild.getText().equals("Search..."))//selection checking if the text field contains the text prompt
				{
					searchQueryTextGlossaryFeild.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(searchQueryTextGlossaryFeild.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					searchQueryTextGlossaryFeild.setText("Search...");//if satisifed then the prompt text is set again
				}
			}
		});
		
	
		
		
		createGlossaryOfTerms(searchQueryTextGlossaryFeild);
	}
	
	
	//new defenition
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createAddToGlossaryPanelGUI()
	{
		if(loaded[10][0]== false)//selection determines whether the panel is yet to be loaded
		{
			createAddToGlossaryPanelGUIGeneral();//the components of the panel are cereated by running the method this is done initially so it is only done once and will be used throughout
			loaded[10][0]= true;//the variable is set as true to prevent the components from breing reran
		}
		addPanel(glossaryNexDefenitionPanel);
		createTopbar(glossaryNexDefenitionPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
		setNonActiveTopPanel();
		createRequestedTermsBox();
		defenition.setText("");
		termToDefineTF.setText("");
	}
	//this method will intiate all the components for the panel once
	//here sizes and locations are set here
	//no data is set in this method as it would be out of date after further system interations
	//once ran the panels boolean loaded value is set true
	public void createAddToGlossaryPanelGUIGeneral()
	{		

		backToConsultantHomeBttn.setLocation(25,65);//the location of the component is set to the desired part of the panel
		backToConsultantHomeBttn.setSize(250,45);//size of component is set
		backToConsultantHomeBttn.setFont(admissionCardFont);//the component has had its font set to a design with the correct size for its purpose
		backToConsultantHomeBttn.setBackground(darkButtonGrey);//the component has its background set to a desireable colour
		backToConsultantHomeBttn.setForeground(new Color(-1));//the component has its font colour changed to a desireable one
		backToConsultantHomeBttn.setText("Back to Home");//formatting component to include text
		glossaryNexDefenitionPanel.add(backToConsultantHomeBttn);//component is added to the panel
		
		JButton addToLibraryBttn = new JButton();//declares a button to allow the user to create an account of that type
		addToLibraryBttn.setMinimumSize(new Dimension(210,35));
		addToLibraryBttn.setLocation(1150,800);//the location of the component is set to the desired part of the panel
		addToLibraryBttn.setSize(250,45);//size of component is set
		addToLibraryBttn.setForeground( new Color(-1) );//the foreground of the component is given a white font
		addToLibraryBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
		addToLibraryBttn.setFont(admissionCardFont);//the font that has been declared is attached to the component 
		addToLibraryBttn.setText("Add to library");
		addToLibraryBttn.addActionListener(new java.awt.event.ActionListener()
		{
			
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				String newTerm = termToDefineTF.getText();
				String stringedDefinition = defenition.getText();
				User tempUser = new User();
				 
				if(tempUser.presenceValidation(newTerm)==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid Term, missing.");
					return;
				}
				if(tempUser.presenceValidation(stringedDefinition)==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid Defenition, missing.");
					return;
				}
				if(tempUser.typeValidationString(newTerm)==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid Term, not string.");
					return;
				}
				if(tempUser.typeValidationStringOrInt(stringedDefinition)==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid Definition, not string or int.");
					return;
				}
				//pulls entire file
				String[] listOfDefinitionsStringed = tempUser.rffReturnFullFile("GlossaryOfTerms.txt");//list of definitions is read from file
				int numberOfDefenitions = Integer.parseInt(listOfDefinitionsStringed[0]);
				////System.out.println("There are "+numberOfDefenitions+" defenitions");
				String[] termOnly = new String[numberOfDefenitions];//declares the stringed array
				int position=-1;
				//While we get the cahnce to compare every value with the search term we can locate where the item can respectfully go
				for(int count = 0;count<numberOfDefenitions;count++)//runs a for loop for the number of definitions there are`
				{
					//splits the arraty
					termOnly[count] = (listOfDefinitionsStringed[count+1].split("@")[0]);
					//checks it is not already on the system
					if(termOnly[count].toLowerCase().equals(newTerm.toLowerCase()))
					{
						//System.out.println("Term Already on system");
						JOptionPane.showMessageDialog(null, "Sorry,Term was already on the system.");

						return;
					}
					
					//uses a modified version of the primarykey linear search
					
					//check it is between values2
					else if((count-1)>=0)
					{
						if(newTerm.compareToIgnoreCase(termOnly[count])<0)//if the desired location is between index a and index a+1 so is not actually on the system
						{
							if(newTerm.compareToIgnoreCase(termOnly[count-1])>0)
							{
								position = count;//asigns the correct index the item should be located in
								//System.out.println("WILL BE AT INDEX "+position);
							}
						}
					}
				}
				//check first value
				if(newTerm.compareToIgnoreCase(termOnly[0])<0)//selection determining if the value is at the end of the array
				{
					position = 0;//assigns position to first index
					//System.out.println("less than WILL BE AT INDEX "+position);
				}
				//check final value
				if(newTerm.compareToIgnoreCase(termOnly[termOnly.length-1])>0)//selection determining if the value is at the end of the array
				{
					position = termOnly.length;
					//System.out.println("more than WILL BE AT INDEX "+position);
					
				}
				position++;//compensate for the initial index
				
				//now we have the index we can write it to file
				String[] copyOfList = new String[listOfDefinitionsStringed.length+1];
				for(int counter = 0;counter<position;counter++)
				{
					copyOfList[counter]=listOfDefinitionsStringed[counter];
				}
				//we insert the new defentition
				copyOfList[position]=newTerm+"@"+stringedDefinition+"@";
				copyOfList[0]=((Integer.parseInt(copyOfList[0])+1)+"");;
				for(int counter2 = position+1;counter2<copyOfList.length;counter2++)
				{
					copyOfList[counter2]=listOfDefinitionsStringed[counter2-1];
				}
				listOfDefinitionsStringed = copyOfList;		
				for(int counter3 = 0;counter3<listOfDefinitionsStringed.length;counter3++)
				{
					//System.out.println(listOfDefinitionsStringed[counter3]);
				}
				tempUser.writeNewDataTofile(listOfDefinitionsStringed,"GlossaryOfTerms.txt");
								
					
			}
			
		});
		glossaryNexDefenitionPanel.add(addToLibraryBttn);//component is added to the panel
		
		Box defenitionAddingBoxBox = new Box(BoxLayout.LINE_AXIS);
		defenitionAddingBoxBox.setLocation(720,130);//the location of the component is set to the desired part of the panel
		defenitionAddingBoxBox.setSize(600,650);//size of component is set
		defenitionAddingBoxBox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
		defenitionAddingBoxBox.setOpaque(true);
		glossaryNexDefenitionPanel.add(defenitionAddingBoxBox);//component is added to the panel

		JPanel defenitionPanel =  new JPanel(null);
		defenitionPanel.setSize(600,650);//size of component is set
		defenitionPanel.setOpaque(true);
		defenitionPanel.setBackground(darkGreyColour);//the background colour of the component is declared to the desired value 
		defenitionAddingBoxBox.add(defenitionPanel);//component is added to the panel

		JLabel defenitionEntryLBL = new JLabel();//declares a label that is used for the first name
		defenitionEntryLBL.setText("Term:");//formatting component to include text
		defenitionEntryLBL.setFont(buttonFontFormatu);//the component has had its font set to a design with the correct size for its purpose
		defenitionEntryLBL.setSize(100,40);		
		defenitionEntryLBL.setLocation(20,10);//the location of the component is set to the desired part of the panel
		defenitionEntryLBL.setFont(buttonFontFormatu.deriveFont(buttonFontFormatu.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		defenitionEntryLBL.setForeground(new Color(-1));//the component has had its font set to a design with the correct size for its purpose
		defenitionPanel.add(defenitionEntryLBL);//component is added to the panel
	
		termToDefineTF.setText("Please enter the term");//formatting component to include text
		termToDefineTF.setSize(400,40);
		termToDefineTF.setLocation(120,10);//the location of the component is set to the desired part of the panel
		termToDefineTF.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
		termToDefineTF.addFocusListener(new FocusListener()//create a new focus listner to the text field, whenever focus is lost or gained the respective will execute
		{
			public void focusGained(FocusEvent e)//when focus has been gained(text field has been clicked on) this method will run
			{
				if(termToDefineTF.getText().equals("Please enter the term"))//selection checking if the text field contains the text prompt
				{
					termToDefineTF.setText("");//if the condition is satisifed then the field will be cleared to allow the user to input their data
				}
			}
			public void focusLost(FocusEvent e)//again a new method is created that passes through an event when the focus has been lost (when the user has clicked of the text field)
			{
				if(termToDefineTF.getText().equals(""))//checks to see if the user has typed in nothing at all
				{
					termToDefineTF.setText("Please enter the term");//if satisifed then the prompt text is set again
				}
			}
		});
		defenitionPanel.add(termToDefineTF);//component is added to the panel
	
		
		defenition.setEditable(true);
		defenition.setSize(450,490);
		defenition.setFont(symptomfont);
		defenition.setLineWrap(true);//forces the text to be moved to the next line if it it leaves the boundaries
		defenition.setWrapStyleWord(true);
		defenition.setLocation(75,130);//the location of the component is set to the desired part of the panel
		defenitionPanel.add(defenition);//component is added to the panel

		JLabel defenitionLBL = new JLabel();//declares a label that is used for the first name
		defenitionLBL.setText("Definition:");//formatting component to include text
		defenitionLBL.setFont(buttonFontFormatu);//the component has had its font set to a design with the correct size for its purpose
		defenitionLBL.setSize(160,40);		
		defenitionLBL.setLocation(10,65);//the location of the component is set to the desired part of the panel
		defenitionLBL.setFont(buttonFontFormatu.deriveFont(buttonFontFormatu.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
		defenitionLBL.setForeground(new Color(-1));//the component has had its font set to a design with the correct size for its purpose
		defenitionPanel.add(defenitionLBL);//component is added to the panel
		
		
	}
	//this box will hold all the values requested by user on the system, here all the terms are split into rows of three
	//also for the terms an actionlistner is attached to the buttons so when pressed the correct index can be retireved by the system
	public void createRequestedTermsBox()
	{
		
		User tempUser = new User();
		//pulls entire file
		String[] listOfDefinitionsStringed = tempUser.rffReturnFullFile("GlossaryOfTerms.txt");//list of definitions is read from file
		int numberOfDefenitions = Integer.parseInt(listOfDefinitionsStringed[0]);
		//System.out.println("There are "+numberOfDefenitions+" defenitions");
		String[][] listOfDefinitions=new String[numberOfDefenitions][2];
		String[] splitDefenittion;//declares the stringed array
		for(int count = 0;count<numberOfDefenitions;count++)//runs a for loop for the number of definitions there are`
		{
			//splits the arraty
			splitDefenittion = listOfDefinitionsStringed[count+1].split("@");
			//adds the split array to the main array
			listOfDefinitions[count]=splitDefenittion;
			////System.out.println(listOfDefinitions[count][0]);
		}
		
		
		
		outerRequestedTermsBox.setOpaque(true);
		outerRequestedTermsBox.setBackground(darkButtonGrey);
		//outerRequestedTermsBox.setLocation(10,1200); //sets the location of the component 
		outerRequestedTermsBox.setSize(600,650);//the components size is correctly declared
		outerRequestedTermsBox.removeAll();
		desiredTermsScroll.setSize(615,650);//the components size is correctly declared
		desiredTermsScroll.setLocation(40,130); //sets the location of the component 
		desiredTermsScroll.setVisible(true);
		desiredTermsScroll.setOpaque(true);
		desiredTermsScroll.setBackground(darkButtonGrey);
		desiredTermsScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		desiredTermsScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		glossaryNexDefenitionPanel.add(desiredTermsScroll);//component is added to the panel
		
		desiredTermsScroll.getHorizontalScrollBar().setUnitIncrement(16);
		if(numberOfDefenitions+1==listOfDefinitionsStringed.length)//no terms to add
		{
			Box informingBox = new Box(BoxLayout.LINE_AXIS);
			//largeBox.putClientProperty("box order",String.valueOf(i));
			informingBox.setBackground(new Color(-1));
			informingBox.setOpaque(true);
			informingBox.setMinimumSize(new Dimension(400,80));
			informingBox.setPreferredSize(new Dimension(400,80));
			informingBox.setMaximumSize(new Dimension(400,80));
			informingBox.setBorder(BorderFactory.createLineBorder(Color.black));
			
			JLabel informingLBL = new JLabel();//declares a label that is used for the first name
			informingLBL.setText("There are no user Suggestions yet");//formatting component to include text
			informingLBL.setFont(symptomfont);//the component has had its font set to a design with the correct size for its purpose
			informingLBL.setSize(300,40);	
			informingLBL.setMinimumSize(new Dimension(400,60));
			informingLBL.setPreferredSize(new Dimension(400,60));
			informingLBL.setMaximumSize(new Dimension(400,60));	
			informingBox.add(Box.createRigidArea(new Dimension(20,0)));
			informingBox.add(informingLBL);//component is added to the panel
			
			//informingLBL.setFont(buttonFontFormatu.deriveFont(buttonFontFormatu.getStyle() & ~Font.BOLD));//the component has had its font set to a design with the correct size for its purpose
			//informingLBL.setForeground(new Color(-1));//the component has had its font set to a design with the correct size for its purpose
			outerRequestedTermsBox.add(Box.createRigidArea(new Dimension(0,20)));
			outerRequestedTermsBox.add(informingBox);//component is added to the panel
		}
		else{
			
			String[] newTermsToAdd = listOfDefinitionsStringed[listOfDefinitionsStringed.length-1].split(",");
			int numberOfTerms = newTermsToAdd.length-1;
			//System.out.println(numberOfTerms +" terms need to be added");
			
			int currentNoOfTerms = 0;//counter
			int numberOfRows = -1;//needs to be calculated
			int numberOfTermsOutPutted = 3;
			int numOfLeftOverTerms=numberOfTerms%3;//finds the remainder of terms left after dividing through by 3
			//checks to see if the number is divsible by 3 or not 
			if(numOfLeftOverTerms!=0)//if no 
			{
				numberOfRows = (numberOfTerms/3)+1;
			}
			else//if it is a multiple of 3
			{
				numberOfRows =  (numberOfTerms/3);
				
			}
			////System.out.println("There needs to be "+numberOfRows);
			for(int outerRowCounter = 0;outerRowCounter<numberOfRows;outerRowCounter++)
			{
				Box rowBox = new Box(BoxLayout.LINE_AXIS);
				rowBox.putClientProperty("box order",String.valueOf(outerRowCounter));
				rowBox.setMinimumSize(new Dimension(600,75));
				rowBox.setPreferredSize(new Dimension(600,75));
				rowBox.setMaximumSize(new Dimension(600,75));
				rowBox.setOpaque(true);
				rowBox.setBackground(new Color(-1));
				outerRequestedTermsBox.add(rowBox);
				for(int termCounter = 0;termCounter<3;termCounter++)
				{
					if(currentNoOfTerms<numberOfTerms)
					{
						Box termBox = new Box(BoxLayout.Y_AXIS);
						termBox.setMinimumSize(new Dimension(200,75));
						termBox.setPreferredSize(new Dimension(200,75));
						termBox.setMaximumSize(new Dimension(200,75));
						termBox.setOpaque(true);
						termBox.setBackground(new Color(-1));
						termBox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
						rowBox.add(termBox);
						
						JLabel word = new JLabel();
						word.setText(newTermsToAdd[currentNoOfTerms+1]);//the 1 is to compensate for the first number which holds the total number of requested terms
						word.setFont(buttonFontFormat);
						word.setMinimumSize(new Dimension(200,30));
						word.setMaximumSize(new Dimension(200,30));
						word.setOpaque(true);
						word.setBackground(new Color(-1));
						word.setForeground( new Color(1) );//the foreground of the component is given a white font
						word.setFont(symptomfont);//the font that has been declared is attached to the component 
						word.setAlignmentY(Component.CENTER_ALIGNMENT);
						termBox.add(word);

						Box buttonBox = new Box(BoxLayout.LINE_AXIS);
						buttonBox.setMinimumSize(new Dimension(200,40));
						buttonBox.setPreferredSize(new Dimension(200,40));
						buttonBox.setMaximumSize(new Dimension(200,40));
						buttonBox.setOpaque(true);
						buttonBox.setBackground(new Color(-1));
						buttonBox.setBorder(BorderFactory.createLineBorder(Color.black));//a boarder is set around the component to allow for visual aid to help distinguish components 
						buttonBox.setAlignmentY(Component.CENTER_ALIGNMENT);
						termBox.add(buttonBox);
						
						JButton deleteTermBTTN = new JButton();//declares a button to allow the user to create an account of that type
						deleteTermBTTN.setMinimumSize(new Dimension(200,30));
						deleteTermBTTN.setPreferredSize(new Dimension(200,30));
						deleteTermBTTN.setMaximumSize(new Dimension(200,30));
						deleteTermBTTN.putClientProperty("selectBttn order",String.valueOf(currentNoOfTerms));
						deleteTermBTTN.setForeground( new Color(-1) );//the foreground of the component is given a white font
						deleteTermBTTN.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value 
						deleteTermBTTN.setFont(symptomfont);//the font that has been declared is attached to the component 
						deleteTermBTTN.setText("Remove Term");
						buttonBox.add(deleteTermBTTN);
						currentNoOfTerms++;
						deleteTermBTTN.addActionListener(new java.awt.event.ActionListener()
						{
							public void actionPerformed(java.awt.event.ActionEvent e)
							{
								JButton source = (JButton)e.getSource();
								String buttonID = (String) source.getClientProperty("selectBttn order");
								int currentIndexTermLocation =Integer.parseInt(buttonID)+1;//plus 1 moves the index to the correct index as before we decremineted it to compensate for the original value
								//delete starts here
								String[] listOfDefinitionsStringed = tempUser.rffReturnFullFile("GlossaryOfTerms.txt");//list of definitions is read from file
								String[] newTermsToAdd = listOfDefinitionsStringed[listOfDefinitionsStringed.length-1].split(",");
								int numberOfTerms = newTermsToAdd.length-1;//compensate for inital number at index 0
								
								//if there are more than 1 terms left
								if(numberOfTerms>1)
								{//creates a copy of itself  and then removes the index the desired data is located at
									String[] copyOfList = new String[newTermsToAdd.length-1];
									for(int counter = 0;counter<currentIndexTermLocation;counter++)
									{
										copyOfList[counter]=newTermsToAdd[counter];
									}
									for(int counter2 = currentIndexTermLocation;counter2<newTermsToAdd.length-1;counter2++)
									{
										copyOfList[counter2]=newTermsToAdd[counter2+1];
									}
									newTermsToAdd = copyOfList;
									//decrements the number of terms
									newTermsToAdd[0] = ((Integer.parseInt(newTermsToAdd[0])-1)+"");
									String requestedTermsConcatenated = newTermsToAdd[0]+"";
									for(int counter3 = 1;counter3<newTermsToAdd.length;counter3++)
									{
										requestedTermsConcatenated = requestedTermsConcatenated+","+newTermsToAdd[counter3];
									}
									//System.out.println(requestedTermsConcatenated);
									listOfDefinitionsStringed[listOfDefinitionsStringed.length-1]=requestedTermsConcatenated;
								}
								
								//else if only one value is left we need to just remove that part
								else
								{//all it does is copy itself over to a smaller array leaving out the last item
									String[] copyOfListFull = new String[listOfDefinitionsStringed.length-1];
									for(int counter4 = 0;counter4<copyOfListFull.length;counter4++)
									{
										copyOfListFull[counter4]=listOfDefinitionsStringed[counter4];
									}
									listOfDefinitionsStringed = copyOfListFull;
								}
								
								tempUser.writeNewDataTofile(listOfDefinitionsStringed,"GlossaryOfTerms.txt");
								createRequestedTermsBox();
							}	
						});	
					}
					
				}
			}
			
		}
		desiredTermsScroll.setVisible(false);
		desiredTermsScroll.setVisible(true);
		
		
	}




//================ACTION LISTENER===================
	//here for the systems buttons all the action events are situated here 
	//when the button is presssed this method will search for the button then correctly call the code inside the methods
	public void actionPerformed(ActionEvent e)//this is the action listener a method that will pass in events performed by the user
    {
		if(e.getSource()==realLoginbttn)//selection determining the location of the action performed by passing the current event through and locating the source
		//if the action comes from the logon button this code will execute
		{
			//Within each statment an intermediary method are in place these will be used for actually declaring and intialising all the correct fields for the user.
			username =usernameTF.getText();//sets username as feild of text feild
			User tempUSer = new User();
			boolean validated = tempUSer.presenceValidation(username);
			if(validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid username, missing.");
				return;
			}
			validated=tempUSer.typeValidationStringOrInt(username);
			if(validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid username, letters only.");
				return;
			}
			validated=tempUSer.lesserLengthValidation(username,11);
			if(validated==false)
			{
				JOptionPane.showMessageDialog(null, "Invalid username, must be less than 11 characters.");
				return;
			}
			if(validated==true)
			{
				if((username.charAt(0)+"").equals("P")==true)
				{
					loginChoice = 0;
					setUpPatientObj(username);//initalises patient object
					usernameTF.setText("");//built in protection on the system as soon as the user tries to login the username will be cleared 
				}
				else if ((username.charAt(0)+"").equals("S")==true)
				{
					loginChoice = 1;
					setUpStaffObj(username);//initalises patient object
					usernameTF.setText("");//built in protection on the system as soon as the user tries to login the username will be cleared 
				}
				else if ((username.charAt(0)+"").equals("M")==true)
				{
					loginChoice = 2;
					setUpManagmentObj(username);//initalises patient object
					usernameTF.setText("");//built in protection on the system as soon as the user tries to login the username will be cleared 
				}
				else if((username.charAt(0)+"").equals("C")==true)
				{
					loginChoice = 3;
					setUpConsultantObj(username);//initalises patient object
					usernameTF.setText("");//built in protection on the system as soon as the user tries to login the username will be cleared
				}
			}
			loginPasswordF.setText("");//built in protection on the system as soon as the user tries to login the password will be cleared 
		}
		if(e.getSource()==logoutbttn)
		{
			String logoutConifimration = "Are you sure you want to logout?";//confirmation text to ensure this is the option the user wants
			String popUpWindowLogout = "Logout?";//text for frame
			int logoutConfirmationanswer = logoutConfirm.showConfirmDialog(null, logoutConifimration, popUpWindowLogout, logoutConfirm.YES_NO_OPTION);//type of popup is declared along with the answer types
			if (logoutConfirmationanswer == 0)//user wishes to logout
			{
				logout();
			}
			else//wish to stay
			{
				logoutConfirm.setVisible(false);//popup is hidden from sight
			}
			logoutConfirm.setVisible(false);//popup is hidden from sight regardless of choice as it wont dissapear after intialisation 
		}
		if(e.getSource()==admissionbttn)
		{
			
			createadmissionHomepagePanelGUI();
			if(topBarNewAdmissionBttn.isEnabled()==true)
			{
				if(systemAdmissionList.length>0)
				{
					
					buttonList[currentAdmissionIndex].setEnabled(false);
					buttonList[currentAdmissionIndex].setBackground(selectedBttcColour);//the background colour of the component is declared to the desired value
					topBarNewAdmissionBttn.setEnabled(true);
					topBarNewAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
					
				}
				else
				{
					createBlankAdmissionHomePage();
				}
			}	
		}
		if(e.getSource()==homebttn)
		{
			setActiveTopPanelBttn(homebttn); //active top bar is set to the button just pressed
			
			if(loginChoice ==0)//selection determining if this user is this type of user
			{
				createPatientHomepagePanelGUI();//correct homepage is created
				patientHomepagePanel.setVisible(false);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
				patientHomepagePanel.setVisible(true);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
				emptyArray(patientHomepagePanel);
				createNotificationsPatient();
				//System.out.println("patientHomepagePanel");
			}
			if(loginChoice ==1)//selection determining if this user is this type of user
			{
				createStaffHomepagePanelGUI();
				adminHomepagePanel.setVisible(false);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
				adminHomepagePanel.setVisible(true);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
					admissionPatientbttn.setEnabled(false);//the component is disabled
				patientDemographicbttn.setEnabled(false);//the component is disabled
				
				
			}
			if(loginChoice ==3)//selection determining if this user is this type of user
			{
				createConsultantHomepagePanelGUI();//correct homepage is created
				consultantHomepagePanel.setVisible(false);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
				consultantHomepagePanel.setVisible(true);//the panel is set to re-render the panel, a bug causes it to hide, this somehow fixes it
				admissionPatientbttn.setEnabled(false);//the component is disabled
				patientDemographicbttn.setEnabled(false);//the component is disabled
			}			
		}
		if(e.getSource()==demographicbttn)
		{
			////System.out.println("demographicHomepagePanel");
			createdemographicHomepagePanelGUI();//correct homepage is created
			setActiveTopPanelBttn(demographicbttn);
			admissionPatientbttn.setEnabled(false);//the component is disabled
			patientDemographicbttn.setEnabled(false);//the component is disabled
			outputPanelList();
		}
		if(e.getSource()==jargonLibrarybttn)
		{
		createComponentsGlossaryOfTerms();
		jargonLibrarybttn.setEnabled(false);//the component is disabled as it is currently being on that panel 
		jargonLibrarybttn.setBackground(selectedBttcColour);//the background colour of the component is declared to the desired value 
	

		}
		notificationloop:
		if(e.getSource()==notificationbttn)
		{
			createNotificationstab();
			if((panelOrder[endPanelPointer]==loginPanel)==false)
			{
				//System.out.println("Comp moved");
				Point p = new Point(notificationbttn.getLocationOnScreen());
				p.translate(330,40);
				displayWindow.setLocation(p);
			}		
			if(displayWindow.isVisible()==true)
			{
				//System.out.println("Window Hidden");
				displayWindow.setVisible(false);//the component is set so it cant be seen
				//displayWindow.repaint();
				open=false;
				break notificationloop;
			}
			if(displayWindow.isVisible()==false)
			{
				//System.out.println("Window shown");
				displayWindow.setVisible(true);
				//displayWindow.repaint();
				open=true;
				break notificationloop;
				
			}
		}
		if(e.getSource()== topBarNewAdmissionBttn)
		{
	
			createNewAdmissionPanel();
		}
		if(e.getSource()==backToAdmissionbttn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			
			if(systemAdmissionList.length>0)
			{
				removePanel();
				createadmissionHomepagePanelGUI();//the method containing all the new components is ran
				buttonList[currentAdmissionIndex].setEnabled(false);
				buttonList[currentAdmissionIndex].setBackground(selectedBttcColour);//the background colour of the component is declared to the desired value
				topBarNewAdmissionBttn.setEnabled(true);
				topBarNewAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
				
			}
			else
			{
				removePanel();
				createBlankAdmissionHomePage();
				outputPanelList();
			}
			
			setActiveTopPanelBttn(admissionbttn);
		}
	
		//this is called after all the admisssion values are entered this will check all the values and validate them checking whether they can be used or not
		//if they are invalid the next panel is not loaded
		//if it is the panel is created and the instance of admission is kept
		if(e.getSource()==SymptomConfirmBttn)
		{
			boolean validated = true;
			tmpAdmission = new Admission();
			tmpAdmission.listOfSymptoms[0] = symptom1TF.getText();//sets attribute for object from component
			tmpAdmission.listOfSymptoms[1] = symptom2TF.getText();//sets attribute for object from component
			tmpAdmission.listOfSymptoms[2] = symptom3TF.getText();//sets attribute for object from component
			tmpAdmission.listOfSymptoms[3] = symptom4TF.getText();//sets attribute for object from component
			for(int counterSymptoms = 0;counterSymptoms<4;counterSymptoms++)
			{
				if(tmpAdmission.listOfSymptoms[counterSymptoms].contains("Symptom"))
				{
					tmpAdmission.listOfSymptoms[counterSymptoms]="";	
				}
			}
			
			areaArrayCounter = 0;
			if(cbHips.isSelected()==true)//selection determining if the checkbox had been selected
			{
				tmpAdmission.listOfAreasAffected[areaArrayCounter]="Hips";
				areaArrayCounter++;
			}
			if(cbShoulder.isSelected()==true)//selection determining if the checkbox had been selected
			{
				tmpAdmission.listOfAreasAffected[areaArrayCounter]="Shoulder";
				areaArrayCounter++;


			}
			if(cbNeck.isSelected()==true)//selection determining if the checkbox had been selected
			{
				tmpAdmission.listOfAreasAffected[areaArrayCounter]="Neck";
				areaArrayCounter++;

				

			}
			if(cbChest.isSelected()==true)//selection determining if the checkbox had been selected
			{
				tmpAdmission.listOfAreasAffected[areaArrayCounter]="Chest";
				areaArrayCounter++;

				

			}
			if(cbHand.isSelected()==true)//selection determining if the checkbox had been selected
			{
				tmpAdmission.listOfAreasAffected[areaArrayCounter]="Hand";
				areaArrayCounter++;

				

			}
			if(cbFoot.isSelected()==true)//selection determining if the checkbox had been selected
			{
				tmpAdmission.listOfAreasAffected[areaArrayCounter]="Foot";
				areaArrayCounter++;

			

			}
			if(cbHead.isSelected()==true)//selection determining if the checkbox had been selected
			{
				tmpAdmission.listOfAreasAffected[areaArrayCounter]="Head";
				areaArrayCounter++;

			

			}
			if(cbAbdomen.isSelected()==true)//selection determining if the checkbox had been selected
			{
				tmpAdmission.listOfAreasAffected[areaArrayCounter]="Abdomen";
				areaArrayCounter++;
			

			}
			if(cbForeArm.isSelected()==true)//selection determining if the checkbox had been selected
			{
				tmpAdmission.listOfAreasAffected[areaArrayCounter]="Forearm";
				areaArrayCounter++;


			}
			if(cbArm.isSelected()==true)//selection determining if the checkbox had been selected
			{
				tmpAdmission.listOfAreasAffected[areaArrayCounter]="Arm";
				areaArrayCounter++;


			}
			if(cbPelvis.isSelected()==true)//selection determining if the checkbox had been selected
			{
				tmpAdmission.listOfAreasAffected[areaArrayCounter]="Pelvis";
				areaArrayCounter++;


			}
			if(cbLeg.isSelected()==true)//selection determining if the checkbox had been selected
			{
				tmpAdmission.listOfAreasAffected[areaArrayCounter]="Leg";
				areaArrayCounter++;


			}
			
			
			if(cbFrequentRecurringPains.isSelected()==true)
			{
				tmpAdmission.typeOfPain ="Frequent recurring Pains";
				
			}
			if(cbStiffnessInMuscle.isSelected()==true)
			{
				tmpAdmission.typeOfPain = "Stiffness in muscle";
				
			}
			if(cbAcutePains.isSelected()==true)
			{
				tmpAdmission.typeOfPain = "Acute Pains";
				
			}
			if(cbChronicPains.isSelected()==true)
			{
				tmpAdmission.typeOfPain = "Chronic pains";
				
			}
			validated = tmpAdmission.validateAdmission(tmpAdmission);
			if(validated == true)
			{
				createSymptomRecomendationPanel();//calls method which displays panel for the correct user
			}
		}
		
		//this button will return the user back to the admission selection panel
		//this is located on the symtpom recomendation panel, and is a way of the user returning back to the last panel
		if(e.getSource()==symptomRecomendationBackToExpertSystem)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			removePanel();
			createTopbar(newAdmissionPanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 

		}
		
		
		
		//Creates New Admission, this calls the method which creates the admission and writes it to file
		if(e.getSource()==symptomRecomendationNewAdmissionBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			createAdmission();
		}	
		
		
		if(e.getSource()==appointmentGoBackBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			try
			{
				if(panelOrder[endPanelPointer-1]==patientHomepagePanel)
				{
				createPatientHomepagePanelGUI();
				}
				else if(panelOrder[endPanelPointer-1]==admissionHomepagePanel)
				{
				createadmissionHomepagePanelGUI();
				}
				if(panelOrder[endPanelPointer-1]==consultantHomepagePanel)
				{
				createConsultantHomepagePanelGUI();
				}
				buttonBar.setVisible(true);
			}
			catch(Exception exc)
			{
				
			}
		}
		
		
		if(e.getSource()==viewAdmissionGoBackBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
						createadmissionHomepagePanelGUI();

						
		}
		if(e.getSource()==backToAdmissionsBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			createadmissionHomepagePanelGUI();
			setActiveAdmissionPanelBttn(buttonList[currentAdmissionIndex]);
			scrollpaneNotitificationstab.repaint();
			
						
		}
		
		if(e.getSource()==archiveAdmissionBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			//HERE WE WANT TO REMOVE THE ADMISSION FROM THE LIST
			//systemAdmissionList.length--;//incs num of admissions
			currentAdmissionIndex = 0;;//sets current admission as the newest one
			if(systemAdmissionList.length==0)
			{
				createBlankAdmissionHomePage();
			}
			
			buttonBar.removeAll();
			createButtonTopBarAdmission();
			topBarNewAdmissionBttn.setEnabled(true);
			topBarNewAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
			if(systemAdmissionList.length>0)
			{
				createadmissionHomepagePanelGUI();//loads admission gui
				buttonList[currentAdmissionIndex].setEnabled(false);
				buttonList[currentAdmissionIndex].setBackground(selectedBttcColour);//the background colour of the component is declared to the desired value
			
			}

			
		}
		
		if(e.getSource()==nextDocumentBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			currentDocumentIndex++;
			createSpecificDocument();
		}
		if(e.getSource()==priorDocumentBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			currentDocumentIndex--;
			createSpecificDocument();
		}
		if(e.getSource()==printDocumentBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			createSpecificDocument();
		}
		
		if(e.getSource()==patientDemographicbttn)
		{
			createdemographicHomepagePanelGUI(userPatient);
			setActiveTopPanelBttn(patientDemographicbttn);

		}
		if(e.getSource()==admissionPatientbttn)
		{
			createadmissionHomepagePanelGUI();
			
		}
		if(e.getSource()==addToJargonBttn)
		{
			createAddToGlossaryPanelGUI();
			admissionPatientbttn.setEnabled(false);//the component is disabled
				patientDemographicbttn.setEnabled(false);//the component is disabled
		}
		if(e.getSource()==backToConsultantHomeBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			removePanel();
			createTopbar(consultantHomepagePanel);//the top multiuse bar is then created, this is isolated from other components as it will be needed for different pannels 
			setActiveTopPanelBttn(homebttn);
			admissionPatientbttn.setEnabled(false);//the component is disabled
				patientDemographicbttn.setEnabled(false);//the component is disabled
		}
		if(e.getSource()==returnBackToAdmissionbttn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			
			if(systemAdmissionList.length>0)
			{
				removePanel();
				createadmissionHomepagePanelGUI();//the method containing all the new components is ran
				buttonList[currentAdmissionIndex].setEnabled(false);
				buttonList[currentAdmissionIndex].setBackground(selectedBttcColour);//the background colour of the component is declared to the desired value
				topBarNewAdmissionBttn.setEnabled(true);
				topBarNewAdmissionBttn.setBackground(darkButtonGrey);//the background colour of the component is declared to the desired value
				
			}
			else
			{
				removePanel();
				createBlankAdmissionHomePage();
				outputPanelList();
			}
			
			setActiveTopPanelBttn(admissionbttn);
		}
	





		if(e.getSource()==addNewLegacyDocumentBttn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			createAddLegacyDocumentPanel();
			
		}
		if(e.getSource()==returnBackToadmissionPage)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			
					createadmissionHomepagePanelGUI();//the method containing all the new components is ran

			
			
		}
		
		//creates A new Document
		if(e.getSource()==createNewDocumentButton)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			createDocumentInstance();
		}
		if(e.getSource()==demographicDisablitiesPromptLbl)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			if(demographicDisablitiesPromptLbl.isSelected()==false)
			{
				Component disabilitiesTa=disabilitiesBox.getComponent(1);//inner
				((JTextArea) disabilitiesTa).setEditable(false);
				((JTextArea) disabilitiesTa).setBackground(lightGreyColor);
				((JTextArea) disabilitiesTa).setText("");
				Component disabilitiescarer=disabilitiesBox.getComponent(2);//inner
				((JRadioButton) disabilitiescarer).setEnabled(false);
				((JRadioButton) disabilitiescarer).setSelected(false);
				Component disabilitiesTranslator=disabilitiesBox.getComponent(3);//inner
				((JRadioButton) disabilitiesTranslator).setEnabled(false);
				((JRadioButton) disabilitiesTranslator).setSelected(false);
				
			
			}
			if(demographicDisablitiesPromptLbl.isSelected()==true)
			{
				Component disabilitiesTa=disabilitiesBox.getComponent(1);//inner
				((JTextArea) disabilitiesTa).setEditable(true);
				((JTextArea) disabilitiesTa).setBackground(new Color(-1));
				Component disabilitiescarer=disabilitiesBox.getComponent(2);//inner
				((JRadioButton) disabilitiescarer).setEnabled(true);
				Component disabilitiesTranslator=disabilitiesBox.getComponent(3);//inner
				((JRadioButton) disabilitiesTranslator).setEnabled(true);
			
			}
			
		}
			 
		if(e.getSource()==createAccountbbtn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			createNewPatientHomepagePanelGUI();
		
		}
		
		//method which creates account
		//when the user decides they want the paient account created they select this
		//this is located on the demographica panel on the new patient panel
		if(e.getSource()==newAccountcreateAccountbbtn)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			createPatient();
		}
	
		//this button will return the user back to the login panel
		//this is located on the patient creation panel, and is a way of the user returning back to the last panel
		if(e.getSource()==newPatientBackToLogin)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			removePanel();
		}
		
		//update Demographic
		//when updating the demographic depending on the user type the correct method is ran
		if(e.getSource()==saveDemographicChanges)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			if(loginChoice ==0)
			{
			updatePateint();
			}
			if(loginChoice ==3)
			{
			updateConsultant();
			}
			if(loginChoice==1)
			{
				updateStaff();
			}
		}

		if(e.getSource()==cbLeg)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			if(cbLeg.isSelected()==true)//selection determining if the checkbox had been selected
			{
				humanImageLegsSelected.setVisible(true);
			}
			if(cbLeg.isSelected()==false)//selection determining if the checkbox had been selected
			{
				humanImageLegsSelected.setVisible(false);
			}
		}
		if(e.getSource()==cbPelvis)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			if(cbPelvis.isSelected()==true)//selection determining if the checkbox had been selected
			{
				humanImagePelvisSelected.setVisible(true);

			}
			if(cbPelvis.isSelected()==false)//selection determining if the checkbox had been selected
			{
				humanImagePelvisSelected.setVisible(false);

			}
		}
		
		if(e.getSource()==cbArm)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			if(cbArm.isSelected()==true)//selection determining if the checkbox had been selected
			{
				humanImageRightArmSelected.setVisible(true);
				humanImageLeftArmSelected.setVisible(true);


			}
			if(cbArm.isSelected()==false)//selection determining if the checkbox had been selected
			{
							
				humanImageRightArmSelected.setVisible(false);
				humanImageLeftArmSelected.setVisible(false);
			}
		}
		
		if(e.getSource()==cbForeArm)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			if(cbForeArm.isSelected()==true)//selection determining if the checkbox had been selected
			{
				humanImageRightforeArmSelected.setVisible(true);
			humanImageLeftForeArmSelected.setVisible(true);
				

			}

			if(cbForeArm.isSelected()==false)//selection determining if the checkbox had been selected
			{
				humanImageRightforeArmSelected.setVisible(false);
			humanImageLeftForeArmSelected.setVisible(false);
				

			}
		}
		
		if(e.getSource()==cbAbdomen)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			if(cbAbdomen.isSelected()==true)//selection determining if the checkbox had been selected
			{
				humanImageAbdomenSelected.setVisible(true);

			}
			if(cbAbdomen.isSelected()==false)//selection determining if the checkbox had been selected
			{
				humanImageAbdomenSelected.setVisible(false);

			}
		}
		
		if(e.getSource()==cbHead)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			if(cbHead.isSelected()==true)//selection determining if the checkbox had been selected
			{
				humanImageHeadSelected.setVisible(true);

			}
			if(cbHead.isSelected()==false)//selection determining if the checkbox had been selected
			{
				humanImageHeadSelected.setVisible(false);

			}
		}
		
		if(e.getSource()==cbFoot)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			if(cbFoot.isSelected()==true)//selection determining if the checkbox had been selected
			{
				humanImageFootSelected.setVisible(true);

			}
			
			if(cbFoot.isSelected()==false)//selection determining if the checkbox had been selected
			{
				humanImageFootSelected.setVisible(false);

			}
		}
		
		if(e.getSource()==cbHand)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			if(cbHand.isSelected()==true)//selection determining if the checkbox had been selected
			{
				
				humanImageLeftHandSelected.setVisible(true);
				humanImageRightHandSelected.setVisible(true);

			}
			if(cbHand.isSelected()==false)//selection determining if the checkbox had been selected
			{
				
				humanImageLeftHandSelected.setVisible(false);
				humanImageRightHandSelected.setVisible(false);

			}
		}
		
		if(e.getSource()==cbChest)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			if(cbChest.isSelected()==true)//selection determining if the checkbox had been selected
			{
				humanImageChestSelected.setVisible(true);

			}
			if(cbChest.isSelected()==false)//selection determining if the checkbox had been selected
			{
				humanImageChestSelected.setVisible(false);

			}
		}
		
		if(e.getSource()==cbNeck)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			if(cbNeck.isSelected()==true)//selection determining if the checkbox had been selected
			{
				humanImageneckSelected.setVisible(true);

			}
			
			if(cbNeck.isSelected()==false)//selection determining if the checkbox had been selected
			{
				humanImageneckSelected.setVisible(false);

			}
		}
		
		if(e.getSource()==cbShoulder)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			if(cbShoulder.isSelected()==true)//selection determining if the checkbox had been selected
			{
				humanImageLeftShoulderSelected.setVisible(true);
				humanImageRightShoulderSelected.setVisible(true);

			}
			
			if(cbShoulder.isSelected()==false)//selection determining if the checkbox had been selected
			{
				humanImageLeftShoulderSelected.setVisible(false);
				humanImageRightShoulderSelected.setVisible(false);

			}
		}
		if(e.getSource()==cbHips)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			if(cbHips.isSelected()==true)//selection determining if the checkbox had been selected
			{
				humanImageHipsSelected.setVisible(true);

			}
			
			if(cbHips.isSelected()==false)//selection determining if the checkbox had been selected
			{
				humanImageHipsSelected.setVisible(false);

			}
		}
		
		//update admission
		if(e.getSource()==updateAdmission)//selection determining the location of the action performed by passing the current event through and locating the source
		{
			updateAdmission();
		}
	
		//createBooking
		//this will create/update bookings on the system, depending on the current typw of booking (new or ezxisting)
		//the method will determine whether the system updates the booking (by deleting it then creating a new one)
		//or just create a new booking
		if(e.getSource()==appointmentcreateBookingBttn)
		{
			//chance if a booking does not exist it could throw a null instance 
			try
			{
				if(systemAdmissionList[currentAdmissionIndex].upComingBooking.newBooking==true)
				{
					boolean made =createBookingInstance();
						userPatient = userPatient = userPatient.createNewNotification(userPatient,"A new booking has been made for Admission "+systemAdmissionList[currentAdmissionIndex].admissionID);
				}
				else if(systemAdmissionList[currentAdmissionIndex].upComingBooking.newBooking==false)
				{
					//old booking found#
					//before the old booking is deleted we need to validate that the data is correct for the booking
					//to this we are going to readf the instances of the GUI early and perform a validation check
					//this prevenets the the system from deleting the booking then going to create a new one to replace it but then get rejected because a field is null, so now the patient has no booking
				
					
					Booking newbooking = new Booking();
					boolean bookingValidated = true;
		
					//before assigned needs to check that it is validated (Not in the past)
					Component timeBookingBox=timeBookingBoxBooking.getComponent(0);//declares local copy of the time picker
					Component dateBookingBox=dateBookingBoxBooking.getComponent(0);//inner
					
					
					LocalDate localDateData = ((DatePicker) dateBookingBox).getDate();
					LocalTime localTimeData = ((TimePicker) timeBookingBox).getTime();//gets the local time of the timepicker
					//here we have to have a custom validation for just the dates checking they have been entered
					bookingValidated = newbooking.validatebooking(localDateData,localTimeData);
					
					//CONVERTS LOCAL DATE TO DATE
					Date date = Date.from(localDateData.atStartOfDay(ZoneId.systemDefault()).toInstant());
					//CONVERTS LOCAL TIME TO DATE (ACTUAL DAY DOENST MATTER)
					//attempts to convert localtime into date
					//What it does is create an instant of the local time and combines this with any date(Will be hid)
					Instant instant = localTimeData.atDate(LocalDate.of(0000, 01, 01)).atZone(ZoneId.systemDefault()).toInstant();
					Date time = Date.from(instant);
					
					Component roomBookingBox=roomBoxBooking.getComponent(3);//inner
					newbooking.room = ((JTextField) roomBookingBox).getText()+"";

					
					bookingValidated =newbooking.validatebooking(newbooking);
				
					if((loginChoice!=0)&&(newbooking.room.equals("PENDING")))
					{
						JOptionPane.showMessageDialog(null, "A room needss to be assigned to the booking.");
					}
					if(bookingValidated==true)
					{
						systemAdmissionList[currentAdmissionIndex].upComingBooking.deleteBooking(systemAdmissionList[currentAdmissionIndex],userPatient);
						refreshEntityInstances();
							
						boolean made =createBookingInstance();
							userPatient = userPatient = userPatient.createNewNotification(userPatient,"The booking for Admission "+systemAdmissionList[currentAdmissionIndex].admissionID+" has been updated");
					}
				}
			}
			catch(Exception exc)
			{
				//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n\n");
					//System.out.println("Emty booking instance");
					//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n\n");
					
				boolean made =createBookingInstance();
					userPatient = userPatient = userPatient.createNewNotification(userPatient,"A new booking has been made for Admission "+systemAdmissionList[currentAdmissionIndex].admissionID);
				if(made ==true)
				{
				systemAdmissionList[currentAdmissionIndex].upComingBooking.newBooking = false;
				createadmissionHomepagePanelGUI();//the method containing all the new components is ran
				setActiveAdmissionPanelBttn(buttonList[currentAdmissionIndex]);
				}
			}
			
		}
	
		if(e.getSource()==appointmentCancelBookingBttn)
		{
			systemAdmissionList[currentAdmissionIndex].upComingBooking.deleteBooking(systemAdmissionList[currentAdmissionIndex],userPatient);
			userPatient = userPatient = userPatient.createNewNotification(userPatient,"The booking for Admission "+systemAdmissionList[currentAdmissionIndex].admissionID+" has been canceled");

			refreshEntityInstances();
			
							
			createadmissionHomepagePanelGUI();//the method containing all the new components is ran
			setActiveAdmissionPanelBttn(buttonList[currentAdmissionIndex]);
		}
	
		//button used to create a legacy document
		//calls the method which creates the document
		if(e.getSource()==createLegacyDocumentButton)
		{
			
			createLegacyDocument();
			
		}
		
		if(e.getSource()==buttonSelectfilePathBttn)
		{
			int returnValue = legacyFileChooser.showOpenDialog(null);
			// int returnValue = jfc.showSaveDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = legacyFileChooser.getSelectedFile();
			actualFilePathTF.setText(selectedFile.getAbsolutePath());
			}
		}



		//search bar functions theses have been made specifically for the search/sort box and specify each individual type below
		//it works differently depending on the data in the list, if the list has not been filtered by the systems sort feature it just runs a standard algorithm which assigns the index depending on the itms location in the array
		//if a search occurs on the data we can perform a quicksort on the data which can order the data in ascendning or descending orders
		//after this the list of indexes is returned to the respective method where they are shown
		if(e.getSource()==sortComboBox)
		{
			try
			{
				if(searchBox==((Box)searchBoxOuter.getComponent(0)))
				{
					String comboText = sortComboBox.getSelectedItem()+"";
					//Ascending order
					if((comboText.equals("Ascending"))&&(listOfsortedDocumentIndexes.length!=0))
					{
						listOfsortedDocumentIndexes=userPatient.quickSort(listOfsortedDocumentIndexes.length-1,0,listOfsortedDocumentIndexes);
						//System.out.println("Ascending sort");
					}
					else if((comboText.equals("Ascending"))&&(listOfsortedDocumentIndexes.length==0))
					{
						listOfsortedDocumentIndexes = orderArrayASC(systemAdmissionList[currentAdmissionIndex].listOfDocuments.length);
						//System.out.println("Ascending sort");
					}
					
					
					else if(comboText.equals("Descending")&&(listOfsortedDocumentIndexes.length!=0))
					{	
						//System.out.println("Descending sort");					
						listOfsortedDocumentIndexes=userPatient.quickSortDESC(listOfsortedDocumentIndexes.length-1,0,listOfsortedDocumentIndexes);
					}
					else if(comboText.equals("Descending")&&(listOfsortedDocumentIndexes.length==0))
					{
						listOfsortedDocumentIndexes = orderArrayDESC(systemAdmissionList[currentAdmissionIndex].listOfDocuments.length);
						//System.out.println("Descending sort");
					}
					createAdmissionDocumentpanel(listOfsortedDocumentIndexes);
				}
			}
			catch(Exception exc){}
			try
			{
				if(searchBox==((Box)searchBoxOuterConsultant.getComponent(0)))
				{
					String comboText1 = sortComboBox.getSelectedItem()+"";
					if((comboText1.equals("Ascending"))&&(listOfsortedconsultantPatientIndexes.length!=0))
					{
						listOfsortedconsultantPatientIndexes=userConsultant.quickSort(listOfsortedconsultantPatientIndexes.length-1,0,listOfsortedconsultantPatientIndexes);
						//System.out.println("Ascending sort");

					}
					else if((comboText1.equals("Ascending"))&&(listOfsortedconsultantPatientIndexes.length==0))
					{
						listOfsortedconsultantPatientIndexes = orderArrayASC(userConsultant.consultantPatientList.length);
						//System.out.println("Ascending sort");

					}
					else if((comboText1.equals("Descending"))&&(listOfsortedconsultantPatientIndexes.length!=0))
					{
						listOfsortedconsultantPatientIndexes=userConsultant.quickSortDESC(listOfsortedconsultantPatientIndexes.length-1,0,listOfsortedconsultantPatientIndexes);
						//System.out.println("Descending sort");
					}
					else if((comboText1.equals("Descending"))&&(listOfsortedconsultantPatientIndexes.length==0))
					{
						listOfsortedconsultantPatientIndexes = orderArrayDESC(userConsultant.consultantPatientList.length);
						//System.out.println("Descending sort");
					}
					createConsultantPatientpanel(listOfsortedconsultantPatientIndexes);
				}
			}
			catch(Exception exc){}
			try
			{
				if(searchBox==((Box)searchBoxOuterStaff.getComponent(0)))
				{
					String comboText2 = sortComboBox.getSelectedItem()+"";
					if((comboText2.equals("Ascending"))&&(listOfsortedstaffPatientIndexes.length!=0))
					{
						listOfsortedstaffPatientIndexes=userStaff.quickSort(listOfsortedstaffPatientIndexes.length-1,0,listOfsortedstaffPatientIndexes);
						
						//System.out.println("Ascending sort");

					}
					else if((comboText2.equals("Ascending"))&&(listOfsortedstaffPatientIndexes.length==0))
					{
						listOfsortedstaffPatientIndexes = orderArrayASC(userStaff.patientIDs.length);
						//System.out.println("Ascending sort");

					}
					else if((comboText2.equals("Descending"))&&(listOfsortedstaffPatientIndexes.length!=0))
					{
						listOfsortedstaffPatientIndexes=userStaff.quickSortDESC(listOfsortedstaffPatientIndexes.length-1,0,listOfsortedstaffPatientIndexes);
						//System.out.println("Descending sort");

					}
					else if((comboText2.equals("Descending"))&&(listOfsortedstaffPatientIndexes.length==0))
					{
						listOfsortedstaffPatientIndexes = orderArrayDESC(userStaff.patientIDs.length);
						//System.out.println("Descending sort");

					}
					createStaffPatientpanel(listOfsortedstaffPatientIndexes);
				}
			}
			catch(Exception exc){}
		}


		if(e.getSource()==clearButton)
		{ 
			try
			{
				if(searchBox==((Box)searchBoxOuter.getComponent(0)))
				{
					//System.out.println("clear occured in admission");
					 listOfsortedDocumentIndexes = new int[0];
					createAdmissionDocumentpanel(listOfsortedDocumentIndexes);
				}
			}
			catch(Exception exc){}
			try
			{
				if(searchBox==((Box)searchBoxOuterConsultant.getComponent(0)))
				{
					//System.out.println("clear occured in consutlant");	
					listOfsortedconsultantPatientIndexes = new int[0];
					createConsultantPatientpanel(listOfsortedconsultantPatientIndexes);

				}
			}
			catch(Exception exc){}
			try
			{
				if(searchBox==((Box)searchBoxOuterStaff.getComponent(0)))
				{
					//System.out.println("clear occured in staff");	
					listOfsortedstaffPatientIndexes = new int[0];
					createStaffPatientpanel(listOfsortedstaffPatientIndexes);
				}
			}
			catch(Exception exc){}
		}
		
		if(e.getSource()==searchListButton)
		{
			try
			{
				if(searchBox==((Box)searchBoxOuter.getComponent(0)))
				{
					//System.out.println("search occured in admission");	
					
					
					String[] entireFile = new String[systemAdmissionList[currentAdmissionIndex].listOfDocuments.length];
					Document tempDocument = new Document();
					for(int counter = 0;counter<systemAdmissionList[currentAdmissionIndex].listOfDocuments.length;counter++)
					{
						tempDocument=systemAdmissionList[currentAdmissionIndex].listOfDocuments[counter];
						entireFile[counter]=tempDocument.documentID+tempDocument.docType+tempDocument.legacyDocument+tempDocument.numberOfPages+ftTimeInc.format(tempDocument.dateOfDocumentCreation)+"";
						//System.out.println(entireFile[counter]);
					}
					listOfsortedDocumentIndexes = itemSearch(entireFile,searchQueryTextFeild.getText());
					createAdmissionDocumentpanel(listOfsortedDocumentIndexes);
				}
			}
			catch(Exception exc){}
			try
			{
				if(searchBox==((Box)searchBoxOuterConsultant.getComponent(0)))
				{
					//System.out.println("search occured in consutlant");	
					String[] entireFile = new String[userConsultant.consultantPatientList.length];
					for(int counter = 0;counter<userConsultant.consultantPatientList.length;counter++)
					{
						String[] fileContents = userConsultant.rffReturnFullFile(userConsultant.consultantPatientList[counter]+"_file.txt");//concatinates the string into an array
						entireFile[counter]=fileContents[0];
						//System.out.println(entireFile[counter]);
					}
					listOfsortedconsultantPatientIndexes = itemSearch(entireFile,searchQueryTextFeild.getText());
					createConsultantPatientpanel(listOfsortedconsultantPatientIndexes);
				}
			}
			catch(Exception exc){}
			try
			{
				if(searchBox==((Box)searchBoxOuterStaff.getComponent(0)))
				{
					//System.out.println("search occured in staff");	
					String[] entireFile = new String[userStaff.patientIDs.length];
					for(int counter = 0;counter<userStaff.patientIDs.length;counter++)
					{
						String[] fileContents = userStaff.rffReturnFullFile(userStaff.patientIDs[counter]+"_file.txt");//concatinates the string into an array
						entireFile[counter]=fileContents[0];
						//System.out.println(entireFile[counter]);
					}
					listOfsortedstaffPatientIndexes = itemSearch(entireFile,searchQueryTextFeild.getText());
					createStaffPatientpanel(listOfsortedstaffPatientIndexes);
				}
			}
			catch(Exception exc){}
			searchQueryTextFeild.setText("Search...");
		}
	
	}
	
	
	
	
	
	
	//this performs a linear search for the items in the array, however the items in each index are actually concatenated together so that instead of many binary searches a for loop is used
	//we use the contain comparission to see if the item is in the loop.
	//if the item comes back as true then the index of the oringinal location in the array is added to a new array
	//at the end of the search we will return an array of indexes of items that contained the item in the list of attributes in the paramter array
	public int[] itemSearch(String[] entireList,String searchValue)
	{
		int numberOfIndexes =0;
		for(int counter = 0;counter<entireList.length;counter++)
		{
			if((entireList[counter].toLowerCase()).contains(searchValue.toLowerCase()))
			{
				////System.out.println("Found");
				numberOfIndexes++;
			}
			
		}
		if(numberOfIndexes ==0)
		{
			JOptionPane.showMessageDialog(null, "No results where found, please try another search value");
		}				
		////System.out.println("There are "+numberOfIndexes +" results");
		int[] arrayofResults = new int[numberOfIndexes];
		numberOfIndexes=0;
		for(int counter2 = 0;counter2<entireList.length;counter2++)
		{
			if((entireList[counter2].toLowerCase()).contains(searchValue.toLowerCase()))
			{
				//System.out.println("added line:"+entireList[counter2]);
				arrayofResults[numberOfIndexes] =counter2;
				numberOfIndexes++;
			}
			
		}
		for(int counter3 = 0;counter3<arrayofResults.length;counter3++)
		{
			
				//System.out.println(""+arrayofResults[counter3]);
		}
		return(arrayofResults);
	}
	//While slightly misleading the method works by assuming that the array is in a ascending order, what it does is reverse the indexes of the items
	//for intance item at index 1 would get the index of item n-1
	//these methods only work for items that use the dynamic graphical features where the items are referenced by index that corrospond to an instance in an array that never changes
	//these indexes once rearragned are then passed back to the calling methodd where the items will get displayed
	public int[] orderArrayDESC(int entireListLength)
	{
		int[] swapOrderArray = new int[entireListLength];
		int value = swapOrderArray.length-1;
		for(int counter = 0;counter<swapOrderArray.length;counter++)
		{
			swapOrderArray[counter]=value;
			value--;
		}
		
		return swapOrderArray;
	}
	//While slightly misleading the method works by assuming that the array is in a descending order, what it does is reverse the indexes of the items
	//for intance item at index 1 would get the index of item n-1
	//these methods only work for items that use the dynamic graphical features where the items are referenced by index that corrospond to an instance in an array that never changes
	//these indexes once rearragned are then passed back to the calling methodd where the items will get displayed
	public int[] orderArrayASC(int entireListLength)
	{
		int[] swapOrderArray = new int[entireListLength];
		for(int counter = 0;counter<swapOrderArray.length;counter++)
		{
			swapOrderArray[counter]=counter;

		}
		
		return swapOrderArray;
	}
	
	//This uses the date picker class to listen for changes in the date box,
	//If a valid date has been selected then the correct list of times for that date are searched and updaetd to the list
	//the system creates a new listner to the date picker where it is constantly checking to see if the date has been changed
	//the class impliments the datechangelistner class from the imported custom date picekr
	//when a date has been correctly entered it will then enable the timepicker to be selected otherwise it is disabled
	private class SampleDateChangeListener implements DateChangeListener
	{
        @Override
        public void dateChanged(DateChangeEvent event)
		{
			LocalDate date = datePicker1.getDate();
			if(panelOrder[endPanelPointer-1]==admissionHomepagePanel)
			{
			updateListOfTimes();
			}
			if((date==null)==false)
			{
				//here want to pull the booking times for the consultant on that day
				//Asign them to the time picker
				timePicker1.setEnabled(true);
			}
			if((date==null)==true)
			{
				timePicker1.setEnabled(false);
				//need to clear array 
				timePicker1.clear();
				timePicker1.setEnabled(false);

			}
		
           //System.out.print("DAte changed");
        }
    }
	
	//Checks if new path has been set
	//if so it will load the image to the user
	// a new type of document listener is intialised to overwirte the old methods 
	//here the feild is checked to see if any inout from the file selecter has been entered by the user
	//it achieves this by entereing the file path into an image buffer and getting it to display
	//if it fails to display the image an error can be displayed
	private class documentTextStateChangeListner implements DocumentListener
	{
		@Override
		public void changedUpdate(DocumentEvent e)
		{

		}
		public void removeUpdate(DocumentEvent e)
		{
			
		}
		public void insertUpdate(DocumentEvent e)
		{
			
		try {
				File inputFile = new File(actualFilePathTF.getText());//gets the tempfile where the currentImage is 
				BufferedImage bi = ImageIO.read(inputFile);//converts it to an image
				
				testLbl.setSize(504,693);
				Image resizedImage = bi.getScaledInstance(testLbl.getWidth(), testLbl.getHeight(),Image.SCALE_SMOOTH);
				//createLegacyDocumentButton.setIcon(new ImageIcon(bi));//Test that the image is retrieved
				testLbl.setIcon(null);
				testLbl.repaint();
				testLbl.setIcon( new ImageIcon(resizedImage) );
				testLbl.setLocation(850,140);
				testLbl.setBorder(BorderFactory.createLineBorder(Color.black));		
				createOldDocumentPanel.add(testLbl);
				testLbl.repaint();
			}
			catch (IOException exc)
			{
				//System.out.println("image writing error");
			}
			
		}
	}
	
	//here we are creating an overwritten version of the item listner for the automatic bookings methodd
	//all this is to do is disalbe the cancel buttons for the panel depending if the check box has been select or not by the user
	private class buttonItemListner implements ItemListener
	{
		 @Override
		 public void itemStateChanged(ItemEvent e)
		 {
			automateBookingsMethod();
		 }
	}
	
	//used in the image selector this removes any unwanted files from the system,
	//The class creates a new filter for the file selecter where only accepted files are images
	//intially we create all the extensions that will be accepted (all image files)
	//next we override the accept method where the system checks that the extensions follow any of the set types
	//if yes then the file can be showed
	//if false then it is hidden
	private class ImageFilter extends FileFilter 
	{
	   public final static String JPEG = "jpeg";
	   public final static String JPG = "jpg";
	   public final static String GIF = "gif";
	   public final static String TIFF = "tiff";
	   public final static String TIF = "tif";
	   public final static String PNG = "png";
	   
	   @Override
	   public boolean accept(File f)
	   {
		  if (f.isDirectory())
		  {
			 return true;
		  }

		  String extension = getExtension(f);
		  if (extension != null)
		  {
			if (extension.equals(TIFF)||extension.equals(TIF)||extension.equals(GIF)||extension.equals(JPEG)||extension.equals(JPG)||extension.equals(PNG))
			{
			   return true;
			} 
			else
			{
			   return false;
			}
		  }
		  return false;
	   }

	   @Override
	   public String getDescription()
	   {
		  return "Image Only";
	   }

	   String getExtension(File f)//function that will run always
	   {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');
		if (i > 0 &&  i < s.length() - 1)
		{
		 ext = s.substring(i+1).toLowerCase();
		}
		return ext;
		}
	}   
	
	//This is for the notifiction system, when the patient logs in they can track all the notifications on the system, this method tracks if the window has moved,
	//If the window has moved it will move the notifiacation widow to reflect the changes made by the user.
	//it does this by getting the boxes point on the screen and then translating it by the offest needed to move it to the correct location
	//however the method checks to see tht the correct panels that use the feature are present
	private class ComponentWindowListener extends Popup implements ComponentListener
	{
	   @Override
		public void componentHidden(ComponentEvent e)
		{
			
		}

		@Override
		public void componentMoved(ComponentEvent e)
		{
			if(loginChoice==0)
			{
				if((panelOrder[endPanelPointer]==loginPanel)==false)
				{
					Point p = new Point(notificationbttn.getLocationOnScreen());
					p.translate(330,40);
					displayWindow.setLocation(p);
				}
			}
		}

		@Override
		public void componentResized(ComponentEvent e)
		{
		
		}
		
		@Override
		public void componentShown(ComponentEvent e)
		{
			
		}
	}
	

	//This document filter is to limit the number of characters in the text field
	//This was found online as the only soloution to solve the problem as Java has no standardised methods to do so
	public class LimitDocumentFilter extends DocumentFilter
	 {

        private int limit;

        public LimitDocumentFilter(int limit)
		{
            if (limit < 0)
			{
                throw new IllegalArgumentException("Limit can not be <= 0");
            }
            this.limit = limit;
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            int currentLength = fb.getDocument().getLength();
            int overLimit = (currentLength + text.length()) - limit - length;
            if (overLimit > 0) {
                text = text.substring(0, text.length() - overLimit);
            }
            if (text.length() >= 0) {
                super.replace(fb, offset, length, text, attrs); 
            }
		
        }

    }
	//here key listners are attached to the password feilds of the login panel this will allow the user to press the key enter and the system will run the same code as the login method
	//The class is created and is attached to the password feild
	public class WindowEmterListener  implements KeyListener
	{
		public void keyTyped(KeyEvent e)
		{
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				//Within each statment an intermediary method are in place these will be used for actually declaring and intialising all the correct fields for the user.
				username =usernameTF.getText();//sets username as feild of text feild
				if((username.charAt(0)+"").equals("P")==true)
				{
					loginChoice = 0;
					setUpPatientObj(username);//initalises patient object
					usernameTF.setText("");//built in protection on the system as soon as the user tries to login the username will be cleared 
				}
				else if ((username.charAt(0)+"").equals("S")==true)
				{
					loginChoice = 1;
					setUpStaffObj(username);//initalises patient object
					usernameTF.setText("");//built in protection on the system as soon as the user tries to login the username will be cleared 
				}
				else if ((username.charAt(0)+"").equals("M")==true)
				{
					loginChoice = 2;
					setUpManagmentObj(username);//initalises patient object
					usernameTF.setText("");//built in protection on the system as soon as the user tries to login the username will be cleared 
				}
				else if((username.charAt(0)+"").equals("C")==true)
				{
					loginChoice = 3;
					setUpConsultantObj(username);//initalises patient object
					usernameTF.setText("");//built in protection on the system as soon as the user tries to login the username will be cleared
				}
				loginPasswordF.setText("");//built in protection on the system as soon as the user tries to login the password will be cleared 
		
			}
		}

		public void keyPressed(KeyEvent e)
		{
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				//Within each statment an intermediary method are in place these will be used for actually declaring and intialising all the correct fields for the user.
				username =usernameTF.getText();//sets username as feild of text feild
				User tempUSer = new User();
				boolean validated = tempUSer.presenceValidation(username);
				if(validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid username, missing.");
					return;
				}
				validated=tempUSer.typeValidationStringOrInt(username);
				if(validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid username, letters only.");
					return;
				}
				validated=tempUSer.lesserLengthValidation(username,11);
				if(validated==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid username, must be less than 26 characters.");
					return;
				}
				if(validated==true)
				{
					if((username.charAt(0)+"").equals("P")==true)
					{
						loginChoice = 0;
						setUpPatientObj(username);//initalises patient object
						usernameTF.setText("");//built in protection on the system as soon as the user tries to login the username will be cleared 
					}
					else if ((username.charAt(0)+"").equals("S")==true)
					{
						loginChoice = 1;
						setUpStaffObj(username);//initalises patient object
						usernameTF.setText("");//built in protection on the system as soon as the user tries to login the username will be cleared 
					}
					else if ((username.charAt(0)+"").equals("M")==true)
					{
						loginChoice = 2;
						setUpManagmentObj(username);//initalises patient object
						usernameTF.setText("");//built in protection on the system as soon as the user tries to login the username will be cleared 
					}
					else if((username.charAt(0)+"").equals("C")==true)
					{
						loginChoice = 3;
						setUpConsultantObj(username);//initalises patient object
						usernameTF.setText("");//built in protection on the system as soon as the user tries to login the username will be cleared
					}
				}
				loginPasswordF.setText("");//built in protection on the system as soon as the user tries to login the password will be cleared 
		
			}
		}

		public void keyReleased(KeyEvent e)
		{
			
		}
	}
		
	
	
	
	//MAIN method 
	//this  method is intially called on the calling of the sysetm
	public static void main(String[] args )//declaring the main method
	{
        Gui guiObj = new Gui();//creates a new instance of Gui will be used to create the GUI
        guiObj.startSystem();//the instance now calls upon the inital load up GUI
    }
} 
  
   