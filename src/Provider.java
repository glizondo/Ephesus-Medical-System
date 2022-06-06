import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A class representing the provider
 * 
 * @author Guillermo Lizondo
 * @version 1.0
 */
public class Provider {

	private String name;
	private String ID;
	private String DOB;
	private String regexUsername;
	private String regexPassword;
	static int countAttempts = 0;
	FileInputStream fileReader;
	Scanner reader;
	FileOutputStream fileStream;
	PrintWriter fileWriter;
	FileOutputStream fileStream2;
	PrintWriter fileWriter2;

	public Provider(String name, String iD, String dOB) {
		this.name = name;
		this.ID = iD;
		this.DOB = dOB;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String userName) {
		this.ID = userName;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	/**
	 * 
	 * @param username
	 * @return true if the input matches the pattern of 6-8 alphanumeric characters
	 */
	public boolean checkRegexUsername(String username) {
		regexUsername = "^([\\w\\d]){6,8}$";
		if (!Pattern.matches(regexUsername, username)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param password
	 * @return true if the input matches the pattern of 8-12 alphanumeric characters
	 *         including all symbols
	 */
	public boolean checkRegexPassword(String password) {
		regexPassword = "^([\\w\\d\\S]){8,12}$";
		if (!Pattern.matches(regexPassword, password)) {
			return false;
		}
		return true;
	}

	/**
	 * @param textFieldID
	 * @param textFieldPassword
	 * @return true if was able to log in
	 */
	public boolean checkLoginUser(String textFieldID, String textFieldPassword) {
		String currentID = "";
		String currentPassword = "";
		boolean logedIn = false;

		if (checkRegexUsername(textFieldID) == true && checkRegexPassword(textFieldPassword) == true) {
			try {
				fileReader = new FileInputStream("LoginProvider.txt");
				reader = new Scanner(fileReader);
				int numberUsers = reader.nextInt();

				if (reader.hasNext() && countAttempts < 10) {
					for (int i = 0; i < numberUsers; i++) {
						currentID = reader.next();
						currentPassword = reader.next();

						if (currentID.equals(textFieldID)) {

							if (currentPassword.equals(textFieldPassword)) {

								System.out.println("YOU LOGGED IN!");
								JOptionPane.showMessageDialog(null, "You logged in", "Ephesus Medical System Message",
										JOptionPane.PLAIN_MESSAGE);
								logedIn = true;
							}
						}

					}
					reader.close();

					if (!currentPassword.equals(textFieldID) && !currentID.equals(textFieldPassword)
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
	 * @param nameSearched
	 * @return true if name searched matched with LoginUser.txt
	 */
	public boolean getNamePatientList(String nameSearched) {
		boolean nameMatch = false;
		String currentIDList = "";
		String currentNameList = "";
		String currentPasswordList = "";
		try {
			fileReader = new FileInputStream("LoginUser.txt");
			reader = new Scanner(fileReader);
			int numberUsersList = reader.nextInt();
			while (reader.hasNext()) {
				for (int i = 0; i < numberUsersList; i++) {
					currentIDList = reader.next();
					currentNameList = reader.next();
					currentPasswordList = reader.next();

					if (currentNameList.equals(nameSearched)) {
						System.out.println("It was true");
						nameMatch = true;
						break;
					}
				}
				reader.close();

			}
		} catch (Exception exceptionNotFound) {
			System.out.println("Info not found");

		}

		return nameMatch;
	}

	/**
	 * 
	 * @param nameSearched
	 * @return String with the user id
	 */
	public String getSpecificIDPatient(String nameSearched) {
		String currentIDList = "";
		String currentNameList = "";
		String currentPasswordList = "";
		String matchedUserNameList = "";
		try {
			fileReader = new FileInputStream("LoginUser.txt");
			reader = new Scanner(fileReader);
			int numberUsersList = reader.nextInt();
			while (reader.hasNext()) {
				for (int i = 0; i < numberUsersList; i++) {
					currentIDList = reader.next();
					currentNameList = reader.next();
					currentPasswordList = reader.next();

					if (currentNameList.equals(nameSearched)) {
						System.out.println("It was true");
//						currentUserNameList = matchedUserNameList;
						break;
					}
				}
				reader.close();

			}
		} catch (Exception exceptionNotFound) {
			System.out.println("Info not found");

		}

		return currentIDList;
	}

	/**
	 * 
	 * @param name
	 * @param DOB
	 * @param insuranceNumber
	 * @param ID
	 * @param password
	 * @return true if it successfully created a new patient
	 */
	public boolean createNewPatient(String name, String DOB, String insuranceNumber, String ID, String password) {
		boolean wasCreated = false;
		ArrayList<String> listInfoToCopy = new ArrayList<String>();
		String infoCopied;
		int amountOfPatients = 0;
		if (checkRegexUsername(ID) && checkRegexPassword(password)) {

			try {
				fileStream = new FileOutputStream(ID + ".txt");
				fileWriter = new PrintWriter(fileStream);
				fileWriter.println(ID);
				fileWriter.println(name);
				fileWriter.println(DOB);
				fileWriter.println(insuranceNumber);
				fileWriter.println("No medication");
				fileWriter.println("There are no notes");
				wasCreated = true;
				fileWriter.close();
			} catch (Exception e) {
				System.out.println("Unable to write");
			}

			try {
				fileReader = new FileInputStream("LoginUser.txt");
				reader = new Scanner(fileReader);
				amountOfPatients = reader.nextInt();
				while (reader.hasNext()) {
					infoCopied = reader.nextLine();
					listInfoToCopy.add(infoCopied);
				}
				reader.close();

			} catch (FileNotFoundException exceptionLoginFile) {
				System.out.println("Unable to read");
			}

			try {
				fileStream2 = new FileOutputStream("LoginUser.txt");
				fileWriter2 = new PrintWriter(fileStream2);
				fileWriter2.print(amountOfPatients + 1);

				for (int i = 0; i < listInfoToCopy.size(); i++) {
					System.out.println(listInfoToCopy.get(i));
					fileWriter2.println(listInfoToCopy.get(i));
				}
				fileWriter2.println();
				fileWriter2.println(ID);
				fileWriter2.println(name);
				fileWriter2.println(password);
				fileWriter2.close();

			} catch (Exception a) {
				System.out.println("Unable to write second time");
			}
		} else {
			wasCreated = false;
		}

		return wasCreated;
	}

	/**
	 * 
	 * @return total number of patients read from the LoginUser.txt first integer
	 */
	public int provideTotalNumberPatients() {
		int totalNumberPatients = 0;
		try {
			fileReader = new FileInputStream("LoginUser.txt");
			reader = new Scanner(fileReader);
			totalNumberPatients = reader.nextInt();
		} catch (FileNotFoundException e) {
			System.out.println("Unable to read file");
		}

		return totalNumberPatients;
	}

	/**
	 * 
	 * @param ID
	 * @param newInsuranceNumber
	 * @param medicationNotes
	 * @param doctorNotes
	 * @return true if the info for the patient was updated
	 */
	public boolean updateInfoPatient(String ID, String newInsuranceNumber, String medicationNotes, String doctorNotes) {
		boolean changedInfo = false;
		ArrayList<String> listInfoToCopy2 = new ArrayList<String>();
		String infoCopied;
		String oldInsuranceNumber = "";
		String medication = "";
		String doctorNotesOriginal = "";
		try {
			fileReader = new FileInputStream(ID + ".txt");
			reader = new Scanner(fileReader);
			ID = reader.next();
			name = reader.next();
			DOB = reader.next();
			oldInsuranceNumber = reader.next();
			String gap = reader.nextLine();
			medication = reader.nextLine();
			doctorNotesOriginal = reader.nextLine();
			while (reader.hasNext()) {
				infoCopied = reader.nextLine();
				listInfoToCopy2.add(infoCopied);
			}

		} catch (FileNotFoundException e1) {
			System.out.println("Unable to read file");
			changedInfo = false;
		}

		try {
			fileStream = new FileOutputStream(ID + ".txt");
			fileWriter = new PrintWriter(fileStream);
			fileWriter.println(ID);
			fileWriter.println(name);
			fileWriter.println(DOB);
			if (!newInsuranceNumber.isEmpty()) {
				fileWriter.println(newInsuranceNumber);

			} else {
				fileWriter.println(oldInsuranceNumber);

			}
			if (!medicationNotes.isEmpty()) {
				fileWriter.println(medicationNotes);

			} else {
				fileWriter.println("No medication");
			}
			if (!doctorNotes.isEmpty()) {
				fileWriter.println(doctorNotes);

			} else {
				fileWriter.println("There are no notes");
			}

			for (int i = 0; i < listInfoToCopy2.size(); i++) {
				System.out.println(listInfoToCopy2.get(i));
				fileWriter.println(listInfoToCopy2.get(i));
			}
			changedInfo = true;

		} catch (FileNotFoundException e) {
			System.out.println("Unable to write on file");
			changedInfo = false;

		}
		fileWriter.close();
		reader.close();
		return changedInfo;
	}

	/**
	 * 
	 * @param ID
	 * @return String with medication notes from specific id txt file for each user
	 */
	public String provideMedicationNotesPatient(String ID) {
		String medication = "";
		try {
			fileReader = new FileInputStream(ID + ".txt");
			reader = new Scanner(fileReader);
			ID = reader.next();
			name = reader.next();
			DOB = reader.next();
			String insuranceNumber = reader.next();
			String gap = reader.nextLine();
			medication = reader.nextLine();
			String doctorNotes = reader.nextLine();

		} catch (FileNotFoundException e1) {
			System.out.println("Unable to read file");
		}
		return medication;
	}

	/**
	 * 
	 * @param ID
	 * @return String with doctor notes from specific id txt file for each user
	 */
	public String provideDoctorNotesPatient(String ID) {
		String doctorNotes = "";
		try {
			fileReader = new FileInputStream(ID + ".txt");
			reader = new Scanner(fileReader);
			ID = reader.next();
			name = reader.next();
			DOB = reader.next();
			String insuranceNumber = reader.next();
			String gap = reader.nextLine();
			String medication = reader.nextLine();
			doctorNotes = reader.nextLine();

		} catch (FileNotFoundException e1) {
			System.out.println("Unable to read file");
		}
		return doctorNotes;
	}

}
