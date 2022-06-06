import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;

/**
 * A class representing the patient that extends Provider class
 * 
 * @author Guillermo Lizondo
 * @version 1.0
 */
public class User extends Provider {

	private String name;
	private String ID;
	private String DOB;
	private String insuranceNumber;
	private String appointment;
	private String password;
	private String medication;
	private String doctorNotes;
	private String regexUsername;
	private String regexPassword;
	static int countAttempts = 0;
	FileInputStream fileReader;
	FileOutputStream fileStream;
	PrintWriter fileWriter;
	Scanner reader;
	Boolean[][][] calendar = new Boolean[13][32][24];
	String[] listAppointmentsCancel;

	public User(String name, String iD, String dOB) {
		super(name, iD, dOB);
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	@Override
	public boolean checkLoginUser(String textFieldID, String textFieldPassword) {
		String currentUserName = "";
		String currentName;
		String currentPassword = "";
		boolean logedIn = false;

		if (checkRegexUsername(textFieldID) == true && checkRegexPassword(textFieldPassword) == true) {
			try {
				fileReader = new FileInputStream("LoginUser.txt");
				reader = new Scanner(fileReader);
				int numberUsers = reader.nextInt();

				if (reader.hasNext() && countAttempts < 10) {
					for (int i = 0; i < numberUsers; i++) {
						currentUserName = reader.next();
						System.out.println(currentUserName + "username");
						currentName = reader.next();
						System.out.println(currentName + "name");
						currentPassword = reader.next();
						System.out.println(currentPassword + "password");
						if (currentUserName.equals(textFieldID)) {
							if (currentPassword.equals(textFieldPassword)) {
								System.out.println("YOU LOGGED IN!");
								JOptionPane.showMessageDialog(null, "You logged in", "Ephesus Medical System Message",
										JOptionPane.PLAIN_MESSAGE);
								logedIn = true;
							}
						}
					}
					reader.close();

					if (!currentPassword.equals(textFieldPassword) && currentUserName.equals(textFieldID)
							&& logedIn == false) {
						JOptionPane.showMessageDialog(null, "Wrong credentials", "Ephesus Medical System Message",
								JOptionPane.PLAIN_MESSAGE);
						countAttempts++;
						logedIn = false;
					}
				}
			} catch (FileNotFoundException exceptionLoginFile) {
				System.out.println("There was an error in the system, please try again");
			}
		}
		if (checkRegexUsername(textFieldID) == false || checkRegexPassword(textFieldPassword) == false) {
			JOptionPane.showMessageDialog(null,
					"Username requirements: 6-8 alphanumeric characters \nPassword requirements: 8-12 letters, numbers, and symbols included",
					"Ephesus Medical System Message", JOptionPane.PLAIN_MESSAGE);
			countAttempts++;
		}
		return logedIn;
	}

	/**
	 * 
	 * @param ID
	 * @return String with name and last name for patient
	 */
	public String provideUserNameLastName(String ID) {
		try {
			fileReader = new FileInputStream(ID + ".txt");
			reader = new Scanner(fileReader);
			ID = reader.next();
			name = reader.next();
		} catch (Exception exceptionNotFound) {
			System.out.println("Info not found");
		}
		reader.close();
		return name;
	}

	/**
	 * 
	 * @param ID
	 * @return String with Date of Birth for patient
	 */
	public String provideUserDOB(String ID) {
		try {
			fileReader = new FileInputStream(ID + ".txt");
			reader = new Scanner(fileReader);
			ID = reader.next();
			name = reader.next();
			DOB = reader.next();
		} catch (Exception exceptionNotFound) {
			System.out.println("Info not found");
		}
		reader.close();
		return DOB;
	}

	/**
	 * 
	 * @param ID
	 * @return String with insurance number for patient
	 */
	public String provideUserInsuranceNumber(String ID) {
		try {
			fileReader = new FileInputStream(ID + ".txt");
			reader = new Scanner(fileReader);
			ID = reader.next();
			name = reader.next();
			DOB = reader.next();
			insuranceNumber = reader.next();
		} catch (Exception exceptionNotFound) {
			System.out.println("Info not found");
		}
		reader.close();
		return insuranceNumber;
	}

	/**
	 * 
	 * @param ID
	 * @return String with all the appointments read from the specific user txt file
	 *         under the id as title
	 */
	public String provideUserAppointments(String ID) {
		try {
			fileReader = new FileInputStream(ID + ".txt");
			reader = new Scanner(fileReader);
			ID = reader.next();
			name = reader.next();
			DOB = reader.next();
			insuranceNumber = reader.next();
			String gap = reader.nextLine();
			medication = reader.nextLine();
			doctorNotes = reader.nextLine();
			appointment = reader.nextLine();
			while (reader.hasNext()) {
				appointment += "\n" + reader.nextLine();
			}

		} catch (Exception exceptionNotFound) {
			System.out.println("Info not found");
		}
		reader.close();
		return appointment;
	}

	/**
	 * 
	 * @param ID
	 * @param year
	 * @param month
	 * @param day
	 * @param hour  Adds appointments to the user id specific txt file
	 */
	public void addAppointment(String ID, Integer year, String month, Integer day, Integer hour) {
		ArrayList<String> listInfoToCopy = new ArrayList<String>();
		String infoCopied;
		try {
			fileReader = new FileInputStream(ID + ".txt");
			reader = new Scanner(fileReader);
			ID = reader.next();
			name = reader.next();
			DOB = reader.next();
			insuranceNumber = reader.next();
			String gap = reader.nextLine();
			medication = reader.nextLine();
			doctorNotes = reader.nextLine();
			while (reader.hasNext()) {
				infoCopied = reader.nextLine();
				listInfoToCopy.add(infoCopied);
			}
		} catch (FileNotFoundException e1) {
			System.out.println("Did not work");
		}
		try {
			fileStream = new FileOutputStream(ID + ".txt");
			fileWriter = new PrintWriter(fileStream);
			fileWriter.println(ID);
			fileWriter.println(name);
			fileWriter.println(DOB);
			fileWriter.println(insuranceNumber);
			fileWriter.println(medication);
			fileWriter.println(doctorNotes);
			fileWriter.println(month + ", " + day + " " + year + " at " + hour + "h");
			for (int i = 0; i < listInfoToCopy.size(); i++) {
				System.out.println(listInfoToCopy.get(i));
				fileWriter.println(listInfoToCopy.get(i));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Did not work");
		}
		fileWriter.close();
	}

	/**
	 * 
	 * @param ID
	 * @return array for the list of appointments available to be cancelled
	 */
	public String[] provideListAppointmentsCancel(String ID) {
		int count = 0;
		String[] listAppointmentsCancel = new String[5];
		try {
			fileReader = new FileInputStream(ID + ".txt");
			reader = new Scanner(fileReader);
			ID = reader.next();
			name = reader.next();
			DOB = reader.next();
			insuranceNumber = reader.next();
			String gap = reader.nextLine();
			medication = reader.nextLine();
			doctorNotes = reader.nextLine();
			System.out.println("Doctor notes " + doctorNotes);
			while (reader.hasNext()) {
				listAppointmentsCancel[count] = reader.nextLine();
				count++;
				System.out.println(count);
			}
		} catch (Exception exceptionNotFound) {
			System.out.println("Info not found");
		}
		reader.close();
		return listAppointmentsCancel;
	}

	/**
	 * 
	 * @param ID
	 * @param appointmentChose
	 * @return true if the appointment was successfully cancelled
	 */
	public boolean appointmentCheckToCancel(String ID, String appointmentChose) {
		String appointmentRead;
		String validAppointment = "";
		ArrayList<String> goodAppointments = new ArrayList<String>();
		boolean appointmentCancelExists = false;
		try {
			fileReader = new FileInputStream(ID + ".txt");
			reader = new Scanner(fileReader);
			ID = reader.next();
			name = reader.next();
			DOB = reader.next();
			insuranceNumber = reader.next();
			String gap = reader.nextLine();
			medication = reader.nextLine();
			doctorNotes = reader.nextLine();
			while (reader.hasNext()) {
				appointmentRead = reader.nextLine();
				if (!appointmentRead.equalsIgnoreCase(appointmentChose) && !appointmentRead.equalsIgnoreCase("")) {
					goodAppointments.add(appointmentRead);
					System.out.println("Copied");
					appointmentCancelExists = false;
				}
			}

		} catch (Exception exceptionNotFound) {
			System.out.println("Info not found");
		}
		try {
			fileStream = new FileOutputStream(ID + ".txt");
			fileWriter = new PrintWriter(fileStream);
			fileWriter.println(ID);
			fileWriter.println(name);
			fileWriter.println(DOB);
			fileWriter.println(insuranceNumber);
			fileWriter.println(medication);
			fileWriter.println(doctorNotes);
			for (int i = 0; i < goodAppointments.size(); i++) {
				fileWriter.println(goodAppointments.get(i));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Did not work");
		}
		fileWriter.close();
		reader.close();
		return appointmentCancelExists;
	}

}
