import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
/**
 * Main class with Swing frames 
 *  
 * @author Guillermo Lizondo
 * @version 1.0
 */
public class Dashboard {

	private JFrame frame;
	private JFrame frameUser;
	private JFrame frameProvider;
	private JFrame frameCalendarUser;
	private JFrame frameCancelAppointmentUser;
	private JFrame frameProviderNewPatient;
	private JFrame frameEditInfoPatient;
	private JTextField textFieldID;
	private JTextField textFieldPassword;
	private JTextField textFieldUserNameLastname;
	private JTextField textFieldUserDOB;
	private JTextField textFieldUserInsuranceNumber;
	private JTextArea textAreaUserAppointment;
	private JComboBox comboBoxMonth;
	private JComboBox comboBoxHour;
	private JComboBox comboBoxDay;
	private JComboBox comboBoxYear;
	private JComboBox comboBoxAppointmentCancel;
	FileInputStream fileReader;
	FileOutputStream fileStream;
	PrintWriter fileWriter;
	Scanner reader;
	User patient;
	Provider provider;
	private JTextField textFieldSearchPatient;
	private JTextField textFieldCreateNewPatientName;
	private JTextField textFieldCreateNewPatientDOB;
	private JTextField textFieldCreateNewPatientInsurance;
	private JTextField textFieldCreateNewPatientID;
	private JTextField textFieldCreateNewPatientPassword;
	private JTextField textFieldTotalNumberPatients;
	private JTextField textFieldMedicationPatient;
	private JTextField textFieldNotesFromDoctor;
	private JTextField textFieldEditInfoPatientName;
	private JTextField textFieldEditInfoPatientDOB;
	private JTextField textFieldEditInfoPatientInsurance;
	private JTextField textFieldEditInfoPatientInsuranceUpdate;
	private JTextField textFieldEditInfoPatientMedicationUpdate;
	private JTextField textFieldEditInfoPatientNotesUpdate;

	// private static String
	// dbURLembedded="jdbc:derby:myDB;create=true;user=test7;password=test1";
//   private static String dbURLembedded="jdbc:derby:c:/Users/Guillermo//MyDB102";
//   private static String tableName = "PATIENTS";
//   
//   private static Connection conn = null;
//   private static Statement stmt = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard window = new Dashboard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Dashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/**
		 * Main frame
		 */
		frame = new JFrame("Ephesus Medical System");
		frame.setBounds(100, 100, 800, 600);
		frame.getContentPane().setLayout(null);
		ImageIcon background = new ImageIcon("Background3.jpg");
		Image img = background.getImage();
		Image temp = img.getScaledInstance(800, 600, Image.SCALE_DEFAULT);
		background = new ImageIcon(temp);
		JLabel back = new JLabel(background);
		back.setLabelFor(null);
		back.setBounds(0, 0, 800, 600);
		/**
		 * Frame for provider
		 */
		frameProvider = new JFrame();
		frameProvider.setBounds(100, 100, 800, 600);
		frameProvider.getContentPane().setLayout(null);
		frameProvider.setBackground(Color.BLUE);
		ImageIcon backgroundProvider = new ImageIcon("Background5.jpg");
		Image imgProvider = backgroundProvider.getImage();
		Image tempProvider = imgProvider.getScaledInstance(800, 600, Image.SCALE_DEFAULT);
		backgroundProvider = new ImageIcon(tempProvider);
		JLabel backProvider = new JLabel(backgroundProvider);
		backProvider.setLabelFor(null);
		backProvider.setBounds(0, -19, 800, 600);
		/**
		 * Frame provider create new patient
		 */
		frameProviderNewPatient = new JFrame();
		frameProviderNewPatient.setBounds(100, 100, 800, 600);
		frameProviderNewPatient.getContentPane().setLayout(null);
		/**
		 * Frame for patient
		 */
		frameUser = new JFrame();
		frameUser.setBounds(100, 100, 800, 600);
		frameUser.getContentPane().setLayout(null);
		frameUser.setBackground(Color.RED);
		ImageIcon backgroundUser = new ImageIcon("Background4.jpg");
		Image imgUser = backgroundUser.getImage();
		Image tempUser = imgUser.getScaledInstance(800, 600, Image.SCALE_DEFAULT);
		backgroundUser = new ImageIcon(tempUser);
		JLabel backUser = new JLabel(backgroundUser);
		backUser.setLabelFor(null);
		backUser.setBounds(0, -21, 800, 600);
		/**
		 * Frame for calendar to book appointment
		 */
		frameCalendarUser = new JFrame();
		frameCalendarUser.setBounds(100, 100, 600, 500);
		frameCalendarUser.getContentPane().setLayout(null);
		frameCalendarUser.setBackground(Color.ORANGE);
		/**
		 * Frame to cancel appointment
		 */
		frameCancelAppointmentUser = new JFrame();
		frameCancelAppointmentUser.setBounds(100, 100, 600, 500);
		frameCancelAppointmentUser.getContentPane().setLayout(null);
		frameCancelAppointmentUser.setBackground(Color.ORANGE);
		/**
		 * Label welcome top main frame
		 */
		frameEditInfoPatient = new JFrame();
		frameEditInfoPatient.setBounds(100, 100, 600, 500);
		frameEditInfoPatient.getContentPane().setLayout(null);
		frameEditInfoPatient.setBackground(Color.ORANGE);

		JLabel labelWelcome = new JLabel("Welcome to Ephesus Medical System");
		labelWelcome.setBackground(Color.ORANGE);
		labelWelcome.setOpaque(true);
		labelWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		labelWelcome.setBounds(250, 100, 300, 50);
		frame.getContentPane().add(labelWelcome);
		/**
		 * Label to indicate where to put the user name
		 */
		JLabel labelID = new JLabel("ID");
		labelID.setBackground(Color.lightGray);
		labelID.setOpaque(true);
		labelID.setHorizontalAlignment(SwingConstants.CENTER);
		labelID.setBounds(250, 167, 300, 30);
		frame.getContentPane().add(labelID);
		/**
		 * Label to indicate where to put the password
		 */
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setBackground(Color.lightGray);
		labelPassword.setOpaque(true);
		labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
		labelPassword.setBounds(250, 247, 300, 30);
		frame.getContentPane().add(labelPassword);
		/**
		 * Text field to add user name
		 */
		textFieldID = new JTextField();
		textFieldID.setBounds(250, 208, 300, 30);
		frame.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		textFieldID.setVisible(true);
		/**
		 * Text field to add password
		 */
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(250, 288, 300, 30);
		frame.getContentPane().add(textFieldPassword);
		textFieldPassword.setColumns(10);
		textFieldPassword.setVisible(true);
		/**
		 * Button to click to login
		 */
		JButton buttonLogIn = new JButton("Log in");
		buttonLogIn.setBackground(new Color(46, 139, 87));
		buttonLogIn.setOpaque(true);
		buttonLogIn.setBounds(335, 329, 134, 37);
		frame.getContentPane().add(buttonLogIn);
		/**
		 * Check box to indicate login for provider
		 */
		JCheckBox checkBoxProvider = new JCheckBox("Provider");
		checkBoxProvider.setBounds(576, 146, 97, 23);
		frame.getContentPane().add(checkBoxProvider);
		/**
		 * Check box to indicate login for patient
		 */
		JCheckBox checkBoxPatient = new JCheckBox("Patient");
		checkBoxPatient.setBounds(576, 172, 97, 23);
		frame.getContentPane().add(checkBoxPatient);
		frame.getContentPane().add(back);
		/**
		 * Button group to limit the selection between provider and patient
		 */
		ButtonGroup checkPatientProviderGroup = new ButtonGroup();
		checkPatientProviderGroup.add(checkBoxPatient);
		checkPatientProviderGroup.add(checkBoxProvider);
		/**
		 * Provider frame / label welcome provider top
		 */
		JLabel labelWelcomeProvider = new JLabel("Provider's Portal");
		labelWelcomeProvider.setBackground(Color.ORANGE);
		labelWelcomeProvider.setOpaque(true);
		labelWelcomeProvider.setHorizontalAlignment(SwingConstants.CENTER);
		labelWelcomeProvider.setBounds(250, 11, 300, 50);
		frameProvider.getContentPane().add(labelWelcomeProvider);
		/**
		 * Provider frame / label search patient
		 */
		JLabel labelSearchPatient = new JLabel("Search patient");
		labelSearchPatient.setHorizontalAlignment(SwingConstants.CENTER);
		labelSearchPatient.setBounds(133, 85, 127, 30);
		frameProvider.getContentPane().add(labelSearchPatient);
		/**
		 * Provider frame / text field search patient
		 */
		textFieldSearchPatient = new JTextField();
		textFieldSearchPatient.setBounds(272, 85, 257, 30);
		frameProvider.getContentPane().add(textFieldSearchPatient);
		textFieldSearchPatient.setColumns(10);
		/**
		 * Provider frame / button search patient
		 */
		JButton buttonSearchPatient = new JButton("Search!");
		buttonSearchPatient.setBounds(541, 85, 90, 28);
		frameProvider.getContentPane().add(buttonSearchPatient);
		/**
		 * Provider frame / button create new patient
		 */
		JButton buttonNewPatient = new JButton("New patient");
		buttonNewPatient.setBounds(541, 135, 127, 28);
		frameProvider.getContentPane().add(buttonNewPatient);

		JLabel labelTotalNumberPatients = new JLabel("Total Number Patients");
		labelTotalNumberPatients.setHorizontalAlignment(SwingConstants.CENTER);
		labelTotalNumberPatients.setBounds(133, 134, 127, 30);
		frameProvider.getContentPane().add(labelTotalNumberPatients);

		textFieldTotalNumberPatients = new JTextField();
		textFieldTotalNumberPatients.setColumns(10);
		textFieldTotalNumberPatients.setBounds(272, 135, 127, 30);
		frameProvider.getContentPane().add(textFieldTotalNumberPatients);
		textFieldTotalNumberPatients.setEditable(false);
		frameProvider.getContentPane().add(backProvider);
		/**
		 * Provider new patient frame / label for name create new patient
		 */
		JLabel labelCreateNewPatientName = new JLabel("Lastname,Name");
		labelCreateNewPatientName.setHorizontalAlignment(SwingConstants.CENTER);
		labelCreateNewPatientName.setBounds(89, 56, 141, 28);
		frameProviderNewPatient.getContentPane().add(labelCreateNewPatientName);
		/**
		 * Provider new patient frame / text field for name create new patient
		 */
		textFieldCreateNewPatientName = new JTextField();
		textFieldCreateNewPatientName.setBounds(248, 56, 263, 28);
		frameProviderNewPatient.getContentPane().add(textFieldCreateNewPatientName);
		textFieldCreateNewPatientName.setColumns(10);
		/**
		 * Provider new patient frame / label for Date of Birth create new patient
		 */
		JLabel labelCreateNewPatientDOB = new JLabel("Date of birth");
		labelCreateNewPatientDOB.setHorizontalAlignment(SwingConstants.CENTER);
		labelCreateNewPatientDOB.setBounds(89, 96, 141, 28);
		frameProviderNewPatient.getContentPane().add(labelCreateNewPatientDOB);
		/**
		 * Provider new patient frame / text field for Date of Birth create new patient
		 */
		textFieldCreateNewPatientDOB = new JTextField();
		textFieldCreateNewPatientDOB.setColumns(10);
		textFieldCreateNewPatientDOB.setBounds(248, 96, 263, 28);
		frameProviderNewPatient.getContentPane().add(textFieldCreateNewPatientDOB);
		/**
		 * Provider new patient frame / label for Insurance create new patient
		 */
		JLabel labelCreateNewPatientInsurance = new JLabel("Insurance number");
		labelCreateNewPatientInsurance.setHorizontalAlignment(SwingConstants.CENTER);
		labelCreateNewPatientInsurance.setBounds(89, 136, 141, 28);
		frameProviderNewPatient.getContentPane().add(labelCreateNewPatientInsurance);
		/**
		 * Provider new patient frame / text field for Insurance create new patient
		 */
		textFieldCreateNewPatientInsurance = new JTextField();
		textFieldCreateNewPatientInsurance.setColumns(10);
		textFieldCreateNewPatientInsurance.setBounds(248, 136, 263, 28);
		frameProviderNewPatient.getContentPane().add(textFieldCreateNewPatientInsurance);
		/**
		 * Provider new patient frame / label for ID create new patient
		 */
		JLabel labelCreateNewPatientID = new JLabel("Username/ID");
		labelCreateNewPatientID.setHorizontalAlignment(SwingConstants.CENTER);
		labelCreateNewPatientID.setBounds(89, 176, 141, 28);
		frameProviderNewPatient.getContentPane().add(labelCreateNewPatientID);
		/**
		 * Provider new patient frame / text field for ID create new patient
		 */
		textFieldCreateNewPatientID = new JTextField();
		textFieldCreateNewPatientID.setColumns(10);
		textFieldCreateNewPatientID.setBounds(248, 176, 263, 28);
		frameProviderNewPatient.getContentPane().add(textFieldCreateNewPatientID);
		/**
		 * Provider new patient frame / label for Password create new patient
		 */
		JLabel labelCreateNewPatientPassword = new JLabel("Password");
		labelCreateNewPatientPassword.setHorizontalAlignment(SwingConstants.CENTER);
		labelCreateNewPatientPassword.setBounds(89, 216, 141, 28);
		frameProviderNewPatient.getContentPane().add(labelCreateNewPatientPassword);
		/**
		 * Provider new patient frame / text field for Password create new patient
		 */
		textFieldCreateNewPatientPassword = new JTextField();
		textFieldCreateNewPatientPassword.setColumns(10);
		textFieldCreateNewPatientPassword.setBounds(248, 216, 263, 28);
		frameProviderNewPatient.getContentPane().add(textFieldCreateNewPatientPassword);
		/**
		 * Provider new patient frame / button create new patient
		 */
		JButton buttonCreateNewPatient = new JButton("Create!");
		buttonCreateNewPatient.setBounds(339, 260, 90, 28);
		frameProviderNewPatient.getContentPane().add(buttonCreateNewPatient);
		/**
		 * Provider new patient frame / button help question mark icon create new
		 * 
		 */
		JButton buttonCreateNewPatientHelp = new JButton("");
		ImageIcon questionImage = new ImageIcon("Question.png");
		Image imageQuestion = questionImage.getImage();
		Image imageQuestion2 = imageQuestion.getScaledInstance(20, 20, 20);
		questionImage = new ImageIcon(imageQuestion2);
		buttonCreateNewPatientHelp.setIcon(questionImage);
		buttonCreateNewPatientHelp.setHorizontalTextPosition(JButton.CENTER);
		buttonCreateNewPatientHelp.setVerticalTextPosition(JButton.CENTER);
		buttonCreateNewPatientHelp.setBounds(554, 56, 37, 28);
		frameProviderNewPatient.getContentPane().add(buttonCreateNewPatientHelp);
		frameProviderNewPatient.setBackground(Color.gray);
		/**
		 * Edit patient info frame / label for patient name
		 * 
		 */
		JLabel labelEditInfoPatientName = new JLabel("Name Patient");
		labelEditInfoPatientName.setBounds(55, 18, 133, 24);
		frameEditInfoPatient.getContentPane().add(labelEditInfoPatientName);
		/**
		 * Edit patient info frame / label for patient DOB
		 * 
		 */
		JLabel labelEditInfoPatientDOB = new JLabel("Date of Birth");
		labelEditInfoPatientDOB.setBounds(55, 54, 133, 24);
		frameEditInfoPatient.getContentPane().add(labelEditInfoPatientDOB);
		/**
		 * Edit patient info frame / label for patient insurance number
		 * 
		 */
		JLabel labelEditInfoPatientInsurance = new JLabel("Insurance Number");
		labelEditInfoPatientInsurance.setBounds(55, 90, 133, 24);
		frameEditInfoPatient.getContentPane().add(labelEditInfoPatientInsurance);
		/**
		 * Edit patient info frame / label indicating info to update below
		 * 
		 */
		JLabel labelUpdateInformationPatient = new JLabel("Update Information");
		labelUpdateInformationPatient.setHorizontalAlignment(SwingConstants.CENTER);
		labelUpdateInformationPatient.setBounds(244, 146, 145, 24);
		frameEditInfoPatient.getContentPane().add(labelUpdateInformationPatient);
		/**
		 * Edit patient info frame / label for patient insurance to update
		 * 
		 */
		JLabel labelEditInfoPatientInsuranceUpdate = new JLabel("Insurance Number");
		labelEditInfoPatientInsuranceUpdate.setBounds(55, 198, 133, 24);
		frameEditInfoPatient.getContentPane().add(labelEditInfoPatientInsuranceUpdate);
		/**
		 * Edit patient info frame / label for patient medication to update
		 * 
		 */
		JLabel labelEditInfoPatientMedicationUpdate = new JLabel("Medication");
		labelEditInfoPatientMedicationUpdate.setBounds(55, 234, 133, 24);
		frameEditInfoPatient.getContentPane().add(labelEditInfoPatientMedicationUpdate);
		/**
		 * Edit patient info frame / label for patient doctor notes to update
		 * 
		 */
		JLabel labelEditInfoPatientNotesUpdate = new JLabel("Notes from Doctor");
		labelEditInfoPatientNotesUpdate.setBounds(55, 287, 133, 24);
		frameEditInfoPatient.getContentPane().add(labelEditInfoPatientNotesUpdate);
		/**
		 * Edit patient info frame / text field patient name
		 * 
		 */
		textFieldEditInfoPatientName = new JTextField();
		textFieldEditInfoPatientName.setEditable(false);
		textFieldEditInfoPatientName.setBounds(165, 17, 313, 26);
		frameEditInfoPatient.getContentPane().add(textFieldEditInfoPatientName);
		textFieldEditInfoPatientName.setColumns(10);
		/**
		 * Edit patient info frame / text field patient DOB
		 * 
		 */
		textFieldEditInfoPatientDOB = new JTextField();
		textFieldEditInfoPatientDOB.setEditable(false);
		textFieldEditInfoPatientDOB.setColumns(10);
		textFieldEditInfoPatientDOB.setBounds(165, 54, 313, 26);
		frameEditInfoPatient.getContentPane().add(textFieldEditInfoPatientDOB);
		/**
		 * Edit patient info frame / text field patient insurance
		 * 
		 */
		textFieldEditInfoPatientInsurance = new JTextField();
		textFieldEditInfoPatientInsurance.setEditable(false);
		textFieldEditInfoPatientInsurance.setColumns(10);
		textFieldEditInfoPatientInsurance.setBounds(165, 89, 313, 26);
		frameEditInfoPatient.getContentPane().add(textFieldEditInfoPatientInsurance);
		/**
		 * Edit patient info frame / text field patient insurance to update
		 * 
		 */
		textFieldEditInfoPatientInsuranceUpdate = new JTextField();
		textFieldEditInfoPatientInsuranceUpdate.setColumns(10);
		textFieldEditInfoPatientInsuranceUpdate.setBounds(165, 196, 313, 26);
		frameEditInfoPatient.getContentPane().add(textFieldEditInfoPatientInsuranceUpdate);
		/**
		 * Edit patient info frame / text field patient medication to update
		 * 
		 */
		textFieldEditInfoPatientMedicationUpdate = new JTextField();
		textFieldEditInfoPatientMedicationUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldEditInfoPatientMedicationUpdate.setColumns(10);
		textFieldEditInfoPatientMedicationUpdate.setBounds(165, 232, 313, 45);
		frameEditInfoPatient.getContentPane().add(textFieldEditInfoPatientMedicationUpdate);
		/**
		 * Edit patient info frame / text field patient doctor notes to update
		 * 
		 */
		textFieldEditInfoPatientNotesUpdate = new JTextField();
		textFieldEditInfoPatientNotesUpdate.setColumns(10);
		textFieldEditInfoPatientNotesUpdate.setBounds(165, 285, 313, 64);
		frameEditInfoPatient.getContentPane().add(textFieldEditInfoPatientNotesUpdate);
		/**
		 * Edit patient info frame / button update information for patient confirmation
		 * 
		 */
		JButton buttonUpdateFormInfoPatient = new JButton("Update");
		buttonUpdateFormInfoPatient.setBounds(274, 361, 90, 28);
		frameEditInfoPatient.getContentPane().add(buttonUpdateFormInfoPatient);
		/**
		 * Patient frame / label welcome to patient top
		 */
		JLabel labelWelcomeUser = new JLabel("Patient's Portal");
		labelWelcomeUser.setBackground(Color.ORANGE);
		labelWelcomeUser.setOpaque(true);
		labelWelcomeUser.setHorizontalAlignment(SwingConstants.CENTER);
		labelWelcomeUser.setBounds(250, 20, 300, 40);
		frameUser.getContentPane().add(labelWelcomeUser);
		/**
		 * Patient frame / label appointments user
		 */
		JLabel labelAppointmentsUser = new JLabel("Your appointments: ");
		labelAppointmentsUser.setBackground(Color.GRAY);
		labelAppointmentsUser.setOpaque(true);
		labelAppointmentsUser.setHorizontalAlignment(SwingConstants.CENTER);
		labelAppointmentsUser.setBounds(100, 150, 200, 40);
		frameUser.getContentPane().add(labelAppointmentsUser);
		/**
		 * Patient frame / label name of the patient
		 */
		JLabel labelUserNameLastname = new JLabel("Name Patient");
		labelUserNameLastname.setBackground(Color.GRAY);
		labelUserNameLastname.setOpaque(true);
		labelUserNameLastname.setHorizontalAlignment(SwingConstants.CENTER);
		labelUserNameLastname.setBounds(175, 70, 150, 30);
		frameUser.getContentPane().add(labelUserNameLastname);
		/**
		 * Patient frame / text field non editable name of the patient
		 */
		textFieldUserNameLastname = new JTextField();
		textFieldUserNameLastname.setEditable(false);
		textFieldUserNameLastname.setBounds(175, 100, 150, 30);
		frameUser.getContentPane().add(textFieldUserNameLastname);
		textFieldUserNameLastname.setColumns(10);
		textFieldUserNameLastname.setVisible(true);
		/**
		 * Patient frame / label Date of Birth of the patient
		 */
		JLabel labelUserDOB = new JLabel("Date of Birth");
		labelUserDOB.setBackground(Color.GRAY);
		labelUserDOB.setOpaque(true);
		labelUserDOB.setHorizontalAlignment(SwingConstants.CENTER);
		labelUserDOB.setBounds(325, 70, 150, 30);
		frameUser.getContentPane().add(labelUserDOB);
		/**
		 * Patient frame / text field non editable Date of Birth of the patient
		 */
		textFieldUserDOB = new JTextField();
		textFieldUserDOB.setEditable(false);
		textFieldUserDOB.setBounds(325, 100, 150, 30);
		frameUser.getContentPane().add(textFieldUserDOB);
		textFieldUserDOB.setColumns(10);
		textFieldUserDOB.setVisible(true);
		/**
		 * Patient frame / label Insurance of the patient
		 */
		JLabel labelUserInsuranceNumber = new JLabel("Insurance Number");
		labelUserInsuranceNumber.setBackground(Color.GRAY);
		labelUserInsuranceNumber.setOpaque(true);
		labelUserInsuranceNumber.setHorizontalAlignment(SwingConstants.CENTER);
		labelUserInsuranceNumber.setBounds(475, 70, 150, 30);
		frameUser.getContentPane().add(labelUserInsuranceNumber);
		/**
		 * Patient frame / text field non editable Insurance of the patient
		 */
		textFieldUserInsuranceNumber = new JTextField();
		textFieldUserInsuranceNumber.setEditable(false);
		textFieldUserInsuranceNumber.setBounds(475, 100, 150, 30);
		frameUser.getContentPane().add(textFieldUserInsuranceNumber);
		textFieldUserInsuranceNumber.setColumns(10);
		textFieldUserInsuranceNumber.setVisible(true);
		/**
		 * Patient frame / button update information in appointments list
		 */
		JButton buttonUpdate = new JButton("Update");
		buttonUpdate.setBackground(Color.GRAY);
		buttonUpdate.setOpaque(true);
		buttonUpdate.setBounds(550, 150, 134, 37);
		frameUser.getContentPane().add(buttonUpdate);
		/**
		 * Patient frame / button book appointment for patient
		 */
		JButton buttonBookAppointment = new JButton("Book Appointment");
		buttonBookAppointment.setBackground(new Color(46, 139, 87));
		buttonBookAppointment.setOpaque(true);
		buttonBookAppointment.setBounds(200, 300, 200, 40);
		frameUser.getContentPane().add(buttonBookAppointment);
		/**
		 * Patient frame / button cancel appointment for patient
		 */
		JButton buttonCancelAppointment = new JButton("Cancel Appointment");
		buttonCancelAppointment.setBackground(new Color(230, 15, 20));
		buttonCancelAppointment.setOpaque(true);
		buttonCancelAppointment.setBounds(400, 300, 200, 40);
		frameUser.getContentPane().add(buttonCancelAppointment);
		/**
		 * Patient frame / scroll pane appointments list
		 */
		JScrollPane scrollPaneAppointments = new JScrollPane();
		scrollPaneAppointments.setBounds(299, 150, 250, 120);
		frameUser.getContentPane().add(scrollPaneAppointments);
		JTextArea textAreaUserAppointment = new JTextArea();
		scrollPaneAppointments.setViewportView(textAreaUserAppointment);
		textAreaUserAppointment.setAutoscrolls(false);
		textAreaUserAppointment.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textAreaUserAppointment.setEditable(false);
		/**
		 * Patient frame / label for medication patient
		 */
		JLabel labelMedicationPatient = new JLabel("Your Medication");
		labelMedicationPatient.setHorizontalAlignment(SwingConstants.CENTER);
		labelMedicationPatient.setBounds(100, 356, 113, 30);
		frameUser.getContentPane().add(labelMedicationPatient);
		/**
		 * Patient frame / text field for medication patient
		 */
		textFieldMedicationPatient = new JTextField();
		textFieldMedicationPatient.setEditable(false);
		textFieldMedicationPatient.setBounds(226, 352, 399, 58);
		frameUser.getContentPane().add(textFieldMedicationPatient);
		textFieldMedicationPatient.setColumns(10);
		/**
		 * Patient frame / label for notes doctor for patient
		 */
		JLabel labelNotesFromDoctor = new JLabel("Notes from the Doctor");
		labelNotesFromDoctor.setHorizontalAlignment(SwingConstants.CENTER);
		labelNotesFromDoctor.setBounds(63, 418, 150, 30);
		frameUser.getContentPane().add(labelNotesFromDoctor);
		/**
		 * Patient frame / text field for notes from doctor patient
		 */
		textFieldNotesFromDoctor = new JTextField();
		textFieldNotesFromDoctor.setEditable(false);
		textFieldNotesFromDoctor.setColumns(10);
		textFieldNotesFromDoctor.setBounds(226, 419, 399, 95);
		frameUser.getContentPane().add(textFieldNotesFromDoctor);
		/**
		 * Patient frame / button modify info patient for provider only
		 */
		JButton buttonModifyPatientInfo = new JButton("Modify Patient's Info");
		buttonModifyPatientInfo.setBounds(120, 202, 167, 40);
		frameUser.getContentPane().add(buttonModifyPatientInfo);
		frameUser.getContentPane().add(backUser);
		/**
		 * Patient calendar frame / label year in calendar
		 */
		JLabel labelYear = new JLabel("Year");
		labelYear.setBackground(Color.GRAY);
		labelYear.setOpaque(true);
		labelYear.setHorizontalAlignment(SwingConstants.CENTER);
		labelYear.setBounds(100, 150, 100, 50);
		frameCalendarUser.getContentPane().add(labelYear);
		/**
		 * Patient calendar frame / combo box and array for year
		 */
		Integer[] year = { 2022, 2023, 2024 };
		comboBoxYear = new JComboBox(year);
		comboBoxYear.setBounds(100, 200, 100, 50);
		frameCalendarUser.getContentPane().add(comboBoxYear);
		/**
		 * Patient calendar frame / label month in calendar
		 */
		JLabel labelMonth = new JLabel("Month");
		labelMonth.setBackground(Color.GRAY);
		labelMonth.setOpaque(true);
		labelMonth.setHorizontalAlignment(SwingConstants.CENTER);
		labelMonth.setBounds(200, 150, 100, 50);
		frameCalendarUser.getContentPane().add(labelMonth);
		/**
		 * Patient calendar frame / combo box and array for month
		 */
		String[] month = { "", "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		comboBoxMonth = new JComboBox(month);
		comboBoxMonth.setBounds(200, 200, 100, 50);
		comboBoxMonth.setSelectedItem("");
		frameCalendarUser.getContentPane().add(comboBoxMonth);
		/**
		 * Patient calendar frame / label day in calendar
		 */
		JLabel labelDay = new JLabel("Day");
		labelDay.setBackground(Color.GRAY);
		labelDay.setOpaque(true);
		labelDay.setHorizontalAlignment(SwingConstants.CENTER);
		labelDay.setBounds(300, 150, 100, 50);
		frameCalendarUser.getContentPane().add(labelDay);
		/**
		 * Patient calendar frame / combo box and array for day
		 */
		comboBoxDay = new JComboBox();
		comboBoxDay.setSelectedItem("");
		comboBoxDay.setBounds(300, 200, 100, 50);
		frameCalendarUser.getContentPane().add(comboBoxDay);
		/**
		 * Patient calendar frame / label hour in calendar
		 */
		JLabel labelHour = new JLabel("Time");
		labelHour.setBackground(Color.GRAY);
		labelHour.setOpaque(true);
		labelHour.setHorizontalAlignment(SwingConstants.CENTER);
		labelHour.setBounds(400, 150, 100, 50);
		frameCalendarUser.getContentPane().add(labelHour);
		/**
		 * Patient calendar frame / combo box and array for hour
		 */
		Integer[] hour = { 9, 10, 11, 12, 13, 14, 15, 16, 17 };
		comboBoxHour = new JComboBox(hour);
		comboBoxHour.setBounds(400, 200, 100, 50);
		frameCalendarUser.getContentPane().add(comboBoxHour);
		/**
		 * Patient calendar frame / button confirm book appointment
		 */
		JButton buttonConfirmBookAppointment = new JButton("Book Appointment Now");
		buttonConfirmBookAppointment.setBackground(new Color(46, 139, 87));
		buttonConfirmBookAppointment.setOpaque(true);
		buttonConfirmBookAppointment.setBounds(200, 329, 200, 37);
		frameCalendarUser.getContentPane().add(buttonConfirmBookAppointment);
		/**
		 * Patient cancel appointment frame / label cancel appointment
		 */
		JLabel labelAppointmentCancel = new JLabel("Choose the appointment to cancel");
		labelAppointmentCancel.setBackground(Color.GRAY);
		labelAppointmentCancel.setOpaque(true);
		labelAppointmentCancel.setHorizontalAlignment(SwingConstants.CENTER);
		labelAppointmentCancel.setBounds(100, 150, 200, 50);
		frameCancelAppointmentUser.getContentPane().add(labelAppointmentCancel);
		/**
		 * Patient cancel appointment frame / button confirm cancel appointment
		 */
		JButton buttonConfirmCancelAppointment = new JButton("Confirm cancelation");
		buttonConfirmCancelAppointment.setBackground(new Color(230, 15, 20));
		buttonConfirmCancelAppointment.setOpaque(true);
		buttonConfirmCancelAppointment.setBounds(200, 330, 200, 40);
		frameCancelAppointmentUser.getContentPane().add(buttonConfirmCancelAppointment);
		/**
		 * Patient cancel appointment frame / combo box for appointments available to
		 * cancel
		 */
		comboBoxAppointmentCancel = new JComboBox();
		comboBoxAppointmentCancel.setBounds(300, 150, 250, 50);
		frameCancelAppointmentUser.getContentPane().add(comboBoxAppointmentCancel);
		/**
		 * Actions executed after clicking on button to login Brings a new frame with
		 * the provider/patient information
		 */
		buttonLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (checkBoxPatient.isSelected()) {
					String idPatient = textFieldID.getText();
					String passwordPatient = textFieldPassword.getText();
					patient = new User("", idPatient, "");
					provider = new Provider("", "", "");

					if (patient.checkLoginUser(idPatient, passwordPatient) == true) {
						frameUser.setVisible(true);
						patient.setID(idPatient);
						String nameLastName = patient.provideUserNameLastName(idPatient);
						patient.setName(nameLastName);
						textFieldUserNameLastname.setText(patient.getName());
						String DOB = patient.provideUserDOB(idPatient);
						patient.setDOB(DOB);
						textFieldUserDOB.setText(patient.getDOB());
						String insuranceNumber = patient.provideUserInsuranceNumber(idPatient);
						patient.setInsuranceNumber(insuranceNumber);
						textFieldUserInsuranceNumber.setText(patient.getInsuranceNumber());
						textAreaUserAppointment.setText(patient.provideUserAppointments(idPatient));
						textFieldMedicationPatient.setText(provider.provideMedicationNotesPatient(patient.getID()));
						textFieldNotesFromDoctor.setText(provider.provideDoctorNotesPatient(patient.getID()));
						buttonModifyPatientInfo.setVisible(false);
					}
					if (User.countAttempts >= 10) {
						JOptionPane.showMessageDialog(null, "Too many attempts. You are locked",
								"Ephesus Medical System Message", JOptionPane.PLAIN_MESSAGE);
						textFieldID.setEditable(false);
						textFieldPassword.setEditable(false);
						buttonLogIn.setEnabled(false);
					}
				}
				if (checkBoxProvider.isSelected()) {

					String idProvider = textFieldID.getText();
					String passwordProvider = textFieldPassword.getText();
					Provider provider = new Provider(null, idProvider, null);
					if (provider.checkLoginUser(idProvider, passwordProvider) == true) {
						frameProvider.setVisible(true);
						String numberOfPatients = Integer.toString(provider.provideTotalNumberPatients());
						textFieldTotalNumberPatients.setText(numberOfPatients);
						buttonModifyPatientInfo.setVisible(true);
					}
				}
			}
		});

		buttonBookAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameCalendarUser.setVisible(true);
				comboBoxMonth.setVisible(true);
				comboBoxDay.setVisible(true);
			}
		});

		buttonConfirmBookAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxDay.setVisible(true);
				String ID = patient.getID();
				Integer year = (Integer) comboBoxYear.getSelectedItem();
				String month = comboBoxMonth.getSelectedItem().toString();
				Integer day = (Integer) comboBoxDay.getSelectedItem();
				Integer hour = (Integer) comboBoxHour.getSelectedItem();
				patient.addAppointment(ID, year, month, day, hour);
				frameCalendarUser.setVisible(false);
				textAreaUserAppointment.setText("");
			}
		});

		comboBoxMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String monthSelected = comboBoxMonth.getSelectedItem().toString();
				if (monthSelected.equalsIgnoreCase("February")) {
					comboBoxDay.removeAllItems();
					for (int i = 1; i <= 28; i++) {
						comboBoxDay.addItem(i);
					}
					comboBoxDay.setSelectedItem("");
					comboBoxDay.setBounds(300, 200, 100, 50);
				} else if (monthSelected.equalsIgnoreCase("April") || monthSelected.equalsIgnoreCase("June")
						|| monthSelected.equalsIgnoreCase("September") || monthSelected.equalsIgnoreCase("November")) {
					comboBoxDay.removeAllItems();
					for (int i = 1; i <= 30; i++) {
						comboBoxDay.addItem(i);
					}
					comboBoxDay.setSelectedItem("");
					comboBoxDay.setBounds(300, 200, 100, 50);
				} else if (monthSelected.equalsIgnoreCase("January") || monthSelected.equalsIgnoreCase("March")
						|| monthSelected.equalsIgnoreCase("May") || monthSelected.equalsIgnoreCase("July")
						|| monthSelected.equalsIgnoreCase("August") || monthSelected.equalsIgnoreCase("October")
						|| monthSelected.equalsIgnoreCase("December")) {
					comboBoxDay.removeAllItems();
					for (int i = 1; i <= 31; i++) {
						comboBoxDay.addItem(i);
					}
					comboBoxDay.setSelectedItem("");
					comboBoxDay.setBounds(300, 200, 100, 50);
				}
			}
		});

		buttonCancelAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameCancelAppointmentUser.setVisible(true);
				try {
					fileReader = new FileInputStream(patient.getID() + ".txt");
					reader = new Scanner(fileReader);
					String ID = reader.next();
					String name = reader.next();
					String DOB = reader.next();
					String insuranceNumber = reader.next();
					String gap = reader.nextLine();
					String medication = reader.nextLine();
					String doctorNotes = reader.nextLine();
					comboBoxAppointmentCancel.removeAllItems();
					System.out.println("all deleted");
					while (reader.hasNext()) {
						System.out.println("one while");
						comboBoxAppointmentCancel.addItem(reader.nextLine());
						System.out.println("ONE");
					}
				} catch (Exception exceptionNotFound) {
					System.out.println("Info not found");
				}
				reader.close();
			}
		});

		buttonConfirmCancelAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patient.appointmentCheckToCancel(patient.getID(),
						comboBoxAppointmentCancel.getSelectedItem().toString());
				frameCancelAppointmentUser.setVisible(false);
				textAreaUserAppointment.setText("");
				try {
					fileReader = new FileInputStream(patient.getID() + ".txt");
					reader = new Scanner(fileReader);
					String ID = reader.next();
					String name = reader.next();
					String DOB = reader.next();
					String insuranceNumber = reader.next();
					String gap = reader.nextLine();
					String medication = reader.nextLine();
					String doctorNotes = reader.nextLine();
					comboBoxAppointmentCancel.removeAllItems();
					System.out.println("all deleted");
					while (reader.hasNext()) {
						System.out.println("one while");
						comboBoxAppointmentCancel.addItem(reader.nextLine());
						System.out.println("ONE");
					}
				} catch (Exception exceptionNotFound) {
					System.out.println("Info not found");
				}
				reader.close();
			}
		});

		buttonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaUserAppointment.removeAll();
				textAreaUserAppointment.setText(patient.provideUserAppointments(patient.getID()));
				textFieldUserInsuranceNumber.setText(patient.getInsuranceNumber());
				textFieldMedicationPatient.setText(provider.provideMedicationNotesPatient(patient.getID()));
				textFieldNotesFromDoctor.setText(provider.provideDoctorNotesPatient(patient.getID()));
				try {
					fileReader = new FileInputStream(patient.getID() + ".txt");
					reader = new Scanner(fileReader);
					String ID = reader.next();
					String name = reader.next();
					String DOB = reader.next();
					String insuranceNumber = reader.next();
					String gap = reader.nextLine();
					String medication = reader.nextLine();
					String doctorNotes = reader.nextLine();
					comboBoxAppointmentCancel.removeAllItems();
					System.out.println("all deleted");
					while (reader.hasNext()) {
						System.out.println("one while");
						comboBoxAppointmentCancel.addItem(reader.nextLine());
						System.out.println("ONE");
					}
				} catch (Exception exceptionNotFound) {
					System.out.println("Info not found");
				}
				reader.close();
			}
		});

		buttonSearchPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameSearched = textFieldSearchPatient.getText();
				patient = new User("", "", "");
				provider = new Provider("", "", "");
				if (provider.getNamePatientList(nameSearched) == true) {
					System.out.println("Howdy");
					frameUser.setVisible(true);

					String IDSearched = provider.getSpecificIDPatient(nameSearched);
					patient.setID(IDSearched);
					String nameLastName = patient.provideUserNameLastName(IDSearched);
					patient.setName(nameLastName);
					textFieldUserNameLastname.setText(patient.getName());
					String DOB = patient.provideUserDOB(IDSearched);
					patient.setDOB(DOB);
					textFieldUserDOB.setText(patient.getDOB());
					String insuranceNumber = patient.provideUserInsuranceNumber(IDSearched);
					patient.setInsuranceNumber(insuranceNumber);
					textFieldUserInsuranceNumber.setText(patient.getInsuranceNumber());
					textAreaUserAppointment.setText(patient.provideUserAppointments(IDSearched));
				}
				if (provider.getNamePatientList(nameSearched) == false) {
					System.out.println("Wrong");
					JOptionPane.showMessageDialog(null, "Sorry, we have no information about that patient",
							"Ephesus Medical System Message", JOptionPane.PLAIN_MESSAGE);
				}

			}
		});

		buttonNewPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameProviderNewPatient.setVisible(true);
			}
		});

		buttonCreateNewPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				provider = new Provider("", "", "");
				if (provider.createNewPatient(textFieldCreateNewPatientName.getText(),
						textFieldCreateNewPatientDOB.getText(), textFieldCreateNewPatientInsurance.getText(),
						textFieldCreateNewPatientID.getText(), textFieldCreateNewPatientPassword.getText()) == true) {
					JOptionPane.showMessageDialog(null, "New patient created", "Ephesus Medical System Message",
							JOptionPane.PLAIN_MESSAGE);
					frameProviderNewPatient.setVisible(false);

				} else {
					JOptionPane.showMessageDialog(null, "Error creating a new patient",
							"Ephesus Medical System Message", JOptionPane.PLAIN_MESSAGE);
				}
				String numberOfPatients = Integer.toString(provider.provideTotalNumberPatients());
				textFieldTotalNumberPatients.setText(numberOfPatients);

			}
		});

		buttonCreateNewPatientHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Name and Lastname format: Williams,John \nDOB format: 01-01-1990 \nInsurance format: I000000000 \nUsername format: 6-8 alphanumeric characters \nPassword format: 8-12 letters, numbers, and symbols included",
						"Ephesus Medical System Message", JOptionPane.PLAIN_MESSAGE);
			}
		});

		buttonModifyPatientInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameEditInfoPatient.setVisible(true);
				textFieldEditInfoPatientName.setText(patient.getName());
				textFieldEditInfoPatientDOB.setText(patient.getDOB());
				textFieldEditInfoPatientInsurance.setText(patient.getInsuranceNumber());
			}
		});

		buttonUpdateFormInfoPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (provider.updateInfoPatient(patient.getID(), textFieldEditInfoPatientInsuranceUpdate.getText(),
						textFieldEditInfoPatientMedicationUpdate.getText(),
						textFieldEditInfoPatientNotesUpdate.getText()) == true) {
					JOptionPane.showMessageDialog(null, "The information was updated", "Ephesus Medical System Message",
							JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Error updating information", "Ephesus Medical System Message",
							JOptionPane.PLAIN_MESSAGE);
				}
				frameEditInfoPatient.setVisible(false);
				textFieldMedicationPatient.setText(provider.provideMedicationNotesPatient(patient.getID()));
				textFieldNotesFromDoctor.setText(provider.provideDoctorNotesPatient(patient.getID()));
			}
		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
